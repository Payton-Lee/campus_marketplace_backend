package cn.com.campus.marketplace.service.impl;

import cn.com.campus.marketplace.entity.UserRole;
import cn.com.campus.marketplace.mapper.UserRoleMapper;
import cn.com.campus.marketplace.service.UserRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements UserRoleService {
}
