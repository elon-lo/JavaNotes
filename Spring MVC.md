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
	
    /*
	 * 	
	 *	 使用headers指定请求头参数
	 *	headers = {"Content-Type=application/json"}
	 * 
	 * */
	@RequestMapping(value = "/testParamsAndHeaders",method = RequestMethod.GET,
			params = { "username", "age!=10" },headers = {"Content-Type=application/json"})
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

## 8、REST风格

REST：即Representational State Transfer。(资源)表现层状态转化。是目前最流行的一种互联网软件

​		     架构，它结构清晰、符合标准、易于理解、扩展方便，所以正得到越来越多网站的应用。

如何理解REST风格：

- HTTP协议里面，四个表示操作的动词：GET、POST、PUT、DELETE。它们分别对应四种基本

  操作：GET用来获取资源，POST用来新建资源，PUT用来更新资源，DELETE用来删除资源。

  示例如下：

  - /order/1	HTTP GET：得到id = 1的order
  - /order/1	HTTP PUT：更新id = 1的order
  - /order/1	HTTP DELETE：删除id = 1的order
  - /order	HTTP POST：新增order

- HiddenHttpMethodFilter：浏览器form表单只支持GET和POST请求，而DELETE和PUT等

  method并不支持，Spring3.0添加了一个过滤器，可以将这些请求转换为标准的http方法，

  使得支持GET、POST、PUT和DELETE请求。



web.xml配置如下：

```xml
<!-- 配置org.springframework.web.filter.HiddenHttpMethodFilter：可以将POST请求转换为PUT或DELETE请求 -->
  <filter>
  	<filter-name>HiddenHttpMethodFilter</filter-name>
  	<filter-class>org.springframework.web.filter.HiddenHttpMethodFilter</filter-class>
  </filter>
  
  <filter-mapping>
  	<filter-name>HiddenHttpMethodFilter</filter-name>
  	<url-pattern>/*</url-pattern>
  </filter-mapping>
```

index.jsp配置如下：

```xml
<form action="springmvc/testRest/1" method="post">
		<input type="hidden" name="_method" value="DELETE"/>
		<input type="submit" value="testRest DELETE"/>
	</form>

	<form action="springmvc/testRest/1" method="post">
		<input type="hidden" name="_method" value="PUT"/>
		<input type="submit" value="testRest PUT"/>
	</form>

	<form action="springmvc/testRest" method="post">
		<input type="submit" value="testRest POST"/>
	</form>
	
	<a href="springmvc/testRest/1"> testRest GET </a><br>
```

controller中配置如下：

```java
@RequestMapping("/springmvc")
@Controller
public class SpringMVCTest {
	
	private static final String SUCCESS = "success";
	
	@RequestMapping(value = "/testRest/{id}",method = RequestMethod.DELETE)
	public String testRestDELETE(@PathVariable Integer id) {
		System.out.println("testRest DELETE:"+id);
		return SUCCESS;
	}
	
	@RequestMapping(value = "/testRest/{id}",method = RequestMethod.PUT)
	public String testRestPUT(@PathVariable Integer id) {
		System.out.println("testRest PUT:"+id);
		return SUCCESS;
	}
	
	@RequestMapping(value = "/testRest",method = RequestMethod.POST)
	public String testRestPOST() {
		System.out.println("testRest POST");
		return SUCCESS;
	}
	
	@RequestMapping(value = "/testRest/{id}",method = RequestMethod.GET)
	public String testRest(@PathVariable Integer id) {
		System.out.println("testRest GET:"+id);
		return SUCCESS;
	}
}
```

<span style='color:red'>注意：如果Tomcat版本高于7.0，则PUT，DELETE请求地址不能转发</span>

<span style='color:red'>至JSP页面，可在其对应方法上添加@ResponseBody注解，将其转为字符串</span>





## @RequestParam的用法

```java
@RequestMapping("/springmvc")
@Controller
public class SpringMVCTest {
	
	private static final String SUCCESS = "success";
	
	/*
	 * @RequestParam来映射请求参数
	 * value值即请求参数的参数名
	 * required 请求是否必须  默认为true
	 * defaultValue 请求参数的默认值
	 * */
	@RequestMapping("/testRequestParam")
	public String testRequestParam(@RequestParam("username") String un,
			@RequestParam(value = "age",required = false,defaultValue = "5") Integer age) {
		
		System.out.println("testRequestParam  username: " + un + ", age: " + age);
		return SUCCESS;
	}
}
```

