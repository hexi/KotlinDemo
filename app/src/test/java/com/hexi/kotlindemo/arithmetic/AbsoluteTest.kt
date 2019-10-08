package com.hexi.kotlindemo.arithmetic

import com.hexi.kotlindemo.test.arithmetic.Arithmetic
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class AbsoluteTest {
    @Test
    fun test0() {
        val a = 0
        assertThat(Arithmetic.myAbs(a)).isEqualTo(0)
    }

    @Test
    fun test1() {
        val a = -1
        assertThat(Arithmetic.myAbs(a)).isEqualTo(1)
    }

    @Test
    fun test2() {
        val a = -2
        assertThat(Arithmetic.myAbs(a)).isEqualTo(2)
    }

    @Test
    fun test3() {
        val a = -10
        assertThat(Arithmetic.myAbs(a)).isEqualTo(10)
    }

    @Test
    fun test4() {
        val a = -11
        assertThat(Arithmetic.myAbs(a)).isEqualTo(11)
    }

    @Test
    fun test5() {
        val a = 11
        assertThat(Arithmetic.myAbs(a)).isEqualTo(11)
    }

    @Test
    fun test6() {
        val a = -22
        assertThat(Arithmetic.myAbs(a)).isEqualTo(22)
    }

    @Test
    fun test7() {
        val a = 22
        assertThat(Arithmetic.myAbs(a)).isEqualTo(22)
    }

    @Test
    fun test8() {
        val a = -100
        assertThat(Arithmetic.myAbs(a)).isEqualTo(100)
    }

    @Test
    fun test9() {
        val a = 100
        assertThat(Arithmetic.myAbs(a)).isEqualTo(100)
    }

    @Test
    fun test10() {
        val a = -101
        assertThat(Arithmetic.myAbs(a)).isEqualTo(101)
    }

    @Test
    fun test11() {
        val a = 101
        assertThat(Arithmetic.myAbs(a)).isEqualTo(101)
    }
}