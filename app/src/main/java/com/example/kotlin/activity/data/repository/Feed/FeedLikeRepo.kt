package com.example.kotlin.activity.data.repository.Feed

import android.app.Application
import android.util.Log
import com.example.kotlin.activity.data.dataclass.Feed.Feed
import com.example.kotlin.activity.data.dataclass.Feed.FeedLike
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import io.reactivex.Observable
import io.reactivex.ObservableEmitter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class FeedLikeRepo() {
    var auth = FirebaseAuth.getInstance()
    val user = auth.currentUser
    var firestore = FirebaseFirestore.getInstance()

    fun createdata(FeedLikeData: FeedLike) {
        firestore.collection("FeedLike").document().set(FeedLikeData)
            .addOnSuccessListener {
                Log.w("FeedLike.create", "Success : $it")
            }
            .addOnFailureListener {
                Log.w("FeedLike.create", "Error : $it")
            }
    }

    fun getdata(): Observable<MutableList<FeedLike>> {
        // subscribeOn => observable 객체 만들 때(create, just) io 쓰레드 활용
        // observeon => 이후 계산 및 연산(onNext 등)은 mainthread 확인
        return Observable.create{ emitter: ObservableEmitter<MutableList<FeedLike>> ->
            firestore.collection("FeedLike")
                .addSnapshotListener { value, e ->
                    if (e != null) {
                        Log.w("우옹", "Listen failed.", e)
                        return@addSnapshotListener
                    }

                    var FeedLikeData = mutableListOf<FeedLike>() // 빈 list 선언
                    for (doc in value!!) {

                        val getData = doc.toObject(FeedLike::class.java)
                        FeedLikeData.add(getData)
//                        getData?.let { currentFeedDoc ->
//                            emitter.onNext(currentFeedDoc)
//                        }

                    }
                    FeedLikeData.let { currentFeedLikeData -> emitter.onNext(currentFeedLikeData) }
                }
        }.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
        // 쓰레드 사용 시 emitter type 명시 필요
    }

    fun updatedata(DocId: String, UpdateField : String, UpdateData : String) {
        firestore.collection("FeedLike").document(DocId).update(UpdateField, UpdateData)
            .addOnSuccessListener {
                Log.w("FeedLike.update", "Success : $it")
            }
            .addOnFailureListener {
                Log.w("FeedLike.update", "Error : $it")
            }
    }

    fun deletedata(DocId: String) {
        firestore.collection("FeedLike").document(DocId).delete()
            .addOnSuccessListener {
                Log.w("FeedLike.delete", "Success : $it")
            }
            .addOnFailureListener {
                Log.w("FeedLike.delete", "Error : $it")
            }
    }
}