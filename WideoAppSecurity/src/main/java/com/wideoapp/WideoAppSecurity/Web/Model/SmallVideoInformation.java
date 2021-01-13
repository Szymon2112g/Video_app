package com.wideoapp.WideoAppSecurity.Web.Model;

public class SmallVideoInformation {

    private String email;
    private String url;
    private String title;
    private String description;
    private int display;
    private String photoUrl;

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String email;
        private String url;
        private String title;
        private String description;
        private int display;
        private String photoUrl;

        private SmallVideoInformation smallVideoInformation;

        public Builder email(String email) {
            this.email = email;
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

        public Builder display(int display) {
            this.display = display;
            return this;
        }

        public Builder photoUrl(String photoUrl) {
            this.photoUrl = photoUrl;
            return this;
        }

        public SmallVideoInformation build() {
            this.smallVideoInformation = new SmallVideoInformation();

            this.smallVideoInformation.setDescription(description);
            this.smallVideoInformation.setDisplay(display);
            this.smallVideoInformation.setEmail(email);
            this.smallVideoInformation.setPhotoUrl(photoUrl);
            this.smallVideoInformation.setTitle(title);
            this.smallVideoInformation.setUrl(url);

            return this.smallVideoInformation;
        }
    }

    public SmallVideoInformation() {
    }

    public SmallVideoInformation(String email, String url, String title, String description, int display, String photoUrl) {
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

