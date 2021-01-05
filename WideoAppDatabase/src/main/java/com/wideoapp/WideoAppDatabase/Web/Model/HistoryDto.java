package com.wideoapp.WideoAppDatabase.Web.Model;

public class HistoryDto {
    private int id;
    private int videoId;

    public HistoryDto(int videoId) {
        this.videoId = videoId;
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
}
