package com.example.kotlin.activity.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.kotlin.activity.data.viewmodel.LoginViewModel
import com.example.kotlin.activity.view.Login.SignupActivity
import com.example.myapplication.R
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.main.*
import kotlinx.android.synthetic.main.signup.*
import java.lang.Exception

class MainActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth

    // init
    private val viewmodel by lazy { ViewModelProvider(this).get(LoginViewModel::class.java) } // 생명주기 처리 없이 Livedata를 저장하고 있는 ViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main)
        auth = FirebaseAuth.getInstance()

        // 로그인 상태이면 페이지로 이동
        val user = auth.currentUser
        if (user != null) {
            Log.d("user", auth.currentUser.toString())
            val nextIntent = Intent(this, HomeActivity::class.java)
            startActivity(nextIntent)
            finish()
        }

        // login button click
        main_login.setOnClickListener() {
            // 값 설정
            viewmodel.getUpdateEmail(login_email.text.toString().trim())
            viewmodel.getUpdatePassword(login_password.text.toString().trim())
            // 미입력 시
            if (viewmodel.Email.value == "" || viewmodel.Password.value == "") {
                Toast.makeText(this@MainActivity, "아이디와 비밀번호를 입력해주세요.", Toast.LENGTH_SHORT).show()
            } else {
                // 로그인
                auth.signInWithEmailAndPassword(viewmodel.Email.value!!, viewmodel.Password.value!!)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {

                            Toast.makeText(this@MainActivity, "로그인 성공", Toast.LENGTH_SHORT).show()

                            // activity 이동
                            val nextIntent = Intent(this, HomeActivity::class.java)
                            startActivity(nextIntent)
                            finish()
                        } else {
                            try {
                                task.result
                            } catch (e: Exception) {
                                e.printStackTrace()
                                Log.d("Fail_register_email", e.message!!)
                                Toast.makeText(
                                    this@MainActivity,
                                    "아이디나 비밀번호가 잘못되었습니다.",
                                    Toast.LENGTH_LONG
                                )
                                    .show()
                            }
                        }
                    }
                    .addOnFailureListener {
                        Toast.makeText(this, "아이디나 비밀번호가 잘못되었습니다.", Toast.LENGTH_SHORT).show()
                    }
            }
        }

        main_sign_up.setOnClickListener(){
            val nextIntent = Intent(this, SignupActivity::class.java)
            startActivity(nextIntent)
            finish()
        }
    }
}