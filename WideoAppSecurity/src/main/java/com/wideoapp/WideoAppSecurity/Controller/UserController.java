package com.wideoapp.WideoAppSecurity.Controller;

import com.wideoapp.WideoAppSecurity.Controller.Model.AddDisplayWithUser;
import com.wideoapp.WideoAppSecurity.Controller.Model.AddReview;
import com.wideoapp.WideoAppSecurity.Controller.Model.GetSubscriptions;
import com.wideoapp.WideoAppSecurity.Dao.*;
import com.wideoapp.WideoAppSecurity.Entity.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class UserController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private UserDao userDao;
    private VideoDao videoDao;
    private ReviewDAO reviewDAO;
    private LikesDao likesDao;
    private DislikesDao dislikesDao;
    private SubscribeDao subscribeDao;

    @Autowired
    public UserController(
            UserDao userDao,
            VideoDao videoDao,
            ReviewDAO reviewDAO,
            LikesDao likesDao,
            DislikesDao dislikesDao,
            SubscribeDao subscribeDao
    ) {
        this.userDao = userDao;
        this.videoDao = videoDao;
        this.reviewDAO = reviewDAO;
        this.likesDao = likesDao;
        this.dislikesDao = dislikesDao;
        this.subscribeDao = subscribeDao;
    }

    @PostMapping(path = "/add-review/")
    public ResponseEntity<?> addReview(@RequestBody AddReview addReview) {

        User user = userDao.findByEmail(addReview.getEmail());
        Video video = videoDao.findById(addReview.getVideoId());

        Review review = new Review(addReview.getComment(), user, video);

        reviewDAO.save(review);

        return ResponseEntity.ok().build();
    }

    @PostMapping(path = "/add-like-to-video", consumes = "application/json")
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

    @PostMapping(path = "/add-dislike-to-video", consumes = "application/json")
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

    @GetMapping(path = "/get-subscription/{email}")
    public List<GetSubscriptions> getSubscription(@PathVariable("email") String email) {

        User user = userDao.findByEmail(email);
        List<GetSubscriptions> subscribes = new ArrayList<>();

        for(Subscribe tmpSub : user.getSubscribeList()) {
            User userTmp = userDao.findById(tmpSub.getUserSubscriptionId());
            String name = userTmp.getFirstName() + " " + userTmp.getLastName();
            subscribes.add(new GetSubscriptions(userTmp.getId(), userTmp.getEmail(), name));
        }

        return subscribes;
    }

    @PostMapping(path = "/add-subscription")
    public ResponseEntity<?> addSubscription(@RequestBody Map<String, Object> body) {

        int userVideoId = Integer.parseInt(body.get("userId").toString());
        String email = body.get("email").toString();
        User user = userDao.findByEmail(email);

        user.getSubscribeList().add(new Subscribe(userVideoId, user.getId()));

        userDao.save(user);
        return ResponseEntity.ok().build();
    }

    @PostMapping(path = "/subtract-subscription")
    public ResponseEntity<?> subtractSubscription(@RequestBody Map<String, Object> body) {

        int userVideoId = Integer.parseInt(body.get("userId").toString());
        String email = body.get("email").toString();
        User user = userDao.findByEmail(email);

        subscribeDao.removeByUserSubscriptionIdAndUserId(userVideoId, user.getId());

        return ResponseEntity.ok().build();
    }

    @PostMapping(path = "/add-display-with-user")
    public ResponseEntity<?> addDisplayWithUser(@RequestBody AddDisplayWithUser addDisplayWithUser) {

        User user = userDao.findByEmail(addDisplayWithUser.getEmail());
        user.getHistoryList().add(new History(user.getId(), addDisplayWithUser.getVideoId()));
        Video video = videoDao.findById(addDisplayWithUser.getVideoId());

        video.setDisplay(video.getDisplay() + 1);

        userDao.save(user);
        videoDao.save(video);

        return ResponseEntity.ok().build();
    }

    @GetMapping(path = "/is-like-to-video")
    public boolean isLikeToVideoRest(@RequestParam("id") String idString, @RequestParam("email") String email) {

        int id = Integer.parseInt(idString);
        return isLikeToVideo(id, email);
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

    @GetMapping(path = "/is-dislike-to-video")
    public boolean isDislikeToVideoRest(@RequestParam("id") String idString, @RequestParam("email") String email) {

        int id = Integer.parseInt(idString);
        return isDislikeToVideo(id, email);
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

    @PostMapping(path = "/subtract-like-to-video", consumes = "application/json")
    public ResponseEntity<?> subtractLikeToVideo(@RequestBody Map<String, Object> body) {

        int id = Integer.parseInt(body.get("id").toString());
        String email = body.get("email").toString();
        User user = userDao.findByEmail(email);

        likesDao.removeByVideoIdAndUserId(id, user.getId());

        Video videoToSave = videoDao.findById(id);
        int likes = videoToSave.getLikes() - 1;
        videoToSave.setLikes(likes);

        videoDao.save(videoToSave);

        return ResponseEntity.ok().build();
    }

    @PostMapping(path = "/subtract-dislike-to-video", consumes = "application/json")
    public ResponseEntity<?> subtractDislikeToVideo(@RequestBody Map<String, Object> body) {

        int id = Integer.parseInt(body.get("id").toString());
        String email = body.get("email").toString();

        User user = userDao.findByEmail(email);
        dislikesDao.removeByVideoIdAndUserId(id, user.getId());

        Video videoToSave = videoDao.findById(id);
        int dislikes = videoToSave.getDislikes() - 1;
        videoToSave.setDislikes(dislikes);

        videoDao.save(videoToSave);

        return ResponseEntity.ok().build();
    }

}
