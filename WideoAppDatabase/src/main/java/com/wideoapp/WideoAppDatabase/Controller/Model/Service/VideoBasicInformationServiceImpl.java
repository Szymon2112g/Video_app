package com.wideoapp.WideoAppDatabase.Controller.Model.Service;

import com.wideoapp.WideoAppDatabase.Controller.Model.VideoBasicInformation;
import com.wideoapp.WideoAppDatabase.Entity.User;
import com.wideoapp.WideoAppDatabase.Entity.Video;
import com.wideoapp.WideoAppDatabase.Service.UserService;
import com.wideoapp.WideoAppDatabase.Service.VideoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class VideoBasicInformationServiceImpl implements VideoBasicInformationService{

    private VideoService videoService;
    private UserService userService;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public VideoBasicInformationServiceImpl(VideoService videoService, UserService userService) {
        this.videoService = videoService;
        this.userService = userService;
    }

    @Override
    public List<VideoBasicInformation> findAll() {
        List<VideoBasicInformation> videoBasicInformations = new ArrayList<>();

        for (Video video: videoService.findAll()) {
            VideoBasicInformation tmp = new VideoBasicInformation(
                    video.getId(), video.getUrl(), video.getTitle(), video.getDescription(),
                    video.getUser().getFirstName(), video.getUser().getLastName(), video.getUser().getId(),
                    video.getDisplay(), video.getPhotoUrl(), video.getDate(),
                    video.getLikes(), video.getDislikes());

            videoBasicInformations.add(tmp);
        }

        return videoBasicInformations;
    }

    @Override
    public VideoBasicInformation findById(int id) {

        Video video = videoService.findById(id);

        VideoBasicInformation videoBasicInformation = new VideoBasicInformation(
                video.getId(), video.getUrl(), video.getTitle(), video.getDescription(),
                video.getUser().getFirstName(), video.getUser().getLastName(), video.getUser().getId(),
                video.getDisplay(), video.getPhotoUrl(), video.getDate(), video.getLikes(), video.getDislikes()
        );

        return videoBasicInformation;
    }

    @Override
    public List<VideoBasicInformation> findByCategory(String category) {
        List<VideoBasicInformation> videoBasicInformations = new ArrayList<>();

        for (Video video: videoService.findByTableColumn(category)) {
            VideoBasicInformation tmp = new VideoBasicInformation(
                    video.getId(), video.getUrl(), video.getTitle(), video.getDescription(),
                    video.getUser().getFirstName(), video.getUser().getLastName(), video.getUser().getId(),
                    video.getDisplay(), video.getPhotoUrl(), video.getDate(),
                    video.getLikes(), video.getDislikes());
            videoBasicInformations.add(tmp);
        }
        return videoBasicInformations;
    }

}
