package cn.com.campus.marketplace.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserConsigneeVo {
    private Integer id;
    private String receiver;
    private String telephone;
    private String area;
    private String address;
}
