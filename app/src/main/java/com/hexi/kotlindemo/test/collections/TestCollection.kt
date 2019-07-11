package com.hexi.kotlindemo.test.collections

/**
 * Created by hexi on 2017/8/3.
 */

fun main(args: Array<String>) {
    val numbers: MutableList<Int> = mutableListOf(1, 2, 3)
    val readOnlyView: List<Int> = numbers
    println(numbers)
    numbers.add(4)
    println(readOnlyView)

    //只读视图，不能修改list
//    readOnlyView.clear()

    val strings = hashSetOf<String>("a", "b", "c", "c")
    assert(strings.size == 3)

    //扩展方法
    val items = listOf(1, 2, 3, 4)
    assert(items.first() == 1)
    assert(items.last() == 4)
    println(items.filter { it % 2 == 0 })
    val rwList = mutableListOf(1, 2, 3)
    println(rwList.requireNoNulls())
    if (rwList.none { it > 6 }) println("No items above 6")
    val firstItem = rwList.firstOrNull()
}

//有时你想给调用者返回一个集合在某个特定时间的一个快照, 一个保证不会变的:
class Controller {
    private val _items = mutableListOf<String>()
    val items:List<String> get() = _items.toList()
}
