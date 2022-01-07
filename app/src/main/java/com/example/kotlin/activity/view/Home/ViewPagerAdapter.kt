package com.example.kotlin.activity.view.Home

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.activity.InviteActivity
import kotlinx.android.synthetic.main.item_home_banner.view.*

class ViewPagerAdapter(itemList: ArrayList<Int>, context: Context) : RecyclerView.Adapter<ViewPagerAdapter.PagerViewHolder>() {
    var item = itemList
    var context = context
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = PagerViewHolder((parent)) // viewholder

    override fun getItemCount():  Int = Int.MAX_VALUE	// 무한 스크롤을 위해 무한으로

    // 페이지 넘길 때마다 뷰홀더에 데이터 연결
    override fun onBindViewHolder(holder: PagerViewHolder, position: Int) {
        holder.item.setImageResource(item[position%3])
        // 클릭 시 페이지 이동
        holder.itemView.setOnClickListener{

                    val intent = Intent(context, InviteActivity::class.java)
                    context.startActivity(intent)

        }

    }

    inner class PagerViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder
        (LayoutInflater.from(parent.context).inflate(R.layout.item_home_banner, parent, false)){

        val item = itemView.imageView_item!!
    }
}