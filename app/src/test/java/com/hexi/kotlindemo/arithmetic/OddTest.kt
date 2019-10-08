package com.hexi.kotlindemo.arithmetic

import com.hexi.kotlindemo.test.arithmetic.Arithmetic
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class OddTest {
    @Test
    fun is_odd_1() {
        val number = 1
        assertThat(Arithmetic.isOdd(number)).isTrue()
    }

    @Test
    fun is_odd_2() {
        val number = 2
        assertThat(Arithmetic.isOdd(number)).isFalse()
    }

    @Test
    fun is_odd_3() {
        val number = 3
        assertThat(Arithmetic.isOdd(number)).isTrue()
    }

    @Test
    fun is_odd_5() {
        val number = 5
        assertThat(Arithmetic.isOdd(number)).isTrue()
    }

    @Test
    fun is_odd_11() {
        val number = 11
        assertThat(Arithmetic.isOdd(number)).isTrue()
    }

    @Test
    fun is_odd_101() {
        val number = 101
        assertThat(Arithmetic.isOdd(number)).isTrue()
    }

    @Test
    fun is_odd_103() {
        val number = 103
        assertThat(Arithmetic.isOdd(number)).isTrue()
    }
}
