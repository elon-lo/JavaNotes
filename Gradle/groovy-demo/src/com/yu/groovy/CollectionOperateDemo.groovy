package com.yu.groovy

/*--------------------集合操作--------------------*/

//1.集合中添加元素
def list = ["a", "c", "r", "g"]
assert list instanceof List
list.add("x")
println list.size()

//2.集合相加
def list2 = ["w", "e", "t", "f"]
println list.plus(list2) //将list2添加到list之后

//3.删除指定下标元素
list.remove(3)
println(list)

//4.删除集合中指定的元素
list.removeElement("a")
println(list)

//5.从list集合中移除包含list3集合中的元素
def list3 = ["c", "r", "f"]
list.removeAll(list3)
println(list)

//6.从list集合中弹出最后一个元素
println list.pop()
println(list)

//7.指定下标元素赋值
list.putAt(3, "y")
println(list)

//8.遍历list集合
[1, 3, 5, 7].each {
    println "Item: ${it}"
}

//9.list集合中是否包含某个值
println list.contains("y")

//10.map集合初始化,键可以不使用引号,可以使用双引号,也可以使用单引号
def map = [J: "Java", "K": "Kotlin", 'G': "Groovy"]

//11.map集合中添加元素
map.put("A", "Android")
println map

//12.map集合根据键移除或键值对移除
map.remove("K")
map.remove('G', "Groovy")
println map

//13.map集合的"-" "+"操作符重载
map2 = map - ["A": "Android"]
println map2

map3 = map + ["J": "JavaScript"]
println map3

//14.map集合的遍历
map.each { key, value ->
    println "key:$key value:$value"
}

