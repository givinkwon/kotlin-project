package com.example.kotlin.activity.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.kotlin.activity.view.Feed.FragmentFeed
import com.example.kotlin.activity.view.Search.FragmentSearch
import com.example.kotlin.activity.view.Mypage.FragmentMypage

import com.example.kotlin.activity.view.Write.FragmentWrite
import com.example.kotlin.activity.view.Home.FragmentHome
import com.example.myapplication.R
import kotlinx.android.synthetic.main.home.*

class HomeActivity : AppCompatActivity() {

    private val fragmentHome by lazy { FragmentHome() }
    private val fragmentSearch by lazy { FragmentSearch() }
    private val fragmentWrite by lazy { FragmentWrite() }
    private val fragmentFeed by lazy { FragmentFeed() }
    private val fragmentMYpage by lazy { FragmentMypage() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home)

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
            .add(R.id.bottom_frame, fragmentWrite)
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
            .hide(fragmentWrite)
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


    }

    private fun initNavigationBar() {
        bottom_button.run {
            setOnNavigationItemSelectedListener {
                when(it.itemId) {
                    R.id.menu_home -> {
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
                            .hide(fragmentWrite)
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
                            .hide(fragmentWrite)
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
                    R.id.menu_write -> {
                        // 보여주기
                        supportFragmentManager
                            .beginTransaction()
                            .show(fragmentWrite)
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
                    R.id.menu_group -> {
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
                            .hide(fragmentWrite)
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
                            .hide(fragmentWrite)
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
        // replace or hide는 원래 fragment를 삭제하므로 보존되지 않음
        when(fragment.id) {
            R.id.menu_home -> {
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
                    .hide(fragmentWrite)
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
                    .hide(fragmentWrite)
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
            R.id.menu_write -> {
                // 보여주기
                supportFragmentManager
                    .beginTransaction()
                    .show(fragmentWrite)
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
            R.id.menu_group -> {
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
                    .hide(fragmentWrite)
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
                    .hide(fragmentWrite)
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
    }
}