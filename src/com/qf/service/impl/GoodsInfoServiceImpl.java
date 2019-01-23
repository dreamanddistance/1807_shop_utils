package com.qf.service.impl;

import java.util.List;

import com.qf.dao.IGoodsInfoDao;
import com.qf.dao.impl.GoodsInfoDaoImpl;
import com.qf.entity.GoodsInfo;
import com.qf.entity.Page;
import com.qf.service.IGoodsInfoService;

import sun.management.resources.agent;

public class GoodsInfoServiceImpl implements IGoodsInfoService {

	private IGoodsInfoDao goodInfoDao = new GoodsInfoDaoImpl();

	@Override
	public int add(GoodsInfo t) {
		return goodInfoDao.add(t);
	}

	@Override
	public int update(GoodsInfo t) {
		return goodInfoDao.update(t);
	}

	@Override
	public GoodsInfo getById(Integer id) {
		return goodInfoDao.getById(id);
	}

	@Override
	public int delete(Integer id) {
		return goodInfoDao.delete(id);
	}

	@Override
	public List<GoodsInfo> getList() {
		return goodInfoDao.getList();
	}

	@Override
	public void getPage(Page<GoodsInfo> page) {
		Integer currentPage = page.getCurrentPage();
		Integer pageSize = page.getPageSize();

		page.setTotalCount(goodInfoDao.getTotalCount());
		page.setList(goodInfoDao.getListPage((currentPage - 1) * pageSize, pageSize));

	}

}
