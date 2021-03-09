package com.wideoapp.WideoAppDatabase.Web.controller;

import com.wideoapp.WideoAppDatabase.Web.Model.ExtendedVideoInformation;
import com.wideoapp.WideoAppDatabase.Service.VideoInformationService;
import com.wideoapp.WideoAppDatabase.Web.Model.VideoDto;
import com.wideoapp.WideoAppDatabase.Web.controller.Exception.InvalidInputException;
import com.wideoapp.WideoAppDatabase.Web.controller.Exception.NoFoundObjectException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<?> findLatestVideo() throws NoFoundObjectException {

        List<ExtendedVideoInformation> videoInformationList = videoInformationService.findLatestVideo();

        if (videoInformationList == null || videoInformationList.isEmpty()) {
            throw new NoFoundObjectException("no found the latest videos");
        }

        return ResponseEntity.ok(videoInformationList);
    }

    @GetMapping("/videos/category/most-display")
    public ResponseEntity<?> findTheMostDisplayVideo() throws NoFoundObjectException {

        List<ExtendedVideoInformation> videoInformationList = videoInformationService.findTheMostDisplayVideo();

        if (videoInformationList == null || videoInformationList.isEmpty()) {
            throw new NoFoundObjectException("no found the most display videos");
        }

        return ResponseEntity.ok(videoInformationList);
    }

    @GetMapping("/videos/category/most-likes")
    public ResponseEntity<?> findTheMostLikesVideo() throws NoFoundObjectException {

        List<ExtendedVideoInformation> videoInformationList = videoInformationService.findTheMostLikesVideo();

        if (videoInformationList == null || videoInformationList.isEmpty()) {
            throw new NoFoundObjectException("no found the most likes videos");
        }

        return ResponseEntity.ok(videoInformationList);
    }

    @GetMapping("/videos/category/most-dislikes")
    public ResponseEntity<?> findTheMostDislikesVideo() throws NoFoundObjectException {

        List<ExtendedVideoInformation> videoInformationList = videoInformationService.findTheMostDislikesVideo();

        if (videoInformationList == null || videoInformationList.isEmpty()) {
            throw new NoFoundObjectException("no found the most dislikes videos");
        }

        return ResponseEntity.ok(videoInformationList);
    }

    //@GetMapping("/get-video/{id}")
    @GetMapping("/video/watch/{videoId}")
    public ResponseEntity<?> getVideo(@PathVariable("videoId") int videoId)
            throws InvalidInputException, NoFoundObjectException {

        if (videoId <= 0) {
            throw new InvalidInputException("video id is no valid");
        }

        ExtendedVideoInformation extendedVideoInformation = videoInformationService.findById(videoId);

        if (extendedVideoInformation == null) {
            throw new NoFoundObjectException("no found video");
        }

        videoInformationService.increaseDisplay(extendedVideoInformation);

        return ResponseEntity.ok(extendedVideoInformation);
    }

    //@GetMapping("/get-video-feed/ontime")
    @GetMapping(path = "/videos/ontime", produces = { "application/json" })
    public ResponseEntity<?> getVideosOnTime() throws NoFoundObjectException {

        List<ExtendedVideoInformation> videoInformationList = videoInformationService.getVideosOnTime();

        if (videoInformationList == null || videoInformationList.isEmpty()) {
            throw new NoFoundObjectException("no found videos on time");
        }

        return ResponseEntity.ok(videoInformationList);
    }

    //@GetMapping("/search/tips/{key}")
    @GetMapping("/search/video/tips/{key}")
    public ResponseEntity<?> findTipsByKey(@PathVariable("key") String key)
            throws NoFoundObjectException, InvalidInputException {

        if (key == null || key.isEmpty() || key.length() < 3) {
            throw new InvalidInputException("key is too short, minimum 3 signs");
        }

        List<String> videoInformationList = videoInformationService.findTipsByKey(key);

        if (videoInformationList == null || videoInformationList.isEmpty()) {
            throw new NoFoundObjectException("no found tips by key");
        }

        return ResponseEntity.ok(videoInformationList);
    }

    //@GetMapping("/search/search/{key}")
    @GetMapping("/search/video/key/{key}")
    public ResponseEntity<?> findVideosByKey(@PathVariable("key") String key) throws InvalidInputException, NoFoundObjectException {

        if (key == null || key.isEmpty() || key.length() < 3) {
            throw new InvalidInputException("key is too short, minimum 3 signs");
        }

        List<ExtendedVideoInformation> videoInformationList = videoInformationService.findVideoByKey(key);

        if (videoInformationList == null || videoInformationList.isEmpty()) {
            throw new NoFoundObjectException("no found video by tips key");
        }

        return ResponseEntity.ok(videoInformationList);
    }

    //@GetMapping("/user/get-video/{userid}")
    @GetMapping("/user/videos/{userid}")
    public ResponseEntity<?> findVideosByUserId(@PathVariable("userid") int userId) throws InvalidInputException, NoFoundObjectException {

        if (userId <= 0) {
            throw new InvalidInputException("userId is invalid");
        }

        List<ExtendedVideoInformation> videoInformationList = videoInformationService.findVideoByUserId(userId);

        if (videoInformationList == null || videoInformationList.isEmpty()) {
            throw new NoFoundObjectException("no found videos");
        }

        return ResponseEntity.ok(videoInformationList);
    }
}
