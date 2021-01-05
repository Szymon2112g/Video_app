package com.wideoapp.WideoAppSecurity.Web.Model;

public class SubscribeDto {
    private int id;
    private int userSubscriptionId;
    private int userId;

    public SubscribeDto(int id, int userSubscriptionId, int userId) {
        this.id = id;
        this.userSubscriptionId = userSubscriptionId;
        this.userId = userId;
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

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
