### 1、ORM概述

##### 1.1、什么是ORM思想

ORM(Object-Relational Mapping)表示对象关系映射。在面向对象开发过程中，通过ORM，就可以将对象映射到关系型数据库中。只要能够建立对象与数据库的关联，操作对象能够直接操作关系型数据库，就可以说实现了ORM对象关系映射

简单来说，ORM就是建立实体类与数据库表之间的关系，从而达到操作实体类就相当于操作数据库表的目的

建立两个映射关系：

- 实体类与表之间的映射关系
- 实体类属性与表中字段的映射关系

实现了ORM思想的框架：mybatis、hibernate

### 2、Hibernate框架介绍

1. hibernate是一个开放源代码的对象关系映射框架
2. 它对JDBC进行了非常轻量级的对象封装
3. 它将POJO与数据库表之间建立映射关系，是一个全自动的ORM框架

### 3、JPA规范

##### 3.1、JPA概述

JPA的全称是Java Persistence API，即Java持久化API，是SUN公司推出的一套基于ORM的规范，内部是由一系列接口和抽象类构成

JPA通过JDK5.0注解描述对象-关系表之间的映射关系，并将运行期的实体对象持久化到数据库中

##### 3.2、JPA的优势

1. 标准化

   JPA是JCP组织发布的Java EE标准之一，因此任何声称符合JPA标准的框架都遵循同样的架构，提供相同的访问API，这保证了基于JPA标准开发的企业应用能够经过少量的修改就能够在不同的JPA框架下运行

2. 容器级特性的支持

   JPA框架中支持大数据集、事务、并发等容器级操作，这使得JPA超越了简单持久化框架的局限，在企业应用发挥更大的作用

3. 简单方便

   JPA的主要目标之一就是提供更加简单的编程模型：在JPA框架下创建实体和创建Java类一样简单，没有任何约束和限制，只需要使用

   javax.persistence.Entity进行注释，JPA的框架和接口也都非常简单，没有太多特别的规则和设计模式的要求，开发者可以很容易的掌握。JPA基于非侵入式原则设计，因此可以很容易和其他框架或容器集成

4. 查询能力

   JPA的查询语言是面向对象而非面向数据库的，它以面向对象的自然语法构造查询语句，可以看成是Hibernate HQL的等价物。JPA定义了独特的JPQL(Java Persistence Query Languge)，JPQL是EJB QL的一种扩展，它是针对实体的一种查询语言，操作对象是实体，而不是关系型数据库中的表，而且能够支持批量更新和修改、JOIN、GROUP BY、HAVING等通常只有SQL语言才能够提供的高级查询特性，甚至还能够支持子查询

5. 高级特性

   JPA中能够支持面向对象的高级特性，如类之间的继承、多态和类之间的复杂关系，这样的支持能够让开发者最大限度的使用面向对象的模型设计企业应用，而不需要自行处理这些特性在关系数据库的持久化

##### 3.3、JPA与Hibernate之间的关系

JPA规范本质上就是一种ORM规范，注意不是ORM框架，因为JPA并未提供ORM实现，它只是制定了一些规范，提供了一些编程的PI接口，但具体实现则由服务厂商(如Hibernate、toplink)来提供实现

JPA和Hibernate的关系就像JDBC和JDBC驱动的关系，JPA是规范，Hibernate除了是一种ORM框架之外，它也是JPA的一种实现。JPA怎么取代Hibernate呢？JDBC规范可以驱动底层数据库吗？答案是否定等待，也就是说，如果使用JPA规范进行数据库操作，底层需要使用Hibernate作为其实现类完成数据持久化操作

### 4、JPA的基本操作

##### 4.1、搭建环境



1. 创建maven工程导入依赖

   ```xml
   		<properties>
           <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
           <project.hibernate.version>5.0.7.Final</project.hibernate.version>
       </properties>
   
       <dependencies>
           <!--junit-->
           <dependency>
               <groupId>junit</groupId>
               <artifactId>junit</artifactId>
               <version>4.12</version>
               <scope>test</scope>
           </dependency>
   
           <!--hibernate对jpa支持的包-->
           <dependency>
               <groupId>org.hibernate</groupId>
               <artifactId>hibernate-entitymanager</artifactId>
               <version>${project.hibernate.version}</version>
           </dependency>
   
           <!--c3p0-->
           <dependency>
               <groupId>org.hibernate</groupId>
               <artifactId>hibernate-c3p0</artifactId>
               <version>${project.hibernate.version}</version>
           </dependency>
   
           <!--log日志-->
           <dependency>
               <groupId>log4j</groupId>
               <artifactId>log4j</artifactId>
               <version>1.2.17</version>
           </dependency>
   
           <!--mysql and MariaDB-->
           <dependency>
               <groupId>mysql</groupId>
               <artifactId>mysql-connector-java</artifactId>
               <version>8.0.23</version>
           </dependency>
       </dependencies>
   
   ```

   

