package com.mirim.hey_dude.alarm

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.hey_dude.R
import com.example.hey_dude.databinding.ActivityFragment1Binding

//알람 리사이클러뷰
class Fragment1 : Fragment() {
    lateinit var applicationContext: Context
    lateinit var alarmFragmentBinding: ActivityFragment1Binding
    lateinit var alarmAdapter: AlarmAdapter
    lateinit var alarmRecyclerView: RecyclerView
//    lateinit var alarmViewModel: AlarmViewModel

    val TAG = "AlarmFragment"
    var db : AlarmDatabase? = null
    var alarmList = mutableListOf<Alarm>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var context = requireContext()

        //초기화
//        db = AlarmDatabase.getDatabase(context)
        //이전에 저장한 내용 모두 불러와서 추가하기기
       val savedAlarm = db!!.alarmDao().getAll()
        if(savedAlarm.isNotEmpty()){
            alarmList.addAll(savedAlarm)
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        alarmFragmentBinding = DataBindingUtil.inflate(
            inflater, R.layout.activity_fragment1,
            container, false
        )
        alarmFragmentBinding.setAlarmList(this)
        var view = alarmFragmentBinding.root

//        TODO: AlarmAdapter
        alarmAdapter = AlarmAdapter()

        alarmRecyclerView = view.findViewById(R.id.recyclerView)
        alarmRecyclerView.setHasFixedSize(true)
        alarmRecyclerView.setLayoutManager(LinearLayoutManager(view.context))
        alarmRecyclerView.adapter = AlarmAdapter()

//      스와이프　삭제 메서드 구현
        attachItemTouchHelperToAdapter()

        return view
    }

    public fun updateAlarmManager(alarm: Alarm, request: String){
//        val alarmIntent = Intent(activity, AlarmManagerActivity::class.java)
//        alarmIntent.putExtra("alarm", alarm)
//        alarmIntent.putExtra("request", request)
//        startActivity(alarmIntent)
    }

    public fun attachItemTouchHelperToAdapter() {
        ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(
            0,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false //연구 과제 : 드래그된 item을 새로운 위치로 옮기는 것
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val alarm = alarmAdapter.getAlarmAt(viewHolder.adapterPosition)
                updateAlarmManager(alarm!!, "cancel")
//                alarmViewModel.delete(alarm)
            }
        }).attachToRecyclerView(alarmRecyclerView)
    }


}