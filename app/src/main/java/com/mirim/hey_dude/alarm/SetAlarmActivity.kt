package com.mirim.hey_dude.alarm

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.hey_dude.R

//알람 설정 페이지
class SetAlarmActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //다시 알람 토글 버튼 이벤트트
//        val switch: SwitchCompat = findViewById(R.id.switchView)

        //닫기 버튼을 누르면 다시
        val cancelBtn = findViewById<Button>(R.id.cancel_btn)
        val saveBtn = findViewById<Button>(R.id.save_btn)
        saveBtn
        setContentView(R.layout.activity_set_alarm)
    }

    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, SetAlarmActivity::class.java)
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.slide_up_enter, R.anim.slide_down_exit)
    }
}