package com.hexi.kotlindemo.arithmetic

import com.hexi.kotlindemo.test.arithmetic.Arithmetic
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class SwapTest {
    @Test
    fun test_swap0() {
        val arr = intArrayOf(0, 1)
        Arithmetic.swap(arr)
        assertThat(arr).containsExactly(1, 0)
    }

    @Test
    fun test_swap1() {
        val arr = intArrayOf(1, 2)
        Arithmetic.swap(arr)
        assertThat(arr).containsExactly(2, 1)
    }

    @Test
    fun test_swap2() {
        val arr = intArrayOf(2, 4)
        Arithmetic.swap(arr)
        assertThat(arr).containsExactly(4, 2)
    }

    @Test
    fun test_swap3() {
        val arr = intArrayOf(3, 6)
        Arithmetic.swap(arr)
        assertThat(arr).containsExactly(6, 3)
    }

    @Test
    fun test_swap4() {
        val arr = intArrayOf(4, 7)
        Arithmetic.swap(arr)
        assertThat(arr).containsExactly(7, 4)
    }

    @Test
    fun test_swap5() {
        val arr = intArrayOf(5, 10)
        Arithmetic.swap(arr)
        assertThat(arr).containsExactly(10, 5)
    }

    @Test
    fun test_swap6() {
        val arr = intArrayOf(5, 9)
        Arithmetic.swap(arr)
        assertThat(arr).containsExactly(9, 5)
    }

    @Test
    fun test_swap7() {
        val arr = intArrayOf(5, 8)
        Arithmetic.swap(arr)
        assertThat(arr).containsExactly(8, 5)
    }

    @Test
    fun test_swap8() {
        val arr = intArrayOf(5, 7)
        Arithmetic.swap(arr)
        assertThat(arr).containsExactly(7, 5)
    }

    @Test
    fun test_swap9() {
        val arr = intArrayOf(5, 6)
        Arithmetic.swap(arr)
        assertThat(arr).containsExactly(6, 5)
    }

    @Test
    fun test_swap10() {
        val arr = intArrayOf(5, 5)
        Arithmetic.swap(arr)
        assertThat(arr).containsExactly(5, 5)
    }
}