package com.hexi.kotlindemo.test.companion

/**
 * Created by hexi on 2017/8/19.
 */
class CompanionB {
    companion object: Factory<CompanionB> {
        override fun create(): CompanionB {
            return CompanionB()
        }
    }
}