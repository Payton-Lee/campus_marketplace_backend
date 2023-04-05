package cn.com.campus.marketplace.service.impl;

import cn.com.campus.marketplace.entity.SalesData;
import cn.com.campus.marketplace.mapper.SalesDataMapper;
import cn.com.campus.marketplace.service.SalesDataService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SalesDataServiceImpl extends ServiceImpl<SalesDataMapper, SalesData> implements SalesDataService {
    @Autowired
    private SalesDataMapper salesDataMapper;
    @Override
    public List<SalesData> getAllSalesData() {
        return salesDataMapper.getSalesDataList();
    }

    @Override
    public List<SalesData> getSalesDataListByGoodsIds(List<Integer> ids) {
        Integer[] arrays = ids.toArray(new Integer[0]);
        return salesDataMapper.getSalesDataListByGoodsId(arrays);
    }
}
