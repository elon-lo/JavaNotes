## 一、Docker简介

#### 1、Docker是什么?

​		Docker是基于Go语言实现的云开源项目。

​		Docker 是一个开源的应用容器引擎，让开发者可以打包他们的应用以及依赖包到一个可移植的容器中，然后发布到任何流行的 Linux 机器上，也可以实现虚拟化。容器是完全使用沙箱机制，相互之间不会有任何接口。Docker 技术的三大核心概念，分别是：镜像 Image、容器 Container、仓库 Repository。

#### 2、Docker的出现解决了哪些问题?

​		一款产品从开发到上线，一般都会有开发环境，测试环境，运行环境。

​		如果有一个环境中某个软件或者依赖版本不同了，可能产品就会出现一些错误，甚至无法运行。比如开发人员在windows系统，但是最终要把项目部署到Linux。如果存在不支持跨平台的软件，那项目肯定也无法部署成功。

​		这就产生了开发和运维人员之间的矛盾。开发人员在开发环境将代码跑通，但是到了上线的时候就崩了。还要重新检查操作系统，软件，依赖等版本，这大大降低了效率。造成了搭环境一两天，部署项目两分钟的事件。

​		Docker的官网上有一句话`docker build,ship and run any app,any where`Docker在任何地方构建、发布和运行任何应用程序。

​		使用Docker后，只需要开发人员把环境配置好，将需要运行的程序包运行成功，然后把程序包和环境一起打包给运维人员，让运维人员部署就可以了。这大大提高了项目上线的效率。

​		用一句话概括来说，Docker是**解决运行环境和配置问题**的**软件容器**，方便做持续集成并有助于整体发布的**容器虚拟化技术**。

#### 3、Docker下载

Docker官网：<https://www.docker.com/>

Docker Hub官网：<https://hub.docker.com/>

#### 4、Docker的应用场景

- Web 应用的自动化打包和发布。
- 自动化测试和持续集成、发布。
- 在服务型环境中部署和调整数据库或其他的后台应用。
- 从头编译或者扩展现有的 OpenShift 或 Cloud Foundry 平台来搭建自己的 PaaS 环境。

## 二、Docker安装

#### 1、前提说明

​		Docker并非是一个通用的容器工具，它依赖于``已存在并运行``的``Linux内核``环境。

​		Docker实质上是在已经运行的Linux下制造了一个隔离的文件环境，因此它执行的效率几乎等同于所部署的Linux主机。因此，Docker必须部署在Linux内核的系统上。如果其他系统想部署Docker就必须安装一个虚拟Linux环境。

​		在Windows上部署Docker的方法有两种。一种是直接下载Windows版本的Docker，一种是先在Windows上安装一个虚拟机，然后在虚拟机下安装Linux系统，最后在安装Linux系统的虚拟机中运行Docker。

#### 2、Docker的基本组成

- 镜像(image)

  Docker镜像(image)就是一个只读的模板。镜像可以用来创建Docker容器，一个镜像可以创建多个容器，它也相当于是一个root文件系统。比如官方镜像CentOS7就包含了完整的一套CentOS7最小系统的root文件系统。相当于容器的"源代码"，**docker镜像文件类似于Java的类模板，而docker容器实例类似于Java中new出来的实例对象。**

- 容器(container)

  **从面向对象角度来看**：Docker容器(container)是独立运行的一个或一组应用，应用程序或服务运行在容器里面，容器就类似于一个虚拟化的运行环境，**容器是用镜像创建的运行实例**。就像是Java中的类和实例一样，镜像是静态的定义，容器是镜像运行时的实体。容器为镜像提供了一个标准的和隔离的运行环境，它可以被启动、开始、停止、删除。每个容器都是相互隔离的，保证安全的平台。

  **从镜像容器角度来看**：**可以把容器看做是一个简易版的Linux环境**(包括root用户权限、进程空间、用户空间和网络空间等)和运行在其中的应用程序。

- 仓库(repository)

  仓库(repository)是**集中存放镜像文件**的场所。

  类似于Maven仓库，存放各种jar包的地方；github仓库，存放各种git项目的地方；Dcoker公司提供的官方registry被称为Docker Hub，存放各种镜像模板的地方。

  仓库分为`公开仓库(Public)`和`私有仓库(Private)`两种形式。最大的公开仓库是`Docker Hub(https://hub.docker.com/)`，存放了数量庞大的镜像供用户下载。国内的公开仓库包括阿里云、网易云等。

#### 3、Docker工作原理

​		Docker是一个Client-Server结构的系统，Docker守护进程运行在主机上，然后通过Socket连接从客户端访问，守护进程从客户端接受命令并管理运行在主机上的容器。

#### 4、Dcoker运行的基本流程

Docker是一个C/S模式的架构，后端是一个松耦合架构，众多模块各司其职。

Docker运行的基本流程为：

- 用户使用Docker Client与Docker Daemon建立通信，并发送请求给后者。
- Docker Daemon作为Docker架构的主体部分，首先提供Docker Server的功能使其可以接受Docker Client的请求。
- Docker Engine执行Docker内部的一系列工作，每一项工作都是以一个Job的形式存在。
- Job运行过程中，当需要容器镜像时，则从Docker Eegistry中下载镜像，并通过镜像管理驱动Graph Driver将下载镜像以Graph的形式存储。
- 当需要为Docker创建网络环境时，通过网络管理驱动Network Driver创建并配置Docker容器网络环境。
- 当需要限制Docker容器运行资源或执行用户指令等操作时，则通过Exec Driver来完成。
- Libcontainer是一项独立的运行容器管理包。Network以及Exec Driver都是通过Libcontainer来实现具体对容器进行的操作。

#### 5、Docker(CentOS系统)安装步骤

##### 5.1、Docker官方文档地址

- <https://docs.docker.com/>

##### 5.2、Docker支持的系统版本

