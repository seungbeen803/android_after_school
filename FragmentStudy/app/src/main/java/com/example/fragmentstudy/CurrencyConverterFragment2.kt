package com.example.fragmentstudy

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import org.w3c.dom.Text

// 상속
// (val a : Int) -> 주생성자
// CurrencyConverterFragment2 : Fragment() -> 이때 사용되는 콜론은 상속이다.
class CurrencyConverterFragment2 : Fragment() {

    lateinit var fromCurrency: String
    lateinit var toCurrency: String

    // mapOf() -> map 만드는 것
    val currencyExchangeMap = mapOf(
        "USD" to 1.0,
        "EUR" to 0.9,
        "JPY" to 110.0,
        "KRW" to 1150.0
    )

    // amount: Double -> 이때 사용되는 콜론은 타입지정
    fun calculateCurrency(amount: Double, from: String, to: String) : Double {
        var USDAmount = if(from != "USD") {
            (amount / currencyExchangeMap[from]!!)
        }else{
            amount
        }
        return currencyExchangeMap[to]!! * USDAmount
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(
            // 레이아웃 리소스 식별자
            R.layout.currency_converter_fragment2, // 실질적으로 바꿔야하는 값
            container,
            false
        )

        val calculateBtn = view.findViewById<Button>(R.id.calculate)
        val amount = view.findViewById<EditText>(R.id.amount)
        val result = view.findViewById<TextView>(R.id.result)
        val exchangeType = view.findViewById<TextView>(R.id.exchange_type)

        // nll 일 수도 있기 때문에 ?를 사용해줌
        // ?를 사용하면 null 리턴
        // !! -> nll 허용 타입에서 허용하지 않는 타입으로 변환해주는 것
        // 값이 없을 때 기본 값
        fromCurrency = (arguments?.getString("from","USD"))!!
        toCurrency = arguments?.getString("to", "USD")!!

        exchangeType.text = "${fromCurrency} ▶ ${toCurrency} 변환"



        calculateBtn.setOnClickListener {
            result.text = calculateCurrency(
                // 문자열을 더블로 바꿈
                amount.text.toString().toDouble(),
                fromCurrency,
                toCurrency
            ).toString()
        }
        
        return view
    }
    // 생성자 대신 값을 고정할 수 있도록 코드 작성
    // 클래스 메서드
    // 정적 메서드, 정적 속성을 만들기 위해서 사용 -> companion object
    companion object {
        fun newInstance(from: String, to: String): CurrencyConverterFragment2 {
            val fragment = CurrencyConverterFragment2()

            // 번들 객체를 만들고 필요한 데이터 저장
            // 객체를 만들면 안드로이드는 내부적으로 보관을 해놓는다
            // 필요한 데이터 저장을 위해 객체 생성
            val args = Bundle() // Bundle() 객체 생성
            args.putString("from", from)
            args.putString("to", to)
            fragment.arguments = args

            return fragment
        }
    }
}