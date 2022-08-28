## 一、MyBatis简介

#### 1、MyBatis历史

MyBatis最初是Apache的一个开源项目**iBatis**，2010年6月这个项目由Apache Software Foundation迁移到了Google Code。随着开发团队转投Google Code旗下，`iBatis3.x`正式更名为MyBatis。代码于2013年11月迁移到GitHub。

iBatis一词来源于“Internet”和“abatis”的组合，是一个基于Java的持久层框架。iBatis提供的持久层框架包括SQL Maps和Data Access Objects(DAO)。

#### 2、MyBatis特性

1）MyBatis是支持定制化SQL、存储过程以及高级映射的优秀的**持久层框架**。

2）MyBatis避免了几乎所有的JDBC代码和手动设置参数以及获取结果集。

3）MyBatis可以使用简单的XML或注解用于配置和原始映射，将接口和Java的POJO(Plan Old Java Objects，普通的Java对象)映射成数据库中的记录。

4）MyBatis是一个`半自动`的ORM(Object Relation Mapping)框架。

#### 3、MyBatis下载

MyBatis下载地址：<https://github.com/mybatis/mybatis-3>

![image-20220707234023118](https://cdn.jsdelivr.net/gh/elon-lo/PicGo-Image@master/img/image-20220707234023118.png)

#### 4、和其他持久层技术对比

- JDBC
  - SQL夹杂在Java代码中，耦合度高，导致硬编码内伤
  - 维护不易且实际开发需求中SQL有变化，频繁修改的情况多见
  - 代码冗长，开发效率低

- Hibernate和JPA
  - 操作简单，开发效率高
  - 程序中的长难复杂SQL需要绕过框架
  - 内部自动生产的SQL，不容易做特殊优化
  - 基于全映射的全自动框架，大量字段的POJO进行部分映射的时候比较困难
  - 反射操作太多，导致数据库性能下降

- MyBatis
  - 轻量级，性能出色
  - SQL和Java编码分开，功能边界清晰，Java代码专注业务、SQL语句专注数据
  - 开发效率稍逊于Hibernate，但是完全能够接受


## 二、MyBatis框架搭建

#### 1、开发环境

IDE：idea 2021.3

构建工具：maven3.6.3

MySQL版本：mysql5.7

MyBatis版本：mybatis3.5.7

#### 2、创建Maven工程

- 创建空工程

  ![image-20220714061706637](https://cdn.jsdelivr.net/gh/elon-lo/PicGo-Image@master/img/image-20220714061706637.png)

  

- 重新加载项目(左侧Project不显示该项目请删除.idea重新进入idea)

  - 创建完后Project不显示项目

    ![image-20220714062104143](https://cdn.jsdelivr.net/gh/elon-lo/PicGo-Image@master/img/image-20220714062104143.png)

    

  - 删除.idea文件

    ![image-20220714061920702](https://cdn.jsdelivr.net/gh/elon-lo/PicGo-Image@master/img/image-20220714061920702.png)

    

  - 左侧Project恢复显示

    ![image-20220714062251919](https://cdn.jsdelivr.net/gh/elon-lo/PicGo-Image@master/img/image-20220714062251919.png)

    

    <span style="color:red;">注：删除idea文件后请重新配置一下项目jdk</span>

- 配置jdk版本

  ![image-20220714062138637](https://cdn.jsdelivr.net/gh/elon-lo/PicGo-Image@master/img/image-20220714062138637.png)

  

- 创建Maven工程

  - 创建模块Module

    ![image-20220714062618133](https://cdn.jsdelivr.net/gh/elon-lo/PicGo-Image@master/img/image-20220714062618133.png)

    

  - 选择Maven工程

    ![image-20220714062938843](https://cdn.jsdelivr.net/gh/elon-lo/PicGo-Image@master/img/image-20220714062938843.png)

    

  - 填写配置信息

    ![image-20220714065441870](https://cdn.jsdelivr.net/gh/elon-lo/PicGo-Image@master/img/image-20220714065441870.png)

    

    <span style="color:red;">注：如只有两个archetype请安装Maven Archetypes相关插件重启idea，或File→Close Project，关闭当前项目，再重新创建Maven项目</span>

- 修改打包方式和引入依赖信息
  
  ![image-20220715064506738](https://cdn.jsdelivr.net/gh/elon-lo/PicGo-Image@master/img/image-20220715064506738.png)

## 三、MyBatis基础功能

#### 1、创建MyBatis核心配置文件

习惯上命名为mybatis-config.xml，这个文件名仅仅只是建议，并非强制要求。将来整合Spring之后，这个配置文件可以省略。

核心配置文件主要用于配置连接数据库的环境以及MyBatis的全局配置信息。

核心配置文件存放的位置是src/main/resources目录下。

```xml-dtd
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE configuration
        PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-config.dtd">
<configuration>
    <!-- 配置连接数据库的环境 -->
    <environments default="development">
        <environment id="development">
            <transactionManager type="JDBC"/>
            <dataSource type="POOLED">
                <property name="driver" value="com.mysql.jdbc.Driver"/>
                <property name="url" value="jdbc:mysql://ip:3307/mybatis?useUnicode=true&amp;characterEncoding=UTF-8&amp;useSSL=false&amp;serverTimezone=Asia/Shanghai"/>
                <property name="username" value="root"/>
                <property name="password" value="123456"/>
            </dataSource>
        </environment>
    </environments>
    <!-- 引入映射文件 -->
    <mappers>
        <mapper resource="mapper/UserMapper.xml"/>
    </mappers>
</configuration>
```

#### 2、创建mapper接口

MyBatis中的mapper接口相当于以前的dao，但是区别自安于mapper仅仅是接口，不需要提供实现类。

```java
public interface UserMapper {

	/**
	 * 添加用户
	 *
	 * @return int  返回受影响的行数
	 */
	int insertUser();

	/**
	 * 更新用户
	 */
	void updateUser();

	/**
	 * 删除用户
	 */
	void deleteUser();

	/**
	 * 查询用户详情
	 *
	 * @return {@link User}
	 */
	User selectUserById();

	/**
	 * 查询用户列表
	 *
	 * @return {@link List}<{@link User}>
	 */
	List<User> selectList();
}
```

#### 3、创建MyBatis映射文件

相关概念：**ORM**(Oject Relationship Mapping)对象关系映射

- 对象：Java的实体类对象
- 关系：关系型数据库
- 映射：二者之间的对应关系

| Java概念 | 数据库概念 |
| -------- | ---------- |
| 类       | 表         |
| 属性     | 字段/列    |
| 对象     | 记录/行    |

1. 映射文件的命名规则：

   表所对应的实体类的类名+Mapper.xml

   例如：表t_user，映射的实体类为User，所对应的映射文件为UserMapper.xml

   因此一个映射文件对应一个实体类，对应一张表的操作

   MyBatis映射文件用于编写SQL，访问以及操作表中的数据MyBatis映射文件存放的位置是src/main/resources/mapper目录下

2. MyBatis中可以面向接口操作数据，但是要保证两个一致

   - mapper接口的全类名和映射文件的命名空间(namespace)保持一致
   - mapper接口中方法的方法名和映射文件中编写SQL的标签的id属性保持一致

3. 映射文件demo

   ```xml-dtd
   <?xml version="1.0" encoding="UTF-8" ?>
   <!DOCTYPE mapper
           PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
           "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
   <mapper namespace="com.yu.mybatis.mapper.UserMapper">
       <insert id="insertUser">
           insert into t_user values(null,"zs","123456",23,0,"zs@outlook.com");
       </insert>
   
       <update id="updateUser">
           update t_user set username = "张三" where id = 4
       </update>
   
       <delete id="deleteUser">
           delete from t_user where id = 5
       </delete>
   
       <select id="selectUserById" resultType="com.yu.mybatis.pojo.User">
           select username, password, age, sex, email from t_user where id = 4
       </select>
   
       <select id="selectList" resultType="com.yu.mybatis.pojo.User">
           select username, password, age, sex, email from t_user
       </select>
   </mapper>
   ```

4. resultType和resultMap区别

   - resultType：设置默认的映射关系(字段名与属性名一致)
   - resultMap：设置自定义的映射关系(字段名与属性名不一致)

   <font color='red'>注：查询功能的标签必须设置resultType或resultMap</font>

#### 4、通过junit测试功能

- 添加依赖

  ```java
  <!-- junit测试 -->
  <dependency>
      <groupId>junit</groupId>
      <artifactId>junit</artifactId>
      <version>4.12</version>
      <scope>test</scope>
  </dependency>
  ```

- 测试mybatis增删改查

  ```java
  public class MyBatisTest {
  	/**
  	 * 测试添加用户
  	 */
  	@Test
  	public void testAdd() throws IOException {
  		// 加载核心配置文件
  		InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
  		// 创建SqlSessionFactoryBuilder对象
  		SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
  		// 通过核心配置文件所对应的字节输入流创建工厂类sqlSessionFactory,生产sqlSession对象
  		SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBuilder.build(is);
  		// 创建sqlSession对象,此时通过sqlSession对象所操作的sql都必须手动提交或回滚事务
  		SqlSession sqlSession = sqlSessionFactory.openSession(true);
  		// 通过代理模式创建UserMapper接口的代理实现类对象
  		UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
  		// 调用UserMapper接口中的方法,就可以根据UserMapper的全类名匹配元素文件,通过调用的方法名匹配映射文件中的SQL标签,并执行标签中的SQL语句
  		int result = userMapper.insertUser();
  		// 手动提交事务
  //		sqlSession.commit();
  		System.out.println("result: " + result);
  	}
  
  	@Test
  	public void testCRUD() throws IOException {
  		InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
  		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
  		SqlSession sqlSession = sqlSessionFactory.openSession(true);
  		UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
  		// 更新用户
  //		userMapper.updateUser();
  		// 删除用户
  //		userMapper.deleteUser();
  		// 查询用户详情
  //		User user = userMapper.selectUserById();
  		// 查询所有用户
  		List<User> userList = userMapper.selectList();
  		userList.forEach(System.out::println);
  	}
  }
  ```

  sqlSession：代表Java程序和数据库之间的会话。(HttpSession是Java程序与浏览器之间的会话)

  sqlSessionFactory：是生产"sqlSeesion"的工厂

  工厂模式：如果创建某一个对象，使用的过程基本稳定，那么我们就可以把创建这个对象的相关代码封装到一个"工厂类"中，以后都实现这个工厂类来"生产"我们需要的对象。

  <font color='red'>注：sqlSession默认不提交事务，可通过sqlSessionFactory.openSession(true)设置自动提交事务</font>

#### 5、加入log4j日志功能

- 添加依赖

  ```java
  <!-- log4j -->
  <dependency>
      <groupId>log4j</groupId>
      <artifactId>log4j</artifactId>
      <version>1.2.17</version>
  </dependency>
  ```

- 加入log4j的配置文件

  log4j的配置文件名为log4j.xml，存放的位置是src/main/resources目录下

  ```xml-dtd
  <?xml version="1.0" encoding="UTF-8"?>
  <!DOCTYPE log4j:configuration SYSTEM "http://logging.apache.org/log4j/1.2/apidocs/org/apache/log4j/xml/doc-files/log4j.dtd">
  
  <log4j:configuration debug="true">
  
      <appender name="STDOUT" class="org.apache.log4j.ConsoleAppender">
          <layout class="org.apache.log4j.PatternLayout">
              <param name="ConversionPattern"
                     value="%-5p %d{dd HH:mm:ss:SSS\} %m (%F:%L) \n" />
          </layout>
      </appender>
      
      <logger name="java.sql">
          <level value="debug" />
      </logger>
  
      <logger name="org.apache.ibatis">
          <level value="info" />
      </logger>
  
      <root>
          <level value="debug" />
          <appender-ref ref="STDOUT" />
      </root>
  </log4j:configuration>
  ```

  日志的级别：FATAL(致命)→ERROR(错误)→WARN(警告)→INFO(信息)→DEBUG(调试)

  从左到右打印的日志越来越详细

#### 6、MyBatis实现增删改查

**添加**

```xml-dtd
<insert id="insertUser">
    insert into t_user values(null,"zs","123456",23,0,"zs@outlook.com");
</insert>
```

**修改**

```xml-dtd
<update id="updateUser">
    update t_user set username = "张三" where id = 4
</update>
```

**删除**

```xml-dtd
<delete id="deleteUser">
    delete from t_user where id = 5
</delete>
```

**查询一个实体类对象**

```xml-dtd
<select id="selectUserById" resultType="com.yu.mybatis.pojo.User">
    select username, password, age, sex, email from t_user where id = 4
</select>
```

**查询集合**

```xml-dtd
<select id="selectList" resultType="com.yu.mybatis.pojo.User">
    select username, password, age, sex, email from t_user
</select>
```

#### 7、idea设置配置文件模板

- 添加`mybatis-config`模板

  ![image-20220814215459667](https://cdn.jsdelivr.net/gh/elon-lo/PicGo-Image@master/img/image-20220814215459667.png)

  ![image-20220814215713648](https://cdn.jsdelivr.net/gh/elon-lo/PicGo-Image@master/img/image-20220814215713648.png)

  ![image-20220814220050428](https://cdn.jsdelivr.net/gh/elon-lo/PicGo-Image@master/img/image-20220814220050428.png)

- 添加`mapper`模板

  同上。

#### 8、MyBatis获取参数值的两种方式

mybatis获取参数值的两种方式`${}`和`#{}`，`${}`本质是字符串拼接，`#{}`本质是占位符赋值

##### 8.1、单个字面量类型的参数

- #{}

  ```xml
  <select id="getUserByUsername" resultType="com.yu.mybatis.pojo.User">
  	select username, password, age, sex, email from t_user where username = #{username}
  </select>
  ```

  ![image-20220814233950610](https://cdn.jsdelivr.net/gh/elon-lo/PicGo-Image@master/img/image-20220814233950610.png)

- ${}

  ```xml
  <select id="getUserByUsername" resultType="com.yu.mybatis.pojo.User">
  	select username, password, age, sex, email from t_user where username = '${username}'
  </select>
  ```

  ![image-20220814234239982](https://cdn.jsdelivr.net/gh/elon-lo/PicGo-Image@master/img/image-20220814234239982.png)

##### 8.2、多个字面量类型的参数

mapper接口的参数为**多个**时，mybatis会将这些参数放在一个`map集合`中

多个参数或者对象类型参数的时候使用要**遵循以下规则**：

使用特定的参数名处理，mybatis的默认参数名有两种，一种是`arg(下标从0开始)`，另一种是`param(下标从1开始)`，可两种结合一起使用，但是要注意顺序

```xml
<select id="checkLogin" resultType="user">
	select username, password, age, sex, email from t_user where username = #{arg0} and password=#{arg1}
</select>

<select id="checkLogin" resultType="user">
	select username, password, age, sex, email from t_user where username = #{param1} and password=#{param2}
</select>

<select id="checkLogin" resultType="user">
	select username, password, age, sex, email from t_user where username = #{arg0} and password=#{param2}
</select>

<select id="checkLogin" resultType="user">
	select username, password, age, sex, email from t_user where username = #{param1} and password=#{arg1}
</select>
```

##### 8.3、map集合类型的参数

```java
public interface ParameterMapper {
	User checkLoginByMap(Map<String, Object> map);
}
```

```xml
<select id="checkLoginByMap" resultType="com.yu.mybatis.pojo.User">
	select username, password, age, sex, email from t_user where username = #{username} and password=#{password}
</select>
```

```java
@Test
public void testCheckLoginByMap() {
    Map<String,Object> map = new LinkedHashMap<>(16);
    SqlSession sqlSession = SqlSessionUtil.getSqlSession();
    ParameterMapper mapper = sqlSession.getMapper(ParameterMapper.class);
    map.put("username","zs");
    map.put("password","123456");
    User user = mapper.checkLoginByMap(map);
    System.out.println(user);
}
```

##### 8.4、实体类类型的参数

```java
public interface ParameterMapper {
	int insertParamUser(User user);
}
```

```xml
<insert id="insertParamUser">
    insert into t_user values(null,#{username},#{password},#{age},#{sex},#{email})
</insert>
```

```java
@Test
public void testInsertParamUser() {
    SqlSession sqlSession = SqlSessionUtil.getSqlSession();
    ParameterMapper mapper = sqlSession.getMapper(ParameterMapper.class);
    int i = mapper.insertParamUser(new User(null,"aaa","123",18,1,"123@qq.com"));
    System.out.println(i);
}
```

##### 8.5、使用@param注解标识参数

```java
public interface ParameterMapper {
	User checkLoginByParam(@Param("name") String username, @Param("passwd") String password);
}
```

```xml
<select id="checkLoginByParam" resultType="com.yu.mybatis.pojo.User">
    select username, password, age, sex, email from t_user where username = #{name} and password=#{passwd}
</select>
```

```java
@Test
public void testCheckLoginByParam() {
    SqlSession sqlSession = SqlSessionUtil.getSqlSession();
    ParameterMapper mapper = sqlSession.getMapper(ParameterMapper.class);
    User user = mapper.checkLoginByParam("zs","123456");
    System.out.println(user);
}
```

<font color='red'>注：如接口中的方法参数未用@param注解指定，则xml中sql的参数名可以是任意名称；由于${}方式是字符串拼接，所以需要添加引号</font>

#### 9、MyBatis各种查询功能

mybatis中设置了默认的类型别名：

java.lang.Integer--->int，integer

int--->`_int`，`_integer`

Map--->map

String--->string

##### 9.1、查询一个实体类对象

```java
public interface SelectMapper {
	/**
	 * 根据id查询用户详情
	 * 返回值可以用实体类对象或者集合类型接收
	 *
	 * @param id id
	 * @return {@link User}
	 */
	User getUserById(@Param("id") Integer id);
//	List<User> getUserById(@Param("id") Integer id);
}
```

```xml
<select id="getUserById" resultType="com.yu.mybatis.pojo.User">
    select username, password, age, sex, email from t_user where id = #{id}
</select>
```

```java
public class SelectMapperTest {
    @Test
    public void testGetUserById() {
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        SelectMapper mapper = sqlSession.getMapper(SelectMapper.class);
        System.out.println(mapper.getUserById(4));
    }
}
```

##### 9.2、查询一个list集合

```java
public interface SelectMapper {
	/**
	 * 查询所有用户
	 * 返回值只能使用集合类型接收
	 *
	 * @return {@link List}<{@link User}>
	 */
	Set<User> getAllUser();
}
```

```xml
<select id="getAllUser" resultType="com.yu.mybatis.pojo.User">
    select username, password, age, sex, email from t_user
</select>
```

```java
public class SelectMapperTest {
    @Test
	public void testGetAllUser() {
		SqlSession sqlSession = SqlSessionUtil.getSqlSession();
		SelectMapper mapper = sqlSession.getMapper(SelectMapper.class);
		System.out.println(mapper.getAllUser());
	}
}
```

##### 9.3、查询单个数据

```java
public interface SelectMapper {
	Integer getCount();
}
```

```xml
<select id="getCount" resultType="java.lang.Integer">
    select count(*) from t_user
</select>
```

```java
public class SelectMapperTest {
    @Test
	public void testGetCount() {
		SqlSession sqlSession = SqlSessionUtil.getSqlSession();
		SelectMapper mapper = sqlSession.getMapper(SelectMapper.class);
		System.out.println(mapper.getCount());
	}
}
```

##### 9.4、查询一条数据为map集合

```java
public interface SelectMapper {
	/**
	 * 根据id查询用户详情(Map类型)
	 *
	 * @param id id
	 * @return {@link Map}<{@link String}, {@link Object}>
	 */
	Map<String, Object> getUserByIdToMap(@Param("id") Integer id);
}
```

```xml
<select id="getUserByIdToMap" resultType="map">
    select username, password, age, sex, email from t_user where id = #{id}
</select>
```

```java
public class SelectMapperTest {
    @Test
	public void testGetUserByIdToMap() {
		SqlSession sqlSession = SqlSessionUtil.getSqlSession();
		SelectMapper mapper = sqlSession.getMapper(SelectMapper.class);
		System.out.println(mapper.getUserByIdToMap(4));
	}
}
```

##### 9.5、查询多条数据为map集合

```java
public interface SelectMapper {
	/**
	 * 查询所有用户
	 * 1、可以使用map类型的集合接收
	 * 2、可以使用@MapKey注解,此时可以将每条数据转换为map集合的值,以某个字段为键,放在同一个map中
	 *
	 * @return {@link Map}<{@link String}, {@link Object}>
	 */
//	List<Map<String, Object>> getAllUserToMap();
	
	@MapKey("username")
	Map<String, Object> getAllUserToMap();
}
```

```xml
<select id="getAllUserToMap" resultType="java.util.Map">
    select username, password, age, sex, email from t_user
</select>
```

```java
public class SelectMapperTest {
    @Test
	public void testGetAllUserToMap() {
		SqlSession sqlSession = SqlSessionUtil.getSqlSession();
		SelectMapper mapper = sqlSession.getMapper(SelectMapper.class);
		System.out.println(mapper.getAllUserToMap());
	}
}
```

## 四、特殊SQL的执行

#### 1、模糊查询

```java
public interface SQLMapper {

	/**
	 * 根据用户名模糊查询用户
	 *
	 * @param username 用户名
	 * @return {@link List}<{@link User}>
	 */
	List<User> getUserByLike(@Param("username") String username);
}
```

模糊查询的三种方式：

- 使用${}拼接

  ```xml
  <select id="getUserByLike" resultType="com.yu.mybatis.pojo.User">
      select username, password, age, sex, email from t_user where username like '%${username}%'
  </select>
  ```

- 使用concat拼接

  ```xml
  <select id="getUserByLike" resultType="com.yu.mybatis.pojo.User">
      select username, password, age, sex, email from t_user where username like concat('%',#{username},'%')
  </select>
  ```

- 直接使用%拼接

  ```xml
  <select id="getUserByLike" resultType="com.yu.mybatis.pojo.User">
      select username, password, age, sex, email from t_user where username like '%'#{username}'%'
  </select>
  ```

```java
public class SQLMapperTest {

	@Test
	public void testGetUserByLike() {
		SqlSession sqlSession = SqlSessionUtil.getSqlSession();
		SQLMapper mapper = sqlSession.getMapper(SQLMapper.class);
		List<User> userList = mapper.getUserByLike("李");
		System.out.println(userList);
	}
}
```

#### 2、批量删除

```java
public interface SQLMapper {

	/**
	 * 批量删除
	 *
	 * @param ids id
	 * @return int
	 */
	int deleteMore(@Param("ids") String ids);
}
```

```xml
<delete id="deleteMore">
    delete from t_user where id in (${ids})
</delete>
```

```java
public class SQLMapperTest {
	@Test
	public void testDeleteMore() {
		SqlSession sqlSession = SqlSessionUtil.getSqlSession();
		SQLMapper mapper = sqlSession.getMapper(SQLMapper.class);
		int i = mapper.deleteMore("3,4,5");
		System.out.println(i);
	}
}
```

#### 3、动态设置表名

```java
public interface SQLMapper {

	/**
	 * 动态设置表名
	 *
	 * @param tableName 表名
	 * @return {@link List}<{@link User}>
	 */
	List<User> getAllUser(@Param("tableName") String tableName);
}
```

```xml
<select id="getAllUser" resultType="com.yu.mybatis.pojo.User">
    select username, password, age, sex, email from ${tableName}
</select>
```

```java
public class SQLMapperTest {
	@Test
	public void testGetAllUser() {
		SqlSession sqlSession = SqlSessionUtil.getSqlSession();
		SQLMapper mapper = sqlSession.getMapper(SQLMapper.class);
		List<User> userList = mapper.getAllUser("t_user");
		System.out.println(userList);
	}
}
```

#### 4、添加功能获取自增的主键

```java
public interface SQLMapper {

	/**
	 * 添加用户
	 *
	 * @param user 用户
	 * @return int
	 */
	int insertUser(User user);
}
```

```xml
<!--
    useGeneratedKeys:设置使用自增的主键
    keyProperty:因为增删改有统一的返回值，是受影响的行数，因此只能将获取的自增主键放在传输参数user的某个属性中
-->
<insert id="insertUser" useGeneratedKeys="true" keyProperty="id">
    insert into t_user values(null, #{username}, #{password}, #{age}, #{sex}, #{email})
</insert>
```

```java
public class SQLMapperTest {
	@Test
	public void testInsertUser() {
		SqlSession sqlSession = SqlSessionUtil.getSqlSession();
		SQLMapper mapper = sqlSession.getMapper(SQLMapper.class);
		User user = new User(null, "aaa", "bbb", 10, 0, "aaa@163.com");
		mapper.insertUser(user);
		System.out.println(user);
	}
}
```

## 五、自定义映射resultMap

**表关系**：

employee(eid,emp_name,age,sex,email,did)

department(did,dept_name)

**实体类对象及属性**：

```java
public class Employee implements Serializable {

	private static final long serialVersionUID = 6670503963015924385L;

	private long eid;

	private String empName;

	private Integer age;

	private Integer sex;

	private String email;

	private Long did;
}
```

```java
public class Department implements Serializable {

	private static final long serialVersionUID = -6647588758966575564L;

	private Long did;

	private String deptName;
}
```

<font color='red'>注：一对多和多对一的时候都要将表关系设置在多的一方</font>

#### 1、表名和字段名不一致的解决方案

- 给字段名设置别名

  ```xml
  <select id="getAllEmployee" resultType="com.yu.mybatis.pojo.Employee">
      select emp_name empName, age, sex, email, did from employee
  </select>
  ```

- mybatis-config.xml中设置全局配置

  ```xml
  <!-- 全局配置 -->
  <settings>
      <!-- 将下划线自动映射为驼峰,如emp_name:empName -->
      <setting name="mapUnderscoreToCamelCase" value="true"/>
  </settings>
  ```

- 使用resultMap映射

  ```xml
  <!--
      resultMap:设置自定义映射关系
      id:唯一标识,不能重复
      type:设置映射关系中的实体类类型
      子标签:
      id:设置主键的映射关系
      result:设置普通字段的映射关系
      属性:
      property:设置映射关系中的属性名,必须是type属性所设置的实体类中的属性名
      column:设置映射关系中的字段名,必须是sql语句查询出的字段名
  -->
  <resultMap id="empResultMap" type="com.yu.mybatis.pojo.Employee">
      <id property="eid" column="eid"/>
      <result property="empName" column="emp_name"/>
      <result property="age" column="age"/>
      <result property="sex" column="sex"/>
      <result property="email" column="email"/>
      <result property="did" column="did"/>
  </resultMap>
  
  <select id="getAllEmployee" resultMap="empResultMap">
      select emp_name, age, sex, email from employee
  </select>
  ```

​		<font color='red'>注：属性名和字段名一致的可以不设置，但是建议最好还是设置</font>

#### 2、多对一设置

- 级联属性赋值

  ```java
  public class Employee implements Serializable {
  
  	private static final long serialVersionUID = 1062872748857276934L;
  
  	private Long eid;
  
  	private String empName;
  
  	private Integer age;
  
  	private Integer sex;
  
  	private String email;
  
  	private Long did;
  
  	private Department department;
  }
  // 省略getter、setter、tostring...
  ```

  ```java
  public interface EmployeeMapper {
  
  	/**
  	 * 根据员工id查询员工信息及员工所在的部门信息
  	 *
  	 * @param eid 员工id
  	 * @return {@link Employee}
  	 */
  	Employee getEmployeeAndDepartmentByEid(@Param("eid") Long eid);
  }
  ```

  ```xml
  <!-- 处理多对一映射关系 -->
  <resultMap id="empAndDeptResultMapOne" type="Employee">
      <id property="eid" column="eid"/>
      <result property="empName" column="emp_name"/>
      <result property="age" column="age"/>
      <result property="sex" column="sex"/>
      <result property="email" column="email"/>
      <result property="department.did" column="did"/>
      <result property="department.deptName" column="dept_name"/>
  </resultMap>
  
  <select id="getEmployeeAndDepartmentByEid" resultMap="empAndDeptResultMapOne">
      select e.eid, e.emp_name, e.age, e.sex, e.email, d.did, d.dept_name
      from employee e
      left join department d on e.did = d.did
      where e.eid = #{eid}
  </select>
  ```

  ```java
  public class EmployeeMapperTest {
  
  	@Test
  	public void testGetEmployeeAndDepartmentByEid() {
  		SqlSession sqlSession = SqlSessionUtil.getSqlSession();
  		EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
  		Employee employee = mapper.getEmployeeAndDepartmentByEid(1L);
  		System.out.println(employee);
  	}
  }
  ```

- 使用association标签处理

  ```xml
  <resultMap id="empAndDeptResultMapTwo" type="Employee">
      <id property="eid" column="eid"/>
      <result property="empName" column="emp_name"/>
      <result property="age" column="age"/>
      <result property="sex" column="sex"/>
      <result property="email" column="email"/>
      <!--
  		association:处理多对一映射关系
  		属性:
           property:需要处理多对一映射关系的属性名
           javaType:该属性的类型
       -->
      <association property="department" javaType="Department">
          <id property="did" column="did"/>
          <result property="deptName" column="dept_name"/>
      </association>
  </resultMap>
  ```

- 分步查询

  ```java
  public interface EmployeeMapper {
  
  	/**
  	 * 分步查询
  	 * 第一步:通过eid查询员工的信息
  	 *
  	 * @param eid 员工id
  	 * @return {@link Employee}
  	 */
  	Employee getEmployeeAndDepartmentByStepOne(@Param("eid") Long eid);
  }
  ```
  
  ```java
  public interface DepartmentMapper {
  
  	/**
  	 * 分步查询
  	 * 第二步:通过did查询员工所在的部门信息
  	 *
  	 * @param did 部门id
  	 * @return {@link Department}
  	 */
  	Department getEmployeeAndDepartmentByStepTwo(@Param("did") Long did);
  }
  ```
  
  ```xml
  <resultMap id="empAndDeptResultMapStep" type="Employee">
      <id property="eid" column="eid"/>
      <result property="empName" column="emp_name"/>
      <result property="age" column="age"/>
      <result property="sex" column="sex"/>
      <result property="email" column="email"/>
      <!--
          property:需要处理的实体类属性
          javaType:属性的类型
          select:设置分步查询的sql唯一标识(mapper接口方法的全类名)
          column:设置分步查询的条件
       -->
      <association property="department"
                       javaType="Department"
                       select="com.yu.mybatis.mapper.DepartmentMapper.getEmployeeAndDepartmentByStepTwo"
                       column="did" />
  </resultMap>
  
  <select id="getEmployeeAndDepartmentByStepOne" resultMap="empAndDeptResultMapStep">
      select emp_name, age, sex, email, did from employee where eid = #{eid}
  </select>
  ```
  
  ```xml
  <setting name="mapUnderscoreToCamelCase" value="true"/>
  ```
  
  ```xml
  <!-- 设置了全局配置,所以不用配置表名和字段名之间的映射 -->
  <select id="getEmployeeAndDepartmentByStepTwo" resultType="com.yu.mybatis.pojo.Department">
      select dept_name from department where did = #{did}
  </select>
  ```

#### 3、延迟加载

mybatis的延迟加载就是**按需查询**，在需要的时候进行查询。需要在核心配置文件中设置全局配置信息；

lazyLoadingEnabled：延迟加载的全局开关。当开启时，所有关联对象都会延迟加载；

aggressiveLazyLoading：当开启时，任何方法的调用都会加载该对象的属性，否则，每个属性会按需加载；

```xml
<settings>
    <!-- 配置延迟加载 -->
    <setting name="lazyLoadingEnabled" value="true"/>

    <!-- 配置按需加载,注意此属性在3.4.1版本之前默认值是true,之后是false -->
    <setting name="aggressiveLazyLoading" value="false"/>
</settings>
```

此时就可以实现按需加载，获取的数据是什么，就只会执行相应的sql。此时可以通过association和collection中的fetchType属性设置当前的分步查询是否使用延迟加载，fetchType="lazy(延迟加载)|eager(立即加载)"

```xml
<!-- 
	fetchType="lazy|eager"	lazy:延迟加载 eager:立即加载
-->
<association property="department"
                     javaType="Department"
                     select="com.yu.mybatis.mapper.DepartmentMapper.getEmployeeAndDepartmentByStepTwo"
                     fetchType="lazy"
                     column="did" />
```

#### 4、一对多设置

- 使用collection标签查询

  ```java
  public class Department implements Serializable {
  
  	private static final long serialVersionUID = 9097566192807688762L;
  
  	private Long did;
  
  	private String deptName;
  	
  	private List<Employee> empList;
  }
  // 省略getter、setter、tostring...
  ```

  ```java
  public interface DepartmentMapper {
  
  	/**
  	 * 查询部门以及部门下面的所有员工信息
  	 *
  	 * @param did 部门id
  	 * @return {@link Department}
  	 */
  	Department getDepartmentAndEmployeeByDid(@Param("did") Long did);
  }
  ```

  ```xml
  <resultMap id="deptAndEmpResultMap" type="Department">
      <id property="did" column="did" />
      <result property="deptName" column="dept_name" />
      <!--
          collection:一对多设置
          属性:
              property:需要处理的集合属性名
              ofType:集合中具体处理的类型
       -->
      <collection property="empList" ofType="Employee">
          <id property="eid" column="eid" />
          <result property="empName" column="emp_name" />
          <result property="age" column="age" />
          <result property="sex" column="sex" />
          <result property="email" column="email" />
      </collection>
  </resultMap>
  
  <select id="getDepartmentAndEmployeeByDid" resultMap="deptAndEmpResultMap">
      select e.eid, e.emp_name, e.age, e.sex, e.email, d.did, d.dept_name
      from department d
      left join employee e on d.did = e.did
      where d.did = #{did}
  </select>
  ```

- 使用分步查询

  ```java
  public interface DepartmentMapper {
  
  	/**
  	 * 分步查询
  	 * 第一步:通过did查询部门信息
  	 *
  	 * @param did 部门id
  	 * @return {@link Department}
  	 */
  	Department getDepartmentAndEmployeeByStepOne(@Param("did") Long did);
  }
  ```

  ```java
  public interface EmployeeMapper {
  
  	/**
  	 * 分步查询
  	 * 第二步:通过did查询部门下的所有员工信息
  	 *
  	 * @param did 部门id
  	 * @return {@link List}<{@link Employee}>
  	 */
  	List<Employee> getDepartmentAndEmployeeByStepTwo(@Param("did") Long did);
  }
  ```

  ```xml
  <resultMap id="departmentAndEmployeeByStepResultMap" type="Department">
      <id property="did" column="did" />
      <result property="deptName" column="dept_name" />
      <collection property="empList"
                  select="com.yu.mybatis.mapper.EmployeeMapper.getDepartmentAndEmployeeByStepTwo"
                  fetchType="lazy"
                  column="did" />
  </resultMap>
  
  <select id="getDepartmentAndEmployeeByStepOne" resultMap="departmentAndEmployeeByStepResultMap">
      select did, dept_name from department where did = #{did}
  </select>
  ```

  ```xml
  <select id="getDepartmentAndEmployeeByStepTwo" resultType="com.yu.mybatis.pojo.Employee">
      select emp_name, age, sex, email from employee where did = #{did}
  </select>
  ```

<font color='red'>*注：一对多查询的时候一定要查询主键信息(关联关系)，mybatis才能正确映射一对多关系，否则会抛出TooManyResultsException异常*</font>

## 六、动态SQL

MyBatis框架的动态SQL技术是一种根据特定条件动态拼接SQL语句的功能，它存在的意义是为了解决拼接SQL语句字符串时的痛点问题。

#### 1、if标签

根据标签中test属性所对应的表达式决定标签中的内容是否需要拼接到SQL中

```java
public interface DynamicSQLMapper {
	
	/**
	 * 动态SQL:多条件查询
	 *
	 * @param employee 查询条件
	 * @return {@link List}<{@link Employee}>
	 */
	List<Employee> getEmployeeByCondition(Employee employee);
}
```

```xml
<select id="getEmployeeByCondition" resultType="com.yu.mybatis.pojo.Employee">
    select emp_name, age, sex, email from employee where 1=1
    <if test="empName != null and empName != ''">
        and emp_name = #{empName}
    </if>
    <if test="age != null">
        and age = #{age}
    </if>
    <if test="sex != null">
        and sex = #{sex}
    </if>
    <if test="email != null and email != ''">
        and email = #{email}
    </if>
</select>
```

<font color='red'>注意：</font>

<font color='red'>1：`1=1`恒成立条件可以避免因第一个if判断不成立导致出现where and错误</font>

<font color='red'>2：只有字符串类型才需要`!=''`判断，其他类型一般来说只需要判断`!=null`</font>

#### 2、where标签

当where标签中有内容时，会自动生成where关键字，并且将内容前多余的and或or去掉，避免出现where and类型情况；

当where标签中没有内容时，不会自动生成where关键字，此时where标签没有任何效果

```xml
<select id="getEmployeeByCondition" resultType="com.yu.mybatis.pojo.Employee">
    select emp_name, age, sex, email from employee
    <where>
        <if test="empName != null and empName != ''">
            and emp_name = #{empName}
        </if>
        <if test="age != null">
            and age = #{age}
        </if>
        <if test="sex != null">
            and sex = #{sex}
        </if>
        <if test="email != null and email != ''">
            and email = #{email}
        </if>
    </where>
</select>
```

<font color='red'>注：where标签不能将条件内容之后多余的and或or去掉</font>

#### 3、trim标签

若标签中有内容时：

prefix|suffix：将trim标签中内容前后添加指定内容

prefixOverrides|suffixOverrides：将trim标签中内容前后去掉指定内容

若标签中没有内容时，trim标签也没有任何效果

```xml
<select id="getEmployeeByCondition" resultType="com.yu.mybatis.pojo.Employee">
    select emp_name, age, sex, email from employee
    <trim prefix="where" suffixOverrides="and|or">
        <if test="empName != null and empName != ''">
            emp_name = #{empName} and
        </if>
        <if test="age != null">
            age = #{age} or
        </if>
        <if test="sex != null">
            and sex = #{sex}
        </if>
        <if test="email != null and email != ''">
            and email = #{email}
        </if>
    </trim>
</select>
```

#### 4、choose、when、otherwise标签

相当于if...else if...else

when至少要有一个，otherwise最多只能有一个

```java
public interface DynamicSQLMapper {

	/**
	 * 动态SQL:测试choose
	 *
	 * @param employee 查询条件
	 * @return {@link List}<{@link Employee}>
	 */
	List<Employee> getEmployeeByChoose(Employee employee);
}
```

```xml
<select id="getEmployeeByChoose" resultType="com.yu.mybatis.pojo.Employee">
    select emp_name, age, sex, email from employee
    <where>
        <choose>
            <when test="empName != null and empName != ''">
                emp_name = #{empName}
            </when>
            <when test="age != null">
                age = #{age}
            </when>
            <when test="sex != null">
                sex = #{sex}
            </when>
            <when test="email != null and email != ''">
                email = #{email}
            </when>
            <otherwise>
                did = 1
            </otherwise>
        </choose>
    </where>
</select>
```

#### 5、foreach标签

collection：设置需要循环的数组或集合

item：表示数组或集合中的每一个元素

separator：循环体之间的分隔符

open：foreach标签所循环的所有内容的开始符

close：foreach标签所循环的所有内容的结束符

- 数组方式

  ```java
  public interface DynamicSQLMapper {
  
      /**
  	 * 通过数组方式批量删除员工
  	 *
  	 * @param eIds 员工id数组
  	 * @return int
  	 */
      int deleteMoreEmployeeByArray(@Param("eIds") Long[] eIds);
  }
  ```

  - 使用in关键字

    ```xml
    <delete id="deleteMoreEmployeeByArray">
        delete from employee where eid in
        <foreach collection="eIds" item="id" open="(" close=")" separator=",">
            #{id}
        </foreach>
    </delete>
    ```

  - 使用or关键字

    ```xml
    <delete id="deleteMoreEmployeeByArray">
        delete from employee
        <where>
            <foreach collection="eIds" item="id"  separator="or">
                eid = #{id}
            </foreach>
        </where>
    </delete>
    ```

- 集合方式

  ```java
  public interface DynamicSQLMapper {
  
      /**
  	 * 通过集合方式批量添加员工信息
  	 *
  	 * @param empList 员工信息集合
  	 * @return int
  	 */
      int insertMoreEmployeeByCollection(@Param("empList") List<Employee> empList);
  }
  ```

  ```xml
  <insert id="insertMoreEmployeeByCollection">
      insert into employee values
      <foreach collection="empList" item="emp" separator=",">
          (#{emp.eid}, #{emp.empName}, #{emp.age}, #{emp.sex}, #{emp.email}, #{emp.did})
      </foreach>
  </insert>
  ```

#### 6、sql标签

复用重复的sql语句

```xml
<sql id="empColumns">
    emp_name, age, sex, email
</sql>

<select id="getEmployeeByCondition" resultType="com.yu.mybatis.pojo.Employee">
    select <include refid="empColumns" /> from employee
    <trim prefix="where" suffixOverrides="and|or">
        <if test="empName != null and empName != ''">
            emp_name = #{empName} and
        </if>
        <if test="age != null">
            age = #{age} or
        </if>
        <if test="sex != null">
            and sex = #{sex}
        </if>
        <if test="email != null and email != ''">
            and email = #{email}
        </if>
    </trim>
</select>
```

```xml
<sql id="empResult">
    select emp_name, age, sex, email from employee where emp_name = '张'
</sql>

<select id="getEmployeeByCondition" resultType="com.yu.mybatis.pojo.Employee">
    <include refid="empResult" />
    <if test="empName != null and empName != ''">
        and emp_name = #{empName}
    </if>
    <if test="age != null">
        and age = #{age}
    </if>
    <if test="sex != null">
        and sex = #{sex}
    </if>
    <if test="email != null and email != ''">
        and email = #{email}
    </if>
</select>
```

*<font color='red'>注：mybatis使用数组或集合作为参数时，建议使用@param注解，否则需要按照特有的方式传参</font>*

## 七、核心配置文件详解

<span style="color:red;">核心配置文件中的标签必须严格按照以下固定的顺序编写：</span>

- configuration

  - properties

  - settings
- typeAliases
  - typeHandlers
- objectFactory
  - plugins
- environments
  
  - environment
  
      - transactionManager
  
      - dataSource
  - databaseIdProvider
- mappers

核心配置文件示例：

```xml-dtd
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE configuration
        PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-config.dtd">
<configuration>
    <!-- 引入properties文件,此时就可以使用${属性名}的方式访问属性值 -->
    <properties resource="jdbc.properties"/>

    <settings>
        <!-- 将表中字段的下划线自动转为驼峰 -->
        <setting name="mapUnderscoreToCamelCase" value="true"/>
        <!-- 开启延迟加载 -->
        <setting name="lazyLoadingEnabled" value="true"/>
    </settings>

    <!-- 设置类型别名 -->
    <typeAliases>
        <!--
            typeAlias:设置某个类型的别名
            属性:
                type:设置需要设置别名的类型
                alias:设置某个类型的别名,若不设置该属性,那么该类型将拥有默认的别名,即类名且不区分大小写
         -->
<!--        <typeAlias type="com.yu.mybatis.pojo.User" alias="User"/>-->

        <!-- 以包为单位,将包下所有的类型设置默认的别名,即类名且不区分大小写 -->
        <package name="com.yu.mybatis.pojo"/>
    </typeAliases>
    
    <!--
        environments:配置多个连接数据库的环境
        属性:
            default:设置默认使用的环境的id
    -->
    <environments default="development">
        <!--
            environment:配置某个具体的环境
            属性:
                id:表示连接数据库的环境的唯一标识,不能重复
         -->
        <environment id="development">
            <!--
                transactionManager:设置事务管理方式
                属性:
                    type="JDBC|MANAGED"
                    JDBC:表示当前环境中,执行SQL时,使用的是JDBC中原生的事务管理方式,事务的提交或回滚需要手动处理
                    MANAGED:被管理,例如被Spring管理
             -->
            <transactionManager type="JDBC"/>
            <!--
                dataSource:配置数据源
                属性:
                    type:设置数据源的类型
                    type="POOLED|UNPOOLED|JNDI"
                    POOLED:表示使用数据库连接池缓存数据库连接
                    UNPOOLED:表示不使用数据库连接池
                    JNDI:表示使用上下文中的数据源
             -->
            <dataSource type="POOLED">
                <!-- 设置连接数据库的驱动 -->
                <property name="driver" value="${jdbc.driver}"/>
                <!-- 设置连接数据库的连接地址 -->
                <property name="url" value="${jdbc.url}"/>
                <!-- 设置连接数据库的用户名 -->
                <property name="username" value="${jdbc.username}"/>
                <!-- 设置连接数据库的密码 -->
                <property name="password" value="${jdbc.password}"/>
            </dataSource>
        </environment>
    </environments>
    
    <!-- 引入映射文件 -->
    <mappers>
<!--        <mapper resource="mapper/UserMapper.xml"/>-->
        <!--
            以包为单位引入映射文件
            要求:
            1、mapper接口所在的包要和映射文件所在的包一致
            2、mapper接口要和映射文件的名字一致
         -->
        <package name="com.yu.mybatis.mapper"/>
    </mappers>
</configuration>
```

自定义数据库连接配置：

```properties
jdbc.url=jdbc:mysql://ip:3307/mybatis?useUnicode=true&characterEncoding=UTF-8&useSSL=false&serverTimezone=Asia/Shanghai
jdbc.driver=com.mysql.jdbc.Driver
jdbc.username=root
jdbc.password=123456
```

*<font color='red'>注：resources目录下创建多级目录以/分割，如com/yu/mybatis</font>*

## 八、MyBatis缓存

#### 1、MyBatis的一级缓存

一级缓存是SqlSession级别的，默认开启，通过同一个SqlSession查询的数据会被缓存，下次查询相同的数据，就会从缓存中直接获取，不会重新访问数据库。

```java
public interface CacheMapper {

    /**
	 * 通过一级缓存查询员工信息
	 *
	 * @param eid 员工id
	 * @return {@link Employee}
	 */
    Employee getEmployeeByFirstLevelCache(@Param("eid") Long eid);
}
```

```xml
<select id="getEmployeeByFirstLevelCache" resultType="com.yu.mybatis.pojo.Employee">
    select emp_name, age, sex, email from employee where eid = #{eid}
</select>
```

```java
public class CacheMapperTest {

    @Test
    public void testGetEmployeeByFirstLevelCache() throws Exception {
        InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        CacheMapper cacheMapper1 = sqlSession.getMapper(CacheMapper.class);
        CacheMapper cacheMapper2 = sqlSession.getMapper(CacheMapper.class);

        Employee employee1 = cacheMapper1.getEmployeeByFirstLevelCache(1L);
        Employee employee2 = cacheMapper2.getEmployeeByFirstLevelCache(1L);

        System.out.println(employee1);
        System.out.println(employee2);
    }
}
```

![image-20220825163730226](https://cdn.jsdelivr.net/gh/elon-lo/PicGo-Image@master/img/image-20220825163730226.png)

**一级缓存失效**的几种情况：

1)不同的SqlSession对应不同的一级缓存

2)同一个SqlSession但是查询条件不同

3)同一个SqlSession两次查询期间执行了任何一次增删改操作

4)同一个SqlSession两次查询期间手动清空了缓存

#### 2、MyBatis的二级缓存

二级缓存是SqlSessionFactory级别，默认是不开启的，需要手动开启。通过同一个SqlSessionFactory创建的SqlSession查询的结果会被缓存；此后如果再执行相同的查询语句，结果就会从缓存中获取。

**二级缓存开启**的条件如下：

1)在核心配置文件中，设置全局配置属性cacheEnabled="true"，默认为true，不需要设置

2)在映射文件中设置标签<cache />

3)二级缓存必须在SqlSession关闭或提交之后才有效

4)查询的数据所转换的实体类类型必须实现序列化的接口

```xml
<!-- 全局配置 -->
<settings>
    <setting name="cacheEnabled" value="true"/>
</settings>
```

```xml
<mapper namespace="com.yu.mybatis.mapper.CacheMapper">
    <cache />
</mapper>
```

```java
public class Employee implements Serializable {}
```

```java
public class CacheMapperTest {
    
    @Test
    public void testGetEmployeeBySecondLevelCache() throws Exception {
        InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);

        SqlSession sqlSession1 = sqlSessionFactory.openSession(true);
        CacheMapper cacheMapper1 = sqlSession1.getMapper(CacheMapper.class);
        System.out.println(cacheMapper1.getEmployeeByFirstLevelCache(1L));
        sqlSession1.commit();
        //改成sqlSession1.close()效果一样


        SqlSession sqlSession2 = sqlSessionFactory.openSession(true);
        CacheMapper cacheMapper2 = sqlSession2.getMapper(CacheMapper.class);
        System.out.println(cacheMapper2.getEmployeeByFirstLevelCache(1L));
        //		sqlSession2.commit();
    }
}
```

![image-20220826162422662](https://cdn.jsdelivr.net/gh/elon-lo/PicGo-Image@master/img/image-20220826162422662.png)

**二级缓存失效**的情况如下：

两次查询之间执行了任意的增删改，会使一级缓存和二级缓存同时失效

<font color='red'>注：必须四个条件同时满足才能开启二级缓存</font>

#### 3、二级缓存的相关配置

在mapper配置文件中添加的cache标签可以设置一些属性：

- eviction属性：缓存回收策略

  - LRU(Least Recently Used)-最近最少使用(<font color='red'>默认</font>)：移除最长时间不被使用的对象。
  - FIFO(First In First Out)-先进先出：按对象进入缓存的顺序来移除它们。
  - SOFT-软引用：移除基于垃圾回收器状态和软引用规则的对象。
  - WEAK-弱引用：更积极的移除基于垃圾收集器状态和弱引用规则的对象。

- flushInteval属性：刷新间隔，单位毫秒

  默认情况下是不设置，也就是没有刷新间隔，缓存仅仅调用语句时刷新。

- size属性：引用数目，正整数

  代表缓存最多存储多少个对象，太大容易导致内存溢出。

- readOnly：是否只读，true|false(<font color='red'>默认</font>)

  true：只读缓存；会给所有调用者返回缓存对象的相同实例，因此这些对象不能被修改。这提供了很重要的性能优势。

  false：读写缓存，会返回缓存对象的拷贝(通过序列化)。这会慢一些，但是安全，因此默认是false。

#### 4、MyBatis缓存查询的顺序

1)先查询二级缓存，因为二级缓存中可能有其他程序已经查询出来的数据，可以拿来直接使用

2)如果二级缓存没有命中，再去查询一级缓存

3)如果一级缓存也没有命中，则查询数据库

4)SqlSession关闭或提交之后，一级缓存中的数据会写入二级缓存

#### 5、整合第三方缓存Ehcache

- 添加依赖

  ```xml
  <dependencies>
      <!-- mybatis核心 -->
      <dependency>
          <groupId>org.mybatis</groupId>
          <artifactId>mybatis</artifactId>
          <version>3.5.7</version>
      </dependency>
  
      <!-- junit测试 -->
      <dependency>
          <groupId>junit</groupId>
          <artifactId>junit</artifactId>
          <version>4.12</version>
          <scope>test</scope>
      </dependency>
  
      <!-- mysql驱动 -->
      <dependency>
          <groupId>mysql</groupId>
          <artifactId>mysql-connector-java</artifactId>
          <version>5.1.38</version>
      </dependency>
  
      <!-- log4j -->
      <dependency>
          <groupId>log4j</groupId>
          <artifactId>log4j</artifactId>
          <version>1.2.17</version>
      </dependency>
  
      <!-- mybatis Ehcache整合包 -->
      <dependency>
          <groupId>org.mybatis.caches</groupId>
          <artifactId>mybatis-ehcache</artifactId>
          <version>1.2.1</version>
      </dependency>
  
      <!-- slf4j日志门面的一个具体实现 -->
      <dependency>
          <groupId>ch.qos.logback</groupId>
          <artifactId>logback-classic</artifactId>
          <version>1.2.3</version>
      </dependency>
  </dependencies>
  ```

- 各jar包功能

  | **jar包名称**   | **作用**                    |
  | --------------- | --------------------------- |
  | mybatis-ehcache | mybatis和Ehcache的整合包    |
  | ehcache         | Ehcache的核心包             |
  | slf4j-api       | slf4j日志门面包             |
  | logback-classic | slf4j日志门面的一个具体实现 |

- ehcache配置

  ```xml
  <?xml version="1.0" encoding="UTF-8"?>
  <ehcache name="ehcache" updateCheck="false">
      <!-- 指定一个文件目录，当EhCache把数据写到硬盘上时，将把数据写到这个文件目录下 -->
      <diskStore path="D:\ehcache\data"/>
      <!-- 设定缓存的默认数据过期策略 -->
      <defaultCache
              maxElementsInMemory="1000"
              maxElementsOnDisk="10000000"
              eternal="false"
              overflowToDisk="true"
              timeToIdleSeconds="120"
              timeToLiveSeconds="120"
              diskPersistent="false"
              diskSpoolBufferSizeMB="30"
              maxEntriesLocalDisk="10000000"
              diskExpiryThreadIntervalSeconds="120"
              memoryStoreEvictionPolicy="LRU">
      </defaultCache>
  </ehcache>
  ```

- Ehcache配置文件说明

  | **属性名**                      | **是否必须** | **作用**                                                     |
  | ------------------------------- | ------------ | ------------------------------------------------------------ |
  | maxElementsInMemory(正整数)     | 是           | 在内存中缓存的最大对象数量                                   |
  | maxElementsOnDisk(正整数)       | 是           | 在磁盘上缓存的最大对象数量，默认值为0，表示不限制            |
  | eternal                         | 是           | 设定缓存对象保存的永久属性，默认为 false 。当为 true 时 timeToIdleSeconds、timeToLiveSeconds 失效 |
  | timeToIdleSeconds(单位：秒)     | 否           | 对象空闲时间，指对象在多长时间没有被访问就会失效。只对eternal为false的有效。默认值0，表示一直可以访问 |
  | timeToLiveSeconds(单位：秒)     | 否           | 对象存活时间，指对象从创建到失效所需要的时间。只对eternal为false的有效。默认值0，表示一直可以访问 |
  | overflowToDisk                  | 是           | 如果内存中数据超过内存限制，是否要缓存到磁盘上               |
  | diskPersistent                  | 否           | 是否在磁盘上持久化。指重启jvm后，数据是否有效。默认为false   |
  | diskSpoolBufferSizeMB(单位：MB) | 否           | DiskStore使用的磁盘大小，默认值30MB。每个cache使用各自的DiskStore |
  | memoryStoreEvictionPolicy       | 是           | 如果内存中数据超过内存限制，向磁盘缓存时的策略。默认值LRU，可选FIFO、LFU |

- 设置二级缓存的类型

  ```xml
  <cache type="org.mybatis.caches.ehcache.EhcacheCache"/>
  ```

- 加入logback日志

  存在slf4j时，作为简易的log4j将失效，此时我们需要借助slf4j的具体实现logback来打印日志

  创建logback的配置文件logback.xml

  ```xml
  <?xml version="1.0" encoding="UTF-8"?>
  <configuration debug="true">
  
      <!--定义日志文件的存储地址 勿在 LogBack 的配置中使用相对路径-->
      <property name="LOG_HOME" value="/home" />
  
      <!--控制台日志， 控制台输出 -->
      <appender name="STDOUT" class="ch.qos.logback.core.ConsoleAppender">
          <encoder class="ch.qos.logback.classic.encoder.PatternLayoutEncoder">
              <!--格式化输出：%d表示日期，%thread表示线程名，%-5level：级别从左显示5个字符宽度,%msg：日志消息，%n是换行符-->
              <pattern>%d{yyyy-MM-dd HH:mm:ss.SSS} [%thread] %-5level %logger{50} - %msg%n</pattern>
          </encoder>
      </appender>
  
      <!-- 日志输出级别 -->
      <root level="DEBUG">
          <appender-ref ref="STDOUT" />
      </root>
  
      <logger name="com.yu.mybatis.mapper" level="DEBUG" />
  </configuration>
  ```

## 九、MyBatis逆向工程

MySQL官方的JDBC文档定义转换规则：https://dev.mysql.com/doc/connector-j/5.1/en/connector-j-reference-type-conversions.html

MyBatis Generator官方文档：http://mybatis.org/generator/quickstart.html

#### 1、添加依赖信息

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>com.yu.mybatis</groupId>
    <artifactId>MyBatis-MBG</artifactId>
    <version>1.0-SNAPSHOT</version>
    <packaging>jar</packaging>

    <properties>
        <maven.compiler.source>8</maven.compiler.source>
        <maven.compiler.target>8</maven.compiler.target>
    </properties>

    <dependencies>
        <dependency>
            <groupId>org.mybatis</groupId>
            <artifactId>mybatis</artifactId>
            <version>3.5.7</version>
        </dependency>

        <!-- junit测试 -->
        <dependency>
            <groupId>junit</groupId>
            <artifactId>junit</artifactId>
            <version>4.12</version>
            <scope>test</scope>
        </dependency>

        <!-- mysql驱动 -->
        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <version>5.1.38</version>
        </dependency>

        <!-- log4j -->
        <dependency>
            <groupId>log4j</groupId>
            <artifactId>log4j</artifactId>
            <version>1.2.17</version>
        </dependency>
    </dependencies>

    <!-- 控制Maven在构建过程中相关配置 -->
    <build>
        <!-- 构建过程中用到的插件 -->
        <plugins>
            <!-- 具体插件,逆向工程的操作是以构建过程中插件形式出现的 -->
            <plugin>
                <groupId>org.mybatis.generator</groupId>
                <artifactId>mybatis-generator-maven-plugin</artifactId>
                <version>1.3.0</version>

                <!-- 插件的依赖 -->
                <dependencies>

                    <!-- 逆向工程的核心依赖 -->
                    <dependency>
                        <groupId>org.mybatis.generator</groupId>
                        <artifactId>mybatis-generator-core</artifactId>
                        <version>1.3.2</version>
                    </dependency>

                    <!-- 数据库连接池 -->
                    <!-- https://mvnrepository.com/artifact/com.mchange/c3p0 -->
                    <dependency>
                        <groupId>com.mchange</groupId>
                        <artifactId>c3p0</artifactId>
                        <version>0.9.2</version>
                    </dependency>

                    <!-- MySQL驱动 -->
                    <dependency>
                        <groupId>mysql</groupId>
                        <artifactId>mysql-connector-java</artifactId>
                        <version>5.1.38</version>
                    </dependency>
                </dependencies>
            </plugin>
        </plugins>
    </build>

</project>
```

#### 2、添加必要的配置文件

```xml-dtd
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE log4j:configuration SYSTEM "http://logging.apache.org/log4j/1.2/apidocs/org/apache/log4j/xml/doc-files/log4j.dtd">

<log4j:configuration debug="true">

    <appender name="STDOUT" class="org.apache.log4j.ConsoleAppender">
        <layout class="org.apache.log4j.PatternLayout">
            <param name="ConversionPattern"
                   value="%-5p %d{dd HH:mm:ss:SSS\} %m (%F:%L) \n" />
        </layout>
    </appender>
    
    <logger name="java.sql">
        <level value="debug" />
    </logger>

    <logger name="org.apache.ibatis">
        <level value="info" />
    </logger>

    <root>
        <level value="debug" />
        <appender-ref ref="STDOUT" />
    </root>
</log4j:configuration>
```

```properties
jdbc.url=jdbc:mysql://ip:3307/mybatis?useUnicode=true&characterEncoding=UTF-8&useSSL=false&serverTimezone=Asia/Shanghai
jdbc.driver=com.mysql.jdbc.Driver
jdbc.username=root
jdbc.password=123456
```

```xml-dtd
<!-- 注意:typeAliases和mappers需要在代码生成后再设置,否则会找不到 -->
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE configuration
        PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-config.dtd">
<configuration>

    <properties resource="jdbc.properties"/>

    <!-- 设置类型别名 -->
    <typeAliases>
        <package name=""/>
    </typeAliases>

    <environments default="development">
        <environment id="development">
            <transactionManager type="JDBC"/>
            <dataSource type="POOLED">
                <property name="driver" value="${jdbc.driver}"/>
                <property name="url" value="${jdbc.url}"/>
                <property name="username" value="${jdbc.username}"/>
                <property name="password" value="${jdbc.password}"/>
            </dataSource>
        </environment>
    </environments>

    <!-- 引入映射文件 -->
    <mappers>
        <package name=""/>
    </mappers>
</configuration>
```

#### 3、创建generatorConfig.xml配置文件

##### 3.1、MyBatis Generator简易版

- 配置文件

  ```xml-dtd
  <?xml version="1.0" encoding="UTF-8"?>
  <!DOCTYPE generatorConfiguration
          PUBLIC "-//mybatis.org//DTD MyBatis Generator Configuration 1.0//EN"
          "http://mybatis.org/dtd/mybatis-generator-config_1_0.dtd">
  
  <generatorConfiguration>
      <!--
  		targetRuntime="MyBatis3Simple|MyBatis3"
  		MyBatis3Simple:表示生成简易版本
  		MyBatis3:表示生成带条件的CRUD
  	-->
      <context id="DB2Tables" targetRuntime="MyBatis3Simple">
          <commentGenerator>
              <!-- 是否去除自动生成的注释。true:是 false:否 -->
              <property name="suppressAllComments" value="true" />
          </commentGenerator>
          <!--数据库连接的信息:驱动类、连接地址、用户名、密码 -->
          <jdbcConnection driverClass="com.mysql.jdbc.Driver"
                          connectionURL="jdbc:mysql://ip:3307/mybatis?tinyInt1isBit=false&amp;useUnicode=true&amp;characterEncoding=UTF-8&amp;useSSL=false&amp;serverTimezone=Asia/Shanghai"
                          userId="root"
                          password="123456">
  
              <!--  解决table schema中有多个重名的表生成表结构不一致问题 -->
              <!--<property name="nullCatalogMeansCurrent" value="true" />-->
          </jdbcConnection>
  
          <!-- 默认false，把JDBC DECIMAL和NUMERIC类型解析为Integer，为true时把JDBC DECIMAL 和
              NUMERIC 类型解析为java.math.BigDecimal -->
          <javaTypeResolver>
              <property name="forceBigDecimals" value="true" />
          </javaTypeResolver>
  
          <!-- targetProject:POJO类生成的位置 -->
          <javaModelGenerator targetPackage="com.yu.mybatis.pojo"
                              targetProject="./src/main/java">
              <!-- enableSubPackages:是否让schema作为包的后缀 -->
              <property name="enableSubPackages" value="true" />
              <!-- 从数据库返回的值被清理前后的空格 -->
              <property name="trimStrings" value="true" />
          </javaModelGenerator>
          <!-- targetProject:mapper映射文件生成的位置 -->
          <sqlMapGenerator targetPackage="com.yu.mybatis.mapper"
                           targetProject="./src/main/resources">
              <!-- enableSubPackages:是否让schema作为包的后缀 -->
              <property name="enableSubPackages" value="true" />
          </sqlMapGenerator>
          <!-- targetPackage:mapper接口生成的位置 -->
          <javaClientGenerator type="XMLMAPPER"
                               targetPackage="com.yu.mybatis.mapper"
                               targetProject="./src/main/java">
              <!-- enableSubPackages:是否让schema作为包的后缀 -->
              <property name="enableSubPackages" value="true" />
          </javaClientGenerator>
          <!-- 指定生成哪些数据库表,要和数据库中对应,不能写错了,这里以t_user表为例,可以写多个;domainObjectName是要生成的实体类名称 -->
          <!-- <table schema="mybatis" tableName="t_user"/> -->
          <table tableName="department" domainObjectName="Dept" />
          <!-- 有些表的字段需要指定java类型 -->
           <table tableName="employee" domainObjectName="Emp">
              <columnOverride column="sex" javaType="Integer" />
          </table>
  
      </context>
  </generatorConfiguration>
  ```

- 使用Maven插件实现代码生成

![image-20220827170857049](https://cdn.jsdelivr.net/gh/elon-lo/PicGo-Image@master/img/image-20220827170857049.png)

- 生成之后的效果如下：

  ![image-20220827172950739](https://cdn.jsdelivr.net/gh/elon-lo/PicGo-Image@master/img/image-20220827172950739.png)

##### 3.2、MyBatis Generator进阶版

- 配置文件

  ```xml
  <!-- 其他配置同上 -->
  <!-- context标签的id是自定义的 -->
  <context id="DB2Tables" targetRuntime="MyBatis3">
  ```

- 生成之后的效果

  ![image-20220827173723463](https://cdn.jsdelivr.net/gh/elon-lo/PicGo-Image@master/img/image-20220827173723463.png)

#### 3.3、测试

```java
public class MBGTest {

	@Test
	public void testMBG() {
		try {
			InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
			SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
			SqlSession sqlSession = sqlSessionFactory.openSession(true);
			EmpMapper mapper = sqlSession.getMapper(EmpMapper.class);

			// 查询所有员工
			/*List<Emp> empList = mapper.selectByExample(null);
			empList.forEach(System.out::println);*/

			// 根据条件查询
			/*EmpExample example = new EmpExample();
			example.createCriteria().andEmpNameEqualTo("zs").andAgeGreaterThan(14);
			example.or().andDidEqualTo(1L);
			List<Emp> empList = mapper.selectByExample(example);
			empList.forEach(System.out::println);*/

//			mapper.updateByPrimaryKey(new Emp(1L, "admin", null, 1, "admin@163.com", 5L));
			mapper.updateByPrimaryKeySelective(new Emp(1L, "admin", null, 1, "admin@163.com", 5L));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
```

## 十、MyBatis分页插件

#### 1、分页插件使用步骤

**a、添加依赖**

```xml
<!-- https://mvnrepository.com/artifact/com.github.pagehelper/pagehelper -->
<dependency>
    <groupId>com.github.pagehelper</groupId>
    <artifactId>pagehelper</artifactId>
    <version>5.2.0</version>
</dependency>

<!-- slf4j日志门面的一个具体实现 -->
<dependency>
    <groupId>ch.qos.logback</groupId>
    <artifactId>logback-classic</artifactId>
    <version>1.2.3</version>
</dependency>
```

**b、配置分页插件**

在MyBatis的核心配置文件中配置分页插件

```xml
<plugins>
    <!-- 设置分页插件 -->
    <plugin interceptor="com.github.pagehelper.PageInterceptor" />
</plugins>
```

#### 2、分页插件的使用

实际使用MySQL的limit关键字进行分页

linit index, pageSize

a、在查询功能之前使用PageHelper.startPage(int pageNum, int pageSize)开启分页功能

pageNum：当前页的页码

pageSize：每页显示的条数

index：当前页的起始索引，index=(pageNum - 1)*pageSize

b、在查询获取list集合之后，使用PageInfo<T> pageInfo = new PageInfo<>(List<T> list, int navigatePages)获取分页相关数据

list：分页之后的数据

navigatePages：导航分页的页码数

c、分页相关数据

常用数据：

pageNum：当前页的页码

pageSize：每页显示的条数

size：当前页显示的条数

total：总记录数

pages：总页数

prePage：上一页的页码

nextPage：下一页的页码

isFirstPage/isLastPage：是否为第一页/最后一页

hasPreviousPage/hasNextPage：是否存在上一页/下一页

navigatePages：导航分页的页码数

navigatepageNums：导航分页的页码, [1,2,3,4,5]

navigateFirstPage/navigateLastPage：导航条上的第一页/最后一页

#### 3、示例

```java
public class PageHelperTest {

	private static final Logger Logger = LoggerFactory.getLogger(PageHelperTest.class);

	@Test
	public void testPageHelper() {
		try {
			InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
			SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
			SqlSession sqlSession = sqlSessionFactory.openSession(true);
			EmpMapper mapper = sqlSession.getMapper(EmpMapper.class);

			PageHelper.startPage(1,4);
			List<Emp> empList = mapper.selectByExample(null);
			PageInfo<Emp> pageInfo = new PageInfo<>(empList,5);
			Logger.info("当前页面第一个元素在数据库中的行号: {}",pageInfo.getStartRow());
			Logger.info("当前页面最后一个元素在数据库中的行号: {}",pageInfo.getEndRow());
			Logger.info("前一页: {}",pageInfo.getPrePage());
			Logger.info("下一页: {}",pageInfo.getNextPage());
			Logger.info("当前页: {}",pageInfo.getPageNum());
			Logger.info("每页的数量: {}",pageInfo.getPageSize());
			Logger.info("当前页的数量: {}",pageInfo.getSize());
			Logger.info("总页数: {}",pageInfo.getPages());
			Logger.info("是否为第一页: {}",pageInfo.isIsFirstPage());
			Logger.info("是否为最后一页: {}",pageInfo.isIsLastPage());
			Logger.info("是否有前一页: {}",pageInfo.isHasPreviousPage());
			Logger.info("是否有下一页: {}",pageInfo.isHasNextPage());
			Logger.info("总记录数: {}",pageInfo.getTotal());
			Logger.info("导航页码数: {}",pageInfo.getNavigatePages());
			Logger.info("所有导航页号: {}",pageInfo.getNavigatepageNums());
			Logger.info("导航条上的第一页: {}",pageInfo.getNavigateFirstPage());
			Logger.info("导航条上的最后一页: {}",pageInfo.getNavigateLastPage());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
```

