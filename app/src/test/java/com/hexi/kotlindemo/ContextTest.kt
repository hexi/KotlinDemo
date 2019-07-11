package com.hexi.kotlindemo

import kotlinx.coroutines.*
import org.junit.Test

class ContextTest {
    @Test
    fun test0() = runBlocking<Unit> {
        launch { // 运行在父协程的上下文中，即 runBlocking 主协程
            println("main runBlocking      : I'm working in thread ${Thread.currentThread().name}")
        }
        launch(Dispatchers.Unconfined) { // 不受限的——将工作在主线程中
            println("Unconfined            : I'm working in thread ${Thread.currentThread().name}")
        }
        launch(Dispatchers.Default) { // 将会获取默认调度器
            println("Default               : I'm working in thread ${Thread.currentThread().name}")
        }
        launch(newSingleThreadContext("MyOwnThread")) { // 将使它获得一个新的线程
            println("newSingleThreadContext: I'm working in thread ${Thread.currentThread().name}")
        }
    }

    @Test
    fun test1() = runBlocking<Unit> {
        launch(Dispatchers.Unconfined) { // 非受限的——将和主线程一起工作
            println("Unconfined      : I'm working in thread ${Thread.currentThread().name}, time: ${System.currentTimeMillis()}")
            delay(500)
            println("Unconfined      : After delay in thread ${Thread.currentThread().name}, time: ${System.currentTimeMillis()}")
        }
        launch { // 父协程的上下文，主 runBlocking 协程
            println("main runBlocking: I'm working in thread ${Thread.currentThread().name}, time: ${System.currentTimeMillis()}")
            delay(1000)
            println("main runBlocking: After delay in thread ${Thread.currentThread().name}, time: ${System.currentTimeMillis()}")
        }
    }

    @Test
    fun test2() = runBlocking<Unit> {
        val a = async<Int> {
            log("I'm computing a piece of the answer")
            6
        }
        val b = async<Int> {
            log("I'm computing another piece of the answer")
            7
        }
        log("The answer is ${a.await() * b.await()}")
    }

    private fun log(msg: String) = println("[${Thread.currentThread().name}] $msg")

    @Test
    fun test3() {
        newSingleThreadContext("Ctx1").use { ctx1 ->
            newSingleThreadContext("Ctx2").use { ctx2 ->
                runBlocking(ctx1) {
                    log("Started in ctx1")
                    withContext(ctx2) {
                        log("Working in ctx2")
                    }
                    log("Back to ctx1")
                }
            }
        }
    }

    @Test
    fun test4() = runBlocking {
        println("My job is ${coroutineContext[Job]}")
    }

    /**
     * 子协程
     */
    @Test
    fun test5() = runBlocking {
        // 启动一个协程来处理某种传入请求（request）
        val request = launch {
            // 孵化了两个子作业, 其中一个通过 GlobalScope 启动
            GlobalScope.launch {
                println("job1: I run in GlobalScope and execute independently!")
                delay(1000)
                println("job1: I am not affected by cancellation of the request")
            }
            // 另一个则承袭了父协程的上下文
            launch {
                delay(100)
                println("job2: I am a child of the request coroutine")
                delay(1000)
                println("job2: I will not execute this line if my parent request is cancelled")
            }
        }

        delay(500)
        request.cancel() // 取消请求（request）的执行
        delay(1000) // 延迟一秒钟来看看发生了什么
        println("main: Who has survived request cancellation?")
    }

    /**
     * 父协程的职责
     */
    @Test
    fun test6() = runBlocking {
        val request = launch {
            repeat(3) { i -> // 启动少量自作业
                launch {
                    delay((i + 1) * 200L) // 延迟 200 毫秒、400 毫秒、600 毫秒的时间
                    println("Coroutine $i is done")
                }
            }
            println("request: I'm done and I don't explicitly join my children that are still active")
        }
        request.join() // 等待请求的完成，包括其所有子协程
        println("Now processing of the request is complete")
    }

    /**
     * 命名协程以用于调试
     */
    @Test
    fun test7() = runBlocking {
        log("Start main coroutine")
        val v1 = async(CoroutineName("v1coroutine")) {
            delay(500)
            log("Computing v1")
            252
        }
        val v2 = async(CoroutineName("v2coroutine")) {
            delay(1000)
            log("Computing v2")
            6
        }
        log("The answer for v1 / v2 = ${v1.await() / v2.await()}")
    }

    @Test
    fun test8() = runBlocking<Unit> {
        launch(Dispatchers.Default + CoroutineName("test")) {
            println("I'm working in thread ${Thread.currentThread().name}")
        }
    }

    /**
     * 协程作用域
     */
    @Test
    fun test9() = runBlocking {
        val activity = Activity()
        activity.doSomething() // 运行测试函数
        println("Launched coroutines")
        delay(500L) // 延迟半秒钟
        println("Destroying activity!")
        activity.destroy() // 取消所有的协程
        delay(2000) // 为了在视觉上确认它们没有工作
    }

    val threadLocal = ThreadLocal<String?>()
    @Test
    fun test10() = runBlocking {
        threadLocal.set("main")
        println("Pre-main, current thread: ${Thread.currentThread()}, thread local value: '${threadLocal.get()}'")
        val job = launch(Dispatchers.Default + threadLocal.asContextElement(value = "launch")) {
            println("Launch start, current thread: ${Thread.currentThread()}, thread local value: '${threadLocal.get()}'")
            yield()
            println("After yield, current thread: ${Thread.currentThread()}, thread local value: '${threadLocal.get()}'")
        }
        job.join()
        println("Post-main, current thread: ${Thread.currentThread()}, thread local value: '${threadLocal.get()}'")
    }
}