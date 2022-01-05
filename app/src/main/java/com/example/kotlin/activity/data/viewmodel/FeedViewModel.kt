package com.example.kotlin.activity.data.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.kotlin.activity.data.repository.Feed.Feed
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import io.reactivex.Observable

class FeedViewModel : ViewModel() {
    private lateinit var auth: FirebaseAuth

    fun getData(): Observable<Feed> {
        auth = FirebaseAuth.getInstance()
        val user = auth.currentUser
        var firestore = FirebaseFirestore.getInstance()

        return Observable.create { emitter ->
            firestore.collection("Feed")
                .addSnapshotListener { value, e ->
                    if (e != null) {
                        Log.w("우옹", "Listen failed.", e)
                        return@addSnapshotListener
                    }

                    for (doc in value!!) {

                        val getData = doc.toObject(Feed::class.java)
                        getData?.let { currentFeedDoc ->
                            emitter.onNext(currentFeedDoc)
                        }

                    }
                }
        }
    }
}