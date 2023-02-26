package cn.com.campus.marketplace.mapper;

import cn.com.campus.marketplace.entity.Image;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ImageMapper extends BaseMapper<Image> {
    @Select("SELECT i.id AS id, g.goods, i.image, i.type, i.numerical_order FROM cm_goods_image AS i " +
            "LEFT JOIN cm_goods AS g ON g.id = i.goods_id ${ew.customSqlSegment}")
    List<Image> getGoodsImage(@Param(Constants.WRAPPER) Wrapper<Image> queryWrapper);
}
