package com.example.kotlin.activity.data.recyclerviewadapter

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.kotlin.activity.data.form.Feed.Feed
import com.example.myapplication.R
import com.example.myapplication.activity.ContentActivity


class FeedRecyclerViewAdapter(private val context: Context): RecyclerView.Adapter<FeedRecyclerViewAdapter.ViewHolder>() {
    private var feedList = mutableListOf<Feed>()

    // component 숨기기 보이기
    fun showHide(view:View) {
        Log.d("우왕", "오앙")
        if (view.visibility == View.VISIBLE){
            view.visibility = View.GONE
        } else {
            view.visibility = View.VISIBLE
        }
    }

    // data init
    fun setListData(data:Feed){
        feedList.add(data)
        notifyDataSetChanged()
    }

    // 뷰 홀더 만들기 => recycler_view_item.xml과 연결하여 view로 변환
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // HomeActivty에서 사용하는 경우
        if(context.toString().contains("HomeActivity")) {
            val view = LayoutInflater.from(context).inflate(R.layout.recycler_view_feed,parent, false)

            view.setOnClickListener {
                val intent = Intent(context, ContentActivity::class.java)
                context.startActivity(intent)
            }

            return ViewHolder(view)
        } else {
            val view = LayoutInflater.from(context).inflate(R.layout.recycler_view_feed,parent, false)
            return ViewHolder(view)
        }

    }

    // 데이터를 바인딩하여 뷰에 뿌려질 수 있도록
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val feed : Feed = feedList[position]
        Glide.with(context)
            .load(feed.thumbnail)
            .fitCenter()
            .into(holder.thumbnail)
//
//        // 내용 숨기기 => 클릭 시 보이게 하기
//        showHide(holder.name)
//        showHide(holder.value)

    }

    // 화면에 가져올 아이템 개수 세기
    override fun getItemCount(): Int {
        return feedList.size
    }

    inner class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        var thumbnail : ImageView = itemView.findViewById(R.id.home_profile_image2)


    }

}