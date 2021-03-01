package com.wideoapp.WideoAppDatabase.DAO;

import com.wideoapp.WideoAppDatabase.Entity.Video;

import java.util.List;

public interface VideoDAO {
    List<Video> findAll();
    Video findById(int id);
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
