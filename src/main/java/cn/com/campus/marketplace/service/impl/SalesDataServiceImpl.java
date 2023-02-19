package cn.com.campus.marketplace.service.impl;

import cn.com.campus.marketplace.entity.SalesData;
import cn.com.campus.marketplace.mapper.SalesDataMapper;
import cn.com.campus.marketplace.service.SalesDataService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class SalesDataServiceImpl extends ServiceImpl<SalesDataMapper, SalesData> implements SalesDataService {
}
