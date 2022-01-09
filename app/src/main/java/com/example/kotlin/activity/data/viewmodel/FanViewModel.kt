package com.example.kotlin.activity.data.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.kotlin.activity.data.dataclass.Fan.Fan
import com.example.kotlin.activity.data.dataclass.Fan.FanImage
import com.example.kotlin.activity.data.dataclass.Fan.FanLike
import com.example.kotlin.activity.data.dataclass.Fan.FanReply
import com.example.kotlin.activity.data.repository.Fan.FanImageRepo
import com.example.kotlin.activity.data.repository.Fan.FanLikeRepo
import com.example.kotlin.activity.data.repository.Fan.FanReplyRepo
import com.example.kotlin.activity.data.repository.Fan.FanRepo

class FanViewModel() : ViewModel() {
    // init
    var Fan = MutableLiveData<Fan>()
    var FanLike = MutableLiveData<FanLike>()
    var FanReply = MutableLiveData<FanReply>()
    var FanImage = MutableLiveData<FanImage>()

    private val FanRepo: FanRepo by lazy { FanRepo() }
    private val FanLikeRepo: FanLikeRepo by lazy { FanLikeRepo() }
    private val FanReplyRepo: FanReplyRepo by lazy { FanReplyRepo() }
    private val FanImageRepo: FanImageRepo by lazy { FanImageRepo() }

    fun getFan() {
        // subscribe
        FanRepo.getdata().subscribe(
            {
                Fan.value = it
            },
            {
                // error
            },
        )

    }

    fun getFanLike() {
        // subscribe
        FanLikeRepo.getdata().subscribe(
            {
                FanLike.value = it
            },
            {
                // error
            },
        )}

    fun getFanReply() {
        // subscribe
        FanReplyRepo.getdata().subscribe(
            {
                FanReply.value = it
            },
            {
                // error
            },
        )}

    fun getFanImage() {
        // subscribe
        FanImageRepo.getdata().subscribe(
            {
                FanImage.value = it
            },
            {
                // error
            },
        )}
}