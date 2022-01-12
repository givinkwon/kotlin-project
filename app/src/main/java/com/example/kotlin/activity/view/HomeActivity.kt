package com.example.kotlin.activity.view

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.kotlin.activity.view.Feed.FragmentFeed
import com.example.kotlin.activity.view.Search.FragmentSearch
import com.example.kotlin.activity.view.Mypage.FragmentMypage

import com.example.kotlin.activity.view.Video.FragmentVideo
import com.example.kotlin.activity.view.Home.FragmentHome
import com.example.myapplication.R
import kotlinx.android.synthetic.main.fragment_video.*
import kotlinx.android.synthetic.main.home.*

class HomeActivity : AppCompatActivity() {

    private val fragmentHome by lazy { FragmentHome() }
    private val fragmentSearch by lazy { FragmentSearch() }
    private val fragmentVideo by lazy { FragmentVideo() }
    private val fragmentFeed by lazy { FragmentFeed() }
    private val fragmentMYpage by lazy { FragmentMypage() }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.home)
        // 메뉴 보이기 조절
        bottom_button.isEnabled = false


            // fragment 모두 추가
            supportFragmentManager
                .beginTransaction()
                .add(R.id.bottom_frame, fragmentHome)
                .commit()
            supportFragmentManager
                .beginTransaction()
                .add(R.id.bottom_frame, fragmentSearch)
                .commit()
            supportFragmentManager
                .beginTransaction()
                .add(R.id.bottom_frame, fragmentVideo)
                .commit()
            supportFragmentManager
                .beginTransaction()
                .add(R.id.bottom_frame, fragmentMYpage)
                .commit()

            // HOME만 보여주기
            supportFragmentManager
                .beginTransaction()
                .hide(fragmentSearch)
                .commit()
            supportFragmentManager
                .beginTransaction()
                .hide(fragmentVideo)
                .commit()
            supportFragmentManager
                .beginTransaction()
                .hide(fragmentFeed)
                .commit()
            supportFragmentManager
                .beginTransaction()
                .hide(fragmentMYpage)
                .commit()

            initNavigationBar()

        // intent data 가져오기
        if(intent.hasExtra("transfer")) { //해당 키값을 가진 intent가 정보를 가지고 있다면 실행
            changeFragment(fragmentVideo)
            // 메뉴 보이기 조절
            bottom_button.isEnabled = true
        }
    }

    private fun initNavigationBar() {
        bottom_button.run {
            setOnNavigationItemSelectedListener {
                when(it.itemId) {
                    R.id.menu_home -> {
                        // 메뉴 보이기 조절
                        bottom_button.isEnabled = false
                        // 보여주기
                        supportFragmentManager
                            .beginTransaction()
                            .show(fragmentHome)
                            .commit()
                        // 숨기기
                        supportFragmentManager
                            .beginTransaction()
                            .hide(fragmentSearch)
                            .commit()
                        supportFragmentManager
                            .beginTransaction()
                            .hide(fragmentVideo)
                            .commit()
                        supportFragmentManager
                            .beginTransaction()
                            .hide(fragmentFeed)
                            .commit()
                        supportFragmentManager
                            .beginTransaction()
                            .hide(fragmentMYpage)
                            .commit()
                    }
                    R.id.menu_search -> {
                        // 메뉴 보이기 조절
                        bottom_button.isEnabled = false
                        // 보여주기
                        supportFragmentManager
                            .beginTransaction()
                            .show(fragmentSearch)
                            .commit()
                        // 숨기기
                        supportFragmentManager
                            .beginTransaction()
                            .hide(fragmentHome)
                            .commit()
                        supportFragmentManager
                            .beginTransaction()
                            .hide(fragmentVideo)
                            .commit()
                        supportFragmentManager
                            .beginTransaction()
                            .hide(fragmentFeed)
                            .commit()
                        supportFragmentManager
                            .beginTransaction()
                            .hide(fragmentMYpage)
                            .commit()
                    }
                    R.id.menu_video -> {
                        // 메뉴 보이기 조절
                        bottom_button.isEnabled = true
                        // 보여주기
                        supportFragmentManager
                            .beginTransaction()
                            .show(fragmentVideo)
                            .commit()
                        // 숨기기
                        supportFragmentManager
                            .beginTransaction()
                            .hide(fragmentSearch)
                            .commit()
                        supportFragmentManager
                            .beginTransaction()
                            .hide(fragmentHome)
                            .commit()
                        supportFragmentManager
                            .beginTransaction()
                            .hide(fragmentFeed)
                            .commit()
                        supportFragmentManager
                            .beginTransaction()
                            .hide(fragmentMYpage)
                            .commit()
                    }
                    R.id.menu_feed -> {
                        // 메뉴 보이기 조절
                        bottom_button.isEnabled = false
                        // 보여주기
                        supportFragmentManager
                            .beginTransaction()
                            .show(fragmentFeed)
                            .commit()
                        // 숨기기
                        supportFragmentManager
                            .beginTransaction()
                            .hide(fragmentSearch)
                            .commit()
                        supportFragmentManager
                            .beginTransaction()
                            .hide(fragmentVideo)
                            .commit()
                        supportFragmentManager
                            .beginTransaction()
                            .hide(fragmentHome)
                            .commit()
                        supportFragmentManager
                            .beginTransaction()
                            .hide(fragmentMYpage)
                            .commit()
                    }
                    R.id.menu_mypage -> {
                        // 메뉴 보이기 조절
                        bottom_button.isEnabled = false
                        // 보여주기
                        supportFragmentManager
                            .beginTransaction()
                            .show(fragmentMYpage)
                            .commit()
                        // 숨기기
                        supportFragmentManager
                            .beginTransaction()
                            .hide(fragmentSearch)
                            .commit()
                        supportFragmentManager
                            .beginTransaction()
                            .hide(fragmentVideo)
                            .commit()
                        supportFragmentManager
                            .beginTransaction()
                            .hide(fragmentFeed)
                            .commit()
                        supportFragmentManager
                            .beginTransaction()
                            .hide(fragmentHome)
                            .commit()
                    }

                }
                true
            }
            selectedItemId = R.id.menu_home
        }

    }

    private fun changeFragment(fragment: Fragment) {

            // 보여주기
            supportFragmentManager
                .beginTransaction()
                .show(fragmentVideo)
                .commit()
            // 숨기기
            supportFragmentManager
                .beginTransaction()
                .hide(fragmentSearch)
                .commit()
            supportFragmentManager
                .beginTransaction()
                .hide(fragmentHome)
                .commit()
            supportFragmentManager
                .beginTransaction()
                .hide(fragmentFeed)
                .commit()
            supportFragmentManager
                .beginTransaction()
                .hide(fragmentMYpage)
                .commit()
        }


}