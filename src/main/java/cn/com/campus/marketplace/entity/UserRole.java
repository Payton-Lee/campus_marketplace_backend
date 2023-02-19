package cn.com.campus.marketplace.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("cm_user_role")
public class UserRole {
    private Integer id;
    private Integer userId;
    private Integer roleId;
}
