package com.mirim.hey_dude.alarm

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(entities = [Alarm::class], version = 1, exportSchema = false)
abstract class AlarmDatabase : RoomDatabase() {
    abstract fun alarmDao(): AlarmDao

    companion object {
        private var instance: AlarmDatabase? = null

        @Synchronized
        fun getInstance(context: Fragment1) : AlarmDatabase? {
            if(instance == null) {
                instance = Room.databaseBuilder(
                    context.applicationContext,
                    AlarmDatabase::class.java,
                    "database-alarm")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallBack)
                    //.allowMainThreadQueries()
                    //allowMainThreadQueries() : 메인스레드에서 DB접근 허용, 데이터를 받아오는 작업이 길어질 경우 UI가 장시간 멈춤 권장x
                    .build()
            }
            return instance as AlarmDatabase
        }
        private val roomCallBack: Callback = object : Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
            }
        }
    }


}