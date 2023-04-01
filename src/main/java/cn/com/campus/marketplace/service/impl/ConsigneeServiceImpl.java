package cn.com.campus.marketplace.service.impl;

import cn.com.campus.marketplace.entity.Consignee;
import cn.com.campus.marketplace.entity.enums.ReturnCode;
import cn.com.campus.marketplace.entity.result.ResultData;
import cn.com.campus.marketplace.entity.vo.UserConsigneeVo;
import cn.com.campus.marketplace.mapper.ConsigneeMapper;
import cn.com.campus.marketplace.service.ConsigneeService;
import cn.hutool.core.lang.Dict;
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

    @Override
    public Object saveNewConsignee(Consignee consignee) {
        Dict result = Dict.create();
        if (save(consignee)) {
            result.set("msg", "新增地址成功").set("consignee", findUserConsigneeByUserId(consignee.getUserId()));
            return result;
        }
        return ResultData.fail(ReturnCode.RC999.code, ReturnCode.RC999.message);
    }
}
