### 1、谈谈面向对象的思想

- 封装
  - 隐藏内部实现，对外提供了私有和公有的访问模式，公有接口代表外部的用户应该知道或可以知道的每件东西，私有的方法数据只能通过该类的成员代码来访问，这就可以确保不会发生不希望的事情
- 继承
  - 继承就是子类继承父类的特征和行为，使得子类对象（实例）具有父类的实例域和方法，或子类从父类继承方法，使得子类具有父类相同的行为。
- 多态
  - 多态是同一个行为具有多个不同表现形式或形态的能力
  - 多态就是同一个接口，使用不同的实例而执行不同操作

### 2、JDK、JRE、JVM的区别？

- JDK：Java开发工具包，包含了Java的运行环境和开发环境
- JRE：Java运行环境，包含Java虚拟机和Java基础类库
- JVM：Java虚拟机，提供执行解析字节码文件的能力

<font color='red'>注意：JVM是实现Java跨平台的核心，但JVM本身不是跨平台的，不同的平台需要安装不同的JVM</font>

### 3、==和equals的区别？

- ==是操作符，equals是方法

- 对于基本类型的变量来说（如 `short`、 `int`、 `long`、 `float`、 `double`），只能使用 == ，因为这些基本类型的变量没有 equals 方法。对于基本类型变量的比较，使用 == 比较， **一般比较的是它们的值**。

- 对于引用类型的变量来说才有 equals 方法，默认情况下，也就是没有重写 Object 类的 equals 方法，使用 == 和 equals 比较是一样效果的，都是**比较的是它们在内存中的存放地址**。**但是 String 和八大基本类型的包装类，由于内部实现了equals方法，使用 equals 方法会比较它们的值**；

### 4、final关键字的用法

- final修饰类，表示类不可变，不能被继承
- final修饰方法，表示该方法不可被重写
- final修饰变量，这个变量就是常量
  - 修饰的是基本数据类型，这个值本身不可修改
  - 修饰的是引用类型，引用的指向不能改变

### 5、String，StringBuffer和StringBuilder的区别

- String是final类型，每次声明的都是不可变的对象，所以每次操作都会产生新的String对象，然后将指针指向新的String对象

- StringBuffer和StringBuilder都是在原有对象上进行操作

- StringBuffer是线程安全的，StringBuilder是线程不安全的，线程不安全性能更高，所以在多线程中，优先使用StringBuilder，

  性能：StringBuilder>StringBuffer>String

### 6、接口和抽象类的区别

- 抽象类可以提供成员方法的实现细节，而接口中只能存在public abstract 方法；
- 抽象类中的成员变量可以是各种类型的，而接口中的成员变量只能是public static final类型的；
- 接口中不能含有静态代码块以及静态方法，而抽象类可以有静态代码块和静态方法；
- 一个类只能继承一个抽象类，而一个类却可以实现多个接口。

### 7、递归算法

```java
/*
求n!
n! = n*(n-1)!,(n-1)!=(n-1)*(n-2)!
*/
public static int stepMulti(int n){
		if (n < 0){
				throw new RuntimeException("请输入一个大于0的数");
		}else if (n == 0 || n == 1){
				return 1;
		}else {
				return n*stepMulti(n-1);
		}
}
```

```java
/*
斐波那契数列
1,1,2,3,5,8,13...
Fn = F(n-2)+F(n-1),f(1)=1,f(2)=1,f(3)=f(2)+f(1),f(4)=f(3)+f(2)
*/
public static int feiBo(int n){
    if (n == 1 || n == 2){
        return 1;
    }else {
        return feiBo(n-2) + feiBo(n-1);
    }
}
```

### 8、排序

### 9、List和Set区别

- List(有序，可重复)
- Set(无序，不可重复)

### 10、ArrayList和LinkedList的区别

- ArrayList底层数据结构是数组，LinkedList底层数据结构是双向链表
- ArrayList查询较快，时间复杂度为O(1)，删除、插入比较慢，时间复杂度为O(n)
- LinkedList查询比较慢，时间复杂度为O(n)，插入、删除比较快，时间复杂度为O(1)

### 11、Set集合的重复验证机制

- 比较对象的hashCode是否相同，若相同，再进行equals方法的判断，true认为元素重复，反之元素不重复。若hashCode不相同，则直接返回元素不重复并将元素插入。

### 12、HashMap和HashTable的区别？

- HashMap是线程不安全的，在多线程环境下会容易产生死循环，但是单线程环境下运行效率高；Hashtable线程安全的，很多方法都有synchronized修饰，但同时因为加锁导致单线程环境下效率较低。
- HashMap允许有一个key为null，允许多个value为null；而Hashtable不允许key或者value为null。

### 13、创建线程的方式

- 继承Thread类
- 实现Runable接口
- 实现Callable接口(可以获取线程执行之后的返回值)

<font color='red'>注意：在实际开发中，通常使用线程池的方式来完成Thread的创建，更好管理线程资源</font>

### 14、谈谈你对线程安全的理解

- 当多个线程访问同一个对象时，如果不用进行额外的同步操作或其他的协调操作，调用对象的行为都可以获得正确的结果，我们就说这个对象是线程安全的

### 15、sleep和wait的区别

- sleep方法定义在Thread上，wait方法定义在Object上
- sleep不会释放锁，wait会释放锁
- sleep可以使用在任何代码块，wait必须在同步方法或同步代码块中执行

### 16、谈谈你对ThreadLocal的理解

