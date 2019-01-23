package com.qf.service;

import java.util.List;

import com.qf.entity.Page;

public interface IBaseService<T> {
	
	public int add(T t);

	public int update(T t);

	public T getById(Integer id);

	public int delete(Integer id);

	public List<T> getList();

	public void getPage(Page<T> page);

}
