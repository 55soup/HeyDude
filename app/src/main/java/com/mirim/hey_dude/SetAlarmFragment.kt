package com.mirim.hey_dude

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TimePicker
import androidx.appcompat.widget.SwitchCompat
import androidx.databinding.DataBindingUtil
import androidx.databinding.DataBindingUtil.setContentView
import androidx.fragment.app.Fragment
import com.example.hey_dude.R
import com.example.hey_dude.databinding.ActivitySetAlarmBinding
import com.mirim.hey_dude.alarm.Alarm
import com.mirim.hey_dude.alarm.AlarmDatabase

class SetAlarmFragment : Fragment() {
    //변수 선언
    private lateinit var alarmLabel: EditText
    private lateinit var switchCompat: SwitchCompat
    private lateinit var cancelBtn: Button
    private lateinit var saveBtn: Button
    private lateinit var timePicker: TimePicker
    private lateinit var setAlarmActivityBinding: ActivitySetAlarmBinding

    lateinit var newAlarm: Alarm
    var alarmHour =0
    var alarmMinute = 0
    lateinit var REQUEST_STATE: String
    lateinit var preIntent: Intent
    private var db: AlarmDatabase? = null

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

        return view

    }
    private fun setTimePicker() {
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
}