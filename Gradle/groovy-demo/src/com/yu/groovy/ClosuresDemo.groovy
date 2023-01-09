package com.yu.groovy

def running(Closure closure) {
    println("running start......")
    closure()
    println("running end......")
}

running {{println("running......")}}


//def caculate(num1, num2, Closure closure) {
//    closure(num1, num2)
//}

def caculate(Closure closure) {
    def num1 = 2
    def num2 = 5
    closure(num1, num2)
}

//caculate(2, 5, {k, v -> println("$k + $v = ${k + v}")})
//caculate(){k, v -> println("$k + $v = ${k + v}")}
caculate{k, v -> println("$k + $v = ${k + v}")}