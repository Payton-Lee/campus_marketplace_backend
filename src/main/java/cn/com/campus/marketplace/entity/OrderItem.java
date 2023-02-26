package cn.com.campus.marketplace.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("cm_order_item")
public class OrderItem {
    private Integer id;
    private Integer orderId;
    private Integer goodsId;
    @TableField(exist = false)
    private String goods;
    @TableField(exist = false)
    private String image;
    @TableField(exist = false)
    private Double price;
    private Integer count;
}
