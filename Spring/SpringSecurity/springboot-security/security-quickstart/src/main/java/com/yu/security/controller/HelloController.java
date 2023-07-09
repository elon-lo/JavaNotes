package com.yu.security.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author elonlo
 * @date 2023/7/8 13:06
 */
@RestController
@RequestMapping("/quick")
public class HelloController {

    @GetMapping("/hello")
    public String hello() {
        return "Hello Security";
    }
}