2. 配置JPA的核心配置文件

   位置：配置到类路径下META-INF的文件夹下

   命名：persistence.xml

   ```xml
   <?xml version="1.0" encoding="UTF-8"?>
   
   <!--
   导入约束
   File→New→File→Edit File Templates→Others→JPA→Deployment Descriptors
   -->
   <persistence xmlns="http://java.sun.com/xml/ns/persistence" version="2.0">
       <!--
           需要配置persistence-unit节点
           持久化单元：
               name：持久化单元名称
               transaction-type：事务管理方式
                   JTA：分布式事务管理
                   RESOURCE_LOCAL：本地事务管理
   
       -->
       <persistence-unit name="myJPA" transaction-type="RESOURCE_LOCAL">
           <!--JPA的实现方式-->
           <provider>org.hibernate.jpa.HibernatePersistenceProvider</provider>
   
           <!--可选配置：配置JPA实现方式的配置信息-->
           <properties>
               <!--
                   数据库信息
                   用户名：javax.persistence.jdbc.user
                   密码：javax.persistence.jdbc.password
                   驱动：javax.persistence.jdbc.driver
                   url：javax.persistence.jdbc.url
               -->
   
               <property name="javax.persistence.jdbc.user" value="root"/>
               <property name="javax.persistence.jdbc.password" value="123456"/>
               <property name="javax.persistence.jdbc.driver" value="com.mysql.cj.jdbc.Driver"/>
               <property name="javax.persistence.jdbc.url" value="jdbc:mysql://127.0.0.1:3306/jpa?useUnicode=true&amp;characterEncoding=UTF-8&amp;serverTimezone=Asia/Shanghai"/>
   
               <!--配置JPA实现方(hibernate)的配置信息
                   显示sql：hibernate.show_sql    false|true
                   自动创建数据库表：hibernate.hbm2ddl.auto
                           create：程序运行时创建数据库表(如果有表,先删除表再创建)
                           update：程序运行时创建数据库表(如果有表,不会创建表)
                           none：不会创建表
               -->
               <property name="hibernate.show_sql" value="true"/>
               <property name="hibernate.hbm2ddl.auto" value="create"/>
           </properties>
   
   
       </persistence-unit>
   </persistence>
   
   ```

   

3. 配置实体类和表，类中属性和表中字段的映射关系

   ```java
   /**
    * 配置映射关系
    *
    * 1.实体类和表的映射关系
    * @Entity：声明实体类
    * @Table：配置实体类和表的映射关系
    *   name：配置数据库表的名称
    *
    * 2.实体类属性和表中字段的映射关系
    *
    */
   @Entity
   @Table(name = "cst_customer")
   public class Customer {
   
       /**
        * @Id：声明主键的配置
        * @GeneratedValue：配置主键的生成策略
        *      strategy
        *          GenerationType.IDENTITY：自增,如mysql
        *                  *底层数据库必须支持自动增长(底层数据库的自动增长方式,对id自增)
        *          GenerationType.SEQUENCE：序列,如oracle
        *                  *底层数据库必须支持序列
        *          GenerationType.TABLE：jpa提供的一种机制,通过一张数据表的形式帮助我们完成主键自增
        *          GenerationType.AUTO：由程序自动帮我们选择主键生成策略
        *
        * @Column：配置属性和字段的映射关系
        *      name：数据库表中字段的名称
        */
       @Id
       @GeneratedValue(strategy = GenerationType.IDENTITY)
       @Column(name = "cust_id")
       private Long custId;//客户主键
   
       @Column(name = "cust_name")
       private String custName;//客户名称
   
       @Column(name = "cust_source")
       private String custSource;//客户来源
   
       @Column(name = "cust_level")
       private String custLevel;//客户级别
   
       @Column(name = "cust_industry")
       private String custIndustry;//客户所属行业
   
       @Column(name = "cust_address")
       private String custAddress;//客户地址
   
       @Column(name = "cust_phone")
       private String custPhone;//客户联系方式
   }
   ```

   

4. 保存客户到数据库中

   ```java
   public class JpaTest {
   
       /**
        * Jpa的操作步骤
        *      1.加载配置文件创建工厂(实体管理器工厂)对象
        						Persistence：静态方法(根据持久化单元名称创建实体管理器工厂)
        									createEntityManagerFactory(持久化单元名称)
        *      2.通过实体管理器工厂获取实体管理器
        						EntityManagerFactory：获取EntityManager对象
        									createEntityManager
        									内部维护了很多的内容
        									内部维护了数据库信息
        									内部维护了缓存信息
        									维护了所有的实体管理器对象
        									在创建createEntityManagerFactory的过程中会根据配置创建数据库表
        						EntityManagerFactory的创建过程比较浪费资源
        						特点：EntityManagerFactory是线程安全的
        						如何解决EntityManagerFactory的创建过程浪费资源(耗时)的问题?
        						思路：创建一个公共的EntityManagerFactory对象
        						静态代码块的方式创建EntityManagerFactory
        									
        *      3.获取事务对象，开启事务
        						EntityManager对象：实体类管理器
        								beginTransaction：创建事务对象
        								presist：保存
        								merge：更新
        								remove：删除
        								faind/getRefrence：根据id查询
        								
        						Transction对象：事务对象
        								begin：开启事务
        								commit：提交事务
        								rollback：回滚事务
        *      4.完成增删改查操作
        *      5.提交事务或回滚事务
        *      6.释放资源
        */
       @Test
       public void testSave(){
   
           EntityManagerFactory factory = null;
           EntityManager entityManager = null;
           try {
               //1.加载配置文件创建工厂(实体管理器工厂)对象
               factory = Persistence.createEntityManagerFactory("myJpa");
   
               //2.通过实体管理器工厂获取实体管理器
               entityManager = factory.createEntityManager();
   
               //3.获取事务对象，开启事务
               EntityTransaction tx = entityManager.getTransaction();//获取事务对象
               tx.begin();//开启事务
   
               //4.完成增删改查操作
               Customer customer = new Customer();
               customer.setCustName("华为");
               customer.setCustIndustry("通信");
   
               //保存
               entityManager.persist(customer);
   
               //5.提交事务或回滚事务
            tx.commit();
   
   
           } catch (Exception e) {
               e.printStackTrace();
           } finally {
               //6.释放资源
               entityManager.close();
               factory.close();
           }
       }
   }
   
   ```



