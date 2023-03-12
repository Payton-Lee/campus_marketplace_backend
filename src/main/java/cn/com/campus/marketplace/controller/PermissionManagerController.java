package cn.com.campus.marketplace.controller;

import cn.com.campus.marketplace.entity.Role;
import cn.com.campus.marketplace.entity.enums.PermissionCode;
import cn.com.campus.marketplace.entity.enums.ReturnCode;
import cn.com.campus.marketplace.entity.result.ResultData;
import cn.com.campus.marketplace.service.PermissionService;
import cn.com.campus.marketplace.service.RolePermissionService;
import cn.com.campus.marketplace.service.RoleService;
import cn.hutool.core.lang.Dict;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/permission")
public class PermissionManagerController {
    @Autowired
    private RoleService roleService;
    @Autowired
    private PermissionService permissionService;
    @Autowired
    private RolePermissionService rolePermissionService;

    @GetMapping("/permissionList")
    public Object permissionList(HttpServletRequest request, @RequestParam Integer current, @RequestParam Integer size, @RequestParam String queryInfo) {
        if(permissionService.verifyPermission(request.getHeader("Authorization"), PermissionCode.PC101.name)) {
            return permissionService.pagePermissionList(current, size, queryInfo);
        }else{
            return ResultData.fail(ReturnCode.ACCESS_DENIED.code, ReturnCode.ACCESS_DENIED.message);
        }
    }

    @GetMapping("/roleList")
    public Object roleList(HttpServletRequest request, @RequestParam Integer current, @RequestParam Integer size, @RequestParam String queryInfo) {
        if(permissionService.verifyPermission(request.getHeader("Authorization"), PermissionCode.PC102.name)) {
            return roleService.pageRoleList(current, size, queryInfo);
        } else {
            return ResultData.fail(ReturnCode.ACCESS_DENIED.code, ReturnCode.ACCESS_DENIED.message);
        }
    }

    @PostMapping("/newRole")
    public Object newRole(HttpServletRequest request, @RequestBody Role role) {
        Dict result = Dict.create();
        if(permissionService.verifyPermission(request.getHeader("Authorization"), PermissionCode.PC103.name)) {
            if (roleService.save(role)) {
                result.set("msg", "创建角色成功").set("role", role.getName());
                return ResultData.success(ReturnCode.RC201.code, ReturnCode.RC200.message, result);
            } else {
                return ResultData.fail(ReturnCode.RC999.code, ReturnCode.RC999.message);
            }
        } else {
            return ResultData.fail(ReturnCode.ACCESS_DENIED.code, ReturnCode.ACCESS_DENIED.message);
        }
    }

    @PutMapping("/editRole")
    public Object editRole(HttpServletRequest request, @RequestBody Role role) {
        Dict data = Dict.create();
        if(permissionService.verifyPermission(request.getHeader("Authorization"), PermissionCode.PC104.name)) {
            if(roleService.updateById(role)) {
                data.set("msg", "修改角色成功").set("role", role.getName());
                return ResultData.success(ReturnCode.RC201.code, ReturnCode.RC201.message, data);
            } else {
                return ResultData.fail(ReturnCode.RC999.code, ReturnCode.RC999.message);
            }
        } else {
            return ResultData.fail(ReturnCode.ACCESS_DENIED.code, ReturnCode.ACCESS_DENIED.message);
        }
    }
    @DeleteMapping("/deleteRole/{roleId}")
    public Object deleteRole(HttpServletRequest request, @PathVariable Integer roleId) {
        if(permissionService.verifyPermission(request.getHeader("Authorization"), PermissionCode.PC105.name)) {
            Dict data = Dict.create();
            Boolean flag = false;
            if(rolePermissionService.getRolePermissionCount(roleId) != 0) {
                flag = rolePermissionService.deleteRolePermissionByRoleId(roleId);
            } else {
                flag = true;
            }
            if(flag) {
                if(roleService.getById(roleId) != null) {
                    try {
                        if (roleService.removeById(roleId)){
                            data.set("msg", "删除角色成功");
                            return ResultData.success(ReturnCode.RC201.code, ReturnCode.RC201.message, data);
                        } else {
                            data.set("msg", "删除角色失败");
                            return ResultData.fail(ReturnCode.RC999.code, ReturnCode.RC999.message, data);
                        }
                    } catch(Exception e) {
                        data.set("msg", "删除角色失败");
                        return ResultData.fail(ReturnCode.RC999.code, ReturnCode.RC999.message, data);
                    }
                } else {
                    data.set("msg", "角色不存在");
                    return ResultData.fail(ReturnCode.RC999.code, ReturnCode.RC999.message, data);
                }
            } else  {
                return ResultData.fail(ReturnCode.RC999.code, ReturnCode.RC999.message);
            }
        }else {
            return ResultData.fail(ReturnCode.ACCESS_DENIED.code, ReturnCode.ACCESS_DENIED.message);
        }
    }

    @GetMapping("/rolePermissionList")
    public  Object listRolePermission(HttpServletRequest request) {
        if(permissionService.verifyPermission(request.getHeader("Authorization"), PermissionCode.PC101.name)) {
            return ResultData.success(ReturnCode.RC200.code, ReturnCode.RC200.message, permissionService.list());
        } else {
            return ResultData.fail(ReturnCode.ACCESS_DENIED.code, ReturnCode.ACCESS_DENIED.message);
        }
    }

    @GetMapping("/singleRolePermission/{roleId}")
    public Object listSingleRolePermission(HttpServletRequest request, @PathVariable Integer roleId) {
        if(permissionService.verifyPermission(request.getHeader("Authorization"), PermissionCode.PC101.name)) {
            return ResultData.success(ReturnCode.RC200.code, ReturnCode.RC200.message, permissionService.findPermissionByRoleId(roleId));
        } else {
            return ResultData.fail(ReturnCode.ACCESS_DENIED.code, ReturnCode.ACCESS_DENIED.message);
        }
    }

    @PutMapping("/{roleId}/rolePermission")
    public Object editRolePermission(HttpServletRequest request, @PathVariable Integer roleId, @RequestParam String permissionIds) {
        if(permissionService.verifyPermission(request.getHeader("Authorization"), PermissionCode.PC106.name)) {
            Dict data =  Dict.create();
            boolean flag = false;
            if(permissionIds.length() == 0) {
                flag = rolePermissionService.deleteRolePermissionByRoleId(roleId);
            } else {
                if(rolePermissionService.getRolePermissionCount(roleId) != 0) {
                    if(rolePermissionService.deleteRolePermissionByRoleId(roleId)) {
                        flag = rolePermissionService.saveRolePermission(roleId, permissionIds);
                    }
                } else {
                    flag = rolePermissionService.saveRolePermission(roleId, permissionIds);
                }
            }
            if(flag) {
                data.set("msg", "角色权限分配成功");
                return ResultData.success(ReturnCode.RC201.code, ReturnCode.RC201.message, data);
            }
            return ResultData.fail(ReturnCode.RC999.code, ReturnCode.RC999.message);
        } else {
            return ResultData.fail(ReturnCode.ACCESS_DENIED.code, ReturnCode.ACCESS_DENIED.message);
        }
    }
}
