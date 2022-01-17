package com.example.kotlin.activity.view.Mypage.Feed

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.kotlin.activity.data.viewmodel.FeedViewModel
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentMypageFeedBinding
import kotlinx.android.synthetic.main.fragment_mypage_feed.*

class FragmentMypageFeed : Fragment() {
    // RecyclerView
    // fragment_home.xml 연결 => lateinit => Fragment가 먼저 생성되고 선언될 수 있음 => onCreateView에서 binding 변수를 초기화함.
    private lateinit var binding: FragmentMypageFeedBinding
    private val feedviewmodel by lazy { ViewModelProvider(this).get(FeedViewModel::class.java) } // 생명주기 처리 없이 Livedata를 저장하고 있는 ViewModel
    private lateinit var adapterfeed: MypageFeedRecyclerViewAdapter // 홀더에 데이터를 뿌려주는 Adapter



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        feedviewmodel.getFeed() // 호출
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layouts for this fragment
        return inflater.inflate(R.layout.fragment_mypage_feed, container, false)
    }

    // view가 만들어지고 나서
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // RecyclerView 시작
        // adapter init
        adapterfeed = MypageFeedRecyclerViewAdapter(requireActivity())

        // recyclerview에 연결 => GridLayout
        mypage_feed_recyclerView.adapter = adapterfeed

        mypage_feed_recyclerView.layoutManager = GridLayoutManager(requireActivity(), 3)

        // data ui update
        observerFeedData()
        // RecyclerView 끝


    }

    // RecyclerView 시작

    // 데이터가 변화되었을 때 자동으로 데이터 가져온 후 변화 알려주기
    fun observerFeedData(){
        feedviewmodel.Feed.observe(requireActivity(), Observer {
            adapterfeed.setListData(it)
            Log.d("data", it.toString())
        }) //LiveData observe
    }

    // RecyclerView 끝

    companion object {

    }
}
