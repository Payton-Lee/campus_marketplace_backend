package cn.com.campus.marketplace.service.impl;

import cn.com.campus.marketplace.entity.Cart;
import cn.com.campus.marketplace.mapper.CartMapper;
import cn.com.campus.marketplace.service.CartService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class CartServiceImpl extends ServiceImpl<CartMapper, Cart> implements CartService {
}
