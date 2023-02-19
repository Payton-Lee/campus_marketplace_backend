package cn.com.campus.marketplace.mapper;

import cn.com.campus.marketplace.entity.Role;
import cn.com.campus.marketplace.entity.vo.UserRoleVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface RoleMapper extends BaseMapper<Role> {
    @Select("SELECT \n" +
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
    List<UserRoleVo> findRoleListByUserId(Integer userId);
}
