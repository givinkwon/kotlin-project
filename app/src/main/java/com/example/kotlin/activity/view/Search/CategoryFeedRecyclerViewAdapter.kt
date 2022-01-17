package com.example.kotlin.activity.view.Search

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.kotlin.activity.data.dataclass.Feed
import com.example.kotlin.activity.view.HomeActivity
import com.example.myapplication.R

// 1. RecyclerViewAdapter는 view(xml)이 아닌 data model에 따라 정의한다
// 2. 범용성을 확보하기 위해 input parameter로 activity/fragment를 받아 view를 정의할 때 if문으로 재설정
// 3. 모든 데이터를 받아오되, container에 따라 data를 다르게 한다.

class CategoryFeedRecyclerViewAdapter(private val context: Context, Postion: Int): RecyclerView.Adapter<CategoryFeedRecyclerViewAdapter.ViewHolder>() {
    private var feedList = mutableListOf<Feed>()

    // data init
    fun setListData(data: Feed){
        Log.d("feeddata", data.toString())
        feedList.add(data)
        notifyDataSetChanged()
    }

    // 뷰 홀더 만들기 => recycler_view_item.xml과 연결하여 view로 변환
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.recycler_view_search_feed,parent, false)

        view.setOnClickListener {
            val intent = Intent(context, HomeActivity::class.java) // Home으로 이동 후 fragment 전환
            intent.putExtra("transfer", true)
            //FLAG_ACTIVITY_NEW_TASK : 새로운 TASK를 생성합니다.
            //FLAG_ACTIVITY_CLEAR_TASK : 현재 TASK를 비웁니다.
            //FLAG_ACTIVITY_CLAER_TOP : 호출하려는 액티비티가 이미 스택에 쌓여있을 때, 새로 인스턴스를 생성하지 않고 기존의 액티비티를 포그라운드로 가져옵니다. 그리고 액티비티스택의 최상단부터 포그라운드로 가져올 액티비티까지의 모든 액티비티를 삭제합니다.
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
        val feedimage : ImageView = itemView.findViewById(R.id.search_feed_image)

    }

}