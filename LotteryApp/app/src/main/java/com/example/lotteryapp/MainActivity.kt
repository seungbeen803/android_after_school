package com.example.lotteryapp

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {
    // 속성을 만듦
    // 값 초기화 안해도 됨, nullable 타입 안해도 됨
    lateinit var currentNums: String

    fun generateRandomLottoNum(count: Int, sep: String = "-"): String {
        val nums = mutableListOf<Int>()
        for(i in 1..count) nums.add((1..45).random())
        return nums.joinToString(sep)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // SharedPreferences 객체 가져오기
        val pref = getSharedPreferences("nums", Context.MODE_PRIVATE)

        val lottoNumView = findViewById<TextView>(R.id.lotto)
        currentNums = generateRandomLottoNum(6, "-")
        lottoNumView.text = currentNums

        var num = findViewById<Button>(R.id.num)
        num.setOnClickListener {
            currentNums = generateRandomLottoNum(6, "-")
            lottoNumView.text = currentNums
        //  fun generateRandomLottoNum(count: Int): String {
//            val nums = mutableListOf<Int>()
//            for(i in 1..count) nums.add((1..45).random())
//            return nums.joinToString("-")
//       }
        }

        val saveNumberBtn = findViewById<Button>(R.id.numsave)
        saveNumberBtn.setOnClickListener {
            // 문자열 가져옴
            // 문자열을 리스트로 변환
            var lottoNums = pref.getString("lottonums", "")
            var numList = if(lottoNums == "") {
                mutableListOf<String>()
            }else{
                // lottoNums 안에는 데이터가 없을 수가 없다
                    // !! => null을 허용하지 않음, 값이 있다는 것을 확신할 때만 사용해야한다
                lottoNums!!.split(",").toMutableList()
            }

            numList.add(currentNums)
            Log.d("mytag", numList.size.toString())
            Log.d("mytag", numList.toString())
            val editor = pref.edit()
            // 리스트를 문자열로
            editor.putString("lottonums", numList.joinToString(","))
            // apply 해야만 바뀜
            editor.apply()
        }

        findViewById<Button>(R.id.savenum).setOnClickListener {
            // Activity의 목적지를 정해줌
            // 원하는 액티비티로 이동하기 위해서 사용
            val intent = Intent(this,
                LotteryNumListActivity::class.java)
            startActivity(intent)
        }

        // 암시적 인텐트
        var result = findViewById<Button>(R.id.result)
        result.setOnClickListener {
            var intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://m.dhlottery.co.kr/gameResult.do?method=byWin&wiselog=M_A_1_8"))
            startActivity(intent)
        }

    }
}