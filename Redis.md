### 1、NoSQL是什么

- NoSQL(NoSQL = Not Only SQL)，意即"不仅仅是SQL"
- 泛指菲关系型数据库。随着互联网web2.0网站的兴起，传统的关系型数据库在应付web2.0网站，特别是超大规模和高并发的SNS类型的web2.0纯动态网站已经显得力不从心，暴露了很多难以克服的问题，而非关系型的数据库则由于其本身的特点得到了非常迅速的发展。NoSQL数据库的产生就是为了解决大规模集合多重数据种类带来的挑战，尤其是大数据应用难题，包括超大规模数据的存储
- (例如Google或Facebook每天为他们的用户收集万亿比特的数据)。这些类型的数据存储不需要固定的模式，无需多余操作就可以横向扩展

### 2、NoSQL的特点

- 易扩展
  - NoSQL数据库的种类繁多，但是有一个共同的特点都是去掉关系型数据库的关系型特性
  - 数据之间无关系，这样就非常容易扩展。也在无形之间，在架构的层面上带来了可扩展的能力
- 大数据量高性能
  - NoSQL数据库都具有非常高的读写能力，尤其在大数据量下，同样表现优秀，这得益于它的无关系性，数据库的结构简单
  - 一般MySQL使用Query Cache，每次表的更新Cache就失效，是一种大粒度的Cache，在针对web2.0的交互频繁的应用，Cache性能不高。而NoSQL的Cache是记录级的，是一种细粒度的Cache，所以NoSQL在这个层面上来说就要性能高很多了
- 多样灵活的数据模型
  - NoSQL无需事先为要存储的数据建立字段，随时可以存储自定义的数据格式。而关系数据库里，增删字段是一件非常麻烦的事情，如果是非常大数据量的表，增加字段简直是一个噩梦

### 3、NoSQL的CAP原理

- Consistency(强一致性)
- Availability(可用性)
- Partition tolerance(分区容错性)

### 4、CAP的3进2

- CAP理论就是说在分布式存储系统中，最多只能实现上面的两点，而由于当前的网络硬件肯定会出现延迟丢包等问题，所以<span style='color:red'>分区容错性是我们必须要实现的</span>，所以我们只能在一致性和可用性之间进行权衡，没有NoSQL系统能够同时保证这三点
- C：强一致性    A：高可用性    P：分布式容忍性
- CA    传统Oracle数据库
- AP    大多数网站架构的选择
- CP    Redis、MongoDB

### 5、CAP理论

- CAP理论的核心是：一个分布式系统不可能同时很好的满足一致性，可用性和分区容错性这三个需求，<span style='color:red'>最多只能同时较好的满足两个</span>
- 因此，根据CAP原理将NoSQL数据库分为了满足CA原则、满足CP原则和满足AP原则三大类：
  - CA - 单点集群，满足一致性，可用性的系统，通常在可扩展性上不太强大
  - CP - 满足一致性，分区容错性的系统，通常性能不是特别高
  - AP - 满足可用性、分区容错性的系统，通常可能对一致性要求低一些

### 6、Redis是什么

- Redis:REmote DIctionary Server(远程字典服务器)
- 是完全开源免费的，用C语言编写的，遵守BSD协议，是一个高性能的(key/value)分布式内存数据库，基于内存运行并支持持久化的NoSQL数据库，是当前最热门的NoSQL数据库之一，也被人们称为数据结构服务器
- Redis与其他key-value缓存产品有以下三个特点：
  - Redis支持数据的格式化，可以将内存中的数据保存在磁盘中，重启的时候可以再次加载进行使用
  - Redis不仅仅支持简单的key-value类型的数据，同时还提供list，set，zset，hash等数据结构的存储
  - Redis支持数据的备份，即master-slave模式的数据备份

### 7、Redis的作用

1. 内存存储和持久化：redis支持异步将内存中的数据写到硬盘上，同时不影响继续服务
2. 取最新N个数据的操作：如可以将最新的10条评论的ID放在Redis的list集合里面
3. 模拟类似于HttpSession这种需要设定过期时间的功能
4. 发布、订阅消息系统
5. 定时器、计数器

### 8、Redis安装(Linux下)

1. Redis官网    https://redis.io/或http://redis.cn/
2. Redis历史版本下载    http://download.redis.io/releases/
3. 下载后将redis安装包传输到linux的/opt/目录下
4. 使用tar -zxvf redis安装包进行解压
5. 解压后进入到redis压缩包下，执行make命令进行安装，如失败，则先安装gcc环境
6. 安装gcc指令    yum install gcc-c++
7. 如提示Jemalloc/Jemalloc.h：没有那个文件和目录，运行make distclean之后再make
8. 保险起见，可以再执行make install命令

### 9、启动Redis

1. 进入redis安装包目录，cd /opt/redis-5.0.0
2. 拷贝redis配置文件到自定义文件夹中，cp /opt/redis-5.0.0/redis.conf /myredis/
3. 启动redis服务端，redis-server /myredis/redis.conf
4. 启动redis客户端，redis-cli -p 6379
5. 执行ping命令，若出现pong则表示连接到redis成功

### 10、Redis性能测试

- redis 性能测试的基本命令如下：

```
redis-benchmark [option] [option value]
```

- redis 性能测试工具可选参数如下所示：

| 序号 | 选项      | 描述                                       | 默认值    |
| ---- | --------- | ------------------------------------------ | --------- |
| 1    | **-h**    | 指定服务器主机名                           | 127.0.0.1 |
| 2    | **-p**    | 指定服务器端口                             | 6379      |
| 3    | **-s**    | 指定服务器 socket                          |           |
| 4    | **-c**    | 指定并发连接数                             | 50        |
| 5    | **-n**    | 指定请求数                                 | 10000     |
| 6    | **-d**    | 以字节的形式指定 SET/GET 值的数据大小      | 2         |
| 7    | **-k**    | 1=keep alive 0=reconnect                   | 1         |
| 8    | **-r**    | SET/GET/INCR 使用随机 key, SADD 使用随机值 |           |
| 9    | **-P**    | 通过管道传输 <numreq> 请求                 | 1         |
| 10   | **-q**    | 强制退出 redis。仅显示 query/sec 值        |           |
| 11   | **--csv** | 以 CSV 格式输出                            |           |
| 12   | **-l**    | 生成循环，永久执行测试                     |           |
| 13   | **-t**    | 仅运行以逗号分隔的测试命令列表。           |           |
| 14   | **-I**    | Idle 模式。仅打开 N 个 idle 连接并等待。   |           |



### 11、Redis基本知识

- Redis是单进程的
  - 单进程来处理客户端的请求，对读写事件的响应
  - 是通过对epoll函数的包装来做到的，Redis的实际处理速度完全依赖主进程的执行效率
  - Epoll是Linux内核为处理大批量文件描述符而做了改进的epoll，是Linux下多路复用IO接口select/poll的增强版本，它能显著提高程序在大量并发连接中只有少量活跃的情况下的系统CPU利用率
