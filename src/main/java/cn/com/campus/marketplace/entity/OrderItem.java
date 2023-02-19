package cn.com.campus.marketplace.entity;

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
    private Integer count;
}
