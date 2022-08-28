package com.yu.mybatis.pojo;

import java.io.Serializable;

/**
 * @author elonlo
 * @date 2022/8/21 19:58
 */
public class Employee implements Serializable {

	private static final long serialVersionUID = -8273218128976840150L;

	private Long eid;

	private String empName;

	private Integer age;

	private Integer sex;

	private String email;

	private Long did;

	private Department department;

	public Employee() {
	}

	public Employee(Long eid, String empName, Integer age, Integer sex, String email, Long did, Department department) {
		this.eid = eid;
		this.empName = empName;
		this.age = age;
		this.sex = sex;
		this.email = email;
		this.did = did;
		this.department = department;
	}

	public Long getEid() {
		return eid;
	}

	public void setEid(Long eid) {
		this.eid = eid;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
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

	public Long getDid() {
		return did;
	}

	public void setDid(Long did) {
		this.did = did;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	@Override
	public String toString() {
		return "Employee{" +
				"eid=" + eid +
				", empName='" + empName + '\'' +
				", age=" + age +
				", sex=" + sex +
				", email='" + email + '\'' +
				", did=" + did +
				", department=" + department +
				'}';
	}
}
