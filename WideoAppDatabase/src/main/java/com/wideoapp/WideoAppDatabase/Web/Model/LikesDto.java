package com.wideoapp.WideoAppDatabase.Web.Model;

public class LikesDto {
    private int id;
    private int videoId;
    private int userId;

    public LikesDto(int videoId, int userId) {
        this.videoId = videoId;
        this.userId = userId;
    }

    public LikesDto() {
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
