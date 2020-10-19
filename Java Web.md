Filter

Servlet中的过滤器Filter是实现了javax.servlet.Filter接口的服务器端程序，主要的用途是过滤字符编码、做一些业务逻辑判断等。其工作原理是，只要你在web.xml文件配置好要拦截的客户端请求，它都会帮你拦截到请求，此时你就可以对请求或响应(Request、Response)统一设置编码，简化操作；同时还可以进行逻辑判断，如用户是否已经登录、有没有权限访问该页面等等工作，它是随你的web应用启动而启动的，只初始化一次，以后就可以拦截相关的请求，只有当你的web应用停止或重新部署的时候才能销毁。

在javax.servlet.Filter接口中定义了3个方法：

void init(FilterConfig filterConfig) 用于完成过滤器的初始化

void destroy() 用于过滤器销毁前，完成某些资源的回收

void doFilter(ServletRequest request, ServletResponse response,FilterChain chain) 实现过滤功能，该方法对每个请求增加额外的处理




Listener

Servlet的监听器Listener，它是实现了javax.servlet.ServletContextListener接口的服务器端程序，它也是随web应用的启动而启动，只初始化一次，随web应用的停止而销毁。主要作用是：做一些初始化的内容添加工作、设置一些基本的内容、比如一些参数或者是一些固定的对象等等。

在javax.servlet.ServletContextListener接口中定义了2种方法：

void contextInitialized(ServletContextEvent sce) 监听器的初始化

void contextDestroyed(ServletContextEvent sce) 监听器销毁



Interceptor

拦截器是在面向切面编程中应用的，就是在你的service或者一个方法前调用一个方法，或者在方法后调用一个方法比如动态代理就是拦截器的简单实现，在你调用方法前打印出字符串（或者做其它业务逻辑的操作），也可以在你调用方法后打印出字符串，甚至在你抛出异常的时候做业务逻辑的操作。拦截器不是在web.xml配置的，比如struts在struts.xml配置，在springMVC在spring与springMVC整合的配置文件中配置。

在springmvc中，定义拦截器要实现HandlerInterceptor接口，并实现该接口中提供的三个方法

preHandle方法：进入Handler方法之前执行。可以用于身份认证、身份授权。比如如果认证没有通过表示用户没有登陆，需要此方法拦截不再往下执行（return false），否则就放行（return true）。

postHandle方法：进入Handler方法之后，返回ModelAndView之前执行。可以看到该方法中有个modelAndView的形参。应用场景：从modelAndView出发：将公用的模型数据（比如菜单导航之类的）在这里传到视图，也可以在这里同一指定视图。

afterCompletion方法：执行Handler完成之后执行。应用场景：统一异常处理，统一日志处理等。

在springmvc中，拦截器是针对具体的HandlerMapping进行配置的，也就是说如果在某个HandlerMapping中配置拦截，经过该 HandlerMapping映射成功的handler最终使用该拦截器。



过滤器和拦截器的区别

1、拦截器是基于Java的反射机制的，而过滤器是基于函数回调
2、过滤器依赖与servlet容器，而拦截器不依赖与servlet容器
3、拦截器只能对action请求起作用，而过滤器则可以对几乎所有的请求起作用
4、拦截器可以访问action上下文、值栈里的对象，而过滤器不能
5、在action的生命周期中，拦截器可以多次被调用，而过滤器只能在容器初始化时被调用一次


过滤器、监听器、拦截器是什么？

过滤器（Filter）：当你有一堆东西的时候，你只希望选择符合你要求的某一些东西。定义这些要求的工具，就是过滤器。
拦截器（Interceptor）：在一个流程正在进行的时候，你希望干预它的进展，甚至终止它进行，这是拦截器做的事情。
监听器（Listener）：当一个事件发生的时候，你希望获得这个事件发生的详细信息，而并不想干预这个事件本身的进程，这就要用到监听器。
