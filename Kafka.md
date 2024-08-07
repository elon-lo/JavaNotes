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
               pdir=$(cd -P $(dirname $file); pwd)
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

   注意：如出现配置错误，直接删除 root 目录下的 `.ssh` 文件夹和 `.cache` 文件夹即可，然后重启

#### 9.2.2 Java 安装

1. 卸载现有的 jdk（三台机器都要执行）

   ```bash
   rpm -qa | grep -i java | xargs -n1 sudo rpm -e --nodeps
   ```

2. 上传 jdk 压缩包 `jdk-8u231-linux-x64.tar` 至 `opt` 目录下

3. jdk 压缩包解压

   ```bash
   # 切换到 /opt 目录下
   cd /opt
   # 解压缩至指定的目录下
   tar -zxvf jdk-8u231-linux-x64.tar -C /usr/local
   # 切换到 /usr/local 目录
   cd /usr/local
   # 目录重命名
   mv jdk1.8.0_231/ java
   ```

4. 配置 Java 环境变量

   ```bash
   vim /etc/profile.d/my_env.sh
   ```

   ```bash
   # JAVA_HOME
   export JAVA_HOME=/usr/local/java
   export PATH=$JAVA_HOME/bin:$PATH
   export CLASSPATH=.:$JAVA_HOME/lib/dt.jar:$JAVA_HOME/lib/tools.jar
   ```

5. 刷新环境变量配置

   ```bash
   source /etc/profile.d/my_env.sh
   ```

6. 安装测试

   ```bash
   java -version
   ```

7. 分发软件

   ```bash
   # 分发环境变量文件
   xsync /etc/profile.d/my_env.sh
   
   # 分发 Java 目录
   xsync /usr/local/java/
   ```

8. 刷新环境变量（三台机器都要执行）

   ```bash
   source /etc/profile.d/my_env.sh
   ```

#### 9.2.3 Zookeeper 安装

1. 上传 zookeeper 压缩包 `apache-zookeeper-3.7.1-bin.tar.gz` 至 `opt` 目录下

2. zookeeper 压缩包解压

   ```bash
   # 切换到 /opt 目录下
   cd /opt
   # 解压缩至指定的目录下
   tar -zxvf apache-zookeeper-3.7.1-bin.tar.gz -C /usr/local
   # 切换到 /usr/local 目录
   cd /usr/local
   # 目录重命名
   mv apache-zookeeper-3.7.1-bin/ zookeeper
   ```

3. 配置服务器编号

   ```bash
   # 切换到 /usr/local/zookeeper 目录下
   cd /usr/local/zookeeper
   # 在 /usr/local/zookeeper 目录下创建 zkData
   mkdir zkData
   # 切换到 zkData 目录下
   cd zkData
   # 创建 myid 文件并写入内容
   touch myid && echo "1" > myid
   ```

4. 重命名 `/usr/local/zookeeper/conf` 目录下的 `zoo_sample.cfg` 文件名为 `zoo.cfg`

   ```bash
   # 切换到 /usr/local/zookeeper/conf 目录下
   cd /usr/local/zookeeper/conf
   # 修改文件名称
   mv zoo_sample.cfg zoo.cfg
   # 修改文件,添加以下内容
   vim zoo.cfg
   ```

   ```properties
   # Kafka数据存放目录
   dataDir=/usr/local/zookeeper/zkData
   
   # 集群配置
   #############################    cluster    #############################
   # server.A=B:C:d
   # 
   # A 是一个数字,表示这个是第几号服务器
   # B 是 A 服务器的主机名
   # C 是 A 服务器与集群中的主服务器（Leader）交换信息的端口
   # D 是 A 服务器用于主服务器（Leader）选举的端口
   #########################################################################
   server.1=kafka-broker-1:2888:3888
   server.2=kafka-broker-2:2888:3888
   server.3=kafka-broker-3:2888:3888
   ```

5. 启动 zookeeper

   ```bash
   # 切换到 /usr/local/zookeeper 目录下
   cd /usr/local/zookeeper
   # 启动 zk 服务
   bin/zkServer.sh start
   ```

6. 关闭 zookeeper

   ```bash
   # 切换到 /usr/local/zookeeper 目录下
   cd /usr/local/zookeeper
   # 启动 zk 服务
   bin/zkServer.sh stop
   ```

7. 查看 zookeeper 状态

   ```bash
   # 切换到 /usr/local/zookeeper 目录下
   cd /usr/local/zookeeper
   # 启动 zk 服务
   bin/zkServer.sh status
   ```

8. 分发软件

   ```bash
   # 分发 zookeeper 目录
   xsync /usr/local/zookeeper/
   
   # 分别将不同服务器上 myid 的值修改为以下对应内容
   # kafka-broker-1:1
   # kafka-broker-2:2
   # kafka-broker-3:3
   ```

9. 启停脚本

   - 创建 zk.sh 文件并添加脚本命令

     ```bash
     # 在 kafka-broker-1 服务器上的 /root/bin 目录下创建 zk.sh 脚本
     vim /root/bin/zk.sh
     ```

     ```bash
     #!/bin/bash
     
     case $1 in
     "start"){
     	for i in kafka-broker-1 kafka-broker-2 kafka-broker-3
     	do
     	echo ------------- zookeeper $i 启动 -------------
     		ssh $i "/usr/local/zookeeper/bin/zkServer.sh start"
     	done
     };;
     "stop"){
     	for i in kafka-broker-1 kafka-broker-2 kafka-broker-3
     	do
     	echo ------------- zookeeper $i 停止 -------------
     		ssh $i "/usr/local/zookeeper/bin/zkServer.sh stop"
     	done
     };;
     "status"){
     	for i in kafka-broker-1 kafka-broker-2 kafka-broker-3
     	do
     	echo ------------- zookeeper $i 状态 -------------
     		ssh $i "/usr/local/zookeeper/bin/zkServer.sh status"
     	done
     };;
     esac
     ```

   - 增加 `zk.sh` 脚本权限

     ```bash
     chmod +x /root/bin/zk.sh
     ```

   - 脚本调用方式

     ```bash
     # 启动 zk 服务
     zk.sh start
     # 停止 zk 服务
     zk.sh stop
     # 查看 zk 状态
     zk.sh status
     ```

10. 客户端工具安装

    - 软件名称：prettyZoo
    - 项目地址：https://github.com/vran-dev/PrettyZoo

#### 9.2.4 Kafka 安装

1. 上传文件并解压重命名

   ```bash
   # 解压缩文件到指定目录
   tar -zxvf kafka_2.12-3.6.1.tgz -C /usr/local
   
   # 切换到 /usr/local 目录下
   cd /usr/local
   
   # 重命名文件目录
   mv kafka_2.12-3.6.1/ kafka
   ```

