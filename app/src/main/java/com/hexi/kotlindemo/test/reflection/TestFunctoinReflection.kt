package com.hexi.kotlindemo.test.reflection

/**
 * Created by hexi on 2017/9/27.
 */

fun isOdd(x: Int) = x % 2 != 0
fun isOdd(s: String) = s == "brillig" || s == "slithy" || s == "tove"

fun main(args: Array<String>) {
    val numbers = listOf(1, 2, 3)
    println(numbers.filter { isOdd(it) })  // 输出 [1, 3]
    //等价于：
    println(numbers.filter(::isOdd)) //引用到 isOdd(x: Int)

    //存储在变量中
    val predicate: (String) -> Boolean = ::isOdd // 引用到 isOdd(x: String)


    //函数组合
    //compose(f, g) = f(g(*))
    fun <A, B, C> compose(f: (B) -> C, g: (A) -> B): (A) -> C {
        return { x -> f(g(x)) }
    }

    fun length(s: String) = s.length
    val oddLength = compose(::isOdd, ::length)
    var strings = listOf("a", "ab", "abc")

    println(strings.filter(oddLength)) // 输出 "[a, abc]"

    //绑定的函数引用
    val numberRegex = "\\d+".toRegex()
    val isNumber = numberRegex::matches
    strings = listOf("abc", "124", "a70")
    println(strings.filter(isNumber)) // 输出“[124]”

}
