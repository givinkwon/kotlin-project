package com.example.kotlin.activity.data.repository.Feed

import android.app.Application
import android.util.Log
import com.example.kotlin.activity.data.dataclass.Feed.Feed
import com.example.kotlin.activity.data.dataclass.Feed.FeedReply
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import io.reactivex.Observable
import io.reactivex.ObservableEmitter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class FeedReplyRepo() {
    var auth = FirebaseAuth.getInstance()
    val user = auth.currentUser
    var firestore = FirebaseFirestore.getInstance()

    fun createdata(FeedReplyData: FeedReply) {
        firestore.collection("FeedReply").document().set(FeedReplyData)
            .addOnSuccessListener {
                Log.w("FeedReply.create", "Success : $it")
            }
            .addOnFailureListener {
                Log.w("FeedReply.create", "Error : $it")
            }
    }

    fun getdata(): Observable<FeedReply> {
        // subscribeOn => observable 객체 만들 때(create, just) io 쓰레드 활용
        // observeon => 이후 계산 및 연산(onNext 등)은 mainthread 확인
        return Observable.create{ emitter: ObservableEmitter<FeedReply> ->
            firestore.collection("FeedReply")
                .addSnapshotListener { value, e ->
                    if (e != null) {
                        Log.w("우옹", "Listen failed.", e)
                        return@addSnapshotListener
                    }

                    for (doc in value!!) {

                        val getData = doc.toObject(FeedReply::class.java)

                        getData?.let { currentFeedDoc ->
                            emitter.onNext(currentFeedDoc)
                        }

                    }

                }
        }.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
        // 쓰레드 사용 시 emitter type 명시 필요
    }

    fun updatedata(DocId: String, UpdateField : String, UpdateData : String) {
        firestore.collection("FeedReply").document(DocId).update(UpdateField, UpdateData)
            .addOnSuccessListener {
                Log.w("FeedReply.update", "Success : $it")
            }
            .addOnFailureListener {
                Log.w("FeedReply.update", "Error : $it")
            }
    }

    fun deletedata(DocId: String) {
        firestore.collection("FeedReply").document(DocId).delete()
            .addOnSuccessListener {
                Log.w("FeedReply.delete", "Success : $it")
            }
            .addOnFailureListener {
                Log.w("FeedReply.delete", "Error : $it")
            }
    }
}