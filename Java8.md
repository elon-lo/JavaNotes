## 简介

- 速度更快
- 代码更少(增加了新的语法Lambda表达式)
- 强大的Stream API
- 便于并行
- 最大化减少空指针异常Optional

## 章节索引

- <a href="#lambda">lambda表达式</a>
- <a href="#function-interface">函数式接口</a>
- <a href="#method-references">方法引用与构造器引用</a>
- <a href="#stream">Stream API</a>
- <a href="#optional">Optional类</a>
- <a href="#default-method">接口中的默认方法与静态方法</a>
- <a href="#new-date">新时间日期API</a>
- <a href="#other">其他新特性</a>

## <span id="lambda">Lambda表达式</span>

Lambda是一种`更简洁、更灵活`的编码方式，用于函数式编程和简化代码的写法。Lambda表达式可以看作是一个匿名函数，可以作为方法参数或返回值。

Lambda表达式的语法如下：

```java
(parameters) -> expression
```

```java
(parameters) -> { statements; }
```

其中，`parameters`指定了Lambda表达式的`参数列表`，可以是`零个或多个参数`。箭头`->`将参数列表与表达式或语句块进行分隔。`expression`是单个表达式，而`{ statements; }`是一个代码块。

以下是lambda表达式的重要特征：

- **可选类型声明：**不需要声明参数类型，编译器可以统一识别参数值。
- **可选的参数圆括号：**一个参数无需定义圆括号，但多个参数需要定义圆括号。
- **可选的大括号：**如果主体包含了一个语句，就不需要使用大括号。
- **可选的返回关键字：**如果主体只有一个表达式返回值则编译器会自动返回值，大括号需要指定表达式返回了一个数值。

Lambda表达式的参数相关类型取决于函数式接口中定义的方法签名。下面是一些常见用法：

- 无参数，无返回值

  ```java
  () -> System.out.println("Hello")
  ```

- 有一个参数，无返回值(参数外的括号可不写)

  ```java
  Consumer<String> con = (x) -> System.out.println(x);
  con.accept("Hello");
  ```

  ```java
  Consumer<String> con1 = x -> System.out.println(x);
  ```

- 有两个参数，无返回值

  ```java
  BiConsumer<String, Integer> printDetails = (name, age) -> {
      System.out.println("Name: " + name);
      System.out.println("Age: " + age);
  };
  printDetails.accept("John", 30);
  ```

- 无参，有返回值

  ```java
  Supplier<String> messageSupplier = () -> "Hello, Lambda!";
  String message = messageSupplier.get();
  System.out.println(message);
  ```

- 有一个参数，有返回值

  ```java
  Function<Integer, Integer> square = x -> x * x;
  int result = square.apply(5);
  System.out.println(result);
  ```

- 有两个参数，有返回值

  ```java
  Comparator<Integer> com = (x, y) -> {
      System.out.println("比较数字大小");
      return Integer.compare(x, y);
  };
  System.out.println(com.compare(1, 5));
  ```

  ```java
  Comparator<Integer> com1 = (x, y) -> Integer.compare(x, y);
  ```

  ```java
  Comparator<Integer> com2 = Integer::compare;
  ```

## <span id="function-interface">函数式接口</span>

接口中`有且仅有一个抽象方法`的接口，但是可以有多个非抽象方法的接口称为函数式接口。可以使用@FunctionalInterface注解修饰检查是否是函数式接口。

Java中的函数式接口都在`java.util.function`包中，其中常用的四大核心函数式接口如下：

|          函数式接口           | 参数类型 | 返回类型 |                             说明                             |
| :---------------------------: | :------: | :------: | :----------------------------------------------------------: |
|  Consumer<T><br/>消费型接口   |    T     |   void   |     对类型为T的对象操作，包含方法：<br/>void accept(T t)     |
|  Supplier<T><br/>供给型接口   |    无    |    T     |          返回类型为T的对象，包含方法：<br/>T get()           |
| Function<T, R><br/>函数型接口 |    T     |    R     | 对类型为T的对象操作，并返回结果。<br/>结果是R类型的对象。包含方法：<br/>R apply(T t) |
|  Predicate<T><br/>断言型接口  |    T     | boolean  | 确定类型为T的对象是否满足其约束，<br/>并返回boolean值。包含方法：<br/>boolean test(T t) |

下面是常用的四大函数式接口的常见用法：

- Consumer<T>(消费型接口)

  ```java
  @Test
  public void consumerTest() {
      eat("鱼", (m) -> System.out.println("小明第一次和小红约会,他们准备吃" + m));
  }
  
  public void eat(String food, Consumer<String> con) {
      con.accept(food);
  }
  ```

- Supplier<T>(供给型接口)

  ```java
  @Test
  public void supplierTest() {
      List<Integer> numList = generateList(5, () -> (int) (Math.random() * 50));
      numList.forEach(System.out::println);
  }
  
  public List<Integer> generateList(int num, Supplier<Integer> sup) {
      List<Integer> numList = new LinkedList<>();
      for (int i = 0; i < num; i++) {
          Integer p = sup.get();
          numList.add(p);
      }
      return numList;
  }
  ```

- Function<T, R>(函数型接口)

  ```java
  @Test
  public void functionTest() {
      String str = strHandler("\t\t\t 张三今天很帅 ", (st) -> st.trim());
      System.out.println(str);
  
      String newStr = strHandler("张三今天很帅", (st) -> st.substring(0, 2));
      System.out.println(newStr);
  }
  
  public String strHandler(String str, Function<String, String> fun) {
      return fun.apply(str);
  }
  ```

- Predicate<T>(断言型接口)

  ```java
  @Test
  public void predicateTest() {
      List<Integer> list = Arrays.asList(10, 15, 18, 25, 50);
      List<Integer> data = filterInt(list, (s) -> s > 18);
      System.out.println(data);
  }
  
  public List<Integer> filterInt(List<Integer> list, Predicate<Integer> pre) {
      List<Integer> result = new ArrayList<>();
      for (Integer item : list) {
          if (pre.test(item)) {
              result.add(item);
          }
      }
      return result;
  }
  ```

## <span id="method-references">方法引用与构造器引用</span>

方法引用的特点如下：

- 方法引用使用一对冒号 **::**，通过方法的名字来指向一个方法。
- 方法引用可以使语言的构造更紧凑简洁，减少冗余代码。

### 构造器引用

它的语法是`Class::new`，或者更一般的`Class<T>::new`

```java
public class House {

	private String type;

	private String address;

	public House() {
	}

	public House(String address) {
		this.address = address;
	}

	public House(String type, String address) {
		this.type = type;
		this.address = address;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "House{" +
				"type='" + type + '\'' +
				", address='" + address + '\'' +
				'}';
	}
}
```

```java
@Test
public void test1() {
    Function<String, House> con = (x) -> new House(x);
    con.apply("beijing");

    // 一个参数的构造器引用
    Function<String, House> fun = House::new;
    System.out.println(fun.apply("beijing"));

    // 两个参数的构造器引用
    BiFunction<String, String, House> fun2 = House::new;
    System.out.println(fun2.apply("Residential", "shanghai"));
}
```

### 方法引用

**实例对象的成员方法的引用**

它的语法是`instance::method`，示例如下：

```java
@Test
public void test2() {
    Consumer<String> con = System.out::println;
    con.accept("hello world!");

    House house = new House();
    Supplier<String> sup = house::getAddress;
    String address = sup.get();
    System.out.println(address);

}
```

**静态方法引用**

它的语法是`Class::static_method`

```java
@Test
public void test3() {
    Comparator<Integer> com = (x, y) -> Integer.compare(x, y);

    Comparator<Integer> com2 = Integer::compare;
    System.out.println(com2.compare(1, 2));
}
```

**类的成员方法引用**

它的语法是`Class::method`

```java
@Test
public void test4() {
    BiPredicate<String, String> bp = (x, y) -> x.equals(y);

    BiPredicate<String, String> bp2 = String::equals;
    System.out.println(bp2.test("aa", "bb"));
}
```

### 数组引用

它的语法是`Type[]::new`

```java
@Test
public void test5() {
    Function<Integer, Integer[]> fun = (x) -> new Integer[x];
    Integer[] ints = fun.apply(5);
    System.out.println(ints.length);

    Function<Integer, Integer[]> fun2 = Integer[]::new;
    Integer[] ints2 = fun2.apply(10);
    System.out.println(ints2.length);
}
```

<span style="color:red;">注意：需要调用的构造器的参数列表与函数式接口中抽象方法的参数列表要一致</span>

## <span id="stream">Stream API</span>

`Stream`是Java8中处理集合的关键抽象概念，它可以指定集合执行非常复杂的`查找、过滤和映射数据`等操作。使用`Stream API(java.util.stream.*)`对集合数据进行操作，就类似于使用SQL执行数据库查询。也可以使用`Stream API`来并行执行操作。简而言之，Stream API提供了一种高效且易于使用的处理数据的方式。

### 什么是Stream流？

Stream(流)是一个来自数据源的元素队列并支持聚合操作。

