package com.mirim.hey_dude.alarm;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

//Room에서 사용 할 테이블
@Entity(tableName = "alarm_table")
public class Alarm implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String label;
    private int hour;
    private int minute;
    private boolean repeatFlag;

    public Alarm() {}
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

//    public boolean isRepeatFlag() {
//        return repeatFlag;
//    }
//
//    public void setRepeatFlag(boolean repeatFlag) {
//        this.repeatFlag = repeatFlag;
//    }
}
