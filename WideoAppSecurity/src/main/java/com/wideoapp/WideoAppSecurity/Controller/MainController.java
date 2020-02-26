package com.wideoapp.WideoAppSecurity.Controller;

import com.wideoapp.WideoAppSecurity.Controller.Model.*;
import com.wideoapp.WideoAppSecurity.Dao.ReviewDAO;
import com.wideoapp.WideoAppSecurity.Dao.UserDao;
import com.wideoapp.WideoAppSecurity.Dao.VideoDao;
import com.wideoapp.WideoAppSecurity.Entity.Review;
import com.wideoapp.WideoAppSecurity.Entity.Subscribe;
import com.wideoapp.WideoAppSecurity.Entity.User;
import com.wideoapp.WideoAppSecurity.Entity.Video;
import com.wideoapp.WideoAppSecurity.Proxy.WideoAppDB;
import com.wideoapp.WideoAppSecurity.Proxy.WideoAppFS;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins="http://localhost:4200")
public class MainController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private WideoAppDB wideoAppDB;
    private WideoAppFS wideoAppFS;
    private UserDao userDao;
    private VideoDao videoDao;
    private ReviewDAO reviewDAO;

    @Autowired
    public MainController(WideoAppDB wideoAppDB, WideoAppFS wideoAppFS, UserDao userDao, VideoDao videoDao,  ReviewDAO reviewDAO) {
        this.wideoAppDB = wideoAppDB;
        this.wideoAppFS = wideoAppFS;
        this.userDao = userDao;
        this.videoDao = videoDao;
        this.reviewDAO = reviewDAO;
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

    @PostMapping(path = "/addreview/")
    public ResponseEntity<?> addReview(@RequestBody AddReview addReview) {

        User user = userDao.findByEmail(addReview.getEmail());
        Video video = videoDao.findById(addReview.getVideoId());

        Review review = new Review(addReview.getComment(),user,video);

        reviewDAO.save(review);

        return ResponseEntity.ok().build();
    }

    @PostMapping(path = "/addliketovideo")
    public ResponseEntity<?> addLikeToVideo(@RequestBody Video video) {
        Video videoToSave = videoDao.findById(video.getId().intValue());

        int likes = videoToSave.getLikes();
        videoToSave.setLikes(likes + 1);

        videoDao.save(videoToSave);

        return ResponseEntity.ok().build();
    }

    @PostMapping(path = "/adddisliketovideo")
    public ResponseEntity<?> addDislikeToVideo(@RequestBody Video video) {
        Video videoToSave = videoDao.findById(video.getId().intValue());

        int disLikes = videoToSave.getDislikes();
        videoToSave.setDislikes(disLikes + 1);

        videoDao.save(videoToSave);

        return ResponseEntity.ok().build();
    }

    @GetMapping(path = "/getsubscription/{email}")
    public List<GetSubscriptions> getSubscription(@PathVariable("email") String email) {

        User user = userDao.findByEmail(email);
        List<GetSubscriptions> subscribes = new ArrayList<>();

        for(Subscribe tmpSub :user.getSubscribeList()) {
            User userTmp = userDao.findById(tmpSub.getSubscribe());
            String mail = userTmp.getEmail();
            String name = userTmp.getFirstName() + " " + userTmp.getLastName();
            subscribes.add(new GetSubscriptions(mail, name));
        }

        return subscribes;
    }

    @PostMapping(path = "/addsubscription")
    public ResponseEntity<?> addSubscription(@RequestBody AddSubscriptions addSubscriptions) {

        Video video = videoDao.findById(addSubscriptions.getVideoId());
        User userVideo = video.getUser();
        User user = userDao.findByEmail(addSubscriptions.getEmail());

        user.getSubscribeList().add(new Subscribe(userVideo.getId()));

        userDao.save(user);

        return ResponseEntity.ok().build();
    }
}
