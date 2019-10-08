package com.hexi.kotlindemo.arithmetic

import com.hexi.kotlindemo.test.arithmetic.Arithmetic
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class FindTwoOddTimesNumTest {
    @Test
    fun test0() {
        val arr = intArrayOf(2, 1, 3, 3, 2, 1, 4, 5)
        val result = Arithmetic.findTwoOddTimesNum(arr)
        assertThat(result)
                .hasSize(2)
                .contains(4, 5)
    }

    @Test
    fun test1() {
        val arr = intArrayOf(11, 22, 33, 11, 22, 55, -33, 55)
        val result = Arithmetic.findTwoOddTimesNum(arr)
        assertThat(result)
                .hasSize(2)
                .contains(33, -33)
    }
}