```jsp
<a href="springmvc/testRequestParam?username=zhangsan&age=10"> testRequestParam </a><br>
```





## @RequestHeader用法

```java
@RequestMapping("/springmvc")
@Controller
public class SpringMVCTest {
	
	private static final String SUCCESS = "success";
	
	/*
	 * 	映射请求头信息
	 * 	用法同@RequestParam
	 * 
	 * */
	@RequestMapping("/testRequestHeader")
	public String testRequestHeader(@RequestHeader("Accept-Language") String al) {
		System.out.println("Accept-Language: " + al);
		return SUCCESS;
	}
}
```

```jsp
<a href="springmvc/testRequestHeader"> testRequestHeader </a><br>
```





## @CookieValue用法

```java
@RequestMapping("/springmvc")
@Controller
public class SpringMVCTest {
	
	private static final String SUCCESS = "success";
	
	/*
	 * @CookieValue 映射一个cookie值  属性同@RequestParam
	 * 
	 * */
	
	 @RequestMapping("/testCookieValue") 
	 public String testCookieValue(@CookieValue(value = "JSESSIONID") String sessionId) {
		 System.out.println("testCookieValue : sessionId : " + sessionId); 
		 return SUCCESS; 
	 }
}
```

```jsp
<a href="springmvc/testCookieValue"> testCookieValue </a><br>
```



## 9、使用POJO对象对象绑定请求参数值

<span style='color:red'>Spring MVC会按请求参数名和POJO属性名进行自动匹配，自动为该对象填充属性值，且支持级联属性</span>

```java
@RequestMapping("/springmvc")
@Controller
public class SpringMVCTest {
	
	private static final String SUCCESS = "success";
	
	@RequestMapping("/testPojo")
	public String testPojo(User user) {
		System.out.println("User: " + user);
		return SUCCESS;
	}
}
```

```jsp
	<form action="springmvc/testPojo" method="post">
		username: <input type="text" name="username"/>
		<br>
		password: <input type="password" name="password"/>
		<br>
		age: <input type="text" name="age"/>
		<br>
		email: <input type="text" name="email"/>
		<br>
		province: <input type="text" name="Address.province"/>
		<br>
		city: <input type="text" name="Address.city"/>
		<br>
		<input type="submit" value="submit"/>
	</form>
```

## 10、MVC的Handler方法支持哪些ServletAPI类型的参数

- HttpServletRequest
- HttpServletResponse
- HttpSession
- java.security.Principal
- Locale
- InputStream
- OutputStream
- Reader
- Writer

```java
@RequestMapping("/springmvc")
@Controller
public class SpringMVCTest {
	
	private static final String SUCCESS = "success";
	
	@RequestMapping("/testServletAPI")
	public void testServletAPI(HttpServletRequest request,
			HttpServletResponse response,Writer out) throws IOException {
		System.out.println(request + "," + response);
		out.write("Hello Spring MVC");
	}
}
```





### ModelAndView

- 目标方法的返回值可以是ModelAndView类型

- 其中可以包含视图和模型信息

- Spring MVC会把ModelAndView的Model中的数据放入到request域中

  ```java
  @RequestMapping("/springmvc")
  @Controller
  public class SpringMVCTest {
  
  	private static final String SUCCESS = "success";
  	
  	@RequestMapping("/testModelAndView")
  	public ModelAndView testModelAndView() {
  		String viewName = SUCCESS;
  		ModelAndView mv = new ModelAndView(viewName);
  		mv.addObject("time", new Date());
  		return mv;
  	}
  }
  ```

### @SessionAttributes

- 若希望在多个请求之间共用某个模型属性数据，则可以在控制器类上标注一个

  @SessionAttributes，Spring MVC将在模型中对应的属性暂存到HttpSession中

- @SessionAttributes除了可以属性名指定需要放到会话中的属性外，还可以通过

  模型属性的对象类型指定哪些模型属性需要放到会话中

```java
@SessionAttributes(value = {"user"},types = {String.class})
@RequestMapping("/springmvc")
@Controller
public class SpringMVCTest {

	private static final String SUCCESS = "success";
	
	
	@RequestMapping("/testSessionAttributes")
	public String testSessionAttributes(Map<String, Object> map) {
		User user = new User("Jack","1433223","Jack@yu.com",18);
		map.put("user", user);
		map.put("school", "school");
		return SUCCESS;
	}
}
```

