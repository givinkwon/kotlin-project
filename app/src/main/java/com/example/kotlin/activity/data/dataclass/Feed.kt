package com.example.kotlin.activity.data.dataclass

import com.example.kotlin.activity.data.dataclass.time

class Feed (
    val content:String = "내용",
    val createdAt: java.util.Date = time.getTime() as java.util.Date,
    val user: String? = null,
    val nickname: String? = null,
    val profileimage: String? = null,
    val thumbnail: String? = null,
    val filter: String? = null,

    val image: ArrayList<String>? = null,
    val like : ArrayList<String>? = null,
    val reply: ArrayList<Map<String, Any>>? = null,
)