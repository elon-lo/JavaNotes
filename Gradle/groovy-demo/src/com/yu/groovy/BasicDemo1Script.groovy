package com.yu.groovy

def obj = new BasicDemo();
//def result = obj.sale(100)
//print(result)
//obj.bookName = "吞噬天空"
//obj["bookName"] = "吞噬天空"
obj.setBookName("吞噬天空")
//def obj = new BasiceDemo(bookName: "吞噬天空");
//print(obj.bookName)
//print(obj["bookName"])
print(obj.getBookName())