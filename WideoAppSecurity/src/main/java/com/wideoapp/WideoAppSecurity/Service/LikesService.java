package com.wideoapp.WideoAppSecurity.Service;

public interface LikesService {
    @Deprecated
    public void addLike(int videoId, String email);
    public boolean isLikeToVideo(int id, String email);
    @Deprecated
    public void subtractLike(int videoId, String email);
}
