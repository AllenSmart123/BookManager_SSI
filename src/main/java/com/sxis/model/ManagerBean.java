package com.sxis.model;



public class ManagerBean {
	private Integer id;
	private String name;
	private String password;
	private Integer grade;
	

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

	public Integer getGrade() {
		return grade;
	}

	public void setGrade(Integer grade) {
		this.grade = grade;
	}

	@Override
	public String toString() {
		return "Manager [id=" + id + ", name=" + name + ", password="
				+ password + ", grade=" + grade + "]";
	}
	
	

}
