package com.hexi.kotlindemo.arithmetic

import com.hexi.kotlindemo.test.arithmetic.Arithmetic
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class TwoSumTest {
    @Test
    fun test_twoSum() {
        val nums = intArrayOf(2, 7, 11, 15)
        val target = 22
        val result = Arithmetic.twoSum(nums, target)
        assertThat(result).hasSize(2).containsExactly(1, 3)
    }
}