## 一 框架简介

Spring Security官方文档地址：https://docs.spring.io/spring-security/site/docs/

**Spring Security**是Spring家族中的一个安全管理框架，提供了一整套Web应用安全性的完整解决方案。相比另一个安全框架**Shiro**，它提供了更丰富的功能，社区资源也比Shiro丰富。

一般来说中大型的项目都是使用Spring Security来做安全框架。小项目使用Shiro的比较多，因为相比Spring Security，Shiro上手更加的简单。

一般来说，Web应用安全性包括**认证（Authentication）**和**授权（Authorization）**两个部分：

- **认证：**验证某个用户是否是系统中的合法主体，也就是说用户能否访问系统，用户认证一般需要用户提供用户名和密码，系统通过校验用户名和密码来完成认证过程。
- **授权：**验证用户是否有权限执行某个操作。在一个系统中，不同用户拥有的权限是不同的。比如有些用户只有查询权限，有的用户拥有修改权限。一般来说，系统会为不同的用户分配不同的角色，而每个角色对应一系列的权限。

## 二 快速入门

### 2.1 准备工作

1. 创建一个security-quickstart的maven项目模块

2. 设置父工程，添加依赖

   ```xml
   <?xml version="1.0" encoding="UTF-8"?>
   <project xmlns="http://maven.apache.org/POM/4.0.0"
            xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
            xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
       <parent>
           <artifactId>springboot-security</artifactId>
           <groupId>com.yu.security</groupId>
           <version>1.0-SNAPSHOT</version>
       </parent>
       <modelVersion>4.0.0</modelVersion>
   
       <artifactId>security-quickstart</artifactId>
   
       <properties>
           <maven.compiler.source>8</maven.compiler.source>
           <maven.compiler.target>8</maven.compiler.target>
       </properties>
   
       <dependencies>
           <dependency>
               <groupId>org.springframework.boot</groupId>
               <artifactId>spring-boot-starter-web</artifactId>
           </dependency>
   
           <dependency>
               <groupId>org.projectlombok</groupId>
               <artifactId>lombok</artifactId>
               <optional>true</optional>
           </dependency>
       </dependencies>
   
   </project>
   ```

3. 创建启动类

   ```java
   @SpringBootApplication
   public class QuickSecurityApplication {
   
       public static void main(String[] args) {
           SpringApplication.run(QuickSecurityApplication.class, args);
       }
   }
   ```

4. 修改项目默认启动端口

   ```yaml
   server:
     port: 9530
   ```

5. 创建Controller

   ```java
   @RestController
   @RequestMapping("/quick")
   public class HelloController {
   
       @GetMapping("/hello")
       public String hello() {
           return "Hello Security";
       }
   }
   ```

### 2.2 引入Spring Security

