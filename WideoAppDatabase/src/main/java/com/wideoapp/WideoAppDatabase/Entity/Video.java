package com.wideoapp.WideoAppDatabase.Entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "video")
public class Video {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "url")
    private String url;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "likes")
    private int likes;

    @Column(name = "dislikes")
    private int dislikes;

    @Column(name = "display")
    private int display;

    @Column(name = "date")
    private String date;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "video",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private List<Review> reviews;

    @Column(name = "photo_url")
    private String photoUrl;

    public Video() {
    }

    public Video(String url, String title, String description, int likes, int dislikes, int display, String date, User user, List<Review> reviews, String photoUrl) {
        this.url = url;
        this.title = title;
        this.description = description;
        this.likes = likes;
        this.dislikes = dislikes;
        this.display = display;
        this.date = date;
        this.user = user;
        this.reviews = reviews;
        this.photoUrl = photoUrl;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }
}
