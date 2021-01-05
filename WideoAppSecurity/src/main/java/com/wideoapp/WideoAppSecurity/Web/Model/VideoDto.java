package com.wideoapp.WideoAppSecurity.Web.Model;

import java.util.List;

public class VideoDto {
    private int id;
    private String url;
    private String title;
    private String description;
    private int likes;
    private int dislikes;
    private int display;
    private String date;
    private int userId;
    private List<ReviewDto> reviews;
    private String photoUrl;

    public VideoDto(int id, String url, String title, String description, int likes, int dislikes, int display, String date, int userId, List<ReviewDto> reviews, String photoUrl) {
        this.id = id;
        this.url = url;
        this.title = title;
        this.description = description;
        this.likes = likes;
        this.dislikes = dislikes;
        this.display = display;
        this.date = date;
        this.userId = userId;
        this.reviews = reviews;
        this.photoUrl = photoUrl;
    }

    public VideoDto() {
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

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public List<ReviewDto> getReviews() {
        return reviews;
    }

    public void setReviews(List<ReviewDto> reviews) {
        this.reviews = reviews;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }
}
