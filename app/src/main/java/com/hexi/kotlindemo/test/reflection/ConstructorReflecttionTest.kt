package com.hexi.kotlindemo.test.reflection

/**
 * Created by hexi on 2017/9/27.
 */

fun main() {
    val cons: (String, Int) -> MyClass = ::MyClass
    println(cons("newMyClass", 12).toString())
}