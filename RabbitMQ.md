## 1.消息队列

### 1.1.MQ 的相关概念

#### 1.1.1.什么是 MQ

MQ（message queue），从字面意思上看，本质是个队列，FIFO 先进先出，只不过队列里面存放的内容是`message`而已，消息队列还是一种跨进程的通信机制，用于上下游传递消息。在互联网架构中，MQ 是一种非常常见的上下游**逻辑解耦+物理解耦**的消息通信服务。使用了 MQ 之后，消息发送上游只需要依赖 MQ，不用依赖其他服务。

#### 1.1.2.为什么要用 MQ

1. **流量削峰**

   比如订单系统最多能处理一万次订单，这个处理能力对付正常时段的下单绰绰有余，正常时段我们下单一秒后就能返回结果。但是如果在高峰期，订单次数可能会达到两万次甚至更多，这个时候系统是处理不了的，只能限制订单超过一万次之后不允许用户下单。我们可以使用消息队列作为缓冲，取消这个限制，把一秒内的订单分散成一段时间来处理，这时有些用户可能在下单十几秒之后才能收到下单成功的操作，但是比不能下单的体验要好。

2. **应用解耦**

   以电商应用为例，应用中有订单、库存、物流、支付系统。用户创建订单后，如果耦合调用库存、物流、支付系统，任何一个子系统出现了故障，都会造成下单操作异常。当转变成基于消息队列的方式之后，系统调用的问题会减少很多，比如物流系统因为发生故障，需要几分钟来修复。在这几分钟的时间里，物流系统要处理的内存被缓存在消息队列中，用户下单的操作可以正常完成。当物流系统恢复后，继续处理订单信息即可，中间用户感受不到物流系统的故障，提升系统的可用性。

   ![application-decoupling](https://image.elonlo.top/img/2023/11/18/65589a1f6e087.jpg)

3. **异步处理**

   有些服务间是异步调用的，比如 A 调用 B，B 需要花费很长时间执行，但是 A 需要知道 B 什么时候可以执行完成，以前一般有两种方式，A 过一段时间去调用 B 的查询 api 查询。或者 A 提供一个 callback api，B 执行完之后调用 api 通知 A 服务。这两种方式都不是很优雅，使用消息总线，可以很方便的解决这个问题，A 调用 B 服务之后，只需要监听 B 处理完成的消息，当 B 处理完成之后，会发送一条消息给 MQ，MQ 会将此消息转发给 A 服务，这样 A 服务既不用循环调用 B 的查询 api，也不用提供 callback api。同样 B 服务也不用做这些操作。A 服务还能及时收到异步处理成功的消息。

   ![asynchronous-processing](https://image.elonlo.top/img/2023/11/18/6558af454b4f7.jpg)

#### 1.1.3.MQ 的分类

| 队列       | ActiveMQ                                                     | RabbitMQ                                                     | RocketMQ                 | kafka                                                        |
| ---------- | ------------------------------------------------------------ | ------------------------------------------------------------ | ------------------------ | ------------------------------------------------------------ |
| 开发语言   | Java                                                         | erlang                                                       | Java                     | scale                                                        |
| 单机吞吐量 | 万级                                                         | 万级                                                         | 十万级                   | 十万级                                                       |
| 时效性     | ms级                                                         | μs级                                                         | ms级                     | ms级以内                                                     |
| 可用性     | 高（主从架构）                                               | 高（主从架构），支持分布式架构                               | 非常高（分布式架构）     | 非常高（分布式架构）                                         |
| 功能特性   | 成熟的产品，在很多公司得到应用；<br/>有较多的文档，各种协议支持较好 | 基于erlang开发，并发能力很强，<br/>性能极好，延时很低，管理界面较丰富 | MQ功能比较完备，扩展性好 | 只支持主要的MQ功能，像一些消息查询，<br/>消息回溯等功能没有提供，在大数据领域应用较广 |
| 消息丢失   | 可能性非常低                                                 | 可能性非常低                                                 | 理论可以做到0丢失        | 理论可以做到0丢失                                            |

#### 1.14.MQ 的选择

1. **Kafka**

   Kafka 的主要特点是基于 Pull 的模式来处理消息消费，追求高吞吐量，一开始的目的就是用于日志收集和传输，适合产生**大量数据**的互联网服务的数据收集业务。**大型公司**建议可以选用，如果有**日志采集**功能，首选 Kafka。

2. **RocketMQ**

   天生为**金融互联网**领域而生，对于可靠性要求很高的场景，尤其是电商里面的订单扣款，以及业务削峰，在大量交易涌入时，后端可能无法及时处理的情况。RocketMQ 在稳定性上可能更值得信赖，这些业务场景在 阿里双11 已经经历了多次考验，如果业务有以上并发场景，可以选择 RocketMQ。

3. **RabbitMQ**

   结合 erlang 语言本身的并发优势，性能好的同时时效性**微秒级**，社区活**跃度比较高**，管理界面用起来十分方便，如果**数据量没有那么大**，中小型公司可以优先选择功能比较完备的 RabbitMQ。

### 1.2.RabbitMQ

#### 1.2.1.RabbitMQ 的概念

RabbitMQ 是一个消息中间件。它接受并转发消息。可以将它当作一个快递站点，当你要发送一个包裹时，你将你的包裹放在快递站，快递员最终会把你的快递送到收件人那里去，按照这种逻辑 RabbitMQ 是一个快递站，一个快递员帮你传递快件。RabbitMQ 与快递站的主要区别在于，它不处理快件，而是接收，存储和转发消息数据。

#### 1.2.2.四大核心概念

1. **生产者**

   产生数据发送消息的程序是生产者。

2. **交换机**

   交换机是 RabbitMQ 非常重要的一个部件，一方面它接收来自生产者的消息，另一方面它将消息推送到队列中。交换机必须确切知道如何处理它接收到的消息，是将这些消息推送到特定队列还是推送到多个队列，亦或者是把消息丢弃，这个由交换机类型来确定。

3. **队列**

   队列是 RabbitMQ 内部使用的一种数据结构，尽管消息流经 RabbitMQ 和应用程序，但它们只能存储在队列中。队列仅受主机的内存和磁盘限制的约束，本质上是一个大的消息缓冲区。许多生产者可以将消息发送到一个队列，许多消费者可以尝试从一个队列接收数据。

4. **消费者**

   消费和接收具有相似的含义。消费大多时候是一个等待接收消息的程序。注意生产者和消费者和消息中间件很多时候并不在同一台机器上。同一个应用程序既可以是生产者又可以是消费者。

#### 1.2.3.RabbitMQ 消息模式

1. 简单模式（Hello World!）
2. 工作模式（Work Queues）
3. 发布/订阅模式（Publish/Subscribe）
4. 路由模式（Routing）
5. 主题模式（Topics）
6. 拉取机制（RPC）

#### 1.2.3.RabbitMQ 核心组成部分

1. **Server**

   又称 Broker，接受客户端的连接，实现 AMQP 实体服务。 安装 rabbitmq-server

2. **Connection**

   连接，应用程序与 Broker 的网络连接 TCP/IP 三次握手和四次挥手

3. **Channel**

   网络信道，几乎所有的操作都在 Channel 中进行，Channel 是进行消息读写的通道，客户端可以建立对各 Channel，每个 Channel 代表一个会话任务。

4. **Message**

   消息，服务与应用程序之间传送的数据，由 Properties 和 body 组成，Properties 可以对消息进行修饰，比如消息的优先级，延迟等高级特性，Body 则就是消息体的内容。

5. **Virtual Host**

   虚拟地址，用于进行逻辑隔离，最上层的消息路由，一个虚拟主机理由可以有若干个 Exhange 和 Queueu，同一个虚拟主机里面不能有相同名字的 Exchange

6. **Exchange**

   交换机，接受消息，根据路由键发送消息到绑定的队列。(不具备消息存储的能力)

7. **Bindings**

   Exchange 和 Queue 之间的虚拟连接，binding 中可以保护多个 routing key

8. **Routing key**

   是一个路由规则，虚拟机可以用它来确定如何路由一个特定消息。

9. **Queue**

   队列，也称为 Message Queue，消息队列，保存消息并将它们转发给消费者。

#### 1.2.4.安装

1. 官网地址

   https://www.rabbitmq.com/download.html

2. 创建`docker-compose.yml`文件

   ```yaml
   version: '3'
   services:
     rabbitmq:
       image: rabbitmq:3.9-management
       container_name: yu_rabbitmq
       restart: unless-stopped
       environment:
         - RABBITMQ_DEFAULT_USER=admin
         - RABBITMQ_DEFAULT_PASS=admin
       volumes:
         - $PWD/data:/var/lib/rabbitmq #数据文件挂载
         - $PWD/logs:/var/log/rabbitmq #日志文件挂载
       ports:
         - 25673:5672
         - 35673:15672
       networks:
         - top
   
   networks:
          top: {}
   ```

3. 启动容器

   ```sh
   docker-compose up -d
   ```

## 2.简单模式（Hello World）

即发送单个消息的生产者（发送者）和接收消息并将其打印出来的消费者（接收者）。

在下图中，“P”是我们的生产者，“C”是我们的消费者。 中间的框是一个队列 - RabbitMQ 代表消费者保留的消息缓冲区。

![image-20231120220819563](https://image.elonlo.top/img/2023/11/20/655b685b39ca2.png)

### 2.1.创建项目结构

1. 创建一个空工程
2. 在空工程中创建一个maven子模块（hello）

### 2.2.添加依赖

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>com.yu.rabbit</groupId>
    <artifactId>rabbitmq-hello</artifactId>
    <version>1.0-SNAPSHOT</version>

    <properties>
        <maven.compiler.source>8</maven.compiler.source>
        <maven.compiler.target>8</maven.compiler.target>
        <rabbitmq.version>5.8.0</rabbitmq.version>
        <commons-io.version>2.6</commons-io.version>
    </properties>

    <dependencies>
        <!-- rabbitmq依赖客户端 -->
        <dependency>
            <groupId>com.rabbitmq</groupId>
            <artifactId>amqp-client</artifactId>
            <version>${rabbitmq.version}</version>
        </dependency>

        <!-- 文件流操作依赖 -->
        <dependency>
            <groupId>commons-io</groupId>
            <artifactId>commons-io</artifactId>
            <version>${commons-io.version}</version>
        </dependency>
    </dependencies>

    <!-- 指定JDK编译版本 -->
    <build>
        <plugins>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-compiler-plugin</artifactId>
                <configuration>
                    <source>8</source>
                    <target>8</target>
                </configuration>
            </plugin>
        </plugins>
    </build>

</project>
```

### 2.3.消息生产者

```java
/**
 * 生产者
 *
 * @author elonlo
 * @date 2023/11/20 23:22
 */
public class Producer {

    /**
     * 队列名称
     */
    public static final String QUEUE_NAME = "hello";

    /**
     * 发送消息
     *
     * @param content 消息内容
     */
    public static void sendMessage(String content) {
        // 1.创建连接工厂
        ConnectionFactory factory = new ConnectionFactory();

        // 2.设置IP
        factory.setHost("ip");

        // 3.设置端口,注意端口不是web界面访问的端口
        factory.setPort(port);

        // 4.设置用户名和密码
        factory.setUsername("admin");
        factory.setPassword("admin");

        Connection connection = null;
        Channel channel = null;

        try {
            // 5.创建连接
            connection = factory.newConnection();

            // 6.创建信道
            channel = connection.createChannel();

            // 7.声明队列
            /**
             * @param queue         队列名称
             * @param durable       是否持久化durable=false(默认) 所谓持久化消息是否存储到磁盘,如果false非持久化,true是持久化
             * @param exclusive     队列是否只提供一个消费者消费,是否进行消息共享,true-允许多个消费者消费 false-不允许
             * @param autoDelete    是否自动删除 最后一个消费者断开连接之后，该队列是否自动删除 true-自动删除 false-不自动删除
             * @param arguments     其他参数
             */
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);

            // 8.发送消息
            /**
             * @param exchange 交换机名称
             * @param routingKey 路由key,本次是队列名称
             * @param props 其他属性
             * @param body 消息体
             */
            channel.basicPublish("", QUEUE_NAME, null, content.getBytes(StandardCharsets.UTF_8));
            System.out.println("消息发送成功!");
        } catch (IOException | TimeoutException e) {
            e.printStackTrace();
        } finally {
            // 9.关闭连接
            if (connection != null && connection.isOpen()) {
                try {
                    connection.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            // 10.关闭信道
            if (channel != null && channel.isOpen()) {
                try {
                    channel.close();
                } catch (IOException | TimeoutException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        String content = "hello world";
        sendMessage(content);
    }
}
```

### 2.4.消息消费者

```java
/**
 * 消费者
 *
 * @author elonlo
 * @date 2023/11/21 10:21
 */
public class Consumer {

    /**
     * 队列名称
     */
    public static final String QUEUE_NAME = "hello";

    /**
     * 消费消息
     */
    public static void receiveMessages() {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("ip");
        factory.setPort(port);
        factory.setUsername("admin");
        factory.setPassword("admin");

        Connection connection = null;
        Channel channel = null;

        try {
            connection = factory.newConnection();
            channel = connection.createChannel();

            /**
             * 接收消息
             * @param queue             队列名称
             * @param autoAck           消费成功后是否自动应答 true-是 false-否
             * @param deliverCallback   接收消息后的回调
             * @param cancelCallback    取消消息后的回调
             */
            channel.basicConsume(QUEUE_NAME, true, new DeliverCallback() {
                @Override
                public void handle(String consumerTag, Delivery message) throws IOException {
                    System.out.println("接收到消息: " + new String(message.getBody(), StandardCharsets.UTF_8));
                    System.exit(0);
                }
            }, new CancelCallback() {
                @Override
                public void handle(String consumerTag) throws IOException {
                    System.out.println("消息消费被中断,取消消费消息");
                }
            });
            // 对消息进行阻断不在往下运行
            System.in.read();
        } catch (IOException | TimeoutException e) {
            e.printStackTrace();
        } finally {
            if (connection != null && connection.isOpen()) {
                try {
                    connection.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (channel != null && channel.isOpen()) {
                try {
                    channel.close();
                } catch (IOException | TimeoutException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        receiveMessages();
    }
}
```

<span style="color:red">注意：第一次运行时需先启动生产者代码，然后执行消费者代码，并且需要执行`System.in.read()`阻断程序，否则无法成功接收到生产者发送的消息。</span>

## 3.工作队列模式（Work Queues）

工作队列（又称任务队列）的主要思想是**避免立即执行资源密集型任务**，而必须等待完成。相反，我们安排了以后要完成的任务。我们将任务封装为消息，然后将其发送到队列。在后台运行的工作过程将弹出任务并最终执行作业。当有多个工作线程时，这些工作线程将一起处理这些任务。

这个概念在短时间 HTTP 请求窗口中无法处理复杂任务的 Web 应用程序中特别有用。

![image-20231122205257443](https://image.elonlo.top/img/2023/11/22/655df9b0b6b2a.png)

### 3.1.轮询分发消息

#### 3.1.1.抽取工具类

```java
/**
 * rabbitmq工具类
 *
 * @author elonlo
 * @date 2023/11/22 21:03
 */
public class RabbitMQUtils {

    private static final Logger logger = LoggerFactory.getLogger(RabbitMQUtils.class);

    /**
     * 创建信道
     *
     * @return {@link Channel}
     */
    public static Channel createChannel() {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("ip");
        factory.setPort(port);
        factory.setUsername("admin");
        factory.setPassword("admin");

        Connection connection = null;
        Channel channel = null;

        try {
            connection = factory.newConnection();
        } catch (IOException | TimeoutException e) {
            logger.error("创建连接失败: []", e);
            e.printStackTrace();
        }

        try {
            if (connection != null) {
                channel = connection.createChannel();
            }
        } catch (IOException e) {
            logger.error("创建信道失败: []", e);
            e.printStackTrace();
        }
        return channel;
    }
}
```

#### 3.1.2.启动两个消费者线程

- 启动消费者线程1

  ```java
  /**
   * 消费者
   *
   * @author elonlo
   * @date 2023/11/22 21:56
   */
  public class Worker {
  
      /**
       * 队列名称
       */
      public static final String QUEUE_NAME = "hello";
  
      public static void main(String[] args) throws IOException {
          // 创建信道
          Channel channel = RabbitMQUtils.createChannel();
  
          // 接收消息
          channel.basicConsume(QUEUE_NAME, true, (customerTag, message) -> {
              System.out.println("C1接收到消息: " + new String(message.getBody(), StandardCharsets.UTF_8));
          }, (customerTag) -> {
              System.out.println("消息消费被中断,取消消费消息");
          });
      }
  }
  ```

- 配置允许多实例启动

  ![image-20231122230249646](https://image.elonlo.top/img/2023/11/22/655e18196d90c.png)

  ![image-20231122230322247](https://image.elonlo.top/img/2023/11/22/655e183a05fab.png)

- 启动消费者线程2

  ```java
  /**
   * 消费者
   *
   * @author elonlo
   * @date 2023/11/22 21:56
   */
  public class Worker {
  
      /**
       * 队列名称
       */
      public static final String QUEUE_NAME = "hello";
  
      public static void main(String[] args) throws IOException {
          // 创建信道
          Channel channel = RabbitMQUtils.createChannel();
  
          // 接收消息
          channel.basicConsume(QUEUE_NAME, true, (customerTag, message) -> {
              System.out.println("C2接收到消息: " + new String(message.getBody(), StandardCharsets.UTF_8));
          }, (customerTag) -> {
              System.out.println("消息消费被中断,取消消费消息");
          });
      }
  }
  ```

#### 3.1.3.启动一个生产者线程

```java
/**
 * 生产者
 *
 * @author elonlo
 * @date 2023/11/22 22:01
 */
public class Tasker {

    /**
     * 队列名称
     */
    public static final String QUEUE_NAME = "hello";

    public static void main(String[] args) throws IOException {
        // 创建信道
        Channel channel = RabbitMQUtils.createChannel();

        // 声明队列
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);

        // 从控制台接收消息
        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNext()) {
            String message = scanner.next();

            // 发送消息
            channel.basicPublish("", QUEUE_NAME, null, message.getBytes(StandardCharsets.UTF_8));

            System.out.println("发送消息: " + message);
        }
    }
}
```

![image-20231122230915215](https://image.elonlo.top/img/2023/11/22/655e199b60cec.png)

### 3.2.消息确认

#### 3.2.1.概念

完成一项任务可能需要几秒钟的时间，如果消费者启动了一项较长的任务，而任务在完成之前就终止了，会发生什么情况？在我们当前的代码中，一旦 RabbitMQ 将消息传递给消费者，它就会立即将其标记为删除。在这种情况下，如果有个消费者突然挂掉了，它刚刚处理的消息就会丢失。已发送给该消费者但尚未处理的消息也会丢失。

为了确保消息不会丢失，RabbitMQ 支持消息确认。消费者会发回一个 ack(nowledgement) 来告诉 RabbitMQ 已收到并处理了特定消息，RabbitMQ 可以删除该消息。

#### 3.2.2.自动确认

消息发送后立即被认为已经传送成功，这种模式需要在高吞吐量和数据传输安全性方面做权衡，因为这种模式如果消息在接收到之前，消费者出现连接或者 Channel 关闭，那么消息就丢失了，另一方面这种模式消费者那边可以传递过载的消息，没有对传递的消息数量进行限制，这样就有可能使得消费者由于接收太多还来不及处理的消息，导致这些消息的积压，最终使得内存耗尽，消费者线程被操作系统杀死，所以这种模式仅适用在消费者可以高效并以某种速率能够处理这些消息的情况下使用。

#### 3.2.3.消息确认方法

- channel.basicAck()

  确认收到一个或多个 信息

- channel.basicNack()

  拒收一条或数条已接收的信息

- channel.basicReject()

  拒收信息

#### 3.2.3.Multiple

比如现在消息队列中先后进来A、B、C、D消息

- true

  批量确认，将确认A、B、C、D消息

- false

  只会确认最新的消息，即D消息

#### 3.2.4.消息重新入队

如果消费者死亡（其通道已关闭、连接已关闭或 TCP 连接已丢失）而未发送应答，RabbitMQ 将理解为消息未被完全处理，并将重新排队。如果有其他消费者同时在线，它将迅速将消息重新传递给另一个消费者。这样，即使工作站偶尔死机，也不会丢失任何消息。

#### 3.2.5.消息手动确认

默认消息采用的是自动确认，所以要想实现消息消费过程中不丢失，需要把自动确认改为手动确认。

- 启动两个手动确认消费者

  ```java
  /**
   * 手动确认消费者
   *
   * @author elonlo
   * @date 2023/11/23 21:22
   */
  public class ManualWorker {
  
      /**
       * 队列名称
       */
      public static final String MANUAL_QUEUE_NAME = "ack_queue";
  
      public static void main(String[] args) throws IOException {
          // 创建信道
          Channel channel = RabbitMQUtils.createChannel();
  
          // 接收消息,消息改为手动确认
          boolean autoAck = false;
          channel.basicConsume(MANUAL_QUEUE_NAME, autoAck, (consumerTag, message) -> {
              try {
                  TimeUnit.SECONDS.sleep(1);
              } catch (InterruptedException e) {
                  e.printStackTrace();
              }
              System.out.println("C1接收到消息: " + new String(message.getBody(), StandardCharsets.UTF_8));
              /**
               * 消息手动确认
               * 消息的标志 tag
               * 是否批量确认 true-是 false-否
               */
              channel.basicAck(message.getEnvelope().getDeliveryTag(), false);
          }, (consumerTag) -> {
              System.out.println(consumerTag + ": 消息消费取消");
          });
      }
  }
  ```

  ```java
  /**
   * 手动确认消费者
   *
   * @author elonlo
   * @date 2023/11/23 21:22
   */
  public class ManualWorker {
  
      /**
       * 队列名称
       */
      public static final String MANUAL_QUEUE_NAME = "ack_queue";
  
      public static void main(String[] args) throws IOException {
          // 创建信道
          Channel channel = RabbitMQUtils.createChannel();
  
          // 接收消息,消息改为手动确认
          boolean autoAck = false;
          channel.basicConsume(MANUAL_QUEUE_NAME, autoAck, (consumerTag, message) -> {
              try {
                  TimeUnit.SECONDS.sleep(15);
              } catch (InterruptedException e) {
                  e.printStackTrace();
              }
              System.out.println("C2接收到消息: " + new String(message.getBody(), StandardCharsets.UTF_8));
              /**
               * 消息手动确认
               * 消息的标志 tag
               * 是否批量确认 true-是 false-否
               */
              channel.basicAck(message.getEnvelope().getDeliveryTag(), false);
          }, (consumerTag) -> {
              System.out.println(consumerTag + ": 消息消费取消");
          });
      }
  }
  ```

- 启动一个生产者

  ```java
  /**
   * 手动确认生产者
   *
   * @author elonlo
   * @date 2023/11/23 21:21
   */
  public class ManualTask {
  
      /**
       * 队列名称
       */
      public static final String MANUAL_QUEUE_NAME = "ack_queue";
  
      public static void main(String[] args) throws IOException {
          // 创建信道
          Channel channel = RabbitMQUtils.createChannel();
  
          // 声明队列
          channel.queueDeclare(MANUAL_QUEUE_NAME, false, false, false, null);
  
          // 从控制台接收消息
          Scanner scanner = new Scanner(System.in);
  
          while (scanner.hasNext()) {
              String message = scanner.next();
  
              // 发送消息
              channel.basicPublish("", MANUAL_QUEUE_NAME, null, message.getBytes(StandardCharsets.UTF_8));
  
              System.out.println("发送消息: " + message);
          }
      }
  }
  ```

![image-20231123222015679](https://image.elonlo.top/img/2023/11/23/655f5fa74cbbc.png)

在本次测试中，生产者先后发送了AA、BB、CC、DD消息，在 C1 收到AA、CC，C2 收到 BB 后，将 C2 停掉，C1 成功收到了本该 C2 接收的消息，实现了消息不丢失

### 3.3.RabbitMQ 持久化

#### 3.3.1.概念

我们已经知道了如何确保即使消费者死亡，任务也不会丢失。但是，如果 RabbitMQ 服务器停止，我们的任务仍然会丢失。

当 RabbitMQ 退出或崩溃时，它会忽视队列和消息，除非告知它不要这样做。要确保消息不会丢失，需要做两件事：我们需要将`队列`和`消息`都标记为**持久化**。

#### 3.3.2.队列持久化

在前面创建的队列都是**非持久化**的，如果重启 RabbitMQ，该队列就会被删除，要实现队列持久化，需要在声明队列的时候将**durable**参数设置为持久化。

但是需要注意，如果之前声明的队列不是持久化的，需要将原队列先删除，或者重新创建一个新的持久化的队列，不能直接在原队列上修改参数值使非持久化队列转为持久化队列，直接修改重启生产者时会出现错误。

```java
// 声明队列
// 设置队列持久化
boolean durable = true;
channel.queueDeclare(MANUAL_QUEUE_NAME, durable, false, false, null);
```

![image-20231123225432567](https://image.elonlo.top/img/2023/11/23/655f67a7f4023.png)

这时候即使重启 RabbitMQ，队列也依然会存在。

#### 3.3.3.消息持久化

要使消息实现持久化需要在**生产者发布消息**时指定属性`MessageProperties.PERSISTENT_TEXT_PLAIN`，将消息标记为持久化，但是并不能保证完成不丢失消息。尽管它告诉 RabbitMQ将消息存储到磁盘，但是依然存在当消息刚准备存储到磁盘或者消息并没有完全存储到磁盘的过程中，消息还在缓存的一个间隔点。此时并没有真正的写入磁盘。持久性保证并不强，但是对于简单任务队列来说已经足够了。

```java
// 设置生产者发送消息为持久化消息（即保存在磁盘上）
channel.basicPublish("", MANUAL_QUEUE_NAME, MessageProperties.PERSISTENT_TEXT_PLAIN, message.getBytes(StandardCharsets.UTF_8));
```

#### 3.3.4.不公平分发

RabbitMQ 默认分发消息采用的是**轮询分发**，但是在某些场景下这种策略并不是很好，比如有两个消费者在处理任务，其中有一个消费者处理任务的速度非常快，而另一个消费者处理消息的速度却很慢，这时候再使用轮询分发就会使处理任务非常快的消费者很大一部分时间处于空闲状态，而处理慢的消费者在一直处理任务。为了避免这种情况，可以在消费者**接收消息之前**设置`channel.basicQos(int prefetch)`中的`prefetch`参数来实现不公平分发：

```java
// 创建信道
Channel channel = RabbitMQUtils.createChannel();

/**
 * 设置不公平分发
 * prefetchCount(预取值): 服务器传递信息的最大数量，无限制时为 0
 * 0-轮询(公平)分发(默认) 不等于0-不公平分发
 *
 */
channel.basicQos(1);
```

![image-20231123234816596](https://image.elonlo.top/img/2023/11/23/655f74405f4c3.png)

#### 3.3.5.预取值

本身信息是以异步方式传递（发送）给客户端的，在任何特定时刻，channel 上肯定不止只有一个消息，消费者的手动确认本质上也是异步的。

这就意味着存在一个未确认的消息缓冲区，因此希望开发人员**能限制此缓冲区的大小**，以避免缓冲区里面**无限制的未确认消息问题**。可以通过使用 basic.qos 方法设置 "预取计数 "值来实现。该值定义了通道上**允许的未确认消息的最大数量**。当数量达到配置的计数时，RabbitMQ 将停止在通道上交付更多消息，除非至少有一条未确认的消息被确认。

值为 0 意味着**无限制**，允许任何数量的未确认消息。

例如，如果通道 Ch 上有未确认的消息 5、6、7 和 8 ，且通道 Ch 的预取计数设置为 4，此时 RabbitMQ 将不会在该通道上**再传递任何消息**，除非**至少有一个未确认的消息被 ack**。比如 tag=6 这个消息刚刚被确认 ack，RabbitMQ 将会感知这个情况**并且再发送一条消息**。**消息确认和 QoS 预取值对用户吞吐量有重大影响**。通常，增加预取值将提高向消费者传递消息的速度。

确认模式和 QoS 预取值对**用户吞吐量**有显著影响。一般来说，增加预取值会提高向用户发送信息的速度。自动确认模式可获得最佳速率。不过，在这两种情况下，**已传递但尚未处理**的信息数量也会增加，从而**增加用户 RAM 消耗**。

应谨慎使用自动确认模式或无限制预取的手动确认模式。如果**消费者在未确认的情况下消耗大量信息**，将导致其所连接节点的**内存消耗增长**。寻找合适的预取值需要反复试验，不同的工作负载会有不同的预取值。**100 到 300 之间的值**通常能提供**最佳的吞吐量**，而且不会给用户带来过大的压力。更高的值通常会遇到收益递减规律。

预取值为 1 是最保守的值。它会大大降低吞吐量，尤其是在消费者连接延迟较高的环境中。对于许多应用而言，更高的值是合适和最佳的。

**总结：预取值是指消费者能够接收但未消费的最大消息数量。**

- 消费者 C1

  ```java
  /**
   * 手动确认消费者
   *
   * @author elonlo
   * @date 2023/11/23 21:22
   */
  public class ManualWorker {
  
      /**
       * 队列名称
       */
      public static final String MANUAL_QUEUE_NAME = "ack_queue";
  
      public static void main(String[] args) throws IOException {
          // 创建信道
          Channel channel = RabbitMQUtils.createChannel();
  
          /**
           * 设置不公平分发
           * prefetchCount: 服务器将传送的信息的最大数量，无限制时为 0
           * 0-轮询(公平)分发(默认) 不等于0-不公平分发
           *
           */
          // 设置预取值为2
          channel.basicQos(2);
  
          // 接收消息,消息改为手动确认
          boolean autoAck = false;
          channel.basicConsume(MANUAL_QUEUE_NAME, autoAck, (consumerTag, message) -> {
              try {
                  TimeUnit.SECONDS.sleep(1);
              } catch (InterruptedException e) {
                  e.printStackTrace();
              }
              System.out.println("C1接收到消息: " + new String(message.getBody(), StandardCharsets.UTF_8) +" " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")));
  
              /**
               * 消息手动确认
               * 消息的标志 tag
               * 是否批量确认 true-是 false-否
               */
              channel.basicAck(message.getEnvelope().getDeliveryTag(), false);
          }, (consumerTag) -> {
              System.out.println(consumerTag + ": 消息消费取消");
          });
      }
  }

- 消费者 C2

  ```java
  /**
   * 手动确认消费者
   *
   * @author elonlo
   * @date 2023/11/23 21:22
   */
  public class ManualWorker {
  
      /**
       * 队列名称
       */
      public static final String MANUAL_QUEUE_NAME = "ack_queue";
  
      public static void main(String[] args) throws IOException {
          // 创建信道
          Channel channel = RabbitMQUtils.createChannel();
  
          /**
           * 设置不公平分发
           * prefetchCount: 服务器将传送的信息的最大数量，无限制时为 0
           * 0-轮询(公平)分发(默认) 不等于0-不公平分发
           *
           */
          // 设置预取值为5
          channel.basicQos(5);
  
          // 接收消息,消息改为手动确认
          boolean autoAck = false;
          channel.basicConsume(MANUAL_QUEUE_NAME, autoAck, (consumerTag, message) -> {
              try {
                  TimeUnit.SECONDS.sleep(30);
              } catch (InterruptedException e) {
                  e.printStackTrace();
              }
              System.out.println("C2接收到消息: " + new String(message.getBody(), StandardCharsets.UTF_8) +" " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")));
  
              /**
               * 消息手动确认
               * 消息的标志 tag
               * 是否批量确认 true-是 false-否
               */
              channel.basicAck(message.getEnvelope().getDeliveryTag(), false);
          }, (consumerTag) -> {
              System.out.println(consumerTag + ": 消息消费取消");
          });
      }
  }
  ```

![image-20231124071857010](https://image.elonlo.top/img/2023/11/24/655fdde0c84e6.png)

## 4.发布确认

### 4.1.发布确认原理

生产者将信道设置为 **confirm** 模式，一旦信道进入 **confirm** 模式，所有在该信道上面发布的消息都会被**指派一个唯一的 ID**（从 1 开始），一旦消息被投递到所有匹配的队列之后，broker 就会**发送一个确认给生产者（包含消息的唯一 ID）**，这就使得生产者知道消息已经正确到达目的地队列了，如果消息和队列是可持久化的，那么**确认消息会在将消息写入磁盘之后发出**，broker 回传给生产者的确认消息中 **delivery-tag 域包含了确认消息的序列号**，此外 broker 也可以设置 basic.ack 的 multiple 域，表示到这个序列号之前的所有消息都已经得到了处理。

confirm 模式的最大好处在于是异步的，一旦发布一条消息，生产者应用程序就可以在等待信道返回确认消息的同时继续发送下一条消息，当消息最终得到确认之后，生产者应用便可以通过回调方法来处理该确认消息。如果 RabbitMQ 因为自身内部错误导致消息丢失，就会发送一条 nack 消息，生产者应用程序同样可以在回调方法处理该 nack 消息

### 4.2.发布确认策略

#### 4.2.1.开启发布确认

发布确认默认是不开启的，如果开启需要调用方法 `channel.confirmSelect()`，示例如下：

```java
// 创建信道
Channel channel = RabbitMQUtils.createChannel();

// 开启发布确认
channel.confirmSelect();
```

#### 4.2.2.单独发布确认

这是一种简单的确认方式，它是一种**同步确认发布**的方式，也就是发布一个消息之后只有它被确认发布，后续的消息才能继续传递。`channel.waitForConfirmsOrDie(long timeout)` 方法用于等待确认。一旦信息得到确认，该方法就会返回。如果消息在超时时间内未得到确认，或者消息被黑屏（意味着由于某种原因，代理无法处理该消息），该方法将抛出一个异常。异常处理通常包括记录错误信息和/或重试发送信息。

这种技术非常简单，但也有一个很大的缺点：它会**大大降低发布速度**，因为消息的确认会**阻止所有后续消息的发布**。这种方法的吞吐量不会超过每秒几百条已发布信息。不过，这对于某些应用来说已经足够了。

```java
/**
 * 发布确认消息
 *
 * @author elonlo
 * @date 2023/11/24 16:00
 */
public class ConfirmMessage {

    /** 
     * 消息数量
     */
    public static final int MESSAGE_COUNT = 1000;

    /**
     * 单独发布消息
     */
    public static void individuallyPublish() throws IOException, InterruptedException {
        // 获取信道
        Channel channel = RabbitMQUtils.createChannel();

        // 开启发布确认
        channel.confirmSelect();

        // 声明队列
        String queueName = UUID.randomUUID().toString();
        channel.queueDeclare(queueName, true, false, false, null);

        long start = System.currentTimeMillis();
        for (int i = 0; i < MESSAGE_COUNT; i++) {
            String message = i + "";
            channel.basicPublish("", queueName, null, message.getBytes(StandardCharsets.UTF_8));

            // 服务端返回false或超时时间内未返回,生产者可以重发消息
            boolean flag = channel.waitForConfirms();
            if (flag) {
                System.out.println("消息发送成功...");
            }
        }

        long end = System.currentTimeMillis();
        System.out.println(MESSAGE_COUNT + "个消息耗时: " + (end - start) + "ms");
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        // 单独发布消息
        individuallyPublish();
    }
}
```

#### 4.2.3.批量发布确认

与等待单条消息的确认相比，等待一批消息的确认可大幅提高吞吐量（远程 RabbitMQ 节点的确认次数可达 20-30 次）。但是却有一个缺点，如果发生故障，我们无法确切知道出错的原因，因此我们可能需要在内存中保留整批消息，以便记录有意义的信息或重新发布消息。而且这种解决方案仍然是同步的，因此会阻止消息的发布。

```java
/**
 * 发布确认消息
 *
 * @author elonlo
 * @date 2023/11/24 16:00
 */
public class ConfirmMessage {

    /** 
     * 消息数量
     */
    public static final int MESSAGE_COUNT = 1000;

    /**
     * 批量发布消息
     */
    public static void batchPublish() throws IOException, InterruptedException {
        // 获取信道
        Channel channel = RabbitMQUtils.createChannel();

        // 开启发布确认
        channel.confirmSelect();

        // 声明队列
        String queueName = UUID.randomUUID().toString();
        channel.queueDeclare(queueName, true, false, false, null);

        // 批量确认大小
        int batchSize = 100;

        long start = System.currentTimeMillis();
        for (int i = 0; i < MESSAGE_COUNT; i++) {
            String message = i + "";
            channel.basicPublish("", queueName, null, message.getBytes(StandardCharsets.UTF_8));

            // 每发送100条消息确认一次
            if (i % batchSize == 0) {
                channel.waitForConfirms();
            }
        }

        long end = System.currentTimeMillis();
        System.out.println("发布" + MESSAGE_COUNT + "个消息耗时: " + (end - start) + "ms");
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        // 批量发布消息
        batchPublish();
    }
}
```

#### 4.2.4.异步发布确认

异步确认虽然编程逻辑上比单独发布和批量发布要复杂，但是可靠性和效率相对来说很高，它利用**回调函数**来达到消息可靠性传递，用户只需在客户端注册一个回调，就能收到这些确认通知。主要有 2 个回调：

一个用于确认消息（ackCallback），另一个用于未确认消息（nackCallback）。每个回调有 2 个参数：

**sequence number（序列号）**：用于识别已确认或被忽略报文的编号。我们很快就会看到如何将其与已发布的报文关联起来。
**multiple**：如果为false，则只有一条信息被确认/加黑；如果为true，则所有序列号较小或相等的信息都被确认/加黑。
序列号可在发布前通过 `channel.getNextPublishSeqNo()` 获取：

```java
/**
 * 发布确认消息
 *
 * @author elonlo
 * @date 2023/11/24 16:00
 */
public class ConfirmMessage {

    /**
     * 消息数量
     */
    public static final int MESSAGE_COUNT = 1000;

    /**
     * 异步发布确认
     */
    public static void asyncPublish() throws IOException {
        // 获取信道
        Channel channel = RabbitMQUtils.createChannel();

        // 开启发布确认
        channel.confirmSelect();

        // 声明队列
        String queueName = UUID.randomUUID().toString();
        channel.queueDeclare(queueName, true, false, false, null);

        long start = System.currentTimeMillis();

        // 消息确认成功回调
        ConfirmCallback confirmCallback = (deliveryTag, multiple) -> {
            System.out.println("确认的消息: " + deliveryTag);
        };

        // 消息确认失败回调
        ConfirmCallback nackCallback = (deliveryTag, multiple) -> {
            System.out.println("未确认的消息: " + deliveryTag);
        };

        /**
         * 1.消息确认成功回调
         * 2.消息确认失败回调
         */
        channel.addConfirmListener(confirmCallback, nackCallback);// 异步通知

        for (int i = 0; i < MESSAGE_COUNT; i++) {
            String message = "消息" + i;

            // 发布消息
            channel.basicPublish("", queueName, null, message.getBytes(StandardCharsets.UTF_8));
        }

        long end = System.currentTimeMillis();
        System.out.println("发布" + MESSAGE_COUNT + "个异步发布消息耗时: " + (end - start) + "ms");
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        // 异步发布消息
        asyncPublish();
    }
}
```

#### 4.2.5.异步未确认消息处理

最好的解决方案就是把未确认的消息放到一个基于内存且能被发布线程访问的 Map 中，例如用 **ConcurrentSkipListMap** 这个队列在 confirm callbacks 与发布线程之间进行消息的传递

```java
/**
 * 发布确认消息
 *
 * @author elonlo
 * @date 2023/11/24 16:00
 */
public class ConfirmMessage {

    /**
     * 消息数量
     */
    public static final int MESSAGE_COUNT = 1000;

    /**
     * 异步发布确认
     */
    public static void asyncPublish() throws IOException {
        // 获取信道
        Channel channel = RabbitMQUtils.createChannel();

        // 开启发布确认
        channel.confirmSelect();

        /**
         * 线程安全有序的哈希表,适用于高并发的情况下
         * 1.轻松的将序号和消息进行关联
         * 2.轻松批量删除消息 根据序号
         * 3.支持高并发（多线程）
         */
        ConcurrentSkipListMap<Long, String> skipListMap = new ConcurrentSkipListMap<>();

        // 声明队列
        String queueName = UUID.randomUUID().toString();
        channel.queueDeclare(queueName, true, false, false, null);

        /**
         * 消息确认成功回调
         * 1.消息的标记
         * 2.是否为批量确认
         *
         */
        ConfirmCallback confirmCallback = (deliveryTag, multiple) -> {
            if (multiple) {
                // 2.删除已经确认的消息,剩下就是未确认的消息
                ConcurrentNavigableMap<Long, String> headMap = skipListMap.headMap(deliveryTag);
                headMap.clear();
            }
            skipListMap.remove(deliveryTag);

            System.out.println("确认的消息: " + deliveryTag);
        };

        // 消息确认失败回调
        ConfirmCallback nackCallback = (deliveryTag, multiple) -> {
            // 3.获取未确认的消息
            String message = skipListMap.get(deliveryTag);
            System.out.println("未确认的消息为: " + message + "未确认的消息tag: " + deliveryTag);
        };

        /**
         * 1.消息确认成功回调
         * 2.消息确认失败回调
         */
        channel.addConfirmListener(confirmCallback, nackCallback);// 异步通知

        long start = System.currentTimeMillis();
        for (int i = 0; i < MESSAGE_COUNT; i++) {
            String message = "消息" + i;

            // 发布消息
            channel.basicPublish("", queueName, null, message.getBytes(StandardCharsets.UTF_8));
            // 1.记录下所有要发送的消息 消息的总和
            skipListMap.put(channel.getNextPublishSeqNo(), message);

        }

        long end = System.currentTimeMillis();
        System.out.println("发布" + MESSAGE_COUNT + "个异步发布消息耗时: " + (end - start) + "ms");
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        // 异步发布消息
        asyncPublish(); // 90ms
    }
}
```

## 5.交换机

### 5.1.Exchanges

#### 5.1.1.概念

RabbitMQ 消息传递模型的核心思想是：**生产者生产的消息从不会直接发送到队列**。实际上，通常生产者甚至都不知道这些消息传递到了哪些队列中。相反，**生产者只能将消息发送到交换机（exchange）**，交换机工作的内容非常简单，一方面它接收来自生产者的消息，另一方面将它们推入队列。交换机必须确切知道如何处理收到的消息，是应该把这些消息放到特定队列还是把他们放到多个队列中，亦或者是丢弃它们，这取决于交换机的类型。

![image-20231129202400849](https://image.elonlo.top/img/2023/11/29/65672d69b9d56.png)

#### 5.1.2.Exchanges 类型

主要有直接（direct）、主题（topic）、标题（headers）、扇出（fanout）这四种。

- **Direct Exchange**：直连交换机，根据Routing Key (路由键)进行投递到不同队列。

- **Fanout Exchange**：扇形交换机，采用广播模式，根据绑定的交换机，路由到与之对应的所有队列。

- **Topic Exchange**：主题交换机，对路由键进行模式匹配后进行投递，符号#表示一个或多个词，*表示一个词。

- **Header Exchange**：头交换机，不处理路由键。 而是根据发送的消息内容中的headers属性进行匹配。

#### 5.1.3.默认 exchange

可以通过指定空字符串创建一个默认的交换机，示例如下：

```java
channel.basicPublish("", QUEUE_NAME, null, content.getBytes(StandardCharsets.UTF_8));
```

### 5.2.临时队列

每当我们连接到 RabbitMQ 时，都需要一个全新的空队列，为此可以创建一个具有随机名称的队列，或者能让服务器为我们选择一个随机队列，其次，一旦**消费者连接关闭，队列就应该被删除。**

创建临时队列的方式如下：

```java
String queue = channel.queueDeclare().getQueue();
```

### 5.3.绑定（bindings）

我们已经创建了一个交换机和一个队列。现在，我们需要告诉交易所向队列发送消息。**交换中心和队列之间**的这种**关系**称为绑定。

![image-20231129203207812](https://image.elonlo.top/img/2023/11/29/65672f47cf7f9.png)

绑定是交换和队列之间的关系。可以简单地理解为：队列对来自该交换的报文感兴趣。

绑定可以使用一个额外的路由键（routingKey）参数。为避免与 basic_publish 参数混淆，我们将其称为绑定密钥。我们可以这样创建一个绑定密钥：

```java
channel.queueBind(queueName, EXCHANGE_NAME, "black");
```

### 5.4.Fanout

#### 5.4.1.概念

Fanout 这种类型非常简单。它是将接收到的所有消息**广播**到它知道的所有队列中。系统默认有这种类型（amq.fanout）的交换机。

![image-20231129205450771](https://image.elonlo.top/img/2023/11/29/6567349a807b9.png)

#### 5.4.2.Fandout实战

- 消费者一

  ```java
  /**
   * 交换机-fanout模式,接收消息1
   *
   * @author elonlo
   * @date 2023/11/29 21:15
   */
  public class ReceiveMessageOne {
  
      /**
       * 交换机名称
       */
      public static final String EXCHANGE_NAME = "logs";
  
      public static void main(String[] args) throws IOException {
          // 创建信道
          Channel channel = RabbitMQUtils.createChannel();
  
          /**
           * 声明交换机
           * 1.交换机的名称
           * 2.交换机的类型
           */
          channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.FANOUT);
  
          /**
           * 生成一个随机队列,队列名称是随机的
           * 消费者断开连接后,队列将被自动删除
           */
          String queueName = channel.queueDeclare().getQueue();
  
          // 绑定交换机和队列
          channel.queueBind(queueName, EXCHANGE_NAME, "");
  
          // 接收消息
          channel.basicConsume(queueName, true, (consumerTag, message) -> {
              System.out.println("ReceiveMessageOne接收到消息: " + new String(message.getBody(), StandardCharsets.UTF_8));
          }, consumerTag -> {
          });
      }
  }
  ```

- 消费者二

  ```java
  /**
   * 交换机-fanout模式,接收消息2
   *
   * @author elonlo
   * @date 2023/11/29 21:15
   */
  public class ReceiveMessageTwo {
  
      /**
       * 交换机名称
       */
      public static final String EXCHANGE_NAME = "logs";
  
      public static void main(String[] args) throws IOException {
          // 创建信道
          Channel channel = RabbitMQUtils.createChannel();
  
          /**
           * 声明交换机
           * 1.交换机的名称
           * 2.交换机的类型
           */
          channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.FANOUT);
  
          /**
           * 生成一个随机队列,队列名称是随机的
           * 消费者断开连接后,队列将被自动删除
           */
          String queueName = channel.queueDeclare().getQueue();
  
          // 绑定交换机和队列
          channel.queueBind(queueName, EXCHANGE_NAME, "");
  
          // 接收消息
          channel.basicConsume(queueName, true, (consumerTag, message) -> {
              System.out.println("ReceiveMessageTwo接收到消息: " + new String(message.getBody(), StandardCharsets.UTF_8));
          }, consumerTag -> {
          });
      }
  }
  ```

- 生产者

  ```java
  /**
   * 交换机-fanout,生产者
   *
   * @author elonlo
   * @date 2023/11/29 21:23
   */
  public class EmitLog {
  
      /**
       * 交换机名称
       */
      public static final String EXCHANGE_NAME = "logs";
  
      public static void main(String[] args) throws IOException {
          // 创建信道
          Channel channel = RabbitMQUtils.createChannel();
  
          // 声明交换机
          channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.FANOUT);
  
          Scanner scanner = new Scanner(System.in);
          System.out.println("请输入信息:");
          while (scanner.hasNext()) {
              String message = scanner.next();
              channel.basicPublish(EXCHANGE_NAME, "", null, message.getBytes(StandardCharsets.UTF_8));
              System.out.println("生产者发出消息: " + message);
          }
      }
  }
  ```

![image-20231129214358428](https://image.elonlo.top/img/2023/11/29/6567401f07186.png)

### 5.5.Direct

直接交换背后的路由算法非常简单，消息会进入**绑定密钥与消息路由密钥完全匹配**的队列，这种类型的工作方式是，消息只去它绑定的 routingKey 队列中去。

![image-20231129215304254](https://image.elonlo.top/img/2023/11/29/6567423fe125c.png)

- 消费者一

  ```java
  /**
   * 交换机-direct,消费者一
   *
   * @author elonlo
   * @date 2023/11/29 22:04
   */
  public class ReceiveLogsDirectOne {
  
      /**
       * 交换机名称
       */
      public static final String EXCHANGE_NAME = "direct_logs";
  
      /**
       * 队列名称
       */
      public static final String QUEUE_NAME = "console";
  
      public static void main(String[] args) throws IOException {
          // 创建信道
          Channel channel = RabbitMQUtils.createChannel();
  
          // 声明交换机
          channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.DIRECT);
  
          // 声明队列
          channel.queueDeclare(QUEUE_NAME, false, false, false, null);
  
          // 队列绑定交换机及路由键
          channel.queueBind(QUEUE_NAME, EXCHANGE_NAME, "info");
          channel.queueBind(QUEUE_NAME, EXCHANGE_NAME, "warning");
  
          // 接收消息
          channel.basicConsume(QUEUE_NAME, true, (consumerTag, message) -> {
              System.out.println("ReceiveLogsDirectOne接收到消息: " + new String(message.getBody(), StandardCharsets.UTF_8));
          }, consumerTag -> {
          });
      }
  }
  ```

- 消费者二

  ```java
  /**
   * 交换机-direct,消费者二
   *
   * @author elonlo
   * @date 2023/11/29 22:04
   */
  public class ReceiveLogsDirectTwo {
  
      /**
       * 交换机名称
       */
      public static final String EXCHANGE_NAME = "direct_logs";
  
      /**
       * 队列名称
       */
      public static final String QUEUE_NAME = "disk";
  
      public static void main(String[] args) throws IOException {
          // 创建信道
          Channel channel = RabbitMQUtils.createChannel();
  
          // 声明交换机
          channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.DIRECT);
  
          // 声明队列
          channel.queueDeclare(QUEUE_NAME, false, false, false, null);
  
          // 队列绑定交换机及路由键
          channel.queueBind(QUEUE_NAME, EXCHANGE_NAME, "error");
  
          // 接收消息
          channel.basicConsume(QUEUE_NAME, true, (consumerTag, message) -> {
              System.out.println("ReceiveLogsDirectTwo接收到消息: " + new String(message.getBody(), StandardCharsets.UTF_8));
          }, consumerTag -> {
          });
      }
  }
  ```

- 生产者

  ```java
  /**
   * 交换机-direct,生产者
   *
   * @author elonlo
   * @date 2023/11/29 21:23
   */
  public class EmitDirectLog {
  
      /**
       * 交换机名称
       */
      public static final String EXCHANGE_NAME = "direct_logs";
  
      public static void main(String[] args) throws IOException {
          // 创建信道
          Channel channel = RabbitMQUtils.createChannel();
  
          // 声明交换机
          channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.DIRECT);
  
          Scanner scanner = new Scanner(System.in);
          System.out.println("请输入信息:");
          while (scanner.hasNext()) {
              String message = scanner.next();
              channel.basicPublish(EXCHANGE_NAME, "error", null, message.getBytes(StandardCharsets.UTF_8));
              System.out.println("生产者发出消息: " + message);
          }
      }
  }
  ```

### 5.6.Topic

#### 5.6.1.Topic 定义

发送到 `Topic交换机` 的报文不能随意定义 `routing_key`，它必须是一个**用点分隔**的单词列表。这些单词可以是任何内容，但通常会指定与信息相关的一些特征。以下是几个有效的路由密钥示例 `stock.usd.nyse`、`nyse.vmw`、`quick.orange.rabbit`。路由密钥的字数不限，最多 255 字节。

绑定密钥也必须采用相同的形式。`Topic交换机` 背后的逻辑与 `direct交换机` 类似，使用特定路由密钥发送的信息将被传送到所有使用匹配绑定密钥绑定的队列。不过，绑定密钥有两种重要的特殊情况：

- *：可以完全替代一个单词。
- #：可以替代零个或多个单词。

#### 5.6.2.Topic 示例

在下图中，Q1 和 Q2 绑定的关系如下：

- Q1：中间是orange带3个单词的字符串(*.orange. *)

- Q2：最后一个单词是 rabbit 的3个单词(*. *.rabbit)

  ​		 第一个单词是 lazy 的多个单词(lazy.#)

![image-20231130190113716](https://image.elonlo.top/img/2023/11/30/65686b80a497b.png)

上图是一个队列绑定关系图，下面给出一些哪些交换机的名称能够与队列绑定示例：

| 路由键名称               | Q1队列 | Q2队列 |
| ------------------------ | ------ | ------ |
| quick.orange.rabbit      | Y      | Y      |
| lazy.orange.elephant     | Y      | Y      |
| quick.orange.fox         | Y      | N      |
| lazy.brown.fox           | N      | Y      |
| lazy.pink.rabbit         | N      | Y      |
| quick.brown.fox          | N      | N      |
| quick.orange.male.rabbit | N      | N      |
| lazy.orange.male.rabbit  | N      | Y      |

当队列使用 `#` 绑定键绑定时，无论路由键是什么，它都将接收所有信息，就像 `fanout` 类型一样。

如果绑定中没有使用特殊字符 `*` 和 `#`，则 `topic交换机` 的行为与 `direct交换机` 相同。

#### 5.6.3.Topic 实战

- 消费者 C1

  ```java
  /**
   * 交换机-topic,消费者C1
   *
   * @author elonlo
   * @date 2023/11/30 20:05
   */
  public class ReceiveLogsTopicOne {
  
      /**
       * 交换机名称
       */
      public static final String EXCHANGE_NAME = "topic_logs";
  
      public static void main(String[] args) throws IOException {
          // 创建信道
          Channel channel = RabbitMQUtils.createChannel();
  
          // 声明交换机
          channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.TOPIC);
  
          // 声明队列
          final String queueName = "Q1";
          channel.queueDeclare(queueName, false, false, false, null);
  
          // 队列绑定交换机
          channel.queueBind(queueName, EXCHANGE_NAME, "*.orange.*");
          System.out.println("Q1等待接收消息......");
  
          // 接收消息
          channel.basicConsume(queueName, true, (consumerTag, message) -> {
              System.out.println("队列Q1接收到消息: " + new String(message.getBody(), StandardCharsets.UTF_8) + " 路由键是: " + message.getEnvelope().getRoutingKey());
          }, consumerTag -> {});
      }
  }
  ```

- 消费者 C2

  ```java
  /**
   * 交换机-topic,消费者C2
   *
   * @author elonlo
   * @date 2023/11/30 20:05
   */
  public class ReceiveLogsTopicTwo {
  
      /**
       * 交换机名称
       */
      public static final String EXCHANGE_NAME = "topic_logs";
  
      public static void main(String[] args) throws IOException {
          // 创建信道
          Channel channel = RabbitMQUtils.createChannel();
  
          // 声明交换机
          channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.TOPIC);
  
          // 声明队列
          final String queueName = "Q2";
          channel.queueDeclare(queueName, false, false, false, null);
  
          // 队列绑定交换机
          channel.queueBind(queueName, EXCHANGE_NAME, "*.*.rabbit");
          channel.queueBind(queueName, EXCHANGE_NAME, "lazy.#");
          System.out.println("Q2等待接收消息......");
  
          // 接收消息
          channel.basicConsume(queueName, true, (consumerTag, message) -> {
              System.out.println("队列Q2接收到消息: " + new String(message.getBody(), StandardCharsets.UTF_8) + " 路由键是: " + message.getEnvelope().getRoutingKey());
          }, consumerTag -> {});
      }
  }
  ```

- 生产者

  ```java
  /**
   * 交换机-topic,生产者
   *
   * @author elonlo
   * @date 2023/11/30 20:14
   */
  public class EmitLogTopic {
  
      /**
       * 交换机名称
       */
      public static final String EXCHANGE_NAME = "topic_logs";
  
      public static void main(String[] args) throws IOException {
          // 创建信道
          Channel channel = RabbitMQUtils.createChannel();
  
          // 声明交换机
          channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.TOPIC);
  
          // 发送消息
          Map<String, String> routingKeyMap = new HashMap<>(16);
          routingKeyMap.put("quick.orange.rabbit", "Q1Q2接收到消息");
          routingKeyMap.put("lazy.orange.elephant", "Q1Q2接收到消息");
          routingKeyMap.put("quick.orange.fox", "Q1接收到消息");
          routingKeyMap.put("lazy.brown.fox", "Q2接收到消息");
          routingKeyMap.put("lazy.pink.rabbit", "Q2接收到消息");
          routingKeyMap.put("quick.brown.fox", "Q1Q2都未接收到消息");
          routingKeyMap.put("quick.orange.male.rabbit", "Q1Q2都未接收到消息");
          routingKeyMap.put("lazy.orange.male.rabbit", "Q2接收到消息");
  
          routingKeyMap.forEach((k, v) -> {
              try {
                  channel.basicPublish(EXCHANGE_NAME, k, null, v.getBytes(StandardCharsets.UTF_8));
                  System.out.println("生产者发送消息: " + v);
              } catch (IOException e) {
                  e.printStackTrace();
              }
          });
      }
  }
  ```

## 6.死信队列

### 6.1.概念

死信，顾名思义就是无法被消费的消息，字面意思上可以这样理解，一般来说，producer 将消息投递到 broker 或者直接到 queue 里了，consumer 从 queue 取出消息进行消费，但某些时候由于**特定的原因导致 queue 中的某些消息无法被消费**，这样的消息如果没有进行处理，就变成了死信，有死信自然就有了死信队列。

例如为了保证订单业务的消息数据不丢失，需要使用到 RabbitMQ 的死信队列机制，当消息消费发生异常时，将消息投入到死信中，还有比如说用户在商城下单成功并点击去支付后在指定时间内未支付自动失效。

### 6.2.死信队列来源

- 消息 TTL 过期
- 队列达到最大长度（即队列满了，无法再添加数据到mq中）
- 消息被拒绝（basic.reject 或 basic.nack）并且 requeue=false

### 6.3.死信实战

#### 6.3.1.死信业务处理图

![image-20231130221604450](https://image.elonlo.top/img/2023/11/30/6568992469d66.png)

#### 6.3.2.消息 TTL 过期

- 消费者 C1

  启动之后关闭该消费者，模拟其接收不到消息

  ```java
  /**
   * 死信队列-消费者C1
   *
   * @author elonlo
   * @date 2023/11/30 22:19
   */
  public class DeadQueueConsumerOne {
  
      /**
       * 普通队列
       */
      public static final String NORMAL_QUEUE = "normal_queue";
  
      /**
       * 死信队列
       */
      public static final String DEAD_QUEUE = "dead_queue";
  
      /**
       * 普通交换机
       */
      public static final String NORMAL_EXCHANGE = "normal_exchange";
  
      /**
       * 死信交换机
       */
      public static final String DEAD_EXCHANGE = "dead_exchange";
  
      public static void main(String[] args) throws IOException {
          // 创建信道
          Channel channel = RabbitMQUtils.createChannel();
  
          // 声明普通交换机、死信交换机
          channel.exchangeDeclare(NORMAL_EXCHANGE, BuiltinExchangeType.DIRECT);
          channel.exchangeDeclare(DEAD_EXCHANGE, BuiltinExchangeType.DIRECT);
  
          // 声明普通队列、死信队列
          // 普通队列设置参数
          Map<String, Object> argMap = new HashMap<>(16);
          // 过期时间,不建议在消费者设置,建议在生产者指定,更灵活
  //        argMap.put("x-message-ttl", 10000);
          // 正常队列设置死信交换机
          argMap.put("x-dead-letter-exchange", DEAD_EXCHANGE);
          // 设置死信RoutingKey
          argMap.put("x-dead-letter-routing-key", "lucy");
          channel.queueDeclare(NORMAL_QUEUE, false, false, false, argMap);
          channel.queueDeclare(DEAD_QUEUE, false, false, false, null);
  
          // 普通队列、死信队列绑定对应的交换机
          channel.queueBind(NORMAL_QUEUE, NORMAL_EXCHANGE, "jack");
          channel.queueBind(DEAD_QUEUE, DEAD_EXCHANGE, "lucy");
          System.out.println("等待接收消息......");
  
          // 普通队列接收消息
          channel.basicConsume(NORMAL_QUEUE, true, (consumerTag, message) -> {
              System.out.println("C1接收到的消息: " + new String(message.getBody(), StandardCharsets.UTF_8));
          }, consumerTag -> {
          });
      }
  }
  ```

- 消费者 C2

  ```java
  /**
   * 死信队列-消费者C2
   *
   * @author elonlo
   * @date 2023/11/30 22:19
   */
  public class DeadQueueConsumerTwo {
  
      /**
       * 死信队列
       */
      public static final String DEAD_QUEUE = "dead_queue";
  
      public static void main(String[] args) throws IOException {
          // 创建信道
          Channel channel = RabbitMQUtils.createChannel();
  
          System.out.println("等待接收消息......");
  
          // 死信队列接收消息
          channel.basicConsume(DEAD_QUEUE, true, (consumerTag, message) -> {
              System.out.println("C2接收到的消息: " + new String(message.getBody(), StandardCharsets.UTF_8));
          }, consumerTag -> {
          });
      }
  }
  ```

- 生产者

  ```java
  /**
   * 死信队列-生产者
   *
   * @author elonlo
   * @date 2023/11/30 22:47
   */
  public class DeadQueueProducer {
  
      /**
       * 普通交换机
       */
      public static final String NORMAL_EXCHANGE = "normal_exchange";
  
      public static void main(String[] args) throws InterruptedException, IOException {
          Channel channel = RabbitMQUtils.createChannel();
  
          // 死信消息  设置TTL
          AMQP.BasicProperties properties = new AMQP.BasicProperties()
                  .builder().expiration("10000").build();
  
          // 发送消息
          for (int i = 1; i < 11; i++) {
              String message = "info" + i;
              // 延迟一秒发送,方便查看普通队列中的消息放到死信队列中
              TimeUnit.SECONDS.sleep(1);
              channel.basicPublish(NORMAL_EXCHANGE, "jack", properties, message.getBytes(StandardCharsets.UTF_8));
              System.out.println("生产者发送消息: " + message);
          }
      }
  }
  ```

#### 6.3.3.队列达到最大长度

注意：本次代码在上述代码上进行修改，因为修改了普通队列的属性，直接重新启动会报错，可以先去 MQ 后台删掉该队列，然后重新创建普通队列。

- 消费者 C1

  ```java
  /**
   * 死信队列-消费者C1
   *
   * @author elonlo
   * @date 2023/11/30 22:19
   */
  public class DeadQueueConsumerOne {
  
      /**
       * 普通队列
       */
      public static final String NORMAL_QUEUE = "normal_queue";
  
      /**
       * 死信队列
       */
      public static final String DEAD_QUEUE = "dead_queue";
  
      /**
       * 普通交换机
       */
      public static final String NORMAL_EXCHANGE = "normal_exchange";
  
      /**
       * 死信交换机
       */
      public static final String DEAD_EXCHANGE = "dead_exchange";
  
      public static void main(String[] args) throws IOException {
          Channel channel = RabbitMQUtils.createChannel();
  
          channel.exchangeDeclare(NORMAL_EXCHANGE, BuiltinExchangeType.DIRECT);
          channel.exchangeDeclare(DEAD_EXCHANGE, BuiltinExchangeType.DIRECT);
  
          // 声明普通队列、死信队列
          // 普通队列设置参数
          Map<String, Object> argMap = new HashMap<>(16);
          // 正常队列设置死信交换机
          argMap.put("x-dead-letter-exchange", DEAD_EXCHANGE);
          // 设置死信RoutingKey
          argMap.put("x-dead-letter-routing-key", "lucy");
          // 设置正常队列的最大长度
          argMap.put("x-max-length", 8);
          channel.queueDeclare(NORMAL_QUEUE, false, false, false, argMap);
          channel.queueDeclare(DEAD_QUEUE, false, false, false, null);
          
          channel.queueBind(NORMAL_QUEUE, NORMAL_EXCHANGE, "jack");
          channel.queueBind(DEAD_QUEUE, DEAD_EXCHANGE, "lucy");
          System.out.println("等待接收消息......");
  
          channel.basicConsume(NORMAL_QUEUE, true, (consumerTag, message) -> {
              System.out.println("C1接收到的消息: " + new String(message.getBody(), StandardCharsets.UTF_8));
          }, consumerTag -> {
          });
      }
  }
  ```

- 生产者

  ```java
  /**
   * 死信队列-生产者
   *
   * @author elonlo
   * @date 2023/11/30 22:47
   */
  public class DeadQueueProducer {
  
      /**
       * 普通交换机
       */
      public static final String NORMAL_EXCHANGE = "normal_exchange";
  
      public static void main(String[] args) throws InterruptedException, IOException {
          Channel channel = RabbitMQUtils.createChannel();
  
          // 发送消息
          for (int i = 1; i < 11; i++) {
              String message = "info" + i;
              // 延迟一秒发送,方便查看普通队列中的消息放到死信队列中
              TimeUnit.SECONDS.sleep(1);
              channel.basicPublish(NORMAL_EXCHANGE, "jack", null, message.getBytes(StandardCharsets.UTF_8));
              System.out.println("生产者发送消息: " + message);
          }
      }
  }
  ```

#### 6.3.4.消息被拒

- 消费者 C1

  ```java
  /**
   * 死信队列-消费者C1
   *
   * @author elonlo
   * @date 2023/11/30 22:19
   */
  public class DeadQueueConsumerOne {
  
      /**
       * 普通队列
       */
      public static final String NORMAL_QUEUE = "normal_queue";
  
      /**
       * 死信队列
       */
      public static final String DEAD_QUEUE = "dead_queue";
  
      /**
       * 普通交换机
       */
      public static final String NORMAL_EXCHANGE = "normal_exchange";
  
      /**
       * 死信交换机
       */
      public static final String DEAD_EXCHANGE = "dead_exchange";
  
      public static void main(String[] args) throws IOException {
          // 创建信道
          Channel channel = RabbitMQUtils.createChannel();
  
          // 声明普通交换机、死信交换机
          channel.exchangeDeclare(NORMAL_EXCHANGE, BuiltinExchangeType.DIRECT);
          channel.exchangeDeclare(DEAD_EXCHANGE, BuiltinExchangeType.DIRECT);
  
          // 声明普通队列、死信队列
          // 普通队列设置参数
          Map<String, Object> argMap = new HashMap<>(16);
          // 正常队列设置死信交换机
          argMap.put("x-dead-letter-exchange", DEAD_EXCHANGE);
          // 设置死信RoutingKey
          argMap.put("x-dead-letter-routing-key", "lucy");
          channel.queueDeclare(NORMAL_QUEUE, false, false, false, argMap);
          channel.queueDeclare(DEAD_QUEUE, false, false, false, null);
  
          // 普通队列、死信队列绑定对应的交换机
          channel.queueBind(NORMAL_QUEUE, NORMAL_EXCHANGE, "jack");
          channel.queueBind(DEAD_QUEUE, DEAD_EXCHANGE, "lucy");
          System.out.println("等待接收消息......");
  
          // 普通队列接收消息 设置手动确认
          channel.basicConsume(NORMAL_QUEUE, false, (consumerTag, message) -> {
              String msg = new String(message.getBody(), StandardCharsets.UTF_8);
              if (msg.equalsIgnoreCase("info7")) {
                  // 将info7消息放入死信队列  requeue:是否重新放回原队列
                  System.out.println("C1接收到的消息: " + msg + " 此消息被拒绝消费");
                  channel.basicReject(message.getEnvelope().getDeliveryTag(), false);
              } else {
                  System.out.println("C1接收到的消息: " + msg);
                  // 手动确认
                  channel.basicAck(message.getEnvelope().getDeliveryTag(), false);
              }
          }, consumerTag -> {
          });
      }
  }
  ```

- 生产者

  ```java
  /**
   * 死信队列-生产者
   *
   * @author elonlo
   * @date 2023/11/30 22:47
   */
  public class DeadQueueProducer {
  
      /**
       * 普通交换机
       */
      public static final String NORMAL_EXCHANGE = "normal_exchange";
  
      public static void main(String[] args) throws IOException {
          Channel channel = RabbitMQUtils.createChannel();
  
          // 发送消息
          for (int i = 1; i < 11; i++) {
              String message = "info" + i;
              channel.basicPublish(NORMAL_EXCHANGE, "jack", null, message.getBytes(StandardCharsets.UTF_8));
              System.out.println("生产者发送消息: " + message);
          }
      }
  }
  ```

## 7.延迟队列

### 7.1.概念

延迟队列，队列内部是**有序**的，最重要的特性体现在它的**延时属性**上，延迟队列中的元素是希望在指定时间到了以后或之前取出和处理，简单来说，延迟队列就是用来存放**需要在指定时间内被处理的元素**的队列。

### 7.2.延迟队列使用场景

1. 订单在十分钟之内未支付则自动取消
2. 新创建的店铺，如果在十天内都没有上传过商品，则自动发送消息提醒
3. 用户注册成功后，如果三天内没有登陆则进行短信提醒
4. 用户发起退款，如果三天内没有得到处理则通知相关运营人员
5. 预定会议后，需要在预定的时间点前十分钟通知各个参会人员参加会议

上述这些场景为什么要使用队列呢？使用轮询一样可以达到这种效果，但是在大数据量的情况下，比如在活动的时候，可能短时间内会产生百万级的消息，这时候使用轮询来处理就显得力不从心了，而且时效性无法保证，对数据库的压力也比较大。

### 7.3.整合 SpringBoot

#### 7.3.1.添加依赖

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <groupId>com.yu.rabbit</groupId>
    <artifactId>spring-boot-rabbitmq</artifactId>
    <version>0.0.1-SNAPSHOT</version>
    <name>spring-boot-rabbitmq</name>
    <description>Demo project for Spring Boot</description>
    <properties>
        <java.version>1.8</java.version>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
        <project.reporting.outputEncoding>UTF-8</project.reporting.outputEncoding>
        <spring-boot.version>2.3.12.RELEASE</spring-boot.version>
        <fastjson.version>1.2.83</fastjson.version>
        <knife4j.version>3.0.3</knife4j.version>
    </properties>
    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter</artifactId>
        </dependency>

        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>

        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-amqp</artifactId>
        </dependency>

        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>

        <dependency>
            <groupId>com.alibaba</groupId>
            <artifactId>fastjson</artifactId>
            <version>${fastjson.version}</version>
        </dependency>

        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <scope>provided</scope>
        </dependency>

        <dependency>
            <groupId>com.github.xiaoymin</groupId>
            <artifactId>knife4j-spring-boot-starter</artifactId>
            <version>${knife4j.version}</version>
        </dependency>

        <dependency>
            <groupId>org.springframework.amqp</groupId>
            <artifactId>spring-rabbit-test</artifactId>
            <scope>test</scope>
        </dependency>
    </dependencies>
    <dependencyManagement>
        <dependencies>
            <dependency>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-dependencies</artifactId>
                <version>${spring-boot.version}</version>
                <type>pom</type>
                <scope>import</scope>
            </dependency>
        </dependencies>
    </dependencyManagement>

    <build>
        <plugins>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-compiler-plugin</artifactId>
                <version>3.8.1</version>
                <configuration>
                    <source>1.8</source>
                    <target>1.8</target>
                    <encoding>UTF-8</encoding>
                </configuration>
            </plugin>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
                <version>${spring-boot.version}</version>
                <configuration>
                    <mainClass>com.yu.rabbit.RabbitmqApplication</mainClass>
                    <skip>true</skip>
                </configuration>
                <executions>
                    <execution>
                        <id>repackage</id>
                        <goals>
                            <goal>repackage</goal>
                        </goals>
                    </execution>
                </executions>
            </plugin>
        </plugins>
    </build>

</project>
```

#### 7.3.2.修改配置文件

```yaml
server:
  port: 9020
  servlet:
    context-path: /rabbit

# rabbitmq
spring:
  rabbitmq:
    host: xxx
    port: xxx
    username: admin
    password: admin
```

#### 7.3.3.添加 swagger 配置

```java
/**
 * Swagger配置类
 */
@EnableSwagger2
@Configuration
public class SwaggerConfig {

    @Bean
    public Docket webApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("webApi")
                .apiInfo(webApiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.yu.rabbit.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo webApiInfo() {
        return new ApiInfoBuilder()
                .title("rabbitmq开发文档")
                .description("本文档描述了rabbitmq服务接口定义")
                .version("1.0")
                .licenseUrl("http://localhost:9020/rabbit/doc.html")
                .contact(new Contact("elonlo", "http://localhost:9020", ""))
                .build();
    }
}
```

### 7.4. 队列 TTL

#### 7.4.1.延迟队列案例

创建两个队列 `cat` 和 `dog`，两个队列 TTL 分别设置为 10s 和 20s，然后再创建一个交换机 `home` 和死信交换机 `zoom`，它们的类型都是 direct，创建一个死信队列 `wolf`，它们的绑定关系如下：

![image-20231202232926160](https://image.elonlo.top/img/2023/12/02/656b4d5f16756.png)

#### 7.4.2.配置文件类

```java
/**
 * ttl队列配置文件
 *
 * @author elonlo
 * @date 2023/12/2 23:33
 */
@Configuration
public class TtlQueueConfig {

    // 普通交换机
    public static final String HOME_EXCHANGE = "home";

    // 死信交换机
    public static final String DEAD_LETTER_EXCHANGE = "zoom";

    // 普通队列
    public static final String CAT_QUEUE = "cat";
    public static final String DOG_QUEUE = "dog";

    // 死信队列
    public static final String DEAD_LETTER_QUEUE = "wolf";

    /**
     * 声明普通交换机
     */
    @Bean("homeExchange")
    public DirectExchange homeExchange() {
        return new DirectExchange(HOME_EXCHANGE);
    }

    /**
     * 声明死信交换机
     */
    @Bean("zoomExchange")
    public DirectExchange zoomExchange() {
        return new DirectExchange(DEAD_LETTER_EXCHANGE);
    }

    /**
     * 声明普通队列 ttl 10s
     */
    @Bean("cat")
    public Queue cat() {
        Map<String, Object> map = new HashMap<>(4);
        // 设置死信交换机
        map.put("x-dead-letter-exchange", DEAD_LETTER_EXCHANGE);
        // 设置死信routingKey
        map.put("x-dead-letter-routing-key", "food");
        // 设置过期时间
        map.put("x-message-ttl", 10000);
        return QueueBuilder.durable(CAT_QUEUE).withArguments(map).build();
    }

    /**
     * 声明普通队列 ttl 20s
     */
    @Bean("dog")
    public Queue dog() {
        Map<String, Object> map = new HashMap<>(4);
        // 设置死信交换机
        map.put("x-dead-letter-exchange", DEAD_LETTER_EXCHANGE);
        // 设置死信routingKey
        map.put("x-dead-letter-routing-key", "food");
        // 设置过期时间
        map.put("x-message-ttl", 20000);
        return QueueBuilder.durable(DOG_QUEUE).withArguments(map).build();
    }

    /**
     * 声明死信队列
     */
    @Bean("wolf")
    public Queue wolf() {
        return QueueBuilder.durable(DEAD_LETTER_QUEUE).build();
    }

    /**
     * cat队列绑定home交换机
     */
    @Bean
    public Binding catQueueBindingHomeExchange(@Qualifier("cat") Queue cat, @Qualifier("homeExchange") DirectExchange homeExchange) {
        return BindingBuilder.bind(cat).to(homeExchange).with("fish");
    }

    /**
     * dog队列绑定home交换机
     */
    @Bean
    public Binding dogQueueBindingZoomExchange(@Qualifier("dog") Queue dog, @Qualifier("homeExchange") DirectExchange homeExchange) {
        return BindingBuilder.bind(dog).to(homeExchange).with("bitterness");
    }

    /**
     * wolf队列绑定zoom交换机
     */
    @Bean
    public Binding wolfQueueBindingZoomExchange(@Qualifier("wolf") Queue wolf, @Qualifier("zoomExchange") DirectExchange zoomExchange) {
        return BindingBuilder.bind(wolf).to(zoomExchange).with("food");
    }
}
```

#### 7.4.3.生产者（TTL）

```java
/**
 * 消息控制器
 *
 * @author elonlo
 * @date 2023/12/3 0:12
 */
@Slf4j
@RestController
@RequestMapping("/ttl")
public class MessageController {

    @Resource
    private RabbitTemplate rabbitTemplate;

    @GetMapping("/sendMsg/{message}")
    public String sendMsg(@PathVariable("message") String message) {
        log.info("当前时间为: {}, 发送一条消息给两个TTL队列,消息内容为: {}", LocalDateTime.now(), message);
        rabbitTemplate.convertAndSend("home", "fish", "消息来自ttl为10s的队列: " + message);
        rabbitTemplate.convertAndSend("home", "bitterness", "消息来自ttl为20s的队列: " + message);
        return "发送成功!";
    }
}
```

#### 7.4.4.消费者（TTL）

```java
/**
 * 死信队列消费者
 *
 * @author elonlo
 * @date 2023/12/3 0:19
 */
@Slf4j
@Component
public class DeadQueueConsumer {

    /**
     * 接收消息
     */
    @RabbitListener(queues = "wolf")
    public void receiveMsg(Message message, Channel channel) {
        log.info("当前时间为: {}, 死信队列收到了消息: {}", LocalDateTime.now(), new String(message.getBody(), StandardCharsets.UTF_8));
    }
}
```

### 7.5.延时队列优化

#### 7.5.1.优化方法

在前面的案例中，我们在声明队列时就设置了死信过期时间，这样有个最大的坏处，就是如果要重新修改过期时间，需要重新声明队列。于是我们可以声明一个普通队列，在生产者发送消息的时候指定过期时间，这样就做到了通用性。

#### 7.5.2.配置文件类

```java
/**
 * ttl队列配置文件
 *
 * @author elonlo
 * @date 2023/12/2 23:33
 */
@Configuration
public class TtlQueueConfig {

    // 普通交换机
    public static final String HOME_EXCHANGE = "home";

    // 死信交换机
    public static final String DEAD_LETTER_EXCHANGE = "zoom";

    // 普通队列
    public static final String ANIMAL_QUEUE = "animal";

    // 死信队列
    public static final String DEAD_LETTER_QUEUE = "wolf";

    /**
     * 声明普通交换机
     */
    @Bean("homeExchange")
    public DirectExchange homeExchange() {
        return new DirectExchange(HOME_EXCHANGE);
    }

    /**
     * 声明死信交换机
     */
    @Bean("zoomExchange")
    public DirectExchange zoomExchange() {
        return new DirectExchange(DEAD_LETTER_EXCHANGE);
    }

    /**
     * 声明通用队列 不设置ttl
     */
    @Bean("animal")
    public Queue animal() {
        Map<String, Object> map = new HashMap<>(4);
        // 设置死信交换机
        map.put("x-dead-letter-exchange", DEAD_LETTER_EXCHANGE);
        // 设置死信routingKey
        map.put("x-dead-letter-routing-key", "food");
        return QueueBuilder.durable(ANIMAL_QUEUE).withArguments(map).build();
    }

    /**
     * 声明死信队列
     */
    @Bean("wolf")
    public Queue wolf() {
        return QueueBuilder.durable(DEAD_LETTER_QUEUE).build();
    }

    /**
     * animal队列绑定home交换机
     */
    @Bean
    public Binding animalQueueBindingZoomExchange(@Qualifier("animal") Queue animal, @Qualifier("homeExchange") DirectExchange homeExchange) {
        return BindingBuilder.bind(animal).to(homeExchange).with("eat");
    }
}
```

#### 7.5.3.生产者

```java
@Slf4j
@RestController
@RequestMapping("/ttl")
public class MessageController {

    @Resource
    private RabbitTemplate rabbitTemplate;

    @GetMapping("/sendExMsg/{ttlTime}/{message}")
    public String sendTtlMsg(@PathVariable("ttlTime") String ttlTime, @PathVariable("message") String message) {
        log.info("当前时间为: {}, 发送一条消息耗时{}的队列,消息内容为: {}", LocalDateTime.now(), ttlTime, message);
        rabbitTemplate.convertAndSend("home", "eat", message, msg -> {
            msg.getMessageProperties().setExpiration(ttlTime);
            return msg;
        });
        return "发送成功!";
    }
}
```

看起来是没有什么问题，但是如果在消息属性上设置 TTL，消息可能并不会按时`过期`，因为 RabbitMQ 只会**检查第一个消息是否过期**，如果过期则丢到死信队列，当第一个消息的**延时时间很长**，而第二个消息的**延时时间很短**，第二个消息将不会**优先得到执行**。

### 7.6.RabbitMQ 插件实现延迟队列

#### 7.6.1.安装延时队列插件

1. 官网地址https://www.rabbitmq.com/community-plugins.html下载延迟队列插件

   ![image-20231203202026459](https://image.elonlo.top/img/2023/12/03/656c72919bdd4.png)

2. 上传到容器中插件目录

   ![image-20231203202237348](https://image.elonlo.top/img/2023/12/03/656c730d4a56e.png)

   ```bash
   docker cp rabbitmq_delayed_message_exchange-3.9.0.ez yu_rabbitmq:/plugins
   ```

   ![image-20231203202505361](https://image.elonlo.top/img/2023/12/03/656c73a137932.png)

3. 启用插件

   在容器中`plugins`目录下执行以下命令启用插件：

   ```bash
   rabbitmq-plugins enable rabbitmq_delayed_message_exchange
   ```

   ![image-20231203203042074](https://image.elonlo.top/img/2023/12/03/656c74f296134.png)

4. 重启容器

#### 7.6.2.插件延迟队列案例

![image-20231203210636119](https://image.elonlo.top/img/2023/12/03/656c7d5c3e74d.png)

#### 7.6.3.配置文件类

在我们自定义的交换机中，这是一种新的交换机类型，该类型消息支持延迟投递机制，消息传递后并**不会立即投递到目标队列**中，而是**存储在 mnesia（一个分布式数据系统）表**中，当**达到投递时间**时，才**投递到目标队列**中。

```java
/**
 * 延迟队列配置类
 *
 * @author elonlo
 * @date 2023/12/3 21:44
 */
@Configuration
public class DelayedQueueConfig {

    /**
     * 延迟队列
     */
    public static final String DELAYED_QUEUE_NAME = "delayed.queue";

    /**
     * 延迟队列交换机
     */
    public static final String DELAYED_EXCHANGE_NAME = "delayed.exchange";

    /**
     * 延迟队列routingKey
     */
    public static final String DELAYED_ROUTING_KEY = "delayed.routingkey";

    /**
     * 声明延迟队列
     */
    @Bean
    public Queue delayedQueue() {
        return QueueBuilder.durable(DELAYED_QUEUE_NAME).build();
    }

    /**
     * 声明延迟交换机
     * name         交换机名称
     * type         交换机类型
     * durable      是否持久化
     * autoDelete   是否自动删除
     * arguments    其他参数
     */
    @Bean
    public CustomExchange delayedExchange() {
        Map<String, Object> map = new HashMap<>(4);
        map.put("x-delayed-type", "direct");
        return new CustomExchange(DELAYED_EXCHANGE_NAME, "x-delayed-message", true, false, map);
    }

    /**
     * 绑定延迟队列与交换机
     */
    @Bean
    public Binding delayedQueueBindingdelayedExchange(@Qualifier("delayedQueue") Queue delayedQueue,
                                                      @Qualifier("delayedExchange") CustomExchange delayedExchange) {
        return BindingBuilder.bind(delayedQueue).to(delayedExchange).with(DELAYED_ROUTING_KEY).noargs();
    }
}
```

#### 7.6.4.生产者（Delayed）

```java
@GetMapping("/sendDelayMsg/{delayTime}/{message}")
public String sendDelayMsg(@PathVariable("delayTime") Integer delayTime, @PathVariable("message") String message) {
    log.info("当前时间为: {}, 发送一条消息耗时{}ms的延迟队列,消息内容为: {}", LocalDateTime.now(), delayTime, message);
    rabbitTemplate.convertAndSend(DelayedQueueConfig.DELAYED_EXCHANGE_NAME, DelayedQueueConfig.DELAYED_ROUTING_KEY, message, msg -> {
        // 设置消息延迟时间
        msg.getMessageProperties().setDelay(delayTime);
        return msg;
    });
    return "发送成功!";
}
```

#### 7.6.5.消费者（Delayed）

```java
/**
 * 基于插件的延迟队列-消费者
 *
 * @author elonlo
 * @date 2023/12/3 22:09
 */
@Slf4j
@Component
public class DelayedQueueConsumer {

    /**
     * 接收延迟队列消息
     */
    @RabbitListener(queues = DelayedQueueConfig.DELAYED_QUEUE_NAME)
    public void receiveDelayedQueueMessage(Message message) {
        log.info("当前时间为: {}, 延迟队列收到了消息: {}", LocalDateTime.now(), new String(message.getBody(), StandardCharsets.UTF_8));
    }
}
```

![image-20231203221541264](https://image.elonlo.top/img/2023/12/03/656c8d8d7e3d8.png)

### 7.7.总结

延迟队列在需要延时处理的场景下非常有用，使用 RabbiMQ 来实现延迟队列可以很好的利用 RabbitMQ 的特性。如消息可靠发送、可靠传递、死信队列来保障消息至少被消费一次以及未被正确处理的消息不会被丢弃。另外，通过 RabbitMQ 集群的特性，可以很好的解决单点故障问题，不会因为单个节点挂掉导致延迟队列不可用或消息丢失。

延迟队列也有其他选择。比如利用 Java 的 DelayedQueue、Redis 的 zset，Quartz，或者 Kafka 的时间轮，这些方式各有各的特点，适用于不同的场景。

## 8.发布确认高级

在生产环境中由于一些不明原因，导致 RabbitMQ 重启，在 RabbitMQ 重启期间生产者投递消息失败，导致消息丢失，需要手动处理和恢复。那么如何才能进行 RabbitMQ 的消息可靠投递呢？特别是在这种比较极端的情况，RabbitMQ 集群不可用的时候，无法投递的消息该如何处理呢？

### 8.1.确认机制

#### 8.1.1.流程图

![image-20231204220707237](https://image.elonlo.top/img/2023/12/04/656ddd135fc96.png)

#### 8.1.2.配置文件

发布确认类型有三种模式，`NONE`、`CORRELATED`、`SIMPLE`，默认是`NONE`模式，我们需要将其设置为`CORRELATED`。

```yaml
spring:
  rabbitmq:
  	# 发布确认类型-生产者确认
    publisher-confirm-type: correlated
```

- NONE

  禁用发布确认模式，默认值

- CORRELATED

  发布消息成功到交换机后会触发回调方法

- SIMPLE

  简单模式，同步机制

#### 8.1.3.添加配置类

```java
/**
 * 发布确认配置类
 *
 * @author elonlo
 * @date 2023/12/4 22:42
 */
@Configuration
public class ConfirmConfig {

    /**
     * 队列
     */
    public static final String CONFIRM_QUEUE = "confirm.queue";

    /**
     * 交换机
     */
    public static final String CONFIRM_EXCHANGE = "confirm.exchange";

    /**
     * routingKey
     */
    public static final String CONFIRM_ROUTING_KEY = "confirm.routingkey";

    /**
     * 声明队列
     */
    @Bean("confirmQueue")
    public Queue confirmQueue() {
        return QueueBuilder.durable(CONFIRM_QUEUE).build();
    }

    /**
     * 声明交换机
     */
    @Bean("confirmExchange")
    public DirectExchange confirmExchange() {
        return new DirectExchange(CONFIRM_EXCHANGE);
    }

    /**
     * 队列绑定交换机
     */
    @Bean
    public Binding confirmQueueBindingConfirmExchange(@Qualifier("confirmQueue") Queue confirmQueue,
                                                      @Qualifier("confirmExchange") DirectExchange confirmExchange) {
        return BindingBuilder.bind(confirmQueue).to(confirmExchange).with(CONFIRM_ROUTING_KEY);
    }
}
```

#### 8.1.4.消息生产者

```java
/**
 * 发布确认-生产者
 *
 * @author elonlo
 * @date 2023/12/4 22:48
 */
@Slf4j
@RestController
@RequestMapping("/confirm")
public class ConfirmController {

    @Resource
    private RabbitTemplate rabbitTemplate;

    @GetMapping("/sendMsg/{message}")
    public String sendMsg(@PathVariable String message) {
        rabbitTemplate.convertAndSend(ConfirmConfig.CONFIRM_EXCHANGE, ConfirmConfig.CONFIRM_ROUTING_KEY, message);
        log.info("生产者发送消息为: {}", message);
        return "发送成功!";
    }
}
```

#### 8.1.5.回调接口

```java
/**
 * 发布确认-确认回调
 *
 * @author elonlo
 * @date 2023/12/4 23:14
 */
@Slf4j
@Component
public class ConfirmCallBack implements RabbitTemplate.ConfirmCallback {
    
    @Resource
    private RabbitTemplate rabbitTemplate;

    @PostConstruct
    public void init() {
        rabbitTemplate.setConfirmCallback(this);
    }

    /**
     * 交换机确认回调方法
     *
     * @param correlationData 保存回调消息的ID及相关信息
     * @param ack             交换机是否收到消息 true-是 false-否
     * @param cause           未收到消息原因
     */
    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        String id = Optional.ofNullable(correlationData).map(CorrelationData::getId).orElse("");
        if (ack) {
            log.info("已收到消息id为{}的消息", id);
        } else {
            log.info("未收到消息id为{}的消息,原因如下: {}", id, cause);
        }
    }
}
```

#### 8.1.6.消息消费者

```java
/**
 * 发布确认-消费者
 *
 * @author elonlo
 * @date 2023/12/4 22:51
 */
@Slf4j
@Component
public class ConfirmConsumer {

    @RabbitListener(queues = ConfirmConfig.CONFIRM_QUEUE)
    public void receiveConfirmMessage(Message message) {
        log.info("接收到confirm.queue队列的消息: {}", new String(message.getBody(), StandardCharsets.UTF_8));
    }
}
```

#### 8.1.7.交换机确认

- 确认成功

  发送一条正确配置的消息，携带`CorrelationData`参数

  ```java
  /**
   * 发布确认控制器
   *
   * @author elonlo
   * @date 2023/12/4 22:48
   */
  @Slf4j
  @RestController
  @RequestMapping("/confirm")
  public class ConfirmController {
  
      @Resource
      private RabbitTemplate rabbitTemplate;
  
      @GetMapping("/sendMsg/{message}")
      public String sendMsg(@PathVariable String message) {
          // 设置回调id
          CorrelationData correlationData = new CorrelationData();
          correlationData.setId("zs");
          rabbitTemplate.convertAndSend(ConfirmConfig.CONFIRM_EXCHANGE, ConfirmConfig.CONFIRM_ROUTING_KEY, message, correlationData);
          log.info("生产者发送消息为: {}", message);
          return "发送成功!";
      }
  }
  ```

  ![image-20231206095734175](https://image.elonlo.top/img/2023/12/06/656fd5177cb79.png)

- 确认失败

  - 交换机不存在

    模拟发送一条失败的消息，修改为错误的交换机名称，携带`CorrelationData`参数

    ```java
    /**
     * 发布确认控制器
     *
     * @author elonlo
     * @date 2023/12/4 22:48
     */
    @Slf4j
    @RestController
    @RequestMapping("/confirm")
    public class ConfirmController {
    
        @Resource
        private RabbitTemplate rabbitTemplate;
    
        @GetMapping("/sendMsg/{message}")
        public String sendMsg(@PathVariable String message) {
            // 设置回调id
            CorrelationData correlationData = new CorrelationData();
            correlationData.setId("zs");
            rabbitTemplate.convertAndSend(ConfirmConfig.CONFIRM_EXCHANGE + "abcd", ConfirmConfig.CONFIRM_ROUTING_KEY, message, correlationData);
            log.info("生产者发送消息为: {}", message);
            return "发送成功!";
        }
    }
    ```

    ![image-20231206100050243](https://image.elonlo.top/img/2023/12/06/656fd5d31ca15.png)

  - 路由键不存在

    模拟发送一条失败的消息，修改为错误的路由键名称，携带`CorrelationData`参数

    ![image-20231206101442906](https://image.elonlo.top/img/2023/12/06/656fd9138fb7a.png)

    可以看到由于**路由键**错误，导致**交换机与队列之间绑定失败**，消费者队列无法接收到生产者发送的消息，消息丢失了。

### 8.2.回退消息

#### 8.2.1.Mandatory 参数

在**仅开启了生产者确认机制**的情况下，交换机接收到消息后，会直接给消息生产者发送确认消息，如果发现**该消息不可路由**，那么**消息会被直接丢弃**，此时**生产者是不知道消息被丢弃**这个事件的。可以通过设置 `Mandatory` 参数，当**消息传递过程中不可达目的地时将消息返回给生产者**。

#### 8.2.2.配置文件

```yaml
spring:
  rabbitmq:
    # 发布确认类型-生产者确认
    publisher-confirm-type: correlated
    # 开启发布确认失败后回退消息机制
    publisher-returns: true
```

#### 8.2.3.消息生产者

```java
/**
 * 发布确认控制器
 *
 * @author elonlo
 * @date 2023/12/4 22:48
 */
@Slf4j
@RestController
@RequestMapping("/confirm")
public class ConfirmController {

    @Resource
    private RabbitTemplate rabbitTemplate;

    @GetMapping("/sendMsg/{message}")
    public String sendMsg(@PathVariable String message) {
        // 设置回调id
        CorrelationData correlationData = new CorrelationData();
        correlationData.setId("zs");
        rabbitTemplate.convertAndSend(ConfirmConfig.CONFIRM_EXCHANGE + "abcd", ConfirmConfig.CONFIRM_ROUTING_KEY, message, correlationData);
        log.info("生产者发送消息为: {}", message);
        return "发送成功!";
    }
}
```

#### 8.2.4.回调接口

```java
/**
 * 发布确认-确认回调
 *
 * @author elonlo
 * @date 2023/12/4 23:14
 */
@Slf4j
@Component
public class ConfirmCallBack implements RabbitTemplate.ConfirmCallback, RabbitTemplate.ReturnCallback {

    @Resource
    private RabbitTemplate rabbitTemplate;

    @PostConstruct
    public void init() {
        rabbitTemplate.setConfirmCallback(this);
        rabbitTemplate.setReturnCallback(this);
    }

    /**
     * 交换机确认回调方法
     *
     * @param correlationData 保存回调消息的ID及相关信息
     * @param ack             交换机是否收到消息 true-是 false-否
     * @param cause           未收到消息原因
     */
    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        String id = Optional.ofNullable(correlationData).map(CorrelationData::getId).orElse("");
        if (ack) {
            log.info("交换机已收到消息id为{}的消息", id);
        } else {
            log.info("交换机未收到消息id为{}的消息,原因如下: {}", id, cause);
        }
    }

    /**
     * 回退消息（只有当消息不可达时才进行回退）
     *
     * @param message    回退的消息
     * @param replyCode  回复code
     * @param replyText  回复内容
     * @param exchange   交换机
     * @param routingKey 路由键
     */
    @Override
    public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
        log.error("消息{}被交换机{}退回,退回原因: {},路由key: {}",
                new String(message.getBody(), StandardCharsets.UTF_8), exchange, replyText, routingKey);
    }
}
```

#### 8.2.5.消息回退

![image-20231206105248803](https://image.elonlo.top/img/2023/12/06/656fe201cc83d.png)

### 8.3.备份交换机

备份交换机可以理解为 RabbitMQ 中交换机的`备胎`，当我们为**某一个交换机声明一个对应的备份交换机**时，就是为它创建一个`备胎`，当交换机接收到一条不可路由的消息时，将会把这条消息转发到备份交换机中，由备份交换机来进行转发和处理，通常备份交换机的类型为 **Fanout** ，这样就能把所有消息都投递到与之绑定的队列中，在备份交换机中绑定队列。还可以创建一个报警队列，用独立的消费者来进行监测和报警。 

#### 8.3.1.流程图

![image-20231210181101235](https://image.elonlo.top/img/2023/12/10/65758ebc9a8b9.png)

#### 8.3.2.修改配置类

```java
@Configuration
public class ConfirmConfig {

    /**
     * 队列
     */
    public static final String CONFIRM_QUEUE = "confirm.queue";

    /**
     * 交换机
     */
    public static final String CONFIRM_EXCHANGE = "confirm.exchange";

    /**
     * routingKey
     */
    public static final String CONFIRM_ROUTING_KEY = "confirm.routingkey";

    /**
     * 备份交换机
     */
    public static final String BACKUP_EXCHANGE = "backup.exchange";

    /**
     * 备份队列
     */
    public static final String BACKUP_QUEUE = "backup.queue";

    /**
     * 告警队列
     */
    public static final String WARNING_QUEUE = "warning.queue";

    /**
     * 声明队列
     */
    @Bean("confirmQueue")
    public Queue confirmQueue() {
        return QueueBuilder.durable(CONFIRM_QUEUE).build();
    }

    /**
     * 声明备份队列
     */
    @Bean("backupQueue")
    public Queue backupQueue() {
        return QueueBuilder.durable(BACKUP_QUEUE).build();
    }

    /**
     * 声明告警队列
     */
    @Bean("warningQueue")
    public Queue warningQueue() {
        return QueueBuilder.durable(WARNING_QUEUE).build();
    }

    /**
     * 声明备份交换机
     */
    @Bean("backupExchange")
    public FanoutExchange backupExchange() {
        return new FanoutExchange(BACKUP_EXCHANGE);
    }

    /**
     * 声明交换机
     */
    @Bean("confirmExchange")
    public DirectExchange confirmExchange() {
        return ExchangeBuilder.directExchange(CONFIRM_EXCHANGE).durable(true)
                // 设置正常交换机绑定队列失败后使用备份交换机
                .withArgument("alternate-exchange", BACKUP_EXCHANGE).build();
    }

    /**
     * 队列绑定交换机
     */
    @Bean
    public Binding confirmQueueBindingConfirmExchange(@Qualifier("confirmQueue") Queue confirmQueue,
                                                      @Qualifier("confirmExchange") DirectExchange confirmExchange) {
        return BindingBuilder.bind(confirmQueue).to(confirmExchange).with(CONFIRM_ROUTING_KEY);
    }

    /**
     * 备份队列绑定备份交换机
     */
    @Bean
    public Binding backupQueueBindingBackupExchange(@Qualifier("backupQueue") Queue backupQueue,
                                                    @Qualifier("backupExchange") FanoutExchange backupExchange) {
        return BindingBuilder.bind(backupQueue).to(backupExchange);
    }

    /**
     * 告警队列绑定备份交换机
     */
    @Bean
    public Binding warningQueueBindingBackupExchange(@Qualifier("warningQueue") Queue warningQueue,
                                                     @Qualifier("backupExchange") FanoutExchange backupExchange) {
        return BindingBuilder.bind(warningQueue).to(backupExchange);
    }
}
```

#### 8.3.3.报警消费者

```java
@Slf4j
@Component
public class WarningConsumer {

    /** 
     * 接收报警消息
     */
    @RabbitListener(queues = ConfirmConfig.WARNING_QUEUE)
    public void receiveWarningMessage(Message message) {
        log.error("报警发现不可路由消息: {}", new String(message.getBody(), StandardCharsets.UTF_8));
    }
}
```

#### 8.3.4.发送消息

```java
@Slf4j
@RestController
@RequestMapping("/confirm")
public class ConfirmController {

    @Resource
    private RabbitTemplate rabbitTemplate;

    @GetMapping("/sendMsg/{message}")
    public String sendMsg(@PathVariable String message) {
        // 设置回调id
        CorrelationData correlationData1 = new CorrelationData();
        correlationData1.setId("zs");
        rabbitTemplate.convertAndSend(ConfirmConfig.CONFIRM_EXCHANGE, ConfirmConfig.CONFIRM_ROUTING_KEY, message, correlationData1);
        log.info("生产者1发送消息为: {}", message);

        CorrelationData correlationData2 = new CorrelationData();
        correlationData2.setId("zs");
        rabbitTemplate.convertAndSend(ConfirmConfig.CONFIRM_EXCHANGE, ConfirmConfig.CONFIRM_ROUTING_KEY + "abcd", message, correlationData2);
        log.info("生产者2发送消息为: {}", message);
        return "发送成功!";
    }
}
```

![image-20231211143246996](https://image.elonlo.top/img/2023/12/11/6576ad170d52f.png)

#### 8.3.5.总结

当设置了**消息回退**和**备份交换机**一起使用的时候，如果两者同时开启，**备份交换机的优先级高**。

## 9.RabbitMQ 高级特性

### 9.1.幂等性

#### 9.1.1.概念

用户对于**同一操作**发起的**一次请求或者多次请求**的**结果是一致**的，**不会因为多次点击而产生了不同的结果**。例如支付，用户购买商品后支付，支付扣款成功，但是返回结果的时候由于网络异常，此时钱已经扣了，用户再次点击支付按钮，此时会进行第二次扣款，返回结果成功，用户查询余额发现多扣钱了，流水记录也变成了两条。在以前的单应用系统中，我们只需要将数据操作放入事务中即可，发生错误立即回滚，但是在响应客户端的时候也可能出现网络中断或者异常等情况。

#### 9.1.2.消息重复消费

消费者在消费 `MQ` 中的消息时，`MQ` 已经把消息发送给消费者，消费者在给 `MQ` 返回 `ack` 时网络中断，故 `MQ` 未收到确认消息，该条消息会重新发给其他的消费者，或者在网络重连后再次发送给该消费者，但实际上该消费者已经成功消费了该条消息，造成消费者消费了重复的消息。

#### 9.1.3.解决方案

在海量订单生成的业务高峰期，生产端就有可能重复消费了消息，这时候消费端要实现幂等性，保证我们的消息永远不会被消费多次，即使收到了一样的消息。

`MQ` 消费者的幂等性解决一般使用 **全局 ID 或者唯一标识**，比如**时间戳或者 UUID**，或者订单消费者消费 MQ 中的消息也可利用 `MQ` 中的id来判断。也可按照自己的规则生成一个全局唯一ID，每次消费消息时用该 id 来判断该消息是否已经消费过。

业界主流的幂等性解决方案有两种：a：唯一 ID +指纹码机制，利用数据库主键去重；b：利用 Redis 的原子性实现

- 唯一ID + 指纹码机制

  指纹码：我们的一些业务规则或者时间戳加别的服务给的唯一信息码，不一定是系统生成的，一般都是由**业务规则拼接**而来，但是**一定要保证唯一性**，然后通过**查询判断这个 id 是否存在数据库中**，优势是实现简单，查询判断是否重复，劣势是在高并发时，如果是单个数据库就会有**写入性能瓶颈**，也可以采用分库分表提升性能，但不是最推荐的方式。

- Redis原子性

  利用 redis 执行 `setnx` 命令，天生具有幂等性。从而实现不重复消费。

### 9.2.优先级队列

#### 9.2.1.使用场景

例如一个**订单催付**的场景，客户在天猫下订单后，淘宝会及时将订单推送给商家，如果用户在设定的时间内未付款就会给用户推送一条短信提醒，但是对于淘宝来说，商家也是要区分大客户和小客户的，比如像苹果、小米等大商家会创造很大的利润，所以理所当然他们的订单需要优先处理，订单量大了后采用 `RabbitMQ` 进行改造和优化，如果是大客户的订单给一个相对来说比较高的优先级，否则就是默认优先级。

#### 9.2.2.优先级队列配置

- 队列添加优先级

  ```java
  // 设置队列优先级 默认0-255,建议设置为10,表示优先级范围为0-10
  Map<String, Object> arguments = new HashMap<>(4);
  arguments.put("x-max-priority", 10);
  channel.queueDeclare(QUEUE_NAME, false, false, false, arguments);
  ```

- 生产者设置发送消息优先级属性

  ```java
  String message = "test" + i;
  // 设置发送消息优先级属性
  AMQP.BasicProperties props = new AMQP.BasicProperties()
          .builder().priority(5).build();
  channel.basicPublish("", QUEUE_NAME, props, message.getBytes(StandardCharsets.UTF_8));
  ```

#### 9.2.3.代码示例

- 生产者

  ```java
  /**
   * 优先级队列-生产者
   *
   * @author elonlo
   * @date 2023/12/11 21:27
   */
  public class PriorityProducer {
  
      /**
       * 队列名称
       */
      public static final String QUEUE_NAME = "hello";
  
      public static void main(String[] args) throws IOException, TimeoutException {
  
          ConnectionFactory factory = new ConnectionFactory();
          factory.setHost("124.220.32.153");
          factory.setPort(25673);
          factory.setUsername("admin");
          factory.setPassword("admin");
  
          Connection connection = factory.newConnection();
          Channel channel = connection.createChannel();
  
          // 声明队列
          // 设置队列优先级 默认0-255,建议设置为10,表示优先级范围为0-10
          Map<String, Object> arguments = new HashMap<>(4);
          arguments.put("x-max-priority", 10);
          channel.queueDeclare(QUEUE_NAME, false, false, false, arguments);
  
          // 发送消息
          for (int i = 1; i < 11; i++) {
              String message = "test" + i;
              // 设置发送消息优先级属性
              AMQP.BasicProperties props = new AMQP.BasicProperties()
                      .builder().priority(5).build();
              if (i == 5) {
                  channel.basicPublish("", QUEUE_NAME, props, message.getBytes(StandardCharsets.UTF_8));
              } else {
                  channel.basicPublish("", QUEUE_NAME, null, message.getBytes(StandardCharsets.UTF_8));
              }
  
          }
          System.out.println("消息发送完毕!");
      }
  }
  ```

- 消费者

  ```java
  /**
   * 优先级队列-消费者
   *
   * @author elonlo
   * @date 2023/12/11 21:42
   */
  public class PriorityConsumer {
  
      public static final String QUEUE_NAME = "hello";
  
      public static void main(String[] args) throws IOException, TimeoutException {
  
          ConnectionFactory factory = new ConnectionFactory();
          factory.setHost("124.220.32.153");
          factory.setPort(25673);
          factory.setUsername("admin");
          factory.setPassword("admin");
  
          Connection connection = factory.newConnection();
          Channel channel = connection.createChannel();
  
          // 接收消息
          channel.basicConsume(QUEUE_NAME, true, (tag, message) -> {
              System.out.println(new String(message.getBody(), StandardCharsets.UTF_8));
          }, tag -> {});
      }
  }
  ```

<span style="color:red">注意：队列需要设置优先级，生产者发送消息设置消息的优先级，而且要先发送消息，然后消费者才去消费，这样才有机会对消息进行排序，实现优先级。</span>

### 9.3.惰性队列

#### 9.3.1.使用场景

`RabbitMQ` 从 3.6.0 版本开始引入了惰性队列的概念。惰性队列会**尽可能的将消息存入磁盘中**，而在**消费者消费到相应的消息**时才会**被加载到内存中**，它的一个重要的设计目标是能够**支持更长的队列**，即**支持更多的消息存储**。当消费者由于各种原因（比如消费者下线、宕机亦或者是由于维护而关闭等）而导致长时间不能消费消息造成消息堆积时，惰性队列就很有必要了。

默认情况下，当生产者将消息发送到 `RabbitMQ` 的时候，队列中的消息会**尽可能的存储在内存之中**，这样可以更快的将消息发送给消费者。即使是持久化的消息，在**被写入磁盘的同时也会在内存中驻留一份备份**。当 `RabbitMQ` 需要释放内存的时候，会**将内存中的消息写入磁盘中**，这个操作会耗费较长的时间，也会**阻塞队列**的操作，进而无法接收新的消息。虽然 `RabbitMQ` 的开发者们一直升级相关的算法，但是效果始终不太理想，尤其是消息量特别大的时候。

#### 9.3.2.两种模式

惰性队列具备两种模式：`default` 和 `lazy`。默认为 `default` 模式，在 3.6.0 之前的版本无需做任何变更。`lazy` 模式即为惰性队列的模式，可以通过调用 `channel.queueDeclare` 方法的时候在参数中设置，也可以通过 `Policy` 的方式设置，如果一个队列同时使用这两种方式设置的话，那么 `Policy` 的方式具有更高的优先级。如果要通过声明的方式改变已有队列的模式，那么只能先删除原有队列，然后再重新声明一个新的队列。

在队列声明的时候可以通过 `x-queue-mode` 参数来设置队列的模式，取值为 `default` 和 `lazy`。下面是一个简单的示例：

```java
Map<String, Object> args = new HashMap<String, Object>();
// 设置惰性队列
args.put("x-queue-mode", "lazy");
channel.queueDeclare("myqueue", false, false, false, args);
```

#### 9.3.3.内存开销对比

![image-20231211221935287](https://image.elonlo.top/img/2023/12/11/65771a7fc6382.png)

在发送 1000000 条消息，每条消息大概占 1KB 的情况下，普通队列占用内存是 1.2GB，而惰性队列仅仅占用 1.5MB

## 10.RabbitMQ 集群

RabbitMQ集群模式有两种：普通模式和镜像模式

- 普通模式：**默认模式**，多个节点组成的普通集群，消息**随机发送到其中一个节点**的队列上，其他节点仅保留元数据，各个节点**仅有相同的元数据**，即队列结构、交换器结构、交换器与队列绑定关系、vhost。消费者消费消息时，会从各个节点拉取消息，如果**保存消息的节点故障**，**则无法消费消息**，如果做了消息持久化，那么得等该节点恢复，然后才可被消费；如果没有持久化的话，就会产生消息丢失的现象。
- 镜像模式：它是**在普通模式的基础上**，把**需要的队列做成镜像队列**，**存在于多个节点来实现高可用(HA)**。该模式解决了上述问题，`Broker` 会**主动地将消息实体在各镜像节点间同步**，在 `consumer` 取数据时无需临时拉取。该模式带来的副作用也很明显，除了降低系统性能外，如果镜像队列数量过多，加之大量的消息进入，集群内部的网络带宽将会被大量消耗。通常地，**对可靠性要求较高的场景建议采用镜像模式**。

### 10.1.普通集群

#### 10.1.1.架构图

![image-20231212144026382](https://image.elonlo.top/img/2023/12/12/6578006339de8.png)

#### 10.1.2.环境准备

- 创建日志目录及设置权限

  ```shell
  mkdir master/logs/ && chmod 777 master/logs/
  mkdir slave1/logs/ && chmod 777 slave1/logs/
  mkdir slave2/logs/ && chmod 777 slave2/logs/
  ```

- 配置文件

  ```yaml
  version: '3'
  services:
    master:
      image: rabbitmq:3.9-management
      container_name: master-rabbitmq
      hostname: master-node
      restart: unless-stopped
      environment:
        - RABBITMQ_DEFAULT_USER=admin
        - RABBITMQ_DEFAULT_PASS=admin
        - RABBITMQ_ERLANG_COOKIE=rabbitmqCookie
      volumes:
        - $PWD/master/data:/var/lib/rabbitmq #数据文件挂载
        - $PWD/master/logs:/var/log/rabbitmq #日志文件挂载
      ports:
        - 25671:5672
        - 35671:15672
      networks:
        - top
  
    slave1:
      image: rabbitmq:3.9-management
      container_name: slave-rabbitmq-1
      hostname: slave-node-1
      restart: unless-stopped
      environment:
        - RABBITMQ_DEFAULT_USER=admin
        - RABBITMQ_DEFAULT_PASS=admin
        - RABBITMQ_ERLANG_COOKIE=rabbitmqCookie
      volumes:
        - $PWD/slave1/data:/var/lib/rabbitmq #数据文件挂载
        - $PWD/slave1/logs:/var/log/rabbitmq #日志文件挂载
      ports:
        - 25672:5672
        - 35672:15672
      links:
        - master:master-node
      networks:
        - top
  
    slave2:
      image: rabbitmq:3.9-management
      container_name: slave-rabbitmq-2
      hostname: slave-node-2
      restart: unless-stopped
      environment:
        - RABBITMQ_DEFAULT_USER=admin
        - RABBITMQ_DEFAULT_PASS=admin
        - RABBITMQ_ERLANG_COOKIE=rabbitmqCookie
      volumes:
        - $PWD/slave2/data:/var/lib/rabbitmq #数据文件挂载
        - $PWD/slave2/logs:/var/log/rabbitmq #日志文件挂载
      ports:
        - 25673:5672
        - 35673:15672
      links:
        - master:master-node
        - slave1:slave-node-1
      networks:
        - top
  
  networks:
         top: {}
  ```

- 创建容器

  ```shell
  docker-compose up -d
  ```

- 管理界面

  ![image-20231212153032468](https://image.elonlo.top/img/2023/12/12/65780c18e2fcc.png)

  ![image-20231212153104479](https://image.elonlo.top/img/2023/12/12/65780c37d7e8c.png)

  ![image-20231212153133691](https://image.elonlo.top/img/2023/12/12/65780c5609b20.png)

#### 10.1.3.集群配置和搭建

- slave-rabbitmq-1容器配置

  ```shell
  docker exec -it slave-rabbitmq-1 bash
  ```

  ```shell
  # 终止RabbitMQ服务,但是Erlang节点还在运行
  rabbitmqctl stop_app
  
  # 表示设置RabbitMQ节点为原始状态。会从该节点所属的cluster中都删除,从管理数据库中删除所有数据,例如配置的用户和vhost,还会删除所有的持久消息,前提是RabbitMQ应用需要处于停止状态,即执行过 stop_app
  rabbitmqctl reset
  
  # 表示结合到指定的集群,如果有参数 --ram 表示作为RAM节点结合到该集群中
  rabbitmqctl join_cluster --ram rabbit@master-node
  
  # 表示启动RabbitMQ服务
  rabbitmqctl start_app
  ```

- slave-rabbitmq-2容器配置

  ```shell
  docker exec -it slave-rabbitmq-2 bash
  ```

  ```shell
  rabbitmqctl stop_app
  
  rabbitmqctl reset
  
  rabbitmqctl join_cluster --ram rabbit@slave-node-1
  
  rabbitmqctl start_app
  ```

- 查看集群状态

  ![image-20231212172742255](https://image.elonlo.top/img/2023/12/12/6578278f27f8f.png)

  ![image-20231212172810742](https://image.elonlo.top/img/2023/12/12/657827aa32d78.png)

### 10.2.镜像集群

#### 10.2.1.为什么使用镜像集群

如果 `RabbitMQ` 集群中只有一个 `Broker` 节点，那么**该节点的失效将导致整体服务的临时不可用**，而且也**可能导致消息的丢失**。因此可以将所有的消息都设置为持久化，并且对应队列的 `durable` 属性也设置为 `true`，但是这样仍然无法避免由于缓存导致的问题。因为**消息在发送和被写入磁盘并执行刷盘动作之间存在一个短暂却会产生问题的时间窗**。通过 `Publisher/Confirm` 机制能够确保客户端知道哪些消息已经存入磁盘，尽管如此，一般不希望遇到因单点故障导致的服务不可用情况。

引入镜像队列（Mirror Queue）的机制，可以**将队列镜像到集群中的其他 `Broker` 节点**上，如果集群中的一个节点失效了，**队列能自动地切换到镜像中的另一个节点上**以保证服务的可用性。

#### 10.2.2.镜像队列参数

队列可**通过策略启用镜像**功能。策略可以随时更改；可以创建一个非镜像队列，然后在某个时间点将其镜像化（反之亦然）。非镜像队列与没有任何镜像的镜像队列是有区别的，前者缺少额外的镜像队列功能，可能会提供更高的吞吐量。

为队列添加镜像会增加群集负载，但有助于降低丢失所有最新副本的概率。

要使普通队列成为镜像队列，可创建一个与之匹配的策略，并设置策略键 `ha-mode` 和（可选参数）`ha-params`。具体参数请参数下表：

| ha-mode（备份模式） | ha-params（备份参数） | ha-sync-mode（备份同步模式） | 含义                                                         |
| ------------------- | --------------------- | ---------------------------- | ------------------------------------------------------------ |
| exactly             | count                 | automatic                    | 集群中的队列副本（领导者加镜像）数量。数值为 1 表示只有一个副本：即队列领导者。<br/>如果运行队列领导者的节点不可用，其行为取决于队列的耐用性。<br/>计数值为 2 表示 2 个副本： 1 个队列领导和 1 个队列镜像。<br/>换句话说 `队列镜像数 = 节点数 - 1`。如果运行队列领导的节点不可用，<br/>队列镜像将根据配置的镜像晋升策略自动晋升为领导。如果群集中的节点数少于 count，<br/>队列将被镜像到所有节点。如果群集中的节点数多于计数，且包含镜像的节点宕机，<br/>则会在另一个节点上创建新的镜像。使用 `exactly `模式和 `ha-promote-on-shutdown`： `always`使用 `exactly`模式可能会很危险，因为队列可能会在集群中迁移，<br/>并在集群关闭时变得不同步。 |
| all                 | (none)                | manual                       | 队列在集群中的所有节点上进行镜像。当集群中添加新节点时，队列将被镜像到该节点。<br/>这种设置非常保守。建议镜像到一个指定集群节点（N/2 + 1） |
| nodes               | node names            |                              | 队列镜像到节点名中列出的节点。节点名称是出现在 `rabbitmqctl cluster_status` 中<br/>的 `Erlang` 节点名称；它们的形式通常是 `rabbit@hostname`。如果这些节点名中有<br/>任何一个不是集群的一部分，这并不构成错误。如果在声明队列时，列表中的节点<br/>都不在线，那么队列将在声明客户端所连接的节点上创建。 |

#### 10.2.3.镜像队列配置示例

| rabbitmqctl           | `rabbitmqctl set_policy ha-two "^two\." \  '{"ha-mode":"exactly","ha-params":2,"ha-sync-mode":"automatic"}' ` |
| :-------------------- | ------------------------------------------------------------ |
| rabbitmqctl (Windows) | `rabbitmqctl.bat set_policy ha-two "^two\." ^   "{""ha-mode"":""exactly"",""ha-params"":2,""ha-sync-mode"":""automatic""}" ` |
| HTTP API              | `PUT /api/policies/%2f/ha-two {  "pattern":"^two\.",  "definition": {    "ha-mode":"exactly",    "ha-params":2,    "ha-sync-mode":"automatic"  } } ` |
| Web UI                | Navigate to Admin > Policies > Add / update a policy.Enter "ha-two" next to Name and "^two\." next to Pattern.Enter "ha-mode" = "exactly" in the first line next to Policy, then "ha-params" = 2 in the second line, then "ha-sync-mode" = "automatic" in the third, and set the type on the second line to "Number".Click Add policy. |

![image-20231212224239753](https://image.elonlo.top/img/2023/12/12/65787166d325e.png)

### 10.3.高可用
