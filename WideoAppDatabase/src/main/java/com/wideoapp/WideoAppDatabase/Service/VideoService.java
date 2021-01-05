package com.wideoapp.WideoAppDatabase.Service;

import com.wideoapp.WideoAppDatabase.Web.Model.VideoDto;

import java.util.List;

public interface VideoService {
    public VideoDto findById(int id);
    public void increaseDisplay(VideoDto theVideo);
    public List<VideoDto> findByTableColumn(String category);
    public List<VideoDto> findAllOrderByDateDesc();
    public List<String> findTipsByKey(String key);
    public List<VideoDto> findVideoByKey(String key);
    public List<VideoDto> findVideoByUserId(int id);
}
