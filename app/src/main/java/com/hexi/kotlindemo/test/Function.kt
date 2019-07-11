package com.hexi.kotlindemo.test

/**
 * Created by hexi on 2017/7/23.
 */

/**
 * 中缀表示法
 * 函数还可以用中缀表示法调用，当
 * 他们是成员函数或扩展函数
 * 他们只有一个参数
 * 他们用 infix 关键字标注
 */

infix fun Int.add(x: Int): Int {
    println("add...")
    return this + x
}

/**
 * 覆盖方法总是使用与基类型方法相同的默认参数值。
 * 当覆盖一个带有默认参数值的方法时， 必须从签名中省略默认参数值:
 * */
open class A {
    open fun foo(i: Int = 10) {

    }
}

class B : A() {
    override fun foo(i: Int) {
        println(i)
    }
}

fun reformat(str: String,
             normalizeCase: Boolean = true,
             upperCaseFirstLetter: Boolean = true,
             divideByCamelHumps: Boolean = false,
             wordSeparator: Char = ' ') {

    println("str:$str, " +
            "normalizeCase:$normalizeCase, " +
            "upperCaseFirstLetter:$upperCaseFirstLetter, " +
            "divideByCamelHumps:$divideByCamelHumps, " +
            "wordSeparator:$wordSeparator")
}

fun someAdd(x: Int = 20, y: Int) = x + y

/**
 * 可变数量的参数
 * 函数的参数(通常是最后一个)可以用 vararg 修饰符标记:
 */
fun <T> asList(vararg ts: T): List<T> {
    val result = ArrayList<T>();
    for (t in ts) {
        result.add(t)
    }
    return result
}

fun <T> max(collection: Collection<T>, less: (left: T, right: T) -> Boolean): T? {
    var max: T? = null
    for (it in collection)
        if (max == null || less(max, it))
            max = it
    return max
}

val funLit = lambda@ fun String.() {
    val d = this // funLit 的接收者
}

fun main(args: Array<String>) {
    val ss = "aaa"
    ss.funLit()

    println(1 add 2)

    B().foo()

    println(someAdd(y = 10))

    reformat("hello",
            normalizeCase = true,
            divideByCamelHumps = false,
            wordSeparator = '_'
    )

    reformat("hello", wordSeparator = '-')

    println(asList(1, 2, 3))

    val a = arrayOf(2, 3, 4)
    println(asList(-1, *a, 5))

    val f = fun(x: Int) = x > 0

    val ints: List<Int> = listOf(-1, -2, 0, 2, 4, 6)
    ints.filter(f)

}