## 1、Spring是什么？

​	Spring是一个轻量级、用于简化开发的容器框架

## 2、Spring的优点有哪些？

- 轻量级
- 面向接口编程、解耦合
- 支持面向切面编程
- 方便集成各种优秀框架

## 3、Spring有哪些模块？

我把Spring分为六个模块，如下所示：

1. Core Container
   - 包括spring-core，spring-beans，spring-context，spring-context-support， and spring-expression（SPEL）模块
2. AOP and Instrumentation
   - 包括spring-aop，spring-aspects，spring-instrument，spring-instrument-tomcat模块
3. Messaging
4. Data Access/Integration
   - 包括spring-jdbc，spring-tx，spring-orm，spring-oxm（XML），spring-jms模块
5. Web
   - 包括spring-web，spring-webmvc，spring-websocket，spring-webmvc-portlet模块
6. Test
   - 包括spring-test模块

## 4、什么是IOC（Inversion Of Control）和DI（Depency）？

#### <span style='color:#DC143C'>IOC：</span>

​		<span style='color:#DC143C'>中文叫控制反转，是一种设计思想，在Java中，IOC意味着将你设计好的对象（包括创建对象，给对象属性赋值，对象之间的关系管理）交给容器控制，而不是传统的在对象内部直接控制。</span>

<span style='color:Orange'>谁控制谁？控制什么？</span> 

- IOC容器控制了对象
- 主要控制了外部资源获取（不只是对象还有文件，图片等）

#### <span style='color:#DC143C'>   DI：</span>

​		<span style='color:#DC143C'>DI是IOC的技术实现方式中文叫依赖注入，由容器动态的将某个依赖关系注入到组件当中,使用的是反射机制</span>

## 5、BeanFactory和ApplicationContext有什么区别？

BeanFactory和ApplicationContext是Spring的两大核心接口，都可以当做Spring的容器。其中ApplicationContext是BeanFactory的子接口

区别如下：

- BeanFactory采用的是延迟加载形式来注入Bean，即只有在使用到某个Bean时，才会对该Bean进行加载实例化
- ApplicationContext是在容器启动时一次性创建了所有的Bean

## 6、Spring中的对象是在什么时候被创建的？创建的对象默认是有参还是无参？

- 在创建Spring容器时，会创建配置文件中的所有对象
- Spring创建对象，默认调用的是无参构造方法

## 7、依赖注入有几种方式？

- <span style='color:red'>setter注入：</span>Spring通过调用类的set方法，在set方法上实现属性的赋值
- <span style='color:red'>构造器注入：</span>Spring调用类的有参构造方法，创建对象，在构造方法中完成属性赋值

## 8、Autowired有几种使用规则?

主要解决了引用类型注入的繁琐，实现自动注入

- byName(按名称注入)：Java类中引用类型的属性名和Spring容器(配置文件)中bean的id一样，且数据类型是一致的

  使用方法：在bean中将autowire="byName"

- byType(按类型注入)：根据bean的类型和当前bean的属性的类型自动装配，若IOC容器中有1个以上类型匹配的，则抛异常

  使用方法：在bean中将autowire="byType"

## 9、Spring中bean的作用域有哪些?

使用bean的scope属性来配置bean的作用域，总共有四种：singleton，prototype，request，session

- singleton：默认值，容器初始化时创建bean实例，在容器的生命周期内只创建这一个bean，单例的
- prototype：原型的，容器初始化时不创建bean实例，而是在每次请求时都创建一个新的bean实例，并返回

## 10、IOC中bean的生命周期?

1. 创建bean实例
2. 为bean的属性赋值
3. 调用bean的初始化方法
4. 操作及使用bean
5. 当容器关闭时，调用bean的销毁方法

## 11、基于注解的方式配置bean

- 组件扫描：Spring能够从classpath下自动扫描、侦测和实例化具有特定注解的组件
- 特定组件如下：
  - @Component：基本注解，标识了一个受Spring管理的组件
  - @Repository：标识持久层组件
  - @Service：标识服务层(业务层)组件
  - @Controller：标识表现层组件
- 当在组件类上使用了特定的注解后，还需要在Spring的配置文件中声明context:component
  - base-package属性指定一个需要扫描的基类包，Spring容器将会扫描这个基类包及其子包里的所有类
  - 当需要扫描多个包时，可以用逗号分隔
  - 如果仅希望扫描特定的类而非基包下的所有类，可以使用resource-pattern属性过滤特定的类

