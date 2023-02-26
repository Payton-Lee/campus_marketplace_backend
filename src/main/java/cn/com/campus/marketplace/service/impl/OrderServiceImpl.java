package cn.com.campus.marketplace.service.impl;

import cn.com.campus.marketplace.entity.Order;
import cn.com.campus.marketplace.mapper.OrderMapper;
import cn.com.campus.marketplace.service.OrderService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {

    private OrderMapper orderMapper;

    @Autowired
    protected void setOrderMapper(OrderMapper orderMapper) {
        this.orderMapper = orderMapper;
    }
    @Override
    public Page<Order> pageOrderList(Integer current, Integer size) {

        QueryWrapper<Order> wrapper = new QueryWrapper<>();
        Page<Order> page = new Page<>(current, size);
        return orderMapper.findOrder(page, wrapper);
    }
}
