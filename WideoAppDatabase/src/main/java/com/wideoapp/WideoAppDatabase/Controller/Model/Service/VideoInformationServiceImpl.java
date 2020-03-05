package com.wideoapp.WideoAppDatabase.Controller.Model.Service;

import com.wideoapp.WideoAppDatabase.Controller.Model.VideoInformation;
import com.wideoapp.WideoAppDatabase.Entity.Video;
import com.wideoapp.WideoAppDatabase.Service.UserService;
import com.wideoapp.WideoAppDatabase.Service.VideoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class VideoInformationServiceImpl implements VideoInformationService {

    private VideoService videoService;
    private UserService userService;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public VideoInformationServiceImpl(VideoService videoService, UserService userService) {
        this.videoService = videoService;
        this.userService = userService;
    }

    @Override
    public List<VideoInformation> findAll() {
        List<VideoInformation> videoInformations = new ArrayList<>();

        for (Video video: videoService.findAll()) {
            VideoInformation tmp = new VideoInformation(video);
            videoInformations.add(tmp);
        }

        return videoInformations;
    }

    @Override
    public VideoInformation findById(int id) {

        Video video = videoService.findById(id);
        VideoInformation videoInformation = new VideoInformation(video);
        return videoInformation;
    }

    @Override
    public List<VideoInformation> findByCategory(String category) {

        List<VideoInformation> videoInformations = new ArrayList<>();

        for (Video video: videoService.findByTableColumn(category)) {
            VideoInformation tmp = new VideoInformation(video);
            videoInformations.add(tmp);
        }
        return videoInformations;
    }

    @Override
    public List<VideoInformation> getVideosOnTime() {

        List<Video> videoList = videoService.findAllOrderByDateDesc();

        Collections.sort(videoList, (o1, o2) -> Integer.compare(o2.getDisplay(), o1.getDisplay()));

        for (int i = 10; i< videoList.size(); i++) {
            videoList.remove(i);
        }

        List<VideoInformation> videoInformations = new ArrayList<>();

        for (Video video: videoList) {
            VideoInformation tmp = new VideoInformation(video);
            videoInformations.add(tmp);
        }

        return videoInformations;
    }

    @Override
    public List<String> findTipsByKey(String key) {
        return videoService.findTipsByKey(key);
    }

    @Override
    public List<VideoInformation> findVideoByKey(String key) {

        List<Video> videoList = videoService.findVideoByKey(key);

        List<VideoInformation> videoInformations = new ArrayList<>();

        for (Video video: videoList) {
            VideoInformation tmp = new VideoInformation(video);
            videoInformations.add(tmp);
        }

        return videoInformations;
    }

    @Override
    public List<VideoInformation> findVideoByUserId(int userId) {

        List<VideoInformation> videoInformations = new ArrayList<>();

        for(Video video: videoService.findVideoByUserId(userId)) {
            VideoInformation tmp = new VideoInformation(video);
            videoInformations.add(tmp);
        }

        return videoInformations;
    }
}
