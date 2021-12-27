package com.example.myapplication.activity

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.kotlin.activity.data.viewmodel.SignupViewModel
import com.example.myapplication.R
import com.google.firebase.FirebaseException
import com.google.firebase.FirebaseTooManyRequestsException
import com.google.firebase.auth.*
import kotlinx.android.synthetic.main.signup.*
import java.lang.Exception
import java.util.concurrent.TimeUnit

class SignupActivity : AppCompatActivity() {

    // init
    private val viewmodel by lazy { ViewModelProvider(this).get(SignupViewModel::class.java) } // 생명주기 처리 없이 Livedata를 저장하고 있는 ViewModel

    // component 숨기기 보이기
    fun showHide(view:View) {
        Log.d("우왕", "오앙")
        if (view.visibility == View.VISIBLE){
            view.visibility = View.GONE
        } else {
            view.visibility = View.VISIBLE
        }
    }

    private lateinit var auth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        // init
        super.onCreate(savedInstanceState)
        setContentView(R.layout.signup)

        auth = FirebaseAuth.getInstance()

        // 인증번호 입력 안보이게 하기
        showHide(signup_check_number_container)
        showHide(signup_check_number)
        showHide(signup_phone_check_confirmation)


        // 인증 문자 요청 클릭 시
        signup_phone_check.setOnClickListener() {
            // 휴대번호 저장
            viewmodel.getUpdatePhoneNumber(signup_phone.text.toString().trim())

            // 휴대폰 입력이 제대로 되었을 때
            if(!android.util.Patterns.PHONE.matcher(viewmodel.PhoneNumber.value!!).matches()){
                Toast.makeText(this@SignupActivity, "올바른 휴대폰번호를 입력해주세요.", Toast.LENGTH_SHORT).show()
            } else {
                // 인증번호 입력 보이게 하기
                showHide(signup_check_number_container)
                showHide(signup_check_number)
                showHide(signup_phone_check_confirmation)

                // 전화번호 설정
                viewmodel.getUpdatePhoneNumber("+82" + signup_phone.text.toString().trim())
                Log.d("viewmodel", "${viewmodel.PhoneNumber.value}")
                // 휴대전화 인증
                val options = PhoneAuthOptions.newBuilder(auth)
                    .setPhoneNumber(viewmodel.PhoneNumber.value!!)       // Phone number to verify
                    .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                    .setActivity(this)                 // Activity (for callback binding)
                    .setCallbacks(callbacks)          // OnVerificationStateChangedCallbacks
                    .build()
                PhoneAuthProvider.verifyPhoneNumber(options)
            }
        }


        // 인증 확인 클릭 시
        signup_phone_check_confirmation.setOnClickListener() {
            viewmodel.getUpdateStoredVerificationCode(signup_check_number.text.toString().trim()) // 인증번호 비교
            verifyUser(viewmodel.StoredVerificationCode.value!!)
        }