- Redis默认有16个数据库，类似数组下标从零开始，默认使用零号库
- Select：用于切换数据库
- Dbsize：查看当前数据库key的数量
- Flushdb：清空当前库
- Flushall：清空所有库
- 统一密码管理，16个库都是同一密码，要么都OK要么一个都连不上
- Redis索引都是从零开始
- 默认端口6379

### 12、Redis五种基本数据类型

- String(字符串)
  - String是Redis最基本的类型，可以理解为与Memcached一模一样的类型，一个key对应一个value
  - String类型是二进制安全的。意思是redis的String类型可以包含任何数据。比如jpg图片或者序列化的对象
  - 一个Redis中字符串的value最多可以是512M
- List(列表)
  - Redis列表是简单的字符串列表，按照插入顺序排序，你可以添加一个元素到列表的头部(左边)或者尾部(右边)，底层实际是链表
- Set(集合)
  - Redis的Set是String类型的无序集合，它是通过HashMap实现的
- Hash(哈希)
  - Redis Hash是一个键值对集合
  - Redis Hash是一个String类型的field和value的映射表，Hash特别适合用于存储对象
  - 类似Java里面的Map<String，Object>
- Zset(sorted set：有序集合)
  - Redis Zset和Set一样也是一个String类型元素的集合，且不允许重复的成员
  - Zset的每个元素都会关联一个double类型的分数
  - Redis正是通过分数来为集合中的成员进行从小到大的排序。Zset的成员是唯一的，但分数(score)可以重复

### 13、Redis常见数据类型操作命令

- 官方文档，https://www.redis.net.cn/order/

- Redis键(key)常用命令：


