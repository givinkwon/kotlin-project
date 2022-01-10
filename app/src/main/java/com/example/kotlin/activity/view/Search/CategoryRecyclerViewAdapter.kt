package com.example.kotlin.activity.view.Search

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.kotlin.activity.data.dataclass.Category
import com.example.kotlin.activity.data.viewmodel.FeedViewModel
import com.example.myapplication.R
import java.security.AccessController.getContext

// 1. RecyclerViewAdapter는 view(xml)이 아닌 data model에 따라 정의한다
// 2. 범용성을 확보하기 위해 input parameter로 activity/fragment를 받아 view를 정의할 때 if문으로 재설정
// 3. 모든 데이터를 받아오되, container에 따라 data를 다르게 한다.

class CategoryRecyclerViewAdapter(private val context: Context, Fragment: FragmentSearch): RecyclerView.Adapter<CategoryRecyclerViewAdapter.ViewHolder>() {
    private var categoryList = mutableListOf<Category>()
    private val feedviewmodel by lazy { ViewModelProvider(Fragment).get(FeedViewModel::class.java) } // 생명주기 처리 없이 Livedata를 저장하고 있는 ViewModel
    private val fragment = Fragment
    // data init
    fun setListData(data: Category){
        categoryList.add(data)
        notifyDataSetChanged()
    }

    // 뷰 홀더 만들기 => recycler_view_item.xml과 연결하여 view로 변환
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.recycler_view_search,parent, false)
        return ViewHolder(view)
    }

    // 데이터를 바인딩하여 뷰에 뿌려질 수 있도록
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val category : Category = categoryList[position]
        Log.d("index", position.toString())
        Log.d("data", category.value.toString())
        holder.value.text = category.value

        // sub recyclerview => setadapter
        val mAdapter = CategoryFeedRecyclerViewAdapter(context, position)
        holder.sub.adapter = mAdapter

        // data 호출
        feedviewmodel.getFeed(category.value.toString())
        feedviewmodel.Feed.observe(fragment, Observer {
            // 같을 때만 데이터 추가
            if (it.filter == category.value) {
                mAdapter.setListData(it)
            }
        })
        val layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        holder.sub.layoutManager = layoutManager

    }

    // 화면에 가져올 아이템 개수 세기
    override fun getItemCount(): Int {
        return categoryList.size
    }

    inner class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val value : TextView = itemView.findViewById(R.id.search_value)
        val sub : RecyclerView = itemView.findViewById(R.id.search_categoryfeedrecyclerView)
    }

}