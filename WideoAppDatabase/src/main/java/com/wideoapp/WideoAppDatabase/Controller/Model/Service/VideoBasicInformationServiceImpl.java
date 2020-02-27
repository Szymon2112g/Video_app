package com.wideoapp.WideoAppDatabase.Controller.Model.Service;

import com.wideoapp.WideoAppDatabase.Controller.Model.VideoBasicInformation;
import com.wideoapp.WideoAppDatabase.Entity.Video;
import com.wideoapp.WideoAppDatabase.Service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class VideoBasicInformationServiceImpl implements VideoBasicInformationService{

    private VideoService videoService;

    @Autowired
    public VideoBasicInformationServiceImpl(VideoService videoService) {
        this.videoService = videoService;
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
}
