package com.qf.service.impl;

import java.util.List;

import com.qf.dao.IOrderDetailDao;
import com.qf.dao.impl.OrderDetailDaoImpl;
import com.qf.entity.OrderDetail;
import com.qf.entity.Page;
import com.qf.service.IOrderDetail;

public class OrderDetailServiceImpl implements IOrderDetail {

	private IOrderDetailDao orderDetailDao = new OrderDetailDaoImpl();
	
	@Override
	public int add(OrderDetail t) {
		return orderDetailDao.add(t);
	}

	@Override
	public int update(OrderDetail t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public OrderDetail getById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int delete(Integer id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<OrderDetail> getList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void getPage(Page<OrderDetail> page) {
		// TODO Auto-generated method stub

	}

}
