package com.qf.dao.impl;

import java.util.List;

import com.qf.dao.IOrderDetailDao;
import com.qf.entity.OrderDetail;
import com.qf.utils.DBManager;

public class OrderDetailDaoImpl implements IOrderDetailDao {

	@Override
	public int add(OrderDetail t) {
		String sql ="insert into t_order_detail (goods_date, o_orderid, goodsid, goodsname, goodsprice, goods_description, goodsnum, goodspic, goods_total_price) values(now(),?,?,?,?,?,?,?,?)";
		return DBManager.commonUpdate(sql, t.getO_orderid(),t.getGoodsid(),t.getGoodsname(),t.getGoodsprice(),t.getGoods_description(),t.getGoodsnum(),t.getGoodspic(),t.getGoods_total_price());
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
	public List<OrderDetail> getListPage(Integer idnex, Integer size) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer getTotalCount() {
		// TODO Auto-generated method stub
		return null;
	}

}
