package com.hexi.kotlindemo.arithmetic

import com.hexi.kotlindemo.test.arithmetic.Arithmetic
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class IsLog2Test {

    @Test
    fun test_isLog2_1() {
        val a = 1
        assertThat(Arithmetic.isLog2(a)).isTrue()
    }

    @Test
    fun test_isLog2_2() {
        val a = 2
        assertThat(Arithmetic.isLog2(a)).isTrue()
    }

    @Test
    fun test_isLog2_3() {
        val a = 3
        assertThat(Arithmetic.isLog2(a)).isFalse()
    }

    @Test
    fun test_isLog2_4() {
        val a = 4
        assertThat(Arithmetic.isLog2(a)).isTrue()
    }

    @Test
    fun test_isLog2_5() {
        val a = 5
        assertThat(Arithmetic.isLog2(a)).isFalse()
    }

    @Test
    fun test_isLog2_6() {
        val a = 6
        assertThat(Arithmetic.isLog2(a)).isFalse()
    }

    @Test
    fun test_isLog2_7() {
        val a = 7
        assertThat(Arithmetic.isLog2(a)).isFalse()
    }

    @Test
    fun test_isLog2_8() {
        val a = 8
        assertThat(Arithmetic.isLog2(a)).isTrue()
    }

    @Test
    fun test_isLog2_9() {
        val a = 9
        assertThat(Arithmetic.isLog2(a)).isFalse()
    }

    @Test
    fun test_isLog2_10() {
        val a = 9
        assertThat(Arithmetic.isLog2(a)).isFalse()
    }

    @Test
    fun test_isLog2_12() {
        val a = 12
        assertThat(Arithmetic.isLog2(a)).isFalse()
    }

    @Test
    fun test_isLog2_16() {
        val a = 16
        assertThat(Arithmetic.isLog2(a)).isTrue()
    }
}