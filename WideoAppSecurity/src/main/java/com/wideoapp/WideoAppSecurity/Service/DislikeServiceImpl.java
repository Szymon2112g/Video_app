package com.wideoapp.WideoAppSecurity.Service;

import com.wideoapp.WideoAppSecurity.Dao.DislikesDao;
import com.wideoapp.WideoAppSecurity.Dao.UserDao;
import com.wideoapp.WideoAppSecurity.Dao.VideoDao;
import com.wideoapp.WideoAppSecurity.Entity.Dislike;
import com.wideoapp.WideoAppSecurity.Entity.User;
import com.wideoapp.WideoAppSecurity.Entity.Video;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DislikeServiceImpl implements DislikeService {

    private VideoDao videoDao;
    private UserDao userDao;
    private DislikesDao dislikesDao;

    @Autowired
    public DislikeServiceImpl(VideoDao videoDao, UserDao userDao, DislikesDao dislikesDao) {
        this.videoDao = videoDao;
        this.userDao = userDao;
        this.dislikesDao = dislikesDao;
    }

    @Override
    public boolean addDislike(int videoId, String email) {

        User user = findUserByEmail(email);

        if (dislikesDao.existsByVideoIdAndUserId(videoId, user.getId())) {
            return false;
        }

        Video videoToSave = findVideoById(videoId);

        int dislikes = videoToSave.getDislikes();
        videoToSave.setDislikes(dislikes + 1);
        videoDao.save(videoToSave);

        user.getDislikeList().add(new Dislike(videoId, user.getId()));
        userDao.save(user);

        return true;
    }

    @Override
    public boolean isDislikeToVideo(int videoId, String email) {

        User user = findUserByEmail(email);

        if (!dislikesDao.existsByVideoIdAndUserId(videoId, user.getId())) {
            return false;
        }

        return true;
    }

    @Override
    public boolean subtractDislike(int videoId, String email) {

        User user = findUserByEmail(email);

        if (!dislikesDao.existsByVideoIdAndUserId(videoId, user.getId())) {
            return false;
        }

        dislikesDao.removeByVideoIdAndUserId(videoId, user.getId());

        Video videoToSave = findVideoById(videoId);

        int dislikes = videoToSave.getDislikes() - 1;
        videoToSave.setDislikes(dislikes);

        videoDao.save(videoToSave);

        return true;
    }

    private Video findVideoById(int id) {

        Optional<Video> videoToSaveOptional = videoDao.findById(id);

        if (!videoToSaveOptional.isPresent()) {
            return null;
        }

        return videoToSaveOptional.get();
    }

    private User findUserByEmail(String email) {

        Optional<User> userOptional = userDao.findByEmail(email);

        if (!userOptional.isPresent()) {
            return null;
        }

        return userOptional.get();
    }
}
