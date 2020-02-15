package com.wideoapp.WideoAppSecurity.Entity;

import javax.persistence.*;

@Entity
@Table(name = "video_detail")
public class VideoDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "likes")
    private int likes;

    @Column(name = "dislikes")
    private int dislikes;

    @Column(name = "display")
    private int display;

    public VideoDetail() {
    }

    public VideoDetail(int likes, int dislikes, int display) {
        this.likes = likes;
        this.dislikes = dislikes;
        this.display = display;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public int getDislikes() {
        return dislikes;
    }

    public void setDislikes(int dislikes) {
        this.dislikes = dislikes;
    }

    public int getDisplay() {
        return display;
    }

    public void setDisplay(int display) {
        this.display = display;
    }
}
