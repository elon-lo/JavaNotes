本文所使用的环境如下：

- IDEA：2021.1.3
- SpringBoot：2.2.1.RELEASE
- MySQL：8.0.32
- Shiro：1.9.0

## 框架整合

1. 创建项目

   ```markdown
   ├── shiro-springboot
   │   └──src
   │   │   ├── main
   │   │   │    ├── java
   │   │   │    │    └── com
   │   │   │    │    │    └── yu
   │   │   │    │    │    │    └── shiro
   │   │   │    │    │    │    │    └── ShiroApplication.java
   │   │   │    └── resources
   │   │   │    │    └── application.yml
   │   │   └── test
   │   │   │    └── java
   
   ```

2. 添加依赖

   ```xml
   <?xml version="1.0" encoding="UTF-8"?>
   <project xmlns="http://maven.apache.org/POM/4.0.0"
            xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
            xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
       <modelVersion>4.0.0</modelVersion>
   
       <groupId>com.yu.shiro</groupId>
       <artifactId>shiro-springboot</artifactId>
       <version>1.0-SNAPSHOT</version>
   
       <parent>
           <groupId>org.springframework.boot</groupId>
           <artifactId>spring-boot-starter-parent</artifactId>
           <version>2.2.1.RELEASE</version>
       </parent>
   
       <properties>
           <maven.compiler.source>8</maven.compiler.source>
           <maven.compiler.target>8</maven.compiler.target>
           <shiro.starter.version>1.9.0</shiro.starter.version>
           <mybatis-plus.version>3.0.5</mybatis-plus.version>
           <mysql.version>5.1.46</mysql.version>
       </properties>
   
       <dependencies>
           <!-- shiro -->
           <dependency>
               <groupId>org.apache.shiro</groupId>
               <artifactId>shiro-spring-boot-web-starter</artifactId>
               <version>${shiro.starter.version}</version>
           </dependency>
   
           <!-- mybatis-plus -->
           <dependency>
               <groupId>com.baomidou</groupId>
               <artifactId>mybatis-plus-boot-starter</artifactId>
               <version>${mybatis-plus.version}</version>
           </dependency>
   
           <dependency>
               <groupId>mysql</groupId>
               <artifactId>mysql-connector-java</artifactId>
               <version>${mysql.version}</version>
           </dependency>
   
           <dependency>
               <groupId>org.projectlombok</groupId>
               <artifactId>lombok</artifactId>
               <optional>true</optional>
           </dependency>
   
           <dependency>
               <groupId>org.springframework.boot</groupId>
               <artifactId>spring-boot-starter-thymeleaf</artifactId>
           </dependency>
       </dependencies>
   
   </project>
   ```

3. 添加配置文件

   ```yaml
   server:
     port: 8085
     servlet:
       context-path: /shiro
   
   mybatis-plus:
     configuration:
       log-impl: org.apache.ibatis.logging.slf4j.Slf4jImpl
     mapper-locations: classpath:mapper/*.xml
   
   spring:
     datasource:
       type: com.zaxxer.hikari.HikariDataSource
       driver-class-name: com.mysql.jdbc.Driver
       url: jdbc:mysql:ip:port/shiro?characterEncoding=utf8&useSSL=false&useUnicode=true
       username: 
       password: 
   
     jackson:
       date-format: yyyy-MM-dd HH:mm:ss
       time-zone: GMT+8
   
   shiro:
     loginUrl: /shiro/login
   ```

   ```java
   @Configuration
   @MapperScan("com.yu.shiro.mapper")
   public class MybatisPlusConfig {
       
   }
   ```

4. 创建启动类

   ```java
   @SpringBootApplication
   public class ShiroApplication {
   
       public static void main(String[] args) {
           SpringApplication.run(ShiroApplication.class, args);
       }
   }
   ```

## 登录认证

### 创建数据表

```sql
CREATE DATABASE IF NOT EXISTS `shiro`

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `user_id` bigint NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `username` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '用户名',
  `passwd` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '密码',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='用户表';
```

### 创建实体类、mapper、service

```java
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@TableName("user")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户id
     */
    @TableId(value = "user_id", type = IdType.AUTO)
    private Long userId;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String passwd;
}
```

```java
public interface UserMapper extends BaseMapper<User> {

}
```

```java
public interface IUserService extends IService<User> {

    /**
     * 根据用户名查询用户
     *
     * @param name 名字
     * @return {@link User}
     */
    User getUserInfoByName(String name);
}
```

```java
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    private final UserMapper userMapper;

    @Autowired
    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    /**
     * 根据用户名查询用户
     */
    @Override
    public User getUserInfoByName(String name) {
        return userMapper.selectOne(new LambdaQueryWrapper<User>().eq(User::getUsername, name));
    }
}
```

```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="com.yu.shiro.mapper.UserMapper">

</mapper>
```

### 自定义Realm

```java
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
        return null;
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
        ByteSource credentialsSalt = ByteSource.Util.bytes("zhangsan");
        int hashIterations = 1024;

        SimpleHash result = new SimpleHash(algorithmName, credentials, credentialsSalt, hashIterations);
        System.out.println(result.toHex());
    }
}
```

### 添加shiro配置

