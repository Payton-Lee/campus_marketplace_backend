package cn.com.campus.marketplace.mapper;

import cn.com.campus.marketplace.entity.Consignee;
import cn.com.campus.marketplace.entity.vo.UserConsigneeVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ConsigneeMapper extends BaseMapper<Consignee> {
    @Select("SELECT cm_consignee.id AS id, \n" +
            "cm_consignee.`name` AS receiver, \n" +
            "cm_consignee.telephone, \n" +
            "area.`name` AS area, \n" +
            "cm_consignee.address \n" +
            "FROM\n" +
            "cm_consignee, \n" +
            "area \n" +
            "WHERE \n" +
            "area.`id` = cm_consignee.area \n" +
            "AND \n" +
            "cm_consignee.id = #{consigneeId};")
    UserConsigneeVo findUserConsigneeByConsigneeId(Integer consigneeId);

    @Select("SELECT cm_consignee.id AS id, \n" +
            "cm_consignee.`name` AS receiver, \n" +
            "cm_consignee.telephone, \n" +
            "cm_area.`area` AS area, \n" +
            "cm_consignee.address \n" +
            "FROM\n" +
            "cm_consignee, \n" +
            "cm_area \n" +
            "WHERE \n" +
            "cm_area.`id` = cm_consignee.area \n" +
            "AND \n" +
            "cm_consignee.user_id = #{userId};")
    List<UserConsigneeVo> findUserConsigneeByUserId(Integer userId);
}
