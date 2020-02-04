package com.wideoapp.WideoAppDatabase.Controller;

import com.wideoapp.WideoAppDatabase.DAO.VideoDAO;
import com.wideoapp.WideoAppDatabase.Entity.Video;
import com.wideoapp.WideoAppDatabase.Service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class VideoRestController {

    private VideoService videoService;

    @Autowired
    public VideoRestController(VideoService videoService) {
        this.videoService = videoService;
    }

    @GetMapping("/video")
    public List<Video> findAll() {
        return videoService.findAll();
    }

    @GetMapping("/videoinformation")
    public List<VideoSpecifics> GetAllVideoSpecifics(){

        List<VideoSpecifics> videoSpecifics = new ArrayList<>();

        for (Video video: videoService.findAll()) {
            VideoSpecifics tmp = new VideoSpecifics(video.getId(),video.getUrl(),
                    video.getTitle(),video.getDescription(),video.getUser().getFirstName(),video.getUser().getLastName());
            videoSpecifics.add(tmp);

        }

        return videoSpecifics;
    }

}

class VideoSpecifics {
    private int id;
    private String url;
    private String title;
    private String description;
    private String firstName;
    private String lastName;

    public VideoSpecifics(int id, String url, String title, String description, String firstName, String lastName) {
        this.id = id;
        this.url = url;
        this.title = title;
        this.description = description;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
