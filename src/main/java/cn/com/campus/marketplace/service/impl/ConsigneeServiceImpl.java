package cn.com.campus.marketplace.service.impl;

import cn.com.campus.marketplace.entity.Consignee;
import cn.com.campus.marketplace.mapper.ConsigneeMapper;
import cn.com.campus.marketplace.service.ConsigneeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class ConsigneeServiceImpl extends ServiceImpl<ConsigneeMapper, Consignee> implements ConsigneeService {
}