2. 修改配置文件

   - 切换到配置文件目录下

     ```bash
     cd /usr/local/kafka/config
     ```

   - 修改配置文件

     ```bash
     vim server.properties
     ```

   - 追加以下内容

     ```properties
     # broker 的全局唯一编号,每个服务节点不能重复,只能是数字
     broker.id=1
     
     # broker 对外暴露的 ip 和端口（每个节点单独配置）
     advertised.listeners=PLAINTEXT://kafka-broker-1:9092
     
     # 处理网络请求的线程数量
     num.network.threads=3
     
     # 用来处理磁盘 IO 的线程数量
     num.io.threads=8
     
     # 发送套接字的缓冲区大小
     socket.send.buffer.bytes=102400
     
     # 接收套接字的缓冲区大小 
     socket.receive.buffer.bytes=102400
     
     # 请求套接字的缓冲区大小 
     socket.request.max.bytes=104857600
     
     # kafka 运行日志（数据）存放的路径,可以配置多个路径,路径与路径之间使用逗号隔开
     log.dirs=/usr/local/kafka/datas
     
     # topic 在当前 broker 上的分区个数
     num.partitions=1
     
     # 用来恢复和清理 data 下数据的线程数量
     num.recovery.threads.per.data.dir=1
     
     # 每个 topic 创建时的副本数,默认是 1 个副本
     offsets.topic.replication.factor=1
     
     # segment 文件保留的最长时间,超时将被删除
     log.retention.hours=168
     
     # 每个 segment 文件的大小,默认为 1G
     log.segment.bytes=1073741824
     
     # 检查过期数据的时间,默认 5 分钟检查一次是否数据过期
     log.retention.check.interval.ms=300000
     
     # 配置连接 zookeeper 集群地址（建议在 zk 根目录下创建 /kafka,方便管理）
     zookeeper.connect=kafka-broker-1:2181,kafka-broker-2:2181,kafka-broker-3:2181/kafka
     ```

   - 防火墙放行端口

     ```bash
     firewall-cmd --zone=public --add-port=2181/tcp --permanent
     firewall-cmd --zone=public --add-port=2888/tcp --permanent
     firewall-cmd --zone=public --add-port=3888/tcp --permanent
     firewall-cmd --zone=public --add-port=9092/tcp --permanent
     ```

   - 刷新防火墙配置

     ```bash
     firewall-cmd --reload
     ```

3. 分发 Kafka 软件

   ```bash
   # 切换到 /usr/local/kafka 目录下
   cd /usr/local/kafka
   
   # 执行分发指令 
   xsync kafka
   
   # 依次配置三台服务器上的 server.properties
   vim /usr/local/kafka/config/server.properties
   # broker.id=1（kafka-broker-1）
   # advertised.listeners=PLAINTEXT://kafka-broker-1:9092（kafka-broker-1）
   
   # broker.id=2（kafka-broker-2）
   # advertised.listeners=PLAINTEXT://kafka-broker-2:9092（kafka-broker-2）
   
   # broker.id=3（kafka-broker-3）
   # advertised.listeners=PLAINTEXT://kafka-broker-3:9092（kafka-broker-3）
   ```

4. 配置环境变量

   ```bash
   # 环境变量追加 Kafka 配置
   vim /etc/profile.d/my_env.sh
   ```

   ```properties
   # KAFKA_HOME
   export KAFKA_HOME=/usr/local/kafka
   export PATH=$PATH:$KAFKA_HOME/bin
   ```

   ```bash
   # 让环境变量生效
   source /etc/profile.d/my_env.sh
   ```

5. 分发配置文件

   ```bash
   xsync /etc/profile.d/my_env.sh
   ```

6. 刷新环境变量（三台服务器都要执行）

   ```bash
   source /etc/profile.d/my_env.sh
   ```

7. 启动 Kafka 

   - 启动 zookeeper 服务

     ```bash
     /usr/local/zookeeper/bin/zkServer.sh start
     ```

   - 启动 Kafka

     ```bash
     /usr/local/kafka/bin/kafka-server-start.sh -daemon /usr/local/kafka/config/server.properties
     ```

8. 关闭 Kafka

   ```bash
   /usr/local/kafka/bin/kafka-server-stop.sh
   ```

9. 添加 Kafka 启停脚本

   - 在 `kafka-broker-1` 服务器下的 `/root/bin` 目录下创建 `kfk.sh` 脚本文件

     ```bash
     vim /root/bin/kfk.sh
     ```

   - 添加以下内容

     ```bash
     #!/bin/bash
     
     case $1 in
     "start"){
     	for i in kafka-broker-1 kafka-broker-2 kafka-broker-3
     	do
     	echo ------------- 启动 $i kafka -------------
     		ssh $i "/usr/local/kafka/bin/kafka-server-start.sh -daemon /usr/local/kafka/config/server.properties"
     	done
     };;
     "stop"){
     	for i in kafka-broker-1 kafka-broker-2 kafka-broker-3
     	do
     	echo ------------- 停止 $i kafka -------------
     		ssh $i "/usr/local/kafka/bin/kafka-server-stop.sh"
     	done
     };;
     esac
     ```

   - 添加 `kfk.sh` 脚本权限

     ```bash
     chmod +x /root/bin/kfk.sh
     ```

10. 添加联合脚本

    - 在 `kafka-broker-1` 服务器下的 `/root/bin` 目录下创建 `xcall` 脚本文件

      ```bash
      vim /root/bin/xcall
      ```

    - 脚本中添加以下内容

      ```bash
      #!/bin/bash
      
      for i in kafka-broker-1 kafka-broker-2 kafka-broker-3
      do
      	echo ------------- $i -------------
      	ssh $i "$*"
      done
      ```

    - 增加 `xcall` 脚本文件权限

      ```bash
      chmod +x /root/bin/xcall
      ```

    - 在 `kafka-broker-1` 服务器下的 `/root/bin` 目录下创建 `cluster.sh` 脚本文件

      ```bash
      vim /root/bin/cluster.sh
      ```

    - 脚本中添加以下内容

      ```bash
      #!/bin/bash
      
      case $1 in
      "start"){
      	echo =================== 启动 kafka 集群 ===================
      	
      	# 启动 zookeeper 集群
      	zk.sh start
      	
      	# 启动 kafka 集群
      	kfk.sh start
      };;
      "stop"){
      	echo =================== 停止 kafka 集群 ===================
      	
      	# 停止 kafka 集群
          kfk.sh stop
          
                  # 循环至 kafka 集群进程全部停止
                  kafka_count=$(xcall jps | grep Kafka | wc -l)
                  while [ $kafka_count -gt 0 ]
                  do
                      sleep 1
                      kafka_count=$(xcall | grep Kafka | wc -l)
                      
          	echo "当前未停止的 Kafka 进程数为 $kafka_count"
          		done
          		
          # 停止 zookeeper 集群
          zk.sh stop  	
      };;
      esac
      ```

    - 增加 `cluster.sh` 脚本文件权限

11. 脚本调用

    - 集群启动

      ```bash
      cluster.sh start
      ```

    - 集群停止

      ```bash
      cluster.sh stop
      ```

#### 9.2.5 Kafka 监控软件

**Kafka-Eagle** 框架可以监控 Kafka 集群的整体运行情况，在生产环境中经常使用。**Kafka-Eagle** 的安装依赖于 MySQL，MySQL 主要用来存储可视化展示的数据。

1. 上传 MySQL 安装包

