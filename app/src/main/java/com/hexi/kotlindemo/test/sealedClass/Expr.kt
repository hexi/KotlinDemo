package com.hexi.kotlindemo.test.sealedClass

/**
 * Created by hexi on 2017/7/30.
 */

sealed class Expr {

}

open class Const(val number: Double) : Expr()

data class Sum(val e1: Expr, val e2: Expr) : Expr()

object NotANumber : Expr()