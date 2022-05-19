package com.hexi.kotlindemo

import com.google.gson.Gson
import org.assertj.core.api.Assertions.assertThat
import org.joda.time.DateTime
import org.joda.time.Days
import org.junit.Test
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.ConcurrentMap
import java.util.concurrent.CountDownLatch

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see [Testing documentation](http://d.android.com/tools/testing)
 */
class ExampleUnitTest {
    val lockA = Object()

    @Test
    @Throws(Exception::class)
    fun addition_isCorrect() {
        val countDownLatch = CountDownLatch(4)
        Thread(Runnable {
            synchronized(lockA) {
                println("thread1 ...")
                lockA.wait()
                println("thread1 after notified")
                countDownLatch.countDown()
            }
        }).start()
        Thread(Runnable {
            synchronized(lockA) {
                println("thread3 ...")
                lockA.wait()
                println("thread3 after notified")
                countDownLatch.countDown()
            }
        }).start()
        Thread(Runnable {
            synchronized(lockA) {
                println("thread4 ...")
                lockA.wait()
                println("thread4 after notified")
                countDownLatch.countDown()
            }
        }).start()

        Thread(Runnable {
            synchronized(lockA) {
                println("thread2 ...")
                lockA.wait(50)
                lockA.notifyAll()
                println("thread2 notify all thread")
                countDownLatch.countDown()
            }
        }).start()

        countDownLatch.await()
    }

    @Test
    fun test_days_between() {
        val last = DateTime.now()
                .plusHours(-5)
                .withTimeAtStartOfDay()

        val now = DateTime.now()
                .withTimeAtStartOfDay()
        println("${Days.daysBetween(last, now).days}, $now, $last, ${last.isEqual(now)}")
    }

    @Test
    fun `test remove item in iterator of hashmap`() {
        val map = ConcurrentHashMap<String, Int>()
        map["a"] = 1
        map["b"] = 2
        map["c"] = 3
        map["d"] = 4
        val it = map.entries.iterator()
        while (it.hasNext()) {
            val entry = it.next()
            println("===key: ${entry.key}, value: ${entry.value}")
            if (entry.key == "b") {
                it.remove()
            }
        }
        println("===map: ${Gson().toJson(map)}")
        assertThat(map.count()).isEqualTo(3)
    }
}