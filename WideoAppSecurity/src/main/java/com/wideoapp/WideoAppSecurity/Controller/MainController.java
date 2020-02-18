package com.wideoapp.WideoAppSecurity.Controller;

import com.wideoapp.WideoAppSecurity.Dao.UserDao;
import com.wideoapp.WideoAppSecurity.Entity.User;
import com.wideoapp.WideoAppSecurity.Entity.Video;
import com.wideoapp.WideoAppSecurity.Controller.Model.VideoToSend;
import com.wideoapp.WideoAppSecurity.Proxy.WideoAppDB;
import com.wideoapp.WideoAppSecurity.Proxy.WideoAppFS;
import com.wideoapp.WideoAppSecurity.Controller.Model.ResponseMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@RestController
@CrossOrigin(origins="http://localhost:4200")
public class MainController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private WideoAppDB wideoAppDB;
    private WideoAppFS wideoAppFS;
    private UserDao userDao;

    @Autowired
    public MainController(WideoAppDB wideoAppDB, WideoAppFS wideoAppFS, UserDao userDao) {
        this.wideoAppDB = wideoAppDB;
        this.wideoAppFS = wideoAppFS;
        this.userDao = userDao;
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
}
