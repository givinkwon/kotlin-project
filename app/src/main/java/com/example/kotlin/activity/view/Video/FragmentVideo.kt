package com.example.kotlin.activity.view.Video

import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.MediaController
import androidx.browser.customtabs.CustomTabsClient.getPackageName
import androidx.viewpager2.widget.ViewPager2
import com.example.kotlin.activity.view.Home.HomeViewPagerAdapter
import com.example.myapplication.R
import com.google.common.reflect.Reflection.getPackageName
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_video.*

class FragmentVideo : Fragment() {

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
        // Inflate the layouts for this fragment
        return inflater.inflate(R.layout.fragment_video, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // viewPager
        viewPager_video.adapter = VideoViewPagerAdapter(getItemList(), requireContext()) // 어댑터 생성
        viewPager_video.orientation = ViewPager2.ORIENTATION_VERTICAL // 방향을 가로로
        with(viewPager_video) {
            viewPager_video.setCurrentItem(currentPosition, false) // 현재 위치를 지정
        }

        val VIDEO_PATH = "android.resource://" + requireActivity().packageName + "/" + R.raw.video
        // ViewPager 시작

        var video = Uri.parse(VIDEO_PATH)
        video_test.setVideoURI(video)
        video_test.setMediaController(MediaController(requireContext()))     // 없으면 에러
        video_test.requestFocus()    // 준비하는 과정을 미리함
        video_test.setOnPreparedListener {
            video_test.start()       // 동영상 재개
        }
    }


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
                viewPager_video.setCurrentItem(++currentPosition, true) // 다음 페이지로 이동
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
}