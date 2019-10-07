package com.hexi.kotlindemo.arithmetic

import com.hexi.kotlindemo.test.arithmetic.Arithmetic
import org.junit.Assert
import org.junit.Test

class SubMaxStringTest {
    @Test
    fun testLengthOfLongestSubstring0() {
        val str = "abcabcbb"
        val len = Arithmetic.lengthOfLongestSubstring(str)
        Assert.assertEquals(len, 3)
    }

    @Test
    fun testLengthOfLongestSubstring1() {
        val str = "bbbbb"
        val len = Arithmetic.lengthOfLongestSubstring(str)
        Assert.assertEquals(len, 1)
    }

    @Test
    fun testLengthOfLongestSubstring2() {
        val str = "pwwkew"
        val len = Arithmetic.lengthOfLongestSubstring(str)
        Assert.assertEquals(len, 3)
    }

    @Test
    fun testLengthOfLongestSubstring3() {
        val str = "dvdf"
        val len = Arithmetic.lengthOfLongestSubstring(str)
        Assert.assertEquals(len, 3)
    }
}