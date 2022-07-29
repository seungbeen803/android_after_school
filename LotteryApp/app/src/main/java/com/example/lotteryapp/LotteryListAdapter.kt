package com.example.lotteryapp

import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

// 문자열
class LotteryListAdapter(val datalist: List<String>)
    : RecyclerView.Adapter<LotteryListAdapter.ItemViewHolder>()
{
    // view에 있는 값이 class 안의 View 로 들어감
        class ItemViewHolder(val view: View)
            : RecyclerView.ViewHolder(view)

    // 뷰를 만들어 주는 것
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(parent.context)
                // 한 항목을 표시할 레이아웃 관련 뷰를 만들어 줌
                // viewType 값이 바로 getItemViewType에서 반환한 레이아웃 리소스 식별자
            .inflate(viewType, parent, false)
        return ItemViewHolder(view)
    }

    // 뷰의 내용을 바꿔줌
    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.view.findViewById<TextView>(R.id.number).text = datalist[position]
    }

    override fun getItemCount(): Int {
        // 전달한 리스트의 길이 리턴
        return datalist.size
    }

    override fun getItemViewType(position: Int): Int {
        // 유일하게 구분하게 할 수 있는 숫자 리턴
        // 레이아웃 리소스 식별자를 숫자로 반환
        return R.layout.lotto_item
    }
}