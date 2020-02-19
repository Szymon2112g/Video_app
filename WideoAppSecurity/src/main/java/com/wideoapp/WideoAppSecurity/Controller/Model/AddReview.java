package com.wideoapp.WideoAppSecurity.Controller.Model;

public class AddReview {
    private String email;
    private String comment;
    private int videoId;

    public AddReview(String email, String comment, int videoId) {
        this.email = email;
        this.comment = comment;
        this.videoId = videoId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getVideoId() {
        return videoId;
    }

    public void setVideoId(int videoId) {
        this.videoId = videoId;
    }
}
