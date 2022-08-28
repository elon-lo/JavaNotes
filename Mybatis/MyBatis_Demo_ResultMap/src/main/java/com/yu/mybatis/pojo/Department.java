package com.yu.mybatis.pojo;

import java.io.Serializable;
import java.util.List;

/**
 * @author elonlo
 * @date 2022/8/21 20:00
 */
public class Department implements Serializable {

	private static final long serialVersionUID = 9097566192807688762L;

	private Long did;

	private String deptName;

	private List<Employee> empList;

	public Department() {
	}

	public Department(Long did, String deptName, List<Employee> empList) {
		this.did = did;
		this.deptName = deptName;
		this.empList = empList;
	}

	public Long getDid() {
		return did;
	}

	public void setDid(Long did) {
		this.did = did;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public List<Employee> getEmpList() {
		return empList;
	}

	public void setEmpList(List<Employee> empList) {
		this.empList = empList;
	}

	@Override
	public String toString() {
		return "Department{" +
				"did=" + did +
				", deptName='" + deptName + '\'' +
				", empList=" + empList +
				'}';
	}
}
