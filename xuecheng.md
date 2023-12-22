## 1、项目背景

### 1.1 在线教育市场环境

以下内容来自于https://report.iresearch.cn/content/2021/01/358854.shtml

在线教育行业是一个有着极强的广度和深度的行业，从校内到校外，从早幼教到职业培训，从教育工具到全信息平台等。

2020年的新冠疫情外生冲击，让在线教育再次站在聚光灯下。疫情下教育领域获融资最多，而其中在线教育最受资本青睐。据艾瑞咨询统计，2020年教育行业累计融资1164亿元，其中在线教育融资金额1034亿元，占比89%。与此同时，在行业处于困境的情况下，会加速洗牌，资源向好的企业集中。2020年资源向头部集中趋势明显，中小型机构生存更加困难。2020年资本向在线教育行业累计输送的1034亿元中，80%都流向了头部的5家公司。

**To C市场快速发展**

据艾瑞咨询统计核算，2020年中国在线教育行业市场规模2573亿元，过去4年的CAGR达34.5%，其中低幼及素质教育赛道、K12学科培训赛道在线化进程加快是在线教育市场快速增长的最主要贡献因素。疫情影响下，低幼及素质教育领域的在线化范围持续纵深，职业教育领域的在线化进程也在不断加速，新的供给和需求不断产生。但同时，2020年疫情外生冲击加快了2020年的在线教育进程，将会透支一部分2021年的增速，艾瑞预计2021年在线教育行业同比增速将回落到20%左右。

