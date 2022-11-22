package com.mirim.hey_dude.alarm

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.hey_dude.R

//DiffUtil :  RecyclerView Adpater의 업데이트를 계산하는데 사용된다.
//ListAdapter : DiffUtil을 활용하여 리스트를 업데이트 할 수 있는 기능 추가 Adapter
class AlarmAdapter(var items: List<AlarmItem>)
    : RecyclerView.Adapter<AlarmAdapter.ViewHolder>() {

    lateinit var mContext: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_alarm_recyclerview, parent, false)
        mContext = parent.context

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items!!.get(position), position)
        holder.label.text = items.get(position).label
        holder.time.text = items.get(position).time
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var index: Int? = null
//        label = itemView.findViewById(R.id.alarm_label)
        lateinit var label: TextView
        lateinit var time: TextView
        fun bind(alarm: AlarmItem, position: Int) {
            label = itemView.findViewById(R.id.alarm_label)
            time = itemView.findViewById(R.id.alarm_time)

            index = position
            label.setText(alarm.label)
            time.setText(alarm.time)

        }

//        fun editData(label: String){
//            Thread {
//                index?.let { items!!.get(it). = contents }
//                index?.let { items!!.get(it) }?.let { db.alarmDao().update(it) }
//            }
//        }
    }

}

