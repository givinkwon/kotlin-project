package com.example.myapplication.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kotlin.activity.data.recyclerviewadapter.FeedRecyclerViewAdapter
import com.example.kotlin.activity.data.viewmodel.FeedViewModel
import com.example.myapplication.R
import kotlinx.android.synthetic.main.invite.*

class InviteActivity : AppCompatActivity() {

    private val feedviewmodel by lazy { ViewModelProvider(this).get(FeedViewModel::class.java) } // 생명주기 처리 없이 Livedata를 저장하고 있는 ViewModel
    private lateinit var adapterfeed: FeedRecyclerViewAdapter // 홀더에 데이터를 뿌려주는 Adapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.invite)

        adapterfeed = FeedRecyclerViewAdapter(this)

        // recycleview 연결
        celebrity_recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        celebrity_recyclerView.adapter = adapterfeed

        observerFeedData()
    }


    // 데이터가 변화되었을 때 자동으로 데이터 가져온 후 변화 알려주기
    fun observerFeedData(){
        feedviewmodel.getData().subscribe(
            {
                adapterfeed.setListData(it)
            },
            {
                // error
            },
        )
    }
}