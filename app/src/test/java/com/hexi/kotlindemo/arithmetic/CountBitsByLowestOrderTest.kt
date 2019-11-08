package com.hexi.kotlindemo.arithmetic

import com.hexi.kotlindemo.test.arithmetic.Arithmetic
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class CountBitsByLowestOrderTest {
    @Test
    fun test2() {
        val res = Arithmetic.countBitsByLowestOrder(2)
        assertThat(res).containsExactly(0, 1, 1)
    }

    @Test
    fun test5() {
        val res = Arithmetic.countBitsByLowestOrder(5)
        assertThat(res).containsExactly(0, 1, 1, 2, 1, 2)
    }

    @Test
    fun test15() {
        val res = Arithmetic.countBitsByLowestOrder(15)
        assertThat(res).containsExactly(0, 1, 1, 2, 1, 2, 2, 3, 1, 2, 2, 3, 2, 3, 3, 4)
    }
}