- **元素**是特定类型的对象，形成一个队列。Java中的Stream并不会存储元素，而是按需计算。
- **数据源**流的来源。 可以是集合，数组，I/O channel，产生器generator 等。
- **聚合操作**类似SQL语句一样的操作。 比如filter，map，reduce，find，match，sorted等。
- **Pipelining**：中间操作都会返回流对象本身。这样多个操作可以串联成一个管道， 如同流式风格（fluent style）。 这样做可以对操作进行优化， 比如延迟执行(laziness)和短路( short-circuiting)。
- **内部迭代**：以前对集合遍历都是通过Iterator或者For-Each的方式, 显式的在集合外部进行迭代，这叫做外部迭代。Stream提供了内部迭代的方式，通过访问者模式(Visitor)实现。

### Stream流操作

#### 创建Stream流

一个数据源(如集合、数组)，获取一个流。

- 通过`Collection`系列集合提供的`stream()`或`parallelStream()`创建一个流。

  ```java
  @Test
  public void test1() {
      List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
      Stream<Integer> stream = numbers.stream();
      stream.forEach(System.out::println);
  }
  ```

- 通过`Arrays`中的`静态方法stream()`获取数组流。

  ```java
  @Test
  public void test2() {
      int[] array = {1, 2, 3, 4, 5};
      IntStream stream = Arrays.stream(array);
      stream.forEach(System.out::println);
  }
  ```

- 通过`Stream`类中的`静态方法of()`创建一个流。

  ```java
  @Test
  public void test3() {
      Stream<String> stream = Stream.of("Java", "Python", "C++");
      stream.forEach(System.out::println);
  }
  ```

- 创建无限流，使用`Stream.iterate()`迭代数据，使用`Stream.generate()`生成数据。

  ```java
  @Test
  public void test4() {
      Stream<Integer> stream = Stream.iterate(0, (n) -> n + 1).limit(10);
      stream.forEach(System.out::println);
  }
  ```

  ```java
  @Test
  public void test5() {
      Stream<Integer> stream = Stream.generate(() -> (int) (Math.random() * 10)).limit(10);
      stream.forEach(System.out::println);
  }
  ```

#### 中间操作

一个中间操作链，对数据源的数据进行处理。

**筛选与切片**

- `filter(Predicate<T> predicate)`：接受Lambda，从流中排除某些元素。

  ```java
  List<House> houses = Arrays.asList(
  			new House("1", "beijing"),
  			new House("1", "shanghai"),
  			new House("1", "shenzhen"),
  			new House("1", "guangzhou"),
  			new House("2", "chengdu"),
  			new House("3", "xuzhou"));
  
  @Test
  public void test1() {
      Stream<House> stream = houses.stream()
              .filter((p) -> "2".equals(p.getType()));
  
      stream.forEach(System.out::println);
  }
  ```

- `distinct()`：筛选，通过流生成元素的hashCode()和equals()去除重复元素。

  ```java
  public class House {
  
  	private String type;
  
  	private String address;
  
  	public House() {
  	}
  
  	public House(String address) {
  		this.address = address;
  	}
  
  	public House(String type, String address) {
  		this.type = type;
  		this.address = address;
  	}
  
  	public String getType() {
  		return type;
  	}
  
  	public void setType(String type) {
  		this.type = type;
  	}
  
  	public String getAddress() {
  		return address;
  	}
  
  	public void setAddress(String address) {
  		this.address = address;
  	}
  
  	@Override
  	public boolean equals(Object o) {
  		if (this == o) return true;
  		if (!(o instanceof House)) return false;
  		House house = (House) o;
  		return type.equals(house.type) && address.equals(house.address);
  	}
  
  	@Override
  	public int hashCode() {
  		return Objects.hash(type, address);
  	}
  
  	@Override
  	public String toString() {
  		return "House{" +
  				"type='" + type + '\'' +
  				", address='" + address + '\'' +
  				'}';
  	}
  }
  ```

  ```java
  List<House> houses = Arrays.asList(
  			new House("1", "beijing"),
  			new House("1", "shanghai"),
  			new House("1", "shenzhen"),
  			new House("1", "guangzhou"),
  			new House("1", "guangzhou"),
  			new House("2", "chengdu"),
  			new House("3", "xuzhou"));
  
  @Test
  public void test4() {
      Stream<House> stream = houses.stream()
              .filter((p) -> "1".equals(p.getType()))
              .distinct();
  
      stream.forEach(System.out::println);
  }
  ```

- `limit(long maxSize)`：截断流，使其元素不超过指定数量。

  ```java
  @Test
  public void test2() {
      Stream<House> stream = houses.stream()
              .filter((p) -> "1".equals(p.getType()))
              .limit(3);
  
      stream.forEach(System.out::println);
  }
  ```

- `skip(long n)`：跳过元素，返回一个扔掉了前n个元素的流。若流中元素不足n个，则返回一个空流，与limit(long maxSize)互补。

  ```java
  @Test
  public void test3() {
      Stream<House> stream = houses.stream()
              .filter((p) -> "1".equals(p.getType()))
              .skip(2);
  
      stream.forEach(System.out::println);
  }
  ```


<span style="color:red;">注意：如果Stream流中的元素是对象，使用distinct()方法必须重写hashCode()和equals()</span>

**映射**

- `map(Function<T, R> mapper)`：将一个流中的每个元素映射为另一个元素。它接收一个Function函数作为参数，该函数定义了元素的转换规则，并返回一个新的Stream流。

  ```java
  List<House> houses = Arrays.asList(
  			new House("1", "beijing"),
  			new House("1", "shanghai"),
  			new House("1", "shenzhen"),
  			new House("1", "guangzhou"),
  			new House("1", "guangzhou"),
  			new House("2", "chengdu"),
  			new House("3", "xuzhou"));
  
  @Test
  public void test1() {
      List<Integer> numList = Arrays.asList(1, 2, 3, 4, 5);
  
      List<Integer> squaredNumbers = numList.stream()
              .map(n -> n * n)
              .collect(Collectors.toList());
      squaredNumbers.forEach(System.out::println);
  }
  
  @Test
  public void test2() {
      List<String> addressList = houses.stream()
              .map(House::getAddress)
              .collect(Collectors.toList());
      addressList.forEach(System.out::println);
  }
  ```

- `mapToInt(ToIntFunction<T> mapper)`：将流中的元素映射为int类型。它接收一个ToIntFunction函数作为参数，并返回一个IntStream。

  ```java
  @Test
  public void test3() {
      List<String> names = Arrays.asList("Alice", "Bob", "Charlie");
      IntStream nameLengths = names.stream()
              .mapToInt(String::length);
      nameLengths.forEach(System.out::println);
  }
  ```

- `mapToLong(ToLongFunction<T> mapper)`：将流中的元素映射为long类型。它接收一个ToLongFunction函数作为参数，并返回一个LongStream。

  ```java
  @Test
  public void test4() {
      List<Double> prices = Arrays.asList(10.5, 20.3, 30.1);
      LongStream priceInCents = prices.stream()
              .mapToLong(price -> (long) (price * 100));
      priceInCents.forEach(System.out::println);
  }
  ```

- `mapToDouble(ToDoubleFunction<T> mapper)`：将流中的元素映射为double类型。它接收一个ToDoubleFunction函数作为参数，并返回一个DoubleStream。

  ```java
  @Test
  public void test5() {
      List<Integer> integers = Arrays.asList(1, 2, 3, 4, 5);
      DoubleStream squareRoots = integers.stream()
              .mapToDouble(Math::sqrt);
      squareRoots.forEach(System.out::println);
  }
  ```

- `flatMap(Function<T, R> mapper)`：将流中的每个元素映射为一个流，然后将这些流扁平化成一个流。它接收一个Function函数作为参数，并返回一个新的Stream。

  ```java
  @Test
  public void test6() {
      List<List<Integer>> numbers = Arrays.asList(
              Arrays.asList(1, 2),
              Arrays.asList(3, 4),
              Arrays.asList(5, 6)
      );
  
      List<Integer> flattenedNumbers = numbers.stream()
              .flatMap(List::stream)
              .collect(Collectors.toList());
      System.out.println(flattenedNumbers);
  }
  ```

- `flatMapToInt(Function<T, IntStream> mapper)`：将流中的每个元素映射为一个IntStream，然后将这些IntStream扁平化成一个IntStream。

  ```java
  @Test
  public void test7() {
      List<List<Integer>> numbers = Arrays.asList(
              Arrays.asList(1, 2, 3),
              Arrays.asList(4, 5, 6),
              Arrays.asList(7, 8, 9)
      );
      IntStream flattenedNumbers = numbers.stream()
              .flatMapToInt(list -> list.stream().mapToInt(Integer::intValue));
      flattenedNumbers.forEach(System.out::println);
  }
  ```

- `flatMapToLong(Function<T, LongStream> mapper)`：将流中的每个元素映射为一个LongStream，然后将这些LongStream扁平化成一个LongStream。

  ```java
  @Test
  public void test8() {
      List<String> numbers = Arrays.asList("123", "4567", "89");
      LongStream flattenedNumbers = numbers.stream()
              .flatMapToLong(str -> str.chars().mapToLong(Character::getNumericValue));
      flattenedNumbers.forEach(System.out::println);
  }
  ```

