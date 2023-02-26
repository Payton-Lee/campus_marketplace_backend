package cn.com.campus.marketplace.service;

import cn.com.campus.marketplace.entity.Order;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

public interface OrderService extends IService<Order> {
    Page<Order> pageOrderList(Integer current, Integer size);
}
