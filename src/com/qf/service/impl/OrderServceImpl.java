package com.qf.service.impl;

import java.util.List;

import com.qf.dao.IOrderDao;
import com.qf.dao.impl.OrderDaoImpl;
import com.qf.entity.Order;
import com.qf.entity.Page;
import com.qf.service.IOrderService;

public class OrderServceImpl implements IOrderService {
	
	private IOrderDao orderDao = new OrderDaoImpl();

	@Override
	public int add(Order t) {
		return orderDao.add(t);
	}

	@Override
	public int update(Order t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Order getById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int delete(Integer id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Order> getList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void getPage(Page<Order> page) {
		// TODO Auto-generated method stub

	}

}
