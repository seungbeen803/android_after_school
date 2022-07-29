package com.example.lotteryapp

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class LotteryNumListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.lottery_num_list_activity_)

        val pref = getSharedPreferences("nums", Context.MODE_PRIVATE)
        // 준비물 1 -> 리스트에 포함될 데이터, numList
        var lottoNums = pref.getString("lottonums", "")
        var numList = if(lottoNums == "") {
            mutableListOf<String>()
        }else{
            // lottoNums 안에는 데이터가 없을 수가 없다
            // !! => null을 허용하지 않음, 값이 있다는 것을 확신할 때만 사용해야한다
            lottoNums!!.split(",").toMutableList()
        }

        val listView = findViewById<RecyclerView>(R.id.num_list)
        listView.setHasFixedSize(true)
//        준비물 3 -> 레이아웃 매니저
        listView.layoutManager = LinearLayoutManager(this)

//      준비물 4,5
        listView.adapter = LotteryListAdapter(numList)

        listView.setHasFixedSize(true)

    }

}