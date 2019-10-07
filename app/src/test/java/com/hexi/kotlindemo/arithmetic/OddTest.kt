package com.hexi.kotlindemo.arithmetic

import com.hexi.kotlindemo.test.arithmetic.Arithmetic
import org.junit.Assert
import org.junit.Test

class OddTest {
    @Test
    fun is_odd_1() {
        val number = 1
        Assert.assertTrue(Arithmetic.isOdd(number))
    }

    @Test
    fun is_odd_2() {
        val number = 2
        Assert.assertFalse(Arithmetic.isOdd(number))
    }

    @Test
    fun is_odd_3() {
        val number = 3
        Assert.assertTrue(Arithmetic.isOdd(number))
    }

    @Test
    fun is_odd_5() {
        val number = 5
        Assert.assertTrue(Arithmetic.isOdd(number))
    }

    @Test
    fun is_odd_11() {
        val number = 11
        Assert.assertTrue(Arithmetic.isOdd(number))
    }

    @Test
    fun is_odd_101() {
        val number = 101
        Assert.assertTrue(Arithmetic.isOdd(number))
    }

    @Test
    fun is_odd_103() {
        val number = 103
        Assert.assertTrue(Arithmetic.isOdd(number))
    }
}
