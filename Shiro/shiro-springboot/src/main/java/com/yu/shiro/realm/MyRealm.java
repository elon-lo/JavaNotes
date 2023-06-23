package com.yu.shiro.realm;

import com.yu.shiro.domain.entity.User;
import com.yu.shiro.service.IUserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

/**
 * 自定义Realm
 *
 * @author elonlo
 * @date 2023/6/22 17:58
 */
@Component
public class MyRealm extends AuthorizingRealm {

    private final IUserService userService;

    @Autowired
    public MyRealm(IUserService userService) {
        this.userService = userService;
    }

    // 自定义授权的方法
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        System.out.println("=======自定义授权的方法=======");

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

        // 1.获取用户信息
        String username = principals.getPrimaryPrincipal().toString();

        // 2.根据用户名查询角色列表
        List<String> roles = userService.findRolesByUsername(username);
        System.out.println("该用户拥有的角色为: " + roles);

        // 3.根据角色列表查询权限列表
        String[] roleNames = roles.toArray(new String[0]);
        List<String> permissions = userService.findPermissionsByRoleNames(roleNames);
        System.out.println("该用户拥有的权限为: " + permissions);

        // 4.封装角色和权限信息并返回
        info.addRoles(roles);
        info.addStringPermissions(permissions);

        return info;
    }

    // 自定义认证的方法
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        // 1.获取用户身份信息
        String username = token.getPrincipal().toString();

        // 2.调用业务层查询用户信息(数据库)
        User user = userService.getUserInfoByName(username);

        // 3.数据不为空,则直接返回
        if (Objects.nonNull(user)) {
            return new SimpleAuthenticationInfo(
                    token.getPrincipal(),
                    user.getPasswd(),
                    ByteSource.Util.bytes(username),
                    token.getPrincipal().toString());
        }
        return null;
    }

    public static void main(String[] args) {
        String algorithmName = "md5";
        Object credentials = "123456!";
        ByteSource credentialsSalt = ByteSource.Util.bytes("lisi");
        int hashIterations = 1024;

        SimpleHash result = new SimpleHash(algorithmName, credentials, credentialsSalt, hashIterations);
        System.out.println(result.toHex());
    }
}
