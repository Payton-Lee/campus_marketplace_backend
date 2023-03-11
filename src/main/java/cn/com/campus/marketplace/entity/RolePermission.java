package cn.com.campus.marketplace.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("cm_role_permission")
public class RolePermission {
    private Integer id;
    private Integer roleId;
    private Integer permissionId;
}
