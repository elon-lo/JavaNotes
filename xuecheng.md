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

   3. 内容管理模块 bootstrap.yml 配置

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

   4. 系统管理模块 bootstrap.yml 配置

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

   搭建 `Nacos` 配置中心，就是为了通过 `Nacos` 去管理项目的所有配置，`Nacos` 通过 `namespace`、`group`、`dataid` 三个参数来声明一个具体的配置信息。

   第一步，通过 namespace、group 找到具体的环境和具体的项目。

   第二步，通过 dataid 找到具体的配置文件，dataid 由三部分组成：

   项目名：比如 project1，在Spring Boot 中可通过spring.application.name来配置

   环境名：由 spring.profiles.active 指定

   配置文件后缀名：如 properties、yml、yaml 等格式

3. Nacos 普通配置

   1. 添加配置中心依赖

      ```xml
      <!-- nacos服务配置依赖 -->
      <dependency>
          <groupId>com.alibaba.cloud</groupId>
          <artifactId>spring-cloud-starter-alibaba-nacos-config</artifactId>
      </dependency>
      ```

   2. bootstrap.yml 添加 Nacos 配置

      ```yaml
      server:
        servlet:
          context-path: /content
        port: 63040
      
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
            # 配置中心配置
            config:
              namespace: dev
              group: xuecheng-plus
              file-extension: yaml
              refresh-enabled: true
      
        datasource:
          driver-class-name: com.mysql.cj.jdbc.Driver
          url: jdbc:mysql://ip:port/xuecheng-content?useUnicode=true&characterEncoding=UTF-8&useSSL=false&serverTimezone=Asia/Shanghai&allowPublicKeyRetrieval=true
          username: xxx
          password: xxx
        profiles:
          active: dev
      ```

   3. 创建 Nacos 命令空间

      ![image-20231222225514438](https://image.elonlo.top/img/2023/12/22/6585a3524eb3c.png)

   4. Nacos 添加 content-api-dev 配置

      ![image-20231222232233274](https://image.elonlo.top/img/2023/12/22/6585a9b95353c.png)

   5. 移除本地服务 bootstrap.yml 配置

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
            # 配置中心配置
            config:
              namespace: dev
              group: xuecheng-plus
              file-extension: yaml
              refresh-enabled: true
      
        profiles:
          active: dev
      ```

4. 配置引用

   1. xuecheng-plus-content-service 添加依赖

      ```xml
      <!-- nacos服务配置依赖 -->
      <dependency>
          <groupId>com.alibaba.cloud</groupId>
          <artifactId>spring-cloud-starter-alibaba-nacos-config</artifactId>
      </dependency>
      ```

   2. 服务 bootstrap.yml 配置

      ```yaml
      spring:
        application:
          name: content-service
        cloud:
          nacos:
            server-addr: ip:port
            # 配置中心配置
            config:
              namespace: dev
              group: xuecheng-plus
              file-extension: yaml
              refresh-enabled: true
      
        profiles:
          active: dev
      ```

   3. Nacos 添加 content-service-dev 配置

      ![image-20231222232414391](https://image.elonlo.top/img/2023/12/22/6585aa1e2da50.png)

   4. xuecheng-plus-content-api 引用 xuecheng-plus-content-service 配置

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
            # 配置中心配置
            config:
              namespace: dev
              group: xuecheng-plus
              file-extension: yaml
              refresh-enabled: true
              # 引用content-service-dev配置
              extension-configs:
                - data-id: content-service-${spring.profiles.active}.yaml
                  group: xuecheng-plus
                  refresh: true
                  
        profiles:
          active: dev            
      ```

5. Nacos 公共配置

   1. 创建日志配置

      ![image-20231222234451303](https://image.elonlo.top/img/2023/12/22/6585aef344881.png)

   2. 服务 bootstrap.yml 配置

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
            # 配置中心配置
            config:
              namespace: dev
              group: xuecheng-plus
              file-extension: yaml
              refresh-enabled: true
              # 引用content-service-dev配置
              extension-configs:
                - data-id: content-service-${spring.profiles.active}.yaml
                  group: xuecheng-plus
                  refresh: true
              # 读取公共配置
              shared-configs:
                - data-id: logging-${spring.profiles.active}.yaml
                  group: xuecheng-plus-common
                  refresh: true
        profiles:
          active: dev
      ```

6. Nacos 配置优先级

   引入配置文件的方式有以下几种：

   1. 以项目应用名方式引入
   2. 以扩展文件方式引入
   3. 以共享配置文件方式引入
   4. 本地配置文件

   各配置文件的优先级从高到低依次为：项目应用配置文件＞扩展配置文件＞共享配置文件＞本地配置文件

   1. Nacos 配置本地配置文件优先

      ![image-20231223000639918](https://image.elonlo.top/img/2023/12/23/6585b40fbdb37.png)

7. 导入配置文件

### 8.2 Gateway

1. 创建工程

2. 添加依赖信息

   ```xml
   <?xml version="1.0" encoding="UTF-8"?>
   <project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
            xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
       <parent>
           <artifactId>xuecheng-plus</artifactId>
           <groupId>com.yu.xuecheng</groupId>
           <version>1.0-SNAPSHOT</version>
       </parent>
       <modelVersion>4.0.0</modelVersion>
   
       <artifactId>xuecheng-plus-gateway</artifactId>
   
       <dependencies>
           <!-- 网关 -->
           <dependency>
               <groupId>org.springframework.cloud</groupId>
               <artifactId>spring-cloud-starter-gateway</artifactId>
           </dependency>
   
           <!-- nacos服务发现依赖 -->
           <dependency>
               <groupId>com.alibaba.cloud</groupId>
               <artifactId>spring-cloud-starter-alibaba-nacos-discovery</artifactId>
           </dependency>
   
           <!-- nacos服务配置依赖 -->
           <dependency>
               <groupId>com.alibaba.cloud</groupId>
               <artifactId>spring-cloud-starter-alibaba-nacos-config</artifactId>
           </dependency>
   
           <dependency>
               <groupId>com.alibaba</groupId>
               <artifactId>fastjson</artifactId>
           </dependency>
   
           <dependency>
               <groupId>org.projectlombok</groupId>
               <artifactId>lombok</artifactId>
           </dependency>
   
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

3. 添加 bootstrap.yml 配置信息

   ```yaml
   spring:
     application:
       name: gateway
     cloud:
       nacos:
         server-addr: 124.220.32.153:19948
         # 注册中心配置
         discovery:
           namespace: dev
           group: xuecheng-plus
         # 配置中心配置
         config:
           namespace: dev
           group: xuecheng-plus
           file-extension: yaml
           refresh-enabled: true
           # 读取公共配置
           shared-configs:
             - data-id: logging-${spring.profiles.active}.yaml
               group: xuecheng-plus-common
               refresh: true
     profiles:
       active: dev
   ```

4. `Nacos` 添加网关配置

   ```yaml
   server:
     port: 63010 # 网关端口
   spring:
     cloud:
       gateway:
         routes: # 网关路由配置
           - id: content-api # 路由id，自定义，只要唯一即可
             # uri: http://127.0.0.1:8081 # 路由的目标地址 http就是固定地址
             uri: lb://content-api # 路由的目标地址 lb就是负载均衡，后面跟服务名称
             predicates: # 路由断言，也就是判断请求是否符合路由规则的条件
               - Path=/content/** # 这个是按照路径匹配，只要以/content/开头就符合要求
           - id: system-api
             uri: lb://system-api
             predicates:
               - Path=/system/**
   ```

## 9、媒资服务

### 9.1 创建工程

1. 创建媒资服务父工程，添加以下 `pom.xml`

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
   
       <artifactId>xuecheng-plus-media</artifactId>
       <packaging>pom</packaging>
       <modules>
           <module>xuecheng-plus-media-api</module>
           <module>xuecheng-plus-media-service</module>
           <module>xuecheng-plus-media-model</module>
       </modules>
   
       <properties>
           <maven.compiler.source>8</maven.compiler.source>
           <maven.compiler.target>8</maven.compiler.target>
       </properties>
   
   </project>
   ```

2. 创建媒资服务 API 工程，添加以下 `pom.xml`

   ```xml
   <?xml version="1.0" encoding="UTF-8"?>
   <project xmlns="http://maven.apache.org/POM/4.0.0"
            xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
            xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
       <parent>
           <artifactId>xuecheng-plus-media</artifactId>
           <groupId>com.yu.xuecheng</groupId>
           <version>1.0-SNAPSHOT</version>
       </parent>
       <modelVersion>4.0.0</modelVersion>
   
       <artifactId>xuecheng-plus-media-api</artifactId>
   
       <properties>
           <maven.compiler.source>8</maven.compiler.source>
           <maven.compiler.target>8</maven.compiler.target>
       </properties>
   
       <dependencies>
           <dependency>
               <groupId>com.alibaba.cloud</groupId>
               <artifactId>spring-cloud-starter-alibaba-nacos-discovery</artifactId>
           </dependency>
           <dependency>
               <groupId>com.alibaba.cloud</groupId>
               <artifactId>spring-cloud-starter-alibaba-nacos-config</artifactId>
           </dependency>
   
           <dependency>
               <groupId>com.yu.xuecheng</groupId>
               <artifactId>xuecheng-plus-media-service</artifactId>
               <version>1.0-SNAPSHOT</version>
           </dependency>
   
           <dependency>
               <groupId>com.yu.xuecheng</groupId>
               <artifactId>xuecheng-plus-media-model</artifactId>
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
               <artifactId>spring-boot-starter-test</artifactId>
               <scope>test</scope>
           </dependency>
   
           <dependency>
               <groupId>org.springframework.boot</groupId>
               <artifactId>spring-boot-configuration-processor</artifactId>
               <optional>true</optional>
           </dependency>
   
           <dependency>
               <groupId>org.springframework.boot</groupId>
               <artifactId>spring-boot-starter-validation</artifactId>
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

3. 创建媒资服务 Service 工程，添加以下 `pom.xml`

   ```xml
   <?xml version="1.0" encoding="UTF-8"?>
   <project xmlns="http://maven.apache.org/POM/4.0.0"
            xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
            xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
       <parent>
           <artifactId>xuecheng-plus-media</artifactId>
           <groupId>com.yu.xuecheng</groupId>
           <version>1.0-SNAPSHOT</version>
       </parent>
       <modelVersion>4.0.0</modelVersion>
   
       <artifactId>xuecheng-plus-media-service</artifactId>
   
       <properties>
           <maven.compiler.source>8</maven.compiler.source>
           <maven.compiler.target>8</maven.compiler.target>
           <minio.version>8.4.3</minio.version>
           <myOkhttp.version>4.8.1</myOkhttp.version>
       </properties>
   
       <dependencies>
           <dependency>
               <groupId>com.alibaba.cloud</groupId>
               <artifactId>spring-cloud-starter-alibaba-nacos-config</artifactId>
           </dependency>
   
           <dependency>
               <groupId>com.yu.xuecheng</groupId>
               <artifactId>xuecheng-plus-media-model</artifactId>
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
   
           <dependency>
               <groupId>io.minio</groupId>
               <artifactId>minio</artifactId>
               <version>${minio.version}</version>
           </dependency>
   
           <dependency>
               <groupId>com.squareup.okhttp3</groupId>
               <artifactId>okhttp</artifactId>
               <version>${myOkhttp.version}</version>
           </dependency>
       </dependencies>
   
   </project>
   ```

4. 创建媒资服务 Model 工程，添加以下 `pom.xml`

   ```xml
   <?xml version="1.0" encoding="UTF-8"?>
   <project xmlns="http://maven.apache.org/POM/4.0.0"
            xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
            xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
       <parent>
           <artifactId>xuecheng-plus-media</artifactId>
           <groupId>com.yu.xuecheng</groupId>
           <version>1.0-SNAPSHOT</version>
       </parent>
       <modelVersion>4.0.0</modelVersion>
   
       <artifactId>xuecheng-plus-media-model</artifactId>
   
       <properties>
           <maven.compiler.source>8</maven.compiler.source>
           <maven.compiler.target>8</maven.compiler.target>
       </properties>
   
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

### 9.2 创建数据库及相关表

```sql
CREATE DATABASE `xuecheng-media` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci */ /*!80016 DEFAULT ENCRYPTION='N' */
```

```sql
DROP TABLE IF EXISTS `media_files`;

CREATE TABLE `media_files` (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '文件id,md5值',
  `company_id` bigint DEFAULT NULL COMMENT '机构ID',
  `company_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '机构名称',
  `filename` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '文件名称',
  `file_type` varchar(12) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '文件类型（图片、文档，视频）',
  `tags` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '标签',
  `bucket` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '存储目录',
  `file_path` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '存储路径',
  `file_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '文件id',
  `url` varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '媒资文件访问地址',
  `username` varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '上传人',
  `create_date` datetime DEFAULT NULL COMMENT '上传时间',
  `change_date` datetime DEFAULT NULL COMMENT '修改时间',
  `status` varchar(12) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT '1' COMMENT '状态,1:正常，0:不展示',
  `remark` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '备注',
  `audit_status` varchar(12) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '审核状态',
  `audit_mind` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '审核意见',
  `file_size` bigint DEFAULT NULL COMMENT '文件大小',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `unique_fileid` (`file_id`) USING BTREE COMMENT '文件id唯一索引 '
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='媒资信息';

DROP TABLE IF EXISTS `media_process`;

CREATE TABLE `media_process` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `file_id` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '文件标识',
  `filename` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '文件名称',
  `bucket` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '存储桶',
  `file_path` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '存储路径',
  `status` varchar(12) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '状态,1:未处理，2：处理成功  3处理失败',
  `create_date` datetime NOT NULL COMMENT '上传时间',
  `finish_date` datetime DEFAULT NULL COMMENT '完成时间',
  `fail_count` int DEFAULT 0 COMMENT '失败次数',
  `url` varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '媒资文件访问地址',
  `errormsg` varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '失败原因',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `unique_fileid` (`file_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

DROP TABLE IF EXISTS `media_process_history`;

CREATE TABLE `media_process_history` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `file_id` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '文件标识',
  `filename` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '文件名称',
  `bucket` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '存储源',
  `status` varchar(12) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '状态,1:未处理，2：处理成功  3处理失败',
  `create_date` datetime NOT NULL COMMENT '上传时间',
  `finish_date` datetime NOT NULL COMMENT '完成时间',
  `url` varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '媒资文件访问地址',
  `fail_count` int DEFAULT 0 COMMENT '失败次数',
  `file_path` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '文件路径',
  `errormsg` varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '失败原因',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

DROP TABLE IF EXISTS `mq_message`;

CREATE TABLE `mq_message` (
  `id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '消息id',
  `message_type` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '消息类型代码',
  `business_key1` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '关联业务信息',
  `business_key2` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '关联业务信息',
  `business_key3` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '关联业务信息',
  `mq_host` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '消息队列主机',
  `mq_port` int NOT NULL COMMENT '消息队列端口',
  `mq_virtualhost` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '消息队列虚拟主机',
  `mq_queue` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '队列名称',
  `inform_num` int unsigned NOT NULL COMMENT '通知次数',
  `state` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '处理状态，0:初始，1:成功',
  `returnfailure_date` datetime DEFAULT NULL COMMENT '回复失败时间',
  `returnsuccess_date` datetime DEFAULT NULL COMMENT '回复成功时间',
  `returnfailure_msg` varchar(2048) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '回复失败内容',
  `inform_date` datetime DEFAULT NULL COMMENT '最近通知时间',
  `stage_state1` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '阶段1处理状态, 0:初始，1:成功',
  `stage_state2` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '阶段2处理状态, 0:初始，1:成功',
  `stage_state3` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '阶段3处理状态, 0:初始，1:成功',
  `stage_state4` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '阶段4处理状态, 0:初始，1:成功',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

DROP TABLE IF EXISTS `mq_message_history`;

CREATE TABLE `mq_message_history` (
  `id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '消息id',
  `message_type` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '消息类型代码',
  `business_key1` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '关联业务信息',
  `business_key2` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '关联业务信息',
  `business_key3` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '关联业务信息',
  `mq_host` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '消息队列主机',
  `mq_port` int NOT NULL COMMENT '消息队列端口',
  `mq_virtualhost` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '消息队列虚拟主机',
  `mq_queue` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '队列名称',
  `inform_num` int(10) unsigned zerofill DEFAULT NULL COMMENT '通知次数',
  `state` int(10) unsigned zerofill DEFAULT NULL COMMENT '处理状态，0:初始，1:成功，2:失败',
  `returnfailure_date` datetime DEFAULT NULL COMMENT '回复失败时间',
  `returnsuccess_date` datetime DEFAULT NULL COMMENT '回复成功时间',
  `returnfailure_msg` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '回复失败内容',
  `inform_date` datetime DEFAULT NULL COMMENT '最近通知时间',
  `stage_state1` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `stage_state2` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `stage_state3` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `stage_state4` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;
```

### 9.3 整合 MinIO

#### 9.3.1 概述

MinIO 是一个轻量级的服务，可以很简单的和其他应用结合使用，它兼容亚马逊 S3 云存储服务接口，非常适合存储大容量非结构化的数据，例如图片、视频、日志文件、备份数据和容器/虚拟机镜像等。

它的最大特点就是轻量，使用简单，功能强大，支持各种平台，单个文件最大支持5TB，兼容 Amazon S3 接口，提供了Java、Python、GO等多版本SDK支持。

官网地址：https://min.io/

官方文档地址：https://www.minio.org.cn/，https://docs.minio.org.cn/docs

MinIO 集群采用去中心化共享架构，每个节点是对等关系，通过 Nginx 可对 MinIO 进行负载均衡访问。

在大数据领域，通常的设计理念都是无中心和分布式。MinIO 分布式模式可以帮助你搭建一个高可用的对象存储服务，你可以使用这些存储设备，而不用考虑其真实物理地址。

它将分布在不同服务器上的多块硬盘组成一个对象存储服务。由于硬盘分布在不同的节点上，分布式 MinIO 避免了单点故障。

#### 9.3.2 创建 MinIO 容器

MinIO `docker-compose.yml` 配置文件示例如下：

```yaml
version: '3'

services:
  minio:
    image: 'bitnami/minio:latest'
    container_name: your_container_name
    cap_add:
      - NET_ADMIN
      - SYS_MODULE
    ports:
      - 'port1:9000'
      - 'port2:9001'
    volumes:
      - $PWD/data:/bitnami/minio/data
    environment:
      - MINIO_ROOT_USER=your_user
      - MINIO_ROOT_PASSWORD=your_password
      - PUID=1000
      - PGID=1000
    networks:
      - app

networks:
  app:
    driver: bridge
```

```shell
docker-compose up -d
```

### 9.4 准备环境

1. 创建 `file` 桶和 `video` 桶

   ![image-20240102145447411](https://image.elonlo.top/img/2024/01/02/6593b34102910.png)

   ![image-20240102145522052](https://image.elonlo.top/img/2024/01/02/6593b35ae475c.png)

   <span style="color:red">注意：需要将 `Access Policy` 设置为 `public`，否则将无法上传图片或视频</span>

2. 媒资服务 Nacos 配置

   - media-service-dev

     ```yaml
     spring:
     # 数据库配置
       datasource:
         driver-class-name: com.mysql.cj.jdbc.Driver
         url: jdbc:mysql://ip:port/xuecheng-media?useUnicode=true&characterEncoding=UTF-8&useSSL=false&serverTimezone=Asia/Shanghai&allowPublicKeyRetrieval=true
         username: xxx
         password: xxx
       cloud:
        config:
         override-none: true
     
     minio:
       endpoint: http://ip:port
       accessKey: xxx
       secretKey: xxx
       bucket:
         files: file
         videofiles: video
     ```

   - media-api-dev

     ```yaml
     server:
       servlet:
         context-path: /media
       port: 63050
     ```

3. 媒资服务项目配置

   ```yaml
   spring:
     application:
       name: media-service
     cloud:
       nacos:
         server-addr: ip:port
         # 配置中心配置
         config:
           namespace: dev
           group: xuecheng-plus
           file-extension: yaml
           refresh-enabled: true
           # 读取公共配置
           shared-configs:
             - data-id: logging-${spring.profiles.active}.yaml
               group: xuecheng-plus-common
               refresh: true
     profiles:
       active: dev
   ```

   ```yaml
   spring:
     application:
       name: media-api
     cloud:
       nacos:
         server-addr: ip:port
         # 注册中心配置
         discovery:
           namespace: dev
           group: xuecheng-plus
         # 配置中心配置
         config:
           namespace: dev
           group: xuecheng-plus
           file-extension: yaml
           refresh-enabled: true
           # 引用content-service-dev配置
           extension-configs:
             - data-id: media-service-${spring.profiles.active}.yaml
               group: xuecheng-plus
               refresh: true
           # 读取公共配置
           shared-configs:
             - data-id: logging-${spring.profiles.active}.yaml
               group: xuecheng-plus-common
               refresh: true
   
     profiles:
       active: dev
   ```

4. 创建属性类读取配置属性（media-service）

   ```java
   /**
    * minio属性类
    *
    * @author elonlo
    * @date 2024/1/2 15:05
    */
   @Data
   @Component
   @ConfigurationProperties(prefix = MinioProperties.MINIO_PREFIX)
   public class MinioProperties {
   
       /**
        * minio属性前缀
        */
       public static final String MINIO_PREFIX = "minio";
   
       /**
        * minio节点
        */
       private String endpoint;
   
       /**
        * minio accessKey
        */
       private String accessKey;
   
       /**
        * minio secretKey
        */
       private String secretKey;
   
       /**
        * minio bucket
        */
       private Bucket bucket;
   
       @Data
       public static class Bucket {
           /**
            * minio file bucket
            */
           private String files;
   
           /**
            * minio video bucket
            */
           private String videofiles;
       }
   }
   ```

5. 创建配置类（media-service）

   ```java
   /**
    * Minio配置类
    *
    * @author elonlo
    * @date 2024/1/2 15:16
    */
   @Configuration
   public class MinioConfig {
   
       @Resource
       private MinioProperties minioProperties;
   
       @Bean
       public MinioClient minioClient() {
           return MinioClient.builder()
                   .endpoint(minioProperties.getEndpoint())
                   .credentials(minioProperties.getAccessKey(), minioProperties.getSecretKey())
                   .build();
       }
   }
   ```

### 9.5 上传图片

1. 添加依赖和工具类（base）

   ```xml
   <properties>
       <simplemagic.version>1.17</simplemagic.version>
   </properties>
   <!--根据扩展名取mimetype-->
   <dependency>
       <groupId>com.j256.simplemagic</groupId>
       <artifactId>simplemagic</artifactId>
       <version>${simplemagic.version}</version>
   </dependency>
   ```

   ```java
   /**
    * 文件工具类
    *
    * @author elonlo
    * @date 2024/1/3 11:14
    */
   public class FileUtil {
   
       /**
        * 获取文件类型
        */
       public static String getMimeType(String extensionName) {
           if (StringUtils.isEmpty(extensionName)) {
               extensionName = "";
           }
           //根据扩展名取出mimeType
           ContentInfo extensionMatch = ContentInfoUtil.findExtensionMatch(extensionName);
           //通用mimeType字节流
           String mimeType = MediaType.APPLICATION_OCTET_STREAM_VALUE;
           if (StringUtils.hasText(mimeType)) {
               mimeType = extensionMatch.getMimeType();
           }
           return mimeType;
       }
   }
   ```

2. 添加文件DTO、PO、VO

   ```java
   /**
    * 上传文件普通参数
    *
    * @author elonlo
    * @date 2024/1/3 10:23
    */
   @NoArgsConstructor
   @AllArgsConstructor
   @Builder
   @Data
   public class UploadFileDTO implements Serializable {
   
       private static final long serialVersionUID = 1L;
   
       /**
        * 文件名
        */
       private String filename;
   
       /**
        * 文件类型（文档,音频,视频）
        */
       private String fileType;
   
       /**
        * 文件大小
        */
       private Long fileSize;
   
       /**
        * 标签
        */
       private String tags;
   
       /**
        * 上传人
        */
       private String username;
   
       /**
        * 备注
        */
       private String remark;
   }
   ```

   ```java
   /**
    * <p>
    * 媒资信息
    * </p>
    *
    * @author elonlo
    * @since 2024-01-02
    */
   @Data
   @EqualsAndHashCode(callSuper = false)
   @Accessors(chain = true)
   @TableName("media_files")
   public class MediaFiles implements Serializable {
   
       private static final long serialVersionUID = 1L;
   
       /**
        * 文件id,md5值
        */
       @TableId(value = "id", type = IdType.INPUT)
       private String id;
   
       /**
        * 机构ID
        */
       private Long companyId;
   
       /**
        * 机构名称
        */
       private String companyName;
   
       /**
        * 文件名称
        */
       private String filename;
   
       /**
        * 文件类型（图片、文档，视频）
        */
       private String fileType;
   
       /**
        * 标签
        */
       private String tags;
   
       /**
        * 存储目录
        */
       private String bucket;
   
       /**
        * 存储路径
        */
       private String filePath;
   
       /**
        * 文件id
        */
       private String fileId;
   
       /**
        * 媒资文件访问地址
        */
       private String url;
   
       /**
        * 上传人
        */
       private String username;
   
       /**
        * 上传时间
        */
       private LocalDateTime createDate;
   
       /**
        * 修改时间
        */
       private LocalDateTime changeDate;
   
       /**
        * 状态,1:正常，0:不展示
        */
       private String status;
   
       /**
        * 备注
        */
       private String remark;
   
       /**
        * 审核状态
        */
       private String auditStatus;
   
       /**
        * 审核意见
        */
       private String auditMind;
   
       /**
        * 文件大小
        */
       private Long fileSize;
   }
   ```

   ```java
   /**
    * 上传文件VO
    *
    * @author elonlo
    * @date 2024/1/3 11:25
    */
   @Data
   public class UploadFileVO extends MediaFiles implements Serializable {
   
       private static final long serialVersionUID = 1L;
   }
   ```

3. 上传文件控制器（media-api）

   ```java
   /**
    * 媒资服务控制器
    *
    * @author elonlo
    * @date 2024/1/3 11:23
    */
   @RestController
   public class MediaController {
   
       private final IMediaFilesService mediaFilesService;
   
       public MediaController(IMediaFilesService mediaFilesService) {
           this.mediaFilesService = mediaFilesService;
       }
   
       @ApiOperation(value = "文件上传")
       @PostMapping("/upload/course")
       public ResultResponse<UploadFileVO> uploadFile(@RequestPart("file")MultipartFile file) {
           UploadFileVO uploadFileVO = mediaFilesService.uploadFile(file);
           return ResultResponse.success(uploadFileVO);
       }
   }
   ```

4. 上传文件服务相关接口

   ```java
   /**
    * <p>
    * 媒资信息 服务类
    * </p>
    *
    * @author elonlo
    * @since 2024-01-02
    */
   public interface IMediaFilesService extends IService<MediaFiles> {
   
       /**
        * 上传文件
        *
        * @param file 文件
        * @return {@link UploadFileVO}
        */
       UploadFileVO uploadFile(MultipartFile file);
   
       /**
        * 保存文件信息
        *
        * @param companyId  机构id
        * @param fileMd5    文件MD5
        * @param dto        文件相关参数
        * @param bucketName 桶名称
        * @param objetName  对象名称
        * @return {@link MediaFiles}
        */
       MediaFiles saveFile(Long companyId, String fileMd5, UploadFileDTO dto, String bucketName, String objetName);
   }
   ```

   ```java
   /**
    * <p>
    * 媒资信息 服务实现类
    * </p>
    *
    * @author elonlo
    * @since 2024-01-02
    */
   @Slf4j
   @Service
   public class MediaFilesServiceImpl extends ServiceImpl<MediaFilesMapper, MediaFiles> implements IMediaFilesService {
   
       private final MediaFilesMapper mediaFilesMapper;
   
       private final MinioClient minioClient;
   
       @Resource
       private IMediaFilesService mediaFilesService;
   
       private final MinioProperties minioProperties;
   
       public MediaFilesServiceImpl(MediaFilesMapper mediaFilesMapper, MinioClient minioClient, MinioProperties minioProperties) {
           this.mediaFilesMapper = mediaFilesMapper;
           this.minioClient = minioClient;
           this.minioProperties = minioProperties;
       }
   
       /**
        * 上传文件
        */
       @Override
       public UploadFileVO uploadFile(MultipartFile file) {
           Long companyId = 12322332L;
   
           // 1.通过file构建UploadFileDTO参数
           UploadFileDTO dto = new UploadFileDTO();
           // 文件名称
           dto.setFilename(file.getOriginalFilename());
           // 文件大小
           dto.setFileSize(file.getSize());
           // 文件类型
           dto.setFileType("001001");
   
           // 2.通过file构建localFilePath
           String localFilePath = this.getLocalFilePath(file);
   
           // 3.上传文件到minio
           String fileName = dto.getFilename();
           // 获取文件扩展名
           String extensionName = fileName.substring(fileName.lastIndexOf("."));
   
           // 获取文件类型
           String mimeType = FileUtil.getMimeType(extensionName);
   
           // 获取文件的md5
           String fileMd5 = this.calculateFileMd5(new File(localFilePath));
   
           String defaultFolderPath = this.getDefaultFolderPath();
           String objectName = defaultFolderPath + fileMd5 + extensionName;
   
           boolean uploadFlag = this.uploadMinio(minioProperties.getBucket().getFiles(), localFilePath, mimeType, objectName);
           if (!uploadFlag) {
               throw new BusinessException("文件上传失败");
           }
   
           // 4.保存文件信息到数据库
           MediaFiles mediaFiles = mediaFilesService.saveFile(companyId, fileMd5, dto, minioProperties.getBucket().getFiles(), objectName);
           if (Objects.isNull(mediaFiles)) {
               throw new BusinessException("保存文件信息到数据库失败");
           }
   
           return CopyUtils.copy(mediaFiles, UploadFileVO.class);
       }
   
       /**
        * 上传文件到minio
        */
       private boolean uploadMinio(String bucketName, String localFilePath, String mimeType, String objetName) {
   
           try {
               UploadObjectArgs uploadObjectArgs = UploadObjectArgs.builder()
                       .bucket(bucketName)
                       .filename(localFilePath)
                       .contentType(mimeType)
                       .object(objetName)
                       .build();
   
               minioClient.uploadObject(uploadObjectArgs);
               log.debug("上传文件成功,bucket:{},objetName:{}", bucketName, objetName);
               return true;
           } catch (IOException | ErrorResponseException | InsufficientDataException |
                   InternalException | InvalidKeyException | InvalidResponseException |
                   NoSuchAlgorithmException | ServerException | XmlParserException e) {
               e.printStackTrace();
               log.error("上传文件失败,bucket:{},objetName:{},错误信息:[]", bucketName, objetName, e);
           }
           return false;
       }
   
       /**
        * 计算文件的md5值
        */
       private String calculateFileMd5(File file) {
           try {
               return DigestUtils.md5Hex(new FileInputStream(file));
           } catch (IOException e) {
               e.printStackTrace();
           }
           return "";
       }
   
       /**
        * 获取默认文件路径
        */
       private String getDefaultFolderPath() {
           return LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd/"));
       }
   
       /**
        * 保存文件信息到数据库
        */
       @Transactional(rollbackFor = {RuntimeException.class, Exception.class}, propagation = Propagation.REQUIRED)
       @Override
       public MediaFiles saveFile(Long companyId, String fileMd5, UploadFileDTO dto, String bucketName, String objetName) {
           // 查询文件是否存在
           MediaFiles mediaFiles = mediaFilesMapper.selectById(fileMd5);
           if (Objects.isNull(mediaFiles)) {
               mediaFiles = CopyUtils.copy(dto, MediaFiles.class);
               assert mediaFiles != null;
               mediaFiles.setId(fileMd5);
               mediaFiles.setCompanyId(companyId);
               mediaFiles.setBucket(bucketName);
               mediaFiles.setCreateDate(LocalDateTime.now());
               mediaFiles.setFileId(fileMd5);
               mediaFiles.setFilePath(objetName);
               mediaFiles.setAuditStatus("002003");
               mediaFiles.setUrl("/" + bucketName + "/" + objetName);
   
               int count = mediaFilesMapper.insert(mediaFiles);
   
               if (count < 0) {
                   log.debug("保存文件信息到数据库失败,bucket:{},objetName:{}", bucketName, objetName);
                   return null;
               }
               return mediaFiles;
           }
           return mediaFiles;
       }
   
       /**
        * 获取本地临时文件的绝对路径
        */
       private String getLocalFilePath(MultipartFile file) {
           try {
               // 创建本地临时文件
               File tempFile = File.createTempFile("minio", ".temp");
               // 将文件信息拷贝到临时文件中
               file.transferTo(tempFile);
               // 返回临时文件的绝对路径
               return tempFile.getAbsolutePath();
           } catch (IOException e) {
               e.printStackTrace();
               log.error("创建本地临时文件失败: []", e);
           }
           return "";
       }
   }
   ```
   
5. 接口测试

   ```http
   ### 上传文件
   POST {{media_host}}/media/upload/course
   Content-Type: multipart/form-data; boundary=WebAppBoundary
   
   --WebAppBoundary
   Content-Disposition: form-data; name="file"; filename="c.jpg"
   
   < c:/Users/elonlo/Pictures/c.jpg
   ```

<span style="color:red">注意：非事务方法调用事务方法会导致事务失效，Spring Boot2.x默认使用CgLib代理，即mapper、service等接口提供的方法才会被代理，添加声明式事务注解才会生效。</span>

### 9.6 上传视频

#### 9.6.1 断点续传

通常视频文件都比较大，所以对于媒资系统上传文件的需求要满足大文件的上传需求。HTTP 协议本身对上传文件大小没有限制，但是客户的网络质量、电脑硬件等参数参差不齐，如果一个大文件块上传完了但是断网了导致没有上传完成，需要客户重新上传，那么用户的体验会非常差，所以对于大文件上传的基本要求是满足断点续传。

断点续传指的是在下载和上传时，将下载或上传任务（一个文件或压缩包）人为的划分为几个部分，每一个部分采用一个线程进行上传或下载，如果碰到网络故障，可以从已经上传或下载的部分开始继续上传或下载未完成的部分，而没有必要从头开始重新上传下载，断点续传可以提交并节省操作时间，提高用户体验性。

#### 9.6.2 视频上传流程

1. 前端对文件进行分块
2. 前端上传分块文件前请求媒资服务检查文件是否存在，如果存在则不再上传
3. 如果分块文件不存在则前端开始上传
4. 前端请求媒资服务上传分块
5. 媒资服务将分块文件上传至 MinIO
6. 前端将分块文件上传完毕后请求媒资服务合并分块
7. 媒资服务判断分块上传完成则请求 MinIO 合并文件
8. 合并完成校验合并后的文件是否完整，如果完整则上传文件，否则删除文件

#### 9.6.2 MinIO 上传分块

1. 上传文件分块相关代码

   ```java
   /**
    * <p>
    * 媒资信息 服务实现类
    * </p>
    *
    * @author elonlo
    * @since 2024-01-02
    */
   @Slf4j
   @Service
   public class MediaFilesServiceImpl extends ServiceImpl<MediaFilesMapper, MediaFiles> implements IMediaFilesService {
   
       private final MediaFilesMapper mediaFilesMapper;
   
       private final MinioClient minioClient;
   
       @Resource
       private IMediaFilesService mediaFilesService;
   
       private final MinioProperties minioProperties;
   
       public MediaFilesServiceImpl(MediaFilesMapper mediaFilesMapper, MinioClient minioClient, MinioProperties minioProperties) {
           this.mediaFilesMapper = mediaFilesMapper;
           this.minioClient = minioClient;
           this.minioProperties = minioProperties;
       }
   
       /**
        * 上传文件到minio
        */
       private boolean uploadMinio(String bucketName, String localFilePath, String mimeType, String objetName) {
   
           try {
               UploadObjectArgs uploadObjectArgs = UploadObjectArgs.builder()
                       .bucket(bucketName)
                       .filename(localFilePath)
                       .contentType(mimeType)
                       .object(objetName)
                       .build();
   
               minioClient.uploadObject(uploadObjectArgs);
               log.debug("上传文件成功,bucket:{},objetName:{}", bucketName, objetName);
               return true;
           } catch (Exception e) {
               e.printStackTrace();
               log.debug("上传文件失败,bucket:{},objetName:{},错误信息:[]", bucketName, objetName, e);
           }
           return false;
       }
   
       /**
        * 获取本地临时文件的绝对路径
        */
       private String getLocalFilePath(MultipartFile file) {
           File tempFile = null;
           try {
               // 创建本地临时文件
               tempFile = File.createTempFile("minio", ".temp");
               // 将文件信息拷贝到临时文件中
               file.transferTo(tempFile);
               // 返回临时文件的绝对路径
               return tempFile.getAbsolutePath();
           } catch (IOException e) {
               e.printStackTrace();
               log.error("创建本地临时文件失败: []", e);
           } finally {
               // 程序退出时删除临时文件
               if (Objects.nonNull(tempFile)) {
                   tempFile.deleteOnExit();
               }
           }
           return "";
       }
   
       /**
        * 检查文件是否存在
        */
       @Override
       public boolean checkFile(String fileMd5) {
           // 先查询数据库是否有文件记录
           MediaFiles mediaFiles = mediaFilesMapper.selectById(fileMd5);
           if (Objects.isNull(mediaFiles)) {
               return false;
           }
   
           // 如果数据库中有文件记录再查询minio中是否有文件
           // 桶
           String bucket = mediaFiles.getBucket();
           // objectName
           String filePath = mediaFiles.getFilePath();
   
           GetObjectArgs getObjectArgs = GetObjectArgs.builder()
                   .bucket(bucket)
                   .object(filePath)
                   .build();
   
           try {
               // 查询minio获取流对象
               GetObjectResponse object = minioClient.getObject(getObjectArgs);
               if (Objects.nonNull(object)) {
                   return true;
               }
           } catch (Exception e) {
               e.printStackTrace();
           }
           // 文件不存在
           return false;
       }
   
       /**
        * 检查分块文件是否存在
        */
       @Override
       public boolean checkChunkFile(String fileMd5, Long chunkIndex) {
           // 获取分块文件所在的目录
           String chunkFolderPath = this.getChunkFolderPath(fileMd5);
   
           // 查询minio中是否有分块文件
           GetObjectArgs getObjectArgs = GetObjectArgs.builder()
                   .bucket(minioProperties.getBucket().getVideofiles())
                   .object(chunkFolderPath + chunkIndex)
                   .build();
   
           try {
               // 查询minio获取流对象
               GetObjectResponse object = minioClient.getObject(getObjectArgs);
               if (Objects.nonNull(object)) {
                   return true;
               }
           } catch (Exception e) {
               e.printStackTrace();
           }
           // 文件不存在
           return false;
       }
   
       /**
        * 上传分块文件到MinIO
        */
       @Override
       public boolean uploadChunkFile(MultipartFile file, String fileMd5, Long chunkIndex) {
           // 获取分块文件上传的目录路径
           String chunkFolderPath = this.getChunkFolderPath(fileMd5);
   
           // 读取本地临时文件地址
           String localFilePath = this.getLocalFilePath(file);
   
           // 获取mimeType
           String mimeType = FileUtil.getMimeType(null);
   
           // 分块文件上传路径
           String chunkFilePath = chunkFolderPath + chunkIndex;
   
           // 上传分块文件到minio
           return this.uploadMinio(minioProperties.getBucket().getVideofiles(), localFilePath, mimeType, chunkFilePath);
       }
   
       /**
        * 获取文件分块目录路径
        */
       private String getChunkFolderPath(String fileMd5) {
           return fileMd5.charAt(0) + "/" + fileMd5.charAt(1) + "/" + fileMd5 + "/" + "chunk" + "/";
       }
   }
   ```

2. 工具类

   ```java
   /**
    * 文件工具类
    *
    * @author elonlo
    * @date 2024/1/3 11:14
    */
   public class FileUtil {
   
       /**
        * 获取文件类型
        */
       public static String getMimeType(String extensionName) {
           if (StringUtils.isEmpty(extensionName)) {
               extensionName = "";
           }
           //根据扩展名取出mimeType
           ContentInfo extensionMatch = ContentInfoUtil.findExtensionMatch(extensionName);
           //通用mimeType字节流
           String mimeType = MediaType.APPLICATION_OCTET_STREAM_VALUE;
           if (Objects.nonNull(extensionMatch)) {
               mimeType = extensionMatch.getMimeType();
           }
           return mimeType;
       }
   }
   ```

3. 项目配置

   ```yaml
   server:
     servlet:
       context-path: /media
     port: 63050
   
   spring:
     servlet:
       multipart:
         max-file-size: 50MB
         max-request-size: 50MB
   ```

#### 9.6.3 MinIO 合并分块

```java
@ApiOperation(value = "合并分块文件")
@PostMapping("/upload/mergeChunkFile")
public ResultResponse<Boolean> mergeChunkFile(@RequestParam("fileMd5") String fileMd5,
                                              @RequestParam("filename") String filename,
                                              @RequestParam("chunkTotal") Integer chunkTotal) {
    Map<String, Object> map = mediaFilesService.mergeChunkFile(1433223113L, fileMd5, chunkTotal, filename);
    boolean flag = (boolean) map.get("flag");
    if (flag) {
        return ResultResponse.success(Boolean.TRUE);
    }
    return ResultResponse.error(String.valueOf(map.get("message")), Boolean.FALSE);
}
```

```java
/**
 * 保存文件信息到数据库
 */
@Transactional(rollbackFor = {RuntimeException.class, Exception.class}, propagation = Propagation.REQUIRED)
@Override
public MediaFiles saveFile(Long companyId, String fileMd5, UploadFileDTO dto, String bucketName, String objetName) {
    // 查询文件是否存在
    MediaFiles mediaFiles = mediaFilesMapper.selectById(fileMd5);
    if (Objects.isNull(mediaFiles)) {
        mediaFiles = CopyUtils.copy(dto, MediaFiles.class);
        assert mediaFiles != null;
        mediaFiles.setId(fileMd5);
        mediaFiles.setCompanyId(companyId);
        mediaFiles.setBucket(bucketName);
        mediaFiles.setCreateDate(LocalDateTime.now());
        mediaFiles.setFileId(fileMd5);
        mediaFiles.setFilePath(objetName);
        mediaFiles.setAuditStatus("002003");
        mediaFiles.setUrl("/" + bucketName + "/" + objetName);

        int count = mediaFilesMapper.insert(mediaFiles);

        if (count < 0) {
            log.debug("保存文件信息到数据库失败,bucket:{},objetName:{}", bucketName, objetName);
            return null;
        }
        return mediaFiles;
    }
    return mediaFiles;
}

/**
 * 合并分块文件到MinIO
 */
@Override
public Map<String, Object> mergeChunkFile(Long companyId, String fileMd5, Integer chunkTotal, String filename) {
    Map<String, Object> resultMap = new HashMap<>(4);
    resultMap.put("flag", false);

    // 获取分块文件路径
    String chunkFolderPath = this.getChunkFolderPath(fileMd5);

    final String bucketName = minioProperties.getBucket().getVideofiles();

    // 获取分块源文件
    List<ComposeSource> sources = Stream.iterate(0, i -> ++i).
            limit(chunkTotal).
            map(i -> ComposeSource.builder().
                    bucket(bucketName)
                    .object(chunkFolderPath + i)
                    .build())
            .collect(Collectors.toList());

    // 获取合并后的文件路径
    String extensionName = filename.substring(filename.lastIndexOf("."));
    String mergeFilePath = this.getMergeFilePathByFileMd5(fileMd5, extensionName);

    // 指定合并后的objectName等信息
    ComposeObjectArgs composeObjectArgs = ComposeObjectArgs.builder()
            .bucket(bucketName)
            .object(mergeFilePath)
            .sources(sources)
            .build();

    // 1、合并分块
    try {
        minioClient.composeObject(composeObjectArgs);
    } catch (Exception e) {
        e.printStackTrace();
        log.error("合并分块文件失败,bucket: {},object: {},错误信息: []", bucketName, mergeFilePath, e);
        resultMap.put("message", "合并分块文件失败");
        return resultMap;
    }

    // 构建文件信息dto
    UploadFileDTO dto = UploadFileDTO.builder()
            .filename(filename)
            .fileType("001002")
            .tags("视频文件")
            .build();

    // 2、校验源文件MD5和合并后的文件MD5是否一致,一致才表示文件上传成功
    // 下载合并后的文件
    File mergeFile = this.downloadFileFromMinIO(bucketName, mergeFilePath);
    try (FileInputStream fileInputStream = new FileInputStream(mergeFile)) {
        // 计算合并后文件MD5
        String mergeMd5 = DigestUtils.md5Hex(fileInputStream);
        if (!Objects.equals(fileMd5, mergeMd5)) {
            log.warn("源文件与合并后文件md5不一致,源文件 :{}, 合并文件: {}", fileMd5, mergeMd5);
            resultMap.put("message", "文件校验失败");
            return resultMap;
        }
        dto.setFileSize(mergeFile.length());
    } catch (Exception e) {
        resultMap.put("message", "文件校验失败");
        return resultMap;
    }

    // 3、保存文件信息
    MediaFiles mediaFiles = mediaFilesService.saveFile(companyId, fileMd5, dto, bucketName, mergeFilePath);
    if (Objects.isNull(mediaFiles)) {
        resultMap.put("message", "保存文件信息失败");
        return resultMap;
    }

    // 4、清理分块文件
    this.clearChunkFile(chunkFolderPath, chunkTotal);

    // 文件合并成功
    resultMap.put("flag", true);
    return resultMap;
}

/**
 * 清理分块文件
 */
private void clearChunkFile(String ChunkFolderPath, Integer chunkTotal) {
    // 构建被清理的分块文件集合
    List<DeleteObject> deleteObjectList = Stream.iterate(0, i -> ++i)
            .limit(chunkTotal)
            .map(i -> new DeleteObject(ChunkFolderPath + i))
            .collect(Collectors.toList());

    RemoveObjectsArgs removeObjectsArgs = RemoveObjectsArgs.builder()
            .bucket(minioProperties.getBucket().getVideofiles())
            .objects(deleteObjectList)
            .build();

    Iterable<Result<DeleteError>> removeObjects = minioClient.removeObjects(removeObjectsArgs);
    // 真正删除需要重新遍历
    removeObjects.forEach(item -> {
        try {
            item.get();
        } catch (Exception e) {
            e.printStackTrace();
        }
    });
}

/**
 * 从minio下载合并后的文件并拷贝到临时文件
 */
private File downloadFileFromMinIO(String bucket, String objectName) {
    File tempFile = null;
    FileOutputStream outputStream = null;
    InputStream inputStream = null;

    GetObjectArgs getObjectArgs = GetObjectArgs.builder()
            .bucket(bucket)
            .object(objectName)
            .build();

    try {
        // 从minio获取文件流
        inputStream = minioClient.getObject(getObjectArgs);

        // 创建临时文件
        tempFile = File.createTempFile("minio", ".merge");
        outputStream = new FileOutputStream(tempFile);
        // 流拷贝
        IOUtils.copy(inputStream, outputStream);
        return tempFile;
    } catch (Exception e) {
        e.printStackTrace();
    } finally {
        if (inputStream != null) {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (outputStream != null) {
            try {
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    return tempFile;
}

/**
 * 获取合并后的文件目录路径
 */
private String getMergeFilePathByFileMd5(String fileMd5, String extensionName) {
    return fileMd5.charAt(0) + "/" + fileMd5.charAt(1) + "/" + fileMd5 + "/" + fileMd5 + extensionName;
}

/**
 * 获取文件分块目录路径
 */
private String getChunkFolderPath(String fileMd5) {
    return fileMd5.charAt(0) + "/" + fileMd5.charAt(1) + "/" + fileMd5 + "/" + "chunk" + "/";
}
```

### 9.7 xxl-job

**任务调度**

任务调度是指系统为了完成特定业务，基于给定时间点，给定时间间隔或者给定执行次数自动执行任务。

**分布式任务调度**

通常任务调用的程序是集成在应用中的，比如：优惠券服务中包括了定时发放优惠券的调度程序，计算服务中包括了定期生成报表的任务调度程序，由于采用分布式架构，一个服务往往会部署多个冗余实例来运行我们的业务，在这种分布式系统环境下运行任务调度，就称之为分布式任务调度。

#### 9.7.1 简介

XXL-JOB 是一个分布式任务调度平台，其核心设计目标是开发迅速、学习简单、轻量级、易扩展。现已开放源代码并接入多家公司线上产品线，开箱即用。

官网：https://www.xuxueli.com/xxl-job/

文档：https://www.xuxueli.com/xxl-job/#%E3%80%8A%E5%88%86%E5%B8%83%E5%BC%8F%E4%BB%BB%E5%8A%A1%E8%B0%83%E5%BA%A6%E5%B9%B3%E5%8F%B0XXL-JOB%E3%80%8B

XXL-JOB 主要有调度中心、执行器、任务。

- 调度中心

  负责管理调度信息，按照调度配置发出调度请求，自身不承担业务代码；主要职责为执行器管理、任务管理、监控运维、日志管理等。

- 任务执行器

  负责接收调度请求并执行任务逻辑；主要职责是注册服务、任务执行服务（接收到任务后会放入线程池中的任务队列）、执行结果上报、日志服务等。

- 任务

  负责执行具体的业务处理。

#### 9.7.2 搭建 xxl-job

1. 下载 xxl-job 的 sql 脚本

   地址：https://gitee.com/xuxueli0323/xxl-job/blob/2.3.1/doc/db/tables_xxl_job.sql

2. 在数据库中创建 xxl-job 相关库表

3. 创建 xxl-job 容器

   ```yaml
   version: "3"
   services:
     db:
       image: mysql:8.0.32
       container_name: mysql
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
         - app
   
     xxl:
       image: xuxueli/xxl-job-admin:2.3.1
       container_name: xxl-job
       restart: unless-stopped
       ports:
         - "28880:8800"   
       environment:
         PARAMS: '
         --server.port=8800
         --server.servlet.context-path=/xxl-job-admin
         --spring.datasource.url=jdbc:mysql://ip:port/xxl_job?useUnicode=true&characterEncoding=UTF-8&autoReconnect=true&serverTimezone=Asia/Shanghai
         --spring.datasource.username=root
         --spring.datasource.password=your_pass
         --xxl.job.accessToken=your_token'
       volumes:
         - $PWD/xxl-job/logs:/data/applogs
       depends_on:
         - db
       networks:
         - app
         
     frp:
       image: snowdreamtech/frps:0.51.0
       container_name: frps
       restart: unless-stopped
       volumes:
         - $PWD/frps/frps.ini:/etc/frp/frps.ini
       environment:
         TZ: Asia/Shanghai
       network_mode: host
       
   networks:
     app:
       driver: bridge
   ```

   ```ini
   [common]
   # 监听端口
   bind_port = 27030
   # 面板端口
   dashboard_port = 27050
   # 登录面板账号设置
   dashboard_user = xxx
   dashboard_pwd = xxx
   # 设置http及https协议下代理端口（非重要）
   vhost_http_port = 27032
   
   allow_ports = 9999-27050
   
   # 身份验证
   token = 9LgPn24Tag9NYh18ZXY
   ```

   ```shell
   docker-compose up -d
   ```

4. 访问 ip:28880/xxl-job-admin

   默认用户名和密码是：admin/123456

5. 添加依赖信息（media-service）

   ```xml
   <!-- 整合xxl-job -->
   <dependency>
       <groupId>com.xuxueli</groupId>
       <artifactId>xxl-job-core</artifactId>
   </dependency>
   ```

6. Nacos 配置 xxl-job

   ```yaml
   xxl:
     job:
       admin: 
         addresses: http://ip:28880/xxl-job-admin
       executor:
         # 执行器别名
         appname: media-process-service
         address: 
         ip: 
         # 执行器启动端口,默认为9999,启动多个执行器时端口不能重复
         port: 8888
         logpath: /data/applogs/xxl-job/jobhandler
         logretentiondays: 30
       accessToken: your_token
   ```

7. 创建 xxl-job 属性类和配置类

   ```java
   /**
    * Xxl-Job属性类
    *
    * @author elonlo
    * @date 2024/1/7 12:07
    */
   @Data
   @Component
   @ConfigurationProperties(prefix = XxlJobProperties.XXL_JOB_PREFIX)
   public class XxlJobProperties {
   
       /**
        * xxl-job属性前缀
        */
       public static final String XXL_JOB_PREFIX = "xxl.job";
   
       /**
        * 调度中心配置
        */
       private Admin admin;
   
       /**
        * 执行器配置
        */
       private Executor executor;
   
       @Data
       public static class Admin {
   
           /**
            * 调度中心部署根地址 [选填]：如调度中心集群部署存在多个地址则用逗号分隔。执行器将会使用该地址进行"执行器心跳注册"和"任务结果回调";为空则关闭自动注册
            */
           private String address;
       }
   
       @Data
       public static class Executor {
   
           /**
            * 执行器AppName [选填]：执行器心跳注册分组依据；为空则关闭自动注册
            */
           private String appName;
   
           /**
            * 执行器注册 [选填]：优先使用该配置作为注册地址，为空时使用内嵌服务 ”IP:PORT“ 作为注册地址。从而更灵活的支持容器类型执行器动态IP和动态映射端口问题
            */
           private String address;
   
           /**
            * 执行器IP [选填]：默认为空表示自动获取IP，多网卡时可手动设置指定IP，该IP不会绑定Host仅作为通讯实用；地址信息用于 "执行器注册" 和 "调度中心请求并触发任务"
            */
           private String ip;
   
           /**
            * 执行器端口号 [选填]：小于等于0则自动获取；默认端口为9999，单机部署多个执行器时，注意要配置不同执行器端口
            */
           private int port;
   
           /**
            * 执行器运行日志文件存储磁盘路径 [选填] ：需要对该路径拥有读写权限；为空则使用默认路径
            */
           private String logPath;
   
           /**
            * 执行器日志文件保存天数 [选填] ： 过期日志自动清理, 限制值大于等于3时生效; 否则, 如-1, 关闭自动清理功能
            */
           private int logRetentionDays;
       }
   
       /**
        * 执行器通讯TOKEN [选填]：非空时启用
        */
       private String accessToken;
   }
   ```

   ```java
   /**
    * Xxl-Job配置类
    *
    * @author elonlo
    * @date 2024/1/7 12:52
    */
   @Configuration
   public class XxlJobConfig {
   
       public static final Logger logger = LoggerFactory.getLogger(XxlJobConfig.class);
   
       @Resource
       private XxlJobProperties xxlJobProperties;
   
       @Bean
       public XxlJobSpringExecutor xxlJobExecutor() {
           logger.info(">>>>>>>>>>> xxl-job config init.");
           XxlJobSpringExecutor xxlJobSpringExecutor = new XxlJobSpringExecutor();
           xxlJobSpringExecutor.setAdminAddresses(xxlJobProperties.getAdmin().getAddress());
           xxlJobSpringExecutor.setAppname(xxlJobProperties.getExecutor().getAppName());
           xxlJobSpringExecutor.setAddress(xxlJobProperties.getExecutor().getAddress());
           xxlJobSpringExecutor.setIp(xxlJobProperties.getExecutor().getIp());
           xxlJobSpringExecutor.setPort(xxlJobProperties.getExecutor().getPort());
           xxlJobSpringExecutor.setAccessToken(xxlJobProperties.getAccessToken());
           xxlJobSpringExecutor.setLogPath(xxlJobProperties.getExecutor().getLogPath());
           xxlJobSpringExecutor.setLogRetentionDays(xxlJobProperties.getExecutor().getLogRetentionDays());
           return xxlJobSpringExecutor;
       }
   }
   ```

8. 配置 frpc

   由于我的调度中心程序部署在腾讯云服务器上，而执行器是在本地，也就是说调度中心在公网，执行器所在程序在内网；为了让调度中心能够访问执行器，需要部署内网穿透服务，上面我们在部署调度中心容器时部署了 frps 服务端程序，我们还需要在本地配置 frpc 服务端，以实现内网穿透，下面是 frpc.ini 的一些示例配置。

   ```ini
   [common]
   # server_addr为FRPS服务器IP地址(即公网IP)
   server_addr = xxx
   # server_port为服务端监听端口,与frps.ini中的bind_port保存一致
   server_port = 27030
   # 身份验证,与frps.ini中保存一致
   token = 9LgPn24Tag9NYh18ZXY
   
   # frp客户端允许代理的端口范围
   allow_ports = 9999-27050
   
   [http_proxy]
   # 协议类型
   type = http
   # 内网服务器端口
   local_port = 8888
   custom_domains = xxx # 可以设置为公网IP
   ```

9. 配置执行器

   使用内网穿透让调度中心调度执行器，需要将执行器地址配置为 公网IP + frps 代理端口（如公网IP:27032），同时 frpc.ini 文件中的内网端口要和执行器配置的端口一致。

   在本地 frpc 目录下，进入到命令行，执行以下命令启动客户端 frp
   
   ```shell
   frpc.exe -c frpc.ini
   ```
   

#### 9.7.3 分片广播

指每个执行器收到调度请求同时执行。

分片广播使用场景如下：

- 分片任务：10个执行器的集群来处理10w条数据，每台机器只需要处理1w条数据，耗时降低10倍
- 广播任务场景：广播执行器同时运行 shell 脚本、广播集群节点进行缓存更新等。

#### 9.7.4 作业分片

每个执行器收到广播任务有两个参数：分片序号和分片总数。每个执行器从数据表取得任务时可以让任务id对分片总数取余，如果等于分片序号则让对应的执行器执行。

上面两个执行器实例，所以分片总数是2，序号为0、1，从任务1开始，如下：

1 % 2 = 1	执行器2执行

2 % 2 = 0	执行器1执行

3 % 2 = 1	执行器2执行

#### 9.7.5 任务不重复执行

xxl-job 中使用**调度过期策略**和**阻塞处理策略**来解决任务不重复执行的问题。

**调度过期策略**

调度策略就是错过调度时间的补偿处理策略。

- 忽略：调度过期后，忽略过期的任务，并从当前时间开始重新计算下次触发时间；
- 立即执行一次：调度过期后，立即执行一次，并从当前时间开始重新计算下次触发时间；

这里我们选择`忽略`，如果选择`立即执行一次`可能重复执行相同的任务。

**阻塞处理策略**

阻塞策略就是指当前执行器正在执行任务还没有结束时调度中心进行任务调度，此时该如何处理？

- 单机串行（默认）：调度请求进入单机执行器后，调度请求进入 FIFO 队列并以串行方式运行
- 丢弃后调度：调度请求进入单机执行器后，发现执行器存在运行的调度任务，本次请求将会被丢弃并标记为失败；
- 覆盖之前调度：调度请求进入单机执行器后，发现执行器存在运行的调度任务，将会终止运行中的调度任务并清空队列，然后运行本地调度任务。

这里我们选择`丢弃后调度`，如果选择`覆盖之前调度`可能重复执行相同的任务。

#### 9.7.6 任务幂等性

幂等性是指一次和多次请求某一个资源对于资源本身应该具有相同的结果。

幂等性是为了解决重复提交问题，比如：恶意刷单、重复支付等。

解决幂等性常用的方案：

- 数据库约束，比如主键和唯一索引
- 乐观锁，常用于数据库，更新数据时根据乐观锁状态去更新
- 唯一序列号，操作传递一个唯一序列号，操作时判断与该序列号相等才执行

### 9.8 视频处理

#### 9.8.1 什么是视频编码

所谓视频[编码方式](https://baike.baidu.com/item/编码方式/6451499?fromModule=lemma_inlink)就是指通过[压缩技术](https://baike.baidu.com/item/压缩技术/1444262?fromModule=lemma_inlink)，将原始[视频格式](https://baike.baidu.com/item/视频格式/123472?fromModule=lemma_inlink)的文件转换成另一种视频格式文件的方式。[视频流](https://baike.baidu.com/item/视频流/9392168?fromModule=lemma_inlink)传输中最为重要的编解码标准有[国际电联](https://baike.baidu.com/item/国际电联/6302433?fromModule=lemma_inlink)的[H.261](https://baike.baidu.com/item/H.261/7591455?fromModule=lemma_inlink)、H.263、[H.264](https://baike.baidu.com/item/H.264/1022230?fromModule=lemma_inlink)，运动静止图像专家组的[M-JPEG](https://baike.baidu.com/item/M-JPEG/4602166?fromModule=lemma_inlink)和[国际标准化组织](https://baike.baidu.com/item/国际标准化组织/779832?fromModule=lemma_inlink)[运动图像](https://baike.baidu.com/item/运动图像/7181391?fromModule=lemma_inlink)专家组的[MPEG](https://baike.baidu.com/item/MPEG/213546?fromModule=lemma_inlink)系列标准。

**文件格式**

是指 `.mp4`、`.avi`、`.rmvb`等这些不同扩展名的视频文件的文件格式，视频文件的内容主要包括视频和音频，其文件格式是按照一定的编码格式去编码。

**音视频编码格式**

音视频编码格式很多，主要分为以下几类：

- MPEG 系列

  由 ISO 下属的 MPEG 开发，视频编码主要是 Mpeg1、 Mpeg2、 Mpeg4、 Mpeg4 AVC； 音频编码主要是 MPEG Audio Layer 1/2、MPEG Audio Layer 3、MPEG-2 AAC、MPEG-4 AAC等

- H.26.X 系列

  由 ITU 主导，侧重网络传输，包括H.261、H.262、H.263、H.263+、H.263++、H.264

目前最常用的编码是**视频H.264**，**音频ACC**

#### 9.8.2 FFmpeg 的基本使用

FFmpeg 被许多开源项目采用，比如 QQ影音、暴风影音、VLC等。

下载地址：https://www.ffmpeg.org/download.html#build-windows

#### 9.8.3 视频处理流程

1. 任务调度中心广播作业分片
2. 执行器收到广播作业分片，从数据库中取出待处理任务，读取未处理及处理失败的任务
3. 执行器更新任务为处理中，根据任务内容从 MinIO 下载要处理的文件
4. 执行器启动多线程去处理任务
5. 任务处理完成，上传处理后的视频到 MinIO
6. 更新任务处理结果，如果视频处理完成，除了更新任务处理结果外还要将文件的访问地址更新至任务处理表及文件表中，最后将任务完成记录写入历史表

#### 9.8.4 待处理任务

1. 添加待处理任务

   ```java
   @Slf4j
   @Service
   public class MediaFilesServiceImpl extends ServiceImpl<MediaFilesMapper, MediaFiles> implements IMediaFilesService {
   
       private final MediaFilesMapper mediaFilesMapper;
   
       private final MinioClient minioClient;
   
       @Resource
       private IMediaFilesService mediaFilesService;
   
       private final MinioProperties minioProperties;
   
       private final MediaProcessMapper mediaProcessMapper;
   
       public MediaFilesServiceImpl(MediaFilesMapper mediaFilesMapper, MinioClient minioClient, MinioProperties minioProperties, MediaProcessMapper mediaProcessMapper) {
           this.mediaFilesMapper = mediaFilesMapper;
           this.minioClient = minioClient;
           this.minioProperties = minioProperties;
           this.mediaProcessMapper = mediaProcessMapper;
       }
   
       /**
        * 保存文件信息到数据库
        */
       @Transactional(rollbackFor = {RuntimeException.class, Exception.class}, propagation = Propagation.REQUIRED)
       @Override
       public MediaFiles saveFile(Long companyId, String fileMd5, UploadFileDTO dto, String bucketName, String objetName) {
           // 查询文件是否存在
           MediaFiles mediaFiles = mediaFilesMapper.selectById(fileMd5);
           if (Objects.isNull(mediaFiles)) {
               mediaFiles = CopyUtils.copy(dto, MediaFiles.class);
               assert mediaFiles != null;
               mediaFiles.setId(fileMd5);
               mediaFiles.setCompanyId(companyId);
               mediaFiles.setBucket(bucketName);
               mediaFiles.setCreateDate(LocalDateTime.now());
               mediaFiles.setFileId(fileMd5);
               mediaFiles.setFilePath(objetName);
               mediaFiles.setAuditStatus("002003");
               mediaFiles.setUrl("/" + bucketName + "/" + objetName);
   
               int count = mediaFilesMapper.insert(mediaFiles);
   
               if (count < 0) {
                   log.debug("保存文件信息到数据库失败,bucket:{},objetName:{}", bucketName, objetName);
                   return null;
               }
               // 记录待处理任务
               this.saveMediaProcess(mediaFiles);
               return mediaFiles;
           }
           return mediaFiles;
       }
   
       /**
        * 添加待处理任务
        */
       private void saveMediaProcess(MediaFiles mediaFiles) {
           // 1、获取文件后缀名
           String filename = mediaFiles.getFilename();
           String extension = filename.substring(filename.lastIndexOf("."));
   
           // 2、通过后缀名获取mimeType
           String mimeType = FileUtil.getMimeType(extension);
   
           // 3、判断mimeType是否在允许上传的视频格式内,这里暂时不判断
   
           // 4、保存任务到数据库
           MediaProcess mediaProcess = CopyUtils.copy(mediaFiles, MediaProcess.class);
           assert mediaProcess != null;
           // 待处理任务
           mediaProcess.setStatus("1");
           mediaProcess.setCreateDate(LocalDateTime.now());
           mediaProcess.setFailCount(0);
           mediaProcess.setUrl(null);
           mediaProcessMapper.insert(mediaProcess);
       }
   }
   ```

2. 查询待处理任务

   ```java
   public interface IMediaProcessService extends IService<MediaProcess> {
   
       /**
        * 查询待处理任务
        *
        * @param ShardIndex 分片索引
        * @param ShardTotal 分片总数
        * @param count      查询总数
        * @return {@link List}<{@link MediaProcess}>
        */
       List<MediaProcess> getMediaProcessList(Integer ShardIndex, Integer ShardTotal, Integer count);
   }
   ```

   ```java
   @Slf4j
   @Service
   public class MediaProcessServiceImpl extends ServiceImpl<MediaProcessMapper, MediaProcess> implements IMediaProcessService {
   
       private final MediaProcessMapper mediaProcessMapper;
   
       public MediaProcessServiceImpl(MediaProcessMapper mediaProcessMapper) {
           this.mediaProcessMapper = mediaProcessMapper;
       }
   
       /**
        * 查询待处理任务
        */
       @Override
       public List<MediaProcess> getMediaProcess(Integer ShardIndex, Integer ShardTotal, Integer count) {
           return mediaProcessMapper.selectMediaProcess(ShardIndex, ShardTotal, count);
       }
   }
   ```

   ```java
   public interface MediaProcessMapper extends BaseMapper<MediaProcess> {
   
       /**
        * 查询待处理任务
        *
        * @param shardIndex 分片索引
        * @param shardTotal 分片总数
        * @param count      查询总数
        * @return {@link List}<{@link MediaProcess}>
        */
       @Select("SELECT * FROM media_process WHERE id % #{shardTotal} = #{shardIndex} AND (`status` = '1' OR `status` = '3') AND fail_count < 3 LIMIT #{count}")
       List<MediaProcess> selectMediaProcess(@Param("shardIndex") Integer shardIndex,@Param("shardTotal") Integer shardTotal,@Param("count") Integer count);
   }
   ```

#### 9.8.5 开启任务

使用乐观锁来实现分布式锁功能

1. `media_process` 表的 `status` 字段增加一个状态`4-处理中`

2. 编写实现乐观锁的 `sql` 语句

   ```sql
   UPDATE media_process t SET t.`status` = '4' WHERE (t.`status` = '1' OR t.`status` = '3') AND t.fail_count < 3 AND t.id = ?
   ```

3. 添加开启任务实现逻辑

   ```java
   public interface IMediaProcessService extends IService<MediaProcess> {
   
       /**
        * 开启任务
        *
        * @param id        主键id
        * @return boolean  true-开启成功,false-开启失败
        */
       boolean startTask(Long id);
   }
   ```

   ```java
   @Slf4j
   @Service
   public class MediaProcessServiceImpl extends ServiceImpl<MediaProcessMapper, MediaProcess> implements IMediaProcessService {
   
       private final MediaProcessMapper mediaProcessMapper;
   
       public MediaProcessServiceImpl(MediaProcessMapper mediaProcessMapper) {
           this.mediaProcessMapper = mediaProcessMapper;
       }
   
       /**
        * 开启任务
        */
       @Override
       public boolean startTask(Long id) {
           return mediaProcessMapper.startTask(id) > 0;
       }
   }
   ```

   ```java
   public interface MediaProcessMapper extends BaseMapper<MediaProcess> {
   
       /**
        * 开启任务
        *
        * @param id    主键id
        * @return int  更新返回的记录数
        */
       @Update("UPDATE media_process t SET t.`status` = '4' WHERE (t.`status` = '1' OR t.`status` = '3') AND t.fail_count < 3 AND t.id = #{id}")
       int startTask(@Param("id") Long id);
   }
   ```

#### 9.8.6 任务处理

1. 添加视频处理相关工具类（xuecheng-plus-base模块）

   ```java
   /**
    * 此文件作为视频文件处理父类，提供：
    * 1、查看视频时长
    * 2、校验两个视频的时长是否相等
    */
   public class VideoUtil {
   
       /**
        * ffmpeg安装路径
        */
       private final String ffmpegPath;
   
       public VideoUtil(String ffmpegPath) {
           this.ffmpegPath = ffmpegPath;
       }
   
       /**
        * 检查视频时间是否一致
        */
       public Boolean checkVideoTime(String source, String target) {
           // 获取源视频时间
           String sourceTime = this.getVideoTime(source);
           if (StringUtils.isEmpty(sourceTime)) {
               return Boolean.FALSE;
           }
   
           // 取出时分秒
           sourceTime = sourceTime.substring(0, sourceTime.lastIndexOf("."));
           // 获取目标视频时间
           String targetTime = this.getVideoTime(target);
           if (StringUtils.isEmpty(targetTime)) {
               return Boolean.FALSE;
           }
           // 取出时分秒
           targetTime = targetTime.substring(0, targetTime.lastIndexOf("."));
           return Objects.equals(sourceTime, targetTime);
       }
   
       /**
        * 获取视频时间(时：分：秒：毫秒)
        */
       private String getVideoTime(String videoPath) {
           /*
           ffmpeg -i  lucene.mp4
            */
           List<String> commend = new ArrayList<>();
           commend.add(ffmpegPath);
           commend.add("-i");
           commend.add(videoPath);
           try {
               ProcessBuilder builder = new ProcessBuilder();
               builder.command(commend);
               // 将标准输入流和错误输入流合并，通过标准输入流程读取信息
               builder.redirectErrorStream(true);
               Process p = builder.start();
               String outString = waitFor(p);
               int start = outString.trim().indexOf("Duration: ");
               if (start < 0) {
                   return null;
               }
               int end = outString.trim().indexOf(", start:");
               if (end < 0) {
                   return null;
               }
               String time = outString.substring(start + 10, end);
               return time.trim();
           } catch (Exception ex) {
               ex.printStackTrace();
           }
           return null;
       }
   
       /**
        * 打印视频处理结果
        */
       public String waitFor(Process p) {
           InputStream in = null;
           InputStream error = null;
           String result = "error";
           int exitValue = -1;
           StringBuffer outputString = new StringBuffer();
           try {
               in = p.getInputStream();
               error = p.getErrorStream();
               boolean finished = false;
               // 每次休眠1秒，最长执行时间10分种
               int maxRetry = 600;
               int retry = 0;
               while (!finished) {
                   if (retry > maxRetry) {
                       return "error";
                   }
                   try {
                       while (in.available() > 0) {
                           Character c = (char) in.read();
                           outputString.append(c);
                           System.out.print(c);
                       }
                       while (error.available() > 0) {
                           Character c = (char) in.read();
                           outputString.append(c);
                           System.out.print(c);
                       }
                       // 进程未结束时调用exitValue将抛出异常
                       exitValue = p.exitValue();
                       finished = true;
                   } catch (IllegalThreadStateException e) {
                       //休眠1秒
                       TimeUnit.SECONDS.sleep(1);
                       retry++;
                   }
               }
   
           } catch (Exception e) {
               e.printStackTrace();
           } finally {
               if (in != null) {
                   try {
                       in.close();
                   } catch (IOException e) {
                       System.out.println(e.getMessage());
                   }
               }
           }
           return outputString.toString();
       }
   }
   ```

   ```java
   /**
    * mp4视频工具类
    */
   public class Mp4VideoUtil extends VideoUtil {
   
       /**
        * 本地ffmpeg程序路径
        */
       private final String ffmpegPath;
   
       /**
        * 源视频路径
        */
       private final String videoPath;
   
       /**
        * 转码后的mp4视频名称
        */
       private String mp4Name;
   
       /**
        * 转码后的mp4视频路径
        */
       private final String mp4folderPath;
   
   
       public Mp4VideoUtil(String ffmpegPath, String videoPath, String mp4Name, String mp4folderPath) {
           super(ffmpegPath);
           this.ffmpegPath = ffmpegPath;
           this.videoPath = videoPath;
           this.mp4Name = mp4Name;
           this.mp4folderPath = mp4folderPath;
       }
   
       /**
        * 清除已生成的mp4
        */
       private void clearMp4(String mp4Path) {
           File mp4File = new File(mp4Path);
           if (mp4File.exists() && mp4File.isFile()) {
               mp4File.delete();
           }
       }
   
       /**
        * 视频编码,生成mp4文件,成功返回success,失败返回控制台日志
        */
       public String generateMp4() {
           //清除已生成的mp4
           clearMp4(mp4folderPath);
           /*
           ffmpeg.exe -i  lucene.avi -c:v libx264 -s 1280x720 -pix_fmt yuv420p -b:a 63k -b:v 753k -r 18 .\lucene.mp4
            */
           List<String> commend = new ArrayList<>();
           commend.add(ffmpegPath);
           commend.add("-i");
           commend.add(videoPath);
           commend.add("-c:v");
           commend.add("libx264");
           // 覆盖输出文件
           commend.add("-y");
           commend.add("-s");
           commend.add("1280x720");
           commend.add("-pix_fmt");
           commend.add("yuv420p");
           commend.add("-b:a");
           commend.add("63k");
           commend.add("-b:v");
           commend.add("753k");
           commend.add("-r");
           commend.add("18");
           commend.add(mp4folderPath);
           String outString = null;
           try {
               ProcessBuilder builder = new ProcessBuilder();
               builder.command(commend);
               // 将标准输入流和错误输入流合并,通过标准输入流程读取信息
               builder.redirectErrorStream(true);
               Process p = builder.start();
               outString = waitFor(p);
           } catch (Exception ex) {
               ex.printStackTrace();
           }
           Boolean checkVideoTime = this.checkVideoTime(videoPath, mp4folderPath);
           if (!checkVideoTime) {
               return outString;
           } else {
               return "success";
           }
       }
   }
   ```

2. 添加ffmpeg属性类和 `Nacos` 配置

   ```java
   /**
    * ffmpeg属性类
    *
    * @author elonlo
    * @date 2024/1/18 21:20
    */
   @Data
   @Component
   @ConfigurationProperties(prefix = FfmpegProperties.FFM_PREFIX)
   public class FfmpegProperties {
   
       public static final String FFM_PREFIX = "video";
   
       /**
        * ffmpeg路径
        */
       private String ffmpegPath;
   }
   ```

   ```yaml
   # 配置本地ffmpeg程序路径
   video:
    ffmpeg_path: D:/Software/Basic/Ffmpeg/ffmpeg.exe
   ```

3. 更新媒资任务处理状态

   ```java
   public interface IMediaProcessService extends IService<MediaProcess> {
   
       /**
        * 更新媒资任务处理状态
        *
        * @param taskId   任务id
        * @param status   任务状态
        * @param fileId   文件id
        * @param url      文件地址
        * @param errorMsg 错误消息
        */
       void updateMediaProcessStatus(Long taskId, String status, String fileId, String url, String errorMsg);
   }
   ```

   ```java
   @Slf4j
   @Service
   public class MediaProcessServiceImpl extends ServiceImpl<MediaProcessMapper, MediaProcess> implements IMediaProcessService {
   
       private final MediaProcessMapper mediaProcessMapper;
   
       private final MediaFilesMapper mediaFilesMapper;
   
       private final MediaProcessHistoryMapper mediaProcessHistoryMapper;
   
       public MediaProcessServiceImpl(MediaProcessMapper mediaProcessMapper, MediaFilesMapper mediaFilesMapper, MediaProcessHistoryMapper mediaProcessHistoryMapper) {
           this.mediaProcessMapper = mediaProcessMapper;
           this.mediaFilesMapper = mediaFilesMapper;
           this.mediaProcessHistoryMapper = mediaProcessHistoryMapper;
       }
   
       /**
        * 更新媒资任务处理状态
        */
       @Transactional(rollbackFor = {RuntimeException.class, Exception.class}, propagation = Propagation.REQUIRED)
       @Override
       public void updateMediaProcessStatus(Long taskId, String status, String fileId, String url, String errorMsg) {
           // 1.查询媒资处理任务
           MediaProcess mediaProcess = mediaProcessMapper.selectById(taskId);
   
           if (Objects.isNull(mediaProcess)) {
               return;
           }
   
           // 2.任务执行失败
           if (Objects.equals("3", status)) {
               mediaProcess.setStatus("3");
               // 失败次数加一
               mediaProcess.setFailCount(mediaProcess.getFailCount() + 1);
               mediaProcess.setErrormsg(errorMsg);
               // 写入失败状态到媒资处理表
               mediaProcessMapper.updateById(mediaProcess);
           }
   
           // 3.任务执行成功
           // 查询文件表记录,更新文件表url
           MediaFiles mediaFiles = mediaFilesMapper.selectById(fileId);
           mediaFiles.setUrl(url);
           mediaFilesMapper.updateById(mediaFiles);
   
           // 4.更新媒资处理表状态
           mediaProcess.setStatus(status);
           mediaProcess.setUrl(url);
           mediaProcess.setFinishDate(LocalDateTime.now());
           mediaProcessMapper.updateById(mediaProcess);
           // 5.媒资处理表同步到媒资处理历史表
           MediaProcessHistory mediaProcessHistory = new MediaProcessHistory();
           BeanUtils.copyProperties(mediaProcess, mediaProcessHistory);
           mediaProcessHistoryMapper.insert(mediaProcessHistory);
   
           // 6.删除媒资处理表记录
           mediaProcessMapper.deleteById(taskId);
       }
   
   ```

4. 视频处理任务调度

   ```java
   /**
    * 视频处理任务调度
    *
    * @author elonlo
    * @date 2024/1/17 22:16
    */
   @Slf4j
   @Component
   public class VideoJobHandler {
   
       private final IMediaProcessService mediaProcessService;
   
       private final IMediaFilesService mediaFilesService;
   
       private final FfmpegProperties ffmpegProperties;
   
       public VideoJobHandler(IMediaProcessService mediaProcessService, IMediaFilesService mediaFilesService, FfmpegProperties ffmpegProperties) {
           this.mediaProcessService = mediaProcessService;
           this.mediaFilesService = mediaFilesService;
           this.ffmpegProperties = ffmpegProperties;
       }
   
       /**
        * 视频处理任务
        */
       @XxlJob("videoHandler")
       public void videoHandler() {
           // 分片参数
           // 执行器序号,从0开始
           int shardIndex = XxlJobHelper.getShardIndex();
           // 执行器总数
           int shardTotal = XxlJobHelper.getShardTotal();
   
           // 获取cpu线程数
           int processors = Runtime.getRuntime().availableProcessors();
   
           // 1.查询待处理任务
           List<MediaProcess> processList = mediaProcessService.getMediaProcessList(shardIndex, shardTotal, processors);
   
           // 待处理任务数
           int size = processList.size();
           if (size <= 0) {
               log.debug("待处理任务数为0");
               return;
           }
   
           // 创建一个cpu线程数的线程池
           ExecutorService executorService = Executors.newFixedThreadPool(size);
   
           // 创建计数器,所有线程执行完之后方法才结束
           CountDownLatch countDownLatch = new CountDownLatch(size);
   
           processList.forEach(mediaProcess -> {
               // 将任务加入线程池
               executorService.execute(() -> {
                   try {
                       // 获取任务id
                       Long taskId = mediaProcess.getId();
   
                       // 获取文件id
                       String fileId = mediaProcess.getFileId();
   
                       // 任务处理url
                       String processUrl = mediaProcess.getUrl();
   
                       // 2.开启任务
                       boolean taskFlag = mediaProcessService.startTask(taskId);
                       if (!taskFlag) {
                           log.debug("抢占任务失败,任务id: {}", taskId);
                           mediaProcessService.updateMediaProcessStatus(taskId, "3", fileId, processUrl, "抢占任务失败");
                           return;
                       }
   
                       // 3.视频转码
                       // 获取源avi视频的路径
                       String bucket = mediaProcess.getBucket();
                       String objectName = mediaProcess.getFilePath();
                       File file = mediaFilesService.downloadFileFromMinIO(bucket, objectName);
                       if (Objects.isNull(file)) {
                           log.debug("下载视频到本地失败,任务id: {}, bucket: {}, objectName: {}", taskId, bucket, objectName);
                           mediaProcessService.updateMediaProcessStatus(taskId, "3", fileId, processUrl, "下载视频到本地失败");
                           return;
                       }
   
                       String videoPath = file.getAbsolutePath();
   
                       // 转换后mp4文件的名称
                       String mp4Name = fileId + ".mp4";
   
                       // 转换后mp4文件的路径
                       // 创建临时文件,作为转换后的文件
                       File tempFile;
                       try {
                           tempFile = File.createTempFile("minio", ".mp4");
                           tempFile.deleteOnExit();
                       } catch (IOException e) {
                           log.error("创建临时文件失败: []", e);
                           mediaProcessService.updateMediaProcessStatus(taskId, "3", fileId, processUrl, "创建临时文件失败");
                           return;
                       }
                       String mp4Path = tempFile.getAbsolutePath();
   
                       //创建工具类对象
                       Mp4VideoUtil videoUtil = new Mp4VideoUtil(ffmpegProperties.getFfmpegPath(), videoPath, mp4Name, mp4Path);
   
                       // 开始视频转换,成功将返回success
                       String result = videoUtil.generateMp4();
   
                       if (!Objects.equals("success", result)) {
                           log.debug("视频转码失败,源avi视频的路径: {},转换后mp4文件的名称: {},转换后mp4文件的路径: {},错误信息: {}", videoPath, mp4Name, mp4Path, "视频转码失败");
                           mediaProcessService.updateMediaProcessStatus(taskId, "3", fileId, processUrl, "视频转码失败");
                           return;
                       }
   
                       // 4.上传转码后的视频到minio
                       String mimeType = FileUtil.getMimeType(".mp4");
   
                       // 获取mp4文件url
                       String url = mediaFilesService.getMergeFilePathByFileMd5(fileId, ".mp4");
   
                       boolean uploadFlag = mediaFilesService.uploadMinio(bucket, mp4Path, mimeType, url);
                       if (!uploadFlag) {
                           log.debug("上传mp4到minio失败,任务id: {}", taskId);
                           mediaProcessService.updateMediaProcessStatus(taskId, "3", fileId, processUrl, "上传mp4到minio失败");
                           return;
                       }
   
                       // 5.保存文件处理记录,任务状态为成功
                       mediaProcessService.updateMediaProcessStatus(taskId, "2", fileId, url, "任务处理成功");
                   } finally {
                       // 计数器减一
                       countDownLatch.countDown();
                   }
               });
           });
   
           try {
               // 阻塞,指定最大限度的等待时间,30分钟后自定释放
               countDownLatch.await(30, TimeUnit.MINUTES);
           } catch (InterruptedException e) {
               log.error("线程释放失败: []", e);
               e.printStackTrace();
           }
       }
   }
   ```


### 9.9 课程计划绑定媒资

1. 查询媒资文件列表

   ```java
   /**
    * 媒资文件查询DTO
    *
    * @author elonlo
    * @date 2024/1/20 14:15
    */
   @AllArgsConstructor
   @NoArgsConstructor
   @Accessors(chain = true)
   @Builder
   @Data
   public class MediaFileQueryDTO implements Serializable {
   
       private static final long serialVersionUID = 1L;
   
       /**
        * 审核状态
        */
       private String auditStatus;
   
       /**
        * 媒资文件名称
        */
       private String filename;
   
       /**
        * 媒资文件类型
        */
       private String type;
   }
   ```

   ```java
   @RestController
   public class MediaController {
   
       private final IMediaFilesService mediaFilesService;
   
       public MediaController(IMediaFilesService mediaFilesService) {
           this.mediaFilesService = mediaFilesService;
       }
   
       @ApiOperation(value = "查询媒资文件列表")
       @PostMapping("/files")
       public PageResult<MediaFiles> listAllMediaFile(PageParams params, @RequestBody MediaFileQueryDTO dto) {
           Page<MediaFiles> mediaFilesPage = mediaFilesService.listAllMediaFile(params, dto);
           return new PageResult<>(mediaFilesPage.getRecords(), mediaFilesPage.getTotal(), mediaFilesPage.getCurrent(), mediaFilesPage.getPages());
       }
   }
   ```

   ```java
   @Slf4j
   @Service
   public class MediaFilesServiceImpl extends ServiceImpl<MediaFilesMapper, MediaFiles> implements IMediaFilesService {
   
       private final MediaFilesMapper mediaFilesMapper;
   
       public MediaFilesServiceImpl(MediaFilesMapper mediaFilesMapper) {
           this.mediaFilesMapper = mediaFilesMapper;
       }
   
       /**
        * 查询媒资文件列表
        */
       @Override
       public Page<MediaFiles> listAllMediaFile(PageParams params, MediaFileQueryDTO dto) {
           // 构建查询条件对象
           LambdaQueryWrapper<MediaFiles> queryWrapper = new LambdaQueryWrapper<>();
           queryWrapper.eq(StringUtils.hasText(dto.getType()), MediaFiles::getFileType, dto.getType());
           queryWrapper.eq(StringUtils.hasText(dto.getAuditStatus()), MediaFiles::getAuditStatus, dto.getAuditStatus());
           queryWrapper.eq(StringUtils.hasText(dto.getFilename()), MediaFiles::getFilename, dto.getFilename());
   
           // 分页对象
           Page<MediaFiles> page = new Page<>(params.getPageNo(), params.getPageSize());
           // 查询数据内容获得结果
           return mediaFilesMapper.selectPage(page, queryWrapper);
       }
   }
   ```

2. 课程计划绑定媒资信息

   ```java
   /**
    * 课程计划媒资绑定DTO
    *
    * @author elonlo
    * @date 2024/1/20 13:12
    */
   @AllArgsConstructor
   @NoArgsConstructor
   @Accessors(chain = true)
   @Builder
   @Data
   public class BindTeachplanMediaDTO implements Serializable {
   
       private static final long serialVersionUID = 1L;
   
       /**
        * 媒资文件id
        */
       private String mediaId;
   
       /**
        * 媒资文件名称
        */
       private String fileName;
   
       /**
        * 课程计划id
        */
       private Long teachplanId;
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
   
       @ApiOperation("课程计划绑定媒资信息")
       @PostMapping("/teachplan/association/media")
       public void associationMedia(@RequestBody BindTeachplanMediaDTO dto) {
           teachplanService.associationMedia(dto);
       }
   }
   ```

   ```java
   @Service
   public class TeachplanServiceImpl extends ServiceImpl<TeachplanMapper, Teachplan> implements ITeachplanService {
   
       private final TeachplanMapper teachplanMapper;
   
       private final TeachplanMediaMapper teachplanMediaMapper;
   
       public TeachplanServiceImpl(TeachplanMapper teachplanMapper, TeachplanMediaMapper teachplanMediaMapper) {
           this.teachplanMapper = teachplanMapper;
           this.teachplanMediaMapper = teachplanMediaMapper;
       }
   
       /**
        * 课程计划创建或修改
        */
       @Override
       public void saveOrUpdateTeachplan(AddTeachplanDTO dto) {
           //通过课程计划id判断是新增和修改
           Long teachplanId = dto.getId();
           if (teachplanId == null) {
               //新增
               Teachplan teachplan = new Teachplan();
               BeanUtils.copyProperties(dto, teachplan);
               //确定排序字段，找到它的同级节点个数，排序字段就是个数加1  select count(1) from teachplan where course_id=117 and parentid=268
               Long parentid = dto.getParentid();
               Long courseId = dto.getCourseId();
               int teachplanCount = getTeachplanCount(courseId, parentid);
               teachplan.setOrderby(teachplanCount);
               teachplanMapper.insert(teachplan);
   
           } else {
               //修改
               Teachplan teachplan = teachplanMapper.selectById(teachplanId);
               //将参数复制到teachplan
               BeanUtils.copyProperties(dto, teachplan);
               teachplanMapper.updateById(teachplan);
           }
       }
   
       /**
        * 课程计划绑定媒资信息
        */
       @Transactional(rollbackFor = {RuntimeException.class, Exception.class}, propagation = Propagation.REQUIRED)
       @Override
       public void associationMedia(BindTeachplanMediaDTO dto) {
   
           // 1.查询课程计划是否存在
           Long teachplanId = dto.getTeachplanId();
           Teachplan teachplan = teachplanMapper.selectById(teachplanId);
           if (Objects.isNull(teachplan)) {
               throw new BusinessException("课程计划不存在");
           }
   
           // 2.只允许第二级课程计划绑定媒资信息
           Integer grade = teachplan.getGrade();
           if (grade != 2) {
               throw new BusinessException("绑定失败,只允许第二级课程计划绑定媒资信息");
           }
   
           // 3.删除课程计划原来绑定的媒资信息
           teachplanMediaMapper.delete(new LambdaQueryWrapper<TeachplanMedia>()
                   .eq(TeachplanMedia::getTeachplanId, teachplanId));
   
           // 4.添加新的媒资信息绑定课程计划
           TeachplanMedia teachplanMedia = new TeachplanMedia();
           BeanUtils.copyProperties(dto, teachplanMedia);
           // 添加课程id
           teachplanMedia.setCourseId(teachplan.getCourseId());
           teachplanMedia.setMediaFilename(dto.getFileName());
           teachplanMediaMapper.insert(teachplanMedia);
       }
   
       /**
        * 查询课程计划数量
        */
       private int getTeachplanCount(Long courseId, Long parentId) {
           LambdaQueryWrapper<Teachplan> queryWrapper = new LambdaQueryWrapper<>();
           queryWrapper = queryWrapper.eq(Teachplan::getCourseId, courseId).eq(Teachplan::getParentid, parentId);
           Integer count = teachplanMapper.selectCount(queryWrapper);
           return count + 1;
       }
   }
   ```

## 10、课程发布

### 10.1 课程预览

#### 10.1.1 配置模板引擎

1. 添加依赖（xuecheng-plus-content-api模块）

   ```xml
   <dependency>
       <groupId>org.springframework.boot</groupId>
       <artifactId>spring-boot-starter-freemarker</artifactId>
   </dependency>
   ```

2. 添加模板引擎 `Nacos` 配置

   ![image-20240120153832226](https://image.elonlo.top/img/2024/01/20/65ab7881b42c9.png)

3. 配置文件引入模板引擎配置

   ```yaml
   spring:
     application:
       name: content-api
     cloud:
       nacos:
         server-addr: yourip:port
         # 注册中心配置
         discovery:
           namespace: dev
           group: xuecheng-plus
         # 配置中心配置
         config:
           namespace: dev
           group: xuecheng-plus
           file-extension: yaml
           refresh-enabled: true
           # 引用content-service-dev配置
           extension-configs:
             - data-id: content-service-${spring.profiles.active}.yaml
               group: xuecheng-plus
               refresh: true
           # 读取公共配置
           shared-configs:
             - data-id: logging-${spring.profiles.active}.yaml
               group: xuecheng-plus-common
               refresh: true
             - data-id: freemarker-config-${spring.profiles.active}.yaml
               group: xuecheng-plus-common
               refresh: true
   
     profiles:
       active: dev
   ```

4. 创建模板文件目录

   ![image-20240120154330508](https://image.elonlo.top/img/2024/01/20/65ab79a3a09e9.png)

#### 10.1.2 配置 Nginx

1. 下载 Windows 版本的 Nginx

2. 将静态文件拷贝到 `html` 目录下的 `xc` 文件夹中

3. 将 `conf` 文件夹中的 `nginx.conf` 文件内容替换为以下内容

   ```ini
   #user  nobody;
   worker_processes  1;
   
   #error_log  logs/error.log;
   #error_log  logs/error.log  notice;
   #error_log  logs/error.log  info;
   
   #pid        logs/nginx.pid;
   
   
   events {
       worker_connections  1024;
   }
   
   
   http {
       include       mime.types;
       default_type  application/octet-stream;
   
       #log_format  main  '$remote_addr - $remote_user [$time_local] "$request" '
       #                  '$status $body_bytes_sent "$http_referer" '
       #                  '"$http_user_agent" "$http_x_forwarded_for"';
   
       #access_log  logs/access.log  main;
   
       sendfile        on;
       #tcp_nopush     on;
   
       #keepalive_timeout  0;
       keepalive_timeout  65;
   
       #gzip  on;
   
   	include D:/Software/Development/Nginx/conf.d/*.conf;
   
   	# minio服务
   	upstream fileserver {
   		server	ip:port	weight=10;
   	}
   }
   ```

4. 在 `conf` 目录同级建立一个 `conf.d` 文件夹，创建一个 `xc.conf` 文件，添加以下内容

   ```ini
   server {
   	listen       8081;
   	server_name  localhost;
   
   	ssi on;
   	ssi_silent_errors on;
   
   	location / {
   		alias   D:/Software/Development/Nginx/html/xc/;
   		index  index.html index.htm;
   	}
   
   	# 静态资源
   	location /static/img/ {
   		alias   D:/Software/Development/Nginx/html/xc/img/;
   	}
   
   	location /static/css/ {
   		alias   D:/Software/Development/Nginx/html/xc/css/;
   	}
   
   	location /static/js/ {
   		alias   D:/Software/Development/Nginx/html/xc/js/;
   	}
   
   	location /static/plugins/ {
   		alias   D:/Software/Development/Nginx/html/xc/plugins/;
   		add_header	Access-Control-Allow-Origin '*';
   		add_header	Access-Control-Allow-Credentials	true;
   		add_header	Access-Control-Allow-Methods	GET;
   	}
   
   	location /plugins/ {
   		alias   D:/Software/Development/Nginx/html/xc/plugins/;
   	}
   
   	location /course/preview/learning.html {
   		alias   D:/Software/Development/Nginx/html/xc/course/learning.html;
   	}
   
   	location /course/search.html {
   		root   D:/Software/Development/Nginx/html/xc;
   	}
   
   	location /course/learning.html {
   		root   D:/Software/Development/Nginx/html/xc;
   	}
   }
   
   
   server {
   	listen       8082;
   	server_name  localhost;
   
   	ssi on;
   	ssi_silent_errors on;
   
   	location /video {
   		proxy_pass	http://fileserver/;
   	}
   
   	location /file {
   		proxy_pass	http://fileserver/;
   	}
   }
   ```

5. 将 `course_template.html`，`learning.html` 中的 `域名1` 替换为 `127.0.0.1:8081`，`域名2` 替换为 `127.0.0.1:8082`

6. 配置网关反向代理

   ```ini
   
   #user  nobody;
   worker_processes  1;
   
   #error_log  logs/error.log;
   #error_log  logs/error.log  notice;
   #error_log  logs/error.log  info;
   
   #pid        logs/nginx.pid;
   
   
   events {
       worker_connections  1024;
   }
   
   
   http {
       include       mime.types;
       default_type  application/octet-stream;
   
       #log_format  main  '$remote_addr - $remote_user [$time_local] "$request" '
       #                  '$status $body_bytes_sent "$http_referer" '
       #                  '"$http_user_agent" "$http_x_forwarded_for"';
   
       #access_log  logs/access.log  main;
   
       sendfile        on;
       #tcp_nopush     on;
   
       #keepalive_timeout  0;
       keepalive_timeout  65;
   
       #gzip  on;
   
   	include D:/Software/Development/Nginx/conf.d/*.conf;
   	
   	# minio服务
   	upstream fileserver {
   		server	ip:port	weight=10;
   	}
   
   	# 网关服务
   	upstream gatewayserver {
   		server	localhost:63010	weight=10;
   	}
   }
   ```

   ```ini
   server {
   	listen       8081;
   	server_name  localhost;
   
   	ssi on;
   	ssi_silent_errors on;
   
   	location / {
   		alias   D:/Software/Development/Nginx/html/xc/;
   		index  index.html index.htm;
   	}
   
   	# 代理网关服务
   	location /api/ {
   		proxy_pass	http://gatewayserver/;
   	}
   }
   ```

#### 10.1.3 课程预览信息

```java
@Controller
public class CoursePublishController {

    private final ICoursePublishService coursePublishService;

    public CoursePublishController(ICoursePublishService coursePublishService) {
        this.coursePublishService = coursePublishService;
    }

    @ApiOperation(value = "课程预览信息")
    @GetMapping("/course/preview/{courseId}")
    public ModelAndView preview(@PathVariable("courseId") Long courseId) {
        ModelAndView modelAndView = new ModelAndView();

        // 查询课程的信息作为模型数据
        CoursePreviewVO coursePreviewInfo = coursePublishService.getCoursePreviewInfo(courseId);

        // 指定模型
        modelAndView.addObject("model", coursePreviewInfo);

        // 指定模板,根据视图名称加.ftl找到模板
        modelAndView.setViewName("courseTemplate");
        return modelAndView;
    }
}
```

```html
<!DOCTYPE html>
<html lang="zh-CN">

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="/static/img/asset-favicon.ico">
    <title>学成在线-${model.courseBase.name}</title>

    <link rel="stylesheet" href="/static/plugins/normalize-css/normalize.css" />
    <link rel="stylesheet" href="/static/plugins/bootstrap/dist/css/bootstrap.css" />
    <link rel="stylesheet" href="/static/css/page-learing-article.css" />
</head>

<body data-spy="scroll" data-target="#articleNavbar" data-offset="150">
<div id="learningArea">
<div class="article-banner">
    <div class="banner-bg"></div>
    <div class="banner-info">
        <div class="banner-left">
            <p>${model.courseBase.mtName!''}<span>\ ${model.courseBase.stName!''}</span></p>
            <p class="tit">${model.courseBase.name}</p>
            <p class="pic">
                <#if model.courseBase.charge=='201000'>
                    <span class="new-pic">免费</span>
                <#else>
                    <span class="new-pic">特惠价格￥${model.courseBase.price!''}</span>
                    <span class="old-pic">原价￥${model.courseBase.originalPrice!''}</span>
                </#if>
            </p>
            <p class="info">
                <a href="#" @click.prevent="startLearning()">马上学习</a>
                <span><em>难度等级</em>
                <#if model.courseBase.grade=='204001'>
                    初级
                 <#elseif model.courseBase.grade=='204002'>
                    中级
                <#elseif model.courseBase.grade=='204003'>
                    高级
                </#if>
                </span>
                <span><em>课程时长</em>2小时27分</span>
                <span><em>评分</em>4.7分</span>
                <span><em>授课模式</em>
                 <#if model.courseBase.teachmode=='200002'>
                     录播
                 <#elseif model.courseBase.teachmode=='200003'>
                     直播
                 </#if>
                </span>
            </p>
        </div>
        <div class="banner-rit">
            <p>
                <a href="http://localhost:8081/course/preview/learning.html?id=${model.courseBase.id}" target="_blank">
                    <#if model.courseBase.pic??>
                        <img src="http://localhost:8082${model.courseBase.pic!''}" alt="" width="270" height="156">
                    <#else>
                        <img src="/static/img/widget-video.png" alt="" width="270" height="156">
                    </#if>

                </a>
            </p>
            <p class="vid-act"><span> <i class="i-heart"></i>收藏 23 </span> <span>分享 <i class="i-weixin"></i><i class="i-qq"></i></span></p>
        </div>
    </div>
</div>
<div class="article-cont">
    <div class="tit-list">
        <a href="javascript:;" id="articleClass" class="active">课程介绍</a>
        <a href="javascript:;" id="articleItem">目录</a>
        <a href="javascript:;" id="artcleAsk">问答</a>
        <a href="javascript:;" id="artcleNot">笔记</a>
        <a href="javascript:;" id="artcleCod">评价</a>
    </div>
    <div class="article-box">
        <div class="articleClass" style="display: block">
            <!--<div class="rit-title">评价</div>-->
            <div class="article-cont">
                <div class="article-left-box">
                    <div class="content">

                        <div class="content-com suit">
                            <div class="title"><span>适用人群</span></div>
                            <div class="cont cktop">
                                <div >
                                    <p>${model.courseBase.users!""}</p>
                                </div>
                            </div>
                        </div>
                        <div class="content-com course">
                            <div class="title"><span>课程制作</span></div>
                            <div class="cont">
                                <div class="img-box"><img src="/static/img/widget-myImg.jpg" alt=""></div>
                                <div class="info-box">
                                    <p class="name">教学方：<em>XX老师</em></p>
                                    <p class="info">JavaEE开发与教学多年</p>
                                </div>
                            </div>

                        </div>
                        <div class="content-com about">
                            <div class="title"><span>课程介绍</span></div>
                            <div class="cont cktop">
                                <div >
                                    <p>${model.courseBase.description!""}</p>
                                </div>
                            </div>
                        </div>
                        <div class="content-com prob">
                            <div class="title"><span>常见问题</span></div>
                            <div class="cont">
                                <ul>
                                    <li class="item"><span class="on-off"><i class="i-chevron-bot"></i> 我什么时候能够访问课程视频与作业？</span>
                                        <div class="drop-down">
                                        </div>
                                    </li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>

            </div>
        </div>
        <div class="articleItem" style="display: none">
            <div class="article-cont-catalog">
                <div class="article-left-box">
                    <div class="content">
                        <#list model.teachplans as firstNode>
                            <div class="item">
                                <div class="title act"><i class="i-chevron-top"></i>${firstNode.pname}<span class="time">x小时</span></div>
                                <div class="drop-down" style="height: 260px;">
                                    <ul class="list-box">
                                        <#list firstNode.teachPlanTreeNodes as secondNode>
                                            <li><a href="http://localhost:8081/course/preview/learning.html?id=${model.courseBase.id}&chapter=${secondNode.teachplanMedia.teachplanId!''}" target="_blank">${secondNode.pname}</a></li>
                                        </#list>
                                    </ul>
                                </div>
                            </div>
                        </#list>
                    </div>
                </div>
            </div>
        </div>
        <#--<div class="articleItem" style="display: none">
            <div class="article-cont-catalog">
                <div class="article-left-box">
                    <div class="content">
                        <div class="item">
                            <div class="title act"><i class="i-chevron-top"></i>第一阶段 HTTP协议基础详解<span class="time">8小时</span></div>
                            <div class="about">使用Java消息中间件处理异步消息成为了分布式系统中的必修课，通过本门课程可以深入浅出的学习如何在Java中使用消息中间件并且一步一步打造更优雅的最佳实践方案。</div>
                            <div class="drop-down" style="height: 260px;">
                                <ul class="list-box">
                                    <li class="active">1.1 阅读：分级政策细节 <span>97’33”</span></li>
                                    <li>1.2 视频：为什么分为 A 部分、B 部分、C 部分 <span>66’15”</span></li>
                                    <li>1.3 视频：软件安装介绍 <span>86’42”</span></li>
                                    <li>1.4 阅读：Emacs安装 <span>59’00”</span></li>
                                    <li>1.5 作业1：Emacs安装 <span>93’29”</span></li>
                                    <li>阶段测试</li>
                                </ul>
                            </div>
                        </div>
                        <div class="item">
                            <a href="#" class="overwrite">毕业考核</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>-->
        <div class="artcleAsk" style="display: none">
            <div class="article-cont-ask">
                <div class="article-left-box">
                    <div class="content">
                        <div class="content-title">
                            <p><a class="all">全部</a><a>精选</a><a>我的</a></p>
                            <p><a class="all">全部</a><span><a>1.1</a><a>1.2</a><a>1.3</a><a>1.4</a><a>1.5</a></span><a href="$" class="more">更多 <i class="i-chevron-bot"></i></a></p>
                        </div>
                        <div class="item">
                            <div class="item-left">
                                <p><img src="/static/img/widget-myImg.jpg" width="60px" alt=""></p>
                                <p>毛老师</p>
                            </div>
                            <div class="item-right">
                                <p class="title">如何用微服务重构应用程序?</p>
                                <p>2017-3-20 <span class="action-box"><span><i class="i-answer"></i>回答 2</span><span><i class="i-browse"></i>浏览 12</span></span>
                                </p>
                            </div>
                        </div>

                        <div class="itemlast">
                            <a href="#" class="overwrite">显示更多问题</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="artcleNot" style="display: none;">
            <div class="article-cont-note">
                <div class="article-left-box">
                    <div class="content">
                        <div class="content-title">
                            <p><a class="all">全部</a><a>精选</a><a>我的</a></p>
                            <p><a class="all">全部</a><span><a>1.1</a><a>1.2</a><a>1.3</a><a>1.4</a><a>1.5</a></span><a href="$" class="more">更多 <i class="i-chevron-bot"></i></a></p>
                        </div>
                        <div class="item">
                            <div class="item-left">
                                <p><img src="/static/img/widget-myImg.jpg" width="60px" alt=""></p>
                                <p>毛老师</p>
                            </div>
                            <div class="item-right">
                                <span class="video-time"><i class="i-play"></i>2`10`</span>
                                <p><img src="/static/img/widget-demo.png" width="221" alt=""></p>
                                <p class="action-box">4小时前 <span class="active-box"><span><i class="i-coll"></i>采集</span><span><i class="i-laud"></i>赞</span></span>
                                </p>
                            </div>
                        </div>
                        <div class="item">
                            <div class="item-left">
                                <p><img src="/static/img/widget-myImg.jpg" width="60px" alt=""></p>
                                <p>毛老师</p>
                            </div>
                            <div class="item-right">
                                <p class="action-box">4小时前 <span class="active-box"><span><i class="i-edt"></i>编辑</span><span><i class="i-del"></i>删除</span><span><i class="i-laud"></i>赞</span></span>
                                </p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="artcleCod" style="display: none;">
            <div class="article-cont">
                <div class="article-left-box">
                    <div class="comment-box">
                        <div class="evaluate">
                            <div class="eva-top">
                                <div class="tit">课程评分 </div>
                                <div class="star">
                                    <div class="score"><i>5</i></div>
                                </div><span class="star-score"> <i>5</i> 分</span></div>
                            <div class="eva-cont">
                                <div class="tit">学员评语 </div>
                                <div class="text-box">
                                    <textarea class="form-control" rows="5" placeholder="扯淡、吐槽、表扬、鼓励......想说啥说啥！"></textarea>
                                    <div class="text-right"><span>发表评论</span></div>
                                </div>
                            </div>
                        </div>
                        <div class="course-evaluate">
                            <div class="top-tit">评论
                                <span>
                        <label><input name="eval" type="radio" value="" checked /> 所有学生 </label>
                        <label><input name="eval" type="radio" value="" /> 完成者 </label>
                    </span>
                            </div>
                            <div class="top-cont">
                                <div class="cont-top-left">
                                    <div class="star-scor">
                                        <div class="star-show">
                                            <div class="score"><i>5</i></div>
                                        </div>
                                        <div class="scor">4.9分</div>
                                    </div>
                                    <div class="all-scor">总评分：12343</div>
                                </div>
                                <div class="cont-top-right">
                                    <div class="star-grade">五星
                                        <div class="grade">
                                            <div class="grade-percent"><span></span></div>
                                            <div class="percent-num"><i>95</i>%</div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="comment-item-box">
                                <div class="title">评论 <span>12453条评论</span></div>
                                <div class="item">
                                    <div class="item-left">
                                        <p><img src="/static/img/widget-pic.png" width="60px" alt=""></p>
                                        <p>毛老师</p>
                                    </div>
                                    <div class="item-cent">
                                        <p>很受用，如果再深入下就更好了。</p>
                                        <p class="time">2017-2-43</p>
                                    </div>
                                    <div class="item-rit">
                                        <p>
                                        <div class="star-show">
                                            <div class="score"><i>4</i></div>
                                        </div>
                                        </p>
                                        <p>评分 <span>5星</span></p>
                                    </div>
                                </div>
                                <div class="get-more">页面加载中...</div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
    <div class="popup-course">
        <div class="mask"></div>
        <!--欢迎访问课程弹窗- start -->
        <div class="popup-course-box">
            <div class="title">${model.courseBase.name} <span class="close-popup-course-box">×</span></div>
            <div class="content">
                <p>欢迎学习本课程</p>
                <p><a href="#" @click.prevent="addCourseTable()">加入我的课程表</a>  <a href="#" @click.prevent="startLearngin()">立即学习</a></p>
            </div>
        </div>
    </div>
    <div class="popup-box">
        <div class="mask"></div>
        <!--支付弹窗- start -->
        <div class="popup-pay-box">
            <div class="title">${model.courseBase.name} <span class="close-popup-pay-box">×</span></div>
            <div class="content">
                <img :src="qrcode" width="200" height="200" alt="请点击支付宝支付按钮，并完成扫码支付。"/>

                <div class="info">
                    <p class="info-tit">${model.courseBase.name}<span>课程有效期:${model.courseBase.validDays}天</span></p>
                    <p class="info-pic">课程价格 : <span>￥${model.courseBase.originalPrice!''}元</span></p>
                    <p class="info-new-pic">优惠价格 : <span>￥${model.courseBase.price!''}元</span></p>
                </div>
            </div>
            <div class="fact-pic">实际支付: <span>￥${model.courseBase.price!''}元</span></div>
            <div class="go-pay"><a href="#" @click.prevent="wxPay()">微信支付</a><a href="#" @click.prevent="aliPay()">支付宝支付</a><a href="#" @click.prevent="querypayresult()">支付完成</a><a href="#" @click.prevent="startLearngin()">试学</a></div>
        </div>
        <!--支付弹窗- end -->
        <div class="popup-comment-box">

        </div>
    </div>
</div>
<script>var courseId = "${model.courseBase.id}";var courseCharge = "${model.courseBase.charge}"</script>
</body>

```

#### 10.1.4 查看课程预览信息

```java
/**
 * 课程开放公共控制器
 *
 * @author elonlo
 * @date 2024/1/21 14:32
 */
@RestController
@RequestMapping("/open")
public class CourseOpenController {

    private final ICoursePublishService coursePublishService;

    public CourseOpenController(ICoursePublishService coursePublishService) {
        this.coursePublishService = coursePublishService;
    }

    @ApiOperation("查询课程预览信息")
    @GetMapping("/course/whole/{courseId}")
    public CoursePreviewVO getPreviewInfo(@PathVariable("courseId") Long courseId) {
        return coursePublishService.getCoursePreviewInfo(courseId);
    }
}
```

#### 10.1.5 查询媒资信息视频url

```java
/**
 * 媒资开放公共控制器
 *
 * @author elonlo
 * @date 2024/1/21 14:38
 */
@RestController
@RequestMapping("/open")
public class MediaOpenController {

    private final IMediaFilesService mediaFilesService;

    public MediaOpenController(IMediaFilesService mediaFilesService) {
        this.mediaFilesService = mediaFilesService;
    }

    @ApiOperation(value = "查询媒资信息视频url")
    @GetMapping("/preview/{mediaId}")
    public ResultResponse<String> getPlayUrlById(@PathVariable("mediaId") String mediaId) {
        MediaFiles mediaFiles = mediaFilesService.selectPlayUrlById(mediaId);

        if (Objects.isNull(mediaFiles)) {
            return ResultResponse.error("找不到视频");
        }

        String url = Optional.of(mediaFiles)
                .map(MediaFiles::getUrl)
                .orElse("");

        if (StringUtils.isEmpty(url)) {
            return ResultResponse.error("视频正在处理中");
        }
        return ResultResponse.success(url);
    }
}
```

#### 10.1.6 修改 `learning.js`

```js
var url = "/api";
const requestGetCourseInfo = (courseId) => {
    return  requestGet(url+"/content/open/course/whole/"+courseId,{});
}
const requestGetMeidaInfo = (mediaId,teachplanId,courseId) => {
    if(url=="/api"){
        return  requestGet(url+"/media/open/preview/"+mediaId,{});
    }else{
        return  requestGet(url+"/learning/open/learn/getvideo/"+courseId+"/"+teachplanId+"/"+mediaId,{});
    }
    
}
var location_url = String(window.location);
if(location_url.indexOf("/preview/")<0){
    url = "/api"
}
```

### 10.2 课程审核

```java
@Controller
public class CoursePublishController {

    private final ICoursePublishService coursePublishService;

    public CoursePublishController(ICoursePublishService coursePublishService) {
        this.coursePublishService = coursePublishService;
    }

    @ApiOperation(value = "课程提交审核")
    @ResponseBody
    @PostMapping("/course/commit/audit/{courseId}")
    public void commitAudit(@PathVariable("courseId") Long courseId) {
        Long companyId = 1232141425L;
        coursePublishService.commitAudit(companyId, courseId);
    }
}
```

```java
@Service
public class CoursePublishServiceImpl extends ServiceImpl<CoursePublishMapper, CoursePublish> implements ICoursePublishService {

    private final ICourseBaseService courseBaseService;

    private final ITeachplanService teachplanService;

    private final CourseMarketMapper courseMarketMapper;

    private final CoursePublishPreMapper coursePublishPreMapper;

    public CoursePublishServiceImpl(ICourseBaseService courseBaseService, ITeachplanService teachplanService, CourseMarketMapper courseMarketMapper, CoursePublishPreMapper coursePublishPreMapper) {
        this.courseBaseService = courseBaseService;
        this.teachplanService = teachplanService;
        this.courseMarketMapper = courseMarketMapper;
        this.coursePublishPreMapper = coursePublishPreMapper;
    }

    /**
     * 课程提交审核
     */
    @Transactional(rollbackFor = {RuntimeException.class, Exception.class}, propagation = Propagation.REQUIRED)
    @Override
    public void commitAudit(Long companyId, Long courseId) {

        CourseBaseInfoVO courseBaseInfoVO = courseBaseService.queryCourseBaseInfoVO(courseId);

        // 1.课程已提交则不允许重复提交
        String auditStatus = courseBaseInfoVO.getAuditStatus();
        if (Objects.equals("202003", auditStatus)) {
            throw new BusinessException("课程已提交,请等待审核");
        }


        // 2.校验课程基本信息是否上传课程图片
        String pic = courseBaseInfoVO.getPic();
        if (StringUtils.isEmpty(pic)) {
            throw new BusinessException("该课程未上传图片,不能提交审核");
        }

        // 3.校验课程基本信息是否上传课程计划
        List<TeachplanVO> teachplanTree = teachplanService.findTeachplanTree(courseId);
        if (CollectionUtils.isEmpty(teachplanTree)) {
            throw new BusinessException("该课程未添加课程计划,不能提交审核");
        }

        // 只能审核本机构的课程

        // 4.将课程基本信息、营销信息、课程计划登信息存入课程预发布表
        CoursePublishPre coursePublishPre = new CoursePublishPre();
        BeanUtils.copyProperties(courseBaseInfoVO, coursePublishPre);

        // 查询课程营销信息
        CourseMarket courseMarket = courseMarketMapper.selectById(courseId);
        String courseMarketJson = JSON.toJSONString(courseMarket);
        coursePublishPre.setMarket(courseMarketJson);

        // 查询课程计划信息
        String teachplanTreeJson = JSON.toJSONString(teachplanTree);
        coursePublishPre.setTeachplan(teachplanTreeJson);
        // 修改状态和创建时间、审核时间
        coursePublishPre.setId(courseId);
        coursePublishPre.setStatus("202003");
        coursePublishPre.setCreateDate(LocalDateTime.now());
        coursePublishPre.setAuditDate(LocalDateTime.now());

        // 添加机构
        coursePublishPre.setCompanyId(companyId);

        // 5.查询预发布记录,如果有记录则更新,否则添加记录
        CoursePublishPre publishPre = coursePublishPreMapper.selectById(courseId);
        if (Objects.isNull(publishPre)) {
            coursePublishPreMapper.insert(coursePublishPre);
        } else {
            coursePublishPreMapper.updateById(coursePublishPre);
        }

        // 6.更新课程基本信息审核状态
        CourseBase courseBase = courseBaseService.getById(courseId);
        courseBase.setAuditStatus("202003");
        courseBaseService.updateById(courseBase);
    }
}
```

### 10.3 消息SDK

为了保证分布式事务下的数据**最终一致性**效果，我们采用了**本地消息表**和**分布式定时任务调度**来解决该问题，将消息处理做成一个公共的组件，其他服务依赖使用。

1. 创建消息模块工程

2. 添加消息实体类

   ```java
   @Data
   @EqualsAndHashCode(callSuper = false)
   @Accessors(chain = true)
   @TableName("mq_message")
   public class MqMessage implements Serializable {
   
       private static final long serialVersionUID = 1L;
   
       /**
        * 消息id
        */
       @TableId(value = "id", type = IdType.AUTO)
       private String id;
   
       /**
        * 消息类型代码
        */
       private String messageType;
   
       /**
        * 关联业务信息
        */
       private String businessKey1;
   
       /**
        * 关联业务信息
        */
       private String businessKey2;
   
       /**
        * 关联业务信息
        */
       private String businessKey3;
   
       /**
        * 消息队列主机
        */
       @TableField(exist = false)
       private String mqHost;
   
       /**
        * 消息队列端口
        */
       @TableField(exist = false)
       private Integer mqPort;
   
       /**
        * 消息队列虚拟主机
        */
       @TableField(exist = false)
       private String mqVirtualhost;
   
       /**
        * 队列名称
        */
       @TableField(exist = false)
       private String mqQueue;
   
       /**
        * 通知次数
        */
       @TableField(exist = false)
       private Integer informNum;
   
       /**
        * 处理状态，0:初始，1:成功
        */
       private String state;
   
       /**
        * 回复失败时间
        */
       private LocalDateTime returnfailureDate;
   
       /**
        * 回复成功时间
        */
       private LocalDateTime returnsuccessDate;
   
       /**
        * 回复失败内容
        */
       private String returnfailureMsg;
   
       /**
        * 最近通知时间
        */
       @TableField(exist = false)
       private LocalDateTime informDate;
   
       /**
        * 阶段1处理状态, 0:初始，1:成功
        */
       private String stageState1;
   
       /**
        * 阶段2处理状态, 0:初始，1:成功
        */
       private String stageState2;
   
       /**
        * 阶段3处理状态, 0:初始，1:成功
        */
       private String stageState3;
   
       /**
        * 阶段4处理状态, 0:初始，1:成功
        */
       private String stageState4;
   }
   ```

   ```java
   @Data
   @EqualsAndHashCode(callSuper = false)
   @Accessors(chain = true)
   @TableName("mq_message_history")
   public class MqMessageHistory implements Serializable {
   
       private static final long serialVersionUID = 1L;
   
       /**
        * 消息id
        */
       @TableId(value = "id", type = IdType.AUTO)
       private String id;
   
       /**
        * 消息类型代码
        */
       private String messageType;
   
       /**
        * 关联业务信息
        */
       private String businessKey1;
   
       /**
        * 关联业务信息
        */
       private String businessKey2;
   
       /**
        * 关联业务信息
        */
       private String businessKey3;
   
       /**
        * 消息队列主机
        */
       private String mqHost;
   
       /**
        * 消息队列端口
        */
       private Integer mqPort;
   
       /**
        * 消息队列虚拟主机
        */
       private String mqVirtualhost;
   
       /**
        * 队列名称
        */
       private String mqQueue;
   
       /**
        * 通知次数
        */
       private Integer informNum;
   
       /**
        * 处理状态，0:初始，1:成功，2:失败
        */
       private Integer state;
   
       /**
        * 回复失败时间
        */
       private LocalDateTime returnfailureDate;
   
       /**
        * 回复成功时间
        */
       private LocalDateTime returnsuccessDate;
   
       /**
        * 回复失败内容
        */
       private String returnfailureMsg;
   
       /**
        * 最近通知时间
        */
       private LocalDateTime informDate;
   
       private String stageState1;
   
       private String stageState2;
   
       private String stageState3;
   
       private String stageState4;
   }
   ```

3. 添加以下依赖信息

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
   
       <artifactId>xuecheng-plus-message</artifactId>
   
       <properties>
           <maven.compiler.source>8</maven.compiler.source>
           <maven.compiler.target>8</maven.compiler.target>
           <myOkhttp.version>4.8.1</myOkhttp.version>
       </properties>
   
       <dependencies>
           <dependency>
               <groupId>org.springframework.cloud</groupId>
               <artifactId>spring-cloud-context</artifactId>
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
   
           <dependency>
               <groupId>org.projectlombok</groupId>
               <artifactId>lombok</artifactId>
           </dependency>
   
           <dependency>
               <groupId>com.squareup.okhttp3</groupId>
               <artifactId>okhttp</artifactId>
               <version>${myOkhttp.version}</version>
           </dependency>
       </dependencies>
   </project>
   ```

4. 使用插件生成`mq_message`，`mq_message_history`的 PO等信息

5. 添加 `MyBatisPlusConfig` 配置类

   ```java
   @Configuration
   @MapperScan("com.yu.xuecheng.**.mapper")
   public class MyBatisPlusConfig {
   
   }
   ```

6. 添加消息相关接口和实现类

   ```java
   /**
    * <p>
    * 消息队列消息表 服务类
    * </p>
    *
    * @author elonlo
    * @since 2024-01-21
    */
   public interface IMqMessageService extends IService<MqMessage> {
   
       /**
        * 扫描消息表记录,查询未处理的消息
        *
        * @param shardIndex 分片序号
        * @param shardTotal 分片总数
        * @param count      扫描记录数
        * @return {@link List}<{@link MqMessage}>
        */
       List<MqMessage> getMessageList(int shardIndex, int shardTotal, String messageType, int count);
   
       /**
        * 添加消息
        *
        * @param businessKey1 业务id1
        * @param businessKey2 业务id2
        * @param businessKey3 业务id3
        * @return {@link MqMessage}
        */
       MqMessage addMessage(String messageType, String businessKey1, String businessKey2, String businessKey3);
   
       /**
        * 完成任务
        *
        * @param messageId 消息id
        * @return int
        */
       int completed(Long messageId);
   
       /**
        * 完成第一阶段任务
        *
        * @param messageId 消息id
        * @return int
        */
       int completedStageOne(Long messageId);
   
       /**
        * 完成第二阶段任务
        *
        * @param messageId 消息id
        * @return int
        */
       int completedStageTwo(Long messageId);
   
       /**
        * 完成第三阶段任务
        *
        * @param messageId 消息id
        * @return int
        */
       int completedStageThree(Long messageId);
   
       /**
        * 完成第四阶段任务
        *
        * @param messageId 消息id
        * @return int
        */
       int completedStageFour(Long messageId);
   
       /**
        * 查询第一阶段状态
        *
        * @param messageId 消息id
        * @return int
        */
       int getStageOne(Long messageId);
   
       /**
        * 查询第二阶段状态
        *
        * @param messageId 消息id
        * @return int
        */
       int getStageTwo(Long messageId);
   
       /**
        * 查询第三阶段状态
        *
        * @param messageId 消息id
        * @return int
        */
       int getStageThree(Long messageId);
   
       /**
        * 查询第四阶段状态
        *
        * @param messageId 消息id
        * @return int
        */
       int getStageFour(Long messageId);
   }
   ```

   ```java
   /**
    * <p>
    * 消息队列消息表 服务实现类
    * </p>
    *
    * @author elonlo
    * @since 2024-01-21
    */
   @Service
   public class MqMessageServiceImpl extends ServiceImpl<MqMessageMapper, MqMessage> implements IMqMessageService {
   
       private final MqMessageMapper mqMessageMapper;
   
       private final MqMessageHistoryMapper mqMessageHistoryMapper;
   
       public MqMessageServiceImpl(MqMessageMapper mqMessageMapper, MqMessageHistoryMapper mqMessageHistoryMapper) {
           this.mqMessageMapper = mqMessageMapper;
           this.mqMessageHistoryMapper = mqMessageHistoryMapper;
       }
   
       /**
        * 扫描消息表记录,查询未处理的消息
        */
       @Override
       public List<MqMessage> getMessageList(int shardIndex, int shardTotal, String messageType, int count) {
           return mqMessageMapper.selectListByShardIndex(shardIndex, shardTotal, messageType, count);
       }
   
       /**
        * 添加消息
        */
       @Override
       public MqMessage addMessage(String messageType, String businessKey1, String businessKey2, String businessKey3) {
           MqMessage mqMessage = new MqMessage();
           mqMessage.setMessageType(messageType);
           mqMessage.setBusinessKey1(businessKey1);
           mqMessage.setBusinessKey2(businessKey2);
           mqMessage.setBusinessKey3(businessKey3);
           int insert = mqMessageMapper.insert(mqMessage);
           if (insert > 0) {
               return mqMessage;
           } else {
               return null;
           }
   
       }
   
       /**
        * 完成任务
        */
       @Transactional
       @Override
       public int completed(Long messageId) {
           MqMessage mqMessage = new MqMessage();
           // 完成任务
           mqMessage.setState("1");
           int update = mqMessageMapper.update(mqMessage, new LambdaQueryWrapper<MqMessage>()
                   .eq(MqMessage::getId, messageId));
           if (update > 0) {
   
               mqMessage = mqMessageMapper.selectById(messageId);
               // 添加到历史表
               MqMessageHistory mqMessageHistory = new MqMessageHistory();
               BeanUtils.copyProperties(mqMessage, mqMessageHistory);
               mqMessageHistoryMapper.insert(mqMessageHistory);
               // 删除消息表
               mqMessageMapper.deleteById(messageId);
               return 1;
           }
           return 0;
   
       }
   
       /**
        * 完成第一阶段任务
        */
       @Override
       public int completedStageOne(Long messageId) {
           MqMessage mqMessage = new MqMessage();
           // 完成阶段1任务
           mqMessage.setStageState1("1");
           return mqMessageMapper.update(mqMessage, new LambdaQueryWrapper<MqMessage>()
                   .eq(MqMessage::getId, messageId));
       }
   
       /**
        * 完成第二阶段任务
        */
       @Override
       public int completedStageTwo(Long messageId) {
           MqMessage mqMessage = new MqMessage();
           // 完成阶段2任务
           mqMessage.setStageState2("1");
           return mqMessageMapper.update(mqMessage, new LambdaQueryWrapper<MqMessage>()
                   .eq(MqMessage::getId, messageId));
       }
   
       /**
        * 完成第三阶段任务
        */
       @Override
       public int completedStageThree(Long messageId) {
           MqMessage mqMessage = new MqMessage();
           // 完成阶段3任务
           mqMessage.setStageState3("1");
           return mqMessageMapper.update(mqMessage, new LambdaQueryWrapper<MqMessage>()
                   .eq(MqMessage::getId, messageId));
       }
   
       /**
        * 完成第四阶段任务
        */
       @Override
       public int completedStageFour(Long messageId) {
           MqMessage mqMessage = new MqMessage();
           // 完成阶段4任务
           mqMessage.setStageState4("1");
           return mqMessageMapper.update(mqMessage, new LambdaQueryWrapper<MqMessage>()
                   .eq(MqMessage::getId, messageId));
       }
   
       /**
        * 查询第一阶段状态
        */
       @Override
       public int getStageOne(Long messageId) {
           return Integer.parseInt(mqMessageMapper.selectById(messageId).getStageState1());
       }
   
       /**
        * 查询第二阶段状态
        */
       @Override
       public int getStageTwo(Long messageId) {
           return Integer.parseInt(mqMessageMapper.selectById(messageId).getStageState2());
       }
   
       /**
        * 查询第三阶段状态
        */
       @Override
       public int getStageThree(Long messageId) {
           return Integer.parseInt(mqMessageMapper.selectById(messageId).getStageState3());
       }
   
       /**
        * 查询第四阶段状态
        */
       @Override
       public int getStageFour(Long messageId) {
           return Integer.parseInt(mqMessageMapper.selectById(messageId).getStageState4());
       }
   }
   ```

   ```java
   /**
    * <p>
    * 消息队列消息表 Mapper 接口
    * </p>
    *
    * @author elonlo
    * @since 2024-01-21
    */
   public interface MqMessageMapper extends BaseMapper<MqMessage> {
   
       /**
        * 查询消息表中未处理的消息
        */
       @Select("SELECT t.* FROM mq_message t WHERE t.id % #{shardTotal} = #{shardIndex} and t.state='0' and t.message_type=#{messageType} limit #{count}")
       List<MqMessage> selectListByShardIndex(@Param("shardIndex") int shardIndex, @Param("shardTotal") int shardTotal, @Param("messageType") String messageType, @Param("count") int count);
   }
   ```

7. 添加消息处理抽象类

   ```java
   /**
    * 消息处理抽象类
    *
    * @author elonlo
    * @date 2024/1/21 23:51
    */
   @Slf4j
   @Data
   public abstract class MessageProcessAbstract {
   
       @Resource
       private IMqMessageService mqMessageService;
   
       /**
        * @param mqMessage 执行任务内容
        * @return boolean true:处理成功，false处理失败
        */
       public abstract boolean execute(MqMessage mqMessage);
   
       /**
        * @param shardIndex  分片序号
        * @param shardTotal  分片总数
        * @param messageType 消息类型
        * @param count       一次取出任务总数
        * @param timeout     预估任务执行时间,到此时间如果任务还没有结束则强制结束 单位秒
        */
       public void process(int shardIndex, int shardTotal, String messageType, int count, long timeout) {
   
           try {
               // 扫描消息表获取任务清单
               List<MqMessage> messageList = mqMessageService.getMessageList(shardIndex, shardTotal, messageType, count);
               // 任务个数
               int size = messageList.size();
               log.debug("取出待处理消息" + size + "条");
               if (size <= 0) {
                   return;
               }
   
               // 创建线程池
               ExecutorService threadPool = Executors.newFixedThreadPool(size);
               // 计数器
               CountDownLatch countDownLatch = new CountDownLatch(size);
               messageList.forEach(message -> threadPool.execute(() -> {
                   log.debug("开始任务:{}", message);
                   // 处理任务
                   try {
                       boolean result = execute(message);
                       if (result) {
                           log.debug("任务执行成功:{})", message);
                           // 更新任务状态,删除消息表记录,添加到历史表
                           int completed = mqMessageService.completed(Long.parseLong(message.getId()));
                           if (completed > 0) {
                               log.debug("任务执行成功:{}", message);
                           } else {
                               log.debug("任务执行失败:{}", message);
                           }
                       }
                   } catch (Exception e) {
                       e.printStackTrace();
                       log.debug("任务出现异常:{},任务:{}", e.getMessage(), message);
                   } finally {
                       // 计数
                       countDownLatch.countDown();
                   }
                   log.debug("结束任务:{}", message);
               }));
   
               // 等待,给一个充裕的超时时间,防止无限等待，到达超时时间还没有处理完成则结束任务
               countDownLatch.await(timeout, TimeUnit.SECONDS);
               log.debug("任务完成...");
           } catch (InterruptedException e) {
               e.printStackTrace();
           }
       }
   }
   ```

### 10.4 课程发布

1. 添加消息SDK（xuecheng-plus-content-service）

   ```xml
   <!-- 本地消息SDK -->
   <dependency>
       <groupId>com.yu.xuecheng</groupId>
       <artifactId>xuecheng-plus-message</artifactId>
       <version>1.0-SNAPSHOT</version>
   </dependency>
   ```

2. 添加课程发布接口

   ```java
   @ApiOperation(value = "课程发布")
   @ResponseBody
   @PostMapping("/course/publish/{courseId}")
   public void publish(@PathVariable("courseId") Long courseId) {
       Long companyId = 1232141425L;
       coursePublishService.publish(companyId, courseId);
   }
   ```

   ```java
   /**
    * 课程发布
    */
   @Override
   public void publish(Long companyId, Long courseId) {
       // 1.查询课程预发布信息
       CoursePublishPre coursePublishPre = coursePublishPreMapper.selectById(courseId);
       if (Objects.isNull(coursePublishPre)) {
           throw new BusinessException("课程不存在");
       }
   
       // 2.判断课程是否审核通过,未通过不允许发布
       String status = coursePublishPre.getStatus();
       if (!Objects.equals("202004", status)) {
           throw new BusinessException("课程审核不通过,请联系管理员");
       }
   
       // 3.向课程发布表中写入数据,有则更新,没有则添加
       CoursePublish coursePublish = new CoursePublish();
       BeanUtils.copyProperties(coursePublishPre, coursePublish);
       CoursePublish publish = coursePublishMapper.selectById(courseId);
       if (Objects.isNull(publish)) {
           // 修改状态为已发布
           coursePublish.setStatus("202002");
           coursePublishMapper.insert(coursePublish);
       } else {
           coursePublishMapper.updateById(coursePublish);
       }
   
       // 4.修改课程基本信息表状态
       CourseBase courseBase = courseBaseService.getById(courseId);
       courseBase.setStatus("202002");
       courseBaseService.updateById(courseBase);
   
       // 5.向本地消息表写入数据
       mqMessageService.addMessage("course_publish", String.valueOf(courseId), null, null);
   
       // 6.删除课程预发布信息
       coursePublishPreMapper.deleteById(courseId);
   }
   ```

3. 课程发布定时任务

   ```java
   /**
    * 课程发布定时任务
    *
    * @author elonlo
    * @date 2024/1/24 20:50
    */
   @Slf4j
   @Component
   public class CoursePublishJobHandler extends MessageProcessAbstract {
   
       /**
        * 课程发布任务
        */
       @Override
       public boolean execute(MqMessage mqMessage) {
           // 1、获取课程id
           long courseId = Long.parseLong(mqMessage.getBusinessKey1());
   
           // 2、生成课程静态化页面上传至文件系统
           generateCourseHtml(mqMessage, courseId);
   
           // 3、向elasticsearch写如课程索引数据
           writeCourseESData(mqMessage, courseId);
   
           // 4、将课程数据写入缓存中
           writeCacheData(mqMessage, courseId);
   
           // 5、上传都完成则发布任务成功
           return true;
       }
   
   
       /**
        * 生成课程静态化页面上传至文件系统
        */
       private void generateCourseHtml(MqMessage mqMessage, long courseId) {
           // 获取消息id
           String messageId = mqMessage.getId();
   
           IMqMessageService mqMessageService = this.getMqMessageService();
   
           // TODO 任务幂等性处理
   
           // 第一阶段任务是否完成 & 直接返回,否则继续完成第一阶段任务
           int stageOne = mqMessageService.getStageOne(Long.parseLong(messageId));
   
           if (stageOne > 0) {
               log.debug("课程静态化任务已经完成,无需处理......");
               return;
           }
   
           mqMessageService.completedStageOne(Long.parseLong(messageId));
       }
   
       /**
        * 向elasticsearch写入课程索引数据
        */
       private void writeCourseESData(MqMessage mqMessage, long courseId) {
           // 获取消息id
           String messageId = mqMessage.getId();
   
           IMqMessageService mqMessageService = this.getMqMessageService();
   
           // TODO 任务幂等性处理
   
           // 第二阶段任务是否完成 & 直接返回,否则继续完成第二阶段任务
           int stageTwo = mqMessageService.getStageTwo(Long.parseLong(messageId));
   
           if (stageTwo > 0) {
               log.debug("向elasticsearch写入课程索引数据任务已经完成,无需处理......");
               return;
           }
   
           mqMessageService.completedStageTwo(Long.parseLong(messageId));
       }
   
       /**
        * 将课程数据写入缓存中
        */
       private void writeCacheData(MqMessage mqMessage, long courseId) {
           // 获取消息id
           String messageId = mqMessage.getId();
   
           IMqMessageService mqMessageService = this.getMqMessageService();
   
           // TODO 任务幂等性处理
   
           // 第三阶段任务是否完成 & 直接返回,否则继续完成第三阶段任务
           int stageThree = mqMessageService.getStageThree(Long.parseLong(messageId));
   
           if (stageThree > 0) {
               log.debug("课程数据写入缓存任务已经完成,无需处理......");
               return;
           }
   
           mqMessageService.completedStageThree(Long.parseLong(messageId));
       }
   }
   ```
   
4. 注释掉 `xuecheng-plus-content-service` 模块的`MybatisPlusConfig`，否则会冲突

### 10.5 页面静态化

1. 添加依赖（xuecheng-plus-content-service）

   ```xml
   <!-- freemarker 模板引擎依赖 -->
   <dependency>
       <groupId>org.springframework.boot</groupId>
       <artifactId>spring-boot-starter-freemarker</artifactId>
   </dependency>
   ```

2. 添加页面静态化工具类

   ```java
   /**
    * 模板页面工具类
    *
    * @author elonlo
    * @date 2024/1/28 14:31
    */
   public class TemplatePageUtils {
   
       /**
        * 生成模板文件
        *
        * @param coursePreviewVO 课程预览数据
        * @return {@link File}
        */
       public static File generateTemplate(CoursePreviewVO coursePreviewVO) {
           Configuration configuration = new Configuration(Configuration.getVersion());
   
           SpringTemplateLoader templateLoader = new SpringTemplateLoader(resourceLoader, "classpath:templates");
   
           // 指定模板的目录
           File templateFile = null;
           try {
               configuration.setTemplateLoader(templateLoader);
   
               //指定编码
               configuration.setDefaultEncoding("utf-8");
   
               Map<String, Object> map = new HashMap<>(16);
               map.put("model", coursePreviewVO);
   
               Template template = configuration.getTemplate("courseTemplate.ftl");
   
               // Template template 模板, Object model 数据
               String html = FreeMarkerTemplateUtils.processTemplateIntoString(template, map);
   
               // 输入流
               InputStream inputStream = IOUtils.toInputStream(html, "utf-8");
   
               // 输出文件
               Long id = coursePreviewVO.getCourseBase().getId();
               templateFile = new File("d:/home/" + id + ".html");
               FileOutputStream outputStream = new FileOutputStream(templateFile);
   
               // 使用流将html写入文件
               IOUtils.copy(inputStream, outputStream);
           } catch (IOException | TemplateException e) {
               e.printStackTrace();
           }
           return templateFile;
       }
   }
   ```

3. 添加远程调用依赖

   ```xml
   <properties>
       <feign-form.version>3.8.0</feign-form.version>
   </properties>
   
   <!-- 服务发现 -->
   <dependency>
       <groupId>com.alibaba.cloud</groupId>
       <artifactId>spring-cloud-starter-alibaba-nacos-discovery</artifactId>
   </dependency>
   
   <!-- 微服务远程调用 -->
   <dependency>
       <groupId>org.springframework.cloud</groupId>
       <artifactId>spring-cloud-starter-openfeign</artifactId>
   </dependency>
   
   <dependency>
       <groupId>io.github.openfeign</groupId>
       <artifactId>feign-httpclient</artifactId>
   </dependency>
   
   <!-- feign支持multipart格式传参 -->
   <dependency>
       <groupId>io.github.openfeign.form</groupId>
       <artifactId>feign-form</artifactId>
       <version>${feign-form.version}</version>
   </dependency>
   
   <dependency>
       <groupId>io.github.openfeign.form</groupId>
       <artifactId>feign-form-spring</artifactId>
       <version>${feign-form.version}</version>
   </dependency>
   ```

4. `Nacos` 配置 `feign` 熔断、降级公共配置

   ```yaml
   feign:
     hystrix:
       enabled: true
     circuitbreaker:
       enabled: true
   hystrix:
     command:
       default:
         execution:
           isolation:
             thread:
               # 熔断超时时间
               timeoutInMilliseconds: 30000
   ribbion:
     # 连接超时时间
     ConnectTimeout: 60000
     # 读超时时间 
     ReadTimeout: 60000
     # 重试次数
     MaxAutoRetries: 0
     # 切换实例的重试次数
     MaxAutoRetriesNextServer: 1
   ```

5. `xuecheng-plus-content-service` 模块引入 `feign` 公共配置

   ```yaml
   spring:
     application:
       name: content-service
     cloud:
       nacos:
         server-addr: ip:port
         # 配置中心配置
         config:
           namespace: dev
           group: xuecheng-plus
           file-extension: yaml
           refresh-enabled: true
           # 读取公共配置
           shared-configs:
               - data-id: logging-${spring.profiles.active}.yaml
                 group: xuecheng-plus-common
                 refresh: true
               - data-id: feign-${spring.profiles.active}.yaml
                 group: xuecheng-plus-common
                 refresh: true
   ```

6. 添加 `Multipart` 配置类（xuecheng-plus-content-service）

   ```java
   /**
    * MultiPart支持配置
    *
    * @author elonlo
    * @date 2024/1/28 15:21
    */
   @Configuration
   public class MultipartSupportConfig {
   
       @Autowired
       private ObjectFactory<HttpMessageConverters> messageConverters;
   
       /**
        * '@Primary' 注入相同类型的bean时优先使用
        */
       @Bean
       @Primary
       @Scope("prototype")
       public Encoder feignEncoder() {
           return new SpringFormEncoder(new SpringEncoder(messageConverters));
       }
   }
   ```

7. 添加 `multipart` 文件处理工具类方法（xuecheng-plus-base）

   ```xml
   <properties>
       <commons-fileupload.version>1.4</commons-fileupload.version>
   </properties>
   
   <dependency>
       <groupId>commons-fileupload</groupId>
       <artifactId>commons-fileupload</artifactId>
       <version>${commons-fileupload.version}</version>
   </dependency>
   ```

   ```java
   public class FileUtil {
   
       /**
        * file转为MultipartFile
        */
       public static MultipartFile getMultipartFile(File file) {
           FileItem item = new DiskFileItemFactory()
                   .createItem("file", MediaType.MULTIPART_FORM_DATA_VALUE, true, file.getName());
           try (FileInputStream inputStream = new FileInputStream(file);
                OutputStream outputStream = item.getOutputStream()) {
               IOUtils.copy(inputStream, outputStream);
           } catch (Exception e) {
               e.printStackTrace();
           }
           return new CommonsMultipartFile(item);
       }
   }
   ```

8. 扩展 `xuecheng-plus-media-service` 模块上传文件到 `minio` 接口

   ```java
   @RestController
   public class MediaController {
   
       private final IMediaFilesService mediaFilesService;
   
       public MediaController(IMediaFilesService mediaFilesService) {
           this.mediaFilesService = mediaFilesService;
       }
   
       @ApiOperation(value = "文件上传")
       @PostMapping("/upload/course")
       public ResultResponse<UploadFileVO> uploadFile(@RequestPart("file") MultipartFile file,
                                                      @RequestParam(value = "objectName", required = false) String objectName) {
           UploadFileVO uploadFileVO = mediaFilesService.uploadFile(file, objectName);
           return ResultResponse.success(uploadFileVO);
       }
   }
   ```

   ```java
   /**
    * 上传文件
    *
    * @param file       文件
    * @param objectName 对象名称,不传则根据规则生成
    * @return {@link UploadFileVO}
    */
   UploadFileVO uploadFile(MultipartFile file, String objectName);
   ```

   ```java
   /**
    * 上传文件
    */
   @Override
   public UploadFileVO uploadFile(MultipartFile file, String objectName) {
       Long companyId = 12322332L;
   
       // 1.通过file构建UploadFileDTO参数
       UploadFileDTO dto = new UploadFileDTO();
       // 文件名称
       dto.setFilename(file.getOriginalFilename());
       // 文件大小
       dto.setFileSize(file.getSize());
       // 文件类型
       dto.setFileType("001001");
   
       // 2.通过file构建localFilePath
       String localFilePath = this.getLocalFilePath(file);
   
       // 3.上传文件到minio
       String fileName = dto.getFilename();
       // 获取文件扩展名
       String extensionName = fileName.substring(fileName.lastIndexOf("."));
   
       // 获取文件类型
       String mimeType = FileUtil.getMimeType(extensionName);
   
       // 获取文件的md5
       String fileMd5 = this.calculateFileMd5(new File(localFilePath));
   
       String defaultFolderPath = this.getDefaultFolderPath();
   
       // 对象名称为空时按规则生成
       if (StringUtils.isEmpty(objectName)) {
           objectName = defaultFolderPath + fileMd5 + extensionName;
       }
   
       boolean uploadFlag = this.uploadMinio(minioProperties.getBucket().getFiles(), localFilePath, mimeType, objectName);
       if (!uploadFlag) {
           throw new BusinessException("文件上传失败");
       }
   
       // 4.保存文件信息到数据库
       MediaFiles mediaFiles = mediaFilesService.saveFile(companyId, fileMd5, dto, minioProperties.getBucket().getFiles(), objectName);
       if (Objects.isNull(mediaFiles)) {
           throw new BusinessException("保存文件信息到数据库失败");
       }
   
       return CopyUtils.copy(mediaFiles, UploadFileVO.class);
   }
   ```

9. 远程调用上传接口

   ```java
   /**
    * 媒资服务远程调用
    *
    * @author elonlo
    * @date 2024/1/29 21:35
    */
   @FeignClient(value = "media-api", configuration = {MultipartSupportConfig.class})
   public interface MediaServiceClient {
   
       /**
        * 文件上传
        */
       @PostMapping(value = "/media/upload/course", produces = "application/json;charset=UTF-8",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
       String uploadFile(@RequestPart("file") MultipartFile file,
                         @RequestParam(value = "objectName", required = false) String objectName);
   }
   ```

   ```java
   /**
    * 内容管理系统接口启动类
    *
    * @author elonlo
    * @date 2023/9/16 23:24
    */
   @EnableFeignClients(basePackages = "com.yu.xuecheng.content.feign")
   @SpringBootApplication
   public class ContentApiApplication {
   
       public static void main(String[] args) {
           SpringApplication.run(ContentApiApplication.class, args);
       }
   }
   ```

10. 生成消息实体类（xuecheng-plus-content-model）

   ```java
   @Data
   @EqualsAndHashCode(callSuper = false)
   @Accessors(chain = true)
   @TableName("mq_message")
   public class MqMessage implements Serializable {
   
       private static final long serialVersionUID = 1L;
   
       /**
        * 消息id
        */
       @TableId(value = "id", type = IdType.AUTO)
       private Long id;
   
       /**
        * 消息类型代码: course_publish ,  media_test
        */
       private String messageType;
   
       /**
        * 关联业务信息
        */
       private String businessKey1;
   
       /**
        * 关联业务信息
        */
       private String businessKey2;
   
       /**
        * 关联业务信息
        */
       private String businessKey3;
   
       /**
        * 通知次数
        */
       private Integer executeNum;
   
       /**
        * 处理状态，0:初始，1:成功
        */
       private String state;
   
       /**
        * 回复失败时间
        */
       private LocalDateTime returnfailureDate;
   
       /**
        * 回复成功时间
        */
       private LocalDateTime returnsuccessDate;
   
       /**
        * 回复失败内容
        */
       private String returnfailureMsg;
   
       /**
        * 最近通知时间
        */
       private LocalDateTime executeDate;
   
       /**
        * 阶段1处理状态, 0:初始，1:成功
        */
       private String stageState1;
   
       /**
        * 阶段2处理状态, 0:初始，1:成功
        */
       private String stageState2;
   
       /**
        * 阶段3处理状态, 0:初始，1:成功
        */
       private String stageState3;
   
       /**
        * 阶段4处理状态, 0:初始，1:成功
        */
       private String stageState4;
   }
   ```

   ```java
   @Data
   @EqualsAndHashCode(callSuper = false)
   @Accessors(chain = true)
   @TableName("mq_message_history")
   public class MqMessageHistory implements Serializable {
   
       private static final long serialVersionUID = 1L;
   
       /**
        * 消息id
        */
       @TableId(value = "id", type = IdType.AUTO)
       private Long id;
   
       /**
        * 消息类型代码
        */
       private String messageType;
   
       /**
        * 关联业务信息
        */
       private String businessKey1;
   
       /**
        * 关联业务信息
        */
       private String businessKey2;
   
       /**
        * 关联业务信息
        */
       private String businessKey3;
   
       /**
        * 通知次数
        */
       private Integer executeNum;
   
       /**
        * 处理状态，0:初始，1:成功，2:失败
        */
       private Integer state;
   
       /**
        * 回复失败时间
        */
       private LocalDateTime returnfailureDate;
   
       /**
        * 回复成功时间
        */
       private LocalDateTime returnsuccessDate;
   
       /**
        * 回复失败内容
        */
       private String returnfailureMsg;
   
       /**
        * 最近通知时间
        */
       private LocalDateTime executeDate;
   
       private String stageState1;
   
       private String stageState2;
   
       private String stageState3;
   
       private String stageState4;
   }
   ```

   注意：`mq_message_history`需要添加自增，否则添加数据行时会提示 `id` 不能为空

11. 生成课程静态化页面上传至文件系统

    ```java
    /**
     * 生成课程静态化页面上传至文件系统
     */
    private void generateCourseHtml(MqMessage mqMessage, long courseId) {
        // 获取消息id
        String messageId = mqMessage.getId();
    
        IMqMessageService mqMessageService = this.getMqMessageService();
    
        // 生成课程静态化页面
        File courseHtml = coursePublishService.generateCourseHtml(courseId);
        if (Objects.isNull(courseHtml)) {
            throw new BusinessException("生成课程静态化页面为空");
        }
    
        // 上传文件
        coursePublishService.uploadCourseHtml(courseId, courseHtml);
    
        // TODO 任务幂等性处理
    
        // 第一阶段任务是否完成 & 直接返回,否则继续完成第一阶段任务
        int stageOne = mqMessageService.getStageOne(Long.parseLong(messageId));
    
        if (stageOne > 0) {
            log.debug("课程静态化任务已经完成,无需处理......");
            return;
        }
    
        mqMessageService.completedStageOne(Long.parseLong(messageId));
    }
    ```

12. 生成课程静态化页面

    ```java
    @Slf4j
    @Service
    public class CoursePublishServiceImpl extends ServiceImpl<CoursePublishMapper, CoursePublish> implements ICoursePublishService {
        
        private final MediaServiceClient mediaServiceClient;
    
        private final ResourceLoader resourceLoader;
    
        public CoursePublishServiceImpl(MediaServiceClient mediaServiceClient, ResourceLoader resourceLoader) {
            this.mediaServiceClient = mediaServiceClient;
            this.resourceLoader = resourceLoader;
        }
        
        /**
         * 生成课程静态化页面
         */
        @Override
        public File generateCourseHtml(Long courseId) {
            Configuration configuration = new Configuration(Configuration.getVersion());
    
            SpringTemplateLoader templateLoader = new SpringTemplateLoader(resourceLoader, "classpath:templates");
    
            // 指定模板的目录
            File templateFile = null;
            try {
                configuration.setTemplateLoader(templateLoader);
    
                //指定编码
                configuration.setDefaultEncoding("utf-8");
    
                Map<String, Object> map = new HashMap<>(16);
                CoursePreviewVO coursePreviewVO = this.getCoursePreviewInfo(courseId);
                map.put("model", coursePreviewVO);
    
                Template template = configuration.getTemplate("courseTemplate.ftl");
    
                // Template template 模板, Object model 数据
                String html = FreeMarkerTemplateUtils.processTemplateIntoString(template, map);
    
                // 输入流
                InputStream inputStream = IOUtils.toInputStream(html, "utf-8");
    
                // 输出文件
                templateFile = new File("d:/home/" + courseId + ".html");
                FileOutputStream outputStream = new FileOutputStream(templateFile);
    
                // 使用流将html写入文件
                IOUtils.copy(inputStream, outputStream);
            } catch (IOException | TemplateException e) {
                log.error("生成课程静态化页面失败, courseId: {}, 异常信息: []", courseId, e);
                e.printStackTrace();
            }
            return templateFile;
        }  
    }
    ```

13. 上传课程静态化页面

    ```java
    @Slf4j
    @Service
    public class CoursePublishServiceImpl extends ServiceImpl<CoursePublishMapper, CoursePublish> implements ICoursePublishService {
    
        private final MediaServiceClient mediaServiceClient;
    
        private final ResourceLoader resourceLoader;
    
        public CoursePublishServiceImpl(MediaServiceClient mediaServiceClient, ResourceLoader resourceLoader) {
            this.mediaServiceClient = mediaServiceClient;
            this.resourceLoader = resourceLoader;
        }
        
        /**
         * 上传课程静态化页面
         */
        @Override
        public void uploadCourseHtml(Long courseId, File file) {
            // 文件格式转化
            MultipartFile multipartFile = FileUtil.getMultipartFile(file);
    
            String objectName = "course/" + courseId + ".html";
    
            try {
                String uploadFile = mediaServiceClient.uploadFile(multipartFile, objectName);
                if (StringUtils.isEmpty(uploadFile)) {
                    log.debug("远程调用媒资文件上传失败,服务降级,courseId: {}", courseId);
                    throw new BusinessException("上传课程静态化页面失败");
                }
            } catch (Exception e) {
                log.error("上传课程静态化页面失败, courseId: {}, 异常信息: []", courseId, e);
                e.printStackTrace();
                throw new BusinessException("上传课程静态化页面失败");
            }
        }
    }
    ```

### 10.6 熔断降级

**什么是熔断降级？**

微服务之间难免会存在服务之间的调用，比如服务 A 调用服务 B 的接口，当微服务运行不正常会导致无法正常调用微服务，此时会出现异常，如果不进行处理可能会导致雪崩效应。

微服务之间的雪崩效应体现在服务与服务之间调用，当其中一个服务无法提供服务时，可能导致其他服务也死掉，比如服务 B 调用服务 A，由于 A 服务异常导致 B 服务响应缓慢，最后 B、C 等服务都不可用，像这样由一个服务引起的一连串的多个服务无法提供服务的现象就是微服务的雪崩效应。

**熔断和降级的区别**

**熔断**和**降级**的相同点都是为了解决微服务系统崩溃的问题，但它们是两个不同的技术手段，两者又存在联系。

熔断：当下游服务异常而断开与上游服务的交互，下游服务异常触发了熔断，从而保证上游服务不受影响。

降级：当下游服务异常触发熔断后，上游服务就不再去调用异常的微服务，而是执行了降级逻辑处理，这个降级逻辑处理可以是本地的单独的一个方法。

#### 10.6.1 定义回调类实现远程调用接口

```java
@FeignClient(value = "media-api", configuration = {MultipartSupportConfig.class}, fallback = MediaServiceClientFallback.class)
public interface MediaServiceClient {

    /**
     * 文件上传
     */
    @PostMapping(value = "/media/upload/course", produces = "application/json;charset=UTF-8",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    String uploadFile(@RequestPart("file") MultipartFile file,
                      @RequestParam(value = "objectName", required = false) String objectName);
}
```

```java
/**
 * 媒资服务远程调用失败回调类
 *
 * @author elonlo
 * @date 2024/1/30 20:03
 */
@Slf4j
@Component
public class MediaServiceClientFallback implements MediaServiceClient {

    @Override
    public String uploadFile(MultipartFile file, String objectName) {
        log.debug("媒资服务上传文件接口远程调用异常, 参数信息, objectName: {}", objectName);
        return null;
    }
}
```

#### 10.6.2 定义回调类实现回调工厂接口

```java
@FeignClient(value = "media-api", configuration = {MultipartSupportConfig.class}, fallbackFactory = MediaServiceClientFallbackFactory.class)
public interface MediaServiceClient {

    /**
     * 文件上传
     */
    @PostMapping(value = "/media/upload/course", produces = "application/json;charset=UTF-8",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    String uploadFile(@RequestPart("file") MultipartFile file,
                      @RequestParam(value = "objectName", required = false) String objectName);
}
```

```java
/**
 * 媒资服务远程调用回调工厂类
 *
 * @author elonlo
 * @date 2024/1/30 20:10
 */
@Slf4j
@Component
public class MediaServiceClientFallbackFactory implements FallbackFactory<MediaServiceClient> {

    @Override
    public MediaServiceClient create(Throwable cause) {
        log.debug("媒资服务上传文件接口远程调用异常, 异常信息: []", cause);
        return (file, objectName) -> null;
    }
}
```

### 10.7 xxl-job 配置

```java
@Data
@Component
@ConfigurationProperties(prefix = XxlJobProperties.XXL_JOB_PREFIX)
public class XxlJobProperties {

    /**
     * xxl-job属性前缀
     */
    public static final String XXL_JOB_PREFIX = "xxl.job";

    /**
     * 调度中心配置
     */
    private Admin admin;

    /**
     * 执行器配置
     */
    private Executor executor;

    @Data
    public static class Admin {

        /**
         * 调度中心部署根地址 [选填]：如调度中心集群部署存在多个地址则用逗号分隔。执行器将会使用该地址进行"执行器心跳注册"和"任务结果回调";为空则关闭自动注册
         */
        private String address;
    }

    @Data
    public static class Executor {

        /**
         * 执行器AppName [选填]：执行器心跳注册分组依据；为空则关闭自动注册
         */
        private String appName;

        /**
         * 执行器注册 [选填]：优先使用该配置作为注册地址，为空时使用内嵌服务 ”IP:PORT“ 作为注册地址。从而更灵活的支持容器类型执行器动态IP和动态映射端口问题
         */
        private String address;

        /**
         * 执行器IP [选填]：默认为空表示自动获取IP，多网卡时可手动设置指定IP，该IP不会绑定Host仅作为通讯实用；地址信息用于 "执行器注册" 和 "调度中心请求并触发任务"
         */
        private String ip;

        /**
         * 执行器端口号 [选填]：小于等于0则自动获取；默认端口为9999，单机部署多个执行器时，注意要配置不同执行器端口
         */
        private int port;

        /**
         * 执行器运行日志文件存储磁盘路径 [选填] ：需要对该路径拥有读写权限；为空则使用默认路径
         */
        private String logPath;

        /**
         * 执行器日志文件保存天数 [选填] ： 过期日志自动清理, 限制值大于等于3时生效; 否则, 如-1, 关闭自动清理功能
         */
        private int logRetentionDays;
    }

    /**
     * 执行器通讯TOKEN [选填]：非空时启用
     */
    private String accessToken;
}
```

```java
@Configuration
public class XxlJobConfig {

    public static final Logger logger = LoggerFactory.getLogger(XxlJobConfig.class);

    @Resource
    private XxlJobProperties xxlJobProperties;

    @Bean
    public XxlJobSpringExecutor xxlJobExecutor() {
        logger.info(">>>>>>>>>>> xxl-job config init.");
        XxlJobSpringExecutor xxlJobSpringExecutor = new XxlJobSpringExecutor();
        xxlJobSpringExecutor.setAdminAddresses(xxlJobProperties.getAdmin().getAddress());
        xxlJobSpringExecutor.setAppname(xxlJobProperties.getExecutor().getAppName());
        xxlJobSpringExecutor.setAddress(xxlJobProperties.getExecutor().getAddress());
        xxlJobSpringExecutor.setIp(xxlJobProperties.getExecutor().getIp());
        xxlJobSpringExecutor.setPort(xxlJobProperties.getExecutor().getPort());
        xxlJobSpringExecutor.setAccessToken(xxlJobProperties.getAccessToken());
        xxlJobSpringExecutor.setLogPath(xxlJobProperties.getExecutor().getLogPath());
        xxlJobSpringExecutor.setLogRetentionDays(xxlJobProperties.getExecutor().getLogRetentionDays());
        return xxlJobSpringExecutor;
    }
}
```

## 11、课程搜索

### 11.1 创建课程搜索模块

1. 添加依赖
2. 添加本地配置
3. 添加 `Nacos` 配置





 

