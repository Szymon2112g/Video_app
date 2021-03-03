package com.wideoapp.WideoAppSecurity.Service;

import com.wideoapp.WideoAppSecurity.Dao.LikesDao;
import com.wideoapp.WideoAppSecurity.Dao.UserDao;
import com.wideoapp.WideoAppSecurity.Dao.VideoDao;
import com.wideoapp.WideoAppSecurity.Entity.Likes;
import com.wideoapp.WideoAppSecurity.Entity.User;
import com.wideoapp.WideoAppSecurity.Entity.Video;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LikesServiceImpl implements LikesService {

    private VideoDao videoDao;
    private UserDao userDao;
    private LikesDao likesDao;

    @Autowired
    public LikesServiceImpl(VideoDao videoDao, UserDao userDao, LikesDao likesDao) {
        this.videoDao = videoDao;
        this.userDao = userDao;
        this.likesDao = likesDao;
    }

    @Override
    public void addLike(int videoId, String email) {

        User user = findUserByEmail(email);

        if (likesDao.existsByVideoIdAndUserId(videoId, user.getId())) {
            return;
        }

        Video video = findVideoById(videoId);

        int likes = video.getLikes();
        video.setLikes(likes + 1);
        videoDao.save(video);

        user.getLikeList().add(new Likes(videoId, user.getId()));
        userDao.save(user);
    }

    @Override
    public boolean isLikeToVideo(int id, String email) {

        User user = findUserByEmail(email);

        if (!likesDao.existsByVideoIdAndUserId(id, user.getId())) {
            return false;
        }

        return true;
    }

    @Override
    public void subtractLike(int videoId, String email) {


        User user = findUserByEmail(email);

        if (!likesDao.existsByVideoIdAndUserId(videoId, user.getId())) {
            return;
        }

        likesDao.removeByVideoIdAndUserId(videoId, user.getId());

        Video videoToSave = findVideoById(videoId);

        int likes = videoToSave.getLikes() - 1;
        videoToSave.setLikes(likes);

        videoDao.save(videoToSave);
    }

    private Video findVideoById(int id) {

        Optional<Video> videoToSaveOptional = videoDao.findById(id);

        if (!videoToSaveOptional.isPresent()) {
            throw new IllegalStateException("No found Video");
        }

        return videoToSaveOptional.get();
    }

    private User findUserByEmail(String email) {

        Optional<User> userOptional = userDao.findByEmail(email);

        if (!userOptional.isPresent()) {
            throw new IllegalStateException("No found user");
        }

        return userOptional.get();
    }
}
