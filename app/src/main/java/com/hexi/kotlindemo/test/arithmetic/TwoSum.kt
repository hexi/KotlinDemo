package com.hexi.kotlindemo.test.arithmetic


object TwoSum {
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
        for(i in nums.indices) {
            for (j in (i + 1) until nums.size) {
                if ((nums[i] + nums[j]) == target) {
                    result[0] = i
                    result[1] = j
                    break
                }
            }
        }
        return result
    }

    fun twoSum2(nums: IntArray, target: Int): IntArray {
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
}