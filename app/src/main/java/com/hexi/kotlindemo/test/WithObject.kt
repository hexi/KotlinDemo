package com.hexi.kotlindemo

/**
 * Created by hexi on 2017/8/11.
 */

class Turtle {
    fun penDown() {
        println("penDown...")
    }

    fun penUp() {
        println("penUp...")
    }

    fun turn(degrees: Double) {
        println("turn $degrees ...")
    }

    fun forward(pixels: Double) {
        println("forward $pixels ...")
    }
}

fun main() {
    val myTurtle = Turtle()
    with(myTurtle) {
        penDown()
        for (i in 1..4) {
            forward(100.0)
            turn(90.0)
        }
        penUp()
    }
}