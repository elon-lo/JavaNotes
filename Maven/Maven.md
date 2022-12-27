# Maven

## 一、Maven简介

`Maven`是Apache软件基金会组织维护的一款专门为Java项目提供`构建`和`依赖管理`支持的工具

### 1.1、Maven的目标

Maven 的主要目标是让开发人员能够在最短的时间内了解开发工作的完整状态。为了实现这一目标，Maven 处理了几个关注领域

- 简化构建过程
- 提供统一的构建系统
- 提供优质的项目信息
- 鼓励更好的发展实践

### 1.2、构建

Java项目开发过程中，构建指的是使用***原材料生产产品***的过程

- 原材料
  - Java源代码
  - 静态资源文件(html，css，js等)
  - 配置文件
- 产品
  - 一个可以在服务器上运行的项目

构建过程包含的主要的环节：

- (Clean)清理：删除上一次构建的结果，为下一次构建做好准备
- (Build)编译：Java源程序编译成.class字节码文件
- (Test)测试：运行提前准备好的测试程序
- (Report)报告：针对刚才测试的结果生成一个全面的信息
- (Package)打包
  - Java工程：jar包
  - Web工程：war包
- (Install)安装：把一个Maven工程经过打包操作生成的jar包或war包安装到Maven仓库
- (Deploy)部署
  - 部署jar包：把一个jar包部署到Nexus私服服务器上
  - 部署war包：借助相关Maven插件(例如cargo)，将war包部署到Tomcat服务器上

### 1.3、依赖

如果A工程里面用到了B工程的类、接口、配置文件等等这样的资源，那么我们就可以说A依赖B。例如：

- junit-4.2依赖hamcrest-core-1.3
- thymeleaf-3.0.12.RELEASE依赖ognl-3.1.26
  - ognl-3.1.26依赖javassist-3.20.0-GA
- thymeleaf-3.0.12.RELEASE依赖attoparser-2.0.5.RELEASE
- thymeleaf-3.0.12.RELEASE依赖slf4j-api-1.7.26

依赖管理中要解决的具体问题：

- jar包的下载：使用Maven之后，jar包会从规范的远程仓库下载到本地
- jar包之间的依赖：通过依赖的传递性自动完成
- jar包之间的冲突：通过对依赖的配置进行调整，让某些jar包不会被导入

### 1.4、Maven的工作机制

TODO

## 二、Maven安装与配置

官方网站：https://maven.apache.org/

Maven的所有版本：https://dlcdn.apache.org/maven/

### 2.1、Windows配置

Windows版本：Windows 10 专业版21H2

