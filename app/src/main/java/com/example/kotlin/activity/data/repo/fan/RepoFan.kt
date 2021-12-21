package com.example.kotlin.activity.data.repo.fan

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.kotlin.activity.data.form.Fan.Fan

import com.google.firebase.firestore.FirebaseFirestore


// 파이어 베이스 데이터 캡슐화!
class RepoFan {
    fun getData(): LiveData<MutableList<Fan>> {
        val mutableData = MutableLiveData<MutableList<Fan>>()
        val listData: MutableList<Fan> = mutableListOf<Fan>()
        // 파이어스토어 인스턴스 초기화
        var firestore = FirebaseFirestore.getInstance()

        firestore.collection("Fan")
            .addSnapshotListener { value, e ->
                if (e != null) {
                    Log.w("우옹", "Listen failed.", e)
                    return@addSnapshotListener
                }

                for (doc in value!!) {
                    val getData = doc.toObject(Fan::class.java)
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