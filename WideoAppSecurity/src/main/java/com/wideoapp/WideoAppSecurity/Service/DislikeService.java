package com.wideoapp.WideoAppSecurity.Service;

public interface DislikeService {
    public void addDislike(int videoId, String email);
    public boolean isDislikeToVideo(int videoId, String email);
    public void subtractDislike(int videoId, String email);
}
