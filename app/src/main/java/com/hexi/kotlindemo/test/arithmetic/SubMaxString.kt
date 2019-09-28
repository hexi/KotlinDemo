package com.hexi.kotlindemo.test.arithmetic

object SubMaxString {
    /**
     * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
     *
     * 输入: "abcabcbb"
     * 出: 3
     * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
     *
     * 输入: "bbbbb"
     * 输出: 1
     * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
     *
     * 输入: "pwwkew"
     * 输出: 3
     * 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
     *   请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
     *
     */
    fun lengthOfLongestSubstring(s: String): Int {
        var maxLen = 0
        val sub = StringBuilder()
        for (index in s.indices) {
            val item = s.subSequence(index, index + 1)
            val subIndex = sub.indexOf(item.toString())
            if (subIndex != -1) {
                maxLen = maxLen.coerceAtLeast(sub.length)
                val retainSub = sub.substring(subIndex + 1)
                sub.clear()
                sub.append(retainSub)
            }
            sub.append(item)
        }
        maxLen = maxLen.coerceAtLeast(sub.length)
        return maxLen
    }
}