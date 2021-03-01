package com.wideoapp.WideoAppDatabase.Web.Model;

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

    public static Builder getBuilder() {
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

        public Builder setId(int id) {
            this.id = id;
            return this;
        }

        public Builder setUrl(String url) {
            this.url = url;
            return this;
        }

        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder setDescription(String description) {
            this.description = description;
            return this;
        }

        public Builder setFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder setLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Builder setUserId(int userId) {
            this.userId = userId;
            return this;
        }

        public Builder setDisplay(int display) {
            this.display = display;
            return this;
        }

        public Builder setPhotoUrl(String photoUrl) {
            this.photoUrl = photoUrl;
            return this;
        }

        public Builder setDate(String date) {
            this.date = date;
            return this;
        }

        public Builder setLikes(int likes) {
            this.likes = likes;
            return this;
        }

        public Builder setDislikes(int dislikes) {
            this.dislikes = dislikes;
            return this;
        }

        public ExtendedVideoInformation build() {
            this.extendedVideoInformation = new ExtendedVideoInformation();

            this.extendedVideoInformation.setId(this.id);
            this.extendedVideoInformation.setUrl(this.url);
            this.extendedVideoInformation.setTitle(this.title);
            this.extendedVideoInformation.setDescription(this.description);
            this.extendedVideoInformation.setFirstName(this.firstName);
            this.extendedVideoInformation.setLastName(this.lastName);
            this.extendedVideoInformation.setUserId(this.userId);
            this.extendedVideoInformation.setDisplay(this.display);
            this.extendedVideoInformation.setPhotoUrl(this.photoUrl);
            this.extendedVideoInformation.setDate(this.date);
            this.extendedVideoInformation.setLikes(this.likes);
            this.extendedVideoInformation.setDislikes(this.dislikes);

            return this.extendedVideoInformation;
        }

        public ExtendedVideoInformation getExtendedVideoInformation() {
            return this.extendedVideoInformation;
        }
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

    public ExtendedVideoInformation() {
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



