package com.wideoapp.WideoAppDatabase.Service;

import com.wideoapp.WideoAppDatabase.DAO.VideoDAO;
import com.wideoapp.WideoAppDatabase.Entity.Video;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class VideoServiceImpl implements VideoService {

    private VideoDAO videoDAO;

    @Autowired
    public VideoServiceImpl(VideoDAO videoDAO) {
        this.videoDAO = videoDAO;
    }

    @Override
    @Transactional
    public List<Video> findAll() {
        return videoDAO.findAll();
    }

    @Override
    @Transactional
    public Video findById(int id) {
        return videoDAO.findById(id);
    }

    @Override
    @Transactional
    public void increaseDisplay(Video theVideo) {
        videoDAO.increaseDisplay(theVideo);
    }

    @Override
    @Transactional
    public List<Video> findByTableColumn(String category) {
        return videoDAO.findByTableColumn(category);
    }

    @Override
    @Transactional
    public List<Video> findAllOrderByDateDesc() {
        return videoDAO.findAllOrderByDateDesc();
    }

    @Override
    @Transactional
    public List<String> findTipsByKey(String key) {
        return videoDAO.findTipsByKey(key);
    }

    @Override
    @Transactional
    public List<Video> findVideoByKey(String key) {
        return videoDAO.findVideoByKey(key);
    }
}
