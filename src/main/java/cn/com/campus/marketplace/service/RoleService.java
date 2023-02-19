package cn.com.campus.marketplace.service;

import cn.com.campus.marketplace.entity.Role;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

public interface RoleService extends IService<Role> {
    Page<Role> pageRoleList(Integer current, Integer size, String queryInfo);
}
