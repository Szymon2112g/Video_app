package com.wideoapp.WideoAppDatabase.Controller.Model.Service;

import com.wideoapp.WideoAppDatabase.Controller.Model.VideoInformation;

import java.util.List;

public interface VideoInformationService {
    public List<VideoInformation> findAll();
    public VideoInformation findById(int id);
    public List<VideoInformation> findByCategory(String category);
    public List<VideoInformation> getVideosOnTime();
    public List<String> findTipsByKey(String key);
    public List<VideoInformation> findVideoByKey(String key);
    public List<VideoInformation> findVideoByUserId(int userId);
}
