package com.wideoapp.WideoAppSecurity.Controller.Model;

public class VideoToSend {

    private String email;
    private String url;
    private String title;
    private String description;
    private int display;
    private String photoUrl;

    public VideoToSend(String email, String url, String title, String description, int display, String photoUrl) {
        this.email = email;
        this.url = url;
        this.title = title;
        this.description = description;
        this.display = display;
        this.photoUrl = photoUrl;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    @Override
    public String toString() {
        return email +" "+
        url +" "+
        title +" "+
        description +" "+
        display +" "+
        photoUrl ;
    }
}

