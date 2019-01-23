package com.qf.entity;

public class GoodsType {

	private Integer id;
	
	private String goods_type_name;
	
	private Integer goods_type_parentid;
	
	private String goods_type_pic;

	private String goods_type_parentname;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getGoods_type_name() {
		return goods_type_name;
	}

	public void setGoods_type_name(String goods_type_name) {
		this.goods_type_name = goods_type_name;
	}

	public Integer getGoods_type_parentid() {
		return goods_type_parentid;
	}

	public void setGoods_type_parentid(Integer goods_type_parentid) {
		this.goods_type_parentid = goods_type_parentid;
	}

	public String getGoods_type_pic() {
		return goods_type_pic;
	}

	public void setGoods_type_pic(String goods_type_pic) {
		this.goods_type_pic = goods_type_pic;
	}

	@Override
	public String toString() {
		return "GoodsType [id=" + id + ", goods_type_name=" + goods_type_name + ", goods_type_parentid="
				+ goods_type_parentid + ", goods_type_pic=" + goods_type_pic + "]";
	}

	public String getGoods_type_parentname() {
		return goods_type_parentname;
	}

	public void setGoods_type_parentname(String goods_type_parentname) {
		this.goods_type_parentname = goods_type_parentname;
	}
}
