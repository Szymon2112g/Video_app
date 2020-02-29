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
public class MainController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private WideoAppDB wideoAppDB;
    private WideoAppFS wideoAppFS;
    private UserDao userDao;
    private VideoDao videoDao;
    private ReviewDAO reviewDAO;
    private LikesDao likesDao;
    private DislikesDao dislikesDao;
    private SubscribeDao subscribeDao;
    private HistoryDao historyDao;


    @Autowired
    public MainController(WideoAppDB wideoAppDB, WideoAppFS wideoAppFS, UserDao userDao, VideoDao videoDao, ReviewDAO reviewDAO, LikesDao likesDao,DislikesDao dislikesDao, SubscribeDao subscribeDao, HistoryDao historyDao) {
        this.wideoAppDB = wideoAppDB;
        this.wideoAppFS = wideoAppFS;
        this.userDao = userDao;
        this.videoDao = videoDao;
        this.reviewDAO = reviewDAO;
        this.likesDao = likesDao;
        this.dislikesDao = dislikesDao;
        this.subscribeDao = subscribeDao;
        this.historyDao = historyDao;
    }

    @PostMapping(path = "/sendvideofile", consumes = {"multipart/form-data","application/json"})
    public ResponseEntity<?> SendVideoFile(@RequestBody MultipartFile file) {

        if(file == null) {
            logger.error("File is equal null");
            return ResponseEntity.noContent().build();
        }

        ResponseEntity<String> responseEntity = wideoAppFS.handleFileUpload(file);
        return ResponseEntity.ok(new ResponseMessage(responseEntity.getBody()));
    }

    @PostMapping(path = "/sendvideotodb")
    public ResponseEntity<?> sendVideoToDB(@RequestBody VideoToSend videoToSend)
    {
        User user = userDao.findByEmail(videoToSend.getEmail());

        String currentDate = (java.time.LocalDate.now()).toString();

        Video video = new Video(videoToSend.getUrl(), videoToSend.getTitle(), videoToSend.getDescription(),
                                0, 0, 0 ,currentDate, null, null, videoToSend.getPhotoUrl());

        user.getVideoList().add(video);
        userDao.save(user);

        return ResponseEntity.ok().build();
    }

    @GetMapping(path = "/get-address-url-file-server")
    public ResponseMessage getAddressUrlFileServer(){
        return new ResponseMessage(wideoAppFS.getAddressUrl());
    }

    @PostMapping(path = "/addreview/")
    public ResponseEntity<?> addReview(@RequestBody AddReview addReview) {

        User user = userDao.findByEmail(addReview.getEmail());
        Video video = videoDao.findById(addReview.getVideoId());

        Review review = new Review(addReview.getComment(),user,video);

        reviewDAO.save(review);

        return ResponseEntity.ok().build();
    }

    @PostMapping(path = "/addliketovideo", consumes = "application/json")
    public ResponseEntity<?> addLikeToVideo(@RequestBody Map<String, Object> body) {
        int id = Integer.parseInt(body.get("id").toString());
        String email = body.get("email").toString();

        if(!isLikeToVideo(id, email)) {
            Video videoToSave = videoDao.findById(id);
            int likes = videoToSave.getLikes();
            videoToSave.setLikes(likes + 1);
            videoDao.save(videoToSave);

            User user = userDao.findByEmail(email);
            user.getLikesList().add(new Likes(id,user.getId()));
            userDao.save(user);
        }

        return ResponseEntity.ok().build();
    }

    @PostMapping(path = "/adddisliketovideo", consumes = "application/json")
    public ResponseEntity<?> addDislikeToVideo(@RequestBody Map<String, Object> body) {
        int id = Integer.parseInt(body.get("id").toString());
        String email = body.get("email").toString();

        if(!isDislikeToVideo(id, email)) {
            Video videoToSave = videoDao.findById(id);
            int dislikes = videoToSave.getDislikes();
            videoToSave.setDislikes(dislikes + 1);
            videoDao.save(videoToSave);

            User user = userDao.findByEmail(email);
            user.getDislikeList().add(new Dislike(id, user.getId()));
            userDao.save(user);
        }

        return ResponseEntity.ok().build();
    }

    @GetMapping(path = "/getsubscription/{email}")
    public List<GetSubscriptions> getSubscription(@PathVariable("email") String email) {

        User user = userDao.findByEmail(email);
        List<GetSubscriptions> subscribes = new ArrayList<>();

        for(Subscribe tmpSub :user.getSubscribeList()) {
            User userTmp = userDao.findById(tmpSub.getUserSubscriptionId());
            String name = userTmp.getFirstName() + " " + userTmp.getLastName();
            subscribes.add(new GetSubscriptions(userTmp.getId(), userTmp.getEmail(), name));
        }

        return subscribes;
    }

    @PostMapping(path = "/addsubscription")
    public ResponseEntity<?> addSubscription(@RequestBody Map<String, Object> body) {
        int userVideoId = Integer.parseInt(body.get("userId").toString());
        String email = body.get("email").toString();
        User user = userDao.findByEmail(email);
        user.getSubscribeList().add(new Subscribe(userVideoId, user.getId()));
        userDao.save(user);
        return ResponseEntity.ok().build();
    }

    @PostMapping(path = "/subtractsubscription")
    public ResponseEntity<?> subtractSubscription(@RequestBody Map<String, Object> body) {
        int userVideoId = Integer.parseInt(body.get("userId").toString());
        String email = body.get("email").toString();
        User user = userDao.findByEmail(email);

        subscribeDao.removeByUserSubscriptionIdAndUserId(userVideoId, user.getId());

        return ResponseEntity.ok().build();
    }

    @PostMapping(path = "/adddisplaywithuser")
    public ResponseEntity<?> addDisplayWithUser(@RequestBody AddDisplayWithUser addDisplayWithUser) {
        User user = userDao.findByEmail(addDisplayWithUser.getEmail());
        user.getHistoryList().add(new History(user.getId(), addDisplayWithUser.getVideoId()));

        Video video = videoDao.findById(addDisplayWithUser.getVideoId());
        video.setDisplay(video.getDisplay() + 1);

        userDao.save(user);
        videoDao.save(video);

        return ResponseEntity.ok().build();
    }

    @GetMapping(path = "/isliketovideo")
    public boolean isLikeToVideoRest(@RequestParam("id") String idString, @RequestParam("email") String email) {

        int id = Integer.parseInt(idString);
        return isLikeToVideo(id, email);
    }

    @GetMapping(path = "/isdisliketovideo")
    public boolean isDislikeToVideoRest(@RequestParam("id") String idString, @RequestParam("email") String email) {

        int id = Integer.parseInt(idString);
        return isDislikeToVideo(id, email);
    }


    public boolean isLikeToVideo(int id, String email) {

        User user = userDao.findByEmail(email);
        for(Likes likes : user.getLikesList()) {
            if(likes.getVideoId() == id)
            {
                return true;
            }
        }
        return false;
    }

    public boolean isDislikeToVideo(int id, String email) {

        User user = userDao.findByEmail(email);
        for(Dislike dislike : user.getDislikeList()) {
            if(dislike.getVideoId() == id)
            {
                return true;
            }
        }
        return false;
    }

    @PostMapping(path = "/subtractliketovideo", consumes = "application/json")
    public ResponseEntity<?> subtractLikeToVideo(@RequestBody Map<String, Object> body) {
        int id = Integer.parseInt(body.get("id").toString());
        String email = body.get("email").toString();
        User user = userDao.findByEmail(email);
        likesDao.removeByVideoIdAndUserId(id, user.getId());

        Video videoToSave = videoDao.findById(id);
        int likes = videoToSave.getLikes();
        videoToSave.setLikes(likes - 1);
        videoDao.save(videoToSave);

        return ResponseEntity.ok().build();
    }

    @PostMapping(path = "/subtractdisliketovideo", consumes = "application/json")
    public ResponseEntity<?> subtractDislikeToVideo(@RequestBody Map<String, Object> body) {
        int id = Integer.parseInt(body.get("id").toString());
        String email = body.get("email").toString();

        User user = userDao.findByEmail(email);
        dislikesDao.removeByVideoIdAndUserId(id, user.getId());

        Video videoToSave = videoDao.findById(id);
        int dislikes = videoToSave.getDislikes();
        videoToSave.setDislikes(dislikes - 1);
        videoDao.save(videoToSave);

        return ResponseEntity.ok().build();
    }

    @GetMapping(path = "/get-video-feed/{category}/{id}")
    public List<VideoBasicInformation> getVideoFeed(@PathVariable("category") String category,
                                                    @PathVariable("id") int id,
                                                    @RequestParam("email") String email) {

        switch (category) {
            case "history":
                return getVideoFeedHistory(category, id, email);
            case "liked":
                return getVideoFeedLiked(category, id, email);
            case "subscription":
                return getVideoFeedSubscription(category, id, email);
        }

        return null;
    }

    public List<VideoBasicInformation> getVideoFeedHistory(String category, int id, String email) {
        User user = userDao.findByEmail(email);

        List<History> historyList = historyDao.findAllByUserIdOrderByIdDesc(user.getId());

        List<VideoBasicInformation> videoBasicInformations = new ArrayList<>();

        for (int i = (id-1)*10; i < id*10; i++) {

            if(i >= historyList.size()) {
                logger.warn("poza lista ");
                continue;
            }

            logger.warn(historyList.get(i).getVideoId() + " wynik");


            Video video = videoDao.findById(historyList.get(i).getVideoId());

            VideoBasicInformation tmp = new VideoBasicInformation(
                    video.getId().intValue(), video.getUrl(), video.getTitle(), video.getDescription(),
                    video.getUser().getFirstName(), video.getUser().getLastName(), video.getUser().getId(),
                    video.getDisplay(), video.getPhotoUrl(), video.getDate(), video.getLikes(), video.getDislikes()
            );

            videoBasicInformations.add(tmp);
        }

        return videoBasicInformations;
    }

    public List<VideoBasicInformation> getVideoFeedLiked(String category, int id, String email) {
        User user = userDao.findByEmail(email);

        List<Likes> likes = likesDao.findAllByUserIdOrderByIdDesc(user.getId());

        List<VideoBasicInformation> videoBasicInformations = new ArrayList<>();

        for (int i = (id-1)*10; i < id*10; i++) {

            if(i >= likes.size()) {
                logger.warn("poza lista ");
                continue;
            }

            logger.warn(likes.get(i).getVideoId() + " wynik");


            Video video = videoDao.findById(likes.get(i).getVideoId());

            VideoBasicInformation tmp = new VideoBasicInformation(
                    video.getId().intValue(), video.getUrl(), video.getTitle(), video.getDescription(),
                    video.getUser().getFirstName(), video.getUser().getLastName(), video.getUser().getId(),
                    video.getDisplay(), video.getPhotoUrl(), video.getDate(), video.getLikes(), video.getDislikes()
            );

            videoBasicInformations.add(tmp);
        }

        return videoBasicInformations;
    }

    public List<VideoBasicInformation> getVideoFeedSubscription(String category, int id, String email) {
        User user = userDao.findByEmail(email);

        List<Subscribe> subscribes = subscribeDao.findAllByUserId(user.getId());

        List<VideoBasicInformation> videoBasicInformations = new ArrayList<>();

        List<User> users = new ArrayList<>();

        List<Video> videos = new ArrayList<>();

        for(Subscribe subscribe: subscribes) {
            User tmp = userDao.findById(subscribe.getUserSubscriptionId());
            users.add(tmp);
            videos.addAll(videoDao.findAllByUserIdOrderByDateDesc(tmp.getId()));
        }

        Collections.sort(videos, new Comparator<Video>() {
            @Override
            public int compare(Video o1, Video o2) {
                return o1.getId()>o2.getId() ? -1 : o1.getId()==o2.getId() ? 0 : 1;
            }
        });

        for (Video video: videos) {
            logger.warn(video.getTitle());
        }


        for (int i = (id-1)*10; i < id*10; i++) {

            if(i >= videos.size()) {
                logger.warn("poza lista ");
                continue;
            }

            Video video = videos.get(i);

            VideoBasicInformation tmp = new VideoBasicInformation(
                    video.getId().intValue(), video.getUrl(), video.getTitle(), video.getDescription(),
                    video.getUser().getFirstName(), video.getUser().getLastName(), video.getUser().getId(),
                    video.getDisplay(), video.getPhotoUrl(), video.getDate(), video.getLikes(), video.getDislikes()
            );

            videoBasicInformations.add(tmp);
        }

        return videoBasicInformations;
    }
}
