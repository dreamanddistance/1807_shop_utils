package com.qf.service.impl;

import org.junit.Test;

import com.qf.entity.GoodsType;
import com.qf.entity.Page;
import com.qf.service.IGoodsTypeService;

public class GoodsTypeServiceImplTest {

	private IGoodsTypeService goodsTypeService = new GoodsTypeServiceImpl();

	@Test
	public void testAdd() {

		GoodsType t = new GoodsType();
		t.setGoods_type_name("童装");
		t.setGoods_type_parentid(2);
		t.setGoods_type_pic("ab.png");
		System.out.println(goodsTypeService.add(t));
	}

	@Test
	public void testUpdate() {
		GoodsType t = new GoodsType();
		t.setGoods_type_name("儿装");
		t.setGoods_type_parentid(2);
		t.setGoods_type_pic("ab1.png");
		t.setId(7);
		System.out.println(goodsTypeService.update(t));
	}

	@Test
	public void testGetById() {
		System.out.println(goodsTypeService.getById(72));
	}

	@Test
	public void testDelete() {
		System.out.println(goodsTypeService.delete(7));
	}

	@Test
	public void testGetList() {
		String id = "1";
		Integer id2 = 1;
		// System.out.println(id.equals(id2.toString()));
		// System.out.println(id2.equals(id));
		Integer a = 128;
		Integer b = 128;
		// System.out.println(a.equals(b));
		System.out.println(a == b);
	}

	@Test
	public void testGetPage() {
		Page<GoodsType> page = new Page<GoodsType>();
		page.setCurrentPage(2);
		goodsTypeService.getPage(page);
		System.out.println(page);
	}

}
