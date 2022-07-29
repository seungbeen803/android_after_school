package com.example.anestudy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import kotlin.math.sqrt
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.btn).setOnClickListener {
            // Toast 메세지 -> 앱의 밑에 뜨는 것
            Toast.makeText(this,
            "Clicked!",
            Toast.LENGTH_SHORT).show()
        }

        var result = findViewById<TextView>(R.id.result)
        findViewById<Button>(R.id.anr).setOnClickListener {
            Thread(Runnable {
//                MAX_VALUE -> 21억
                var sum = 0.0
                for(i in 1 .. 60) {
                    // 랜덤 제곱근 값을 더한다
                    sum += sqrt(Random.nextDouble())
                    Thread.sleep(100)
                }
                Log.d("mytag", sum.toString())
                // runonUiThread {} -> UI 스레드로 가서 실행해라
                runOnUiThread{
                    result.text = sum.toString()
                }
            }).start()
        }
    }
}