package com.wideoapp.WideoAppDatabase.Service;

import com.wideoapp.WideoAppDatabase.Entity.Video;

import java.util.List;

public interface VideoService {
    public List<Video> findAll();
    public Video findById(int id);
    public void increaseDisplay(Video theVideo);
}
