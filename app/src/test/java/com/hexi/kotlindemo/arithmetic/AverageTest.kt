package com.hexi.kotlindemo.arithmetic

import com.hexi.kotlindemo.test.arithmetic.Arithmetic
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class AverageTest {
    @Test
    fun test_average0() {
        val a = 1
        val b = 3
        assertThat(Arithmetic.getAverage(a, b)).isEqualTo(2)
    }

    @Test
    fun test_average1() {
        val a = 13
        val b = 15
        assertThat(Arithmetic.getAverage(a, b)).isEqualTo(14)
    }

    @Test
    fun test_average3() {
        val a = 13
        val b = 10
        assertThat(Arithmetic.getAverage(a, b)).isEqualTo(11)
    }

    @Test
    fun test_average4() {
        val a = 0
        val b = 1
        assertThat(Arithmetic.getAverage(a, b)).isEqualTo(0)
    }
}