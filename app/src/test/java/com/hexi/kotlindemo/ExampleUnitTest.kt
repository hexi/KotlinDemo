package com.hexi.kotlindemo

import org.joda.time.DateTime
import org.joda.time.Days
import org.junit.Test
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
}