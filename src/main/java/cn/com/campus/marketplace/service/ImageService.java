package cn.com.campus.marketplace.service;

import cn.com.campus.marketplace.entity.Image;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface ImageService extends IService<Image> {
    List<Image> findGoodsImage(Integer goodsId);
}
