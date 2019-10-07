package com.hexi.kotlindemo.arithmetic

import com.hexi.kotlindemo.test.arithmetic.Arithmetic
import org.junit.Assert
import org.junit.Test

class IsLog2Test {

    @Test
    fun test_isLog2_1() {
        val a = 1
        Assert.assertTrue(Arithmetic.isLog2(a))
    }

    @Test
    fun test_isLog2_2() {
        val a = 2
        Assert.assertTrue(Arithmetic.isLog2(a))
    }

    @Test
    fun test_isLog2_3() {
        val a = 3
        Assert.assertFalse(Arithmetic.isLog2(a))
    }

    @Test
    fun test_isLog2_4() {
        val a = 4
        Assert.assertTrue(Arithmetic.isLog2(a))
    }

    @Test
    fun test_isLog2_5() {
        val a = 5
        Assert.assertFalse(Arithmetic.isLog2(a))
    }

    @Test
    fun test_isLog2_6() {
        val a = 6
        Assert.assertFalse(Arithmetic.isLog2(a))
    }

    @Test
    fun test_isLog2_7() {
        val a = 7
        Assert.assertFalse(Arithmetic.isLog2(a))
    }

    @Test
    fun test_isLog2_8() {
        val a = 8
        Assert.assertTrue(Arithmetic.isLog2(a))
    }

    @Test
    fun test_isLog2_9() {
        val a = 9
        Assert.assertFalse(Arithmetic.isLog2(a))
    }

    @Test
    fun test_isLog2_10() {
        val a = 9
        Assert.assertFalse(Arithmetic.isLog2(a))
    }

    @Test
    fun test_isLog2_12() {
        val a = 12
        Assert.assertFalse(Arithmetic.isLog2(a))
    }

    @Test
    fun test_isLog2_16() {
        val a = 16
        Assert.assertTrue(Arithmetic.isLog2(a))
    }
}