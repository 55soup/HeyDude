package com.mirim.hey_dude

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.media.Ringtone
import android.media.RingtoneManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TimePicker
import android.widget.Toast
import androidx.appcompat.widget.SwitchCompat
import androidx.fragment.app.Fragment
import androidx.test.core.app.ApplicationProvider.getApplicationContext
import com.example.hey_dude.R
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.ktx.Firebase
import com.mirim.hey_dude.alarm.Alarm
import com.mirim.hey_dude.alarm.AlarmDatabase
import java.util.*
import kotlin.concurrent.timerTask


class SetAlarmFragment : Fragment() {
    //변수 선언
    private lateinit var alarmLabel: EditText
    private lateinit var switchCompat: SwitchCompat
    private lateinit var cancelBtn: Button
    private lateinit var saveBtn: Button
    private lateinit var timePicker: TimePicker
//    private lateinit var setAlarmActivityBinding: ActivitySetAlarmBinding
    private val user = Firebase.auth.currentUser
    private val reference = FirebaseDatabase.getInstance().getReference("users")

    lateinit var nickname: String
    lateinit var newAlarm: Alarm
    lateinit var REQUEST_STATE: String
    lateinit var preIntent: Intent
    private var db: AlarmDatabase? = null
    val CHANNEL_ID = 45
    var alarmhour: Int = 0
    var alarmminute: Int = 0

    var currentTIme : Long = System.currentTimeMillis()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
//        var setAlarmActivityBinding = inflater.inflate(R.layout.activity_set_alarm,
//            container, false)
//        var view: View = setAlarmActivityBinding.rootView
        var view = inflater.inflate(R.layout.activity_set_alarm, container, false)

        cancelBtn = view.findViewById(R.id.cancel_btn)
        saveBtn = view.findViewById(R.id.save_btn)
        alarmLabel = view.findViewById(R.id.alarm_label)
        timePicker = view.findViewById(R.id.timePicker)
        setTimePicker()

        val r: Ringtone = RingtoneManager.getRingtone(context, RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE))
        val timer = Timer()


        saveBtn.setOnClickListener {
            user?.let {
                // Name, email address, and profile photo Url
                val uid = user.uid //각 사용자를 구별하는 uid
                reference.child(uid).addListenerForSingleValueEvent(object : ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        nickname = snapshot.child("nickname").getValue().toString()
                    }

                    override fun onCancelled(error: DatabaseError) {}
                }
                )
//                var builder: Notification = NotificationCompat.Builder(this, CHANNEL_ID)
//                    .setSmallIcon(R.drawable.alarm_icon)
//                    .setContentTitle("${nickname}알람 설정 완료")
//                    .setContentText("지금 바로 ")
//                    .setPriority(NotificationCompat.PRIORITY_DEFAULT)
//                    .build()
            }
            timer.scheduleAtFixedRate(timerTask {
                run(){
                    if(currentTIme.equals(alarmTime())){
//                    val builder = AlertDialog.Builder(context)
//                        .setTitle("알람 제목")
//                        .setMessage("알람이 울렸습니다.")
//                        .setPositiveButton("중지")
                        r.play()
                    } else {
                        r.stop()
                    }
                }
            },0,1000)
        }
        return view
    }

    fun alarmTime(): String {
        var alarmHour: Int = timePicker.currentHour
        var alarmMinute = timePicker.currentMinute

        var ALARM_TIME: String

        if(alarmHour>12){
            alarmHour = alarmHour - 12
            ALARM_TIME = alarmHour.toString().plus(":").plus(alarmMinute.toString()).plus("PM")
        } else {
            ALARM_TIME = alarmHour.toString().plus(":").plus(alarmMinute.toString()).plus("AM")
        }

        return ALARM_TIME
    }

    fun setTimePicker() {
//        timePicker.setIs24HourView(true)
        alarmhour = timePicker.currentHour
        alarmminute = timePicker.currentMinute
        timePicker.setOnTimeChangedListener(timeChangedListener())
    }
    inner class timeChangedListener :  TimePicker.OnTimeChangedListener {
        override fun onTimeChanged(timeView: TimePicker, hour: Int, minute: Int) {
            alarmhour = hour
            alarmminute = minute
        }
    }
}