在SpringBoot项目中使用Spring Security我们只需要引入依赖即可实现入门案例

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-security</artifactId>
</dependency>
```

此时访问后端接口`localhost:9530/quick/hello`会自动跳转到Spring Security登录页面进行验证，默认用户名是user，密码可以从项目启动的控制台中找到，必须登录之后才能访问接口。

## 三 认证

### 3.1 登录校验流程

```sequence
前端->后端: 携带用户名和密码访问登录接口
后端->后端: 和数据库中用户名和密码进行比对
后端->后端: 如果正确,使用用户名/用户id生成一个jwt
后端->前端: 将JWT返回给前端
前端->前端: 前端收到JWT
前端->前端: 将JWT存储在本地
前端->后端: 用户进行其他请求,需要在请求头中携带token
后端->后端: 获取请求头中的token进行解析,获取用户名/用户id
后端->后端: 根据用户名/用户id获取用户信息,如果有权限则允许访问相关资源
后端->后端: 访问目标资源,返回给前端
后端->前端: 响应信息
```

### 3.2 Spring Security完整流程

Spring Security本质就是一个`过滤器链`，内部包含了提供各种功能的过滤器。这里介绍三个核心的过滤器：

- **UsernamePasswordAuthenticationFilter**：负责处理在登录页面填写了用户名和密码之后的登录请求

- **ExceptionTranslationFilter：**负责处理过滤器链中抛出的任何`AccessDeniedException和AuthenticationException`

- **FilterSecurityInterceptor：**负责权限校验的过滤器

Spring Security中所有的过滤器以及过滤器执行的顺序如下：

1. WebAsyncManagerIntegrationFilter
2. SecurityContextPersistenceFilter
3. HeaderWriterFilter
4. CsrfFilter
5. LogoutFilter
6. UsernamePasswordAuthenticationFilter
7. DefaultLoginPageGeneratingFilter
8. DefaultLogoutPageGeneratingFilter
9. BasicAuthenticationFilter
10. RequestCacheAwareFilter
11. SecurityContextHolderAwareRequestFilter
12. AnonymousAuthenticationFilter
13. SessionManagementFilter
14. ExceptionTranslationFilter
15. FilterSecurityInterceptor

### 3.3 认证流程详解

1. 提交用户名和密码
2. 将用户名和密码封装为`Authentication`对象
3. 调用`authenticate`方法进行认证
4. 调用`DaoAuthenticationProvider`的`authenticate`方法进行认证
5. 调用`loadUserByUsername`方法查询用户，根据用户名查询这个用户以及对应的权限等信息，`InMemoryUserDetailsManager`是在内存中查找，再把对应的用户以及权限等信息封装为`UserDetails`对象
6. 返回`UserDetails`对象
7. 通过`PasswordEncoder`对比`UserDetails`中的密码和`Authentication`中的密码是否正确
8. 如果正确就把`UserDetails`对象中的权限等信息设置到`Authentication`对象中
9. 返回`Authentication`对象
10. 使用`SecurityContextHolder.getContext.setAuthentication`方法存储该对象，其他过滤器会通过`SecurityContextHolder`来获取当前用户信息

### 3.4 认证实现

#### 3.4.1 思路分析

- 自定义登录接口
  - 调用ProviderManager的方法进行认证，如果认证通过生成jwt
  - 把用户信息存入redis
- 自定义UserDetailsService
  - 在实现类中查询数据库
- 定义JWT认证过滤器
  - 获取token
  - 解析token获取其中的userid
  - 从redis中获取用户信息
  - 存入SecurityContextHolder

#### 3.4.2 准备工作

1. 创建一个security-token的maven项目模块

2. 添加依赖

   ```xml
   <?xml version="1.0" encoding="UTF-8"?>
   <project xmlns="http://maven.apache.org/POM/4.0.0"
            xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
            xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
       <parent>
           <artifactId>springboot-security</artifactId>
           <groupId>com.yu.security</groupId>
           <version>1.0-SNAPSHOT</version>
       </parent>
       <modelVersion>4.0.0</modelVersion>
   
       <artifactId>security-token</artifactId>
   
       <properties>
           <maven.compiler.source>8</maven.compiler.source>
           <maven.compiler.target>8</maven.compiler.target>
           <fastjson.version>1.2.33</fastjson.version>
           <jjwt.version>0.9.0</jjwt.version>
       </properties>
   
       <dependencies>
           <dependency>
               <groupId>org.springframework.boot</groupId>
               <artifactId>spring-boot-starter-web</artifactId>
           </dependency>
   
           <!-- spring security依赖 -->
           <dependency>
               <groupId>org.springframework.boot</groupId>
               <artifactId>spring-boot-starter-security</artifactId>
           </dependency>
   
           <!-- redis依赖 -->
           <dependency>
               <groupId>org.springframework.boot</groupId>
               <artifactId>spring-boot-starter-data-redis</artifactId>
           </dependency>
   
           <dependency>
               <groupId>com.alibaba</groupId>
               <artifactId>fastjson</artifactId>
               <version>${fastjson.version}</version>
           </dependency>
   
           <dependency>
               <groupId>io.jsonwebtoken</groupId>
               <artifactId>jjwt</artifactId>
               <version>${jjwt.version}</version>
           </dependency>
   
           <dependency>
               <groupId>org.projectlombok</groupId>
               <artifactId>lombok</artifactId>
               <optional>true</optional>
           </dependency>
       </dependencies>
   
   </project>
   ```

3. 添加redis相关配置

   ```java
   package com.yu.security.config;
   
   import com.alibaba.fastjson.JSON;
   import org.springframework.data.redis.serializer.RedisSerializer;
   import org.springframework.data.redis.serializer.SerializationException;
   import org.springframework.util.Assert;
   import org.springframework.util.StringUtils;
   
   import java.nio.charset.Charset;
   import java.nio.charset.StandardCharsets;
   
   /**
    * redis配置string序列化(key序列化)
    */
   public class StringRedisSerializer<T> implements RedisSerializer<Object> {
   
       private final Charset charset;
   
       public StringRedisSerializer() {
           this(StandardCharsets.UTF_8);
       }
   
       public StringRedisSerializer(Charset charset) {
           Assert.notNull(charset, "Charset must not be null!");
           this.charset = charset;
       }
   
       @Override
       public byte[] serialize(Object object) throws SerializationException {
           final String target = "\"";
           final String replacement = "";
   
           String string = JSON.toJSONString(object);
           if (StringUtils.hasText(string)) {
               return null;
           }
           string = string.replace(target, replacement);
           return string.getBytes(charset);
       }
   
       @Override
       public Object deserialize(byte[] bytes) throws SerializationException {
           return (null == bytes ? null : new String(bytes, charset));
       }
   }
   ```

   ```java
   package com.yu.security.config;
   
   import com.alibaba.fastjson.JSON;
   import com.alibaba.fastjson.serializer.SerializerFeature;
   import org.springframework.data.redis.serializer.RedisSerializer;
   import org.springframework.data.redis.serializer.SerializationException;
   
   import java.nio.charset.StandardCharsets;
   
   /**
    * redis配置fastjson序列化(value序列化)
    */
   public class FastJsonRedisSerializer<T> implements RedisSerializer<T> {
   
       private final Class<T> clazz;
   
       public FastJsonRedisSerializer(Class<T> clazz) {
           super();
           this.clazz = clazz;
       }
   
       @Override
       public byte[] serialize(T t) throws SerializationException {
           if (t == null) {
               return new byte[0];
           }
           return JSON.toJSONString(t, SerializerFeature.WriteClassName,
                   // 是否输出值为null的字段,默认为false
                   SerializerFeature.WriteMapNullValue,
                   // List字段如果为null,输出为[],而非null
                   SerializerFeature.WriteNullListAsEmpty).getBytes(StandardCharsets.UTF_8);
       }
   
       @Override
       public T deserialize(byte[] bytes) throws SerializationException {
           if (null == bytes || bytes.length <= 0) {
               return null;
           }
           String str = new String(bytes, StandardCharsets.UTF_8);
           return JSON.parseObject(str, clazz);
       }
   }
   ```

   ```java
   /**
    * redis配置类
    */
   @Configuration
   public class RedisConfig {
   
       @Bean
       public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
           RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
           // 使用fastJson序列化
           FastJsonRedisSerializer<Object> fastJsonRedisSerializer = new FastJsonRedisSerializer<>(Object.class);
           StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
           // value值的序列化采用fastJsonRedisSerializer
           redisTemplate.setValueSerializer(fastJsonRedisSerializer);
           redisTemplate.setHashValueSerializer(fastJsonRedisSerializer);
           // 全局开启AutoType，不建议使用
           // ParserConfig.getGlobalInstance().setAutoTypeSupport(true);
           // 建议使用这种方式，小范围指定白名单，需要序列化的类
           ParserConfig.getGlobalInstance().addAccept("com.yu.security");
           // key的序列化采用StringRedisSerializer
           redisTemplate.setKeySerializer(stringRedisSerializer);
           redisTemplate.setHashKeySerializer(stringRedisSerializer);
           redisTemplate.setConnectionFactory(redisConnectionFactory);
           return redisTemplate;
       }
   
       @Bean
       @SuppressWarnings(value = {"unchecked", "rawtypes"})
       public RedisCacheManager cacheManager(RedisConnectionFactory connectionFactory) {
           FastJsonRedisSerializer<Object> fastJsonRedisSerializer = new FastJsonRedisSerializer<>(Object.class);
           StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
           RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig()
                   // 默认缓存时间
                   .entryTtl(Duration.ofSeconds(600))
                   // 设置key的序列化方式
                   .serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(stringRedisSerializer))
                   // 设置value的序列化方式
                   .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(fastJsonRedisSerializer));
           return RedisCacheManager.builder(connectionFactory)
                   .cacheDefaults(config)
                   .transactionAware()
                   .build();
       }
   }
   ```

4. 添加工具类

   ```java
   /**
    * redis工具类
    */
   @Component
   public class RedisUtils {
   
       private final RedisTemplate<String, Object> redisTemplate;
   
       public RedisUtils(RedisTemplate<String, Object> redisTemplate) {
           this.redisTemplate = redisTemplate;
       }
   
       /** -------------------key相关操作--------------------- */
   
       public void delete(String key) {
           redisTemplate.delete(key);
       }
   
       public void delete(Collection<String> keys) {
           redisTemplate.delete(keys);
       }
   
       public Boolean hasKey(String key) {
           return redisTemplate.hasKey(key);
       }
   
       public Boolean expire(String key, long timeout, TimeUnit unit) {
           return redisTemplate.expire(key, timeout, unit);
       }
   
       public Boolean expireAt(String key, Date date) {
           return redisTemplate.expireAt(key, date);
       }
   
       public Set<String> keys(String pattern) {
           return redisTemplate.keys(pattern);
       }
   
       public Long getExpire(String key) {
           return redisTemplate.getExpire(key);
       }
   
       public DataType type(String key) {
           return redisTemplate.type(key);
       }
   
       /** -------------------string相关操作--------------------- */
       
       public void set(String key, String value, long time, TimeUnit unit) {
           //key设置过期时间和单位,默认给过期时间为5分钟
           redisTemplate.opsForValue().set(key, value, time, unit);
       }
   
       public Object get(String key) {
           return redisTemplate.opsForValue().get(key);
       }
   
       public void setEx(String key, String value, long timeout, TimeUnit unit) {
           redisTemplate.opsForValue().set(key, value, timeout, unit);
       }
   
       public Long size(String key) {
           return redisTemplate.opsForValue().size(key);
       }
   
       public void multiSet(Map<String, String> maps) {
           redisTemplate.opsForValue().multiSet(maps);
       }
   
       public Long incrBy(String key, long increment) {
           return redisTemplate.opsForValue().increment(key, increment);
       }
   
       /** -------------------hash相关操作------------------------- */
   
       public Object hGet(String key, String field) {
           return redisTemplate.opsForHash().get(key, field);
       }
   
       public void hPut(String key, String hashKey, String value) {
           redisTemplate.opsForHash().put(key, hashKey, value);
       }
   
       public Long hDelete(String key, Object... fields) {
           return redisTemplate.opsForHash().delete(key, fields);
       }
   
       public boolean hExists(String key, String field) {
           return redisTemplate.opsForHash().hasKey(key, field);
       }
   
       public Long hIncrBy(String key, Object field, long increment) {
           return redisTemplate.opsForHash().increment(key, field, increment);
       }
   
       public Set<Object> hKeys(String key) {
           return redisTemplate.opsForHash().keys(key);
       }
       
       public List<Object> hValues(String key) {
           return redisTemplate.opsForHash().values(key);
       }
   
       /** ------------------------list相关操作---------------------------- */
   
       public Object lIndex(String key, long index) {
           return redisTemplate.opsForList().index(key, index);
       }
   
       public List<Object> lRange(String key, long start, long end) {
           return redisTemplate.opsForList().range(key, start, end);
       }
   
       public Long lLeftPush(String key, String value) {
           return redisTemplate.opsForList().leftPush(key, value);
       }
   
       public Long lLeftPush(String key, String pivot, String value) {
           return redisTemplate.opsForList().leftPush(key, pivot, value);
       }
   
       public Long lRightPush(String key, String value) {
           return redisTemplate.opsForList().rightPush(key, value);
       }
   
       public Long lRightPush(String key, String pivot, String value) {
           return redisTemplate.opsForList().rightPush(key, pivot, value);
       }
   
       public void lSet(String key, long index, String value) {
           redisTemplate.opsForList().set(key, index, value);
       }
   
       public Object lLeftPop(String key) {
           return redisTemplate.opsForList().leftPop(key);
       }
   
       public Object lBLeftPop(String key, long timeout, TimeUnit unit) {
           return redisTemplate.opsForList().leftPop(key, timeout, unit);
       }
       
       public Object lRightPop(String key) {
           return redisTemplate.opsForList().rightPop(key);
       }
   
       public Object lBRightPop(String key, long timeout, TimeUnit unit) {
           return redisTemplate.opsForList().rightPop(key, timeout, unit);
       }
   
       public Long lRemove(String key, long index, String value) {
           return redisTemplate.opsForList().remove(key, index, value);
       }
   
       public Long lLen(String key) {
           return redisTemplate.opsForList().size(key);
       }
   }
   ```

   ```java
   /**
    * 统一返回结果类
    */
   @JsonInclude(JsonInclude.Include.NON_NULL)
   public class ResponseResult<T> {
   
       /**
        * 状态码
        */
       private Integer code;
   
       /**
        * 消息
        */
       private String msg;
   
       /**
        * 结果数据
        */
       private T data;
   
       public ResponseResult(Integer code, String msg) {
           this.code = code;
           this.msg = msg;
       }
   
       public ResponseResult(Integer code, T data) {
           this.code = code;
           this.data = data;
       }
   
       public Integer getCode() {
           return code;
       }
   
       public void setCode(Integer code) {
           this.code = code;
       }
   
       public String getMsg() {
           return msg;
       }
   
       public void setMsg(String msg) {
           this.msg = msg;
       }
   
       public T getData() {
           return data;
       }
   
       public void setData(T data) {
           this.data = data;
       }
   }
   ```

   ```java
   /**
    * jwt工具类
    */
   public class JwtUtils {
   
       /**
        * token过期时间,一个小时
        */
       public static final Long EXPIRE = 1000 * 60 * 60L;
   
       /**
        * token秘钥
        */
       public static final String JWT_SECRET = "ukc8BDbRigUDaY6pZFfWus2jZWLPHO";
   
       /**
        * 传入参数，获取jwt字符串
        *
        * @param id 参数之id
        * @return
        */
       public static String createJwtToken(String id) {
   
           return Jwts.builder()
                   .setHeaderParam("typ", "JWT")
                   .setHeaderParam("alg", "HS256")
                   .setSubject("user")
                   .setIssuedAt(new Date())
                   .setExpiration(new Date(System.currentTimeMillis() + EXPIRE))
                   .setId(id)
   
                   /*
                    * 在这里可以一直添加参数
                    */
   //                .claim("id", id)
   //                .claim("name", "zs")
   
                   .signWith(SignatureAlgorithm.HS256, JWT_SECRET)
                   .compact();
       }
   
       /**
        * 判断token是否存在与有效，无效会抛异常
        */
       public static boolean checkToken(String jwtToken) {
           if (!StringUtils.hasText(jwtToken)) {
               return false;
           }
           try {
               Jwts.parser().setSigningKey(JWT_SECRET).parseClaimsJws(jwtToken);
           } catch (Exception e) {
               e.printStackTrace();
               return false;
           }
           return true;
       }
   
       /**
        * 判断token是否存在与有效，无效会抛异常
        *
        * @param request
        * @return
        */
       public static boolean checkToken(HttpServletRequest request) {
           try {
               String jwtToken = request.getHeader("token");
               if (!StringUtils.hasText(jwtToken)) {
                   return false;
               }
               Jwts.parser().setSigningKey(JWT_SECRET).parseClaimsJws(jwtToken);
           } catch (Exception e) {
               e.printStackTrace();
               return false;
           }
           return true;
       }
   
   
       /**
        * 解析token
        *
        * @param request 请求头信息
        * @return {@link Claims}
        */
       public static Claims parseJwtToken(HttpServletRequest request) {
           String token = request.getHeader("token");
           if (!StringUtils.hasText(token)) {
               return null;
           }
   
           return Jwts.parser()
                   .setSigningKey(JWT_SECRET)
                   .parseClaimsJws(token)
                   .getBody();
       }
   }
   ```

#### 3.4.3 实现过程

1. 创建用户数据表

   ```sql
   DROP TABLE IF EXISTS `sys_user`;
   
   CREATE TABLE `sys_user` (
   	id BIGINT(20) auto_increment NOT NULL COMMENT '主键ID',
   	user_name varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '用户名',
   	nick_name varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '昵称',
   	passwd varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '密码',
   	state TINYINT(2) DEFAULT 0 NULL COMMENT '账号状态（0-正常 1-停用）',
   	email varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL COMMENT '邮箱',
   	phone_number varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL COMMENT '手机号',
   	sex TINYINT(2) NULL COMMENT '用户性别（0-男 1-女 2-未知）',
   	avatar varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL COMMENT '头像',
   	user_type TINYINT(2) DEFAULT 1 NULL COMMENT '用户类型（0-管理员 1-普通用户）',
   	create_by BIGINT(20) NULL COMMENT '创建人用户id',
   	create_time DATETIME NULL COMMENT '创建时间',
   	update_by BIGINT(20) NULL COMMENT '更新人用户id',
   	update_time DATETIME NULL COMMENT '更新时间',
   	deleted TINYINT(2) DEFAULT 0 NULL COMMENT '删除标志(0-未删除 1-已删除)',
   	CONSTRAINT sys_user_PK PRIMARY KEY (id)
   )
   ENGINE=InnoDB
   DEFAULT CHARSET=utf8mb4
   COLLATE=utf8mb4_unicode_ci
   COMMENT='用户表';
   ```

2. 添加实体类

   ```java
   @Data
   @EqualsAndHashCode(callSuper = false)
   @Accessors(chain = true)
   @TableName("sys_user")
   public class User implements Serializable {
   
       private static final long serialVersionUID = 1L;
   
       /**
        * 主键ID
        */
       @TableId(value = "id", type = IdType.AUTO)
       private Long id;
   
       /**
        * 用户名
        */
       private String userName;
   
       /**
        * 昵称
        */
       private String nickName;
   
       /**
        * 密码
        */
       private String passwd;
   
       /**
        * 账号状态（0-正常 1-停用）
        */
       private Boolean state;
   
       /**
        * 邮箱
        */
       private String email;
   
       /**
        * 手机号
        */
       private String phoneNumber;
   
       /**
        * 用户性别（0-男 1-女 2-未知）
        */
       private Boolean sex;
   
       /**
        * 头像
        */
       private String avatar;
   
       /**
        * 用户类型（0-管理员 1-普通用户）
        */
       private Boolean userType;
   
       /**
        * 创建人用户id
        */
       private Long createBy;
   
       /**
        * 创建时间
        */
       private LocalDateTime createTime;
   
       /**
        * 更新人用户id
        */
       private Long updateBy;
   
       /**
        * 更新时间
        */
       private Long updateTime;
   
       /**
        * 删除标志(0-未删除 1-已删除)
        */
       private Boolean deleted;
   }
   ```

3. 添加依赖信息

   ```xml
   <properties>
       <mybatis-plus.version>3.4.3</mybatis-plus.version>
   </properties>
   
   <!-- mybatis-plus依赖 -->
   <dependency>
       <groupId>com.baomidou</groupId>
       <artifactId>mybatis-plus-boot-starter</artifactId>
       <version>${mybatis-plus.version}</version>
   </dependency>
   
   <!-- mysql依赖 -->
   <dependency>
       <groupId>mysql</groupId>
       <artifactId>mysql-connector-java</artifactId>
   </dependency>
   ```

4. 配置数据库

   ```yaml
   spring:
     datasource:
       url: jdbc:mysql://ip:port/study?useUnicode=true&characterEncoding=utf-8&useSSL=false&serverTimezone=Asia/Shanghai
       driverClassName: com.mysql.cj.jdbc.Driver
       username: root
       password: root
   ```

5. 添加mybatis-plus配置类

   ```java
   @Configuration
   @MapperScan(basePackages = {"com.yu.security.mapper"})
   public class MyBatisPlusConfig {
       
   }
   ```

6. 自定义认证逻辑

   ```java
   /**
    * 登录用户实体类
    */
   @NoArgsConstructor
   @AllArgsConstructor
   @Data
   public class LoginUser implements UserDetails {
   
       private User user;
   
       @Override
       public Collection<? extends GrantedAuthority> getAuthorities() {
           return null;
       }
   
       @Override
       public String getPassword() {
           return user.getPasswd();
       }
   
       @Override
       public String getUsername() {
           return user.getUserName();
       }
   
       @Override
       public boolean isAccountNonExpired() {
           return true;
       }
   
       @Override
       public boolean isAccountNonLocked() {
           return true;
       }
   
       @Override
       public boolean isCredentialsNonExpired() {
           return true;
       }
   
       @Override
       public boolean isEnabled() {
           return true;
       }
   }
   ```

   ```java
   /**
    * 用户详情服务实现类(自定义认证)
    */
   @Service
   public class UserDetailsServiceImpl implements UserDetailsService {
       
       private final UserMapper userMapper;
   
       public UserDetailsServiceImpl(UserMapper userMapper) {
           this.userMapper = userMapper;
       }
   
       @Override
       public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
   
           // 根据用户名查询用户
           User user = userMapper.selectOne(new LambdaQueryWrapper<User>().eq(User::getUserName, username));
           if (Objects.isNull(user)) {
               throw new UsernameNotFoundException("该用户不存在!");
           }
           // TODO: 2023/7/8  校验权限
   
           return new LoginUser(user);
       }
   }
   ```

7. 密码加密

   在实际项目中，不会将密码明文存储到数据库中。Spring Security默认使用的PasswordEncoder要求数据库中的密码格式为`{id}password`。它会根据id去判断密码的加密方式，一般也不会使用这种，而是使用`BCryptPasswordEncoder`。只需要将BCryptPasswordEncoder注入Spring容器中，便可替换默认的加密方式。

   ```java
   @Configuration
   public class SecurityConfig implements WebMvcConfigurer {
   
       /**
        * 将加密方式修改为BCryptPasswordEncoder
        */
       @Bean
       public PasswordEncoder passwordEncoder() {
           return new BCryptPasswordEncoder();
       }
   }
   ```

   下面是一个使用BCryptPasswordEncoder加密的示例：

   ```java
   @SpringBootTest
   public class TokenTest {
   
       @Autowired
       private PasswordEncoder passwordEncoder;
   
       @Test
       public void test2() {
           String encode = passwordEncoder.encode("12345");
           System.out.println(encode);
       }
   }
   ```

8. 自定义登录接口

   自定义登录接口的时候，需要配置过滤器放行登录接口，在接口中可以使用`AuthenticationManager`的`authenticate`方法来进行用户认证，所以需要将`AuthenticationManager`注入到Spring容器中。认证成功后会生成jwt放在响应中返回，为了保持会话，可以将用户信息存入redis，以用户id作为key。

   ```java
   @RestController
   @RequestMapping("/user")
   public class LoginController {
   
       private final LoginService loginService;
   
       public LoginController(LoginService loginService) {
           this.loginService = loginService;
       }
   
       /**
        * 登录
        */
       @PostMapping("/login")
       public ResponseResult<Map<String, Object>> login(@RequestBody LoginUserDTO dto) {
           Map<String, Object> map = loginService.login(dto);
           return new ResponseResult<>(200, "登录成功", map);
       }
   }
   ```

   ```java
   public interface LoginService {
   
       /**
        * 登录
        *
        * @param dto dto
        * @return {@link Map}<{@link String}, {@link Object}>
        */
       Map<String, Object> login(LoginUserDTO dto);
   }
   ```

   ```java
   @Service
   public class LoginServiceImpl implements LoginService {
   
       private final AuthenticationManager authenticationManager;
   
       private final RedisUtils redisUtils;
   
       public LoginServiceImpl(AuthenticationManager authenticationManager, RedisUtils redisUtils) {
           this.authenticationManager = authenticationManager;
           this.redisUtils = redisUtils;
       }
   
       /**
        * 登录
        */
       @Override
       public Map<String, Object> login(LoginUserDTO dto) {
           // 1.用户名和密码构建成UsernamePasswordAuthenticationToken对象
           UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(dto.getUserName(), dto.getPasswd());
   
           // 2.使用AuthenticationManager的authentication方法进行用户认证,实际调用loadUserByUsername方法进行校验
           Authentication authenticate = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
   
           // 3.认证失败则给出对应的提示
           if (Objects.isNull(authenticate)) {
               throw new RuntimeException("用户名或密码错误!");
           }
   
           // 4.获取认证后的用户信息,principal其实就是loadUserByUsername方法返回的信息
           LoginUser principal = (LoginUser) authenticate.getPrincipal();
   
           Long userId = Optional.ofNullable(principal).map(LoginUser::getUser).map(User::getId).orElse(null);
   
           // 5.认证通过则使用userId生成jwt保存到map中
           Map<String, Object> map = new HashMap<>(16);
           String token = JwtUtils.createJwtToken(String.valueOf(userId));
           map.put("token", token);
   
           // 6.把完整的用户信息存入redis
           redisUtils.set("login:" + userId, JSON.toJSONString(principal), 1, TimeUnit.HOURS);
   
           return map;
       }
   }
   ```

   ```java
   @Configuration
   public class SecurityConfig implements WebMvcConfigurer {
   
       /**
        * 将加密方式修改为BCryptPasswordEncoder
        */
       @Bean
       public PasswordEncoder passwordEncoder() {
           return new BCryptPasswordEncoder();
       }
   
       /**
        * 注入认证管理器
        */
       @Bean
       public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
           return authenticationConfiguration.getAuthenticationManager();
       }
   
       @Bean
       SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
           return http
                   // 关闭csrf
                   .csrf().disable()
                   // 不通过Session获取SecurityContext
                   .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                   .and()
                   // 下面开始设置权限
                   .authorizeRequests(authorize -> authorize
                           // 对于登录接口,允许匿名访问
                           .antMatchers("/user/login").anonymous()
                           // 其他地址的访问均需验证权限
                           .anyRequest().authenticated()
                   )
                   .build();
       }
   }
   ```

   ```yaml
   server:
     port: 9531
   
   spring:
     datasource:
       url: jdbc:mysql://ip:port/study?useUnicode=true&characterEncoding=utf-8&useSSL=false&serverTimezone=Asia/Shanghai
       driverClassName: com.mysql.cj.jdbc.Driver
       username: root
       password: root
   
     jackson:
       time-zone: GMT+8
       date-format: yyyy-MM-dd HH:mm:ss
   
     redis:
       database: 1
       # Redis服务器地址
       host: 
       # Redis服务器连接端口
       port: 
       # Redis服务器连接密码（默认为空）
       password: 
       timeout: 2000
       lettuce:
         pool:
           # 连接池最大连接数（使用负值表示没有限制）
           max-active: 100
           # 连接池最大阻塞等待时间（使用负值表示没有限制）
           max-wait: -1ms
           # 连接池中的最大空闲连接
           max-idle: 20
           # 连接池中的最小空闲连接
           min-idle: 0
   ```

   ```xml
   <!-- 使用redis lettuce必须添加此依赖,否则启动会报错 -->
   <dependency>
       <groupId>org.apache.commons</groupId>
       <artifactId>commons-pool2</artifactId>
   </dependency>
   ```

9. 认证过滤器

   自定义认证过滤器获取请求头中的token，并且解析token获取用户id，再通过用户id获取redis中存储的用户对象，然后将用户对象封装到`Authentication`对象中，最后将Authentication对象存入`SecurityContextHolder`中。

   ```java
   /**
    * JWT过滤器
    */
   @Component
   public class JwtFilter extends OncePerRequestFilter {
   
       private final RedisUtils redisUtils;
   
       public JwtFilter(RedisUtils redisUtils) {
           this.redisUtils = redisUtils;
       }
   
       @Override
       protected void doFilterInternal(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response, @NotNull FilterChain filterChain) throws ServletException, IOException {
           final String userId;
   
           try {
               // 从请求头获取token并解析
               Claims claims = JwtUtils.parseJwtToken(request);
   
               // token不存在则直接放行,否则继续执行
               if (Objects.isNull(claims)) {
                   filterChain.doFilter(request, response);
                   return;
               }
               userId = claims.getId();
           } catch (Exception e) {
               e.printStackTrace();
               throw new RuntimeException("获取token非法");
           }
   
           // 从redis中获取用户信息并将json字符串转为对象
           Object o = redisUtils.get("login:" + userId);
           if (Objects.isNull(o)) {
               throw new RuntimeException("用户未登录");
           }
           LoginUser loginUser = JSONObject.parseObject(o.toString(), LoginUser.class);
   
           // 将对象信息构造到UsernamePasswordAuthenticationToken,这里注意必须使用三个参数的构造方法,用于设置authenticated为true
           UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginUser, null, null);
   
           // 将authenticationToken存入SecurityContextHolder
           // TODO 获取权限封装到SecurityContextHolder
           SecurityContextHolder.getContext().setAuthentication(authenticationToken);
           filterChain.doFilter(request, response);
       }
   }
   ```

   ```java
   @Configuration
   public class SecurityConfig implements WebMvcConfigurer {
       
       private final JwtFilter jwtFilter;
   
       public SecurityConfig(JwtFilter jwtFilter) {
           this.jwtFilter = jwtFilter;
       }
   
       /**
        * 将加密方式修改为BCryptPasswordEncoder
        */
       @Bean
       public PasswordEncoder passwordEncoder() {
           return new BCryptPasswordEncoder();
       }
   
       /**
        * 注入认证管理器
        */
       @Bean
       public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
           return authenticationConfiguration.getAuthenticationManager();
       }
   
       @Bean
       SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
           return http
                   // 关闭csrf
                   .csrf().disable()
                   // 不通过Session获取SecurityContext
                   .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                   .and()
                   // 下面开始设置权限
                   .authorizeRequests(authorize -> authorize
                           // 对于登录接口,允许匿名访问
                           .antMatchers("/user/login").anonymous()
                           // 其他地址的访问均需验证权限
                           .anyRequest().authenticated()
                   )
                   // 添加JWT过滤器,JWT过滤器在用户名密码认证过滤器之前
                   .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                   .build();
       }
   }
   ```

10. 注销

    获取SecurityContextHolder中的认证信息，然后删除redis中存储的数据

    ```java
    @RestController
    @RequestMapping("/user")
    public class LoginController {
    
        private final LoginService loginService;
    
        public LoginController(LoginService loginService) {
            this.loginService = loginService;
        }
    
        /**
         * 注销
         *
         * @return {@link ResponseResult}<{@link String}>
         */
        @GetMapping("/logout")
        public ResponseResult<String> logout() {
            String msg = loginService.logout();
            return new ResponseResult<>(200, msg);
        }
    }
    ```

    ```java
    public interface LoginService {
    
        /**
         * 注销
         *
         * @return {@link String}
         */
        String logout();
    }
    ```

    ```java
    @Service
    public class LoginServiceImpl implements LoginService {
    
        private final AuthenticationManager authenticationManager;
    
        private final RedisUtils redisUtils;
    
        public LoginServiceImpl(AuthenticationManager authenticationManager, RedisUtils redisUtils) {
            this.authenticationManager = authenticationManager;
            this.redisUtils = redisUtils;
        }
    
        /**
         * 注销
         */
        @Override
        public String logout() {
            // 从SecurityContextHolder中获取authentication
            UsernamePasswordAuthenticationToken authenticationToken = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
    
            // authentication获取用户信息
            LoginUser loginUser = (LoginUser) authenticationToken.getPrincipal();
    
            // 根据用户id删除redis数据
            redisUtils.delete("login:" + loginUser.getUser().getId());
    
            return "注销成功";
        }
    }
    ```

## 四 授权

### 4.1 权限系统的作用

例如一个学校的图书管理系统，如果是普通学生只能看到借书和还书相关的功能，如果是管理员应该有添加书籍、修改书籍相关的功能。即`不同用户拥有不同的功能去操作某个事情`，权限不能放在前端处理，因为可能会有人伪造请求来实现相关功能操作，一般在后台进行校验，判断当前用户是否有对应的权限，必须基于权限才能进行相应的操作。

### 4.2 授权基本流程

1. Spring Security会使用默认的`FilterSecurityInterceptor`来进行权限校验
2. `FilterSecurityInterceptor`从`SecurityContextHolder`获取其中的`Authentication`，然后获取权限信息
3. 判断当前用户是否拥有访问当前资源的权限

### 4.3 授权实现

#### 4.3.1 授权配置

Spring Securityt提供了两种权限控制方案：基于**注解**和基于**配置**。目前主流的方案是基于注解，使用之前需要开启相关的配置。下面是一个简单的配置示例：

```java
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig implements WebMvcConfigurer {
    
}
```

#### 4.3.2 封装权限信息

```java
@NoArgsConstructor
@Data
public class LoginUser implements UserDetails {

