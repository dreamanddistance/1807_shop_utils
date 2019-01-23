package com.qf.dao.impl;

import java.util.List;

import com.qf.dao.IOrderDao;
import com.qf.entity.Order;
import com.qf.utils.DBManager;

public class OrderDaoImpl implements IOrderDao {

	@Override
	public int add(Order t) {
		String sql ="insert into t_order (o_sendtype, o_paytype, o_paycount, o_orderdate, userid, o_shperson, o_shphone, o_shaddress) values(?,?,?,now(),?,?,?,?)";
		// 这里要返回主键
		return DBManager.commonInsert(sql, t.getO_sendtype(),t.getO_paytype(),t.getO_paycount(),t.getUserid(),t.getO_shperson(),t.getO_shphone(),t.getO_shaddress());
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
	public List<Order> getListPage(Integer idnex, Integer size) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer getTotalCount() {
		// TODO Auto-generated method stub
		return null;
	}

}
