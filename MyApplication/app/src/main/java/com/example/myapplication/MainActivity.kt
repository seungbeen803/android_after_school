package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {
    // onCreate -> 생성자의 역할
    override fun onCreate(savedInstanceState: Bundle?) {
        // 무조건 부모의 것이 호출된다.
        super.onCreate(savedInstanceState)
       //setContentView(R.layout.my_layout)
        setContentView(R.layout.view_study2)


        /*// 식별자를 이용한 뷰 접근
        // id 값을 지정할 때는 소문자로 사용한다
        // find를 리턴하는 타입은 View이다
        var myButton : Button = findViewById(R.id.my_button)
        var myEditText = findViewById(R.id.my_edittext) as EditText
        var myTextView = findViewById<TextView>(R.id.my_textview) // 제일 좋은 방법

//        myButton.setOnClickListener(
//            // 익명 클래스
//            object: View.OnClickListener { // View안에 정의 되어있는 OnClickListener
//                override fun onClick(p0: View?) {
//                    Toast.makeText(this@MainActivity, "클릭!", Toast.LENGTH_SHORT).show() // 파라미터 이름 -> context
//                }
//
//            }
//        )

//        myButton.setOnClickListener {
//            v ->
//            Toast.makeText(this@MainActivity, "클릭!", Toast.LENGTH_SHORT).show()
//        }

        myButton.setOnClickListener {
            // Toast.makeText(this@MainActivity, "클릭!", Toast.LENGTH_SHORT).show() // <- 함수형 인터페이스일 때만 사용 가능하다
            // Log.d("태그명", "메시지"
            Log.d( "my_tag", "Hello") // d가 static 메서드인 이유는 Log의 이름만으로도 접근할 수 있기때문이다.
        }*/

        // 체크박스 뷰 가져오기
        val checkBox = findViewById<CheckBox>(R.id.checkbox1)
        checkBox.setOnCheckedChangeListener {
                compoundButton, b ->
            // 체크박스가 체크 되었을때 로직
            if(b) Log.d("my_tag", "checked")
            // 아닌 경우 로직
            else Log.d("my_tag", "unchecked")
        }

        val group = findViewById<RadioGroup>(R.id.radio_group)
        group.setOnCheckedChangeListener {
                radioGroup, id ->
            Log.d("my_tag", id.toString())
            // when - case
            when(id) {
                R.id.radio_button1 -> {
                    Log.d("my_tag", "버튼 1 선택")
                }
                R.id.radio_button2 -> {
                    Log.d("my_tag", "버튼 2 선택")
                }
            }
        }

        val spinner = findViewById<Spinner>(R.id.my_spinner)
        val adapter = ArrayAdapter.createFromResource(this,
        R.array.my_str_array,
        android.R.layout.simple_spinner_item)
        adapter.setDropDownViewResource(
            android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter

        // 익명 클래스 쓰면 클래스 정의와 객체 생성을 한꺼번에
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?,
                                        p1: View?,
                                        p2: Int,
                                        p3: Long) {
                Log.d("my_tag", p2.toString())
                val selected = p0?.getItemAtPosition(p2).toString()

                Log.d("my_tag", selected)
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {

            }

        }
    }
}