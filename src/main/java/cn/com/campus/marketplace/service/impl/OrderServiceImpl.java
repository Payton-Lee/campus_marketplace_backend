package cn.com.campus.marketplace.service.impl;

import cn.com.campus.marketplace.entity.Order;
import cn.com.campus.marketplace.mapper.OrderMapper;
import cn.com.campus.marketplace.service.OrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {
}
