package com.example.kotlin.activity.data.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.example.kotlin.activity.data.dataclass.Feed.Feed
import com.example.kotlin.activity.data.dataclass.Feed.FeedImage
import com.example.kotlin.activity.data.dataclass.Feed.FeedLike
import com.example.kotlin.activity.data.dataclass.Feed.FeedReply
import com.example.kotlin.activity.data.repository.Feed.FeedImageRepo
import com.example.kotlin.activity.data.repository.Feed.FeedLikeRepo
import com.example.kotlin.activity.data.repository.Feed.FeedReplyRepo
import com.example.kotlin.activity.data.repository.Feed.FeedRepo
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import io.reactivex.Observable

class FeedViewModel() : ViewModel() {
      // init
      var Feed = MutableLiveData<Feed>()
      var FeedLike = MutableLiveData<MutableList<FeedLike>>()
      var FeedReply = MutableLiveData<MutableList<FeedReply>>()
      var FeedImage = MutableLiveData<MutableList<FeedImage>>()

      private val FeedRepo: FeedRepo by lazy { FeedRepo() }
      private val FeedLikeRepo: FeedLikeRepo by lazy { FeedLikeRepo() }
      private val FeedReplyRepo: FeedReplyRepo by lazy { FeedReplyRepo() }
      private val FeedImageRepo: FeedImageRepo by lazy { FeedImageRepo() }

      fun getFeed() {
          // subscribe
          FeedRepo.getdata().subscribe(
              {
                  Feed.value = it
              },
              {
                  // error
              },
          )

      }

    fun getFeedLike() {
        // subscribe
        FeedLikeRepo.getdata().subscribe(
            {
                FeedLike.value = it
            },
            {
                // error
            },
        )}

    fun getFeedReply() {
        // subscribe
        FeedReplyRepo.getdata().subscribe(
            {
                FeedReply.value = it
            },
            {
                // error
            },
        )}

    fun getFeedImage() {
        // subscribe
        FeedImageRepo.getdata().subscribe(
            {
                FeedImage.value = it
            },
            {
                // error
            },
        )}
}