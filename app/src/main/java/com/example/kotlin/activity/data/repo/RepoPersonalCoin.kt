package com.example.kotlin.activity.data.repo

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.kotlin.activity.data.form.PersonalCoin
import com.example.kotlin.activity.data.form.User

import com.google.firebase.firestore.FirebaseFirestore


// 파이어 베이스 데이터 캡슐화!
class RepoPersonalCoin {
    fun getData(): LiveData<MutableList<PersonalCoin>> {
        val mutableData = MutableLiveData<MutableList<PersonalCoin>>()
        val listData: MutableList<PersonalCoin> = mutableListOf<PersonalCoin>()
        // 파이어스토어 인스턴스 초기화
        var firestore = FirebaseFirestore.getInstance()

        firestore.collection("PersonalCoin")
            .addSnapshotListener { value, e ->
                if (e != null) {
                    Log.w("우옹", "Listen failed.", e)
                    return@addSnapshotListener
                }

                for (doc in value!!) {
                    val getData = doc.toObject(PersonalCoin::class.java)
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