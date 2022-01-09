package com.example.kotlin.activity.data.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

import com.example.kotlin.activity.data.dataclass.User
import com.example.kotlin.activity.data.dataclass.time
import com.example.kotlin.activity.data.repository.UserRepo
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore


class UserViewModel : ViewModel() {
    // init
    var User = MutableLiveData<User>()
    var firestore = FirebaseFirestore.getInstance()

    private val UserRepo: UserRepo by lazy { UserRepo() }

    fun getUser() {
        // subscribe
        UserRepo.getdata().subscribe(
            {
                User.value = it
            },
            {
                // error
            },
        )

    }


    // Signup data start
    // private로 은닉
    private var phoneNum = MutableLiveData<String>() // 전화번호
    private var storedVerificationId = MutableLiveData<String>() // 전화 인증 id를 저장하는 변수
    private var storedVerificationCode = MutableLiveData<String>() // 전화 인증 코드를 저장하는 변수
    private var email = MutableLiveData<String>() // 이메일
    private var name = MutableLiveData<String>() // 이름
    private var password = MutableLiveData<String>() // 비밀번호
    private var verificationState = MutableLiveData<Boolean>() // 인증확인 여부

    // 외부 클래스에서 접근

    // 인증에 필요한 전화번호 시작
    val PhoneNumber : LiveData<String>
        get() = phoneNum
    // 초기값 설정
    init {
        phoneNum.value = "+821012345678"
    }

    fun getUpdatePhoneNumber(text : String){
        phoneNum.value = text
    }
    // 인증에 필요한 전화번호 끝

    // 전화 인증 id 시작
    val StoredVerificationId : LiveData<String>
        get() = storedVerificationId
    // 초기값 설정
    init {
        storedVerificationId.value = ""
    }

    fun getUpdateStoredVerificationId(text : String){
        storedVerificationId.value = text
    }
    // 전화 인증 id 끝

    // 전화 인증 코드 시작
    val StoredVerificationCode : LiveData<String>
        get() = storedVerificationCode
    // 초기값 설정
    init {
        storedVerificationCode.value = ""
    }

    fun getUpdateStoredVerificationCode(text : String){
        storedVerificationCode.value = text
    }
    // 전화 인증 코드 끝

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

    // 이름 시작
    val Name : LiveData<String>
        get() = name
    // 초기값 설정
    init {
        name.value = ""
    }

    fun getUpdateName(text : String){
        name.value = text
    }
    // 이름 끝

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

    // 인증확인 상태 시작
    val VerificationState : LiveData<Boolean>
        get() = verificationState
    // 초기값 설정
    init {
        verificationState.value = false
    }

    fun getUpdateVerificationState(state : Boolean){
        verificationState.value = state
    }
    // 인증확인 상태 끝

    // 회원가입 시작
    fun SignupComplete() {
        data class User(
            var email: String? = null,
            var personal_coin_id: String? = null,
            var real_active_state: Boolean? = null,
            var username: String? = null,
            var value: String? = null,
            var phone: String? = null,
            var profileimage : String? = null,
            var createdAt: java.util.Date = time.getTime() as java.util.Date,
        )

        // User 클래스 생성
        var createUser: User = User()
        createUser.email = email.value
        createUser.personal_coin_id = "#000000"
        createUser.real_active_state = true
        createUser.username = name.value
        createUser.value = "0"
        createUser.phone = phoneNum.value
        createUser.profileimage = ""

        firestore.collection("User").document(email.value!!).set(createUser)
    }
    // Signup data end

}