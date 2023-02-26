package cn.com.campus.marketplace.service;

import cn.com.campus.marketplace.entity.Goods;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

public interface GoodsService extends IService<Goods> {
    Page<Goods> pageGoodsList(Integer current, Integer size, String queryInfo);
}
