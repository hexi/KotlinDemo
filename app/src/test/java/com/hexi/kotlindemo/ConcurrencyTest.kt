package com.hexi.kotlindemo

import kotlinx.coroutines.*
import kotlinx.coroutines.channels.actor
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import org.junit.Test
import java.util.concurrent.atomic.AtomicInteger
import kotlin.system.measureTimeMillis

class ConcurrencyTest {
    @Test
    fun test_with_problem() = runBlocking {
        var counter = 0
        withContext(Dispatchers.Default) {
            massiveRun {
                counter++
            }
        }
        println("Counter = $counter")
    }

    private suspend fun massiveRun(action: suspend () -> Unit) {
        val n = 100 //启动的协程数量
        val k = 1000 // 每个协程重复执行同一动作的次数
        val time = measureTimeMillis {
            coroutineScope {
                repeat(n) {
                    launch {
                        repeat(k) { action() }
                    }
                }
            }
        }
        println("Completed ${n * k} actions in $time ms")
    }

    /**
     * 线程安全的数据结构
     */
    @Test
    fun test_with_atomic() = runBlocking {
        var counter = AtomicInteger()
        withContext(Dispatchers.Default) {
            massiveRun {
                counter.incrementAndGet()
            }
        }
        println("Counter = $counter")
    }

    /**
     * 以细粒度限制线程
     */
    @Test
    fun test_with_fine_grain_thread_limit() = runBlocking {
        var counter = 0
        val counterContext = newSingleThreadContext("CounterContext")
        withContext(Dispatchers.Default) {
            massiveRun {
                withContext(counterContext) {
                    counter++
                }
            }
        }
        println("Counter = $counter")
    }

    /**
     * 以粗粒度限制线程。
     * 在单线程上下文中运行每个协程。
     */
    @Test
    fun test_with_coarse_grain_thread_limit() = runBlocking {
        var counter = 0
        val counterContext = newSingleThreadContext("CounterContext")
        withContext(counterContext) {
            massiveRun {
                counter++
            }
        }
        println("Counter = $counter")
    }

    @Test
    fun test_with_mutex() = runBlocking {
        var counter = 0
        val mutex = Mutex()
        withContext(Dispatchers.Default) {
            massiveRun {
                mutex.withLock {
                    counter++
                }
            }
        }

        println("Counter = $counter")
    }

    @Test
    fun test_with_actor() = runBlocking<Unit> {
        val counter = counterActor()
        withContext(Dispatchers.Default) {
            massiveRun {
                counter.send(IncCounter)
            }
        }
        val response = CompletableDeferred<Int>()
        counter.send(GetCounter(response))
        println("Counter = ${response.await()}")
        counter.close()
    }

    private fun CoroutineScope.counterActor() = actor<CounterMsg> {
        var counter = 0
        for (msg in channel) {
            when (msg) {
                is IncCounter -> counter++
                is GetCounter -> msg.response.complete(counter)
            }
        }
    }
}

sealed class CounterMsg
object IncCounter : CounterMsg() // 递增计数器的单向消息
class GetCounter(val response: CompletableDeferred<Int>) : CounterMsg() // 携带回复的请求