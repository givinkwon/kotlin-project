package com.example.kotlin.activity.data.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

import com.example.kotlin.activity.data.dataclass.User
import com.example.kotlin.activity.data.repository.UserRepo


class UserViewModel : ViewModel() {
    // init
    var User = MutableLiveData<User>()

    private val UserRepo: UserRepo by lazy { UserRepo() }

    fun getUser() {
        // subscribe
        UserRepo.getdata().subscribe(
            {
                User.value = it
                Log.d("user", it.toString())
            },
            {
                // error
            },
        )

    }

//    fun getData(): Observable<User> {
//        auth = FirebaseAuth.getInstance()
//        val user = auth.currentUser
//        var firestore = FirebaseFirestore.getInstance()
//
//
//        return Observable.create { emitter ->
//            firestore.collection("User")
//                .addSnapshotListener { value, e ->
//                    if (e != null) {
//                        Log.w("우옹", "Listen failed.", e)
//                        return@addSnapshotListener
//                    }
//
//                    for (doc in value!!) {
//
//                        val getData = doc.toObject(User::class.java)
//                        getData?.let { currentUserDoc ->
//                            emitter.onNext(currentUserDoc)
//                        }
//
//                    }
//                }
//        }
//    }
//
//    fun getCurrentUserData(): Observable<User> {
//        auth = FirebaseAuth.getInstance()
//        val user = auth.currentUser
//        var firestore = FirebaseFirestore.getInstance()
//
//
//        return Observable.create { emitter ->
//            firestore.collection("User").whereEqualTo("email", user?.email!!)
//                .addSnapshotListener { value, e ->
//                    if (e != null) {
//                        Log.w("우옹", "Listen failed.", e)
//                        return@addSnapshotListener
//                    }
//
//                    for (doc in value!!) {
//
//                        val getData = doc.toObject(User::class.java)
//                        getData?.let { currentUserDoc ->
//                            emitter.onNext(currentUserDoc)
//                        }
//
//                    }
//                }
//        }
//    }
}