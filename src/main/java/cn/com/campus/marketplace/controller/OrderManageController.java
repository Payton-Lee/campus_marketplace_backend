package cn.com.campus.marketplace.controller;

import cn.com.campus.marketplace.entity.Order;
import cn.com.campus.marketplace.entity.enums.PermissionCode;
import cn.com.campus.marketplace.entity.enums.ReturnCode;
import cn.com.campus.marketplace.entity.result.ResultData;
import cn.com.campus.marketplace.service.ConsigneeService;
import cn.com.campus.marketplace.service.OrderItemService;
import cn.com.campus.marketplace.service.OrderService;
import cn.com.campus.marketplace.service.PermissionService;
import cn.hutool.core.lang.Dict;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

@RestController
@RequestMapping("/order")
public class OrderManageController {
    private OrderService orderService;
    private PermissionService permissionService;
    private ConsigneeService consigneeService;
    private OrderItemService orderItemService;
    @Autowired
    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }
    @Autowired
    public void setPermissionService(PermissionService permissionService) {
        this.permissionService = permissionService;
    }

    @Autowired
    public void setConsigneeService(ConsigneeService consigneeService) {
        this.consigneeService = consigneeService;
    }

    @Autowired
    public void setOrderItemService(OrderItemService orderItemService) {
        this.orderItemService = orderItemService;
    }
    @GetMapping("/orderList")
    public Object orderList(HttpServletRequest request, @RequestParam Integer current, @RequestParam Integer size) {
        if(permissionService.verifyPermission(request.getHeader("Authorization"), PermissionCode.PC120.name)) {
            return orderService.pageOrderList(current, size);
        } else {
            return ResultData.fail(ReturnCode.ACCESS_DENIED.code, ReturnCode.ACCESS_DENIED.message);
        }
    }

    @PostMapping("/getGoodsByOrderId/{orderId}")
    public Object getGoodsByOrderId(HttpServletRequest request, @PathVariable Integer orderId) {
        if(permissionService.verifyPermission(request.getHeader("Authorization"), PermissionCode.PC120.name)) {
            Dict data = Dict.create();
            Order orderInDB = orderService.getById(orderId);
            if(!Objects.isNull(orderInDB)) {
                return orderItemService.listGoodsByOrderId(orderId);
            } else {
                data.set("msg", "订单不存在");
                return ResultData.fail(ReturnCode.ORDER_NO_EXIST.code, ReturnCode.ORDER_NO_EXIST.message, data);
            }
        } else {
            return ResultData.fail(ReturnCode.ACCESS_DENIED.code, ReturnCode.ACCESS_DENIED.message);
        }
    }

    @PutMapping("/{orderId}/orderSend/{isSend}")
    public Object orderSend(HttpServletRequest request, @PathVariable Integer orderId, @PathVariable Integer isSend) {
        if(permissionService.verifyPermission(request.getHeader("Authorization"), PermissionCode.PC121.name)) {
            Dict data = Dict.create();
            Order orderInDB = orderService.getById(orderId);
            if (orderInDB.getPayStatus() != 1) {
                orderInDB.setIsSend(isSend);
                if(orderService.updateById(orderInDB)) {
                    data.set("msg", "发货成功");
                    return ResultData.success(ReturnCode.RC201.code, ReturnCode.RC201.message, data);
                } else {
                    return ResultData.fail(ReturnCode.RC999.code, ReturnCode.RC999.message);
                }
            } else {
                data.set("msg", "订单未付款");
                return ResultData.fail(ReturnCode.RC999.code, ReturnCode.RC999.message, data);
            }
        } else {
            return ResultData.fail(ReturnCode.ACCESS_DENIED.code, ReturnCode.ACCESS_DENIED.message);
        }
    }


    @GetMapping("/currentConsignee")
    public Object getCurrentConsigneeById(HttpServletRequest request, @RequestParam Integer consigneeId) {
        if(permissionService.verifyPermission(request.getHeader("Authorization"), PermissionCode.PC122.name)) {
            return consigneeService.findUserConsigneeByConsigneeId(consigneeId);
        } else {
            return ResultData.fail(ReturnCode.ACCESS_DENIED.code, ReturnCode.ACCESS_DENIED.message);
        }
    }

    @GetMapping("/consigneeList")
    public Object getConsigneeListByUserId(HttpServletRequest request, @RequestParam Integer userId) {
        if(permissionService.verifyPermission(request.getHeader("Authorization"), PermissionCode.PC122.name)) {
            return consigneeService.findUserConsigneeByUserId(userId);
        } else {
            return ResultData.fail(ReturnCode.ACCESS_DENIED.code, ReturnCode.ACCESS_DENIED.message);
        }
    }

    @PutMapping("/{orderId}/changeConsignee/{consigneeId}")
    public Object setConsigneeChange(HttpServletRequest request, @PathVariable Integer orderId, @PathVariable Integer consigneeId) {
        if(permissionService.verifyPermission(request.getHeader("Authorization"), PermissionCode.PC122.name)) {
            Order orderInDB = orderService.getById(orderId);
            Dict data = Dict.create();
            if(orderInDB.getIsSend() == 2) {
                data.set("msg", "订单已发货，无法修改地址！");
                return ResultData.fail(ReturnCode.RC999.code, ReturnCode.RC999.message, data);
            } else {
                orderInDB.setConsigneeId(consigneeId);
                if(orderService.updateById(orderInDB)) {
                    data.set("msg", "订单地址修改成功！").set("order", orderInDB);
                    return ResultData.success(ReturnCode.RC201.code, ReturnCode.RC201.message, data);
                } else {
                    return ResultData.fail(ReturnCode.RC999.code, ReturnCode.RC999.message);
                }
            }
        } else {
            return ResultData.fail(ReturnCode.ACCESS_DENIED.code, ReturnCode.ACCESS_DENIED.message);
        }
    }
}
