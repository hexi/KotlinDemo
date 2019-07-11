package com.hexi.kotlindemo

import kotlinx.coroutines.*
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test
import kotlin.system.measureTimeMillis

class CombinationSuspendTest {

    private val mainThreadSurrogate = newSingleThreadContext("UI thread")

    @Before
    fun setUp() {
        Dispatchers.setMain(mainThreadSurrogate)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain() // reset main dispatcher to the original Main dispatcher
        mainThreadSurrogate.close()
    }

    /**
     * 默认顺序调用
     */
    @Test
    fun test_orderly_invoke() = runBlocking {
        val time = measureTimeMillis {
            val one = doSomethingUsefulOne()
            val two = doSomethingUsefulTwo()
            println("The answer is ${one + two}")
        }
        println("Completed in $time ms")
    }

    private suspend fun doSomethingUsefulOne(): Int {
        delay(1000L)
        return 13
    }

    private suspend fun doSomethingUsefulTwo(): Int {
        delay(1000L)
        return 29
    }

    /**
     * 使用 async 并发
     */
    @Test
    fun test_async() = runBlocking {
        val time = measureTimeMillis {
            val one = async { doSomethingUsefulOne() }
            val two = async { doSomethingUsefulTwo() }
            println("The answer is ${one.await() + two.await()}")
        }
        println("Completed in $time ms")
    }

    /**
     * 惰性启动的 async
     */
    @Test
    fun test_lazy_async() = runBlocking {
        val time = measureTimeMillis {
            val one = async(start = CoroutineStart.LAZY) { doSomethingUsefulOne() }
            val two = async(start = CoroutineStart.LAZY) { doSomethingUsefulTwo() }
            one.start()
            two.start()
            println("The answer is ${one.await()} + ${two.await()}")
        }
        println("Completed in $time ms")
    }

    /**
     * async 风格的函数
     */
    @Test
    fun test_async_style_concurrency() {
        val time = measureTimeMillis {
            // 我们可以在协程外面启动异步执行
            val one = doSomethingUsefulOneAsync()
            val two = doSomethingUsefulTwoAsync()
            // 但是等待结果必须调用其它的挂起或者阻塞
            // 当我们等待结果的时候，这里我们使用 `runBlocking { …… }` 来阻塞主线程
            runBlocking {
                println("The answer is ${one.await() + two.await()}")
            }
        }
        println("Completed in $time ms")
    }

    private fun doSomethingUsefulOneAsync() = GlobalScope.async {
        doSomethingUsefulOne()
    }

    private fun doSomethingUsefulTwoAsync() = GlobalScope.async {
        doSomethingUsefulTwo()
    }

    /**
     * 使用 async 的结构化并发
     */
    @Test
    fun test_structured_concurrency() = runBlocking {
        val time = measureTimeMillis {
            println("The answer is ${concurrentSum()}")
        }
        println("Completed in $time ms")
    }

    private suspend fun concurrentSum(): Int = coroutineScope {
        val one = async { doSomethingUsefulOne() }
        val two = async { doSomethingUsefulTwo() }
        one.await() + two.await()
    }

    @Test
    fun test_failed_structured_concurrency() = runBlocking<Unit> {
        try {
            failedConcurrentSum()
        } catch (e: ArithmeticException) {
            println("Computation failed with ArithmeticException")
        }
    }

    private suspend fun failedConcurrentSum(): Int = coroutineScope {
        val one = async {
            try {
                delay(Long.MAX_VALUE)
                42
            } finally {
                println("first child was cancelled")
            }
        }
        val two = async<Int> {
            println("Second was throws an exception")
            throw ArithmeticException()
        }

        one.await() + two.await()
    }

    /**
     * 结构化并发失败，协程将丢失错误
     */
    @Test
    fun test_failed_structured_concurrency1() = runBlocking<Unit> {
        try {
            lostError()
        } catch (e: ArithmeticException) {
            println("Computation failed with ArithmeticException")
        }
    }

    val unrelatedScope = MainScope()
    private suspend fun lostError() {
        // async without structured concurrency
        unrelatedScope.async {
            throw ArithmeticException("except")
        }
    }

    @Test
    fun test10() = runBlocking<Unit> {
        CoroutineScope(Dispatchers.Main).launch {
            aaa()
        }
    }

    private fun log(msg: String) = println("[${Thread.currentThread().name}] $msg")
    suspend private fun aaa() {
        log("===aaa")
    }

}