```jsp
	request user: ${requestScope.user }<br>
	session user: ${requestScope.user }<br>
	
	request school: ${requestScope.school }<br>
	session school: ${requestScope.school }<br>
```



### @ModelAttribute

```java
@RequestMapping("/springmvc")
@Controller
public class SpringMVCTest {

	private static final String SUCCESS = "success";
	
	/*
	 * 有@ModelAttribute标注的方法，会在每个目标方法执行之前被Spring MVC调用
	 * 
	 * */
	@ModelAttribute
	public void getUser(@RequestParam(value = "id",required = false) Integer id,
			Map<String,Object> map) {
		//模拟从数据库中获取对象
		if (id != null) {
			User user = new User(5, "Tom", "1433223", "Tom@yu.com", 18);
			System.out.println("从数据库中获取一个对象: " + user);
			
			map.put("user", user);
		}
	}
	
	/*
	 *	运行流程
	 *	1、执行被@ModelAttribute修饰的方法，从数据库中取出对象，将对象放入到了Map中，键：user
	 * 	2、Spring MVC从Map中取出User对象，并把表单的请求参数赋给该user对象对应的属性
	 * 	3、Spring MVC把上述对象传入目标方法的参数
	 * 
	 * */
	@RequestMapping("/testModelAttribute")
	public String testModelAttribute(User user) {
		System.out.println("修改: "+user);
		return SUCCESS;
	}
}
```

### 视图

- 视图的作用是渲染模型数据，将模型里的数据以某种形式呈现给客户
- 为了实现视图模型和具体实现技术的解耦，Spring在org.springframework.web.servlet包中定义了一个高度抽象的View接口
- 视图对象由视图解析器负责实例化。由于视图是无状态的，所以他们不会有线程安全的问题



### 视图解析器

- Spring MVC为逻辑视图名的解析提供了不同的策略，可以在Spring WEB上下文中配置一种或多种解析策略，

  并指定他们之间的先后顺序。每一种映射策略对应一个具体的视图解析器实现类

- 视图解析器的作用比较单一：将逻辑视图解析为一个具体的视图对象

- 所有的视图解析器都必须实现ViewResolver接口

- 常用的几种视图解析器：

  - InternalResourceViewResolver

    - JSP最常用的视图技术，可以使用InternalResourceViewResolver作为视图解析器

    - 若项目中使用了JSTL，则Spring MVC会自动把视图由InternalResourceViewResolver转为JstlView

    - 若使用了JSTL的fmt标签则需要在Spring MVC的配置文件中配置国际化资源文件

    - 若希望直接响应通过Spring MVC渲染的页面，可以使用mvc:view-controller标签实现，

      一般在配置mvc:view-controller时会同时配置mvc:annotation-driven标签

  - 自定义一个视图

    ```java
    @Component
    public class HelloView implements View{
    
    	public String getContentType() {
    		// TODO Auto-generated method stub
    		return "text/html";
    	}
    
    	public void render(Map<String, ?> model, HttpServletRequest request, HttpServletResponse response)
    			throws Exception {
    		response.getWriter().print("HelloView Time: " + new Date());
    		
    	}
    
    }
    ```

    ```xml
    <!-- 配置视图BeanNameViewResolver解析器：使用视图的名字来解析视图 -->
    	<bean class="org.springframework.web.servlet.view.BeanNameViewResolver">
    		<property name="order" value="100"></property>
    	</bean>
    ```

    ```java
    @RequestMapping("/springmvc")
    @Controller
    public class SpringMVCTest {
    
    	private static final String SUCCESS = "success";
    	
    	@RequestMapping("/testBeanNameViewResolver")
    	public String testBeanNameViewResolver() {
    		System.out.println("Test BeanNameViewResolver");
    		return "HelloView";
    	}
    }
    ```

  - 关于重定向的问题

    - 一般情况下，控制器方法返回字符串类型的值会被当做逻辑视图名处理

    - 如果返回的字符串中带forward:或redirect:前缀时，Spring MVC会对他们进行特殊处理：

      将forward:和redirect:当成指示符，其后的字符串作为URL来处理

## 11、自定义类型转换器

