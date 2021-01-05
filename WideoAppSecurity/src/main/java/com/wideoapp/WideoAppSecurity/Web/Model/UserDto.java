package com.wideoapp.WideoAppSecurity.Web.Model;

import java.util.List;

public class UserDto {
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private List<VideoDto> videoList;
    private List<SubscribeDto> subscribeList;
    private List<DislikeDto> dislikeList;
    private List<HistoryDto> historyList;
    private List<LikesDto> likeList;
    private List<ReviewDto> reviews;

    public UserDto(int id, String firstName, String lastName, String email, String password, List<VideoDto> videoList, List<SubscribeDto> subscribeList, List<DislikeDto> dislikeList, List<HistoryDto> historyList, List<LikesDto> likeList, List<ReviewDto> reviews) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.videoList = videoList;
        this.subscribeList = subscribeList;
        this.dislikeList = dislikeList;
        this.historyList = historyList;
        this.likeList = likeList;
        this.reviews = reviews;
    }

    public UserDto() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<VideoDto> getVideoList() {
        return videoList;
    }

    public void setVideoList(List<VideoDto> videoList) {
        this.videoList = videoList;
    }

    public List<SubscribeDto> getSubscribeList() {
        return subscribeList;
    }

    public void setSubscribeList(List<SubscribeDto> subscribeList) {
        this.subscribeList = subscribeList;
    }

    public List<DislikeDto> getDislikeList() {
        return dislikeList;
    }

    public void setDislikeList(List<DislikeDto> dislikeList) {
        this.dislikeList = dislikeList;
    }

    public List<HistoryDto> getHistoryList() {
        return historyList;
    }

    public void setHistoryList(List<HistoryDto> historyList) {
        this.historyList = historyList;
    }

    public List<LikesDto> getLikeList() {
        return likeList;
    }

    public void setLikeList(List<LikesDto> likeList) {
        this.likeList = likeList;
    }

    public List<ReviewDto> getReviews() {
        return reviews;
    }

    public void setReviews(List<ReviewDto> reviews) {
        this.reviews = reviews;
    }
}

