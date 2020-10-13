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

​		<span style='color:#DC143C'>DI是IOC的技术实现方式中文叫依赖注入，由容器动态的将某个依赖关系注入到组件当中</span>

## 5、BeanFactory和ApplicationContext有什么区别？

BeanFactory和ApplicationContext是Spring的两大核心接口，都可以当做Spring的容器。其中ApplicationContext是BeanFactory的子接口

区别如下：

- BeanFactory采用的是延迟加载形式来注入Bean，即只有在使用到某个Bean时，才会对该Bean进行加载实例化
- ApplicationContext是在容器启动时一次性创建了所有的Bean
