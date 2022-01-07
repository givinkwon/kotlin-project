package com.example.kotlin.activity.data.repository

import android.app.Application
import android.util.Log
import com.example.kotlin.activity.data.dataclass.PersonalCoin
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import io.reactivex.Observable
import io.reactivex.ObservableEmitter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class PersonalCoinRepo() {
    var auth = FirebaseAuth.getInstance()
    val user = auth.currentUser
    var firestore = FirebaseFirestore.getInstance()

    fun createdata(PersonalCoinData: PersonalCoin) {
        firestore.collection("PersonalCoin").document().set(PersonalCoinData)
            .addOnSuccessListener {
                Log.w("PersonalCoin.create", "Success : $it")
            }
            .addOnFailureListener {
                Log.w("PersonalCoin.create", "Error : $it")
            }
    }

    fun getdata(): Observable<PersonalCoin> {
        // subscribeOn => observable 객체 만들 때(create, just) io 쓰레드 활용
        // observeon => 이후 계산 및 연산(onNext 등)은 mainthread 확인
        return Observable.create{ emitter: ObservableEmitter<PersonalCoin> ->
            firestore.collection("PersonalCoin")
                .addSnapshotListener { value, e ->
                    if (e != null) {
                        Log.w("우옹", "Listen failed.", e)
                        return@addSnapshotListener
                    }

                    for (doc in value!!) {

                        val getData = doc.toObject(PersonalCoin::class.java)
                        getData?.let { currentPersonalCoinDoc ->
                            emitter.onNext(currentPersonalCoinDoc)
                        }

                    }
                }
        }.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
        // 쓰레드 사용 시 emitter type 명시 필요
    }

    fun updatedata(DocId: String, UpdateField : String, UpdateData : String) {
        firestore.collection("PersonalCoin").document(DocId).update(UpdateField, UpdateData)
            .addOnSuccessListener {
                Log.w("PersonalCoin.update", "Success : $it")
            }
            .addOnFailureListener {
                Log.w("PersonalCoin.update", "Error : $it")
            }
    }

    fun deletedata(DocId: String) {
        firestore.collection("PersonalCoin").document(DocId).delete()
            .addOnSuccessListener {
                Log.w("PersonalCoin.delete", "Success : $it")
            }
            .addOnFailureListener {
                Log.w("PersonalCoin.delete", "Error : $it")
            }
    }
}