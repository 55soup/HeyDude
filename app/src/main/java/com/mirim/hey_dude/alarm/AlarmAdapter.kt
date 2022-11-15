package com.mirim.hey_dude.alarm

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.hey_dude.R
import com.mirim.hey_dude.recordRecyclerView.RecordAdapter
import org.w3c.dom.Text

class AlarmAdapter(private val context: Context) :
    RecyclerView.Adapter<AlarmAdapter.ViewHolder>() {

    var datas = mutableListOf<AlarmData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_alarm_recyclerview, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(datas[position])
    }

    override fun getItemCount(): Int = datas.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val alarmIcon : ImageView = itemView.findViewById(R.id.alarm_icon)
        private val textTime : TextView = itemView.findViewById(R.id.txtTime)
        private val alarmLabel : TextView = itemView.findViewById(R.id.alarm_label)

        fun bind(item: AlarmData){
//            Glide.with(itemView).load(item.img).into(alarmIcon)
            textTime.text = item.time
            alarmLabel.text = item.label
        }
    }

}