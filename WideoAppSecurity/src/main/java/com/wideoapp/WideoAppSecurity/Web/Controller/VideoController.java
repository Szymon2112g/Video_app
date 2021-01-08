package com.wideoapp.WideoAppSecurity.Web.Controller;
import com.wideoapp.WideoAppSecurity.Proxy.WideoAppFS;
import com.wideoapp.WideoAppSecurity.Service.*;
import com.wideoapp.WideoAppSecurity.Web.Model.ExtendedVideoInformation;
import com.wideoapp.WideoAppSecurity.Web.Model.ResponseMessage;
import com.wideoapp.WideoAppSecurity.Web.Model.SmallVideoInformation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

@RestController
@CrossOrigin(origins="http://localhost:4200")
public class VideoController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private WideoAppFS wideoAppFS;
    private VideoService videoService;

    @Autowired
    public VideoController(WideoAppFS wideoAppFS, VideoService videoService) {
        this.wideoAppFS = wideoAppFS;
        this.videoService = videoService;
    }

    //@PostMapping(path = "/send-video-to-db")
    @PostMapping(path = "/video/add/db")
    public ResponseEntity<?> sendVideoToDB(@RequestBody SmallVideoInformation smallVideoInformation) {
        if (smallVideoInformation == null) {
            ResponseEntity.badRequest().build();
        }

        videoService.addSpecificVideoToDB(smallVideoInformation);
        return ResponseEntity.ok().build();
    }

    //@GetMapping(path = "/get-video-feed/{category}/{id}")
    @GetMapping(path = "/video/get/feed/{category}/{id}")
    public ResponseEntity<?> getVideoFeed(
            @PathVariable("category") String category,
            @PathVariable("id") int id,
            @RequestParam("email") String email) {

        if (category == null || email == null) {
            return ResponseEntity.badRequest().build();
        }

        if (!checkIfStringIsEmail(email)) {
            ResponseEntity.badRequest().build();
        }

        switch (category) {
            case "history":
                return ResponseEntity.ok(videoService.getVideoFeedHistory(id, email));
            case "liked":
                return ResponseEntity.ok(videoService.getVideoFeedLiked(id, email));
            case "subscription":
                return ResponseEntity.ok(videoService.getVideoFeedSubscription(id, email));
        }

        return ResponseEntity.badRequest().build();
    }

    @Deprecated
    //@PostMapping(path = "/send-file", consumes = {"multipart/form-data"})
    @PostMapping(path = "/video/send-file", consumes = {"multipart/form-data"})
    public ResponseEntity<?> handleFileUpload(@RequestBody MultipartFile file) {

        String response = wideoAppFS.handleFileUpload(file);
        return ResponseEntity.ok(new ResponseMessage(response));
    }

    private boolean checkIfStringIsNumber(String number) {
        return number.matches("\\d+");
    }

    @Deprecated
    private boolean checkIfStringIsEmail(String email) {
        return true;
    }
}
