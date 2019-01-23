package com.qf.entity;

public class User {

	//实体类中的类型尽量的都用包装类
	
//	int i = 0/ // 默认值是0
//	Integer i= null; // 默认值null
	
	private Integer id;
	
	// 数据库中的命名规范是：单词和单词之间用下划线隔开
	// Java中的命名规范是:驼峰命名
	private String name; // 故意和表中的字段名称不一样
	
	private String password;
	
	private Integer sex;
	
	private String email;
	
	private Integer role;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getRole() {
		return role;
	}

	public void setRole(Integer role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", password=" + password + ", sex=" + sex + ", email=" + email
				+ ", role=" + role + "]";
	}


}
