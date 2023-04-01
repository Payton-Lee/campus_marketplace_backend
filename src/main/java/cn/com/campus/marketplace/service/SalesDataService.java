package cn.com.campus.marketplace.service;

import cn.com.campus.marketplace.entity.SalesData;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface SalesDataService extends IService<SalesData> {
    List<SalesData> getAllSalesData();
}
