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
import android.content.res.ColorStateList




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
        bottom_button.visibility = View.GONE
        bottom_transparency_button.visibility = View.VISIBLE

        bottom_transparency_button.setBackgroundColor(Color.WHITE)
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

            val colors = intArrayOf(
                Color.GRAY,
                Color.WHITE
            )

            val states = arrayOf(
                intArrayOf(android.R.attr.state_enabled, -android.R.attr.state_checked),
                intArrayOf(android.R.attr.state_enabled, android.R.attr.state_checked)
            )
            bottom_transparency_button.itemTextColor = ColorStateList(states, colors)
            bottom_transparency_button.itemIconTintList = ColorStateList(states, colors)
            bottom_transparency_button.setBackgroundColor(Color.TRANSPARENT)
        }
    }

    private fun initNavigationBar() {

        bottom_transparency_button.run {
            setOnNavigationItemSelectedListener {
                when(it.itemId) {
                    R.id.menu_home -> {
                        bottom_transparency_button.setBackgroundColor(Color.WHITE)
                        // icon 및 text 색 변경
                        val colors = intArrayOf(
                            Color.GRAY,
                            Color.BLUE
                        )

                        val states = arrayOf(
                            intArrayOf(android.R.attr.state_enabled, -android.R.attr.state_checked),
                            intArrayOf(android.R.attr.state_enabled, android.R.attr.state_checked)
                        )
                        bottom_transparency_button.itemTextColor = ColorStateList(states, colors)
                        bottom_transparency_button.itemIconTintList = ColorStateList(states, colors)

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
                        bottom_transparency_button.setBackgroundColor(Color.WHITE)
                        // icon 및 text 색 변경
                        val colors = intArrayOf(
                            Color.GRAY,
                            Color.BLUE
                        )

                        val states = arrayOf(
                            intArrayOf(android.R.attr.state_enabled, -android.R.attr.state_checked),
                            intArrayOf(android.R.attr.state_enabled, android.R.attr.state_checked)
                        )
                        bottom_transparency_button.itemTextColor = ColorStateList(states, colors)
                        bottom_transparency_button.itemIconTintList = ColorStateList(states, colors)
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
                        bottom_transparency_button.setBackgroundColor(Color.TRANSPARENT)
                        // icon 및 text 색 변경
                        val colors = intArrayOf(
                            Color.GRAY,
                            Color.WHITE
                        )

                        val states = arrayOf(
                            intArrayOf(android.R.attr.state_enabled, -android.R.attr.state_checked),
                            intArrayOf(android.R.attr.state_enabled, android.R.attr.state_checked)
                        )
                        bottom_transparency_button.itemTextColor = ColorStateList(states, colors)
                        bottom_transparency_button.itemIconTintList = ColorStateList(states, colors)

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
                        bottom_transparency_button.setBackgroundColor(Color.WHITE)
                        // icon 및 text 색 변경
                        val colors = intArrayOf(
                            Color.GRAY,
                            Color.BLUE
                        )

                        val states = arrayOf(
                            intArrayOf(android.R.attr.state_enabled, -android.R.attr.state_checked),
                            intArrayOf(android.R.attr.state_enabled, android.R.attr.state_checked)
                        )
                        bottom_transparency_button.itemTextColor = ColorStateList(states, colors)
                        bottom_transparency_button.itemIconTintList = ColorStateList(states, colors)

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
                        bottom_transparency_button.setBackgroundColor(Color.WHITE)
                        // icon 및 text 색 변경
                        val colors = intArrayOf(
                            Color.GRAY,
                            Color.BLUE
                        )

                        val states = arrayOf(
                            intArrayOf(android.R.attr.state_enabled, -android.R.attr.state_checked),
                            intArrayOf(android.R.attr.state_enabled, android.R.attr.state_checked)
                        )
                        bottom_transparency_button.itemTextColor = ColorStateList(states, colors)
                        bottom_transparency_button.itemIconTintList = ColorStateList(states, colors)

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

            //
            bottom_transparency_button.selectedItemId = R.id.menu_video
        }


}