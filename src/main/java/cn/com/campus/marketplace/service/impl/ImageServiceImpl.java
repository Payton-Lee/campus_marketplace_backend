package cn.com.campus.marketplace.service.impl;

import cn.com.campus.marketplace.entity.Image;
import cn.com.campus.marketplace.mapper.ImageMapper;
import cn.com.campus.marketplace.service.ImageService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImageServiceImpl extends ServiceImpl<ImageMapper, Image> implements ImageService {

    private ImageMapper imageMapper;
    @Override
    public List<Image> findGoodsImage(Integer goodsId) {
        QueryWrapper<Image> wrapper = new QueryWrapper<>();
        wrapper.eq("goods_id", goodsId);
        return imageMapper.getGoodsImage(wrapper);
    }
}
