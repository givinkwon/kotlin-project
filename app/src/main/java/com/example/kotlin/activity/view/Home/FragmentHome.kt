package com.example.kotlin.activity.view.Home

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
import com.example.kotlin.activity.view.Feed.FeedRecyclerViewAdapter
import com.example.kotlin.activity.data.viewmodel.FeedViewModel
import com.example.kotlin.activity.data.viewmodel.UserViewModel

import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentHomeBinding
import kotlinx.android.synthetic.main.fragment_home.*


class FragmentHome: Fragment() {

    // RecyclerView
    // fragment_home.xml 연결 => lateinit => Fragment가 먼저 생성되고 선언될 수 있음 => onCreateView에서 binding 변수를 초기화함.
    private lateinit var binding: FragmentHomeBinding
    private val userviewmodel by lazy { ViewModelProvider(this).get(UserViewModel::class.java) } // 생명주기 처리 없이 Livedata를 저장하고 있는 ViewModel
    private val feedviewmodel by lazy { ViewModelProvider(this).get(FeedViewModel::class.java) } // 생명주기 처리 없이 Livedata를 저장하고 있는 ViewModel
    private lateinit var adapteruser: UserRecyclerViewAdapter // 홀더에 데이터를 뿌려주는 Adapter
    private lateinit var adapterfeed: FeedRecyclerViewAdapter // 홀더에 데이터를 뿌려주는 Adapter


    // ViewPager
    private var numBanner = 3
    private var currentPosition = Int.MAX_VALUE / 2 // 좌우로 무한 스크롤 할 수 있도록 현재 위치를 중간으로 설정
    private var myHandler = MyHandler()
    private val intervalTime = 1500.toLong() // 몇초 간격으로 페이지를 넘길것인지 (1500 = 1.5초)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)

        return binding.root // binding.root == Frament_home.xml
    }

    // view가 만들어지고 나서
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // RecyclerView 시작
        // adapter init
        adapterfeed = FeedRecyclerViewAdapter(requireActivity())
        adapteruser = UserRecyclerViewAdapter(requireActivity())

        // recyclerview에 연결
        // 1. top_recyclerview
        top_recyclerView.layoutManager = LinearLayoutManager(requireActivity(), LinearLayoutManager.HORIZONTAL, false)

        top_recyclerView.adapter = adapteruser


        // 2. higher_people_recyclerview
        higher_people_recyclerView.layoutManager = LinearLayoutManager(requireActivity(), LinearLayoutManager.HORIZONTAL, false)

        higher_people_recyclerView.adapter = adapterfeed

        // 3. lower_people_recyclerview
        lower_people_recyclerView.layoutManager = LinearLayoutManager(requireActivity(), LinearLayoutManager.HORIZONTAL, false)

        lower_people_recyclerView.adapter = adapterfeed


        // 4. my_people_recyclerview
        my_people_recyclerView.layoutManager = LinearLayoutManager(requireActivity(), LinearLayoutManager.HORIZONTAL, false)

        my_people_recyclerView.adapter = adapteruser

        // data ui update
        observerUserData()
        observerFeedData()
        // RecyclerView 끝

        // viewPager
        viewPager_invite.adapter = ViewPagerAdapter(getItemList(), requireContext()) // 어댑터 생성
        viewPager_invite.orientation = ViewPager2.ORIENTATION_HORIZONTAL // 방향을 가로로
        with(viewPager_invite) {
            viewPager_invite.setCurrentItem(currentPosition, false) // 현재 위치를 지정

        }


        // 스크롤 자동 이동
        viewPager_invite.apply {
            registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                }

                // 뷰 페이저의 변화를 확인하고 변화 시 확인
                override fun onPageScrollStateChanged(state: Int) {
                    super.onPageScrollStateChanged(state)
                    when (state) {
                        // 뷰페이저에서 손 떼었을때 / 뷰페이저 멈춰있을 때
                        ViewPager2.SCROLL_STATE_IDLE -> autoScrollStart(intervalTime)
                        // 뷰페이저 움직이는 중
                        ViewPager2.SCROLL_STATE_DRAGGING -> autoScrollStop()
                    }
                }
            })
        }
    }

// RecyclerView 시작

//    // 데이터가 변화되었을 때 자동으로 데이터 가져온 후 변화 알려주기
    fun observerUserData(){
    userviewmodel.getUser()
    userviewmodel.User.observe(requireActivity(), Observer {
        adapteruser.setListData(it)
        Log.d("data", it.email!!)
    })
    }

    fun observerFeedData(){
        feedviewmodel.getFeed()
        feedviewmodel.Feed.observe(requireActivity(), Observer {
            adapterfeed.setListData(it)
        })
    }

    // RecyclerView 끝

    // ViewPager 시작

    // 핸들러를 제작해줌.
    private fun autoScrollStart(intervalTime: Long) {
        myHandler.removeMessages(0) // 이거 안하면 핸들러가 1개, 2개, 3개 ... n개 만큼 계속 늘어남
        myHandler.sendEmptyMessageDelayed(0, intervalTime) // intervalTime 만큼 반복해서 핸들러를 실행하게 함
    }

    // 핸들러 중지
   private fun autoScrollStop(){
        myHandler.removeMessages(0) // 핸들러를 중지시킴
    }

    private inner class MyHandler : Handler() {
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)

            if(msg.what == 0) {
                viewPager_invite.setCurrentItem(++currentPosition, true) // 다음 페이지로 이동
                autoScrollStart(intervalTime) // 스크롤을 계속 이어서 한다.
            }
        }
    }

    // 다른 페이지 갔다가 돌아오면 다시 스크롤 시작
    override fun onResume() {
        super.onResume()
        autoScrollStart(intervalTime)
    }

    // 다른 페이지로 떠나있는 동안 스크롤이 동작할 필요는 없음. 정지
    override fun onPause() {
        super.onPause()
        autoScrollStop()
    }

    // 뷰 페이저에 들어갈 아이템
    private fun getItemList(): ArrayList<Int> {
        return arrayListOf<Int>(R.drawable.logo1, R.drawable.logo2, R.drawable.logo3)
    }

    // ViewPager 끝

    companion object {

    }
}
