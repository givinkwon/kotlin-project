package com.example.kotlin.activity.data.form.Fan

import com.example.kotlin.activity.data.form.time

class Fan (
    val content:String = "내용",
    val title:String = "제목",
    val createdAt: java.util.Date = time.getTime() as java.util.Date,
)