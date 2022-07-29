package com.example.jsondeserializationstudy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.databind.deser.std.StdDeserializer
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue

@JsonDeserialize(using=ComplexJSONDataDeserializer::class)
data class  ComplexJSONData(
    val innerData: String,
    val data1: Int,
    val data2: String,
    val list: List<Int>)

class ComplexJSONDataDeserializer : StdDeserializer<ComplexJSONData>(
    ComplexJSONData::class.java
){
    override fun deserialize(p: JsonParser?, ctxt: DeserializationContext?): ComplexJSONData
    {
        // node ->
        val node = p?.codec?.readTree<JsonNode>(p)

        // nested 전체를 가리킴
        val nestedNode = node?.get("nested")
        // asText -> 문자열로 변형
        // 내부에서 정해준 것으로 형변환 해주어야한다.
        val innerDataValue = nestedNode?.get("inner_data")?.asText()
        // TODO : data1이랑 data2 가져오기
        // inner_nested -> 객체이기 때문에 뒤에 .asText()를 붙히면 안된다.
        val innerNestedNode = nestedNode?.get("inner_nested")
        val innerNestedData1 = innerNestedNode?.get("data1")?.asInt()
        val innerNestedData2 = innerNestedNode?.get("data2")?.asText()

        val list = mutableListOf<Int>()
        innerNestedNode?.get("list")?.elements()?.forEach {
            list.add(it.asInt())
        }

        return ComplexJSONData(
            innerDataValue!!,
            innerNestedData1!!,
            innerNestedData2!!,
            list
        )
    }

}

data class MyJSONDataClass(
    val data1: Int,
    val data2: String,
    val list: List<Int>)

data class MyJOSNNestedDataClass(val nested: MyJSONDataClass)

data class Address(
    val city: String,
    val lat: Double,
    val lon: Double)


data class Person(
    val name: String,
    val age: Int,
    val favorites: List<String>,
    val address: Address)

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var mapper = jacksonObjectMapper()
        val jsonString = """
            {
                "data1": 1234,
                "data2": "Hello",
                "list": [1, 2, 3]
            }
        """.trimIndent()

        val d1 = mapper?.readValue<MyJSONDataClass>(jsonString)
        Log.d("mytag", d1.toString())

        val jsonString2 = """
            {
                "nested": {
                    "data1": 1234,
                    "data2": "Hello",
                    "list": [1, 2, 3]
                    }
            }
        """.trimIndent()

        val d2 = mapper?.readValue<MyJOSNNestedDataClass>(jsonString2)
        Log.d("mytag", d2.toString())

        val personString = """
        {
            "name": "John",
            "age": 20,
            "favorites": ["study", "game"],
            "address": {
                "city": "Seoul",
                "lat": 0.0,
                "lon": 1.0
             }
        }
        """.trimIndent()

        val person = mapper?.readValue<Person>(personString)
        Log.d("mytag", person.toString())

        // 노드가 가리키는 곳
        val complexJsonString = """
        {
            "nested": {
                "inner_data": "Hello from inner",
                "inner_nested": {
                    "data1": 1234,
                    "data2": "Hello",
                    "list": [1, 2, 3]
                }
            }
        }
        """.trimIndent()

        val complex = mapper?.readValue<ComplexJSONData>(complexJsonString)
        Log.d("mytag", complex.toString())
    }
}