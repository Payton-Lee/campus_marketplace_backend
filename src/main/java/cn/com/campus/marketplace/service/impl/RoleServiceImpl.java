package cn.com.campus.marketplace.service.impl;

import cn.com.campus.marketplace.entity.Role;
import cn.com.campus.marketplace.mapper.RoleMapper;
import cn.com.campus.marketplace.service.RoleService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

public class RoleServiceImpl  extends ServiceImpl<RoleMapper, Role> implements RoleService{
    @Override
    public Page<Role> pageRoleList(Integer current, Integer size, String queryInfo) {
        QueryWrapper<Role> wrapper = new QueryWrapper<>();
        Page<Role> page = new Page<>(current,size);
        if(!StringUtils.isEmpty(queryInfo)) {
            wrapper.like("name", queryInfo);
        }
        return page(page, wrapper);
    }
}
