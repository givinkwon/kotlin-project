package com.example.myapplication.activity

import android.app.Activity
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kotlin.activity.Fragment.Home.FeedRecyclerViewAdapter
import com.example.kotlin.activity.Fragment.Home.RecyclerViewModel
import com.example.kotlin.activity.Fragment.Home.UserRecyclerViewAdapter
import com.example.kotlin.activity.invite.InviteRecyclerViewAdapter
import com.example.kotlin.activity.invite.InviteRecyclerViewModel
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentHomeBinding
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home.top_recyclerView
import kotlinx.android.synthetic.main.invite.*

class InviteActivity : AppCompatActivity() {

    private val viewmodel by lazy { ViewModelProvider(this).get(InviteRecyclerViewModel::class.java) } // 생명주기 처리 없이 Livedata를 저장하고 있는 ViewModel
    private lateinit var adapterinvite: InviteRecyclerViewAdapter // 홀더에 데이터를 뿌려주는 Adapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.invite)

        adapterinvite = InviteRecyclerViewAdapter(this)

        // recycleview 연결
        celebrity_recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        celebrity_recyclerView.adapter = adapterinvite

        observerInviteData()
    }


    // 데이터가 변화되었을 때 자동으로 데이터 가져온 후 변화 알려주기
    fun observerInviteData(){
        viewmodel.fetchData().observe(this, Observer {
            adapterinvite.setListData(it)
            // 변화 알려주기
            adapterinvite.notifyDataSetChanged()
        })
    }
}