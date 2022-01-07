package com.example.kotlin.activity.data.repository.Feed

import android.app.Application
import android.util.Log
import com.example.kotlin.activity.data.dataclass.Feed.Feed
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import io.reactivex.Observable
import io.reactivex.ObservableEmitter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class FeedRepo (){
    var auth = FirebaseAuth.getInstance()
    val user = auth.currentUser
    var firestore = FirebaseFirestore.getInstance()

    fun createdata(FeedData: Feed) {
        firestore.collection("Feed").document().set(FeedData)
            .addOnSuccessListener {
                Log.w("Feed.create", "Success : $it")
            }
            .addOnFailureListener {
                Log.w("Feed.create", "Error : $it")
            }
    }

    fun getdata(): Observable<Feed> {
        // subscribeOn => observable 객체 만들 때(create, just) io 쓰레드 활용
        // observeon => 이후 계산 및 연산(onNext 등)은 mainthread 확인
        return Observable.create{ emitter: ObservableEmitter<Feed> ->
            firestore.collection("Feed")
                .addSnapshotListener { value, e ->
                    if (e != null) {
                        Log.w("우옹", "Listen failed.", e)
                        return@addSnapshotListener
                    }

                    var FeedData = mutableListOf<Feed>() // 빈 list 선언
                    for (doc in value!!) {

                        val getData = doc.toObject(Feed::class.java)
//                        FeedData.add(getData)
                        getData?.let { currentFeedDoc ->
                            emitter.onNext(currentFeedDoc)
                        }

                    }
//                    FeedData.let { currentFeedData -> emitter.onNext(currentFeedData) }
                }
        }.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
        // 쓰레드 사용 시 emitter type 명시 필요
    }

    fun updatedata(DocId: String, UpdateField : String, UpdateData : String) {
        firestore.collection("Feed").document(DocId).update(UpdateField, UpdateData)
            .addOnSuccessListener {
                Log.w("Feed.update", "Success : $it")
            }
            .addOnFailureListener {
                Log.w("Feed.update", "Error : $it")
            }
    }

    fun deletedata(DocId: String) {
        firestore.collection("Feed").document(DocId).delete()
            .addOnSuccessListener {
                Log.w("Feed.delete", "Success : $it")
            }
            .addOnFailureListener {
                Log.w("Feed.delete", "Error : $it")
            }
    }
}