package com.hexi.kotlindemo.test.sealedClass

/**
 * Created by hexi on 2017/8/27.
 */

fun eval(expr: Expr): Double = when (expr) {
    is Foo -> 110.0
    is Const -> expr.number
    is Sum -> eval(expr.e1) + eval(expr.e2)
    NotANumber -> Double.NaN
// 不再需要 `else` 子句，因为我们已经覆盖了所有的情况
}

fun main() {
    val const = Const(1.0)
}