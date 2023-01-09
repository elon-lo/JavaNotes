# Gradle

## 一、Gradle入门

### 1.1、Gradle介绍

Gradle 是一种开源构建自动化工具，专注于灵活性和性能。 Gradle 构建脚本是使用 Groovy 或 Kotlin DSL 编写的。

Gradle官方地址：https://gradle.org/

Gradle官方文档地址：https://docs.gradle.org/current/userguide/userguide.html

### 1.2、Gradle的优点

- 高度可定制

  Gradle 以最基本的方式以可定制和可扩展的方式建模。

- 快速

  Gradle 通过重用先前执行的输出、仅处理更改的输入以及并行执行任务来快速完成任务。

- 强大

  Gradle 是 Android 的官方构建工具，并支持许多流行的语言和技术。

### 1.3、常见的项目构建工具

- Ant

  2000年Apache推出的纯Java编写的构建工具，通过xml(build.xml)文件管理项目。

  优点：使用灵活，构建速度快(比Maven和Gradle快)。

  缺点：Ant没有指定任何编码约定的项目目录结构，开发人员需要编写繁杂的xml文件构建指令，对开发人员是一个挑战。

- Maven

  2004年Apache组织推出的再次使用xml(pom.xml)管理项目的构建工具。

  优点：遵循一套约定大于配置的项目目录结构，使用统一的GAV坐标进行依赖管理，**侧重于包管理**。

  缺点：项目构建过程僵化，配置文件编写不够灵活、不方便自定义组件，构建速度慢于Gradle。

- Gradle

  2012年Google推出的基于Groovy语言的全新项目构建工具，集合了Ant和Maven各自的优势。

  优点：集Ant脚本的灵活性与Maven约定大于配置的项目目录优势，支持多种远程仓库和插件，**侧重于大项目构建**。

  缺点：学习成本高、资料少、脚本灵活、版本兼容性差等。

| 自动化构建工具对比 |      Ant       |    Maven    |          Gradle          |
| :----------------: | :------------: | :---------: | :----------------------: |
|      构建性能      |      最高      |    最低     |           居中           |
|        仓库        | 开发者自己处理 |  maven仓库  |     支持多种远程仓库     |
|      依赖管理      |    ivy管理     | GAV坐标管理 |       GNV坐标管理        |
|      插件支持      |    实现方便    |  实现较难   |         实现方便         |
|  遵循特定目录结构  |     不遵循     |    遵循     |           遵循           |
|      配置文件      | xml文件最繁琐  |   xml文件   | 代码脚本，便于写业务逻辑 |
|       侧重点       |  小型项目构建  | 项目包管理  |       大型项目构建       |
|       流行度       |    使用较少    |  目前主流   |   未来趋势(Spring家族)   |

### 1.4、Gradle安装

#### 1.4.1、安装说明

SpringBoot官方文档明确指出，目前SpringBoot的Gradle插件仅支持gradle6.8及以上版本。

#### 1.4.2、安装JDK

要求jdk为1.8或1.8以上版本。

#### 1.4.3、查看当前IDEA默认的Gradle版本

IDEA版本与Gradle版本对应表：https://www.jetbrains.com/legal/third-party-software/?product=IIU

这里列举一些，更多请上官网查看：

|     IDEA版本      | Gradle版本 |
| :---------------: | :--------: |
|     2018.3.x      |    4.10    |
|     2019.1.x      |   4.10.3   |
| 2019.2.x-2019.3.x |   5.2.1    |
|     2020.1.x      |    6.1     |
|     2020.2.x      |    6.5     |
|     2020.3.x      |    6.7     |
|     2021.1.x      |    6.8     |
| 2021.2.x-2021.3.x |    7.1     |
| 2022.1.x-2022.2.x |    7.4     |
|     2022.3.x      |   7.5.1    |

#### 1.4.4、下载并解压到指定目录

