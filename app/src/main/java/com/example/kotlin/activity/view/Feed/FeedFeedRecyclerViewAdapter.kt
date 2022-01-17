package com.example.kotlin.activity.view.Feed

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.kotlin.activity.data.dataclass.Feed
import com.example.myapplication.R


class FeedFeedRecyclerViewAdapter(private val context: Context): RecyclerView.Adapter<FeedFeedRecyclerViewAdapter.ViewHolder>() {
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
    fun setListData(data: Feed){
        feedList.add(data)
        notifyDataSetChanged()
    }

    // 뷰 홀더 만들기 => recycler_view_item.xml과 연결하여 view로 변환
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.recycler_view_feed_feed,parent, false)
        return ViewHolder(view)
    }

    // 데이터를 바인딩하여 뷰에 뿌려질 수 있도록
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val feed : Feed = feedList[position]

        holder.content.text = feed.content
        holder.nickname.text = feed.nickname

        // feed thumbnail
        Glide.with(context)
            .load(feed.thumbnail)
            .placeholder(R.drawable.ic_launcher_background)
            .fitCenter()
            .into(holder.image)

        // profileimage
        Glide.with(context)
            .load(feed.profileimage)
            .into(holder.profileimage)


    }

    // 화면에 가져올 아이템 개수 세기
    override fun getItemCount(): Int {
        return feedList.size
    }

    inner class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        var profileimage : ImageView = itemView.findViewById(R.id.feed_profile_image)
        var nickname : TextView = itemView.findViewById(R.id.feed_nickname)
        var image : ImageView = itemView.findViewById(R.id.feed_image)
        var content : TextView = itemView.findViewById(R.id.feed_content)

    }

}