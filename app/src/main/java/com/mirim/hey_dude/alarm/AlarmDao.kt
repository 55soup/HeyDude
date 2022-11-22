package com.mirim.hey_dude.alarm

import android.database.Observable
import androidx.lifecycle.LiveData
import androidx.room.*
import java.util.*

@Dao
interface AlarmDao {
    @Query("SELECT * FROM alarm_table")
    fun getAll(): List<Alarm>

    @Insert
    fun insert(vararg alarm: Alarm) //vararg : 가변인자 지원

    @Delete
    fun delete(vararg alarm: Alarm)

    @Update
    fun update(vararg alarm: Alarm)

    @Query("SELECT * FROM alarm_table")
    fun getAllAlarms(): LiveData<List<Alarm>>

    @Query("SELECT * FROM alarm_table")
    fun getAllAlarmsFromService(): Observable<List<Alarm>>

    @Query("SELECT * FROM alarm_table WHERE id = :id")
    fun getAlarm (id: Int): Alarm
}