- ConversionService是Spring类型转换体系的核心接口。

- 可以利用ConversionServiceFactoryBean在Spring的IOC容器中定义一个ConversionService，

  Spring将自动识别出IOC容器中的ConversionService，并在Bean属性配置及Spring MVC处理

  方法入参绑定等场合使用它进行数据的转换

- 可通过ConversionServiceFactoryBean的converters属性注册自定义的类型转换器

- Spring支持的转换器如下：

  - Spring定义了3种类型的转换器接口，实现任意一种转换器接口都可以作为自定义转换器

    注册到ConversionServiceFactoryBean中：

    - Converters<S，T>：将S类型对象转换为T类型对象

    - ConverterFactory：将相同系列多个“同质”Converter封装在一起。如果希望将一种

      类型的对象转换为另一种类型及其子类的对象（例如将String转换为Number及Number子类

      （Integer、Double、Long）等对象）可使用该转换器工厂类

    - GenericConverter：会根据源类对象和目标类对象所在的宿主类中的上下文信息进行类型转换

## 12、JSR303数据校验

- JSR 303是Java为Bean数据合法性校验提供的标准化框架，它已经包含在JavaEE6.0中

- JSR 303通过在Bean属性上标注类似于@NotNull、@Max等标准的注解指定校验规则，并通过标准的验证接口对Bean进行验证

- 常用的注解如下

  ```wiki
  注解								功能说明
  @Null							 被注释的元素必须为null
  @NotNull						 被注释的元素必须不为null
  @AssertTrue						 被注释的元素必须为true
  @AssertFalse					 被注释的元素必须为false
  @Min(value)						 被注释的元素必须是一个数字,其值必须大于等于指定的最小值
  @Max(value)						 被注释的元素必须是一个数字,其值必须大于等于指定的最大值
  @DecimalMin(value)				 被注释的元素必须是一个数字,其值必须大于等于指定的最小值
  @DecimalMax(value)				 被注释的元素必须是一个数字,其值必须大于等于指定的最大值
  @Size(max,min)					 被注释的元素大小必须在指定的范围内
  @Digits(integer,fraction)		 被注释的元素必须是一个数字,其值必须在可接受的范围内
  @Past							 被注释的元素必须是一个过去的日期
  @Future							 被注释的元素必须是一个将来的日期
  @Pattern(value)					 被注释的元素符合指定的正则表达式
  ```

- Hibernate Validator是JSR 303的一个参考实现，除支持所有标准的校验注解外，它还支持以下的扩展注解

  ```wiki
  注解				  功能说明
  @Email				被注释的元素必须是电子邮箱地址
  @Length				被注释的字符串的大小必须在指定的范围内
  @NotEmpty			被注释的字符串必须非空
  @Range				被注释的元素必须在合适的范围内
  ```

  

## 13、统一处理乱码

- 处理对象乱码

  ```xml
  <!--配置过滤器解决乱码-->
      <filter>
          <filter-name>Encoding</filter-name>
          <filter-class>org.springframework.web.filter.CharacterEncodingFilter</filter-class>
          <init-param>
              <param-name>encoding</param-name>
              <param-value>utf-8</param-value>
          </init-param>
      </filter>
  
      <filter-mapping>
          <filter-name>Encoding</filter-name>
          <url-pattern>/*</url-pattern>
      </filter-mapping>
  ```

- 处理Json乱码

  ```xml
  <!-- 配置处理json乱码 -->
  <mvc:annotation-driven>
     <mvc:message-converters register-defaults="true">
         <bean class="org.springframework.http.converter.StringHttpMessageConverter">
             <constructor-arg value="UTF-8"/>
         </bean>
         <bean class="org.springframework.http.converter.json.MappingJackson2HttpMessageConverter">
             <property name="objectMapper">
                 <bean class="org.springframework.http.converter.json.Jackson2ObjectMapperFactoryBean">
                     <property name="failOnEmptyBeans" value="false"/>
                 </bean>
             </property>
         </bean>
     </mvc:message-converters>
  </mvc:annotation-driven>
  ```

  

## 14、Spring MVC整合Jackson

```java
<dependency>
    <groupId>com.fasterxml.jackson.core</groupId>
    <artifactId>jackson-databind</artifactId>
    <version>2.10.2</version>
</dependency>
```