- `flatMapToDouble(Function<T, DoubleStream> mapper)`：将流中的每个元素映射为一个DoubleStream，然后将这些DoubleStream扁平化成一个DoubleStream。

  ```java
  @Test
  public void test9() {
      List<List<Double>> numbers = Arrays.asList(
              Arrays.asList(1.1, 2.2, 3.3),
              Arrays.asList(4.4, 5.5, 6.6),
              Arrays.asList(7.7, 8.8, 9.9)
      );
      DoubleStream flattenedNumbers = numbers.stream()
              .flatMapToDouble(list -> list.stream().mapToDouble(Double::doubleValue));
      flattenedNumbers.forEach(System.out::println);
  }
  ```

- `peek(Consumer<T> action)`：它提供了一种在流的每个元素上执行操作的方式，同时还可以保留流的原始元素顺序。`peek`方法接受一个函数作为参数，该函数会被应用于流的每个元素，并可以执行一些操作，例如调试、打印或记录流的中间状态。

  ```java
  @Test
  public void test10() {
      List<Integer> numbers = Arrays.asList(1, 2, 3);
  
      numbers.stream()
              .peek(n -> System.out.println("Before map: " + n))
              .map(n -> n * 2)
              .peek(n -> System.out.println("After map: " + n))
              .forEach(n -> System.out.println("Final result: " + n));
  }
  ```

**排序**

- `sorted()`：使用默认的自然顺序对流中的元素进行排序。

  ```java
  @Test
  public void test1() {
      List<Integer> numbers = Arrays.asList(5, 2, 8, 1, 6, 3);
      List<Integer> sortedNumbers = numbers.stream()
              .sorted()
              .collect(Collectors.toList());
      System.out.println(sortedNumbers);
  }
  ```

- `sorted(Comparator<T> comparator)`：使用指定的Comparator比较器对流中的元素进行排序。

  ```java
  List<House> houses = Arrays.asList(
  			new House("1", "beijing"),
  			new House("1", "shanghai"),
  			new House("1", "shenzhen"),
  			new House("1", "guangzhou"),
  			new House("2", "chengdu"),
  			new House("3", "xuzhou"));
  
  @Test
  public void test2() {
      houses.stream()
              .sorted((e1, e2) -> {
                  if (e1.getType().equals(e2.getType())) {
                      return e1.getAddress().compareTo(e2.getAddress());
                  } else {
                      return e1.getType().compareTo(e2.getType());
                  }
              })
              .collect(Collectors.toList())
              .forEach(System.out::println);
  }
  
  @Test
  public void test3() {
      houses.stream()
              .sorted(Comparator.comparing(House::getType).thenComparing(House::getAddress))
              .forEach(System.out::println);
  }
  
  @Test
  public void test4() {
      houses.stream()
              .sorted(Comparator.comparing(House::getType).thenComparing(House::getAddress, String::compareTo))
              .forEach(System.out::println);
  }
  ```

#### 终止操作

一个终止操作，执行中间操作链，并产生结果。

**筛选与查找**

- `findAny()`：返回流中的任意一个元素。该操作可用于获取流中的任意一个元素，适用于并行流的情况。

  ```java
  public class House {
  
  	private String type;
  
  	private String address;
  
  	private Status status;
  
  	public House() {
  	}
  
  	public House(String address) {
  		this.address = address;
  	}
  
  	public House(String type, String address) {
  		this.type = type;
  		this.address = address;
  	}
  
  	public House(String type, String address, House.Status status) {
  		this.type = type;
  		this.address = address;
  		this.status = status;
  	}
  
  	public String getType() {
  		return type;
  	}
  
  	public void setType(String type) {
  		this.type = type;
  	}
  
  	public String getAddress() {
  		return address;
  	}
  
  	public void setAddress(String address) {
  		this.address = address;
  	}
  
  	public Status getStatus() {
  		return status;
  	}
  
  	public void setStatus(Status status) {
  		this.status = status;
  	}
  
  	@Override
  	public boolean equals(Object o) {
  		if (this == o) return true;
  		if (!(o instanceof House)) return false;
  		House house = (House) o;
  		return Objects.equals(type, house.type) && Objects.equals(address, house.address) && status == house.status;
  	}
  
  	@Override
  	public int hashCode() {
  		return Objects.hash(type, address, status);
  	}
  
  	public enum Status {
  		AVAILABLE,
  		UNSALEABLE,
  		SOLD
  	}
  
  	@Override
  	public String toString() {
  		return "House{" +
  				"type='" + type + '\'' +
  				", address='" + address + '\'' +
  				", status=" + status +
  				'}';
  	}
  }
  ```

  ```java
  List<House> houses = Arrays.asList(
  			new House("1", "beijing", House.Status.AVAILABLE),
  			new House("1", "shanghai", House.Status.SOLD),
  			new House("2", "chengdu", House.Status.UNSALEABLE),
  			new House("3", "xuzhou", House.Status.AVAILABLE));
  
  @Test
  public void test1() {
      Optional<House> anyHouse = houses.parallelStream()
              .filter(e -> "1".equals(e.getType()))
              .findAny();
      anyHouse.ifPresent(System.out::println);
  }
  ```

- `findFirst()`：返回流中的第一个元素。该操作适用于有序流或有序集合。

  ```java
  @Test
  public void test2() {
      Optional<House> firstHouse = houses.stream()
              .filter(e -> "1".equals(e.getType()))
              .findFirst();
      firstHouse.ifPresent(System.out::println);
  }
  ```

- `noneMatch(Predicate<T> predicate)`：检查流中的元素是否都不满足给定的条件。如果没有元素满足条件，返回true；否则返回false。

  ```java
  @Test
  public void test3() {
      boolean noneMatch = houses.stream()
              .noneMatch(e -> House.Status.AVAILABLE.equals(e.getStatus()));
      System.out.println(noneMatch);
  
  }
  ```

- `allMatch(Predicate<T> predicate)`：检查流中的所有元素是否都满足给定的条件。如果所有元素都满足条件，返回true；否则返回false。

  ```java
  @Test
  public void test4() {
      boolean allMatch = houses.stream()
              .allMatch(e -> House.Status.AVAILABLE.equals(e.getStatus()));
      System.out.println(allMatch);
  }
  ```

- `anyMatch(Predicate<T> predicate)`：检查流中的任意一个元素是否满足给定的条件。如果存在满足条件的元素，返回true；否则返回false。

  ```java
  @Test
  public void test5() {
      boolean anyMatch = houses.stream()
              .anyMatch(e -> House.Status.AVAILABLE.equals(e.getStatus()));
      System.out.println(anyMatch);
  }
  ```

- `count()`：返回流中的元素个数。

  ```java
  @Test
  public void test6() {
      long count = houses.stream()
              .count();
      System.out.println(count);
  }
  ```

- `max(Comparator<T> comparator)`：返回流中的最大元素，根据给定的Comparator比较器进行比较。

  ```java
  @Test
  public void test7() {
      Optional<String> maxHouse = houses.stream()
              .map(House::getType)
              .max(String::compareTo);
      maxHouse.ifPresent(System.out::println);
  }
  ```

- `min(Comparator<T> comparator)`：返回流中的最小元素，根据给定的Comparator比较器进行比较。

  ```java
  @Test
  public void test8() {
      Optional<House> minHouse = houses.stream()
              .min(Comparator.comparing(House::getAddress));
      minHouse.ifPresent(System.out::println);
  }
  ```

- `forEach(Consumer<T> action)`：`forEach`方法是Stream接口的一个终端操作方法，它接受一个Consumer函数作为参数，并对流中的每个元素执行该函数。`forEach`方法不保证元素的处理顺序，所以在并行流中，元素的处理顺序是不确定的。

  ```java
  @Test
  public void test9() {
      List<Integer> list = Arrays.asList(1, 3, 5, 7, 9);
      list.stream().forEach(System.out::println);
  }
  ```

- `forEachOrdered(Consumer<T> action)`：`forEachOrdered`方法与`forEach`方法类似，也是Stream接口的一个终端操作方法。它接受一个Consumer函数作为参数，并对流中的每个元素执行该函数。不同的是，`forEachOrdered`方法保证按照流的原始顺序处理元素，即使是在并行流中也是如此。

  ```java
  @Test
  public void test10() {
      List<Integer> list = Arrays.asList(1, 3, 5, 7, 9);
      list.parallelStream().forEachOrdered(System.out::println);
  }
  ```

**归约与收集**

Java 8引入了Stream API，其中包含了`归约（Reduction）`和`收集（Collecting）`的功能。这些功能允许您在处理集合数据时更加方便和灵活地进行`聚合`操作。

**归约（Reduction）**：将一个流中的元素按照某种规约方式，通过计算得到一个最终结果的过程。

