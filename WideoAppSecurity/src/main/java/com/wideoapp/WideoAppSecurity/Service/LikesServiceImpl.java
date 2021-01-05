package com.wideoapp.WideoAppSecurity.Service;

import com.wideoapp.WideoAppSecurity.Dao.LikesDao;
import com.wideoapp.WideoAppSecurity.Dao.UserDao;
import com.wideoapp.WideoAppSecurity.Dao.VideoDao;
import com.wideoapp.WideoAppSecurity.Entity.Likes;
import com.wideoapp.WideoAppSecurity.Entity.User;
import com.wideoapp.WideoAppSecurity.Entity.Video;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        Video video = videoDao.findById(videoId);
        int likes = video.getLikes();
        video.setLikes(likes + 1);
        videoDao.save(video);

        User user = userDao.findByEmail(email);
        user.getLikeList().add(new Likes(videoId, user.getId()));
        userDao.save(user);
    }

    @Override
    public boolean isLikeToVideo(int id, String email) {

        User user = userDao.findByEmail(email);

        for(Likes like : user.getLikeList()) {
            if(like.getVideoId() == id)
            {
                return true;
            }
        }

        return false;
    }

    @Override
    public void subtractLike(int videoId, String email) {
        User user = userDao.findByEmail(email);

        likesDao.removeByVideoIdAndUserId(videoId, user.getId());

        Video videoToSave = videoDao.findById(videoId);
        int likes = videoToSave.getLikes() - 1;
        videoToSave.setLikes(likes);

        videoDao.save(videoToSave);
    }
}
