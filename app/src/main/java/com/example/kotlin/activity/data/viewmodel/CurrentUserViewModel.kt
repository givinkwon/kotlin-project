package com.example.kotlin.activity.data.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.kotlin.activity.data.form.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import com.squareup.okhttp.internal.DiskLruCache
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

class CurrentUserViewModel : ViewModel() {
    private lateinit var auth: FirebaseAuth

    fun getData(): Single<User> {
        auth = FirebaseAuth.getInstance()
        val user = auth.currentUser
        var firestore = FirebaseFirestore.getInstance()


        return Single.create { emitter ->
            firestore.collection("User").whereEqualTo("email", user?.email!!)
                .addSnapshotListener { value, e ->
                    if (e != null) {
                        Log.w("우옹", "Listen failed.", e)
                        return@addSnapshotListener
                    }

                    for (doc in value!!) {

                        val getData = doc.toObject(User::class.java)
                        getData?.let { currentUserDoc ->
                            emitter.onSuccess(currentUserDoc)
                        }

                    }
                }
        }
    }
}