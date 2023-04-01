package cn.com.campus.marketplace.mapper;

import cn.com.campus.marketplace.entity.SalesData;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface SalesDataMapper extends BaseMapper<SalesData> {
    @Select("SELECT cm_sales_data.id, cm_goods.goods, " +
            "cm_sales_data.count, cm_sales_data.goods_id " +
            "goodsId from cm_sales_data, cm_goods " +
            "WHERE cm_sales_data.goods_id = cm_goods.id")
    List<SalesData> getSalesDataList();
}
