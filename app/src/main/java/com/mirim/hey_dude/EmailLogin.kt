package com.mirim.hey_dude

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.hey_dude.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.mirim.hey_dude.Login.SignUpActivity

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
            val email = findViewById<EditText>(R.id.loginID).text.toString()
            val password = findViewById<EditText>(R.id.loginPW).text.toString()
//            https://firebase.google.com/docs/auth/android/start#sign_in_existing_users
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
        findViewById<Button>(R.id.registerBtn).setOnClickListener {
            startActivity(Intent(this, SignUpActivity::class.java))
        }
    }
    fun userLogout(){
        val user = FirebaseAuth.getInstance()
        user.signOut()
    }

}