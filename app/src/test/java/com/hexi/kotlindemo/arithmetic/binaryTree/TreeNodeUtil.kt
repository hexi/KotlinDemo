package com.hexi.kotlindemo.arithmetic.binaryTree

import java.util.*

object TreeNodeUtil {
    /**
     * 一维数组转换二叉树结构
     * @param array
     * @return
     */
    fun arrayToTreeNode(array: Array<Int?>): TreeNode? {
        if (array.isEmpty()) {
            return null
        }
        val root = TreeNode(
            array[0]!!
        )
        val queue: Queue<TreeNode> = LinkedList<TreeNode>()
        queue.add(root)
        var isLeft = true
        for (i in 1 until array.size) {
            val node: TreeNode = queue.peek()
            if (isLeft) {
                if (array[i] != null) {
                    node.left = TreeNode(array[i]!!)
                    queue.offer(node.left)
                }
                isLeft = false
            } else {
                if (array[i] != null) {
                    node.right = TreeNode(array[i]!!)
                    queue.offer(node.right)
                }
                queue.poll()
                isLeft = true
            }
        }
        return root
    }

    fun isSameTree(p: TreeNode?, q: TreeNode?): Boolean {
        return if (p == null || q == null) {
            p == q
        } else p.value === q.value && isSameTree(p.left, q.left) && isSameTree(p.right, q.right)
    }
}