##### 4.2、创建实体类管理器工厂工具类

```java
public class JpaUtil {

    private static EntityManagerFactory factory;

    static {
         factory = Persistence.createEntityManagerFactory("myJpa");
    }

    public static EntityManager getEntityManager(){
        return factory.createEntityManager();
    }

}

```

##### 4.3、Jpa的其他API操作

```java
/**
     * 根据id查询
     * 立即加载,调用find方法时就操作数据库
     */
    @Test
    public void testFind(){
        EntityManager entityManager = JpaUtil.getEntityManager();

        EntityTransaction tx = entityManager.getTransaction();

        tx.begin();

        Customer customer = entityManager.find(Customer.class, 1l);

        tx.commit();

        entityManager.close();
    }

    /**
     * 根据id查询
     * 延迟加载(只有在使用时才操作数据库)
     */
    @Test
    public void testReference(){
        EntityManager entityManager = JpaUtil.getEntityManager();

        EntityTransaction tx = entityManager.getTransaction();

        tx.begin();

        Customer customer = entityManager.getReference(Customer.class, 1l);

        System.out.println(customer);

        tx.commit();

        entityManager.close();
    }

    /**
     * 删除操作
     * remove(Object)
     */
    @Test
    public void testRemove(){
        EntityManager entityManager = JpaUtil.getEntityManager();

        EntityTransaction tx = entityManager.getTransaction();

        tx.begin();

        //删除时先调用查询api获取id,然后根据调用查询到的对象id删除
        Customer customer = entityManager.find(Customer.class, 1l);

        entityManager.remove(customer);

        tx.commit();

        entityManager.close();
    }

    /**
     * 更新操作
     * merge(Object)
     */
    @Test
    public void testUpdate(){
        EntityManager entityManager = JpaUtil.getEntityManager();

        EntityTransaction tx = entityManager.getTransaction();

        tx.begin();

        //i 更新之前先查询
        //ii 更新操作

        Customer customer = entityManager.find(Customer.class, 1l);
        customer.setCustName("小米");
        entityManager.merge(customer);

        tx.commit();

        entityManager.close();
    }

```

##### 4.4、JPQL查询

1. 查询所有，createQuery(String jpql)

   ```java
   /**
        * 查询全部
        *      jpql：from com.yu.entity.Customer
        *      sql：select * from cst_customer
        */
       @Test
       public void testFindAll(){
           //1.获取entityManager对象
           EntityManager entityManager = JpaUtil.getEntityManager();
   
           //2.开启事务
           EntityTransaction tx = entityManager.getTransaction();
           tx.begin();
   
           //3.查询全部
         	String jpql = "from Customer";
           //String jpql = "from com.yu.Entity.Customer";
   
           //4.创建query查询对象
           Query query = entityManager.createQuery(jpql);
   
           //5.发送查询,并封装结果集
           List resultList = query.getResultList();
   
           for (Object object : resultList) {
               System.out.println(object);
           }
   
           //6.提交事务
           tx.commit();
   
           //7.释放资源
           entityManager.close();
       }
   ```

2. 排序查询

   ```java
   /**
        * 排序查询
        * jpql：from Customer ORDER BY cust_id DESC
        * jpql：from Customer ORDER BY cust_id ASC
        */
       @Test
       public void testSort(){
           EntityManager entityManager = JpaUtil.getEntityManager();
   
           EntityTransaction tx = entityManager.getTransaction();
   
           tx.begin();
   
           String jpql = "from Customer ORDER BY cust_id DESC";
   
           Query query = entityManager.createQuery(jpql);
   
           List resultList = query.getResultList();
           for (Object object : resultList) {
               System.out.println(object);
           }
   
           tx.commit();
   
           entityManager.close();
       }
   ```

3. 统计查询

   ```java
   /**
        * 统计查询
        * jpql：select COUNT(custId) from Customer
        */
       @Test
       public void testCount(){
           EntityManager entityManager = JpaUtil.getEntityManager();
   
           EntityTransaction tx = entityManager.getTransaction();
   
           tx.begin();
   
           String jpql = "select COUNT(custId) from Customer";
   
           Query query = entityManager.createQuery(jpql);
   
           /**
            * getSingleResult：得到唯一的结果集
            */
           Object result = query.getSingleResult();
   
           System.out.println(result);
   
           tx.commit();
   
           entityManager.close();
       }
   ```

