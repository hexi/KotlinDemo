package com.hexi.kotlindemo.test.coroutines

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    launch {
        delay(200L)
        println("Task from runBlocking")
    }

    println("Coroutine scope is over") // 该行将在嵌套结束之后才会被打印
}