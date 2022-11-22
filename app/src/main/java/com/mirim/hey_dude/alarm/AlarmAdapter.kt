package com.mirim.hey_dude.alarm

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.hey_dude.R
import com.example.hey_dude.databinding.ItemAlarmRecyclerviewBinding

//DiffUtil :  RecyclerView Adpater의 업데이트를 계산하는데 사용된다.
//ListAdapter : DiffUtil을 활용하여 리스트를 업데이트 할 수 있는 기능 추가 Adapter
class AlarmAdapter : ListAdapter<Alarm, AlarmAdapter.AlarmItemViewHolder>(diffUtil){

    //AlarmItemViewHolder
    inner class AlarmItemViewHolder(private val binding: ItemAlarmRecyclerviewBinding)
        :RecyclerView.ViewHolder(binding.root){

        //ViewHolder : 내가 넣고자하는 data를 실제 레이아웃의 데이터로 연결시키는 기능
        fun bind(alarmModel: Alarm){
//            binding.alarmSwitch.setOnCheckedChangeListener { buttonView, isChecked ->
//                //getBindingAdapterPosition() : Adapter 내의 위치를 반환
//                val position: Int = getBindingAdapterPosition()
//
//            }

            binding.alarmLabel.text = alarmModel.label
            binding.alarmIcon.setImageResource(R.drawable.alarm_icon)
            binding.txtTime.text = alarmModel.hour.toString()
            }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlarmItemViewHolder {

        val itemBinding = ItemAlarmRecyclerviewBinding.inflate(
                           LayoutInflater.from(parent.context),parent,false)

        return AlarmItemViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: AlarmItemViewHolder, position: Int) {
        val alarm = getItem(position)
        holder.bind(currentList[position])
//        holder.bind(alarm)
    }

    fun getAlarmAt(position: Int): Alarm? {
        return getItem(position)
    }

    //diffUtil 사용하려면 diffUtil.callback  기능 구현
    companion object {
        val diffUtil = object :DiffUtil.ItemCallback<Alarm>() {
            override fun areItemsTheSame(oldItem: Alarm, newItem: Alarm): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Alarm, newItem: Alarm): Boolean {
                return oldItem.label.equals(newItem.label)&&
                        oldItem.hour == newItem.hour &&
                        oldItem.minute == newItem.minute
            }

        }
    }
}

