package com.hexi.kotlindemo.arithmetic.binaryTree

import com.hexi.kotlindemo.arithmetic.binaryTree.TreeNodeUtil.arrayToTreeNode
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class TreeNodeUtilTest {

    @Test
    fun test1() {
        val p = TreeNode(2, null, TreeNode(4, TreeNode(9), TreeNode(8, TreeNode(4), null)))
        val q = arrayToTreeNode(arrayOf(2, null, 4, 9, 8, null, null, 4))
        assertThat(TreeNodeUtil.isSameTree(p, q)).isTrue
    }

    @Test
    fun test2() {
        val p = TreeNode(3, TreeNode(1, TreeNode(3)), TreeNode(4, TreeNode(1), TreeNode(5)))
        val q = arrayToTreeNode(arrayOf(3, 1, 4, 3, null, 1, 5))
        assertThat(TreeNodeUtil.isSameTree(p, q)).isTrue
    }
}