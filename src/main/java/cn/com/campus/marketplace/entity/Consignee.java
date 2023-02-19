package cn.com.campus.marketplace.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("cm_consignee")
public class Consignee {
    private Integer id;
    private Integer userId;
    private String name;
    private String area;
    private String address;
    private String telephone;
}