- `reduce(T identity, BinaryOperator<T> accumulator)`：对流中的元素进行归约操作，返回归约结果。`identity`是初始值，`accumulator`是一个BinaryOperator函数，用于将两个元素结合生成新值。

  ```java
  public class Employee {
  
  	private String name;
  
  	private Integer age;
  
  	private Double salary;
  
  	private State state;
  
  	public Employee() {
  	}
  
  	public Employee(String name, Integer age, Double salary, State state) {
  		this.name = name;
  		this.age = age;
  		this.salary = salary;
  		this.state = state;
  	}
  
  	public String getName() {
  		return name;
  	}
  
  	public void setName(String name) {
  		this.name = name;
  	}
  
  	public Integer getAge() {
  		return age;
  	}
  
  	public void setAge(Integer age) {
  		this.age = age;
  	}
  
  	public Double getSalary() {
  		return salary;
  	}
  
  	public void setSalary(Double salary) {
  		this.salary = salary;
  	}
  
  	public State getState() {
  		return state;
  	}
  
  	public void setState(State state) {
  		this.state = state;
  	}
  
  	public enum State {
  		FREE,
  		BUSY,
  		VOCATION;
  	}
  
  	@Override
  	public String toString() {
  		return "Employee{" +
  				"name='" + name + '\'' +
  				", age=" + age +
  				", salary=" + salary +
  				", state=" + state +
  				'}';
  	}
  }
  ```

  ```java
  @Test
  public void test1() {
      List<BigDecimal> decimals = Arrays.asList(
              new BigDecimal("1.23"),
              new BigDecimal("2.37"),
              new BigDecimal("1.58"),
              new BigDecimal("4.7")
      );
      BigDecimal reduce = decimals.stream()
              .reduce(BigDecimal.ZERO, BigDecimal::add);
      System.out.println(reduce);
  }
  ```

- `reduce(BinaryOperator<T> accumulator)`：对流中的元素进行归约操作，返回Optional类型的归约结果。`accumulator`是一个BinaryOperator函数，用于将两个元素结合生成新值。

  ```java
  List<Employee> employeeList = Arrays.asList(
          new Employee("zhangsan", 28, 5288.0, Employee.State.FREE),
          new Employee("lisi", 25, 4368.0, Employee.State.BUSY),
          new Employee("wangwu", 30, 6238.0, Employee.State.VOCATION),
          new Employee("zhaoliu", 46, 5150.0, Employee.State.BUSY),
          new Employee("zhaoliu", 40, 5150.0, Employee.State.BUSY)
  );
  
  @Test
  public void test2() {
      Optional<Double> reduce = employeeList.stream()
              .map(Employee::getSalary)
              .reduce(Double::sum);
      reduce.ifPresent(System.out::println);
  }
  ```

**收集（Collecting）**：将流中的元素聚集到一个集合中的操作。

- `toList()`：将流中的元素收集到一个List集合中。

  ```java
  @Test
  public void test3() {
      List<String> list = employeeList.stream()
              .map(Employee::getName)
              .collect(Collectors.toList());
      list.forEach(System.out::println);
  }
  ```

- `toSet()`：将流中的元素收集到一个Set集合中。

  ```java
  @Test
  public void test4() {
      Set<String> set = employeeList.stream()
              .map(Employee::getName)
              .collect(Collectors.toSet());
      set.forEach(System.out::println);
  }
  ```

- `joining(CharSequence delimiter)`：将流中的元素拼接成一个字符串，使用指定的连接符分隔元素。

  ```java
  @Test
  public void test5() {
      String str = employeeList.stream()
              .map(Employee::getName)
              .collect(Collectors.joining(","));
      System.out.println(str);
  }
  ```

- `toCollection(Supplier<C> collectionFactory)`：将流中的元素收集到一个自定义集合中。

  ```java
  @Test
  public void test6() {
      LinkedHashSet<String> linkedHashSet = employeeList.stream()
              .map(Employee::getName)
              .collect(Collectors.toCollection(LinkedHashSet::new));
      linkedHashSet.forEach(System.out::println);
  }
  ```

- `toMap(keyMapper, valueMapper)`：将流中的元素根据给定的Key和Value映射函数收集到一个Map中。

  ```java
  @Test
  public void test7() {
      Map<String, Employee> employeeMap = employeeList.stream()
              .collect(Collectors.toMap(it -> it.getName() + ":" + it.getAge(), item -> item));
      employeeMap.forEach((k, v) -> System.out.println("k: " + k + ", v: " + v));
  
      Map<String, Employee> collect = employeeList.stream()
              .collect(Collectors.toMap(Employee::getName, item -> item, (p1, p2) -> p1));
      System.out.println(collect);
  
      Map<Integer, Double> collect1 = employeeList.stream()
              .collect(Collectors.toMap(Employee::getAge, Employee::getSalary));
      System.out.println(collect1);
  }
  ```

- `groupingBy(Function<T, K> classifier)`：`groupingBy`方法接受一个分类函数作为参数，并返回一个按照分类函数对元素进行分组的收集器。

  ```java
  @Test
  public void test8() {
      Map<String, List<Employee>> collect = employeeList.stream()
              .collect(Collectors.groupingBy(Employee::getName));
      System.out.println(collect);
  }
  ```

- `groupingBy(Function<T, K> classifier, Collector<T, A, D> downstream)`：`groupingBy`方法还支持多级分组，即在第一级分组的基础上再进行二级或更多级的分组操作。

  ```java
  @Test
  public void test9() {
      Map<String, Map<Integer, List<Employee>>> collect = employeeList.stream()
              .collect(Collectors.groupingBy(Employee::getName, Collectors.groupingBy(Employee::getAge)));
      System.out.println(collect);
  }
  ```

- `mapping(Function<T, U> mapper, Collector<U, A, R> downstream)`：`mapping`方法接收两个参数：一个函数，用于对流中的元素进行映射；一个用于收集映射结果的收集器。它将流中的元素进行映射，并将映射结果收集到指定的收集器中。

  ```java
  @Test
  public void test10() {
      Map<String, Set<Integer>> collect = employeeList.stream()
              .collect(Collectors.groupingBy(Employee::getName, Collectors.mapping(Employee::getAge, Collectors.toSet())));
      System.out.println(collect);
  }
  ```

- `reducing(T identity, BinaryOperator<T> op)`：`reducing`方法接收两个参数：一个初始值，一个函数，用于对流中的元素进行操作，以及一个用于收集结果的收集器。它将流中的元素进行操作，并将操作结果收集到指定的收集器中。

  ```java
  @Test
  public void test11() {
      Map<String, Double> collect = employeeList.stream()
              .collect(Collectors.groupingBy(Employee::getName, Collectors.reducing(0.0, Employee::getSalary, Double::sum)));
      System.out.println(collect);
  }
  ```

- `collectingAndThen(Collector<T,A,R> downstream,Function<R,RR> finisher)`：`collectingAndThen`方法接受两个参数：一个用于收集元素的收集器和一个函数，用于对收集结果进行转换。它将元素收集到指定的收集器中，并在收集完成后对结果进行转换。

  ```java
  @Test
  public void test12() {
      String collect = employeeList.stream()
              .map(Employee::getName)
              .collect(Collectors.collectingAndThen(Collectors.toSet(), item -> String.join("-", item)));
      System.out.println(collect);
  }
  ```

- `counting()`：`counting`方法是一个简单的收集器，它用于计算流中元素的数量，并返回结果。

  ```java
  @Test
  public void test13() {
      Map<String, Long> collect = employeeList.stream()
              .collect(Collectors.groupingBy(Employee::getName, Collectors.counting()));
      System.out.println(collect);
  }
  ```

- `averagingDouble(ToDoubleFunction<T> mapper)`：`averagingDouble`方法用于计算流中元素的平均值，它接受一个提取元素的函数作为参数，并返回一个收集器。

  ```java
  @Test
  public void test14() {
      Double collect = employeeList.stream()
              .collect(Collectors.averagingDouble(Employee::getSalary));
      System.out.println(collect);
  }
  ```

- `summingDouble(ToDoubleFunction<T> mapper)`：`summingDouble`接受一个提取元素的函数作为参数，并返回一个收集器，用于计算流中元素的总和。

  ```java
  @Test
  public void test15() {
      Double collect = employeeList.stream()
              .collect(Collectors.summingDouble(Employee::getSalary));
      System.out.println(collect);
  }
  ```

- `maxBy(Comparator<T> comparator)`：`maxBy`方法用于找到流中的最大值。它接受一个提取元素的函数作为参数，并返回一个收集器。

  ```java
  @Test
  public void test16() {
      Optional<Employee> maxSalary = employeeList.stream()
              .collect(Collectors.maxBy(Comparator.comparing(Employee::getSalary)));
      Double result = maxSalary.map(Employee::getSalary).orElse(0.0);
      System.out.println(result);
  }
  ```

- `minBy(Comparator<T> comparator)`：`minBy`方法用于找到流中的最小值。它接受一个提取元素的函数作为参数，并返回一个收集器。

  ```java
  @Test
  public void test17() {
      Optional<Double> minSalary = employeeList.stream()
              .map(Employee::getSalary)
              .collect(Collectors.minBy(Double::compareTo));
      minSalary.ifPresent(System.out::println);
  }
  ```

- `summarizingDouble(ToDoubleFunction<T> mapper)`：`summarizingDouble`方法用于计算流中元素的统计信息，包括总数、平均值、最大值、最小值等。它接受一个提取元素的函数作为参数，并返回一个收集器。

  ```java
  @Test
  public void test18() {
      DoubleSummaryStatistics collect = employeeList.stream()
              .collect(Collectors.summarizingDouble(Employee::getSalary));
      System.out.println(collect);
      System.out.println(collect.getCount());
      System.out.println(collect.getSum());
      System.out.println(collect.getAverage());
      System.out.println(collect.getMax());
      System.out.println(collect.getMin());
  }
  ```

