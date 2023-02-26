package cn.com.campus.marketplace.controller;

import cn.com.campus.marketplace.entity.Cart;
import cn.com.campus.marketplace.entity.Consignee;
import cn.com.campus.marketplace.entity.Goods;
import cn.com.campus.marketplace.entity.enums.PermissionCode;
import cn.com.campus.marketplace.entity.enums.ReturnCode;
import cn.com.campus.marketplace.entity.result.ResultData;
import cn.com.campus.marketplace.service.*;
import cn.hutool.core.lang.Dict;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/buy")
public class ShoppingController {
    private OrderItemService orderItemService;
    private OrderService orderService;
    private CartService cartService;
    private GoodsService goodsService;
    private PermissionService permissionService;
    private UserService userService;
    private ConsigneeService consigneeService;

    @Autowired
    public void setPermissionService(PermissionService permissionService) {
        this.permissionService = permissionService;
    }

    @GetMapping("/goodsList")
    public Object getGoodsList(HttpServletRequest request, @RequestParam String queryInfo) {
        if (permissionService.verifyPermission(request.getHeader("Authorization"), PermissionCode.PC124.name)) {
            return goodsService.getGoodsList(queryInfo);
        } else {
            return ResultData.fail(ReturnCode.USERNAME_NOT_EXIST.code, ReturnCode.USERNAME_NOT_EXIST.message);
        }
    }

    @PostMapping("/buyOneGoods")
    public Object buyOneGoods(HttpServletRequest request, @RequestBody Map<String, Integer> orderMap) {
        if (permissionService.verifyPermission(request.getHeader("Authorization"), PermissionCode.PC126.name)) {
            Dict data = Dict.create();
            int goodsId = orderMap.get("goodsId");
            int consigneeId = orderMap.get("consigneeId");
            int count = orderMap.get("count");
            Goods goodsInDB = goodsService.getById(goodsId);
            if (Objects.isNull(goodsInDB)) {
                return ResultData.fail(ReturnCode.GOODS_NO_EXIST.code, ReturnCode.GOODS_NO_EXIST.message);
            }
            Consignee consigneeInDB = consigneeService.getById(consigneeId);
            if (Objects.isNull(consigneeInDB)) {
                return ResultData.fail(ReturnCode.CONSIGNEE_NO_EXIST.code, ReturnCode.CONSIGNEE_NO_EXIST.message);
            }
            int userId = userService.getTokenUserId(request.getHeader("Authorization"));
            if (orderService.createNewOrder(goodsInDB, consigneeId, count, userId)) {
                data.set("msg", "购买成功");
                return ResultData.success(ReturnCode.RC201.code, ReturnCode.RC201.message, data);
            }
            return ResultData.fail(ReturnCode.RC999.code, ReturnCode.RC999.message);
        } else {
            return ResultData.fail(ReturnCode.USERNAME_NOT_EXIST.code, ReturnCode.USERNAME_NOT_EXIST.message);
        }
    }

    @PostMapping("/addCart")
    public Object addCart(HttpServletRequest request, @RequestBody Cart cart) {
        if (permissionService.verifyPermission(request.getHeader("Authorization"), PermissionCode.PC127.name)) {
            int userId = userService.getTokenUserId(request.getHeader("Authorization"));
            cart.setUserId(userId);
            Dict data = Dict.create();
            if (cartService.addCart(cart)) {
                data.set("msg", "添加购物车成功");
                return ResultData.success(ReturnCode.RC201.code, ReturnCode.RC200.message, data);
            }
            return ResultData.fail(ReturnCode.RC999.code, ReturnCode.RC999.message);
        } else {
            return ResultData.fail(ReturnCode.USERNAME_NOT_EXIST.code, ReturnCode.USERNAME_NOT_EXIST.message);
        }
    }

    @GetMapping("/getCartList")
    public Object getCartList(HttpServletRequest request) {
        if (permissionService.verifyPermission(request.getHeader("Authorization"), PermissionCode.PC127.name)) {
            int userId = userService.getTokenUserId(request.getHeader("Authorization"));
            return cartService.getCartListByUserId(userId);
        } else {
            return ResultData.fail(ReturnCode.USERNAME_NOT_EXIST.code, ReturnCode.USERNAME_NOT_EXIST.message);
        }
    }

    @GetMapping("/getOrderList")
    public Object getOrderList(HttpServletRequest request) {
        if (permissionService.verifyPermission(request.getHeader("Authorization"), PermissionCode.PC128.name)) {
            int userId = userService.getTokenUserId(request.getHeader("Authorization"));
            return orderService.getOrderListByUserId(userId);
        } else {
            return ResultData.fail(ReturnCode.USERNAME_NOT_EXIST.code, ReturnCode.USERNAME_NOT_EXIST.message);
        }
    }

    @GetMapping("/getOrderItemList/{orderId}")
    public Object getOrderItemList(HttpServletRequest request, @PathVariable Integer orderId) {
        if (permissionService.verifyPermission(request.getHeader("Authorization"), PermissionCode.PC128.name)) {
            int userId = userService.getTokenUserId(request.getHeader("Authorization"));
            return orderItemService.getOrderItemListByUserId(userId, orderId);
        } else {
            return ResultData.fail(ReturnCode.USERNAME_NOT_EXIST.code, ReturnCode.USERNAME_NOT_EXIST.message);
        }
    }

    @PostMapping("/commitOrderBatch/{consigneeId}")
    public Object commitOrderBatch(HttpServletRequest request, @RequestBody List<Cart> cartList, @PathVariable Integer consigneeId) {
        if (permissionService.verifyPermission(request.getHeader("Authorization"), PermissionCode.PC125.name)) {
            int userId = userService.getTokenUserId(request.getHeader("Authorization"));
            Consignee consigneeInDB = consigneeService.getById(consigneeId);
            if (Objects.isNull(consigneeInDB)) {
                return ResultData.fail(ReturnCode.CONSIGNEE_NO_EXIST.code, ReturnCode.CONSIGNEE_NO_EXIST.message);
            }
            if (orderService.commitOrderBatch(cartList, userId, consigneeId)) {
                Dict data = Dict.create();
                data.set("msg", "购买成功");
                return ResultData.success(ReturnCode.RC201.code, ReturnCode.RC200.message, data);
            }
            return ResultData.fail(ReturnCode.RC999.code, ReturnCode.RC999.message);
        } else {
            return ResultData.fail(ReturnCode.USERNAME_NOT_EXIST.code, ReturnCode.USERNAME_NOT_EXIST.message);
        }
    }

}
