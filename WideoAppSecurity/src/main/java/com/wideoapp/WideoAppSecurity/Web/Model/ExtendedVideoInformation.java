package com.wideoapp.WideoAppSecurity.Web.Model;

public class ExtendedVideoInformation {

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

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
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

        private ExtendedVideoInformation extendedVideoInformation;

        public Builder() {
        }

        public Builder id(int id) {
            this.id = id;
            return this;
        }

        public Builder url(String url) {
            this.url = url;
            return this;
        }

        public Builder title(String title) {
            this.title = title;
            return this;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public Builder firstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder lastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Builder userId(int userId) {
            this.userId = userId;
            return this;
        }

        public Builder display(int display) {
            this.display = display;
            return this;
        }

        public Builder photoUrl(String photoUrl) {
            this.photoUrl = photoUrl;
            return this;
        }

        public Builder date(String date) {
            this.date = date;
            return this;
        }

        public Builder likes(int likes) {
            this.likes = likes;
            return this;
        }

        public Builder dislikes(int dislikes) {
            this.dislikes = dislikes;
            return this;
        }

        public ExtendedVideoInformation build() {
            this.extendedVideoInformation = new ExtendedVideoInformation();

            this.extendedVideoInformation.setDate(date);
            this.extendedVideoInformation.setDescription(description);
            this.extendedVideoInformation.setDislikes(dislikes);
            this.extendedVideoInformation.setDisplay(display);
            this.extendedVideoInformation.setFirstName(firstName);
            this.extendedVideoInformation.setId(id);
            this.extendedVideoInformation.setLastName(lastName);
            this.extendedVideoInformation.setLikes(likes);
            this.extendedVideoInformation.setPhotoUrl(photoUrl);
            this.extendedVideoInformation.setTitle(title);
            this.extendedVideoInformation.setUrl(url);

            return extendedVideoInformation;
        }

    }

    public ExtendedVideoInformation() {
    }

    public ExtendedVideoInformation(
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



