package com.example.kotlin.activity.view.Video

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlin.activity.data.dataclass.Feed
import com.example.myapplication.R
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.ui.PlayerView


class VideoViewPagerAdapter(private val context: Context, Fragment: FragmentVideo) : RecyclerView.Adapter<VideoViewPagerAdapter.PagerViewHolder>() {
    private var feedList = mutableListOf<Feed>()
    // Build video
    lateinit var player : ExoPlayer
    // ViewPager 시작
    val VIDEO_PATH = "android.resource://" + "com.example.kotlin" + "/" + R.raw.video
    var videourl = Uri.parse(VIDEO_PATH)

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

        player = ExoPlayer.Builder(context).build()
        var mediaItem = MediaItem.fromUri(videourl)

        holder.video.setPlayer(player)

        // set meida
        player.setMediaItem(mediaItem)

        // Prepare the player
        player.prepare()
        // Start video
        player.play()

        // 상세 보기 클릭 시 새로운 페이지로 이동하게 하게
//        holder.itemView.setOnClickListener{
//
//            val intent = Intent(context, InviteActivity::class.java)
//            context.startActivity(intent)
//
//        }

    }

    override fun getItemCount():  Int = Int.MAX_VALUE	// 무한 스크롤을 위해 무한으로

    // 페이지가 떨어질 떄
    override fun onViewDetachedFromWindow(holder: PagerViewHolder) {
        super.onViewDetachedFromWindow(holder)
        player.pause()
        player.release()
    }


    inner class PagerViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder
        (LayoutInflater.from(parent.context).inflate(R.layout.viewpager_video, parent, false)){

        val video : PlayerView = itemView.findViewById(R.id.video_video)
        val id : TextView = itemView.findViewById(R.id.video_id)
        val content : TextView = itemView.findViewById(R.id.video_content)
    }
}