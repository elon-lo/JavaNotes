package com.yu.shiro.realm;

import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Objects;

/**
 * 另一个自定义realm
 *
 * @author elonlo
 * @date 2023/6/22 23:23
 */
@Component
public class SecondRealm extends AuthorizingRealm {

    // 自定义授权的方法
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        return null;
    }

    // 自定义认证的方法
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        // 1.获取用户身份信息
        UsernamePasswordToken upToken = (UsernamePasswordToken) token;

        String username = upToken.getUsername();

        // 2.用户名为zhangsan,设置一个错误的密码,用来测试修改认证策略是否成功
        String passwd = "";
        if (Objects.equals("zhangsan", username)) {
            passwd = "12345";
        }

        // 3.数据不为空,则直接返回
        if (!StringUtils.isEmpty(passwd)) {
            return new SimpleAuthenticationInfo(
                    username,
                    passwd,
                    ByteSource.Util.bytes(username),
                    username);
        }
        return null;
    }
}
