package com.example.kotlin.activity.data.dataclass

class ValueHistory (
    val value:String = "현재 가치",
    val user:String = "소유자",
    val date: java.util.Date = time.getTime() as java.util.Date,
)