2. 创建 MySQL 安装配置脚本

   ```bash
   vim install_mysql.sh
   ```

   ```bash
   #!/bin/bash
   
   # 设置错误处理
   set -e
   
   # 检查是否以 root 用户运行
   if [ "$(whoami)" != "root" ]; then
     echo "请使用 root 用户运行此脚本"
     exit 1
   fi
   
   # 检查是否存在所有的 RPM 包
   if [ "$(ls *.rpm | wc -l)" != "7" ]; then
     echo "所有 MySQL RPM 包都必须在当前目录中"
     exit 1
   fi
   
   # 确认所有必需的 RPM 包
   for pkg in mysql-community-client-8.0.31-1.el7.x86_64.rpm \
              mysql-community-client-plugins-8.0.31-1.el7.x86_64.rpm \
              mysql-community-common-8.0.31-1.el7.x86_64.rpm \
              mysql-community-icu-data-files-8.0.31-1.el7.x86_64.rpm \
              mysql-community-libs-8.0.31-1.el7.x86_64.rpm \
              mysql-community-libs-compat-8.0.31-1.el7.x86_64.rpm \
              mysql-community-server-8.0.31-1.el7.x86_64.rpm; do
     if [ ! -f "$pkg" ]; then
       echo "缺少 $pkg 文件"
       exit 1
     fi
   done
   
   # 卸载现有的 MySQL 包
   echo "卸载现有的 MySQL 包..."
   systemctl stop mysqld || true
   rpm -qa | grep -i 'mysql\|mariadb' | xargs -r rpm -e --nodeps || true
   rm -rf /var/lib/mysql /var/log/mysqld.log /usr/lib64/mysql /etc/my.cnf /usr/my.cnf
   
   # 安装 MySQL RPM 包
   echo "安装 MySQL RPM 包..."
   yum localinstall -y *.rpm
   
   # 启动 MySQL 服务
   echo "启动 MySQL 服务..."
   systemctl start mysqld
   systemctl enable mysqld
   
   # 查找临时密码
   tpass=$(grep 'temporary password' /var/log/mysqld.log | awk '{print $NF}')
   echo "临时密码为: $tpass"
   
   # 配置 MySQL 密码策略并重启
   echo "配置 MySQL 密码策略..."
   cat << EOF >> /etc/my.cnf
   [mysqld]
   validate_password.length=4
   validate_password.policy=0
   default_authentication_plugin=mysql_native_password
   EOF
   systemctl restart mysqld
   
   # 设置 MySQL root 密码和权限
   echo "配置 MySQL root 用户..."
   mysql -uroot -p"${tpass}" --connect-expired-password << EOF
   ALTER USER 'root'@'localhost' IDENTIFIED WITH 'mysql_native_password' BY '000000';
   CREATE USER 'root'@'%' IDENTIFIED WITH 'mysql_native_password' BY '000000';
   GRANT ALL PRIVILEGES ON *.* TO 'root'@'%' WITH GRANT OPTION;
   FLUSH PRIVILEGES;
   EOF
   
   echo "MySQL 安装和配置完成。你现在可以使用 'mysql -uroot -p000000' 登录 MySQL。"
   ```

3. 防火墙放行 `3306` 端口（三台服务器都要配置）

   ```bash
   firewall-cmd --zone=public --add-port=3306/tcp --permanent
   
   firewall-cmd --reload
   ```

4. 修改 Kafka 集群配置

   - 切换到 `/usr/local/kafka/bin` 目录下

   - 修改 `kafka-server-start.sh` 脚本中 `KAFKA_HEAP_OPTS` 部分的配置为以下内容 

     ```bash
     if [ "x$KAFKA_HEAP_OPTS" = "x" ]; then
     	export KAFKA_HEAP_OPTS="-server -Xms2G -Xmx2G -XX:PermSize=128m -XX:+UseG1GC -XX:MaxGCPauseMillis=200 -XX:ParallelGCThreads=8 -XX:ConcGCThreads=5 -XX:InitiatingHeapOccupancyPercent=70"
         export JMX_PORT="9999"
         # export KAFKA_HEAP_OPTS="-Xms1G -Xmx1G"
     fi
     ```

   - 分发修改后的脚本

     ```bash
     xsync /usr/local/kafka/bin/kafka-server-start.sh
     ```

5. Kafka-Eagle 安装

   - 上传安装包到 `opt` 目录下

   - 解压 `kafka-eagle-bin-3.0.1.tar.gz` 压缩包

     ```bash
     tar -zxvf kafka-eagle-bin-3.0.1.tar.gz
     ```

   - 切换到 `kafka-eagle-bin-3.0.1` 目录下

     ```bash
     cd kafka-eagle-bin-3.0.1/
     ```

   - 解压 `efak-web-3.0.1-bin.tar.gz` 到 `/usr/local` 目录下 

     ```bash
     tar -zxvf efak-web-3.0.1-bin.tar.gz -C /usr/local
     ```

   - 将 `efak-web-3.0.1` 目录重命名为 `efak`

     ```bash
     mv efak-web-3.0.1/ efak
     ```

6. Kafka-Eagle 配置

   - 切换到 `/usr/local/efak/conf` 目录下

     ```bash
     cd /usr/local/efak/conf
     ```

   - 修改 `system-config.properties` 文件

     ```bash
     vim system-config.properties
     ```

   - 将文件中的内容替换为以下内容

     ```properties
     ######################################
     # multi zookeeper & kafka cluster list
     # Settings prefixed with 'kafka.eagle.' will be deprecated, use 'efak.' instead
     ######################################
     efak.zk.cluster.alias=cluster1
     cluster1.zk.list=kafka-broker-1:2181,kafka-broker-2:2181,kafka-broker-3:2181/kafka
     
     ######################################
     # zookeeper enable acl
     ######################################
     cluster1.zk.acl.enable=false
     cluster1.zk.acl.schema=digest
     cluster1.zk.acl.username=test
     cluster1.zk.acl.password=test
     
     ######################################
     # broker size online list
     ######################################
     cluster1.efak.broker.size=20
     
     ######################################
     # zk client thread limit
     ######################################
     kafka.zk.limit.size=32
     
     ######################################
     # EFAK webui port
     ######################################
     efak.webui.port=8048
     
     ######################################
     # EFAK enable distributed
     ######################################
     efak.distributed.enable=false
     efak.cluster.mode.status=master
     efak.worknode.master.host=localhost
     efak.worknode.port=8085
     
     ######################################
     # kafka jmx acl and ssl authenticate
     ######################################
     cluster1.efak.jmx.acl=false
     cluster1.efak.jmx.user=keadmin
     cluster1.efak.jmx.password=keadmin123
     cluster1.efak.jmx.ssl=false
     cluster1.efak.jmx.truststore.location=/data/ssl/certificates/kafka.truststore
     cluster1.efak.jmx.truststore.password=ke123456
     
     ######################################
     # kafka offset storage
     ######################################
     cluster1.efak.offset.storage=kafka
     
     ######################################
     # kafka jmx uri
     ######################################
     cluster1.efak.jmx.uri=service:jmx:rmi:///jndi/rmi://%s/jmxrmi
     
     ######################################
     # kafka metrics, 15 days by default
     ######################################
     efak.metrics.charts=true
     efak.metrics.retain=15
     
     ######################################
     # kafka sql topic records max
     ######################################
     efak.sql.topic.records.max=5000
     efak.sql.topic.preview.records.max=10
     
     ######################################
     # delete kafka topic token
     ######################################
     efak.topic.token=keadmin
     
     ######################################
     # kafka sasl authenticate
     ######################################
     cluster1.efak.sasl.enable=false
     cluster1.efak.sasl.protocol=SASL_PLAINTEXT
     cluster1.efak.sasl.mechanism=SCRAM-SHA-256
     cluster1.efak.sasl.jaas.config=org.apache.kafka.common.security.scram.ScramLoginModule required username="kafka" password="kafka-eagle";
     cluster1.efak.sasl.client.id=
     cluster1.efak.blacklist.topics=
     cluster1.efak.sasl.cgroup.enable=false
     cluster1.efak.sasl.cgroup.topics=
     cluster2.efak.sasl.enable=false
     cluster2.efak.sasl.protocol=SASL_PLAINTEXT
     cluster2.efak.sasl.mechanism=PLAIN
     cluster2.efak.sasl.jaas.config=org.apache.kafka.common.security.plain.PlainLoginModule required username="kafka" password="kafka-eagle";
     cluster2.efak.sasl.client.id=
     cluster2.efak.blacklist.topics=
     cluster2.efak.sasl.cgroup.enable=false
     cluster2.efak.sasl.cgroup.topics=
     
     ######################################
     # kafka ssl authenticate
     ######################################
     cluster3.efak.ssl.enable=false
     cluster3.efak.ssl.protocol=SSL
     cluster3.efak.ssl.truststore.location=
     cluster3.efak.ssl.truststore.password=
     cluster3.efak.ssl.keystore.location=
     cluster3.efak.ssl.keystore.password=
     cluster3.efak.ssl.key.password=
     cluster3.efak.ssl.endpoint.identification.algorithm=https
     cluster3.efak.blacklist.topics=
     cluster3.efak.ssl.cgroup.enable=false
     cluster3.efak.ssl.cgroup.topics=
     
     # 配置 mysql 连接
     efak.driver=com.mysql.cj.jdbc.Driver
     efak.url=jdbc:mysql://kafka-broker-1:3306/ke?useUnicode=true&characterEncoding=UTF-8&zeroDateTimeBehavior=convertToNull
     efak.username=root
     efak.password=000000
     ```

