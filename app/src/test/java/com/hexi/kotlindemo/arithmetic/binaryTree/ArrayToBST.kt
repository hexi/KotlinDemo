package com.hexi.kotlindemo.arithmetic.binaryTree

import kotlin.math.ceil

/**
 * 将有序数组转换为平衡二叉搜索树
 */
object ArrayToBST {
    private var numbers: IntArray? = null

    fun sortedArrayToBST(nums: IntArray? = null): TreeNode<Int>? {
        if (nums == null) {
            return null
        }
        this.numbers = nums
        return buildTree(0, nums.size - 1)
    }

    private fun buildTree(l: Int, r: Int): TreeNode<Int>? {
        if (l > r) return null
        val nums = this.numbers ?: return null
        val middle = l + ceil((r - l) / 2.0).toInt()
        val root = TreeNode(nums[middle])
        root.left = buildTree(l, middle - 1)
        root.right = buildTree(middle + 1, r)
        return root
    }
}