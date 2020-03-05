package com.wideoapp.WideoAppDatabase.Controller;

import com.wideoapp.WideoAppDatabase.Controller.Model.VideoInformation;
import com.wideoapp.WideoAppDatabase.Controller.Model.Service.VideoInformationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin( origins = "http://localhost:4200")
public class VideoInformationRestController {

    private VideoInformationService videoInformationService;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public VideoInformationRestController(VideoInformationService videoInformationService) {
        this.videoInformationService = videoInformationService;
    }

    @GetMapping("/get-video-information/{category}")
    public List<VideoInformation> findVideoByCategory(@PathVariable("category") String category) {
        return videoInformationService.findByCategory(category);
    }

    @GetMapping("/get-video/{id}")
    public VideoInformation getVideo(@PathVariable("id") int id) {
        return videoInformationService.findById(id);
    }

    @GetMapping("/get-video-feed/ontime")
    public List<VideoInformation> getVideosOnTime() {
        return videoInformationService.getVideosOnTime();
    }

    @GetMapping("/search/tips/{key}")
    public List<String> findTipsByKey(@PathVariable("key") String key) {
        return videoInformationService.findTipsByKey(key);
    }

    @GetMapping("/search/search/{key}")
    public List<VideoInformation> getVideosOnTime(@PathVariable("key") String key) {
        return videoInformationService.findVideoByKey(key);
    }

    @GetMapping("/user/get-video/{userid}")
    public List<VideoInformation> findVideoByUserId(@PathVariable("userid") int userId) {
        return videoInformationService.findVideoByUserId(userId);
    }
}
