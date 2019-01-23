package com.qf.dao.impl;

import java.util.List;

import com.qf.dao.IAddressDao;
import com.qf.entity.Address;
import com.qf.utils.DBManager;

public class AddressDaoImpl implements IAddressDao {

	@Override
	public List<Address> getAddressListByUserId(Integer usreId) {
		String sql ="select * from t_address where userid = ?";
		return DBManager.commonQuery(sql, Address.class, usreId);
	}

}
