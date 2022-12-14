package com.mirim.hey_dude.alarm;

public class AlarmBuilder {
    Alarm alarm;

    public AlarmBuilder() {
        alarm = new Alarm();
    }
    public AlarmBuilder setId(int id) {
        alarm.setId(id);
        return this;
    }

    AlarmBuilder setHour(int hour) {
        alarm.setHour(hour);
        return this;
    }

    public AlarmBuilder setMinute(int minute) {
        alarm.setMinute(minute);
        return this;
    }

    public AlarmBuilder setLabel(String label) {
        alarm.setLabel(label);
        return this;
    }

//    public AlarmBuilder setRepeatFlag(boolean repeatFlag) {
//        alarm.setRepeatFlag(repeatFlag);
//        return this;
//    }

    public Alarm build() { return this.alarm; }
}
