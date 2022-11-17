package com.mirim.hey_dude

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.widget.ImageButton
import android.os.Bundle
import android.view.View
import android.widget.TableRow
import androidx.activity.OnBackPressedCallback
import com.example.hey_dude.R
import com.google.android.gms.common.SignInButton
import com.mirim.hey_dude.EmailLogin

class SettingActivity : AppCompatActivity() {
    var btn_back: ImageButton? = null
    var userLogout: TableRow? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting)
        findViewById<ImageButton>(R.id.btn_back).setOnClickListener(View.OnClickListener { onBackPressed() })
        findViewById<TableRow>(R.id.userLogout).setOnClickListener(View.OnClickListener {
            val e1 = EmailLogin()
            e1.userLogout()
            finishAffinity() //쌓인 화면스택 제거
            val intent = Intent(this, EmailLogin::class.java)  // 인텐트를 생성해줌,
            startActivity(intent)  // 화면 전환을 시켜줌
        })
    }
}