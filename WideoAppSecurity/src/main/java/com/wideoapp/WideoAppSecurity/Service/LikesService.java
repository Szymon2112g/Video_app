package com.wideoapp.WideoAppSecurity.Service;

public interface LikesService {
    public boolean addLike(int videoId, String email);
    public boolean isLikeToVideo(int id, String email);
    public boolean subtractLike(int videoId, String email);
}