```java
 @RequestMapping("/json")
    @ResponseBody
    public String testJson(){
        User user = new User("张三",18,"男");

        //使用ObjectMapper将对象转为json字符串
        ObjectMapper mapper = new ObjectMapper();

        String str = null;
        try {
            str = mapper.writeValueAsString(user);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return str;
    }
```



## 15、国际化及其配置

国际化概述如下：

- 在页面上能通过浏览器语言设置情况对文本(不是内容)，时间，数值进行本地化处理
- 可以在bean中获取国际化资源文件Local对应的消息
- 可以通过超链接切换Local，而不再依赖浏览器的语言设置情况

如何实现国际化？

- 使用JSTL的fmt标签
- 在bean中注入ResourceBundleMessageSource的实例，使用其对应的getMessage方法
- 配置LocalResolver和LocalChangeInterceptor

```xml
<!-- springmvc.xml -->
<!--配置国际化资源文件-->
    <bean id="messageSource" class="org.springframework.context.support.ResourceBundleMessageSource">
        <property name="basename" value="i18n"/>
    </bean>

    <mvc:view-controller path="/i18n" view-name="i18n"/>
    <mvc:view-controller path="/i18n2" view-name="i18n2"/>
```

```properties
#i18n.properties
i18n.user=User
i18n.password=Password
```

```properties
#i18n_en_US.properties
i18n.user=User
i18n.password=Password
```

```properties
#i18n_zh_CN.properties
i18n.user=用户名
i18n.password=密码
```

```jsp
<!-- i18n.jsp -->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <fmt:message key="i18n.user"/>
    <br><br>
    <a href="i18n2">I18N2 PAGE</a>
</body>
</html>
```

```jsp
<!-- i18n2.jsp -->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <fmt:message key="i18n.password"/>
    <br><br>
    <a href="i18n">I18N PAGE</a>
</body>
</html>
```

```java
	@Autowired
	private ResourceBundleMessageSource messageSource;
	
	@RequestMapping("/i18n")
    public String testI18n(Locale locale){
        String userVal = messageSource.getMessage("i18n.user",null,locale);
        System.out.println(userVal);
        return "i18n";
    }
```



## 16、文件上传

- Spring MVC为文件上传提供了直接的支持，这种支持是通过即插即用的MultipartResolver

  实现的。Spring用Jakarta Commons FileUpload技术实现了一个MultipartResolver的实现类：

  CommonsMultipartResolver

- Spring MVC上下文中默认没有装配MultipartResolver，因此默认情况下不能处理文件的上传

  工作，如果想使用文件上传功能，需要先在上下文中配置MultipartResolver

- defaultEncoding：必须和用户JSP文件的pageEncoding属性一致，以便正确解析表单的内容

- 为了让CommonsMultipartResolver正常工作，必须先将Jakarta Commons FileUpload和

  Jakarta Commons io的类包加到类路径下

```xml
<!-- springmvc.xml -->
<!--配置MultipartResolver-->
    <bean id="multipartResolver" 	 			class="org.springframework.web.multipart.commons.CommonsMultipartResolver">
        <property name="defaultEncoding" value="UTF-8"/>
        <property name="maxInMemorySize" value="1024000"/>
    </bean>
```

```java
 	@RequestMapping("/testMultipartResolver")
    public String fileUpload(@RequestParam("desc") String desc,
                             @RequestParam("file")MultipartFile file) throws IOException {

        System.out.println("DESC :" + desc);
        System.out.println("OriginalFilename :" + file.getOriginalFilename());
        System.out.println("InputStream :" + file.getInputStream());
        return "success";
    }
```

```jsp
	<form action="testMultipartResolver" method="post" enctype="multipart/form-data">
        File:<input type="file" name="file"/>
        Desc:<input type="text" name="desc"/>
        <input type="submit" value="Submit"/>
    </form>
```



## 17、拦截器

