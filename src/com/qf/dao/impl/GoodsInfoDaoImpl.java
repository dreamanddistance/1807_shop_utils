package com.qf.dao.impl;

import java.util.List;

import com.qf.dao.IGoodsInfoDao;
import com.qf.entity.GoodsInfo;
import com.qf.utils.DBManager;

public class GoodsInfoDaoImpl implements IGoodsInfoDao {

	@Override
	public int add(GoodsInfo t) {
		String sql ="insert into t_goods_info(goods_name,goods_description,goods_pic,goods_price,goods_stock,goods_price_off,goods_discount,goods_fatherid,goods_parentid) values(?,?,?,?,?,?,?,?,?)";
		return DBManager.commonUpdate(sql, t.getGoods_name(),t.getGoods_description(),t.getGoods_pic(),t.getGoods_price(),t.getGoods_stock(),t.getGoods_price_off(),t.getGoods_discount(),t.getGoods_fatherid(),t.getGoods_parentid());
	}

	@Override
	public int update(GoodsInfo t) {
		String sql ="update t_goods_info set goods_name= ?, goods_description= ?, goods_pic= ?, goods_price= ?, goods_stock= ?, goods_price_off= ?, goods_discount= ?, goods_fatherid= ?, goods_parentid= ? where id = ?";
		return DBManager.commonUpdate(sql, t.getGoods_name(),t.getGoods_description(),t.getGoods_pic(),t.getGoods_price(),t.getGoods_stock(),t.getGoods_price_off(),t.getGoods_discount(),t.getGoods_fatherid(),t.getGoods_parentid(),t.getId());
	}

	@Override
	public GoodsInfo getById(Integer id) {
		String sql ="select * from t_goods_info  where id = ?";
		List<GoodsInfo> list = DBManager.commonQuery(sql, GoodsInfo.class, id);
		if(!list.isEmpty()){
			return list.get(0);
		}
		return null;
	}

	@Override
	public int delete(Integer id) {
		String sql ="delete from t_goods_info  where id = ?";
		return DBManager.commonUpdate(sql, id);
	}

	@Override
	public List<GoodsInfo> getList() {
		String sql ="select * from t_goods_info";
		return DBManager.commonQuery(sql, GoodsInfo.class);
	}

	@Override
	public List<GoodsInfo> getListPage(Integer idnex, Integer size) {
		String sql ="select * from t_goods_info limit ?,?";
		return DBManager.commonQuery(sql, GoodsInfo.class, idnex,size);
	}

	@Override
	public Integer getTotalCount() {
		String sql ="select count(1) from t_goods_info ";
		return DBManager.commonCount(sql);
	}

}