4. 分页查询

   ```java
   /**
        * 分页查询
        * jpql：from Customer
        */
       @Test
       public void testPaged(){
           EntityManager entityManager = JpaUtil.getEntityManager();
   
           EntityTransaction tx = entityManager.getTransaction();
   
           tx.begin();
   
           String jpql = "from Customer";
   
           Query query = entityManager.createQuery(jpql);
           //起始索引
           Query firstResult = query.setFirstResult(1);
           //每页查询的条数
           query.setMaxResults(3);
   
           List resultList = query.getResultList();
           for (Object object : resultList) {
               System.out.println(object);
           }
   
           tx.commit();
   
           entityManager.close();
       }
   ```

5. 条件查询

   ```java
   /**
        * 条件查询
        * jpql：from Customer where custName like ?
        */
       @Test
       public void testCondition(){
           EntityManager entityManager = JpaUtil.getEntityManager();
   
           EntityTransaction tx = entityManager.getTransaction();
   
           tx.begin();
   
           String jpql = "from Customer where custName like ?";
   
           Query query = entityManager.createQuery(jpql);
           //对参数赋值
           query.setParameter(1,"小%");
   
           List resultList = query.getResultList();
           for (Object object : resultList) {
               System.out.println(object);
           }
   
           tx.commit();
   
           entityManager.close();
       }
   ```



### 5、SpringData JPA

##### 5.1、SpringData JPA概述

SpringData JPA是Spring基于ORM框架、JPA的规范的基础上封装的一套JPA应用框架，可使开发者用极简的代码就可实现对数据库的访问和操作。它提供了包括增删改查等在内的常用功能，且易于扩展！学习并使用SpringData JPA可以极大提高开发效率

SpringData JPA让我们解脱了DAO层的操作，基本上所有CRUD都可以依赖它来实现，在实际使用过程中，推荐使用SpringData JPA+ORM(如：Hibernate)完成操作，这样在切换不同的ORM框架时提供了极大的方便，同时也使数据库层操作更加简单，方便解耦

##### 5.2、SpringData JPA特性

SpringData JPA极大简化了数据库访问层代码。如何简化的呢？使用了SpringData JPA，我们的dao层中只需要写接口，就自动具有了增删改查、分页查询等方法

##### 5.3、SpringData JPA的基本操作

1. 创建实体类并配置和数据库表的映射规则

2. 编写Spring的配置文件

   ```xml
   <?xml version="1.0" encoding="UTF-8"?>
   <beans xmlns="http://www.springframework.org/schema/beans"
          xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:jpa="http://www.springframework.org/schema/data/jpa"
          xmlns:context="http://www.springframework.org/schema/context"
          xsi:schemaLocation="http://www.springframework.org/schema/beans
          http://www.springframework.org/schema/beans/spring-beans.xsd
          http://www.springframework.org/schema/data/jpa
          http://www.springframework.org/schema/data/jpa/spring-jpa.xsd
          http://www.springframework.org/schema/context
          http://www.springframework.org/schema/context/spring-context.xsd">
   
   
       <!--Spring 和 SpringData Jpa的配置-->
   
       <!--1、创建entityManager对象交由spring容器管理-->
       <bean id="entityManagerFactory" class="org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean">
           <property name="dataSource" ref="dataSource"/>
           <!--配置实体类所在的包扫描-->
           <property name="packagesToScan" value="com.yu.entity"/>
           <!--Jpa的实现厂家-->
           <property name="persistenceProvider">
               <bean class="org.hibernate.jpa.HibernatePersistenceProvider"/>
           </property>
   
           <!--Jpa的供应商适配器-->
           <property name="jpaVendorAdapter">
               <bean class="org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter">
                   <!--配置是否自动创建数据库表-->
                   <property name="generateDdl" value="false"/>
                   <!--指定数据库类型-->
                   <property name="database" value="MYSQL"/>
                   <!--数据库方言-->
                   <property name="databasePlatform" value="org.hibernate.dialect.MySQLDialect"/>
                   <!--是否显示sql-->
                   <property name="showSql" value="true"/>
               </bean>
           </property>
   
           <!--Jpa的方言,高级特性-->
           <property name="jpaDialect">
               <bean class="org.springframework.orm.jpa.vendor.HibernateJpaDialect"/>
           </property>
       </bean>
   
       <!--2、创建数据库连接池-->
       <bean id="dataSource" class="com.mchange.v2.c3p0.ComboPooledDataSource">
           <property name="user" value="root"/>
           <property name="password" value="123456"/>
           <property name="driverClass" value="com.mysql.cj.jdbc.Driver"/>
           <property name="jdbcUrl" value="jdbc:mysql://127.0.0.1:3306/jpa?useUnicode=true&amp;characterEncoding=UTF-8&amp;serverTimezone=Asia/Shanghai"
       </bean>
   
       <!--3、整合SpringData Jpa-->
       <jpa:repositories base-package="com.yu.dao" transaction-manager-ref="transactionManager"
                         entity-manager-factory-ref="entityManagerFactory"></jpa:repositories>
   
       <!--4、配置事务管理器-->
       <bean id="transactionManager" class="org.springframework.orm.jpa.JpaTransactionManager">
           <property name="entityManagerFactory" ref="entityManagerFactory"></property>
       </bean>
   
       <!--5、配置声明式事务-->
   
       <!--配置包扫描-->
       <context:component-scan base-package="com.yu"/>
   
   </beans>
   ```