- `partitioningBy(Predicate<T> predicate)`：`partitioningBy`方法接受一个`Predicate`函数作为参数，用于判断元素是否满足条件。它返回一个`Collector`，用于将流中的元素分区为两个部分，一个是满足条件的部分，另一个是不满足条件的部分。返回的结果是一个`Map<Boolean, List<T>>`，其中键为`true`表示满足条件的部分，键为`false`表示不满足条件的部分。

  ```java
  @Test
  public void test19() {
      Map<Boolean, List<Employee>> collect = employeeList.stream()
              .collect(Collectors.partitioningBy(e -> e.getSalary() > 5000));
      System.out.println(collect);
  }
  ```

### 并行流与串行流

Java8中将并行进行了优化，我们可以很容易的对数据进行并行操作。Stream API可以声明性的通过`parallel()`与`sequential()`在并行流与串行流之间进行切换。

- `parallel()`: `parallel()`方法用于将一个顺序的Stream流转换为并行流。并行流允许多个线程同时处理流的元素，从而提高处理速度。在使用`parallel()`方法后，流的操作将会并行执行。

  ```java
  @Test
  public void test1() {
      Instant start = Instant.now();
      LongStream.rangeClosed(0, 50000000000L)
              .parallel()
              .reduce(0, Long::sum);
  
      Instant end = Instant.now();
      System.out.println("耗费时间为: " + (Duration.between(start, end).toMillis()));
  }
  ```

- `sequential()`: `sequential()`方法用于将一个并行流转换为顺序流。顺序流按照流的顺序依次处理元素，只使用一个线程进行处理。在使用`sequential()`方法后，流的操作将会顺序执行。

  ```java
  @Test
  public void test2() {
      Instant start = Instant.now();
      LongStream.rangeClosed(0, 50000000000L)
              .sequential()
              .reduce(0, Long::sum);
  
      Instant end = Instant.now();
      System.out.println("耗费时间为: " + (Duration.between(start, end).toMillis()));
  }
  ```

## <span id="optional">Optional类</span>

在Java8中，Optional类(java.util.Optional<T>)是用于处理可能为空的值的容器类。它提供了一些常见的方法来处理和操作这些可选值。下面是一些常见的Optional类方法及其使用示例：

- `empty()`：返回一个空的Optional实例。

  ```java
  @Test
  public void test1() {
      Optional<Object> empty = Optional.empty();
  }
  ```

- `ofNullable(T value)`：如果T非空，返回一个描述指定值的Optional，否则返回一个空的Optional。

  ```java
  @Test
  public void test2() {
      Optional<String> optional = Optional.ofNullable("Hello world!");
      System.out.println(optional.get());
  }
  ```

- `of(T value)`：返回一个具有指定的当前非空值的Optional，如果T为空，则抛出空指针异常。

  ```java
  @Test
  public void test3() {
      Optional<String> optional = Optional.of("hello");
      System.out.println(optional.get());
  }
  ```

- `get()`：获取Optional对象中的值。如果Optional对象为空，则抛出NoSuchElementException异常。

  ```java
  @Test
  public void test4() {
      Optional<String> optional = Optional.of("zs");
      System.out.println(optional.get());
  }
  ```

- `ifPresent(Consumer<T> consumer)`：如果Optional对象包含值，就用这个值调用指定的消费者，否则不做任何操作。

  ```java
  @Test
  public void test5() {
      Optional<String> optional = Optional.ofNullable("Hello");
      optional.ifPresent(value -> System.out.println("Value is present: " + value));
  }
  ```

- `isPresent()`：判断Optional对象是否包含值，如果包含返回true，否则返回false。

  ```java
  @Test
  public void test6() {
      Optional<String> optional = Optional.ofNullable("Hello");
      if (optional.isPresent()) {
          System.out.println("Value is present: " + optional.get());
      } else {
          System.out.println("Value is absent");
      }
  }
  ```

- `orElse(T other)`：如果Optional对象为空，则返回指定的默认值。

  ```java
  @Test
  public void test7() {
      Optional<String> optional = Optional.empty();
      String value = optional.orElse("Default Value");
      System.out.println("Value: " + value);
  }
  ```

- `orElseGet(Supplier<T> supplier)`：如果Optional对象为空，则根据提供的Supplier生成默认值。

  ```java
  @Test
  public void test8() {
      Optional<String> optional = Optional.empty();
      String value = optional.orElseGet(() -> {
          // 生成默认值的逻辑
          return "Default Value";
      });
      System.out.println("Value: " + value);
  }
  ```

- `orElseThrow(Supplier<X> exceptionSupplier)`：如果Optional对象为空，则抛出指定的异常。

  ```java
  @Test
  public void test9() {
      Optional<String> optional = Optional.empty();
      optional.orElseThrow(() -> new RuntimeException("Value is absent"));
  }
  ```

- `map(Function<T, U> mapper)`：对Optional对象中的值进行映射转换，并返回一个新的Optional对象。

  ```java
  @Test
  public void test10() {
      // 使用map操作转换Employee对象的name属性
      Optional<String> nameOptional = Optional.of(new Employee("John Doe", 30, 2500.0, Employee.State.FREE)).map(Employee::getName);
      nameOptional.ifPresent(name -> System.out.println("Name: " + name));
  }
  ```

- `filter(Predicate<T> predicate)`：对Optional对象进行过滤操作，如果满足条件则返回原始Optional对象，否则返回一个空的Optional对象。

  ```java
  @Test
  public void test11() {
      // 使用filter操作过滤Employee对象的age属性
      Optional<Employee> filteredOptional = Optional.of(new Employee("John Doe", 30, 2500.0, Employee.State.FREE)).filter(e -> e.getAge() > 25);
      filteredOptional.ifPresent(employee -> System.out.println("Filtered Employee: " + employee.getName()));
  }
  ```

- `flatMap(Function<T, Optional<U>> mapper)`：对Optional对象进行扁平化操作，将内部的Optional对象展平，并返回一个新的Optional对象。

  ```java
  @Test
  public void test12() {
      Optional<Integer> ageOptional = Optional.of(new Employee("John Doe", 30, 2500.0, Employee.State.FREE)).flatMap(e -> Optional.ofNullable(e.getAge()));
      ageOptional.ifPresent(item -> System.out.println("Age: " + item));
  }
  ```

下面提供一个使用Optional类对级联对象进行判空处理的示例：

```java
public class Teacher {

	private String tname;

	private Student student;

	public Teacher(String tname, Student student) {
		this.tname = tname;
		this.student = student;
	}

	public String getTname() {
		return tname;
	}

	public void setTname(String tname) {
		this.tname = tname;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}
}
```

```java
public class Student {

	private String sname;

	private Integer age;

	private Course course;

	public Student(String sname, Integer age, Course course) {
		this.sname = sname;
		this.age = age;
		this.course = course;
	}

	public String getSname() {
		return sname;
	}

	public void setSname(String sname) {
		this.sname = sname;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}
}
```

```java
public class Course {

	private String cname;

	private Double price;

	public Course(String cname, Double price) {
		this.cname = cname;
		this.price = price;
	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}
}
```

```java
@Test
public void test13() {
    Course courseWithPrice = new Course("Math", 100.0);
    Course courseWithoutPrice = new Course("English", null);
    Student studentWithCourse = new Student("Alice", 20, courseWithPrice);
    Student studentWithoutCourse = new Student("Bob", 22, null);
    Teacher teacherWithStudent = new Teacher("John", studentWithCourse);
    Teacher teacherWithoutStudent = new Teacher("Mike", null);

    Double withPrice = Optional.ofNullable(teacherWithStudent)
            .map(Teacher::getStudent)
            .map(Student::getCourse)
            .map(Course::getPrice)
            .orElse(0.0);

    System.out.println(withPrice);

    Double anotherWithoutPrice = Optional.ofNullable(teacherWithoutStudent)
            .flatMap(s -> Optional.ofNullable(s.getStudent()))
            .flatMap(s -> Optional.ofNullable(s.getCourse()))
            .map(Course::getPrice)
            .orElse(0.0);

    System.out.println(anotherWithoutPrice);

    Double withoutPrice = Optional.ofNullable(teacherWithoutStudent)
            .map(Teacher::getStudent)
            .map(Student::getCourse)
            .map(Course::getPrice)
            .orElse(0.0);

    System.out.println(withoutPrice);
}
```

## <span id="default-method">接口中的默认方法与静态方法</span>

**默认方法（Default Method）**

指接口中带有默认实现的方法。它们允许在接口中定义具体的方法实现，而不需要实现该接口的所有类都必须实现这些方法。默认方法使用`default`关键字进行修饰。

若一个接口中定义了一个默认方法，而另外一个父类或接口中又定义了一个同名的方法时。

- 选择父类中的方法。如果一个父类提供了具体的实现，那么接口中具有相同名称和参数的默认方法会被忽略。

  ```java
  public interface MyFunction {
  
  	default String getName() {
  		return "myFun getName";
  	}
  }
  ```

  ```java
  public class MyClass {
  
  	public String getName() {
  		return "MyClass getName";
  	}
  }
  ```

  ```java
  public class SubClass extends MyClass implements MyFunction{
  
  	@Override
  	public String getName() {
  		return super.getName();
  	}
  
  	public static void main(String[] args) {
  		SubClass sc = new SubClass();
  		System.out.println(sc.getName());
  	}
  }
  ```

