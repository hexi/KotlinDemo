package com.hexi.kotlindemo.arithmetic

import com.hexi.kotlindemo.test.arithmetic.Arithmetic
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class EvenTest {
    @Test
    fun is_even_2() {
        val number = 2
        assertThat(Arithmetic.isEven(number)).isTrue()
    }

    @Test
    fun is_even_4() {
        val number = 4
        assertThat(Arithmetic.isEven(number)).isTrue()
    }

    @Test
    fun is_odd_3() {
        val number = 3
        assertThat(Arithmetic.isEven(number)).isFalse()
    }

    @Test
    fun is_even_6() {
        val number = 6
        assertThat(Arithmetic.isEven(number)).isTrue()
    }

    @Test
    fun is_even_8() {
        val number = 8
        assertThat(Arithmetic.isEven(number)).isTrue()
    }

    @Test
    fun is_even_10() {
        val number = 10
        assertThat(Arithmetic.isEven(number)).isTrue()
    }

    @Test
    fun is_even_100() {
        val number = 100
        assertThat(Arithmetic.isEven(number)).isTrue()
    }

    @Test
    fun is_even_44() {
        val number = 44
        assertThat(Arithmetic.isEven(number)).isTrue()
    }

    @Test
    fun is_even_1000() {
        val number = 1000
        assertThat(Arithmetic.isEven(number)).isTrue()
    }
}