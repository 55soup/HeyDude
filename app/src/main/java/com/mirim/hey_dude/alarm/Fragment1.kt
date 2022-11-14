package com.mirim.hey_dude.alarm

import androidx.annotation.RequiresApi
import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import com.example.hey_dude.R

class Fragment1 : Fragment() {
    lateinit var alarmAdapter : AlarmAdapter
    val datas = mutableListOf<AlarmData>()

    @RequiresApi(api = Build.VERSION_CODES.M)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.activity_fragment1, container, false) // 반드시 추가
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initRecycler()
    }
    private fun initRecycler() {
        alarmAdapter = AlarmAdapter(this)
//        alarm.adapter = alarmAdapter

        datas.apply {
            add(AlarmData(img = R.drawable.alarm_icon, time = "9:30 AM", label = "졸업식"))
            add(AlarmData(img = R.drawable.alarm_icon, time = "9:30 AM", label = "졸업식"))

            alarmAdapter.datas = datas
            alarmAdapter.notifyDataSetChanged()
        }
    }
}