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

        initNavigationBar()
    }

    private fun initNavigationBar() {
        bottom_button.run {
            setOnNavigationItemSelectedListener {
                when(it.itemId) {
                    R.id.menu_home -> {
                        changeFragment(fragmentHome)
                    }
                    R.id.menu_search -> {
                        changeFragment(fragmentSearch)
                    }
                    R.id.menu_write -> {
                        changeFragment(fragmentWrite)
                    }
                    R.id.menu_group -> {
                        changeFragment(fragmentFeed)
                    }
                    R.id.menu_mypage -> {
                        changeFragment(fragmentMYpage)
                    }
                }
                true
            }
            selectedItemId = R.id.menu_home
        }
    }

    private fun changeFragment(fragment: Fragment) {

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.bottom_frame, fragment)
            .commit()
    }
}