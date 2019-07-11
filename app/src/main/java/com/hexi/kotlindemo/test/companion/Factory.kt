package com.hexi.kotlindemo.test.companion

/**
 * Created by hexi on 2017/7/23.
 */
interface Factory<out T> {
    fun create(): T
}