package com.hexi.kotlindemo

import kotlinx.coroutines.*
import kotlinx.coroutines.channels.*
import org.junit.Test

class ChannelTest {
    @Test
    fun test_channel() = runBlocking {
        val channel = Channel<Int>()
        launch {
            // 这里可能是消耗大量 CPU 运算的异步逻辑，我们将仅仅做 5 次整数的平方并发送
            for (x in 1..5) channel.send(x * x)
        }

        // 这里我们打印了 5 次被接收的整数：
        repeat(5) { println(channel.receive())}
        println("Done!")
    }

    /**
     * 关闭与迭代通道
     */
    @Test
    fun test_close_channel() = runBlocking {
        val channel = Channel<Int>()
        launch {
            for (x in 1..5) channel.send(x * x)
            channel.close()
        }
        // 这里我们使用 `for` 循环来打印所有被接收到的元素（直到通道被关闭）
        for (y in channel) println(y)
        println("Done!")
    }

    /**
     * 管道
     */
    @Test
    fun test_pipeline() = runBlocking {
        val numbers = produceNumbers()
        val square = square(numbers)
        for (i in 1..5) println(square.receive())
        println("Done")
        coroutineContext.cancelChildren()
    }

    fun CoroutineScope.produceNumbers() = produce {
        var x = 1
        while (true) send(x++)
    }

    fun CoroutineScope.square(numbers: ReceiveChannel<Int>):ReceiveChannel<Int> = produce {
        for (x in numbers) send(x * x)
    }

    /**
     * 使用管道的素数
     */
    @Test
    fun test_prime() = runBlocking {
        var cur = numbersFrom(2)
        for (i in 1..20) {
            val prime = cur.receive()
            println(prime)
            cur = filter(cur, prime)
        }
        coroutineContext.cancelChildren()
    }

    fun CoroutineScope.numbersFrom(start: Int) = produce {
        var x = start
        while (true) send(x++)
    }

    fun CoroutineScope.filter(numbers: ReceiveChannel<Int>, prime: Int) = produce {
        for (i in numbers) if (i % prime != 0) send(i)
    }

    /**
     * 扇出
     */
    @Test
    fun test_fan_out() = runBlocking {
        val producer = oneSecProduceTenNumbers()
        repeat(5) { launchProcessor(it, producer) }
        delay(950)
        producer.cancel()
    }

    fun CoroutineScope.oneSecProduceTenNumbers() = produce {
        var x = 1
        while (true) {
            send(x++)
            delay(100)
        }
    }

    fun CoroutineScope.launchProcessor(id: Int, channel: ReceiveChannel<Int>) = launch {
        for (msg in channel) {
            println("Processor #$id received $msg")
        }
    }

    /**
     * 扇入
     */
    @Test
    fun test_fan_in() = runBlocking {
        val channel = Channel<String>()
        launch { sendString(channel, "foo", 200L) }
        launch { sendString(channel, "BAR!", 500L) }
        repeat(6) {
            println(channel.receive())
        }
        coroutineContext.cancelChildren()
    }

    private suspend fun sendString(channel: SendChannel<String>, s: String, time: Long) {
        while (true) {
            delay(time)
            channel.send(s)
        }
    }

    /**
     * 带缓冲的通道
     */
    @Test
    fun test_buffer_channel() = runBlocking {
        val channel = Channel<Int>(4)
        val sender = launch {
            repeat(10) {
                println("Sending $it")
                channel.send(it)
            }
        }
        delay(1000)
        sender.cancel()
    }

    /**
     * 通道是公平的
     */
    @Test
    fun test_fair_channel() = runBlocking {
        val table = Channel<Ball>()
        launch { player("ping", table) }
        launch { player("pong", table) }
        table.send(Ball(0))
        delay(1000)
        coroutineContext.cancelChildren()
    }

    private suspend fun player(name: String, table: Channel<Ball>) {
        for (ball in table) {
            ball.hits++
            println("$name, $ball, time: ${System.currentTimeMillis()}")
            delay(300)
            println("$name, send ball, time: ${System.currentTimeMillis()}")
            table.send(ball)
        }
    }

    /**
     * 计时器通道
     */
    @Test
    fun test_ticker_channel() = runBlocking {
        val tickerChannel = ticker(delayMillis = 100, initialDelayMillis = 0, mode = TickerMode.FIXED_PERIOD) //创建计时器通道
        var nextElement = withTimeoutOrNull(1) { tickerChannel.receive() }
        println("Initial element is available immediately: $nextElement") // 初始尚未经过的延迟
        nextElement = withTimeoutOrNull(50) { tickerChannel.receive() }
        println("Next element is not ready in 50 ms: $nextElement")

        nextElement = withTimeoutOrNull(60) { tickerChannel.receive() }
        println("Next element is ready in 100 ms: $nextElement")

        // 模拟大量消费延迟
        println("Consumer pauses for 150ms")
        delay(150)
        // 下一个元素立即可用
        nextElement = withTimeoutOrNull(1) { tickerChannel.receive() }
        println("Next element is available immediately after large consumer delay: $nextElement")
        // 请注意，`receive` 调用之间的暂停被考虑在内，下一个元素的到达速度更快
        nextElement = withTimeoutOrNull(60) { tickerChannel.receive() }
        println("Next element is ready in 50ms after consumer pause in 150ms: $nextElement")
        tickerChannel.cancel() // 表明不需要更多的元素
    }
}

data class Ball(var hits: Int)