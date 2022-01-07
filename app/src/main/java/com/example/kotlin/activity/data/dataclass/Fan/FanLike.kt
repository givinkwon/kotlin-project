package com.example.kotlin.activity.data.dataclass.Fan

import com.example.kotlin.activity.data.dataclass.time

class FanLike (
    val fanid:String = "글_팬ID",
    val user:String = "좋아요한 사람",
    val createdAt: java.util.Date = time.getTime() as java.util.Date,
)