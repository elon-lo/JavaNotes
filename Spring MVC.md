## 1、Spring MVC是什么？

Spring MVC是一个为展现层提供的基于MVC设计理念的优秀的WEB框架，是目前最主流的MVC框架之一

## 2、Spring MVC的优点有哪些？

- Spring MVC通过一套MVC注解，让POJO成为处理请求的控制器，而无须实现任何接口
- 支持REST风格的URL请求
- 采用了松散耦合可插拔组件结构，比其他MVC框架更具扩展性和灵活性
- Spring3.0后全面超越Struts2，成为最优秀的MVC框架

## 3、Spring MVC快速入门

- web.xml中配置如下:

  ```xml
  <!-- 配置DispatcherServlet -->
    <servlet>
    	<servlet-name>DispatcherServlet</servlet-name>
    	<servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
    	<!-- 配置DispatcherServlet的一个初始化参数：配置Spring MVC配置文件的位置和名称 -->
    	<init-param>
          <!-- 
    			实际上也可以不通过contextConfigLocation来配置Spring MVC的配置文件，而使用默认的配置文件
    			默认的配置文件为：/WEB-INF/<servlet-name>-servlet.xml
    		 -->
    		<param-name>contextConfigLocation</param-name>
    		<param-value>classpath:springmvc.xml</param-value>
    	</init-param>
    	<load-on-startup>1</load-on-startup>
    </servlet>
    
    <servlet-mapping>
    	<servlet-name>DispatcherServlet</servlet-name>
    	<url-pattern>/</url-pattern>
    </servlet-mapping>
  ```

- springmvc.xml中配置如下：

  ```xml
  <!-- 配置自动扫描的包 -->
  	<context:component-scan base-package="com.yu.springmvc.handler"></context:component-scan>
  	
  	<!-- 配置视图解析器 -->
  	<bean class="org.springframework.web.servlet.view.InternalResourceViewResolver">
  		<!-- 配置前缀 -->
  		<property name="prefix" value="/WEB-INF/views/"></property>
  		<!-- 配置后缀 -->
  		<property name="suffix" value=".jsp"></property>
  	</bean>handler中配置如下
  ```

- handler中配置如下：

  ```java
  
  @Controller
  public class HelloWorld {
  	
  	/*
  	 * 1、 使用@RequestMapping注解来映射请求的URL
  	 * 2、返回值会通过视图解析器解析为实际的物理视图，对于InternalResourceViewResolver解析器，会做出如下解析：
  	 *	通过prefix + returnVal + suffix的方式得到实际的物理视图，然后做转发操作
  	 *
  	 *	/WEB-INF/views/success.jsp
  	 * */
  	@RequestMapping("/HelloWorld")
  	public String hello() {
  		return "success";
  	}
  }
  ```

- index.jsp中配置如下：

  ```jsp
  <body>
  	<a href="HelloWorld"> first springmvc --HelloWorld </a>
  </body>
  ```

- success.jsp中配置如下：

  ```jsp
  <body>
  	<h1>Success</h1>
  </body>
  ```

## 4、@RequestMapping注解

- Spring MVC使用@RequestMapping注解为控制器指定可以处理哪些URL请求

- 在控制器的类定义及方法定义处都可标记

- @RequestMapping:

  - 类定义处：提供初步的请求映射信息。相对与WEB应用的根目录

  - 方法定义处：提供进一步的细分映射信息。相对于类定义处的URL，若类定义处未标注@RequestMapping注解，

    则方法定义处标记的URL相对与WEB应用的根目录

- DispatcherServlet截获请求后，就通过控制器上@RequestMapping提供的映射信息确定请求所对应的处理方法

```java
@RequestMapping("/springmvc")
@Controller
public class SpringMVCTest {
	
	private static final String SUCCESS = "success";
	
	@RequestMapping("/testSpringMVCTest")
	public String testSpringMVCTest() {
		System.out.println("testSpringMVCTest");
		return SUCCESS;
	}

}
```

## 5、映射请求方法、请求参数或请求头

- @RequestMapping除了可以使用请求URL映射请求外，还可以使用请求方法、请求参数及请求头

  映射请求

- @RequestMapping的value、method、params及headers分别代表请求URL、请求方法、请求参数及请求头的

  映射条件，它们之间是与的关系，联合使用多个条件可以让请求映射更加精确化

- params和heads支持简单的表达式：

  - param1：表示请求必须包含名为param1的请求参数

  - !param1：表示请求不能包含名为param1的请求参数

  - param1  !=  value1：表示请求包含名为param1的请求参数，但其值不能为value1

  - {"param1=value1","param2"}：表示请求必须包含名为param1和param2的两个请求参数，

    且param1参数的值必须为value1

```java
@RequestMapping("/springmvc")
@Controller
public class SpringMVCTest {
	
	private static final String SUCCESS = "success";
	
	/*
	 * 
	 * 	常用：使用method属性来指定请求方式
	 * 
	 * */
	@RequestMapping(value = "/testMethod",method = RequestMethod.POST)
	public String testMethod() {
		System.out.println("testMethod");
		return SUCCESS;
	}
	
	@RequestMapping(value = "/testParamsAndHeaders",method = RequestMethod.GET,
			params = { "username", "age!=10" },headers = {"Accept-Language: zh-CN"})
	public String testParamsAndHeaders() {
		System.out.println("testParamsAndHeaders");
		return SUCCESS;
	}
}
```



## 6、使用@RequestMapping映射请求

- Ant风格资源地址支持3种匹配符：
  - ？：匹配文件名中的一个字符
  - *：匹配文件名中的任意字符
  - ** ：**匹配多层路径
- @RequestMapping还支持Ant风格的URL
  - /user/ * /createUser：匹配/user/aaa/createUser、/user/bbb/createUser等URL
  - /user/ ** /createUser：匹配/user/createUser、/user/aaa/bbb/createUser等URL
  - /user/ createUser??：匹配/user/createUseraa、/user/createUserbb等URL

```java
@RequestMapping("/springmvc")
@Controller
public class SpringMVCTest {
	
	private static final String SUCCESS = "success";
	
	
	@RequestMapping("/testAntPath/*/abc")
	public String testAntPath() {
		System.out.println("testAntPath");
		return SUCCESS;
	}
}
```



## 7、@PathVariable映射URL绑定的占位符

- 带占位符的URL是Spring3.0新增的功能，该功能在Spring MVC向REST挺进发展过程中

  具有里程碑的意义

- 通过@PathVariable可以将URL占位符参数绑定到控制器处理方法的入参中：URL中的

  {xxx}占位符可以通过@PathVariable{"xxx"}绑定到操作方法的入参中

```java
@RequestMapping("/springmvc")
@Controller
public class SpringMVCTest {
	
	private static final String SUCCESS = "success";
	
	@RequestMapping("/testPathVariable/{id}")
	public String testPathVariable(@PathVariable("id") Integer id) {
		System.out.println("testPathVariable :" + id);
		return SUCCESS;
	}
}
```

