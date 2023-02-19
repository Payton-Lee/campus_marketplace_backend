package cn.com.campus.marketplace.entity.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum PermissionCode {
    PC101(101, "权限列表"),
    PC102(102, "角色列表"),
    PC103(103, "新增角色"),
    PC104(104, "修改角色"),
    PC105(105, "删除角色"),
    PC106(106, "角色授权撤权"),
    PC107(107, "用户列表"),
    PC108(108, "用户状态"),
    PC109(109, "角色名字"),
    PC110(110, "分配角色"),
    PC111(111, "商品列表"),
    PC112(112, "新增商品"),
    PC113(113, "编辑商品"),
    PC114(114, "商品上架"),
    PC115(115, "删除商品"),
    PC116(116, "图片列表"),
    PC117(117, "新增图片"),
    PC118(118, "修改图片"),
    PC119(119, "删除图片"),
    PC120(120, "订单列表"),
    PC121(121, "订单发货"),
    PC122(122, "修改地址"),
    PC123(123, "销售数据"),
    PC124(124, "商品浏览"),
    PC125(125, "创建订单"),
    PC126(126, "订单付款");


    public final int code;
    public final String name;
}