3. 编写符合SpringData Jpa规范的dao层接口

   ```java
   /**
    * 符合SpringData Jpa的dao层接口规范
    *          JpaRepository<操作的实体类类型,实体类中主键属性的类型>,封装了基本的CRUD操作
    *          JpaSpecificationExecutor<操作的实体类类型>,封装了复杂查询(分页)
    *
    */
   public interface CustomerDao extends JpaRepository<Customer,Long>, JpaSpecificationExecutor<Customer> {
   }
   ```

4. 编写测试类

   - 根据id查询

     ```java
     /**
      * @ClassName SpringDataJpaTest
      * @Description TODO
      * @Author yu
      * @Date 2021/4/3 18:46
      */
     @RunWith(SpringJUnit4ClassRunner.class)//声明Spring提供的单元测试环境
     @ContextConfiguration(locations = "classpath:applicationContext.xml")//指定Spring容器的配置信息
     public class SpringDataJpaTest {
     
         @Autowired
         private CustomerDao customerDao;
     
         /**
          * findOne(id)
          * 根据id查询
          */
         @Test
         public void testFindOne(){
             Customer customer = customerDao.findOne(2l);
             System.out.println(customer);
         }
     }
     ```

   - 保存或更新

     ```java
     /**
          * save(Customer)：保存或更新
          *      如果没有id主键属性：保存
          *      存在id主键属性,根据id查询数据,然后更新数据
          */
         @Test
         public void testSave(){
             Customer customer = new Customer();
             customer.setCustName("中公教育");
             customer.setCustLevel("vip");
             customer.setCustIndustry("培训");
             customerDao.save(customer);
         }
     
     		/**
          * save(Customer)
          */
         @Test
         public void testUpdate(){
             Customer customer = new Customer();
             customer.setCustId(8l);
             customer.setCustName("望子成龙");
             customerDao.save(customer);
         }
     
     ```

   - 根据id删除

     ```java
     /**
          * delete(id)
          * 根据id删除
          */
         @Test
         public void testDelete(){
             customerDao.delete(6l);
         }
     ```

   - 查询所有数据

     ```java
     /**
          * findAll()
          * 查询所有数据
          */
         @Test
         public void testFindAll(){
             List<Customer> customerList = customerDao.findAll();
             for (Customer customer : customerList) {
                 System.out.println(customer);
             }
         }
     ```

##### 5.4、SpringData JPA运行原理

1. 通过JdkDynamicAopProxy的invoke方法创建了一个动态代理对象SimpleJpaRepository
2. SimpleJpaRepository实现了JpaRepository， JpaSpecificationExecutor接口
3. SimpleJpaRepository当中封装了JPA的操作(借助JPA的API完成数据库的CRUD操作)
4. findOne()：通过entityManager完成查询操作
5. 通过Hibernate完成数据库操作(封装了JDBC)

##### 5.5、SpringData JPA的复杂操作

1. 常用API操作

   - 统计查询

   ```java
   /**
        * count()
        * 统计查询
        */
       @Test
       public void testCount(){
           long count = customerDao.count();
           System.out.println(count);
       }
   ```

   - 根据id查询某条数据是否存在

   ```java
   /**
        * exists(id)
        * 根据id查询某条数据是否存在
        */
       @Test
       public void testExists(){
           boolean exists = customerDao.exists(5l);
           System.out.println(exists);
       }
   ```

   - 根据id从数据库查询

   ```java
   /**
        * 根据id从数据库查询
        * @Transactional：保证getOne(id)正常执行
        *
        * findOne(id)：
        *      em.find()           立即加载
        *
        * getOne(id)：
        *      em.getReference()   延迟加载
        *      返回的是一个对象的动态代理对象
        *      什么时候用,什么时候查询
        */
       @Test
       @Transactional
       public void testGetOne(){
           Customer customer = customerDao.getOne(4l);
           System.out.println(customer);
       }
   ```

2. jpql的查询方式

   jpql：jpa query languge(jpq查询语言)

   特点：语法或关键字与sql语句相似，查询的是类和类中的属性

   需要将JPQL语言配置到接口方法上

   - 自定义查询：需要在dao接口上配置方法
   - 在新添加的方法上，使用注解的方式配置jpql查询语言
   - 注解：@Query