        // 회원가입 클릭 시
        signup_sign_up.setOnClickListener(){
            viewmodel.getUpdateEmail(signup_email.text.toString().trim())
            viewmodel.getUpdateName(signup_name.text.toString().trim())
            viewmodel.getUpdatePhoneNumber(signup_phone.text.toString().trim())
            viewmodel.getUpdatePassword(signup_password.text.toString().trim())
            var error = 0;
            // Validate...

            // email 확인
            if(!android.util.Patterns.EMAIL_ADDRESS.matcher(viewmodel.Email.value!!).matches()){
                Toast.makeText(this@SignupActivity, "올바른 이메일을 입력해주세요.", Toast.LENGTH_SHORT).show()
                error = 1
            }
            // password 입력 확인
            if (viewmodel.Password.value.isNullOrEmpty()){
                Toast.makeText(this@SignupActivity, "비밀번호를 입력해주세요.", Toast.LENGTH_SHORT).show()
                error = 1
            }
            // password가 6글자 이하인 경우
            if (viewmodel.Password.value!!.length < 6){
                Toast.makeText(this@SignupActivity, "6글자 이상의 비밀번호를 입력해주세요", Toast.LENGTH_SHORT).show()
                error = 1
            }
            // name 입력 확인
            if (viewmodel.Name.value.isNullOrEmpty()){
                Toast.makeText(this@SignupActivity, "이름을 입력해주세요.", Toast.LENGTH_SHORT).show()
                error = 1
            }
            // phone 입력 확인
            if (viewmodel.PhoneNumber.value.isNullOrEmpty()){
                Toast.makeText(this@SignupActivity, "휴대폰을 입력해주세요.", Toast.LENGTH_SHORT).show()
                error = 1
            }
            // 휴대폰 인증이 완료되지 않은 경우
            if (viewmodel.VerificationState.value == false){
                Toast.makeText(this@SignupActivity, "휴대폰 인증을 완료해주세요.", Toast.LENGTH_SHORT).show()
                error = 1
            }

            // error가 없으면
            if (error == 0) {
                createUser(viewmodel.Email.value!!, viewmodel.Password.value!!)
            }

            


        }


    }

    // == 휴대폰 인증 관련 함수 시작

    // 휴대폰 인증 확인 누를 때 호출되는 함수
    private fun verifyUser(verificationCode: String) {
        if (viewmodel.StoredVerificationId.value != null) {
            val credential = PhoneAuthProvider.getCredential(viewmodel.StoredVerificationId.value!!, viewmodel.StoredVerificationCode.value!!)
            signInWithPhoneAuthCredential(credential)
        }
        else {
            // decide what you want to do if it hasn't been given a value yet.
        }
    }

    // callback함수 정의
    val callbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

        // 인증이 완료되었을 때 호출됌
        override fun onVerificationCompleted(credential: PhoneAuthCredential) {
            // This callback will be invoked in two situations:
            // 1 - Instant verification. In some cases the phone number can be instantly
            //     verified without needing to send or enter a verification code.
            // 2 - Auto-retrieval. On some devices Google Play services can automatically
            //     detect the incoming verification SMS and perform verification without
            //     user action.
            Log.d(TAG, "onVerificationCompleted:$credential")
            signInWithPhoneAuthCredential(credential)
        }

        // 요청에 잘못된 전화번호 등의 잘못된 인증 요청
        override fun onVerificationFailed(e: FirebaseException) {
            // This callback is invoked in an invalid request for verification is made,
            // for instance if the the phone number format is not valid.
            Log.w(TAG, "onVerificationFailed", e)

            if (e is FirebaseAuthInvalidCredentialsException) {
                // Invalid request
            } else if (e is FirebaseTooManyRequestsException) {
                // The SMS quota for the project has been exceeded
            }

            // Show a message and update the UI
        }

        // 제공된 전화번호로 인증 코드가 SMS 통해 전송된 후에 호출출
        override fun onCodeSent(
            verificationId: String,
            token: PhoneAuthProvider.ForceResendingToken
        ) {
            // The SMS verification code has been sent to the provided phone number, we
            // now need to ask the user to enter the code and then construct a credential
            // by combining the code with a verification ID.
            Log.d(TAG, "onCodeSent:$verificationId")

            // Save verification ID and resending token so we can use them later
            viewmodel.getUpdateStoredVerificationId(verificationId)
            val resendToken = token

        }
    }

    // 휴대폰 인증 성공 실패 관련 함수
    private fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential) {
        auth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "signInWithCredential:success")
                    viewmodel.getUpdateVerificationState(true) // 인증 state를 true로 체크하기


                    val user = task.result?.user
                } else {
                    // Sign in failed, display a message and update the UI
                    Log.w(TAG, "signInWithCredential:failure", task.exception)
                    if (task.exception is FirebaseAuthInvalidCredentialsException) {
                        // The verification code entered was invalid
                    }
                    // Update UI
                }
            }
    }

    // == 휴대폰 인증 관련 함수 끝

    // 회원가입 함수
    private fun createUser(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {

                    Toast.makeText(this@SignupActivity, "회원가입 성공", Toast.LENGTH_SHORT).show()

                    // 아이디 만들기
                    viewmodel.SignupComplete()

                    // activity 이동
                    val user = auth.currentUser
                    val nextIntent = Intent(this, MainActivity::class.java)
                    startActivity(nextIntent)
                    finish()

                } else {
                    try {
                        task.result
                    } catch (e: Exception) {
                        e.printStackTrace()
                        Log.d("Fail_register_email", e.message!!)
                        Toast.makeText(this@SignupActivity, "이미있는 이메일 형식입니다 다시 입력해주세요", Toast.LENGTH_LONG)
                            .show()
                    }
                }
            }
            .addOnFailureListener {
                Toast.makeText(this, "이미 있는 이메일입니다. 다시 입력해주세요..", Toast.LENGTH_SHORT).show()
            }
    }

}