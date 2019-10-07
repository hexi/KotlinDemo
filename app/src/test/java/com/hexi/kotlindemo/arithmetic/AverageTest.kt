package com.hexi.kotlindemo.arithmetic

import com.hexi.kotlindemo.test.arithmetic.Arithmetic
import org.junit.Assert
import org.junit.Test

class AverageTest {
    @Test
    fun test_average0() {
        val a = 1
        val b = 3
        Assert.assertEquals(Arithmetic.getAverage(a, b), 2)
    }

    @Test
    fun test_average1() {
        val a = 13
        val b = 15
        Assert.assertEquals(Arithmetic.getAverage(a, b), 14)
    }

    @Test
    fun test_average3() {
        val a = 13
        val b = 10
        Assert.assertEquals(Arithmetic.getAverage(a, b), 11)
    }

    @Test
    fun test_average4() {
        val a = 0
        val b = 1
        Assert.assertEquals(Arithmetic.getAverage(a, b), 0)
    }
}