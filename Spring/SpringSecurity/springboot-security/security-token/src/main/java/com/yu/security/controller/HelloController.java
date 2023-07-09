package com.yu.security.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author elonlo
 * @date 2023/7/8 13:06
 */
@RestController
@RequestMapping("/token")
public class HelloController {

    @GetMapping("/hello")
    @PreAuthorize("@ex.hasAuthority('system:user:add')")
    public String hello() {
        return "Hello Security";
    }
}
