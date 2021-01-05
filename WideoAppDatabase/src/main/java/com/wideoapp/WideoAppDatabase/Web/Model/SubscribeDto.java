package com.wideoapp.WideoAppDatabase.Web.Model;

public class SubscribeDto {
    private int id;
    private int userSubscriptionId;

    public SubscribeDto(int userSubscriptionId) {
        this.userSubscriptionId = userSubscriptionId;
    }

    public SubscribeDto() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserSubscriptionId() {
        return userSubscriptionId;
    }

    public void setUserSubscriptionId(int userSubscriptionId) {
        this.userSubscriptionId = userSubscriptionId;
    }
}
