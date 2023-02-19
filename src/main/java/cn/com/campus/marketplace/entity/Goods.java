package cn.com.campus.marketplace.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("cm_goods")
public class Goods {
    private Integer id;
    private String goods;
    private double price;
    private Integer inStock;
    private String productNumber;
    private String goodsIntroduce;
    private Integer isDeleted;
    private LocalDateTime addTime;
    private LocalDateTime updateTime;
    private LocalDateTime deleteTime;
    private Integer goodsState;
}
