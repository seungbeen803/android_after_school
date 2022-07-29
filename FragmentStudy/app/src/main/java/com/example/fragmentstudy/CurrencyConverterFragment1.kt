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
// CurrencyConverterFragment1 : Fragment() -> 이때 사용되는 콜론은 상속이다.
class CurrencyConverterFragment1 : Fragment() {
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
            R.layout.currency_converter_fragment1, // 실질적으로 바꿔야하는 값
            container,
            false
        )

        val calculateBtn = view.findViewById<Button>(R.id.calculate)
        val amount = view.findViewById<EditText>(R.id.amount)
        val result = view.findViewById<TextView>(R.id.result)
        val fromCurrencySpinner = view.findViewById<Spinner>(R.id.from_currency)
        val toCurrencySpinner = view.findViewById<Spinner>(R.id.to_currency)

        // Spinner 초기화
        val curreneySelectionArrayAdapter =ArrayAdapter<String>(
            view.context,
            // android.R -> 안드로이드에서 이미 만들어 놓은 리소스
            // R -> 내가 만든 리소스
            android.R.layout.simple_spinner_item,
            listOf("USD", "EUR", "JPY", "KRW") // 표시 되는 부분은 listOf()이기 때문에 이곳을 바꿔주면 된다
        )
        curreneySelectionArrayAdapter.setDropDownViewResource(
            android.R.layout.simple_spinner_dropdown_item
        )
        // Spinner에 만들어 놓은 adapter 적용
        fromCurrencySpinner.adapter = curreneySelectionArrayAdapter
        toCurrencySpinner.adapter = curreneySelectionArrayAdapter

        calculateBtn.setOnClickListener {
            result.text = calculateCurrency(
                // 문자열을 더블로 바꿈
                amount.text.toString().toDouble(),
                fromCurrencySpinner.selectedItem.toString(),
                toCurrencySpinner.selectedItem.toString()
            ).toString()
        }
        // 구현할 메서드가 2개
        // object : -> 익명 클래스를 만들 때 사용
        val itemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                result.text = calculateCurrency(
                    // 문자열을 더블로 바꿈
                    amount.text.toString().toDouble(),
                    fromCurrencySpinner.selectedItem.toString(),
                    toCurrencySpinner.selectedItem.toString()
                ).toString()
            }
            override fun onNothingSelected(p0: AdapterView<*>?) {}
        }
        // 위의 itemSelectedListener를 받음
        fromCurrencySpinner.onItemSelectedListener = itemSelectedListener
        toCurrencySpinner.onItemSelectedListener = itemSelectedListener


//      https://ddolcat.tistory.com/576
//        val textWatcher = object : TextWatcher {
//            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
//
//            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
//
//            override fun afterTextChanged(p0: Editable?) {
//                result.text = calculateCurrency(
//                    // 문자열을 더블로 바꿈
//                    amount.text.toString().toDouble(),
//                    fromCurrencySpinner.selectedItem.toString(),
//                    toCurrencySpinner.selectedItem.toString()
//                ).toString()
//            }
//        }
//        // EditText 안의 내용이 바뀔 때마다 안에 내용을 반복
//        amount.addTextChangedListener(textWatcher)
        return view
    }
}