    /**
     * 数据库中的用户信息
     */
    private User user;

    /**
     * 自定义的权限信息,需要封装到SimpleGrantedAuthority中
     */
    private Set<String> permissions;

    /**
     * spring security实际的权限认证信息
     */
    @JSONField(serialize = false)
    private List<SimpleGrantedAuthority> authorities;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // 权限信息存在则直接返回,不存在则将permissions封装到authorities中再返回
        if (!CollectionUtils.isEmpty(authorities)) {
            return authorities;
        }

        return permissions.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    public LoginUser(User user, Set<String> permissions) {
        this.user = user;
        this.permissions = permissions;
    }

    @Override
    public String getPassword() {
        return user.getPasswd();
    }

    @Override
    public String getUsername() {
        return user.getUserName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
```

```java
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    
    private final UserMapper userMapper;

    public UserDetailsServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        // 根据用户名查询用户
        User user = userMapper.selectOne(new LambdaQueryWrapper<User>().eq(User::getUserName, username));
//        if (Objects.isNull(user)) {
//            throw new UsernameNotFoundException("该用户不存在!");
//        }
        // TODO: 2023/7/8  校验权限
        List<String> permissions = Arrays.asList("test", "admin");
        Set<String> permissionList = new HashSet<>(permissions);

        return new LoginUser(user, permissionList);
    }
}
```

```java
@Component
public class JwtFilter extends OncePerRequestFilter {

