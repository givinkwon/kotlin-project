package com.example.kotlin.activity.data.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.kotlin.activity.data.repository.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import io.reactivex.Observable

class UserViewModel : ViewModel() {
    private lateinit var auth: FirebaseAuth

    fun getData(): Observable<User> {
        auth = FirebaseAuth.getInstance()
        val user = auth.currentUser
        var firestore = FirebaseFirestore.getInstance()


        return Observable.create { emitter ->
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
        }
    }

    fun getCurrentUserData(): Observable<User> {
        auth = FirebaseAuth.getInstance()
        val user = auth.currentUser
        var firestore = FirebaseFirestore.getInstance()


        return Observable.create { emitter ->
            firestore.collection("User").whereEqualTo("email", user?.email!!)
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
        }
    }
}