| **Platform**                                                 | **x86_64 / amd64** | **arm64 / aarch64** | **arm (32-bit)** | **s390x** |
| ------------------------------------------------------------ | ------------------ | ------------------- | ---------------- | --------- |
| [CentOS](https://docs.docker.com/engine/install/centos/)     | √                  | √                   |                  |           |
| [Debian](https://docs.docker.com/engine/install/debian/)     | √                  | √                   | √                |           |
| [Fedora](https://docs.docker.com/engine/install/fedora/)     | √                  | √                   |                  |           |
| [Raspbian](https://docs.docker.com/engine/install/debian/)   |                    |                     | √                |           |
| [RHEL](https://docs.docker.com/engine/install/rhel/)         |                    |                     |                  | √         |
| [SLES](https://docs.docker.com/engine/install/sles/)         |                    |                     |                  | √         |
| [Ubuntu](https://docs.docker.com/engine/install/ubuntu/)     | √                  | √                   | √                | √         |
| [Binaries](https://docs.docker.com/engine/install/binaries/) | √                  | √                   | √                |           |

##### 5.3、Docker支持的CentOS版本和Linux内核版本

- Docker仅支持CentOS7及以上版本，Linux内核3.8及以上版本,，安装Docker前请检查CentOS版本和Linux内核版本，使用`cat /etc/redhat-release`命令查看CentOS版本，使用`uname -r`查看Linux内核版本。

![image-20220701233809319](https://cdn.jsdelivr.net/gh/elon-lo/PicGo-Image@master/img/image-20220701233809319.png)

##### 5.4、卸载CentOS旧版本

- 官网地址：<https://docs.docker.com/engine/install/centos/>

- 使用`yum list installed | grep docker`命令查看安装了哪些docker插件

- 使用以下命令进行卸载

  ```shell
  $ sudo yum remove docker \
                    docker-client \
                    docker-client-latest \
                    docker-common \
                    docker-latest \
                    docker-latest-logrotate \
                    docker-logrotate \
                    docker-engine
  ```

##### 5.5、yum安装gcc相关软件

```shell
$ yum -y install gcc
$ yum -y install gcc-c++
```

##### 5.6、安装需要的软件包

```shell
$ yum install -y yum-utils
```

##### 5.7、设置stable镜像仓库

- 注意需要设置镜像仓库为<span style="color:red;">阿里云源</span>，能够提供更高效的下载速度

```shell
#设置镜像下载仓库地址
$ yum-config-manager --add-repo http://mirrors.aliyun.com/docker-ce/linux/centos/docker-ce.repo
```

##### 5.8、更新yum软件包索引

```shell
#更新yum
$ yum update

#更新yum软件索引
$ yum makecache fast
```

##### 5.9、安装、启动、测试、卸载Docker

**安装Docker Ce**

```shell
#安装docker
$ yum -y install docker-ce docker-ce-cli containerd.io
```

**启动Docker**

```shell
#启动docker
$ systemctl start docker	

#查看docker状态
$ systemctl status docker	

#关闭docker
$ systemctl stop docker	
```

**测试Docker**

```shell
#查看docker版本信息
$ docker version		

#docker指定镜像启动容器
$ docker run hello-world	
```

**卸载Docker**

```shell
#卸载docker
$ yum -y remove docker-ce docker-ce-cli containerd.io	

#删除容器和配置文件等
$ rm -rf /var/lib/docker
$ rm -rf /var/lib/containerd
```

#### 6、阿里云镜像加速配置

- 阿里云容器服务：<https://promotion.aliyun.com/ntms/act/kubernetes.html>
- 注册一个属于自己的阿里云账号(阿里系都可以)
- 获得加速器地址链接，登录阿里云→左侧列表产品与服务→弹性计算→容器镜像服务→镜像工具→镜像加速器
- 配置镜像加速

```shell
#创建docker配置文件夹
$ mkdir -p /etc/dokcer

#将配置json字符串写入daemon.json文件
$ tee /etc/docker/daemon.json <<-'EOF'
{
  "registry-mirrors": ["https://9dltubz7.mirror.aliyuncs.com"]
}
EOF

#重载docker配置文件
$ systemctl daemon-reload

#重启docker容器
$ systemctl restart docker
```

#### 7、Docker Run的运行机制

```flow
st=>start: 开始
op=>operation: Docker在本机中寻找该镜像
op1=>operation: 以该镜像为模板生产容器实例运行
op2=>operation: 去Docker Hub上查找该镜像
cond=>condition: 本机是否有该镜像
cond1=>condition: Hub能否找到
op3=>operation: 下载该镜像到本地
op4=>operation: 以该镜像为模板生产容器实例运行
op5=>operation: 返回失败错误,查不到该镜像
sub1=>subroutine: 子流程
io=>inputoutput: 输入输出框
e=>end: 结束框
st(right)->op(right)->cond
cond(yes)->op1(bottom)
cond(no)->op2(right)->cond1
cond1(yes)->op3(right)->op4
cond1(no)->op5
```

#### 8、Docker底层工作原理

##### 8.1、Docker和传统虚拟化方式的不同之处

- 传统虚拟化技术是虚拟出一套硬件后，在其上运行一个完整操作系统，在该系统上再运行所需应用程序。
- 容器内的应用进程直接运行于宿主的内核，容器内没有自己的内核也<span style="color:red;">没有进行硬件虚拟</span>。因此容器要比传统虚拟机更加轻便。
- 每个容器之间互相隔离，每个容器都有自己的文件系统，容器之间进程不会相互影响，能区分计算资源。

##### 8.2、为什么Docker会比VM虚拟机更快

- **Docker有比虚拟机更少的抽象层**

​		由于docker不需要Hypervisor(虚拟机)实现硬件资源虚拟化，运行在docker容器上的程序直接使用的是实际物理机的硬件资源。因此在CPU、内存利用率上docker会在效率上有明显的优势。

- **docker利用的是宿主机的内核，而不需要加载操作系统OS内核**

  ​	当新建一个容器时，docker不需要和虚拟机一样重新加载一个操作系统内核。进而避免引寻、加载操作系统内核返回等比较费时费资源的过程，当新建一个虚拟机时，虚拟机软件需要加载OS，返回新建过程都是分钟级别的。而docker由于直接利用宿主机的操作系统，则省略了返回过程，因此新建一个docker容器只需要几秒钟。

## 三、Docker常用命令

#### 1、帮助启动类命令

- 启动docker						 	`systemctl start docker`
- 停止docker                             `systemctl stop docker`
- 重启docker                             `systemctl restart docker`
- 查看docker状态                      `systemctl status docker`
- 开机启动                                 `systemctl enable docker`
- 查看docker概要信息               `docker info`
- 查看docker总体帮助文档        `docker --help`
- 查看docker命令帮助文档        `docker 具体命令 --help`，如`docker image --help`，`docker run --help`等

#### 2、镜像命令

- `docker image [OPTIONS]`(列出本地主机上的所有镜像)

- OPTIONS说明：

  - -a:列出本地所有的镜像(含历史映像层)
  - -q:只显示镜像ID

- 参数说明：

  - REPOSITORY：表示镜像的仓库源
  - TAG：镜像的标签版本号
  - IMAGE ID：镜像ID
  - CREATED：镜像创建时间
  - SIZE：镜像大小

  注：同一仓库源可以有多个TAG版本,代表这个仓库源的不同版本,我们使用REPOSITORY:TAG来定义不同的镜像，<span style="color:red;">如果不指定镜像的版本标签，将默认使用最新版本latest</span>，如只使用redis,docker将默认使用redis:latest镜像

- `docker search [OPTIONS] 镜像名`(搜索镜像)

- 各参数含义：

  - NAME：镜像名称
  - DESCRIPTION：镜像说明
  - STARS：点赞数量
  - OFFICIAL：是否是官方的
  - AUTOMATED：是否是自动构建的

  注: OFFICIAL参数表示官方认证

```shell
$ docker search --limit 5 redis 
```

![image-20220702001641518](https://cdn.jsdelivr.net/gh/elon-lo/PicGo-Image@master/img/image-20220702001641518.png)

- `docker pull 镜像名`(下载镜像)

​		可以使用`docker pull 镜像名:TAG`指定要下载的镜像的版本号，如不指定版本号则默认下载最新(latest)版

- `docker system df`(查看镜像、容器、数据卷所占的空间)
- `docker rmi 镜像名/仓库名`(删除镜像)
  - `docker rmi -f 镜像名/仓库名`										强制删除单个镜像
  - `docker rmi -f 镜像名1:TAG 镜像名2:TAG`                      强制删除多个镜像
  - `dokcer rmi -f $(docker images -qa)`                         强制删除全部镜像
- `docker的虚悬镜像(dangling image)`
  - 仓库名、标签都是<none>的镜像
  - 虚悬镜像没有实际意义，有可能还会导致docker构建失败，<span style="color:red;">建议删除</span>
  - `docker images -f dangling=true`        列出虚悬镜像
  - `docker image prune`                           删除虚悬镜像

#### 3、容器命令

- <span style="color:red;">有镜像才能创建容器，这是根本前提(下载一个centos或者ubuntu系统演示)</span>

- 创建和启动容器

  - `docker run [OPTIONS] IMAGE [COMMAND] [ARG...]`

  - options说明：

    - --name "container name"：为容器指定名称
    - -d：后台运行并返回容器id，即启动守护式容器(后台运行)
    - -i：以交互模式运行容器，通常与-t同时使用
    - -t：为容器重新分配一个伪输入终端，通常与-i同时使用，也叫启动交互式容器(前台有伪终端,等待交互)
    - -P：大写p，随机映射宿主机端口
    - -p：小写p，指定端口映射

  - docker启动交互式容器命令

    ```shell
    #  使用镜像ubuntu:latest以交互式容器命令启动一个容器,在容器内执行/bin/bash命令。
    $ docker run -it ubuntu:latest /bin/bash
    
    #  容器启动时并重命名
    $ docker run -it --name myubuntu /bin/bash
    ```

    参数说明：

    - -i: 交互式操作
    - -t: 终端
    - ubuntu: ubuntu镜像
    - /bin/bash: 放在镜像名后的是命令，希望有一个交互式的shell，因此使用/bin/bash命令
    - exit: 退出终端命令(退出即容器停止运行)

- 列出当前所有<span style="color:red;">正在运行</span>的容器

  - docker ps [OPTIONS]

    OPTIONS说明如下(常用)：

    - -a:						列出当前所有<span style="color:red;">正在运行</span>+<span style="color:red;">历史运行</span>过的容器
    - -l:                         显示最近创建的容器
    - -n number:        显示最近创建的n个容器
    - <span style="color:red;">-q:                       静默模式,只显示容器编号</span>

- 退出容器

  - exit：				退出即容器停止
  - ctrl+p+q：       退出容器继续运行

- 启动已停止的容器

  ```shell
  $ docker start CONTAINER ID或者docker start CONTAINER NAME
  ```

- 重启容器

  ```shel
  $ docker restart CONTAINER ID或者docker restart CONTAINER NAME

- 停止容器

  ```shell
  $ docker stop CONTAINER ID或者docker stop CONTAINER NAME
  ```

- 强制停止容器

  ```shell
  $ docker kill CONTAINER ID或者docker kill CONTAINER NAME
  ```

- 删除已停止的容器

  ```shell
  $ docker rm CONTAINER ID或者docker rm CONTAINER NAME
  ```

- 强制删除容器

  ```shell
  $ docker rm -f CONTAINER ID或者docker rm -f CONTAINER NAME
  
  #一次性强制删除多个容器
  $ docker rm -f $(docker ps -a -q)
  $ docker ps -a -q | xargs docker rm
  ```

- docker容器操作示例

  - 下载容器

    ```shell
    $ docker pull redis:6.0.8
    ```

  - 启动守护式容器(后台服务器)

    ```shell
    #在大部分场景下,我们希望docker的服务是在后台运行的,我们可以通过-d指定容器的后台运行模式
    $ docker run -d CONTAINER ID或者docker run -d CONTAINER NAME
    ```

    - docker两种方式启动redis

    ```shell
    #前台交互式启动容器
    $ docker run -it redis:6.0.8
    
    #后台守护式启动容器
    $ docker run -d redis:6.0.8
    ```

  - 查看容器日志

    ```shell
    $ docker logs CONTAINER ID
    ```

  - 查看容器内运行的进程

    ```shell
    $ docker top CONTAINER ID
    ```

  - 查看容器内部细节

    ```shell
    $ docker inspect CONTAINER ID
    ```

  - <span style="color:red;">进入正在运行的容器并以命令行交互</span>

  - 两种命令的区别如下：
  
    - attach：直接进入容器启动命令的终端，不会启动新的进程，用exit退出，会导致容器的停止
    - exec：在容器中打开新的终端，并且可以启动新的进程，用exit退出，不会导致容器的停止
    
    ```shell
    #进入正在运行的容器并以命令行交互(使用exit退出容器终端时不会停止容器)
    $ docker exec -it CONTAINER ID /bin/bash或docker exec -it CONTAINER ID bash
    
    #重新进入容器(使用exit退出容器终端时会停止容器)
    $ docker attach CONTAINER ID
    
    $ docker run -d --privileged=true -p 6379:6379 --restart always -v docker/redis/conf/redis.conf:/etc/redis/redis.conf -v /docker/redis/data:/data \
    --name myredis redis:6.0.8 redis-server /etc/redis/redis.conf 
    --appendonly yes
    ```
    
    <span style="color:red;">注：使用-d后台启动的容器会和attach命令冲突，导致使用attach命令时卡住问题，即attach只适用于-it启动的容器</span>
    
  - 从容器内拷贝文件到主机上
  
    ```shell
    $ docker cp CONTAINER ID:CONTAINER PATH TARGET PATH
    # 如: docker cp 727cda841968:/usr/local/etc/redis/redis.conf /yu/
    ```
  
  - 从主机拷贝文件到容器中
  
    ```shell
    $ docker cp PATH CONTAINER ID:CONTAINER TARGET PATH
    # 如: docker cp /yu/ 727cda841968:/usr/local/etc/redis/redis.conf
    ```
  
  - 导入和导出容器
  
    - export：导出容器的内容作为一个tar归档文件
  
      ```shell
      $ docker export CONTAINER ID > FILE NAME.tar
      ```
  
    - import：从tar包中的内容创建一个新的文件系统再导入为新的镜像
  
      ```shell
      $ cat FILE NAME.tar | docker import-IMAGE USER/IMAGE NAME:IMAGE VERSION
      ```
  
  - 常用命令
  
    ```shell
    #当前shell下attach连接指定运行镜像
    $ attach(Attach to a running container)				
    
    #通过Dockerfile定制镜像
    $ build(Build an image from a Dcokerfile)				
    
    #提交当前容器为新的镜像
    $ commit(Create a new image from a container changes)	
    
    #从容器中拷贝指定文件或者目录到宿主机中
    $ cp(Copy files/folders from the container file system to the host path)	
    
    #创建一个新的容器,同run,但不启动容器
    $ create(Create a new container)		
    
    #查看docker容器变化
    $ diff(Inspect changes on container's file system)	
    
    #从docker服务器获取容器实时事件
    $ events(Get real time events from the server)	
    
    #在已存在的容器上运行命令
    $ exec(Run a command in an existing container)
    
    #导出容器的内容流作为一个tar归档文件
    $ export(Stream the contents of a container as a tar archive)
    
    #展示一个镜像形成历史
    $ history(Show the history of an image)
    
    #列出系统当前所有镜像
    $ images(List images)	
    
    #从tar包中的内容创建一个新的文件系统镜像
    $ import(Create a new file system image from the contents of a tar ball)	
    
    #显示系统相关信息
    $ info(Display system-wide information)		
    
    #查看容器详细信息
    $ inspect(Return low-level information on a container)
    
    #kill指定docker容器
    $ kill(Kill a running container)	
    
    #从一个tar包中加载一个镜像[对应save]
    $ load(Load an image from a tar archive)
    
    #注册或者登录一个docker源服务器
    $ login(Register or Login to the docker registry server)
    
    #从当前Docker registry退出
    $ logout(Logout from a Docker registry server)
    
    #输出当前容器日志信息
    $ logs(Fetch the logs of a container)
    
    #查看映射端口对应的容器内部端口
    $ port(Lookup the public-facing port which is NAT-ed to PRIVATE_PORT)	
    
    #暂停容器
    $ pause(Pause all processes with a container)	
    
    #列出容器列表
    $ ps(List containers)		
    
    #从docker镜像源服务器拉取指定镜像或者库镜像
    $ pull(Pull an image or a repository from the docker registry server)	
    
    #推送指定镜像或者库镜像到docker源服务器
    $ push(Push an image or a repository to the docker registry server)
    
    #重启运行的容器
    $ restart(Restart a running container)
    
    #移除一个或多个容器
    $ rm(Remove one or more containers)
    
    #移除一个或多个镜像[无容器使用该镜像才可删除,否则需要删除相关容器才可继续或-f强制删除]
    $ rmi(Remove one or more images)
    
    #创建一个新的容器并运行一个命令
    $ run(Run a command in a new container)
    
    #保存一个镜像为一个tar包[对应load]
    $ save(Save an image to a tar archive)
    
    #在docker hub中搜索镜像
    $ search(Search for an image on the Docker Hub)
    
    #启动容器
    $ start(Start a stopped containers)	
    
    #停止容器
    $ stop(Stop a running containers)			
    
    #给源中镜像打标签
    $ tag(Tag an image into a repository)	
    
    #查看容器中运行的进程信息
    $ top(Lookup the running processes of a container)
    
    #取消暂停容器
    $ unpause(Unpause a paused container)	
    
    #查看docker版本号
    $ version(Show the docker version information)
    
    #截取容器停止时的退出状态值
    $ wait(Block until a container stops,then print its exit code)	
    ```
  

## 四、Docker镜像

#### 1、docker镜像是什么？

是一种`轻量级、可执行`的独立软件包，它包含运行某个软件所需的所有内容，我们把应用程序和配置依赖打包好形成一个可交付的运行环境(包括代码、运行时需要的库、环境变量和配置文件等)，这个打包好的运行环境就是image镜像文件。

只有通过这个镜像文件才能生成Docker容器实例(类似Java中new出来一个对象)

#### 2、docker镜像分层

docker的镜像都是分层的

#### 3、UnionFS(联合文件系统)

Union文件系统(UnionFS)是一种分层、轻量级并且高性能的文件系统，它支持`对文件系统的修改作为一次提交来一层层的叠加`，同时可以将不同目录挂载到同一个虚拟文件系统下(unite several directories into a single virtual file system)。Union文件系统是Docker镜像的基础。镜像可以通过分层来进行继承，<span style="color:red;">基于基础镜像，可以制作具体的应用镜像。</span>

特性：一次同时加载多个文件系统，但从表面看起来，只能看到一个文件系统，联合加载会把各层文件系统叠加起来，这样最终的文件系统就会包含所有底层的文件和目录。

#### 4、Docker镜像加载原理

 docker的镜像实际上是由一层一层的文件系统组成，这种层级的文件系统称为UnionFS。

`bootfs(bootfile system)`主要包含`bootloader和kernel`，bootloader主要是`引导加载kernel`，<span style="color:red;">Linux刚启动是会加载bootfs文件系统，在Docker镜像的最底层是引导文件系统bootfs。</span>这一层与我们典型的Linux/Unix系统是一样的，包含boot加载器和内核。当boot加载完之后整个内核就都在内存中了，此时内存的使用权已由bootfs转交给内核，此时系统也会卸载bootfs。

`rootfs(root file system)`，在bootfs之上，包含的就是典型Linux系统中的/dev，/proc，/bin，/etc等标准目录和文件。`rootfs就是各种不同的操作系统发行版`，比如Ubuntu，Centos等。

对于一个精简的OS，rootfs可以很小，只需要包括最基本的命令、工具和程序库就可以了，`因为底层直接用Host的kernel，自己只需要提供rootfs就行了`。由此可见对于不同的Linux发行版，bootfs基本是一致的，rootfs会有差别，<span style="color:red;">因此不同的发行版可以公用bootfs。</span>

#### 5、Docker镜像分层结构

镜像分层最大的一个好处就是<span style="color:red;">共享资源，方便复制迁移</span>，就是为了复用。

比如有多个镜像都从相同的base镜像构建而来，那么DDocker Host只需在磁盘上保存一份base镜像；同时内存中也只需加载一份base镜像，就可以为所有容器服务了。而且<span style="color:red;">镜像的每一层都可以被共享。</span>

#### 6、Docker镜像的底层原理

<span style="color:red;">docker镜像层都是只读的，容器层是可写的。</span>

当容器启动时，一个新的可写层被加载到镜像的顶部。这一层通常被称作**容器层**，**容器层**之下的都叫**镜像层**。所有对容器的改动，无论添加、删除还是修改文件都只会发生在容器层中。只有容器层是可写的，容器层下面的所有镜像层都是只读的。

#### 7、Docker自定义镜像案例(镜像安装vim)

##### 7.1、更新包管理工具

```shell
$ apt-get update
```

##### 7.2、安装vim软件

```shell
$ apt-get install -y vim
```

##### 7.3、使用docker commit命令提交使之成为一个新镜像

```shell
#格式为docker commit -m="提交的描述信息" -a="作者" 容器id 自定义目标镜像名:[标签名]
$ docker commit -m="vim CMD add" -a="elonlo" 28b82444a584 myubuntu:2.0
```

## 五、本地镜像发布到阿里云

#### 1、进入阿里云开发者平台

```shell
https://home.console.aliyun.com/home/dashboard/ProductAndService
#左侧导航栏	产品与服务→容器服务→容器镜像服务
```

#### 2、创建镜像仓库

- 选择控制台，进入容器镜像服务

- 选择个人实例

  ![](https://cdn.jsdelivr.net/gh/elon-lo/PicGo-Image@master/img/image-20220506162217547.png)

- 创建命名空间

  ![](https://cdn.jsdelivr.net/gh/elon-lo/PicGo-Image@master/img/image-20220506162420951.png)

- 创建镜像仓库

  ![](https://cdn.jsdelivr.net/gh/elon-lo/PicGo-Image@master/img/image-20220506162652854.png)

  ![](https://cdn.jsdelivr.net/gh/elon-lo/PicGo-Image@master/img/image-20220506162728646.png)

- 将镜像推送到阿里云registry

  - 登录阿里云

    ```shell
    $ docker login --username=butterflyjuly registry.cn-hangzhou.aliyuncs.com
    #注: 用于登录的用户名为阿里云账号全名，密码为开通服务时设置的密码
    ```

    ![](https://cdn.jsdelivr.net/gh/elon-lo/PicGo-Image@master/img/image-20220506163303202.png)

  - 创建本地镜像

    ```shell
    $ docker tag [ImageId] registry.cn-hangzhou.aliyuncs.com/eloncd/myubuntu:[镜像版本号]
    ```

  - 推送本地镜像到阿里云

    ```shell
    $ docker push registry.cn-hangzhou.aliyuncs.com/eloncd/myubuntu:[镜像版本号]
    ```

    ![](https://cdn.jsdelivr.net/gh/elon-lo/PicGo-Image@master/img/image-20220506164345356.png)

- 从阿里云上拉取镜像

  ```shell
  $ docker pull registry.cn-hangzhou.aliyuncs.com/eloncd/myubuntu:[镜像版本号]
  ```


## 六、本地镜像发布到私有库

#### 1、下载镜像Docker Registry

```shell
$ docker pull registry
```

#### 2、运行私有库Registry(相当于本地有个私有Docker Hub)

```shell
$ docker run -d -p 5000:5000 -v /root/yu/registry/:/tmp/registry --privileged=true registry:latest
#注:默认情况下,仓库被创建在容器的/var/lib/registry目录下,使用-v进行目录挂载
```

#### 3、创建新镜像(ubuntu安装ifconfig命令)

##### 3.1、从Docker Hub上下载Ubuntu

```shell
$ docker pull ubuntu
```

##### 3.2、更新包管理工具

```shell
$ apt-get update
```

##### 3.3、安装net-tools

```shell
$ apt-get install -y net-tools
```

##### 3.3、生成新的镜像

```shell
$ docker commit -m="ifconfig cmd add" -a="elonlo" 07fb642508c6 elon-ubuntu:2.0
```

#### 4、查询私服上的镜像

```shell
$ curl -XGET http://ip:5000/v2/_catalog
#注:如出现一直卡顿状态请检查防火墙是否开放端口
```

#### 5、修改镜像名称

```shell
#格式 docker tag 源镜像名:tag Host:Port/Repository:Tag
$ docker tag elon-ubuntu:2.0 ip:5000/elon-ubuntu:2.0
```

#### 6、修改docker配置文件

```shell
$ vim /etc/docker/daemon.json
{
  "registry-mirrors": ["https://9dltubz7.mirror.aliyuncs.com"],
  "insecure-registries": ["ip:5000"]
}
#docker默认不允许http方式推送镜像,通过配置选项来取消这个限制
#注:修改配置文件如不生效,建议重启一下docker
```

#### 7、镜像推送到私服库

```shell
$ docker push ip:5000/elon-ubuntu:2.0
```

#### 8、验证镜像是否推送成功

```shell
$ curl -XGET http://ip:5000/v2/_catalog
```

#### 9、从私服库下载镜像

```shell
$ docker pull ip:5000/elon-ubuntu:2.0 
```

## 七、Docker容器数据卷

#### 1、docker容器权限

docker挂载主机目录后，访问容器目录时出现`cannot open directory:Permission denied`，<span style="color:red;">在挂载目录后面添加一个`--privileged=true`参数即可。</span>

如果是CentOS7及以上版本，安全模块会比之前系统版本增强，不安全的会先禁止，所以目录挂载的情况被默认为不安全的行为，在SELinux里面挂载目录被禁止掉了，如果要开启，<span style="color:red;">我们一般使用`--privileged=true`命令，扩大容器的权限解决挂载目录没有权限的问题</span>，也即使用该参数，container内的root拥有真正的root权限，否则container内的root只是外部的一个普通用户权限。

#### 2、docker容器挂载目录

创建容器时使用`-v`命令指定挂载的目录

```shell
$ docker run -d -p 6278:6278 -v 宿主机目录:容器内目录 elon-redis:v1.0

$ docker run -p 6278:6278 --restart=always \
-v /root/redis/conf/redis.conf:/usr/local/etc/redis/redis.conf \
-v /root/redis/data:/data \
--name myredis -elon-redis:v1.0 --appendonly yes

#注:宿主机目录和容器目录只能使用绝对路径,可以使用多个-v命令指定挂载的多个目录
```

**特点**：

- 数据卷可在容器之间共享或重用数据
- 卷中的更改可以直接实时生效
- 数据卷中的更改不会包含在镜像的更新中
- 数据卷的生命周期一直持续到没有容器使用它为止

**容器和宿主机之间共享数据**

- 容器修改数据，宿主机同步修改
- 宿主机修改数据，容器同步修改
- 容器停止后，宿主机修改数据，容器重启后，同步修改数据

**容器数据卷读写规则**

```shell
#不声明容器权限则默认是读写权限,即rw(read write)
$ docker run -it --privileged=true -v /docker/run:/docker/run:rw ubuntu

#指定容器为只读权限,即ro(read only)
$ docker run -it --privileged=true -v /docker/run:/docker/run:ro ubuntu
```

**卷的继承与共享**

```shell
#创建容器u1
$ docker run -it --privileged=true -v /mydocker/run:/tmp/run --name u1 myubuntu:2.0

#将容器u1的内容复制到容器u2
$ docker run -it --privileged=true --volumes-from u1 --name u2 myubuntu:2.0

#宿主机与u1、u2实现一主二从主从复,无论u1、u2容器是否停止,数据都会同步并共享
```

## 八、Docker安装常用软件

#### 1、docker安装tomcat

- 从docker hub上搜索tomcat镜像

  ```shell
  $ docker search tomcat
  ```

- 从docker hub上拉取tomcat镜像到本地

  ```shell
  $ docker pull tomcat
  ```

- 查看镜像是否拉取成功

  ```shell
  $ docker images
  ```

- 使用tomcat镜像创建容器实例(也叫运行镜像)

  ```shell
  $ docker run -it -p 8080:8080 
  ```

- 查看是否启动成功

  ```shell
  # 虚拟机或云服务器ip:端口
  $ ip:8080
  
  #	注:如无法访问,使用dockre exec -it命令进入tomcat容器,删除webapps文件夹,将webapps.dist文件夹重命名webapps,
  #	重启tomcat容器再进行访问
  ```

- tomcat版本选择

  ```shell
  #	日常使用不需要太高的tomcat版本,下面我们使用tomcat8,免去上面繁琐的配置
  $ docker pull billygoo/tomcat8-jdk8
  $ docker run -d -p 8080:8080 --name mytomcat8 billygoo/tomcat8-jdk8
  ```


#### 2、docker安装mysql

- docker下载mysql镜像

  ```shell
  $ docker pull mysql:5.7
  ```

- 使用mysql镜像创建容器实例

  ```shell
  $ docker run -d -p 23306:3306 --name mysql57 -e MYSQL_ROOT_PASSWORD mysql:5.7
  #注:	1、如在外部使用Navicat等数据库连接工具连接不成功,请检查端口是否开放,若为云服务器,需检查安全组是否开放端口
  #	 2、如数据库表插入中文失败,请在容器内使用SHOW VARIABLES LIKE '%CHARACTER%'查看字符集编码
  ```

- mysql镜像创建容器并挂载容器数据卷

  ```shell
  $ docker run -d -p 23306:3306 --privileged=true \
  -v /elon/mysql/log:/var/log/mysql \
  -v /elon/mysql/data:/var/lib/mysql \
  -v /elon/mysql/conf:/etc/mysql/conf.d \
  -e MYSQL_ROOT_PASSWORD=123456 \
  --name mysql57 \
  mysql:5.7
  ```

- 新建mysql配置文件my.cnf并同步到容器

  ```shell
  # 在/elon/mysql/conf新建my.cnf文件,加入以下配置,并通过容器数据卷同步到容器
  [client]
  default_character_set = utf8mb4
  [mysqld]
  collation_server = utf8mb4_general_ci
  character_set_server = utf8mb4
  
  #注: 1、修改完配置后,建议重新删除容器,再重新挂载容器卷启动容器
  # 	 2、docker安装完mysql并创建容器后,建议先修改完字符集编码后再新建mysql库-表-插数据
  # 	 3、修改完的配置只对之后创建的库表生效,对之前创建的库表不起作用
  ```

- 测试删除容器再创建容器是否会恢复数据

  ```shell
  # 直接删除容器
  $ docker rm -f mysql57
  
  # 重新创建容器
  $ docker run -p 23306:3306 --privileged=true \
  -v /elon/mysql/log:/var/log/mysql \
  -v /elon/mysql/data:/var/lib/mysql \
  -v /elon/mysql/conf:/etc/mysql/conf.d \
  -e MYSQL_ROOT_PASSWORD=123456 \
  --name mysql57 \
  -d mysql:5.7
  ```


#### 3、docker安装redis

- docker下载redis镜像

  ```shell
  $ docker pull redis:6.0.16
  ```

- 宿主机下创建容器数据卷映射目录

  ```shell
  # 创建文件夹,映射容器配置文件目录和数据目录
  $ mkdir /elon/redis
  
  # 复制配置文件到该文件夹下
  $ cp /root/redis/conf/redis.conf /elon/redis/ 
  ```

- 修改redis默认配置文件

  ```shell
  # 开启redis密码认证,默认是关闭的
  $ requirepass 123456
  
  # 开启redis远程连接,注释掉bind 127.0.0.1
  # bind 127.0.0.1 或 改为 bind 0.0.0.0
  
  # 将daemonize yes注释或改为daemonize no,因为该配置和docker run中的-d参数冲突,会导致容器一直启动失败
  $ daemonize no
  
  # 修改默认持久化方式为AOF
  # 将appendonly no改为appendonly yes
  $ appendonly yes
  ```

- 使用redis镜像创建容器，并配置容器数据卷

  ```shell
  $ docker run -p 26379:6379 --name redis6 --privileged=true \
  -v /elon/redis/redis.conf:/etc/redis/redis.conf \
  -v /elon/redis/data:/data \
  -d redis:6.0.16 redis-server /etc/redis/redis.conf
  
  # 如出现启动失败请检查\与-v之间换行问题,\与-v之间不能有空格
  ```

- 测试是否使用了自定义的配置文件

  ```shell
  # 修改redis数据库的个数,然后比较
  # 修改redis的密码,和修改前的进行比较
  ```

## 九、Docker集群配置

### 1、Docker安装MySQL主从复制

1、新建主服务器实例3307

```shell
$ docker run -p 3307:3306 --name mysql-master --privileged=true \
-v /elon/mysql-master/log:/var/log/mysql \
-v /elon/mysql-master/data:/var/lib/mysql \
-v /elon/mysql-master/conf:/etc/mysql \
-e MYSQL_ROOT_PASSWORD=123456 \
-d mysql:5.7

# 注: 1、映射配置文件路径到容器中只能使用/etc/mysql目录,因为mysql会优先加载/etc/mysql目录下的配置文件,其次再加载
#	  /etc/mysql/conf.d等目录下的配置
#	  2、创建容器实例的时候宿主机目录不能存在,否则会出现同步数据库不成功导致启动容器失败
```

2、进入/elon/mysql-master/conf目录下下新建my.cnf

```shell
[mysqld]
##  设置server_id,同一局域网需要唯一
server_id=101

##  指定不需要同步的数据库名称
binlog-ignore-db=mysql

##  开启二进制日志功能
log-bin=mall-mysql-bin

##  设置二进制日志使用内存大小(事务)
binlog_cache_size=1M

##  设置使用的二进制日志格式(mixed,statement,row)
binlog_format=mixed

##  二进制日志过期清理时间,默认值为0,表示不自动清理
expire_logs_days=7

##  跳过主从复制中遇到的所有错误或指定类型的错误,避免slave端复制中断
##  如: 1062是指一些主键重复,1032是因为主从数据库数据不一致
slave_skip_errors=1062
```

3、修改完配置重启master实例

```shell
$ docker restart mysql-master
```

4、进入mysql-master容器

```shell
$ docker exec -it mysql-master /bin/bash

$ mysql -uroot -p
```

5、master容器实例内创建数据同步用户

```shell
#  创建mysql中需要同步的用户
CREATE USER 'slave'@'%' IDENTIFIED BY '123456';

#  授权用户权限
GRANT REPLICATION SLAVE,REPLICATION CLIENT ON *.* TO 'slave'@'%';

#  刷新权限
FLUSH PRIVILEGES;
```

6、创建从服务器实例3308

```shell
$ docker run -p 3308:3306 --name mysql-slave --privileged=true \
-v /elon/mysql-slave/log:/var/log/mysql \
-v /elon/mysql-slave/data:/var/lib/mysql \
-v /elon/mysql-slave/conf:/etc/mysql \
-e MYSQL_ROOT_PASSWORD=123456 \
-d mysql:5.7
```

7、进入/elon/mysql-slave/conf目录下下新建my.cnf

```shell
[mysqld]
##  设置server_id,同一局域网需要唯一
server_id=102

##  指定不需要同步的数据库名称
binlog-ignore-db=mysql

##  开启二进制日志功能,以备slave作为其它数据库实例的master使用
log-bin=mall-mysql-bin

##  设置二进制日志使用内存大小(事务)
binlog_cache_size=1M

##  设置使用的二进制日志格式(mixed,statement,row)
binlog_format=mixed

##  二进制日志过期清理时间,默认值为0,表示不自动清理
expire_logs_days=7

##  跳过主从复制中遇到的所有错误或指定类型的错误,避免slave端复制中断
##  如: 1062是指一些主键重复,1032是因为主从数据库数据不一致
slave_skip_errors=1062

##  relay_log配置中继日志
relay_log=mall-mysql-relay-bin

##  log_slave_updates表示slave将复制事件写进自己的二进制日志
log_slave_updates=1

##  slave设置为只读(具有super权限的用户除外)
read_only=1
```

8、修改完配置后重启slave实例

```shell
$ docker restart mysql-slave
```

9、在**主数据库**中查看主从同步状态

```shell
$ show master status
```

10、进入mysql-slave容器

```shell
$ docker exec -it mysql-slave /bin/bash

$ mysql -uroot -p
```

11、在**从数据库**上配置主从复制

```shell
#	master_host:			主数据库的ip地址
#	master_port:			主数据库的运行端口
#	master_user:			在主数据库上创建的用于同步数据的用户账号
#	master_password:		在主数据库上创建的用于同步数据用户密码
#	master_log_file:		指定从数据库要复制数据的日志文件,通过在主数据库中show master status查看File参数
#	master_log_pos:			指定从数据库从哪个位置开始复制数据,通过show master status查看Position参数
#	master_connect_retry:	连接失败重试的时间间隔,单位为秒

$ change master to master_host='ip',master_user='slave', master_password='123456', master_port=3307,master_log_file='mall-mysql-bin.000001',master_log_pos=769,master_connect_retry=30;
```

12、在**从数据库**中查看主从同步状态

```shell
$ show slave status \G;

# 注: 查看Slave_IO_Running和Slave_SQL_Running均为no
```

13、在**从数据库**中开启主从同步

```shell
$ start slave;

$ show slave status \G;
# 注: 查看Slave_IO_Running和Slave_SQL_Running均为yes
```

14、主从复制测试

```shell
#	主数据库新建库-新建表-插入数据		ok
#	从数据库-查看数据是否同步			 ok	
```

### 2、Docker安装Redis集群

**redis大数据量缓存解决方案(1~2亿条)**

单机单台肯定不行，只能使用分布式存储，用redis如何落地？

目前有三种解决方案：

- 哈希取余分区

  ```markdown
  2亿条记录就是两亿个k,v，单机不行只能使用分布式多机,假设三台机器构成一个集群,用户每次读写都根据公式:hash(key)%N台机器数,计算出哈希值,用来决定数据映射到哪一个节点上。
  
  优点:	简单粗暴,直接有效,只需要预估好数据规划好节点,例如3台,5台,8台,就能保证一段时间的数据支撑。使用Hash算法使固定的一部分请求落在同一台服务器上,这样每台服务器固定处理一部分请求(并维护这些请求的信息),起到负载均衡+分而治之的作用
  
  缺点:	如果出现扩缩容,就会出现集群机器台数的变化,映射关系需要重新计算,在服务器个数固定不变时没有问题,如果需要弹性扩容或故障停机的情况下,原来的取模公式就会发生变化。此时地址经过取余运算的结果将会发生很大变化,根据公式获取的服务器也会变得不可控。
  如某个redis机器宕机了,由于集群机器台数变化,会导致hash取余获取数据的变化
  ```

- 一致性哈希算法分区

  - 什么是一致性Hash算法?

    **一致性哈希算法**在1997年由麻省理工学院提出,是一种特殊的哈希算法,`在移除或者添加一个服务器时,能够尽可能小地改变已存在的服务请求与处理请求服务器之间的映射关系;一致性哈希解决了简单哈希算法在分布式`哈希表`（Distributed Hash Table，DHT）中存在的动态伸缩等问题`。

    一致性hash算法本质也是一种取模算法；不过不同于按服务器数量取模，一致性hash是`对固定的2^32取模`。

    ```markdown
    IPv4的地址是4组8位2进制数组成,所以用2^32可以保证每个IP地址都有唯一的映射。
    ```

    构建一致性hash算法的步骤如下：

    - **构建hash环**

      我们可以将这`2^32`个值抽象成一个圆环⭕️，圆环的正上方的点表示0，顺时针排列，依次为1、2、3...直到`2^32-1`，而这个由`2^32`个点组成的圆环统称为`hash环`;

      ![](https://cdn.jsdelivr.net/gh/elon-lo/PicGo-Image@master/img/fc48f539bd957e74ef54c2e71c3052f1.jpeg)

    - **服务器映射到hash环**

      在对服务器进行映射时，使用`hash(服务器ip) % 2^32`，即：

      ```markdown
      使用服务器IP地址进行hash运算，用哈希后的结果对2^32取模,结果一定是一个0到2^32-1之间的整数;
      而这个整数映射到hash环上的位置代表了一个服务器,依次将node0、node1、node2三个缓存服务器映射到hash环上;
      ```

      ![](https://cdn.jsdelivr.net/gh/elon-lo/PicGo-Image@master/img/16be733928f3f852df11fd383ee16c59.jpeg)

    - **对象key映射到服务器**

      在对应的key映射到具体的服务器时，需要首先计算key的hash值：`hash(key) % 2^32`；

      ```markdown
      注:	此处的hash函数可以和之前计算服务器映射至hash环的函数不同,只要保证取值范围和hash环的范围相同即可(即2^32)
      ```

      将key映射至服务器遵循下面的逻辑：

      ```markdown
      从缓存对象key的位置开始,沿顺时针方向遇到的第一个服务器,便是当前对象将要缓存到的服务器;
      ```

      假设我们有 "semlinker"、"kakuqo"、"lolo"、"fer" 四个对象，分别简写为 o1、o2、o3 和 o4；

      首先，使用哈希函数计算这个对象的 hash 值，值的范围是 [0, 2^32-1]：

      ![](https://cdn.jsdelivr.net/gh/elon-lo/PicGo-Image@master/img/d8e84d51b800ecdd7ace107b4b320df3.jpeg)

      图中对象映射的关系如下：

      ```markdown
      hash(o1) = k1; hash(o2) = k2;
      hash(o3) = k3; hash(o4) = k4;
      ```

      同时 3 台缓存服务器，分别为 CS1、CS2 和 CS3：

      ![](https://cdn.jsdelivr.net/gh/elon-lo/PicGo-Image@master/img/b82de55bf05d0f69bddb2d1db408afca.jpeg)

      则可知，各对象和服务器的映射关系如下：

      ```markdown
      K1 => CS1
      K4 => CS3
      K2 => CS2
      K3 => CS1
      ```

      ![](https://cdn.jsdelivr.net/gh/elon-lo/PicGo-Image@master/img/af1ae3810f9496f0e43b68a7b3edee2b.jpeg)

      以上便是一致性 Hash 的工作原理；

      **可以看到，一致性 Hash 就是：将原本单个点的 Hash 映射，转变为了在一个环上的某个片段上的映射！**

    

    下面我们来看几种服务器扩缩容的场景；

    - **服务器增加**

      假如业务量激增，我们需要增加一台服务器 CS4，经过同样的 hash 运算，该服务器最终落于 t1 和 t2 服务器之间，具体如下图所示：

      ![](https://cdn.jsdelivr.net/gh/elon-lo/PicGo-Image@master/img/bc48dc037a5b1438649f73e4f0408c4b.jpeg)

      此时，只有 t1 和 t2 服务器之间的部分对象需要重新分配；

      在以上示例中只有 o3 对象需要重新分配，即它被重新到 CS4 服务器；

      在前面我们已经说过：如果使用简单的取模方法，当新添加服务器时可能会导致大部分缓存失效，而使用一致性哈希算法后，这种情况得到了较大的改善，因为只有少部分对象需要重新分配！

      

    - **服务器减少**

      假设 CS3 服务器出现故障导致服务下线，这时原本存储于 CS3 服务器的对象 o4，需要被重新分配至 CS2 服务器，其它对象仍存储在原有的机器上：

      ![](https://cdn.jsdelivr.net/gh/elon-lo/PicGo-Image@master/img/3bced258982f20084ec8cfced6799703.jpeg)

      **此时受影响的数据只有 CS2 和 CS3 服务器之间的部分数据！**

      

    一致性hash算法也有以下缺点：

    **数据偏斜&服务器性能平衡问题**

    在上面给出的例子中，各个服务器几乎是平均被均摊到 Hash 环上；

    但是在实际场景中很难选取到一个 Hash 函数这么完美的将各个服务器散列到 Hash 环上；

    此时，在服务器节点数量太少的情况下，很容易因为**节点分布不均匀而造成数据倾斜问题；**

    如下图被缓存的对象大部分缓存在`node-4`服务器上，导致其他节点资源浪费，系统压力大部分集中在`node-4`节点上，这样的集群是非常不健康的：

    ![](https://cdn.jsdelivr.net/gh/elon-lo/PicGo-Image@master/img/bef4e534867476a50f14df843be11150.jpeg)

    同时，还有另一个问题：

    在上面新增服务器 CS4 时，CS4 只分担了 CS1 服务器的负载，服务器 CS2 和 CS3 并没有因为 CS4 服务器的加入而减少负载压力；如果 CS4 服务器的性能与原有服务器的性能一致甚至可能更高，那么这种结果并不是我们所期望的；

- 哈希槽分区

  **Redis集群(Redis Cluster)没有使用一致性hash，而是引入了哈希槽的概念**	

  - 哈希槽

    Redis Cluster 采用虚拟哈希槽分区，所有的键根据哈希函数映射到 0 ~ 16383 (2^14-1)整数槽内**，每个key通过CRC16校验后对16384取模来决定放置哪个槽(Slot)，**每一个节点负责维护一部分槽以及槽所映射的键值数据。

    计算公式：slot = CRC16(key) % 16383

    这种结构很`容易添加或者删除节点`，并且无论是添加删除或者修改某一个节点，都`不会造成集群不可用`的状态。**使用哈希槽的好处就在于可以方便的添加或移除节点。**

    - 增加节点

      当需要增加节点时，只需要把其他节点的某些哈希槽挪到新节点就可以了；

    - 减少节点

      当需要移除节点时，只需要把移除节点上的哈希槽挪到其他节点就行了。

- **Redis三主三从集群扩缩容搭建**

  - Redis集群配置

    - 新建6个docker容器实例

      ```shell
      docker run --name redis-node-1 --net host --privileged=true \
      -v /elon/redis-cluster/data/redis/share/redis-node-1:/data \
      -d redis:6.0.16 --cluster-enabled yes --appendonly yes --port 6381 
      
      docker run --name redis-node-2 --net host --privileged=true \
      -v /elon/redis-cluster/data/redis/share/redis-node-2:/data \
      -d redis:6.0.16 --cluster-enabled yes --appendonly yes --port 6382
      
      docker run --name redis-node-3 --net host --privileged=true \
      -v /elon/redis-cluster/data/redis/share/redis-node-3:/data \
      -d redis:6.0.16 --cluster-enabled yes --appendonly yes --port 6383
      
      docker run --name redis-node-4 --net host --privileged=true \
      -v /elon/redis-cluster/data/redis/share/redis-node-4:/data \
      -d redis:6.0.16 --cluster-enabled yes --appendonly yes --port 6384
      
      docker run --name redis-node-5 --net host --privileged=true \
      -v /elon/redis-cluster/data/redis/share/redis-node-5:/data \
      -d redis:6.0.16 --cluster-enabled yes --appendonly yes --port 6385
      
      docker run --name redis-node-6 --net host --privileged=true \
      -v /elon/redis-cluster/data/redis/share/redis-node-6:/data \
      -d redis:6.0.16 --cluster-enabled yes --appendonly yes --port 6386
      
      #注: \与下一行之间不能有空格,否则会导致启动失败,cluster-enabled、appendonly、port必须写在镜像名之后,否则也会导致启动失败
      ```

    - 进入任意容器为6台机器构建集群关系

      ```shell
      # 进入1号节点容器
      $ docker exec -it redis-node-1 /bin/bash
      ```

      ![](https://cdn.jsdelivr.net/gh/elon-lo/PicGo-Image@master/img/image-20220528174642231.png)

      ```shell
      # 查看服务器ip地址
      $ ifconfig
      ```

      ![](https://cdn.jsdelivr.net/gh/elon-lo/PicGo-Image@master/img/image-20220528174906473.png)

      ```shell
      # 执行命令配置集群主从关系
      $ redis-cli --cluster create 10.0.16.9:6381 10.0.16.9:6382 10.0.16.9:6383 \
      10.0.16.9:6384 10.0.16.9:6385 10.0.16.9:6386 --cluster-replicas 1
      
      $ redis-cli --cluster create 172.17.0.1:6381 172.17.0.1:6382 172.17.0.1:6383 \
      172.17.0.1:6384 172.17.0.1:6385 172.17.0.1:6386 --cluster-replicas 1
      
      # 使用两个ip地址创建集群均可
      ```

      ![](https://cdn.jsdelivr.net/gh/elon-lo/PicGo-Image@master/img/image-20220528175225221.png)

      ![](https://cdn.jsdelivr.net/gh/elon-lo/PicGo-Image@master/img/image-20220528175247870.png)

      ```shell
      # 连接6381查看集群状态
      $ redis-cli -p 6381
      
      # 查看集群信息
      $ cluster info
      ```

      ![](https://cdn.jsdelivr.net/gh/elon-lo/PicGo-Image@master/img/image-20220528175351289.png)

      ```shell
      # 查看集群节点主从关系
      $ cluster nodes
      
      # 由下图可知
      M			S
      1			5
      2			6
      3			4
      ```

      ![](https://cdn.jsdelivr.net/gh/elon-lo/PicGo-Image@master/img/image-20220528175522805.png)

  - Redis主从容错切换迁移

    - 数据读写存储

      **重新进入redis-node-1机器，连接6381端口，测试存储key**

      ```shell
      $ docker exec -it redis-node-1 /bin/bash
      
      $ redis-cli -p 6381
      
      $ set k1 v1
      $ set k2 v2
      
      # 单机方式启动,无法自动重定向到其他节点
      ```

      ![](https://cdn.jsdelivr.net/gh/elon-lo/PicGo-Image@master/img/image-20220528180900158.png)

      **防止路由失效，启动时加入`-c`参数，表示以集群方式启动**

      ```shell
      $ redis-cli -p 6381 -c
      ```

      ![](https://cdn.jsdelivr.net/gh/elon-lo/PicGo-Image@master/img/image-20220528181238515.png)

      **查看集群信息**

      ```shell
      $ redis-cli --cluster check 172.17.0.1:6381
      ```

      ![](https://cdn.jsdelivr.net/gh/elon-lo/PicGo-Image@master/img/image-20220528181704233.png)

    - 容错切换迁移

      **主机6381和从机6385切换，先停止主机**

      ```shell
      $ docker stop redis-node-1
      ```

      **使用主机6382进入容器查看集群信息**

      ```shell
      $ docker exec -it redis-node-2 /bin/bash
      
      $ redis-cli -p 6382 -c
      
      $ cluster nodes
      ```

      ![](https://cdn.jsdelivr.net/gh/elon-lo/PicGo-Image@master/img/image-20220528183003192.png)

      **恢复主机6381，再次查看容器状态**

      ```shell
      $ docker start redis-node-1
      
      $ redis-cli --cluster check 172.17.0.1:6381
      
      $ redis-cli -p 6381 -c
      
      $ cluster nodes
      
      # 可以看到6381与6385机器主从状态已调换
      ```

      ![](https://cdn.jsdelivr.net/gh/elon-lo/PicGo-Image@master/img/image-20220528183410361.png)

      ![](https://cdn.jsdelivr.net/gh/elon-lo/PicGo-Image@master/img/image-20220528183511247.png)

      **还原之前的三主三从配置**

      ```shell
      $ docker stop redis-node-5
      
      $ docker start redis-node-5
      
      $ docker exec -it redis-node-1 /bin/bash
      
      $ redis-cli -p 6381 -c
      
      $ cluster nodes
      ```

      ![](https://cdn.jsdelivr.net/gh/elon-lo/PicGo-Image@master/img/image-20220528184304712.png)

      ![](https://cdn.jsdelivr.net/gh/elon-lo/PicGo-Image@master/img/image-20220528184414356.png)

      **查看集群状态**

      ![](https://cdn.jsdelivr.net/gh/elon-lo/PicGo-Image@master/img/image-20220528184535267.png)

  - Redis主从扩容

    - 新建6387、6388两个节点，启动后查看节点数

      ```shell
      docker run --name redis-node-7 --net host --privileged=true \
      -v /elon/redis-cluster/data/redis/share/redis-node-7:/data \
      -d redis:6.0.16 --cluster-enabled yes --appendonly yes --port 6387
      
      docker run --name redis-node-8 --net host --privileged=true \
      -v /elon/redis-cluster/data/redis/share/redis-node-8:/data \
      -d redis:6.0.16 --cluster-enabled yes --appendonly yes --port 6388
      
      $ docker ps
      ```

      ![](https://cdn.jsdelivr.net/gh/elon-lo/PicGo-Image@master/img/image-20220528195130535.png)

    - 进入6387容器内部

      ```shell
      $ docker exec -it redis-node-7 /bin/bash
      ```

    - 将新增的6387节点(空槽位)作为master节点加入原集群

      ```shell
      # 将新增的6387作为master节点加入集群
      $ redis-cli --cluster add-node 172.17.0.1:6387 172.17.0.1:6381
      
      # 6381就是原集群的master节点,理论可以用所有master节点,相当于通过6381找到组织加入集群
      ```

      ![](https://cdn.jsdelivr.net/gh/elon-lo/PicGo-Image@master/img/image-20220528195848689.png)

    - 检查集群信息

      ```shell
      $ redis-cli --cluster check 172.17.0.1:6381
      ```

      ![](https://cdn.jsdelivr.net/gh/elon-lo/PicGo-Image@master/img/image-20220528200152224.png)

    - 重新分配槽号

      ```shell
      $ redis-cli --cluster reshard 172.17.0.1:6381
      ```

      ![](https://cdn.jsdelivr.net/gh/elon-lo/PicGo-Image@master/img/image-20220528201131127.png)

    - 再次检查集群信息

      ```shell
      redis-cli --cluster check 172.17.0.1:6381
      ```

      ![](https://cdn.jsdelivr.net/gh/elon-lo/PicGo-Image@master/img/image-20220528201709803.png)

    - 槽号重新分配说明

      ```markdown
      为什么6387槽号是三个区间?
      因为重新分配成本太高,所以前n个节点各自均分出一部分给新增的节点
      ```

    - 为主节点6387分配从节点6388

      ```shell
      $ redis-cli --cluster add-node 172.17.0.1:6388 172.17.0.1:6387 --cluster-slave \
      --cluster-master-id fbf51246a0f121d899a71223ec0b78b91ef5de6b
      ```
      
      ![](https://cdn.jsdelivr.net/gh/elon-lo/PicGo-Image@master/img/image-20220528215742613.png)
      
    - 检查集群信息，查看节点
  
      ```shell
      $ redis-cli --cluster check 172.17.0.1:6381
      ```
  
      ![](https://cdn.jsdelivr.net/gh/elon-lo/PicGo-Image@master/img/image-20220528220242865.png)
  
  - Redis主从缩容
  
    - 检查集群信息获得从节点6388的节点id
  
      ```shell
      $ redis-cli --cluster check 172.17.0.1:6388
      ```
  
      ![](https://cdn.jsdelivr.net/gh/elon-lo/PicGo-Image@master/img/image-20220528220746926.png)
  
    - 从集群中将从节点6388删除
  
      ```shell
      $ redis-cli --cluster del-node 172.17.0.1:6388 cbdfd3bc84a17d33bab4c29849eb36c675d34fce
      ```
  
      ![](https://cdn.jsdelivr.net/gh/elon-lo/PicGo-Image@master/img/image-20220528221055746.png)
  
    - 将6387节点的槽位清空，重新分配，此次将6387的槽位全部分配给6381
  
      ```shell
      $ redis-cli --cluster reshard 172.17.0.1:6381
      ```
  
      ![](https://cdn.jsdelivr.net/gh/elon-lo/PicGo-Image@master/img/image-20220528222104774.png)
  
      ![](https://cdn.jsdelivr.net/gh/elon-lo/PicGo-Image@master/img/image-20220528222342270.png)
  
    - 从集群中将节点6387删除
  
      ```shell
      $ redis-cli --cluster del-node 172.17.0.1:6387 fbf51246a0f121d899a71223ec0b78b91ef5de6b
      ```
  
      ![](https://cdn.jsdelivr.net/gh/elon-lo/PicGo-Image@master/img/image-20220528222720134.png)
  

## 十、Docker时区

#### 1、查看时间

```shell
# 查看宿主机时间
$ date

# 查看容器内时间
$ docker exec [continer id] date
```

#### 2、容器设置时区

```shell
# 创建容器时指定时区,加上如下参数,即可使用宿主机时间
$ -e TZ=Asia/Shanghai

# 使用Dockerfile文件设置时区
$ RUN cp /usr/share/zoneinfo/Asia/Shanghai /etc/localtime && echo 'Asia/Shanghai' >/etc/timezone
# 如失败,请用以下命令
$ RUN ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime && echo 'Asia/Shanghai' >/etc/timezone

# 使用cp命令复制宿主机时区到容器
$ docker cp /usr/share/zoneinfo/Asia/Shanghai [continer id]:/etc/localtime
# 注: 此方法只能改变一次,如容器被删除需要重新
```

## 十一、Dockerfile解析

#### 1、概述

Dockerfile是`用来构建Docker镜像`的文本文件，是由一条条构建镜像所需的`指令和参数`构成的脚本。

#### 2、官网文档地址

<https://docs.docker.com/engine/reference/builder/>

#### 3、构建Dockerfile步骤

- 编写Dockerfile文件
- docker build命令构建镜像
- docker run根据镜像创建容器实例

#### 4、Dockerfile构建过程

- Dockerfile内容基础知识
  - 每条保留字指令都<span style="color:red;">必须为大写字母</span>且后面至少要跟一个参数
  - 指令按照从上到下，顺序执行
  - #表示注释
  - 每条指令都会创建一个新的镜像层并对镜像进行提交
- Docker执行Dockerfile流程
  - docker会从Dockerfile文件头FROM指定的基础镜像运行一个容器
  - 然后执行一条指令，对容器作出修改
  - 接着执行类似于docker commit的操作，创建一个新的镜像层
  - 再基于刚创建的镜像运行一个新的容器

#### 5、Dockerfile常用保留字指令

- FROM：指定基础镜像，必须为Dockerfile中的第一个命令

  - 格式：

    ```shell
    FROM <image>
    FROM <image>:<tag>
    FROM <image>@<digest>
    ```

  - 示例：

    ```shell
    FROM mysql:5.7
    ```

    注：tag或digest是可选的,如果不使用这两个值时,会使用latest版本的基础镜像

  

- MAINTAINER：维护者信息

  - 格式：

    ```shell
    MAINTAINER <name>
    ```

  - 示例：

    ```shell
    MAINTAINER Jack
    MAINTAINER jack@163.com
    MAINTAINER Jack <jack@163.com>
    ```

  

- RUN：构建镜像时执行的命令，一个文件中可以包括多个RUN命令

  RUN用于在镜像容器中执行命令,有以下两种命令执行方式：

  - shell执行，即/bin/sh 

    - 格式：

      ```shell
      RUN <command>
      ```

    - 示例：

    ```shell
    RUN yum install -y nginx 
    ```

  - exec执行

    - 格式：

      ```shell
      RUN ["executable", "param1", "param2"]
      ```

    - 示例：

      ```shell
      RUN ["yum", "install", "-y", "nginx"] 
      ```

      注：executable是命令，后面的param是参数

      由于RUN命令会生成一个镜像层，所以RUN并不是越多越好，需要合理使用,如果一个RUN中执行多个命令，可以使用 && 连接，如果命令过长，可以使用 \ 换行：

      ```shell
      RUN apt-get update && apt-get install -y \  
       	bzr \
       	cvs \
       	git \
       	mercurial \
       	subversion
      ```

      且这样写还有个优点，apt-get update 和 apt-get install 被放在一个 RUN 指令中执行，这样能够保证每次安装的是最新的包。如果 apt-get install 在单独的 RUN 中执行，则会使用 apt-get update 创建的镜像层，而这一层可能是很久以前缓存的

    

    ​		注：RUN指令创建的中间镜像会被缓存,并会在下次构建中使用。如果不想使用这些缓存镜像,可以在		构建时指定--no-cache参数

    ```shell
    docker build ... --no-cache
    ```

  

- EXPOSE：指定暴露镜像的端口供主机做映射

  - 格式：

    ```shell
    EXPOSE <port> [<port>...]
    ```

  - 示例：

    ```shell
    EXPOSE 80 443
    EXPOSE 8080
    EXPOSE 11211/tcp 11211/udp
    ```

    注：EXPOSE并不会让容器的端口访问到主机。要使其可访问，需要在docker run运行容器时通过-p来发布这些端口，或通过-P参数来发布EXPOSE导出的所有端口	

  

- WORKDIR：指定工作目录，类似于cd命令，之后的命令都是基于此工作目录

  - 格式：

    ```shell
    WORKDIR /path/to/workdir
    ```

  - 示例：

    ```shell
    WORKDIR /a  (这时工作目录为/a)
    WORKDIR b  (这时工作目录为/a/b)
    WORKDIR c  (这时工作目录为/a/b/c)
    ```

    注：通过WORKDIR设置工作目录后，Dockerfile中其后的命令RUN、CMD、ENTRYPOINT、ADD、COPY等命令都会在该目录下执行。在使用docker run运行容器时，可以通过-w参数覆盖构建时所设置的工作目录。

  

- USER：指定运行容器时的用户名或 UID，后续的操作都会使用指定用户。使用USER指定用户时，可以使用用户名、UID或GID，或是两者的组合。当服务不需要管理员权限时，可以通过该命令指定运行用户。并且可以在之前创建所需要的用户

  - 格式：

    ```shell
    USER user
    USER user:group
    USER uid
    USER uid:gid
    USER user:gid
    USER uid:group
    ```

  - 示例：

    ```shell
    USER www
    ```

    注：使用USER指定用户后，Dockerfile中其后的命令RUN、CMD、ENTRYPOINT都将使用该用户。镜像构建完成后，通过docker run运行容器时，可以通过-u参数来覆盖所指定的用户。

  

- ENV：设置环境变量

  - 格式：

    ```shell
    ENV <key> <value>
    ```

    <key>之后的所有内容均会被视为其<value>的组成部分,因此，一次只能设置一个变量

    ```shell
    ENV <key>=<value> ...
    ```

    可以设置多个变量，每个变量为一个"<key>=<value>"的键值对，如果<key>中包含空格，可以使用\来进行转义，也可以通过""来进行标示；另外，反斜线也可以用于续行

  - 示例：

    ```shell
    ENV myName John Doe
    ENV myDog Rex The Dog
    ENV myCat=fluffy
    ```

  

- ADD：将本地文件添加到容器中，tar类型文件会自动解压(网络压缩资源不会被解压)，可以访问网络资源，类似wget

  - 格式：

    ```shell
    ADD <src>... <dest>
    
    # 用于支持包含空格的路径
    ADD ["<src>",... "<dest>"]
    ```

  - 示例：

    ```shell
    # 添加所有以"hom"开头的文件
    ADD hom* /mydir/    
    
    # ? 替代一个单字符,例如："home.txt"
    ADD hom?.txt /mydir/     
    
    # 添加 "test" 到 `WORKDIR`/relativeDir/
    ADD test relativeDir/     
    
    # 添加 "test" 到 /absoluteDir/
    ADD test /absoluteDir/    
    ```

    注：如果目的位置不存在，Docker会自动创建所需要的目录，需要复制的本地文件一定要放在Dockerfile文件的同级目录下

    原因：因为构建环境将会先上传到Docker守护进程，而复制是在Docker守护进程中进行的。任何位于构建环境之外的东西都是不可用的。ADD指令的目的的位置则必须是容器内部的一个绝对路径。

  

- COPY：功能类似ADD，但是是不会自动解压文件，也不能访问网络资源，其他限制条件跟ADD一样

  

- VOLUME：添加卷，用于指定持久化目录

  - 格式：

    ```shell
    VOLUME ["/path/to/dir"]
    ```

  - 示例：

    ```shell
    VOLUME ["/data"]
    VOLUME ["/var/www", "/var/log/apache2", "/etc/apache2"]
    ```

    一个卷可以存在于一个或多个容器的指定目录，该目录可以绕过联合文件系统，并具有以下功能：

    1. 卷可以容器间共享和重用
    2. 容器并不一定要和其它容器共享卷
    3. 修改卷后会立即生效
    4. 对卷的修改不会对镜像产生影响
    5. 卷会一直存在，直到没有任何容器在使用它

  

- CMD：构建容器后调用，也就是在容器启动时才进行调用，存在多个CMD时只有最后一个生效，也支持exec语法。

  - 格式：

    ```shell
    # 执行可执行文件，优先
    CMD ["executable","param1","param2"]
    
    # 设置了ENTRYPOINT，则直接调用ENTRYPOINT添加参数
    CMD ["param1","param2"]
    
    # 执行shell内部命令
    CMD command param1 param2
    ```

  - 示例：

    ```shell
    CMD echo "This is a test." | wc -
    CMD ["/usr/bin/wc","--help"]
    ```

    注：CMD不同于RUN，CMD用于指定在容器启动时所要执行的命令，而RUN用于指定镜像构建时所要执行的命令。

  

- ENTRYPOINT：配置容器，使其可执行化。配合CMD可省去"application"，只使用参数。

  - 格式：

    ```shell
    # 可执行文件, 优先
    ENTRYPOINT ["executable", "param1", "param2"]
    
    # shell内部命令
    ENTRYPOINT command param1 param2
    ```

  - 示例：

    ```shell
    FROM ubuntu
    ENTRYPOINT ["top", "-b"]
    CMD ["-c"]
    ```

    注：ENTRYPOINT与CMD非常类似，不同的是通过docker run执行的命令不会覆盖ENTRYPOINT，而docker run命令中指定的任何参数，都会被当做参数再次传递给ENTRYPOINT。Dockerfile中只允许有一个ENTRYPOINT命令，多指定时会覆盖前面的设置，而只执行最后的ENTRYPOINT指令。

  

- LABEL：用于为镜像添加元数据

  - 格式：

    ```shell
    LABEL <key>=<value> <key>=<value> <key>=<value> ...
    ```

  - 示例：

    ```shell
    LABEL version="1.0" description="这是一测试工程"
    ```

    注：使用LABEL指定元数据时，一条LABEL指定可以指定一或多条元数据，指定多条元数据时不同元数据之间通过空格分隔。推荐将所有的元数据通过一条LABEL指令指定，以免生成过多的中间镜像。

  

- ARG：用于指定传递给构建运行时的变量

  - 格式：

    ```shell
    ARG <name>[=<default value>]
    ```

  - 示例：

    ```shell
    ARG site
    ARG build_user=www
    ```

  

- ONBUILD：用于设置镜像触发器

  - 格式：

    ```shell
    ONBUILD [INSTRUCTION]
    ```

  - 示例：

    ```shell
    ONBUILD ADD . /app/src
    ONBUILD RUN /usr/local/bin/python-build --dir /app/src
    ```

    注：当所构建的镜像被用做其它镜像的基础镜像时(比如用户的镜像需要从某为准备好的位置添加源代码，或者用户需要执行特定于构建镜像的环境的构建脚本)，该镜像中的触发器将会被钥触发

  

  ​		例如创建镜像image-A  FROM ubuntu  ...  ONBUILD ADD . /var/www  ...

  ​		然后创建镜像image-B,指定image-A为基础镜像,如  FROM image-A  ...

  ​		然后在构建image-B的时候,日志上显示如下:  Step 0 : FROM image-A  # Execting 1 build triggers  Step 		onbuild-0 : ADD . /var/www  ...



<span style="color:red;">注意：CMD和ENTRYPOINT同样作为容器启动时执行的命令，区别有以下几点：
     CMD的命令会被 docker run 的命令覆盖而ENTRYPOINT不会。 如使用CMD ["/bin/bash"]或ENTRYPOINT ["/bin/bash"]后，再使用docker run -ti image启动容器，它会自动进入容器内部的交互终端，如同使用docker run -ti image /bin/bash。
     但是如果启动镜像的命令为docker run -ti image /bin/ps，使用CMD后面的命令就会被覆盖转而执行bin/ps命令，而ENTRYPOINT的则不会，而是会把docker run 后面的命令当做ENTRYPOINT执行命令的参数。

</span>



```markdown
Dockerfile中为
ENTRYPOINT ["/user/sbin/nginx"]

然后通过启动build之后的容器
docker run -ti image -g "daemon off"

此时-g "daemon off"会被当成参数传递给ENTRYPOINT，最终的命令变成了
/user/sbin/nginx -g "daemon off"如果Dockerfile中定义的是CMD，则会被覆盖
```



<span style="color:red;">CMD和ENTRYPOINT都存在时，CMD的指令就变成了ENTRYPOINT的参数，并且此CMD提供的参数也会被 docker run 后面的命令覆盖</span>

```markdown
Dockerfile中指令
...
ENTRYPOINT ["echo","hello","i am"]
CMD ["docker"]

之后启动构建之后的容器

使用docker run -ti image
输出“hello i am docker”

使用docker run -ti image world
输出“hello i am world”
```



#### 6、Dockerfile案例

- 下载linux版本的jdk

  <https://www.oracle.com/java/technologies/downloads/archive/>

- 编写Dockerfile文件

  ```dockerfile
  FROM centos:7
  MAINTAINER elonlo1024@gmail.com
  
  #声明环境变量
  ENV MY_PATH /usr/local
  #指定进入容器时和文件下载的路径
  WORKDIR $MY_PATH
  
  #安装vim编辑器
  RUN yum -y install vim
  #安装ifconfig命令
  RUN yum -y install net-tools
  #安装jdk及jdk需要的包
  RUN yum -y install glibc.i686
  RUN mkdir -p /usr/local/jdk
  #ADD 是相对路径,把jdk压缩包添加到容器中,安装包必须要和Dockerfile文件在同一目录下
  ADD jdk-8u171-linux-x64.tar.gz /usr/local/jdk
  #配置java环境变量
  ENV JAVA_HOME=/usr/local/jdk/jdk1.8.0_171
  ENV CLASSPATH .:$JAVA_HOME/lib/dt.jar:$JAVA_HOME/lib/tools.jar:$JAVA_HOME/lib
  ENV PATH $JAVA_HOME/bin:$PATH
  
  EXPOSE 82
  
  CMD echo $MY_PATH
  CMD echo "success.............ok"
  CMD /bin/bash
  ```
  

​		注：建议将需要的文件最好和Dockerfile文件放在同一目录下，注意JAVA_HOME的路径，由于使用ADD命令复制压缩包并解压，<span style="color:red;">如出现Java命令无法使用，请查看容器内的JAVA_HOME地址与Dockerfile中的JAVA_HOME地址是否一致</span>

#### 7、虚悬镜像

- 测试添加虚悬镜像

  ```shell
  #创建测试文件夹
  mkdir /root/myfile/test
  ```

  ```shell
  #创建Dockerfile文件
  vim Dockerfile
  
  #添加以下内容
  FROM ubuntu
  CMD echo "sucess!!!"
  ```

  ```shell
  #构建虚悬镜像
  docker build .
  ```

- 查看虚悬镜像

  ```shell
  docker image ls -f dangling=true
  ```

  ![](https://cdn.jsdelivr.net/gh/elon-lo/PicGo-Image@master/img/image-20220605171215166.png)

- 删除虚悬镜像

  ```shell
  docker image prune
  ```



## 十二、Docker网络

#### 1、作用

- 能实现容器间的互联和通信以及端口映射
- 容器ip变动的时候可以通过服务名直接网络通信而不受到影响

#### 2、常用基本命令

- 查看网络

  ```shell
  $ docker network ls
  NETWORK ID     NAME      DRIVER    SCOPE
  33abe70fd325   bridge    bridge    local
  e3f6fee1e023   host      host      local
  5b29a77775e3   none      null      local
  ```

  Docker内置这<span style="color:red;">三个网络</span>，运行容器时，你可以使用该<span style="color:red;">--network</span>标志来指定容器应连接到哪些网络。

  该bridge网络代表docker0所有Docker安装中存在的网络。除非你使用该docker run --network=<NETWORK>选项指定，否则Docker守护程序默认将容器连接到此网络。

  我们在使用docker run创建Docker容器时，可以用<span style="color:red;"> --net 选项指定容器的网络模式</span>，Docker可以有以下4种网络模式：

  host模式：使用 --net=host 指定。

  none模式：使用 --net=none 指定。

  bridge模式：使用 --net=bridge 指定，默认设置。

  container模式：使用 --net=container:NAME_or_ID 指定。

- 查看网络源数据

  ```shell
  $ docker network inspect [network id]
  ```

- 创建网络

  ```shell
  # 如不指定,则创建的默认是bridge模式
  $ docker network create [network name]
  
  # 创建指定网络模式的网络
  $ docker network create --driver [network model] new_bridge
  ```

- 删除网络

  ```shell
  $ docker network rm [network name]
  ```


#### 3、网络模式

| 网络模式  | 简介                                                         |
| --------- | ------------------------------------------------------------ |
| bridge    | 此模式会为每一个容器分配、设置 IP 等，并将容器连接到一个 docker0 虚拟网桥，通过 docker0 网桥以及 Iptables nat 表配置与宿主机通信 |
| host      | 容器不会虚拟出自己的网卡，配置自己的IP等，而是使用宿主机的IP和端口 |
| none      | 该模式关闭了容器的网络功能                                   |
| container | 新创建的容器不会创建自己的网卡和配置自己的IP，而是和一个指定的容器共享IP、端口范围等 |

4种模式如下：

- bridge模式：使用--network bridge指定，默认设置。

  相当于Vmware中的Nat模式，容器使用独立network Namespace，<span style="color:red;">并连接到docker0虚拟网卡（默认模式）</span>。通过docker0网桥以及Iptables nat表配置与宿主机通信；bridge模式是Docker默认的网络设置，此模式会为每一个容器分配Network Namespace、设置IP等，并将一个主机上的Docker容器连接到一个虚拟网桥上。

  ```shell
  # 查看bridge网络的详细信息,并通过grep获取名称
  $ docker network inspect bridge | grep name
  
  $ ifconfig | grep docker 
  
  $ docker run -d -p 8081:8080 --network bridge --name tomcat81 billygoo/tomcat8-jdk8
  ```

  - 查看容器网络情况

    ```shell
    $ docker inspect tomcat81 | tail -n 20
    ```

    ![image-20220702195538115](https://cdn.jsdelivr.net/gh/elon-lo/PicGo-Image@master/img/image-20220702195538115.png)

- host模式：使用--network host指定

  相当于Vmware中的桥接模式，与宿主机在同一个网络中，但<span style="color:red;">没有独立IP地址</span>。众所周知，Docker使用了Linux的Namespaces技术来进行资源隔离，如PID Namespace隔离进程，Mount Namespace隔离文件系统，Network Namespace隔离网络等。一个Network Namespace提供了一份独立的网络环境，包括网卡、路由、Iptable规则等都与其他的Network Namespace隔离。一个Docker容器一般会分配一个独立的Network Namespace。但如果启动容器的时候使用host模式，那么这个容器将不会获得一个独立的Network Namespace，而是和宿主机共用一个Network Namespace。<span style="color:red;">容器将不会虚拟出自己的网卡，配置自己的IP等，而是使用宿主机的IP和端口</span>。

  例如，我们在10.0.16.9的机器上用host模式启动一个含有nginx应用的Docker容器，监听tcp80端口

  当我们在容器中执行任何类似ifconfig命令查看网络环境时，看到的都是宿主机上的信息。而外界访问容器中的应用，则直接使用10.0.16.9:80即可，不用任何NAT转换，就如直接跑在宿主机中一样。但是，容器的其他方面，如文件系统、进程列表等还是和宿主机隔离的。

  ```shell
  $ docker run --name=nginx_host --net=host -p 80:80 -d nginx
  
  $ netstat -nplt | grep redis
  
  $ docker run -d -p 8081:8080 --network host --name tomcat83 billygoo/tomcat8-jdk8
  ```

  - 查看容器网络情况

    ```shell
    $ docker inspect tomcat83 | tail -n 20
    ```

    ![image-20220702195610512](https://cdn.jsdelivr.net/gh/elon-lo/PicGo-Image@master/img/image-20220702195610512.png)

- none模式：使用--network none指定

  该模式将容器放置在它自己的网络栈中，但是并不进行任何配置。实际上，该模式关闭了容器的网络功能，在以下两种情况下是有用的：容器并不需要网络（例如只需要写磁盘卷的批处理任务）。

  overlay

  在docker1.7代码进行了重构，单独把网络部分独立出来编写，所以在docker1.8新加入的一个overlay网络模式。Docker对于网络访问的控制也是在逐渐完善的。

  ```shell
  $ docker run -d -p 8081:8080 --network none --name tomcat82 billygoo/tomcat8-jdk8:latest
  ```

  - 查看容器网络情况

    ```shell
    $ docker inspect tomcat82 | tail -n 20
    ```

    ![image-20220702195739376](https://cdn.jsdelivr.net/gh/elon-lo/PicGo-Image@master/img/image-20220702195739376.png)

- container模式：使用--network container:NAME_or_ID指定

​		这个模式指定新创建的容器和已经存在的一个容器共享一个Network Namespace，而不是和宿主机共享。新创建的容器不会创建自己的网卡，配置自己的IP，而是和一个指定的容器共享IP、端口范围等。同样，两个容器除了网络方面，其他的如文件系统、进程列表等还是隔离的。两个容器的进程可以通过lo网卡设备通信。

```shell
$ docker run -d -p 8085:8080 --network container:tomcat83 --name tomcat84 billygoo/tomcat8-jdk8:latest
# docker: Error response from daemon: conflicting options: port publishing and the container type network mode.

#共用8080端口发生冲突,换一个镜像

# 启动容器alpine1
$ docker run -it --name alpine1 alpine /bin/sh

# 启动容器alpine2,与alpine1共用同一个网络
$ docker run -it --network container:alpine1 --name alpine2 alpine /bin/sh

# 查看alpine1网络
$ docker exec -it alpine1 /bin/sh
/ # ip addr
1: lo: <LOOPBACK,UP,LOWER_UP> mtu 65536 qdisc noqueue state UNKNOWN qlen 1000
    link/loopback 00:00:00:00:00:00 brd 00:00:00:00:00:00
    inet 127.0.0.1/8 scope host lo
       valid_lft forever preferred_lft forever
348: eth0@if349: <BROADCAST,MULTICAST,UP,LOWER_UP,M-DOWN> mtu 1500 qdisc noqueue state UP 
    link/ether 02:42:ac:11:00:07 brd ff:ff:ff:ff:ff:ff
    inet 172.17.0.7/16 brd 172.17.255.255 scope global eth0
       valid_lft forever preferred_lft forever

# 查看alpine2网络
$ docker exec -it alpine2 /bin/sh
/ # ip addr
1: lo: <LOOPBACK,UP,LOWER_UP> mtu 65536 qdisc noqueue state UNKNOWN qlen 1000
    link/loopback 00:00:00:00:00:00 brd 00:00:00:00:00:00
    inet 127.0.0.1/8 scope host lo
       valid_lft forever preferred_lft forever
348: eth0@if349: <BROADCAST,MULTICAST,UP,LOWER_UP,M-DOWN> mtu 1500 qdisc noqueue state UP 
    link/ether 02:42:ac:11:00:07 brd ff:ff:ff:ff:ff:ff
    inet 172.17.0.7/16 brd 172.17.255.255 scope global eth0
       valid_lft forever preferred_lft forever
       
# 此时停掉alpine1容器,再查看alpine2网络
$ docker exec -it alpine2 /bin/sh
/ # ip addr
1: lo: <LOOPBACK,UP,LOWER_UP> mtu 65536 qdisc noqueue state UNKNOWN qlen 1000
    link/loopback 00:00:00:00:00:00 brd 00:00:00:00:00:00
    inet 127.0.0.1/8 scope host lo
       valid_lft forever preferred_lft forever
```



#### 4、Bridge模式

##### 4.1、Bridge模式的拓扑

当Docker server启动时，会在主机上创建一个名为docker0的虚拟网桥，此主机上启动的Docker容器会连接到这个虚拟网桥上。虚拟网桥的工作方式和物理交换机类似，这样主机上的所有容器就通过交换机连在了一个二层网络中。接下来就要为容器分配IP了，Docker会从RFC1918所定义的私有IP网段中，选择一个和宿主机不同的IP地址和子网分配给docker0，连接到docker0的容器就从这个子网中选择一个未占用的IP使用。如一般Docker会使用172.17.0.0/16这个网段，并将172.17.0.1/16分配给docker0网桥（在主机上使用ifconfig命令是可以看到docker0的，可以认为它是网桥的管理接口，在宿主机上作为一块虚拟网卡使用）。单机环境下的网络拓扑如下，主机地址为10.10.0.186/24。

![](https://cdn.jsdelivr.net/gh/elon-lo/PicGo-Image@master/img/1259802-20180410165500455-232801094.jpg)

##### 4.2、网络模式详解

Docker完成以上网络配置的过程大致是这样的：

1. 在主机上创建一对虚拟网卡veth pair设备。veth设备总是成对出现的，它们组成了一个数据的通道，数据从一个设备进入，就会从另一个设备出来。因此，veth设备常用来连接两个网络设备。

2. Docker将veth pair设备的一端放在新创建的容器中，并命名为eth0。另一端放在主机中，以veth65f9这样类似的名字命名，并将这个网络设备加入到docker0网桥中，可以通过brctl show命令查看。

   ```shell
   $ brctl show
   ```

   ![image-20220702215620202](https://cdn.jsdelivr.net/gh/elon-lo/PicGo-Image@master/img/image-20220702215620202.png)

   注：如出现bash: brctl: command not found，请使用yum -y install bridge-utils.x86_64安装软件

3. 从docker0子网中分配一个IP给容器使用，并设置docker0的IP地址为容器的默认网关。

   1. 运行容器

      ```shell
      $ docker run --name=nginx_host --network=bridge -p 80:80 -d nginx:alpine
      ```

   2. 查看容器网络

      ```shell
      $ docker inspect 12a343552861 | tail -n 20
      ```

      ![image-20220702220045915](https://cdn.jsdelivr.net/gh/elon-lo/PicGo-Image@master/img/image-20220702220045915.png)

   

##### 4.3、bridge模式下容器的通信

1. 创建自定义网络yu_bridge

   ```shell
   $ docker network create --driver bridge yu_bridge
   ```

2. 使用自定义网络创建容器

   ```shell
   $ docker run -itd -p 8085:8080 --network yu_bridge --name tomcat85 billygoo/tomcat8-jdk8
   
   $ docker run -itd -p 8086:8080 --network yu_bridge --name tomcat86 billygoo/tomcat8-jdk8
   ```

3. 使用容器名查看容器之间是否处于同一网段

   ```shell
   $ docker exec -it tomcat85 /bin/bash
   ```

   ![image-20220704220806984](https://cdn.jsdelivr.net/gh/elon-lo/PicGo-Image@master/img/image-20220704220806984.png)

   ```shell
   $ docker exec -it tomcat86 /bin/bash
   ```

   ![image-20220704221102913](https://cdn.jsdelivr.net/gh/elon-lo/PicGo-Image@master/img/image-20220704221102913.png)

4. 自定义网络的优点

​	容器的ip可能会变化，但是容器名不会，<span style="color:red;">自定义网络能够解决因容器ip变化导致容器间无法互联的问题</span>

## 十三、Docker Compose容器编排

#### 1、Compose简介

Compose是Dcoker官方的开源项目，负责实现对Docker容器集群的快速编排。Compose 是<span style="color:red">用于定义和运行多容器 Docker 应用程序的工具</span>。通过 Compose，您可以使用 YML 文件来配置应用程序需要的所有容器服务。然后，使用一个命令，就可以从 YML 文件配置中创建并启动所有容器服务。

Compose 使用的三个步骤:

- 使用 Dockerfile 定义应用程序的环境。
- 使用 docker-compose.yml 定义构成应用程序的服务，这样它们可以在隔离环境中一起运行。
- 最后，执行 docker-compose up 命令来启动并运行整个应用程序。

#### 2、Compose安装与卸载

1. 官方文档

   <https://docs.docker.com/compose/compose-file/compose-file-v3/>

2. Compose下载

   <https://docs.docker.com/compose/install/compose-plugin/#install-using-the-repository/>

3. Compose安装

   ```shell
   # 下载compose到指定目录下
   $ curl -SL https://github.com/docker/compose/releases/download/v2.6.0/docker-compose-linux-x86_64 -o /usr/local/bin/docker-compose
   
   # 也可以使用以下命令替换
   $ curl -SL https://get.daocloud.io/docker/compose/releases/download/v2.6.0/docker-compose-`uname -s`-`uname -m` > /usr/local/bin/docker-compose
   
   # 添加可执行权限
   $ chmod +x /usr/local/bin/docker-compose
   
   # 创建软链接
   $ ln -s /usr/local/bin/docker-compose /usr/bin/docker-compose
   
   # 查看是否安装成功
   $ docker-compose --version
   Docker Compose version v2.6.0
   
   # 使用curl下载,卸载方法如下
   $ rm -rf /usr/local/b
   ```
   

#### 3、Compose使用步骤

1. 编写**Dockerfile**定义各个微服务应用并构建出对应的镜像文件
2. 使用**docker-compose.yml**文件定义一个业务完整单元，安排好整体应用中的各个容器服务
3. 执行**docker-compose up**来启动并运行整个应用程序，完成一键部署上线

#### 4、Compose常用命令

```shell
$ docker-compose -h						#查看帮助
$ docker-compose up						#启动所有docker-compose服务
$ docker-compose up -d					#启动所有docker-compose服务并后台运行
$ docker-compose down					#停止并删除容器、网络、卷、镜像

#进入容器实例内部 docker-compose exec docker-compose.yml文件中写的服务id /bin/bash
$ docker-compose exec yml里面的服务id	 

$ docker-compose ps						#展示当前docker-compose编排过运行的所有容器
$ docker-compose top					#展示当前docker-compose编排过的容器进程
$ docker-compose logs yml里面的服务id	 #查看容器输出日志
$ docker-compose config					#检查配置
$ docker-compose config -q				#检查配置,有问题才输出
$ docker-compose start					#启动服务
$ docker-compose stop					#停止服务
$ docker-compose restart				#重启服务
```

#### 5、Compose容器编排微服务

- 安装mysql

  ```shell
  $ docker run -d -p 13306:3306 --privileged=true \
  -v /root/mydocker/mysql/log:/var/log/mysql \
  -v /root/mydocker/mysql/data:/var/lib/mysql \
  -v /root/mydocker/mysql/conf:/etc/mysql/conf.d \
  -e MYSQL_ROOT_PASSWORD=yu518720 \
  --name docker_boot_mysql \
  mysql:8.0.21
  ```

- 安装redis

  ```shell
  $ docker run -p 16379:6379 --name docker_boot_redis --privileged=true \
  -v /root/mydocker/redis/redis.conf:/etc/redis/redis.conf \
  -v /root/mydocker/redis/data:/data \
  -d redis:6.0.16 redis-server /etc/redis/redis.conf
  ```

- 构建镜像，启动容器实例

  ```shell
  # 编写Dockerfile文件
  
  #基础镜像使用java
  FROM java:8-jre
  #作者
  MAINTAINER elonlo
  #VOLUME指定临时文件目录为/tmp,在主机/var/lib/docker目录下创建了一个临时文件并链接到容器的/tmp
  VOLUME /tmp
  #将jar包添加到容器中并更名为app.jar
  ADD docker_boot.jar /app.jar
  #运行jar包
  RUN bash -c 'touch /app.jar'
  ENTRYPOINT ["java","-jar","/app.jar"]
  #暴露端口
  EXPOSE 6201
  
  # 构建镜像
  $ docker build -t docker-boot:3.0 .
  
  # 启动容器
  docker run -p 6201:6201 --name docker_boot --privileged=true -v /root/mydocker:/home -d docker-boot:4.0
  ```

- 使用docker-compose.yml文件编排容器服务

  - 创建docker-compose.yml文件

    ```yaml
    version: "3"
    
    services:
      microService:
        image: docker-boot:4.0
        container_name: docker_boot
        environment:
          TZ: Asia/Shanghai
        ports:
          - 6201:6201
        volumes:
          - /app/microService:/data
        networks:
          - docker_boot_net
        depends_on:
          - redis
          - mysql
    
      redis:
        image: redis:6.0.16
        container_name: redis
        environment:
          TZ: Asia/Shanghai
        ports:
          - 16379:6379
        volumes:
          - /app/redis/conf:/etc/redis/redis.conf
          - /app/redis/data:/data
        networks:
          - docker_boot_net
        command: redis-server /etc/redis/redis.conf
    
      mysql:
        image: mysql:8.0.21
        container_name: mysql
        environment:
          TZ: Asia/Shanghai
          MYSQL_ROOT_PASSWORD: yu518720	     #root用户密码
          MYSQL_ALL_EMPTY_PASSWORD: no
          MYSQL_DATABASE: docker_boot
          MYSQL_USER: user			        #创建新用户					
          MYSQL_PASSWORD: 123456			#新用户的密码
        ports:
          - 13306:3306
        volumes:
          - /app/mysql/db:/var/lib/mysql
          - /app/mysql/conf:/etc/my.cnf
          - /app/mysql/init:/docker-entrypoint-initdb.d
        networks:
          - docker_boot_net
        command: 
          --default-authentication-plugin=mysql_native_password 
          --character-set-server=utf8mb4
          --collation-server=utf8mb4_general_ci
    
    networks:
      docker_boot_net:
    ```

  - 微服务中mysql、redis远程连接ip改为服务名

    ```yaml
    server:
      port: 6201
    
    spring:
      jackson:
        time-zone: GMT+8
        date-format: yyyy-MM-dd HH:mm:ss
    
      redis:
        database: 1
        # Redis服务器地址
        host: redis
        # Redis服务器连接端口
        port: 16379
        # Redis服务器连接密码（默认为空）
        password: yu518720
        # 连接池最大连接数（使用负值表示没有限制）
        jedis:
          pool:
            max-active: 8
            # 连接池最大阻塞等待时间（使用负值表示没有限制）
            max-wait: -1ms
            # 连接池中的最大空闲连接
            max-idle: 8
            # 连接池中的最小空闲连接
            min-idle: 0
        # 连接超时时间（毫秒）
        timeout: 5000
        lettuce:
          shutdown-timeout: 0
    
      #数据库配置
      datasource:
        type: com.zaxxer.hikari.HikariDataSource
        driver-class-name: com.mysql.cj.jdbc.Driver
        url: jdbc:mysql://mysql:13306/docker_boot?useUnicode=true&characterEncoding=UTF-8&useSSL=false&serverTimezone=Asia/Shanghai&rewriteBatchedStatements=true
        username: root
        password: yu518720
        hikari:
          connection-test-query: SELECT 1
          idle-timeout: 180000
          max-lifetime: 300000
          maximum-pool-size: 20
          minimum-idle: 5
          connection-timeout: 300000
      servlet:
        multipart:
          max-file-size: -1
          max-request-size: -1
    ```

  - 删除原来的容器和镜像，构建新的镜像

    ```shell
    $ docker rm -f docker_boot_mysql docker_boot_redis docker-boot
    
    $ docker rmi -f docker-boot:3.0
    ```

  - 查看docker-compose.yml文件是否有语法错误

    ```shell
    $ docker-compose config -q
    ```

  - 使用docker-compose up -d后台方式启动

    ```shell
    $ docker-compose up -d
    ```
    
    ![image-20220705174022032](https://cdn.jsdelivr.net/gh/elon-lo/PicGo-Image@master/img/image-20220705174022032.png)
    
    
    
  - 查看容器和网络是否已创建
  
    ```shell
    # 查看网络
    $ docker network ls
    ```
    
    ![image-20220705174138283](https://cdn.jsdelivr.net/gh/elon-lo/PicGo-Image@master/img/image-20220705174138283.png)
    
    ![image-20220705174221549](https://cdn.jsdelivr.net/gh/elon-lo/PicGo-Image@master/img/image-20220705174221549.png)

## 十四、Dcoker轻量级可视化工具Portainer

#### 1、简介

Portainer是一款轻量级的应用，它提供了图形化的界面，用于方便地管理Docker环境，包括单机和集群环境。

#### 2、安装

- 官网地址

  <https://www.portainer.io/>

- 官方文档地址

  <https://docs.portainer.io/v/ce-2.11/start/install/server/docker/linux>

- 步骤

  1. docker安装命令

     ```shell
     $ docker run -d -p 8000:8000 -p 9000:9000 --name portainer --restart=always \
     -v /var/run/docker.sock:/var/run/docker.sock -v portainer_data:/data \
     portainer/portainer-ce:2.11.1
     ```

  2. 登录并创建portainer用户

     第一次登录需创建admin账户，访问地址为ip:9000

  3. 选择home→local选项卡后展示本地docker详细信息

## 十五、Docker容器监控之CAdvisor+InfluxDB+Grafana

#### 1、CAdvisor(监控收集)

CAdvisor是一个容器资源监控工具，包括容器的内存、CPU、网络IO、磁盘IO等监控，同时提供了一个WEB页面用于查看容器的实时运行状态。CAdvisor默认存储2分钟的数据，而且只是针对单物理机。不过，CAdvisor提供了很多数据集成接口，支持InfluxDB、Redis、Kafka、Elasticsearch等集成，可以加上对应配置将监控数据发往这些数据库存储起来。

CAdvisor的功能主要有两点：

- 展示Hosts和容器两个层次的监控数据。
- 展示历史变化数据。

#### 2、InfluxDB(存储数据)

InfluxDB是用Go语言编写的一个开源分布式时序、事件和指标数据库，无需外部依赖。

CAdvisor默认只在本机保存最近2分钟的数据，为了持久化存储数据和统一收集展示监控数据，需要将数据存储到InfluxDB中。InfluxDB是一个时序数据库，专门用于存储时序相关数据，很适合存储CAdvisor的数据。而且，CAdvisor本身已经提供了InfluxDB的集成方法，启动容器时指定配置即可。

InfluxDB主要功能如下：

- 基于时间序列，支持与时间有关的相关函数(如最大、最小、求和等)。
- 可度量性，可以实时对大量数据进行计算。
- 基于事件，支持任意的事件数据。

#### 3、Grafana(展示图表)

Grafana是一个开源的数据监控分析可视化平台，支持多种数据源配置(如InfluxDB、MySQL、Elasticsearch、OpenTSDB、Graphite等)和丰富的插件及模板功能，支持图标权限控制和报警。

Grafana主要功能如下：

- 灵活丰富的图形化选项。
- 可以混合多种风格。
- 支持白天和夜间模式。
- 支持多数据源。

#### 4、使用docker-compose构建环境

1. 新建目录

   ```shell
   $ mkdir -p /root/mydocker/cig
   ```

2. 编写docker-compose.yml文件

   ```yaml
   version: "3.1"
   
   volumes:
     grafana_data: {}
   services:
     influxdb:
       image: tutum/influxdb:0.9
       restart: always
       environment:
         - PRE_CREATE_DB=cadvisor
       ports:
         - "8083:8083"
         - "8086:8086"
       volumes:
         - ./data/influxdb:/data
   
     cadvisor:
       image: google/cadvisor
       links:
         - influxdb:influxsrv
       command: -storage_driver=influxdb -storage_driver_db=cadvisor -storage_driver_host=influxsrv:5000
       restart: always
       ports:
         - "8080:8080"
       volumes:
         - /:/rootfs:ro
         - /var/run:/var/run:rw
         - /sys:/sys:ro
         - /var/lib/docker:/var/lib/docker:ro
         
     grafana:
       user: "104"
       image: grafana/grafana
       user: "104"
       restart: always
       links:
         - influxdb:influxsrv
       ports:
         - "3000:3000"
       volumes:
         - grafana_data:/var/lib/grafana
       environment:
         - HTTP_USER=admin
         - HTTP_PASS=admin
         - INFLUXDB_HOST=influxsrv
         - INFLUXDB_PORT=5000
   ```

   

3. 启动docker-compose创建容器

4. 登录

   CAdvisor：ip:8080

   InfluxDB：ip:8083

   Grafana：ip:3000，默认账号和密码都是admin

5. CGI配置

   - 配置数据源

     http://ip:3000/datasources

   - 配置服务名、用户名、密码、数据库、URL等

     Name(服务名)：InfluxDB

     URL：http://InfluxDB:8086

     Database：cadvisor

     User：root

     Password：root

     



