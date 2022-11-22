package com.mirim.hey_dude.alarm

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TimePicker
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SwitchCompat
import com.example.hey_dude.R

//알람 설정 페이지
public class SetAlarmActivity : AppCompatActivity() {
    //변수 선언
    private lateinit var alarmLabel: EditText
    private lateinit var switchCompat: SwitchCompat
    private lateinit var cancelBtn: Button
    private lateinit var saveBtn: Button
    private lateinit var timePicker: TimePicker
//    private lateinit var alarmTime

//    var updateAlarm = Alarm()
    lateinit var newAlarm:Alarm
    var alarmHour =0
    var alarmMinute = 0
//    var repeatFlag: Boolean
    lateinit var REQUEST_STATE: String
    lateinit var preIntent: Intent
    private var db: AlarmDatabase? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_set_alarm)

        setObjectView()
        setTimePicker()

        //닫기 버튼을 누르면 다시
        cancelBtn.setOnClickListener {
            onBackPressed()
        }
        //알람 추가(저장) 버튼 클릭
        saveBtn.setOnClickListener {
            //액티비티 이동 시 화면 전환 애니메이션

//            newAlarm = Alarm()
//            var data = Intent()
//            setResult(RESULT_OK, data)

//            Thread(Runnable {
//                db!!.alarmDao().insert(Alarm(null, alarmLabel.text.toString(), null))
//            }).start()
//            val alarmlabel = alarmLabel.text.toString()
//            val intent = Intent(this, Fragment1::class.java)
//            intent.putExtra("label", alarmlabel)
//            onBackPressed()
        }
//        db = AlarmDatabase.getInstance(this)
    }
    fun setObjectView() {
        cancelBtn = findViewById(R.id.cancel_btn)
        saveBtn = findViewById(R.id.save_btn)
        alarmLabel = findViewById(R.id.alarm_label)
        timePicker = findViewById(R.id.timePicker)

    }

    fun setTimePicker() {
        timePicker.setIs24HourView(true)
        alarmHour = timePicker.currentHour
        alarmMinute = timePicker.currentMinute
        timePicker.setOnTimeChangedListener(timeChangedListener())
    }

    inner class timeChangedListener :  TimePicker.OnTimeChangedListener {
        override fun onTimeChanged(timeView: TimePicker, hour: Int, minute: Int) {
            alarmHour = hour
            alarmMinute = minute
        }
    }

//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//        if (requestCode >= 1000)
//            if (requestCode == RESULT_OK) {//성공여부 확인
//
//            }
//    }
//    companion object {
//        fun newIntent(context: Context): Intent {
//            return Intent(context, SetAlarmActivity::class.java)
//        }
//    }
//
//    @RequiresApi(api = Build.VERSION_CODES.M)
//    fun isExternalStorageReadable(): Boolean {
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//            if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
//                == PackageManager.PERMISSION_DENIED
//            ) {
//                requestPermissions(arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE), 1)
//                return false
//            }
//        }
//        return true
//    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.slide_up_enter, R.anim.slide_down_exit)
    }
}