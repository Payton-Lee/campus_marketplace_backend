package cn.com.campus.marketplace.service;

import cn.com.campus.marketplace.entity.Role;
import cn.com.campus.marketplace.entity.vo.UserRoleVo;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface RoleService extends IService<Role> {
    List<UserRoleVo> findRoleListByUserId(Integer userId);
    Page<Role> pageRoleList(Integer current, Integer size, String queryInfo);
}
