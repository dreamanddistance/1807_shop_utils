package com.qf.dao.impl;

import java.util.List;

import com.qf.dao.IGoodsTypeDao;
import com.qf.entity.GoodsType;
import com.qf.utils.DBManager;

public class GoodsTypeDaoImpl implements IGoodsTypeDao {

	@Override
	public int add(GoodsType t) {
		String sql = "insert into t_goods_type(goods_type_name,goods_type_parentid,goods_type_pic) values(?,?,?)";
		return DBManager.commonUpdate(sql, t.getGoods_type_name(), t.getGoods_type_parentid(), t.getGoods_type_pic());
	}

	@Override
	public int update(GoodsType t) {
		String sql = "update t_goods_type set goods_type_name = ?,goods_type_parentid = ?,goods_type_pic = ? where id = ?";
		return DBManager.commonUpdate(sql, t.getGoods_type_name(), t.getGoods_type_parentid(), t.getGoods_type_pic(),
				t.getId());
	}

	@Override
	public GoodsType getById(Integer id) {
		String sql = "select * from t_goods_type where id = ?";
		List<GoodsType> list = DBManager.commonQuery(sql, GoodsType.class, id);
		if (!list.isEmpty()) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public int delete(Integer id) {
		String sql = "delete from t_goods_type where id = ?";
		return DBManager.commonUpdate(sql, id);
	}

	@Override
	public List<GoodsType> getList() {
		String sql = "select * from t_goods_type";
		return DBManager.commonQuery(sql, GoodsType.class);
	}

	@Override
	public List<GoodsType> getListPage(Integer index, Integer size) {
		String sql = "SELECT t1.*, ifnull(t2.goods_type_name,'大类') as goods_type_parentname FROM t_goods_type t1 LEFT JOIN t_goods_type t2 ON (t1.goods_type_parentid = t2.id) limit ?,?";
		return DBManager.commonQuery(sql, GoodsType.class,index,size);
	}

	@Override
	public Integer getTotalCount() {
		String sql = "select count(1) from t_goods_type";
		return DBManager.commonCount(sql);
	}

	@Override
	public List<GoodsType> getGoodsTypeListByParentId(Integer parentId) {
		String sql ="select * from t_goods_type t where t.goods_type_parentid = ?";
		return DBManager.commonQuery(sql, GoodsType.class, parentId);
	}

}
