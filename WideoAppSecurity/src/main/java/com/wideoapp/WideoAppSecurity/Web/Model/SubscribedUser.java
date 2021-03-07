package com.wideoapp.WideoAppSecurity.Web.Model;

public class SubscribedUser {
    private int userId;
    private String email;
    private String name;

    public SubscribedUser(int userId, String email, String name) {
        this.userId = userId;
        this.email = email;
        this.name = name;
    }

    public SubscribedUser() {
    }

    public int getUserId() {
        return userId;
    }

    public SubscribedUser setUserId(int userId) {
        this.userId = userId;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public SubscribedUser setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getName() {
        return name;
    }

    public SubscribedUser setName(String name) {
        this.name = name;
        return this;
    }
}
