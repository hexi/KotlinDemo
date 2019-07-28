package com.hexi.kotlindemo.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.FragmentActivity
import com.hexi.kotlindemo.R

class MainActivity : FragmentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun gotoRxJava2Sample(view: View) {
        startActivity(Intent(this, SimpleRetrofitActivity::class.java))
    }

    fun gotoRoomSample(view: View) {
        startActivity(Intent(this, RoomSampleActivity::class.java))
    }
}


