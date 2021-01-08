package com.wideoapp.WideoAppSecurity.Web.Model;

public class ReviewDto {
    private int id;
    private String comment;
    private int userId;
    private int videoId;

    public ReviewDto(String comment, int userId, int videoId) {
        this.comment = comment;
        this.userId = userId;
        this.videoId = videoId;
    }

    public ReviewDto() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getVideoId() {
        return videoId;
    }

    public void setVideoId(int videoId) {
        this.videoId = videoId;
    }
}
