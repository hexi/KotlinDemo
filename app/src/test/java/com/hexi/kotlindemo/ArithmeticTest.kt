package com.hexi.kotlindemo

import com.google.gson.Gson
import com.hexi.kotlindemo.test.Arithmetic
import org.junit.Test

class ArithmeticTest {
    @Test
    fun test_twoSum() {
        val nums = intArrayOf(2, 7, 11, 15)
        val target = 22
        val result = Arithmetic.toSum2(nums, target)
        println("test_twoSum: ${Gson().toJson(result)}")
    }
}