![image-20221231101727321](https://typora-yu-pic.oss-cn-chengdu.aliyuncs.com/picBed/image-20221231101727321.png)

#### 1.4.5、配置环境变量

- 配置gradle环境变量

  ![image-20221231102611061](https://typora-yu-pic.oss-cn-chengdu.aliyuncs.com/picBed/image-20221231102611061.png)

  ![image-20221231102821636](https://typora-yu-pic.oss-cn-chengdu.aliyuncs.com/picBed/image-20221231102821636.png)

- 配置本地仓库

  ![image-20221231102912580](https://typora-yu-pic.oss-cn-chengdu.aliyuncs.com/picBed/image-20221231102912580.png)

  注意：这里的`GRADLE_USER_HOME`是固定的，不需要再添加到path中，GRADLE_USER_HOME相当于配置`Gradle本地仓库地址`和`Gradle Wrapper缓存目录`。

#### 1.4.6、检测是否安装成功

使用`gradle -v`或`gradle --version`检测是否安装成功。

#### 1.4.7、Gradle项目目录结构

Gradle项目默认目录结构和Maven的项目目录结构一致，都是基于约定大于配置

```markdown
├─build:封装编译后的字节码、打包文件[jar、war]、测试报告等信息					     
├─gradle:封装包装器文件夹                                                          
|  └─wrapper															     
|    ├─gradle-wrapper.jar                                                         
|    └─gradle-wrapper.properties                                                                    
├─src                                                                      		
|  ├─main                                                                         
|  |  ├─java
|  |  ├─resource
|  |  └─webapp
|  |     ├─WEB-INF
|  |     |  └─web.xml
|  |     └─index.jsp
|  └─test
|     ├─java
|     └─resource
├─gradlew
├─gradlew.bat:包装器启动脚本
├─build.gradle:构建脚本,类似于maven中的pom
├─settings.gradle:设置文件,定义项目及子项目名称关系,和项目是一一对应关系
```

#### 1.4.8、Gradle项目

Spring脚手架地址：https://start.spring.io

##### 1.4.8.1、Spring脚手架创建

![image-20221231115322700](https://typora-yu-pic.oss-cn-chengdu.aliyuncs.com/picBed/image-20221231115322700.png)

![image-20221231115406896](https://typora-yu-pic.oss-cn-chengdu.aliyuncs.com/picBed/image-20221231115406896.png)

##### 1.4.8.2、命令行方式创建

```shell
>gradle init

Welcome to Gradle 7.4!

Here are the highlights of this release:
 - Aggregated test and JaCoCo reports
 - Marking additional test source directories as tests in IntelliJ
 - Support for Adoptium JDKs in Java toolchains

For more details see https://docs.gradle.org/7.4/release-notes.html

Starting a Gradle Daemon (subsequent builds will be faster)

Select type of project to generate:
  1: basic
  2: application
  3: library
  4: Gradle plugin
Enter selection (default: basic) [1..4] 2

Select implementation language:
  1: C++
  2: Groovy
  3: Java
  4: Kotlin
  5: Scala
  6: Swift
Enter selection (default: Java) [1..6] 3

Split functionality across multiple subprojects?:
  1: no - only one application project
  2: yes - application and library projects
Enter selection (default: no - only one application project) [1..2] 1

Select build script DSL:
  1: Groovy
  2: Kotlin
Enter selection (default: Groovy) [1..2] 1

Generate build using new APIs and behavior (some features may change in the next minor release)? (default: no) [yes, no] no

Select test framework:
  1: JUnit 4
  2: TestNG
  3: Spock
  4: JUnit Jupiter
Enter selection (default: JUnit Jupiter) [1..4] 1

Project name (default: gradle-demo2):

Source package (default: gradle.demo2): com.yu.gradle


> Task :init
Get more help with your project: https://docs.gradle.org/7.4/samples/sample_building_java_applications.html

BUILD SUCCESSFUL in 40s
2 actionable tasks: 2 executed
```

![image-20221231122616172](https://typora-yu-pic.oss-cn-chengdu.aliyuncs.com/picBed/image-20221231122616172.png)

##### 1.4.8.3、Gradle中的常用指令

|    常用gradle指令    |            作用            |
| :------------------: | :------------------------: |
|     gradle clean     |       清空build目录        |
|    gradle classes    |   编译业务代码和配置文件   |
|     gradle test      | 编译测试代码，生成测试报告 |
|     gradle build     |          构建项目          |
| gradle build -x test |      跳过测试构建项目      |

注意：gradle指令要在含有`build.gradle`的目录下执行。

##### 1.4.8.4、修改Gradle下载源

- 修改下载源方式一

  **init.d文件夹**

  在gradle的init.d目录下创建以.gradle文件结尾的文件，.gradle文件可以在build开始之前执行，所以可以在这个配置文件配置一些预加载操作。

  **在init.d文件夹创建init.gradle文件**

  ```xml
  allprojects {
  	repositories {
  		mavenLocal()
  		maven { name "Alibaba" ; url  "https://maven.aliyun.com/repository/public" }
  		maven { name "Bstek" ; url  "https://nexus.bsdn.org/content/groups/public/" }
  		mavenCentral()
  	}
  
  	buildscript {
  		repositories {
  		maven { name "Alibaba" ; url  "https://maven.aliyun.com/repository/public" }
  		maven { name "Bstek" ; url  "https://nexus.bsdn.org/content/groups/public/" }
  		maven { name "M2" ; url  "https://plugins.gradle.org/m2/" }
  		}
  	}
  }
  ```

  启用init.gradle文件的方法有以下几种：

  1、在命令行执行文件，例如`gradle --init-script ${yourdir}/init.gradle -q taskName`，可以多次输入此命令来指定多个init文件。

  2、把init.gradle文件放到`${USER_HOME}/.gradle/`目录下

  3、把以.gradle结尾的文件放到`${USER_HOME}/.gradle/init.d/`目录下

  4、把以.gradle结尾的文件放到`${GRADLE_HOME}/init.d/`目录下

  注意：如果存在上面4种方式的2种及以上，gradle会按上面的1-4依次执行这些文件，如果给定的目录下存在多个init脚本，会按拼音a-z顺序执行这些脚本，每个init脚本都存在一个对应的gradle实例，在这个文件中调用的所有方法和属性，都会委托给这个gradle实例。

  仓库地址说明：

  mavenLocal()：指定使用maven本地仓库，而本地仓库在配置maven时settings文件指定的仓库位置。

  maven {url 地址}：指定maven仓库，一般用私有仓库地址或其他的第三方地库(如阿里云镜像仓库地址)。

  mavenCentral()：maven的中央仓库，无需配置，直接声明就可以用。

  jcenter()：JCenter中央仓库，实际也是用maven搭建的，但相比maven仓库更加友好，通过CDN分发，并且支持https访问(在新版本已经废弃)

- 修改下载源方式二

  - 全局更改Gradle镜像下载地址

    如果配置`GRADLE_USER_HOME`路径则在`${GRADLE_USER_HOME}`下新建`init.gradle`文件
    如果没有配置则在默认路径`${USER_HOME}/.gradle/`下新建`init.gradle`文件。

    init.gradle文件内容如下：

    ```xml
    allprojects {
        repositories {
            def ALIYUN_REPOSITORY_URL = 'https://maven.aliyun.com/repository/public'
            def ALIYUN_JCENTER_URL = 'https://maven.aliyun.com/repository/jcenter'
            def ALIYUN_GOOGLE_URL = 'https://maven.aliyun.com/repository/google'
            def ALIYUN_GRADLE_PLUGIN_URL = 'https://maven.aliyun.com/repository/gradle-plugin'
            def ALIYUN_GRAILS_CORE = 'https://maven.aliyun.com/repository/grails-core'
    
            def REPOSITORY_URL = 'https://repo1.maven.org/maven2/'
            def JCENTER_URL = 'https://jcenter.bintray.com/'
            def GOOGLE_URL = 'https://dl.google.com/dl/android/maven2/'
            def GRADLE_PLUGIN_URL = 'https://plugins.gradle.org/m2/'
    
            all { ArtifactRepository repo ->
                if (repo instanceof MavenArtifactRepository) {
                    def url = repo.url.toString()
                    if (url.startsWith(REPOSITORY_URL)) {
                        project.logger.lifecycle "Repository ${repo.url} replaced by $ALIYUN_REPOSITORY_URL."
                        remove repo
                    }
                    if (url.startsWith(JCENTER_URL)) {
                        project.logger.lifecycle "Repository ${repo.url} replaced by $ALIYUN_JCENTER_URL."
                        remove repo
                    }
                    if (url.startsWith(GOOGLE_URL)) {
                        project.logger.lifecycle "Repository ${repo.url} replaced by $ALIYUN_GOOGLE_URL."
                        remove repo
                    }
                    if (url.startsWith(GRADLE_PLUGIN_URL)) {
                        project.logger.lifecycle "Repository ${repo.url} replaced by $ALIYUN_GRADLE_PLUGIN_URL."
                        remove repo
                    }
                }
            }
            maven { url ALIYUN_REPOSITORY_URL }
            maven { url ALIYUN_JCENTER_URL }
            maven { url ALIYUN_GOOGLE_URL }
            maven { url ALIYUN_GRADLE_PLUGIN_URL }
            maven { url ALIYUN_GRAILS_CORE }
            maven { url REPOSITORY_URL }
            maven { url JCENTER_URL }
            maven { url GOOGLE_URL }
            maven { url GRADLE_PLUGIN_URL }
        }
    }
    ```

  - 单项目更改Gradle镜像下载地址

    在`project`下的`build.gradle`文件中的`buildscript节点和allprojects节点`中的`repositories`补充如下内容：

    ```xml
    mavenLocal()
    maven { url 'https://maven.aliyun.com/repository/public' }
    maven { url 'https://maven.aliyun.com/repository/jcenter' }
    maven { url 'https://maven.aliyun.com/repository/google' }
    maven { url 'https://maven.aliyun.com/repository/gradle-plugin' }
    maven { url 'https://maven.aliyun.com/repository/grails-core' }
    google()
    ```

    完整build.gradle示例如下：

    ```xml
    buildscript {
    
        repositories {
            mavenLocal()
            maven { url 'https://maven.aliyun.com/repository/public' }
            maven { url 'https://maven.aliyun.com/repository/jcenter' }
            maven { url 'https://maven.aliyun.com/repository/google' }
            maven { url 'https://maven.aliyun.com/repository/gradle-plugin' }
            maven { url 'https://maven.aliyun.com/repository/grails-core' }
            google()
        }
        dependencies {
            classpath 'com.android.tools.build:gradle:3.3.1'
            classpath "io.objectbox:objectbox-gradle-plugin:2.3.4"
            // NOTE: Do not place your application dependencies here; they belong
            // in the individual module build.gradle files
        }
    }
    
    allprojects {
        repositories {
            mavenLocal()
            maven { url 'https://maven.aliyun.com/repository/public' }
            maven { url 'https://maven.aliyun.com/repository/jcenter' }
            maven { url 'https://maven.aliyun.com/repository/google' }
            maven { url 'https://maven.aliyun.com/repository/gradle-plugin' }
            maven { url 'https://maven.aliyun.com/repository/grails-core' }
            google()
        }
    }
    
    task clean(type: Delete) {
        delete rootProject.buildDir
    }
    ```

阿里云代理仓库官网：https://developer.aliyun.com/mvn/guide

##### 1.4.8.5、Wrapper包装器

Gradle Wrapper实际上是对Gradle的一层包装，用于`解决`实际开发中可能遇到的`不同项目需要不同的版本Gradle问题`。

例如：把自己的代码共享给其他人使用，可能出现以下情况：

1、对方电脑没有安装gradle

2、对方电脑安装过gradle，但是版本太旧了

这时候，可以考虑使用Gradle Wrapper，这也是官方建议使用Gradle Wrapper的原因。实际上有了Gradle Wrapper之后，本地可以不配置Gradle，下载Gradle项目后，使用Gradle项目自带的Wrapper操作。

项目中的gradlew、gradlew.bat脚本就是wrapper中规定的gradle版本。而上面我们提到的gradle指令用的是本地gradle，所以gradle指令和gradlew指令所使用的gradle版本有可能是不一样的，gradlew、gradlew.bat的使用方式与gradle使用方式完全一致，只不过把gradle指令换成了gradlew。

当然我们也可以在终端执行gradlew指令时，指定一些参数来控制wrapper的生成，比如依赖的版本等等：

| 参数名                    | 说明                              |
| ------------------------- | --------------------------------- |
| --gradle-version          | 用于指定使用的Gradle版本          |
| --gradle-distribution-url | 用于指定下载Gradle发行版的url地址 |

## 二、Gradle与IDEA整合

### 2.1、Groovy简介

Groovy官方文档：https://groovy-lang.org/documentation.html

在某种程度上，Groovy可以被视为Java的一种脚本化改良版，Groovy也是运行在JVM上，它可以很好的与Java代码以及相关库进行交互操作。它是一种成熟的面向对象编程语言，既可以面向对象编程，又可以用作纯粹的脚本语言。大多数有效的Java代码也可以转换为有效的Groovy代码，Groovy与Java语言的主要区别是：完成同样的任务所需的Groovy代码比Java代码更少。其特点为：

> - 功能强大，例如提供了动态类型转换、闭包和元编程支持。
> - 支持函数式编程，不需要main函数。
> - 默认导入常用的包。
> - 类不支持default作用域，且默认作用域为public。
> - Groovy中基本类型也是对象，可以直接调用对象的方法。
> - 支持DSL(Domain Specific Languages领域特定语言)和其它简洁的语法，让代码变得易于阅读和维护。
> - Groovy是基于Java语言的，完全兼容Java语言，对Java程序员学习成本很低。

### 2.2、Groovy安装与IDEA整合

Groovy下载地址：https://archive.apache.org/dist/groovy/

#### 2.2.1、下载Groovy

![image-20221231184116043](https://typora-yu-pic.oss-cn-chengdu.aliyuncs.com/picBed/image-20221231184116043.png)

#### 2.2.2、配置环境变量

![image-20221231183921151](https://typora-yu-pic.oss-cn-chengdu.aliyuncs.com/picBed/image-20221231183921151.png)

![image-20221231184035750](https://typora-yu-pic.oss-cn-chengdu.aliyuncs.com/picBed/image-20221231184035750.png)

![image-20221231184307353](https://typora-yu-pic.oss-cn-chengdu.aliyuncs.com/picBed/image-20221231184307353.png)

### 2.3、创建Groovy项目

![image-20221231233107690](https://typora-yu-pic.oss-cn-chengdu.aliyuncs.com/picBed/image-20221231233107690.png)

![image-20221231233150045](https://typora-yu-pic.oss-cn-chengdu.aliyuncs.com/picBed/image-20221231233150045.png)

### 2.4、Groovy基本语法

Groovy语法文档地址：http://www.groovy-lang.org/syntax.html

#### 2.4.1、基本语法

- Groovy是基于Java语言的，完全兼容Java语法，可作为面向对象编程语言[定义类]，也可以作为脚本型语言[文件定义中不出现类]。
- 在一个Groovy文件中可以混合类的定义和脚本定义。此时不要再定义一个和文件同名的类。
- Groovy中使用def定义变量、方法，不建议使用具体的数据类型。
- Groovy中的注释：单行注释用`//`，多行注释用`/**/`。
- Groovy中语句末尾的分号是可以省略的，以换行作为结束。
- 默认类、方法、字段都是public修饰的。
- 对象的属性操作
  - 给对象属性赋值
    - 对象.属性名=xxx。
    - 对象的setter方法。
    - 具名构造器[groovy自带的]：可用于给task指定分组，description描述信息使用。

  - 对象属性读取操作
    - 对象.属性名。
    - 对象["属性名"]。
    - 对象.getter方法。

  - 对类属性操作的本质是通过属性对应的getter、setter方法完成的。

- 方法
  - 声明时
    - 参数类型返回值类型可以省略。
    - return 关键字：默认使用方法的最后一行的返回值作为方法返回值。

  - 调用时，()可以省略。

- 支持顺序结构、分支结构、循环结构语句。
- 支持的各种运算符：算术、关系、位、赋值、范围运算符。
- 基本类型也是对象，可以直接调用对象的方法。
- groovy中的字符串有单引号、双引号、三个引号
  - 单引号：作为字符串常量使用，没有运算能力。
  - 双引号：可引用变量${}，有运算能力。
  - 三引号：模板字符串，支持换行。

- 数据类型：变量、属性、方法、闭包的参数以及方法的返回值类型都是可有可无的，都是在给变量赋值的时候才决定它的类型。

**类型转换**：当需要时，类型之间会自动发生转换：字符串(String)、基本类型(如int)和基本类型的包装类型(如Integet)。

**类说明**：如果一个groovy文件中没有任何类定义，它将被当做`Script`处理，也就意味着这个文件将被透明的转换为一个`Script类型`的类，这个自动转换得到的类将使用原始的groovy文件名作为类的名字。groovy文件的内容被打包进`run`方法，另外新产生的类被加入一个`main`方法以进行外部执行脚本。

```groovy
package com.yu.groovy

class BasicDemo {
/*--------------------groovy基本语法--------------------*/
    /*
        1.groovy中使用def定义属性、方法,def支持动态类型声明
        2.单行注释: //  多行注释: /**/
        3.gradle语句最后的;是可以省略的
        4.groovy可以为属性自动生成getter、setter方法
        5.方法声明时: 参数类型、返回值类型、return关键字可以省略,方法调用时,在不引起歧义的地方,可以省略()
        6.变量引用时: 在不引起歧义的前提下{}也可以省略,在容易引起歧义的地方不能省略{}
        7.对象属性赋值:
                方式1: 对象.属性名=
                方式2: 对象["属性名"]=
                方式3: 对象.属性setter()
                方式4: 具名构造器的方式
          读取属性值:
                方式1: 对象.属性名=
                方式2: 对象["属性名"]=
                方式3: 对象.属性getter()
     */

    // 描述信息
    def desc = "唐家三少经典作品"

    /**
     *
     *
     */
    def bookName = "斗罗大陆"

    def sale(price) {
        "This book sells for ${price} yuan"
    }
}
```

```groovy
package com.yu.groovy

def obj = new BasiceDemo();
//def result = obj.sale(100)
//print(result)
//obj.bookName = "吞噬天空"
//obj["bookName"] = "吞噬天空"
obj.setBookName("吞噬天空")
//def obj = new BasiceDemo(bookName: "吞噬天空");
//print(obj.bookName)
//print(obj["bookName"])
print(obj.getBookName())
```

#### 2.4.2、引号说明

```groovy
package com.yu.groovy

/*--------------------字符串操作--------------------*/
def desc = "测试"

def str1 = '单引号,不支持变量引用,不支持换行操作 ${desc}'

def str2 = "双引号,支持变量引用,不支持换行操作 ${desc}"

def str3 = '''三引号,模板字符串,不支持变量引用,
              支持换行操作 ${desc}'''

println(str1)
println(str2)
println(str3)

println()

println(str1.getClass().toString())
println(str2.getClass().toString())
println(str3.getClass().toString())
```

#### 2.4.3、三个语句结构

Groovy支持顺序结构从上到下依次解析，分支结构(if..else、if..else if..else..、switch..case、for、while、do..while)

Groovy条件结构部分文档地址：http://www.groovy-lang.org/semantics.html#_conditional_structures

#### 2.4.4、数据类型及权限修饰符

Groovy类型与权限部分文档地址：http://www.groovy-lang.org/objectorientation.html#_modifiers_on_a_propert

- 原生数据类型及包装类

  | Primitive type | Wrapper class |
  | -------------- | ------------- |
  | boolean        | Boolean       |
  | char           | Character     |
  | short          | Short         |
  | int            | Integer       |
  | long           | Long          |
  | float          | Float         |
  | double         | Double        |

- 类、内部类、抽象类、接口

- 注解

- Trait：可以看成是带有方法实现的接口

- 权限修饰符：public(默认)、protected、private

Groovy与Java的主要区别如下：

- Groovy类或方法的默认权限修饰符是public，Java则是default。
- 没有可见性修饰符的字段将自动转换为属性，不需要显式的getter和setter方法。
- 如果属性声明为final，则不会生成setter方法。
- 一个源文件可能包含一个或多个类(如果一个文件不包含类定义，则将其视为脚本文件)。脚本只是具有一些特殊约定的类，它们的名称与源文件相同(不要在源文件中定义一个与源文件同名的类)。

#### 2.4.5、集合操作

Groovy集合部分文档地址：http://www.groovy-lang.org/syntax.html#_lists

Groovy支持List、Map集合操作，并且拓展了Java中的API，具体参考如下方法：

**List**

- add()：添加某个元素
- plus()：添加某个list集合
- remove()：删除指定下标的元素
- removeElement()：删除某个指定的元素
- removeAll()：移除某个集合中的元素
- pop()：弹出list集合中最后一个元素
- putAt()：修改指定下标的元素
- each()：遍历
- size()：获取list列表中的元素个数
- contains()：判断列表中是否包含指定的值，包含则返回true，否则为false

**Map**

- put()：添加元素
- remove()：根据某个键移除元素
- +、-：支持map集合的加减操作
- each()：遍历map集合

**注意：可以将不同的基本类型添加到同一集合中**

```groovy
package com.yu.groovy

/*--------------------集合操作--------------------*/

//1.集合中添加元素
def list = ["a", "c", "r", "g"]
assert list instanceof List
list.add("x")
println list.size()

//2.集合相加
def list2 = ["w", "e", "t", "f"]
println list.plus(list2) //将list2添加到list之后

//3.删除指定下标元素
list.remove(3)
println(list)

//4.删除集合中指定的元素
list.removeElement("a")
println(list)

//5.从list集合中移除包含list3集合中的元素
def list3 = ["c", "r", "f"]
list.removeAll(list3)
println(list)

//6.从list集合中弹出最后一个元素
println list.pop()
println(list)

//7.指定下标元素赋值
list.putAt(3, "y")
println(list)

//8.遍历list集合
[1, 3, 5, 7].each {
    println "Item: ${it}"
}

//9.list集合中是否包含某个值
println list.contains("y")

//10.map集合初始化,键可以不使用引号,可以使用双引号,也可以使用单引号
def map = [J: "Java", "K": "Kotlin", 'G': "Groovy"]

//11.map集合中添加元素
map.put("A", "Android")
println map

//12.map集合根据键移除或键值对移除
map.remove("K")
map.remove('G', "Groovy")
println map

//13.map集合的"-" "+"操作符重载
map2 = map - ["A": "Android"]
println map2

map3 = map + ["J": "JavaScript"]
println map3

//14.map集合的遍历
map.each { key, value ->
    println "key:$key value:$value"
}
```

**注意：使用each方法遍历list集合时，隐式参数为it，遍历时使用$it得到值**

#### 2.4.6、类导入

Groovy类导入部分文档地址：http://www.groovy-lang.org/structure.html

Groovy遵循Java语法，允许使用`import`语句解析类引用的概念。

Groovy默认导入了以下类：

```groovy
import java.lang.*
import java.util.*
import java.io.*
import java.net.*
import groovy.lang.*
import groovy.util.*
import java.math.BigInteger
import java.math.BigDecimal
```

#### 2.4.7、异常处理

Groovy异常处理部分文档地址：http://www.groovy-lang.org/semantics.html#_try_catch_finally

Groovy中的异常处理和Java的异常处理是一样的。

```groovy
def z
try {
    def i = 7, j = 0
    try {
        def k = i / j
        assert false        //never reached due to Exception in previous line
    } finally {
        z = 'reached here'  //always executed even if Exception thrown
    }
} catch ( e ) {
    assert e in ArithmeticException
    assert z == 'reached here'
}
```

#### 2.4.8、闭包

Groovy闭包部分文档地址：https://www.groovy-lang.org/closures.html

闭包定义遵循以下语法：

```markdown
{ [closureParameters -> ] statements }
```

`[closureParameters->]`是可选的以逗号分隔的参数列表，statements 是 0 个或多个 Groovy 语句。参数看起来类似于方法参数列表，并且这些参数可以是类型化的或非类型化的。

指定参数列表时，`->`字符是必需的，用于将参数与闭包主体分开。语句部分由 0、1 或多个 Groovy 语句组成。

下面是一些有效闭包定义的例子：

```markdown
a.{ item++ }                                          

b.{ -> item++ }                                       

c.{ println it }                                      

d.{ it -> println it }                                

e.{ name -> println name }                            

f
{ String x, int y ->                                
    println "hey ${x} the value is ${y}"
}

g
{ reader ->                                         
    def line = reader.readLine()
    line.trim()
}
```

a：引用名为`item`变量的闭包

b：可以通过添加(`->`)箭头显式地将闭包参数与代码分开

c：使用隐式参数(`it`)的闭包

d：一个替代版本，`it`它是一个显式参数

e：在这种情况下，通常最好为参数使用显式名称

f：接受两个类型化参数的闭包

g：一个闭包可以包含多个语句

```groovy
package com.yu.groovy

def running(Closure closure) {
    println("running start......")
    closure()
    println("running end......")
}

running {{println("running......")}}


//def caculate(num1, num2, Closure closure) {
//    closure(num1, num2)
//}

def caculate(Closure closure) {
    def num1 = 2
    def num2 = 5
    closure(num1, num2)
}

//caculate(2, 5, {k, v -> println("$k + $v = ${k + v}")})
//caculate(){k, v -> println("$k + $v = ${k + v}")}
caculate{k, v -> println("$k + $v = ${k + v}")}
```

### 2.5、idea创建普通Java工程

- 创建工程

  ![image-20230101174354398](https://typora-yu-pic.oss-cn-chengdu.aliyuncs.com/picBed/image-20230101174354398.png)

  ![image-20230101174455970](https://typora-yu-pic.oss-cn-chengdu.aliyuncs.com/picBed/image-20230101174455970.png)

- IDEA修改Gradle配置

  ![image-20230101175441906](https://typora-yu-pic.oss-cn-chengdu.aliyuncs.com/picBed/image-20230101175441906.png)

**注意：1、Terminal中以gradlew开头指令用的Wrapper规定的gradle版本，Wrapper中规定的默认版本和idea插件中规定的版本一致。**

​			**2、图形化的IDEA使用的命令是自定义的Gradle版本。**

​			**3、IDEA2021目前只能在创建项目后重新设置本地Gradle，IDEA2022可以在创建项目时指定本地Gradle**

### 2.6、idea创建普通Web工程

- 创建工程

  ![image-20230101183659214](https://typora-yu-pic.oss-cn-chengdu.aliyuncs.com/picBed/image-20230101183659214.png)

  ![image-20230101183729652](https://typora-yu-pic.oss-cn-chengdu.aliyuncs.com/picBed/image-20230101183729652.png)

- 修改Gradle配置

  ![image-20230101183902659](https://typora-yu-pic.oss-cn-chengdu.aliyuncs.com/picBed/image-20230101183902659.png)

- 创建web.xml文件

  ![image-20230101184623892](https://typora-yu-pic.oss-cn-chengdu.aliyuncs.com/picBed/image-20230101184623892.png)

### 2.7、Gradle项目部署

#### 2.7.1、IDEA部署Web项目

- 设置编译后文件的输出目录

  ![image-20230102134910717](https://typora-yu-pic.oss-cn-chengdu.aliyuncs.com/picBed/image-20230102134910717.png)

- 设置编译方式为IDEA

  ![image-20230102133437434](https://typora-yu-pic.oss-cn-chengdu.aliyuncs.com/picBed/image-20230102133437434.png)

- 导入相关依赖

  ```groovy
  // build.gradle
  
  plugins {
      id 'war'
  }
  
  group 'com.yu.gradle'
  version '1.0-SNAPSHOT'
  
  repositories {
      mavenCentral()
  }
  
  dependencies {
      providedCompile 'javax.servlet:javax.servlet-api:3.1.0'
      testImplementation 'junit:junit:4.12'
  }
  ```

- 编写代码

  - webapp下添加index.jsp

    ```jsp
    <%--
      Created by IntelliJ IDEA.
      Date: 2023/1/1
      Time: 18:37
      To change this template use File | Settings | File Templates.
    --%>
    <%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <html>
    <head>
      <title>Hello Page</title>
    </head>
    <body>
    <h2>Hello, ${username}!</h2>
    </body>
    </html>
    ```

  - webapp下添加index.html

    ```html
    <html lang="en">
    <head>
        <title>Web Demo</title>
    </head>
    <body>
    <p>Say <a href="hello">Hello</a></p>
    
    <form method="post" action="hello">
        <h2>Name:</h2>
        <label for="say-hello-text-input"></label><input type="text" id="say-hello-text-input" name="name"/>
        <input type="submit" id="say-hello-button" value="Say Hello"/>
    </form>
    </body>
    </html>
    ```

  - 添加servlet类

    ```java
    package com.yu.gradle;
    
    import javax.servlet.ServletException;
    import javax.servlet.http.HttpServlet;
    import javax.servlet.http.HttpServletRequest;
    import javax.servlet.http.HttpServletResponse;
    import java.io.IOException;
    
    public class HelloServlet extends HttpServlet {
    
    	@Override
    	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    		resp.getWriter().print("Hello World!");
    	}
    
    	@Override
    	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    		String name = req.getParameter("name");
    		if (name == null) {
    			name = "World";
    		}
    		req.setAttribute("username", name);
    		req.getRequestDispatcher("index.jsp").forward(req, resp);
    	}
    }
    ```

- 配置请求访问方式

  - web.xml

    在webapp目录下创建WEB-INF文件夹，再在WEB-INF文件夹下创建web.xml文件

    ```xml
    <?xml version="1.0" encoding="UTF-8"?>
    <web-app xmlns="http://xmlns.jcp.org/xml/ns/javaee"
             xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
             xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee
                      http://xmlns.jcp.org/xml/ns/javaee/web-app_4_0.xsd"
             version="4.0"
             metadata-complete="false">
    
        <servlet>
            <servlet-name>HelloServlet</servlet-name>
            <servlet-class>com.yu.gradle.HelloServlet</servlet-class>
        </servlet>
    
        <servlet-mapping>
            <servlet-name>HelloServlet</servlet-name>
            <url-pattern>/hello</url-pattern>
        </servlet-mapping>
    </web-app>
    ```

  - WebServlet注解

    在servlet类上配置@WebServlet

    ```java
    package com.yu.gradle;
    
    import javax.servlet.ServletException;
    import javax.servlet.annotation.WebServlet;
    import javax.servlet.http.HttpServlet;
    import javax.servlet.http.HttpServletRequest;
    import javax.servlet.http.HttpServletResponse;
    import java.io.IOException;
    
    @WebServlet("/hello")
    public class HelloServlet extends HttpServlet {
    
    	@Override
    	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    		resp.getWriter().print("Hello World!");
    	}
    
    	@Override
    	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    		String name = req.getParameter("name");
    		if (name == null) {
    			name = "World";
    		}
    		req.setAttribute("username", name);
    		req.getRequestDispatcher("index.jsp").forward(req, resp);
    	}
    }
    ```

- 配置本地tomcat

  ![image-20230102153221438](https://typora-yu-pic.oss-cn-chengdu.aliyuncs.com/picBed/image-20230102153221438.png)

  ![image-20230102153314018](https://typora-yu-pic.oss-cn-chengdu.aliyuncs.com/picBed/image-20230102153314018.png)

  ![image-20230102153433326](https://typora-yu-pic.oss-cn-chengdu.aliyuncs.com/picBed/image-20230102153433326.png)

  ![image-20230102153542931](https://typora-yu-pic.oss-cn-chengdu.aliyuncs.com/picBed/image-20230102153542931.png)

  ![image-20230102153631838](https://typora-yu-pic.oss-cn-chengdu.aliyuncs.com/picBed/image-20230102153631838.png)

#### 2.7.2、Gretty部署项目

Gretty官网地址：http://akhikhl.github.io/gretty-doc/index.html

Gretty是一个功能丰富的`Gradle插件`，用于在`嵌入式的servlet容器`上运行web应用程序，让项目开发和部署更加简单。目前Gretty插件已经作为Gradle的核心库使用了，Gretty的核心功能包含以下几点：

1、底层支持jetty，tomcat等servlet容器。

2、支持项目热部署、HTTPS、调试。

**Gretty使用**

- 引入Gretty插件

  ```groovy
  // build.gradle
  
  plugins {
      id 'war'
      id 'org.gretty' version '2.2.0'
  }
  ```

- 指定Maven仓库

  ```groovy
  // build.gradle
  
  repositories {
      // 指定jcenter仓库，一定要放在前面
      jcenter()
      mavenCentral()
  }
  ```

- 设置Gretty插件

  ```groovy
  // build.gradle
  
  greety{
      httpPort=8888
      contextPath="/web"
      // 默认端口为5005
      debugPort=5005
      // 默认为true
      debugSuspend=true
      httpsEnabled=true
      // 修改类之后重新加载
      managedClassReload=true
      // 如果不指定servlet容器,默认使用的是jetty容器
      servletContainer='tomcat8'
      httpsPort=4431
  }
  ```

- 执行Gretty插件

  ```shell
  gradle appRun
  ```

**注意事项：1、下载tomcat时请下载二进制压缩包(即不带src的压缩包)；**

​				  **2、Gradle插件设置为使用IDEA编译。**

### 2.8、Gradle测试

测试任务自动检测并执行测试源中的所有单元测试，测试完成后会生成一个测试报告。支持JUnit和TestNG测试。

#### 2.8.1、默认的测试标准输出

```markdown
├─build
   ├─classes
|  |   ├─main
|  |   └─test
|  ├─reports                                                         
|  |   └─test															     
|  └─test-results 
```

#### 2.8.2、JUnit使用

- Gradle对JUnit4.x支持

  ```groovy
  // build.gradle
  
  dependencies {
      testImplementation 'junit:junit:4.12'
  }
  
  test {
      useJUnit()
  }
  ```

  ```java
  package com.yu.gradle.test1;
  
  
  import org.junit.Test;
  
  public class TestDemo {
  
  	@Test
  	public void test() {
  		System.out.println("this is a junit4 test");
  	}
  }
  ```

- Gradle对JUnit5.x支持

  ```groovy
  // build.gradle
  
  dependencies {
      testImplementation 'org.junit.jupiter:junit-jupiter-api:5.7.0'
      testRuntimeOnly 'org.junit.jupiter:junit-jupiter-engine:5.7.0'
  }
  
  test {
      useJUnitPlatform()
  }
  ```
  
  ```java
  package com.yu.gradle.test2;
  
  import org.junit.jupiter.api.Test;
  
  public class TestDemo {
  
  	@Test
  	public void test() {
  		System.out.println("this is a junit5 test");
  	}
  }
  ```

注意：无论是JUnit4.x还是JUnit5.x版本，只需要在`build.gradle`目录下执行`gradle test`指令，gradle就会执行所有加了`@Test`注解的测试，并生成测试报告。

#### 2.8.3、包含和排除特定测试

```groovy
// build.gradle

test {
    // 是否开启测试,true:开启 false:关闭
    enabled(true)
    // 支持junit5测试
    useJUnitPlatform()
    // 执行指定包下的测试
//    include("com/yu/gradle/test1/**")
    // 排除指定包下的测试
    exclude("com/yu/gradle/test1/**")
}
```

## 三、Gradle进阶

### 3.1、项目生命周期

Gradle项目的生命周期分为三个阶段，分别是 `Initialization (初始化)`、`Configuration (配置)` 和 `Execution (执行)`，且任何的构建使命都会经过这个三个阶段。

- Initialization

  主要目的是初始化构建，它又分为两个子过程，一个是执行`Init Script`，另一个是执行`Settting Script`。`init.gradle`文件会在每个项目build之前被调用，用于一些初始化的操作，它主要有以下作用：

  1. 配置内部的仓库信息(如公司的Maven仓库信息)。
  2. 配置一些全局属性。
  3. 配置用户名及密码信息(如公司仓库的用户名和密码)。

  `Settting Script`初始化了一次构建所参与的所有模块。

- Configuration

  这个阶段开始加载项目中所有模块的`Build Script`。所谓"加载"就是执行`build.gradle`中的语句，根据脚本代码创建对应的`task`，最终根据所有task生成由Task组成的`有向无环图`。

- Execution

  这个阶段会根据上个阶段构建好的有向无环图，按顺序执行Task[Action动作]。

### 3.2、settings文件

- 作用

  主要是在`项目初始化阶段`确定一下引入`哪些工程`需要加入到项目构建中，为`构建项目工程树`做准备。

- 项目工程树

  Gradle中有工程树的概念，类似于Maven中的project和module。

- 主要内容

  里面主要定义了当前Gradle项目及子project的项目名称。

- 文件目录位置

  必须放在根工程目录下。

- 文件名

  固定为`settings.gradle`，不能修改。

- 对应实例

  与`org.gradle.api.initialization.Settings`实例是一一对应关系。每个项目只有一个settings文件。

```groovy
// setting.gradle

// 根工程项目名
rootProject.name = 'gradle-parent-demo'
// 包含的子工程
include 'gradle-first-subject-demo'
include 'gradle-second-subject-demo'
include 'gradle-third-subject-demo'

// 包含的子工程下的子工程
include 'gradle-third-subject-demo:demo1'
```

**注意：作为开发者只需要关注该文件的`include`方法即可。使用`相对路径`引入子工程，一个子工程只有在`setting`文件中配置了才会被Gradle识别，这样在构建的时候才会被包含进去。项目工程中":"代表项目的分隔符，类似于路径中的"/"，如果以":"开头则表示相对于root project。然后Gradle会为每个带有`build.gradle`脚本文件的工程构建一个与之对应的project对象。**

### 3.3、Tasks

项目实质上是`Task对象`的集合。一个Task表示一个逻辑上较为`独立`的执行过程，比如`编译Java源代码，拷贝文件，打包jar文件，甚至可以是执行一个系统命令`。另外，一个Task可以读取和设置`project的property`以完成特定的操作。

#### 3.3.1、Task入门

Task官网文档地址：https://docs.gradle.org/current/userguide/tutorial_using_tasks.html

```groovy
// build.gradle

task("taskDemo") {
    // 任务的配置段:在配置阶段完成
    println("这是一个简单任务demo")
    // 任务的行为:在执行阶段执行,doFirst会在doFirst执行之前执行
    doFirst {
        println("taskDemo doFirst")
    }

    doLast {
        println("taskDemo doLast")
    }
}
```

在项目目录下使用以下命令执行一个task任务：

```shell
# -i表示输出info及info级别以上的日志
gradle -i 任务名
```

注意以下内容：

1. task的`配置段`是在`配置阶段`完成。
2. task的`doFirst、doLast方法`是在`执行阶段`完成，并且doFirst会在doFirst执行之前执行
3. 区分任务的配置段和任务的行为：任务的`配置段`是在`配置阶段`完成，任务的`行为`是在`执行阶段`执行。

#### 3.3.2、任务的行为

doFirst、doLast可以在方法的内部定义，也可以在方法的外部定义。

```groovy
// build.gradle

def map = new LinkedHashMap<String, Object>(16)
map.put("action", {println "custom task action"})

task(map, "taskDemo") {
    // 任务的配置段:在配置阶段完成
    println("这是一个简单任务demo")
    // 任务的行为:在执行阶段执行,doFirst会在doFirst执行之前执行
    doFirst {
        println("taskDemo doFirst")
    }

    doLast {
        println("taskDemo doLast")
    }
}

taskDemo.doFirst {
    println("taskDemo doFirst outer")
}

taskDemo.doLast {
    println("taskDemo doLast outer")
}
```

**任务执行底层原理**

doFirst：每次执行时都将当前doFirst放入list中的头部

doLast：每次执行时都将当前doLast放入list中的尾部

无论是定义任务自身的action，还是添加的doFirst、doLast方法，其实底层都是被放入到一个action的list中。最初这个list是空的，当我们设置了action之后，它先将action加入到list，此时list中只有一个action，后续执行doFirst的时候将doFirst添加到action前面，执行doLast的时候将doLast添加到action后面。

**注意：该语法已被废弃**

```groovy
// build.gradle

// 其中<<代表doLast,在gradle5.x版本之后就被废弃
task hello << {
    println 'Hello World!'
}
```

#### 3.3.3、任务的依赖方式

Task之间的依赖关系可以在以下几部分设置

1. 参数依赖

   ```groovy
   // build.gradle
   
   task A {
       doLast{
           println "Task A..."
       }
   }
   
   task 'B' {
       doLast{
           println "Task B..."
       }
   }
   
   task "C" {
       doLast{
           println "Task C..."
       }
   }
   
   // 参数依赖方式: dependsOn后面用冒号
   task "D" (dependsOn: ['A', 'B', 'C']) {
       doLast{
           println "Task D..."
       }
   }
   ```

   执行任务

   ```shell
   gradle D
   ```

2. 内部依赖

   ```groovy
   // build.gradle
   
   // 参数依赖方式: dependsOn后面用冒号
   task "E"  {
       // 内部依赖: dependsOn后面用等号
       dependsOn=['A', 'C', 'D']
       doLast{
           println "Task E..."
       }
   }
   ```

   执行任务

   ```shell
   gradle E
   ```

3. 外部依赖

   ```groovy
   // build.gradle
   
   // 外部依赖方式: 任务名.dependsOn方式
   E.dependsOn=['A', 'C', D]
   ```

   执行任务

   ```shell
   gradle E
   ```

4. 跨项目依赖

   在`gradle-first-subject-demo`工程的`build.gradle`文件中定义以下内容：

   ```groovy
   // build.gradle
   
   task A {
       doLast{
           println "Task A..."
       }
   }
   ```

   在`gradle-second-subject-demo`工程的`build.gradle`文件中定义以下内容：
   
   ```groovy
   // build.gradle
   
   task 'B' {
       // 跨项目依赖,依赖根工程下的gradle-first-subject-demo中的任务A
       dependsOn(":gradle-first-subject-demo:A")
       doLast{
           println "Task B..."
       }
   }
   ```
   
   在`gradle-second-subject-demo`工程下执行以下命令：
   
   ```shell
   gradle B
   ```

**注意：重复依赖的任务只会执行一次。**

**比如A依赖B、C，B也依赖C，执行任务A的时候，显然任务C被重复依赖了，C只会执行一次。**

#### 3.3.4、任务执行

任务执行语法：`gradle [taskName...] [--option-name...]`

| 分类             | 解释                                                         |
| ---------------- | ------------------------------------------------------------ |
| 常见的任务（*）  | gradle build：构建项目、编译、测试、打包等操作<br/>gradle run：运行一个服务，需要application插件支持，并且指定了主启动类才能运行<br/>gradle clean：删除build目录<br/>gradle init：初始化gradle项目<br/>gradle wrapper：生成wrapper文件夹<br/>gradle wrapper --gradle-version=verison：升级wrapper版本<br/>gradle wrapper --gradle-version version --distribution-type all：关联源码 |
| 项目报告相关任务 | gradle projects：列出所选项目及子项目列表，以层次结构的形式显示<br/>gradle tasks：列出所选项目[当前project，不包含父、子项目]的已分配给任务组的那些任务<br/>gradle tasks --all：列出所选项目的所有任务<br/>gradle tasks --group="build setup"：列出所选项目中指定分组中的任务<br/>gradle help --task taskName：显示某个任务的详细信息<br/>gradle dependencies：查看整个项目的依赖信息，以依赖树的方式显示<br/>gradle properties：列出所选项目的属性列表 |
| 调试相关选项     | -h，--help：查看帮助信息<br/>-v，--version：打印Gradle、Groovy、Ant、JVM和操作系统版本信息<br/>-S，--full-stacktrace：打印出所有异常的完整堆栈跟踪信息<br/>-Dorg.gradle.daemon.debug=true：调试Gradle守护进程<br/>-Dorg.gradle.debug=true：调试Gradle客户端(非daemon)进程<br/>-Dorg.gradle..debug.port=port number：指定启用调试时要侦听的端口号，默认为5005 |
| 性能选项         | --build-cache，--no-build-cache：尝试重用先前版本的输出，默认为关闭(off)<br/>--max-workers：设置Gradle可以使用的worker数，默认值时处理器数<br/>-parallel，--no-parallel：并行执行项目，默认设置为关闭(off) |
| 守护进程选项     | --daemon，--no-daemon：使用Gradle守护进程运行构建，默认是on<br/>--foregroud：在前台进程中启动Gradle守护进程<br/>-D.org.gradle.daemon.idletimeout=(number of milliseconds)：Gradle将在这个空闲时间的毫秒数之后停止自己，<br/>默认为<10800000(three hours) |
| 日志选项         | -D.org.gradle.logging.level=[quiet,warn,lifecycle,indo,debug]：通过Gradle属性设置日志记录级别，<br/>-q，--quiet：只能记录错误信息<br/>-w，--warn：设置日志级别为warn<br/>-i，--info：将日志级别设置为info<br/>-d，--debug：登录调试模式(包括正常的堆栈跟踪) |
| 其他             | -x，--exclude-task：常见用法为gradle -x test clean build<br/>--return-tasks：强制执行任务，忽略up-to-date，常见用法为gradle build --return-tasks<br/>--continue：忽略前面失败的任务，继续执行，而不是在遇到第一个失败时立即停止执行，每个遇到的故障<br/>都将在构建结束时报告，常见用法为gradle build --continue<br/>gradle init --type pom：将maven项目转换为gradle项目(需要在项目根目录下执行)<br/>gradle [taskName]：执行自定义任务 |
|                  |                                                              |

如果使用`System.out.println()`输出控制台出现`中文乱码`，请在当前`module`中`build.gradle`中加入如下代码：

```groovy
// build.gradle

tasks.withType(JavaCompile) {
    options.encoding = "UTF-8"
}
```

#### 3.3.5、任务定义方式

任务定义方式总体分为两大类：一种是通过`Project`中的`task`方法，另一种是通过`task对象`的`create或者register`方法

```groovy
// build.gradle

task('A', {
    println "task A..."
})

task('B') {
    println "task B..."
}

task C {
    println "task C..."
}

def map = new LinkedHashMap(16)
map.put("action", {
    println "task D..."
})
task(map, "D")

// 使用task的create方法创建
tasks.create('E') {
    println "task E..."
}

// register执行的是延迟创建,只有当task被需要使用的时候才会创建
tasks.register("F") {
    println "task F..."
}
```

可以在定义任务的同时指定任务的属性，具体属性有：

| 配置项      | 描述                                             | 默认值      |
| ----------- | ------------------------------------------------ | ----------- |
| type        | 基于一个存在的Task来创建，和Java中的类继承差不多 | DefaultTask |
| overwrite   | 是否替换存在的Task，建议搭配type使用             | fasle       |
| dependsOn   | 用于配置任务的依赖                               | []          |
| action      | 添加到任务中的一个Action或者一个闭包             | null        |
| description | 用于配置任务的描述                               | null        |
| group       | 用于配置任务的分组                               | null        |

在定义任务的同时也可以给任务分配属性：

- 定义任务的时候直接指定任务属性
- 给已有的任务动态分配属性

```groovy
// build.gradle

// G是任务名,通过具名参数给map赋值,以参数方式指定任务的属性信息
task(group: "yu", description: "this is G task", "G")

// H是任务名,定义任务的同时,在内部直接指定属性信息
task("H") {
    group("yu")
    description("this is H task")
}

// Y是任务名,给已有的任务 在外部直接指定属性信息
task 'Y' {}
Y.group = "yu"
// 给已有的clean任务重新指定分组信息
clean.group("yu")
```

#### 3.3.6、任务类型

Gradle任务类型部分官方文档地址：https://docs.gradle.org/current/dsl/org.gradle.api.Task.html

常见的任务类型如下：

| 常见任务类型             | 该类型任务的作用                                             |
| ------------------------ | ------------------------------------------------------------ |
| Delete                   | 删除文件或目录                                               |
| Copy                     | 将文件复制到目标目录中，此任务还可以在复制时重命名和筛选文件 |
| CreateStarScripts        | 创建启动脚本                                                 |
| Exec                     | 执行命令行进程                                               |
| GenerateMavenPom         | 生成POM文件                                                  |
| GradleBuild              | 执行Gradle构建                                               |
| Jar                      | 生成JAR包                                                    |
| JavaCompile              | 编译Java源文件                                               |
| Javadoc                  | 为Java类生成HTML API文档                                     |
| PublishToMavenRepository | 将MavenPublication发布到mavenartifactrepostal                |
| Tar                      | 生成TAR包                                                    |
| Test                     | 执行JUnit(3.8.x，4.x或5.x)或TestNG测试                       |
| Upload                   | 将Configuration的构件上传到一组存储库                        |
| War                      | 生成WAR包                                                    |
| Zip                      | 生成ZIP压缩包，默认是压缩ZIP的内容                           |

示例：使用指定任务类型删除proejct下的build目录

```groovy
// build.gradle

tasks.register("myClean", Delete) {
    delete buildDir
}
```

```shell
gradle Myclean
```

**自定义任务类型**

```groovy
// build.gradle

// 自定义执行的任务类型
class CustomTask extends DefaultTask {

    // @TaskAction表示本身任务执行的类型
    @TaskAction
    def doSelf() {
        println "自定义任务执行的doSelf"
    }
}

def myTask = task MyDefinitionTask(type: CustomTask)
myTask.doFirst() {
    println "自定义任务执行的doFirst"
}

myTask.doLast() {
    println "自定义任务执行的doLast"
}
```

```shell
gradle MyDefinitionTask
```

#### 3.3.7、任务的执行顺序

Gradle任务执行顺序部分官方文档地址：https://docs.gradle.org/current/dsl/org.gradle.api.Task.html

在Gradle中有三种方式可以指定Task执行顺序：

1. `dependsOn`强依赖方式
2. 通过`Task`输入输出
3. 通过`API`指定执行顺序

#### 3.3.8、动态分配任务

```groovy
// build.gradle

// 循环注册任务
4.times (counter -> {
    tasks.register("task$counter") {
        println "I'm task $counter"
    }
})

// 指定某个任务强制依赖多个任务
tasks.named("task0") {dependsOn("task1", "task2")}
```

#### 3.3.9、任务的关闭与开启

每个任务都有一个`enabled`标签，默认为`true`，将其设置为`false`，即可阻止任何任务动作，禁用的任务将被标记为`跳过`

```groovy
// build.gradle

task disableMe {
    doLast() {
        println "This task is Executing..."
    }
    // 默认为true
    // enabled(false)
}

// 也可在任务外部声明
disableMe.enabled=false
```

#### 3.3.10、任务的超时

每个任务都有一个`timeout`，可用于限制其执行时间的属性。当任务达到超时时，其任务执行线程将被中断，该任务将被标志失败，终结器任务仍将运行。

如果`--continue`使用，其他任务可以在此之后继续执行，不响应中断的任务无法超时，Gradle的所有内置任务均会及时响应超时。

```groovy
// build.gradle

task a {
    doLast() {
        Thread.sleep(1000)
        println "当前任务a执行了..."
    }
    timeout = Duration.ofMillis(500)
}

task b {
    doLast() {
        println "当前任务b执行了..."
    }
}
```

```shell
# 任务a、b都不会执行
gradle a b
```

```shell
# 任务a执行失败,任务b执行
gradle a b --continue
```

#### 3.3.11、任务的查找

- 根据任务名查找

  ```groovy
  // build.gradle
  
  // gradle-parent-demo工程中
  
  task huawei {
      doLast() {
          println "现代黄埔军校: 华为总部"
      }
  }
  
  tasks.findByName("huawei").doFirst() {println "分公司3: 华为广州分公司"}
  tasks.findByName("huawei").doFirst() {println "分公司4: 华为成都分公司"}
  ```

  ```shell
  gradle huawei
  ```

- 根据任务路径(相对路径)查找

  ```groovy
  // build.gradle
  
  // gradle-first-subject-demo工程中
  
  tasks.findByPath(":huawei").doFirst() {println "分公司1: 华为上海分公司"}
  tasks.getByPath(":huawei").doFirst() {println "分公司2: 华为北京分公司"}
  ```
  
  ```shell
  gradle huawei
  ```

#### 3.3.12、任务的规则

当我们执行、依赖一个不存在的任务时，Gradle会执行失败，打印错误信息。可以自定义规则，当执行一个不存在的任务时，不报错而打印提示信息。

```groovy
// build.gradle

task hello {
    doLast() {
        println "hello xiaobizaizi"
    }
}

tasks.addRule("口令: 天王盖地虎") {
    String taskName -> task(taskName) {
        doLast {
            println "the $taskName is not exist"
        }
    }
}
```

```shell
>gradle aa hello

> Task :aa
the aa is not exist

> Task :hello
hello xiaobizaizi
```

#### 3.3.13、任务的onlyIf断言

断言就是一个`条件表达式`。Task有一个`onlyIf`方法，它接受一个`闭包`作为参数，如果该闭包返回true则该任务执行，否则跳过。这有很多用途，比如控制程序哪些情况下打什么包，什么时候执行单元测试等等。具体案例如下：

hello任务没有school属性时执行，有则不执行

```groovy
// build.gradle

task hello {
    doLast() {
        println "hello xiaobizaizi"
    }
}

hello.onlyIf{!project.hasProperty("school")}
```

```shell
>gradle aa hello

> Task :hello
hello xiaobizaizi
```

```shell
>gradle aa hello

> Task :hello
hello xiaobizaizi
```

```shell
>gradle hello -Pschool


```

#### 3.3.14、默认任务

Gradle可以使用`defaultTasks`标签定义一个或多个在没有指定其他任务执行时的默认任务。

```groovy
// build.gradle

defaultTasks 'myRun','myStart'

tasks.register('myRun') {
    doLast {
        println "this is a myRun task"
    }
}

tasks.register('myStart') {
    doLast {
        println "this is a myStart task"
    }
}

tasks.register('other') {
    doLast {
        println "this is a other task"
    }
}
```

```shell
>gradle -q
this is a myRun task
this is a myStart task
```

### 3.4、Gradle中的文件操作

Gradle文件操作官网文档地址：https://docs.gradle.org/current/userguide/working_with_files.html

#### 3.4.1、本地文件

使用`Project.file(java.lang.Object)`方法，通过指定文件的`相对路径或绝对路径`来对文件的操作，其中相对路径为相对当前`project[根peoject或者子project]`的目录。其实使用Project.file(java.lang.Object)方法创建的File对象就是Java中的File对象，我们可以像在Java中使用一样操作它。

- 相对路径方式(当前工程相对于根工程或者子工程)

  ```groovy
  // build.gradle
  
  // 相对路径
  File relativeFile = file("src/config.xml")
  relativeFile.createNewFile()
  ```

- 绝对路径方式

  ```groovy
  // build.gradle
  
  // 绝对路径
  File absoluteFile = file("D:\\hello.conf")
  absoluteFile.createNewFile()
  ```

- 使用一个文件对象

  ```groovy
  // build.gradle
  
  // 绝对路径
  File absoluteFile = new File("D:\\config.xml")
  absoluteFile.createNewFile()
  ```

#### 3.4.2、文件集合

文件集合就是一组文件的列表，在Gradle中，文件集合用FileCollection接口表示。可以使用`Project.file(java.lang.Object[])`方法来获得一个文件集合对象。

```groovy
// build.gradle

// 文件集合
FileCollection fileCollection = files("src/text1.html", ["src/text2.html", "src/text3.html"])
fileCollection.forEach(item -> {
//    println item.name
    item.createNewFile()
})

// 文件集合转set
Set set1 = fileCollection.files

Set set2 = fileCollection as Set
for (final def item in set2) {
    println item.name + "..."
}

// 文件集合转List
List list = fileCollection as List
for (final def item in list) {
    println item.exists()
}

// 添加或删除一个元素
def union = fileCollection + files("src/text4.html")
def minus = fileCollection - files("src/text2.html")
println "=====union====="
union.forEach() {it -> {
    println it.name
}}
println "=====minus====="
minus.forEach() {it -> {
    println it.name
}}
```

#### 3.4.3、文件树

文件树是有`层级结构`的`文件集合`，一个文件树可以代表`一个目录结构`或ZIP压缩包里的`内容结构`。文件树是从文件集合`继承`过来的，所以文件树具有文件集合`所有的功能`。可以使用`Project.fileTree(java.util.Map)`方法来创建文件树对象，还可以使用`过滤条件`来`包含或排除`相关文件。

```groovy
// build.gradle

// 使用路径创建文件树对象,同时指定包含的文件
def fileTrees
fileTrees = fileTree("src/main")
fileTrees.include("**/*.java").forEach(item -> {
    println item.name
})

// 通过闭包创建文件树
fileTrees = fileTree('src/main') {
    include '**/*.java'
}
fileTrees.forEach(item -> {
    println item.name
})

// 通过路径和闭包创建文件树,使用具名参数给map传值
fileTrees = fileTree(dir: 'src/main', include: '**/*.java')
fileTrees.forEach(item -> {
    println item.name
})

fileTrees = fileTree(dir: 'src/main', includes: ['**/*.java', '**/*.xml'], exclude: '**/test*/**')
fileTrees.forEach(item -> {
    println item
    println item.name
})
```

#### 3.4.4、文件拷贝

可以使用`Copy任务`来拷贝文件，通过它可以`过滤`指定拷贝内容，还能对文件进行`重命名`操作等。Copy任务必须指定`一组需要拷贝的文件和需要拷贝的目录`，这里使用`CopySpec.from(java.lang.Object[])`方法指定原文件，使用`CopySpec.into(java.lang.Object)`方法指定目标目录。

from()方法接受的参数和文件集合files()一样，into()方法接受的参数和本地文件file()一样。当参数为一个目录时，该目录下的所有文件都会被拷贝到指定目录下(目录本身不会被拷贝)；当参数为一个文件时，该文件会被拷贝到指定目录；如果参数指定的文件不存在，则会被忽略；当参数为一个Zip时，该压缩包的内容会被拷贝到指定目录。

```groovy
// build.gradle

task copyTask(type: Copy) {
//    // 拷贝src/main/webapp目录下所有文件
//    from('src/main/webapp')
//
//    // 拷贝单独的一个文件
//    from('src/main/config.xml')
//
//    // 从Zip压缩包中拷贝内容
//    from zipTree('src/main/test.zip')

//    from 'src/main/'
//    // 过滤文件
//    include("**/*.java")
//    exclude("**/*.txt")

    from 'src/main/config.xml'
    // 拷贝过程中重命名文件
    rename { String fileName ->
        fileName.replace('config', 'hello')
    }

    // 拷贝到的目标目录
    into 'build/explodedWar'
}
```

右键执行`run`运行

```groovy
// build.gradle

// 直接使用Project的copy方法
copy {
    // 相对路径或绝对路径
    from file('src/main/config.xml')
    into this.buildDir.absolutePath
}
```

执行`gradle build`命令

#### 3.4.5、归档文件

通常一个项目会有很多的Jar包，如果希望将项目打包成一个WAR、ZIP或TAR包进行发布，我们可以使用Zip、War、Tar和Ear任务来实现。他们的实现方式都一样。

**项目打包成Zip**

```groovy
// build.gradle

// 可将Zip替换为Jar、War、Tar
task myZip(type: Zip) {
    from('src/main')

    into 'build'

    baseName('myFile')
}
```

执行命令`gradle -q myZip`进行打包

```shell
gradle -q myZip
```

可以使用`Project.zipTree(java.lang.Object)`和`Project.tarTree(java.lang.Object)`方法来创建`访问Zip压缩包`的文件树对象

```groovy
// build.gradle

// 使用zipTree
FileTree zip = zipTree('src/main/test.zip')

// 使用tarTree
FileTree tar = tarTree('src/main/test.tar')
```

### 3.5、Dependencies

#### 3.5.1、依赖的方式

>  Gradle中依赖有三种，直接依赖、项目依赖、本地jar依赖。

**直接依赖**

```groovy
// build.gradle

dependencies {
    testImplementation 'org.junit.jupiter:junit-jupiter-api:5.7.0'
    testRuntimeOnly 'org.junit.jupiter:junit-jupiter-engine:5.7.0'
}
```

**项目依赖**

```groovy
// build.gradle

dependencies {
    implementation project(':gradle-second-subject-demo')
}
```

**本地jar依赖**

```groovy
// build.gradle

// 可以通过文件树、文件集合的方式
dependencies {
    implementation files('/lib/jjwt.jar', '/lib/commons-io.jar')
    implementation fileTree(dir: 'lib', includes: ['*.jar'], excludes: ['*.txt'])
}
```

#### 3.5.2、依赖的下载

当执行`gradle build`指令时，gradle就会去配置的依赖仓库中下载对应的jar，并应用到项目中。

#### 3.5.3、依赖的类型

Gradle依赖范围关系和说明官方文档地址：https://docs.gradle.org/current/userguide/java_library_plugin.html

Gradle依赖范围升级和移除官方文档地址：https://docs.gradle.org/current/userguide/upgrading_version_6.html

类似于Maven的`scope`标签，gradle也提供了依赖的类型，如下所示：

| 依赖类型          | 依赖说明                                                     |
| ----------------- | ------------------------------------------------------------ |
| compileOnly       | 由Java插件提供，以前叫provided，后续版本已经改成了compileOnly，适用于编译期需要而不需要打包的情况 |
| runtimeOnly       | 由Java插件提供，只在运行期有效，编译时不需要，比如MySQL的驱动包。取代老版本中被移除的runtime |
| implementation    | 由Java插件提供，针对源码[src/main]目录，在编译、运行期都有效。取代老版本中被移除的compile |
| testCompileOnly   | 由Java插件提供，用于编译测试的依赖项，运行时不需要           |
| tetRuntimeOnly    | 由Java插件提供，只在测试运行时有效，在测试编译时不需要。取代老版本中被移除的testRuntime |
| tetImplementation | 由Java插件提供，针对源码[src/test]目录，取代老版本中被移除的testCompile |
| providedCompile   | 由War插件提供，编译、测试阶段需要依赖此类jar包，而运行阶段容器已经提供了相应的支持，所以无需将这些<br/>文件打入war包。例如：servelt-api.jar，jsp-api.jar |
| compile           | 编译依赖范围在所有的classpath中可用，同时他们也会被打包。在gradle7.0已被移除 |
| runtime           | runtime范围在测试和运行的时候有效，在编译时不需要。在gradle7.0已被移除 |
| api               | java-library插件提供支持，这些依赖项可以传递性的导出给使用者，用于编译和运行时。取代老版本中被移除的compile |
| compileOnlyApi    | java-library插件提供支持，在声明模块和使用者编译时需要的依赖项，但在运行时不需要 |

**注意：java插件提供的功能，java-library插件都提供。**

#### 3.5.4、api与implementation区别

|          |                      api                       |                implementation                |
| :------: | :--------------------------------------------: | :------------------------------------------: |
|  编译时  | 能进行依赖传递，底层变，全部都要变，编译速度慢 | 不能进行依赖传递，不用全部都要变，编译速度快 |
|  运行时  |    运行时会加载，所有模块的class都要被加载     |   运行时会加载，所有模块的class都要被加载    |
| 应用场景 |         适用于多模块依赖，避免重复依赖         |         多数情况下使用implementation         |

有A、B、C、D四个模块：

- A implementation B，B implementation C，则A不能使用C。

- A implementation B，B API C，则A可以使用C。
- A implementation B，B implementation C，C API D，则B可以使用D，但A不可以使用D。
- A implementation B，B API C，C API D，则A可以使用D。
- 不管ABCD在何处，被添加到类路径都一样，在运行时这些模块中的class都是要被加载的。

注意：除非涉及到多模块依赖，为了避免重复依赖使用API，其他情况下都是优先选择implementation。

#### 3.5.5、依赖冲突及解决方法

**依赖冲突**

在编译过程中，如果存在某个依赖的`多个版本`，构建系统时应该选择哪个进行构建的问题。

`hibernate-core`中的`slf4j-api`版本为`1.6.1`，手动引入`slf4j-api:1.4.0`

```groovy
// build.gradle

dependencies {
    implementation 'org.hibernate:hibernate-core:3.6.3.Final'
    implementation 'org.slf4j:slf4j-api:1.4.0'
}
```

如上所示，默认情况下，Gradle会使用`最新版本的jar包`[因为一般新版本的jar包都是向下兼容的]，实际开发中建议使用官方默认的解决方案。

Gradle提供了另外几种解决依赖冲突的方案：

1. Exclude排除某个依赖

   ```groovy
   // build.gradle
   
   dependencies {
       implementation('org.hibernate:hibernate-core:3.6.3.Final') {
           // 排除某个依赖,下面三种方法都可以
           exclude group: 'org.slf4j'
           exclude module: 'slf4j-api'
           exclude group: 'org.slf4j', module: 'slf4j-api'
       }
   
       // 排除之后,使用手动方式重新引入指定的依赖版本
       implementation 'org.slf4j:slf4j-api:1.4.0'
   }
   ```

2. 不允许依赖传递

   ```groovy
   // build.gradle
   
   dependencies {
       implementation('org.hibernate:hibernate-core:3.6.3.Final') {
           // 设置不允许依赖传递
           transitive(false)
       }
       // 排除之后,使用手动方式重新引入指定的依赖版本
       implementation 'org.slf4j:slf4j-api:1.4.0'
   }
   ```

3. 强制使用某个版本的依赖

   ```groovy
   // build.gradle
   
   dependencies {
       implementation 'org.hibernate:hibernate-core:3.6.3.Final'
       // 使用!!强制使用某个版本
       implementation 'org.slf4j:slf4j-api:1.4.0!!'
   }
   ```

   ```groovy
   // build.gradle
   
   dependencies {
       implementation 'org.hibernate:hibernate-core:3.6.3.Final'
       // 强制使用某个版本
       implementation('org.slf4j:slf4j-api:1.4.0') {
           version {
               strictly("1.4.0")
           }
       }
   }
   ```

4. 配置当Gradle构建遇到依赖冲突时，就立即构建失败

   ```groovy
   // build.gradle
   
   // 配置当Gradle构建遇到依赖冲突时,就立即构建失败
   configurations.all() {
       Configuration configuration ->
           // 当遇到版本冲突时直接构建失败
           configuration.resolutionStrategy.failOnVersionConflict()
   }
   ```

5. 动态版本声明(不建议使用)，`+、latest.integration`表示使用最新版本

   ```groovy
   // build.gradle
   
   dependencies {
       implementation 'org.hibernate:hibernate-core:3.6.3.Final'
       // 动态版本声明,+、latest.integration表示使用最新版本
       implementation 'org.slf4j:slf4j-api:+'
       implementation 'org.slf4j:slf4j-api:latest.integration'
   }
   ```
   

### 3.6、Gradle插件

Gradle插件官网文档地址：https://docs.gradle.org/current/userguide/plugin_reference.html

#### 3.6.1、插件的功能

- 促进代码重用、减少功能类似代码编写、提升工作效率
- 促进项目更高程度的模块化、自动化、便捷化
- 可插拔式的扩展项目的功能

#### 3.6.2、插件的作用

- 可以添加任务[Task]到项目中，从而帮助完成测试、编译、打包等
- 可以添加依赖配置到项目中
- 可以向项目中拓展新的扩展方法、属性等
- 可以对项目进行一些约定，如应用Java插件后，约定`src/main/java`目录是我们源码存在的目录，编译时编译该目录下的Java源代码

#### 3.6.3、插件的分类及使用

```markdown
├─Gradle插件				     															     
|  ├─脚本插件   
|  └─二进制插件(对象插件)                                                                   
|  |   ├─内部插件
|  |   ├─第三方插件
|  |   └─自定义插件
```

**脚本插件**

脚本插件的本质就是一个`脚本文件`。使用脚本插件时通过`apply from:`加载脚本，脚本文件可以是`本地`脚本文件，也可以是`网络`上的脚本文件

 ```groovy
 // version.gradle
 
 ext {
     company = "华为技术有限公司"
     cfgs = [
             compileSdkVersion: JavaVersion.VERSION_1_8
     ]
     spring = [
             version: '5.2.1'
     ]
 }
 ```

```groovy
// build.gradle

apply from: 'version.gradle'

task taskVersion {
    doLast {
        println "公司名称为: ${company}, JDK版本为: ${cfgs.compileSdkVersion}, Spring版本为: ${spring.version}"
    }
}
```

```shell
>gradle taskVersion
> Task :taskVersion
公司名称为: 华为技术有限公司, JDK版本为: 1.8, Spring版本为: 5.2.1
```

脚本文件是模块化的基础。按功能可以将脚本拆分为一个个公用、职责分明的文件，然后在主脚本文件中引用。比如：统一管理依赖版本号、应用构建版本等。

**对象插件之内部插件**

二进制插件[对象插件]就是实现了`org.gradle.Plugin`接口的插件，每个`Java Gradle插件`都有一个`plugin id`

```markdown
├─二进制插件(对象插件) 			     															     
|  ├─内部插件
|  |  ├─apply方式
|  |  |  ├─apaply(map具名参数)
|  |  |  └─apaply(闭包)
|  |  └─plugin DSL 
|  ├─第三方插件
|  |  ├─传统使用方式
|  |  |  ├─1、先引入依赖
|  |  |  └─通过apaply引用
|  |  └─plugin DSL 
|  └─用户自定义插件  
|  |  └─构建过程中默认执行
```

- apply方式

  - map具名参数

    ```groovy
    // build.gradle
    
    // apply: map具名参数方式
    // key:plugin value:插件id、插件的全类名、插件的简写名
    // apply plugin: 'java'
    // apply plugin: org.gradle.api.plugins.JavaPlugin
    apply plugin: JavaPlugin
    ```

  - 闭包

    ```groovy
    // build.gradle
    
    // apply: 闭包方式
    apply {
        plugin JavaPlugin
    }
    ```

- plugin DSL方式

  ```groovy
  // build.gradle
  
  plugins {
      id 'java'
  }
  ```

  

**对象插件之第三方插件**

使用第三方发布的二进制插件，一般需要配置对应的仓库和类路径

- 传统应用方式

  ```groovy
  // build.gradle
  
  // 使用传统方式
  buildscript {
      ext {
          springBootVersion = "2.7.7"
      }
  
      repositories {
          mavenLocal()
          maven {
              url 'https://maven.aliyun.com/repository/public'
          }
          jcenter()
      }
  
      // 引入插件
      dependencies {
          classpath("org.springframework.boot:spring-boot-gradle-plugin:${springBootVersion}")
      }
  }
  
  plugins {
      id 'application'
  }
  
  // 应用插件,不用写版本号
  apply plugin: 'org.springframework.boot'
  ```

**注意：`buildscript`标签必须写在`build.gradle`文件最上面**

- plugin DSL方式

  如果第三方插件已经被托管在`https://plugins.gradle.org/`，则不用使用`buildscript`里配置`classpath`方式引入依赖，直接使用plugin DSL方式引入即可

  ```groovy
  // build.gradle
  
  plugins {
      id 'org.springframework.boot' version '2.7.7'
  }
  ```

**对象插件之用户自定义插件**

Gradle自定义插件官方文档地址：https://docs.gradle.org/current/userguide/custom_plugins.html

```groovy
// build.gradle

class GreetingPlugin implements Plugin<Project> {
    void apply(Project project) {
        project.task('hello') {
            doLast {
                println 'Hello from the GreetingPlugin'
            }
        }
    }
}

// Apply the plugin
apply plugin: GreetingPlugin
```

```shell
> gradle -q hello
Hello from the GreetingPlugin
```

### 3.7、build.gradle文件

- `build.gradle`文件是一个gradle的`构建脚本`文件，支持Java、groovy等语言
- 每个`project`都有一个`build.gradle`文件，该文件是项目构建入口，可配置版本、插件、依赖库等信息
- 每个build文件都有一个对应的`project实例`，对build.gradle文件配置，本质就是设置project实例的`属性和方法`
- Root Project可以获取到所有的Child Project，可以在Root Project的`build.gradle`文件对Child Project统一配置。比如依赖的插件、依赖的maven中央仓库等

![image-20230107152526318](https://typora-yu-pic.oss-cn-chengdu.aliyuncs.com/picBed/image-20230107152526318.png)

#### 3.7.1、常见属性

```groovy
// build.gradle

// 指定使用什么版本的JDK语法编译源代码,跟编译环境有关,在有Java插件时才能使用
sourceCompatibility=JavaVersion.VERSION_1_8
// 指定生成某个特定JDK版本的class文件,跟运行环境有关,在有Java插件时才能使用
targetCompatibility=1.8
// 指定编译源码解码的业务编码字符集[编译器]
compileJava.options.encoding="UTF-8"
// 指定编译源码解码时的测试编码字符集[编译器]
compileTestJava.options.encoding="UTF-8"

// 指定源码编码的字符集[源文件]
tasks.withType(JavaCompile) {
    options.encoding = "UTF-8"
}
// 指定文档编码的字符集[源文件]
tasks.withType(Javadoc) {
    options.encoding = "UTF-8"
}
```

注意：`group+name+version`类似于maven中的`group+artifactId+version`；`encoding`能解决业务代码与测试代码中中文乱码问题

#### 3.7.2、Repositories

Gradle会按照仓库配置的顺序，`从上往下`依次去对应仓库中找所需要的jar包，如果找到，则停止向下搜索，否则，继续向下搜索，所有仓库找不到则提示错误信息。

```groovy
// build.gradle

repositories {
    // 指定去本地某个磁盘目录中查找,使用本地file文件协议，一般不用这种方式
    maven { url 'file:///D:/gradle/repository' }
    maven { url "$rootDir/lib/release" }

    // 指定去maven的本地仓库查找
    mavenLocal()

    // 指定去maven的私服或者第三方镜像仓库查找
    maven { name "Alibaba" url "https://maven.aliyun.com/repository/public" }
    maven { name "Bstek" url "https://nexus.bsdn.org/content/groups/public/" }

    // 指定去maven的远程仓库查找,https://repo.maven.apache.org/maven2/
    mavenCentral()

    // 指定去google仓库查找
    google()
}
```

**注意：Gradle没有自己的远程仓库，而是使用Maven、jcenter、jvy、Google这些远程仓库**

#### 3.7.3、Subprojects与Allprojects

allprojects是对所有project(包括`Root Project + Child Project`)进行统一配置，subprojects是对所有的`Child Project`进行统一配置

```groovy
// build.gradle

allprojects {
    tasks.create('elon') {
        println "current project name is $project.name"
    }
}
```

```shell
>gradle build
current project name is gradle-parent-demo
current project name is gradle-first-subject-demo
current project name is gradle-second-subject-demo
current project name is gradle-third-subject-demo
```

```groovy
// build.gradle

subprojects {
    tasks.create('elon') {
        println "current project name is $project.name"
    }
}
```

```shell
>gradle build
current project name is gradle-first-subject-demo
current project name is gradle-second-subject-demo
current project name is gradle-third-subject-demo
```

**根project中配置子工程信息**

```groovy
// build.gradle

project(':gradle-first-subject-demo') {
    apply plugin: 'java'
    dependencies {
        implementation 'org.hibernate:hibernate-core:3.6.3.Final'
    }
}
```

**注意：如果是在根project配置repositories和dependencies，则只对根project有效，即只对当前工程有效**

#### 3.7.4、ext用户自定义属性

Project和Task都允许用户添加额外的自定义属性，可以通过应用所属对象的ext属性实现。添加之后可以通过ext属性对自定义属性读取和设置，如果要同时添加多个自定义属性，可以使用ext代码块。

```groovy
// build.gradle

// 自定义一个Project的属性
ext.age=18

// 通过代码块同时定义多个属性
ext {
    phone=13888888888
    address='深圳华为'
}

task extCustomProperty {
    // 在task中自定义属性
    ext {
        desc = '鸡你太美'
    }
    doLast {
        println "年龄: ${age}"
        println "电话: ${phone}"
        println "地址: ${address}"
        println "描述: ${desc}"
    }
}
```

```shell
>gradle -q extCustomProperty
年龄: 18
电话: 13888888888
地址: 深圳华为
描述: 鸡你太美
```

**注意：ext声明的属性可以跨项目，比如可以在父项目中访问子项目中定义的ext属性**

#### 3.7.5、gradle.properties

`gradle.properties`一般用来定义系统属性、环境变量、项目属性、JVM相关配置信息，`gradle.properties`文件中的属性会自动在项目运行时加载。

```properties
## gradle.properties

# 设置此参数主要是编译下载包会占用大量的内存,可能会内存溢出
org.gradle.jvmargs=-Xms4096m -Xmx8192m
# 开启gradle缓存
org.gradle.caching=true
# 开启并行编译
org.gradle.parallel=true
# 启用新的孵化模式
org.gradle.configureondemand=true
# 开启守护进程
org.gradle.daemon=true
```

**注意：`gradle.properties`必须位于项目的根目录下**

#### 3.7.6、buildscript

buildscript脚本文件内容是gradle脚本执行`所需依赖`，分别对应的是maven库和插件。

```groovy
// build.gradle

import org.apache.commons.codec.binary.Base64

buildscript {
    repositories {
        mavenCentral()
    }
    dependencies {
        classpath group: 'commons-codec', name: 'commons-codec', version: '1.2'
    }
}

tasks.register('encode') {
    doLast {
        def byte[] encodedString = new Base64().encode('hello,world\n'.getBytes())
        println new String(encodedString)
    }
}
```

```groovy
// build.gradle

// 使用传统方式
buildscript {
    ext {
        springBootVersion = "2.7.7"
    }

    repositories {
        mavenLocal()
        maven {
            url 'https://maven.aliyun.com/repository/public'
        }
        jcenter()
    }

    // 引入插件
    dependencies {
        classpath("org.springframework.boot:spring-boot-gradle-plugin:${springBootVersion}")
    }
}

// 核心插件,不需要事先引入
apply plugin: 'java'

// 应用插件,不用写版本号
apply plugin: 'org.springframework.boot'
```

**注意事项如下：**

1. **buildscript{}必须在build.gradle文件的最前面**
2. **对于多项目构建，项目的buildscript()方法声明的依赖关系可用于其所有子项目的构建脚本**
3. **构建脚本依赖可能是Gradle插件**

### 3.8、publishing 项目发布

#### 3.8.1、引入Maven发布的插件

```groovy
// build.gradle

plugins {
    // 如果发布war包,需要java-library插件,java-library支持带源码、文档发布
    id 'java-library'
    id 'maven-publish'
}
```

#### 3.8.2、设置发布代码

- 发布jar包

  ```groovy
  // build.gradle
  
  plugins {
      id 'java'
      id 'maven-publish'
      // 如果发布war包,需要java-library插件,java-library支持带源码、文档发布
      id 'java-library'
  }
  
  // 带源码和javadoc的发布: 需要java-library插件支持
  javadoc.options.encoding='UTF-8'
  java {
      withJavadocJar()
      withSourcesJar()
  }
  
  publishing {
      publications {
          myLibrary(MavenPublication) {
              // 指定GAV坐标
              groupId = 'org.gradle.sample'
              artifactId = 'library'
              version = '1.1'
              // 发布jar包
              from components.java
          }
      }
  
      repositories {
          mavenLocal()
          // 发布项目到私服
          maven {
              // name属性可选,表示仓库名称,url必填
              name = 'myRepo'
  
              // 本地发布
  //            url = layout.buildDirectory.dir("repo")
  
              // 远程发布
  //            url = 'http://my.org/repo'
  
              def releaseRepoUrl = layout.buildDirectory.dir('repos/release')
              def snapshotsRepoUrl = layout.buildDirectory.dir('repos/snapshots')
              url = version.endsWith('SNAPSHOT') ? snapshotsRepoUrl : releaseRepoUrl
  //            // 认证信息
  //            credentials {
  //                username = ''
  //                password = ''
  //            }
          }
      }
  }
  ```

- 发布war包

  ```groovy
  // build.gradle
  
  plugins {
      id 'war'
      id 'maven-publish'
      // 如果发布war包,需要java-library插件,java-library支持带源码、文档发布
      id 'java-library'
  }
  
  publishing {
      publications {
          myLibrary(MavenPublication) {
              // 指定GAV坐标
              groupId = 'org.gradle.sample'
              artifactId = 'library'
              version = '1.1'
              // 发布war包,需要引入java-library插件
              from components.web
          }
      }
  
      repositories {
          mavenLocal()
          // 发布项目到私服
          maven {
              // name属性可选,表示仓库名称,url必填
              name = 'myRepo'
  
              // 本地发布
  //            url = layout.buildDirectory.dir("repo")
  
              // 远程发布
  //            url = 'http://my.org/repo'
  
              def releaseRepoUrl = layout.buildDirectory.dir('repos/release')
              def snapshotsRepoUrl = layout.buildDirectory.dir('repos/snapshots')
              url = version.endsWith('SNAPSHOT') ? snapshotsRepoUrl : releaseRepoUrl
  //            // 认证信息
  //            credentials {
  //                username = ''
  //                password = ''
  //            }
          }
      }
  }
  ```

#### 3.8.3、执行发布指令

执行发布指令，将项目发布到本地仓库或者远程仓库。常见的发布指令有：

- generatePomFileForMyLibraryPublication：生成pom文件
- publish`${PubName}`PublicationTo`${RepoName}`Repository：发布项目到指定仓库，如果没有仓库名，默认为maven
- publish`${PubName}`PublicationToMavenLocal：将PubName发布复制到本地Maven仓库中，包括POM文件和其他元数据
- **publish(常用命令)：发布到repositories中指定的仓库(比如Maven私服)**
- publishToMavenLocal：执行所有发布任务中的操作发布到本地Maven仓库[默认在用户家目录下的.m2/repository]

发布示例如下：

```shell
>gradle publish
```

![image-20230108160519077](https://typora-yu-pic.oss-cn-chengdu.aliyuncs.com/picBed/image-20230108160519077.png)

### 3.9、生命周期 Hook

生命周期中的这些钩子函数都是由gradle自动回调完成的，利用这些钩子函数可以帮助我们实现一些想要的功能。

**Gradle初始化阶段**

- 在settings.gradle执行完后，会回调Gradle对象的settingsEvaluated方法
- 在构建所有工程的build.gradle对应的project对象之后，初始化完毕，会回调Gradle对象的projectsLoaded方法

**Gradle配置阶段**

- Gradle会循环执行每个工程的build.gradle文件
- 在执行当前工程build.gradle之前，会回调Gradle对象的beforeProject方法和当前project对象的beforeEvaluate方法，虽然beforeEvaluate属于project的生命周期，但是此时buildscript尚未被加载。所以beforeEvaluate的设置要在initscript或settingscript中进行，不要在buildscipt使用project.beforeEvaluate方法。
- 在执行当前工程build.gradle之后，会回调Gradle对象的afterProject方法和当前project对象的afterEvaluate方法
- 在所有build.gradle执行完毕后，会回调Gradle对象的projectsEvaluate方法
- 在构建Task依赖有向无环图后，配置阶段完毕，会回调TaskExcutionGraph对象的whenReady方法

**Gradle执行阶段**

- Gradle会循环执行Task及其依赖的Task
- 在当前Task执行之前，会回调TaskExcutionGraph对象的beforeTask方法
- 在当前Task执行之后，会回调TaskExcutionGraph对象的afterTask方法

- 当所有的Task执行完毕后，会回调Gradle对象的buildFinish方法

Gradle执行脚本文件的时候会生成对应的实例，主要有以下几种对象：

1. Gradle对象：在项目初始化时构建，全局单例存在，只有这一个对象
2. Project对象：每一个build.gradle文件都会转换为一个Project对象，类似于Maven中的pom.xml文件
3. Settings对象：settings.gradle会转换为一个settings对象，和整个项目是一对一的关系，一般只用到include方法
4. Task对象：从前面的有向无环图中，可以看出gradle最终是基于Task的，一个项目可以有一个或多个Task

Gradle生命周期钩子函数调用顺序示例如下：

在项目目录下的`settings.gradle`文件中添加以下代码：

```groovy
// settings.gradle

rootProject.name = 'gradle-parent-demo'
include 'gradle-first-subject-demo'
include 'gradle-second-subject-demo'
include 'gradle-third-subject-demo'

// 1.settingsEvaluated钩子函数,在初始化阶段完成
gradle.settingsEvaluated {
    println "settingsEvaluated"
}

// 2.projectsLoaded钩子函数,在初始化阶段完成
gradle.projectsLoaded {
    println "projectsLoaded"
}

// 声明一个变量,表示当前项目名,在每次执行某个项目的beforeEvaluate方法时先给projectName变量赋值
def projectName = ""
gradle.addProjectEvaluationListener(new ProjectEvaluationListener() {
    // 3.执行各个project的beforeEvaluate,在配置阶段完成
    @Override
    void beforeEvaluate(Project project) {
        projectName = project.name
        println "${project.name} project beforeEvaluate"
    }

    // 5.执行各个project的afterEvaluate,在配置阶段完成
    @Override
    void afterEvaluate(Project project, ProjectState state) {
        println "${project.name} project afterEvaluate"
    }
})

// 4.执行各个project的beforeProject,在配置阶段完成
gradle.beforeProject {
    println "${projectName} beforeProject"
}

// 6.执行各个project的afterProject,在配置阶段完成
gradle.afterProject() {
    println "${projectName} afterProject"
}

// 7.所有build.gradle执行完毕后，会回调Gradle对象的projectsEvaluate方法,在配置阶段完成
def rootProjectName = rootProject.getName()
gradle.projectsEvaluated {
    println "${rootProjectName} projectsEvaluated"
}

// 8.配置阶段完成后，会回调TaskExcutionGraph对象的whenReady方法,在配置阶段完成
gradle.taskGraph.whenReady {
    println "${rootProjectName} taskGraph whenReady"
}

// 9.在当前Task执行之前，会回调TaskExcutionGraph对象的beforeTask方法,在执行阶段完成
gradle.taskGraph.beforeTask {task -> {
    println "this is the task ${task.name} of the project ${task.getProject().name} beforeTask"
}}

// 10.在当前Task执行之后，会回调TaskExcutionGraph对象的afterTask方法,在执行阶段完成
gradle.taskGraph.afterTask() {task -> {
    println "this is the task ${task.name} of the project ${task.getProject().name} afterTask"
}}

// 11.当所有的Task执行完毕后，会回调Gradle对象的buildFinish方法,在执行阶段完成
gradle.buildFinished {
    println "${rootProjectName} buildFinished"
}
```

在根工程中的`build.gradle`文件中添加以下代码：

```groovy
// gradle-parent-demo
// build.gradle

plugins {
    id 'java'
}


// 指定使用什么版本的JDK语法编译源代码,跟编译环境有关,在有Java插件时才能使用
sourceCompatibility=JavaVersion.VERSION_1_8
// 指定生成某个特定JDK版本的class文件,跟运行环境有关,在有Java插件时才能使用
targetCompatibility=1.8
// 指定编译源码解码的业务编码字符集[编译器]
compileJava.options.encoding="UTF-8"
// 指定编译源码解码时的测试编码字符集[编译器]
compileTestJava.options.encoding="UTF-8"

// 指定源码编码的字符集[源文件]
tasks.withType(JavaCompile) {
    options.encoding = "UTF-8"
}
// 指定文档编码的字符集[源文件]
tasks.withType(Javadoc) {
    options.encoding = "UTF-8"
}

group 'com.yu.gradle'
version '1.0-SNAPSHOT'

//mainClassName = 'com.yu.gradle.GradleApplication'

repositories {
    mavenCentral()

    // 指定去google仓库查找
    google()
}

dependencies {
    testImplementation 'org.junit.jupiter:junit-jupiter-api:5.7.0'
    testRuntimeOnly 'org.junit.jupiter:junit-jupiter-engine:5.7.0'

    implementation 'org.hibernate:hibernate-core:3.6.3.Final'
}

test {
    useJUnitPlatform()
}

task A {
    println "taskA..."
    doFirst {
        println "taskA doFirst..."
    }

    doLast {
        println "taskA doLast..."
    }
}
```

在`gradle-first-subject-demo`工程中的`build.gradle`文件中添加以下代码：

```groovy
// gradle-first-subject-demo
// build.gradle

plugins {
    id 'java'
}

group 'com.yu.gradle'
version '1.0-SNAPSHOT'

repositories {
    mavenCentral()
}

dependencies {
    testImplementation 'org.junit.jupiter:junit-jupiter-api:5.7.0'
    testRuntimeOnly 'org.junit.jupiter:junit-jupiter-engine:5.7.0'
}

test {
    useJUnitPlatform()
}

task B {
    println "taskB..."
    doFirst {
        println "taskB doFirst..."
    }

    doLast {
        println "taskB doLast..."
    }
}

task C {
    // 依赖B
    dependsOn 'B'
    println "taskC..."
    doFirst {
        println "taskC doFirst..."
    }

    doLast {
        println "taskC doLast..."
    }
}
```

在项目目录下的`Terminal`执行`gradle C`命令

```shell
>gradle C
settingsEvaluated
projectsLoaded

> Configure project :
gradle-parent-demo project beforeEvaluate
gradle-parent-demo beforeProject
taskA...
gradle-parent-demo project afterEvaluate
gradle-parent-demo afterProject

> Configure project :gradle-first-subject-demo
gradle-first-subject-demo project beforeEvaluate
gradle-first-subject-demo beforeProject
taskB...
taskC...
gradle-first-subject-demo project afterEvaluate
gradle-first-subject-demo afterProject

> Configure project :gradle-second-subject-demo
gradle-second-subject-demo project beforeEvaluate
gradle-second-subject-demo beforeProject
gradle-second-subject-demo project afterEvaluate
gradle-second-subject-demo afterProject

> Configure project :gradle-third-subject-demo
gradle-third-subject-demo project beforeEvaluate
gradle-third-subject-demo beforeProject
gradle-third-subject-demo project afterEvaluate
gradle-third-subject-demo afterProject
gradle-parent-demo projectsEvaluated
gradle-parent-demo taskGraph whenReady

> Task :gradle-first-subject-demo:B
this is the task B of the project gradle-first-subject-demo beforeTask
taskB doFirst...
taskB doLast...
this is the task B of the project gradle-first-subject-demo afterTask

> Task :gradle-first-subject-demo:C
this is the task C of the project gradle-first-subject-demo beforeTask
taskC doFirst...
taskC doLast...
this is the task C of the project gradle-first-subject-demo afterTask
gradle-parent-demo buildFinished
```

### 3.10、Gradle创建SpringBoot项目

SpringBoot整合Gradle官方文档地址：https://docs.spring.io/spring-boot/docs/current/gradle-plugin/reference/htmlsingle/#getting-started

Gradle提供了Spring Boot插件的支持。支持打包可执行jar或war归档文件，运行Spring Boot应用程序，并使用Spring Boot Dendencies提供的依赖管理。

#### 3.10.1、引入SpringBoot插件

该插件发布在Gradle的插件门户网站上，可以使用插件来应用

```groovy
// build.gradle

plugins {
    id 'java'
    id 'org.springframework.boot' version '2.2.6.RELEASE'
    // 类似于maven中的<dependencyManagement>标签,引入其他依赖时省略版本号,只做依赖的管理,不做实际依赖
    id 'io.spring.dependency-management' version '1.0.10.RELEASE'
}
```

**注意：`org.springframework.boot`必须和`io.spring.dependency-management`同时使用，否则会报错**

#### 3.10.2、引入所需的依赖

```groovy
// build.gradle

dependencies {
    testImplementation 'org.junit.jupiter:junit-jupiter-api:5.7.0'
    testRuntimeOnly 'org.junit.jupiter:junit-jupiter-engine:5.7.0'
    implementation 'org.springframework.boot:spring-boot-starter-web'
}
```

#### 3.10.3、编写主启动类和业务代码

**主启动类**

```java
package com.yu.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Boot启动类
 */
@SpringBootApplication
public class BootApplication {
	public static void main(String[] args) {
		SpringApplication.run(BootApplication.class, args);
	}
}
```

**业务代码**

```java
package com.yu.boot.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户控制器
 */
@RestController
@RequestMapping("/user")
public class UserController {

	@GetMapping("/name")
	public String getUser() {
		return "zhangsan";
	}
}
```

#### 3.10.3、运行SpringBoot项目

Gradle行SpringBoot项目有两种方式，直接执行`gradle bootRun`和使用IDEA中Gradle图形化界面执行

- 执行gradle bootRun命令

  ```shell
  >gradle bootRun
  > Task :bootRun
  2023-01-09 00:07:03.612  INFO 24748 --- [           main] com.yu.boot.BootApplication              : Started BootApplication in 1.427 seconds (JVM running for 1.7
  58)
  ```

- Gradle图形化界面

  ![image-20230109000943058](https://typora-yu-pic.oss-cn-chengdu.aliyuncs.com/picBed/image-20230109000943058.png)

#### 3.10.4、spring-boot-gradle-plugin插件

使用`spring-boot-gradle-plugin`能解决版本问题，不用再考虑`dependency-management`等依赖版本，只需要在`buildscript`定义`spring-boot-gradle-plugin`版本，后续依赖通过`apply plugin: ${pluginName}`方式引入即可。

```groovy
buildscript {
    repositories {
        maven { url 'https://maven.aliyun.com/repository/public'}
    }

    dependencies {
        // 这里修改版本,aliyun镜像仓库无springboot:2.2.6版本
        classpath 'org.springframework.boot:spring-boot-gradle-plugin:2.4.1'
    }
}

apply plugin: 'java'
apply plugin: 'org.springframework.boot'
apply plugin: 'io.spring.dependency-management'

group 'com.yu.boot'
version '1.0-SNAPSHOT'

repositories {
    mavenCentral()
}

dependencies {
    testImplementation 'org.junit.jupiter:junit-jupiter-api:5.7.0'
    testRuntimeOnly 'org.junit.jupiter:junit-jupiter-engine:5.7.0'
    implementation 'org.springframework.boot:spring-boot-starter-web'
}

test {
    useJUnitPlatform()
}
```

### 3.11、多模块项目案例

待定...

### 3.12、springboot微服务

待定...