- 自定义拦截器

  - Spring MVC可以使用拦截器对请求进行拦截处理，用户可以自定义拦截器实现特定的功能，

    自定义的拦截器必须实现HandlerInterceptor接口

    - preHandle()：这个方法在业务处理请求之前被调用(权限、日志、事务等)，在该方法中对用户请求request

      进行处理。如果程序员决定该拦截器对请求进行拦截处理后还要调用其他的拦截器，

      或者是业务处理器去进行处理，则返回true；如果程序员决定不需要再调用其他的组件

      去处理请求，则返回false

    - postHandle()：这个方法在调用目标方法之后，渲染视图之前被调用(可以对请求域中的属性或视图

      做出修改)，在该方法中对用户请求request进行处理

    - afterCompletion()：这个方法在DispatcherServlet完全处理完请求后被调用，可以在

      该方法中进行一些资源清理的操作(释放资源)

    ```xml
    <!-- springmvc.xml -->
    <!--配置自定义拦截器-->
        <mvc:interceptors>
            <bean id="firstInterceptor" class="com.yu.springmvc.interceptors.FirstInterceptor"></bean>
            <mvc:interceptor>
                <!--配置拦截器(不)作用的路径-->
                <mvc:mapping path="/json"/>
                    <bean class="com.yu.springmvc.interceptors.SecondInterceptor"></bean>
            </mvc:interceptor>
        </mvc:interceptors>
    ```

    ```java
    public class SecondInterceptor implements HandlerInterceptor {
        @Override
        public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
            System.out.println("[SecondInterceptor]  preHandle...");
            return true;
        }
    
        @Override
        public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
            System.out.println("[SecondInterceptor]  postHandle...");
        }
    
        @Override
        public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
            System.out.println("[SecondInterceptor]  afterCompletion...");
        }
    }
    ```

    ![‪](C:\Users\yu\Desktop\Interceptor.png)

## 18、异常处理

- Spring MVC通过HandlerExceptionResolver处理程序的异常，包括Handler映射、数据绑定以及目标

  方法执行时发生的异常

- Spring MVC提供了HandlerExceptionResolver的实现类

- DispatcherServlet默认装配的HandlerExceptionResolver如下：

  - 没有使用<mvc:annotation-driven/ >配置：

    - AnnotationMethodHandlerExceptionResolver

    - ResponseStatusExceptionResolver

    - DefaultHandlerExceptionResolver

      

  - 使用了<mvc:annotation-driven/ >配置：

    - ExceptionHandlerExceptionResolver
    - ResponseStatusExceptionResolver
    - DefaultHandlerExceptionResolver



- **ExceptionHandlerExceptionResolver**

  - 主要处理Handler中用@ExceptionHandler注解定义的方法

  - @ExceptionHandler注解定义方法优先级问题：例如发生的是NullPointerException,

    但是声明的异常有RuntimeException和Exception，此时会根据异常的最近继承关系

    找到继承深度最浅的那个@ExceptionHandler注解方法，即标记了RuntimeException

    的方法

  - ExceptionHandlerMethodResolver内部若找不到@ExceptionHandler注解的话，会找

    @ControllerAdvice中的@ExceptionHandler注解方法

    ```java
     	/*
        *  @ExceptionHandler  方法的入参中可以加入Exception类型的参数,该参数即对应发生的异常对象
        *  @ExceptionHandler  方法的入参中不能传入Map,若希望把异常信息传到页面上,需要使用ModelAndView作为返回值
        *  @ExceptionHandler  方法标记的异常有优先级的问题
        *  @ControllerAdvice  如果在当前Handle找不到@ExceptionHandler的方法来处理当前方法发生的异常,
        *  则将去@ControllerAdvice标记的类查找@ExceptionHandler标记的方法来处理异常
         * */
        @ExceptionHandler({ArithmeticException.class})
        public ModelAndView handleArithmeticException(Exception ex) {
            System.out.println("出异常了: "+ex);
            ModelAndView mv = new ModelAndView("error");
            mv.addObject("exception",ex);
            return mv;
        }
        
        @RequestMapping("/testExceptionHandlerExceptionResolver")
        public String testExceptionHandlerExceptionResolver(@RequestParam("i") int i){
            System.out.println("Result :" + (10 / i));
            return "success";
        }
    ```

    ```java
    @ControllerAdvice
    public class TestHandleException {
        @ExceptionHandler({ArithmeticException.class})
        public ModelAndView handleArithmeticException(Exception ex) {
            System.out.println("出异常了: "+ex);
            ModelAndView mv = new ModelAndView("error");
            mv.addObject("exception",ex);
            return mv;
        }
    }
    ```

    ```jsp
    <a href="/testExceptionHandlerExceptionResolver?i=10">Test testExceptionHandlerExceptionResolver</a>
    ```