- 接口冲突。如果一个父接口提供一个默认方法，而另外一个接口也提供了一个具有相同名称和参数列表的方法(不管方法是否是默认方法)，那么必须覆盖该方法来解决冲突

  ```java
  public interface MyInterface {
  
  	default String getName() {
  		return "MyInterface getName";
  	}
  }
  ```

  ```java
  public class SubClass implements MyFunction, MyInterface{
  
  	@Override
  	public String getName() {
  		return MyInterface.super.getName();
  	}
  
  	public static void main(String[] args) {
  		SubClass sc = new SubClass();
  		System.out.println(sc.getName());
  	}
  }
  ```

  ```java
  public class SubClass implements MyFunction, MyInterface{
  
  	@Override
  	public String getName() {
  		return MyFunction.super.getName();
  	}
  
  	public static void main(String[] args) {
  		SubClass sc = new SubClass();
  		System.out.println(sc.getName());
  	}
  }
  ```

**静态方法（Static Method）**

静态方法是指在接口中使用`static`关键字定义的方法。它们属于接口本身，而不是实现该接口的具体类。静态方法可以直接通过接口名称进行调用，无需创建接口的实例。

```java
public interface MathOperations {

	static int add(int a, int b) {
		return a + b;
	}

	static int sub(int a, int b) {
		return a - b;
	}
}
```

```java
public static void main(String[] args) {
    int sum = MathOperations.add(2, 5);
    int sub = MathOperations.sub(5, 3);
    System.out.println("sum: " + sum);
    System.out.println("sub: " + sub);
}
```

## <span id="new-date">新时间日期API</span>

LocalDate、LocalTime、LocalDateTime类的实例是**不可变的对象**，分别表示使用`ISO-8601`日历系统(ISO-8601日历系统是国际标准化组织制定的现代公民的日期和时间的表示法)的日期、时间、日期和时间。它们提供了简单的日期或时间，并不包括当前的时间信息。也不包含与时区相关的信息。

### LocalDateTime

- 获取当前日期时间

  ```java
  @Test
  public void test1() {
      LocalDateTime localDateTime = LocalDateTime.now();
  
      // 当前年的多少天
      int dayOfYear = localDateTime.getDayOfYear();
  
      // 当前月的多少天
      int dayOfMonth = localDateTime.getDayOfMonth();
  
      // 当前周的星期几
      DayOfWeek dayOfWeek = localDateTime.getDayOfWeek();
  
      // 当前年份
      int year = localDateTime.getYear();
  
      // 当前月份
      int monthValue = localDateTime.getMonthValue();
  
      // 当前月份英文
      Month month = localDateTime.getMonth();
  
      // 当前日期小时
      int hour = localDateTime.getHour();
  
      // 当前日期分钟
      int minute = localDateTime.getMinute();
  
      // 当前日期秒数
      int second = localDateTime.getSecond();
  
      System.out.println("当前时间: " + localDateTime);
      System.out.println("当前年的多少天: " + dayOfYear);
      System.out.println("当前月的多少天: " + dayOfMonth);
      System.out.println("当前周的星期几: " + dayOfWeek);
      System.out.println("当前年份: " + year);
      System.out.println("当前月份: " + monthValue);
      System.out.println("当前月份英文: " + month);
      System.out.println("当前日期小时: " + hour);
      System.out.println("当前日期分钟: " + minute);
  
      System.out.println("当前日期秒数: " + second);
  }
  ```

- 获取指定日期时间

  ```java
  @Test
  public void test2() {
      LocalDateTime dateTime = LocalDateTime.of(2029, 12, 31, 23, 59, 59);
  
      LocalDateTime localDateTime = LocalDateTime.now();
  
      LocalDateTime yearLocalDateTime = localDateTime.withYear(2025);
  
      LocalDateTime monthLocalDateTime = localDateTime.withMonth(11);
  
      LocalDateTime dayOfMonthLocalDateTime = localDateTime.withDayOfMonth(25);
  
      LocalDateTime hourLocalDateTime = localDateTime.withHour(20);
  
      LocalDateTime minuteLocalDateTime = localDateTime.withMinute(28);
  
      LocalDateTime secondLocalDateTime = localDateTime.withSecond(58);
  
      System.out.println("指定日期时间为: " + dateTime);
      System.out.println("指定日期时间的年份: " + yearLocalDateTime);
      System.out.println("指定日期时间的月份: " + monthLocalDateTime);
      System.out.println("指定日期时间的月份的第几天: " + dayOfMonthLocalDateTime);
      System.out.println("指定日期时间的小时: " + hourLocalDateTime);
      System.out.println("指定日期时间的分钟: " + minuteLocalDateTime);
      System.out.println("指定日期时间的秒: " + secondLocalDateTime);
  }
  ```

- 比较日期时间

  ```java
  @Test
  public void test3() {
      LocalDateTime localDateTime = LocalDateTime.now();
  
      LocalDateTime compareLocalDateTime = localDateTime.withYear(2024).withMonth(5).withDayOfMonth(28);
  
      boolean equal = localDateTime.isEqual(compareLocalDateTime);
  
      boolean before = localDateTime.isBefore(compareLocalDateTime);
  
      boolean after = localDateTime.isAfter(compareLocalDateTime);
  
      System.out.println("当前日期时间是否等于指定的日期时间: " + equal);
      System.out.println("当前日期时间是否早于指定的日期时间: " + before);
      System.out.println("当前日期时间是否晚于指定的日期时间: " + after);
  }
  ```

- 操纵日期时间

  ```java
  @Test
  public void test5() {
      LocalDateTime localDateTime = LocalDateTime.now();
  
      LocalDateTime yearsPlusLocalDateTime = localDateTime.plusYears(3);
  
      LocalDateTime monthsPlusLocalDateTime = localDateTime.plusMonths(5);
  
      LocalDateTime weeksPlusLocalDateTime = localDateTime.plusWeeks(2);
  
      LocalDateTime daysPlusLocalDateTime = localDateTime.plusDays(5);
  
      LocalDateTime hoursPlusLocalDateTime = localDateTime.plusHours(8);
  
      LocalDateTime minutesPlusLocalDateTime = localDateTime.plusMinutes(20);
  
      LocalDateTime secondsPlusLocalDateTime = localDateTime.plusSeconds(35);
  
      LocalDateTime yearsMinusLocalDateTime = localDateTime.minusYears(3);
  
      LocalDateTime monthsMinusLocalDateTime = localDateTime.minusMonths(5);
  
      LocalDateTime weeksMinusLocalDateTime = localDateTime.minusWeeks(2);
  
      LocalDateTime daysMinusLocalDateTime = localDateTime.minusDays(5);
  
      LocalDateTime hoursMinusLocalDateTime = localDateTime.minusHours(8);
  
      LocalDateTime minutesMinusLocalDateTime = localDateTime.minusMinutes(20);
  
      LocalDateTime secondsMinusLocalDateTime = localDateTime.minusSeconds(35);
  
      System.out.println("三年后的日期时间: " + yearsPlusLocalDateTime);
      System.out.println("五个月后的日期时间: " + monthsPlusLocalDateTime);
      System.out.println("两周后的日期时间: " + weeksPlusLocalDateTime);
      System.out.println("五天后的日期时间: " + daysPlusLocalDateTime);
      System.out.println("八小时后的日期时间: " + hoursPlusLocalDateTime);
      System.out.println("二十分钟后的日期时间: " + minutesPlusLocalDateTime);
      System.out.println("三十五秒后的日期时间: " + secondsPlusLocalDateTime);
  
      System.out.println("===================================================");
  
      System.out.println("三年前的日期时间: " + yearsMinusLocalDateTime);
      System.out.println("五个月前的日期时间: " + monthsMinusLocalDateTime);
      System.out.println("两周前的日期时间: " + weeksMinusLocalDateTime);
      System.out.println("五天前的日期时间: " + daysMinusLocalDateTime);
      System.out.println("八小时前的日期时间: " + hoursMinusLocalDateTime);
      System.out.println("二十分钟前的日期时间: " + minutesMinusLocalDateTime);
      System.out.println("三十五秒前的日期时间: " + secondsMinusLocalDateTime);
  }
  ```

### LocalDate

- 获取当前日期

  ```java
  @Test
  public void test6() {
      LocalDate localDate = LocalDate.now();
  
      int dayOfYear = localDate.getDayOfYear();
  
      int monthValue = localDate.getMonthValue();
  
      int dayOfMonth = localDate.getDayOfMonth();
  
      DayOfWeek dayOfWeek = localDate.getDayOfWeek();
  
      int year = localDate.getYear();
  
      System.out.println("当前日期: " + localDate);
      System.out.println("当前年份的多少天: " + dayOfYear);
      System.out.println("当前年份的月份: " + monthValue);
      System.out.println("当前月的第几天: " + dayOfMonth);
      System.out.println("当前周的星期几英文: " + dayOfWeek);
      System.out.println("当前年份: " + year);
  }
  ```

- 获取指定日期

  ```java
  @Test
  public void test7() {
      LocalDate date = LocalDate.of(2028, 5, 30);
  
      LocalDate now = LocalDate.now();
  
      LocalDate withYear = now.withYear(2026);
  
      LocalDate withMonth = now.withMonth(8);
  
      LocalDate withDayOfMonth = now.withDayOfMonth(28);
  
      LocalDate withDayOfYear = now.withDayOfYear(200);
  
      System.out.println("指定日期为: " + date);
      System.out.println("当前日期为: " + now);
      System.out.println("指定日期年份的日期为: " + withYear);
      System.out.println("指定日期月份的日期为: " + withMonth);
      System.out.println("指定日期为当月的第几天的的日期为: " + withDayOfMonth);
      System.out.println("指定日期为当年的第几天的的日期为: " + withDayOfYear);
  }
  ```

