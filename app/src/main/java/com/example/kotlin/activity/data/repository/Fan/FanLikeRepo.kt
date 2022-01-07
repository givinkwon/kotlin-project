package com.example.kotlin.activity.data.repository.Fan

import android.app.Application
import android.util.Log
import com.example.kotlin.activity.data.dataclass.Fan.FanLike
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import io.reactivex.Observable
import io.reactivex.ObservableEmitter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class FanLikeRepo() {
    var auth = FirebaseAuth.getInstance()
    val user = auth.currentUser
    var firestore = FirebaseFirestore.getInstance()

    fun createdata(FanLikeData: FanLike) {
        firestore.collection("FanLike").document().set(FanLikeData)
            .addOnSuccessListener {
                Log.w("FanLike.create", "Success : $it")
            }
            .addOnFailureListener {
                Log.w("FanLike.create", "Error : $it")
            }
    }

    fun getdata(): Observable<FanLike> {
        // subscribeOn => observable 객체 만들 때(create, just) io 쓰레드 활용
        // observeon => 이후 계산 및 연산(onNext 등)은 mainthread 확인
        return Observable.create{ emitter: ObservableEmitter<FanLike> ->
            firestore.collection("FanLike")
                .addSnapshotListener { value, e ->
                    if (e != null) {
                        Log.w("우옹", "Listen failed.", e)
                        return@addSnapshotListener
                    }

                    for (doc in value!!) {

                        val getData = doc.toObject(FanLike::class.java)
                        getData?.let { currentFanLikeDoc ->
                            emitter.onNext(currentFanLikeDoc)
                        }

                    }
                }
        }.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
        // 쓰레드 사용 시 emitter type 명시 필요
    }

    fun updatedata(DocId: String, UpdateField : String, UpdateData : String) {
        firestore.collection("FanLike").document(DocId).update(UpdateField, UpdateData)
            .addOnSuccessListener {
                Log.w("FanLike.update", "Success : $it")
            }
            .addOnFailureListener {
                Log.w("FanLike.update", "Error : $it")
            }
    }

    fun deletedata(DocId: String) {
        firestore.collection("FanLike").document(DocId).delete()
            .addOnSuccessListener {
                Log.w("FanLike.delete", "Success : $it")
            }
            .addOnFailureListener {
                Log.w("FanLike.delete", "Error : $it")
            }
    }
}