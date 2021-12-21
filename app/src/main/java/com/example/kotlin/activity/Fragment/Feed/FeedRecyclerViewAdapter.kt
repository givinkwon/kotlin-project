package com.example.kotlin.activity.Fragment.Feed

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.kotlin.activity.data.form.User
import com.example.myapplication.R
import kotlinx.android.synthetic.main.fragment_mypage.*
import kotlinx.android.synthetic.main.signup.*


class FeedRecyclerViewAdapter(private val context: Context): RecyclerView.Adapter<FeedRecyclerViewAdapter.ViewHolder>() {
    private var feedList = mutableListOf<User>()

    // data init
    fun setListData(data:MutableList<User>){
        feedList = data
    }

    // 뷰 홀더 만들기 => recycler_view_item.xml과 연결하여 view로 변환
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.recycler_view_user,parent, false)
        return ViewHolder(view)
    }

    // 데이터를 바인딩하여 뷰에 뿌려질 수 있도록
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val user : User = userList[position]
        Log.d("NO..", "${user.username}")
        holder.name.text = user.username
        Glide.with(context)
            .load(user.recent_content_image)
            .fitCenter()
            .into(holder.profile_image)
        holder.value.text = user.value

        // 내용 숨기기 => 클릭 시 보이게 하기
        showHide(holder.name)
        showHide(holder.value)

    }

    // 화면에 가져올 아이템 개수 세기
    override fun getItemCount(): Int {
        return userList.size
    }

    inner class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val name : TextView = itemView.findViewById(R.id.home_name)
        val profile_image : ImageView = itemView.findViewById(R.id.home_profile_image)
        val value : TextView = itemView.findViewById(R.id.home_value)

    }

}