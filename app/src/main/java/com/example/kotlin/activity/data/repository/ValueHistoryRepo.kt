package com.example.kotlin.activity.data.repository

import android.app.Application
import android.util.Log
import com.example.kotlin.activity.data.dataclass.ValueHistory
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import io.reactivex.Observable
import io.reactivex.ObservableEmitter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ValueHistoryRepo() {
    var auth = FirebaseAuth.getInstance()
    val user = auth.currentUser
    var firestore = FirebaseFirestore.getInstance()

    fun createdata(ValueHistoryData: ValueHistory) {
        firestore.collection("ValueHistory").document().set(ValueHistoryData)
            .addOnSuccessListener {
                Log.w("ValueHistory.create", "Success : $it")
            }
            .addOnFailureListener {
                Log.w("ValueHistory.create", "Error : $it")
            }
    }

    fun getdata(): Observable<ValueHistory> {
        // subscribeOn => observable 객체 만들 때(create, just) io 쓰레드 활용
        // observeon => 이후 계산 및 연산(onNext 등)은 mainthread 확인
        return Observable.create{ emitter: ObservableEmitter<ValueHistory> ->
            firestore.collection("ValueHistory")
                .addSnapshotListener { value, e ->
                    if (e != null) {
                        Log.w("우옹", "Listen failed.", e)
                        return@addSnapshotListener
                    }

                    for (doc in value!!) {

                        val getData = doc.toObject(ValueHistory::class.java)
                        getData?.let { currentValueHistoryDoc ->
                            emitter.onNext(currentValueHistoryDoc)
                        }

                    }
                }
        }.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
        // 쓰레드 사용 시 emitter type 명시 필요
    }

    fun updatedata(DocId: String, UpdateField : String, UpdateData : String) {
        firestore.collection("ValueHistory").document(DocId).update(UpdateField, UpdateData)
            .addOnSuccessListener {
                Log.w("ValueHistory.update", "Success : $it")
            }
            .addOnFailureListener {
                Log.w("ValueHistory.update", "Error : $it")
            }
    }

    fun deletedata(DocId: String) {
        firestore.collection("ValueHistory").document(DocId).delete()
            .addOnSuccessListener {
                Log.w("ValueHistory.delete", "Success : $it")
            }
            .addOnFailureListener {
                Log.w("ValueHistory.delete", "Error : $it")
            }
    }
}