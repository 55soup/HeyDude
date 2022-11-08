package com.mirim.hey_dude

import android.content.Context
import android.content.Intent
import android.icu.number.Scale.none
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Switch
import android.widget.ToggleButton
import androidx.appcompat.widget.SwitchCompat
import com.example.hey_dude.R
//알람 설정 페이지
class SetAlarmActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_set_alarm)

        //다시 알람 토글 버튼 이벤트트
        val switch: SwitchCompat = findViewById(R.id.switchView)
        switch.setOnCheckedChangeListener { compoundButton, ischecked ->  }

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