package com.example.kotlin.activity.view.Mypage.My

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kotlin.activity.data.viewmodel.FeedViewModel
import com.example.kotlin.activity.data.viewmodel.UserViewModel
import com.example.myapplication.databinding.FragmentMypageMyBinding
import kotlinx.android.synthetic.main.fragment_mypage_my.*

class FragmentMypageMy : Fragment() {
    // RecyclerView
    // fragment_home.xml 연결 => lateinit => Fragment가 먼저 생성되고 선언될 수 있음 => onCreateView에서 binding 변수를 초기화함.
    private lateinit var binding: FragmentMypageMyBinding
    private val userviewmodel by lazy { ViewModelProvider(this).get(UserViewModel::class.java) } // 생명주기 처리 없이 Livedata를 저장하고 있는 ViewModel
    private val feedviewmodel by lazy { ViewModelProvider(this).get(FeedViewModel::class.java) } // 생명주기 처리 없이 Livedata를 저장하고 있는 ViewModel
    private lateinit var adapteruser: MypageMyUserRecyclerViewAdapter // 홀더에 데이터를 뿌려주는 Adapter
    private lateinit var adapterfeed: MypageMyFeedRecyclerViewAdapter // 홀더에 데이터를 뿌려주는 Adapter

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
        adapterfeed = MypageMyFeedRecyclerViewAdapter(requireActivity())

        // recyclerview에 연결
        // 1. mypage_my_friend_recyclerview
        mypage_my_friend_recyclerView.layoutManager = LinearLayoutManager(requireActivity(), LinearLayoutManager.HORIZONTAL, false)

        mypage_my_friend_recyclerView.adapter = adapterfeed


        // 2. higher_people_recyclerview
        mypage_my_higher_people_recyclerView.layoutManager = LinearLayoutManager(requireActivity(), LinearLayoutManager.HORIZONTAL, false)

        mypage_my_higher_people_recyclerView.adapter = adapterfeed


        // 3. lower_people_recyclerview
        mypage_my_lower_people_recyclerView.layoutManager = LinearLayoutManager(requireActivity(), LinearLayoutManager.HORIZONTAL, false)

        mypage_my_lower_people_recyclerView.adapter = adapterfeed

        observerFeedData()

        // RecyclerView 끝


    }

// RecyclerView 시작

    // 데이터가 변화되었을 때 자동으로 데이터 가져온 후 변화 알려주기
    fun observerUserData(){
        userviewmodel.getUser() // 호출
        userviewmodel.User.observe(requireActivity(), Observer {
            adapteruser.setListData(it)
            Log.d("data", it.email!!)
        }) //LiveData observe
    }

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
