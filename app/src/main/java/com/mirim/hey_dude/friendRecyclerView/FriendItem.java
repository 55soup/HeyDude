package com.mirim.hey_dude.friendRecyclerView;

public class FriendItem {
    String name;
    String today;
    String resourceId;

    public FriendItem(String name, String today, String resourceId) {
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

    public String getResourceId() {
        return resourceId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setToday(String today) {
        this.today = today;
    }

    public void setResourceId(String resourceId) {
        this.resourceId = resourceId;
    }

}