    private final RedisUtils redisUtils;

    public JwtFilter(RedisUtils redisUtils) {
        this.redisUtils = redisUtils;
    }

    @Override
    protected void doFilterInternal(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response, @NotNull FilterChain filterChain) throws ServletException, IOException {
        final String userId;

        try {
            // 从请求头获取token并解析
            Claims claims = JwtUtils.parseJwtToken(request);

            // token不存在则直接放行,否则继续执行
            if (Objects.isNull(claims)) {
                filterChain.doFilter(request, response);
                return;
            }
            userId = claims.getId();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("获取token非法");
        }

        // 从redis中获取用户信息并将json字符串转为对象
        Object o = redisUtils.get("login:" + userId);
        if (Objects.isNull(o)) {
            throw new RuntimeException("用户未登录");
        }
        LoginUser loginUser = JSONObject.parseObject(o.toString(), LoginUser.class);

        // 将对象信息构造到UsernamePasswordAuthenticationToken,这里注意必须使用三个参数的构造方法,用于设置authenticated为true
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginUser, null, loginUser.getAuthorities());

        // 将authenticationToken存入SecurityContextHolder
        // TODO 获取权限封装到SecurityContextHolder
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        filterChain.doFilter(request, response);
    }
}
```

```java
@RestController
@RequestMapping("/token")
public class HelloController {