![image-20230914194617881](https://image.elonlo.top/img/2023/09/14/6502f2908bf6b.webp)

**To B市场空间被打开**

疫情也加速了整个教育产业链的进化，to B机构快速成长起来，扮演着赋能者的角色，课程内容、招生、师训、直播系统、管理系统等产品及服务大量涌现。随着云服务发展成熟以及疫情对直播课需求的催化，大量提供直播授课系统等PaaS/SaaS服务的机构迅速成长起来，成为各种会展上的主力军。

![image-20230914194723871](https://image.elonlo.top/img/2023/09/14/6502f2cb54d67.webp)

不同赛道上，参与者的盈利模式亦有很大不同，综合来看，上游供应商主要收取订阅/课时/采购费，市场主体以课时费为主，平台型主体和流量平台主要收取广告费和抽成。

![image-20230914194838684](https://image.elonlo.top/img/2023/09/14/6502f3161eabe.webp)

### 1.2 项目背景

本项目是本公司自研的一个专门针对成人职业技能教育的网络课堂系统，网站提供了成人职业技能的培训相关课程，如：软件开发培训、职业资格证书培训、成人学历教育培训等课程。项目基于B2B2C的业务模式，培训机构可以在平台入驻、发布课程，运营人员对发布的课程进行审核，审核通过后课程才可以发布成功，课程包括免费和收费两种模式，对于免费课程可以直接选课学习，对于收费课程在选课后需要支付成功后才可以继续学习。

**什么是B2B2C？**

B2B2C是以一种电子商务类型的网络购物商业模式，B是business的简称，C是consumer的简称，第一个B指的是商品或服务的供应商，第二个B指的是从事电子商务的企业，C则表示消费者。

B2B：企业于企业之间的电子商务运作模式。

B2C：企业与消费者之间的电子商务运作模式。

## 2、项目介绍

### 2.1 项目概括

本项目包括了用户端、机构端、运营端。

核心模块包括：内容管理、媒资管理、课程搜索、订单支付、选课管理、认证授权等。

用户端：包含登录/注册、课程搜索、课程推荐、视频学习、课程评论、课程问答、学习作业、我的考试、我的订单、个人中心

机构端：入驻加盟、内容管理、教师管理、作业管理、考试管理、评论管理、订单管理、媒资管理、统计分析、活动管理

运营端：机构管理、分类管理、课程审核、媒资审核、题目审核、用户管理、统计分析、权限配置、财务管理、系统管理

支撑系统：消息系统、学习社区、问答系统、评论系统、权限系统、推荐系统

本项目采用前后端分离架构，后端采用Spring Boot、Spring Cloud技术栈开发，数据库使用了MySQL，Redis、消息队列、分布式文件系统、Elasticsearch等中间件系统。

划分的微服务有：内容管理服务、媒资管理服务、搜索服务、订单支付服务、学习中心服务、系统管理服务、认证授权服务、网关服务、注册中心服务、配置中心服务等。

### 2.2 功能模块与演示

本项目包括了用户端、机构端、运营端，核心模块包括：内容管理、媒资管理、课程搜索、订单支付、选课管理、认证授权等。

本项目主要包括三类用户角色：学生、教学机构的老师、平台运营人员。

主要业务流程如下：

1、教学机构的老师登录教学管理平台，编辑课程信息，发布自己的课程。

2、平台运营人员登录运营平台审核课程、视频等信息，审核通过后课程方可发布。

3、课程编辑与发布流程如下：添加课程→上传课程图片→课程营销信息→课程计划→媒资管理→课程师资信息→课程预览→提交审核→课程发布。

4、课程发布后学生登录平台进行选课、在线学习。免费课程可直接学习，收费课程需要下单购买。

5、学生选课流程如下：进入首页→浏览商品→课程列表→课程详情→在线学习→购买课程→订单详情→支付页面→支付结果→开始学习。

### 2.3 技术栈

视图：H5、Vue/React、Mobile Native

接入：Gateway、Ribbon、Feign、Nginx、Jwt/Oauth2.0

组件：Spring Boot、Druid、Lombok、Log4j2、Guava、Mybatis-Plus、sharding-jdbc、Swagger、XXL-JOB、Freemarker

基础设施：MySQL、RabbitMQ、Redis、Elasticsearch、Minio

DevOps：Maven、Nacos、Git/Gitee、Jenkins、Docker、zipkin、Sentinel、ELK

## 3、项目搭建

|   开发工具    |       版本号       | 安装位置 |
| :-----------: | :----------------: | :------: |
| IntelliJ-IDEA |   2021.x以上版本   | 个人电脑 |
|      JDK      |       1.8.x        | 个人电脑 |
|     Maven     |    3.6.x、3.8.x    | 个人电脑 |
|      Git      |       2.37.x       | 个人电脑 |
|    VmWare     |       15.5.x       | 个人电脑 |
|    CentOS     |        7.x         |  虚拟机  |
|    Docker     |      18.09.0       |  虚拟机  |
|     MySQL     |        8.x         |  docker  |
|     Nacos     |       1.4.1        |  docker  |
|   RabbitMQ    |       3.8.34       |  docker  |
|     Redis     |       6.2.7        |  docker  |
| xxl-job-admin |       2.3.1        |  docker  |
|     Minio     | RELEASE.2022-09-07 |  docker  |
| Elasticsearch |       7.12.1       |  docker  |
|    kibana     |       7.12.1       |  docker  |
|     gogs      |       0.13.0       |  docker  |
|     Nginx     |       1.12.2       |  docker  |

## 4、环境搭建

### 4.1 项目整体架构

该项目使用Maven来进行项目的管理和构建。整个项目分为三大类工程：父工程、基础工程和微服务工程。

![xuecheng](https://image.elonlo.top/img/2023/09/17/650715e866369.webp)

### 4.2 创建模块工程

1. 创建maven父工程`xuecheng-plus`

2. 在`xuecheng-plus`目录下分别创建`xuecheng-plus-parent`和`xuecheng-plus-base`两个maven模块工程

3. 删除`xuecheng-plus`父工程的其他文件和目录，只保留`pom.xml`，并在`pom.xml`中添加以下内容：

   ```xml
   <?xml version="1.0" encoding="UTF-8"?>
   <project xmlns="http://maven.apache.org/POM/4.0.0"
            xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
            xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
       <modelVersion>4.0.0</modelVersion>
   
       <groupId>com.yu.xuecheng</groupId>
       <artifactId>xuecheng-plus</artifactId>
       <packaging>pom</packaging>
       <version>1.0-SNAPSHOT</version>
       <modules>
           <module>xuecheng-plus-base</module>
           <module>xuecheng-plus-content</module>
       </modules>
   
       <properties>
           <java.version>1.8</java.version>
           <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
           <project.reporting.outputEncoding>UTF-8</project.reporting.outputEncoding>
           <spring-boot.version>2.3.7.RELEASE</spring-boot.version>
           <spring-cloud.version>Hoxton.SR9</spring-cloud.version>
           <org.mapstruct.version>1.3.1.Final</org.mapstruct.version>
           <spring-cloud-alibaba.version>2.2.6.RELEASE</spring-cloud-alibaba.version>
           <org.projectlombok.version>1.18.8</org.projectlombok.version>
           <javax.servlet-api.version>4.0.1</javax.servlet-api.version>
           <fastjson.version>1.2.83</fastjson.version>
           <druid-spring-boot-starter.version>1.2.8</druid-spring-boot-starter.version>
           <mysql-connector-java.version>8.0.30</mysql-connector-java.version>
           <mybatis-plus-boot-starter.version>3.4.1</mybatis-plus-boot-starter.version>
           <commons-lang.version>2.6</commons-lang.version>
           <minio.version>8.4.3</minio.version>
           <xxl-job-core.version>2.3.1</xxl-job-core.version>
           <commons-lang3.version>3.10</commons-lang3.version>
           <myOkhttp.version>4.8.1</myOkhttp.version>
           <knife4j.version>3.0.3</knife4j.version>
           <hutool-all.version>5.7.3</hutool-all.version>
           <elasticsearch.version>7.12.1</elasticsearch.version>
           <guava.version>25.1-jre</guava.version>
       </properties>
   
       <!-- 注意先要在dependencies中导入依赖后再将dependencies加入到dependencyManagement中 -->
       <dependencyManagement>
           <dependencies>
               <dependency>
                   <groupId>org.springframework.cloud</groupId>
                   <artifactId>spring-cloud-dependencies</artifactId>
                   <version>${spring-cloud.version}</version>
                   <type>pom</type>
                   <scope>import</scope>
               </dependency>
   
               <dependency>
                   <groupId>org.springframework.boot</groupId>
                   <artifactId>spring-boot-dependencies</artifactId>
                   <version>${spring-boot.version}</version>
                   <type>pom</type>
                   <scope>import</scope>
               </dependency>
   
               <dependency>
                   <groupId>com.alibaba.cloud</groupId>
                   <artifactId>spring-cloud-alibaba-dependencies</artifactId>
                   <version>${spring-cloud-alibaba.version}</version>
                   <type>pom</type>
                   <scope>import</scope>
               </dependency>
   
               <dependency>
                   <groupId>org.projectlombok</groupId>
                   <artifactId>lombok</artifactId>
                   <version>${org.projectlombok.version}</version>
               </dependency>
   
               <dependency>
                   <groupId>org.mapstruct</groupId>
                   <artifactId>mapstruct</artifactId>
                   <version>${org.mapstruct.version}</version>
               </dependency>
   
               <dependency>
                   <groupId>com.github.xiaoymin</groupId>
                   <artifactId>knife4j-spring-boot-starter</artifactId>
                   <version>${knife4j.version}</version>
               </dependency>
   
               <dependency>
                   <groupId>javax.servlet</groupId>
                   <artifactId>javax.servlet-api</artifactId>
                   <version>${javax.servlet-api.version}</version>
                   <scope>provided</scope>
               </dependency>
   
               <dependency>
                   <groupId>com.alibaba</groupId>
                   <artifactId>fastjson</artifactId>
                   <version>${fastjson.version}</version>
               </dependency>
   
               <dependency>
                   <groupId>com.alibaba</groupId>
                   <artifactId>druid-spring-boot-starter</artifactId>
                   <version>${druid-spring-boot-starter.version}</version>
               </dependency>
   
               <dependency>
                   <groupId>mysql</groupId>
                   <artifactId>mysql-connector-java</artifactId>
                   <version>${mysql-connector-java.version}</version>
               </dependency>
   
               <dependency>
                   <groupId>com.baomidou</groupId>
                   <artifactId>mybatis-plus-boot-starter</artifactId>
                   <version>${mybatis-plus-boot-starter.version}</version>
               </dependency>
   
               <dependency>
                   <groupId>commons-lang</groupId>
                   <artifactId>commons-lang</artifactId>
                   <version>${commons-lang.version}</version>
               </dependency>
   
               <dependency>
                   <groupId>org.apache.commons</groupId>
                   <artifactId>commons-lang3</artifactId>
                   <version>${commons-lang3.version}</version>
               </dependency>
   
               <dependency>
                   <groupId>io.minio</groupId>
                   <artifactId>minio</artifactId>
                   <version>${minio.version}</version>
               </dependency>
   
               <dependency>
                   <groupId>com.google.guava</groupId>
                   <artifactId>guava</artifactId>
                   <version>${guava.version}</version>
               </dependency>
   
               <dependency>
                   <groupId>com.xuxueli</groupId>
                   <artifactId>xxl-job-core</artifactId>
                   <version>${xxl-job-core.version}</version>
               </dependency>
   
               <dependency>
                   <groupId>cn.hutool</groupId>
                   <artifactId>hutool-all</artifactId>
                   <version>${hutool-all.version}</version>
               </dependency>
   
               <dependency>
                   <groupId>com.squareup.okhttp3</groupId>
                   <artifactId>okhttp</artifactId>
                   <version>${myOkhttp.version}</version>
               </dependency>
   
               <dependency>
                   <groupId>org.elasticsearch.client</groupId>
                   <artifactId>elasticsearch-rest-high-level-client</artifactId>
                   <version>${elasticsearch.version}</version>
               </dependency>
   
               <dependency>
                   <groupId>org.elasticsearch</groupId>
                   <artifactId>elasticsearch</artifactId>
                   <version>${elasticsearch.version}</version>
               </dependency>
   
               <dependency>
                   <groupId>org.springframework.boot</groupId>
                   <artifactId>spring-boot-starter-test</artifactId>
                   <version>${spring-boot.version}</version>
                   <scope>test</scope>
                   <exclusions>
                       <exclusion>
                           <groupId>org.junit.jupiter</groupId>
                           <artifactId>junit-jupiter-engine</artifactId>
                       </exclusion>
                   </exclusions>
               </dependency>
           </dependencies>
       </dependencyManagement>
   
       <build>
           <finalName>${project.name}</finalName>
           <!-- 编译打包过滤配置 -->
           <resources>
               <resource>
                   <directory>src/main/java</directory>
                   <includes>
                       <include>**/*.xml</include>
                   </includes>
               </resource>
   
               <resource>
                   <directory>src/main/resources</directory>
                   <includes>
                       <include>**/*</include>
                   </includes>
                   <filtering>true</filtering>
               </resource>
           </resources>
   
           <plugins>
               <plugin>
                   <groupId>org.apache.maven.plugins</groupId>
                   <artifactId>maven-compiler-plugin</artifactId>
                   <version>3.8.1</version>
                   <configuration>
                       <!-- 指定项目源码jdk的版本 -->
                       <source>1.8</source>
                       <!-- 指定项目编译后的jdk的版本 -->
                       <target>1.8</target>
                       <!-- 配置注解预编译 -->
                       <annotationProcessorPaths>
                           <path>
                               <groupId>org.projectlombok</groupId>
                               <artifactId>lombok</artifactId>
                               <version>${org.projectlombok.version}</version>
                           </path>
                       </annotationProcessorPaths>
                   </configuration>
               </plugin>
   
               <!-- 处理项目资源文件并拷贝到输出目录,如果有额外的资源文件目录则需要配置 -->
               <plugin>
                   <groupId>org.apache.maven.plugins</groupId>
                   <artifactId>maven-resources-plugin</artifactId>
                   <version>3.3.0</version>
                   <configuration>
                       <encoding>utf-8</encoding>
                       <!-- 使用默认分隔符, resources中可以使用分隔符定义过滤的路径-->
                       <useDefaultDelimiters>true</useDefaultDelimiters>
                   </configuration>
               </plugin>
           </plugins>
       </build>
   </project>
   ```

4. 删除`xuecheng-plus-base`模块工程中的启动类和测试类以及resources目录下的配置文件和其他多余文件，并在`pom.xml`中添加以下内容：

   ```xml
   <?xml version="1.0" encoding="UTF-8"?>
   <project xmlns="http://maven.apache.org/POM/4.0.0"
            xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
            xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
       <parent>
           <artifactId>xuecheng-plus</artifactId>
           <groupId>com.yu.xuecheng</groupId>
           <version>1.0-SNAPSHOT</version>
       </parent>
       <modelVersion>4.0.0</modelVersion>
   
       <artifactId>xuecheng-plus-base</artifactId>
   
       <properties>
           <java.version>1.8</java.version>
       </properties>
   
       <dependencies>
           <dependency>
               <groupId>org.projectlombok</groupId>
               <artifactId>lombok</artifactId>
           </dependency>
   
           <dependency>
               <groupId>org.apache.commons</groupId>
               <artifactId>commons-lang3</artifactId>
           </dependency>
   
           <dependency>
               <groupId>com.alibaba</groupId>
               <artifactId>fastjson</artifactId>
           </dependency>
   
           <dependency>
               <groupId>javax.servlet</groupId>
               <artifactId>javax.servlet-api</artifactId>
               <scope>provided</scope>
           </dependency>
   
           <dependency>
               <groupId>commons-lang</groupId>
               <artifactId>commons-lang</artifactId>
           </dependency>
   
           <dependency>
               <groupId>commons-codec</groupId>
               <artifactId>commons-codec</artifactId>
           </dependency>
   
           <dependency>
               <groupId>org.springframework</groupId>
               <artifactId>spring-web</artifactId>
           </dependency>
   
           <dependency>
               <groupId>com.github.xiaoymin</groupId>
               <artifactId>knife4j-spring-boot-starter</artifactId>
               <exclusions>
                   <exclusion>
                       <artifactId>swagger-annotations</artifactId>
                       <groupId>io.swagger</groupId>
                   </exclusion>
               </exclusions>
           </dependency>
   
           <dependency>
               <groupId>org.springframework.boot</groupId>
               <artifactId>spring-boot-starter-validation</artifactId>
           </dependency>
   
           <dependency>
               <groupId>org.springframework.boot</groupId>
               <artifactId>spring-boot-starter-log4j2</artifactId>
           </dependency>
   
           <dependency>
               <groupId>com.google.zxing</groupId>
               <artifactId>core</artifactId>
               <version>3.3.3</version>
           </dependency>
   
           <dependency>
               <groupId>com.google.zxing</groupId>
               <artifactId>javase</artifactId>
               <version>3.3.3</version>
           </dependency>
   
           <dependency>
               <groupId>com.fasterxml.jackson.module</groupId>
               <artifactId>jackson-module-parameter-names</artifactId>
           </dependency>
   
           <dependency>
               <groupId>com.fasterxml.jackson.datatype</groupId>
               <artifactId>jackson-datatype-jdk8</artifactId>
           </dependency>
   
           <dependency>
               <groupId>com.fasterxml.jackson.datatype</groupId>
               <artifactId>jackson-datatype-jsr310</artifactId>
           </dependency>
       </dependencies>
   </project>
   ```

5. 删除`xuecheng-plus-content`模块工程多余文件，只保留`pom.xml`，并添加以下内容：

   ```xml
   <?xml version="1.0" encoding="UTF-8"?>
   <project xmlns="http://maven.apache.org/POM/4.0.0"
            xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
            xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
       <parent>
           <artifactId>xuecheng-plus</artifactId>
           <groupId>com.yu.xuecheng</groupId>
           <version>1.0-SNAPSHOT</version>
       </parent>
       <modelVersion>4.0.0</modelVersion>
   
       <artifactId>xuecheng-plus-content</artifactId>
       <packaging>pom</packaging>
   </project>
   ```

## 5、内容管理模块

### 5.1 模块工程结构

- xuecheng-plus-content（内容管理模块）
  - xuecheng-plus-content-api（内容管理接口模块）
  - xuecheng-plus-content-service（内容管理服务模块）
  - xuecheng-plus-content-model（内容管理数据模型模块）

### 5.2 创建模块工程

各模块工程的`pom.xml`文件如下：

**xuecheng-plus-content**

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <parent>
        <artifactId>xuecheng-plus</artifactId>
        <groupId>com.yu.xuecheng</groupId>
        <version>1.0-SNAPSHOT</version>
    </parent>
    <modelVersion>4.0.0</modelVersion>

    <artifactId>xuecheng-plus-content</artifactId>
    <packaging>pom</packaging>
    <modules>
        <module>xuecheng-plus-content-model</module>
        <module>xuecheng-plus-content-service</module>
        <module>xuecheng-plus-content-api</module>
    </modules>
</project>
```

**xuecheng-plus-content-model**

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <parent>
        <artifactId>xuecheng-plus-content</artifactId>
        <groupId>com.yu.xuecheng</groupId>
        <version>1.0-SNAPSHOT</version>
    </parent>
    <modelVersion>4.0.0</modelVersion>

    <artifactId>xuecheng-plus-content-model</artifactId>

    <dependencies>
        <dependency>
            <groupId>com.yu.xuecheng</groupId>
            <artifactId>xuecheng-plus-base</artifactId>
            <version>1.0-SNAPSHOT</version>
        </dependency>

        <!--存在mybatisplus注解添加相关注解保证不报错-->
        <dependency>
            <groupId>com.baomidou</groupId>
            <artifactId>mybatis-plus-annotation</artifactId>
            <version>${mybatis-plus-boot-starter.version}</version>
        </dependency>

        <dependency>
            <groupId>com.baomidou</groupId>
            <artifactId>mybatis-plus-core</artifactId>
            <version>${mybatis-plus-boot-starter.version}</version>
        </dependency>

        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
        </dependency>
    </dependencies>
</project>
```

**xuecheng-plus-content-service**

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <parent>
        <artifactId>xuecheng-plus-content</artifactId>
        <groupId>com.yu.xuecheng</groupId>
        <version>1.0-SNAPSHOT</version>
    </parent>
    <modelVersion>4.0.0</modelVersion>

    <artifactId>xuecheng-plus-content-service</artifactId>

    <dependencies>
        <dependency>
            <groupId>com.yu.xuecheng</groupId>
            <artifactId>xuecheng-plus-content-model</artifactId>
            <version>1.0-SNAPSHOT</version>
        </dependency>
        
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>

        <!-- MySQL 驱动 -->
        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <scope>runtime</scope>
        </dependency>

        <!-- mybatis plus的依赖 -->
        <dependency>
            <groupId>com.baomidou</groupId>
            <artifactId>mybatis-plus-boot-starter</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-context</artifactId>
        </dependency>
        <!-- Spring Boot 集成 Junit -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
            <exclusions>
                <exclusion>
                    <groupId>org.junit.vintage</groupId>
                    <artifactId>junit-vintage-engine</artifactId>
                </exclusion>
            </exclusions>
        </dependency>

        <!-- 新版Spring Boot必须手动引入junit -->
        <dependency>
            <groupId>org.junit.platform</groupId>
            <artifactId>junit-platform-launcher</artifactId>
            <scope>test</scope>
        </dependency>

        <dependency>
            <groupId>org.junit.jupiter</groupId>
            <artifactId>junit-jupiter-engine</artifactId>
            <scope>test</scope>
        </dependency>

        <!-- 排除 Spring Boot 依赖的日志包冲突 -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter</artifactId>
            <exclusions>
                <exclusion>
                    <groupId>org.springframework.boot</groupId>
                    <artifactId>spring-boot-starter-logging</artifactId>
                </exclusion>
            </exclusions>
        </dependency>

        <!-- Spring Boot 集成 log4j2 -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-log4j2</artifactId>
        </dependency>

        <!-- 必须引入swagger依赖,否则启动该项目会启动失败 -->
        <dependency>
            <groupId>com.github.xiaoymin</groupId>
            <artifactId>knife4j-spring-boot-starter</artifactId>
        </dependency>
    </dependencies>
</project>
```

**xuecheng-plus-content-api**

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
    <parent>
        <artifactId>xuecheng-plus-content</artifactId>
        <groupId>com.yu.xuecheng</groupId>
        <version>1.0-SNAPSHOT</version>
    </parent>
    <modelVersion>4.0.0</modelVersion>

    <artifactId>xuecheng-plus-content-api</artifactId>

    <dependencies>
        <dependency>
            <groupId>com.yu.xuecheng</groupId>
            <artifactId>xuecheng-plus-content-service</artifactId>
            <version>1.0-SNAPSHOT</version>
        </dependency>

        <!--cloud的基础环境包-->
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-context</artifactId>
        </dependency>

        <!-- Spring Boot 的 Spring Web MVC 集成 -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>

        <!-- 排除 Spring Boot 依赖的日志包冲突 -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter</artifactId>
            <exclusions>
                <exclusion>
                    <groupId>org.springframework.boot</groupId>
                    <artifactId>spring-boot-starter-logging</artifactId>
                </exclusion>
            </exclusions>
        </dependency>

        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-validation</artifactId>
        </dependency>

        <!-- Spring Boot 集成 log4j2 -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-log4j2</artifactId>
        </dependency>

        <dependency>
            <groupId>com.github.xiaoymin</groupId>
            <artifactId>knife4j-spring-boot-starter</artifactId>
        </dependency>
    </dependencies>
</project>
```

### 5.3 创建数据库和PO类

#### 5.3.1 创建数据库表

```sql
-- 创建内容管理相关数据库表
CREATE DATABASE IF NOT EXISTS `xuecheng-content` CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE `xuecheng-content`;

DROP TABLE IF EXISTS `course_audit`;

CREATE TABLE `course_audit` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `course_id` bigint NOT NULL COMMENT '课程id',
  `audit_mind` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '审核意见',
  `audit_status` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '审核状态',
  `audit_people` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '审核人',
  `audit_date` datetime DEFAULT NULL COMMENT '审核时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC;

DROP TABLE IF EXISTS `course_base`;

CREATE TABLE `course_base` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `company_id` bigint NOT NULL COMMENT '机构ID',
  `company_name` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '机构名称',
  `name` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '课程名称',
  `users` varchar(500) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '适用人群',
  `tags` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '课程标签',
  `mt` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '大分类',
  `st` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '小分类',
  `grade` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '课程等级',
  `teachmode` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '教育模式(common普通，record 录播，live直播等）',
  `description` text CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci COMMENT '课程介绍',
  `pic` varchar(500) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '课程图片',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `change_date` datetime DEFAULT NULL COMMENT '修改时间',
  `create_people` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '创建人',
  `change_people` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '更新人',
  `audit_status` varchar(10) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '审核状态',
  `status` varchar(10) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL DEFAULT '1' COMMENT '课程发布状态 未发布  已发布 下线',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=122 DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC COMMENT='课程基本信息';

DROP TABLE IF EXISTS `course_category`;

CREATE TABLE `course_category` (
  `id` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '主键',
  `name` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '分类名称',
  `label` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '分类标签默认和名称一样',
  `parentid` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL DEFAULT '0' COMMENT '父结点id（第一级的父节点是0，自关联字段id）',
  `is_show` tinyint DEFAULT NULL COMMENT '是否显示',
  `orderby` int DEFAULT NULL COMMENT '排序字段',
  `is_leaf` tinyint DEFAULT NULL COMMENT '是否叶子',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC COMMENT='课程分类';

DROP TABLE IF EXISTS `course_market`;

CREATE TABLE `course_market` (
  `id` bigint NOT NULL COMMENT '主键，课程id',
  `charge` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '收费规则，对应数据字典',
  `price` float(10,2) DEFAULT NULL COMMENT '现价',
  `original_price` float(10,2) DEFAULT NULL COMMENT '原价',
  `qq` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '咨询qq',
  `wechat` varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '微信',
  `phone` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '电话',
  `valid_days` int DEFAULT NULL COMMENT '有效期天数',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC COMMENT='课程营销信息';

DROP TABLE IF EXISTS `course_publish`;

CREATE TABLE `course_publish` (
  `id` bigint NOT NULL COMMENT '主键',
  `company_id` bigint NOT NULL COMMENT '机构ID',
  `company_name` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '公司名称',
  `name` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '课程名称',
  `users` varchar(500) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '适用人群',
  `tags` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '标签',
  `username` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '创建人',
  `mt` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '大分类',
  `mt_name` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '大分类名称',
  `st` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '小分类',
  `st_name` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '小分类名称',
  `grade` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '课程等级',
  `teachmode` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '教育模式',
  `pic` varchar(500) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '课程图片',
  `description` text CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci COMMENT '课程介绍',
  `market` text CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci COMMENT '课程营销信息，json格式',
  `teachplan` text CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci COMMENT '所有课程计划，json格式',
  `teachers` text CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci COMMENT '教师信息，json格式',
  `create_date` datetime DEFAULT NULL COMMENT '发布时间',
  `online_date` datetime DEFAULT NULL COMMENT '上架时间',
  `offline_date` datetime DEFAULT NULL COMMENT '下架时间',
  `status` varchar(10) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT '1' COMMENT '发布状态',
  `remark` varchar(500) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '备注',
  `charge` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '收费规则，对应数据字典--203',
  `price` float(10,2) DEFAULT NULL COMMENT '现价',
  `original_price` float(10,2) DEFAULT NULL COMMENT '原价',
  `valid_days` int DEFAULT NULL COMMENT '课程有效期天数',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC COMMENT='课程发布';

DROP TABLE IF EXISTS `course_publish_pre`;

CREATE TABLE `course_publish_pre` (
  `id` bigint NOT NULL COMMENT '主键',
  `company_id` bigint NOT NULL COMMENT '机构ID',
  `company_name` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '公司名称',
  `name` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '课程名称',
  `users` varchar(500) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '适用人群',
  `tags` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '标签',
  `username` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '创建人',
  `mt` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '大分类',
  `mt_name` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '大分类名称',
  `st` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '小分类',
  `st_name` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '小分类名称',
  `grade` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '课程等级',
  `teachmode` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '教育模式',
  `pic` varchar(500) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '课程图片',
  `description` text CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci COMMENT '课程介绍',
  `market` text CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci COMMENT '课程营销信息，json格式',
  `teachplan` text CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci COMMENT '所有课程计划，json格式',
  `teachers` text CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci COMMENT '教师信息，json格式',
  `create_date` datetime DEFAULT NULL COMMENT '提交时间',
  `audit_date` datetime DEFAULT NULL COMMENT '审核时间',
  `status` varchar(10) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT '1' COMMENT '状态',
  `remark` varchar(500) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '备注',
  `charge` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '收费规则，对应数据字典--203',
  `price` float(10,2) DEFAULT NULL COMMENT '现价',
  `original_price` float(10,2) DEFAULT NULL COMMENT '原价',
  `valid_days` int DEFAULT NULL COMMENT '课程有效期天数',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC COMMENT='课程发布';

DROP TABLE IF EXISTS `course_teacher`;

CREATE TABLE `course_teacher` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `course_id` bigint DEFAULT NULL COMMENT '课程标识',
  `teacher_name` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '教师标识',
  `position` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '教师职位',
  `introduction` varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '教师简介',
  `photograph` varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '照片',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `courseid_teacherId_unique` (`course_id`,`teacher_name`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC COMMENT='课程-教师关系表';

DROP TABLE IF EXISTS `mq_message`;

CREATE TABLE `mq_message` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '消息id',
  `message_type` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '消息类型代码: course_publish ,  media_test',
  `business_key1` varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '关联业务信息',
  `business_key2` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '关联业务信息',
  `business_key3` varchar(512) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '关联业务信息',
  `execute_num` int unsigned NOT NULL DEFAULT '0' COMMENT '通知次数',
  `state` char(1) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL DEFAULT '0' COMMENT '处理状态，0:初始，1:成功',
  `returnfailure_date` datetime DEFAULT NULL COMMENT '回复失败时间',
  `returnsuccess_date` datetime DEFAULT NULL COMMENT '回复成功时间',
  `returnfailure_msg` varchar(2048) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '回复失败内容',
  `execute_date` datetime DEFAULT NULL COMMENT '最近通知时间',
  `stage_state1` char(1) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL DEFAULT '0' COMMENT '阶段1处理状态, 0:初始，1:成功',
  `stage_state2` char(1) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL DEFAULT '0' COMMENT '阶段2处理状态, 0:初始，1:成功',
  `stage_state3` char(1) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL DEFAULT '0' COMMENT '阶段3处理状态, 0:初始，1:成功',
  `stage_state4` char(1) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL DEFAULT '0' COMMENT '阶段4处理状态, 0:初始，1:成功',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC;

DROP TABLE IF EXISTS `mq_message_history`;

CREATE TABLE `mq_message_history` (
  `id` bigint NOT NULL COMMENT '消息id',
  `message_type` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '消息类型代码',
  `business_key1` varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '关联业务信息',
  `business_key2` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '关联业务信息',
  `business_key3` varchar(512) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '关联业务信息',
  `execute_num` int unsigned DEFAULT NULL COMMENT '通知次数',
  `state` int(10) unsigned zerofill DEFAULT NULL COMMENT '处理状态，0:初始，1:成功，2:失败',
  `returnfailure_date` datetime DEFAULT NULL COMMENT '回复失败时间',
  `returnsuccess_date` datetime DEFAULT NULL COMMENT '回复成功时间',
  `returnfailure_msg` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '回复失败内容',
  `execute_date` datetime DEFAULT NULL COMMENT '最近通知时间',
  `stage_state1` char(1) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `stage_state2` char(1) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `stage_state3` char(1) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `stage_state4` char(1) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC;

DROP TABLE IF EXISTS `teachplan`;

CREATE TABLE `teachplan` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `pname` varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '课程计划名称',
  `parentid` bigint NOT NULL COMMENT '课程计划父级Id',
  `grade` smallint NOT NULL COMMENT '层级，分为1、2、3级',
  `media_type` varchar(10) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '课程类型:1视频、2文档',
  `start_time` datetime DEFAULT NULL COMMENT '开始直播时间',
  `end_time` datetime DEFAULT NULL COMMENT '直播结束时间',
  `description` varchar(500) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '章节及课程时介绍',
  `timelength` varchar(30) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '时长，单位时:分:秒',
  `orderby` int DEFAULT '0' COMMENT '排序字段',
  `course_id` bigint NOT NULL COMMENT '课程标识',
  `course_pub_id` bigint DEFAULT NULL COMMENT '课程发布标识',
  `status` int NOT NULL DEFAULT '1' COMMENT '状态（1正常  0删除）',
  `is_preview` char(1) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT '0' COMMENT '是否支持试学或预览（试看）',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `change_date` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=290 DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC COMMENT='课程计划';

DROP TABLE IF EXISTS `teachplan_media`;

CREATE TABLE `teachplan_media` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `media_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '媒资文件id',
  `teachplan_id` bigint NOT NULL COMMENT '课程计划标识',
  `course_id` bigint NOT NULL COMMENT '课程标识',
  `media_fileName` varchar(150) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '媒资文件原始名称',
  `create_date` datetime DEFAULT NULL,
  `create_people` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建人',
  `change_people` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=58 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC;

DROP TABLE IF EXISTS `teachplan_work`;

CREATE TABLE `teachplan_work` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `work_id` bigint NOT NULL COMMENT '作业信息标识',
  `work_title` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '作业标题',
  `teachplan_id` bigint NOT NULL COMMENT '课程计划标识',
  `course_id` bigint DEFAULT NULL COMMENT '课程标识',
  `create_date` datetime DEFAULT NULL,
  `course_pub_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=45 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC;
```

#### 5.3.2 生成PO类

**CourseBase**

```java
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("course_base")
public class CourseBase implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 机构ID
     */
    private Long companyId;

    /**
     * 机构名称
     */
    private String companyName;

    /**
     * 课程名称
     */
    private String name;

    /**
     * 适用人群
     */
    private String users;

    /**
     * 课程标签
     */
    private String tags;

    /**
     * 大分类
     */
    private String mt;

    /**
     * 小分类
     */
    private String st;

    /**
     * 课程等级
     */
    private String grade;

    /**
     * 教育模式(common普通，record 录播，live直播等）
     */
    private String teachmode;

    /**
     * 课程介绍
     */
    private String description;

    /**
     * 课程图片
     */
    private String pic;

    /**
     * 创建时间
     */
    private LocalDateTime createDate;

    /**
     * 修改时间
     */
    private LocalDateTime changeDate;

    /**
     * 创建人
     */
    private String createPeople;

    /**
     * 更新人
     */
    private String changePeople;

    /**
     * 审核状态
     */
    private String auditStatus;

    /**
     * 课程发布状态 未发布  已发布 下线
     */
    private String status;
}
```

**CourseCategory**

```java
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("course_category")
public class CourseCategory implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private String id;

    /**
     * 分类名称
     */
    private String name;

    /**
     * 分类标签默认和名称一样
     */
    private String label;

    /**
     * 父结点id（第一级的父节点是0，自关联字段id）
     */
    private String parentid;

    /**
     * 是否显示
     */
    private Integer isShow;

    /**
     * 排序字段
     */
    private Integer orderby;

    /**
     * 是否叶子
     */
    private Integer isLeaf;
}
```

**CourseMarket**

```java
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("course_market")
public class CourseMarket implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键，课程id
     */
    @TableId(value = "id", type = IdType.INPUT)
    private Long id;

    /**
     * 收费规则，对应数据字典
     */
    private String charge;

    /**
     * 现价
     */
    private Float price;

    /**
     * 原价
     */
    private Float originalPrice;

    /**
     * 咨询qq
     */
    private String qq;

    /**
     * 微信
     */
    private String wechat;

    /**
     * 电话
     */
    private String phone;

    /**
     * 有效期天数
     */
    private Integer validDays;
}
```

**CoursePublish**

```java
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("course_publish")
public class CoursePublish implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 机构ID
     */
    private Long companyId;

    /**
     * 公司名称
     */
    private String companyName;

    /**
     * 课程名称
     */
    private String name;

    /**
     * 适用人群
     */
    private String users;

    /**
     * 标签
     */
    private String tags;

    /**
     * 创建人
     */
    private String username;

    /**
     * 大分类
     */
    private String mt;

    /**
     * 大分类名称
     */
    private String mtName;

    /**
     * 小分类
     */
    private String st;

    /**
     * 小分类名称
     */
    private String stName;

    /**
     * 课程等级
     */
    private String grade;

    /**
     * 教育模式
     */
    private String teachmode;

    /**
     * 课程图片
     */
    private String pic;

    /**
     * 课程介绍
     */
    private String description;

    /**
     * 课程营销信息，json格式
     */
    private String market;

    /**
     * 所有课程计划，json格式
     */
    private String teachplan;

    /**
     * 教师信息，json格式
     */
    private String teachers;

    /**
     * 发布时间
     */
    private LocalDateTime createDate;

    /**
     * 上架时间
     */
    private LocalDateTime onlineDate;

    /**
     * 下架时间
     */
    private LocalDateTime offlineDate;

    /**
     * 发布状态
     */
    private String status;

    /**
     * 备注
     */
    private String remark;

    /**
     * 收费规则，对应数据字典--203
     */
    private String charge;

    /**
     * 现价
     */
    private Float price;

    /**
     * 原价
     */
    private Float originalPrice;

    /**
     * 课程有效期天数
     */
    private Integer validDays;
}
```

**CoursePublishPre**

```java
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("course_publish_pre")
public class CoursePublishPre implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 机构ID
     */
    private Long companyId;

    /**
     * 公司名称
     */
    private String companyName;

    /**
     * 课程名称
     */
    private String name;

    /**
     * 适用人群
     */
    private String users;

    /**
     * 标签
     */
    private String tags;

    /**
     * 创建人
     */
    private String username;

    /**
     * 大分类
     */
    private String mt;

    /**
     * 大分类名称
     */
    private String mtName;

    /**
     * 小分类
     */
    private String st;

    /**
     * 小分类名称
     */
    private String stName;

    /**
     * 课程等级
     */
    private String grade;

    /**
     * 教育模式
     */
    private String teachmode;

    /**
     * 课程图片
     */
    private String pic;

    /**
     * 课程介绍
     */
    private String description;

    /**
     * 课程营销信息，json格式
     */
    private String market;

    /**
     * 所有课程计划，json格式
     */
    private String teachplan;

    /**
     * 教师信息，json格式
     */
    private String teachers;

    /**
     * 提交时间
     */
    private LocalDateTime createDate;

    /**
     * 审核时间
     */
    private LocalDateTime auditDate;

    /**
     * 状态
     */
    private String status;

    /**
     * 备注
     */
    private String remark;

    /**
     * 收费规则，对应数据字典--203
     */
    private String charge;

    /**
     * 现价
     */
    private Float price;

    /**
     * 原价
     */
    private Float originalPrice;

    /**
     * 课程有效期天数
     */
    private Integer validDays;
}
```

**CourseTeacher**

```java
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("course_teacher")
public class CourseTeacher implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 课程标识
     */
    private Long courseId;

    /**
     * 教师标识
     */
    private String teacherName;

    /**
     * 教师职位
     */
    private String position;

    /**
     * 教师简介
     */
    private String introduction;

    /**
     * 照片
     */
    private String photograph;

    /**
     * 创建时间
     */
    private LocalDateTime createDate;
}
```

**Teachplan**

```java
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("teachplan")
public class Teachplan implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 课程计划名称
     */
    private String pname;

    /**
     * 课程计划父级Id
     */
    private Long parentid;

    /**
     * 层级，分为1、2、3级
     */
    private Integer grade;

    /**
     * 课程类型:1视频、2文档
     */
    private String mediaType;

    /**
     * 开始直播时间
     */
    private LocalDateTime startTime;

    /**
     * 直播结束时间
     */
    private LocalDateTime endTime;

    /**
     * 章节及课程时介绍
     */
    private String description;

    /**
     * 时长，单位时:分:秒
     */
    private String timelength;

    /**
     * 排序字段
     */
    private Integer orderby;

    /**
     * 课程标识
     */
    private Long courseId;

    /**
     * 课程发布标识
     */
    private Long coursePubId;

    /**
     * 状态（1正常  0删除）
     */
    private Integer status;

    /**
     * 是否支持试学或预览（试看）
     */
    private String isPreview;

    /**
     * 创建时间
     */
    private LocalDateTime createDate;

    /**
     * 修改时间
     */
    private LocalDateTime changeDate;
}
```

**TeachplanMedia**

```java
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("teachplan_media")
public class TeachplanMedia implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 媒资文件id
     */
    private String mediaId;

    /**
     * 课程计划标识
     */
    private Long teachplanId;

    /**
     * 课程标识
     */
    private Long courseId;

    /**
     * 媒资文件原始名称
     */
    private String mediaFilename;

    /**
     * 创建日期
     */
    private LocalDateTime createDate;

    /**
     * 创建人
     */
    private String createPeople;

    /**
     * 修改人
     */
    private String changePeople;
}
```

### 5.4 定义模型

#### 5.4.1 增加分页参数类

在`xuecheng-plus-base`模块中添加以下类：

```java
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
public class PageParams implements Serializable {

    private static final long serialVersionUID = 1L;
    
    /**
     * 当前页
     */
    private long pageNo = 1L;

    /**
     * 每页显示的记录数
     */
    private long pageSize = 30L;
}
```

#### 5.4.2 课程通用查询模型类

在`xuecheng-plus-content-model`模块中添加以下类：

```java
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
public class QueryCourseParamsDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 审核状态
     */
    private String auditStatus;

    /**
     * 课程名称
     */
    private String courseName;

    /**
     * 发布状态
     */
    private String publishStatus;
}
```

#### 5.4.3 分页通用查询结果类

在`xuecheng-plus-base`模块中添加以下类：

```java
@AllArgsConstructor
@ToString
@Data
public class PageResult<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 数据列表
     */
    private List<T> items;

    /**
     * 总记录数
     */
    private long counts;

    /**
     * 当前页
     */
    private long pageNo;

    /**
     * 每页显示的记录数
     */
    private long pageSize;
}
```

### 5.5 内容管理项目配置

#### 5.5.1 接口文档配置

```java
@Configuration
@EnableKnife4j
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket webApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("webApi")
                .apiInfo(webApiInfo())
                .enable(true)
                .select()
                // 对指定目录下文件进行监控
                .apis(RequestHandlerSelectors.basePackage("com.yu.xuecheng.content"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo webApiInfo() {
        return new ApiInfoBuilder()
                .title("课程内容管理系统")
                .description("课程内容管理系统-对课程相关信息进行管理")
                .version("1.0")
                .build();
    }
}
```

#### 5.5.2 配置分页插件

在`xuecheng-plus-content-service`模块中添加以下配置：

```java
@Configuration
@MapperScan("com.yu.xuecheng.**.mapper")
public class MyBatisPlusConfig {

    /**
     * 分页拦截器
     */
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
        return interceptor;
    }
}
```

<span style="color:red">注意：这里的params和dto的参数位置顺序不能改变，且params的参数应该在url路径上，dto的参数使用json方式传参</span>

#### 5.5.3 日志配置

在`xuecheng-plus-content-api`模块和`xuecheng-plus-content-service`模块中的`log4j2-dev.xml`文件中**分别**添加以下配置：

```xml
<?xml version="1.0" encoding="UTF-8"?>
<Configuration monitorInterval="180" packages="">
    <properties>
        <property name="logdir">logs</property>
        <property name="PATTERN">%date{YYYY-MM-dd HH:mm:ss,SSS} %level [%thread][%file:%line] - %msg%n%throwable</property>
    </properties>
    <Appenders>
        <Console name="Console" target="SYSTEM_OUT">
            <PatternLayout pattern="${PATTERN}"/>
        </Console>

        <RollingFile name="ErrorAppender" fileName="${logdir}/error.log"
            filePattern="${logdir}/$${date:yyyy-MM-dd}/error.%d{yyyy-MM-dd-HH}.log" append="true">
            <PatternLayout pattern="${PATTERN}"/>
            <ThresholdFilter level="ERROR" onMatch="ACCEPT" onMismatch="DENY"/>
            <Policies>
                <TimeBasedTriggeringPolicy interval="1" modulate="true" />
            </Policies>
        </RollingFile>

        <RollingFile name="DebugAppender" fileName="${logdir}/info.log"
            filePattern="${logdir}/$${date:yyyy-MM-dd}/info.%d{yyyy-MM-dd-HH}.log" append="true">
            <PatternLayout pattern="${PATTERN}"/>
            <ThresholdFilter level="DEBUG" onMatch="ACCEPT" onMismatch="DENY"/>
            <Policies>
                <TimeBasedTriggeringPolicy interval="1" modulate="true" />
            </Policies>
        </RollingFile>
        
        <!--异步appender-->
         <Async name="AsyncAppender" includeLocation="true">
            <AppenderRef ref="ErrorAppender"/>
            <AppenderRef ref="DebugAppender"/>
        </Async>
    </Appenders>
    
    <Loggers>
         <!--过滤掉spring和mybatis的一些无用的debug信息
        <logger name="org.springframework" level="INFO">
        </logger>
        <logger name="org.mybatis" level="INFO">
        </logger>-->
        <logger name="cn.itcast.wanxinp2p.consumer.mapper" level="DEBUG">
        </logger>

        <logger name="springfox" level="INFO">
        </logger>
		<logger name="org.apache.http" level="INFO">
        </logger>
        <logger name="com.netflix.discovery" level="INFO">
        </logger>
        
        <logger name="RocketmqCommon"  level="INFO" >
		</logger>
		
		<logger name="RocketmqRemoting" level="INFO"  >
		</logger>
		
		<logger name="RocketmqClient" level="WARN">
		</logger>

        <logger name="org.dromara.hmily" level="WARN">
        </logger>

        <logger name="org.dromara.hmily.lottery" level="WARN">
        </logger>

        <logger name="org.dromara.hmily.bonuspoint" level="WARN">
        </logger>
		
        <!--OFF   0-->
        <!--FATAL   100-->
        <!--ERROR   200-->
        <!--WARN   300-->
        <!--INFO   400-->
        <!--DEBUG   500-->
        <!--TRACE   600-->
        <!--ALL   Integer.MAX_VALUE-->
        <Root level="DEBUG" includeLocation="true">
            <AppenderRef ref="AsyncAppender"/>
            <AppenderRef ref="Console"/>
            <AppenderRef ref="DebugAppender"/>
        </Root>
    </Loggers>
</Configuration>
```

#### 5.5.4 项目基础配置

在`xuecheng-plus-content-api`模块`bootstrap.yml`文件中添加以下配置：

```yaml
server:
  servlet:
    context-path: /content
  port: 63040
  
# 数据库配置
spring:
  application:
    name: content-api
  datasource:
    driver-class-name: com.mysql.cj.jdbc.Driver
    url: jdbc:mysql://ip:port/xuecheng-content?useUnicode=true&characterEncoding=UTF-8&useSSL=false&serverTimezone=Asia/Shanghai&allowPublicKeyRetrieval=true
    username: xxx
    password: xxx
    
# 日志文件配置路径
logging:
  config: classpath:log4j2-dev.xml
```

<span style="color:red">注意：1、正常情况下，API模块只配置项目相关内容，Service模块配置项目和数据库相关内容，用于数据交互，这里API配置数据库只是为了测试数据，下面的其他模块都是按照这种方式来配置</span>

<span style="color:red">2、`bootstrap.yml`能识别，但是配置不能继承，比如这里API模块虽然依赖了Service模块，但是启动时会报数据库未配置异常，这种情况可以将API模块的`bootstrap.yml`修改为`application.yml`或者将Service模块的`bootstrap.yml`修改为`application.yml`，即可解决该问题</span>

在`xuecheng-plus-content-service`模块`bootstrap.yml`文件中添加以下配置：

```yaml
# 数据库配置
spring:
  application:
    name: content-service
  datasource:
    driver-class-name: com.mysql.cj.jdbc.Driver
    url: jdbc:mysql://ip:port/xuecheng-content?useUnicode=true&characterEncoding=UTF-8&useSSL=false&serverTimezone=Asia/Shanghai&allowPublicKeyRetrieval=true
    username: xxx
    password: xxx

# 日志文件配置路径
logging:
  config: classpath:log4j2-dev.xml
```

#### 5.5.5 生成service、mapper接口

在`xuecheng-plus-content-service`模块中生成相关接口及实现类，我这里使用`MyBatisPlus`插件生成，不做过多介绍

#### 5.5.6 简单demo案例

在`xuecheng-plus-content-api`模块中添加以下类：

```java
@Api(value = "内容管理系统", tags = {"内容管理系统"})
@RestController
public class CourseBaseInfoController {

    @ApiOperation(value = "课程信息列表")
    @PostMapping("/course/list")
    public PageResult<CourseBase> list(PageParams params, @RequestBody(required = false) QueryCourseParamsDTO dto) {
        Page<CourseBase> basePage = courseBaseService.listAllCourse(params, dto);

        return new PageResult<>(basePage.getRecords(), basePage.getTotal(), basePage.getCurrent(), basePage.getPages());
    }
}
```

```java
public interface ICourseBaseService extends IService<CourseBase> {

    /**
     * 课程信息列表
     *
     * @param params 分页参数
     * @param dto    请求参数
     * @return {@link Page}<{@link CourseBase}>
     */
    Page<CourseBase> listAllCourse(PageParams params, QueryCourseParamsDTO dto);
}
```

```java
@Service
public class CourseBaseServiceImpl extends ServiceImpl<CourseBaseMapper, CourseBase> implements ICourseBaseService {

    private final CourseBaseMapper courseBaseMapper;

    public CourseBaseServiceImpl(CourseBaseMapper courseBaseMapper) {
        this.courseBaseMapper = courseBaseMapper;
    }

    /**
     * 课程信息列表
     */
    @Override
    public Page<CourseBase> listAllCourse(PageParams params, QueryCourseParamsDTO dto) {

        Page<CourseBase> page = new Page<>(params.getPageNo(), params.getPageSize());

        return courseBaseMapper.selectPage(page, new LambdaQueryWrapper<CourseBase>()
                .like(!StringUtils.isEmpty(dto.getCourseName()), CourseBase::getName, dto.getCourseName())
                .eq(!StringUtils.isEmpty(dto.getAuditStatus()), CourseBase::getAuditStatus, dto.getAuditStatus()));
    }
}
```

#### 5.5.7 内容服务启动类

```java
@SpringBootApplication
public class ContentServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ContentServiceApplication.class, args);
    }
}
```

### 5.6 课程内容

#### 5.6.1 课程分类

以下仅提供关键部分代码示例：

```java
@EqualsAndHashCode(callSuper = true)
@Builder
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CourseCategoryDTO extends CourseCategory implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 课程分类子节点
     */
    private List<CourseCategoryDTO> childrenTreeNodes;
}
```

```java
@Api(value = "课程分类", tags = {"课程分类"})
@RestController
public class CourseCategoryController {

    private final ICourseCategoryService courseCategoryService;

    public CourseCategoryController(ICourseCategoryService courseCategoryService) {
        this.courseCategoryService = courseCategoryService;
    }

    @ApiOperation(value = "课程分类树")
    @GetMapping("/category/tree")
    public List<CourseCategoryDTO> tree(@RequestParam("rootId") String rootId) {
        return courseCategoryService.tree(rootId);
    }
}
```

```java
@Service
public class CourseCategoryServiceImpl extends ServiceImpl<CourseCategoryMapper, CourseCategory> implements ICourseCategoryService {

    private final CourseCategoryMapper courseCategoryMapper;

    public CourseCategoryServiceImpl(CourseCategoryMapper courseCategoryMapper) {
        this.courseCategoryMapper = courseCategoryMapper;
    }

    /**
     * 课程分类树
     */
    @Override
    public List<CourseCategoryDTO> tree(String rootId) {
        List<CourseCategoryDTO> categoryList = courseCategoryMapper.tree(rootId);

        Map<String, CourseCategoryDTO> treeMap = categoryList.stream().filter(item -> !rootId.equals(item.getId())).collect(Collectors.toMap(CourseCategoryDTO::getId, value -> value, (k1, k2) -> k2));

        // 定义一个新的集合用于接收最终组装的数据
        List<CourseCategoryDTO> list = new ArrayList<>();

        categoryList.stream()
                .filter(item -> !rootId.equals(item.getId()))
                .forEach(item -> {
                    // 如果当前节点的父id等于传入的id,则将当前作为父节点
                    if (rootId.equals(item.getParentid())) {
                        list.add(item);
                    }

                    // 获取当前节点的父节点
                    CourseCategoryDTO courseCategoryParent = treeMap.get(item.getParentid());

                    // 如果当前节点的父节点不为null,则获取父节点的子节点
                    if (courseCategoryParent != null) {
                        // 子节点为null,则添加一个空集合,不为null则直接添加当前节点到父节点的子节点
                        if (courseCategoryParent.getChildrenTreeNodes() == null) {
                            courseCategoryParent.setChildrenTreeNodes(new ArrayList<>());
                        }
                        courseCategoryParent.getChildrenTreeNodes().add(item);
                    }
                });

        return list;
    }
}
```

```xml-dtd
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="com.yu.xuecheng.content.mapper.CourseCategoryMapper">

    <select id="tree" parameterType="string" resultType="com.yu.xuecheng.content.domain.dto.CourseCategoryDTO">
        WITH RECURSIVE t1 AS (
            SELECT * FROM course_category WHERE id = #{rootId}
            UNION ALL
            SELECT t2.* FROM course_category t2 INNER JOIN t1 ON t1.id = t2.parentid
        )
        SELECT * FROM t1 ORDER BY id
    </select>
</mapper>
```

#### 5.6.2 新增课程

```java
@EqualsAndHashCode(callSuper = true)
@Builder
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CourseBaseInfoVO extends CourseBase {

    /**
     * 收费规则，对应数据字典
     */
    private String charge;

    /**
     * 价格
     */
    private Float price;


    /**
     * 原价
     */
    private Float originalPrice;

    /**
     * 咨询qq
     */
    private String qq;

    /**
     * 微信
     */
    private String wechat;

    /**
     * 电话
     */
    private String phone;

    /**
     * 有效期天数
     */
    private Integer validDays;

    /**
     * 大分类名称
     */
    private String mtName;

    /**
     * 小分类名称
     */
    private String stName;
}
```

```java
@Builder
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
@ApiModel(value = "AddCourseDto", description = "新增课程基本信息")
public class AddCourseDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotEmpty(message = "新增课程名称不能为空", groups = {ValidationGroups.Insert.class})
    @NotEmpty(message = "修改课程名称不能为空", groups = {ValidationGroups.Update.class})
    @ApiModelProperty(value = "课程名称", required = true)
    private String name;

    @NotEmpty(message = "适用人群不能为空", groups = {ValidationGroups.Insert.class})
    @Size(message = "适用人群内容过少", min = 10, groups = {ValidationGroups.Insert.class})
    @ApiModelProperty(value = "适用人群", required = true)
    private String users;

    @ApiModelProperty(value = "课程标签")
    private String tags;

    @NotEmpty(message = "课程分类不能为空", groups = {ValidationGroups.Insert.class})
    @ApiModelProperty(value = "大分类", required = true)
    private String mt;

    @NotEmpty(message = "课程分类不能为空", groups = {ValidationGroups.Insert.class})
    @ApiModelProperty(value = "小分类", required = true)
    private String st;

    @NotEmpty(message = "课程等级不能为空", groups = {ValidationGroups.Insert.class})
    @ApiModelProperty(value = "课程等级", required = true)
    private String grade;

    @ApiModelProperty(value = "教学模式（普通，录播，直播等）", required = true)
    private String teachmode;

    @ApiModelProperty(value = "课程介绍")
    @Size(message = "课程描述内容过少", min = 10, groups = {ValidationGroups.Insert.class})
    private String description;

    @ApiModelProperty(value = "课程图片", required = true)
    private String pic;

    @NotEmpty(message = "收费规则不能为空", groups = {ValidationGroups.Insert.class})
    @ApiModelProperty(value = "收费规则，对应数据字典", required = true)
    private String charge;

    @ApiModelProperty(value = "价格")
    private Float price;

    @ApiModelProperty(value = "原价")
    private Float originalPrice;

    @ApiModelProperty(value = "qq")
    private String qq;

    @ApiModelProperty(value = "微信")
    private String wechat;
    
    @ApiModelProperty(value = "电话")
    private String phone;

    @ApiModelProperty(value = "有效期")
    private Integer validDays;
}
```

```java
@Api(value = "课程基础信息", tags = {"课程基础信息"})
@RestController
public class CourseBaseInfoController {

    private final ICourseBaseService courseBaseService;

    public CourseBaseInfoController(ICourseBaseService courseBaseService) {
        this.courseBaseService = courseBaseService;
    }

    @ApiOperation(value = "添加课程")
    @PostMapping("/course")
    public CourseBaseInfoVO addCourse(@RequestBody @Validated(ValidationGroups.Insert.class) AddCourseDTO dto) {
        Long companyId = 1232141425L;
        return courseBaseService.addCourse(companyId, dto);
    }
}
```

```java
public interface ICourseBaseService extends IService<CourseBase> {

    /**
     * 添加课程
     *
     * @param companyId 机构id
     * @param dto       课程基本信息dto
     * @return {@link CourseBaseInfoVO}
     */
    CourseBaseInfoVO addCourse(Long companyId, AddCourseDTO dto);
}
```

```java
@Slf4j
@Service
public class CourseBaseServiceImpl extends ServiceImpl<CourseBaseMapper, CourseBase> implements ICourseBaseService {

    private final CourseBaseMapper courseBaseMapper;

    private final CourseMarketMapper courseMarketMapper;

    private final CourseCategoryMapper courseCategoryMapper;

    public CourseBaseServiceImpl(CourseBaseMapper courseBaseMapper, CourseMarketMapper courseMarketMapper, CourseCategoryMapper courseCategoryMapper) {
        this.courseBaseMapper = courseBaseMapper;
        this.courseMarketMapper = courseMarketMapper;
        this.courseCategoryMapper = courseCategoryMapper;
    }

    /**
     * 添加课程
     */
    @Transactional(rollbackFor = {Exception.class, RuntimeException.class}, propagation = Propagation.REQUIRED)
    @Override
    public CourseBaseInfoVO addCourse(Long companyId, AddCourseDTO dto) {
        CourseBase courseBase = new CourseBase();

        // 添加课程基本信息
        BeanUtils.copyProperties(dto, courseBase);
        courseBase.setCompanyId(companyId);

        // 设置课程审核状态和发布状态
        courseBase.setAuditStatus("202002");
        courseBase.setStatus("203001");

        int baseCount = courseBaseMapper.insert(courseBase);
        if (baseCount < 0) {
            throw new RuntimeException("添加课程基础信息失败!");
        }

        // 添加课程营销信息
        CourseMarket courseMarket = new CourseMarket();
        BeanUtils.copyProperties(dto, courseMarket);
        courseMarket.setId(courseBase.getId());
        int marketCount = addCourseMarket(courseMarket);
        if (marketCount < 0) {
            throw new RuntimeException("添加课程营销信息失败!");
        }

        // 查询课程基本信息和营销信息,组合数据后返回
        return queryCourseBaseInfoVO(courseBase.getId());
    }

    /**
     * 添加课程营销信息
     */
    private int addCourseMarket(CourseMarket courseMarket) {

        // 校验收费课程价格
        Float price = courseMarket.getPrice();
        if (Objects.isNull(price) || price < 0) {
            throw new RuntimeException("课程价格填写不正确");
        }

        // 添加课程营销信息,如果存在则更新
        CourseMarket market = courseMarketMapper.selectById(courseMarket.getId());
        if (Objects.isNull(market)) {
            return courseMarketMapper.insert(courseMarket);
        } else {
            BeanUtils.copyProperties(courseMarket, market);
            return courseMarketMapper.updateById(market);
        }
    }

    private CourseBaseInfoVO queryCourseBaseInfoVO(Long courseId) {
        CourseBaseInfoVO courseBaseInfoVO = new CourseBaseInfoVO();

        CourseBase courseBase = courseBaseMapper.selectById(courseId);

        if (Objects.isNull(courseBase)) {
            return null;
        }

        BeanUtils.copyProperties(courseBase, courseBaseInfoVO);

        CourseMarket courseMarket = courseMarketMapper.selectById(courseId);

        BeanUtils.copyProperties(courseMarket, courseBaseInfoVO);
        return courseBaseInfoVO;
    }
}
```

<span style="color:red">注意：因为`CourseMarket`的id是通过`CourseBase`获取的，所以应该将`CourseMarket`实体类的id主键生成类型设置为`IdType.INPUT`</span>

#### 5.6.3 课程详情

```java
@ApiOperation(value = "课程信息详情")
@GetMapping("/course/{courseId}")
public CourseBaseInfoVO getCourseDetails(@PathVariable Long courseId) {
    return courseBaseService.queryCourseBaseInfoVO(courseId);
}
```

```java
/**
 * 查询课程详情
 *
 * @param courseId 课程id
 * @return {@link CourseBaseInfoVO}
 */
CourseBaseInfoVO queryCourseBaseInfoVO(Long courseId);
```

```java
/**
 * 查询课程详情
 */
public CourseBaseInfoVO queryCourseBaseInfoVO(Long courseId) {
    CourseBaseInfoVO courseBaseInfoVO = new CourseBaseInfoVO();

    CourseBase courseBase = courseBaseMapper.selectById(courseId);

    if (Objects.isNull(courseBase)) {
        return null;
    }

    BeanUtils.copyProperties(courseBase, courseBaseInfoVO);

    CourseMarket courseMarket = courseMarketMapper.selectById(courseId);

    BeanUtils.copyProperties(courseMarket, courseBaseInfoVO);
    return courseBaseInfoVO;
}
```

#### 5.6.4 编辑课程

```java
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class EditCourseDTO extends AddCourseDTO {

    /*
     * 课程id
     */
    private Long id;
}
```

```java
@ApiOperation(value = "编辑课程")
@PutMapping("/course")
public CourseBaseInfoVO editCourse(@RequestBody @Validated(ValidationGroups.Update.class) EditCourseDTO dto) {
    Long companyId = 1232141425L;
    return courseBaseService.editCourse(companyId, dto);
}
```

```java
/**
 * 编辑课程
 *
 * @param companyId 机构id
 * @param dto       课程编辑基本信息dto
 * @return {@link CourseBaseInfoVO}
 */
CourseBaseInfoVO editCourse(Long companyId, EditCourseDTO dto);
```

```java
/**
 * 编辑课程
 */
@Transactional(rollbackFor = {Exception.class, RuntimeException.class}, propagation = Propagation.REQUIRED)
@Override
public CourseBaseInfoVO editCourse(Long companyId, EditCourseDTO dto) {
    // 查询课程基本信息和课程营销信息是否存在
    CourseBase courseBase = courseBaseMapper.selectById(dto.getId());
    if (Objects.isNull(courseBase)) {
        throw new BusinessException("该课程基本信息不存在");
    }

    CourseMarket courseMarket = courseMarketMapper.selectById(dto.getId());
    if (Objects.isNull(courseMarket)) {
        throw new BusinessException("该课程营销信息不存在");
    }


    BeanUtils.copyProperties(dto, courseBase);

    // 更新课程基本信息
    int update = courseBaseMapper.updateById(courseBase);
    if (update < 0) {
        throw new BusinessException("更新失败");
    }

    // 更新课程营销信息
    BeanUtils.copyProperties(dto, courseMarket);
    courseMarketMapper.updateById(courseMarket);

    // 查询更新后的课程
    return this.queryCourseBaseInfoVO(dto.getId());
}
```

### 5.7 课程计划

#### 5.7.1 查询课程计划树形结构

```java
@EqualsAndHashCode(callSuper = true)
@Data
public class TeachplanDTO extends Teachplan {

    /**
     * 媒资管理信息
     */
    private TeachplanMedia teachplanMedia;

    /**
     * 小章节
     */
    private List<TeachplanDTO> teachPlanTreeNodes;
}
```

```java
@Api(value = "课程计划管理",tags = "课程计划管理")
@RestController
public class TeachplanController {

    private final ITeachplanService teachplanService;

    public TeachplanController(ITeachplanService teachplanService) {
        this.teachplanService = teachplanService;
    }

    @ApiOperation("查询课程计划树形结构")
    @GetMapping("/teachplan/{courseId}/tree-nodes")
    public List<TeachplanDTO> getTreeNodes(@PathVariable Long courseId){
        return teachplanService.findTeachplanTree(courseId);
    }
}
```

```java
public interface ITeachplanService extends IService<Teachplan> {

    /**
     * 查询课程计划树形结构
     *
     * @param courseId 课程id
     * @return {@link List}<{@link TeachplanDTO}>
     */
    List<TeachplanDTO> findTeachplanTree(Long courseId);
}
```

```java
@Service
public class TeachplanServiceImpl extends ServiceImpl<TeachplanMapper, Teachplan> implements ITeachplanService {

    private final TeachplanMapper teachplanMapper;

    public TeachplanServiceImpl(TeachplanMapper teachplanMapper) {
        this.teachplanMapper = teachplanMapper;
    }

    /**
     * 查询课程计划树形结构
     */
    @Override
    public List<TeachplanDTO> findTeachplanTree(Long courseId) {
        return teachplanMapper.selectTreeNodes(courseId);
    }
}
```

```java
public interface TeachplanMapper extends BaseMapper<Teachplan> {

    /**
     * 查询课程计划树形结构
     *
     * @param courseId 课程id
     * @return {@link List}<{@link TeachplanDTO}>
     */
    List<TeachplanDTO> selectTreeNodes(@Param("courseId") Long courseId);
}
```

```xml-dtd
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="com.yu.xuecheng.content.mapper.TeachplanMapper">

    <!-- 通用查询映射结果 -->
    <resultMap id="BaseResultMap" type="com.yu.xuecheng.content.domain.po.Teachplan">
        <id column="id" property="id" />
        <result column="pname" property="pname" />
        <result column="parentid" property="parentid" />
        <result column="grade" property="grade" />
        <result column="media_type" property="mediaType" />
        <result column="start_time" property="startTime" />
        <result column="end_time" property="endTime" />
        <result column="description" property="description" />
        <result column="timelength" property="timelength" />
        <result column="orderby" property="orderby" />
        <result column="course_id" property="courseId" />
        <result column="course_pub_id" property="coursePubId" />
        <result column="status" property="status" />
        <result column="is_preview" property="isPreview" />
        <result column="create_date" property="createDate" />
        <result column="change_date" property="changeDate" />
    </resultMap>

    <!-- 通用查询结果列 -->
    <sql id="Base_Column_List">
        id, pname, parentid, grade, media_type, start_time, end_time, description, timelength, orderby, course_id, course_pub_id, status, is_preview, create_date, change_date
    </sql>

    <resultMap id="treeNodeResultMap" type="com.yu.xuecheng.content.domain.dto.TeachplanDTO">

        <id     column="one_id"        property="id" />
        <result column="one_pname"      property="pname" />
        <result column="one_parentid"     property="parentid" />
        <result column="one_grade"  property="grade" />
        <result column="one_mediaType"   property="mediaType" />
        <result column="one_stratTime"   property="stratTime" />
        <result column="one_endTime"   property="endTime" />
        <result column="one_orderby"   property="orderby" />
        <result column="one_courseId"   property="courseId" />
        <result column="one_coursePubId"   property="coursePubId" />
        <!--映射子节点,一对多映射,ofType list中的对象类型-->
        <collection property="teachPlanTreeNodes" ofType="com.yu.xuecheng.content.domain.dto.TeachplanDTO">
            <id     column="two_id"        property="id" />
            <result column="two_pname"      property="pname" />
            <result column="two_parentid"     property="parentid" />
            <result column="two_grade"  property="grade" />
            <result column="two_mediaType"   property="mediaType" />
            <result column="two_stratTime"   property="stratTime" />
            <result column="two_endTime"   property="endTime" />
            <result column="two_orderby"   property="orderby" />
            <result column="two_courseId"   property="courseId" />
            <result column="two_coursePubId"   property="coursePubId" />
            <!--一对一映射-->
            <association property="teachplanMedia" javaType="com.yu.xuecheng.content.domain.po.TeachplanMedia">
                <id column="teachplanMeidaId" property="id"/>
                <result column="mediaFilename" property="mediaFilename"/>
                <result column="mediaId" property="mediaId"/>

            </association>
        </collection>

    </resultMap>


    <select id="selectTreeNodes" parameterType="long" resultMap="treeNodeResultMap">
        select
            one.id            one_id,
            one.pname         one_pname,
            one.parentid      one_parentid,
            one.grade         one_grade,
            one.media_type    one_mediaType,
            one.start_time    one_stratTime,
            one.end_time      one_endTime,
            one.orderby       one_orderby,
            one.course_id     one_courseId,
            one.course_pub_id one_coursePubId,
            two.id            two_id,
            two.pname         two_pname,
            two.parentid      two_parentid,
            two.grade         two_grade,
            two.media_type    two_mediaType,
            two.start_time    two_stratTime,
            two.end_time      two_endTime,
            two.orderby       two_orderby,
            two.course_id     two_courseId,
            two.course_pub_id two_coursePubId,
            m1.media_fileName mediaFilename,
            m1.id             teachplanMeidaId,
            m1.media_id       mediaId
        from teachplan one
                 inner join teachplan two on two.parentid = one.id
                 left join teachplan_media m1 on two.id = m1.teachplan_id
        where one.parentid = 0
          and one.course_id = #{courseId}
        order by one.orderby,two.orderby
    </select>
</mapper>
```

#### 5.7.2 课程计划创建或修改

```java
@Data
public class AddTeachplanDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    /***
     * 教学计划id
     */
    private Long id;

    /**
     * 课程计划名称
     */
    private String pname;

    /**
     * 课程计划父级Id
     */
    private Long parentid;

    /**
     * 层级，分为1、2、3级
     */
    private Integer grade;

    /**
     * 课程类型:1视频、2文档
     */
    private String mediaType;

    /**
     * 课程标识
     */
    private Long courseId;

    /**
     * 课程发布标识
     */
    private Long coursePubId;
    
    /**
     * 是否支持试学或预览（试看）
     */
    private String isPreview;
}
```

```java
@Api(value = "课程计划管理", tags = "课程计划管理")
@RestController
public class TeachplanController {

    private final ITeachplanService teachplanService;

    public TeachplanController(ITeachplanService teachplanService) {
        this.teachplanService = teachplanService;
    }

    @ApiOperation("课程计划创建或修改")
    @PostMapping("/teachplan")
    public void saveOrUpdateTeachplan(@RequestBody AddTeachplanDTO dto) {
        teachplanService.saveOrUpdateTeachplan(dto);
    }
}
```

```java
public interface ITeachplanService extends IService<Teachplan> {

    /**
     * 课程计划创建或修改
     *
     * @param dto 课程计划
     */
    void saveOrUpdateTeachplan(AddTeachplanDTO dto);
}
```

```java
@Service
public class TeachplanServiceImpl extends ServiceImpl<TeachplanMapper, Teachplan> implements ITeachplanService {

    private final TeachplanMapper teachplanMapper;

    public TeachplanServiceImpl(TeachplanMapper teachplanMapper) {
        this.teachplanMapper = teachplanMapper;
    }

    /**
     * 课程计划创建或修改
     */
    @Override
    public void saveOrUpdateTeachplan(AddTeachplanDTO dto) {
        //通过课程计划id判断是新增和修改
        Teachplan teachplan = CopyUtils.copy(dto, Teachplan.class);
        this.saveOrUpdate(teachplan);
    }
}
```

## 6、统一返回结果和异常

### 6.1 添加依赖信息

在`xuecheng-plus-base`模块中添加以下依赖信息：

```xml
<dependency>
    <groupId>org.springframework</groupId>
    <artifactId>spring-webmvc</artifactId>
</dependency>
```

### 6.2 统一返回结果

**HTTP响应状态枚举**

```java
public enum HttpStatusEnum {

    /**
     * 操作成功
     */
    SUCCESS("操作成功", 200),

    /**
     * 资源没有被修改
     */
    NOT_MODIFIED("资源没有被修改", 304),

    /**
     * 参数列表错误（缺少，格式不匹配）
     */
    BAD_REQUEST("参数列表错误（缺少，格式不匹配）", 400),

    /**
     * 未授权
     */
    UNAUTHORIZED("未授权", 401),

    /**
     * 访问受限，授权过期
     */
    FORBIDDEN("访问受限，授权过期", 403),

    /**
     * 资源，服务未找到
     */
    NOT_FOUND("资源，服务未找到", 404),

    /**
     * 不允许的http方法
     */
    BAD_METHOD("不允许的http方法", 405),

    /**
     * 系统内部错误
     */
    ERROR("系统内部错误", 500);

    private final String message;

    private final int code;

    HttpStatusEnum(String message, int code) {
        this.message = message;
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public int getCode() {
        return code;
    }
}
```

**统一返回结果**

```java
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResultResponse<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 状态码
     */
    private int code;

    /**
     * 返回消息
     */
    private String msg;

    /**
     * 返回数据
     */
    private T data;

    /**
     * 发生异常时的堆栈数据
     */
    @JsonIgnore
    private List<String> stackTraceList;

    public ResultResponse(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public ResultResponse(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public ResultResponse(int code, String msg, T data, Throwable e) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    /**
     * 返回成功消息
     *
     * @return 成功消息
     */
    public static <T> ResultResponse<T> success() {
        return ResultResponse.success(HttpStatusEnum.SUCCESS.getMessage(), null);
    }

    /**
     * 自定义code和消息
     *
     * @return 成功消息
     */
    public static <T> ResultResponse<T> success(int code, String msg, T data) {
        return new ResultResponse<T>(code, msg, data);
    }

    /**
     * 返回成功数据
     *
     * @return 成功消息
     */
    public static <T> ResultResponse<T> success(T data) {
        return ResultResponse.success(HttpStatusEnum.SUCCESS.getMessage(), data);
    }

    /**
     * 返回成功消息
     *
     * @param msg  返回内容
     * @param data 数据对象
     * @return 成功消息
     */
    public static <T> ResultResponse<T> success(String msg, T data) {
        return new ResultResponse<>(HttpStatusEnum.SUCCESS.getCode(), msg, data);
    }

    /**
     * 返回错误消息
     *
     * @return
     */
    public static <T> ResultResponse<T> error() {
        return ResultResponse.error("操作失败");
    }

    /**
     * 返回错误消息
     *
     * @param msg 返回内容
     * @return 警告消息
     */
    public static <T> ResultResponse<T> error(String msg) {
        return ResultResponse.error(msg, null);
    }

    /**
     * 返回错误消息
     *
     * @param msg 返回内容
     * @return 警告消息
     */
    public static <T> ResultResponse<T> error(String msg, Throwable e) {
        return ResultResponse.error(msg, null, e);
    }

    /**
     * 返回错误消息
     *
     * @param msg  返回内容
     * @param data 数据对象
     * @return 警告消息
     */
    public static <T> ResultResponse<T> error(String msg, T data) {
        return new ResultResponse<>(HttpStatusEnum.ERROR.getCode(), msg, data);
    }

    /**
     * 返回错误消息
     *
     * @param msg  返回内容
     * @param data 数据对象
     * @return 警告消息
     */
    public static <T> ResultResponse<T> error(String msg, T data, Throwable e) {
        return new ResultResponse<>(HttpStatusEnum.ERROR.getCode(), msg, data, e);
    }

    /**
     * 返回错误消息
     *
     * @param code 状态码
     * @param msg  返回内容
     * @return 警告消息
     */
    public static <T> ResultResponse<T> error(int code, String msg) {
        return new ResultResponse<>(code, msg, null);
    }

    /**
     * 返回错误消息
     *
     * @param code 状态码
     * @param msg  返回内容
     * @param e    异常
     * @return 警告消息
     */
    public static <T> ResultResponse<T> error(int code, String msg, Throwable e) {
        return new ResultResponse<>(code, msg, null, e);
    }

    /**
     * 自定义返回错误消息
     */
    public static <T> ResultResponse<T> error(int code, String msg, T data) {
        return new ResultResponse<>(code, msg, data);
    }

    /**
     * 获取异常的堆栈信息
     *
     * @param e
     * @return
     */
    private static List<String> getStackTraceList(Throwable e) {
        List<String> stackTraceList = new ArrayList<>();
        stackTraceList.add("异常描述：" + e.getClass().toString() + ": " + e.getMessage());
        for (StackTraceElement stackTraceElement : e.getStackTrace()) {
            String s = "--类:" + stackTraceElement.getFileName() + "--方法:" + stackTraceElement.getMethodName() + "--行数:" + stackTraceElement.getLineNumber();
            stackTraceList.add(s);
        }
        return stackTraceList;
    }
}
```

### 6.3 统一返回异常

**自定义业务异常**

```java
public class BusinessException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private Integer code;

	private final String message;

	public BusinessException(String message) {
		this.message = message;
	}

	public BusinessException(String message, Integer code) {
		this.message = message;
		this.code = code;
	}

	public BusinessException(String message, Throwable e) {
		super(message, e);
		this.message = message;
	}

	@Override
	public String getMessage() {
		return message;
	}

	public Integer getCode() {
		return code;
	}
}
```

**分组校验**

```java
public class ValidationGroups {

    public interface Insert {
    }

    public interface Update {
    }

    public interface Delete {
    }
}
```

**全局异常处理**

```java
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 业务异常
     */
    @ExceptionHandler(BusinessException.class)
    public ResultResponse<String> businessException(BusinessException e) {
        log.error(HttpStatusEnum.NOT_FOUND.getMessage(), e);
        if (Objects.isNull(e.getCode())) {
            return ResultResponse.error(e.getMessage(), null);
        }
        return ResultResponse.error(e.getCode(), e.getMessage(), null);
    }

    /**
     * 路径资源异常
     */
    @ExceptionHandler(NoHandlerFoundException.class)
    public ResultResponse<String> handlerNoFoundException(Exception e) {
        log.error(HttpStatusEnum.NOT_FOUND.getMessage(), e);
        return ResultResponse.error(HttpStatusEnum.NOT_FOUND.getCode(), HttpStatusEnum.NOT_FOUND.getMessage(), e);
    }

    /**
     * 处理异常
     */
    @ExceptionHandler(Exception.class)
    public ResultResponse<String> handleException(Exception e) {
        log.error(HttpStatusEnum.ERROR.getMessage(), e);
        return ResultResponse.error(e.getMessage(), e);
    }

    /**
     * 处理Get请求中 使用@Valid 验证路径中请求实体校验失败后抛出的异常
     */
    @ExceptionHandler(BindException.class)
    public ResultResponse<String> validatedBindException(BindException e) {
        log.error(HttpStatusEnum.BAD_REQUEST.getMessage(), e);
        String errorMsg = e.getBindingResult().getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.joining(","));
        return ResultResponse.error(HttpStatusEnum.BAD_REQUEST.getCode(), errorMsg, HttpStatusEnum.BAD_REQUEST.getMessage());
    }

    /**
     * 处理请求参数格式错误 @RequestBody上validate失败后抛出的异常是MethodArgumentNotValidException异常。
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResultResponse<String> validExceptionHandler(MethodArgumentNotValidException e) {
        log.error(HttpStatusEnum.BAD_REQUEST.getMessage(), e);
        String errorMsg = e.getBindingResult().getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.joining(","));
        return ResultResponse.error(HttpStatusEnum.BAD_REQUEST.getCode(), errorMsg, HttpStatusEnum.BAD_REQUEST.getMessage());
    }

    /**
     * 处理请求参数格式错误 @RequestParam上validate失败后抛出的异常是javax.validation.ConstraintViolationException
     *
     * @param e e
     * @return {@link ResultResponse}
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public ResultResponse<String> handleConstraintViolationException(ConstraintViolationException e) {
        log.error(HttpStatusEnum.BAD_REQUEST.getMessage(), e);
        String errorMsg = e.getConstraintViolations().stream().map(ConstraintViolation::getMessage).collect(Collectors.joining(","));
        return ResultResponse.error(HttpStatusEnum.BAD_REQUEST.getCode(), errorMsg, HttpStatusEnum.BAD_REQUEST.getMessage());
    }
}
```

## 7、系统管理模块

### 7.1 模块工程结构

xuecheng-plus-system（系统管理模块）

- xuecheng-plus-system-api（系统管理接口模块）
- xuecheng-plus-system-service（系统管理服务模块）
- xuecheng-plus-system-model（系统管理数据模型模块）

### 7.2 创建模块工程

各模块工程的`pom.xml`文件如下：

**xuecheng-plus-system**

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <parent>
        <artifactId>xuecheng-plus</artifactId>
        <groupId>com.yu.xuecheng</groupId>
        <version>1.0-SNAPSHOT</version>
    </parent>
    <modelVersion>4.0.0</modelVersion>

    <artifactId>xuecheng-plus-system</artifactId>
    <packaging>pom</packaging>
    <modules>
        <module>xuecheng-plus-system-model</module>
        <module>xuecheng-plus-system-service</module>
        <module>xuecheng-plus-system-api</module>
    </modules>
</project>
```

**xuecheng-plus-system-model**

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <parent>
        <artifactId>xuecheng-plus-system</artifactId>
        <groupId>com.yu.xuecheng</groupId>
        <version>1.0-SNAPSHOT</version>
    </parent>
    <modelVersion>4.0.0</modelVersion>

    <artifactId>xuecheng-plus-system-model</artifactId>

    <dependencies>
        <dependency>
            <groupId>com.yu.xuecheng</groupId>
            <artifactId>xuecheng-plus-base</artifactId>
            <version>1.0-SNAPSHOT</version>
        </dependency>

        <!--存在mybatisplus注解添加相关注解保证不报错-->
        <dependency>
            <groupId>com.baomidou</groupId>
            <artifactId>mybatis-plus-annotation</artifactId>
            <version>${mybatis-plus-boot-starter.version}</version>
        </dependency>

        <dependency>
            <groupId>com.baomidou</groupId>
            <artifactId>mybatis-plus-core</artifactId>
            <version>${mybatis-plus-boot-starter.version}</version>
        </dependency>

        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
        </dependency>
    </dependencies>
</project>
```

**xuecheng-plus-system-service**

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <parent>
        <artifactId>xuecheng-plus-system</artifactId>
        <groupId>com.yu.xuecheng</groupId>
        <version>1.0-SNAPSHOT</version>
    </parent>
    <modelVersion>4.0.0</modelVersion>

    <artifactId>xuecheng-plus-system-service</artifactId>

    <dependencies>
        <dependency>
            <groupId>com.yu.xuecheng</groupId>
            <artifactId>xuecheng-plus-system-model</artifactId>
            <version>1.0-SNAPSHOT</version>
        </dependency>

        <!-- MySQL 驱动 -->
        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <scope>runtime</scope>
        </dependency>

        <!-- mybatis plus的依赖 -->
        <dependency>
            <groupId>com.baomidou</groupId>
            <artifactId>mybatis-plus-boot-starter</artifactId>
        </dependency>
        <!-- Spring Boot 集成 Junit -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
        <!-- 排除 Spring Boot 依赖的日志包冲突 -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter</artifactId>
            <exclusions>
                <exclusion>
                    <groupId>org.springframework.boot</groupId>
                    <artifactId>spring-boot-starter-logging</artifactId>
                </exclusion>
            </exclusions>
        </dependency>

        <!-- Spring Boot 集成 log4j2 -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-log4j2</artifactId>
        </dependency>
    </dependencies>
</project>
```

**xuecheng-plus-system-api**

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <parent>
        <artifactId>xuecheng-plus-system</artifactId>
        <groupId>com.yu.xuecheng</groupId>
        <version>1.0-SNAPSHOT</version>
    </parent>
    <modelVersion>4.0.0</modelVersion>

    <artifactId>xuecheng-plus-system-api</artifactId>

    <dependencies>
        <dependency>
            <groupId>com.yu.xuecheng</groupId>
            <artifactId>xuecheng-plus-system-model</artifactId>
            <version>1.0-SNAPSHOT</version>
        </dependency>

        <dependency>
            <groupId>com.yu.xuecheng</groupId>
            <artifactId>xuecheng-plus-system-service</artifactId>
            <version>1.0-SNAPSHOT</version>
        </dependency>

        <!--cloud的基础环境包-->
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-context</artifactId>
        </dependency>

        <!-- Spring Boot 的 Spring Web MVC 集成 -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-validation</artifactId>
        </dependency>
        <!-- Spring Boot 对 LocalDateTime
          boot-starter-web自动引入
          -->
        <!-- <dependency>
             <groupId>com.fasterxml.jackson.module</groupId>
             <artifactId>jackson-module-parameter-names</artifactId>
         </dependency>
         <dependency>
             <groupId>com.fasterxml.jackson.datatype</groupId>
             <artifactId>jackson-datatype-jdk8</artifactId>
         </dependency>
         <dependency>
             <groupId>com.fasterxml.jackson.datatype</groupId>
             <artifactId>jackson-datatype-jsr310</artifactId>
         </dependency>-->

        <!--<dependency>
            <groupId>org.apache.commons</groupId>
            <artifactId>commons-pool2</artifactId>
        </dependency>-->
        <!-- 排除 Spring Boot 依赖的日志包冲突 -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter</artifactId>
            <exclusions>
                <exclusion>
                    <groupId>org.springframework.boot</groupId>
                    <artifactId>spring-boot-starter-logging</artifactId>
                </exclusion>
            </exclusions>
        </dependency>

        <!-- Spring Boot 集成 log4j2 -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-log4j2</artifactId>
        </dependency>

        <dependency>
            <groupId>com.github.xiaoymin</groupId>
            <artifactId>knife4j-spring-boot-starter</artifactId>
        </dependency>
    </dependencies>
</project>
```

### 7.3 创建数据库和PO类

#### 7.3.1 创建数据库表

```sql
-- 创建数据字典表
CREATE DATABASE IF NOT EXISTS `xuecheng-system` CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE `xuecheng-system`;

DROP TABLE IF EXISTS `dictionary`;

CREATE TABLE `dictionary` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id标识',
  `name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '数据字典名称',
  `code` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '数据字典代码',
  `item_values` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci COMMENT '数据字典项--json格式\n  ',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `tb_code_unique` (`code`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC COMMENT='数据字典';
```

#### 7.3.2 生成PO类

```java
@Data
@TableName("dictionary")
public class Dictionary implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id标识
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 数据字典名称
     */
    private String name;

    /**
     * 数据字典代码
     */
    private String code;

    /**
     * 数据字典项--json格式
            [{
                  "sd_name": "低级",
                  "sd_id": "200001",
                  "sd_status": "1"
               }, {
                  "sd_name": "中级",
                  "sd_id": "200002",
                  "sd_status": "1"
               }, {
                  "sd_name": "高级",
                  "sd_id": "200003",
                  "sd_status": "1"
               }]
     */
    private String itemValues;
}
```

### 7.4 系统管理项目配置

#### 7.4.1 配置分页插件

在`xuecheng-plus-system-service`模块工程中添加以下配置：

```java
@Configuration
@MapperScan("com.yu.xuecheng.**.mapper")
public class MybatisPlusConfig {
	/**
	 * 新的分页插件
	 * 需要设置 MybatisConfiguration#useDeprecatedExecutor = false
	 * 避免缓存出现问题(该属性会在旧插件移除后一同移除)
	 */
	@Bean
	public MybatisPlusInterceptor mybatisPlusInterceptor() {
		MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
		interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
		return interceptor;
	}
}
```

#### 7.4.2 日志配置

在`xuecheng-plus-content-api`模块`log4j2-dev.xml`文件中添加以下配置：

```xml
<?xml version="1.0" encoding="UTF-8"?>
<Configuration monitorInterval="180" packages="">
    <properties>
        <property name="logdir">logs</property>
        <property name="PATTERN">%date{YYYY-MM-dd HH:mm:ss,SSS} %level [%thread][%file:%line] - %msg%n%throwable</property>
    </properties>
    <Appenders>
        <Console name="Console" target="SYSTEM_OUT">
            <PatternLayout pattern="${PATTERN}"/>
        </Console>

        <RollingFile name="ErrorAppender" fileName="${logdir}/error.log"
            filePattern="${logdir}/$${date:yyyy-MM-dd}/error.%d{yyyy-MM-dd-HH}.log" append="true">
            <PatternLayout pattern="${PATTERN}"/>
            <ThresholdFilter level="ERROR" onMatch="ACCEPT" onMismatch="DENY"/>
            <Policies>
                <TimeBasedTriggeringPolicy interval="1" modulate="true" />
            </Policies>
        </RollingFile>

        <RollingFile name="DebugAppender" fileName="${logdir}/info.log"
            filePattern="${logdir}/$${date:yyyy-MM-dd}/info.%d{yyyy-MM-dd-HH}.log" append="true">
            <PatternLayout pattern="${PATTERN}"/>
            <ThresholdFilter level="DEBUG" onMatch="ACCEPT" onMismatch="DENY"/>
            <Policies>
                <TimeBasedTriggeringPolicy interval="1" modulate="true" />
            </Policies>
        </RollingFile>
        
        <!--异步appender-->
         <Async name="AsyncAppender" includeLocation="true">
            <AppenderRef ref="ErrorAppender"/>
            <AppenderRef ref="DebugAppender"/>
        </Async>
    </Appenders>
    
    <Loggers>
         <!--过滤掉spring和mybatis的一些无用的debug信息-->
        <logger name="org.springframework" level="INFO">
        </logger>
        <logger name="org.mybatis" level="INFO">
        </logger>
        <logger name="cn.itcast.wanxinp2p.consumer.mapper" level="DEBUG">
        </logger>

        <logger name="springfox" level="INFO">
        </logger>
		<logger name="org.apache.http" level="INFO">
        </logger>
        <logger name="com.netflix.discovery" level="INFO">
        </logger>
        
        <logger name="RocketmqCommon"  level="INFO" >
		</logger>
		
		<logger name="RocketmqRemoting" level="INFO"  >
		</logger>
		
		<logger name="RocketmqClient" level="WARN">
		</logger>

        <logger name="org.dromara.hmily" level="WARN">
        </logger>

        <logger name="org.dromara.hmily.lottery" level="WARN">
        </logger>

        <logger name="org.dromara.hmily.bonuspoint" level="WARN">
        </logger>
		
        <Root level="DEBUG" includeLocation="true">
            <AppenderRef ref="AsyncAppender"/>
            <AppenderRef ref="Console"/>
            <AppenderRef ref="DebugAppender"/>
        </Root>
    </Loggers>
</Configuration>
```

#### 7.4.3 项目基础配置

在`xuecheng-plus-system-service`模块`application.yml`文件中添加以下配置：

```yaml
spring:
  application:
    name: system-service
  datasource:
    driver-class-name: com.mysql.cj.jdbc.Driver
    url: jdbc:mysql://ip:port/xuecheng-content?useUnicode=true&characterEncoding=UTF-8&useSSL=false&serverTimezone=Asia/Shanghai&allowPublicKeyRetrieval=true
    username: xxx
    password: xxx

# 日志文件配置路径
logging:
  config: classpath:log4j2-dev.xml
```

在`xuecheng-plus-content-api`模块`bootstrap.yml`文件中添加以下配置：

```yaml
server:
  servlet:
    context-path: /system
  port: 63110
# 微服务配置
spring:
  application:
    name: system-service

# 日志文件配置路径
logging:
  config: classpath:log4j2-dev.xml
```

#### 7.4.4 生成service、mapper接口

在`xuecheng-plus-system-service`模块中生成相关接口及实现类，我这里使用`MyBatisPlus`插件生成，不做过多介绍

#### 7.4.5 跨域配置

```java
@Configuration
public class GlobalCorsConfig {

    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration config = new CorsConfiguration();
        //允许白名单域名进行跨域调用
        config.addAllowedOrigin("*");
        //允许跨越发送cookie
        config.setAllowCredentials(true);
        //放行全部原始头信息
        config.addAllowedHeader("*");
        //允许所有请求方法跨域调用
        config.addAllowedMethod("*");
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }
}
```

#### 7.4.6 系统服务启动类

```java
@EnableScheduling
@SpringBootApplication
public class SystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(SystemApplication.class,args);
    }
}
```

## 8、网关服务

### 8.1 Nacos

官方文档地址：https://nacos.io/zh-cn/docs

GitHub地址：https://github.com/alibaba/nacos

#### 8.1.1 概念

`Nacos` 有两个核心概念：`namespace` (命名空间)和 `group` (组)。

- namespace：用于区分环境，比如开发、测试、生产环境
- group：用于区分项目，比如 project1、project2

#### 8.1.2 搭建 Nacos

- 创建 docker-compose.yml

  ```yaml
  version: "3"
  services:
    db:
      image: mysql:8.0.32
      container_name: xuecheng-mysql
      restart: unless-stopped
      ports:
        - "29502:3306"
      env_file:
        - $PWD/mysql/mysql.env
      volumes:
        - $PWD/mysql/data:/var/lib/mysql
        - $PWD/mysql/logs:/var/log/mysql
        - $PWD/mysql/conf.d:/etc/mysql/conf.d
        - $PWD/mysql/my.cnf:/etc/mysql/my.cnf
      networks:
        - yu
     
    nacos:
      image: nacos/nacos-server:1.4.1
      container_name: xuecheng-nacos
      restart: unless-stopped
      environment:
        PREFER_HOST_MODE: hostname #如果支持主机名可以使用hostname,否则使用ip，默认也是ip
        SPRING_DATASOURCE_PLATFORM: mysql #数据源平台 仅支持mysql或不保存empty
        MODE: standalone
        JVM_XMS: 256m
        JVM_XMX: 256m
        JVM_XMN: 128m
        JVM_MMS: 256m
      depends_on:
        - db
      volumes:
        - $PWD/nacos/logs/:/home/nacos/logs
        - $PWD/nacos/plugins/:/home/nacos/plugins
        - $PWD/nacos/conf/application.properties:/home/nacos/conf/application.properties
      ports:
        - "19948:19948"   
      networks:
        - yu
  
  networks:
         yu: {}
  ```

- 创建 nacos 的 `application.properties` 文件

  ```properties
  server.contextPath=/nacos
  server.servlet.contextPath=/nacos
  server.port=19948
  
  spring.datasource.platform=mysql
  
  db.num=1
  db.url.0=jdbc:mysql://ip:port/nacos?characterEncoding=utf8&connectTimeout=1000&socketTimeout=3000&autoReconnect=true
  db.user=xxx
  db.password=xxx
  
  nacos.cmdb.dumpTaskInterval=3600
  nacos.cmdb.eventTaskInterval=10
  nacos.cmdb.labelTaskInterval=300
  nacos.cmdb.loadDataAtStart=false
  
  management.metrics.export.elastic.enabled=false
  management.metrics.export.influx.enabled=false
  
  server.tomcat.accesslog.enabled=true
  server.tomcat.accesslog.pattern=%h %l %u %t "%r" %s %b %D %{User-Agent}i
  
  nacos.security.ignore.urls=/,/**/*.css,/**/*.js,/**/*.html,/**/*.map,/**/*.svg,/**/*.png,/**/*.ico,/console-fe/public/**,/v1/auth/login,/v1/console/health/**,/v1/cs/**,/v1/ns/**,/v1/cmdb/**,/actuator/**,/v1/console/server/**
  nacos.naming.distro.taskDispatchThreadCount=1
  nacos.naming.distro.taskDispatchPeriod=200
  nacos.naming.distro.batchSyncKeyCount=1000
  nacos.naming.distro.initDataRatio=0.9
  nacos.naming.distro.syncRetryDelay=5000
  nacos.naming.data.warmup=true
  nacos.naming.expireInstance=true
  ```

- my.cnf

  ```properties
  [client]
  port = 3306 # 客户端连接MySQL的端口号
  
  default-character-set = utf8mb4 # 客户端连接MySQL时默认使用的字符集
  
  [mysql]
  no-auto-rehash  # 禁用自动重哈希功能
  
  [mysqld]
  port = 3306 # MySQL监听的端口号
  
  bind-address = 0.0.0.0  # 绑定的IP地址，0.0.0.0表示所有可用的IP地址
  
  character-set-server = utf8mb4  # 服务器端使用的默认字符集
  collation-server = utf8mb4_unicode_ci # 服务器端使用字符集校对规则
  
  skip-name-resolve # 禁用DNS反向解析，加速连接建立速度
  
  max_connections = 200 # 最大连接数
  
  # 默认使用“mysql_native_password”插件认证
  default_authentication_plugin=mysql_native_password
  
  # SQL模式，可以根据需求进行调整
  # sql_mode=STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION
  
  log-error=/var/log/mysql/error.log  # 错误日志文件路径
  
  pid-file=/var/run/mysqld/mysqld.pid # MySQL进程ID文件路径
  ```

- mysql.env

  ```properties
  # root用户密码,自行修改
  MYSQL_ROOT_PASSWORD=xxx
  # 创建容器时创建并使用指定的数据库
  MYSQL_DATABASE=xuecheng-content
  # 创建普通用户
  MYSQL_USER=xxx
  # 普通用户密码,自行修改
  MYSQL_PASSWORD=xxx
  # 指定数据库所在时区
  TZ=Asia/Shanghai
  ```

- 创建 Nacos 容器和 MySQL 容器

  ```shell
  docker-compose up -d
  ```
  
- 创建数据库

  ```sql
  CREATE DATABASE `nacos` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci */ /*!80016 DEFAULT ENCRYPTION='N' */
  ```
  
- 下载与容器同版本的 Nacos 压缩包

  下载地址：https://github.com/alibaba/nacos/releases
  
- 导入 nacos 数据库脚本执行文件

#### 8.1.3 配置 Nacos

1. 注册中心配置

   1. 在父POM文件中添加服务发现依赖信息

      ```xml
      <dependency>
          <groupId>com.alibaba.cloud</groupId>
          <artifactId>spring-cloud-alibaba-dependencies</artifactId>
          <version>${spring-cloud-alibaba.version}</version>
          <type>pom</type>
          <scope>import</scope>
      </dependency>
      ```

   2. 内容管理（xuecheng-plus-content-api）、系统管理模块（xuecheng-plus-system-service）添加以下依赖

      ```xml
      <!-- nacos服务发现依赖 -->
      <dependency>
          <groupId>com.alibaba.cloud</groupId>
          <artifactId>spring-cloud-starter-alibaba-nacos-discovery</artifactId>
      </dependency>
      ```

   3. 内容管理模块（xuecheng-plus-content-api）配置

      ```yaml
      spring:
        application:
          name: content-api
        cloud:
          nacos:
            server-addr: ip:port
            # 注册中心配置
            discovery:
              namespace: dev
              group: xuecheng-plus
      ```

   4. 系统管理模块（xuecheng-plus-system-service）配置

      ```yaml
      spring:
        application:
          name: system-service
        cloud:
          nacos:
            server-addr: ip:port
            # 注册中心配置
            discovery:
              namespace: dev
              group: xuecheng-plus
      ```

2. 配置中心配置