```java
@Configuration
public class ShiroConfig {

    private final MyRealm myRealm;

    @Autowired
    public ShiroConfig(MyRealm myRealm) {
        this.myRealm = myRealm;
    }


    /**
     * 配置defaultWebSecurityManager
     *
     * @return {@link DefaultWebSecurityManager}
     */
    @Bean
    public DefaultWebSecurityManager defaultWebSecurityManager() {
        // 1.创建defaultWebSecurityManager
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();

        // 2.创建加密对象,设置相关属性
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        hashedCredentialsMatcher.setHashAlgorithmName("md5");
        hashedCredentialsMatcher.setHashIterations(1024);

        // 3.将加密对象存储到myRealm中
        myRealm.setCredentialsMatcher(hashedCredentialsMatcher);

        // 4.将myRealm存入defaultWebSecurityManager
        defaultWebSecurityManager.setRealm(myRealm);

        return defaultWebSecurityManager;
    }

    /**
     * 配置shiro内置过滤器拦截范围
     *
     * @return {@link DefaultShiroFilterChainDefinition}
     */
    @Bean
    public DefaultShiroFilterChainDefinition shiroFilterChainDefinition() {
        DefaultShiroFilterChainDefinition shiroFilterChainDefinition = new DefaultShiroFilterChainDefinition();

        // 配置匿名访问路径
        shiroFilterChainDefinition.addPathDefinition("/user/login", "anon");
        shiroFilterChainDefinition.addPathDefinition("/login", "anon");

        // 配置认证访问路径
        shiroFilterChainDefinition.addPathDefinition("/**", "authc");

        return shiroFilterChainDefinition;
    }

}
```

### 登录

```java
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
}
```

```java
public interface IUserService extends IService<User> {

    /**
     * 根据用户名查询用户
     *
     * @param name 名字
     * @return {@link User}
     */
    User getUserInfoByName(String name);

    /**
     * 登录
     *
     * @param dto 用户登录DTO
     * @return boolean
     */
    boolean login(LoginUserDTO dto);
}
```

```java
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    private final UserMapper userMapper;

    @Autowired
    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    /**
     * 根据用户名查询用户
     */
    @Override
    public User getUserInfoByName(String name) {
        return userMapper.selectOne(new LambdaQueryWrapper<User>().eq(User::getUsername, name));
    }

    /**
     * 登录
     */
    @Override
    public boolean login(LoginUserDTO dto) {
        // 1.获取subject对象
        Subject subject = SecurityUtils.getSubject();

        // 2.使用用户名和密码构建UsernamePasswordToken对象
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(dto.getUsername(), dto.getPasswd());

        // 3.调用登录方法
        try {
            subject.login(usernamePasswordToken);
            return true;
        } catch (AuthenticationException e) {
            LOGGER.error("登录失败: {}", e);
            e.printStackTrace();
        }
        return false;
    }
}
```

```java
@RestController
@RequestMapping("/user")
public class UserController {

    private final IUserService userService;

    @Autowired
    public UserController(IUserService userService) {
        this.userService = userService;
    }

    /**
     * 登录
     */
    @PostMapping("/login")
    public String login(@RequestBody LoginUserDTO dto) {
        boolean flag = userService.login(dto);
        if (flag) {
            return "登录成功";
        }
        return "登录失败";
    }
}
```

### 前端登录认证

#### 添加前端页面

```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
    <h1>Shiro登录认证</h1>

    <form action="/shiro/user/userLogin" method="post">
        <div>用户名: <input type="text" name="username" id="username" value=""></div>
        <div>密码: <input type="password" name="passwd" id="passwd" value=""></div>
        <div><input type="submit" value="登录"></div>
    </form>
</body>
</html>
```

```html
<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
    <h1>Welcome to index</h1>

    登录用户为: <span th:text="${session.user}"></span>

</body>
</html>
```

#### 修改application.yml配置

```yaml
shiro:
  loginUrl: /user/login
```

#### 修改shiroConfig

```java
@Bean
public DefaultShiroFilterChainDefinition shiroFilterChainDefinition() {
    DefaultShiroFilterChainDefinition shiroFilterChainDefinition = new DefaultShiroFilterChainDefinition();

    // 配置匿名访问路径
    shiroFilterChainDefinition.addPathDefinition("/user/userLogin", "anon");
    shiroFilterChainDefinition.addPathDefinition("/user/login", "anon");

    // 配置认证访问路径
    shiroFilterChainDefinition.addPathDefinition("/**", "authc");

    return shiroFilterChainDefinition;
}
```

#### 修改controller、service部分代码

```java
@Controller
@RequestMapping("/user")
public class UserController {

    private final IUserService userService;

    @Autowired
    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    /**
     * 登录
     */
    @PostMapping("/userLogin")
    public String login(@ModelAttribute LoginUserDTO dto, HttpSession session) {
        boolean flag = userService.login(dto, session);
        if (flag) {
            return "main";
        }
        return "登录失败";
    }
}
```

```java
public interface IUserService extends IService<User> {
    /**
     * 登录
     *
     * @param dto 用户登录DTO
     * @param session 会话
     * @return boolean
     */
    boolean login(LoginUserDTO dto, HttpSession session);
}
```