- 比较日期

  ```java
  @Test
  public void test8() {
      LocalDate now = LocalDate.now();
  
      LocalDate compareLocalDate = now.withYear(2024).withMonth(9).withDayOfMonth(29);
  
      boolean equal = now.isEqual(compareLocalDate);
  
      boolean before = now.isBefore(compareLocalDate);
  
      boolean after = now.isAfter(compareLocalDate);
  
      System.out.println("当前日期是否等于指定的日期: " + equal);
      System.out.println("当前日期是否早于指定的日期: " + before);
      System.out.println("当前日期是否晚于指定的日期: " + after);
  }
  ```

- 操纵日期

  ```java
  @Test
  public void test9() {
      LocalDate now = LocalDate.now();
  
      LocalDate years = now.plusYears(6);
  
      LocalDate months = now.plusMonths(7);
  
      LocalDate days = now.plusDays(80);
  
      System.out.println("六年后的日期为: " + years);
      System.out.println("七个月后的日期为: " + months);
      System.out.println("八十天后的日期为: " + days);
  }
  ```

- 获取年、月、周第一天和最后一天

  ```java
  @Test
  public void test1() {
      LocalDate now = LocalDate.now();
  
      LocalDate firstDayOfYear = now.with(TemporalAdjusters.firstDayOfYear());
      LocalDate lastDayOfYear = now.with(TemporalAdjusters.lastDayOfYear());
      LocalDate firstDayOfNextYear = now.with(TemporalAdjusters.firstDayOfNextYear());
      int lengthOfYear = now.lengthOfYear();
  
      LocalDate firstDayOfMonth = now.with(TemporalAdjusters.firstDayOfMonth());
      LocalDate lastDayOfMonth = now.with(TemporalAdjusters.lastDayOfMonth());
      LocalDate firstDayOfNextMonth = now.with(TemporalAdjusters.firstDayOfNextMonth());
      int lengthOfMonth = now.lengthOfMonth();
  
      LocalDate firstDayOfWeek = now.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
      LocalDate lastDayOfWeek = now.with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY));
  
      LocalDate dayOfWeekInMonth = now.with(TemporalAdjusters.dayOfWeekInMonth(1, DayOfWeek.TUESDAY));
  
      System.out.println("本年的第一天: " + firstDayOfYear);
      System.out.println("本年的最后一天: " + lastDayOfYear);
      System.out.println("明年的第一天: " + firstDayOfNextYear);
      System.out.println("本年的天数为: " + lengthOfYear);
      System.out.println("本月的第一天: " + firstDayOfMonth);
      System.out.println("本月的最后一天: " + lastDayOfMonth);
      System.out.println("下月的第一天: " + firstDayOfNextMonth);
      System.out.println("本月的天数为: " + lengthOfMonth);
      System.out.println("本周的第一天: " + firstDayOfWeek);
      System.out.println("本周的最后一天: " + lastDayOfWeek);
      System.out.println("本月的第几个星期几: " + dayOfWeekInMonth);
  }
  ```

### LocalTime

- 获取当前时间

  ```java
  @Test
  public void test10() {
      LocalTime now = LocalTime.now();
  
      int hour = now.getHour();
  
      int minute = now.getMinute();
  
      int second = now.getSecond();
  
      System.out.println("当前时间为: " + now);
      System.out.println("当前时间小时为: " + hour);
      System.out.println("当前时间分钟为: " + minute);
      System.out.println("当前时间秒为: " + second);
  }
  ```

- 获取指定时间

  ```java
  @Test
  public void test11() {
      LocalTime localTime = LocalTime.now().withHour(10).withMinute(25).withSecond(59);
      System.out.println(localTime);
  }
  ```

- 比较时间

  ```java
  @Test
  public void test12() {
      LocalTime now = LocalTime.now();
  
      LocalTime compareLocalTime = now.withHour(10).withMinute(55).withSecond(59);
  
      boolean before = now.isBefore(compareLocalTime);
  
      boolean after = now.isAfter(compareLocalTime);
  
      System.out.println("当前时间早于指定时间: " + before);
  
      System.out.println("当前时间晚于指定时间: " + after);
  }
  ```

- 操纵时间

  ```java
  @Test
  public void test13() {
      LocalTime now = LocalTime.now();
  
      LocalTime plusHours = now.plusHours(5);
  
      LocalTime plusMinutes = now.plusMinutes(40);
  
      LocalTime plusSeconds = now.plusSeconds(45);
  
      LocalTime minusHours = now.minusHours(10);
  
      LocalTime minusMinutes = now.minusMinutes(30);
  
      LocalTime minusSeconds = now.minusSeconds(39);
  
      System.out.println("五小时后的时间为: " + plusHours);
      System.out.println("四十分钟后的时间为: " + plusMinutes);
      System.out.println("四十五秒后的时间为: " + plusSeconds);
      System.out.println("十小时前的时间为: " + minusHours);
      System.out.println("三十分钟前的时间为: " + minusMinutes);
      System.out.println("三十九秒前的时间为: " + minusSeconds);
  }
  ```

### Instant

`Instant`类是不可变的，表示的是自1970年1月1日 00:00:00(UTC/GMT的午夜)以来经过的时间的秒数。它是用于表示机器时间的一种方式，与时区无关，不考虑闰秒。Java中的时间戳是Unix时间戳。

- 当前时间

  ```java
  @Test
  public void test1() {
      Instant now = Instant.now();
      long epochSecond = now.getEpochSecond();
      long epochMilli = now.toEpochMilli();
  
      System.out.println("当前时间为: " + now);
      System.out.println("时间戳为: " + epochSecond);
      System.out.println("时间毫秒数为: " + epochMilli);
  }
  ```

- 指定时间戳的Instant

  ```java
  @Test
  public void test2() {
      Instant instant = Instant.ofEpochSecond(1685946043L);
      System.out.println(instant);
  }
  ```

- 比较时间

  ```java
  @Test
  public void test3() {
      Instant now = Instant.now();
      Instant compareInstant = now.minusSeconds(30);
  
      boolean before = now.isBefore(compareInstant);
      boolean after = now.isAfter(compareInstant);
  
      System.out.println("当前时间早于指定时间: " + before);
      System.out.println("当前时间晚于指定时间: " + after);
  }
  ```

- 操纵时间

  ```java
  @Test
  public void test4() {
      Instant now = Instant.now();
      Instant plusSeconds = now.plusSeconds(100);
      Instant plusMillis = now.plusMillis(20000);
  
      Instant minusSeconds = now.minusSeconds(100);
      Instant minusMillis = now.minusMillis(20000);
  
      System.out.println("当前时间为: " + now);
      System.out.println("100s后的时间为: " + plusSeconds);
      System.out.println("20000ms后的时间为: " + plusMillis);
      System.out.println("100s前的时间为: " + minusSeconds);
      System.out.println("200000s前的时间为: " + minusMillis);
  }
  ```

### 计算日期时间差

- 使用`java.time.Duration`类

  ```java
  @Test
  public void test1() {
      LocalDateTime now = LocalDateTime.now();
      LocalDateTime startTime = now.withYear(2023).withMonth(5).withDayOfMonth(25).withHour(5).withMinute(52).withSecond(26);
      LocalDateTime endTime = now.withYear(2023).withMonth(11).withDayOfMonth(16).withHour(8).withMinute(29).withSecond(38);
  
      Duration between = Duration.between(startTime, endTime);
  
      long days = between.toDays();
      long hours = between.toHours();
      long minutes = between.toMinutes();
      long seconds = between.getSeconds();
      long millis = between.toMillis();
  
      System.out.println("两个时间相差多少天: " + days);
      System.out.println("两个时间相差多少小时: " + hours);
      System.out.println("两个时间相差多少分钟: " + minutes);
      System.out.println("两个时间相差多少秒: " + seconds);
      System.out.println("两个时间相差多少毫秒: " + millis);
  }
  ```

  ```java
  @Test
  public void test4() {
      Instant startTime = Instant.now();
  
      try {
          TimeUnit.SECONDS.sleep(3);
      } catch (InterruptedException e) {
          e.printStackTrace();
      }
  
      Instant endTime = Instant.now();
  
      Duration between = Duration.between(startTime, endTime);
      long diff = between.toMillis();
  
      System.out.println("运行时间为: " + diff);
  }
  ```

- 使用`java.time.temporal.ChronUnit`枚举类

  ```java
  @Test
  public void test2() {
      LocalDateTime now = LocalDateTime.now();
      LocalDateTime startTime = now.withYear(2023).withMonth(5).withDayOfMonth(25).withHour(5).withMinute(52).withSecond(26);
      LocalDateTime endTime = now.withYear(2023).withMonth(11).withDayOfMonth(16).withHour(8).withMinute(29).withSecond(38);
  
      long year = ChronoUnit.YEARS.between(startTime, endTime);
      long month = ChronoUnit.MONTHS.between(startTime, endTime);
      long day = ChronoUnit.DAYS.between(startTime, endTime);
      long hour = ChronoUnit.HOURS.between(startTime, endTime);
      long minute = ChronoUnit.MINUTES.between(startTime, endTime);
      long second = ChronoUnit.SECONDS.between(startTime, endTime);
  
      System.out.println("两个时间相差多少年: " + year);
      System.out.println("两个时间相差多少月: " + month);
      System.out.println("两个时间相差多少天: " + day);
      System.out.println("两个时间相差多少小时: " + hour);
      System.out.println("两个时间相差多少分钟: " + minute);
      System.out.println("两个时间相差多少秒: " + second);
  }
  ```

