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

## 四、Zookeeper

### 4.1 基础架构

![image-20240630091156889](https://image.elonlo.top/img/2024/06/30/6680b0e82ed97.png)

### 4.2 核心功能

![image-20240630092659544](https://image.elonlo.top/img/2024/06/30/6680b4664badf.png)

### 4.3 选举流程

1. 多个节点各自注册 **Broker** 节点，添加到 `/brokers/ids` 中
2. Kafka 监听器监听 **controller** 节点
3. 默认按节点启动顺序将第一个启动的节点注册并选举为 **controller** 节点，后续节点注册时将无法选举为 **controller** 节点
4. **controller** 节点启动监听器监听 `/brokers/ids` 中各节点的情况
5. 新增 `broker` 节点时会通知 **controller** 节点集群的变化
6.  **controller** 节点连接新增的 `broker` 节点，发送集群的相关数据
7. 当 **controller** 节点被删除时，由于监听器的存在，`Kafka` 会通知所有 `broker` 节点删除的情况
8. 所有broker节点会重新去争抢注册为 `controller` 节点，此时正常情况下第二个注册的 `broker`  节点将被选举为 **controller** 节点
9. 最新选举为 **controller** 节点启动监听器监听 `/brokers/ids` 中各节点的情况
10.  **controller** 节点连接所有的 `broker`，发送集群的相关数据

## 五、主题

### 5.1 创建主题

```java
/**
 * Kafka主题
 *
 * @author elonlo
 * @date 2024/6/30 15:54
 */
public class KafkaAdminTopicTest {

	/**
	 * kafka服务地址
	 */
	private static final String BOOTSTRAP_SERVER = "localhost:9092";

	/**
	 * 主题一
	 */
	private static final String topic_one = "test1";

	/**
	 * 主题二
	 */
	private static final String topic_two = "test2";

	public static void main(String[] args) {

		// 1、创建管理员对象
		Map<String, Object> configMap = new HashMap<>(4);
		configMap.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVER);
		final Admin admin = Admin.create(configMap);

		// 2、构建主题对象
		// 构建主题需要传递三个参数
		//  第一个参数表示主题的名称: 字母,数字,点,下划线,中横线
		//  第二个参数表示主题分区的数量: int
		//  第三个参数表示主题分区副本的（因子）数量: short
		int partitionCount = 1;
		short replicationCount = 1;
		NewTopic topic1 = new NewTopic(topic_one, partitionCount, replicationCount);

		partitionCount = 2;
		replicationCount = 2;
		NewTopic topic2 = new NewTopic(topic_two, partitionCount, replicationCount);

		String topicName = "test3";
		Map<Integer, List<Integer>> map = new HashMap<>(4);
		// 表示当前节点的第几个分区在哪个节点上有leader副本和follower副本,比如map.put(0, Arrays.asList(3, 1))
		// 表示在test3主题的第一个分区在node-3节点(即Kafka启动的服务)上面有leader副本,在node-1上面有follower副本,并且数据为test3-0
		map.put(0, Arrays.asList(3, 1));
		// node-2: test3-1, node-3: test3-1
		map.put(1, Arrays.asList(2, 3));
		// node-1: test3-2, node-2: test3-2
		map.put(2, Arrays.asList(1, 2));
		NewTopic topic3 = new NewTopic(topicName, map);

		// 3、创建主题
		CreateTopicsResult topics = admin.createTopics(Arrays.asList(topic1, topic2, topic3));
        
        // In - Sync - Replicas:    同步副本列表(ISR)

		// 4、关闭管理员对象
		admin.close();
	}
}
```

### 5.2 主题分区副本策略

`Kafka` 默认创建的主题分区副本策略可能不是最优的。下面是一个简单的副本分区判断方法案例：

**test1 主题创建一个分区，一个副本**

```tex
Topic: test1    TopicId: 9uFWtvdATSSWTmp_IfT6eQ PartitionCount: 1       ReplicationFactor: 1    Configs:
        Topic: test1    Partition: 0    Leader: 3       Replicas: 3     Isr: 3
```

表示 `test1` 主题的第一个分区在 `node-3` 节点（即Kafka启动的服务）上面只有一个 `leader副本`

**test2 主题创建两个分区，两个副本**

```tex
Topic: test2    TopicId: gSPHd1iuQS-3QgZpug22yg PartitionCount: 2       ReplicationFactor: 2    Configs:
        Topic: test2    Partition: 0    Leader: 3       Replicas: 3,1   Isr: 3,1
        Topic: test2    Partition: 1    Leader: 1       Replicas: 1,2   Isr: 1,2
```

表示 `test2` 主题的第一个分区在 `node-3` 节点（即Kafka启动的服务）上面有一个 `leader副本`，在 `node-1` 节点上有一个 `follower副本`

`test2` 主题的第二个分区在 `node-1` 节点（即Kafka启动的服务）上面有一个 `leader副本`，在 `node-2` 节点上有一个 `follower副本`

## 六、生产者

### 6.1 自定义拦截器

1. 编写自定义拦截器

   ```java
   /**
    * 自定义拦截器
    *
    *  1.实现ProducerInterceptor接口
    *  2.定义泛型
    *  3.重写方法
    *
    * @author elonlo
    * @date 2024/6/30 18:07
    */
   public class CustomProducerInterceptor implements ProducerInterceptor<String, String> {
   
   	// 发送生产者数据时会调用此方法
   	@Override
   	public ProducerRecord<String, String> onSend(ProducerRecord<String, String> record) {
   		return new ProducerRecord<>(record.topic(), record.key(), record.value() + "china");
   	}
   
   	// 发送数据完毕,服务器返回的响应会调用此方法
   	@Override
   	public void onAcknowledgement(RecordMetadata metadata, Exception exception) {
   
   	}
   
   	// 生产者对象关闭的时候,会调用此方法
   	@Override
   	public void close() {
   
   	}
   
   	// 创建生产者对象的时候调用
   	@Override
   	public void configure(Map<String, ?> configs) {
   
   	}
   }
   ```

2. 配置自定义拦截器

   ```java
   // 1、创建配置对象
   Map<String, Object> configMap = new HashMap<>(4);
   configMap.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVER);
   
   // 2、对生产者对象数据k,v序列化
   configMap.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
   configMap.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
   configMap.put(ProducerConfig.INTERCEPTOR_CLASSES_CONFIG, CustomProducerInterceptor.class.getName());
   
   // 3、创建生产者对象
   KafkaProducer<String, String> producer = new KafkaProducer<>(configMap);
   ```

### 6.2 自定义分区策略

1. 编写自定义分区策略

   ```java
   /**
    * 自定义分区策略
    *
    * @author elonlo
    * @date 2024/6/30 19:32
    */
   public class CustomKafkaPartitioner implements Partitioner {
   
   	// 重写分区策略
   	@Override
   	public int partition(String topic, Object key, byte[] keyBytes, Object value, byte[] valueBytes, Cluster cluster) {
   		return 1;
   	}
   
   	@Override
   	public void close() {
   
   	}
   
   	@Override
   	public void configure(Map<String, ?> configs) {
   
   	}
   }
   ```

2. 配置自定义分区策略

   ```java
   // 1、创建配置对象
   Map<String, Object> configMap = new HashMap<>(4);
   configMap.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVER);
   
   // 2、对生产者对象数据k,v序列化
   configMap.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
   configMap.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
   configMap.put(ProducerConfig.PARTITIONER_CLASS_CONFIG, CustomKafkaPartitioner.class.getName());
   
   // 3、创建生产者对象
   KafkaProducer<String, String> producer = new KafkaProducer<>(configMap);
   ```

注意：默认情况下不需要配置分区策略，`Kafka` 内部有自己的分区策略，`Kafka` 底层不会校验分区是否存在，所以自定义分区时如果计算出来的**分区编号不存在**将导致生产者一直**阻塞**。

### 6.3 同步/异步发送数据

1. 异步（默认）

   ```java
   // 3、创建生产者对象
   KafkaProducer<String, String> producer = new KafkaProducer<>(configMap);
   
   for (int i = 0; i < 10; i++) {
       // 4、创建数据
       // 构建数据时需要传递三个参数, 1: 主题 2: 消息key 3: 消息value
       ProducerRecord<String, String> record = new ProducerRecord<>(
               TOPIC, MESSAGE_KEY + i, MESSAGE_VALUE + i
       );
   
       // 5、发送数据   异步
       producer.send(record, (metadata, exception) -> System.out.println("发送数据: " + metadata));
       System.out.println("发送数据");
   }
   ```

   ```tex
   发送数据
   发送数据
   发送数据
   发送数据
   发送数据
   发送数据
   发送数据
   发送数据
   发送数据
   发送数据
   发送数据: test2-1@41
   发送数据: test2-1@42
   发送数据: test2-1@43
   发送数据: test2-1@44
   发送数据: test2-1@45
   发送数据: test2-1@46
   发送数据: test2-1@47
   发送数据: test2-1@48
   发送数据: test2-1@49
   发送数据: test2-1@50
   ```

2. 同步

   ```java
   // 3、创建生产者对象
   KafkaProducer<String, String> producer = new KafkaProducer<>(configMap);
   
   for (int i = 0; i < 10; i++) {
       // 4、创建数据
       // 构建数据时需要传递三个参数, 1: 主题 2: 消息key 3: 消息value
       ProducerRecord<String, String> record = new ProducerRecord<>(
               TOPIC, MESSAGE_KEY + i, MESSAGE_VALUE + i
       );
   
       // 5、发送数据   同步
       Future<RecordMetadata> future = producer.send(record, (metadata, exception) -> System.out.println("发送数据: " + metadata));
       System.out.println("发送数据");
       try {
           future.get();
       } catch (InterruptedException | ExecutionException e) {
           e.printStackTrace();
       }
   }
   ```

   ```tex
   发送数据
   发送数据: test2-1@61
   发送数据
   发送数据: test2-1@62
   发送数据
   发送数据: test2-1@63
   发送数据
   发送数据: test2-1@64
   发送数据
   发送数据: test2-1@65
   发送数据
   发送数据: test2-1@66
   发送数据
   发送数据: test2-1@67
   发送数据
   发送数据: test2-1@68
   发送数据
   发送数据: test2-1@69
   发送数据
   发送数据: test2-1@70
   ```

### 6.4 消息接收应答处理

Kafka 的消息应答处理可以通过配置 `acks参数` 实现不同的处理效果。`acks参数` 有以下几种设置：

- acks=0：如果设置为零，生产者将完全不等待服务器的任何确认。记录将被**立即添加**到套接字**缓冲区**，并视为**已发送**。在这种情况下，**不能保证**服务器已收到记录，重试配置也**不会生效**（因为**客户端通常不会知道任何失败**）。
- acks=1：`leader` 会将记录写入其**本地日志**，但不会等待所有 `follower` 的**完整确认**就做出响应。在这种情况下，如果 `leader` 在确认记录后，但在 `follower` 复制记录之前立即发生故障，那么记录就会丢失。
- acks=all（默认）：这意味着 `leader`  将**等待全部同步复制**来确认记录。这保证了只要至少有一个同步副本还活着，记录就不会丢失。这是最强的可用保证。等同于 `acks=-1` 设置。

```java
// 1、创建配置对象
Map<String, Object> configMap = new HashMap<>(4);
configMap.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVER);

// 2、对生产者对象数据k,v序列化
configMap.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
configMap.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
configMap.put(ProducerConfig.ACKS_CONFIG, "0");

// 3、创建生产者对象
KafkaProducer<String, String> producer = new KafkaProducer<>(configMap);
```

### 6.5 重试机制

`Kafka` 在保证数据一致性的情况下引入了数据重试机制，但是引入之后又带来了另外一个问题，在某些情况下可能会导致**数据重复**和**数据乱序**问题。

**数据重复**

1. 消息应答处理设置为 acks=1
2. 数据发送给 Broker 中的 Leader，Leader 接收到数据之后将数据写入磁盘中
3. Leader 响应接收到数据的信息给 Producer，但是此时网络出现波动导致响应失败
4. Producer 未收到响应信息，认为未发送成功，于是又将数据从 Buffer 中重新发送
5. 重新发送之后就导致数据重复了

![image-20240702181223487](https://image.elonlo.top/img/2024/07/02/6683d28f8335b.png)

**数据乱序**

1. 当前缓冲区有三条数据 data1、data2、data3，`NetworkClient` 从缓冲区取到这三条数据
2. `NetworkClient` 将这三条数据发送给 `Broker`，由于生产者可以同时处理 5 个请求，所以三条数据可以同时被处理
3. 由于出现网络等相关问题，data2、data3 发送并响应成功，但是 data1 响应失败了
4. 于是 `NetworkClient` 重新发送 data1 数据，但是此时发送之后 data1 的数据就在 data2、data3之后了

![image-20240702183345878](https://image.elonlo.top/img/2024/07/02/6683d78bcdfd4.png)

### 6.6 幂等性

Kafka 引入了幂等生产者的概念，这是 Kafka 从 0.11.0 版本开始提供的一项特性。幂等生产者确保同一个消息即使被发送多次，最终也只会在目标 topic 中存储一次。

**关键机制**：

- **Producer ID（PID）**：每个生产者在初始化时被分配一个唯一的 PID。
- **序列号（Sequence Number）**：生产者在每个分区的每个消息上附加一个单调递增的序列号。

当 Kafka broker 接收到消息时，它会检查消息的 PID 和序列号。如果消息的序列号高于当前已记录的序列号，则消息会被写入日志；否则，消息会被视为重复并被忽略。

```java
// 1、创建配置对象
Map<String, Object> configMap = new HashMap<>(4);
configMap.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVER);

// 2、对生产者对象数据k,v序列化
configMap.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
configMap.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
// 使用默认配置
configMap.put(ProducerConfig.ACKS_CONFIG, "-1");
// 开启幂等性,默认为false
configMap.put(ProducerConfig.ENABLE_IDEMPOTENCE_CONFIG, true);
// 设置重试次数,默认为0,不重试
configMap.put(ProducerConfig.RETRIES_CONFIG, 5);
// 在途请求缓冲区,默认值5
configMap.put(ProducerConfig.BATCH_SIZE_CONFIG, 5);
// 设置超时时间,默认为30s
configMap.put(ProducerConfig.REQUEST_TIMEOUT_MS_CONFIG, 3000);
```

幂等性操作的要求如下：

- ACKS=-1
- 开启重试机制
- 在途请求缓冲区的数据不能大于5

注意：幂等性只能保证一个分区的幂等性，不同分区的幂等性无法保证

### 6.7 事务

为了确保消息的端到端幂等性，即在生产者、Kafka broker 和消费者之间的一致性，Kafka 提供了事务性消息特性。事务性消息确保生产者可以在一个事务中发送多个消息，要么全部成功，要么全部失败。

**关键机制：**

- **事务 ID（Transaction ID）**：生产者在初始化时指定一个唯一的事务 ID。
- **事务日志（Transaction Log）**：Kafka broker 记录事务的开始和结束状态。

```java
/**
 * Kafka事务
 *
 * @author elonlo
 * @date 2024/6/30 00:04
 */
public class KafkaProducerTransactionTest {

	/**
	 * kafka服务地址
	 */
	private static final String BOOTSTRAP_SERVER = "localhost:9092";

	/**
	 * kafka主题
	 */
	private static final String TOPIC = "test2";

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
		// 使用默认配置
		configMap.put(ProducerConfig.ACKS_CONFIG, "-1");
		// 开启幂等性,默认为false
		configMap.put(ProducerConfig.ENABLE_IDEMPOTENCE_CONFIG, true);
		// 设置重试次数,默认为0,不重试
		configMap.put(ProducerConfig.RETRIES_CONFIG, 5);
		// 在途请求缓冲区,默认值5
		configMap.put(ProducerConfig.BATCH_SIZE_CONFIG, 5);
		// 设置超时时间,默认为30s
		configMap.put(ProducerConfig.REQUEST_TIMEOUT_MS_CONFIG, 3000);
		// 设置事务id,必须开启幂等性
		configMap.put(ProducerConfig.TRANSACTIONAL_ID_CONFIG, "my-tx-id");

		// 3、创建生产者对象
		KafkaProducer<String, String> producer = new KafkaProducer<>(configMap);

		// 初始化事务
		producer.initTransactions();

		try {
			// 开启事务
			producer.beginTransaction();
			for (int i = 0; i < 20; i++) {
				// 4、创建数据
				// 构建数据时需要传递三个参数, 1: 主题 2: 消息key 3: 消息value
				ProducerRecord<String, String> record = new ProducerRecord<>(
						TOPIC, MESSAGE_KEY + i, MESSAGE_VALUE + i
				);

				// 5、发送数据
				Future<RecordMetadata> future = producer.send(record);
			}

			// 提交事务
			producer.commitTransaction();
		} catch (Exception e) {
			e.printStackTrace();
			// 中止事务
			producer.abortTransaction();
		} finally {
			// 6、关闭生产者对象
			producer.close();
		}
	}
}
```

事务流程如下：

![image-20240702231901802](https://image.elonlo.top/img/2024/07/02/66841a665338e.png)

## 七、数据存储

### 7.1 数据存储文件类型

Kafka 的分区目录下有三个类型的文件：

- **.log：**数据的日志文件，20位长度的数字字符串，数字的含义就是当前日志文件的起始偏移量
- **.index：**偏移量的索引文件，将偏移量与当前文件中的数据具体物理位置做一个关联，保存到索引文件中，可以按照偏移量的方式对数据在当前文件中进行定位
- **.timeindex：**时间索引文件，用于保存数据时间戳和数据偏移量的关系，便于按照时间的方式来读取数据

Kafka 中的数据是**存储在 .log 文件**中的，producer 将当前数据写入 leader 副本时，leader 副本并**不会立即将数据写入磁盘**中，而是先**将数据写入内存**中，由 **LogManager** **周期性的**将数据从内存刷写到磁盘中。

| 参数名                          | 含义                                                         |
| ------------------------------- | ------------------------------------------------------------ |
| log.flush.interval.messages     | 将信息刷新到磁盘之前，日志分区上累积的信息数量。默认值：9223372036854775807（long类型的最大值） |
| log.flush.interval.ms           | 任何主题中的消息在刷新到磁盘之前在内存中保留的最长时间（毫秒）。如果未设置，则使用 log.flush.scheduler.interval.ms 中的值。默认值：null |
| log.flush.scheduler.interval.ms | 日志刷新程序检查是否有日志需要刷新到磁盘的频率，以毫秒为单位，默认值：9223372036854775807（long类型的最大值） |
| log.segment.bytes               | 单个日志文件的最大大小。默认值：1GB，最小值必须大于14，单位比特 |
| log.roll.ms                     | 滚动新日志段之前的最长时间（毫秒）。如果未设置，则使用 log.roll.hours 中的值。默认值：null |
| log.roll.hours                  | 产生新日志段之前的最长时间（小时），次于 log.roll.ms 属性。默认值：168小时（7天） |
| log.index.interval.bytes        | 数据在写入文件的时候达到 4k 才会向 index 文件中写数据。默认值：4k |

日志文件写入磁盘配置案例如下：

- 修改 Kafka `server.properties` 文件中的配置

  ```properties
  # 将数据从内存刷写进磁盘的日志累积数量
  log.flush.interval.messages=1
  # 单个日志文件的最大大小
  log.segment.bytes=200
  # 日志滚动生成的最长时间
  log.roll.ms=5
  ```

- 配置多个批次，产生多个小文件

  ```java
  // 配置产生多个批次数据
  configMap.put(ProducerConfig.BATCH_SIZE_CONFIG, 2);
  ```

- 使用工具查看 .log 文件中的内容，在 Kafka 根目录下执行以下命令

  ```bash
  bin\windows\kafka-run-class.bat kafka.tools.DumpLogSegments --files D:/Home/kafka/data/kafka/test-0/00000000000000000000.log --print-data-log
  ```

  ```tex
  Dumping D:\Home\kafka\data\kafka\test-0\00000000000000000000.log
  Log starting offset: 0
  baseOffset: 0 lastOffset: 1 count: 2 baseSequence: 0 lastSequence: 1 producerId: 0 producerEpoch: 0 partitionLeaderEpoch: 0 isTransactional: false isControl: false deleteHorizonMs: OptionalLong.empty position: 0 CreateTime: 1720015067577 size: 95 magic: 2 compresscodec: none crc: 654334578 isvalid: true
  | offset: 0 CreateTime: 1720015067567 keySize: 4 valueSize: 6 sequence: 0 headerKeys: [] key: key0 payload: value0
  | offset: 1 CreateTime: 1720015067577 keySize: 4 valueSize: 6 sequence: 1 headerKeys: [] key: key1 payload: value1
  baseOffset: 2 lastOffset: 3 count: 2 baseSequence: 2 lastSequence: 3 producerId: 0 producerEpoch: 0 partitionLeaderEpoch: 0 isTransactional: false isControl: false deleteHorizonMs: OptionalLong.empty position: 95 CreateTime: 1720015067577 size: 95 magic: 2 compresscodec: none crc: 1945938213 isvalid: true
  | offset: 2 CreateTime: 1720015067577 keySize: 4 valueSize: 6 sequence: 2 headerKeys: [] key: key2 payload: value2
  | offset: 3 CreateTime: 1720015067577 keySize: 4 valueSize: 6 sequence: 3 headerKeys: [] key: key3 payload: value3
  ```

### 7.2 数据存储流程

1. Kafka 生产者发送生产数据的请求
2. Broker 通过 produce 标记后被 KafkaApis 组件的应用处理接口接收到
3. 应用处理接口将请求转发给 ReplicaManager（副本管理器），副本管理器会追加数据
4. 副本管理器将数据追加给 Partition 的 Leader 副本
5. Partition 将数据追加到 UnifiedLog 中，同时判断写入的数据文件是否重复
6. UnifiedLog 将数据追加到 LogSegment（包含.log、.index、.timeindex文件），然后写入到本地磁盘文件中

### 7.3 日志清理策略

Kafka 软件的本质是用于传输数据，而不是存储数据，但是为了均衡生产数据速率和消费者的消费速率，所以可以将数据保存到日志文件中进行存储。默认的数据日志保存时间为 7 天，可以通过调整如下参数修改保存时间：

- log.retention.hours：小时（默认值：7，最低优先级）
- log.retention.minutes：分钟
- log.retention.ms：毫秒（最高优先级）
- log.retention.check.interval.ms：检查周期（默认值：5 分钟）

日志一旦超过了设置的时间，Kafka 中提供了两种日志清理策略：delete 和 compact。

1. delete（将过期数据删除）

   - log.cleanup.policy ：默认值：delete，所有数据启用删除策略
     - 基于时间：默认打开。以 segment 中所有记录中的最大时间戳作为该文件时间戳
     - 基于大小：默认关闭。超过设置的所有日志总大小，删除最早的 segment。log.retentions.bytes，默认等于 -1，表示无穷大。

2. compact（日志压缩）

   基本思路就是将相同 key 的数据只保留最后一个。

   - log.cleanup.policy：compact，所有数据启用压缩策略。

## 八、消费者

### 8.1 自动提交偏移量

Kafka 消费数据是根据偏移量来消费的，如果 Kafka 中没有初始偏移量，或者服务器上不再存在当前偏移量（例如，因为数据已被删除），该怎么办？下面是 Kafka 几种常见的偏移量设置：

- **earliest：**自动将偏移量重置为最早的偏移量
- **latest：**默认值，自动将偏移量重置为最新偏移量
- **none：**如果没有找到消费者组的先前偏移量，则向消费者抛出异常
- **anything else：**向用户抛出异常。

**earliest**（从最早偏移量获取数据）

```java
// 设置偏移量,earliest: 最早,latest: 最新
configMap.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
```

```tex
ConsumerRecord(topic = test, partition = 0, leaderEpoch = 0, offset = 0, CreateTime = 1720183657629, serialized key size = 4, serialized value size = 6, headers = RecordHeaders(headers = [], isReadOnly = false), key = key0, value = value0)
ConsumerRecord(topic = test, partition = 0, leaderEpoch = 0, offset = 1, CreateTime = 1720183657638, serialized key size = 4, serialized value size = 6, headers = RecordHeaders(headers = [], isReadOnly = false), key = key1, value = value1)
ConsumerRecord(topic = test, partition = 0, leaderEpoch = 0, offset = 2, CreateTime = 1720183657638, serialized key size = 4, serialized value size = 6, headers = RecordHeaders(headers = [], isReadOnly = false), key = key2, value = value2)
```

**latest**（从最新偏移量获取数据）

```java
// 设置偏移量,earliest: 最早,latest: 最新
configMap.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "latest");
```

```tex
ConsumerRecord(topic = test, partition = 0, leaderEpoch = 0, offset = 3, CreateTime = 1720184668974, serialized key size = 4, serialized value size = 6, headers = RecordHeaders(headers = [], isReadOnly = false), key = key3, value = value3)
ConsumerRecord(topic = test, partition = 0, leaderEpoch = 0, offset = 4, CreateTime = 1720184668983, serialized key size = 4, serialized value size = 6, headers = RecordHeaders(headers = [], isReadOnly = false), key = key4, value = value4)
ConsumerRecord(topic = test, partition = 0, leaderEpoch = 0, offset = 5, CreateTime = 1720184668983, serialized key size = 4, serialized value size = 6, headers = RecordHeaders(headers = [], isReadOnly = false), key = key5, value = value5)
```

**从指定偏移量位置获取数据**

```java
// 1、消费者配置
Map<String, Object> configMap = new HashMap<>(4);
configMap.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVER);

// 2、消费者数据反序列化
configMap.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
configMap.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());

// 3、配置组
configMap.put(ConsumerConfig.GROUP_ID_CONFIG, GROUP);

// 4、创建消费者对象
KafkaConsumer<String, String> consumer = new KafkaConsumer<>(configMap);

// 5、订阅主题
consumer.subscribe(Collections.singletonList(TOPIC));

// 获取集群信息
boolean flag = true;
while (flag) {
    consumer.poll(Duration.ofMillis(100));
    // 获取当前消费主题的分区信息
    final Set<TopicPartition> assignment = consumer.assignment();
    if (null == assignment || assignment.isEmpty()) {
        continue;
    }
    for (TopicPartition topicPartition : assignment) {
        if (TOPIC.equals(topicPartition.topic())) {
            // 设置从指定偏移量位置消费数据
            consumer.seek(topicPartition, 3);
            flag = false;
        }
    }
}

while (true) {
    try {
        // 6、从主题中拉取数据
        final ConsumerRecords<String, String> datas = consumer.poll(Duration.ofMillis(100));
        for (ConsumerRecord<String, String> record : datas) {
            System.out.println(record);
        }
    } catch (Exception e) {
        e.printStackTrace();
        return;
    }
}
```

```tex
ConsumerRecord(topic = test, partition = 0, leaderEpoch = 0, offset = 3, CreateTime = 1720185017771, serialized key size = 4, serialized value size = 6, headers = RecordHeaders(headers = [], isReadOnly = false), key = key3, value = value3)
ConsumerRecord(topic = test, partition = 0, leaderEpoch = 0, offset = 4, CreateTime = 1720185017771, serialized key size = 4, serialized value size = 6, headers = RecordHeaders(headers = [], isReadOnly = false), key = key4, value = value4)
```

### 8.2 手动提交偏移量

由于 Kafka 中的偏移量默认是自动提交的，当消费者重启之后可能存在数据重复消费的情况，于是可以通过**关闭自动提交**，使用**手动保存偏移量**的方式解决该问题。下面是两种常见的解决方案

#### 8.2.1 同步提交

```java
// 关闭自动提交
configMap.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, false);

while (true) {
    try {
        // 6、从主题中拉取数据
        final ConsumerRecords<String, String> datas = consumer.poll(Duration.ofMillis(100));
        for (ConsumerRecord<String, String> record : datas) {
            System.out.println(record);
        }

        // 手动保存偏移量,同步提交,会阻塞当前线程等待提交结果，提交失败会一直重试
        consumer.commitSync();
    } catch (Exception e) {
        e.printStackTrace();
        return;
    }
}
```

#### 8.2.2 异步提交

```java
// 关闭自动提交
configMap.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, false);

while (true) {
    try {
        // 6、从主题中拉取数据
        final ConsumerRecords<String, String> datas = consumer.poll(Duration.ofMillis(100));
        for (ConsumerRecord<String, String> record : datas) {
            System.out.println(record);
        }

        // 手动保存偏移量,异步提交
        consumer.commitAsync();
    } catch (Exception e) {
        e.printStackTrace();
        return;
    }
}
```

注意：手动提交偏移量虽然可以解决数据重复消费的问题，但是可能会出现漏消费数据的问题。

### 8.3 事务的隔离级别

控制如何读取以事务方式写入的消息。如果设置为 read_committed，consumer.poll() 将只返回已提交的事务消息。如果设置为 read_uncommitted（默认），consumer.poll() 将返回所有信息，甚至包括已中止的事务信息。在这两种模式下，非事务消息都将无条件返回。

消息总是按偏移顺序返回。因此，在读取已提交模式下，consumer.poll() 只返回直到最后一个稳定偏移量（LSO）的报文，即小于第一个打开事务偏移量的报文。特别是在属于正在进行的事务的消息之后出现的任何消息，都将被保留，直到相关事务完成。因此，当有正在进行的交易时，已读已提交的用户将无法读到高水位标记。

**read_committed**

```java
configMap.put(ConsumerConfig.ISOLATION_LEVEL_CONFIG, "read_committed");
```

**read_uncommitted**

```java
configMap.put(ConsumerConfig.ISOLATION_LEVEL_CONFIG, "read_uncommitted");
```

### 8.4 消费者组

为了提高消费数据的吞吐量，Kafka 提出了消费者组的概念，将多个消费者通过一个 `group.id` 的参数绑定起来，提高消费者的消费能力。但是**多个消费者不能消费同一个分区的数据**，反之，**一个消费者可以消费多个分区的数据**。

下面是一个简单的消费者组消费数据的案例：

1. 创建一个有两个分区、一个副本的 test 主题

2. 生产者

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
   
   		for (int i = 0; i < 2000; i++) {
   			// 4、创建数据
   			// 构建数据时需要传递三个参数, 1: 主题 2: 消息key 3: 消息value
   			// 分区设置,向不同的分区添加数据
   			ProducerRecord<String, String> record = new ProducerRecord<>(
   					TOPIC, i % 2, MESSAGE_KEY + i, MESSAGE_VALUE + i
   			);
   
   			// 5、发送数据
   			producer.send(record);
   			try {
   				TimeUnit.SECONDS.sleep(1);
   			} catch (InterruptedException e) {
   				e.printStackTrace();
   			}
   		}
   
   		// 6、关闭生产者对象
   		producer.close();
   	}
   }
   ```

3. 消费者

   ```java
   /**
    * Kafka消费者
    *
    * @author elonlo
    * @date 2024/7/6 9:47
    */
   public class KafkaConsumerGroup1Test {
   
   	/**
   	 * kafka服务地址
   	 */
   	private static final String BOOTSTRAP_SERVER = "localhost:9092";
   
   	/**
   	 * kafka主题
   	 */
   	private static final String TOPIC = "test";
   
   	/**
   	 * 组
   	 */
   	private static final String GROUP = "elonlo";
   
   	public static void main(String[] args) {
   
   		// 1、消费者配置
   		Map<String, Object> configMap = new HashMap<>(4);
   		configMap.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVER);
   
   		// 2、消费者数据反序列化
   		configMap.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
   		configMap.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
   
   		// 3、配置组
   		configMap.put(ConsumerConfig.GROUP_ID_CONFIG, GROUP);
   
   		// 4、创建消费者对象
   		KafkaConsumer<String, String> consumer = new KafkaConsumer<>(configMap);
   
   		// 5、订阅主题
   		consumer.subscribe(Collections.singletonList(TOPIC));
   
   		while (true) {
   			try {
   				// 6、从主题中拉取数据
   				final ConsumerRecords<String, String> datas = consumer.poll(Duration.ofMillis(100));
   				for (ConsumerRecord<String, String> record : datas) {
   					System.out.println(record.partition());
   				}
   			} catch (Exception e) {
   				e.printStackTrace();
   				return;
   			}
   		}
   	}
   }
   ```

   ```java
   /**
    * Kafka消费者
    *
    * @author elonlo
    * @date 2024/7/6 9:47
    */
   public class KafkaConsumerGroup2Test {
   
   	/**
   	 * kafka服务地址
   	 */
   	private static final String BOOTSTRAP_SERVER = "localhost:9092";
   
   	/**
   	 * kafka主题
   	 */
   	private static final String TOPIC = "test";
   
   	/**
   	 * 组
   	 */
   	private static final String GROUP = "elonlo";
   
   	public static void main(String[] args) {
   
   		// 1、消费者配置
   		Map<String, Object> configMap = new HashMap<>(4);
   		configMap.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVER);
   
   		// 2、消费者数据反序列化
   		configMap.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
   		configMap.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
   
   		// 3、配置组
   		configMap.put(ConsumerConfig.GROUP_ID_CONFIG, GROUP);
   
   		// 4、创建消费者对象
   		KafkaConsumer<String, String> consumer = new KafkaConsumer<>(configMap);
   
   		// 5、订阅主题
   		consumer.subscribe(Collections.singletonList(TOPIC));
   
   		while (true) {
   			try {
   				// 6、从主题中拉取数据
   				final ConsumerRecords<String, String> datas = consumer.poll(Duration.ofMillis(100));
   				for (ConsumerRecord<String, String> record : datas) {
   					System.out.println(record.partition());
   				}
   			} catch (Exception e) {
   				e.printStackTrace();
   				return;
   			}
   		}
   	}
   }
   ```

   ```java
   /**
    * Kafka消费者
    *
    * @author elonlo
    * @date 2024/7/6 9:47
    */
   public class KafkaConsumerGroup3Test {
   
   	/**
   	 * kafka服务地址
   	 */
   	private static final String BOOTSTRAP_SERVER = "localhost:9092";
   
   	/**
   	 * kafka主题
   	 */
   	private static final String TOPIC = "test";
   
   	/**
   	 * 组
   	 */
   	private static final String GROUP = "elonlo";
   
   	public static void main(String[] args) {
   
   		// 1、消费者配置
   		Map<String, Object> configMap = new HashMap<>(4);
   		configMap.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVER);
   
   		// 2、消费者数据反序列化
   		configMap.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
   		configMap.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
   
   		// 3、配置组
   		configMap.put(ConsumerConfig.GROUP_ID_CONFIG, GROUP);
   
   		// 4、创建消费者对象
   		KafkaConsumer<String, String> consumer = new KafkaConsumer<>(configMap);
   
   		// 5、订阅主题
   		consumer.subscribe(Collections.singletonList(TOPIC));
   
   		while (true) {
   			try {
   				// 6、从主题中拉取数据
   				final ConsumerRecords<String, String> datas = consumer.poll(Duration.ofMillis(100));
   				for (ConsumerRecord<String, String> record : datas) {
   					System.out.println(record.partition());
   				}
   			} catch (Exception e) {
   				e.printStackTrace();
   				return;
   			}
   		}
   	}
   }
   ```

4. 依次启动 KafkaConsumerGroup1Test、KafkaConsumerGroup2Test、KafkaConsumerGroup3Test、KafkaProducerTest，可以看到控制台打印以下内容

   **KafkaConsumerGroup1Test**

   ```tex
   1
   1
   1
   1
   ```

   **KafkaConsumerGroup2Test**

   ```tex
   0
   0
   0
   0
   ```

   **KafkaConsumerGroup3Test**

   ```tex
   
   ```

5. 停止并关闭 KafkaConsumerGroup1Test 后

   **KafkaConsumerGroup2Test**

   ```tex
   0
   0
   0
   0
   ```

   **KafkaConsumerGroup3Test**

   ```tex
   1
   1
   1
   1
   ```

6. 停止并同时关闭 KafkaConsumerGroup1Test、KafkaConsumerGroup2Test 后

   **KafkaConsumerGroup3Test**

   ```tex
   1
   0
   1
   0
   1
   0
   1
   0
   ```

### 8.5 分区分配策略

**RoundRobinAssignor（轮询分配策略）**

每个消费者组中的消费者都会含有一个自动生产的 UUID 作为 memberid。轮询策略中会将每个消费者按照 memberid 进行排序，所有 memberid 消费的主题分区根据主题名称进行排序。将主题分区轮询分配给对应的订阅用户，注意未订阅当前轮询主题的消费者会跳过。

**RangeAssignor（范围分配策略）**

按照每个 topic 的 partition 数计算出每个消费者应该分配的分区数量，然后分配，分配的原则就是一个主题的分区尽可能的平均分配，如果不能平均分配，那就按顺序向前补齐既可。

**假设有【1，2，3，4，5】5 个分区分配给 2 个消费者：**

5 / 2 = 2，5 % 2 = 1 => 剩余的一个补在第一个中[2 + 1] [2] => 结果为 [1,2,3] [4,5]

**假设有【1，2，3，4，5】5 个分区分配给 3 个消费者：**

5 / 3 = 1，5 % 3 = 2 => 剩余的两个补在第一个和第二个中 [1 + 1] [1 + 1] [1] => 结果为 [1,2] [3,4] [5]

**StickyAssignor（粘性分区策略）**

在第一次分配后，每个组成员都保留分配给自己的分区信息。如果有消费者加入或退出，那么在进行分区再分配时（一般情况下，消费者退出 45s 后，才会进行再分配，因为需要考虑可能又恢复的情况），尽可能保证消费者原有的分区不变，重新对加入或退出消费者的分区进行分配。

**CooperativeStickyAssignor**

前面的三种分配策略在进行重新分配时使用的都是 EAGER 协议，会让当前的所有消费者放弃当前分区，关闭连接，资源清理，重新加入组和等待分配策略。明显效率是比较低的，所以从 Kafka 2.4 版本开始，在粘性分区策略的基础上，优化了重分配的过程，使用的是 COOPERATIVE 协议，特点是在整个再分配的过程中粘性分区策略分配的会更加均匀和高效一些，COOPERATIVE 协议将一次全局重平衡，改成每次小规模重平衡，直至最终收敛平衡的过程。

Kafka 消费者默认的分区分配策略就是 RangeAssignor 和 CooperativeStickyAssignor

### 8.6 消费者 Leader 选举

![image-20240706134747313](https://image.elonlo.top/img/2024/07/06/6688da8ce0a12.png)

注意：每个消费者都有可能成为 Leader，当有新的消费者加入消费者组时，消费者组会将所有的消费者移出，然后重新选举 Leader 节点。

## 九、高级特性

### 9.1 分布式集群脑裂

**什么是集群脑裂问题？**

比如有 A、B、C 三个 Broker 节点，最开始时 A 是作为整个集群的 Controller，但是 A 由于网络等原因掉线了，于是 C 成为了新的 Controller，这时 C 向 B 同步数据，同步数据的时候 A 恢复了，这时候 A 也向 B 同步数据，此时集群当中就有了两个 Controller，B 不知道具体要同步谁的数据，这就是集群的脑裂问题。

**集群脑裂解决**

在 Zookeeper 中创建一个 epoch 节点，这个节点的名称为 controller_epoch，默认创建时值为 0，当 controller 选举成功之后就会更新该值，将值加 1。

任何节点都能够从集群中获取最新的 epoch，当集群中有多个管理者时，节点只需判断当前同步数据的 epoch 值和当前集群中最新的 epoch 是否相等，一旦同步数据的 epoch 值小于集群中最新的 epoch，节点就可以不接收同步数据的处理

### 9.2 Linux 集群部署

| 服务节点 | Kafka-broker-1 | Kafka-broker-2 | Kafka-broker-3 |
| -------- | -------------- | -------------- | -------------- |
| 服务进程 | QuorumPeerMain | QuorumPeerMain | QuorumPeerMain |
| 服务进程 | Kafka          | Kafka          | Kafka          |

#### 9.2.1 服务器集群网络配置

1. 导入已创建的虚拟机 Kafka-broker-1

2. 将 Kafka-broker-1 分别克隆出 Kafka-broker-2、Kafka-broker-3

3. 虚拟机中修改网络，在 vmware 的编辑→虚拟网络编辑器→更改设置→VMnet8，下面的子网 IP 修改为`192.168.10.0`，NAT 设置将网络修改为 `192.168.10.2`，DHCP 设置中起始 IP 设置为 `192.168.10.100`

4. 使用 root 用户分别登录三台服务器，执行以下命令将虚拟机修改为静态 IP

   ```bash
   # 修改虚拟机网络
   vim /etc/sysconfig/network-scripts/ifcfg-ens33
   ```

   ```properties
   # Kafka-broker-1
   TYPE=Ethernet
   PROXY_METHOD=none
   BROWSER_ONLY=no
   DEFROUTE=yes
   IPV4_FAILURE_FATAL=no
   IPV6INIT=yes
   IPV6_AUTOCONF=yes
   IPV6_DEFROUTE=yes
   IPV6_FAILURE_FATAL=no
   IPV6_ADDR_GEN_MODE=stable-privacy
   NAME=ens33
   UUID=cf96a10c-94e6-49bb-94c7-e8c8359333a7
   DEVICE=ens33
   
   BOOTPROTO=static
   ONBOOT=yes
   IPADDR=192.168.10.101
   NETMASK=255.255.255.0
   GATEWAY=192.168.10.2
   DNS1=192.168.10.2
   DNS2=223.5.5.5
   DNS3=119.29.29.29
   ```

   ```properties
   # Kafka-broker-2
   TYPE=Ethernet
   PROXY_METHOD=none
   BROWSER_ONLY=no
   DEFROUTE=yes
   IPV4_FAILURE_FATAL=no
   IPV6INIT=yes
   IPV6_AUTOCONF=yes
   IPV6_DEFROUTE=yes
   IPV6_FAILURE_FATAL=no
   IPV6_ADDR_GEN_MODE=stable-privacy
   NAME=ens33
   UUID=cf96a10c-94e6-49bb-94c7-e8c8359333a7
   DEVICE=ens33
   
   BOOTPROTO=static
   ONBOOT=yes
   IPADDR=192.168.10.102
   NETMASK=255.255.255.0
   GATEWAY=192.168.10.2
   DNS1=192.168.10.2
   DNS2=223.5.5.5
   DNS3=119.29.29.29
   ```

   ```properties
   # Kafka-broker-3
   TYPE=Ethernet
   PROXY_METHOD=none
   BROWSER_ONLY=no
   DEFROUTE=yes
   IPV4_FAILURE_FATAL=no
   IPV6INIT=yes
   IPV6_AUTOCONF=yes
   IPV6_DEFROUTE=yes
   IPV6_FAILURE_FATAL=no
   IPV6_ADDR_GEN_MODE=stable-privacy
   NAME=ens33
   UUID=cf96a10c-94e6-49bb-94c7-e8c8359333a7
   DEVICE=ens33
   
   BOOTPROTO=static
   ONBOOT=yes
   IPADDR=192.168.10.103
   NETMASK=255.255.255.0
   GATEWAY=192.168.10.2
   DNS1=192.168.10.2
   DNS2=223.5.5.5
   DNS3=119.29.29.29
   ```

5. 服务器修改主机名

   ```bash
   vim /etc/hostname
   ```

   ```tex
   # Kafka-broker-1
   Kafka-broker-1
   ```

   ```tex
   # Kafka-broker-2
   Kafka-broker-2
   ```

   ```tex
   # Kafka-broker-3
   Kafka-broker-3
   ```

6. 修改主机名映射，三台主机都添加以下配置

   ```bash
   vim /etc/hosts
   ```

   ```tex
   192.168.10.101 kafka-broker-1
   192.168.10.102 kafka-broker-2
   192.168.10.103 kafka-broker-3
   ```

7. Windows 修改主机名，找到 `C:\Windows\System32\drivers\etc\hosts` 这个文件，添加以下内容

   ```tex
   192.168.10.101 kafka-broker-1
   192.168.10.102 kafka-broker-2
   192.168.10.103 kafka-broker-3
   ```

8. 添加分发脚本

   ```bash
   # 切换到root目录
   cd /root
   
   # 创建 bin 目录
   mkdir bin
   
   # 切换到 bin 目录
   cd bin
   
   # 创建分发脚本
   vim xsync
   ```

   ```bash
   #!/bin/bash
   
   # 1.判断参数个数
   if [ $# -lt 1 ]
   then
       echo Not Enough Arguement!
       exit;
   fi
   
   # 2.遍历集群所有机器
   for host in kafka-broker-1 kafka-broker-2 kafka-broker-3
   do
       echo ====================  $host  ====================
       # 3.遍历所有目录,挨个发送
       for file in $@
       do
   	# 4.判断文件是否存在
   	if [ -e $file ]
   	then
   	    # 5.获取父目录
   	    padir=$(cd -P $(dirname $file); pwd)
   	    # 6.获取当前文件的名称
   	    fname=$(basename $file)
   	    ssh $host "mkdir -p $pdir"
   	    rsync -av $pdir/$fname $host:$pdir
   	else
   	    echo $file does not exists!
   	fi
       done 
   done
   ```

   ```bash
   # 切换到xsync脚本所在目录
   cd /root/bin
   
   # 修改脚本执行权限
   chmod 777 xsync
   ```

9. 配置 SSH 免密登录

   - 生成公钥和私钥（三台服务器）

     ```bash
     ssh-keygen -t rsa
     ```

     一直回车直到执行成功，会生成两个文件 id_rsa（私钥）、id_rsa.pub（公钥）

   - 将公钥拷贝到需要免密登录的目标机器上，拷贝过程中需要输入目标机器密码，三台服务器依次执行以下命令

     ```bash
     ssh-copy-id kafka-broker-1
     ssh-copy-id kafka-broker-2
     ssh-copy-id kafka-broker-3
     ```

   注意：如出现配置错误，直接删除 root 目录下的 `.ssh` 文件夹和 `.cache` 文件夹即可，ran'hou

#### 9.2.2 Java 安装

#### 9.2.3 Zookeeper 安装

#### 9.2.4 Kafka 安装





