package com.hexi.kotlindemo.test.companion

/**
 * Created by hexi on 2017/7/23.
 */
class CompanionA {
    companion object: Factory<CompanionA> {
        override fun create(): CompanionA {
            println("creating CompanionA...")
            return CompanionA()
        }
    }
}