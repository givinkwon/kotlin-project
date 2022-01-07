package com.example.kotlin.activity.data.dataclass
import java.util.*

val time = Calendar.getInstance()

class User(
    val email:String? = null,
    val personal_coin_id:String? = null,
    val real_active_state:Boolean? = null,
    val recent_content_image:String? = null,
    val username:String? = null,
    val value:String? = null,
    val profileimage:String? = null,
    val createdAt: java.util.Date? = null,
)