package com.example.kotlin.activity.data.dataclass

import com.example.kotlin.activity.data.dataclass.time

class Fan (
    val content:String = "내용",
    val title:String = "제목",
    val createdAt: java.util.Date = time.getTime() as java.util.Date,

    val image: ArrayList<String>? = null,
    val like : ArrayList<String>? = null,
    val reply: ArrayList<Map<String, Any>>? = null,
)