package com.mirim.hey_dude.alarm

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SwitchCompat
import com.example.hey_dude.R

//알람 설정 페이지
class SetAlarmActivity : AppCompatActivity() {
    private lateinit var switchCompat: SwitchCompat
    private lateinit var cancelBtn: Button
    private lateinit var saveBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_set_alarm)

        setObjectView();

        //닫기 버튼을 누르면 다시
        cancelBtn.setOnClickListener {
            onBackPressed()
        }
    }

    fun setObjectView() {
        saveBtn = findViewById(R.id.save_btn)
        cancelBtn = findViewById(R.id.cancel_btn)
        switchCompat = findViewById(R.id.switchView)
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