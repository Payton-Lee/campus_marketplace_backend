package cn.com.campus.marketplace.mapper;

import cn.com.campus.marketplace.entity.Permission;
import cn.com.campus.marketplace.entity.vo.RolePermissionVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface PermissionMapper extends BaseMapper<Permission> {
    @Select("SELECT \n" +
            "cm_permission.id AS permissionId, \n" +
            "cm_permission.`name` AS permission, \n" +
            "cm_role.`name` AS role, \n" +
            "cm_permission.type AS type \n" +
            "FROM \n" +
            "cm_permission, cm_role, cm_role_permission \n" +
            "WHERE\n" +
            "cm_role.id = cm_role_permission.role_id \n" +
            "AND\n" +
            "cm_permission.id = cm_role_permission.permission_id \n" +
            "AND\n" +
            "cm_role.id = #{roleId};")
    List<RolePermissionVo> findPermissionByRoleId(Integer roleId);
}
