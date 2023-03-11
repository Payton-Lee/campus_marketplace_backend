package cn.com.campus.marketplace.controller;

import cn.com.campus.marketplace.entity.User;
import cn.com.campus.marketplace.entity.enums.ReturnCode;
import cn.com.campus.marketplace.entity.result.ResultData;
import cn.com.campus.marketplace.entity.vo.UserRoleVo;
import cn.com.campus.marketplace.service.PermissionService;
import cn.com.campus.marketplace.service.RoleService;
import cn.com.campus.marketplace.service.UserService;
import cn.hutool.core.lang.Dict;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.swing.plaf.PanelUI;
import java.time.LocalDateTime;
import java.util.List;

@RestController
public class LoginRegController {
    private UserService userService;
    private RoleService roleService;
    private PermissionService permissionService;
    @Autowired
    public void setUserService(UserService userService){
        this.userService = userService;
    }
    @Autowired
    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }

    @Autowired
    public void setPermissionService(PermissionService permissionService) {
        this.permissionService = permissionService;
    }

    @GetMapping("/index")
    public String index(){
        return "hello world!";
    }

    @PostMapping("/admin/login")
    public Object adminLogin(@RequestBody User user) {
        Dict result = Dict.create();
        System.out.println();
        if(!userService.isExist(user.getUsername())){
            return ResultData.fail(ReturnCode.USERNAME_ERROR.code, ReturnCode.USERNAME_ERROR.message);
        } else {
            User userInDB = userService.getByUserName(user.getUsername());
            List<UserRoleVo> userRoleVos = roleService.findRoleListByUserId(user.getId());
            if(userRoleVos.stream().flatMap(userRoleVo -> permissionService.findPermissionByRoleId(userRoleVo.getRoleId()).stream())
                    .anyMatch(rolePermissionVo -> rolePermissionVo.getType() == 3)) {
                return ResultData.fail(ReturnCode.USER_DENIED.code, ReturnCode.USER_DENIED.message);
            }
            // 判断当前用户是否可以用
            if(userInDB.getState() != 1) {
                return ResultData.fail(ReturnCode.USER_DENIED.code, ReturnCode.USER_DENIED.message);
            } else {
                // 对密码进行hash判断
                if (userService.verifyPassword(user.getPassword(), userInDB.getPassword())) {
                    String token = userService.getToken(userInDB);
                    result.set("username", userInDB.getUsername()).set("msg", "登录成功").set("token", token);
                    return ResultData.success(ReturnCode.RC200.code, ReturnCode.RC200.message, result);
                } else {
                    return ResultData.fail(ReturnCode.PASSWORD_ERROR.code, ReturnCode.PASSWORD_ERROR.message);
                }
            }
        }
    }

    @PostMapping("/admin/register")
    public Object adminRegister(@RequestBody User user) {
        Dict result = Dict.create();
        ResultData<Object> resultData;
        if(userService.isExist(user.getUsername())) {
            // 判断用户是否存在，存在则不允许注册
            resultData = ResultData.fail(ReturnCode.USERNAME_EXIST.code, ReturnCode.USERNAME_EXIST.message);
        } else {
            // 把密码加密
            user.setPassword(userService.getEncryptedPassword(user.getPassword()));
            // 设置状态，默认值为0
            if(user.getState() == null) {
                user.setState(0);
            }
            // 设置当前用户的创建时间
            user.setCreateTime(LocalDateTime.now());
            if(userService.register(user)){
                result.set("username", user.getUsername()).set("msg", "注册成功, 等待管理员审核！");
                resultData = ResultData.success(ReturnCode.RC201.code, ReturnCode.RC201.message, result);
            } else {
                result.set("msg", "注册失败，请重试！");
                resultData = ResultData.fail(ReturnCode.RC999.code, ReturnCode.RC999.message, result);
            }
        }
        return resultData;
    }

    @PostMapping("/user/login")
    public Object userLogin(@RequestBody User user) {
        Dict result = Dict.create();
        System.out.println();
        if(!userService.isExist(user.getUsername())){
            return ResultData.fail(ReturnCode.USERNAME_ERROR.code, ReturnCode.USERNAME_ERROR.message);
        } else {
            User userInDB = userService.getByUserName(user.getUsername());
            var userRoleVos = roleService.findRoleListByUserId(user.getId());
            if(userRoleVos.stream().flatMap(userRoleVo -> permissionService.findPermissionByRoleId(userRoleVo.getRoleId()).stream())
                    .anyMatch(rolePermissionVo -> rolePermissionVo.getType() != 3)) {
                return ResultData.fail(ReturnCode.USER_DENIED.code, ReturnCode.USER_DENIED.message);
            }
            // 判断当前用户是否可以用
            if(userInDB.getState() != 1) {
                return ResultData.fail(ReturnCode.USER_DENIED.code, ReturnCode.USER_DENIED.message);
            } else {
                // 对密码进行hash判断
                if (userService.verifyPassword(user.getPassword(), userInDB.getPassword())) {
                    String token = userService.getToken(userInDB);
                    result.set("username", userInDB.getUsername()).set("msg", "登录成功").set("token", token);
                    return ResultData.success(ReturnCode.RC200.code, ReturnCode.RC200.message, result);
                } else {
                    return ResultData.fail(ReturnCode.PASSWORD_ERROR.code, ReturnCode.PASSWORD_ERROR.message);
                }
            }
        }
    }

    @PostMapping("/user/register")
    public Object userRegister(@RequestBody User user) {
        Dict result = Dict.create();
        ResultData<Object> resultData;
        if(userService.isExist(user.getUsername())) {
            // 判断用户是否存在，存在则不允许注册
            resultData = ResultData.fail(ReturnCode.USERNAME_EXIST.code, ReturnCode.USERNAME_EXIST.message);
        } else {
            // 把密码加密
            user.setPassword(userService.getEncryptedPassword(user.getPassword()));
            // 设置状态，默认值为0
            if(user.getState() == null) {
                user.setState(1);
            }
            // 设置当前用户的创建时间
            user.setCreateTime(LocalDateTime.now());
            if(userService.register(user)){
                result.set("username", user.getUsername()).set("msg", "注册成功, 请登录！");
                resultData = ResultData.success(ReturnCode.RC201.code, ReturnCode.RC201.message, result);
            } else {
                result.set("msg", "注册失败，请重试！");
                resultData = ResultData.fail(ReturnCode.RC999.code, ReturnCode.RC999.message, result);
            }
        }
        return resultData;
    }

}
