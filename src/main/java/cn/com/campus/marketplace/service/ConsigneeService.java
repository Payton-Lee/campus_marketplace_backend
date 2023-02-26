package cn.com.campus.marketplace.service;

import cn.com.campus.marketplace.entity.Consignee;
import cn.com.campus.marketplace.entity.vo.UserConsigneeVo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface ConsigneeService extends IService<Consignee> {
    UserConsigneeVo findUserConsigneeByConsigneeId(Integer consigneeId);
    List<UserConsigneeVo> findUserConsigneeByUserId(Integer userId);
}
