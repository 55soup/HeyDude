package com.mirim.hey_dude.Login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.example.hey_dude.R
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.Identity
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.SignInButton
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class GoogleLoginActivity : AppCompatActivity() {
    lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_google_login)
        //회원가입
        findViewById<Button>(R.id.sign_up).setOnClickListener {
            startActivity(Intent(this, SignUpActivity::class.java))
        }
        //구글 로그인 버튼 기능 구현
        findViewById<SignInButton>(R.id.google_login_btn).setOnClickListener {
            val gso = GoogleSignInOptions
                .Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                //웹클라이언트 ID 전달
                .requestIdToken(Config.GOOGLE_SIGN_IN_WEB_CLIENT_ID)
                .requestEmail()
                .build()
            //클라이언트 객체 생성
            val googleSignInClient = GoogleSignIn.getClient(this, gso)
            //구글 로그인을 위한 인텐트 객체 생성
            val signInIntent = googleSignInClient.signInIntent
            // 인텐트 전달하며 로그인 진행 (내부적으로는 구글 로그인 액티비티 실행)
            startActivityForResult(signInIntent, Config.RC_SIGN_IN)
        }
//        findViewById<Button>(R.id.forgot_password).setOnClickListener {
//            startActivity(Intent(this, ForgotPasswordActivity::class.java))
//        }
    }
    fun userLoginFailed(e: Exception?){
        val message = if(e == null) "로그인 실패" else "로그인 실패 ${e.message}"
        Toast.makeText(this, "${message}", Toast.LENGTH_SHORT).show()
        Log.d("mytag", "로그인 실패 : ${message}")
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        //구글 로그인 액티비티의 결과값 전달받기
        if (requestCode == Config.RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)

            try {
                //TODO : 파이어베이스 인증 작업 시작!!
                val account = task.getResult(ApiException::class.java)
                val credential = GoogleAuthProvider.getCredential(account.idToken!!, null)
                auth.signInWithCredential(credential)
                    .addOnCompleteListener(this) {
                        if (it.isSuccessful) {
                            Log.d("mytag","로그인 성공")
                            startActivity(Intent(this, UserProfileActivity::class.java))
                        }
                    }
            } catch (e: ApiException){
                userLoginFailed(e)
            }
        }
    }
}