    @GetMapping("/hello")
    @PreAuthorize("hasAuthority('test')")
    public String hello() {
        return "Hello Security";
    }
}
```

#### 4.3.4 从数据库中查询权限

1. 创建数据表

   ```sql
   DROP TABLE IF EXISTS `sys_menu`;
   
   CREATE TABLE `sys_menu` (
   	id BIGINT(20) auto_increment NOT NULL COMMENT '主键ID',
   	menu_name varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '菜单名',
   	menu_path varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL COMMENT '路由地址',
   	component_path varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL COMMENT '组件路径',
   	menu_visible TINYINT(2) DEFAULT 0 NULL COMMENT '菜单状态（0-显示 1-隐藏）',
   	state TINYINT(2) DEFAULT 0 NULL COMMENT '状态（0-正常 1-停用）',
   	perms varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL COMMENT '权限标识',
   	icon varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT '#' NULL COMMENT '菜单图标',
   	create_by BIGINT(20) NULL COMMENT '创建人用户id',
   	create_time DATETIME NULL COMMENT '创建时间',
   	update_by BIGINT(20) NULL COMMENT '更新人用户id',
   	update_time DATETIME NULL COMMENT '更新时间',
   	deleted TINYINT(2) DEFAULT 0 NULL COMMENT '删除标志(0-未删除 1-已删除)',
   	remark varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL COMMENT '备注',
   	CONSTRAINT sys_menu_PK PRIMARY KEY (id)
   )
   ENGINE=InnoDB
   DEFAULT CHARSET=utf8mb4
   COLLATE=utf8mb4_unicode_ci
   COMMENT='菜单表';
   
   DROP TABLE IF EXISTS `sys_role`;
   
   CREATE TABLE `sys_role` (
   	id BIGINT(20) auto_increment NOT NULL COMMENT '主键ID',
   	role_name varchar(100)  CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL COMMENT '角色名',
   	role_key varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL COMMENT '角色权限字符串',
   	state TINYINT(2) DEFAULT 0 NULL COMMENT '状态（0-正常 1-停用）',
   	create_by BIGINT(20) NULL COMMENT '创建人用户id',
   	create_time DATETIME NULL COMMENT '创建时间',
   	update_by BIGINT(20) NULL COMMENT '更新人用户id',
   	update_time DATETIME NULL COMMENT '更新时间',
   	deleted TINYINT(2) DEFAULT 0 NULL COMMENT '删除标志(0-未删除 1-已删除)',
   	remark varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL COMMENT '备注',
   	CONSTRAINT sys_role_PK PRIMARY KEY (id)
   )
   ENGINE=InnoDB
   DEFAULT CHARSET=utf8mb4
   COLLATE=utf8mb4_unicode_ci
   COMMENT='角色表';
   
   DROP TABLE IF EXISTS `sys_role_menu`;
   
   CREATE TABLE `sys_role_menu` (
   	role_id BIGINT(20) auto_increment NOT NULL COMMENT '角色ID',
   	menu_id BIGINT(20) NOT NULL COMMENT '菜单ID',
   	CONSTRAINT sys_role_menu_PK PRIMARY KEY (role_id,menu_id)
   )
   ENGINE=InnoDB
   DEFAULT CHARSET=utf8mb4
   COLLATE=utf8mb4_unicode_ci
   COMMENT='角色菜单表';
   
   DROP TABLE IF EXISTS `sys_user_role`;
   
   CREATE TABLE `sys_user_role` (
   	user_id BIGINT(20) auto_increment NOT NULL COMMENT '用户ID',
   	role_id BIGINT(20) NOT NULL COMMENT '角色ID',
   	CONSTRAINT sys_user_role_PK PRIMARY KEY (user_id,role_id)
   )
   ENGINE=InnoDB
   DEFAULT CHARSET=utf8mb4
   COLLATE=utf8mb4_unicode_ci
   COMMENT='用户角色表';
   ```

2. 添加测试数据

   ```sql
   -- 添加角色数据
   INSERT INTO `sys_role` (role_name,role_key,state,create_by,create_time,update_by,update_time,deleted,remark) VALUES
   	 ('CEO','ceo',0,NULL,NULL,NULL,NULL,0,NULL),
   	 ('CODER','coder',0,NULL,NULL,NULL,NULL,0,NULL);
   
   -- 添加用户角色数据
   INSERT INTO `sys_user_role` (role_id) VALUES(1);
   
   -- 添加菜单数据
   INSERT INTO study.sys_menu (menu_name,menu_path,component_path,menu_visible,state,perms,icon,create_by,create_time,update_by,update_time,deleted,remark) VALUES
   	 ('添加用户','user','system/user/add',0,0,'system:user:add','#',NULL,NULL,NULL,NULL,0,NULL),
   	 ('测试','test','system/test',0,0,'system:test','#',NULL,NULL,NULL,NULL,0,NULL);
   
   -- 添加角色菜单数据
   INSERT INTO study.sys_role_menu (menu_id) VALUES(1);
   ```

3. 添加实体类和Mapper接口、Service接口、Service接口实现类

4. 编写通过用户id查询菜单sql

   ```java
   @Repository
   public interface MenuMapper extends BaseMapper<Menu> {
   
       /**
        * 根据用户id查询权限
        *
        * @param userId 用户id
        * @return {@link List}<{@link String}>
        */
       List<String> selectPermsByUserId(@Param("userId") Long userId);
   }
   ```

   ```xml
   <?xml version="1.0" encoding="UTF-8"?>
   <!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
   <mapper namespace="com.yu.security.mapper.MenuMapper">
   
       <select id="selectPermsByUserId" resultType="java.lang.String" parameterType="java.lang.Long">
           SELECT
               DISTINCT sm.perms
           FROM
               sys_user_role sur
           LEFT JOIN sys_role_menu srm ON
               sur.role_id = srm.role_id
           LEFT JOIN sys_menu sm ON
               sm.id = srm.menu_id
           WHERE
               sur.user_id = #{userId}
               AND sm.state = 0
       </select>
   </mapper>
   ```

5. 通过查询数据库获取菜单

   ```java
   @Service
   public class UserDetailsServiceImpl implements UserDetailsService {
       
       private final UserMapper userMapper;
   
       private final MenuMapper menuMapper;
   
       public UserDetailsServiceImpl(UserMapper userMapper, MenuMapper menuMapper) {
           this.userMapper = userMapper;
           this.menuMapper = menuMapper;
       }
   
       @Override
       public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
   
           // 根据用户名查询用户
           User user = userMapper.selectOne(new LambdaQueryWrapper<User>().eq(User::getUserName, username));
   //        if (Objects.isNull(user)) {
   //            throw new UsernameNotFoundException("该用户不存在!");
   //        }
           // TODO: 2023/7/8  校验权限
   //        List<String> permissions = Arrays.asList("test", "admin");
           List<String> permissions = menuMapper.selectPermsByUserId(user.getId());
           Set<String> permissionList = new HashSet<>(permissions);
   
           return new LoginUser(user, permissionList);
       }
   }
   ```

   ```java
   @RestController
   @RequestMapping("/token")
   public class HelloController {
   
       @GetMapping("/hello")
       @PreAuthorize("hasAuthority('system:user:add')")
       public String hello() {
           return "Hello Security";
       }
   }
   ```

## 五 自定义认证异常

在Spring Security中，如果在认证或者授权的过程中出现了异常会被`ExceptionTranslationFilter`过滤器捕获到。该过滤器会判断当前异常是认证异常还是授权异常。

下面是两个认证异常和授权异常相关的类介绍：

- **AuthenticationException：**认证过程中出现的异常会被封装成`AuthenticationException`，然后调用`AuthenticationEntryPoint`对象的方法进行异常处理
- **AccessDeniedException：**授权过程中出现的异常会被封装成`AccessDeniedException`，然后调用`AccessDeniedHandler`对象的方法进行异常处理

下面是一个简单的配置示例：

```java
/**
 * 认证异常处理
 */
