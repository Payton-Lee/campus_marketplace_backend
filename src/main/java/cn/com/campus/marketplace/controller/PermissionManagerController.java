package cn.com.campus.marketplace.controller;

import cn.com.campus.marketplace.service.PermissionService;
import cn.com.campus.marketplace.service.RolePermissionService;
import cn.com.campus.marketplace.service.RoleService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/permission")
public class PermissionManagerController {
    private RoleService roleService;
    private PermissionService permissionService;
    private RolePermissionService rolePermissionService;
}
