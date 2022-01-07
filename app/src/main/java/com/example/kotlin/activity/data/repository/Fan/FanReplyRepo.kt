package com.example.kotlin.activity.data.repository.Fan

import android.app.Application
import android.util.Log
import com.example.kotlin.activity.data.dataclass.Fan.FanReply
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import io.reactivex.Observable
import io.reactivex.ObservableEmitter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class FanReplyRepo() {
    var auth = FirebaseAuth.getInstance()
    val user = auth.currentUser
    var firestore = FirebaseFirestore.getInstance()

    fun createdata(FanReplyData: FanReply) {
        firestore.collection("FanReply").document().set(FanReplyData)
            .addOnSuccessListener {
                Log.w("FanReply.create", "Success : $it")
            }
            .addOnFailureListener {
                Log.w("FanReply.create", "Error : $it")
            }
    }

    fun getdata(): Observable<FanReply> {
        // subscribeOn => observable 객체 만들 때(create, just) io 쓰레드 활용
        // observeon => 이후 계산 및 연산(onNext 등)은 mainthread 확인
        return Observable.create{ emitter: ObservableEmitter<FanReply> ->
            firestore.collection("FanReply")
                .addSnapshotListener { value, e ->
                    if (e != null) {
                        Log.w("우옹", "Listen failed.", e)
                        return@addSnapshotListener
                    }

                    for (doc in value!!) {

                        val getData = doc.toObject(FanReply::class.java)
                        getData?.let { currentFanReplyDoc ->
                            emitter.onNext(currentFanReplyDoc)
                        }

                    }
                }
        }.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
        // 쓰레드 사용 시 emitter type 명시 필요
    }

    fun updatedata(DocId: String, UpdateField : String, UpdateData : String) {
        firestore.collection("FanReply").document(DocId).update(UpdateField, UpdateData)
            .addOnSuccessListener {
                Log.w("FanReply.update", "Success : $it")
            }
            .addOnFailureListener {
                Log.w("FanReply.update", "Error : $it")
            }
    }

    fun deletedata(DocId: String) {
        firestore.collection("FanReply").document(DocId).delete()
            .addOnSuccessListener {
                Log.w("FanReply.delete", "Success : $it")
            }
            .addOnFailureListener {
                Log.w("FanReply.delete", "Error : $it")
            }
    }
}