package cn.com.campus.marketplace.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cart {
    private Integer id;
    private Integer userId;
    private Integer goodsId;
    private Integer count;
    @TableField(exist = false)
    private String goods;
    @TableField(exist = false)
    private String image;
}
