package com.hexi.kotlindemo.arithmetic

import com.hexi.kotlindemo.test.arithmetic.SubMaxString
import org.junit.Assert
import org.junit.Test

class SubMaxStringTest {
    @Test
    fun testLengthOfLongestSubstring0() {
        val str = "abcabcbb"
        val len = SubMaxString.lengthOfLongestSubstring(str)
        Assert.assertEquals(len, 3)
    }

    @Test
    fun testLengthOfLongestSubstring1() {
        val str = "bbbbb"
        val len = SubMaxString.lengthOfLongestSubstring(str)
        Assert.assertEquals(len, 1)
    }

    @Test
    fun testLengthOfLongestSubstring2() {
        val str = "pwwkew"
        val len = SubMaxString.lengthOfLongestSubstring(str)
        Assert.assertEquals(len, 3)
    }

    @Test
    fun testLengthOfLongestSubstring3() {
        val str = "dvdf"
        val len = SubMaxString.lengthOfLongestSubstring(str)
        Assert.assertEquals(len, 3)
    }
}