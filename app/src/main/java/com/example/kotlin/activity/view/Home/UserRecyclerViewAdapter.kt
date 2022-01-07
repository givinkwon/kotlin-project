package com.example.kotlin.activity.view.Home

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.kotlin.activity.data.dataclass.User
import com.example.myapplication.R

// 1. RecyclerViewAdapter는 view(xml)이 아닌 data model에 따라 정의한다
// 2. 범용성을 확보하기 위해 input parameter로 activity/fragment를 받아 view를 정의할 때 if문으로 재설정
// 3. 모든 데이터를 받아오되, container에 따라 data를 다르게 한다.

class UserRecyclerViewAdapter(private val context: Context): RecyclerView.Adapter<UserRecyclerViewAdapter.ViewHolder>() {
    private var userList = mutableListOf<User>()

    // data init
    fun setListData(data: User){
        Log.d("user_adapter", data.toString())
        userList.add(data)
        Log.d("user_adapter", data.toString())
        Log.d("user_adapter", userList.toString())
        notifyDataSetChanged()
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
            .load(user.profileimage)
            .fitCenter()
            .into(holder.profile_image)
        holder.value.text = user.value


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