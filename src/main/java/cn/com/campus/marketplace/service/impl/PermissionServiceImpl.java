package cn.com.campus.marketplace.service.impl;

import cn.com.campus.marketplace.entity.Permission;
import cn.com.campus.marketplace.mapper.PermissionMapper;
import cn.com.campus.marketplace.service.PermissionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements PermissionService {
}
