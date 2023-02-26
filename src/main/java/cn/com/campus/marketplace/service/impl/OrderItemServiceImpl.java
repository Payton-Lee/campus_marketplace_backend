package cn.com.campus.marketplace.service.impl;


import cn.com.campus.marketplace.entity.OrderItem;
import cn.com.campus.marketplace.mapper.OrderItemMapper;
import cn.com.campus.marketplace.service.OrderItemService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderItemServiceImpl extends ServiceImpl<OrderItemMapper, OrderItem> implements OrderItemService {
    @Autowired
    private OrderItemMapper orderItemMapper;
    @Override
    public List<OrderItem> listGoodsByOrderId(Integer orderId) {
        return orderItemMapper.findOrderItemListByOrderId(orderId);
    }
}
