package com.yu.mybatis.pojo;

import java.io.Serializable;

/**
 * 用户表
 *
 * @author elonlo
 * @date 2022/7/16 18:57
 */
public class User implements Serializable {

	private static final long serialVersionUID = -2998494959667126967L;

	/**
	 * id
	 */
	private Long id;

	/**
	 * 用户名
	 */
	private String username;

	/**
	 * 密码
	 */
	private String password;

	/**
	 * 年龄
	 */
	private Integer age;

	/**
	 * 性别(0-男、1-女)
	 */
	private Integer sex;

	/**
	 * 邮箱
	 */
	private String email;

	public User() {
	}

	public User(Long id, String username, String password, Integer age, Integer sex, String email) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.age = age;
		this.sex = sex;
		this.email = email;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	@Override
	public String toString() {
		return "User{" +
				"id=" + id +
				", username='" + username + '\'' +
				", password='" + password + '\'' +
				", age=" + age +
				", sex=" + sex +
				", email='" + email + '\'' +
				'}';
	}
}
