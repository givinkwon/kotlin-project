package com.example.kotlin.activity.view.Search

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kotlin.activity.view.Feed.FeedRecyclerViewAdapter
import com.example.kotlin.activity.Fragment.Search.CategorydetailActivity
import com.example.kotlin.activity.data.viewmodel.CategoryViewModel
import com.example.kotlin.activity.data.viewmodel.FeedViewModel

import com.example.myapplication.databinding.FragmentSearchBinding
import kotlinx.android.synthetic.main.fragment_search.*
import kotlinx.android.synthetic.main.fragment_search.search_categoryrecyclerView
import kotlinx.android.synthetic.main.recycler_view_search.*


class FragmentSearch: Fragment() {

    // RecyclerView
    // fragment_home.xml 연결 => lateinit => Fragment가 먼저 생성되고 선언될 수 있음 => onCreateView에서 binding 변수를 초기화함.
    private lateinit var binding: FragmentSearchBinding
    private val categoryviewmodel by lazy { ViewModelProvider(this).get(CategoryViewModel::class.java) } // 생명주기 처리 없이 Livedata를 저장하고 있는 ViewModel
    private val feedviewmodel by lazy { ViewModelProvider(this).get(FeedViewModel::class.java) } // 생명주기 처리 없이 Livedata를 저장하고 있는 ViewModel

    private lateinit var adaptercategory: CategoryRecyclerViewAdapter // 홀더에 데이터를 뿌려주는 Adapter
    private lateinit var adaptercategoryfeed: CategoryFeedRecyclerViewAdapter // 홀더에 데이터를 뿌려주는 Adapter

    // ViewPager
    private var numBanner = 3
    private var currentPosition = Int.MAX_VALUE / 2 // 좌우로 무한 스크롤 할 수 있도록 현재 위치를 중간으로 설정
    private val intervalTime = 1500.toLong() // 몇초 간격으로 페이지를 넘길것인지 (1500 = 1.5초)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchBinding.inflate(layoutInflater, container, false)

        return binding.root // binding.root == Frament_home.xml
    }

    // view가 만들어지고 나서
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // RecyclerView 시작
        // adapter init
        adaptercategory = CategoryRecyclerViewAdapter(requireActivity())

        // recyclerview에 연결
        // 1. category_recyclerview
        search_categoryrecyclerView.layoutManager = LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)

        search_categoryrecyclerView.adapter = adaptercategory


        // 2. category_feedrecyclerview
        search_categoryfeedrecyclerView.layoutManager = LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)

        search_categoryfeedrecyclerView.adapter = adaptercategory

        observerCategoryData()

        // RecyclerView 끝

    }



// RecyclerView 시작

    //데이터가 변화되었을 때 자동으로 데이터 가져온 후 변화 알려주기
    fun observerCategoryData(){
        categoryviewmodel.getCategory() // 호출
        categoryviewmodel.Category.observe(requireActivity(), Observer {
            adaptercategory.setListData(it)
            feedviewmodel.getFeed(it.value.toString())
            feedviewmodel.Feed.observe(requireActivity(), Observer {
                adaptercategoryfeed.setListData(it)
            }) //LiveData obs
        }) //LiveData observe

    }



    // RecyclerView 끝

    companion object {

    }
}

