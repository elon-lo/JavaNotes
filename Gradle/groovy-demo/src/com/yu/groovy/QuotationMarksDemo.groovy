package com.yu.groovy

/*--------------------字符串操作--------------------*/
def desc = "测试"

def str1 = '单引号,不支持变量引用,不支持换行操作 ${desc}'

def str2 = "双引号,支持变量引用,不支持换行操作 ${desc}"

def str3 = '''三引号,模板字符串,不支持变量引用,
              支持换行操作 ${desc}'''

println(str1)
println(str2)
println(str3)

println()

println(str1.getClass().toString())
println(str2.getClass().toString())
println(str3.getClass().toString())