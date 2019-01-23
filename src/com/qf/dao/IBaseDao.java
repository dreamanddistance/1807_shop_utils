package com.qf.dao;

import java.util.List;

public interface IBaseDao<T> {

	public int add(T t);

	public int update(T t);

	public T getById(Integer id);
	
	public int delete(Integer id);
	
	public List<T> getList();
	
	public List<T> getListPage(Integer idnex,Integer size);
	
	public Integer getTotalCount();
}
