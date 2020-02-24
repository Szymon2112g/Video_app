package com.wideoapp.WideoAppDatabase.Controller;

import com.wideoapp.WideoAppDatabase.Entity.Video;
import com.wideoapp.WideoAppDatabase.Service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin( origins = "http://localhost:4200")
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

    @PostMapping(path = "/adddisplay")
    public ResponseEntity<?> addDisplay(@RequestBody Video video) {
        Video videoToSave = videoService.findById(video.getId());

        videoService.increaseDisplay(videoToSave);

        return ResponseEntity.ok().build();
    }

}
