package com.mirim.hey_dude.alarm

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "alarmTable")
data class Alarm(
    @PrimaryKey
    var id: Long?,

    @ColumnInfo(name = "time")
    var time: String,

    @ColumnInfo(name = "label")
    var label: String?
)
