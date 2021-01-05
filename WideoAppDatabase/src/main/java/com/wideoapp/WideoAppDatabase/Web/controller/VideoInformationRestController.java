package com.wideoapp.WideoAppDatabase.Web.controller;

import com.wideoapp.WideoAppDatabase.Web.Model.ExtendedVideoInformation;
import com.wideoapp.WideoAppDatabase.Service.VideoInformationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@CrossOrigin( origins = "http://localhost:4200")
public class VideoInformationRestController {

    private VideoInformationService videoInformationService;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public VideoInformationRestController(VideoInformationService videoInformationService) {
        this.videoInformationService = videoInformationService;
    }

    //@GetMapping("/get-video-information/{category}")
    @GetMapping("/videos-extended-information/category/{category}")
    public List<ExtendedVideoInformation> findVideoByCategory(@PathVariable("category") String category) {
        return videoInformationService.findByCategory(category);
    }

    //@GetMapping("/get-video/{id}")
    @GetMapping("/video/{id}")
    public ExtendedVideoInformation getVideo(@PathVariable("id") int id) {
        return videoInformationService.findById(id);
    }

    //@GetMapping("/get-video-feed/ontime")
    @GetMapping("/videos/ontime")
    public List<ExtendedVideoInformation> getVideosOnTime() {
        return videoInformationService.getVideosOnTime();
    }

    //@GetMapping("/search/tips/{key}")
    @GetMapping("/search/video/tips/{key}")
    public List<String> findTipsByKey(@PathVariable("key") String key) {
        return videoInformationService.findTipsByKey(key);
    }

    //@GetMapping("/search/search/{key}")
    @GetMapping("/search/video/key/{key}")
    public List<ExtendedVideoInformation> findVideosByKey(@PathVariable("key") String key) {
        return videoInformationService.findVideoByKey(key);
    }

    //@GetMapping("/user/get-video/{userid}")
    @GetMapping("/user/videos/{userid}")
    public List<ExtendedVideoInformation> findVideoByUserId(@PathVariable("userid") int userId) {
        return videoInformationService.findVideoByUserId(userId);
    }
}
