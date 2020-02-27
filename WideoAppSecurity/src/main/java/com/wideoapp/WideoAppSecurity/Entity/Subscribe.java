package com.wideoapp.WideoAppSecurity.Entity;

import javax.persistence.*;

@Entity
@Table(name = "subscribe")
public class Subscribe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "user_subscription_id")
    private int userSubscriptionId;

    @Column(name = "user_id")
    private int userId;

    public Subscribe() {}

    public Subscribe(int userSubscriptionId, int userId) {
        this.userSubscriptionId = userSubscriptionId;
        this.userId = userId;
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
