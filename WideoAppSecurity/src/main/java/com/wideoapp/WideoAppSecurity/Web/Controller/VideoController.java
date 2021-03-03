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
//@CrossOrigin(origins="http://localhost:4200")
public class VideoController {

    private WideoAppFS wideoAppFS;
    private VideoService videoService;
    private JwtTokenService jwtTokenService;

    @Autowired
    public VideoController(WideoAppFS wideoAppFS, VideoService videoService, JwtTokenService jwtTokenService) {
        this.wideoAppFS = wideoAppFS;
        this.videoService = videoService;
        this.jwtTokenService = jwtTokenService;
    }

    //@PostMapping(path = "/send-video-to-db")
    @PostMapping(path = "/video/file/db")
    public ResponseEntity<?> sendVideoToDB(@RequestBody SmallVideoInformation smallVideoInformation) {

        if (smallVideoInformation == null) {
            ResponseEntity.badRequest().build();
        }

        videoService.addSpecificVideoToDB(smallVideoInformation);
        return ResponseEntity.ok().build();
    }

    //@GetMapping(path = "/get-video-feed/{category}/{id}")
    @GetMapping(path = "/video/feed/history/{id}")
    public ResponseEntity<?> getVideoHistoryFeed(
            @RequestHeader Map<String, String> header,
            @PathVariable("id") int id) {

        if (id < 0) {
            return ResponseEntity.badRequest().build();
        }

        String email = jwtTokenService.findEmailFromTokenOfHeader(header);

        List<ExtendedVideoInformation> historyFeed = videoService.getVideoFeedHistory(id, email);

        return ResponseEntity.ok(historyFeed);
    }

    //@GetMapping(path = "/get-video-feed/{category}/{id}")
    @GetMapping(path = "/video/feed/liked/{id}")
    public ResponseEntity<?> getVideoLikeFeed(
            @RequestHeader Map<String, String> header,
            @PathVariable("id") int id) {

        if (id < 0) {
            return ResponseEntity.badRequest().build();
        }

        String email = jwtTokenService.findEmailFromTokenOfHeader(header);

        List<ExtendedVideoInformation> likeFeed = videoService.getVideoFeedLiked(id, email);

        return ResponseEntity.ok(likeFeed);
    }

    //@GetMapping(path = "/get-video-feed/{category}/{id}")
    @GetMapping(path = "/video/feed/Subscription/{id}")
    public ResponseEntity<?> getVideoSubscriptionFeed(
            @RequestHeader Map<String, String> header,
            @PathVariable("id") int id) {

        if (id < 0) {
            return ResponseEntity.badRequest().build();
        }

        String email = jwtTokenService.findEmailFromTokenOfHeader(header);

        List<ExtendedVideoInformation> subscriptionFeed = videoService.getVideoFeedSubscription(id, email);

        return ResponseEntity.ok(subscriptionFeed);
    }

    //@PostMapping(path = "/send-file", consumes = {"multipart/form-data"})
    @PostMapping(path = "/video/send-file", consumes = {"multipart/form-data"})
    public ResponseEntity<?> handleFileUpload(@RequestBody MultipartFile file) {

        String response = wideoAppFS.handleFileUpload(file);
        return ResponseEntity.ok(new ResponseMessage(response));
    }
}
