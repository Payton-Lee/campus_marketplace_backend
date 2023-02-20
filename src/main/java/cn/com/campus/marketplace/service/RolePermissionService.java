package cn.com.campus.marketplace.service;

import cn.com.campus.marketplace.entity.RolePermission;
import com.baomidou.mybatisplus.extension.service.IService;

public interface RolePermissionService extends IService<RolePermission> {
    Boolean deleteRolePermissionByRoleId(Integer roleId);
    Long getRolePermissionCount(Integer roleId);
    Boolean saveRolePermission(Integer roleId, String permissionIds);
}
