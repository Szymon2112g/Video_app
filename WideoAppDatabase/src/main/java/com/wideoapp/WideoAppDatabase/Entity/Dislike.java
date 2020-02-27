package com.wideoapp.WideoAppDatabase.Entity;

import javax.persistence.*;

@Entity
@Table(name = "dislike")
public class Dislike {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "video_id")
    private int videoId;

    @Column(name = "user_id")
    private int userId;

    public Dislike() {
    }

    public Dislike(int videoId, int userId) {
        this.videoId = videoId;
        this.userId = userId;
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
