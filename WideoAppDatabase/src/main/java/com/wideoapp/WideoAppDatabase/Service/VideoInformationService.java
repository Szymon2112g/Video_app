package com.wideoapp.WideoAppDatabase.Service;

import com.wideoapp.WideoAppDatabase.Web.Model.ExtendedVideoInformation;

import java.util.List;

public interface VideoInformationService {
    public ExtendedVideoInformation findById(int id);
    public List<ExtendedVideoInformation> findByCategory(String category);
    public List<ExtendedVideoInformation> getVideosOnTime();
    public List<String> findTipsByKey(String key);
    public List<ExtendedVideoInformation> findVideoByKey(String key);
    public List<ExtendedVideoInformation> findVideoByUserId(int userId);
}
