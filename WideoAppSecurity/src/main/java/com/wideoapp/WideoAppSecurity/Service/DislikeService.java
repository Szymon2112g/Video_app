package com.wideoapp.WideoAppSecurity.Service;

public interface DislikeService {
    @Deprecated
    public void addDislike(int videoId, String email);
    public boolean isDislikeToVideo(int id, String email);
    @Deprecated
    public void subtractDislike(int videoId, String email);
}
