package cn.com.campus.marketplace;

import cn.com.campus.marketplace.entity.vo.UserRoleVo;
import cn.com.campus.marketplace.service.GoodsService;
import cn.com.campus.marketplace.service.RoleService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class MarketplaceApplicationTests {

	private GoodsService goodsService;
	@Autowired
	private RoleService roleService;

	@Test
	public void contextLoads() {
		List<UserRoleVo> userRoleVoList = roleService.findRoleListByUserId(1);
	}

}