7. 添加 `eagle` 环境变量

   - 在 `/etc/profile.d/my_env.sh` 脚本中追加以下内容

     ```properties
     # kafka-efak
     export KE_HOME=/usr/local/efak
     export PATH=$PATH:$KE_HOME/bin
     ```

   - 刷新环境变量

     ```bash
     source /etc/profile.d/my_env.sh
     ```

8. 启动集群

   - 启动 zookeeper、Kafka 集群

     ```bash
     /root/bin/cluster.sh start
     ```

   - 启动 efak

     ```bash
     cd /usr/local/efak
     ```

     ```bash
     # 启动 efak
     bin/ke.sh start
     ```

     ```bash
     # 停止 efak
     bin/ke.sh stop
     ```

   - 防火墙放行 `8048、9999` 端口（三台服务器都要配置）

     ```bash
     firewall-cmd --zone=public --add-port=8048/tcp --permanent
     firewall-cmd --zone=public --add-port=9999/tcp --permanent
     
     firewall-cmd --reload
     ```

#### 9.2.6 KRaft 模式

Kafka 作为一种高吞吐量的分布式发布订阅消息系统，在消息应用中广泛使用，尤其在需要实时数据处理和应用程序活动跟踪的场景，Kafka 已成为首选服务。在 **Kafka 2.8** 之前，Kafka 强依赖 zookeeper 来负责集群元数据的管理，这也导致当 zookeeper 集群性能发生抖动时，Kafka 的性能也会受到很大的影响。**2.8 版本之后**，Kafka 开始**提供 KRaft（Kafka KRaft，依赖 Java 8+）模式**，开始**去除 zookeeper 的依赖**。最新的 3.6.1 版本中，Kafka 依然兼容 zookeeper Controller，但 Kafka KRaft 元数据模式，已经可以在不依赖 zookeeper 的情况下独立启动 Kafka 了。官方预计会在 Kafka 4.0 中移除 zookeeper。 

**KRaft 模式的优势**

- **更简单的部署和管理**：通过只安装和管理一个应用程序，无需安装更多的软件，简化软件的安装部署。这也使得在边缘的小型设备中更容易利用 Kafka。
- **提高可扩展性**：KRaft 的恢复时间比 zookeeper 快一个数量级。这使得我们能够有效地扩展到单个集群中的数百万个分区。zookeeper 的有效限制是数万个分区。
- **更有效的元数据传播**：基于日志、事件驱动的元数据传播可以提高 Kafka 的许多核心功能的性能。另外它还支持元数据主题的快照。
- **读写能力提高**：由于不依赖 zookeeper，集群扩展时不再受到 zookeeper 读写能力限制。
- **节点合理分配**：controller 不再动态选举，而是由配置文件规定。这样可以针对性的加强 controller 节点的配置，而不是像以前一样对随机 controller 节点产生高负载。

**Kafka-KRaft 集群部署**

1. 上传 `kafka_2.12-3.6.1.tgz` 文件到 `opt` 目录下

2. 解压 `kafka_2.12-3.6.1.tgz`  文件到 `/usr/local` 目录下

   ```bash
   tar -zxvf kafka_2.12-3.6.1.tgz -C /usr/local
   ```

3. 切换到 `/usr/local` 目录下

   ```bash
   cd /usr/local
   ```

4. 将 `kafka_2.12-3.6.1` 目录重命名为 `kafka-kraft`

   ```bash
   mv kafka_2.12-3.6.1/ kafka-kraft
   ```

5. 切换到 `/usr/local/kafka-kraft/config/kraft` 目录下

   ```bash
   cd /usr/local/kafka-kraft/config/kraft
   ```

6. 修改 `server.properties` 文件，并替换以下内容

   ```properties
   # kafka 的角色(controller 相当于主机、broker 节点相当于从机，主机类似 zk 功能)
   process.roles=broker, controller
   
   # 节点id
   node.id=1
   
   # controller 服务协议别名
   controller.listener.names=CONTROLLER
   
   # 全 controller 列表
   controller.quorum.voters=1@kafka-broker-1:9093,2@kafka-broker-2:9093,3@kafka-broker-3:9093
   
   # 不同服务器绑定的端口
   listeners=PLAINTEXT://:9092,CONTROLLER://:9093
   
   # broker 服务协议别名
   inter.broker.listener.name=PLAINTEXT
   
   # broker 对外暴露的地址
   advertised.listeners=PLAINTEXT://kafka-broker-1:9092
   
   # Kafka 数据存储目录
   log.dirs=/usr/local/kafka-kraft/datas
   ```

7. 分发 `server.properties` 配置到 `kafka-broker-2、kafka-broker-3` 节点

   ```bash
   xsync /usr/local/kafka-kraft/config/kraft/server.properties
   ```

   ```tex
   # 修改以下内容
   node.id=2（kafka-broker-2）
   node.id=3（kafka-broker-3）
   
   advertised.listeners=PLAINTEXT://kafka-broker-2:9092（kafka-broker-2）
   advertised.listeners=PLAINTEXT://kafka-broker-3:9092（kafka-broker-3）
   ```

