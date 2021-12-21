package com.example.kotlin.activity.data.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.kotlin.activity.data.form.time
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import io.grpc.util.AdvancedTlsX509TrustManager

class LoginViewModel : ViewModel() {
    // private로 은닉
    private var email = MutableLiveData<String>() // 이메일
    private var password = MutableLiveData<String>() // 비밀번호
    private var user = MutableLiveData<FirebaseUser>() // user data

    // 외부 클래스에서 접근

    // 이메일 시작
    val Email : LiveData<String>
        get() = email
    // 초기값 설정
    init {
        email.value = ""
    }

    fun getUpdateEmail(text : String){
        email.value = text
    }
    // 이메일 끝

    // 비밀번호 시작
    val Password : LiveData<String>
        get() = password
    // 초기값 설정
    init {
        password.value = ""
    }

    fun getUpdatePassword(text : String){
        password.value = text
    }
    // 비밀번호 끝

    // 유저 데이터 시작
    val User : LiveData<FirebaseUser>
        get() = user

    fun getUpdateUser(currentuser : FirebaseUser){
        user.value = currentuser
    }
    // 비밀번호 끝

}