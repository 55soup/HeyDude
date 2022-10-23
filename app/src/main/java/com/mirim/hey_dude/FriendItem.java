package com.mirim.hey_dude;

public class FriendItem {
    String name;
    String today;
    int resourceId;

    public FriendItem(String name, String today, int resourceId) {
        this.name = name;
        this.today = today;
        this.resourceId = resourceId;
    }

    public String getName() {
        return name;
    }

    public String getToday() {
        return today;
    }

    public int getResourceId() {
        return resourceId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setToday(String today) {
        this.today = today;
    }

    public void setResourceId(int resourceId) {
        this.resourceId = resourceId;
    }
}