| 序号 | 命令及描述                                                   |
| ---- | ------------------------------------------------------------ |
| 1    | [DEL key](https://www.runoob.com/redis/keys-del.html) 该命令用于在 key 存在时删除 key。 |
| 2    | [DUMP key](https://www.runoob.com/redis/keys-dump.html) 序列化给定 key ，并返回被序列化的值。 |
| 3    | [EXISTS key](https://www.runoob.com/redis/keys-exists.html) 检查给定 key 是否存在。 |
| 4    | [EXPIRE key](https://www.runoob.com/redis/keys-expire.html) seconds 为给定 key 设置过期时间，以秒计。 |
| 5    | [EXPIREAT key timestamp](https://www.runoob.com/redis/keys-expireat.html) EXPIREAT 的作用和 EXPIRE 类似，都用于为 key 设置过期时间。 不同在于 EXPIREAT 命令接受的时间参数是 UNIX 时间戳(unix timestamp)。 |
| 6    | [PEXPIRE key milliseconds](https://www.runoob.com/redis/keys-pexpire.html) 设置 key 的过期时间以毫秒计。 |
| 7    | [PEXPIREAT key milliseconds-timestamp](https://www.runoob.com/redis/keys-pexpireat.html) 设置 key 过期时间的时间戳(unix timestamp) 以毫秒计 |
| 8    | [KEYS pattern](https://www.runoob.com/redis/keys-keys.html) 查找所有符合给定模式( pattern)的 key 。 |
| 9    | [MOVE key db](https://www.runoob.com/redis/keys-move.html) 将当前数据库的 key 移动到给定的数据库 db 当中。 |
| 10   | [PERSIST key](https://www.runoob.com/redis/keys-persist.html) 移除 key 的过期时间，key 将持久保持。 |
| 11   | [PTTL key](https://www.runoob.com/redis/keys-pttl.html) 以毫秒为单位返回 key 的剩余的过期时间。 |
| 12   | [TTL key](https://www.runoob.com/redis/keys-ttl.html) 以秒为单位，返回给定 key 的剩余生存时间(TTL, time to live)。 |
| 13   | [RANDOMKEY](https://www.runoob.com/redis/keys-randomkey.html) 从当前数据库中随机返回一个 key 。 |
| 14   | [RENAME key newkey](https://www.runoob.com/redis/keys-rename.html) 修改 key 的名称 |
| 15   | [RENAMENX key newkey](https://www.runoob.com/redis/keys-renamenx.html) 仅当 newkey 不存在时，将 key 改名为 newkey 。 |
| 16   | [SCAN cursor [MATCH pattern\] [COUNT count]](https://www.runoob.com/redis/keys-scan.html) 迭代数据库中的数据库键。 |
| 17   | [TYPE key](https://www.runoob.com/redis/keys-type.html) 返回 key 所储存的值的类型。 |



- Redis字符串(String)常用命令：

  | 序号 | 命令及描述                                                   |
  | ---- | ------------------------------------------------------------ |
  | 1    | [SET key value](https://www.runoob.com/redis/strings-set.html) 设置指定 key 的值 |
  | 2    | [GET key](https://www.runoob.com/redis/strings-get.html) 获取指定 key 的值。 |
  | 3    | [GETRANGE key start end](https://www.runoob.com/redis/strings-getrange.html) 返回 key 中字符串值的子字符 |
  | 4    | [GETSET key value](https://www.runoob.com/redis/strings-getset.html) 将给定 key 的值设为 value ，并返回 key 的旧值(old value)。 |
  | 5    | [GETBIT key offset](https://www.runoob.com/redis/strings-getbit.html) 对 key 所储存的字符串值，获取指定偏移量上的位(bit)。 |
  | 6    | [MGET key1 [key2..\]](https://www.runoob.com/redis/strings-mget.html) 获取所有(一个或多个)给定 key 的值。 |
  | 7    | [SETBIT key offset value](https://www.runoob.com/redis/strings-setbit.html) 对 key 所储存的字符串值，设置或清除指定偏移量上的位(bit)。 |
  | 8    | [SETEX key seconds value](https://www.runoob.com/redis/strings-setex.html) 将值 value 关联到 key ，并将 key 的过期时间设为 seconds (以秒为单位)。 |
  | 9    | [SETNX key value](https://www.runoob.com/redis/strings-setnx.html) 只有在 key 不存在时设置 key 的值。 |
  | 10   | [SETRANGE key offset value](https://www.runoob.com/redis/strings-setrange.html) 用 value 参数覆写给定 key 所储存的字符串值，从偏移量 offset 开始。 |
  | 11   | [STRLEN key](https://www.runoob.com/redis/strings-strlen.html) 返回 key 所储存的字符串值的长度。 |
  | 12   | [MSET key value [key value ...\]](https://www.runoob.com/redis/strings-mset.html) 同时设置一个或多个 key-value 对。 |
  | 13   | [MSETNX key value [key value ...\]](https://www.runoob.com/redis/strings-msetnx.html) 同时设置一个或多个 key-value 对，当且仅当所有给定 key 都不存在。 |
  | 14   | [PSETEX key milliseconds value](https://www.runoob.com/redis/strings-psetex.html) 这个命令和 SETEX 命令相似，但它以毫秒为单位设置 key 的生存时间，而不是像 SETEX 命令那样，以秒为单位。 |
  | 15   | [INCR key](https://www.runoob.com/redis/strings-incr.html) 将 key 中储存的数字值增一。 |
  | 16   | [INCRBY key increment](https://www.runoob.com/redis/strings-incrby.html) 将 key 所储存的值加上给定的增量值（increment） 。 |
  | 17   | [INCRBYFLOAT key increment](https://www.runoob.com/redis/strings-incrbyfloat.html) 将 key 所储存的值加上给定的浮点增量值（increment） 。 |
  | 18   | [DECR key](https://www.runoob.com/redis/strings-decr.html) 将 key 中储存的数字值减一。 |
  | 19   | [DECRBY key decrement](https://www.runoob.com/redis/strings-decrby.html) key 所储存的值减去给定的减量值（decrement） 。 |
  | 20   | [APPEND key value](https://www.runoob.com/redis/strings-append.html) 如果 key 已经存在并且是一个字符串， APPEND 命令将指定的 value 追加到该 key 原来值（value）的末尾。 |

  

- Redis列表(List)常用命令：

  | 序号 | 命令及描述                                                   |
  | ---- | ------------------------------------------------------------ |
  | 1    | [BLPOP key1 [key2 \] timeout](https://www.runoob.com/redis/lists-blpop.html) 移出并获取列表的第一个元素， 如果列表没有元素会阻塞列表直到等待超时或发现可弹出元素为止。 |
  | 2    | [BRPOP key1 [key2 \] timeout](https://www.runoob.com/redis/lists-brpop.html) 移出并获取列表的最后一个元素， 如果列表没有元素会阻塞列表直到等待超时或发现可弹出元素为止。 |
  | 3    | [BRPOPLPUSH source destination timeout](https://www.runoob.com/redis/lists-brpoplpush.html) 从列表中弹出一个值，将弹出的元素插入到另外一个列表中并返回它； 如果列表没有元素会阻塞列表直到等待超时或发现可弹出元素为止。 |
  | 4    | [LINDEX key index](https://www.runoob.com/redis/lists-lindex.html) 通过索引获取列表中的元素 |
  | 5    | [LINSERT key BEFORE\|AFTER pivot value](https://www.runoob.com/redis/lists-linsert.html) 在列表的元素前或者后插入元素 |
  | 6    | [LLEN key](https://www.runoob.com/redis/lists-llen.html) 获取列表长度 |
  | 7    | [LPOP key](https://www.runoob.com/redis/lists-lpop.html) 移出并获取列表的第一个元素 |
  | 8    | [LPUSH key value1 [value2\]](https://www.runoob.com/redis/lists-lpush.html) 将一个或多个值插入到列表头部 |
  | 9    | [LPUSHX key value](https://www.runoob.com/redis/lists-lpushx.html) 将一个值插入到已存在的列表头部 |
  | 10   | [LRANGE key start stop](https://www.runoob.com/redis/lists-lrange.html) 获取列表指定范围内的元素 |
  | 11   | [LREM key count value](https://www.runoob.com/redis/lists-lrem.html) 移除列表元素 |
  | 12   | [LSET key index value](https://www.runoob.com/redis/lists-lset.html) 通过索引设置列表元素的值 |
  | 13   | [LTRIM key start stop](https://www.runoob.com/redis/lists-ltrim.html) 对一个列表进行修剪(trim)，就是说，让列表只保留指定区间内的元素，不在指定区间之内的元素都将被删除。 |
  | 14   | [RPOP key](https://www.runoob.com/redis/lists-rpop.html) 移除列表的最后一个元素，返回值为移除的元素。 |
  | 15   | [RPOPLPUSH source destination](https://www.runoob.com/redis/lists-rpoplpush.html) 移除列表的最后一个元素，并将该元素添加到另一个列表并返回 |
  | 16   | [RPUSH key value1 [value2\]](https://www.runoob.com/redis/lists-rpush.html) 在列表中添加一个或多个值 |
  | 17   | [RPUSHX key value](https://www.runoob.com/redis/lists-rpushx.html) 为已存在的列表添加值 |

  

- Redis集合(Set)常用命令：

  | 序号 | 命令及描述                                                   |
  | ---- | ------------------------------------------------------------ |
  | 1    | [SADD key member1 [member2\]](https://www.runoob.com/redis/sets-sadd.html) 向集合添加一个或多个成员 |
  | 2    | [SCARD key](https://www.runoob.com/redis/sets-scard.html) 获取集合的成员数 |
  | 3    | [SDIFF key1 [key2\]](https://www.runoob.com/redis/sets-sdiff.html) 返回第一个集合与其他集合之间的差异。 |
  | 4    | [SDIFFSTORE destination key1 [key2\]](https://www.runoob.com/redis/sets-sdiffstore.html) 返回给定所有集合的差集并存储在 destination 中 |
  | 5    | [SINTER key1 [key2\]](https://www.runoob.com/redis/sets-sinter.html) 返回给定所有集合的交集 |
  | 6    | [SINTERSTORE destination key1 [key2\]](https://www.runoob.com/redis/sets-sinterstore.html) 返回给定所有集合的交集并存储在 destination 中 |
  | 7    | [SISMEMBER key member](https://www.runoob.com/redis/sets-sismember.html) 判断 member 元素是否是集合 key 的成员 |
  | 8    | [SMEMBERS key](https://www.runoob.com/redis/sets-smembers.html) 返回集合中的所有成员 |
  | 9    | [SMOVE source destination member](https://www.runoob.com/redis/sets-smove.html) 将 member 元素从 source 集合移动到 destination 集合 |
  | 10   | [SPOP key](https://www.runoob.com/redis/sets-spop.html) 移除并返回集合中的一个随机元素 |
  | 11   | [SRANDMEMBER key [count\]](https://www.runoob.com/redis/sets-srandmember.html) 返回集合中一个或多个随机数 |
  | 12   | [SREM key member1 [member2\]](https://www.runoob.com/redis/sets-srem.html) 移除集合中一个或多个成员 |
  | 13   | [SUNION key1 [key2\]](https://www.runoob.com/redis/sets-sunion.html) 返回所有给定集合的并集 |
  | 14   | [SUNIONSTORE destination key1 [key2\]](https://www.runoob.com/redis/sets-sunionstore.html) 所有给定集合的并集存储在 destination 集合中 |
  | 15   | [SSCAN key cursor [MATCH pattern\] [COUNT count]](https://www.runoob.com/redis/sets-sscan.html) 迭代集合中的元素 |

  

- Redis哈希(Hash)常用命令：

  | 序号 | 命令及描述                                                   |
  | ---- | ------------------------------------------------------------ |
  | 1    | [HDEL key field1 [field2\]](https://www.runoob.com/redis/hashes-hdel.html) 删除一个或多个哈希表字段 |
  | 2    | [HEXISTS key field](https://www.runoob.com/redis/hashes-hexists.html) 查看哈希表 key 中，指定的字段是否存在。 |
  | 3    | [HGET key field](https://www.runoob.com/redis/hashes-hget.html) 获取存储在哈希表中指定字段的值。 |
  | 4    | [HGETALL key](https://www.runoob.com/redis/hashes-hgetall.html) 获取在哈希表中指定 key 的所有字段和值 |
  | 5    | [HINCRBY key field increment](https://www.runoob.com/redis/hashes-hincrby.html) 为哈希表 key 中的指定字段的整数值加上增量 increment 。 |
  | 6    | [HINCRBYFLOAT key field increment](https://www.runoob.com/redis/hashes-hincrbyfloat.html) 为哈希表 key 中的指定字段的浮点数值加上增量 increment 。 |
  | 7    | [HKEYS key](https://www.runoob.com/redis/hashes-hkeys.html) 获取所有哈希表中的字段 |
  | 8    | [HLEN key](https://www.runoob.com/redis/hashes-hlen.html) 获取哈希表中字段的数量 |
  | 9    | [HMGET key field1 [field2\]](https://www.runoob.com/redis/hashes-hmget.html) 获取所有给定字段的值 |
  | 10   | [HMSET key field1 value1 [field2 value2 \]](https://www.runoob.com/redis/hashes-hmset.html) 同时将多个 field-value (域-值)对设置到哈希表 key 中。 |
  | 11   | [HSET key field value](https://www.runoob.com/redis/hashes-hset.html) 将哈希表 key 中的字段 field 的值设为 value 。 |
  | 12   | [HSETNX key field value](https://www.runoob.com/redis/hashes-hsetnx.html) 只有在字段 field 不存在时，设置哈希表字段的值。 |
  | 13   | [HVALS key](https://www.runoob.com/redis/hashes-hvals.html) 获取哈希表中所有值。 |
  | 14   | [HSCAN key cursor [MATCH pattern\] [COUNT count]](https://www.runoob.com/redis/hashes-hscan.html) 迭代哈希表中的键值对。 |

  

- Redis有序集合(sorted set)常用命令：

  | 序号 | 命令及描述                                                   |
  | ---- | ------------------------------------------------------------ |
  | 1    | [ZADD key score1 member1 [score2 member2\]](https://www.runoob.com/redis/sorted-sets-zadd.html) 向有序集合添加一个或多个成员，或者更新已存在成员的分数 |
  | 2    | [ZCARD key](https://www.runoob.com/redis/sorted-sets-zcard.html) 获取有序集合的成员数 |
  | 3    | [ZCOUNT key min max](https://www.runoob.com/redis/sorted-sets-zcount.html) 计算在有序集合中指定区间分数的成员数 |
  | 4    | [ZINCRBY key increment member](https://www.runoob.com/redis/sorted-sets-zincrby.html) 有序集合中对指定成员的分数加上增量 increment |
  | 5    | [ZINTERSTORE destination numkeys key [key ...\]](https://www.runoob.com/redis/sorted-sets-zinterstore.html) 计算给定的一个或多个有序集的交集并将结果集存储在新的有序集合 destination 中 |
  | 6    | [ZLEXCOUNT key min max](https://www.runoob.com/redis/sorted-sets-zlexcount.html) 在有序集合中计算指定字典区间内成员数量 |
  | 7    | [ZRANGE key start stop [WITHSCORES\]](https://www.runoob.com/redis/sorted-sets-zrange.html) 通过索引区间返回有序集合指定区间内的成员 |
  | 8    | [ZRANGEBYLEX key min max [LIMIT offset count\]](https://www.runoob.com/redis/sorted-sets-zrangebylex.html) 通过字典区间返回有序集合的成员 |
  | 9    | [ZRANGEBYSCORE key min max [WITHSCORES\] [LIMIT]](https://www.runoob.com/redis/sorted-sets-zrangebyscore.html) 通过分数返回有序集合指定区间内的成员 |
  | 10   | [ZRANK key member](https://www.runoob.com/redis/sorted-sets-zrank.html) 返回有序集合中指定成员的索引 |
  | 11   | [ZREM key member [member ...\]](https://www.runoob.com/redis/sorted-sets-zrem.html) 移除有序集合中的一个或多个成员 |
  | 12   | [ZREMRANGEBYLEX key min max](https://www.runoob.com/redis/sorted-sets-zremrangebylex.html) 移除有序集合中给定的字典区间的所有成员 |
  | 13   | [ZREMRANGEBYRANK key start stop](https://www.runoob.com/redis/sorted-sets-zremrangebyrank.html) 移除有序集合中给定的排名区间的所有成员 |
  | 14   | [ZREMRANGEBYSCORE key min max](https://www.runoob.com/redis/sorted-sets-zremrangebyscore.html) 移除有序集合中给定的分数区间的所有成员 |
  | 15   | [ZREVRANGE key start stop [WITHSCORES\]](https://www.runoob.com/redis/sorted-sets-zrevrange.html) 返回有序集中指定区间内的成员，通过索引，分数从高到低 |
  | 16   | [ZREVRANGEBYSCORE key max min [WITHSCORES\]](https://www.runoob.com/redis/sorted-sets-zrevrangebyscore.html) 返回有序集中指定分数区间内的成员，分数从高到低排序 |
  | 17   | [ZREVRANK key member](https://www.runoob.com/redis/sorted-sets-zrevrank.html) 返回有序集合中指定成员的排名，有序集成员按分数值递减(从大到小)排序 |
  | 18   | [ZSCORE key member](https://www.runoob.com/redis/sorted-sets-zscore.html) 返回有序集中，成员的分数值 |
  | 19   | [ZUNIONSTORE destination numkeys key [key ...\]](https://www.runoob.com/redis/sorted-sets-zunionstore.html) 计算给定的一个或多个有序集的并集，并存储在新的 key 中 |
  | 20   | [ZSCAN key cursor [MATCH pattern\] [COUNT count]](https://www.runoob.com/redis/sorted-sets-zscan.html) 迭代有序集合中的元素（包括元素成员和元素分值） |

  

### 14、Redis两种特殊数据类型

**1、Redis HyperLogLog**：Redis 在 2.8.9 版本添加了 HyperLogLog 结构。

- Redis HyperLogLog 是用来做基数统计的算法，HyperLogLog 的优点是，在输入元素的数量或者体积非常非常大时，计算基数所需的空间总是固定 的、并且是很小的。
- 在 Redis 里面，每个 HyperLogLog 键只需要花费 12 KB 内存，就可以计算接近 2^64 个不同元素的基 数。这和计算基数时，元素越多耗费内存就越多的集合形成鲜明对比。
- 但是，因为 HyperLogLog 只会根据输入元素来计算基数，而不会储存输入元素本身，所以 HyperLogLog 不能像集合那样，返回输入的各个元素。

```markdown
#  基数
比如数据集 {1, 3, 5, 7, 5, 7, 8}， 那么这个数据集的基数集为 {1, 3, 5 ,7, 8}, 基数(不重复元素)为5。 基数估计就是在误差可接受的范围内，快速计算基数。
```

```markdown
127.0.0.1:6379> pfadd runoobkey mysql
(integer) 1
127.0.0.1:6379> pfadd runoobkey oracle
(integer) 1
127.0.0.1:6379> pfcount runoobkey
(integer) 4
127.0.0.1:6379> pfmerge mysql oracle
OK
```



- 下表列出了 redis HyperLogLog 的基本命令：

  | 序号 | 命令                                                         | 描述                                |
  | ---- | ------------------------------------------------------------ | ----------------------------------- |
  | 1    | [PFADD key element [element ...\]](https://www.runoob.com/redis/hyperloglog-pfadd.html) | 添加指定元素到 HyperLogLog 中。     |
  | 2    | [PFCOUNT key [key ...\]](https://www.runoob.com/redis/hyperloglog-pfcount.html) | 返回给定 HyperLogLog 的基数估算值。 |
  | 3    | [PFMERGE destkey sourcekey [sourcekey ...\]](https://www.runoob.com/redis/hyperloglog-pfmerge.html)  HyperLogLog | 将多个 HyperLogLog 合并为一个       |



**2、Bitmaps(位图)**：是一种数据结构，操作二进制位来记录，只有0和1两个状态

- 只要是两个状态的，如登录成功或失败，用户活跃不活跃，打卡与未打卡等

```markdown
#  如统计每周的打卡情况

#  设置打卡
127.0.0.1:6379> setbit sign 1 0
(integer) 0
127.0.0.1:6379> setbit sign 2 1
(integer) 1
127.0.0.1:6379> setbit sign 3 1
(integer) 1
127.0.0.1:6379> setbit sign 4 1
(integer) 1
127.0.0.1:6379> setbit sign 5 1
(integer) 1
127.0.0.1:6379> setbit sign 6 0
(integer) 0
127.0.0.1:6379> setbit sign 7 0
(integer) 0

#  获取打卡记录
127.0.0.1:6379> getbit sign 5
(integer) 1

#  获取打卡成功的记录
127.0.0.1:6379> bitcount sign
(integer) 4
```

- redis Bitmaps的基本命令

| 序号 | 命令                     | 描述                                            |
| ---- | ------------------------ | ----------------------------------------------- |
| 1    | setbit key offset value  | 设置或者清空key的value(字符串)在offset处的bit值 |
| 2    | getbit key offset        | 返回key对应的string在offset处的bit值            |
| 3    | bitcount key [start end] | 统计字符串被设置为1的bit数                      |

### 15、Redis GEO

- geospatial(简称Geo)：Redis GEO 主要用于存储地理位置信息，并对存储的信息进行操作，该功能在 Redis 3.2 版本新增。

- Geo使用手册：https://www.runoob.com/redis/redis-geo.html

- Redis GEO 操作方法有：

  - geoadd：添加地理位置的坐标。
  - geopos：获取地理位置的坐标。
  - geodist：计算两个位置之间的距离。
  - georadius：根据用户给定的经纬度坐标来获取指定范围内的地理位置集合。
  - georadiusbymember：根据储存在位置集合里面的某个地点获取指定范围内的地理位置集合。
  - geohash：返回一个或多个位置对象的 geohash 值。

- geoadd

  - geoadd 用于存储指定的地理空间位置，可以将一个或多个经度(longitude)、纬度(latitude)、位置名称(member)添加到指定的 key 中。
  - geoadd 语法格式如下：

  ```markdown
  GEOADD key longitude latitude member [longitude latitude member ...]
  ```

  ```markdown
  127.0.0.1:6379> geoadd china:city 116.40 39.90 beijing
  (integer) 1
  ```

  

- geopos

  - geopos 用于从给定的 key 里返回所有指定名称(member)的位置（经度和纬度），不存在的返回 nil。
  - geopos 语法格式如下：

  ````markdown
  GEOPOS key member [member ...]
  ````

  ```markdown
  127.0.0.1:6379> geopos china:city beijing
  1) 1) "116.39999896287918091"
     2) "39.90000009167092543"
  ```

  

- geodist

  - geodist 用于返回两个给定位置之间的距离。
  - geodist 语法格式如下：

  ```markdown
  GEODIST key member1 member2 [m|km|ft|mi]
  ```

  ```markdown
  127.0.0.1:6379> geodist china:city beijing shenzhen km
  "1942.4695"
  ```

  

- georadius、georadiusbymember

  - georadius 以给定的经纬度为中心， 返回键包含的位置元素当中， 与中心的距离不超过给定最大距离的所有位置元素。
  - georadiusbymember 和 GEORADIUS 命令一样， 都可以找出位于指定范围内的元素， 但是 georadiusbymember 的中心点是由给定的位置元素决定的， 而不是使用经度和纬度来决定中心点。
  - georadius 与 georadiusbymember 语法格式如下：

  ```markdown
  GEORADIUS key longitude latitude radius m|km|ft|mi [WITHCOORD] [WITHDIST] [WITHHASH] [COUNT count] [ASC|DESC] [STORE key] [STOREDIST key]
  GEORADIUSBYMEMBER key member radius m|km|ft|mi [WITHCOORD] [WITHDIST] [WITHHASH] [COUNT count] [ASC|DESC] [STORE key] [STOREDIST key]
  ```

  - 参数说明：
    - m ：米，默认单位。
    - km ：千米。
    - mi ：英里。
    - ft ：英尺。
    - WITHDIST: 在返回位置元素的同时， 将位置元素与中心之间的距离也一并返回。
    - WITHCOORD: 将位置元素的经度和维度也一并返回。
    - WITHHASH: 以 52 位有符号整数的形式， 返回位置元素经过原始 geohash 编码的有序集合分值。 这个选项主要用于底层应用或者调试， 实际中的作用并不大。
    - COUNT 限定返回的记录数。
    - ASC: 查找结果根据距离从近到远排序。
    - DESC: 查找结果根据从远到近排序。

  ```markdown
  127.0.0.1:6379> georadius china:city 114 22 1000 km
  1) "shenzhen"
  2) "guangzhou"
  
  127.0.0.1:6379> georadiusbymember china:city shenzhen 200 km
  1) "shenzhen"
  2) "guangzhou"
  ```

  

- geohash

  - Redis GEO 使用 geohash 来保存地理位置的坐标，geohash 用于获取一个或多个位置元素的 geohash 值。
  - geohash 语法格式如下：

  ```markdown
  GEOHASH key member [member ...]
  ```

  ```markdown
  127.0.0.1:6379> geohash china:city beijing shanghai
  1) "wx4fbxxfke0"
  2) "wtw3sj5zbj0"
  ```

  

### 16、Redis配置

1. Redis配置文件位置

   - 备份Redis配置文件，然后修改备份的配置文件

   - Redis 的配置文件位于 Redis 安装目录下，文件名为 **redis.conf**(Windows 名为 redis.windows.conf)。

     你可以通过 **CONFIG** 命令查看或设置配置项。

   - **Redis CONFIG 命令格式如下：**

   ```markdown
   redis 127.0.0.1:6379> CONFIG GET CONFIG_SETTING_NAME
   #  实例如下
   redis 127.0.0.1:6379> CONFIG GET loglevel
   1) "loglevel"
   2) "notice"
   #  使用 * 号获取所有配置项：
   ```

   - 你可以通过修改 redis.conf 文件或使用 **CONFIG set** 命令来修改配置。

   ```markdown
   redis 127.0.0.1:6379> CONFIG SET CONFIG_SETTING_NAME NEW_CONFIG_VALUE
   #  实例如下
   redis 127.0.0.1:6379> CONFIG SET loglevel "notice"
   OK
   redis 127.0.0.1:6379> CONFIG GET loglevel
   1) "loglevel"
   2) "notice"
   ```

### 17、Redis常见配置参数

​	

| 序号 | 配置项                                                       | 说明                                                         |
| ---- | ------------------------------------------------------------ | ------------------------------------------------------------ |
| 1    | `daemonize no`                                               | Redis 默认不是以守护进程的方式运行，可以通过该配置项修改，使用 yes 启用守护进程（Windows 不支持守护线程的配置为 no ） |
| 2    | `pidfile /var/run/redis.pid`                                 | 当 Redis 以守护进程方式运行时，Redis 默认会把 pid 写入 /var/run/redis.pid 文件，可以通过 pidfile 指定 |
| 3    | `port 6379`                                                  | 指定 Redis 监听端口，默认端口为 6379，作者在自己的一篇博文中解释了为什么选用 6379 作为默认端口，因为 6379 在手机按键上 MERZ 对应的号码，而 MERZ 取自意大利歌女 Alessia Merz 的名字 |
| 4    | `bind 127.0.0.1`                                             | 绑定的主机地址                                               |
| 5    | `timeout 300`                                                | 当客户端闲置多长秒后关闭连接，如果指定为 0 ，表示关闭该功能  |
| 6    | `loglevel notice`                                            | 指定日志记录级别，Redis 总共支持四个级别：debug、verbose、notice、warning，默认为 notice |
| 7    | `logfile stdout`                                             | 日志记录方式，默认为标准输出，如果配置 Redis 为守护进程方式运行，而这里又配置为日志记录方式为标准输出，则日志将会发送给 /dev/null |
| 8    | `databases 16`                                               | 设置数据库的数量，默认数据库为0，可以使用SELECT 命令在连接上指定数据库id |
| 9    | `save <seconds> <changes>`Redis 默认配置文件中提供了三个条件：**save 900 1  save 300 10  save 60 10000**，分别表示 900 秒（15 分钟）内有 1 个更改，300 秒（5 分钟）内有 10 个更改以及 60 秒内有 10000 个更改。 | 指定在多长时间内，有多少次更新操作，就将数据同步到数据文件，可以多个条件配合 |
| 10   | `rdbcompression yes`                                         | 指定存储至本地数据库时是否压缩数据，默认为 yes，Redis        |
| 11   | `dbfilename dump.rdb`                                        | 指定本地数据库文件名，默认值为 dump.rdb                      |
| 12   | `dir ./`                                                     | 指定本地数据库存放目录                                       |
| 13   | `slaveof <masterip> <masterport>`                            | 设置当本机为 slave 服务时，设置 master 服务的 IP 地址及端口，在 Redis 启动时，它会自动从 master 进行数据同步 |
| 14   | `masterauth <master-password>`                               | 当 master 服务设置了密码保护时，slav 服务连接 master 的密码  |
| 15   | `requirepass foobared`                                       | 设置 Redis 连接密码，如果配置了连接密码，客户端在连接 Redis 时需要通过 AUTH <password> 命令提供密码，默认关闭 |
| 16   | ` maxclients 128`                                            | 设置同一时间最大客户端连接数，默认无限制，Redis 可以同时打开的客户端连接数为 Redis 进程可以打开的最大文件描述符数，如果设置 maxclients 0，表示不作限制。当客户端连接数到达限制时，Redis 会关闭新的连接并向客户端返回 max number of clients reached 错误信息 |
| 17   | `maxmemory <bytes>`                                          | 指定 Redis 最大内存限制，Redis 在启动时会把数据加载到内存中，达到最大内存后，Redis 会先尝试清除已到期或即将到期的 Key，当此方法处理 后，仍然到达最大内存设置，将无法再进行写入操作，但仍然可以进行读取操作。Redis 新的 vm 机制，会把 Key 存放内存，Value 会存放在 swap 区 |
| 18   | `appendonly no`                                              | 指定是否在每次更新操作后进行日志记录，Redis 在默认情况下是异步的把数据写入磁盘，如果不开启，可能会在断电时导致一段时间内的数据丢失。因为 redis 本身同步数据文件是按上面 save 条件来同步的，所以有的数据会在一段时间内只存在于内存中。默认为 no |
| 19   | `appendfilename appendonly.aof`                              | 指定更新日志文件名，默认为 appendonly.aof                    |
| 20   | `appendfsync everysec`                                       | 指定更新日志条件，共有 3 个可选值：**no**：表示等操作系统进行数据缓存同步到磁盘（快）**always**：表示每次更新操作后手动调用 fsync() 将数据写到磁盘（慢，安全）**everysec**：表示每秒同步一次（折中，默认值） |
| 21   | `vm-enabled no`                                              | 指定是否启用虚拟内存机制，默认值为 no，简单的介绍一下，VM 机制将数据分页存放，由 Redis 将访问量较少的页即冷数据 swap 到磁盘上，访问多的页面由磁盘自动换出到内存中（在后面的文章我会仔细分析 Redis 的 VM 机制） |
| 22   | `vm-swap-file /tmp/redis.swap`                               | 虚拟内存文件路径，默认值为 /tmp/redis.swap，不可多个 Redis 实例共享 |
| 23   | `vm-max-memory 0`                                            | 将所有大于 vm-max-memory 的数据存入虚拟内存，无论 vm-max-memory 设置多小，所有索引数据都是内存存储的(Redis 的索引数据 就是 keys)，也就是说，当 vm-max-memory 设置为 0 的时候，其实是所有 value 都存在于磁盘。默认值为 0 |
| 24   | `vm-page-size 32`                                            | Redis swap 文件分成了很多的 page，一个对象可以保存在多个 page 上面，但一个 page 上不能被多个对象共享，vm-page-size 是要根据存储的 数据大小来设定的，作者建议如果存储很多小对象，page 大小最好设置为 32 或者 64bytes；如果存储很大大对象，则可以使用更大的 page，如果不确定，就使用默认值 |
| 25   | `vm-pages 134217728`                                         | 设置 swap 文件中的 page 数量，由于页表（一种表示页面空闲或使用的 bitmap）是在放在内存中的，，在磁盘上每 8 个 pages 将消耗 1byte 的内存。 |
| 26   | `vm-max-threads 4`                                           | 设置访问swap文件的线程数,最好不要超过机器的核数,如果设置为0,那么所有对swap文件的操作都是串行的，可能会造成比较长时间的延迟。默认值为4 |
| 27   | `glueoutputbuf yes`                                          | 设置在向客户端应答时，是否把较小的包合并为一个包发送，默认为开启 |
| 28   | hash-max-zipmap-entries 64                          hash-max-zipmap-value 512 | 指定在超过一定的数量或者最大的元素超过某一临界值时，采用一种特殊的哈希算法 |
| 29   | `activerehashing yes`                                        | 指定是否激活重置哈希，默认为开启（后面在介绍 Redis 的哈希算法时具体介绍） |
| 30   | `include /path/to/local.conf`                                | 指定包含其它的配置文件，可以在同一主机上多个Redis实例之间使用同一份配置文件，而同时各个实例又拥有自己的特定配置文件 |



### 18、Redis事务

- Redis 事务可以一次执行多个命令， 并且带有以下三个重要的保证：
  - 批量操作在发送 EXEC 命令前被放入队列缓存。
  - 收到 EXEC 命令后进入事务执行，事务中任意命令执行失败，其余的命令依然被执行。
  - 在事务执行过程，其他客户端提交的命令请求不会插入到事务执行命令序列中。
- 一个事务从开始到执行会经历以下三个阶段：
  - 开始事务。
  - 命令入队。
  - 执行事务。
- 以下是一个事务的例子， 它先以 **MULTI** 开始一个事务， 然后将多个命令入队到事务中， 最后由 **EXEC** 命令触发事务， 一并执行事务中的所有命令：

```markdown
127.0.0.1:6379> multi
OK
127.0.0.1:6379> set k1 v1
QUEUED
127.0.0.1:6379> set k2 v2
QUEUED
127.0.0.1:6379> get k2
QUEUED
127.0.0.1:6379> set k3 v3
QUEUED
127.0.0.1:6379> set k4 v4
QUEUED
127.0.0.1:6379> exec
1) OK
2) OK
3) "v2"
4) OK
5) OK
```

- <span style='color:red'>单个 Redis 命令的执行是原子性的，但 Redis 没有在事务上增加任何维持原子性的机制，所以 Redis 事务的执行并不是原子性的。</span>
- <span style='color:red'>事务可以理解为一个打包的批量执行脚本，但批量指令并非原子化的操作，中间某条指令的失败不会导致前面已做指令的回滚，也不会造成后续的指令不做。</span>

```markdown
#  如果在set doctor MissLi aaa处失败，set teacher MissWang 已成功不会回滚，set engineer MarChen还会继续执行。
127.0.0.1:6379> set teacher MissWang 
QUEUED
127.0.0.1:6379> set doctor MissLi aaa
QUEUED
127.0.0.1:6379> set engineer MarChen 
QUEUED
127.0.0.1:6379> exec
1) OK
2) (error) ERR syntax error
3) OK
```

- 下表列出了 redis 事务的相关命令：

| 序号 | 命令                   | 描述                                                         |
| ---- | ---------------------- | ------------------------------------------------------------ |
| 1    | [DISCARD]              | 取消事务，放弃执行事务块内的所有命令。                       |
| 2    | [EXEC]                 | 执行所有事务块内的命令。                                     |
| 3    | [MULTI]                | 标记一个事务块的开始。                                       |
| 4    | [UNWATCH]              | 取消 WATCH 命令对所有 key 的监视。                           |
| 5    | [WATCH key [key ...\]] | 监视一个(或多个) key ，如果在事务执行之前这个(或这些) key 被其他命令所改动，那么事务将被打断。 |



### 19、Jedis

1. 导入对应的依赖

   ```xml
   		<dependencies>
           <!--导入jedis依赖-->
           <!-- https://mvnrepository.com/artifact/redis.clients/jedis -->
           <dependency>
               <groupId>redis.clients</groupId>
               <artifactId>jedis</artifactId>
               <version>3.3.0</version>
           </dependency>
   
           <!--导入fastjson-->
           <dependency>
               <groupId>com.alibaba</groupId>
               <artifactId>fastjson</artifactId>
               <version>1.2.58</version>
           </dependency>
       </dependencies>
   ```

   

2. 编写测试代码

   - 连接数据库
   - 操作命令
   - 关闭连接

   ```java
   package com.yu;
   
   import redis.clients.jedis.Jedis;
   
   import java.util.List;
   
   /**
    * @ClassName JedisDemo
    * @Description TODO
    * @Author yu
    * @Date 2020/11/24 16:50
    */
   public class JedisDemo {
       public static void main(String[] args) {
           /*
           连接远程数据库
   
           1、需在Linux中关闭防火墙或让防火墙开放6379端口
           2、将Redis的配置文件redis.conf中的bind 127.0.0.1注释或
           改为bind 127.0.0.1 远程服务器的IP地址
           3、修改完后需要重启redis服务,以新的配置文件启动
           */
           Jedis jedis = new Jedis("192.168.252.130", 6379);
           //测试连接是否成功
           System.out.println(jedis.ping());
           //关闭连接
           jedis.close();
       }
   }
   ```

### 20、SpringBoot整合Redis

```
#		在SpringBoot2.x以后，原来使用的jedis被替换了成了lettuce
#		jedis:使用直连的方式连接,多个线程操作的话是不安全的,如要避免,可以使用jedis Pool
#		lettuce:使用netty,实例可以在多个线程中共享,不存在线程不安全的情况
```

1. 导入依赖

   ```xml
   <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-data-redis</artifactId>
   </dependency>
   ```

   

2. 配置连接

   ```yaml
   # redis配置
   spring:
     redis:
       host: 192.168.252.130
       port: 6379
   ```

   

3. 操作命令

   ```java
   package com.yu;
   
   @SpringBootTest
   class Redis02SpringbootApplicationTests {
   
       @Autowired
       private RedisTemplate redisTemplate;
   
       @Test
       void contextLoads() {
           /*
           redisTemplate   操作不同的数据类型
   
           opsForValue()   操作字符串,类似String
           opsForList()    操作列表,类似List
           opsForSet()     操作集合,类似Set
           opsForHash()    操作哈希,类似Hash
           opsForZSet()    操作有序集合,类似ZSet
           opsForGeo()
           opsForHyperLogLog()
           */
   
           /*
           获取redis的连接对象
   
            RedisConnection connection = redisTemplate.getConnectionFactory().getConnection();
            connection.flushDb();
            connection.flushAll();
           */
   
           redisTemplate.opsForValue().set("mykey","yu");
           System.out.println(redisTemplate.opsForValue().get("mykey"));
       }
   
   }
   ```

4. 自定义redisTemplate和配置Json序列化

   ```java
   package com.yu.config;
   
   /**
    * @ClassName RedisConfig
    * @Description TODO
    * @Author yu
    * @Date 2020/11/25 11:53
    */
    
   @Configuration
   public class RedisConfig {
   
       //自定义redisTemplate
       @Bean
       public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory)
               throws UnknownHostException {
           RedisTemplate<String, Object> template = new RedisTemplate<>();
           template.setConnectionFactory(factory);
   
           //自定义序列化配置
           Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<Object>(Object.class);
           ObjectMapper mapper = new ObjectMapper();
           mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
           mapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
           jackson2JsonRedisSerializer.setObjectMapper(mapper);
   
           //String的序列化
           StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
   
           //key采用String的序列化方式
           template.setKeySerializer(stringRedisSerializer);
           //Hash的key也采用String的序列化方式
           template.setHashKeySerializer(stringRedisSerializer);
   
           //value采用jackson的序列化方式
           template.setValueSerializer(jackson2JsonRedisSerializer);
           //Hash的value也采用jackson的序列化方式
           template.setHashValueSerializer(jackson2JsonRedisSerializer);
           return template;
       }
   }
   ```



### 21、Redis持久化

- #### RDB(Redis DataBase)

- 在指定的时间间隔内将内存中的数据集快照写入磁盘，也就是Snapshot，恢复时将快照文件直接读到内存里

- **RDB持久化的执行过程**：

  ​	Redis会单独创建(fork)一个子进程来进行持久化，会先将数据写入到一个临时文件中，待持久化过程都	结束了，再用这个临时文件替换上次持久化好的文件。整个过程中，主进程是不会进行任何IO操作的，	这就确保了极高的性能，如果需要进行大规模数据的恢复且对于数据恢复的完整性不是非常敏感，那	      	RDB方式要比AOF方式更加的高效。RDB的缺点是最后一次持久化后的数据可能丢失

- 关于fork

  - 在Linux程序中，fork()会产生一个和父进程完全相同的子进程，但子进程在此后会使用exec系统调用，处于效率考虑，Linux中引入了"写时复制技术(需要写的时候才复制)"，一般情况父进程和子进程会共用一段物理内存，只有进程空间的各段的内存要发生改变才会将父进程的内容复制一份给子进程

- rdb保存的文件

  - 在redis.conf中配置文件名称，默认为dump.rdb

  - rdb文件的保存路径也可以修改，默认为Redis启动时命令行所在的目录下

  - rdb的保存策略

    ```bash
    save	900		1		# 15min内有1次数据的更新
    save	300		10	#	5min内有10次数据的更新
    save	60		10000		#	1min内有10000次数据的更新
    ```

  - rdb持久化策略相关配置

    ```bash
    stop-writes-on-bgsave-error yes #当Redis无法写入磁盘的时候，直接关掉Redis的写操作
    rdbcompression yes							#进行rdb保存时，将文件压缩
    rdbchecksum yes									#在存储快照后，还可以让Redis使用CRC64算法来进行数据																	校验,但是这样做会增加大约10%的性能消耗,如果希望获取																 到最大的性能提升，可以关闭此功能
    ```

    

- 触发机制(即生成dump.rdb文件)

  - save条件满足的情况下，会自动触发rdb规则
  - 执行flushall命令，也会触发rdb规则
  - 正常退出redis，也会触发rdb规则

- 如何恢复rdb文件

  - 将rdb文件放在redis启动目录下
  - Redis启动的时候回自动检测dump.rdb，并恢复其中的数据

- 查看rdb文件存放的位置

  ```bash
  127.0.0.1:6379> config get dir
  1) "dir"
  2) "/usr/local/bin"
  ```

- **优点**

  - 适合大规模的数据恢复
  - 适用于对数据的完整性要求不高的操作

- **缺点**

  - 如果Redis意外宕机，最后一次持久化的数据可能会丢失
  - fork进程的时候，会占用一定的内存空间，相当于原来进程的2倍



- #### AOF(Append Only File)

- 将我们操作的所有命令都记录下来，恢复的时候就把这个文件的命令再执行一遍

- **AOF持久化的过程：**

  ​	以日志的形式来记录每个写操作，将Redis执行过的所有指令记录下来(读操作不记录)，只能追加文件不	能改写文件，Redis在启动前会读取该文件并重新构建数据，换言之，Redis重启的时候就根据日志文件	的内容将写指令从前到后执行一次以完成数据的恢复

- <span style='color:red'>AOF默认不开启，需要手动在配置文件中配置</span>

- 可以在redis.conf中指定配置文件名称，默认为appendonly.aof

- AOF文件的保存路径同RDB的路径一致

- AOF文件故障备份：

  - AOF的备份机制和性能虽然和RDB不同，但是备份和恢复的操作同RDB一样，都是拷贝备份文件，需要恢复时再拷贝到Redis工作目录下，启动系统即加载
  - AOF和RDB同时开启，系统默认读取AOF的数据

- AOF文件故障恢复

  - AOF文件的保存路径同RDB的路径一致
  - 如遇到AOF文件损坏导致无法连接redis客户端，可通过redis-check-aof  --fix  appendonly.aof恢复，但会丢失部分数据

- AOF同步频率设置

  ```bash
  # appendfsync always	#始终同步,每次Redis的写操作都会立刻记入日志
  appendfsync everysec	#每秒同步，每秒记录日志一次，若果宕机，本秒的数据可能丢失
  # appendfsync no			#不主动进行同步,把同步时机交给操作系统
  ```

- AOF重写机制

  ```bash
  auto-aof-rewrite-percentage 100
  auto-aof-rewrite-min-size 64mb
  ```

- **优点**

  - 备份
  <span style="color:red;">注意：创建新的角色组后，需要在用户管理里面绑定新的角色组，同时修改储存策略中的角色组</span>
