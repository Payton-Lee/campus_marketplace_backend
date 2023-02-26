package cn.com.campus.marketplace.service;

import cn.com.campus.marketplace.entity.Cart;
import cn.com.campus.marketplace.entity.Goods;
import cn.com.campus.marketplace.entity.Order;
import cn.com.campus.marketplace.entity.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface OrderService extends IService<Order> {
    Page<Order> pageOrderList(Integer current, Integer size);

    Boolean createNewOrder(Goods goods, Integer consigneeId, Integer count, Integer userId);
    List<Order> getOrderListByUserId(Integer userId);

    Boolean commitOrderBatch(List<Cart> cartList, Integer userId, Integer consigneeId);
}