```java
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
    
    @Autowired
    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }
    
    @Override
    public boolean login(LoginUserDTO dto, HttpSession session) {
        // 1.获取subject对象
        Subject subject = SecurityUtils.getSubject();

        // 2.使用用户名和密码构建UsernamePasswordToken对象
        UsernamePasswordToken token = new UsernamePasswordToken(dto.getUsername(), dto.getPasswd());

        // 3.调用登录方法
        try {
            subject.login(token);
            session.setAttribute("user", token.getPrincipal());
            return true;
        } catch (AuthenticationException e) {
            LOGGER.error("登录失败: {}", e);
            e.printStackTrace();
        }
        return false;
    }
}
```

<span style="color:red">注意：如果设置了`servelet.context-path`，shiro设置的`loginUrl`和不需要认证访问的路径不能加`servelet.context-path`，否则会出现无法访问情况；加密时盐值要和Realm中认证使用的盐值一致；post请求中使用@RequestBody注解与thymeleaf不兼容，请修改为@ModelAttribute注解或不添加注解。</span>

## 多个Realm的认证策略

当应用程序配置多个Realm时，例如，用户密码校验、手机号验证码校验等等。Shiro的`ModularRealmAuthenticator`会使用内部的`AuthenticationStrategy组件`判断认证是成功还是失败。

AuthenticationStrategy是一个`无状态`的组件，它在身份验证尝试中被`询问4次`(这4次交互所需的任何必要的状态都将被作为方法参数)：

- 在所有Realm被调用之前。
- 在调用Realm的getAuthenticationInfo方法之前。
- 在调用Realm的getAuthenticationInfo方法之后。
- 在所有Realm被调用之后。

认证策略的另一项工作就是聚合所有的Realm的结果信息封装到一个AuthenticationInfo实例中，并将此信息返回，以此作为Subject的身份信息。

Shiro定义了三种认证策略的实现：

| 认证策略方法                 | 描述                                                         |
| :--------------------------- | :----------------------------------------------------------- |
| AtLeastOneSuccessfulStrategy | 只要有一个(或更多的)Realm验证成功，那么认证将视为成功(默认认证策略) |
| FirstSuccessfulStrategy      | 第一个Realm验证成功，整体认证将视为成功，且后续Realm将被忽略 |
| AllSuccessfulStrategy        | 所有Realm验证成功，认证才视为成功                            |

下面是一个简单的多Realm配置示例：

- 添加另一个Realm

  ```java
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
  ```

- 配置多Realm和认证策略

  ```java
  @Configuration
  public class ShiroConfig {
  
      private final MyRealm myRealm;
  
      private final SecondRealm secondRealm;
  
      @Autowired
      public ShiroConfig(MyRealm myRealm, SecondRealm secondRealm) {
          this.myRealm = myRealm;
          this.secondRealm = secondRealm;
      }
  
      /**
       * 配置defaultWebSecurityManager
       *
       * @return {@link DefaultWebSecurityManager}
       */
      @Bean
      public DefaultWebSecurityManager defaultWebSecurityManager() {
          // 1.创建defaultWebSecurityManager
          DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
  
          // 2.创建加密对象,设置相关属性
          HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
          hashedCredentialsMatcher.setHashAlgorithmName("md5");
          hashedCredentialsMatcher.setHashIterations(1024);
  
          // 3.将加密对象存储到myRealm中
          myRealm.setCredentialsMatcher(hashedCredentialsMatcher);
          secondRealm.setCredentialsMatcher(hashedCredentialsMatcher);
  
          // 4.修改默认认证策略
          defaultWebSecurityManager.setAuthenticator(modularRealmAuthenticator());
  
          // 5.设置多Realm
          Set<Realm> realms = new HashSet<>(16);
          realms.add(myRealm);
          realms.add(secondRealm);
          defaultWebSecurityManager.setRealms(realms);
  
          return defaultWebSecurityManager;
      }
  
      /**
       * 配置认证策略
       *
       * @return {@link Authenticator}
       */
      @Bean
      public ModularRealmAuthenticator modularRealmAuthenticator() {
          ModularRealmAuthenticator modularRealmAuthenticator = new ModularRealmAuthenticator();
          // 修改默认认证策略
          modularRealmAuthenticator.setAuthenticationStrategy(new FirstSuccessfulStrategy());
          return modularRealmAuthenticator;
      }
  }
  ```

<span style="color:red">注意：defaultWebSecurityManager设置认证策略一定要在设置多realm之前，否则会出现未配置realm错误。</span>

## Remeber Me功能

Shiro提供了记住我(RemeberMe)的功能，比如访问一些网站时，关闭了浏览器，下次再打开时不用重新登录即可访问。

基本流程如下：

1. 首先在登录页面选中RemeberMe，然后登录成功，如果是浏览器登录，一般会把RemeberMe的Cookie写到客户端并保存下来。
2. 关闭浏览器再重新打开，发现浏览器还是记住你的，访问一般的网页服务器端，仍然是无需重新登录的。
3. 但是，如果我们访问电商平台时，如果要查看我们的订单或进行支付时，此时还是需要再进行身份认证的，以确保安全性。

代码示例如下：

- 实体类添加rememberMe属性

  ```java
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
  ```

