package com.hexi.kotlindemo.test.reflection

/**
 * Created by hexi on 2017/9/27.
 */

fun main(args: Array<String>) {
    val cons: (String, Int) -> MyClass = ::MyClass
    println(cons("newMyClass", 12).toString())
}