package cn.com.campus.marketplace;

import cn.com.campus.marketplace.entity.Goods;
import cn.com.campus.marketplace.entity.vo.UserRoleVo;
import cn.com.campus.marketplace.mapper.GoodsMapper;
import cn.com.campus.marketplace.service.GoodsService;
import cn.com.campus.marketplace.service.RoleService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class MarketplaceApplicationTests {

	@Autowired
	private GoodsService goodsService;
	@Autowired
	private RoleService roleService;
	@Autowired
	private GoodsMapper goodsMapper;

	@Test
	public void contextLoads() {
		List<UserRoleVo> userRoleVoList = roleService.findRoleListByUserId(1);
	}

	@Test
	public void getAllGoodsList() {
		List<Goods> goodsList = goodsMapper.findGoodsListById(new Integer[]{1, 2, 3, 4, 5, 6});
		for (Goods goods: goodsList) {
			System.out.println(goods.toString());
		}
	}

}