8. 初始化集群数据目录

   - 在每个部署节点生成存储目录唯一 ID

     ```bash
     cd /usr/local/kafka-kraft
     ```

     ```bas
     # 记录下生成的 ID,比如我这里为: Q3IKiPcMQcWTOZIbrBRHWA
     bin/kafka-storage.sh random-uuid

   - 用生成的 ID 格式化每一个 Kafka 数据存储目录（三台服务器都要操作，使用同一个 ID）

9. 启动 Kafka 集群

   - 切换到  `/usr/local/kafka-kraft` 目录下

     ```bash
     cd /usr/local/kafka-kraft
     ```

   - 执行 `kraft` 启动脚本

     ```bash
     bin/kafka-server-start.sh -daemon config/kraft/server.properties
     ```

10. 停止 Kafka 集群

    ```bash
    bin/kafka-server-stop.sh
    ```

11. 封装 `kraft` 启停脚本

    - 在 `/root/bin` 目录下创建 `kfk-kraft.sh` 文件

      ```bash
      touch /root/bin/kfk-kraft.sh
      ```

    - `kfk-kraft.sh` 文件中添加以下内容

      ```bash
      #!/bin/bash
      
      case $1 in
      "start"){
      	for i in kafka-broker-1 kafka-broker-2 kafka-broker-3
      	do
      	echo ------------- 启动 $i kafka-kraft -------------
      		ssh $i "/usr/local/kafka-kraft/bin/kafka-server-start.sh -daemon /usr/local/kafka-kraft/config/kraft/server.properties"
      	done
      };;
      "stop"){
      	for i in kafka-broker-1 kafka-broker-2 kafka-broker-3
      	do
      	echo ------------- 停止 $i kafka-kraft -------------
      		ssh $i "/usr/local/kafka-kraft/bin/kafka-server-stop.sh"
      	done
      };;
      esac
      ```

    - 防火墙放行 `9093` 端口（三台服务器都要配置）

      ```bash
      firewall-cmd --zone=public --add-port=9093/tcp --permanent
      
      firewall-cmd --reload
      ```

    - 添加 `kfk-kraft.sh` 脚本权限

      ```bash
      chmod +x /root/bin/kfk-kraft.sh
      ```

    - 启动和停止集群

      ```bash
      # 启动
      kfk-kraft.sh start
      
      # 停止
      kfk-kraft.sh stop
      ```

## 十、Kafka 集成

### 10.1 大数据应用场景

#### 10.1.1 Flume 集成

Flume 也是日志采集器，类似于 ELK 中的 LogStash 软件功能。早期设计的功能就是通过 Flume 采集数据，然后将数据写入 HDFS 分布式文件存储系统，不过随着功能的扩展，现在也可以把采集的数据写入到 Kafka 中，作为实时数据使用。

1. 下载 Flume
   - Flume 官网地址：https://flume.apache.org/
   - Flume 文档地址：https://flume.apache.org/FlumeUserGuide.html
   - Flume 下载地址：https://archive.apache.org/dist/flume/

2. 安装部署 Flume

   - 上传 `Flume` 压缩包到 `/opt` 目录下

   - 将 `Flume` 压缩包解压至 `/usr/local` 目录下

     ```bash
     tar -zxvf apache-flume-1.10.1-bin.tar.gz -C /usr/local
     ```

   - 修改 `Flume` 目录名称

     ```bash
     mv /usr/local/apache-flume-1.10.1-bin/ /usr/local/flume
     ```

   - 生产环境下，将 `Flume` 的堆内存设置为 4G 或以上

     ```bash
     # 修改配置文件名称 
     mv /usr/local/flume/conf/flume-env.sh.template /usr/local/flume/conf/flume-env.sh
     ```

     ```bash
     # 在 /usr/local/flume/conf/flume-env.sh 文件中添加以下内容
     export JAVA_OPTS="-Xms4096m -Xmx4096m -Dcom.sun.management.jmxremote"
     ```

3. 增加集成配置

   1. 创建 job 目录

      ```bash
      mkdir -p /usr/local/flume/job
      ```

   2. 创建配置文件

      ```bash
      touch /usr/local/flume/job/file_to_kafka.conf
      ```

   3. `file_to_kafka.conf` 文件中添加以下内容

      ```properties
      # 定义组件
      a1.sources = r1
      a1.channels = c1
      
      # 配置 source
      a1.source.r1.type = TAILDIR
      a1.source.r1.filegroups = f1
      
      # 日志（数据）文件
      a1.source.r1.filegroups.f1 = /usr/local/flume/datas/test.log
      a1.source.r1.positionFile = /usr/local/flume/taidir_position.json
      
      # 配置 channel
      # 采用 Kafka channel,省去了 Sink,提高了效率
      a1.channels.c1.type = org.apache.flume.channel.kafka.KafkaChannel
      a1.channels.c1.kafka.bootstrap.servers = kafka-broker-1:9092,kafka-broker-2:9092,kafka-broker-3:9092
      a1.channels.c1.kafka.topic = test
      a1.channels.c1.parseAsFlumeEvent = false
      
      # 组装
      a1.source.r1.channels = c1
      ```

4. 集成测试

   - 启动 `zookeeper` 和 `Kafka` 集群

     ```bash
     /root/bin/cluster.sh start
     ```

   - 执行 `Flume` 操作采集数据到 `Kafka`

     ```bash
     /usr/local/flume/bin/flume-ng agent -n a1 -c conf/ -f job/file_to_kafka.conf
     ```

   
   可以通过向 Flume 的 `/usr/local/flume/datas/test.log` 文件中写入需要的日志内容，Kafka 后续就可以消费这些写入的日志数据

#### 10.1.2 SparkStreaming 集成

Spark 是一个分布式计算引擎，同时也是一款非常强大的离线分布式计算框架，其中的 SparkStreaming 模块用于准实时数据处理，其中就可以将 Kafka 作为数据源进行处理。

1. 创建一个 Spark 的  Maven 项目

2. 添加 `pom.xml` 依赖信息

   ```xml
   <?xml version="1.0" encoding="UTF-8"?>
   <project xmlns="http://maven.apache.org/POM/4.0.0"
            xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
            xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
       <modelVersion>4.0.0</modelVersion>
   
       <groupId>com.yu.kafka.spark</groupId>
       <artifactId>kafka-spark</artifactId>
       <version>1.0-SNAPSHOT</version>
   
       <properties>
           <maven.compiler.source>8</maven.compiler.source>
           <maven.compiler.target>8</maven.compiler.target>
           <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
           <spark.version>3.3.1</spark.version>
       </properties>
   
       <dependencies>
           <dependency>
               <groupId>org.apache.spark</groupId>
               <artifactId>spark-core_2.12</artifactId>
               <version>${spark.version}</version>
           </dependency>
   
           <dependency>
               <groupId>org.apache.spark</groupId>
               <artifactId>spark-streaming_2.12</artifactId>
               <version>${spark.version}</version>
           </dependency>
   
           <dependency>
               <groupId>org.apache.spark</groupId>
               <artifactId>spark-streaming-kafka-0-10_2.12</artifactId>
               <version>${spark.version}</version>
           </dependency>
       </dependencies>
   
   </project>
   ```

3. 添加测试代码

   ```java
   /**
    * Kafka集成SparkStreaming
    *
    * @author elonlo
    * @date 2024/7/13 23:32
    */
   public class KafkaSparkStreamingTest {
   
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
   
   	public static void main(String[] args) throws InterruptedException {
   
   		// 1.创建 spark 配置对象
   		SparkConf sparkConf = new SparkConf();
   		sparkConf.setMaster("local[*]");
   		sparkConf.setAppName("SparkStreaming");
   
   		// 2.创建环境对象
   		JavaStreamingContext ssc = new JavaStreamingContext(sparkConf, new Duration(3 * 1000));
   
   		// 3.使用 Kafka 作为数据源
   		// 配置 Kafka
   		Map<String, Object> map = new HashMap<>(16);
   		map.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVER);
   		map.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
   		map.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
   		map.put(ConsumerConfig.GROUP_ID_CONFIG, GROUP);
   		map.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "latest");
   
   		// 4.配置需要消费的主题
   		List<String> list = new ArrayList<>();
   		list.add(TOPIC);
   
   		JavaInputDStream<ConsumerRecord<Object, Object>> directStream = KafkaUtils.createDirectStream(
   				ssc,
   				LocationStrategies.PreferBrokers(),
   				ConsumerStrategies.Subscribe(list, map));
   
   		directStream.map((Function<ConsumerRecord<Object, Object>, Object>) ConsumerRecord::value)
   				.print(100);
   
   		ssc.start();
   		ssc.awaitTermination();
   	}
   }
   ```

4. 启动本地 Kafka 并生产数据

#### 10.1.3 Flink 集成

Flink 也是一款强大的实时分布式计算框架和引擎，可以将 Kafka 作为数据源处理。

1. 创建一个 Flink 的 Maven 项目

2. 添加 `pom.xml` 依赖信息

   ```xml
   <properties>
       <maven.compiler.source>8</maven.compiler.source>
       <maven.compiler.target>8</maven.compiler.target>
       <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
       <flink.version>1.17.0</flink.version>
   </properties>
   
   <dependencies>
       <dependency>
           <groupId>org.apache.flink</groupId>
           <artifactId>flink-java</artifactId>
           <version>${flink.version}</version>
       </dependency>
   
       <dependency>
           <groupId>org.apache.flink</groupId>
           <artifactId>flink-streaming-java</artifactId>
           <version>${flink.version}</version>
       </dependency>
   
       <dependency>
           <groupId>org.apache.flink</groupId>
           <artifactId>flink-clients</artifactId>
           <version>${flink.version}</version>
       </dependency>
   
       <dependency>
           <groupId>org.apache.flink</groupId>
           <artifactId>flink-connector-kafka</artifactId>
           <version>${flink.version}</version>
       </dependency>
   </dependencies>
   ```

3. 添加测试代码

   ```java
   /**
    * kafka集成flink
    *
    * @author elonlo
    * @date 2024/7/14 0:08
    */
   public class KafkaFlinkTest {
   
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
   
   
   	public static void main(String[] args) throws Exception {
   
   		StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
   
   		KafkaSource<String> kafkaSource = KafkaSource.<String>builder()
   				.setBootstrapServers(BOOTSTRAP_SERVER)
   				.setTopics(TOPIC)
   				.setGroupId(GROUP)
   				.setStartingOffsets(OffsetsInitializer.latest())
   				.setValueOnlyDeserializer(new SimpleStringSchema())
   				.build();
   
   		DataStreamSource<String> streamSource = env.fromSource(
   				kafkaSource,
   				WatermarkStrategy.noWatermarks(),
   				"kafka-source");
   
   		streamSource.print("kafka");
   
   		env.execute();
   	}
   }
   ```

4. 启动本地 Kafka 并生产数据

### 10.2 SpringBoot 集成

1. 创建一个 Kafka 的 SpringBoot 项目

   注意这里使用的是 SpringBoot3，要求 Java 17 为最低版本

2. 添加 `pom.xml` 依赖信息

   ```xml
   <properties>
       <java.version>17</java.version>
       <kafka.version>3.6.1</kafka.version>
       <fastjson.version>1.2.83</fastjson.version>
       <hutool.version>5.8.11</hutool.version>
   </properties>
   
   <dependencies>
       <dependency>
           <groupId>org.springframework.boot</groupId>
           <artifactId>spring-boot-starter</artifactId>
       </dependency>
   
       <dependency>
           <groupId>org.springframework.boot</groupId>
           <artifactId>spring-boot-starter-web</artifactId>
           <exclusions>
               <exclusion>
                   <groupId>org.springframework.boot</groupId>
                   <artifactId>spring-boot-starter-logging</artifactId>
               </exclusion>
           </exclusions>
       </dependency>
   
       <dependency>
           <groupId>org.springframework.boot</groupId>
           <artifactId>spring-boot-starter-test</artifactId>
           <scope>test</scope>
       </dependency>
   
       <!-- kafka 集成 spring 依赖 -->
       <dependency>
           <groupId>org.springframework.kafka</groupId>
           <artifactId>spring-kafka</artifactId>
       </dependency>
   
       <!-- kafka 客户端 -->
       <dependency>
           <groupId>org.apache.kafka</groupId>
           <artifactId>kafka-clients</artifactId>
           <version>${kafka.version}</version>
       </dependency>
   
       <dependency>
           <groupId>com.alibaba</groupId>
           <artifactId>fastjson</artifactId>
           <version>${fastjson.version}</version>
       </dependency>
   
       <dependency>
           <groupId>cn.hutool</groupId>
           <artifactId>hutool-json</artifactId>
           <version>${hutool.version}</version>
       </dependency>
   
       <dependency>
           <groupId>cn.hutool</groupId>
           <artifactId>hutool-db</artifactId>
           <version>${hutool.version}</version>
       </dependency>
   
       <dependency>
           <groupId>org.projectlombok</groupId>
           <artifactId>lombok</artifactId>
           <optional>true</optional>
       </dependency>
   </dependencies>
   
   <build>
       <plugins>
           <plugin>
               <groupId>org.springframework.boot</groupId>
               <artifactId>spring-boot-maven-plugin</artifactId>
           </plugin>
       </plugins>
   </build>
   ```

3. 添加配置信息

   ```yaml
   spring:
     kafka:
       bootstrap-servers: localhost:9092
       producer:
         acks: all
         batch-size: 16384
         buffer-memory: 33554432
         key-serializer: org.apache.kafka.common.serialization.StringSerializer
         value-serializer: org.apache.kafka.common.serialization.StringSerializer
         retries: 0
   
       consumer:
         # 消费者组
         group-id: test
         # 消费方式: 在有提交记录的时候, earliest 与 latest 是一样的,从有提交记录的下一条开始消费
         # earliest: 无提交记录,从头开始消费
         # latest:   无提交记录,从最新消息的下一条开始消费
         auto-offset-reset: latest
         # 是否自动提交偏移量 offset
         enable-auto-commit: true
         # 自动提交的频率,须先开启 enable-auto-commit 此配置才能生效
         auto-commit-interval: 1s
         key-deserializer: org.apache.kafka.common.serialization.StringDeserializer
         value-deserializer: org.apache.kafka.common.serialization.StringDeserializer
         max-poll-records: 2
         properties:
           # 如果在此时间内没有收到心跳,该消费者会被踢出组并触发组再平衡
           session.timeout.ms: 120000
           # 最大消费时间,获取消息后提交偏移量的最大时间,超过设定的时间(默认5分钟),
           max.poll.interval.ms: 300000
           # 配置控制客户端等待请求响应的最长时间
           # 如果在超时之前没有收到响应,客户端将在必要时重新发送请求
           # 并且如果重试次数用尽,则请求失败
           request.timeout.ms: 60000
           # 订阅或分配主题时,允许自动创建主题。0.11 之前,必须设置 false
           allow.auto.create.topics: true
           # poll 方法向协调器发送心跳的频率,为 session.timeout.ms 的三分之一
           heartbeat.interval.ms: 40000
           # 每个分区里返回的记录最多不超过 max.partitions.fetch.bytes 指定的字节
           # 0.10.1 版本之后,如果 fetch 的第一个非空分区中的第一条消息大于这个限制
           # 仍然会返回该消息,以确保消费者可以进行消费
   #        max.partitions.fetch.bytes: 1048576
   
       listener:
         # 当 enable-auto-commit 的值设置为 false 时,该配置会生效,为 true 时不生效
         # manual_immediate: 需要手动调用 Acknowledgment.acknowledge() 后立即提交
         ack-mode: manual_immediate
         # 如果至少有一个 topic 不存在, true: 启动失败,false: 忽略
         missing-topics-fatal: true
         # type: single,单条消费 batch,批量消费 批量消费需要配合 consumer.max-poll-records
         type: batch
         # 为每个消费者实例创建多少个线程,多出分区的线程空闲
         concurrency: 2
   
       template:
         default-topic: "test"
   
   server:
     port: 9999
   ```

4. 创建 Kafka 配置类

   ```java
   /**
    * kafka 配置类
    *
    * @author elonlo
    * @date 2024/7/14 10:28
    */
   public class KafkaConfig {
   
   	/**
   	 * 主题名称
   	 */
   	public static final String TOPIC = "test";
   
   	/**
   	 * 消费者组 id
   	 */
   	public static final String GROUP_ID = "test";
   }
   ```

5. 生产者代码

   ```java
   /**
    * kafka 生产者控制器
    *
    * @author elonlo
    * @date 2024/7/14 10:30
    */
   @Slf4j
   @RestController
   @RequestMapping("/kafka")
   public class KafkaProducerController {
   
   	private final KafkaTemplate<String, String> kafkaTemplate;
   
   	@Autowired
   	public KafkaProducerController(KafkaTemplate<String, String> kafkaTemplate) {
   		this.kafkaTemplate = kafkaTemplate;
   	}
   
   	@PostMapping( "/produce")
   	public String producer(@RequestBody Object obj) {
   
   		try {
   			String obj2String = JSONUtil.toJsonStr(obj);
   
   			// 生产者发送消息
   			kafkaTemplate.send(KafkaConfig.TOPIC, obj2String);
   
   			return "success";
   		} catch (Exception e) {
   			e.printStackTrace();
   		}
   
   		return "false";
   	}
   }
   ```

6. 消费者代码

   ```java
   /**
    * kafka 消费者
    *
    * @author elonlo
    * @date 2024/7/14 10:38
    */
   @Slf4j
   @Component
   public class KafkaDataConsumer {
   
   	/***
   	 * 监听主题并消费消息
   	 *
   	 * @param messages  生产者发送的消息
   	 * @param topic     消费者消费的主题
   	 */
   	@KafkaListener(topics = KafkaConfig.TOPIC, groupId = KafkaConfig.GROUP_ID)
   	public void consumeMessage(List<String> messages, @Header(KafkaHeaders.RECEIVED_TOPIC) String topic) {
   
   		for (String message : messages) {
   			final JSONObject object = JSONUtil.parseObj(message);
   
   			log.info("{}消费了{}主题发送的消息: {}", KafkaConfig.GROUP_ID, topic, object.getStr("data"));
   		}
   	}
   }
   ```

## 十一、Kafka 优化

### 11.1 资源配置

#### 11.1.1 操作系统

Kafka 的**网络客户端**底层使用 **Java NIO** 的 **Selector 方式**，而 Selector 在 Linux 的实现是 **epoll**，在 Windows 上实现机制是 **select**。因此 Kafka 部署在 Linux 上会有**更高效的 I/O 性能**。

数据在磁盘和网络之间进行传输的时候，在 Linux 上可以享受到**零拷贝**带来的**快捷和高效**，而 Windows **只会在一定程度**上使用零拷贝操作。所以建议 Kafka 部署在 Linux 操作系统上。

#### 11.1.2 磁盘选择

Kafka 存储方式为**顺序读写**，机械硬盘的最大劣势在于随机读写慢，所以使用**机械硬盘**并**不会造成 Kafka 性能降低**，选用普通的机械硬盘即可。Kafka 自身有冗余机制，而且通过分区的设计，实现了负载均衡的功能。所以不用组磁盘阵列也是可以的。

下面是一个简单估算 Kafka 磁盘空间的案例：

**设计场景**

日志数据每天向 Kafka 发送 1 亿条数据，每条数据有两个副本防止数据丢失，数据保存两周，每条消息平均大小为 1KB。

1. 每天 1 亿条 1KB 消息，保存两份，则每天大小为：

   (100000000 * 1KB * 2) / 1024 / 1024 ≈ 200GB

2. Kafka 除了消息数据还有其他类型的数据，故增加 10% 的冗余空间，则需要 220GB

3. 两周时间大约为 220GB * 14 ≈ 3TB

4. 如果启用压缩，压缩比在 0.75 左右，则总存储空间规划为 3TB * 0.75 = 2.25TB

#### 11.1.3 网络带宽

如果网络为万兆带宽，基本不会出现网络瓶颈，如果数据量特别大，可以按照下文中的设计场景进行计算。如果网络为百兆或者千兆带宽，在处理较大数据量场景下会出现网络瓶颈，可按照下面的传统经验公式进行计算处理，也可以通过下述场景在生产环境按照实际情况进行设计。

**计算公式：服务器台数 = 2 × （生产者峰值生产速率 × 副本数 ÷ 100） + 1**

**设计场景**

如果机房为千兆带宽，并且需要在一小时内处理 1TB 的数据，需要多少台 Kafka 服务器？

1. 由于带宽为千兆网络，1000Mbps = 1Gbps，则每秒钟每个服务器能收到的数据量为 1Gb = 1000Mb
2. 假设 Kafka 占用整个服务器网络的 70%（其他 30% 为别的服务预留），则 Kafka 可以使用到 700Mb 的带宽，但是如果从常规角度考虑，不能让 Kafka 一直处于满带宽的情况，所以要预留出 2/3 甚至 3/4 的资源，也就是说，Kafka 单台服务器的实际使用带宽应该为 700Mb / 3 = 240Mb
3. 1 小时需要处理 1TB 数据，1TB = 1024 * 1024 * 8Mb = 8000000Mb，则一秒钟处理数据量为：8000000Mb / 60 / 60 ≈ 2230Mb 数据
4. 需要的服务器台数为：2230Mb / 240Mb ≈ 10 台 
5. 考虑到消息的副本数如果为 2，则需要 20 台服务器，副本如果为 3，则需要 30 台服务器

#### 11.1.4 内存配置

Kafka 运行过程中涉及到的内存主要为 JVM 的堆内存和操作系统的页缓存，每个 Broker 节点的堆内存建议 10-15G 内存，而数据文件（默认为 1G）中的数据 25% 在内存就可以了。综上所述，Kafka 在大数据场景下能够流畅稳定运行至少需要 11G 内存，建议安装 Kafka 的服务器节点的内存至少大于等于 16G。

#### 11.1.5 CPU 选择

观察所有的 Kafka 与线程相关的配置，一共有以下几个：

| 参数名                            | 备注                                                   | 默认值 |
| --------------------------------- | ------------------------------------------------------ | ------ |
| num.network.threads               | 服务器用于接收来自网络的请求并网络发送响应的线程数     | 3      |
| num.io.threads                    | 服务器用于处理请求的线程数，可能包括磁盘 I/O           | 8      |
| num.replica.fetchers              | 副本拉取线程数，调大该值可以增加副本节点拉取的并行度   | 1      |
| num.recovery.threads.per.data.dir | 每个数据目录在启动时用于日志恢复和在关闭时刷新的线程数 | 1      |
| log.cleaner.threads               | 用于日志清理的后台线程数                               | 1      |
| background.threads                | 用于各种后台处理任务的线程数                           | 10     |

在 Kafka 生产环境中，建议 CPU 核数最少为 16 核，实际 32 核 以上，方可保证大数据环境中的 Kafka 集群正常处理和运行。

### 11.2 集群容错

#### 11.2.1 副本分配策略

Kafka 采用**分区机制**对数据进行**管理和存储**，每个 Topic 可以有**多个分区**，每个分区可以有**多个副本**。应该根据业务需求合理配置副本，一般建议设置至少 2 个副本以保证高可用性。

#### 11.2.2 故障转移方案

当 Kafka 集群中的**某个 Broker 节点发生故障**时，其负责的**分区副本**将会被**重新分配**到**其他存活的 Broker 节点**上，并且会**自动选择一个备份分区**作为新的**主分区**来处理消息的读写请求。

#### 11.2.3 数据备份和恢复

Kafka 采用基于**日志文件**的存储方式，每个 Broker 节点上都有**副本数据的本地备份**。在数据备份方面，可以通过配置 Kafka 的**数据保留策略**和**数据分区调整策略**来保证**数据的持久性和安全性**；在数据恢复方面，可以通过**查找备份数据**并进行相应的**分区副本替换**来恢复数据。

### 11.3 参数配置优化

| 参数名                                | 默认参数值    | 所在端 | 优化场景 | 备注                     |
| ------------------------------------- | ------------- | ------ | -------- | ------------------------ |
| num.network.threads                   | 3             | 服务端 | 低延迟   |                          |
| num.io.threads                        | 8             | 服务端 | 低延迟   |                          |
| socket.send.buffer.bytes              | 102400(100K)  | 服务端 | 高吞吐   |                          |
| socket.receiver.buffer.bytes          | 65536(64K)    | 服务端 | 高吞吐   |                          |
| max.in.flight.requests.per.connection | 5             | 生产端 | 幂等     |                          |
| buffer.memory                         | 33554432(32M) | 生产端 | 高吞吐   |                          |
| batch.size                            | 16384(16K)    | 生产端 | 高性能   |                          |
| linger.ms                             | 0             | 生产端 | 高性能   |                          |
| fetch.min.bytes                       | 1             | 消费端 | 高性能   | 网络交互次数             |
| max.poll.records                      | 500           | 消费端 | 批量处理 | 控制批量获取消息数量     |
| fetch.max.bytes                       | 57671680(55M) | 消费端 | 批量处理 | 控制批量获取消息字节大小 |

### 11.4 数据压缩和批量发送

通过压缩和批量发送可以优化 Kafka 的性能表现。Kafka 支持多种数据压缩算法，包括 Gzip、Snappy、lz4和zstd。在不同场景下，需要选择合适的压缩算法，以确保性能最优。

下面是常见的几种压缩算法的测试数据：

| 压缩算法 | 压缩比例 | 压缩效率 | 解压缩效率 |
| -------- | -------- | -------- | ---------- |
| snappy   | 2.073    | 580m/s   | 2020m/s    |
| lz4      | 2.101    | 800m/s   | 4220m/s    |
| zstd     | 2.884    | 520m/s   | 1600m/s    |

从表格数据可以看出，**zstd** 有着**最高压缩比**，而 **lz4** 算法在**吞吐量**上表现得**非常高效**。对于 Kafka 而言，在**吞吐量**上比较：**lz4 > snappy > zstd > gzip**；而在**压缩效率**上比较：**zstd > lz4 > gzip > snappy**。

Kafka 支持两种批处理方式：异步批处理和同步批处理。在不同场景下，需要选择合适的批处理方式，进行性能优化。同时需要合理设置批处理参数，比如 `batch.size`、`linger.ms` 等。

## 十二、Kafka 常见问题

1. Kafka 的 LSO、LEO、HW 的含义？

   LSO、LEO、HW 其实都是 Kafka 中偏移量。只不过它们代表的含义是不同的。

   **LSO**

   这里的 LSO 有两层含义：一个是 `Long Start Offset`，一个是 `Long Stable Offset`，`Long Start Offset` 表示数据文件的起始偏移量，比如 Kafka 中的 `.log` 文件名前面的数字就是当前文件 LSO；`Long Stable Offset` 表示的

   位移值是用来判断事务型消费者的可见性，就是所谓的事务隔离级别，一个叫 `read_commited`，一个叫 `read_uncommited`。

   **LEO**

   LEO 表示 Long End Offset，就是下一个要写入的数据偏移量，所以这个偏移量的数据是不存在的。

   **HW**

   HW 表示高水位偏移量的意思。是 Kafka 为了数据的一致性所增加的一种数据隔离方式。简单来说，就是消费者只能消费到小于高水位偏移量的数据。

2. Controller 选举是怎么实现的？

   这里的 Controller 选举主要指的是 Kafka 依赖于 ZK 实现的 Controller 选举机制，也就是说，Kafka 的所有 Broker 节点会监听 ZK 中的一个 Controller 临时节点，如果这个节点没有创建，那么 Broker 就会申请创建，一旦创建成功，那么创建的 Broker 就会当选为集群的管理者 Controller，一旦失去了和 ZK 的通信，那么临时节点就会消失，此时就会再次进行 Controller 的选举，选举的规则是完全一样的，一旦新的 Controller 被选举，那么 Controller 纪元会被更新。

3. 分区副本 AR、ISR、OSR 的含义？

   **AR**

   可以理解为分区的所有副本集合。

   **ISR**

   表示的是正在同步数据的副本列表，列表的第一个就是分区的 Leader 副本，其他的副本就是 Follower 副本。

   **OSR**

   就是没有处于同步数据的副本列表。一旦副本拉取数据满足了特定的条件，那么会从 OSR 中移出并增加到 ISR 中；同样，如果副本没有拉取数据满足了特定的条件，就会从 ISR 中移出，放入到 OSR 中。这就是所谓的 ISR 列表的收缩和扩张。Kafka 使用这种 ISR 的方式有效的权衡了数据可靠性和性能之间的关系。

4. Producer 的 ACK 应答策略

   ACK 应答机制就是生产者发送数据后 Kafka 的接收确认方式。Kafka 确认的方式有以下三种：

   - **acks=0**

     当生产者数据发送到网络客户端缓冲区后，Kafka 就认为数据收到了，那么就会进行响应，也就是应答。但是这种方式数据可靠性是非常低的，因为不能保证数据一定会写入日志文件，但是发送效率影响不大。

   - **acks=1**

     当主题分区的 Leader 副本将数据写入日志后，Kafka 才认为数据收到了，然后再对生产者进行响应。这种方式发送的效率会降低，但是可靠性会高一些。

   - **acks=-1或acks=all**

     当主题分区的 ISR 副本列表中所有的副本都已经将数据写入日志后，Kafka 才认为数据收到了，然后再对生产者进行响应。这种方式的发送效率会非常低，生产者对象可以根据生产环境和业务需求对应答机制进行配置。

   生产者数据幂等性操作要求 ACK 应答处理机制必须为 -1，而 ACK 的参数默认值也是 -1。

5. Producer 消息重复或消息丢失的原因？

   主要就是 Kafka 为了提高数据可靠性所提供的重试机制，如果禁用重试机制，那么一旦数据发送失败，数据就丢失了。而数据重复，恰恰是因为开启重试机制后，如果因为网络阻塞或不稳定，导致数据重复发送，那么数据就有可能是重复的。所以 Kafka 提供了幂等性操作解决数据重复，并且幂等性操作要求必须开启重试功能和 ACK 取值为 -1，这样数据就不会丢失了。

   Kafka 提供的幂等性操作只能保证同一个生产者会话中同一个分区中的数据不会重复，一旦数据发送过程中，生产者对象重启，那么幂等性操作就会失效。那么此时就需要使用 Kafka 的事务功能来解决跨会话的幂等性操作。但是跨分区的幂等性操作是无法实现的。

6. Consumer 消息重复或消息丢失的原因？

   消费者为了防止意外情况下，重启之后不知道从哪里开始消费，所以会每 5s 时间自动保存偏移量。但是这种自动保存偏移量的操作是基于时间的，一旦未达到时间，消费者重启了，那么消费者就可能重复消费数据。

   Kafka 提供自动保存偏移量的同时，也提供了手动保存偏移量的两种方式：一个是同步提交，一个是异步提交。本质上都是提交一批数据的最后一个偏移量的值，但是可能会出现偏移量提交完毕，但是拉取的数据未处理完毕，消费者重启了。那么此时有的数据就消费不到了，也就是所谓的消息丢失。

7. Kafka 数据如何保证有序？

   一般来说有序指的是生产有序、存储有序、消费有序。

   **生产有序**

   就是生产者对象需要给数据增加序列号用于标记数据的顺序，然后在服务端进行缓存数据的比对，一旦发现数据是乱序的，那么就需要让生产者客户端进行数据的排序，然后重新发送数据，保证数据的有序。不过这里的缓存数据的比对，最多只能有 5 条数据进行比对，所以生产者客户端需要配置参数，将在途请求缓冲区的请求队列数据设置为 5，否则数据依然可能乱序。因为服务端的缓存数据是以分区为单位的，所以这就要求生产者客户端将数据发送到一个分区中，如果数据发送到多个分区，是无法保证顺序的，这就是生产有序。

   **存储有序**

   指的是 Kafka 服务端获取到数据后会将数据顺序写入到日志文件，这样就保证了存储有序，当然也只能保证一个分区的存储有序。

   **消费有序**

   其实就是 Kafka 在存储数据时会给数据增加一个访问的偏移量值，那么消费者只能按照偏移量的方式顺序访问，并且一个分区的数据只能被消费者组中的一个消费者消费，按照偏移量方式的读取就不会出现乱序的情况。



