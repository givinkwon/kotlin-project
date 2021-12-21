package com.example.kotlin.activity.data.repo

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.kotlin.activity.data.form.User
import com.google.firebase.firestore.DocumentId

import com.google.firebase.firestore.FirebaseFirestore


// 파이어 베이스 데이터 캡슐화!
class RepoFeed {
    fun getData(): LiveData<MutableList<User>> {
        val mutableData = MutableLiveData<MutableList<User>>()
        var listData: MutableList<User> = mutableListOf<User>()
        // 파이어스토어 인스턴스 초기화
        var firestore = FirebaseFirestore.getInstance()

        firestore.collection("User")
            .addSnapshotListener { value, e ->
                if (e != null) {
                    Log.w("우옹", "Listen failed.", e)
                    return@addSnapshotListener
                }

                for (doc in value!!) {
                    val getData = doc.toObject(User::class.java)
                    listData.add(getData!!)
                    Log.d("tag", "${listData}")
                    doc.getString("name")?.let {
//                        listData.add(getData!!)
                    }
                }


                mutableData.value = listData
            }
        return mutableData
    }

    fun getInviteData(): LiveData<MutableList<User>> {
        val mutableData = MutableLiveData<MutableList<User>>()
        var listData: MutableList<User> = mutableListOf<User>()
        // 파이어스토어 인스턴스 초기화
        var firestore = FirebaseFirestore.getInstance()

        firestore.collection("User").whereEqualTo("real_active_state", false)
            .addSnapshotListener { value, e ->
                if (e != null) {
                    Log.w("우옹", "Listen failed.", e)
                    return@addSnapshotListener
                }

                for (doc in value!!) {
                    val getData = doc.toObject(User::class.java)
                    listData.add(getData!!)
                    Log.d("tag", "${listData}")
                    doc.getString("name")?.let {
//                        listData.add(getData!!)
                    }
                }


                mutableData.value = listData
            }
        return mutableData
    }

}