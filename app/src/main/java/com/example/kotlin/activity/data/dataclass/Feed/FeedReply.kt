package com.example.kotlin.activity.data.dataclass.Feed

import com.example.kotlin.activity.data.dataclass.time

class FeedReply (
    val feedid:String = "글_피드ID",
    val user:String = "댓글 쓴 사람",
    val content: String = "댓글 내용",
    val createdAt: java.util.Date = time.getTime() as java.util.Date,
)