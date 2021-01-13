package com.wideoapp.WideoAppDatabase.Web.controller;

import com.wideoapp.WideoAppDatabase.Service.VideoService;
import com.wideoapp.WideoAppDatabase.Web.Model.VideoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
//@CrossOrigin( origins = "http://localhost:4200")
public class VideoRestController {

    private VideoService videoService;

    @Autowired
    public VideoRestController(VideoService videoService) {
        this.videoService = videoService;
    }


    // Raczej do skasowania
    //@PostMapping(path = "/add-display")
    @PostMapping(path = "/display")
    public ResponseEntity<?> addDisplay(@RequestBody VideoDto video) {
        VideoDto videoToSave = videoService.findById(video.getId());
        videoService.increaseDisplay(videoToSave);
        return ResponseEntity.ok().build();
    }
}
