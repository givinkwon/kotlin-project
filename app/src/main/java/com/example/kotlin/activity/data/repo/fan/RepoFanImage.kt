package com.example.kotlin.activity.data.repo.fan

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.kotlin.activity.data.form.Feed.FanImage
import com.example.kotlin.activity.data.form.Feed.Feed
import com.example.kotlin.activity.data.form.Feed.FeedImage

import com.google.firebase.firestore.FirebaseFirestore


// 파이어 베이스 데이터 캡슐화!
class RepoFanImage {
    fun getData(): LiveData<MutableList<FanImage>> {
        val mutableData = MutableLiveData<MutableList<FanImage>>()
        val listData: MutableList<FanImage> = mutableListOf<FanImage>()
        // 파이어스토어 인스턴스 초기화
        var firestore = FirebaseFirestore.getInstance()

        firestore.collection("FanImage")
            .addSnapshotListener { value, e ->
                if (e != null) {
                    Log.w("우옹", "Listen failed.", e)
                    return@addSnapshotListener
                }

                for (doc in value!!) {
                    val getData = doc.toObject(FanImage::class.java)

                    listData.add(getData!!)
                    doc.getString("name")?.let {
//                        listData.add(getData!!)
                    }
                }
                mutableData.value = listData
            }
        return mutableData
    }

}