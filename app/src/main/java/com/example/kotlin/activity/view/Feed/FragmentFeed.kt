package com.example.kotlin.activity.view.Feed

import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.example.kotlin.activity.data.viewmodel.FeedViewModel
import com.example.kotlin.activity.view.Feed.FeedFeedRecyclerViewAdapter

import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentFeedBinding
import com.example.myapplication.databinding.FragmentHomeBinding
import kotlinx.android.synthetic.main.fragment_feed.*
import kotlinx.android.synthetic.main.fragment_home.*


class FragmentFeed: Fragment() {

    // RecyclerView
    // fragment_home.xml 연결 => lateinit => Fragment가 먼저 생성되고 선언될 수 있음 => onCreateView에서 binding 변수를 초기화함.
    private lateinit var binding: FragmentFeedBinding
    private val feedviewmodel by lazy { ViewModelProvider(this).get(FeedViewModel::class.java) } // 생명주기 처리 없이 Livedata를 저장하고 있는 ViewModel
    private lateinit var adapterfeed: FeedFeedRecyclerViewAdapter // 홀더에 데이터를 뿌려주는 Adapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFeedBinding.inflate(layoutInflater, container, false)

        return binding.root // binding.root == Frament_feed.xml
    }

    // view가 만들어지고 나서
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // RecyclerView 시작
        // adapter init
        adapterfeed = FeedFeedRecyclerViewAdapter(requireActivity())

        // recyclerview에 연결
        // 1. top_recyclerview
        feed_feed_recyclerView.layoutManager = LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)

        feed_feed_recyclerView.adapter = adapterfeed


        // data ui update
        observerFeedData()
        // RecyclerView 끝


    }

// RecyclerView 시작

    // 데이터가 변화되었을 때 자동으로 데이터 가져온 후 변화 알려주기
    fun observerFeedData(){
        feedviewmodel.getFeed() // 호출
        feedviewmodel.Feed.observe(requireActivity(), Observer {
            adapterfeed.setListData(it)
            Log.d("data", it.toString())
        }) //LiveData observe
    }

    // RecyclerView 끝

    companion object {

    }
}
