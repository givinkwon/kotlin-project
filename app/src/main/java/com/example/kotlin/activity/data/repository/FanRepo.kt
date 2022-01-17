package com.example.kotlin.activity.data.repository

import android.util.Log
import com.example.kotlin.activity.data.dataclass.Fan
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import io.reactivex.Observable
import io.reactivex.ObservableEmitter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class FanRepo() {
    var auth = FirebaseAuth.getInstance()
    val user = auth.currentUser
    var firestore = FirebaseFirestore.getInstance()

    fun createdata(FanData: Fan) {
        firestore.collection("Fan").document().set(FanData)
            .addOnSuccessListener {
                Log.w("Fan.create", "Success : $it")
            }
            .addOnFailureListener {
                Log.w("Fan.create", "Error : $it")
            }
    }

    fun getdata(): Observable<Fan> {
        // subscribeOn => observable 객체 만들 때(create, just) io 쓰레드 활용
        // observeon => 이후 계산 및 연산(onNext 등)은 mainthread 확인
        return Observable.create{ emitter: ObservableEmitter<Fan> ->
            firestore.collection("Fan")
                .addSnapshotListener { value, e ->
                    if (e != null) {
                        Log.w("우옹", "Listen failed.", e)
                        return@addSnapshotListener
                    }

                    for (doc in value!!) {

                        val getData = doc.toObject(Fan::class.java)
                        getData?.let { currentFanDoc ->
                            emitter.onNext(currentFanDoc)
                        }

                    }
                }
        }.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
        // 쓰레드 사용 시 emitter type 명시 필요
    }

    fun updatedata(DocId: String, UpdateField : String, UpdateData : String) {
        firestore.collection("Fan").document(DocId).update(UpdateField, UpdateData)
            .addOnSuccessListener {
                Log.w("Fan.update", "Success : $it")
            }
            .addOnFailureListener {
                Log.w("Fan.update", "Error : $it")
            }
    }

    fun deletedata(DocId: String) {
        firestore.collection("Fan").document(DocId).delete()
            .addOnSuccessListener {
                Log.w("Fan.delete", "Success : $it")
            }
            .addOnFailureListener {
                Log.w("Fan.delete", "Error : $it")
            }
    }
}