## 12、什么是AOP?

- AOP(Aspects-Oriented Programming)，即面向切面编程，是对OOP(Oriented Object Programming)的补充。

- AOP的主要编程对象是切面(aspect)，而切面模块化横切关注点

  AOP的优点：

  - 每个事物逻辑位于一个位置，代码不分散，便于维护和升级
  - 业务模块更简洁，只包含核心业务代码

通俗理解如下：

​	面向切面编程（AOP是Aspect Oriented Program的首字母缩写） ，我们知道，面向对象的特点是继承、多态和封装。而封装就要求将功能分散到不同的对象中去，这在软件设计中往往称为职责分配。实际上也就是说，让不同的类设计不同的方法。这样代码就分散到一个个的类中去了。这样做的好处是降低了代码的复杂程度，使类可重用。
​      但是人们也发现，在分散代码的同时，也增加了代码的重复性。什么意思呢？比如说，我们在两个类中，可能都需要在每个方法中做日志。按面向对象的设计方法，我们就必须在两个类的方法中都加入日志的内容。也许他们是完全相同的，但就是因为面向对象的设计让类与类之间无法联系，而不能将这些重复的代码统一起来。
​    也许有人会说，那好办啊，我们可以将这段代码写在一个独立的类独立的方法里，然后再在这两个类中调用。但是，这样一来，这两个类跟我们上面提到的独立的类就有耦合了，它的改变会影响这两个类。那么，有没有什么办法，能让我们在需要的时候，随意地加入代码呢？**这种在运行时，动态地将代码切入到类的指定方法、指定位置上的编程思想就是面向切面的编程。**

13、什么是AspectJ

<span style='color:red'>AspectJ：Java社区里最完整最流行的AOP框架</span>

- 在AspectJ注解中，切面只是一个带有@Aspect注解的Java类
- 通知是标注有某种注解的简单Java方法
- AspectJ支持5种类型的通知注解
  - @Before：前置通知，在方法执行之前执行
  - @After：后置通知，在方法执行之后执行
  - @AfterReturning：返回通知，在方法返回结果之后执行
  - @AfterThrowing：异常通知，在方法抛出异常之后执行
  - @Around：环绕通知，围绕着方法执行

```java
package com.yu.spring.aop.impl;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

/**
 * @ClassName 日志切面
 * @Description TODO
 * @Author yu
 * @Date 2020/10/16 13:05
 * 把这个类声明为一个切面：需要把该类放到Spring IOC容器中，再声明一个切面
 */
@Aspect
@Component
public class LoggingAspect {

    //声明该方法是一个前置通知：在目标方法开始之前执行
    @Before("execution(* com.yu.spring.aop.impl.*.*(int, int))")
    public void loggingBeFor(JoinPoint joinPoint){
        //获取该方法的方法名
        String methodName = joinPoint.getSignature().getName();
        //获取形参
        List<Object> args = Arrays.asList(joinPoint.getArgs());
        System.out.println("The method " + methodName + " begins with" + args);
    }

    //声明该方法是一个后置通知(无论是否发生异常)：在目标方法开始之后执行
    @After("execution(* com.yu.spring.aop.impl.*.*(int, int))")
    public void loggingAfter(JoinPoint joinPoint){
        //获取该方法的方法名
        String methodName = joinPoint.getSignature().getName();
        //后置通知不能获取到方法的返回值
        System.out.println("The method " + methodName + " ends " );
    }
    /*
    * 在方法正常结束后执行的代码
    * @param AfterReturning:返回通知可以访问到方法的返回值
    * */
    @AfterReturning(value = "execution(* com.yu.spring.aop.impl.*.*(int, int))",returning = "res")
    public void loggingAfterReturning(JoinPoint joinPoint,Object res){
        //获取该方法的方法名
        String methodName = joinPoint.getSignature().getName();
        //访问到方法的返回值
        System.out.println("The method " + methodName + " ends " + res);
    }

    /*
    * 在目标方法出现异常时执行 的代码
    * 可以访问到异常对象，可以指定在出现特定异常时再执行通知代码
    * */
    @AfterThrowing(value = "execution(* com.yu.spring.aop.impl.*.*(int, int))",throwing = "ex")
    public void loggingThrow(JoinPoint joinPoint,Exception ex){
        //获取该方法的方法名
        String methodName = joinPoint.getSignature().getName();
        //访问到方法的返回值
        System.out.println("The method " + methodName + " occurs exception " + ex);
    }
    
    
     //使用@Pointcut声明切入点表达式
    @Pointcut("execution(* com.yu.spring.aop.impl.*.*(int, int))")
    public void declareJoinPointExpression(){}
    
    
     /** 环绕通知需要携带ProceedingJoinPoint 类型的参数
    * 环绕通知类似于动态代理的全过程：ProceedingJoinPoint 类型的参数可以决定是否执行目标方法
    * 且环绕通知必须有返回值，返回值的类型即目标方法的返回值
    *
    * */
    @Around("declareJoinPointExpression()")
    public Object loggingAround(ProceedingJoinPoint pjd){
        String methodName = pjd.getSignature().getName();
        //前置通知
        System.out.println("The method " + methodName + " begins with " + Arrays.asList(pjd.getArgs()));
        Object result = null;
        try {
            //返回通知
            result = pjd.proceed();
            System.out.println("The method " + methodName + " ends with " + result);
        } catch (Throwable e) {
            System.out.println("The method " + methodName + " exception: " + e);
        }
        //后置通知
        System.out.println("The method " + methodName + " ends ");
        //异常通知

        return result;
    }
}
```

