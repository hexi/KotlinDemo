package com.hexi.kotlindemo

import kotlinx.coroutines.*
import org.junit.Test

class SupervisorTest {
    /**
     * 监督作业
     */
    @Test
    fun test_supervisor_job() = runBlocking {
        val supervisor = SupervisorJob()
        with(CoroutineScope(coroutineContext + supervisor)) {
            val firstChild = launch(CoroutineExceptionHandler {_, _ -> }) {
                println("First child is failing")
                throw AssertionError("First child is canceled")
            }
            val secondChild = launch {
                firstChild.join()
                // 取消了第一个子作业且没有传播给第二个子作业
                println("First child is cancelled: ${firstChild.isCancelled}, but second one is still active")
                try {
                    delay(Long.MAX_VALUE)
                } finally {
                    // 但是取消了监督的传播
                    println("Second child is cancelled because supervisor is cancelled")
                }
            }
            // 等待直到第一个子作业失败且执行完成
            firstChild.join()
            println("Canceling supervisor")
            supervisor.cancel()
            secondChild.join()
        }
    }

    /**
     * 监督作用域
     */
    @Test
    fun test_supervisor_scope() = runBlocking {
        try {
            supervisorScope {
                val child = launch {
                    try {
                        println("Child is sleeping")
                        delay(Long.MAX_VALUE)
                    } finally {
                        println("Child is canceled")
                    }
                }
                // 使用yield 来个我们的子作业一个机会来执行打印
                yield()
                println("Throwing exception from scope")
                throw AssertionError()
            }
        } catch (e: AssertionError) {
            println("Caught assertion error")
        }
    }

    /**
     * 常规的作业和监督作业之间的另一个重要区别是异常处理。
     * 每一个子作业应该通过异常处理机制处理自身的异常。
     * 这种差异来自于子作业的执行失败不会传播给它的父作业的事实
     */
    @Test
    fun test_supervisor_exception() = runBlocking {
        val handler = CoroutineExceptionHandler { _, exception ->
            println("Caught $exception")
        }
        supervisorScope {
            try {
                val child = launch(handler) {
                    println("Child throws an exception")
                    throw AssertionError()
                }
                child.join()
                delay(Long.MAX_VALUE)
            } finally {
                println("Scope is completing")
            }
        }
        println("Scope is completed")
    }

    @Test
    fun test_normal_exception() = runBlocking {
        val handler = CoroutineExceptionHandler { _, exception ->
            println("Caught $exception")
        }
        val job = GlobalScope.launch(handler) {
            val child = launch {
                println("Child throws an exception")
                throw AssertionError()
            }
            try {
                child.join()
                delay(Long.MAX_VALUE)
            } catch (e: CancellationException) {
                println("Canceled by child's exception")
            } finally {
                println("Scope is completing")
            }
        }
        job.join()
        println("Scope is completed")
    }
}