- 登录页添加rememberMe复选框

  ```html
  <!DOCTYPE html>
  <html lang="en">
  <head>
      <meta charset="UTF-8">
      <title>Title</title>
  </head>
  <body>
      <h1>Shiro登录认证</h1>
  
      <form action="/shiro/user/userLogin" method="post">
          <div>用户名: <input type="text" name="username" id="username" value=""></div>
          <div>密码: <input type="password" name="passwd" id="passwd" value=""></div>
          <div><input type="checkbox" name="rememberMe" value="true"></div>
          <div><input type="submit" value="登录"></div>
      </form>
  </body>
  </html>
  ```

- 添加rememberMe相关配置

  ```java
  @Configuration
  public class ShiroConfig {
  
      private final MyRealm myRealm;
  
      private final SecondRealm secondRealm;
  
      @Autowired
      public ShiroConfig(MyRealm myRealm, SecondRealm secondRealm) {
          this.myRealm = myRealm;
          this.secondRealm = secondRealm;
      }
  
      /**
       * 配置defaultWebSecurityManager
       *
       * @return {@link DefaultWebSecurityManager}
       */
      @Bean
      public DefaultWebSecurityManager defaultWebSecurityManager() {
          // 1.创建defaultWebSecurityManager
          DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
  
          // 2.创建加密对象,设置相关属性
          HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
          hashedCredentialsMatcher.setHashAlgorithmName("md5");
          hashedCredentialsMatcher.setHashIterations(1024);
  
          // 3.将加密对象存储到myRealm中
          myRealm.setCredentialsMatcher(hashedCredentialsMatcher);
          secondRealm.setCredentialsMatcher(hashedCredentialsMatcher);
  
          // 4.修改默认认证策略
          defaultWebSecurityManager.setAuthenticator(modularRealmAuthenticator());
  
          // 5.设置多Realm
          Set<Realm> realms = new HashSet<>(16);
          realms.add(myRealm);
          realms.add(secondRealm);
          defaultWebSecurityManager.setRealms(realms);
  
          // 6.设置rememberMe
          defaultWebSecurityManager.setRememberMeManager(rememberMeManager());
  
          return defaultWebSecurityManager;
      }
  
      /**
       * 配置认证策略
       *
       * @return {@link Authenticator}
       */
      @Bean
      public ModularRealmAuthenticator modularRealmAuthenticator() {
          ModularRealmAuthenticator modularRealmAuthenticator = new ModularRealmAuthenticator();
          // 修改默认认证策略
          modularRealmAuthenticator.setAuthenticationStrategy(new AtLeastOneSuccessfulStrategy());
          return modularRealmAuthenticator;
      }
  
      /**
       * cookie属性设置
       *
       * @return {@link SimpleCookie}
       */
      @Bean
      public SimpleCookie rememberMeCookie() {
          // 需要与前端记住我的name属性值一致
          SimpleCookie cookie = new SimpleCookie("rememberMe");
  
          cookie.setPath("/");
          cookie.setHttpOnly(true);
          cookie.setMaxAge(86400);
          return cookie;
      }
  
      /**
       * Shiro的cookie管理对象
       *
       * @return {@link CookieRememberMeManager}
       */
      @Bean
      public CookieRememberMeManager rememberMeManager() {
          CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
          cookieRememberMeManager.setCookie(rememberMeCookie());
          // 手动设置对称加密秘钥，防止重启系统后系统生成新的随机秘钥，防止导致客户端cookie无效
          cookieRememberMeManager.setCipherKey(Base64.decode("4AvVhmFLUs0KTA3Kprsdag=="));
          return cookieRememberMeManager;
      }
  
  
      /**
       * 配置shiro内置过滤器拦截范围
       *
       * @return {@link DefaultShiroFilterChainDefinition}
       */
      @Bean
      public DefaultShiroFilterChainDefinition shiroFilterChainDefinition() {
          DefaultShiroFilterChainDefinition shiroFilterChainDefinition = new DefaultShiroFilterChainDefinition();
  
          // 配置匿名访问路径
          shiroFilterChainDefinition.addPathDefinition("/user/userLogin", "anon");
          shiroFilterChainDefinition.addPathDefinition("/user/login", "anon");
  
          // 配置认证访问路径
          shiroFilterChainDefinition.addPathDefinition("/**", "authc");
          // 添加存在用户的过滤器,在remember me功能中,用户拦截器用于验证并恢复用户的身份,启用用户拦截器才能使用remember me功能
          shiroFilterChainDefinition.addPathDefinition("/**", "user");
  
          return shiroFilterChainDefinition;
      }
  }
  ```

