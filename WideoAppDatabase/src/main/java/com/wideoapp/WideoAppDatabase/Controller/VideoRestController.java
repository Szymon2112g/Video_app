package com.wideoapp.WideoAppDatabase.Controller;

import com.wideoapp.WideoAppDatabase.DAO.VideoDAO;
import com.wideoapp.WideoAppDatabase.Entity.Video;
import com.wideoapp.WideoAppDatabase.Service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class VideoRestController {

    private VideoService videoService;

    @Autowired
    public VideoRestController(VideoService videoService) {
        this.videoService = videoService;
    }

    @GetMapping("/video")
    public List<Video> findAll() {
        return videoService.findAll();
    }
}
