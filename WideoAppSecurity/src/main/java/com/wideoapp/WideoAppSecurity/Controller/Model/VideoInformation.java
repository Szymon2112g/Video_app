package com.wideoapp.WideoAppSecurity.Controller.Model;

import com.wideoapp.WideoAppSecurity.Entity.Video;

public class VideoInformation {

    private int id;
    private String url;
    private String title;
    private String description;
    private String firstName;
    private String lastName;
    private int userId;
    private int display;
    private String photoUrl;
    private String date;
    private int likes;
    private int dislikes;

    public VideoInformation(
            int id,
            String url,
            String title,
            String description,
            String firstName,
            String lastName,
            int userId,
            int display,
            String photoUrl,
            String date,
            int likes,
            int dislikes) {
        this.id = id;
        this.url = url;
        this.title = title;
        this.description = description;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userId = userId;
        this.display = display;
        this.photoUrl = photoUrl;
        this.date = date;
        this.likes = likes;
        this.dislikes = dislikes;
    }

    public VideoInformation(Video video) {
        this.id = video.getId().intValue();
        this.url = video.getUrl();
        this.title = video.getTitle();
        this.description = video.getDescription();
        this.firstName = video.getUser().getFirstName();
        this.lastName = video.getUser().getLastName();
        this.userId = video.getUser().getId();
        this.display = video.getDisplay();
        this.photoUrl = video.getPhotoUrl();
        this.date = video.getDate();
        this.likes = video.getLikes();
        this.dislikes = video.getDislikes();
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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getDisplay() {
        return display;
    }

    public void setDisplay(int display) {
        this.display = display;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
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
}
