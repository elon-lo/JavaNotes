### 1、什么是JUC？

即java.util.concurrent的缩写

### 2、Volatile关键字及内存可见性

- 当多个线程进行操作共享数据时,可以保证内存中的数据可见，相较于synchronized是一种较为轻量级的同步策略

```java
package com.yu.juc;

/**
 * @ClassName
 * @Description TODO
 * @Author yu
 * @Date 2020/11/11 20:40
 *
 *
 * Volatile关键字：当多个线程进行操作共享数据时,可以保证内存中的数据可见
 *                相较于synchronized是一种较为轻量级的同步策略
 *
 * 注意：
 * 1、volatile不具备"互斥性"
 * 2、volatile不能保证变量的"原子性"
 */
public class VolatileDemo {
    public static void main(String[] args) {
        ThreadDemo demo = new ThreadDemo();


        new Thread(demo).start();

        //当前main线程读的是缓存中的数据
        while (true){
            if (demo.getFlag()) {
                System.out.println("------------------");
                break;
            }
        }
    }
}

class ThreadDemo implements Runnable{

    //使用volatile后用户线程和main线程都从共享数据中获取数据
    private volatile boolean flag = false;

    public boolean getFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    @Override
    public void run() {
//        Thread.sleep(200);
        flag = true;
        System.out.println("flag = "+getFlag());
    }
}
```

- <span style='color:red'>volatile不具备“互斥性”</span>
- <span style='color:red'>volatile不能保证变量的”原子性“</span>

### 3、原子变量

```
一、i++ 的原子性问题：i++的操作实际上分为三个步骤"读-改-写"

										int i = 10;
										i = i + 1;//10
										
										
										int temp = i;
											 i = i + 1;
											 i = temp;
											 
											 
二、原子变量：jdk1.5之后java.util.concurrent.atmoic包下提供了常用的原子变量：
					1、volatile	保证内存可见性
					2、CAS(Compare-And-Swap)算法保证数据的原子性
					
					CAS算法是硬件对于并发操作共享数据的支持
					CAS包含了三个操作数：
					V	内存值
					A	预估值
					B	更新值
					当且仅当V == A时,V = B,否则，将不做任何操作
```



```java
package com.yu.juc;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @ClassName
 * @Description TODO
 * @Author yu
 * @Date 2020/11/11 21:28
 *
 * 原子变量和CAS算法
 */
public class AtomicDemo {
    public static void main(String[] args) {
        AtomicThread atomicThread = new AtomicThread();

        for (int i = 0; i < 50; i++) {
            new Thread(atomicThread).start();
        }
    }
}

class AtomicThread implements Runnable{
    private AtomicInteger serialNumber = new AtomicInteger();

    @Override
    public void run() {
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(getSerialNumber());
    }

    public int getSerialNumber() {
        return serialNumber.incrementAndGet();
    }

    public void setSerialNumber(AtomicInteger serialNumber) {
        this.serialNumber = serialNumber;
    }
}
```

### 3、CAS算法

- CAS(Compare-And-Swap)是一种硬件对并发的支持，针对多处理器操作而设计的处理器中的一种特殊指令，用于管理对共享数据的并发访问
- CAS是一种无锁的非阻塞算法的实现
- CAS包含了3个操作数
  - 需要读写的内存值	V
  - 进行比较的值    A
  - 拟写入的新值    B
- 当且仅当 V 的值等于 A 时，CAS通过原子方式用新值 B 来更新 V 的值，否则不会执行任何操作

