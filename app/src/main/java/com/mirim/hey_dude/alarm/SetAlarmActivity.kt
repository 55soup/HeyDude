package com.mirim.hey_dude

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SwitchCompat
import com.example.hey_dude.R

//알람 설정 페이지
class SetAlarmActivity : AppCompatActivity() {
    //레이아웃 요소 변수 선언
    private lateinit var cancelBtn : Button
    private lateinit var saveBtn : Button
    private lateinit var switch : SwitchCompat

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_set_alarm)

        //레이아웃 요소 가져오기
        cancelBtn = findViewById(R.id.cancel_btn)
        saveBtn = findViewById(R.id.save_btn)
        switch = findViewById(R.id.switchView)

        //닫기 버튼을 누르면 다시
        cancelBtn.setOnClickListener {
            onBackPressed() //Activity 종료/실행 애니메이션
        }
        //저장 버튼 클릭
        saveBtn.setOnClickListener {
            setAlarm()
        }
    }

    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, SetAlarmActivity::class.java)
        }
    }

    //------------메서드------------
    fun setAlarm() {

    }
    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.slide_up_enter, R.anim.slide_down_exit)
    }
}