- 修改Controller、Service相关代码

  ```java
  @Controller
  @RequestMapping("/user")
  public class UserController {
  
      private final IUserService userService;
  
      @Autowired
      public UserController(IUserService userService) {
          this.userService = userService;
      }
  
      @GetMapping("/login")
      public String login() {
          return "login";
      }
  
      /**
       * 登录
       */
      @PostMapping("/userLogin")
      public String login(@ModelAttribute LoginUserDTO dto, HttpSession session) {
          boolean flag = userService.login(dto, session);
          if (flag) {
              return "main";
          }
          return "error";
      }
  
      /**
       * 用于测试rememberMe功能是否生效
       */
      @GetMapping("/userLoginRememberMe")
      public String userLoginRememberMe(HttpSession session) {
          session.setAttribute("user", "rememberMe");
          return "userLoginRememberMe";
      }
  }
  ```

  ```java
  public interface IUserService extends IService<User> {
  
      /**
       * 根据用户名查询用户
       *
       * @param name 名字
       * @return {@link User}
       */
      User getUserInfoByName(String name);
  
  
      /**
       * 登录
       *
       * @param dto 用户登录DTO
       * @param session 会话
       * @return boolean
       */
      boolean login(LoginUserDTO dto, HttpSession session);
  }
  ```

  ```java
  @Service
  public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
  
      private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);
  
      private final UserMapper userMapper;
  
      @Autowired
      public UserServiceImpl(UserMapper userMapper) {
          this.userMapper = userMapper;
      }
  
      /**
       * 根据用户名查询用户
       */
      @Override
      public User getUserInfoByName(String name) {
          return userMapper.selectOne(new LambdaQueryWrapper<User>().eq(User::getUsername, name));
      }
  
      /**
       * 登录
       */
      @Override
      public boolean login(LoginUserDTO dto, HttpSession session) {
          // 1.获取subject对象
          Subject subject = SecurityUtils.getSubject();
  
          // 2.使用用户名和密码构建UsernamePasswordToken对象
          UsernamePasswordToken token = new UsernamePasswordToken(dto.getUsername(), dto.getPasswd(), dto.getRememberMe());
  
          // 3.调用登录方法
          try {
              subject.login(token);
              session.setAttribute("user", token.getPrincipal());
              return true;
          } catch (AuthenticationException e) {
              LOGGER.error("登录失败: {}", e);
              e.printStackTrace();
          }
          return false;
      }
  }
  ```

<span style="color:red">注意：如果Remeber Me功能启用不成功，可从以下几个方面进行排查：</span>

1. <span style="color:red">是否在DefaultShiroFilterChainDefinition中设置用户拦截器。</span>
2. <span style="color:red">在CookieRememberMeManager中是否手动设置对称加密秘钥，防止重启系统后系统生成新的随机秘钥，防止导致客户端cookie无效。</span>
3. <span style="color:red">SimpleCookie中的name属性值要与前端记住我的name属性值一致。</span>
4. <span style="color:red">如果登录请求参数为对象，对象必须实现Serializable接口。</span>
5. <span style="color:red">检查浏览器是否开启了退出浏览器清除cookie功能。</span>

## 用户登录认证后登出

用户登录以后可以直接使用Shiro的过滤器即可实现登出。

代码示例如下：

```html
<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
    <h1>Welcome to index</h1>

    登录用户为: <span th:text="${session.user}"></span>

    <br>
    <a href="/shiro/logout">登出</a>

</body>
</html>
```

```java
@Configuration
public class ShiroConfig {
    /**
     * 配置shiro内置过滤器拦截范围
     *
     * @return {@link DefaultShiroFilterChainDefinition}
     */
    @Bean
    public DefaultShiroFilterChainDefinition shiroFilterChainDefinition() {
        DefaultShiroFilterChainDefinition shiroFilterChainDefinition = new DefaultShiroFilterChainDefinition();

        // 配置匿名访问路径
        shiroFilterChainDefinition.addPathDefinition("/user/userLogin", "anon");
        shiroFilterChainDefinition.addPathDefinition("/user/login", "anon");

        // 配置登出拦截器
        shiroFilterChainDefinition.addPathDefinition("/logout", "logout");

        // 配置认证访问路径
        shiroFilterChainDefinition.addPathDefinition("/**", "authc");
        // 添加存在用户的过滤器,在remember me功能中,用户拦截器用于验证并恢复用户的身份,启用用户拦截器才能使用remember me功能
        shiroFilterChainDefinition.addPathDefinition("/**", "user");

        return shiroFilterChainDefinition;
    }
}
```

## 授权

用户登录后，需要验证是否具有指定角色权限，Shiro也提供了非常方便的工具判断。这个工具就是Realm的`doGetAuthorizationInfo`方法进行判断，触发权限判断主要有以下两种方式，下面主要介绍注解方式：

1. 在页面中通过shiro:****属性判断
2. 在接口服务中通过注解@Requires****进行判断

通过给接口服务方法添加注解可以实现权限校验，可以加在控制器方法上，也可以加在业务方法上，一般是加在控制器方法上。常用注解如下：

- @RequiresAuthentication：验证用户是否登录，等同于方法subject.isAuthenticated()。
- @RequiresUser：验证用户是否认证成功或记住，登录认证成功subject.isAuthenticated()为true，登录后被记住subject.isRemembered()为true。
- @RequiresGuest：验证是否是一个guest(游客)的请求。
- @RequiresRoles：验证subject是否有对应的角色，没有则会抛出AuthorizationException。
- @RequiresPermissions：验证subject是否有相应权限，有权限访问权限注解下的方法，没有则会抛出异常AuthorizationException。

### 创建数据表

