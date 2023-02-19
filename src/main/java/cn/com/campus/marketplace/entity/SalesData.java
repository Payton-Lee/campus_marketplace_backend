package cn.com.campus.marketplace.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("cm_sales_data")
public class SalesData {
    private Integer id;
    private Integer goodsId;
    @TableField(exist = false)
    private String goods;
    private Integer count;
}
