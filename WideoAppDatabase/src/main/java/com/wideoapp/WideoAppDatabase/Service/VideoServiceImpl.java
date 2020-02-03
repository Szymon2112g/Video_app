package com.wideoapp.WideoAppDatabase.Service;

import com.wideoapp.WideoAppDatabase.DAO.VideoDAO;
import com.wideoapp.WideoAppDatabase.Entity.Video;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VideoServiceImpl implements VideoService {

    private VideoDAO videoDAO;

    @Autowired
    public VideoServiceImpl(VideoDAO videoDAO) {
        this.videoDAO = videoDAO;
    }

    @Override
    public List<Video> findAll() {
        return videoDAO.findAll();
    }
}
