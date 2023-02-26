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
            "LEFT JOIN cm_goods as g ON g.id = o.order_id \n" +
            "INNER JOIN cm_goods_image as i ON i.goods_id = o.goods_id AND i.type = 1 AND o.order_id = #{orderId}")
    List<OrderItem> findOrderItemListByOrderId(Integer orderId);
}
