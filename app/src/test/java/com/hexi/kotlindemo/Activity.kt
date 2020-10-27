package com.hexi.kotlindemo

import kotlinx.coroutines.*

class Activity : CoroutineScope by MainScope() {
    fun doSomething() {
        // 在示例中启动了 10 个协程，且每个都工作了不同的时长
        repeat(10) { i ->
            launch {
                log("Coroutine $i is start...")
                delay((i + 1) * 200L) // 延迟 200 毫秒、400 毫秒、600 毫秒等等不同的时间
                log("Coroutine $i is done")
            }
        }
    }

    fun destroy() {
        cancel() // Extension on CoroutineScope
    }

    private fun log(msg: String) = println("[${Thread.currentThread().name}] $msg")
}