package com.hexi.kotlindemo.arithmetic

import com.hexi.kotlindemo.test.arithmetic.Arithmetic
import org.junit.Assert
import org.junit.Test

class EvenTest {
    @Test
    fun is_even_2() {
        val number = 2
        Assert.assertTrue(Arithmetic.isEven(number))
    }

    @Test
    fun is_even_4() {
        val number = 4
        Assert.assertTrue(Arithmetic.isEven(number))
    }

    @Test
    fun is_odd_3() {
        val number = 3
        Assert.assertFalse(Arithmetic.isEven(number))
    }

    @Test
    fun is_even_6() {
        val number = 6
        Assert.assertTrue(Arithmetic.isEven(number))
    }

    @Test
    fun is_even_8() {
        val number = 8
        Assert.assertTrue(Arithmetic.isEven(number))
    }

    @Test
    fun is_even_10() {
        val number = 10
        Assert.assertTrue(Arithmetic.isEven(number))
    }

    @Test
    fun is_even_100() {
        val number = 100
        Assert.assertTrue(Arithmetic.isEven(number))
    }

    @Test
    fun is_even_44() {
        val number = 44
        Assert.assertTrue(Arithmetic.isEven(number))
    }

    @Test
    fun is_even_1000() {
        val number = 1000
        Assert.assertTrue(Arithmetic.isEven(number))
    }
}