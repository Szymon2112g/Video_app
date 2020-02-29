package com.wideoapp.WideoAppDatabase.Controller.Model.Service;

import com.wideoapp.WideoAppDatabase.Controller.Model.VideoBasicInformation;

import java.util.List;

public interface VideoBasicInformationService {
    public List<VideoBasicInformation> findAll();
    public VideoBasicInformation findById(int id);
    public List<VideoBasicInformation> findByCategory(String category);
}
