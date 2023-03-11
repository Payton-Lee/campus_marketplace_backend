package cn.com.campus.marketplace.service.impl;

import cn.com.campus.marketplace.entity.Permission;
import cn.com.campus.marketplace.entity.vo.RolePermissionVo;
import cn.com.campus.marketplace.mapper.PermissionMapper;
import cn.com.campus.marketplace.service.PermissionService;
import cn.com.campus.marketplace.service.RoleService;
import cn.com.campus.marketplace.service.UserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements PermissionService {

    @Autowired
    private PermissionMapper permissionMapper;
    @Autowired
    private RoleService roleService;
    @Autowired
    private UserService userService;
    @Override
    public List<RolePermissionVo> findPermissionByRoleId(Integer roleId) {
        return permissionMapper.findPermissionByRoleId(roleId);
    }

    @Override
    public Boolean verifyPermission(String token, String permission) {
        var userRoleVos = roleService.findRoleListByUserId(userService.getTokenUserId(token));
        return userRoleVos.stream().flatMap(userRoleVo -> findPermissionByRoleId(userRoleVo.getRoleId()).stream())
                .anyMatch(rolePermissionVo -> rolePermissionVo.getPermission().equals(permission));
    }

    @Override
    public Page<Permission> pagePermissionList(Integer current, Integer size, String queryInfo) {
        QueryWrapper<Permission> wrapper = new QueryWrapper<>();
        Page<Permission> page = new Page<>(current, size);
        if(!StringUtils.isEmpty(queryInfo)) {
            wrapper.like("name", queryInfo);
        }
        return page(page, wrapper);
    }
}
