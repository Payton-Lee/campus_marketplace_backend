package cn.com.campus.marketplace.mapper;

import cn.com.campus.marketplace.entity.Cart;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CartMapper extends BaseMapper<Cart> {
    @Select("SELECT c.id, c.goods_id as goodsId, c.count, c.user_id as userId,\n" +
            "g.goods, g.price, i.image \n" +
            "FROM cm_cart as c LEFT JOIN cm_goods as g ON c.goods_id = g.id \n" +
            "INNER JOIN cm_goods_image as i \n" +
            "ON c.goods_id = i.goods_id AND c.user_id = #{userId}")
    List<Cart> findCartListByUserId(Integer userId);
}
