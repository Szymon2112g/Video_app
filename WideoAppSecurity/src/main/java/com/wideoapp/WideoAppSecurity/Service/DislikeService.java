package com.wideoapp.WideoAppSecurity.Service;

public interface DislikeService {
    public boolean addDislike(int videoId, String email);
    public boolean isDislikeToVideo(int videoId, String email);
    public boolean subtractDislike(int videoId, String email);
}
