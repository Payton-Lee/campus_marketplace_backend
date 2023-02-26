package cn.com.campus.marketplace.mapper;

import cn.com.campus.marketplace.entity.User;
import cn.com.campus.marketplace.entity.vo.UserRoleVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper extends BaseMapper<User> {
    @Select("SELECT \n" +
            "cm_user.id AS userId, \n" +
            "cm_role.id AS roleId, \n" +
            "cm_user.username, \n" +
            "cm_role.`name` AS role \n" +
            "FROM \n" +
            "cm_user, cm_role, cm_user_role \n" +
            "WHERE \n" +
            "cm_user.id = cm_user_role.user_id \n" +
            "AND \n" +
            "cm_role.id = cm_user_role.role_id \n" +
            "AND \n" +
            "cm_user.id = #{userId};")
    UserRoleVo findUserRoleByUserId(Integer userId);
}
