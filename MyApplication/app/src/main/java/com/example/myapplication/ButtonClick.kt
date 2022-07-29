package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.TypedValue
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView

class ButtonClick : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       var count = 1
        val root = LinearLayout(this)
        root.orientation = LinearLayout.VERTICAL
        val btn = Button(this)
        btn.text = "add"
        btn.setOnClickListener {
            val t = TextView(this)
            t.text = "Hello ${count}"
            count++
            root.addView(t)
        }

        root.addView(btn)
        setContentView(root)

        //세터, 게터 함수 관련 얘기해야 된다고 얘기좀 해주세요~~


    }
}