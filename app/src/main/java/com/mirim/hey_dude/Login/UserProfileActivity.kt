package com.mirim.hey_dude.Login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import com.example.hey_dude.R
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase

class UserProfileActivity : AppCompatActivity() {
    private val auth = Firebase.auth
    private val database = Firebase.database(Config.DATABASE_URL)
    private lateinit var progressBar: ProgressBar

    private lateinit var nickname: String
    private lateinit var email: String
    private lateinit var nicknameTextView: TextView
    private lateinit var emailTextView: TextView
    private lateinit var uidTextView: TextView

    // provider 정보 저장할 속성 추가
    private var authFrom: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_profile)

        val user = auth.currentUser!!
        nicknameTextView = findViewById(R.id.user_profile_nickname)
        emailTextView = findViewById(R.id.user_profile_email)
        uidTextView = findViewById(R.id.user_profile_uid)
        progressBar = findViewById(R.id.user_profile_progress)

        // 어떤 provider로부터 계정 정보를 전달받는지 providerData를 검사
        for (user in FirebaseAuth.getInstance().currentUser!!.providerData) {
            Log.d("mytag", user.providerId)
            if (user.providerId == "google.com") {
                authFrom = user.providerId
            }
        }

        authFrom?.let {
            val user = auth.currentUser!!
            // 구글 로그인의 경우 따로 데이터를 저장받지는 않았으므로
            // displayName을 보여 주지만,
            // 원한다면 구글 로그인이 성공한 시점 이후에 액티비티를 통해서
            // 구글 계정과 관련된 추가 정보를 저장받도록 구현해도 무방함
            nicknameTextView.text = user.displayName
            emailTextView.text = user.email
            uidTextView.text = user.uid
            showProfile()
            // 구글 로그아웃 메서드 붙이기
            findViewById<Button>(R.id.logout).setOnClickListener {
                logoutGoogleAccount()
            }
        } ?: run {
            database.getReference("users").child(user.uid).get().addOnSuccessListener {
                nickname = it.child("nickname").getValue<String>()!!
                email = it.child("email").getValue<String>()!!

                nicknameTextView.text = nickname
                emailTextView.text = email
                uidTextView.text = user.uid
                showProfile()
            }
            findViewById<Button>(R.id.logout).setOnClickListener {
                logout()
            }
        }
    }

    fun logout() {
        auth.signOut()
        Toast.makeText(this, "로그아웃", Toast.LENGTH_SHORT).show()
        startActivity(Intent(this, LoginActivity::class.java))
        finish()
    }

    // 구글 로그아웃 메서드
    fun logoutGoogleAccount() {
        val gso = GoogleSignInOptions
            .Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(Config.GOOGLE_SIGN_IN_WEB_CLIENT_ID)
            .requestEmail()
            .build()
        val googleSignInClient = GoogleSignIn.getClient(this, gso)
        // 로그아웃
        auth.signOut()
        // 이후 구글 계정도 로그아웃
        googleSignInClient.signOut().addOnCompleteListener {
            Toast.makeText(this, "로그아웃", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }

    fun showProfile() {
        progressBar.visibility = View.GONE
        nicknameTextView.visibility = View.VISIBLE
        emailTextView.visibility = View.VISIBLE
        uidTextView.visibility = View.VISIBLE
    }
}

