package com.wideoapp.WideoAppDatabase.Entity;

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

    public Subscribe() {}

    public Subscribe(int userSubscriptionId) {
        this.userSubscriptionId = userSubscriptionId;
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
