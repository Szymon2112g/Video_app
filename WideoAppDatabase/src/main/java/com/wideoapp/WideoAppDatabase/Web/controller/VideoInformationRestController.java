package com.wideoapp.WideoAppDatabase.Web.controller;

import com.wideoapp.WideoAppDatabase.Web.Model.ExtendedVideoInformation;
import com.wideoapp.WideoAppDatabase.Service.VideoInformationService;
import com.wideoapp.WideoAppDatabase.Web.Model.VideoDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@CrossOrigin( origins = "http://localhost:4200")
public class VideoInformationRestController {

    private VideoInformationService videoInformationService;

    @Autowired
    public VideoInformationRestController(VideoInformationService videoInformationService) {
        this.videoInformationService = videoInformationService;
    }

    @GetMapping("/videos/category/latest")
    public List<ExtendedVideoInformation> findLatestVideo() {
        return videoInformationService.findLatestVideo();
    }

    @GetMapping("/videos/category/most-display")
    public List<ExtendedVideoInformation> findTheMostDisplayVideo() {
        return videoInformationService.findTheMostDisplayVideo();
    }

    @GetMapping("/videos/category/most-likes")
    public List<ExtendedVideoInformation> findTheMostLikesVideo() {
        return videoInformationService.findTheMostLikesVideo();
    }

    @GetMapping("/videos/category/most-dislikes")
    public List<ExtendedVideoInformation> findTheMostDislikesVideo() {
        return videoInformationService.findTheMostDislikesVideo();
    }

    //@GetMapping("/get-video/{id}")
    @GetMapping("/video/watch/{id}")
    public ExtendedVideoInformation getVideo(@PathVariable("id") int id) {

        ExtendedVideoInformation extendedVideoInformation = videoInformationService.findById(id);
        videoInformationService.increaseDisplay(extendedVideoInformation);

        return extendedVideoInformation;
    }

    //@GetMapping("/get-video-feed/ontime")
    @GetMapping(path = "/videos/ontime", produces = { "application/json" })
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
    public List<ExtendedVideoInformation> findVideosByUserId(@PathVariable("userid") int userId) {
        return videoInformationService.findVideoByUserId(userId);
    }
}
