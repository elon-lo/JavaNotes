## 1、Shiro是什么？

- Apache Shiro是Java的一个安全(权限)框架
- Shiro可以非常容易的开发出足够好的应用，其不仅可以用在JavaSE环境，也可以用在JavaEE环境
- Shiro可以完成：认证、授权、加密、会话管理、与Web集成、缓存等
- 下载：http://shiro.apache.org/

## 2、Shiro可以做什么？

- 验证用户身份
- 用户访问权限控制，比如：1、判断用户是否分配了一定的安全角色。2、判断用户是否被授予完成某个操作的权限
- 在非 Web 或 EJB 容器的环境下可以任意使用Session API
- 可以响应认证、访问控制，或者 Session 生命周期中发生的事件
- 可将一个或以上用户安全数据源数据组合成一个复合的用户 “view”(视图)
- 支持单点登录(SSO)功能
- 支持提供“Remember Me”服务，获取用户关联信息而无需登录

## 3、Shiro功能简介

- 基本功能点如下图所示：

  ![](https://shiro.apache.org/assets/images/ShiroFeatures.png)

  Authentication（认证）, Authorization（授权）, Session Management（会话管理）, Cryptography（加密）被 Shiro 框架的开发团队称之为应用安全的四大基石。那么就让我们来看看它们吧：

  - **Authentication（认证）：**用户身份识别，通常被称为用户“登录”
  - **Authorization（授权）：**访问控制。比如某个用户是否具有某个操作的使用权限。
  - **Session Management（会话管理）：**特定于用户的会话管理,甚至在非web 或 EJB 应用程序。
  - **Cryptography（加密）：**在对数据源使用加密算法加密的同时，保证易于使用。

  还有其他的功能来支持和加强这些不同应用环境下安全领域的关注点。特别是对以下的功能支持：

  - **Web支持：**Shiro的Web支持API有助于保护Web应用程序。

  - **缓存：**缓存是Apache Shiro API中的第一级，以确保安全操作保持快速和高效。

  - **并发性：**Apache Shiro支持具有并发功能的多线程应用程序。

  - **测试：**存在测试支持，可帮助您编写单元测试和集成测试，并确保代码按预期得到保障。

  - **“运行方式”：**允许用户承担另一个用户的身份(如果允许)的功能，有时在管理方案中很有用。

  - **“记住我”：**记住用户在会话中的身份，所以用户只需要强制登录即可。

    

    **注意：** Shiro不会去维护用户、维护权限，这些需要我们自己去设计/提供，然后通过相应的接口注入给Shiro



## 4、Shiro架构

在概念层，Shiro 架构包含三个主要的理念：Subject,SecurityManager和 Realm。下面的图展示了这些组件如何相互作用，我们将在下面依次对其进行描述

![](https://pic3.zhimg.com/80/v2-c0841dfc8cb19a94c322eef635371cf6_720w.jpg)

- **Subject：**当前用户，Subject 可以是一个人，但也可以是第三方服务、守护进程帐户、时钟守护任务或者其它–当前和软件交互的任何事件。
- **SecurityManager：**管理所有Subject，SecurityManager 是 Shiro 架构的核心，配合内部安全组件共同组成安全伞。
- **Realms：**用于进行权限信息的验证，我们自己实现。Realm 本质上是一个特定的安全 DAO：它封装与数据源连接的细节，得到Shiro 所需的相关的数据。在配置 Shiro 的时候，你必须指定至少一个Realm 来实现认证（authentication）和/或授权（authorization）。

我们需要实现Realms的Authentication 和 Authorization。其中 Authentication 是用来验证用户身份，Authorization 是授权访问控制，用于对用户进行的操作授权，证明该用户是否允许进行当前操作，如访问某个链接，某个资源文件等。



## 5、Shiro认证过程

![](https://pic4.zhimg.com/v2-2531156d2e6fb3ec0702f1d1ed795f43_r.jpg)

## 6、Shiro快速入门

```java
/**
 * Simple Quickstart application showing how to use Shiro's API.
 *
 * @since 0.9 RC2
 */
public class Quickstart {

    private static final transient Logger log = LoggerFactory.getLogger(Quickstart.class);


    public static void main(String[] args) {

       
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro.ini");
        SecurityManager securityManager = factory.getInstance();
        
        SecurityUtils.setSecurityManager(securityManager);
        
        //获取当前的Subject,调用SecurityUtils.getSubject()
        Subject currentUser = SecurityUtils.getSubject();
        
        //测试使用session
        //获取Session: 调用Subject#getSession()
        Session session = currentUser.getSession();
        session.setAttribute("someKey", "aValue");
        String value = (String) session.getAttribute("someKey");
        if (value.equals("aValue")) {
            log.info("=========>  Retrieved the correct value! [" + value + "]");
        }
        
        //测试当前的用户是否已经被认证,即是否登录
        //调用Subject#isAuthenticated()
        if (!currentUser.isAuthenticated()) {
            //把用户名和密码封装为UsernamePasswordToken对象
            UsernamePasswordToken token = new UsernamePasswordToken("lonestarr", "vespass");
            token.setRememberMe(true);
            try {
                //执行登录
                currentUser.login(token);
                //若没有指定的账户,shiro将会抛出UnknownAccountException异常
            } catch (UnknownAccountException uae) {
                log.info("========>  There is no user with username of " + token.getPrincipal());
                //若账户存在,但是密码不匹配,shiro将会抛出IncorrectCredentialsException异常
            } catch (IncorrectCredentialsException ice) {
                log.info("========>  Password for account " + token.getPrincipal() + " was incorrect!");
                //用户被锁定的异常LockedAccountException
            } catch (LockedAccountException lae) {
                log.info("The account for username " + token.getPrincipal() + " is locked.  " +
                        "Please contact your administrator to unlock it.");
            }
            //所有认证时异常的父类
            catch (AuthenticationException ae) {
                //unexpected condition?  error?
            }
        }

        
        log.info("User [" + currentUser.getPrincipal() + "] logged in successfully.");
        
        //判断当前用户是否有某一个角色,调用Subject的hasRole()
        if (currentUser.hasRole("schwartz")) {
            log.info("========>  May the Schwartz be with you!");
        } else {
            log.info("Hello, mere mortal.");
        }
        
        //判断当前用户是否有某种权限,调用Subject的isPermitted()
        if (currentUser.isPermitted("lightsaber:wield")) {
            log.info("You may use a lightsaber ring.  Use it wisely.");
        } else {
            log.info("Sorry, lightsaber rings are for schwartz masters only.");
        }
        
        //测试用户是否具备某一个权限
        if (currentUser.isPermitted("winnebago:drive:eagle5")) {
                                    //如:user:delete:zhangsan
            log.info("You are permitted to 'drive' the winnebago with license plate (id) 'eagle5'.  " +
                    "Here are the keys - have fun!");
        } else {
            log.info("Sorry, you aren't allowed to drive the 'eagle5' winnebago!");
        }
        
        //执行登出,调用Subject的logout()

        System.out.println(currentUser.isAuthenticated());
        currentUser.logout();
        System.out.println(currentUser.isAuthenticated());

        System.exit(0);
    }
}

```



## 7、Shiro整合Spring

```xml
<!--web.xml-->

<?xml version="1.0" encoding="UTF-8"?>
<web-app xmlns="http://xmlns.jcp.org/xml/ns/javaee"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee http://xmlns.jcp.org/xml/ns/javaee/web-app_4_0.xsd"
         version="4.0">
    
    <!--配置Spring-->
    <context-param>
        <param-name>contextConfigLocation</param-name>
        <param-value>classpath:applicationContext.xml</param-value>
    </context-param>

    <listener>
        <listener-class>org.springframework.web.context.ContextLoaderListener</listener-class>
    </listener>

    <!--配置Spring MVC-->
    <servlet>
        <servlet-name>springmvc</servlet-name>
        <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
        <load-on-startup>1</load-on-startup>
    </servlet>

    <servlet-mapping>
        <servlet-name>springmvc</servlet-name>
        <url-pattern>/</url-pattern>
    </servlet-mapping>


     <!--
        配置shiro的filter
        DelegatingFilterProxy实际上是Filter的一个代理对象,默认情况下,Spring会到IOC容器中查找
        和<filter-name>对应的filter bean,也可以通过targetBeanName的初始化参数来配置filter bean 的id
    -->
    <filter>
        <filter-name>shiroFilter</filter-name>
        <filter-class>org.springframework.web.filter.DelegatingFilterProxy</filter-class>
        <init-param>
            <param-name>targetFilterLifecycle</param-name>
            <param-value>true</param-value>
        </init-param>
    </filter>

    <filter-mapping>
        <filter-name>shiroFilter</filter-name>
        <url-pattern>/*</url-pattern>
    </filter-mapping>
</web-app>
```

```xml
<!--springmvc-servlet.xml-->
<beans>
 <!--配置自动扫描包-->
    <context:component-scan base-package="com.yu.shiro"/>
    <!--配置视图解析器-->
    <bean class="org.springframework.web.servlet.view.InternalResourceViewResolver">
        <property name="prefix" value="/"/>
        <property name="suffix" value=".jsp"/>
    </bean>

    <!--处理静态资源-->
    <mvc:annotation-driven></mvc:annotation-driven>

    <mvc:default-servlet-handler />
</beans>
```

```xml
<!--applicationContext.xml-->
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" 
       xsi:schemaLocation="http://www.springframework.org/schema/beans
       http://www.springframework.org/schema/beans/spring-beans.xsd">

    <!--
    1、配置SecurityManager
    -->
    <bean id="securityManager" class="org.apache.shiro.web.mgt.DefaultWebSecurityManager">
       
        <property name="realm" ref="MyRealm"/>
        <!--<property name="cacheManager" ref="cacheManager"/>-->
    </bean>
    <!--
    2、配置EhCacheManager
    2.1、加入ehcache的jar包及配置文件
    -->
    <!--<bean id="cacheManager" class="org.apache.shiro.cache.ehcache.EhCacheManager">
        <property name="cacheManagerConfigFile" value="classpath:ehcache.xml"/>
    </bean>-->

    <!--
    3、配置Realm
    3.1、配置直接实现了org.apache.shiro.realm.Realm接口的bean
    -->
    <bean id="MyRealm" class="com.yu.shiro.realms.MyRealm"></bean>

    <!-- Post processor that automatically invokes init() and destroy() methods -->
    <!--
    4、可以自动的来调用配置在Spring IOC容器中Shiro bean的生命周期方法
    -->
    <bean id="lifecycleBeanPostProcessor" class="org.apache.shiro.spring.LifecycleBeanPostProcessor"/>

    <!--
    5、启用IOC容器中使用shiro的注解,但必须在配置了lifecycleBeanPostProcessor之后才可以使用
    -->
    <bean id="defaultAdvisorAutoProxyCreator" class="org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator"
        depends-on="lifecycleBeanPostProcessor"/>

    <bean id="authorizationAttributeSourceAdvisor" class="org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor">
        <property name="securityManager" ref="securityManager"/>
    </bean>
    <!--
    6、配置shiroFilter
    6.1、id必须和web.xml文件中配置的DelegatingFilterProxy的<filter-name>一致
	若不一致,将会抛出NoSuchBeanDefinitionException,因为Shiro会来IOC容器寻找和<filter-name>名字对应的filter bean
    -->
    <bean id="shiroFilter" class="org.apache.shiro.spring.web.ShiroFilterFactoryBean">
        <property name="securityManager" ref="securityManager"/>
        <property name="loginUrl" value="/login.jsp"/>
        <property name="successUrl" value="/success.jsp"/>
        <property name="unauthorizedUrl" value="/unauthorized.jsp"/>

        <!--
        7、配置哪些页面需要受保护,以及访问这些页面需要的权限
        1).anon:可以匿名访问
        2).authc:必须认证(即登录)之后才可以访问
        -->
        <property name="filterChainDefinitions">
            <value>
                /login.jsp = anon
                # everything else requires authentication:
                /** = authc
            </value>
        </property>
    </bean>
</beans>
```

```properties
#log4j.properties
log4j.rootLogger=INFO, stdout

log4j.appender.stdout=org.apache.log4j.ConsoleAppender
log4j.appender.stdout.layout=org.apache.log4j.PatternLayout
log4j.appender.stdout.layout.ConversionPattern=%d %p [%c] - %m %n

# General Apache libraries
log4j.logger.org.apache=WARN

# Spring
log4j.logger.org.springframework=INFO

# Default Shiro logging
log4j.logger.org.apache.shiro=INFO

# Disable verbose logging
log4j.logger.org.apache.shiro.util.ThreadContext=WARN
log4j.logger.org.apache.shiro.cache.ehcache.EhCache=WARN

```

```java
public class MyRealm implements Realm {
    public String getName() {
        return "";
    }

    public boolean supports(AuthenticationToken authenticationToken) {
        return false;
    }

    public AuthenticationInfo getAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        return null;
    }

}
```

```jsp
<!--login.jsp-->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
    <h1>Login Page</h1>
</body>
</html>
```

```jsp
<!--success.jsp-->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Success</title>
</head>
<body>
    <h1>Success Page</h1>
</body>
</html>
```

## 8、ShiroFilter

- Shiro提供了与Web集成的支持，其通过一个ShiroFilter入口来拦截需要安全控制的URL，然后进行相应的控制

- ShiroFilter类似于如Strut2/Spring MVC这种web框架的前端控制器，是安全控制的入口点，其负责读取配置

  (如ini配置文件)，然后判断URL是否需要登录/权限等工作

- ShiroFilter的工作原理如下：

![](https://img-blog.csdn.net/20170219204401129?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvd194X3pf/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)



## 9、URL拦截器配置

- [urls]部分的配置，其格式是："url=拦截器(参数)，拦截器(参数)"
- 如果当前请求的url匹配[urls]部分的某个url模式，将会执行其配置的拦截器
- anon(anonymous)拦截器表示匿名访问(即不需要登录就可访问)
- authc(authentication)拦截器表示需要身份认证通过后才可以访问

## 10、URL匹配模式

- url模式使用Ant风格模式
- Ant路径通配符支持?、*、**、<span style='color:red'>注意通配符匹配不包括目录分隔符"/"</span>
  - ?：匹配一个字符，如/admin?将匹配/admin1，但不匹配/admin或/admin/
  - *：匹配零个或多个字符，如/admin *将匹配/admin或/admin123，但不匹配/admin/123
  - ** ：匹配路径中的零个或多个多个路径，如/admin/ **将匹配/admin/a或/admin/a/b
- URL匹配顺序如下：
  - URL权限采取第一次匹配优先的方式，即从头开始使用第一个匹配的url模式对应的拦截器链
  - 如：
    - /bb/**=filter1
    - /bb/aa=filter2
    - /**=filter3
    - 如果请求的url是"/bb/aa"，因为按照声明顺序匹配，所以将使用filter1拦截



## 11、Shiro认证流程

- 获取当前的Subject，调用SecurityUtils的getSubject()
- 测试当前的用户是否被认证，即是否登录，调用Subject的isAuthenticated()
- 若没有被认证，则把用户名和密码封装为UsernamePasswordToken对象
  - 创建一个表单页面
  - 把请求提交到Spring MVC的Handler
  - 获取用户名和密码
- 执行登录，调用Suject的login(AuthenticationToken)
- 自定义Realm方法，从数据库中获取对应的记录，返回给Shiro
  - 实际上需要继承org.apache.shiro.realm.AuthenticatingRealm
  - 实现doGetAuthenticationInfo(AuthenticationToken)
- 由Shiro完成对密码的校验
- 认证实现如下：

```java
@Controller
@RequestMapping("/shiro")
public class ShiroHandler {

    @RequestMapping("/login")
    public String login(@RequestParam("username") String username,@RequestParam("password")
                        String password){
        Subject subject = SecurityUtils.getSubject();

       if (!subject.isAuthenticated()){
           UsernamePasswordToken token = new UsernamePasswordToken(username,password);
           System.out.println("UsernamePasswordToken: "+token.hashCode());
           token.setRememberMe(true);

           try {
               subject.login(token);
           } catch (AuthenticationException e) {
               System.out.println("认证失败"+e.getMessage());
           }
       }

        return "redirect:/success.jsp";
    }
}
```

```java
/**
 * @ClassName
 * @Description TODO
 * @Author yu
 * @Date 2020/10/29 22:46
 */
public class MyRealm extends AuthenticatingRealm {
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        System.out.println("doGetAuthenticationInfo: "+token.hashCode());
        //1、将AuthenticationToken转换为UsernamePasswordToken
        UsernamePasswordToken uToken = (UsernamePasswordToken) token;
        //2、从UsernamePasswordToken中获取username
        String username =uToken.getUsername();
        //3、调用数据库的方法,从数据库中username对应的用户记录
        System.out.println("从数据库中获取的"+username+"的用户信息");
        //4、若用户不存在,则可能抛出UnknownAccountException异常
        if ("unknown".equals(username)){
            throw new UnknownAccountException("用户名不存在!");
        }
        //5、根据用户的情况,决定是否要抛出其他的AuthenticationException异常
        if ("monster".equals(username)){
            throw new LockedAccountException("账户被锁定");
        }
        //6、根据用户的情况,构建AuthenticationInfo对象并返回,通常使用的实现类: SimpleAuthenticationInfo
        //以下信息是从数据库中获取的
        // 1)、认证的实体信息,可以是username,也可以是数据表对应的用户的实体类对象
        Object principal = username;
        // 2)、credentials：密码(从数据库中获取)
        Object credentials = "123456";
        // 3)、realm：当前realm对象的name,调用父类的getName()即可
        String realName = getName();
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(principal,credentials,realName);


        return info;
    }
```

```xml
<bean id="shiroFilter" class="org.apache.shiro.spring.web.ShiroFilterFactoryBean">
        <property name="securityManager" ref="securityManager"/>
        <property name="loginUrl" value="/login.jsp"/>
        <property name="successUrl" value="/success.jsp"/>
        <property name="unauthorizedUrl" value="/unauthorized.jsp"/>

        <!--
        7、配置哪些页面需要受保护,以及访问这些页面需要的权限
        1).anon:可以匿名访问
        2).authc:必须认证(即登录)之后才可以访问
        3).logout:登出
        -->
        <property name="filterChainDefinitions">
            <value>
                /index.jsp = anon
                /shiro/login = anon
                /shiro/logout = logout
                # everything else requires authentication:
                /** = authc <!--
                                所有未认证的都被重定向到shiroFilter的loginUrl
                                若指定某一个或多个被拦截的请求(不包括index.jsp)
                                则默认跳转到index.jsp页面
                             -->

            </value>
        </property>
</bean>
```

- Shiro如何进行密码的校验

  - 通过AuthenticatingRealm的credentialsMatcher属性来进行密码的对比

  - 如何实现MD5加密

    - 替换当前的Realm的credentialsMatcher属性,直接使用HashedCredentialsMatcher对象,
      并设置加密算法即可

    ```java
     Object credentials = "123456";
     String algorithmName = "MD5";
     Object salt = null;
     int hashlterations = 1024;
     Object password = new SimpleHash(algorithmName,credentials,salt,hashlterations);
     System.out.println("AuthenticatingRealm======="+password+"=======");
    ```

    ```xml
    <bean id="MyRealm" class="com.yu.shiro.realms.MyRealm">
        <property name="credentialsMatcher">
            <bean class="org.apache.shiro.authc.credential.HashedCredentialsMatcher">
                <property name="hashAlgorithmName" value="MD5"/>
                <property name="hashIterations" value="1024"/>
            </bean>
        </property>
    </bean>
    ```

    

  - 如何实现MD5盐值加密

    - 在doGetAuthenticationInfo方法创建SimpleAuthenticationInfo对象的时候，需要使用

      SimpleAuthenticationInfo(username,credentials,credentialsSalt,realName)构造器

    - 使用ByteSource.Util.bytes()来计算盐值

    - 盐值需要唯一，一般使用随机字符串或user id

    - 使用new SimpleHash(algorithmName,credentials,credentialsSalt,hashlterations)来计算

      盐值加密后密码的值

    ```java
    // 1)、认证的实体信息,可以是username,也可以是数据表对应的用户的实体类对象
            Object principal = username;
            // 2)、credentials：密码(从数据库中获取)
            Object credentials = null;
            if ("admin".equals(username)){
                credentials = "038bdaf98f2037b31f1e75b5b4c9b26e";
            }else if ("user".equals(username)){
                credentials = "098d2c478e9c11555ce2823231e02ec1";
            }
            //盐值
            ByteSource credentialsSalt = ByteSource.Util.bytes(username);
    
            // 3)、realm：当前realm对象的name,调用父类的getName()即可
            String realName = getName();
    
    
            SimpleAuthenticationInfo info = null;
            info = new SimpleAuthenticationInfo(username,credentials,credentialsSalt,realName);
    
            return info;
    ```

    

    - 多个realm配置

    ```xml
    <bean id="securityManager" class="org.apache.shiro.web.mgt.DefaultWebSecurityManager">
        <property name="authenticator" ref="authenticator"/>
    </bean>
    
    <bean id="authenticator" class="org.apache.shiro.authc.pam.ModularRealmAuthenticator">
      	<property name="realms">
            <list>
                <ref bean="MyRealm"/>
                 <ref bean="secondRealm"/>
             </list>
         </property>
    </bean>
    
    <bean id="MyRealm" class="com.yu.shiro.realms.MyRealm">
         <property name="credentialsMatcher">
            <bean class="org.apache.shiro.authc.credential.HashedCredentialsMatcher">
                <property name="hashAlgorithmName" value="MD5"/>
                <property name="hashIterations" value="1024"/>
            </bean>
         </property>
    </bean>
    
    <bean id="secondRealm" class="com.yu.shiro.realms.SecondRealm">
         <property name="credentialsMatcher">
            <bean class="org.apache.shiro.authc.credential.HashedCredentialsMatcher">
               <property name="hashAlgorithmName" value="SHA1"/>
               <property name="hashIterations" value="1024"/>
            </bean>
         </property>
    </bean>
    ```



## 12、认证策略

- AuthenticationStrategy

  - AuthenticationStrategy接口的默认实现

  - FirstSuccessfulStrategy：只要有一个realm验证成功，只返回第一个realm身份验证成功后

    的认证信息，其他的忽略

  - AtLeastOneSuccessfulStrategy：只要有一个realm验证成功即可，和FirstSuccessfulStrategy

    不同，将返回所有realm身份验证成功后的认证信息

  - AllSuccessfulStrategy：所有realm验证成功才算成功，且返回所有realm身份验证成功后的

    认证信息，如果有一个失败就失败了

  - ModularRealmAuthenticator：默认是AtLeastOneSuccessfulStrategy策略

  - 修改认证策略：

  ```xml
  <bean id="authenticator" class="org.apache.shiro.authc.pam.ModularRealmAuthenticator">
          <property name="realms">
              <list>
                  <ref bean="MyRealm"/>
                  <ref bean="secondRealm"/>
              </list>
          </property>
          <property name="authenticationStrategy">
              <bean class="org.apache.shiro.authc.pam.AllSuccessfulStrategy"/>
          </property>
  </bean>
  ```

  

## 13、授权

- 授权，也叫访问控制，即在应用中控制谁访问哪些资源(如访问页面/编辑数据/页面操作等)，

  在授权中需了解几个关键对象：主体(Subject)、资源(Resource)、权限(Premission)、

  角色(Role)

  - 主体(Subject)：访问应用的用户，在Shiro中使用Subject代表该用户。用户只有授权后

    才允许访问相应的资源

  - 资源(Resource)：在应用中用户可以访问的URL，比如访问JSP页面、查看/编辑某些

    数据、访问某个业务方法、打印文本等等都是资源。用户需要授权后才能访问

  - 权限(Premission)：安全策略中的原子授权单位，通过权限我们可以表示在应用中

    用户有没有操作某个资源的权利。即权限表示在应用中用户能不能访问某个资源，

    如：访问用户列表页面查看/新增/修改/删除用户数据(即很多时候都是CRUD式权限

    控制)等。权限代表了用户有没有操作某个资源的权利，即反射在某个资源上的操作

    允不允许

  - Shiro支持粗粒度权限(如用户模块的所有权限)和细粒度权限(操作某个用户的权限，

    即实例级别的)

  - 角色(Role)：权限的集合，一般情况下会赋予用户角色而不是权限，即这样用户可以

    拥有一组权限，赋予权限时比较方便，典型的入：项目经理、技术总监、CTO等都是

    角色，不同的角色拥有一组不同的权限

- 授权方式

  Shiro支持三种方式的授权：

  - 编程式：通过写if/else授权代码块完成
  - 注解式：通过在执行的Java方法上放置相应的注解完成，没有权限将抛出相应的异常
  - JSP/GSP标签：在JSP/GSP页面通过相应的标签完成

- 认证相关的拦截器如下：

  - auth(org.apache.shiro.web.filter.authc.FormAuthenticationFilter)

    - 基于表单的拦截器：如"/**=authc"，如果没有登录会跳转到相应的登录页面登录

      主要属性如下：

      usernameParam：表单提交的用户参数名(username)

      passwordParam：表单提交的密码参数名(password)

      rememberMeParam：表单提交的密码参数名(rememberMe)

      loginUrl：登录页面地址(/login.jsp)

      successUrl：登录成功后的默认重定向地址

      failureKeyAttribute：登录失败后错误信息存储key(shiroLoginFailure)

  - authcBasic(org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter)

    Basic Http身份验证拦截器，主要属性如下：

    applicationName：弹出登录框显示的信息(application)

  - logout(org.apache.shiro.web.filter.authc.LogoutFilter)

    退出拦截器，主要属性如下：

    redirectUrl：退出成功后重定向的地址(/)，如："/logout=logout"

  - user(org.apache.shiro.web.filter.authc.UserFilter)

    用户拦截器，用户已经身份验证/记住我登录的都可以：如"/**=user"

  - anon(org.apache.shiro.web.filter.authc.AnonymousFilter)

    匿名拦截器，即不需要认证(登录)即可访问，一般用于静态资源过滤

    如："/static/**=anon"



- 授权相关的拦截器如下：

  - roles(org.apache.shiro.web.filter.authz.RolesAuthorizationFilter)

    - 角色授权拦截器，验证用户是否拥有所有角色，主要属性如下：

      loginUrl：登录页面地址(/login.jsp)

      unauthorizedUrl：未授权后重定向的地址

      如："/admin/**=roles[admin]"

  - perms(org.apache.shiro.web.filter.authz.PermissionAuthorizationFilter)

    - 权限授权拦截器，验证用户是否拥有所有权限，属性和roles一样

      如："/user/**=perms[user:create]"

  - port(org.apache.shiro.web.filter.authz.)

    - 端口拦截器，主要属性如下：

      port(80)：可以通过的端口

      如："/test=port[80]"，如果用户访问该页面是非80，将自动将请求端口

      改为80并重定向到该80端口，其他路径/参数等都一样

  - rest(org.apache.shiro.web.filter.authz.HttpMethodPermissionFilter)

    - rest风格拦截器，自动根据请求方法构建权限字符串

      (GET=read,POST=create,PUT=update,DELETE=delete,HEAD=read,TRACE=read,

      OPTIONS=read,MKCOL=create)构建权限字符串，如：

      "/users=rest[user]"，会自动拼出"user:read,user:create,user:update,user:delete"

      权限字符串进行权限匹配(所有都得匹配,isPermittedAll)

  - ssl(org.apache.shiro.web.filter.authz.SslFilter)

    - SSL拦截器，只有请求协议是https才能通过，否则会自动跳转到https端口(443)，

      其他和port拦截器一样

- 授权流程

  - 授权需要继承AuthorizingRealm类，并实现其doGetAuthorizationInfo方法

  - AuthorizingRealm继承自AuthenticatingRealm，但没有实现AuthenticatingRealm

    中的doGetAuthenticationInfo方法，所以认证和授权只需要继承AuthorizingRealm

    就可以了，同时实现两个抽象方法

  ```java
  //实现授权
      @Override
      protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
          System.out.println("Authorization doGetAuthorizationInfo");
          //1、从principalCollection中获取登录用户的信息
          Object principal = principalCollection.getPrimaryPrincipal();
          //2、利用登录用户的信息来查看当前用户的角色和权限(可能需要查询数据库)
          Set<String> roles = new HashSet<>();
          roles.add("user");
          if ("admin".equals(principal)){
              roles.add("admin");
          }
          //3、创建SimpleAuthorizationInfo,并设置roles
          SimpleAuthorizationInfo info = new SimpleAuthorizationInfo(roles);
          //4、返回SimpleAuthorizationInfo
          return info;
      }
  ```

  ```xml
  <!--applicationContext.xml-->
  <property name="filterChainDefinitions">
      <value>
          /index.jsp = anon
          /shiro/login = anon
          /shiro/logout = logout
  
          /user.jsp = roles[user]
          /admin.jsp = roles[admin]
          # everything else requires authentication:
          /** = authc <!--
          所有未认证的都被重定向到shiroFilter的loginUrl
          若指定某一个或多个被拦截的请求(不包括index.jsp)
          则默认跳转到index.jsp页面
          -->
  
      </value>
  </property>
  ```

  - Shiro标签实现授权

    - Shiro提供了JSTL标签用于在JSP页面进行权限控制，如根据登录用户

      显示相应的页面按钮

    - guest标签：用户没有身份验证时显示相应信息，即游客访问信息

    - user标签：用户已经认证/记住我登录后显示相应的信息

    - authenticated标签：用户已经身份验证，即通过Subject.login()登录

      成功，不是记住我登录的

    - notAuthenticated：用户没有进行身份验证，即没有通过Subject.login()

      进行登录，包括记住我自动登录的也属于未进行身份验证

    - principal标签：显示用户身份信息，默认调用Subject.login()获取,

      即Primary Principal

    - hasRole标签：如果当前Subject有角色将显示body体内容

    - hasAnyRole：如果当前Subject有任意一个角色(或)将显示body体内容

    - lacksRole：如果当前Subject没有角色将显示body体内容

    - hasPermission：如果当前Subject有权限将显示body体内容

    - lacksPermission：如果当前Subject没有权限将显示body体内容

    ```jsp
     Welcome:<shiro:principal></shiro:principal>
        <br>
        <shiro:hasRole name="user">
            <a href="user.jsp">User</a>
        </shiro:hasRole>
        <br>
        <shiro:hasRole name="admin">
            <a href="admin.jsp">Admin</a>
        </shiro:hasRole>
    ```

  - Shiro注解实现授权

    - @RequiresAuthentication：表示当前Subject已经通过login进行了身份验证，

      即Subject.isAuthenticated()返回true

    - @RequiresUser：表示当前Subject已经身份验证或者通过记住我登录的

    - @RequiresGuest：表示当前Subject没有身份验证或者通过记住我登录过，

      即时游客身份

    - @RequiresRoles(value={"admin","user"},logical=Logical.AND())：表示

      当前Subject需要角色admin和user

    - @RequiresPermissions(value={"user:a","user:b"},logical=Logical.OR())：

      表示当前Subject需要权限user:a或user:b

    ```java
    public class ShiroService {
    
        @RequiresRoles(value = {"admin"})
        public void testMethod(){
            System.out.println("testMethod Time: "+new Date());
        }
    }
    ```

    ```java
    @Controller
    @RequestMapping("/shiro")
    public class ShiroHandler {
    
        @Autowired
        private ShiroService shiroService;
    
        @RequestMapping("/testShiroService")
        public String testShiroService(){
            shiroService.testMethod();
            return "redirect:/success.jsp";
        }
    }
    ```

    
