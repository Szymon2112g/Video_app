package com.wideoapp.WideoAppDatabase.DAO;

import com.wideoapp.WideoAppDatabase.Entity.Video;

import java.util.List;
import java.util.Optional;

public interface VideoDAO {
    List<Video> findAll();
    Optional<Video> findById(int id);
    void increaseDisplay(Video Video);
    List<Video> findTheMostDisplayVideo();
    List<Video> findTheMostLikesVideo();
    List<Video> findTheMostDislikesVideo();
    List<Video> findLatestVideo();
    List<Video> findAllOrderByDateDesc();
    List<String> findTipsByKey(String key);
    List<Video> findVideoByKey(String key);
    List<Video> findVideoByUserId(int id);
}
