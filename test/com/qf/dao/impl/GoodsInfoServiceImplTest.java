package com.qf.dao.impl;

import static org.junit.Assert.*;

import org.junit.Test;

import com.qf.entity.GoodsInfo;
import com.qf.entity.Page;
import com.qf.service.IGoodsInfoService;
import com.qf.service.impl.GoodsInfoServiceImpl;

public class GoodsInfoServiceImplTest {

	private IGoodsInfoService goodsInfoService = new GoodsInfoServiceImpl();
	
	@Test
	public void testAdd() {
		GoodsInfo t = new GoodsInfo();
		t.setGoods_name("热狗");
		t.setGoods_description("热狗desc");
		t.setGoods_discount(0.8);
		t.setGoods_fatherid(1);
		t.setGoods_parentid(2);
		t.setGoods_pic("xxx.png");
		t.setGoods_price(3.0);
		t.setGoods_price_off(2.5);
		t.setGoods_stock(10);
		System.out.println(goodsInfoService.add(t));
	}

	@Test
	public void testUpdate() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetById() {
		fail("Not yet implemented");
	}

	@Test
	public void testDelete() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetList() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetPage() {
		Page<GoodsInfo> page = new Page<GoodsInfo>();
		goodsInfoService.getPage(page);;
		System.out.println(page);
	}

}
