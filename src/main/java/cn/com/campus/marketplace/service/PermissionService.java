package cn.com.campus.marketplace.service;

import cn.com.campus.marketplace.entity.Permission;
import cn.com.campus.marketplace.entity.vo.RolePermissionVo;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface PermissionService extends IService<Permission> {
    List<RolePermissionVo> findPermissionByRoleId(Integer roleId);
    Boolean verifyPermission(String token, String permission);
    Page<Permission> pagePermissionList(Integer current, Integer size, String queryInfo);
}
