package com.yu.boot.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户控制器
 */
@RestController
@RequestMapping("/user")
public class UserController {

	@GetMapping("/name")
	public String getUser() {
		return "zhangsan";
	}
}
