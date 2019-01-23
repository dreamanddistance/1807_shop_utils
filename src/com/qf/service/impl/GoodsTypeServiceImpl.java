package com.qf.service.impl;

import java.util.List;

import com.qf.dao.IGoodsTypeDao;
import com.qf.dao.impl.GoodsTypeDaoImpl;
import com.qf.entity.GoodsType;
import com.qf.entity.Page;
import com.qf.service.IGoodsTypeService;

public class GoodsTypeServiceImpl implements IGoodsTypeService {

	private IGoodsTypeDao gtd = new GoodsTypeDaoImpl();
	
	@Override
	public int add(GoodsType t) {
		return gtd.add(t);
	}

	@Override
	public int update(GoodsType t) {
		return gtd.update(t);
	}

	@Override
	public GoodsType getById(Integer id) {
		return gtd.getById(id);
	}

	@Override
	public int delete(Integer id) {
		return gtd.delete(id);
	}

	@Override
	public List<GoodsType> getList() {
		return gtd.getList();
	}

	@Override
	public void getPage(Page<GoodsType> page) {
		
		Integer currentPage = page.getCurrentPage();
		Integer pageSize = page.getPageSize();
		
		page.setTotalCount(gtd.getTotalCount()); // 设置完总条数后就可以算出总页数
		page.setList(gtd.getListPage((currentPage-1)*pageSize, pageSize));
		
	}

	@Override
	public List<GoodsType> getGoodsTypeListByParentId(Integer parentId) {
		return gtd.getGoodsTypeListByParentId(parentId);
	}

}
