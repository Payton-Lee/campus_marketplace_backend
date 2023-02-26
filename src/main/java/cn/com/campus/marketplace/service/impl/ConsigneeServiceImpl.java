package cn.com.campus.marketplace.service.impl;

import cn.com.campus.marketplace.entity.Consignee;
import cn.com.campus.marketplace.entity.vo.UserConsigneeVo;
import cn.com.campus.marketplace.mapper.ConsigneeMapper;
import cn.com.campus.marketplace.service.ConsigneeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConsigneeServiceImpl extends ServiceImpl<ConsigneeMapper, Consignee> implements ConsigneeService {

    @Autowired
    private ConsigneeMapper consigneeMapper;
    @Override
    public UserConsigneeVo findUserConsigneeByConsigneeId(Integer consigneeId) {
        return consigneeMapper.findUserConsigneeByConsigneeId(consigneeId);
    }

    @Override
    public List<UserConsigneeVo> findUserConsigneeByUserId(Integer userId) {
        return consigneeMapper.findUserConsigneeByUserId(userId);
    }
}
