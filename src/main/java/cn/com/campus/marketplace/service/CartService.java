package cn.com.campus.marketplace.service;

import cn.com.campus.marketplace.entity.Cart;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface CartService extends IService<Cart> {
    boolean addCart(Cart cart);
    List<Cart> getCartListByUserId(Integer userId);
}
