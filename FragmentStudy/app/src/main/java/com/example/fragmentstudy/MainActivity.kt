package com.example.fragmentstudy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView

class MainActivity : AppCompatActivity(), CurrencyConverterFragment3.CurrencyCalculationListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        
        // 커밋이 호출되기 전까지 교체, 제거, 추가 작업 가능
        val transaction = supportFragmentManager.beginTransaction()
//        transaction.add(R.id.fragment_container, CurrencyConverterFragment2.newInstance("USD", "KRW"))
//        transaction.add(R.id.fragment_container, CurrencyConverterFragment2.newInstance("KRW", "USD"))
          transaction.add(R.id.fragment_container, CurrencyConverterFragment3.newInstance("USD", "KRW"))
        transaction.commit()
    }

    override fun onCalculate(result: Double, amount: Double, from: String, to: String) {
        Log.d("mytag", result.toString())
        findViewById<TextView>(R.id.result).text = result.toString()
    }
}