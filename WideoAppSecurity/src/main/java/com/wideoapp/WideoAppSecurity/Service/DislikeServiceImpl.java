package com.wideoapp.WideoAppSecurity.Service;

import com.wideoapp.WideoAppSecurity.Dao.DislikesDao;
import com.wideoapp.WideoAppSecurity.Dao.UserDao;
import com.wideoapp.WideoAppSecurity.Dao.VideoDao;
import com.wideoapp.WideoAppSecurity.Entity.Dislike;
import com.wideoapp.WideoAppSecurity.Entity.User;
import com.wideoapp.WideoAppSecurity.Entity.Video;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public void addDislike(int videoId, String email) {
        Video videoToSave = videoDao.findById(videoId);
        int dislikes = videoToSave.getDislikes();
        videoToSave.setDislikes(dislikes + 1);
        videoDao.save(videoToSave);

        User user = userDao.findByEmail(email);
        user.getDislikeList().add(new Dislike(videoId, user.getId()));
        userDao.save(user);
    }

    @Override
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

    @Override
    public void subtractDislike(int videoId, String email) {
        User user = userDao.findByEmail(email);
        dislikesDao.removeByVideoIdAndUserId(videoId, user.getId());

        Video videoToSave = videoDao.findById(videoId);
        int dislikes = videoToSave.getDislikes() - 1;
        videoToSave.setDislikes(dislikes);

        videoDao.save(videoToSave);
    }
}