- 使用`java.time.Period`类

  ```java
  @Test
  public void test4() {
      LocalDate startDate = LocalDate.now().withYear(2023).withMonth(5).withDayOfMonth(28);
      LocalDate endDate = LocalDate.now().withYear(2025).withMonth(8).withDayOfMonth(31);
  
      Period between = Period.between(startDate, endDate);
  
      int years = between.getYears();
      int months = between.getMonths();
      int days = between.getDays();
  
      System.out.println("两个时间相差多少年: " + years);
      System.out.println("两个时间相差多少月: " + months);
      System.out.println("两个时间相差多少天: " + days);
  }
  ```

- 使用`java.util.concurrent.TimeUtil`

  ```java
  @Test
  public void test5() {
      LocalDateTime now = LocalDateTime.now();
      LocalDateTime startTime = now.withYear(2023).withMonth(5).withDayOfMonth(25).withHour(5).withMinute(52).withSecond(26);
      LocalDateTime endTime = now.withYear(2023).withMonth(11).withDayOfMonth(16).withHour(8).withMinute(29).withSecond(38);
  
      long startMilli = startTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
      long endMilli = endTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
      long result = endMilli - startMilli;
  
      long days = TimeUnit.MILLISECONDS.toDays(result);
      long hours = TimeUnit.MILLISECONDS.toHours(result);
      long minutes = TimeUnit.MILLISECONDS.toMinutes(result);
      long seconds = TimeUnit.MILLISECONDS.toSeconds(result);
  
      System.out.println("两个时间相差多少天: " + days);
      System.out.println("两个时间相差多少小时: " + hours);
      System.out.println("两个时间相差多少分钟: " + minutes);
      System.out.println("两个时间相差多少秒: " + seconds);
  }
  ```

### 日期格式化

```java
@Test
public void test1() {
    LocalDateTime localDateTime = LocalDateTime.now();

    String localDateTimeFormat = localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    System.out.println(localDateTimeFormat);
}

@Test
public void test2() {
    LocalDate localDate = LocalDate.now();

    String localDateFormat = localDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    System.out.println(localDateFormat);
}

@Test
public void test3() {
    LocalTime localTime = LocalTime.now();

    String localTimeFormat = localTime.format(DateTimeFormatter.ofPattern("HH:mm:ss"));
    System.out.println(localTimeFormat);

}
```

### 日期格式互转

- LocalDateTime转LocalDate

  ```java
  @Test
  public void test1() {
      LocalDateTime now = LocalDateTime.now();
  
      LocalDate localDate = now.toLocalDate();
      System.out.println(localDate);
  }
  ```

- LocalDateTime转LocalTime

  ```java
  @Test
  public void test2() {
      LocalDateTime now = LocalDateTime.now();
  
      LocalTime localTime = now.toLocalTime();
      System.out.println(localTime);
  }
  ```

- LocalDateTime转时间戳

  ```java
  @Test
  public void test3() {
      LocalDateTime now = LocalDateTime.now();
  
      long epochMilli = now.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
      System.out.println(epochMilli);
  }
  ```

- LocalDateTime转日期字符串

  ```java
  @Test
  public void test4() {
      LocalDateTime now = LocalDateTime.now();
  
      String format = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
      System.out.println(format);
  }
  ```

- LocalDateTime转Date

  ```java
  @Test
  public void test17() {
      LocalDateTime now = LocalDateTime.now();
  
      Date date = Date.from(now.atZone(ZoneId.systemDefault()).toInstant());
      System.out.println(date);
  }
  ```

- LocalDate转LocalDateTime

  ```java
  @Test
  public void test5() {
      LocalDate now = LocalDate.now();
  
      LocalDateTime localDateTime = now.atTime(LocalTime.now());
      System.out.println(localDateTime);
  }
  ```

- LocalDate转时间戳

  ```java
  @Test
  public void test6() {
      LocalDate now = LocalDate.now();
  
      long epochMilli = now.atStartOfDay(ZoneId.systemDefault()).toInstant().toEpochMilli();
      System.out.println(epochMilli);
  }
  ```

- LocalDate转日期字符串

  ```java
  @Test
  public void test7() {
      LocalDate now = LocalDate.now();
  
      String format = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
      System.out.println(format);
  }
  ```

- LocalDate转Date

  ```java
  @Test
  public void test18() {
      LocalDate now = LocalDate.now();
  
      Date date = Date.from(now.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
      System.out.println(date);
  }
  ```

- LocalTime转时间字符串

  ```java
  @Test
  public void test8() {
      LocalTime now = LocalTime.now();
  
      String format = now.format(DateTimeFormatter.ofPattern("HH:mm:ss"));
      System.out.println(format);
  }
  ```

- 时间戳转LocalDateTime

  ```java
  @Test
  public void test9() {
      long epochMilli = Instant.now().toEpochMilli();
      Instant instant = Instant.ofEpochMilli(epochMilli);
  
      LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
      System.out.println(localDateTime);
  }
  ```

- 时间戳转LocalDate

  ```java
  @Test
  public void test10() {
      long epochMilli = Instant.now().toEpochMilli();
      Instant instant = Instant.ofEpochMilli(epochMilli);
  
      LocalDate localDate = instant.atZone(ZoneId.systemDefault()).toLocalDate();
      System.out.println(localDate);
  }
  ```

- 时间戳转LocalTime

  ```java
  @Test
  public void test11() {
      long epochMilli = Instant.now().toEpochMilli();
      Instant instant = Instant.ofEpochMilli(epochMilli);
  
      LocalTime localTime = instant.atZone(ZoneId.systemDefault()).toLocalTime();
      System.out.println(localTime);
  }
  ```

- 时间戳转日期字符串

  ```java
  @Test
  public void test12() {
      long epochMilli = Instant.now().toEpochMilli();
      Instant instant = Instant.ofEpochMilli(epochMilli);
  
      String format = instant.atZone(ZoneId.systemDefault()).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
      System.out.println(format);
  }
  ```

- 时间戳转Date

  ```java
  @Test
  public void test19() {
      long epochMilli = Instant.now().toEpochMilli();
  
      Date date = Date.from(Instant.ofEpochMilli(epochMilli));
      System.out.println(date);
  }
  ```

- 日期时间字符串转LocalDateTime

  ```java
  @Test
  public void test13() {
      final String dateTimeStr = "2023-06-05 17:09:50";
  
      LocalDateTime localDateTime = LocalDateTime.parse(dateTimeStr, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
      System.out.println(localDateTime);
  }
  ```

- 日期字符串转LocalDate

  ```java
  @Test
  public void test14() {
      final String dateTimeStr = "2023-06-05";
  
      LocalDate localDate = LocalDate.parse(dateTimeStr, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
      System.out.println(localDate);
  }
  ```

- 时间字符串转LocalTime

  ```java
  @Test
  public void test15() {
      final String dateTimeStr = "17:09:50";
  
      LocalTime localTime = LocalTime.parse(dateTimeStr, DateTimeFormatter.ofPattern("HH:mm:ss"));
      System.out.println(localTime);
  }
  ```

- 日期时间字符串转时间戳

  ```java
  @Test
  public void test16() {
      final String dateTimeStr = "2023-06-05 17:09:50";
  
      long epochMilli = LocalDateTime.parse(dateTimeStr, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
              .atZone(ZoneId.systemDefault())
              .toInstant()
              .toEpochMilli();
  
      System.out.println(epochMilli);
  }
  ```

- 日期时间字符串转Date

  ```java
  @Test
  public void test20() {
      final String dateTimeStr = "2023-06-05 17:09:50";
  
      SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
      Date date = null;
      try {
          date = sdf.parse(dateTimeStr);
      } catch (ParseException e) {
          e.printStackTrace();
      }
      System.out.println(date);
  }
  ```

- Date转LocalDateTime

  ```java
  @Test
  public void test21() {
      Date date = new Date();
  
      LocalDateTime localDateTime = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
      System.out.println(localDateTime);
  }
  ```

- Date转LocalDate

  ```java
  @Test
  public void test22() {
      Date date = new Date();
  
      LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
      System.out.println(localDate);
  }
  ```

- Date转LocalTime

  ```java
  @Test
  public void test23() {
      Date date = new Date();
  
      LocalTime localTime = date.toInstant().atZone(ZoneId.systemDefault()).toLocalTime();
      System.out.println(localTime);
  }
  ```

- Date转日期字符串

  ```java
  @Test
  public void test24() {
      Date date = new Date();
  
      SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
      String format = sdf.format(date);
      System.out.println(format);
  }
  ```

- Date转时间戳

  ```java
  @Test
  public void test25() {
      Date date = new Date();
  
      long epochMilli = date.toInstant().toEpochMilli();
      System.out.println(epochMilli);
  }
  ```

## <span id="other">其他新特性</span>
