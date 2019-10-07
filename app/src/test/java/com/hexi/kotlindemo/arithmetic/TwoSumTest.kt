package com.hexi.kotlindemo.arithmetic

import com.google.gson.Gson
import com.hexi.kotlindemo.test.arithmetic.Arithmetic
import org.junit.Test

class TwoSumTest {
    @Test
    fun test_twoSum() {
        val nums = intArrayOf(2, 7, 11, 15)
        val target = 22
        val result = Arithmetic.twoSum(nums, target)
        println("test_twoSum: ${Gson().toJson(result)}")
    }
}