- **ResponseStatusExceptionResolver**

  - 在异常及异常父类中找到@ResponseStatus注解，然后使用这个注解的属性进行处理

  - 定义一个@ResponseStatus注解修饰的异常类

  - 若在处理器方法中抛出了上述异常：若ExceptionHandlerExceptionResolver不解析

    上述异常。由于触发的异常UnauthorizedException带有@ResponseStatus注解。因

    此会被ResponseStatusExceptionResolver解析到。最后响应HttpStatus.UNAUTHORIZED

    代码给客户端。HttpStatus.UNAUTHORIZED代表响应码401，无权限。关于其他的响应码

    请参考HttpStatus枚举类型源码

  ```java
  @ResponseStatus(value = HttpStatus.FORBIDDEN,reason = "用户名和密码不匹配")
  public class UserNameNotMatchPasswordException extends RuntimeException{
  
  }
  ```

  ```java
   @ResponseStatus(value = HttpStatus.NOT_FOUND,reason = "测试")
      @RequestMapping("/testResponseStatusExceptionResolver")
      public String testResponseStatusExceptionResolver(@RequestParam("i") int i) {
              if (i == 13){
                  throw new UserNameNotMatchPasswordException();
              }
                  System.out.println("testResponseStatusExceptionResolver...");
          return "sucess";
      }
  ```

- **DefaultHandlerExceptionResolver**

  - 对一些特殊的异常进行处理，比如：

    - NoSuchRequestHandlingMethodException、HttpRequestMethodNotSupportedException、

      HttpMediaTypeNotSupportedException、HttpMediaTypeNotAcceptableException等

    ```java
    @RequestMapping(value = "/testDefaultHandlerExceptionResolver",method = RequestMethod.POST)
        public String testDefaultHandlerExceptionResolver(){
            return "success";
        }
    ```

- <span style='color:red'>**SimpleMappingExceptionResolver**</span>

  - <span style='color:red'>如果希望对所有异常进行统一处理，可以使用SimpleMappingExceptionResolver，它将异常类名映射成视图名，即发生异常时使用对应的视图报告异常</span>

  ```xml
  <!--配置统一处理异常-->
      <bean class="org.springframework.web.servlet.handler.SimpleMappingExceptionResolver">
          <!--自定义异常属性-->
          <property name="exceptionAttribute" value="ex"/>
          <property name="exceptionMappings">
              <props>
                  <prop key="java.lang.ArrayIndexOutOfBoundsException">error</prop>
              </props>
          </property>
      </bean>
  ```

  ```java
  	@RequestMapping("/testSimpleMappingExceptionResolver")
      public String testSimpleMappingExceptionResolver(@RequestParam("i") int i){
          String[] vals = new String[10];
          System.out.println("vals: " + vals[i]);
          return "success";
      }
  ```



## <span style='color:red'>19、Spring MVC运行流程</span>

- SpringMVC 三大组件：HandlerMapping 处理器映射器、HandlerAdapter 处理器适配器以及 ViewReslover 视图解析器

- Spring MVC运行流程如下：

  ![](https://pic3.zhimg.com/80/v2-7d4fc793caae495b87eba4e7dc42e386_720w.jpg)

- 这张图中我用了两种颜色的块状区域来表示，绿色表示 MVC 部分，橘色表示 SpringMVC 三大组件，这里共划分出了 11 个步骤，这里对这 11 个步骤进行一下说明：

  1）用户发送请求至前端控制器 DispatcherServlet。

  2）DispatcherServlet 收到请求调用 HandlerMapping 处理器映射器。

  3） 处理器映射器根据请求 url 找到具体的处理器，生成处理器对象及处理器拦截器（如果有则生成）一并返回给 DispatcherServlet。

  4） DispatcherServlet 通过 HandlerAdapter 处理器适配器调用处理器。

  5） HandlerAdapter 执行处理器（handler，也叫后端控制器）。

  6） Controller 执行完成返回 ModelAndView。

  7） HandlerAdapter 将 handler 执行结果 ModelAndView 返回给 DispatcherServlet。

  8） DispatcherServlet 将 ModelAndView 传给 ViewReslover 视图解析器。

  9） ViewReslover 解析后返回具体 View 对象。

  10） DispatcherServlet 对 View 进行渲染视图（即将模型数据填充至视图中）。

  11） DispatcherServlet 响应用户。
```

