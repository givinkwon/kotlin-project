package com.example.kotlin.activity.view.Video

import android.os.Bundle
import android.os.Handler
import android.os.Message
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.example.myapplication.R
import kotlinx.android.synthetic.main.fragment_video.*

class FragmentVideo : Fragment() {

    // ViewPager
    private var currentPosition = Int.MAX_VALUE / 2 // 좌우로 무한 스크롤 할 수 있도록 현재 위치를 중간으로 설정

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
        viewPager_video.adapter = VideoViewPagerAdapter(requireContext(),this) // 어댑터 생성
        viewPager_video.orientation = ViewPager2.ORIENTATION_VERTICAL // 방향을 가로로
        with(viewPager_video) {
            viewPager_video.setCurrentItem(currentPosition, false) // 현재 위치를 지정
        }

    }

}