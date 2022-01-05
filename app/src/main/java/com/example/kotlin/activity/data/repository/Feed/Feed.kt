package com.example.kotlin.activity.data.repository.Feed

import com.example.kotlin.activity.data.repository.time

class Feed (
    val content:String = "내용",
    val title:String = "제목",
    val createdAt: java.util.Date = time.getTime() as java.util.Date,
    val user: String? = null,
    val thumbnail: String? = null,
)