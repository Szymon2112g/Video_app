package com.wideoapp.WideoAppDatabase.Controller.Model;

public class ReviewInformation {

    private String comment;
    private String firstName;
    private String lastName;

    public ReviewInformation(String comment, String firstName, String lastName) {
        this.comment = comment;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
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
