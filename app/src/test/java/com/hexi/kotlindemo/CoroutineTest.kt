package com.hexi.kotlindemo

import kotlinx.coroutines.*
import org.junit.Test

class CoroutineTest {

    @Test
    fun test_start_lots_of_coroutines() = runBlocking {
        val mp = Runtime.getRuntime().availableProcessors()
        println("逻辑cpu数量: $mp")
        val threadId = Thread.currentThread().id
        println("主协程所属线程id = $threadId")
        for (i in 1..10) {
            async { name(i) }
        }

        delay(100 * 1000)
    }

    private suspend fun name(i: Int) = withContext(Dispatchers.IO) {
        repeat(10) {
            delay(1000)
            val threadId = Thread.currentThread().id
            val s = "iqoo$i belong thread $threadId"
            println(s)
        }
    }

    @Test
    fun sample_coroutine() = runBlocking {
        val job = GlobalScope.launch {
            delay(1000L)
            println("World!")
        }
        println("Hello,")
        job.join()
    }

    @Test
    fun test_launch() = runBlocking {
        launch {
            delay(2000L)
            println("World!")
        }

        println("Hello,")
    }

    @Test
    fun test_coroutine_scope() = runBlocking {
        launch {
            delay(200L)
            println("Task from runBlocking")
        }
        coroutineScope {
            // 创建一个协程作用域
            launch {
                delay(500L)
                println("Task from nested launch")
            }

            delay(100L)
            println("Task from coroutine scope") // 这一行会在内嵌 launch 之前输出
        }
        println("Coroutine scope is over") // 这一行在内嵌 launch 执行完毕后才输出
    }

    @Test
    fun test_cancel() = runBlocking {
        val job = launch {
            repeat(1000) { i ->
                println("job: I'm sleeping $i ...")
                delay(500L)
            }
        }
        delay(1300L) // 延迟一段时间
        println("main: I'm tired of waiting!")
        job.cancel() // 取消该任务
        job.join() // 等待任务执行结束
        println("main: Now I can quit.")
    }

    @Test
    fun test_cancel_synergic() = runBlocking {
        val startTime = System.currentTimeMillis()
        val job = launch(Dispatchers.Default) {
            var nextPrintTime = startTime
            var i = 0
            while (i < 5) {
                // 每秒打印两次信息
                if (System.currentTimeMillis() >= nextPrintTime) {
                    println("job: I'm sleeping ${i++} ...")
                    nextPrintTime += 500L
                }
            }
        }
        delay(1300L) // 等待一段时间
        println("main: I'm tired of waiting!")
        job.cancelAndJoin() // 取消一个任务并等待它结束
        println("main: Now i can quit.")

    }

    @Test
    fun test_cancel_calculation() = runBlocking {
        val startTime = System.currentTimeMillis()
        val job = launch(Dispatchers.Default) {
            var nextPrintTime = startTime
            var i = 0
            while (isActive) {
                if (System.currentTimeMillis() >= nextPrintTime) {
                    println("job: I'm sleeping ${i++} ...")
                    nextPrintTime += 500L
                }
            }
        }
        delay(1300L)
        println("main: I'm tired waiting!")
        job.cancelAndJoin()
        println("main: Now i can quit.")
    }

    @Test
    fun test_release_res_in_finally() = runBlocking {
        val job = launch {
            try {
                repeat(1000) { i ->
                    println("job: I'm sleeping $i ...")
                    delay(500L)
                }
            }  finally {
                println("job: I'm running finally.")
            }
        }
        delay(1300L) // 延迟一段时间
        println("main: I'm tired waiting!")
        job.cancelAndJoin() // 等待了所有的终结动作执行完毕，才会执行下面的语句
        println("main: Now i can quit.")
    }

    @Test
    fun test_run_non_cancellable() = runBlocking {
        val job = launch {
            try {
                repeat(1000) { i ->
                    println("job: I'm sleeping $i ...")
                    delay(500L)
                }
            } finally {
                withContext(NonCancellable) {
                    println("job: I'm running finally.")
                    delay(5000L)
                    println("job: And I've just delayed for 5 secs because I'm non-cancellable")
                }
            }
        }
        delay(1300L)
        println("main: I'm tired waiting!")
        job.cancelAndJoin()
        println("main: Now i can quit.")
    }

    @Test
    fun test_timeout() = runBlocking {
        withTimeout(1300) {
            repeat(1000) { i ->
                println("I'm sleeping $i ...")
                delay(500L)
            }
        }
        println("main: Now i can quit.")
    }

    @Test
    fun test_catch_timeout() = runBlocking {
        try {
            withTimeout(1300) {
                repeat(1000) { i ->
                    println("I'm sleeping $i ...")
                    delay(500L)
                }
            }
        } catch (e: TimeoutCancellationException) {
            println("catch timeout cancellation exception: ${e.message}")
        }

        println("main: I waiting...")
    }

    @Test
    fun test_timeout_or_null() = runBlocking {
        val result = withTimeoutOrNull(1300) {
            repeat(1000) { i ->
                println("I'm sleeping $i ...")
                delay(500L)
            }
            "Done"
        }
        println("Result is $result")
    }

}