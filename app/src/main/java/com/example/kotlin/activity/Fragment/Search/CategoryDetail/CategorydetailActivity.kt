package com.example.kotlin.activity.Fragment.Search

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kotlin.activity.Fragment.Search.CategoryDetail.CategoryDetailRecyclerViewAdapter
import com.example.kotlin.activity.Fragment.Search.CategoryDetail.CategoryDetailRecyclerViewModel
import com.example.myapplication.R
import com.example.myapplication.databinding.CategoryDetailBinding
import kotlinx.android.synthetic.main.category_detail.*


class CategorydetailActivity: AppCompatActivity() {

    // RecyclerView
    // fragment_home.xml 연결 => lateinit => Fragment가 먼저 생성되고 선언될 수 있음 => onCreateView에서 binding 변수를 초기화함.
    private lateinit var binding: CategoryDetailBinding
    private val viewmodel by lazy { ViewModelProvider(this).get(CategoryDetailRecyclerViewModel::class.java) } // 생명주기 처리 없이 Livedata를 저장하고 있는 ViewModel
    private lateinit var adapter: CategoryDetailRecyclerViewAdapter // 홀더에 데이터를 뿌려주는 Adapter

    // ViewPager
    private var numBanner = 3
    private var currentPosition = Int.MAX_VALUE / 2 // 좌우로 무한 스크롤 할 수 있도록 현재 위치를 중간으로 설정
    private val intervalTime = 1500.toLong() // 몇초 간격으로 페이지를 넘길것인지 (1500 = 1.5초)

    // actionbar의 포함되는 메뉴의 작동
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item?.itemId){
            android.R.id.home -> {
                finish()
                return true
            }
            else -> {
                return super.onOptionsItemSelected(item)
            }
        }
        return super.onOptionsItemSelected(item)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // RecyclerView 시작
        setContentView(R.layout.category_detail)

        // adapter init
        adapter = CategoryDetailRecyclerViewAdapter(this)

        // recyclerview에 연결
        category_detail_recyclerView1.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        category_detail_recyclerView1.adapter = adapter
        observerData()

        setSupportActionBar(category_detail_toolbar) // toolbar를 액션바로 이용
        supportActionBar?.setDisplayHomeAsUpEnabled(true) // 뒤로가기
        supportActionBar?.setDisplayShowTitleEnabled(false) // 테마에 있는 제목 지우기
        category_detail_toolbar.title = "카테고리" // 제목 새롭게 설정하기

    }




// RecyclerView 시작

    // 데이터가 변화되었을 때 자동으로 데이터 가져온 후 변화 알려주기
    fun observerData(){
        viewmodel.fetchData().observe(this, Observer {
            adapter.setListData(it)
            // 변화 알려주기
            adapter.notifyDataSetChanged()
        })
    }

    // RecyclerView 끝

    companion object {

    }
}