3. 自定义jpql查询方法

   ```java
   public interface CustomerDao extends JpaRepository<Customer,Long>, JpaSpecificationExecutor<Customer> {
       /**
        * 自定义查询方法,使用@Query注解
        * @param custName
        * @return
        */
       @Query(value = "from Customer where custName = ?")
       public Customer findCustByName(String custName);
       /**
        * 对于多个占位符赋值的时候,占位符的位置要和方法参数中的位置保持一致
        * 也可以指定占位符参数的位置
        *        ?索引的方式指定此占位符的来源
        * @param custName
        * @param custId
        * @return
        */
       @Query(value = "from Customer where custName = ?2 and custId = ?1")
       public Customer findCustByNameAndId(Long custId,String custName);
       /**
        * 根据id更新
        * @param custId
        * @param custName
        * @return
        * @Query：代表的是进行查询
        * @Modifying：当前执行的是一个更新操作
        */
       @Query(value = "update Customer set custName = ?2 where custId = ?1")
       @Modifying
       public void updateCustomer(Long custId,String custName);
   }
   
   ```

   ```java
   @RunWith(SpringJUnit4ClassRunner.class)//声明Spring提供的单元测试环境
   @ContextConfiguration(locations = "classpath:applicationContext.xml")//指定Spring容器的配置信息
   public class JpqlTest {
       @Autowired
       private CustomerDao customerDao;
       /**
        * 根据名称查询
        */
       @Test
       public void testFindCustomerByName(){
           Customer customer = customerDao.findCustByName("小米");
           System.out.println(customer);
       }
       /**
        * 根据名称和id查询
        */
       @Test
       public void testFindCustByNameAndId(){
           Customer customer = customerDao.findCustByNameAndId(1l,"小米");
           System.out.println(customer);
       }
       /**
        * 根据id更新用户名
        * SpringData Jpa中使用jpql完成更新/删除操作
        *                  默认无返回值
        *                  需要添加事务的支持
        *                  默认会在方法执行之后回滚事务
        */
       @Transactional
       @Rollback(value = false)
       @Test
       public void testUpdateCustomer(){
           customerDao.updateCustomer(1l, "雷军");
       }
   }
   ```

4. 自定义sql查询

   - 自定义查询：需要在dao接口上配置方法

   - 在新添加的方法上，使用注解的方式配置jpql查询语言

   - 注解：@Query

     ​			value：jpql语句|sql语句

     ​			nativeQuery：false（使用jpql查询，默认值）| true（使用本地查询：sql查询）

   ```java
   /**
        * 使用sql的方式查询全部
        * sql：select * from cst_customer
        * Query：配置sql查询
        *   value：sql语句
        *   nativeQuery：查询方式
        *          true：sql查询
        *          false：jpql查询
        * @return
        */
       @Query(value = "select * from cst_customer",nativeQuery = true)
       public List<Object[] > findAllBySql();
       /**
        * 根据名称模糊查询
        * @return
        */
       @Query(value = "select * from cst_customer where cust_name like ?1",nativeQuery = true)
       public List<Object[] > findAllBySqlAndName(String custName);
   ```

   ```java
   //使用sql语句查询全部
       @Test
       public void testFindAllBySql(){
           List<Object[]> list = customerDao.findAllBySql();
           for (Object[] objects : list) {
               System.out.println(Arrays.toString(objects));
           }
       }
       //使用sql语句模糊查询
       @Test
       public void testFindAllBySqlAndName(){
           List<Object[]> list = customerDao.findAllBySqlAndName("华%");
           for (Object[] objects : list) {
               System.out.println(Arrays.toString(objects));
           }
       }
   ```

5. 根据方法命名规则查询

   ```java
   /**
        * 方法名的约定
        * findBy：查询
        * 对象中的属性名(首字母大写)：查询的条件
        * CustName
        * findByCustName：根据客户名称查询
        *
        * 在SpringData Jpa的运行阶段，会根据方法名解析 findBy     from xxx(实体类)
        *                                          属性名称    where custName =
        * @param custName
        * @return
        */
       public Customer findByCustName(String custName);
   
       /**
        * findBy + 属性名称 (根据属性名称进行匹配查询=)
        * findBy + 属性名称 + '查询方式(Like | isnull等)'
        * @param custName
        * @return
        */
       public List<Customer> findByCustNameLike(String custName);
   
   		/**
        * 多条件查询
        * findBy + 属性名 + '查询方式' + '多条件的连接符(and|or)' + 属性名 + '查询方式'
        * @param custName
        * @param custIndustry
        * @return
        */
       public List<Customer> findByCustNameLikeAndCustIndustry(String custName,String custIndustry);
   ```

   ```java
    //使用方法名的约定规则查询
       @Test
       public void testFindByCustName(){
           Customer customer = customerDao.findCustByName("华为");
           System.out.println(customer);
       }
       //使用方法名的约定规则模糊查询
       @Test
       public void testFindByCustNameLike(){
           List<Customer> list = customerDao.findByCustNameLike("华%");
           for (Customer customer : list) {
               System.out.println(customer);
           }
       }
       //使用方法名的约定规则多条件查询
       @Test
       public void testFindByCustNameLikeAndCustIndustry(){
           List<Customer> list = customerDao.findByCustNameLikeAndCustIndustry("华%","通信");
           for (Customer customer : list) {
               System.out.println(customer);
           }
       }
   ```

### 6、Specifications动态查询

JpaSpecificationExecutor接口中的方法

- T findOne(Specification<T> spec);//查询单个对象
- List<T> findAll(Specification<T> spec);查询列表
- Page<T> findAll(Specification<T> spec, Pageable pageable);
  - 查询列表并分页
  - Pageable ：分页参数
  - 返回值：分页pageBean(page：是SpringData Jpa提供的)
- List<T> findAll(Specification<T> spec, Sort sort);//查询列表并排序
- long count(Specification<T> spec);//统计查询
- Specification：查询条件
  - 需要自定义我们的Specification实现类
  - Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb);
  - root：查询的根对象(查询的任何属性都可以从根对象中获取)
  - CriteriaQuery：顶层查询对象，自定义查询方式(了解：一般不用)
  - CriteriaBuilder：查询的构造器，封装了很多的查询条件

