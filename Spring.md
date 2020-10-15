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
