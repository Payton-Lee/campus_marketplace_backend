package cn.com.campus.marketplace.service;

import cn.com.campus.marketplace.entity.OrderItem;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface OrderItemService extends IService<OrderItem> {
    List<OrderItem> listGoodsByOrderId(Integer orderId);
    List<OrderItem> getOrderItemListByUserId(Integer userId, Integer orderId);
}