@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) {
        // 定义异常返回信息
        ResponseResult<String> result = new ResponseResult<>(HttpStatus.UNAUTHORIZED.value(), "用户认证失败", "");

        // 将异常信息转为json字符串
        String json = JSON.toJSONString(result);

        // 通过流写出
        WebUtils.renderString(response, json);
    }
}
```

```java
/**
 * 授权异常处理
 */
@Component
public class AccessDeniedHandlerImpl implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) {
        // 定义异常返回信息
        ResponseResult<String> result = new ResponseResult<>(HttpStatus.FORBIDDEN.value(), "您的权限不足,请联系管理员", "");

        // 将异常信息转为json字符串
        String json = JSON.toJSONString(result);

        // 通过流写出
        WebUtils.renderString(response, json);
    }
}
```

```java
/**
 * security配置类
 */
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig implements WebMvcConfigurer {

    private final JwtFilter jwtFilter;

    public SecurityConfig(JwtFilter jwtFilter) {
        this.jwtFilter = jwtFilter;
    }

    /**
     * 将加密方式修改为BCryptPasswordEncoder
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * 注入认证管理器
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    /**
     * 认证异常处理
     */
    @Bean
    public AuthenticationEntryPoint authenticationEntryPoint() {
        return new AuthenticationEntryPointImpl();
    }

    /**
     * 授权异常处理
     */
    @Bean
    public AccessDeniedHandler accessDeniedHandler() {
        return new AccessDeniedHandlerImpl();
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                // 关闭csrf
                .csrf().disable()
                // 不通过Session获取SecurityContext
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                // 配置认证异常、授权异常处理
                .exceptionHandling().authenticationEntryPoint(authenticationEntryPoint()).accessDeniedHandler(accessDeniedHandler())
                .and()
                // 下面开始设置权限
                .authorizeRequests(authorize -> authorize
                        // 对于登录接口,允许匿名访问
                        .antMatchers("/user/login").anonymous()
                        // 其他地址的访问均需验证权限
                        .anyRequest().authenticated()
                )
                // 添加JWT过滤器,JWT过滤器在用户名密码认证过滤器之前
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }
}
```

## 六 跨域处理

```java
/**
 * security配置类
 */
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig implements WebMvcConfigurer {

    private final JwtFilter jwtFilter;

    public SecurityConfig(JwtFilter jwtFilter) {
        this.jwtFilter = jwtFilter;
    }

    /**
     * 将加密方式修改为BCryptPasswordEncoder
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * 注入认证管理器
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    /**
     * 认证异常处理
     */
    @Bean
    public AuthenticationEntryPoint authenticationEntryPoint() {
        return new AuthenticationEntryPointImpl();
    }

    /**
     * 授权异常处理
     */
    @Bean
    public AccessDeniedHandler accessDeniedHandler() {
        return new AccessDeniedHandlerImpl();
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                // 关闭csrf
                .csrf().disable()
                // 开启跨域访问
                .cors(Customizer.withDefaults())
                // 不通过Session获取SecurityContext
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                // 配置认证异常、授权异常处理
                .exceptionHandling().authenticationEntryPoint(authenticationEntryPoint()).accessDeniedHandler(accessDeniedHandler())
                .and()
                // 下面开始设置权限
                .authorizeRequests(authorize -> authorize
                        // 对于登录接口,允许匿名访问
                        .antMatchers("/user/login").anonymous()
                        // 其他地址的访问均需验证权限
                        .anyRequest().authenticated()
                )
                // 添加JWT过滤器,JWT过滤器在用户名密码认证过滤器之前
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    /**
     * 配置跨域访问
     */
    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        configuration.setAllowCredentials(true);
        configuration.addAllowedOrigin("*");
        configuration.addAllowedHeader(CorsConfiguration.ALL);
        configuration.addAllowedMethod(CorsConfiguration.ALL);
        configuration.setMaxAge(3600L);
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}

```

## 七 自定义权限校验

```java
/**
 * 自定义权限校验方法
 */
@Component("ex")
public class SecurityExpression {

    public boolean hasAuthority(String authString) {
        // 获取权限集合
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        Set<String> permissions = loginUser.getPermissions();

        // 判断字符串是否包含在权限集合中
        return permissions.contains(authString);
    }
}
```

```java
@RestController
@RequestMapping("/token")
public class HelloController {

    @GetMapping("/hello")
    @PreAuthorize("@ex.hasAuthority('system:user:add')")
    public String hello() {
        return "Hello Security";
    }
}
```

## 八 基于配置的权限校验

```java
@Bean
SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    return http
            // 关闭csrf
            .csrf().disable()
            // 开启跨域访问
            .cors(Customizer.withDefaults())
            // 不通过Session获取SecurityContext
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            // 配置认证异常、授权异常处理
            .exceptionHandling().authenticationEntryPoint(authenticationEntryPoint()).accessDeniedHandler(accessDeniedHandler())
            .and()
            // 下面开始设置权限
            .authorizeRequests(authorize -> authorize
                    // 对于登录接口,允许匿名访问
                    .antMatchers("/user/login").anonymous()
                    // 给某个接口指定权限才能访问
                    .antMatchers("/user/add").hasAuthority("system:user:add")
                    // 其他地址的访问均需验证权限
                    .anyRequest().authenticated()
            )
            // 添加JWT过滤器,JWT过滤器在用户名密码认证过滤器之前
            .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
            .build();
}
```