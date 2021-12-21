package com.example.kotlin.activity.Fragment.Mypage.Fragment.Feed

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.kotlin.activity.data.form.Feed.Feed
import com.example.kotlin.activity.data.repo.feed.RepoFeed

class MypageFeedRecyclerViewModel : ViewModel() {
    private val repo = RepoFeed()
    // firebase 데이터 가져와서 설정해주기
    fun fetchData(): LiveData<MutableList<Feed>> {
        val mutableData = MutableLiveData<MutableList<Feed>>()
        repo.getData().observeForever{
            mutableData.value = it
        }
        return mutableData
    }
}