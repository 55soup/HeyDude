package com.mirim.hey_dude.alarm

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TimePicker
import android.widget.TimePicker.OnTimeChangedListener
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SwitchCompat
import com.example.hey_dude.R
import java.sql.Time

//알람 설정 페이지
class SetAlarmActivity : AppCompatActivity() {
    //변수 선언
    private lateinit var alarmLabel: EditText
    private lateinit var switchCompat: SwitchCompat
    private lateinit var cancelBtn: Button
    private lateinit var saveBtn: Button
    private lateinit var timePicker: TimePicker

    var updateAlarm = Alarm()
    var newAlarm = Alarm()
    var alarmHour = 0
    var alarmMinute = 0
//    lateinit var repeatFlag: Boolean
    lateinit var REQUEST_STATE: String
    lateinit var preIntent: Intent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_set_alarm)

        setObjectView()
        setTimePicker()
        setAlarmView()

        preIntent = intent
        REQUEST_STATE = preIntent.getStringExtra("REQUEST_STATE").toString()

        //닫기 버튼을 누르면 다시
        cancelBtn.setOnClickListener {
            onBackPressed()
        }
        //알람 추가(저장) 버튼 클릭
        saveBtn.setOnClickListener {
            setAlarm()
            if (REQUEST_STATE == "update") {
                newAlarm.id = updateAlarm.id
            }
        }

    }//onCreate()
    fun setAlarm() {
        newAlarm = AlarmBuilder()
            .setHour(alarmHour)
            .setLabel(alarmLabel.text.toString())
            .setMinute(alarmMinute)
//            .setRepeatFlag(repeatFlag)
            .build()
    }

    fun setObjectView() {
        alarmLabel = findViewById(R.id.alarm_label)
        saveBtn = findViewById(R.id.save_btn)
        cancelBtn = findViewById(R.id.cancel_btn)
        switchCompat = findViewById(R.id.switchView)
//        timePicker = findViewById(R.id.timePicker)
    }
    fun setAlarmView() {
        timePicker.currentHour = updateAlarm.getHour()
        timePicker.currentMinute = updateAlarm.getMinute()
        alarmLabel.setText(updateAlarm.getLabel())
//        switchCompat.isChecked(updateAlarm.isRepeatFlag())
    }

    fun setTimePicker() {
        timePicker.setIs24HourView(true)
        alarmHour = timePicker.currentHour
        alarmMinute = timePicker.currentMinute
        timePicker.setOnTimeChangedListener(timeChangedListener())
    }

    class timeChangedListener :  TimePicker.OnTimeChangedListener {
        override fun onTimeChanged(timeView: TimePicker, hour: Int, minute: Int) {
//            alarmHour = hour
//            alarmMinute = minute
        }
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