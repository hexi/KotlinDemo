package com.hexi.kotlindemo.arithmetic

import com.hexi.kotlindemo.test.arithmetic.Arithmetic
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class OddTimesNumberTest {
    @Test
    fun test0() {
        val arr = intArrayOf(2, 1, 3, 3, 2, 1, 4, 5, 4)
        assertThat(Arithmetic.oddTimesNum(arr)).isEqualTo(5)
    }

    @Test
    fun test1() {
        val arr = intArrayOf(0, 11, 33, 55, 33, 11, 33, 55, 0)
        assertThat(Arithmetic.oddTimesNum(arr)).isEqualTo(33)
    }
}