```java
/**
     * 根据条件,查询单个对象
     */
    @Test
    public void testSpecFindOne(){
        /**
         * 匿名内部类
         *
         * 自定义查询条件
         *      1.实现Specification接口(提供泛型,查询的对象类型)
         *      2.实现toPredicate方法(构造查询条件)
         *      3.需要借助方法参数中的两个参数(root：获取需要查询的对象属性
         *                                CriteriaBuilder：构造查询条件,内部封装了很多查询条件(模糊匹配,精准匹配)
         *                                )
         */
        Specification<Customer> spec = new Specification<Customer>() {
            /**
             *
             * @param root
             * @param query
             * @param cb
             * @return
             *
             * 根据名称查询对象
             *       查询条件
             *          1.查询方式
             *          cb对象
             *          2.比较的属性名称
             *          root对象
             */
            @Override
            public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                //1.获取比较的属性
                Path<Object> custName = root.get("custName");
                /**
                 * 2.构造查询条件
                 * 第一个参数:需要比较的属性(path对象)
                 * 第二个参数:当前需要比较参数的值
                 */
                Predicate predicate = cb.equal(custName, "阿里");
                return predicate;
            }
        };
        Customer customer = customerDao.findOne(spec);
        System.out.println(customer);
    }

/**
     * 多条件查询
     */
    @Test
    public void testFindCustByCustNameAndCustIndustry(){
        Specification<Customer> spec = new Specification<Customer>() {
            @Override
            public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                Path<Object> custName = root.get("custName");
                Path<Object> custIndustry = root.get("custIndustry");
                Predicate p1 = cb.equal(custName, "华为");
                Predicate p2 = cb.equal(custIndustry, "通信");
                //使用and或or组合查询条件
                Predicate and = cb.and(p1, p2);
//                cb.or(p1,p2);
                return and;
            }
        };

        Customer customer = customerDao.findOne(spec);
        System.out.println(customer);
    }


/**
     * 模糊查询
     * equal:直接得到path对象,然后进行比较即可
     * gt,lt,ge,le,like:得到path对象,根据path对象指定比较的参数类型,再去进行比较
     *              指定参数类型:path.as(类型的字节码对象)
     */
    @Test
    public void testFindCustByCustNameLike(){
        Specification<Customer> spec = new Specification<Customer>() {
            @Override
            public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                Path<Object> custName = root.get("custName");
                //模糊查询
                Predicate like = cb.like(custName.as(String.class), "华%");
                return like;
            }
        };

         /*List<Customer> list = customerDao.findAll(spec);
        for (Customer customer : list) {
            System.out.println(customer);
        }*/

        /**
         * 创建排序对象,需要调用构造方法实例化sort对象
         * 第一个参数:排序顺序(升序|降序),Sort.Direction.DESC(降序),Sort.Direction.ASC(升序)
         * 第二个参数:排序的属性名称
         */
        Sort sort = new Sort(Sort.Direction.DESC,"custId");
        List<Customer> all = customerDao.findAll(spec, sort);
        for (Customer customer : all) {
            System.out.println(customer);
        }
    }

/**
     * 分页查询
     *      Specification:查询条件
     *      Pageable:分页参数
     *          findAll(Specification,Pageable):带有条件的分页,返回page对象
     *          findAll(Pageable):没有条件的分页,返回page对象
     *
     */
    @Test
    public void testSpecificationPage(){
        Specification spec = null;
        /**
         * PageRequest是Pageable的实现类
         * PageRequest(page,size)
         * page:当前查询的页数
         * size:每页查询的数量
         */
        Pageable pageable = new PageRequest(0,2);
        Page<Customer> page = customerDao.findAll(null, pageable);
        System.out.println(page.getContent());//得到数据集合列表
        System.out.println(page.getTotalElements());//得到总条数
        System.out.println(page.getTotalPages());//得到总页数
    }


```



### 7、一对多和多对一的配置

```java
@Entity
@Table(name = "cst_customer")
public class Customer {
/**
     * 使用注解的方式配置多表关系
     *      1.声明关系
     *          @OneToMany:配置一对多关系
     *              targetEntity:对方对象的字节码
     *      2.配置外键(中间表)
     *          @JoinColumn:配置外键
     *              name:外键字段名称
     *              referencedColumnName:参照的主表的主键字段名称
     */
  
    //    @OneToMany(targetEntity = LinkMan.class)
		//    @JoinColumn(name = "lkm_cust_id",referencedColumnName = "cust_id")
    /**
     * 放弃外键维护权
     *      mappedBy:对方配置关系的属性名称
     */
    @OneToMany(mappedBy = "customer")

    private Set<LinkMan> linkMEN = new HashSet<>();
}
```

```java
@Entity
@Table(name = "cst_linkman")
public class LinkMan {
 /**
     * 配置联系人到客户的多对一关系
     */
    @ManyToOne(targetEntity = Customer.class)
    @JoinColumn(name = "lkm_cust_id",referencedColumnName = "cust_id")
    private Customer customer;
}
```

```xml
<!--1、创建entityManager对象交由spring容器管理-->
    <bean id="entityManagerFactory" class="org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean">
        <property name="dataSource" ref="dataSource"/>
        <!--配置实体类所在的包扫描-->
        <property name="packagesToScan" value="com.yu.entity"/>
        <!--Jpa的实现厂家-->
        <property name="persistenceProvider">
            <bean class="org.hibernate.jpa.HibernatePersistenceProvider"/>
        </property>

        <!--
            注入jpa的配置信息,自动创建数据库表
        -->
        <property name="jpaProperties">
            <props>
                <prop key="hibernate.hbm2ddl.auto">create</prop>
            </props>
        </property>
    </bean>
```

