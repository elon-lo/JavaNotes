package com.yu.shiro.domain.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Optional;

/**
 * 用户登录DTO
 *
 * @author elonlo
 * @date 2023/6/22 18:52
 */
@Data
public class LoginUserDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String passwd;

    /**
     * 记住我,默认不开启
     */
    private Boolean rememberMe;

    public Boolean getRememberMe() {
        return Optional.ofNullable(rememberMe).isPresent() ? rememberMe : Boolean.FALSE;
    }
}
