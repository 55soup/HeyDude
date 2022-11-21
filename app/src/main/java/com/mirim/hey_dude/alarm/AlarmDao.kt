package com.mirim.hey_dude.alarm

import androidx.room.*

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
}