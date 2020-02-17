package com.wideoapp.WideoAppDatabase.Controller;

import com.wideoapp.WideoAppDatabase.Controller.Model.VideoBasicInformation;
import com.wideoapp.WideoAppDatabase.Controller.Model.VideoBasicInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
