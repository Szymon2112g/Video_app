package com.wideoapp.WideoAppSecurity.Service;

public interface LikesService {
    public void addLike(int videoId, String email);
    public boolean isLikeToVideo(int id, String email);
    public void subtractLike(int videoId, String email);
}