```java
package com.yu.juc;

/**
 * @ClassName CompareAndSwapDemo
 * @Description TODO
 * @Author yu
 * @Date 2020/11/12 21:15
 *
 * 模拟CAS算法
 */
public class CompareAndSwapDemo {
    public static void main(String[] args) {
        final CompareAndSwap cas = new CompareAndSwap();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    int expectedValue = cas.get();
                    boolean b = cas.compareAndSet(expectedValue, (int) (Math.random() * 101));
                    System.out.println(b);
                }
            }
        }).start();
    }
}

class CompareAndSwap{
    private int value;


    //获取内存值
    public synchronized int get(){
        return value;
    }

    //比较
    public synchronized int compareAndSwap(int expectedValue,int newValue){
        int oldValue = value;

        if (oldValue == expectedValue){
            this.value = newValue;
        }
        return oldValue;
    }


    //设置
    public synchronized boolean compareAndSet(int expectedValue,int newValue){
        return expectedValue == compareAndSwap(expectedValue,newValue);
    }
}
```

### 4、线程池的作用

- 线程池做的工作主要是控制运行的线程数量，处理过程中将任务放入队列，然后在线程创建后启动这些任务，如果线程数量超过了线程池的最大数量，超出数量的线程排队等候，等其他线程执行完毕，再从队列中取出任务来执行
- 线程池的主要特点有线程复用、控制最大并发数、管理线程
  - 降低资源消耗。通过重复利用已创建的线程降低线程创建和销毁造成的消耗
  - 提高响应速度。当任务到达时，任务可以不需要等待线程创建就能立即执行
  - 提高线程的可管理性。线程是稀缺资源，如果无限制的创建，不仅会消耗系统资源，还会降低系统的稳定性，使用线程池可以进行统一的分配、调优和监控



### 5、线程池如何使用

- Java中的线程池是通过Executor框架实现的，该框架中用到了Executor，Executors，ExecutorService，ThreadPoolExecutor这几个类
- 线程池架构图如下：

