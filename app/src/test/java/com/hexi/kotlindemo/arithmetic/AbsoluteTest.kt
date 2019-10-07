package com.hexi.kotlindemo.arithmetic

import com.hexi.kotlindemo.test.arithmetic.Arithmetic
import org.junit.Assert
import org.junit.Test

class AbsoluteTest {
    @Test
    fun test0() {
        val a = 0
        Assert.assertEquals(Arithmetic.myAbs(a), 0)
    }

    @Test
    fun test1() {
        val a = -1
        Assert.assertEquals(Arithmetic.myAbs(a), 1)
    }

    @Test
    fun test2() {
        val a = -2
        Assert.assertEquals(Arithmetic.myAbs(a), 2)
    }

    @Test
    fun test3() {
        val a = -10
        Assert.assertEquals(Arithmetic.myAbs(a), 10)
    }

    @Test
    fun test4() {
        val a = -11
        Assert.assertEquals(Arithmetic.myAbs(a), 11)
    }

    @Test
    fun test5() {
        val a = 11
        Assert.assertEquals(Arithmetic.myAbs(a), 11)
    }

    @Test
    fun test6() {
        val a = -22
        Assert.assertEquals(Arithmetic.myAbs(a), 22)
    }

    @Test
    fun test7() {
        val a = 22
        Assert.assertEquals(Arithmetic.myAbs(a), 22)
    }

    @Test
    fun test8() {
        val a = -100
        Assert.assertEquals(Arithmetic.myAbs(a), 100)
    }

    @Test
    fun test9() {
        val a = 100
        Assert.assertEquals(Arithmetic.myAbs(a), 100)
    }

    @Test
    fun test10() {
        val a = -101
        Assert.assertEquals(Arithmetic.myAbs(a), 101)
    }

    @Test
    fun test11() {
        val a = 101
        Assert.assertEquals(Arithmetic.myAbs(a), 101)
    }
}