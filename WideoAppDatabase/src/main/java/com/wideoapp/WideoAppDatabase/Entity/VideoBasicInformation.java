package com.wideoapp.WideoAppDatabase.Entity;

public class VideoBasicInformation {

        private int id;
        private String url;
        private String title;
        private String description;
        private String firstName;
        private String lastName;

        public VideoBasicInformation(int id, String url, String title, String description, String firstName, String lastName) {
            this.id = id;
            this.url = url;
            this.title = title;
            this.description = description;
            this.firstName = firstName;
            this.lastName = lastName;
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
}
