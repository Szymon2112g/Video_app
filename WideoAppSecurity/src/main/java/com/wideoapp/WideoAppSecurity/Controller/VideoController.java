package com.wideoapp.WideoAppSecurity.Controller;

import com.wideoapp.WideoAppSecurity.Controller.Model.*;
import com.wideoapp.WideoAppSecurity.Dao.*;
import com.wideoapp.WideoAppSecurity.Entity.*;
import com.wideoapp.WideoAppSecurity.Proxy.WideoAppDB;
import com.wideoapp.WideoAppSecurity.Proxy.WideoAppFS;
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
    private UserDao userDao;
    private VideoDao videoDao;
    private LikesDao likesDao;
    private SubscribeDao subscribeDao;
    private HistoryDao historyDao;

    @Autowired
    public VideoController(
            WideoAppFS wideoAppFS,
            UserDao userDao,
            VideoDao videoDao,
            LikesDao likesDao,
            SubscribeDao subscribeDao,
            HistoryDao historyDao
    ) {
        this.wideoAppFS = wideoAppFS;
        this.userDao = userDao;
        this.videoDao = videoDao;
        this.likesDao = likesDao;
        this.subscribeDao = subscribeDao;
        this.historyDao = historyDao;
    }

    @PostMapping(path = "/send-video-to-db")
    public ResponseEntity<?> sendVideoToDB(@RequestBody VideoToSend videoToSend) {
        User user = userDao.findByEmail(videoToSend.getEmail());

        String currentDate = (java.time.LocalDate.now()).toString();

        Video video = new Video(
                videoToSend.getUrl(),
                videoToSend.getTitle(),
                videoToSend.getDescription(),
                0,
                0,
                0,
                currentDate,
                null,
                null,
                videoToSend.getPhotoUrl()
        );

        user.getVideoList().add(video);
        userDao.save(user);

        return ResponseEntity.ok().build();
    }

    @GetMapping(path = "/get-address-url-file-server")
    public ResponseMessage getAddressUrlFileServer(){
        return new ResponseMessage(wideoAppFS.getAddressUrl());
    }

    @GetMapping(path = "/get-video-feed/{category}/{id}")
    public List<VideoInformation> getVideoFeed(
            @PathVariable("category") String category,
            @PathVariable("id") int id,
            @RequestParam("email") String email) {

        switch (category) {
            case "history":
                return getVideoFeedHistory(id, email);
            case "liked":
                return getVideoFeedLiked(id, email);
            case "subscription":
                return getVideoFeedSubscription(id, email);
        }

        return null;
    }

    public List<VideoInformation> getVideoFeedHistory(int id, String email) {

        User user = userDao.findByEmail(email);
        List<History> historyList = historyDao.findAllByUserIdOrderByIdDesc(user.getId());
        List<VideoInformation> videoInformations = new ArrayList<>();

        for (int i = (id-1)*10; i < id*10; i++) {

            if(i >= historyList.size()) {
                continue;
            }

            Video video = videoDao.findById(historyList.get(i).getVideoId());
            VideoInformation tmp = new VideoInformation(video);
            videoInformations.add(tmp);
        }

        return videoInformations;
    }

    public List<VideoInformation> getVideoFeedLiked(int id, String email) {

        User user = userDao.findByEmail(email);
        List<Likes> likes = likesDao.findAllByUserIdOrderByIdDesc(user.getId());
        List<VideoInformation> videoInformations = new ArrayList<>();

        for (int i = (id-1)*10; i < id*10; i++) {

            if(i >= likes.size()) {
                continue;
            }

            Video video = videoDao.findById(likes.get(i).getVideoId());
            VideoInformation tmp = new VideoInformation(video);
            videoInformations.add(tmp);
        }

        return videoInformations;
    }

    public List<VideoInformation> getVideoFeedSubscription(int id, String email) {

        User user = userDao.findByEmail(email);
        List<Subscribe> subscribes = subscribeDao.findAllByUserId(user.getId());

        List<VideoInformation> videoInformations = new ArrayList<>();
        List<Video> videoList = new ArrayList<>();

        for(Subscribe tmpSubscribe: subscribes) {
            User tmpUser = userDao.findById(tmpSubscribe.getUserSubscriptionId());
            videoList.addAll(videoDao.findAllByUserIdOrderByDateDesc(tmpUser.getId()));
        }

        Collections.sort(videoList, new Comparator<Video>() {
            @Override
            public int compare(Video o1, Video o2) {
                return o1.getId()>o2.getId() ? -1 : o1.getId()==o2.getId() ? 0 : 1;
            }
        });

        for (int i = (id-1)*10; i < id*10; i++) {

            if(i >= videoList.size()) {
                continue;
            }

            Video video = videoList.get(i);
            VideoInformation tmp = new VideoInformation(video);
            videoInformations.add(tmp);
        }

        return videoInformations;
    }
}
