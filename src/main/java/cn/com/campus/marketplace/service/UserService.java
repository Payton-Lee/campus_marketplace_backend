package cn.com.campus.marketplace.service;

import cn.com.campus.marketplace.entity.User;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

public interface UserService extends IService<User> {
    User getByUserName(String userName);
    Boolean isExist(String userName);
    String getToken(User user);
    String getEncryptedPassword(String password);
    Boolean verifyPassword(String password, String pw_hash);
    Boolean register(User user);
    User getUserExceptPassword(Integer userId);
    Integer getTokenUserId(String token);
    Page<User> pageUserExceptPassword(Integer current, Integer size, String queryInfo);
//    UserRoleVo findUserRoleByUserId(Integer userId);
}
