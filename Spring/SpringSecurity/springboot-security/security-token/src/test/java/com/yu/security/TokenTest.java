package com.yu.security;

import com.yu.security.domain.entity.User;
import com.yu.security.service.IUserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

/**
 * 测试类
 *
 * @author elonlo
 * @date 2023/7/8 16:03
 */
@SpringBootTest
public class TokenTest {

    @Autowired
    private IUserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void test2() {
        String encode = passwordEncoder.encode("1423");
        System.out.println(encode);
    }

    @Test
    public void test1() {
        List<User> list = userService.list();
        System.out.println(list);
    }
}
