package com.hexi.kotlindemo.test.arithmetic

import com.hexi.kotlindemo.test.arithmetic.data.ListNode

object Arithmetic {

    fun isOdd(number: Int): Boolean {
        return (number and  1) == 1
    }

    fun isEven(number: Int): Boolean {
        return (number and 1) == 0
    }

    /**
     * 利⽤异或: A^A = 0, A^0 = A
     */
    fun swap(arr: IntArray) {
        if (arr.size < 2) return
        arr[0] = arr[0] xor arr[1]
        arr[1] = arr[0] xor arr[1] // arr[1] = arr[0] ^ arr[1] ^ arr[1] = arr[0] ^ 0 = arr[0]
        arr[0] = arr[0] xor arr[1] // arr[0] = arr[0] ^ arr[0] ^ arr[1] = arr[1] ^ 0 = arr[1]
    }

    fun getAverage(a: Int, b: Int): Int {
        return (a and b) + ((a xor b) shr 1)
    }

    /**
     * 求绝对值
     *
     * 数值在计算机以补码形式表示和存储，负数的绝对值等于对其补码全部取反并加1，就可得到其数值。
     * n右移31位，可以获得n的符号。若n为正数，得到0；若n为负数，得到 -1。
     * 如果n为正数，n ^ 0 - 0 = n；如果n为负数，n ^ (-1) - (-1) = ~(n) + 1
     *
     */
    fun myAbs(n: Int): Int {
        return (n xor (n shr 31)) - (n shr 31)
    }

    /**
     * 是不不是2的整数次⽅方
     *
     * 如果为2的整数次方那么二进制中的只有⼀一个1，消掉这个1就变为0
     */
    fun isLog2(number: Int): Boolean {
        return (number and (number - 1)) == 0
    }

    /**
     * 求一个整数的二进制表示中1的个数
     * n&(n - 1) 会消掉最低位的1 如：
     *   1010
     * & 1001
     * ------
     *   1000
     *
     */
    fun countOne(number: Int): Int {
        var count = 0
        var a = number
        while (a != 0) {
            a = a and (a - 1)
            count++
        }
        return count
    }

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

    /**
     * 给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。
     * 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
     * 您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
     *
     * 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
     * 输出：7 -> 0 -> 8
     * 原因：342 + 465 = 807
     *
     */
    fun addTwoNumbers(l1: ListNode?, l2: ListNode?): ListNode? {
        val result = ListNode(0)
        var current = result
        var p1 = l1
        var p2 = l2
        var carry = 0

        while (p1 != null || p2 != null) {
            val x: Int = p1?.`val` ?: 0
            val y: Int = p2?.`val` ?: 0
            val sum = carry + x + y
            carry = sum / 10
            current.next = ListNode(sum % 10)
            current.next?.let { current = it }
            p1 = p1?.next
            p2 = p2?.next
        }

        if (carry > 0) {
            current.next = ListNode(carry)
        }
        return result.next
    }

    /**
     *
     * 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
     * 你可以假设每种输入只会对应一个答案。但是，你不能重复利用这个数组中同样的元素。
     *
     * 给定 nums = [2, 7, 11, 15], target = 9
     * 因为 nums[0] + nums[1] = 2 + 7 = 9
     * 所以返回 [0, 1]
     *
     */
    fun twoSum(nums: IntArray, target: Int): IntArray {
        val result = IntArray(2)
        val map: MutableMap<Int, Int> = mutableMapOf()
        for (i in nums.indices) {
            val current = nums[i]
            val subtractor = target - current
            if (map.containsKey(subtractor)) {
                val subtractorIndex = map[subtractor]!!
                if (i < subtractorIndex) {
                    result[0] = i
                    result[1] = subtractorIndex
                } else {
                    result[0] = subtractorIndex
                    result[1] = i
                }
                break
            }
            map[current] = i
        }

        return result
    }

    /**
     * 在其他数都出现两次的数组中找到只出现一次的那个数。
     *
     * 首先我们应该知道二进制异或操作，异或结果是二进制中两个位相同为0，相异为1。因此可以有个规律：
     * 规律1：A^A = 0, A^0 = A
     * 还需要知道一个规律：
     * 规律2：多个数异或操作，遵循交换律和结合律。
     *
     * 如果我们有一个变量 eO = 0 那么在遍历数组过程中，使每个数与 eO 异或得到的值再赋值给 eO，
     * 即 eO = eO ^ num 那么遍历结束后eO的值一定是那个出现一次的数的值。我们可以举个例子：
     * 假设有这么一个序列： C B D A A B C 其中只有 D 出现一次，那么因为异或满足交换律和结合律，
     * 所以我们遍历异或此序列的过程等价于：
     * eO ^ (A ^ A ^ B ^ B ^ C ^ C ) ^ D = eO ^ 0 ^ D = D
     *
     */
    fun oddTimesNum(arr: IntArray): Int {
        var e0 = 0
        arr.forEach { item ->
            e0 = e0 xor item
        }

        return e0
    }

