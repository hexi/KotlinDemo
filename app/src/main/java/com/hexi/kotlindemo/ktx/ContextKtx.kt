package com.hexi.kotlindemo.ktx

import android.content.Context
import android.view.Gravity
import android.widget.Toast

fun Context.showToast(text: CharSequence) {
    Toast.makeText(this, text, Toast.LENGTH_SHORT).apply {
        setGravity(Gravity.CENTER, 0, 0)
    }.show()
}