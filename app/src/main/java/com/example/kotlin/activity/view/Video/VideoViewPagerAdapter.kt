package com.example.kotlin.activity.view.Video

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.MediaController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.kotlin.activity.data.dataclass.Feed
import com.example.kotlin.activity.view.Home.HomeViewPagerAdapter
import com.example.kotlin.activity.view.Video.VideoViewPagerAdapter
import com.example.myapplication.R
import com.example.myapplication.activity.InviteActivity
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.SimpleExoPlayer
import kotlinx.android.synthetic.main.item_home_banner.view.*
import kotlinx.android.synthetic.main.viewpager_video.view.*

class VideoViewPagerAdapter(itemList: ArrayList<Int>, private val context: Context) : RecyclerView.Adapter<VideoViewPagerAdapter.PagerViewHolder>() {
    private var feedList = mutableListOf<Feed>()
    private var player = ExoPlayer.Builder(context).build()

    // data init
    fun setListData(data: Feed){
        feedList.add(data)
        notifyDataSetChanged()
    }

    // 생성자
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = PagerViewHolder((parent)) // viewholder

    // 페이지 넘길 때마다 뷰홀더에 데이터 연결
    override fun onBindViewHolder(holder: PagerViewHolder, position: Int) {
//        val feed : Feed = feedList[position]

        // Build video
//        val mediaItem: MediaItem = MediaItem.fromUri()

//        holder.video.player = player
//
//        // set meida
//        player.setMediaItem(mediaItem)
//        // Prepare the player
//        player.prepare()
//        // Start video
//        player.play()


        // 상세 보기 클릭 시 새로운 페이지로 이동하게 하게
//        holder.itemView.setOnClickListener{
//
//            val intent = Intent(context, InviteActivity::class.java)
//            context.startActivity(intent)
//
//        }

    }

    override fun getItemCount():  Int = Int.MAX_VALUE	// 무한 스크롤을 위해 무한으로

    inner class PagerViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder
        (LayoutInflater.from(parent.context).inflate(R.layout.fragment_video, parent, false)){

//        val video = itemView.video_video
        val like = itemView.video_like
        val reply = itemView.video_reply
        val share = itemView.video_share
        val more = itemView.video_more
        val id = itemView.video_id
        val content = itemView.video_content
    }
}