- 可以使用@Order指定切面的优先级，值越小优先级越高

  **<span style='color:red'>基于XML配置如下：</span>**

  ```	xml
  <!-- 配置AOP -->
  <aop:config>
          <!-- 配置切入点表达式 -->
          <aop:pointcut id="pointcut" expression="execution(* com.yu.spring.aop.impl.Calculator.*(..))"></aop:pointcut>
          <aop:aspect ref="loggingAspect" order="2">
              <!-- 配置切面及通知 -->
              <aop:before method="loggingBeFor" pointcut-ref="pointcut"/>
              <aop:after method="loggingAfter" pointcut-ref="pointcut"/>
              <aop:after-returning method="loggingAfterReturning" pointcut-ref="pointcut" returning="res"/>
              <aop:after-throwing method="loggingThrow" pointcut-ref="pointcut" throwing="ex"/>
              <!--<aop:around method="loggingAround" pointcut-ref="pointcut"/>-->
          </aop:aspect>
          
          <aop:aspect ref="validationAspect" order="1">
              <aop:before method="beFore" pointcut-ref="pointcut"/>
          </aop:aspect>
      </aop:config>
  ```

  ## 13、在Spring中整合JDBC

  ```properties
  jdbc.user = root
  jdbc.password = 123456
  jdbc.driverClass = com.mysql.jdbc.Driver
  jdbc.jdbcUrl = jdbc:mysql://192.168.252.130:3306/springjdbc?charset=utf8mb4
  
  
  jdbc.initialPoolSize = 5
  jdbc.maxPoolSize = 10
  ```

  ```xml
  <!-- 导入资源文件 -->
      <context:property-placeholder location="classpath:db.properties"/>
      <!-- 配置c3p0数据源 -->
      <bean id="DataSource" class="com.mchange.v2.c3p0.ComboPooledDataSource">
          <property name="user" value="${jdbc.user}"/>
          <property name="password" value="${jdbc.password}"/>
          <property name="driverClass" value="${jdbc.driverClass}"/>
          <property name="jdbcUrl" value="${jdbc.jdbcUrl}"/>
  
          <property name="initialPoolSize" value="${jdbc.initialPoolSize}"/>
          <property name="maxPoolSize" value="${jdbc.maxPoolSize}"/>
      </bean>
  
      <!-- 配置Spring的JdbcTemplate -->
      <bean id="jdbcTemplate" class="org.springframework.jdbc.core.JdbcTemplate">
          <property name="dataSource" ref="DataSource"/>
      </bean>
  ```

  

  ## 14、Spring中的事务管理

  事务的四个关键属性(ACID)

  - 原子性(atomicity)：事务是一个原子操作，由一系列动作完成，事务的原子性确保动作要么全部完成要么完全不起作用
  - 一致性(consistency)：一旦所有事务动作全部完成，事务就被提交，数据和资源就处于一种满足业务规则的一致性状态
  - 隔离性：可能有许多事务会同时处理相同的数据，因此每个事务都应该与其他的事务隔离开来，防止数据损坏
  - 持久性：一旦事务完成，无论发生什么系统错误，它的结果都不应该受到影响，通常情况下，事务的结果被写入到持久化存储器中
