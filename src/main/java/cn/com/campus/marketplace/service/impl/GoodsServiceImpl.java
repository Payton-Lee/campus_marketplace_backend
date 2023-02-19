package cn.com.campus.marketplace.service.impl;

import cn.com.campus.marketplace.entity.Goods;
import cn.com.campus.marketplace.mapper.GoodsMapper;
import cn.com.campus.marketplace.service.GoodsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class GoodsServiceImpl extends ServiceImpl<GoodsMapper, Goods> implements GoodsService {
}
