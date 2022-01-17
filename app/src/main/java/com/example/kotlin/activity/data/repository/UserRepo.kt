package com.example.kotlin.activity.data.repository

import android.app.Application
import android.util.Log
import com.example.kotlin.activity.data.dataclass.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import io.reactivex.Observable
import io.reactivex.ObservableEmitter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class UserRepo() {
    var auth = FirebaseAuth.getInstance()
    val user = auth.currentUser
    var firestore = FirebaseFirestore.getInstance()

    fun createdata(UserData: User) {
        firestore.collection("User").document().set(UserData)
            .addOnSuccessListener {
                Log.w("User.create", "Success : $it")
            }
            .addOnFailureListener {
                Log.w("User.create", "Error : $it")
            }
    }

    // 현재 user email 가져오기
    fun getcurrentdata(): Observable<User> {
        // subscribeOn => observable 객체 만들 때(create, just) io 쓰레드 활용
        // observeon => 이후 계산 및 연산(onNext 등)은 mainthread 확인
        return Observable.create{ emitter: ObservableEmitter<User> ->
            firestore.collection("User").whereEqualTo("email", auth.currentUser?.email)
                .addSnapshotListener { value, e ->
                    if (e != null) {
                        Log.w("우옹", "Listen failed.", e)
                        return@addSnapshotListener
                    }

                    for (doc in value!!) {

                        val getData = doc.toObject(User::class.java)
                        getData?.let { currentUserDoc ->
                            emitter.onNext(currentUserDoc)
                        }

                    }
                }
        }.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
        // 쓰레드 사용 시 emitter type 명시 필요
    }

    fun getdata(): Observable<User> {
        // subscribeOn => observable 객체 만들 때(create, just) io 쓰레드 활용
        // observeon => 이후 계산 및 연산(onNext 등)은 mainthread 확인
        return Observable.create{ emitter: ObservableEmitter<User> ->
            firestore.collection("User")
                .addSnapshotListener { value, e ->
                    if (e != null) {
                        Log.w("우옹", "Listen failed.", e)
                        return@addSnapshotListener
                    }

                    for (doc in value!!) {

                        val getData = doc.toObject(User::class.java)
                        getData?.let { currentUserDoc ->
                            emitter.onNext(currentUserDoc)
                        }

                    }
                }
        }.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
        // 쓰레드 사용 시 emitter type 명시 필요
    }

    fun updatedata(DocId: String, UpdateField : String, UpdateData : String) {
        firestore.collection("User").document(DocId).update(UpdateField, UpdateData)
            .addOnSuccessListener {
                Log.w("User.update", "Success : $it")
            }
            .addOnFailureListener {
                Log.w("User.update", "Error : $it")
            }
    }

    fun deletedata(DocId: String) {
        firestore.collection("User").document(DocId).delete()
            .addOnSuccessListener {
                Log.w("User.delete", "Success : $it")
            }
            .addOnFailureListener {
                Log.w("User.delete", "Error : $it")
            }
    }
}