```java
		@Test
    @Transactional//配置事务
    @Rollback(false)//不自动回滚
    public void testSaveCustomer(){
        Customer customer = new Customer();
        customer.setCustName("绝世好剑");

        LinkMan linkMan = new LinkMan();
        linkMan.setLkmName("步惊云");

        customer.getLinkMEN().add(linkMan);

        customerDao.save(customer);
        linkManDao.save(linkMan);
    }

@Test
    @Transactional//配置事务
    @Rollback(false)//不自动回滚
    public void testSaveLinkMan(){
        Customer customer = new Customer();
        customer.setCustName("绝世好剑");
        LinkMan linkMan = new LinkMan();
        linkMan.setLkmName("步惊云");
        linkMan.setCustomer(customer);
        customerDao.save(customer);
        linkManDao.save(linkMan);
    }

    @Test
    @Transactional//配置事务
    @Rollback(false)//不自动回滚
    public void testSaveLinkManAndCustomer(){
        Customer customer = new Customer();
        customer.setCustName("绝世好剑");
        LinkMan linkMan = new LinkMan();
        linkMan.setLkmName("步惊云");
        customer.getLinkMEN().add(linkMan);
        linkMan.setCustomer(customer);
        customerDao.save(customer);
        linkManDao.save(linkMan);
    }
```

##### 7.1、级联操作及其配置

级联操作步骤 :

- 需要区分操作主体
- 需要在操作主体的实体类上，添加级联属性(需要添加到多表映射关系的注解上)
- cascade(配置级联)

```java
/**
     * 放弃外键维护权
     *      mappedBy:对方配置关系的属性名称
     *      cascade:配置级联操作
     */
    @OneToMany(mappedBy = "customer",cascade = CascadeType.ALL)
    private Set<LinkMan> linkMEN = new HashSet<>();
```

```xml
<!--
            注入jpa的配置信息,自动创建数据库表
        -->
        <property name="jpaProperties">
            <props>
                <prop key="hibernate.hbm2ddl.auto">update</prop>
            </props>
        </property>
    </bean>
```

```java
/**
     * 级联添加
     */
    @Test
    @Transactional//配置事务
    @Rollback(false)//不自动回滚
    public void testCascadedAdd(){
        Customer customer = new Customer();
        customer.setCustName("绝世好剑");

        LinkMan linkMan = new LinkMan();
        linkMan.setLkmName("步惊云");

        customer.getLinkMEN().add(linkMan);
        customerDao.save(customer);
    }
    /**
     * 级联删除
     */
    @Test
    @Transactional//配置事务
    @Rollback(false)//不自动回滚
    public void testCascadedDelete(){
        Customer customer = customerDao.findOne(1l);
        customerDao.delete(customer);
    }
```

### 8、多对多的配置

```java
public class User{
/**
     * 配置多对多之间的映射关系
     *      1.声明表关系的配置
     *          @ManyToMany(targetEntity = Role.class) //多对多
     *              targetEntity:代表对方的实体类字节码
     *      2.配置中间表(包含两个外键)
     *          @JoinTable
     *              name:中间表的名称
     *              joinColumns:配置当前对象在中间表的外键
     *                  @JoinColumn的数组
     *                      name:外键名
     *                      referencedColumnName:参照的主表的主键名
     *
     */
    @ManyToMany(targetEntity = Role.class)
    @JoinTable(name = "sys_user_role",
                //joinColumns:当前对象在中间表的外键
                joinColumns = {@JoinColumn(name = "sys_user_id",referencedColumnName = "user_id")},
                //inverseJoinColumns:对方对象在中间表的外键
                inverseJoinColumns = {@JoinColumn(name = "sys_role_id",referencedColumnName = "role_id")})
    private Set<Role> roles = new HashSet<>();
}
```

```java
public class Role{
/**
     * 放弃对联合主键的维护
     */
    @ManyToMany(mappedBy = "roles")
    private Set<User> users = new HashSet<>();
}
```

```java
		@Test
    @Transactional
    @Rollback(false)
    public void testSaveUserAndRole(){
        User user = new User();
        user.setUserName("张三");
        Role role = new Role();
        role.setRoleName("Java开发工程师");

        user.getRoles().add(role);
        role.getUsers().add(user);

        userDao.save(user);
        roleDao.save(role);
    }
```

##### 8.1、多对多的级联操作

```java
@ManyToMany(targetEntity = Role.class,cascade = CascadeType.ALL)
```

```java
 /**
     * 级联添加
     */
    @Test
    @Transactional
    @Rollback(false)
    public void testCascadedAdd(){
        User user = new User();
        user.setUserName("李四");
        Role role = new Role();
        role.setRoleName("Python开发工程师");
        user.getRoles().add(role);
        role.getUsers().add(user);
        userDao.save(user);
    }
    /**
     * 级联删除
     */
    @Test
    @Transactional
    @Rollback(false)
    public void testCascadedDelete(){
        User user = userDao.findOne(1l);
        userDao.delete(user);
    }
```









