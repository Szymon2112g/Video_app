package com.wideoapp.WideoAppSecurity.Web.Model;

public class DislikeDto {
    private int id;
    private int videoId;
    private int userId;

    public DislikeDto(int videoId, int userId) {
        this.videoId = videoId;
        this.userId = userId;
    }

    public DislikeDto() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getVideoId() {
        return videoId;
    }

    public void setVideoId(int videoId) {
        this.videoId = videoId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
