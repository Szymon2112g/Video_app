package com.wideoapp.WideoAppDatabase.Controller;

import com.wideoapp.WideoAppDatabase.Controller.Model.ResponseMessage;
import com.wideoapp.WideoAppDatabase.Controller.Model.VideoBasicInformation;
import com.wideoapp.WideoAppDatabase.Controller.Model.Service.VideoBasicInformationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin( origins = "http://localhost:4200")
public class VideoBasicInformationRestController {

    private VideoBasicInformationService videoBasicInformationService;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public VideoBasicInformationRestController(VideoBasicInformationService videoBasicInformationService) {
        this.videoBasicInformationService = videoBasicInformationService;
    }

    @GetMapping("/videobasicinformation/{category}")
    public List<VideoBasicInformation> findVideoByCategory(@PathVariable("category") String category) {
        return videoBasicInformationService.findByCategory(category);
    }

    @GetMapping("/getvideo/{id}")
    public VideoBasicInformation getVideo(@PathVariable("id") int id) {
        return videoBasicInformationService.findById(id);
    }

    @GetMapping("/get-video-feed/ontime")
    public List<VideoBasicInformation> getVideosOnTime() {
        return videoBasicInformationService.getVideosOnTime();
    }

    @GetMapping("/search/tips/{key}")
    public List<String> findTipsByKey(@PathVariable("key") String key) {
        return videoBasicInformationService.findTipsByKey(key);
    }

    @GetMapping("/search/search/{key}")
    public List<VideoBasicInformation> getVideosOnTime(@PathVariable("key") String key) {
        return videoBasicInformationService.findVideoByKey(key);
    }
}
