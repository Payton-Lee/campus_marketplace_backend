package cn.com.campus.marketplace.mapper;

import cn.com.campus.marketplace.entity.Goods;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface GoodsMapper extends BaseMapper<Goods> {
    @Select("SELECT g.id, g.goods, g.price, g.in_stock as inStock, " +
            "g.product_number as productNumber,\n" +
            "g.goods_introduce as goodsIntroduce,\n" +
            "g.is_deleted as isDeleted, g.add_time as addTime, \n" +
            "i.image, g.update_time as updateTime, g.delete_time as deleteTime, \n" +
            "g.goods_state as goodsState FROM cm_goods as g \n" +
            "LEFT JOIN cm_goods_image as i ON g.id = i.goods_id AND i.type = 1 ${ew.customSqlSegment}")
    List<Goods> findGoodsList(@Param(Constants.WRAPPER) QueryWrapper<Goods> queryWrapper);

    @Select("SELECT g.id, g.goods, g.price, g.in_stock as inStock, " +
            "g.product_number as productNumber,\n" +
            "g.goods_introduce as goodsIntroduce,\n" +
            "g.is_deleted as isDeleted, g.add_time as addTime, \n" +
            "i.image, g.update_time as updateTime, g.delete_time as deleteTime, \n" +
            "g.goods_state as goodsState FROM cm_goods as g \n" +
            "LEFT JOIN cm_goods_image as i ON g.id = i.goods_id AND i.type = 1 ${ew.customSqlSegment}")
    <P extends IPage<Goods>> P findGoodsList(P page, @Param(Constants.WRAPPER) QueryWrapper<Goods> queryWrapper);
}
