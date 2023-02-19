package cn.com.campus.marketplace.service.impl;

import cn.com.campus.marketplace.entity.RolePermission;
import cn.com.campus.marketplace.mapper.RolePermissionMapper;
import cn.com.campus.marketplace.service.RolePermissionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class RolePermissionServiceImpl extends ServiceImpl<RolePermissionMapper, RolePermission> implements RolePermissionService {
}
