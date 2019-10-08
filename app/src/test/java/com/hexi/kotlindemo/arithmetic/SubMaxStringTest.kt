package com.hexi.kotlindemo.arithmetic

import com.hexi.kotlindemo.test.arithmetic.Arithmetic
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class SubMaxStringTest {
    @Test
    fun testLengthOfLongestSubstring0() {
        val str = "abcabcbb"
        val len = Arithmetic.lengthOfLongestSubstring(str)
        assertThat(len).isEqualTo(3)
    }

    @Test
    fun testLengthOfLongestSubstring1() {
        val str = "bbbbb"
        val len = Arithmetic.lengthOfLongestSubstring(str)
        assertThat(len).isEqualTo(1)
    }

    @Test
    fun testLengthOfLongestSubstring2() {
        val str = "pwwkew"
        val len = Arithmetic.lengthOfLongestSubstring(str)
        assertThat(len).isEqualTo(3)
    }

    @Test
    fun testLengthOfLongestSubstring3() {
        val str = "dvdf"
        val len = Arithmetic.lengthOfLongestSubstring(str)
        assertThat(len).isEqualTo(3)
    }
}