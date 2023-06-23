package com.yu.shiro.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * MybatisPlus配置类
 *
 * @author elonlo
 * @date 2023/6/22 16:17
 */
@Configuration
@MapperScan("com.yu.shiro.mapper")
public class MybatisPlusConfig {

}
