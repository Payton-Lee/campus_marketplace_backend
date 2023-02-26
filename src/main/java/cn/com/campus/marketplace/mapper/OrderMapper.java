package cn.com.campus.marketplace.mapper;

import cn.com.campus.marketplace.entity.Order;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface OrderMapper extends BaseMapper<Order> {
    @Select("SELECT o.id, o.user_id as userId, \n" +
            "u.username,o.order_number as orderNumber,\n" +
            "o.pay_status as payStatus, \n" +
            "o.order_price as orderPrice, \n" +
            "o.is_send as isSend, o.consignee_id as consigneeId, \n" +
            "o.create_time as createTime, o.update_time as updateTime\n" +
            "FROM cm_order as o LEFT JOIN cm_user as u ON o.user_id = u.id ${ew.customSqlSegment}")
    <P extends IPage<Order>> P findOrder(P page, @Param(Constants.WRAPPER) Wrapper<Order> queryWrapper);
}
