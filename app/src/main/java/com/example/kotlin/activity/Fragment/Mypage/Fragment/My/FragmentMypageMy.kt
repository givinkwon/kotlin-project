package com.example.kotlin.activity.Fragment.Mypage.Fragment.My

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kotlin.activity.Fragment.Feed.FeedRecyclerViewAdapter
import com.example.kotlin.activity.data.viewmodel.FeedViewModel
import com.example.myapplication.databinding.FragmentMypageMyBinding
import kotlinx.android.synthetic.main.fragment_mypage_my.*

class FragmentMypageMy : Fragment() {
    // RecyclerView
    // fragment_home.xml 연결 => lateinit => Fragment가 먼저 생성되고 선언될 수 있음 => onCreateView에서 binding 변수를 초기화함.
    private lateinit var binding: FragmentMypageMyBinding
    private val feedviewmodel by lazy { ViewModelProvider(this).get(FeedViewModel::class.java) } // 생명주기 처리 없이 Livedata를 저장하고 있는 ViewModel
    private lateinit var adapterfeed: FeedRecyclerViewAdapter // 홀더에 데이터를 뿌려주는 Adapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMypageMyBinding.inflate(layoutInflater, container, false)

        return binding.root // binding.root == Frament_home.xml

    }

    // view가 만들어지고 나서
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // RecyclerView 시작
        // adapter init
        adapterfeed = FeedRecyclerViewAdapter(requireActivity())

        // recyclerview에 연결
        // 1. mypage_my_friend_recyclerview
        mypage_my_friend_recyclerView.layoutManager = LinearLayoutManager(requireActivity(), LinearLayoutManager.HORIZONTAL, false)

        mypage_my_friend_recyclerView.adapter = adapterfeed
        observerFeedData()

        // 2. higher_people_recyclerview
        mypage_my_higher_people_recyclerView.layoutManager = LinearLayoutManager(requireActivity(), LinearLayoutManager.HORIZONTAL, false)

        mypage_my_higher_people_recyclerView.adapter = adapterfeed


        // 3. lower_people_recyclerview
        mypage_my_lower_people_recyclerView.layoutManager = LinearLayoutManager(requireActivity(), LinearLayoutManager.HORIZONTAL, false)

        mypage_my_lower_people_recyclerView.adapter = adapterfeed



        // RecyclerView 끝


    }

// RecyclerView 시작

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

    // RecyclerView 끝

    companion object {

    }
}
