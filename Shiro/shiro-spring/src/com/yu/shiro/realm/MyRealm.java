package com.yu.shiro.realm;

import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import java.util.HashSet;
import java.util.Set;

/**
 * 自定义realm
 *
 * @author elonlo
 * @date 2023/6/18 17:22
 */
public class MyRealm extends AuthorizingRealm {

    // 认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        System.out.println("my realm......");
        // 1.将AuthenticationToken转为UsernamePasswordToken
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) token;

        // 2.从UsernamePasswordToken获取username
        String username = usernamePasswordToken.getUsername();

        // 3.调用数据库,从数据库中查询username对应的用户记录
        System.out.println("用户" + username + "的数据库记录信息");

        // 4.若用户不存在,则可以抛出UnknownAccountException异常
        if ("test".equals(username)) {
            throw new UnknownAccountException("用户不存在!");
        }

        // 5.根据查询的用户信息,判断是否要抛出其他的AuthenticationException异常
        if ("guest".equals(username)) {
            throw new LockedAccountException("账户被锁定!");
        }

        // 6.根据用户信息构建AuthenticationInfo对象并返回,通常使用的实现类为SimpleAuthenticationInfo
        // principal: 认证的实体信息,可以是username,也可以是数据表对应的用户的实体类对象
        Object principal = username;
        Object credentials = null;
        // credentials: 数据库中的密码
        if ("admin".equals(username)) {
            credentials = "6536c0250b93dc83fd780be4a9cefbbd";
        } else if ("user".equals(username)) {
            credentials = "04f66380c87589f0572d1f407ca5b160";
        }
        // realmName: 当前realm对象的name,调用父类的getName()即可
        String realmName = getName();
        // salt: 盐值,一般为唯一字符串,这里使用username作为盐值
        ByteSource credentialsSalt = ByteSource.Util.bytes(username);

        return new SimpleAuthenticationInfo(principal, credentials, credentialsSalt, realmName);
    }

    // 授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        // 1.从PrincipalCollection中获取登录用户的信息
        Object principal = principals.getPrimaryPrincipal();

        // 2.从登录用户的信息获取当前用户拥有的角色和权限(可能需要查询数据库)
        Set<String> roles = new HashSet<>(16);
        roles.add("user");
        if ("admin".equals(principal)) {
            roles.add("admin");
        }

        // 3.创建simpleAuthorizationInfo,并设置roles属性
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo(roles);
        Set<String> set = simpleAuthorizationInfo.getRoles();
        System.out.println("当前用户拥有的角色为: " + set);

        // 4.返回simpleAuthorizationInfo对象
        return simpleAuthorizationInfo;
    }

    public static void main(String[] args) {
        String algorithmName = "MD5";
        Object credentials = "1qaz!@";
        Object salt = ByteSource.Util.bytes("user");
        int hashIterations = 1024;

        Object result = new SimpleHash(algorithmName, credentials, salt, hashIterations);
        System.out.println(result);
    }
}
