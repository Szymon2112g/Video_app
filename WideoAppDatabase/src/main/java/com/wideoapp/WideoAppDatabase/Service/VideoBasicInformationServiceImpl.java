package com.wideoapp.WideoAppDatabase.Service;

import com.wideoapp.WideoAppDatabase.Entity.Video;
import com.wideoapp.WideoAppDatabase.Entity.VideoBasicInformation;
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
                    video.getUser().getFirstName(), video.getUser().getLastName());

            videoBasicInformations.add(tmp);
        }

        return videoBasicInformations;
    }
}