```sql
DROP TABLE IF EXISTS `role`;

CREATE TABLE `role` (
	role_id BIGINT(20) auto_increment NOT NULL COMMENT '角色ID',
	role_name varchar(100) NOT NULL COMMENT '角色名称',
	remark varchar(255) NULL COMMENT '备注',
	CONSTRAINT role_PK PRIMARY KEY (role_id)
)
ENGINE=InnoDB
DEFAULT CHARSET=utf8mb4
COLLATE=utf8mb4_unicode_ci
COMMENT='角色表';

INSERT INTO `role`(role_name,remark) VALUES('admin','管理员'),('test','测试');

DROP TABLE IF EXISTS `user_role`;

CREATE TABLE user_role (
	id BIGINT(20) auto_increment NOT NULL COMMENT '主键ID',
	user_id BIGINT(20) NOT NULL COMMENT '用户ID',
	role_id BIGINT NOT NULL COMMENT '角色ID',
	CONSTRAINT user_role_PK PRIMARY KEY (id)
)
ENGINE=InnoDB
DEFAULT CHARSET=utf8mb4
COLLATE=utf8mb4_unicode_ci
COMMENT='用户角色关联表';

INSERT INTO `user_role`(user_id,role_id) VALUES(1,1),(1,2),(2,2);

DROP TABLE IF EXISTS `permission`;

CREATE TABLE permission (
	permission_id BIGINT(20) auto_increment NOT NULL COMMENT '权限ID',
	url varchar(100) NOT NULL COMMENT '权限URL',
	`desc` varchar(255) NULL COMMENT '权限描述',
	CONSTRAINT permission_PK PRIMARY KEY (permission_id)
)
ENGINE=InnoDB
DEFAULT CHARSET=utf8mb4
COLLATE=utf8mb4_unicode_ci
COMMENT='权限表';

INSERT INTO `permission`(url,`desc`) VALUES('user:add','新增用户'),('user:edit','修改用户'),('user:delete','删除用户');

DROP TABLE IF EXISTS `role_permission`;

CREATE TABLE role_permission (
	id BIGINT(20) auto_increment NOT NULL COMMENT '主键ID',
	role_id BIGINT(20) NOT NULL COMMENT '角色ID',
	permission_id BIGINT(20) NOT NULL COMMENT '权限ID',
	CONSTRAINT role_permission_PK PRIMARY KEY (id)
)
ENGINE=InnoDB
DEFAULT CHARSET=utf8mb4
COLLATE=utf8mb4_unicode_ci
COMMENT='角色权限关联表';

INSERT INTO `role_permission`(role_id,permission_id) VALUES(1,1),(1,2),(1,3),(2,2);
```

### 验证用户角色

```java
@Controller
@RequestMapping("/user")
public class UserController {

    private final IUserService userService;

    @Autowired
    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    /**
     * 登录
     */
    @PostMapping("/userLogin")
    public String login(@ModelAttribute LoginUserDTO dto, HttpSession session) {
        boolean flag = userService.login(dto, session);
        if (flag) {
            return "main";
        }
        return "error";
    }

    @RequiresRoles("admin")
    @ResponseBody
    @GetMapping("/userLoginRoles")
    public String userLoginRoles() {
        System.out.println("验证用户角色信息");
        return "该用户有admin角色";
    }
}
```

```java
public interface IUserService extends IService<User> {

    /**
     * 根据用户名查询用户
     *
     * @param name 名字
     * @return {@link User}
     */
    User getUserInfoByName(String name);


    /**
     * 登录
     *
     * @param dto     用户登录DTO
     * @param session 会话
     * @return boolean
     */
    boolean login(LoginUserDTO dto, HttpSession session);

    /**
     * 根据用户名查询角色列表
     *
     * @param username 用户名
     * @return {@link List}<{@link String}>
     */
    List<String> findRolesByUsername(String username);
}
```

```java
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    private final UserMapper userMapper;

    @Autowired
    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    /**
     * 根据用户名查询用户
     */
    @Override
    public User getUserInfoByName(String name) {
        return userMapper.selectOne(new LambdaQueryWrapper<User>().eq(User::getUsername, name));
    }

    /**
     * 根据用户名查询角色列表
     */
    @Override
    public List<String> findRolesByUsername(String username) {
        return userMapper.selectRolesByUsername(username);
    }

    /**
     * 登录
     */
    @Override
    public boolean login(LoginUserDTO dto, HttpSession session) {
        // 1.获取subject对象
        Subject subject = SecurityUtils.getSubject();

        // 2.使用用户名和密码构建UsernamePasswordToken对象
        UsernamePasswordToken token = new UsernamePasswordToken(dto.getUsername(), dto.getPasswd(), dto.getRememberMe());

        // 3.调用登录方法
        try {
            subject.login(token);
            session.setAttribute("user", token.getPrincipal());
            return true;
        } catch (AuthenticationException e) {
            LOGGER.error("登录失败: {}", e);
            e.printStackTrace();
        }
        return false;
    }
}
```

```java
@Mapper
public interface UserMapper extends BaseMapper<User> {

    /**
     * 根据用户名查询角色列表
     *
     * @param username 用户名
     * @return {@link List}<{@link String}>
     */
    List<String> selectRolesByUsername(@Param("username") String username);
}
```

