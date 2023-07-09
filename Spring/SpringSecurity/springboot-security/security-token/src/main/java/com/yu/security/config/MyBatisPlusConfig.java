package com.yu.security.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * mybatis-plus配置类
 *
 * @author elonlo
 * @date 2023/7/8 15:59
 */
@Configuration
@MapperScan(basePackages = {"com.yu.security.mapper"})
public class MyBatisPlusConfig {

}
