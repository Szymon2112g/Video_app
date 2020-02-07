package com.wideoapp.WideoAppDatabase.Controller;

import com.wideoapp.WideoAppDatabase.Entity.VideoBasicInformation;
import com.wideoapp.WideoAppDatabase.Service.VideoBasicInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin( origins = "http://localhost:4200")
public class VideoBasicInformationRestController {

    private VideoBasicInformationService videoBasicInformationService;

    @Autowired
    public VideoBasicInformationRestController(VideoBasicInformationService videoBasicInformationService) {
        this.videoBasicInformationService = videoBasicInformationService;
    }

    @GetMapping("/videobasicinformation")
    public List<VideoBasicInformation> findAll() {
        return videoBasicInformationService.findAll();
    }
}