![](https://img-blog.csdnimg.cn/20191014094502445.jpg?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L1RoaW5rV29u,size_16,color_FFFFFF,t_70)



| 类/接口                     | 描述                                                         |
| --------------------------- | ------------------------------------------------------------ |
| ExecutorService             | 真正的线程池接口                                             |
| ScheduledExecutorService    | 能和Timer/TimerTask类似，解决那些需要任务重复执行的问题      |
| ThreadPoolExecutor          | ExecutorService的默认实现                                    |
| ScheduledThreadPoolExecutor | 继承ThreadPoolExecutor的ScheduledExecutorService接口实现，周期性任务调度的类实现 |

Java通过**Executors工厂类提供四种线程池**，分别为：

1. **newCachedThreadPool** ：创建一个可缓存线程池，如果线程池长度超过处理需要，可灵活回收空闲线程，若无可回收，否则新建线程。（线程最大并发数不可控制）
2. **newFixedThreadPool**：创建一个固定大小的线程池，可控制线程最大并发数，超出的线程会在队列中等待。
3. **newScheduledThreadPool** ： 创建一个定时线程池，支持定时及周期性任务执行。
4. **newSingleThreadExecutor** ：创建一个单线程化的线程池，它只会用唯一的工作线程来执行任务，保证所有任务按照指定顺序(FIFO, LIFO, 优先级)执行。

```java
package com.yu.juc;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName MyThreadPoolDemo
 * @Description TODO
 * @Author yu
 * @Date 2020/11/13 0:08
 *
 * 线程池的三大方法
 */
public class MyThreadPoolDemo {
    public static void main(String[] args) {

//      ExecutorService threadPool = Executors.newFixedThreadPool(5);//一池5个工作线程,相当于银行的5个窗口
//      ExecutorService threadPool = Executors.newSingleThreadExecutor();//一池1个工作线程,相当于银行的1个窗口
        ExecutorService threadPool = Executors.newCachedThreadPool();//一池N个工作线程,相当于银行的N个窗口


        try {
            //模拟有10个顾客来办理业务,目前池子5个工作人员
            for (int i = 0; i < 10; i++) {
                threadPool.execute(()->{
                    System.out.println(Thread.currentThread().getName()+"-"+"办理业务");
                });
//                TimeUnit.MILLISECONDS.sleep(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            threadPool.shutdown();
        }
    }
}
```

### 6、线程池的7个参数

- corePoolSize：线程池中的常驻核心线程数
- maximumPoolSize：线程池中能够容纳同时执行的最大线程数，此值必须大于等于1
- keepAliveTime：多余的空闲线程的存活时间，当前池中线程数量超过corePoolSize时，当空闲时间达到keepAliveTime时，多余线程会被销毁知道剩下corePoolSize个线程为止
- unit：keepAliveTime的单位
- workQueue：任务队列，任务提交但尚未被执行的任务
- threadFactory：表示生成线程池中工作线程的线程工厂，用于创建线程，一般默认的即可
- handler：拒绝策略，表示当队列满了，并且工作线程大于等于线程池的最大线程(maximumPoolSize)数时如何来拒绝请求执行的Runnable的策略

```java
public ThreadPoolExecutor(int corePoolSize,
                              int maximumPoolSize,
                              long keepAliveTime,
                              TimeUnit unit,
                              BlockingQueue<Runnable> workQueue,
                              ThreadFactory threadFactory,
                              RejectedExecutionHandler handler) {
        if (corePoolSize < 0 ||
            maximumPoolSize <= 0 ||
            maximumPoolSize < corePoolSize ||
            keepAliveTime < 0)
            throw new IllegalArgumentException();
        if (workQueue == null || threadFactory == null || handler == null)
            throw new NullPointerException();
        this.acc = System.getSecurityManager() == null ?
                null :
                AccessController.getContext();
        this.corePoolSize = corePoolSize;
        this.maximumPoolSize = maximumPoolSize;
        this.workQueue = workQueue;
        this.keepAliveTime = unit.toNanos(keepAliveTime);
        this.threadFactory = threadFactory;
        this.handler = handler;
    }
```

### 8、线程池底层工作原理

- 在创建了线程池后，开始等待请求
- 当调用execute()方法添加一个请求任务时，线程池会做出如下判断：
  - 如果正在运行的线程数量小于corePoolSize，那么马上创建线程运行这个任务
  - 如果正在运行的线程数量大于或等于corePoolSize，那么将这个任务放入队列
  - 如果这个时候队列满了且正在运行的线程数量还小于maxmunPoolSize，那么还是要创建非核心线程立刻运行这个任务
  - 如果队列满了且正在运行的线程数量大于或等于maxmunPoolSize，那么线程池会启动饱和拒绝策略来执行
- 当一个线程完成任务时，它还会从队列中取下一个任务来执行
- 当一个线程空闲超过一定的时间(keepAliveTime)时，线程会判断：
  - 如果当前运行的线程数量大于corePoolSize，那么这个线程就被停掉
  - 所以线程池的所有任务完成后，它最终会收缩到corePoolSize的大小

![](https://img-blog.csdnimg.cn/20200507203018638.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzQzMzIzNTg1,size_16,color_FFFFFF,t_70)



![](https://img-blog.csdnimg.cn/201812241107535.png)

### 9、线程池的选用

- 在工作中单一/固定/可变的三种创建线程池的方法哪个用的多？
  - 都不用，我们工作中只能使用自定义的
  - 阿里巴巴开发手册中提到，线程池不允许使用Excutors去创建，而是通过ThreadPoolExcutor的方式，这样的处理方式让写的人更加明确线程池的运行规则，规避资源耗尽的风险
  - Excutors返回的线程池对象弊端如下：
    - FixedThreadPool和SingleThreadPool：
      - 允许的请求队列长度为Integer.MAX_VALUE，可能会堆积大量的请求，从而导致OOM
    - CachedThreadPool和ScheduledThreadPool：
      - 允许的创建线程数量为Integer.MAX_VALUE，可能会创建大量的线程，从而导致OOM

- 线程池的拒绝策略：

  - AbortPolicy(默认)：直接抛出RejectedexecutionException异常阻止系统正常运行

  ```java
  package com.yu.juc;
  
  import java.util.concurrent.*;
  
  /**
   * @ClassName MyThreadPoolDemo
   * @Description TODO
   * @Author yu
   * @Date 2020/11/13 0:08
   *
   * 线程池AbortPolicy()策略
   */
  public class MyThreadPoolDemo {
      public static void main(String[] args) {
  
          ThreadPoolExecutor threadPool = new ThreadPoolExecutor(
                  2,
                  5,
                  2L,
                  TimeUnit.SECONDS,
                  new LinkedBlockingQueue<Runnable>(3),
                  Executors.defaultThreadFactory(),
                  //线程池的最大线程数 = maximumPoolSize
                  //线程池的最大容纳数 = maximumPoolSize + queueCapacity
                  new ThreadPoolExecutor.AbortPolicy()
          );
  
          try {
              //模拟有10个顾客来办理业务
              for (int i = 0; i < 9; i++) {
                  threadPool.execute(()->{
                      System.out.println(Thread.currentThread().getName()+"-"+"办理业务");
                  });
              }
          } catch (Exception e) {
              e.printStackTrace();
          } finally {
              threadPool.shutdown();
          }
      }
  }
  ```

  ```markdown
  pool-1-thread-1-办理业务
  pool-1-thread-1-办理业务
  pool-1-thread-1-办理业务
  pool-1-thread-1-办理业务
  java.util.concurrent.RejectedExecutionException: Task com.yu.juc.MyThreadPoolDemo$$Lambda$1/1096979270@7ba4f24f rejected from java.util.concurrent.ThreadPoolExecutor@3b9a45b3[Running, pool size = 5, active threads = 5, queued tasks = 3, completed tasks = 0]
  	at java.util.concurrent.ThreadPoolExecutor$AbortPolicy.rejectedExecution(ThreadPoolExecutor.java:2063)
  	at java.util.concurrent.ThreadPoolExecutor.reject(ThreadPoolExecutor.java:830)
  	at java.util.concurrent.ThreadPoolExecutor.execute(ThreadPoolExecutor.java:1379)
  	at com.yu.juc.MyThreadPoolDemo.main(MyThreadPoolDemo.java:31)
  pool-1-thread-2-办理业务
  pool-1-thread-3-办理业务
  pool-1-thread-4-办理业务
  pool-1-thread-5-办理业务
  ```

  - CallerRunsPolicy："调用者"运行一种调节机制，该策略既不会抛弃任务，也不会抛出异常，而是将某些任务回退到调用者，从而降低新任务的流量

  ```markdown
  pool-1-thread-1-办理业务
  pool-1-thread-3-办理业务
  pool-1-thread-2-办理业务
  pool-1-thread-3-办理业务
  pool-1-thread-3-办理业务
  main-办理业务
  pool-1-thread-2-办理业务
  pool-1-thread-4-办理业务
  pool-1-thread-5-办理业务
  ```

  - DiscardOldestPolicy：抛弃队列中等待最久的任务，然后把当前任务加入队列中尝试再次提交当前任务

  ```markdown
  pool-1-thread-1-办理业务
  pool-1-thread-1-办理业务
  pool-1-thread-1-办理业务
  pool-1-thread-1-办理业务
  pool-1-thread-2-办理业务
  pool-1-thread-3-办理业务
  pool-1-thread-4-办理业务
  pool-1-thread-5-办理业务
  ```

  - DiscardPolicy：该策略默默地丢弃无法处理的任务，不予任何处理也不抛出异常，如果允许任务丢失，这是最好的一种策略

  ```markdown
  pool-1-thread-1-办理业务
  pool-1-thread-1-办理业务
  pool-1-thread-1-办理业务
  pool-1-thread-1-办理业务
  pool-1-thread-2-办理业务
  pool-1-thread-3-办理业务
  pool-1-thread-4-办理业务
  pool-1-thread-5-办理业务
  ```

  