```java
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="com.yu.shiro.mapper.UserMapper">

    <select id="selectRolesByUsername" resultType="java.lang.String" parameterType="java.lang.String">
        SELECT
            r.role_name
        FROM
            user_role ur
        LEFT JOIN `user` u ON
            ur.user_id = u.user_id
        LEFT JOIN `role` r ON
            ur.role_id = r.role_id
        WHERE
            u.username = #{username}
    </select>
</mapper>
```

```java
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

        // 3.封装角色列表并返回
        info.addRoles(roles);

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
}
```

```java
<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
    <h1>Welcome to index</h1>

    登录用户为: <span th:text="${session.user}"></span>

    <br><br>
    <a href="/shiro/logout">登出</a>
    <br><br>

    <a href="/shiro/user/userLoginRoles">授权-用户角色</a>

</body>
</html>
```



### 验证用户权限

```java
@Controller
@RequestMapping("/user")
public class UserController {

    private final IUserService userService;

    @Autowired
    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    /**
     * 登录
     */
    @PostMapping("/userLogin")
    public String login(@ModelAttribute LoginUserDTO dto, HttpSession session) {
        boolean flag = userService.login(dto, session);
        if (flag) {
            return "main";
        }
        return "error";
    }

    @RequiresRoles("admin")
    @ResponseBody
    @GetMapping("/userLoginRoles")
    public String userLoginRoles() {
        System.out.println("验证用户角色信息");
        return "该用户有admin角色";
    }

    @RequiresPermissions("user:add")
    @ResponseBody
    @GetMapping("/userLoginPermissions")
    public String userLoginPermissions() {
        System.out.println("验证用户权限信息");
        return "该用户有user:add权限";
    }
}
```

```java
public interface IUserService extends IService<User> {

    /**
     * 根据用户名查询用户
     *
     * @param name 名字
     * @return {@link User}
     */
    User getUserInfoByName(String name);


    /**
     * 登录
     *
     * @param dto     用户登录DTO
     * @param session 会话
     * @return boolean
     */
    boolean login(LoginUserDTO dto, HttpSession session);

    /**
     * 根据用户名查询角色列表
     *
     * @param username 用户名
     * @return {@link List}<{@link String}>
     */
    List<String> findRolesByUsername(String username);


    /**
     * 根据角色名称数组查询权限列表
     *
     * @param roleNames 角色名称数组
     * @return {@link List}<{@link String}>
     */
    List<String> findPermissionsByRoleNames(String[] roleNames);
}
```

```java
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    private final UserMapper userMapper;

    @Autowired
    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    /**
     * 根据用户名查询用户
     */
    @Override
    public User getUserInfoByName(String name) {
        return userMapper.selectOne(new LambdaQueryWrapper<User>().eq(User::getUsername, name));
    }

    /**
     * 根据用户名查询角色列表
     */
    @Override
    public List<String> findRolesByUsername(String username) {
        return userMapper.selectRolesByUsername(username);
    }

    /**
     * 根据角色名称数组查询权限列表
     */
    @Override
    public List<String> findPermissionsByRoleNames(String[] roleNames) {
        return userMapper.selectPermissionsByRoleNames(roleNames);
    }

    /**
     * 登录
     */
    @Override
    public boolean login(LoginUserDTO dto, HttpSession session) {
        // 1.获取subject对象
        Subject subject = SecurityUtils.getSubject();

        // 2.使用用户名和密码构建UsernamePasswordToken对象
        UsernamePasswordToken token = new UsernamePasswordToken(dto.getUsername(), dto.getPasswd(), dto.getRememberMe());

        // 3.调用登录方法
        try {
            subject.login(token);
            session.setAttribute("user", token.getPrincipal());
            return true;
        } catch (AuthenticationException e) {
            LOGGER.error("登录失败: {}", e);
            e.printStackTrace();
        }
        return false;
    }
}
```

```java
@Mapper
public interface UserMapper extends BaseMapper<User> {

    /**
     * 根据用户名查询角色列表
     *
     * @param username 用户名
     * @return {@link List}<{@link String}>
     */
    List<String> selectRolesByUsername(@Param("username") String username);

    /**
     * 根据角色名称数组查询权限列表
     *
     * @param roleNames 角色名称数组
     * @return {@link List}<{@link String}>
     */
    List<String> selectPermissionsByRoleNames(@Param("roleNames") String[] roleNames);
}
```

```java
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="com.yu.shiro.mapper.UserMapper">

    <select id="selectRolesByUsername" resultType="java.lang.String" parameterType="java.lang.String">
        SELECT
            r.role_name
        FROM
            user_role ur
        LEFT JOIN `user` u ON
            ur.user_id = u.user_id
        LEFT JOIN `role` r ON
            ur.role_id = r.role_id
        WHERE
            u.username = #{username}
    </select>

    <select id="selectPermissionsByRoleNames" resultType="java.lang.String">
        SELECT
            p.url
        FROM
            role_permission rp
        LEFT JOIN `role` r ON
            rp.role_id = r.role_id
        LEFT JOIN permission p ON
            rp.permission_id = p.permission_id
        WHERE
            r.role_name IN
        <foreach item="name" collection="roleNames" open="(" separator="," close=")">
            #{name}
        </foreach>
        GROUP BY p.url
    </select>
</mapper>
```

