package com.wideoapp.WideoAppDatabase.DAO;

import com.wideoapp.WideoAppDatabase.Entity.Video;
import com.wideoapp.WideoAppDatabase.Web.Model.VideoDto;

import java.util.List;

public interface VideoDAO {
    public List<Video> findAll();
    public Video findById(int id);
    public void increaseDisplay(Video Video);
    public List<Video> findByTableColumn(String category);
    public List<Video> findAllOrderByDateDesc();
    public List<String> findTipsByKey(String key);
    public List<Video> findVideoByKey(String key);
    public List<Video> findVideoByUserId(int id);
}
