package com.wideoapp.WideoAppDatabase.Controller;

import com.wideoapp.WideoAppDatabase.Controller.Model.VideoBasicInformation;
import com.wideoapp.WideoAppDatabase.Controller.Model.Service.VideoBasicInformationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/videobasicinformation")
    public List<VideoBasicInformation> findAll() {
        return videoBasicInformationService.findAll();
    }

    @GetMapping("/getvideo/{id}")
    public VideoBasicInformation getVideo(@PathVariable("id") int id) {
        return videoBasicInformationService.findById(id);
    }

}