- 下载Maven安装包程序

  ![image-20221109235545421](https://cdn.jsdelivr.net/gh/elon-lo/PicGo-Image@master/img/image-20221109235545421.png)

  ![image-20221109235932330](https://cdn.jsdelivr.net/gh/elon-lo/PicGo-Image@master/img/image-20221109235932330.png)

- 解压至指定目录

  ![image-20221110000052394](https://cdn.jsdelivr.net/gh/elon-lo/PicGo-Image@master/img/image-20221110000052394.png)

- 配置环境变量

  ![image-20221110000302259](https://cdn.jsdelivr.net/gh/elon-lo/PicGo-Image@master/img/image-20221110000302259.png)

  ![image-20221110001153652](https://cdn.jsdelivr.net/gh/elon-lo/PicGo-Image@master/img/image-20221110001153652.png)

  ![image-20221110001255225](https://cdn.jsdelivr.net/gh/elon-lo/PicGo-Image@master/img/image-20221110001255225.png)

- 指定本地仓库

  在maven安装目录下有一个核心配置文件`setting.xml`。

  默认本地仓库：在用户目录下的/.m2/repository，由于本地仓库的默认位置是在用户目录下，而用户目录往往是在C盘，也就是系统盘，将来maven仓库中的jar包越来越多，可能会拖慢C盘运行速度，影响系统性能，所以建议将maven的本地仓库修改在其他盘符下，配置方式如下：

  ```xml
  <!-- localRepository
     | The path to the local repository maven will use to store artifacts.
     |
     | Default: ${user.home}/.m2/repository
    <localRepository>/path/to/local/repo</localRepository>
  -->
  <localRepository>D:\DevSoftware\Java\Maven\apache-maven-3.6.3\repository</localRepository>
  ```

- 配置国内镜像仓库

  maven下载jar包默认访问国外的中央仓库，由于访问国外网站速度较慢，所以建议改成国内阿里云提供的镜像仓库，可以让maven下载jar包的时候速度更快，配置的方式是：将下面的mirror标签整体复制到setting.xml文件的mirrors标签的内部，配置如下：

  ```xml
  <mirror>  
      <id>alimaven</id>  
      <name>aliyun maven</name>  
      <url>https://maven.aliyun.com/nexus/content/groups/public/</url>  
      <mirrorOf>central</mirrorOf>          
  </mirror>
  ```

- 配置maven工程的基础JDK版本

  如果按照默认配置运行，Java工程使用的默认JDK版本是1.5，而我们通常使用的是JDK1.8，修改配置如下：

  ```xml
  <profiles>
  	<profile>
  		<id>jdk-1.8</id>
  		<activation>
  			<activeByDefault>true</activeByDefault>
          <jdk>1.8</jdk>
  		</activation>
  		<properties>
  			<maven.compiler.source>1.8</maven.compiler.source>
  			<maven.compiler.target>1.8</maven.compiler.target>
  			<maven.compiler.compilerVersion>1.8</maven.compiler.compilerVersion>
  		</properties>
  	</profile>
  </profiles>
  ```

## 三、Maven入门

### 3.1、根据坐标创建Maven工程

#### 3.1.1、Maven的坐标

**使用三个向量在Maven的仓库中的唯一定位到一个jar包**

- groupId：公司或组织的id
- artifactId：一个项目或者是项目中的一个模块的id
- version：版本号

**三个向量的取值方式**

- groupId：公司或组织域名的倒序，通常也会加上项目名称
  - 例如：com.huawei.maven
- artifactId：模块的名称，将来作为Maven工程的工程名
- version：模块的版本号，根据自己需要设定
  - SNAPSHOT：表示快照版本，正在迭代过程中，不稳定的版本
  - RELEASE：正式版本

**坐标和仓库中的jar包存储路径之间的对应关系**

```xml
<groupId>javax.servlet</groupId>
<artifactId>servlet-api</artifactId>
<version>2.5</version>
```

上面坐标对应的jar包位置为Maven本地仓库中的**/javax/servlet/servlet-api/2.5/servlet-api-2.5.jar**

**使用Maven命令创建工程**

- 命令行输入`mvn archetype:generate`并回车

  ![image-20221201224119613](https://typora-yu-pic.oss-cn-chengdu.aliyuncs.com/picBed/image-20221201224119613.png)
  
- 选择`maven-archetype-quickstart`

  ![image-20221201224636360](https://typora-yu-pic.oss-cn-chengdu.aliyuncs.com/picBed/image-20221201224636360.png)

- 填写`groupId`和`artifictId`

  ![image-20221201225323380](https://typora-yu-pic.oss-cn-chengdu.aliyuncs.com/picBed/image-20221201225323380.png)

**修改POM文件**

Maven默认生成的工程，junit依赖是较低的3.8.1版本，建议修改为4.12版本，自动生成的App.java和AppTest.java可以删除。

```xml
<!-- 依赖信息配置 -->
<!-- dependencies标签: 里面包含dependency标签-->
<dependencies>
    <!-- dependency标签: 配置一个具体的依赖 -->
    <dependency>
        <!-- 通过坐标来依赖其他jar包 -->
        <groupId>junit</groupId>
        <artifactId>junit</artifactId>
        <version>4.12</version>
        <!-- 依赖的范围 -->
        <scope>test</scope>
    </dependency>
</dependencies>
```

```xml
<!-- project标签: 表示对当前工程进行配置、管理 -->
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <!-- modelVersion标签: 代表当前pom.xml所采用标签结构，从Maven2开始就固定是4.0.0 -->
    <modelVersion>4.0.0</modelVersion>
    
    <!-- groupId标签: 代表公司或组织开发的某一个项目，一般为域名倒序，如com.huawei对应huawei.com -->
    <groupId>com.huawei.maven</groupId>
    
    <!-- artifactId标签: 代表项目下的某一个模块 -->
    <artifactId>first-maven-java</artifactId>
    
    <!-- version标签: 代表当前模块的版本 -->
    <version>1.0-SNAPSHOT</version>
    
    <!-- packaging标签: 代表当前项目的打包方式，默认是jar
        jar: 表示这是一个Java工程
        war: 表示这是一个web工程
        pom: 表示这个工程用来管理其它工程
 	-->
    <packaging>jar</packaging>
    
    <!-- name标签: 当前项目名称 -->
    <name>first-maven-java</name>
    
    <!-- url标签: Maven官网地址 -->
    <url>http://maven.apache.org</url>
    
    <!-- properties标签: 用来定义当前项目的属性 -->
    <properties>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
    </properties>
    
    <!-- dependencies标签: 配置依赖信息，可以包含多个dependency标签 -->
    <dependencies>
        <!-- dependency标签: 配置具体的依赖信息 -->
        <dependency>
            <!-- 通过坐标来依赖其他jar包 -->
            <groupId>junit</groupId>
            <artifactId>junit</artifactId>
            <version>4.12</version>
            <!-- scope标签: 配置当前依赖的范围 -->
            <scope>test</scope>
        </dependency>
    </dependencies>
</project>
```

#### 3.1.2、Maven的POM

**定义**

POM：**P**roject **O**bject **M**odel(项目对象模型)。和POM类似的是：DOM(Document Object Model)，文档对象模型。他们都是模型化思想的具体体现。

**模型化思想**

POM将工程抽象成一个模型，再用程序中的对象来描述这个模型。这样就可以用程序来管理项目了。在开发过程中，最基本的做法就是将现实生活中的事物抽象成模型，然后封装模型相关的数据为一个对象，这样就可以在程序中计算与现实事物中相关的数据。

**配置文件**

POM理念集中体现在Maven工程根目录下`pom.xml`这个配置文件中，这个配置文件时Maven工程的核心配置文件。

#### 3.1.3、Maven约定的目录结构

**各个目录的作用**

| 目录      | 含义                   |
| --------- | ---------------------- |
| src       | 源码目录               |
| main      | 主程序目录             |
| java      | Java源代码             |
| com       | package目录            |
| resources | 配置文件目录           |
| test      | 测试程序目录           |
| java      | Java测试源代码         |
| com       | 测试package目录        |
| target    | 存放构建操作输出的结果 |

#### 3.1.4、约定目录的意义

Maven为了让构建过程能够尽可能自动化完成，所以必须约定目录结构的作用。例如：Maven执行编译操作，必须先去Java源程序目录读取Java源代码，然后执行编译，最后将编译结果存放在target目录。

#### 3.1.5、约定大于配置

Maven对于目录结构这个问题，没有采用配置的方式，而是基于约定。这样会让我们在开发过程中非常方便。目前开发领域的技术发展趋势就是：约定大于配置，配置大于编码。

### 3.2、Maven相关命令

#### 3.2.1、清理(clean)

命令：mvn clean

作用：删除target目录

#### 3.2.2、编译(compile)

主程序编译：mvn compile

测试程序编译：mvn test-compile

主程序编译结果存放的目录：target/classes

测试程序编译结果存放的目录：target/test-classes

#### 3.2.3、测试(test)

命令：mvn test

测试报告存放目录：target/surefire-reports

#### 3.2.4、打包(package)

命令：mvn package

打包结果存放目录：target

#### 3.2.5、安装(install)

> 安装是将本地构建过程中生成的jar包存入Maven本地仓库。这个jar包在Maven仓库中的路径是根据它的坐标生成的

命令：mvn install

### 3.3、依赖的范围

`scope`的默认依赖范围是`compile`

标签所在位置：Dependencies→Dependency→scope

标签可选值：compile/test/provided/system/runtime/import

**compile和test对比**

|  scope  | main目录 | test目录 | 服务器 |
| :-----: | :------: | :------: | :----: |
| compile |   有效   |   有效   |  有效  |
|  test   |   无效   |   有效   |  无效  |

**compile和provided对比**

|  scope   | main目录 | test目录 | 服务器 |
| :------: | :------: | :------: | :----: |
| compile  |   有效   |   有效   |  有效  |
| provided |   有效   |   有效   |  无效  |

**依赖的传递性**

在A依赖B，B依赖C的前提下，C是否能够传递到A，取决于B依赖C时使用的依赖范围。

- B依赖C时使用compile范围：可以传递
- B依赖C时使用test或provided范围：不能传递，所以需要这样的依赖时，必须在需要的地方明确配置依赖才可以

### 3.4、依赖的排除

**概念**

当A依赖B，B依赖C且C可以传递到A的时候，A不想要C，需要在A里面把C排除掉。往往这种情况都是为了避免jar包之间的冲突。所以配置依赖的排除其实就是阻止某些jar包的传递。因为这样的jar传递过来会和其他jar冲突。

配置如下：

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
    <!-- 配置依赖的排除 -->
    <exclusions>
        <!-- 配置具体的排除信息 -->
        <exclusion>
            <!-- 指定坐标时不需要指定version -->
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-tomcat</artifactId>
        </exclusion>
    </exclusions>
</dependency>
```

### 3.5、继承

**概念**

Maven工程之间，A工程继承B工程

- B工程：父工程
- A工程：子工程

本质上是A工程的pom.xml中的配置继承了B工程中的pom.xml

**作用**

在父工程中统一管理项目中的依赖信息，具体来说是管理依赖信息的版本。

### 3.6、聚合

**概念**

使用一个`总工程`将各个`模块工程`汇聚起来，作为一个整体对应完整的项目

**优点**

一键执行Maven命令，很多构建命令都可以在`总工程`中一键执行。

以mvn install为例：Maven要求有父工程时先安装父工程；有依赖的工程时，先安装被依赖的工程。我们自己考虑这些规则会很麻烦。但是在工程聚合之后，在总工程执行mvn install可以一键完成安装，而且会按照正确的顺序执行。

配置聚合之后，各个模块工程会在总工程中展示一个列表，让项目中的各个模块一目了然。

**配置**

在总工程中配置modules即可：

```xml
<!-- 父工程打包方式必须设置为pom -->
<packaging>pom</packaging>
<modules>
    <module>hs-admin</module>
    <module>hs-framework</module>
    <module>hs-system</module>
    <module>hs-quartz</module>
    <module>hs-generator</module>
    <module>hs-common</module>
</modules>
```

**自定义属性**

```xml
<properties>
	    <mybatis-plus.version>3.4.2</mybatis-plus.version>
</properties>

<dependency>
    <groupId>com.baomidou</groupId>
    <artifactId>mybatis-plus-boot-starter</artifactId>
    <version>${mybatis-plus.version}</version>
</dependency>
```

## 四、IDEA配置Maven

环境如下：

IDEA：2021.3

jdk：1.8.0_291

maven：3.6.3

### 4.1、创建父工程

![image-20221220132705215](https://typora-yu-pic.oss-cn-chengdu.aliyuncs.com/picBed/image-20221220132705215.png)

![image-20221220132851067](https://typora-yu-pic.oss-cn-chengdu.aliyuncs.com/picBed/image-20221220132851067.png)

![image-20221220133041282](https://typora-yu-pic.oss-cn-chengdu.aliyuncs.com/picBed/image-20221220133041282.png)

### 4.2、配置Maven信息

File→Settings→Build, Execution, Deployment→Build Tools→Maven

![image-20221220142722411](https://typora-yu-pic.oss-cn-chengdu.aliyuncs.com/picBed/image-20221220142722411.png)

### 4.3、创建Java模块工程

- 在idea中创建Java工程

  ![image-20221220143018769](https://typora-yu-pic.oss-cn-chengdu.aliyuncs.com/picBed/image-20221220143018769.png)

  ![image-20221220143035061](https://typora-yu-pic.oss-cn-chengdu.aliyuncs.com/picBed/image-20221220143035061.png)

- idea中使用maven命令

  - 在idea中直接执行

    ![image-20221220145057889](https://typora-yu-pic.oss-cn-chengdu.aliyuncs.com/picBed/image-20221220145057889.png)

  - 在idea中手动输入命令执行

    ![image-20221220145509008](https://typora-yu-pic.oss-cn-chengdu.aliyuncs.com/picBed/image-20221220145509008.png)

  - 在idea中调用命令行执行

    ![image-20221220145617356](https://typora-yu-pic.oss-cn-chengdu.aliyuncs.com/picBed/image-20221220145617356.png)

### 4.4、创建Web模块工程

- 创建maven工程

  ![image-20221220150342671](https://typora-yu-pic.oss-cn-chengdu.aliyuncs.com/picBed/image-20221220150342671.png)

- 修改pom.xml文件

  ```xml
  <?xml version="1.0" encoding="UTF-8"?>
  <project xmlns="http://maven.apache.org/POM/4.0.0"
           xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
           xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
      <parent>
          <artifactId>demo-maven-parent</artifactId>
          <groupId>com.yu.maven</groupId>
          <version>1.0-SNAPSHOT</version>
      </parent>
      <modelVersion>4.0.0</modelVersion>
      <packaging>war</packaging>
  
      <artifactId>demo-maven-web</artifactId>
  
      <properties>
          <maven.compiler.source>8</maven.compiler.source>
          <maven.compiler.target>8</maven.compiler.target>
      </properties>
  
  </project>
  ```

- 添加webapp目录和web.xml文件

  File→Project Structure→Facets

  ![image-20221220150838091](https://typora-yu-pic.oss-cn-chengdu.aliyuncs.com/picBed/image-20221220150838091.png)

  ![image-20221220151655314](https://typora-yu-pic.oss-cn-chengdu.aliyuncs.com/picBed/image-20221220151655314.png)

  ![image-20221220151200009](https://typora-yu-pic.oss-cn-chengdu.aliyuncs.com/picBed/image-20221220151200009.png)

## 五、Maven的生命周期和插件

### 5.1、Maven生命周期

**作用**

为了让构建过程自动化完成，Maven设定了三个生命周期，生命周期中的每一个环节对应构建过程中的一个操作。

| 生命周期名称 | 作用         | 各个环节                                                     |
| ------------ | ------------ | ------------------------------------------------------------ |
| Clean        | 清理相关操作 | pre-clean<br />clean<br />post-clean                         |
| Site         | 生成站点相关 | pre-site<br />site<br />post-site<br />deploy-site           |
| Default      | 主要构建过程 | validate<br />generate-sources<br />process-sources<br />generate-resources<br />process-resources<br />compile<br />process-classes<br />generate-test-sources<br />process-test-sources<br />generate-test-resources<br />process-test-resources<br />test-compile<br />process-test-classes<br />test<br />prepare-package<br />package<br />pre-integration-test<br />integration-test<br />post-integration-test<br />verify<br />install<br />deploy |

### 5.2、Maven插件

**插件**

Maven的核心程序仅仅负责宏观调度，不做具体工作。具体工作都是由Maven插件完成的。例如：编译就是由maven-compiler-plugin-3.1.jar插件来完成的。

**目标**

一个插件可以对应多个目标，而每一个目标都和生命周期中的某一个环节对应。

Default目标生命周期中有compile和test-compile两个和编译相关的环节，这两个环节对应compile和test-compile两个目标，而这两个目标都是由maven-compiler-plugin-3.1.jar插件来执行的。

### 5.3、仓库

- 本地仓库：本地电脑上的maven仓库

- 远程仓库：需要联网下载，某一类组织提供的公共maven仓库

  - 局域网：一般是公司自己搭建的nexus私服仓库
  - internet：
    - 中央仓库
    - 镜像仓库：内容和中央仓库保持一致，但是能够分担中央仓库的负载，同时让用户能够就近访问，提高下载速度，例如：Nexus aliyun

  建议：不要中央仓库和阿里云镜像仓库混用，否则jar包来源不纯，彼此冲突。

## 六、项目打包部署

### 6.1、单一架构

- 设置打包后的名称

  ```xml
  <build>
      <!-- 设置打包后的最终名称 -->
      <finalName>demo-single</finalName>
  </build>
  ```

- 打包跳过测试

  ![image-20221223113817542](https://typora-yu-pic.oss-cn-chengdu.aliyuncs.com/picBed/image-20221223113817542.png)

  ![image-20221223113914396](https://typora-yu-pic.oss-cn-chengdu.aliyuncs.com/picBed/image-20221223113914396.png)

  ![image-20221223113957955](https://typora-yu-pic.oss-cn-chengdu.aliyuncs.com/picBed/image-20221223113957955.png)

### 6.2、微服务架构

- 模块之间的关系如下：

  ```markdown
  ├─grain_college
  │  ├─grain_business
  │  │  ├─grain_business_edu
  │  │  │  ├─pom.xml
  │  │  ├─grain_business_member
  │  │  │  ├─pom.xml
  │  │  ├─pom.xml
  │  ├─grain_canal
  │  │  ├─pom.xml
  │  ├─grain_common
  │  │  ├─grain_common_base
  │  │  │  ├─pom.xml
  │  │  ├─pom.xml
  │  ├─pom.xml
  ```

- 各模块的pom文件如下：

  `grain_college`项目pom文件

  ```xml
  <?xml version="1.0" encoding="UTF-8"?>
  <project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
           xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
      <modelVersion>4.0.0</modelVersion>
      <modules>
          <module>grain_business</module>
          <module>grain_common</module>
          <module>grain_canal</module>
      </modules>
      <parent>
          <groupId>org.springframework.boot</groupId>
          <artifactId>spring-boot-starter-parent</artifactId>
          <version>2.2.1.RELEASE</version>
          <relativePath/> <!-- lookup parent from repository -->
      </parent>
      <groupId>com.yu.grain</groupId>
      <artifactId>grain_college</artifactId>
      <version>0.0.1-SNAPSHOT</version>
      <name>grain_college</name>
      <packaging>pom</packaging>
      <description>Demo project for Spring Boot</description>
      <properties>
          <java.version>1.8</java.version>
          <grain.version>0.0.1-SNAPSHOT</grain.version>
          <commons-io.version>2.6</commons-io.version>
          <jwt.version>0.9.0</jwt.version>
          <hutool.version>5.3.10</hutool.version>
          <boot.version>2.2.1.RELEASE</boot.version>
          <canal.cilent.version>1.1.0</canal.cilent.version>
          <docker.image.prefix>zx</docker.image.prefix>
          <cloud-alibaba.version>0.2.2.RELEASE</cloud-alibaba.version>
  
          <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
          <project.reporting.outputEncoding>UTF-8</project.reporting.outputEncoding>
          <maven.compiler.source>1.8</maven.compiler.source>
          <maven.compiler.target>1.8</maven.compiler.target>
          <encoding>UTF-8</encoding>
      </properties>
  
      <dependencyManagement>
          <dependencies>
              <!-- 整合spring cloud -->
              <dependency>
                  <groupId>org.springframework.cloud</groupId>
                  <artifactId>spring-cloud-dependencies</artifactId>
                  <version>Hoxton.RELEASE</version>
                  <type>pom</type>
                  <scope>import</scope>
              </dependency>
  
              <dependency>
                  <groupId>org.springframework.cloud</groupId>
                  <artifactId>spring-cloud-alibaba-dependencies</artifactId>
                  <version>${cloud-alibaba.version}</version>
                  <type>pom</type>
                  <scope>import</scope>
              </dependency>
  
              <dependency>
                  <groupId>org.springframework.boot</groupId>
                  <artifactId>spring-boot-configuration-processor</artifactId>
                  <version>${boot.version}</version>
              </dependency>
  
              <!-- hu tool工具类 -->
              <dependency>
                  <groupId>cn.hutool</groupId>
                  <artifactId>hutool-all</artifactId>
                  <version>${hutool.version}</version>
              </dependency>
  
              <!-- commons-io -->
              <dependency>
                  <groupId>commons-io</groupId>
                  <artifactId>commons-io</artifactId>
                  <version>${commons-io.version}</version>
              </dependency>
  
              <dependency>
                  <groupId>io.jsonwebtoken</groupId>
                  <artifactId>jjwt</artifactId>
                  <version>${jwt.version}</version>
              </dependency>
  
              <dependency>
                  <groupId>com.alibaba.otter</groupId>
                  <artifactId>canal.client</artifactId>
                  <version>${canal.cilent.version}</version>
              </dependency>
  
          </dependencies>
      </dependencyManagement>
  </project>
  ```

  `grain_business`模块pom文件

  ```xml
  <?xml version="1.0" encoding="UTF-8"?>
  <project xmlns="http://maven.apache.org/POM/4.0.0"
           xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
           xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
      <parent>
          <artifactId>grain_college</artifactId>
          <groupId>com.yu.grain</groupId>
          <version>0.0.1-SNAPSHOT</version>
      </parent>
      <modelVersion>4.0.0</modelVersion>
  
      <artifactId>grain_business</artifactId>
      <packaging>pom</packaging>
      <modules>
          <module>grain_business_edu</module>
          <module>grain_business_member</module>
      </modules>
  
      <properties>
          <maven.compiler.source>8</maven.compiler.source>
          <maven.compiler.target>8</maven.compiler.target>
      </properties>
  
      <dependencies>
   		......
      </dependencies>
  
  </project>
  ```

  `grain_business_edu`模块pom文件

  ```xml
  <?xml version="1.0" encoding="UTF-8"?>
  <project xmlns="http://maven.apache.org/POM/4.0.0"
           xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
           xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
      <parent>
          <artifactId>grain_business</artifactId>
          <groupId>com.yu.grain</groupId>
          <version>0.0.1-SNAPSHOT</version>
      </parent>
      <modelVersion>4.0.0</modelVersion>
  
      <artifactId>grain_business_edu</artifactId>
  
      <properties>
          <maven.compiler.source>8</maven.compiler.source>
          <maven.compiler.target>8</maven.compiler.target>
      </properties>
  
      <dependencies>
          <dependency>
              <groupId>com.yu.grain</groupId>
              <artifactId>grain_common_base</artifactId>
              <version>0.0.1-SNAPSHOT</version>
          </dependency>
      </dependencies>
  
      <!-- 项目打包时会将java目录中的*.xml文件也进行打包 -->
      <build>
          <finalName>edu-service</finalName>
          <plugins>
              <plugin>
                  <!--该插件主要用途：构建可执行的JAR -->
                  <groupId>org.springframework.boot</groupId>
                  <artifactId>spring-boot-maven-plugin</artifactId>
                  <configuration>
                      <mainClass>com.yu.grain.EduTeacherApplication</mainClass>
                      <layout>ZIP</layout>
                  </configuration>
                  <executions>
                      <execution>
                          <goals>
                              <goal>repackage</goal>
                          </goals>
                      </execution>
                  </executions>
              </plugin>
          </plugins>
          <resources>
              <resource>
                  <directory>src/main/java</directory>
                  <includes>
                      <include>**/*.xml</include>
                      <include>**/*.yml</include>
                      <include>**/*.properties</include>
                  </includes>
                  <filtering>false</filtering>
              </resource>
  
              <!--设置自己目录下的配置文件-->
              <resource>
                  <directory>src/main/resources</directory>
                  <includes>
                      <include>**/*</include>
                  </includes>
              </resource>
          </resources>
      </build>
  </project>
  ```

  `grain_business_member`模块pom文件

  ```xml
  <?xml version="1.0" encoding="UTF-8"?>
  <project xmlns="http://maven.apache.org/POM/4.0.0"
           xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
           xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
      <parent>
          <artifactId>grain_business</artifactId>
          <groupId>com.yu.grain</groupId>
          <version>0.0.1-SNAPSHOT</version>
      </parent>
      <modelVersion>4.0.0</modelVersion>
  
      <artifactId>grain_business_member</artifactId>
  
      <properties>
          <maven.compiler.source>8</maven.compiler.source>
          <maven.compiler.target>8</maven.compiler.target>
      </properties>
  
      <dependencies>
          <dependency>
              <groupId>com.yu.grain</groupId>
              <artifactId>grain_common_base</artifactId>
              <version>0.0.1-SNAPSHOT</version>
          </dependency>
      </dependencies>
  
      <!-- 项目打包时会将java目录中的*.xml文件也进行打包 -->
      <build>
          <finalName>member-service</finalName>
          <plugins>
              <plugin>
                  <!--该插件主要用途：构建可执行的JAR -->
                  <groupId>org.springframework.boot</groupId>
                  <artifactId>spring-boot-maven-plugin</artifactId>
                  <configuration>
                      <mainClass>com.yu.grain.MemberApplication</mainClass>
                      <layout>ZIP</layout>
                  </configuration>
                  <executions>
                      <execution>
                          <goals>
                              <goal>repackage</goal>
                          </goals>
                      </execution>
                  </executions>
              </plugin>
          </plugins>
          <resources>
              <resource>
                  <directory>src/main/java</directory>
                  <includes>
                      <include>**/*.xml</include>
                      <include>**/*.yml</include>
                      <include>**/*.properties</include>
                  </includes>
                  <filtering>false</filtering>
              </resource>
  
              <!--设置自己目录下的配置文件-->
              <resource>
                  <directory>src/main/resources</directory>
                  <includes>
                      <include>**/*</include>
                  </includes>
              </resource>
          </resources>
      </build>
  </project>
  ```

  `grain_canal`模块pom文件

  ```xml
  <?xml version="1.0" encoding="UTF-8"?>
  <project xmlns="http://maven.apache.org/POM/4.0.0"
           xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
           xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
      <parent>
          <artifactId>grain_college</artifactId>
          <groupId>com.yu.grain</groupId>
          <version>0.0.1-SNAPSHOT</version>
      </parent>
      <modelVersion>4.0.0</modelVersion>
  
      <artifactId>grain_canal</artifactId>
  
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
              <groupId>com.alibaba.otter</groupId>
              <artifactId>canal.client</artifactId>
          </dependency>
      </dependencies>
  
      <!-- 项目打包时会将java目录中的*.xml文件也进行打包 -->
      <build>
          <finalName>canal-service</finalName>
          <plugins>
              <plugin>
                  <!--该插件主要用途：构建可执行的JAR -->
                  <groupId>org.springframework.boot</groupId>
                  <artifactId>spring-boot-maven-plugin</artifactId>
                  <configuration>
                      <mainClass>com.yu.grain.CanalApplication</mainClass>
                      <layout>ZIP</layout>
                  </configuration>
                  <executions>
                      <execution>
                          <goals>
                              <goal>repackage</goal>
                          </goals>
                      </execution>
                  </executions>
              </plugin>
          </plugins>
          <resources>
              <resource>
                  <directory>src/main/java</directory>
                  <includes>
                      <include>**/*.xml</include>
                      <include>**/*.yml</include>
                      <include>**/*.properties</include>
                  </includes>
                  <filtering>false</filtering>
              </resource>
  
              <!--设置自己目录下的配置文件-->
              <resource>
                  <directory>src/main/resources</directory>
                  <includes>
                      <include>**/*</include>
                  </includes>
              </resource>
          </resources>
      </build>
  </project>
  ```

  `grain_common`模块pom文件

  ```xml
  <?xml version="1.0" encoding="UTF-8"?>
  <project xmlns="http://maven.apache.org/POM/4.0.0"
           xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
           xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
      <parent>
          <artifactId>grain_college</artifactId>
          <groupId>com.yu.grain</groupId>
          <version>0.0.1-SNAPSHOT</version>
      </parent>
      <modelVersion>4.0.0</modelVersion>
  
      <artifactId>grain_common</artifactId>
      <packaging>pom</packaging>
      <modules>
          <module>grain_common_base</module>
      </modules>
  
      <properties>
          <maven.compiler.source>8</maven.compiler.source>
          <maven.compiler.target>8</maven.compiler.target>
      </properties>
  
      <dependencies>
          <dependency>
              <groupId>org.springframework.boot</groupId>
              <artifactId>spring-boot-starter-web</artifactId>
              <scope>provided</scope>
          </dependency>
  
          <!-- hu tool工具类 -->
          <dependency>
              <groupId>cn.hutool</groupId>
              <artifactId>hutool-all</artifactId>
              <scope>provided</scope>
          </dependency>
      </dependencies>
  </project>
  ```

  `grain_common_base`模块pom文件

  ```xml
  <?xml version="1.0" encoding="UTF-8"?>
  <project xmlns="http://maven.apache.org/POM/4.0.0"
           xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
           xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
      <parent>
          <artifactId>grain_common</artifactId>
          <groupId>com.yu.grain</groupId>
          <version>0.0.1-SNAPSHOT</version>
      </parent>
      <modelVersion>4.0.0</modelVersion>
  
      <artifactId>grain_common_base</artifactId>
  
      <properties>
          <maven.compiler.source>8</maven.compiler.source>
          <maven.compiler.target>8</maven.compiler.target>
      </properties>
  
      <dependencies>
          <!-- 整合JWT -->
          <dependency>
              <groupId>io.jsonwebtoken</groupId>
              <artifactId>jjwt</artifactId>
          </dependency>
      </dependencies>
  </project>
  ```

- 执行打包命令

  - 在每个需要打包的模块下执行mvn clean package -Dmaven.test.skip=true
  - 在父项目下执行mvn clean package -Dmaven.test.skip=true

## 七、POM文件深入理解

### 7.1、POM的四个层次

#### 1、超级POM

Maven在构建的过程中有很多默认的设定。例如：源文件存放的目录、测试源文件存放的目录、构建输出的目录......等等。但是其实这些要素也是被Maven定义了的，定义的位置就是：**超级POM**

关于超级POM，Maven官网是这样介绍的：

> Similar to the inheritance of objects in object oriented programming, POMs that extend a parent POM inherit certain values from that parent. Moreover, just as Java objects ultimately inherit from `java.lang.Object`, all Project Object Models inherit from a base Super POM. The snippet below is the Super POM for Maven 3.5.4
>
> 
>
> 译文：类似于面向对象编程中的对象继承，扩展父 POM 的 POM 从父 POM 继承某些值。 此外，正如 Java 对象最终继承自 java.lang.Object 一样，所有项目对象模型都继承自基础 Super POM。 下面的代码片段是 Maven 3.5.4 的 Super POM

```xml
<project>
  <modelVersion>4.0.0</modelVersion>
 
  <repositories>
    <repository>
      <id>central</id>
      <name>Central Repository</name>
      <url>https://repo.maven.apache.org/maven2</url>
      <layout>default</layout>
      <snapshots>
        <enabled>false</enabled>
      </snapshots>
    </repository>
  </repositories>
 
  <pluginRepositories>
    <pluginRepository>
      <id>central</id>
      <name>Central Repository</name>
      <url>https://repo.maven.apache.org/maven2</url>
      <layout>default</layout>
      <snapshots>
        <enabled>false</enabled>
      </snapshots>
      <releases>
        <updatePolicy>never</updatePolicy>
      </releases>
    </pluginRepository>
  </pluginRepositories>
 
  <build>
    <directory>${project.basedir}/target</directory>
    <outputDirectory>${project.build.directory}/classes</outputDirectory>
    <finalName>${project.artifactId}-${project.version}</finalName>
    <testOutputDirectory>${project.build.directory}/test-classes</testOutputDirectory>
    <sourceDirectory>${project.basedir}/src/main/java</sourceDirectory>
    <scriptSourceDirectory>${project.basedir}/src/main/scripts</scriptSourceDirectory>
    <testSourceDirectory>${project.basedir}/src/test/java</testSourceDirectory>
    <resources>
      <resource>
        <directory>${project.basedir}/src/main/resources</directory>
      </resource>
    </resources>
    <testResources>
      <testResource>
        <directory>${project.basedir}/src/test/resources</directory>
      </testResource>
    </testResources>
    <pluginManagement>
      <!-- NOTE: These plugins will be removed from future versions of the super POM -->
      <!-- They are kept for the moment as they are very unlikely to conflict with lifecycle mappings (MNG-4453) -->
      <plugins>
        <plugin>
          <artifactId>maven-antrun-plugin</artifactId>
          <version>1.3</version>
        </plugin>
        <plugin>
          <artifactId>maven-assembly-plugin</artifactId>
          <version>2.2-beta-5</version>
        </plugin>
        <plugin>
          <artifactId>maven-dependency-plugin</artifactId>
          <version>2.8</version>
        </plugin>
        <plugin>
          <artifactId>maven-release-plugin</artifactId>
          <version>2.5.3</version>
        </plugin>
      </plugins>
    </pluginManagement>
  </build>
 
  <reporting>
    <outputDirectory>${project.build.directory}/site</outputDirectory>
  </reporting>
 
  <profiles>
    <!-- NOTE: The release profile will be removed from future versions of the super POM -->
    <profile>
      <id>release-profile</id>
 
      <activation>
        <property>
          <name>performRelease</name>
          <value>true</value>
        </property>
      </activation>
 
      <build>
        <plugins>
          <plugin>
            <inherited>true</inherited>
            <artifactId>maven-source-plugin</artifactId>
            <executions>
              <execution>
                <id>attach-sources</id>
                <goals>
                  <goal>jar-no-fork</goal>
                </goals>
              </execution>
            </executions>
          </plugin>
          <plugin>
            <inherited>true</inherited>
            <artifactId>maven-javadoc-plugin</artifactId>
            <executions>
              <execution>
                <id>attach-javadocs</id>
                <goals>
                  <goal>jar</goal>
                </goals>
              </execution>
            </executions>
          </plugin>
          <plugin>
            <inherited>true</inherited>
            <artifactId>maven-deploy-plugin</artifactId>
            <configuration>
              <updateReleaseInfo>true</updateReleaseInfo>
            </configuration>
          </plugin>
        </plugins>
      </build>
    </profile>
  </profiles>
 
</project>
```

#### 2、父POM

和Java类一样，POM之间其实也是`单继承`的。如果我们给一个POM指定了父POM，那么继承关系如下所示：

```markdown
															超级POM
															   ↑
															  继承
															   ↑
															 父POM
															   ↑
															  继承
															   ↑
															 当前POM
```

#### 3、有效POM

概念：有效POM英文翻译为effective POM，在POM的继承关系中，子POM可以覆盖父POM中的配置；如果子POM没有覆盖，那么父POM中的配置将会被继承。按照这个规则，继承关系中的所有POM叠加到一起，就得到了最终生效的POM。显然Maven实际运行过程中，执行构建操作就是按照这个最终生效的POM来运行的。这个最终生效的POM就是`有效POM(effective POM)`

查看有效POM可以使用以下命令：

> mvn help:effective-pom

#### 4、小结

综上所述，平时我们使用和配置的POM大致是由以下四个层次组成的：

- 超级POM：所有POM默认继承，只是有直接和间接区别。
- 父POM：这一层可能没有，可能有一层，也可能有很多层。
- 当前POM：我们最多关注和使用的一层。
- 有效POM：隐含的一层，但是实际上真正生效的一层。

### 7.2、属性的声明与引用

#### 1、help插件的各个目标

官网地址：https://maven.apache.org/plugins/maven-help-plugin

| 目标                    | 说明                                          |
| ----------------------- | --------------------------------------------- |
| help:active-profiles    | 列出当前已激活的profile                       |
| help:all-profiles       | 列出当前工程所有可用profile                   |
| help:describe           | 描述一个插件和/或Mojo的属性                   |
| help:effective-pom      | 以XML格式展示有效POM                          |
| help:effective-settings | 为当前工程以XML格式展示计算得到的settings配置 |
| help:evaluate           | 计算用户在交互模式下给出的Maven表达式         |
| help:system             | 显示平台详细信息列表，如系统属性和环境变量    |

#### 2、使用`mvn help:evaluate`命令查看属性值

```markdown
D:\DevSoftware\Java\JetBrains\IDEA2021\IdeaProjects\grain_college\grain_business\grain_business_edu>mvn help:evaluate
${user.home}
[INFO]
C:\Users\elon
```

#### 3、通过Maven访问系统属性

```markdown
D:\DevSoftware\Java\JetBrains\IDEA2021\IdeaProjects\grain_college\grain_business\grain_business_edu>mvn help:evaluate
[INFO] Enter the Maven expression i.e. ${project.groupId} or 0 to exit?:
${java.runtime.name}
[INFO]
Java(TM) SE Runtime Environment
```

#### 4、访问系统环境变量

${env.系统环境变量名}

```markdown
D:\DevSoftware\Java\JetBrains\IDEA2021\IdeaProjects\grain_college\grain_business\grain_business_edu>mvn help:evaluate
[INFO] Enter the Maven expression i.e. ${project.groupId} or 0 to exit?:
${env.JAVA_HOME}
[INFO]
D:\DevSoftware\Java\jdk1.8.0_291
```

#### 5、访问project属性

- 访问一级标签

  ```markdown
  D:\DevSoftware\Java\JetBrains\IDEA2021\IdeaProjects\grain_college\grain_business\grain_business_edu>mvn help:evaluate
  [INFO] Enter the Maven expression i.e. ${project.groupId} or 0 to exit?:
  ${project.artifactId}
  [INFO]
  grain_business_edu
  ```

- 访问子标签

  ```markdown
  D:\DevSoftware\Java\JetBrains\IDEA2021\IdeaProjects\grain_college\grain_business\grain_business_edu>mvn help:evaluate
  [INFO] Enter the Maven expression i.e. ${project.groupId} or 0 to exit?:
  ${project.parent.artifactId}
  [INFO]
  grain_business
  ```

- 访问列表标签

```markdown
D:\DevSoftware\Java\JetBrains\IDEA2021\IdeaProjects\grain_college\grain_business>mvn help:evaluate
[INFO] Enter the Maven expression i.e. ${project.groupId} or 0 to exit?:
${project.modules[5]}
[INFO]
grain_business_member
```

#### 6、访问settings全局配置

```markdown
D:\DevSoftware\Java\JetBrains\IDEA2021\IdeaProjects\grain_college\grain_business>mvn help:evaluate
[INFO] Enter the Maven expression i.e. ${project.groupId} or 0 to exit?:
${settings.LocalRepository}
[INFO]
D:\DevSoftware\Java\Maven\apache-maven-3.6.3\repository
```

#### 7、属性引用和资源过滤

- 资源过滤

  在非Maven配置文件中引用属性，由Maven在处理资源时将引用属性的表达式替换为属性

- 引用

  在当前pom.xml中引用属性

  ```xml
  <properties>
  	<jwt.version>0.9.0</jwt.version>
  </properties>
  
  <dependencies>
      <dependency>
          <groupId>io.jsonwebtoken</groupId>
          <artifactId>jjwt</artifactId>
          <version>${jwt.version}</version>
      </dependency>
  </dependencies>
  ```

### 7.3、build标签详解

#### 1、指定jdk版本

- settings.xml文件中配置

  ```xml
  <profiles>
      <profile>
          <id>jdk-1.8</id>
          <activation>
              <activeByDefault>true</activeByDefault>
              <jdk>1.8</jdk>
          </activation>
          <properties>
              <maven.compiler.source>1.8</maven.compiler.source>
              <maven.compiler.target>1.8</maven.compiler.target>
              <maven.compiler.compilerVersion>1.8</maven.compiler.compilerVersion>
          </properties>
      </profile>
  </profiles>
  ```

- pom.xml中properties标签中指定

  ```xml
  <properties>
      <maven.compiler.source>1.8</maven.compiler.source>
      <maven.compiler.target>1.8</maven.compiler.target>
  </properties>
  ```

- pom.xml中build标签中指定

  ```xml
  <build>
      <plugins>
          <plugin>
              <groupId>org.apache.maven.plugins</groupId>
              <artifactId>maven-compiler-plugin</artifactId>
              <version>3.10.1</version>
              <configuration>
                  <source>1.8</source>
                  <target>1.8</target>
              </configuration>
          </plugin>
      </plugins>
  </build>
  ```

### 7.4、依赖配置补充

#### import

import依赖范围使用要求：

- 打包类型必须是pom
- 必须放在dependencyManagement标签中

```xml
<dependencyManagement>
    <dependencies>
        <!-- 整合spring cloud -->
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-dependencies</artifactId>
            <version>Hoxton.RELEASE</version>
            <type>pom</type>
            <scope>import</scope>
        </dependency>

        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-alibaba-dependencies</artifactId>
            <version>${cloud-alibaba.version}</version>
            <type>pom</type>
            <scope>import</scope>
        </dependency>
    </dependencies>
</dependencyManagement>
```

#### system

非本地仓库引入、存在系统的某个路径下的jar。（一般不使用）

```xml
<dependency>
    <groupId>org.geotools</groupId>
    <artifactId>gt-geojson</artifactId>
    <scope>system</scope>
    <systemPath>${sys.lib.dir}/gt-geojson-18.1.jar</systemPath>
</dependency>
```

#### runtime

编译不需要，在运行期有效，需要导入包中。（接口与实现分离）

```xml
<!-- 热部署 -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-devtools</artifactId>
    <scope>runtime</scope>
    <optional>true</optional>
</dependency>
```

#### 可选依赖

举例来说，一个类似hibernate的项目，它支持对mysql、oracle等各种数据库的支持，但是在引用这个项目时，我们可能只用到其对mysql的支持，此时就可以在这个项目中配置可选依赖。**可选依赖就是optional标签，默认false。**

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-devtools</artifactId>
    <scope>runtime</scope>
    <optional>true</optional>
</dependency>
```

## 八、多环境开发配置

加载指定环境配置

调用格式如下：

```markdown
mvn 指令 -P 环境id
```

范例：

```markdown
mvn install -P pro_env
```

配置如下：

```xml
<!-- 创建多环境 -->
<profiles>
    <!-- 定义具体的环境:生产环境 -->
    <profile>
        <!-- 定义环境对应的唯一名称 -->
        <id>pro_env</id>
        <!-- 定义环境中换用属性值 -->
        <properties>
            <jdbc.url>jdbc:mysql://127.0.0.1:3307/db</jdbc.url>
        </properties>
        <!-- 设置默认启动 -->
        <activation>
            <activeByDefault>true</activeByDefault>
        </activation>
    </profile>
    <!-- 定义具体的环境:开发环境 -->
    <profile>
        <id>dep_env</id>
        <properties>
            <jdbc.url>jdbc:mysql://127.0.0.1:3308/db</jdbc.url>
        </properties>
    </profile>
</profiles>
```

## 九、跳过测试

### 1、命令行方式跳过测试

我们可以用两种命令来跳过测试

- mvn clean install -DskipTests
- mvn clean install -Dmaven.test.skip=true

\- DskipTests，不执行测试用例，但编译测试用例类生成相应的class文件至target/test-classes下

\- Dmaven.test.skip=true，不执行测试用例，也不编译测试用例类

使用maven.test.skip，不但跳过单元测试的运行，也跳过测试代码的编译;

使用 mvn package -DskipTests 跳过单元测试，但是会继续编译。

### 2、pom.xml中配置跳过测试

可以在pom.xml中添加如下配置来跳过测试：

```xml
<build>
    <plugins>
        <!-- maven 打包时跳过测试 -->
        <plugin>
            <groupId>org.apache.maven.plugins</groupId>
            <artifactId>maven-surefire-plugin</artifactId>
            <configuration>
                <skip>true</skip>
            </configuration>
        </plugin>
    </plugins>
</build> 
```

### 3、IDEA中配置跳过测试

Idea是我们常用的开发工具，我们经常直接用Idea的插件来进行打包。我们可以通过配置来跳过测试。

- 直接配置

  Maven命令栏的工具栏有下图中的图标，这个图标就是 Skip Tests。点击选中，再用LifeStyle中的打包就会跳过测试。

  ![image-20221226094601403](https://typora-yu-pic.oss-cn-chengdu.aliyuncs.com/picBed/image-20221226094601403.png)

- 更改Maven配置

  - 方式1：打开配置，找到Maven-->Runner，在VM option中添加 -Dmaven.test.skip=true

    ![image-20221226094849057](https://typora-yu-pic.oss-cn-chengdu.aliyuncs.com/picBed/image-20221226094849057.png)

  - 方式2：Runner--> Propertis 中选中 Skip tests

    ![image-20221226095010532](https://typora-yu-pic.oss-cn-chengdu.aliyuncs.com/picBed/image-20221226095010532.png)

## 十、私服

目的：解决分模块开发的问题

### 1、下载Nexus

- Nexus是Sonatype公司的一款Maven私服产品
- 下载地址：https://help.sonatype.com/repomanager3/download

### 2、安装、部署Nexus

- 启动服务器（命令行启动）

  ```sh
  nexus.exe /run nexus
  ```

- 访问服务器（默认端口8081）

  ```http
  http://localhost:8081
  ```

- 修改基础配置信息

  安装路径下etc目录中nexus-default.properties文件保存有nexus基础配置信息，例如默认访问端口

- 修改服务器运行配置信息

  安装路径下bin目录中nexus.vmoptions文件保存有nexus服务器启动对应的配置信息，例如默认占用空间

### 3、仓库分类

- 宿主仓库hosted

  保存无法从中央仓库获取的资源，如自研项目和第三方非开源项目等。

  - 创建自定义仓库

    - RELEASE

      ![image-20221226232824674](https://typora-yu-pic.oss-cn-chengdu.aliyuncs.com/picBed/image-20221226232824674.png)

      ![image-20221226232926816](https://typora-yu-pic.oss-cn-chengdu.aliyuncs.com/picBed/image-20221226232926816.png)

      ![image-20221226232955783](https://typora-yu-pic.oss-cn-chengdu.aliyuncs.com/picBed/image-20221226232955783.png)

      ![image-20221226233044206](https://typora-yu-pic.oss-cn-chengdu.aliyuncs.com/picBed/image-20221226233044206.png)

    - SNAPSHOT

      ![image-20221226235220777](https://typora-yu-pic.oss-cn-chengdu.aliyuncs.com/picBed/image-20221226235220777.png)

  - 加入公共仓库

    ![image-20221226233555549](https://typora-yu-pic.oss-cn-chengdu.aliyuncs.com/picBed/image-20221226233555549.png)

    ![image-20221226233638808](https://typora-yu-pic.oss-cn-chengdu.aliyuncs.com/picBed/image-20221226233638808.png)

    ![image-20221226235350141](https://typora-yu-pic.oss-cn-chengdu.aliyuncs.com/picBed/image-20221226235350141.png)

  - 手动上传组件

    ![image-20221226234059028](https://typora-yu-pic.oss-cn-chengdu.aliyuncs.com/picBed/image-20221226234059028.png)

    ![image-20221226234122503](https://typora-yu-pic.oss-cn-chengdu.aliyuncs.com/picBed/image-20221226234122503.png)

    ![image-20221226234332950](https://typora-yu-pic.oss-cn-chengdu.aliyuncs.com/picBed/image-20221226234332950.png)

    ![image-20221226234417033](https://typora-yu-pic.oss-cn-chengdu.aliyuncs.com/picBed/image-20221226234417033.png)

- 代理仓库proxy

  代理远程仓库，通过nexus访问其他公共仓库，例如中央仓库。

- 仓库group

  - 将若干个仓库组成一个群组，简化配置
  - 仓库组不存保存资源，属于设计型仓库

### 4、本地仓库访问私服

- 配置本地仓库访问私服的权限（settings.xml）

  ```xml
  <servers>
      <!-- 配置访问私服的权限、用户和密码 -->
      <server>
          <id>yu-release</id>
          <username>admin</username>
          <password>admin</password>
      </server>
      <server>
          <id>yu-snapshots</id>
          <username>admin</username>
          <password>admin</password>
      </server>
  </servers>
  ```

- 配置本地仓库资源来源（settings.xml）

  ![image-20221227000720136](https://typora-yu-pic.oss-cn-chengdu.aliyuncs.com/picBed/image-20221227000720136.png)

  ```xml
  <mirrors>
      <!-- 自定义的私服地址 --> 
      <mirror>
          <id>nexus-yu</id>
          <mirrorOf>*</mirrorOf>
          <url>http://localhost:8085/repository/maven-public/</url>
      </mirror>
  </mirrors>
  ```

### 5、IDEA

- 配置当前项目访问私服上传资源的保存位置（项目父pom.xml）

  ```xml
  <distributionManagement>
      <repository>
          <id>yu-release</id>
          <url>http://localhost:8085/repository/yu-release/</url>
      </repository>
      <snapshotRepository>
          <id>yu-snapshots</id>
          <url>http://localhost:8085/repository/yu-snapshots/</url>
      </snapshotRepository>
  </distributionManagement>
  ```

- 发布资源到私服命令（项目目录下执行）

  ```sh
  mvn deploy
  ```

  ![image-20221227002115020](https://typora-yu-pic.oss-cn-chengdu.aliyuncs.com/picBed/image-20221227002115020.png)
