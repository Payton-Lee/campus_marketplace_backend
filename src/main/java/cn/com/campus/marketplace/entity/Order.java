package cn.com.campus.marketplace.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("cm_order")
public class Order {
    private Integer id;
    private Integer userId;
    @TableField(exist = false)
    private String username;
    private String orderNumber;
    private double orderPrice;
    private Integer payStatus;
    private Integer isSend;
    private Integer consigneeId;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
