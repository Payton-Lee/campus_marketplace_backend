package cn.com.campus.marketplace.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("cm_cart")
public class Cart {
    private Integer id;
    private Integer userId;
    private Integer goodsId;
    private Integer count;
    @TableField(exist = false)
    private String goods;
    @TableField(exist = false)
    private String image;
    @TableField(exist = false)
    private Double price;
}
