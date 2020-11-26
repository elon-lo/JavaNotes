## 1、Nginx基本概念

- 什么是Nginx?

  - Nginx是一个高性能的HTTP和反向代理服务器，特点是占用内存少，并发能力强，

    事实上nginx的并发能力确实在同类型的网页服务器中表现较好

  - Nginx专为性能优化而开发，性能是其最重要的考量，实现上也非常注重效率，能

    经受高负载的考验，有报告表明能支持高达50000的并发连接数

- 反向代理

  - 正向代理：在客户端(浏览器)配置代理服务器，通过代理服务器进行互联网访问

    ![](https://pic4.zhimg.com/80/v2-c8ac111c267ae0745f984e326ef0c47f_720w.jpg)

  - 反向代理：我们只需要将请求发送到反向代理服务器，由反向代理服务器去选择

    目标服务器获取数据后，在返回给客户端，此时反向代理服务器和目标服务器对

    外就是一个服务器，暴露的是代理服务器地址，隐藏了真实服务器IP地址

  ![](https://pic2.zhimg.com/80/v2-4787a512240b238ebf928cd0651e1d99_720w.jpg)

- 负载均衡

  - 单个服务器解决不了，我们增加服务器的数量，然后将请求分发到各个服务器上，

    将原先请求集中到单个服务器上的情况改为将请求分发到多个服务器上，将负载

    分发到不同的服务器上，也就是我们所说的负载均衡

- 动静分离

  - 为了加快网站的解析速度，可以把动态页面和静态页面由不同的服务器来解析，

    加快解析速度，降低原来单个服务器的压力

  

## 2、Nginx安装

- Nginx下载

  - nginx：https://nginx.org/en/download.html

  - zlib：http://zlib.net/fossils/
  - openSSL：https://www.openssl.org/source/old/
  - pcre：https://ftp.pcre.org/pub/pcre/

- 安装pcre

  - 安装gcc，yum install gcc-c++
  - 把安装压缩文件放入linux系统中
  - 解压压缩文件
  - 进入解压之后的目录，执行./configure
  - 在当前目录使用make && make install完成编译和安装
  - 安装之后，使用pcre-config --version查看版本号

- 安装zlib

  - yum -y install make zlib zlib-devel gcc-c++ libtool openssl openssl-devel

- 安装nginx

  - 同pcre安装

  - 安装成功之后，在usr会多出来一个local/nginx文件夹，在nginx的sbin里

    有启动脚本，执行./nginx

  - 查看防火墙所有开放的端口 firewall-cmd --list-port或firewall-cmd --list-all

  - 添加端口号  firewall-cmd --zone=public --add-port=80/tcp --permanent 

  - 重启防火墙  firewall-cmd --reload

  - 在浏览器输入虚拟机ip(如在windows安装则输入127.0.0.1)

## 3、Nginx操作的常用命令

- 使用nginx操作命令前提条件：必须进入nginx的目录 /usr/src/local/sbin
- 查看nginx的版本                ./nginx -v
- 启动nginx                           ./nginx
- 关闭nginx                          ./nginx -s stop
- 重新加载nginx                 ./nginx -s reload

## 4、Nginx配置文件

- nginx配置文件位置	/usr/local/nginx/conf/nginx.conf

- nginx配置文件由三部分组成

  - 全局块：

    ​	从配置文件开始到events块之间的内容，主要设置一些影响nginx服务器整体

    ​	运行的配置指令，比如：worker_process	1；worker_process值越大，可

    ​	以支持的并发处理量也越多

  - events块：

    ​	events块涉及的指令主要影响Nginx服务器与用户的网络连接，比如:

    ​	worker_connections	1024；支持的最大连接数

  - http块：

    ​	Nginx服务器配置中最频繁的部分，http块也可以包括http全局块、server块



## 5、Nginx配置实例-反向代理

- 实现效果

  - 打开浏览器，在浏览器地址栏输入www.123.com，跳转到linux系统中tomcat主页

- 准备工作

  - 在linux系统中安装tomcat，使用默认端口8080
  - tomcat文件放到linux中，解压
  - 进入tomcat的bin目录，./startup.sh启动
  - 防火墙开放8080端口    firewall-cmd --zone=public --add-port=8080/tcp --permanent

- 访问过程

  - www.123.com(windows浏览器)→192.168.252.130:80(nginx)→127.0.0.1:8080(tomcat)
  - 在windows的host文件配置域名映射的ip地址

- 具体配置

  - 在windows的host文件中加入以下配置

    ```properties
    192.168.252.130	www.123.com
    ```

  - 在nginx.conf中加入 以下配置

    ```properties
    #gzip  on;
    
        server {
            listen       80;
            server_name  192.168.252.130;
    
            #charset koi8-r;
    
            #access_log  logs/host.access.log  main;
    
            location / {
                root   html;
                proxy_pass  http://127.0.0.1:8080;
                index  index.html index.htm;
            }
    
    ```

    

## 6、Nginx配置实例-反向代理

- 实现效果

  - 使用nginx反向代理，根据访问的路径跳转到不同端口的服务中，nginx监听端口为9001

  - 访问http://192.168.252.130:9001/edu/直接跳转到127.0.0.1:8080

    访问http://192.168.252.130:9001/vod/直接跳转到127.0.0.1:8081

- 准备工作

  - 准备两个tomcat服务器，一个8080端口，一个8081端口
  - 创建文件夹和测试页面

- 3、具体配置

  - 找到nginx配置文件，进行反向代理配置

    ```properties
    # another virtual host using mix of IP-, name-, and port-based configuration
        #
        server {
            listen       9001;
        #    listen       somename:8080;
            server_name  192.168.252.130;
    
            location ~ /edu/ {
                #root   html;
                #index  index.html index.htm;
                proxy_pass  http://127.0.0.1:8080;
            }
        
            location ~ /vod/ {
                proxy_pass  http://127.0.0.1:8081;
            }
        }
    
    ```

- location指令说明

  - =：用于不含正则表达式的uri前，要求请求字符串与uri严格匹配，如果匹配成功，

    就停止继续向下搜索并立即处理该请求

  - ~：用于表示uri包含正则表达式，并且区分大小写

  - ~*：用于表示uri包含正则表达式，并且不区分大小写

  - ^~：用于不含正则表达式的uri前，要求Nginx服务器找到标识uri和请求字符串匹配

    度最高的location后，立即使用此location处理请求，而不再使用location块中的正

    则uri和请求字符串做匹配

    <span style='color:red'>注意：如果uri包含正则表达式，则必须要有~或~*标识</span>



## 7、负载均衡

- 实现效果

  - 浏览器地址栏输入地址http:192.168.252.130/edu/a.html，负载均衡将请求平均分配

    到8080和8081端口中

- 准备工作

  - 准备两台tomcat服务器，一台8080，一台8081

  - 在两台tomcat里面的webapps目录中，创建名称是edu的文件夹，在edu文件夹中创建

    页面a.html，用于测试

  - 在nginx的配置文件中进行负载均衡的配置

- 具体配置如下

  ```properties
  upstream myserver{
                  server  192.168.252.130:8080 weight=5;
                  server  192.168.252.130:8081 weight=10;
          }
  
  ```

  ```properties
  location / {
              root   html;
              proxy_pass  http://myserver;
              index  index.html index.htm;
          }
  
  ```

  



## 8、Nginx分配服务器策略

- 轮询(默认)：每个请求按时间顺序逐一分配到不同的后端服务器，如果后端服务器宕(down)

  掉，能自动剔除该服务器

```properties
upstream myserver{
    server  192.168.252.130:8080;
    server  192.168.252.130:8081;
}
```



- weight(权重)：weight代表权重默认为1，权重越高被分配的客户端越多

```properties
upstream myserver{
    server  192.168.252.130:8080 weight=5;
    server  192.168.252.130:8081 weight=10;
}
```



- ip_hash：每个请求按访问ip的hash结果分配，这样每个访客固定访问一个后端服务器

```properties
upstream myserver{
	ip_hash
    server  192.168.252.130:8080;
    server  192.168.252.130:8081;
}
```



- fair(第三方)：按后端服务器的响应时间来分配请求，响应时间短的优先分配

```properties
upstream myserver{
    server  192.168.252.130:8080;
    server  192.168.252.130:8081;
    fair
}
```

## 9、动静分离

- 通过location制定不同的后缀名实现不同的请求转发。通过expires参数设置，可以使

  浏览器缓存过期时间，减少与服务器之间的请求和流量。具体Expires定义：是给一个

  资源设定一个过期时间，也就是说无需去服务端验证，直接通过浏览器自身确实是否

  过期即可，所以不会产生额外的流量。此种方法非常不适合经常变动的资源。(如果经

  常更新的文件，不建议使用Expires来缓存)，设置3d，表示在这3天之内访问这个URL，

  发送一个请求，比如服务器该文件最后更新时间没有变化，则不会从服务器抓取，返回

  状态码304，如果有修改，则直接从服务器重新下载，返回状态码200

- 准备工作

  - 在root目录下新建一个data目录，然后在data目录下新建www和image目录，一个

    目录放html文件，一个放图片

  ```properties
  location /www/ {
              root    /data/;
              index    index.html;
          }
  
          location /image/ {
              root    /data/;
              autoindex    on;
          }
  
  ```

  - 在浏览器分别输入http://192.168.252.130/image/和http://192.168.252.130/www/a.html测试



