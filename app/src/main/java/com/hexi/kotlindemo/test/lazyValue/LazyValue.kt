package com.hexi.kotlindemo.test.lazyValue

/**
 * Created by hexi on 2017/7/23.
 */

class Example {
    val lazyValue: String by lazy(LazyThreadSafetyMode.NONE) {
        println("computed!")
        "hello"
    }
}

fun main(args: Array<String>) {
    val example = Example()
    println(example.lazyValue)
    println(example.lazyValue)
    println(example.lazyValue)
}