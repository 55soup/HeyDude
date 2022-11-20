package com.mirim.hey_dude.recordRecyclerView;

public class RecordItem {
    String alarmTime;
    String friendUserName;
    String pushAlarmTime;

    public RecordItem(String alarmTime, String friendUserName, String pushAlarmTime) {
        this.alarmTime = alarmTime;
        this.friendUserName = friendUserName;
        this.pushAlarmTime = pushAlarmTime;
    }

    public String getAlarmTime() {
        return alarmTime;
    }

    public void setAlarmTime(String alarmTime) {
        this.alarmTime = alarmTime;
    }

    public String getFriendUserName() {
        return friendUserName;
    }

    public void setFriendUserName(String friendUserName) {
        this.friendUserName = friendUserName;
    }

    public String getPushAlarmTime() {
        return pushAlarmTime;
    }

    public void setPushAlarmTime(String pushAlarmTime) {
        this.pushAlarmTime = pushAlarmTime;
    }
}
