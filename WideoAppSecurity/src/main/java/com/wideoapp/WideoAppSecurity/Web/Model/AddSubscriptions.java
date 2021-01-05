package com.wideoapp.WideoAppSecurity.Web.Model;

public class AddSubscriptions {
    private String email;
    private int videoId;

    public AddSubscriptions(String email, int videoId) {
        this.email = email;
        this.videoId = videoId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getVideoId() {
        return videoId;
    }

    public void setVideoId(int videoId) {
        this.videoId = videoId;
    }
}
