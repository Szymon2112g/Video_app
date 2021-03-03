package com.wideoapp.WideoAppSecurity.Web.Model;

public class ReviewToAdd {
    private String comment;
    private int videoId;

    public ReviewToAdd(String comment, int videoId) {
        this.comment = comment;
        this.videoId = videoId;
    }

    public ReviewToAdd() {
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
