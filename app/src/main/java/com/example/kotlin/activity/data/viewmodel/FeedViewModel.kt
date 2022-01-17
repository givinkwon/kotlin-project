package com.example.kotlin.activity.data.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.kotlin.activity.data.dataclass.Feed
import com.example.kotlin.activity.data.repository.FeedRepo

class FeedViewModel() : ViewModel() {
      // init
      var Feed = MutableLiveData<Feed>()

      private val FeedRepo: FeedRepo by lazy { FeedRepo() }


     var SelectedCategory = MutableLiveData<String>()

      fun getFeed(filter: String = "") {
          // subscribe
          FeedRepo.getdata(filter).subscribe(
              {
                  Feed.value = it
              },
              {
                  // error
              },
          )

      }

}