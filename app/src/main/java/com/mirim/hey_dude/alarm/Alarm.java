package com.mirim.hey_dude.alarm;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

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

    public String getTitle() {
        return label;
    }

    public void setTitle(String title) {
        this.label = title;
    }

    public boolean isRepeatFlag() {
        return repeatFlag;
    }

    public void setRepeatFlag(boolean repeatFlag) {
        this.repeatFlag = repeatFlag;
    }
}