```java
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
}
```

```java
<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
    <h1>Welcome to index</h1>

    登录用户为: <span th:text="${session.user}"></span>

    <br><br>
    <a href="/shiro/logout">登出</a>
    <br><br>

    <a href="/shiro/user/userLoginRoles">授权-用户角色</a>

    <br><br>
    <a href="/shiro/user/userLoginPermissions">授权-用户权限</a>

</body>
</html>
```

## 认证授权异常处理

```java
@RestControllerAdvice
public class PermissionException {

    @ExceptionHandler(UnauthorizedException.class)
    public String unauthorizedException(Exception e) {
        return "无权限!";
    }
}
```

## 整合EhCache

EhCache是一种广泛使用的`开源Java分布式缓存`。主要面向通用缓存、JavaEE和轻量级容器。可以和大部分Java项目无缝整合，例如Hibernate中的缓存就是基于EhCache实现的。EhCache支持`内存和磁盘存储`，默认存储在`内存`中，如内存不够时把缓存数据同步到磁盘中。EhCache支持基于Filter的Cache实现，也支持Gzip压缩算法。

EhCache的优点是直接在JVM虚拟机中缓存，速度快，效率高。缺点是缓存共享麻烦，集群分布式应用不方便。

1. 添加依赖

   ```xml
   <!-- shiro整合ehcache -->
   <dependency>
       <groupId>org.apache.shiro</groupId>
       <artifactId>shiro-ehcache</artifactId>
       <version>${shiro-ehcache.version}</version>
   </dependency>
   
   <dependency>
       <groupId>commons-io</groupId>
       <artifactId>commons-io</artifactId>
       <version>${commons-io.version}</version>
   </dependency>
   ```

2. 创建配置文件和配置信息

   ```xml
   <?xml version="1.0" encoding="UTF-8"?>
   <ehcache name="ehcache" updateCheck="false">
       <!-- 磁盘的缓存位置 -->
       <diskStore path="java.io.tmpdir"/>
       <!-- 默认缓存 -->
       <defaultCache
              maxEntriesLocalHeap="1000"
              eternal="false"
              timeToIdleSeconds="3600"
              timeToLiveSeconds="3600"
              overflowToDisk="false" />
   
       <!-- 登录认证信息缓存: 缓存用户角色权限 -->
       <cache name="loginRolesPermissionsCache"
              maxEntriesLocalHeap="2000"
              eternal="false"
              timeToIdleSeconds="600"
              timeToLiveSeconds="0"
              overflowToDisk="false"
              statistics="true" />
   </ehcache>
   ```

3. 配置ehCacheManager

   ```java
   @Configuration
   public class ShiroConfig {
   
       private final MyRealm myRealm;
   
       private final SecondRealm secondRealm;
   
       @Autowired
       public ShiroConfig(MyRealm myRealm, SecondRealm secondRealm) {
           this.myRealm = myRealm;
           this.secondRealm = secondRealm;
       }
   
       /**
        * 配置defaultWebSecurityManager
        *
        * @return {@link DefaultWebSecurityManager}
        */
       @Bean
       public DefaultWebSecurityManager defaultWebSecurityManager() {
           // 1.创建defaultWebSecurityManager
           DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
   
           // 2.创建加密对象,设置相关属性
           HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
           hashedCredentialsMatcher.setHashAlgorithmName("md5");
           hashedCredentialsMatcher.setHashIterations(1024);
   
           // 3.将加密对象存储到myRealm中
           myRealm.setCredentialsMatcher(hashedCredentialsMatcher);
           secondRealm.setCredentialsMatcher(hashedCredentialsMatcher);
   
           // 4.修改默认认证策略
           defaultWebSecurityManager.setAuthenticator(modularRealmAuthenticator());
   
           // 5.设置多Realm
           Set<Realm> realms = new HashSet<>(16);
           realms.add(myRealm);
           realms.add(secondRealm);
           defaultWebSecurityManager.setRealms(realms);
   
           // 6.配置ehcache
           defaultWebSecurityManager.setCacheManager(ehCacheManager());
   
           return defaultWebSecurityManager;
       }
   
       /**
        * 配置ehcache
        *
        * @return {@link EhCacheManager}
        */
       @Bean
       public EhCacheManager ehCacheManager() {
           // 创建ehCacheManager
           EhCacheManager ehCacheManager = new EhCacheManager();
   
           // 读取配置文件
           InputStream is = null;
           try {
               is = ResourceUtils.getInputStreamForPath("classpath:ehcache/ehcache-shiro.xml");
           } catch (IOException e) {
               e.printStackTrace();
           }
           CacheManager cacheManager = new CacheManager(is);
           ehCacheManager.setCacheManager(cacheManager);
           return ehCacheManager;
       }
       
       /**
        * 配置认证策略
        *
        * @return {@link Authenticator}
        */
       @Bean
       public ModularRealmAuthenticator modularRealmAuthenticator() {
           ModularRealmAuthenticator modularRealmAuthenticator = new ModularRealmAuthenticator();
           // 修改默认认证策略
           modularRealmAuthenticator.setAuthenticationStrategy(new AtLeastOneSuccessfulStrategy());
           return modularRealmAuthenticator;
       }
   }
   ```