## 一、概述

### 1.1 官网

项目地址：https://github.com/apache/kafka

官方文档地址：https://kafka.apache.org/documentation

### 1.1 定义

传统定义：`Kafka` 是一个**分布式**的基于**发布/订阅**模型的**消息队列**（Message queue），主要应用于**大数据实时处理**领域。

最新定义：`Kafka` 是一个开源的**分布式事件流平台**（Event Streaming Platform），被数千家公司用于高性能**数据管道**、**流分析**、**数据集成**和**关键任务应用**。

### 1.2 消息队列

目前企业中比较常见的消息队列产品主要有：Kafka、ActiveMQ、RabbitMQ、RocketMQ等。

在大数据场景中主要采用 `Kafka` 作为消息队列。在 `Java EE` 开发中主要采用 ActiveMQ、RabbitMQ、RocketMQ。

#### 1.2.1 JMS

![image-20240629110948980](https://image.elonlo.top/img/2024/06/29/667f7afe3f4f7.png)

#### 1.2.2 组件

![image-20240629145245024](https://image.elonlo.top/img/2024/06/29/667ffac6d9525.png)

#### 1.2.3 传统消息队列的应用场景

传统的消息队列的主要应用场景包括：**缓存/削峰**、**解耦**和**异步通信**。

- **缓存/削峰**：有助于**控制**和优化**数据流经过系统**的**速度**，解决生产消息和消费消息的处理速度不一致的情况。
- **解耦**：允许进行独立的扩展或修改两边的处理过程，只要确保他们遵循同样的接口约束。
- **异步通信**：允许将消息放入队列，但不用立即处理它，而是在需要它们的时候再去处理消息。

#### 1.2.4 消息队列的两种模式

1. 点对点模式

   - 消费者主动拉取数据，消息收到后清除消息，消息**不能重复消费**。

   ![image-20240629143258966](https://image.elonlo.top/img/2024/06/29/667faa9c6274e.png)

2. 发布/订阅模式

   - 可以有多个 **topic 主题**（浏览、点赞、收藏等）
   - 消费者消费数据后不删除数据
   - 每个消费者相互独立，都可以消费消息，消息可**重复被消费**
   
   ![image-20240629112827041](https://image.elonlo.top/img/2024/06/29/667f7f5c6ef15.png)

### 1.3 消息中间件对比

| 特性                    | ActiveMQ                            | RabbitMQ                            | RocketMQ                                 | Kafka                                                   |
| ----------------------- | ----------------------------------- | ----------------------------------- | ---------------------------------------- | ------------------------------------------------------- |
| 单机吞吐量              | 万级，比RocketMQ，Kafka低一个数量级 | 万级，比RocketMQ，Kafka低一个数量级 | 10万级，支持高吞吐                       | 10万级，支持高吞吐                                      |
| Topic数量对吞吐量的影响 |                                     |                                     | Topic可以达到几百/几千量级               | Topic可以达到几百量级，如果更多的话，吞吐量会大幅度降低 |
| 时效性                  | ms级                                | 微秒级别，延迟最低                  | ms级                                     | ms级                                                    |
| 可用性                  | 高，基于主从架构实现高可用          | 高，基于主从架构实现高可用          | 非常高，分布式架构                       | 非常高，分布式架构                                      |
| 消息可靠性              | 有较低的概率丢失数据                | 基本不丢                            | 经过参数优化配置，可以做到0丢失          | 经过参数优化配置，可以做到0丢失                         |
| 功能支持                | MQ领域的功能极其完备                | 并发能力强，性能极好，延时很低      | MQ领域的功能较为完善，分布式，扩展性好   | 功能较为简单，支持简单的MQ功能，在大数据领域被广泛使用  |
| 其他                    | 很早的软件，社区不是很活跃          | 开源，稳定，社区活跃度高            | 阿里开源，已捐献给apache，活跃度不是很高 | 开源，高吞吐量，社区活跃度极高                          |

通过上述对比可以得知，在大数据场景中主要使用 Kafka 作为消息中间件，而在 Java EE 开发中主要采用 ActiveMQ、RabbitMQ、RocketMQ 作为消息中间件。如果将 Java EE 和大数据在项目中进行融合，可以选择 Kafka

### 1.4 基础架构

为了数据可靠性，可以将数据文件进行备份，但是 `Kafka` 没有备份的概念，在 `Kafka` 中称之为**副本**，多个副本同时只能有一个提供数据读写，具有读写能力的副本称之为 **Leader** 副本，作为备份的副本称之为 **follower** 副本

**总体架构**

![image-20240629202807751](https://image.elonlo.top/img/2024/06/29/667ffddddabff.png)

**业务组件**

![image-20240629212721127](https://image.elonlo.top/img/2024/06/29/66800bbc91609.png)

**Broker**：服务节点（集群）

**Partition**：分区（编号）

**副本**：Leader 和 Follower

## 二、快速入门

### 2.1 安装部署(Windows)

#### 2.1.1 安装 Kafka

1. 官网下载地址：https://kafka.apache.org/downloads.html
2. 解压安装包，修改解压后的文件名称

#### 2.1.2 启动 Zookeeper

1. 进入 `Kafka` 的 config 目录，修改 `zookeeper.properties` 配置文件

   ```properties
   # 在kafka根目录下创建data目录
   dataDir=D:/Home/kafka/data/zk
   ```

2. 进入到 `Kafka` 根目录，在此目录下打开 `DOS` 窗口，使用 `cd bin\windows` 命令切换到该目录下

3. 执行 `zookeeper-server-start.bat ../../config/zookeeper.properties` 命令启动 `Zookeeper`

   ![image-20240628220156644](https://image.elonlo.top/img/2024/06/28/667ec26061857.png)

4. 在 `Kafka` 根目录下创建 `Zookeeper` 快速启动脚本 `zk.cmd`

   ```bash
   # 调用启动命令，且同时指定配置文件
   call bin/windows/zookeeper-server-start.bat config/zookeeper.properties
   ```

#### 2.1.3 启动 Kafka

1. 进入 `Kafka` 的 config 目录，修改 `server.properties` 配置文件

   ```properties
   # 配置Kafka数据的存放位置，如果文件目录不存在，会自动生成
   log.dirs=D:/Home/kafka/data/kafka
   ```

2. 在 `Kafka` 根目录下创建 `kafka` 快速启动脚本 `kafka.cmd`

   ```bash
   call bin/windows/kafka-server-start.bat config/server.properties
   ```

   ![image-20240628223537397](https://image.elonlo.top/img/2024/06/28/667eca3c124c4.png)

注意：启动时应该先启动 Zookeeper 再启动 Kafka ，关闭时先关闭 Kafka 再关闭 Zookeeper。

### 2.2 消息主题

1. 创建主题

   在 `bin/windows` 目录下执行以下命令，创建一个 `test` 的主题

   ```bash
   kafka-topics.bat --bootstrap-server localhost:9092 --topic test --create
   ```

   ![image-20240628225226497](https://image.elonlo.top/img/2024/06/28/667ece2c9519a.png)

   注意：如创建后出现很多其他日志，使用其他 `jdk` 高版本即可解决

2. 查看主题

   ```bash
   kafka-topics.bat --bootstrap-server localhost:9092 --list
   ```

   ![image-20240628225648813](https://image.elonlo.top/img/2024/06/28/667ecf32726e8.png)

3. 查看主题详情

   ```bash
   kafka-topics.bat --bootstrap-server localhost:9092 --topic test --describe
   ```

   ![image-20240628225816616](https://image.elonlo.top/img/2024/06/28/667ecf8abd5fc.png)

4. 修改主题

   ```bash
   kafka-topics.bat --bootstrap-server localhost:9092 --topic testabc --alter --partitions 2
   ```

   ![image-20240628230028507](https://image.elonlo.top/img/2024/06/28/667ed00e6e40c.png)

5. 删除主题

   ```bash
   kafka-topics.bat --bootstrap-server localhost:9092 --topic testabc --delete
   ```

   ![image-20240628230355096](https://image.elonlo.top/img/2024/06/28/667ed0dcc52cd.png)

注意：在 `Windows` 环境下直接删除主题可能会导致 `Kafka` 异常，如出现这种情况，需要删除 `Kafka` 根目录下的 data 文件夹，然后重新创建主题就好了。

### 2.3 生产者和消费者

1. 命令行创建

   **消费者**

   在 `bin/windows` 目录下执行以下命令，启动一个 `test` 的主题的消费者

   ```bash
   kafka-console-consumer.bat --bootstrap-server localhost:9092 --topic test
   ```

   ![image-20240629094256342](https://image.elonlo.top/img/2024/06/29/667f66a1de0c6.png)

   **生产者**

   在 `bin/windows` 目录下执行以下命令，启动一个 `test` 的主题的生产者

   ```bash
   kafka-console-producer.bat --bootstrap-server localhost:9092 --topic test
   ```

   ![image-20240629094413225](https://image.elonlo.top/img/2024/06/29/667f66ee6e4c1.png)

   **生产者发送消息**

   ![image-20240629094548677](https://image.elonlo.top/img/2024/06/29/667f674dcb5d3.png)

   **消费者接收消息**

   ![image-20240629094610047](https://image.elonlo.top/img/2024/06/29/667f67632367b.png)

2. 程序创建

   1. 创建 `Kafka` 的 `maven` 项目，添加以下依赖信息

      ```xml
      <properties>
          <kafka.version>3.6.1</kafka.version>
      </properties>
      
      <dependencies>
          <dependency>
              <groupId>org.apache.kafka</groupId>
              <artifactId>kafka-clients</artifactId>
              <version>${kafka.version}</version>
          </dependency>
      </dependencies>
      ```

   2. 生产者代码

      ```java
      /**
       * Kafka生产者
       *
       * @author elonlo
       * @date 2024/6/29 15:52
       */
      public class KafkaProducerTest {
      
      	/**
      	 * kafka服务地址
      	 */
      	private static final String BOOTSTRAP_SERVER = "localhost:9092";
      
      	/**
      	 * kafka主题
      	 */
      	private static final String TOPIC = "test";
      
      	/**
      	 * kafka消息key
      	 */
      	private static final String MESSAGE_KEY = "key";
      
      	/**
      	 * kafka消息value
      	 */
      	private static final String MESSAGE_VALUE = "value";
      
      	public static void main(String[] args) {
      
      		// 1、创建配置对象
      		Map<String, Object> configMap = new HashMap<>(4);
      		configMap.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVER);
      
      		// 2、对生产者对象数据k,v序列化
      		configMap.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
      		configMap.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
      
      		// 3、创建生产者对象
      		KafkaProducer<String, String> producer = new KafkaProducer<>(configMap);
      
      		for (int i = 0; i < 10; i++) {
      			// 4、创建数据
      			// 构建数据时需要传递三个参数, 1: 主题 2: 消息key 3: 消息value
      			ProducerRecord<String, String> record = new ProducerRecord<>(
      					TOPIC, MESSAGE_KEY + i, MESSAGE_VALUE + i
      			);
      
      			// 5、发送数据
      			producer.send(record);
      		}
      
      		// 6、关闭生产者对象
      		producer.close();
      	}
      }
      ```

   3. 消费者代码

      ```java
      /**
       * Kafka生产者
       *
       * @author elonlo
       * @date 2024/6/29 15:52
       */
      public class KafkaProducerTest {
      
      	/**
      	 * kafka服务地址
      	 */
      	private static final String BOOTSTRAP_SERVER = "localhost:9092";
      
      	/**
      	 * kafka主题
      	 */
      	private static final String TOPIC = "test";
      
      	/**
      	 * kafka消息key
      	 */
      	private static final String MESSAGE_KEY = "key";
      
      	/**
      	 * kafka消息value
      	 */
      	private static final String MESSAGE_VALUE = "value";
      
      	public static void main(String[] args) {
      
      		// 1、创建配置对象
      		Map<String, Object> configMap = new HashMap<>(4);
      		configMap.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVER);
      
      		// 2、对生产者对象数据k,v序列化
      		configMap.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
      		configMap.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
      
      		// 3、创建生产者对象
      		KafkaProducer<String, String> producer = new KafkaProducer<>(configMap);
      
      		for (int i = 0; i < 10; i++) {
      			// 4、创建数据
      			// 构建数据时需要传递三个参数, 1: 主题 2: 消息key 3: 消息value
      			ProducerRecord<String, String> record = new ProducerRecord<>(
      					TOPIC, MESSAGE_KEY + i, MESSAGE_VALUE + i
      			);
      
      			// 5、发送数据
      			producer.send(record);
      		}
      
      		// 6、关闭生产者对象
      		producer.close();
      	}
      }
      ```

## 三、集群部署(Windows)

### 3.1 配置 Kafka

1. 在 `Home` 目录下创建 `cluster` 目录

2. 将 `Kafka` 压缩包解压至 `cluster` 目录下

3. 将解压后的文件复制三份，分别命名为 `kafka-node-1`、 `kafka-node-2`、 `kafka-node-3`

4. 分别修改 `server.properties` 配置文件为以下内容

   ```properties
   # 当有多个broker时,需要配置此参数,且broker.id应是唯一的,不可重复
   broker.id=1
   
   ############################# Socket Server Settings #############################
   # 当有多个broker时,多个broker端口号不能相同
   listeners=PLAINTEXT://:9091
   
   ############################# Log Basics #############################
   
   # A comma separated list of directories under which to store log files
   log.dirs=D:/Home/cluster/kafka-node-1/data
   ```

   ```properties
   broker.id=2
   
   ############################# Socket Server Settings #############################
   listeners=PLAINTEXT://:9092
   
   ############################# Log Basics #############################
   
   # A comma separated list of directories under which to store log files
   log.dirs=D:/Home/cluster/kafka-node-2/data
   ```

   ```properties
   broker.id=3
   
   ############################# Socket Server Settings #############################
   listeners=PLAINTEXT://:9093
   
   ############################# Log Basics #############################
   
   # A comma separated list of directories under which to store log files
   log.dirs=D:/Home/cluster/kafka-node-3/data
   ```

### 3.2 配置 zookeeper

1. 将 Kafka 压缩包解压至 `cluster` 目录下，命名为 `kafka-zookeeper`

2. 修改 `zookeeper.properties` 配置为以下内容

   ```properties
   dataDir=D:/Home/cluster/kafka-zookeeper/data
   ```

### 3.4 集群配置

1. 在 `kafka-zookeeper` 目录下添加以下内容创建 `zookeeper` 启动脚本 `zk.cmd`

   ```bash
   call bin/windows/zookeeper-server-start.bat config/zookeeper.properties
   ```

2. 分别在 `kafka-node-1、kafka-node-2、kafka-node-3` 目录下添加以下内容创建 `kafka` 启动脚本 `kafka.cmd`

   ```bash
   call bin/windows/kafka-server-start.bat config/server.properties
   ```

3. 在 `cluster` 目录下添加以下内容创建集群启动脚本 `cluster.cmd`

   ```bash
   cd kafka-zookeeper
   start zk.cmd
   ping 127.0.0.1 -n 10 > nul
   cd ../kafka-node-1
   start kafka.cmd
   cd ../kafka-node-2
   start kafka.cmd
   cd ../kafka-node-3
   start kafka.cmd
   ```

4. 在 `cluster` 目录下添加以下内容创建集群清理脚本 `cluster-clear.cmd`，用于清理和重置 `Kafka` 数据

   ```bash
   cd kafka-zookeeper
   rd /s /q data
   ping 127.0.0.1 -n 10 > nul
   cd ../kafka-node-1
   rd /s /q data
   cd ../kafka-node-2
   rd /s /q data
   cd ../kafka-node-3
   rd /s /q data
   ```

注意：如启动时提示命令行过长，将 `cluster` 目录移动到电脑磁盘根目录下，然后修改 `zookeeper`、`Kafka` 中配置文件 `log` 文件存放地址即可。如在集群启动过程中报错，可以执行完集群清理脚本然后再重新启动。



