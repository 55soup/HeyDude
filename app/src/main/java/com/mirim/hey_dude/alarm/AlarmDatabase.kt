package com.mirim.hey_dude.alarm

import android.app.Application
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(entities = arrayOf(Alarm::class), version = 1, exportSchema = false)
abstract class AlarmDatabase : RoomDatabase() {
    abstract fun alarmDao() : AlarmDao

    companion object {
        private var INSTANCE: AlarmDatabase? = null
        fun getInstance(context: Context) : AlarmDatabase {
            if(INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(
                    context,
                    AlarmDatabase::class.java,
                    "alarm_database")
                    .addCallback(sRoomDatabaseCallback)
                    .build()
            }
            return INSTANCE as AlarmDatabase
        }
        private var sRoomDatabaseCallback = object : RoomDatabase.Callback() {
            override fun onOpen(db: SupportSQLiteDatabase) {
                super.onOpen(db)
                Thread {
                    val dao: AlarmDao? = INSTANCE?.alarmDao()
                    dao?.deleteAll()
                    //데이터 채우기
                }.start()
            }
        }
    }

}