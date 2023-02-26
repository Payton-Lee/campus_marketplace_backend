package cn.com.campus.marketplace.service;

import cn.com.campus.marketplace.entity.UserRole;
import com.baomidou.mybatisplus.extension.service.IService;

public interface UserRoleService extends IService<UserRole> {
    Boolean deleteUserRoleByUserId(Integer userId);
    Boolean saveUserRole(Integer userId, String roleIds);
    //    List<UserRole> getUserRoleByUserId(Integer userId);
    Long getUserRoleCount(Integer userId);
}
