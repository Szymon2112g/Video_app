package com.wideoapp.WideoAppSecurity.Web.Model;

public class HistoryDto {
    private int id;
    private int videoId;
    private int userId;

    public HistoryDto(int id, int videoId, int userId) {
        this.id = id;
        this.videoId = videoId;
        this.userId = userId;
    }

    public HistoryDto() {
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
