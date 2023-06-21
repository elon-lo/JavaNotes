package com.yu.shiro.realm;

import org.apache.shiro.authc.*;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthenticatingRealm;
import org.apache.shiro.util.ByteSource;

/**
 * 自定义realm
 *
 * @author elonlo
 * @date 2023/6/18 17:22
 */
public class SecondRealm extends AuthenticatingRealm {

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        System.out.println("second realm......");
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
            credentials = "4e543728b5bcf4433d4bde61e76217e919bcf71f";
        } else if ("user".equals(username)) {
            credentials = "400076b2287bfe40be100496475a5c8559e4ca3f";
        }
        // realmName: 当前realm对象的name,调用父类的getName()即可
        String realmName = getName();
        // salt: 盐值,一般为唯一字符串,这里使用username作为盐值
        ByteSource credentialsSalt = ByteSource.Util.bytes(username);

        return new SimpleAuthenticationInfo(principal, credentials, credentialsSalt, realmName);
    }

    public static void main(String[] args) {
        String algorithmName = "SHA1";
        Object credentials = "1qaz!@";
        Object salt = ByteSource.Util.bytes("admin");
        int hashIterations = 1024;

        Object result = new SimpleHash(algorithmName, credentials, salt, hashIterations);
        System.out.println(result);
    }
}
