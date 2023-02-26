package cn.com.campus.marketplace.service.impl;

import cn.com.campus.marketplace.entity.Cart;
import cn.com.campus.marketplace.mapper.CartMapper;
import cn.com.campus.marketplace.service.CartService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class CartServiceImpl extends ServiceImpl<CartMapper, Cart> implements CartService {
    private CartMapper cartMapper;
    @Override
    public boolean addCart(Cart cart) {
        QueryWrapper<Cart> cartQueryWrapper = new QueryWrapper<>();
        cartQueryWrapper.eq("user_id", cart.getUserId());
        cartQueryWrapper.eq("goods_id", cart.getGoodsId());
        Cart cartInDB = getOne(cartQueryWrapper);
        if (Objects.isNull(cartInDB)) {
            return save(cart);
        } else {
            cartInDB.setCount(cartInDB.getCount() + cart.getCount());
            return updateById(cartInDB);
        }
    }

    @Override
    public List<Cart> getCartListByUserId(Integer userId) {
        return cartMapper.findCartListByUserId(userId);
    }
}