    /**
     * 在其他数都出现两次的数组中找到只出现一次的那两个数。
     *
     * 我们顺着上题的思路来思考，如果有两个数获得的结果 eO 肯定是 eO = a^b,此题的关键就在于如何分别得到 a，b 这两个数。
     * 我们应该想到，任何不相同的两个除了跟自己异或外，不可能每一个位都相同，也就是说不相同的两个数 a b 异或得到结果二进制表示上肯定有一位为 1。 这是关键。
     *
     * 我们可以假设第 k 位不为 0 ，那么就说明 a 与 b 在这位上数值不相同。我们要做只是设置一个数第 k 位 为 1，其余位为 0 记为 rightOne。
     *
     * 这时需要拿 eOhasOne = 0 再异或遍历一次数组，但是需要忽略与 rightOne 相与等于 0 的数。
     * 因为相与等于 0 则代表了这个数肯定是两个数中第 k 位不为 1的那个。
     * 最终得到的 eOhasOne 就是 a b 中第 k 为 1 的那个。
     *
     * 那么接下来就剩下一个问题要解决了，如何找到 rightOne ，
     * 这里采用与本身补码相与的方法得到即 int rightOne = eO & (~eO + 1) 。
     *
     */
    fun findTwoOddTimesNum(arr: IntArray): IntArray {
        var e0 = 0
        arr.forEach { item ->
            e0 = e0 xor item
        }
        var rightOne = e0 and (e0.inv() + 1)
        var e0hasOne = 0
        arr.forEach { item ->
            if ((rightOne and item) != 0) {
                e0hasOne = e0hasOne xor item
            }
        }

        return intArrayOf(e0hasOne, e0 xor e0hasOne)
    }

    /**
     * 比特位计数
     *
     * 给定一个非负整数 num。对于 0 ≤ i ≤ num 范围中的每个数字 i ，计算其二进制数中的 1 的数目并将它们作为数组返回。
     * 示例:
     * 输入: 5
     * 输出: [0,1,1,2,1,2]
     *
     * 动态规划 + 最高有效位
     * 0 = (0)2
     * 1 = (1)2
     *
     * 2 = (10)2
     * 3 = (11)2
     *
     * 4 = (100)2
     * 5 = (101)2
     * 6 = (110)2
     * 7 = (111)2
     *
     * 8  = (1000)2
     * 9  = (1001)2
     * 10 = (1010)2
     * 11 = (1011)2
     * 12 = (1100)2
     * 13 = (1101)2
     * 14 = (1110)2
     * 15 = (1111)2
     *
     * 遍历:
     * [0, 1)
     * [2, 3)
     * [4, 7)
     * [8, 15)
     * [16, 31)
     *
     * 对于pop count P(x)P(x)，我们有以下的状态转移函数：
     * P(x+b) = P(x)+1, b = 2^m > x
     *
     */
    fun countBitsByHighestOrder(num: Int): IntArray {
        val res = IntArray(num + 1)
        var i = 0
        var b = 1
        while (b <= num) {
            while (i < b && i + b <= num) {
                res[i + b] = res[i] + 1
                ++i
            }
            b = b shl 1 // b = 2b
            i = 0 // reset i
        }

        return res
    }

    /**
     * 比特位计数
     *
     * 给定一个非负整数 num。对于 0 ≤ i ≤ num 范围中的每个数字 i ，计算其二进制数中的 1 的数目并将它们作为数组返回。
     * 示例:
     * 输入: 5
     * 输出: [0,1,1,2,1,2]
     *
     * 动态规划 + 最低有效位。
     *
     * 直觉：只要x' 小于 x，且它们的 pop count 之间存在函数关系，就可以写出其他的状态转移函数。
     * 算法：遵循上一方法的相同原则，我们还可以通过最低有效位来获得状态转移函数。
     * 观察x 和 x' = x / 2 的关系：
     * x = (1001011101)2 = (605)10
     * x′ = (100101110)2 = (302)10
     * 可以发现 x'与x 只有一位不同，这是因为x'可以看做x移除最低有效位的结果。这样，我们就有了下面的状态转移函数：
     * P(x) = P(x/2) + (x mod 2)
     */
    fun countBitsByLowestOrder(num: Int): IntArray {
        val res = IntArray(num + 1)
        for (i in 1 .. num) {
            res[i] = res[i shr  1] + (i and 1)
        }
        return res
    }


    /**
     *
     * 比特位计数
     *
     * 给定一个非负整数 num。对于 0 ≤ i ≤ num 范围中的每个数字 i ，计算其二进制数中的 1 的数目并将它们作为数组返回。
     * 示例:
     * 输入: 5
     * 输出: [0,1,1,2,1,2]
     *
     * 动态规划 + 最后设置位
     * 状态转移函数：P(x) = P(x & (x−1)) + 1
     */
    fun countBitsByFirstOne(num: Int): IntArray {
        val res = IntArray(num + 1)
        for (i in 1 .. num) {
            res[i] = res[ i and (i - 1)] + 1
        }

        return res
    }

}