package com.mirim.hey_dude

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.hey_dude.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.mirim.hey_dude.Login.SignUpActivity
import okhttp3.OkHttpClient


class EmailLogin : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        // Initialize Firebase Auth
        auth = Firebase.auth
        val currentUser = auth.currentUser
        if(currentUser != null) {
            startActivity(Intent(this, NavBar::class.java))
            finish()
        }

        findViewById<Button>(R.id.loginBtn).setOnClickListener {
            val email = findViewById<EditText>(R.id.loginID).text.toString().trim()
            val password = findViewById<EditText>(R.id.loginPW).text.toString()

            //Network가 연결이 되어있는가?
            val connectivityManager = application.getSystemService(ConnectivityManager::class.java)
            val currentNetwork = connectivityManager.activeNetwork
            if(currentNetwork==null)
                Toast.makeText(applicationContext, "네트워크 연결 안됨", Toast.LENGTH_SHORT).show()

            // email, password edittext가 공백일때
            if(email=="" && password=="")
                Toast.makeText(baseContext, "이메일 비밀번호를 입력해주세요",
                    Toast.LENGTH_SHORT).show()
            else{
                // https://firebase.google.com/docs/auth/android/start#sign_in_existing_users
                auth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            // 로그인 성공 이후 auth.currentUser 속성 통해서 유저 정보 접근 가능
                            val user = auth.currentUser
                            Log.d("mytag", "로그인 성공 ${user.toString()}")
                            startActivity(Intent(this,
                                NavBar::class.java))
                            finish()
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.d("mytag", "로그인 실패 (사유 : ${task.exception})")
                            Toast.makeText(baseContext, "로그인 실패",
                                Toast.LENGTH_SHORT).show()
                        }
                    }
            }

        }
        findViewById<Button>(R.id.registerBtn).setOnClickListener {
            startActivity(Intent(this, SignUpActivity::class.java))
        }
    }
    fun userLogout(){
        val user = FirebaseAuth.getInstance()
        user.signOut()
    }

}