package com.wideoapp.WideoAppSecurity.Service;

import com.wideoapp.WideoAppSecurity.Dao.UserDao;
import com.wideoapp.WideoAppSecurity.Dao.VideoDao;
import com.wideoapp.WideoAppSecurity.Entity.History;
import com.wideoapp.WideoAppSecurity.Entity.User;
import com.wideoapp.WideoAppSecurity.Entity.Video;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class HistoryServiceImpl implements HistoryService {

    private UserDao userDao;
    private VideoDao videoDao;

    @Autowired
    public HistoryServiceImpl(UserDao userDao, VideoDao videoDao) {
        this.userDao = userDao;
        this.videoDao = videoDao;
    }

    @Override
    public boolean addHistory(String email, int videoId) {

        User user = findUserByEmail(email);

        if (user == null) {
            return false;
        }

        Video video = findVideoById(videoId);

        if (video == null) {
            return false;
        }

        user.getHistoryList().add(new History(0, video.getId(), user.getId()));

        userDao.save(user);

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
