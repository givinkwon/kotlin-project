package com.example.kotlin.activity.view.Search.Detail

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.kotlin.activity.data.dataclass.Feed.Feed
import com.example.kotlin.activity.view.HomeActivity
import com.example.kotlin.activity.view.Video.FragmentVideo
import com.example.myapplication.R


class SearchDetailRecyclerViewAdapter(private val context: Context): RecyclerView.Adapter<SearchDetailRecyclerViewAdapter.ViewHolder>() {
    private var feedList = mutableListOf<Feed>()

    // data init
    fun setListData(data: Feed){
        feedList.add(data)
        notifyDataSetChanged()
    }

    // 뷰 홀더 만들기 => recycler_view_item.xml과 연결하여 view로 변환
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.recycler_view_search_detail,parent, false)

        view.setOnClickListener {
            val intent = Intent(context, HomeActivity::class.java) // Home으로 이동 후 fragment 전환
            intent.putExtra("transfer", true)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP //액티비티 스택제거
            context.startActivity(intent)

        }

        return ViewHolder(view)
    }

    // 데이터를 바인딩하여 뷰에 뿌려질 수 있도록
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val feed : Feed = feedList[position]
        Glide.with(context)
            .load(feed.thumbnail)
            .fitCenter()
            .into(holder.feedimage)


    }

    // 화면에 가져올 아이템 개수 세기
    override fun getItemCount(): Int {
        return feedList.size
    }

    inner class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val feedimage : ImageView = itemView.findViewById(R.id.search_detail_image)


    }

}