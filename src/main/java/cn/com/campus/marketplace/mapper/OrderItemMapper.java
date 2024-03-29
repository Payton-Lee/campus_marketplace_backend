package cn.com.campus.marketplace.mapper;

import cn.com.campus.marketplace.entity.OrderItem;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface OrderItemMapper extends BaseMapper<OrderItem> {
    @Select("SELECT o.id, o.order_id as orderId, \n" +
            "o.goods_id AS goodsId, g.goods, \n" +
            "g.price, o.count, i.image FROM\n" +
            "cm_order_item as o \n" +
            "LEFT JOIN cm_goods as g ON g.id = o.goods_id \n" +
            "INNER JOIN cm_goods_image as i ON i.goods_id = o.goods_id " +
            "AND i.type = 1 AND o.order_id = #{orderId}")
    List<OrderItem> findOrderItemListByOrderId(Integer orderId);

    @Select("SELECT oi.id, oi.order_id as orderId,\n" +
            "oi.goods_id as goodsId, g.goods, g.price, oi.count,\n" +
            "i.image FROM \n" +
            "cm_order_item AS oi, cm_order AS o, cm_goods AS g, cm_goods_image AS i \n" +
            "WHERE g.id = oi.goods_id AND i.goods_id = oi.goods_id \n" +
            "AND oi.order_id = o.id AND o.user_id = #{userId} AND oi.order_id = #{orderId};")
    List<OrderItem> findOrderItemListByOrderIdAndUserId(Integer userId, Integer orderId);
}
