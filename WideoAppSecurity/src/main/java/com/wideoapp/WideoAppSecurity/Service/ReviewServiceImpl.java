package com.wideoapp.WideoAppSecurity.Service;

import com.wideoapp.WideoAppSecurity.Dao.ReviewDAO;
import com.wideoapp.WideoAppSecurity.Dao.UserDao;
import com.wideoapp.WideoAppSecurity.Dao.VideoDao;
import com.wideoapp.WideoAppSecurity.Entity.Review;
import com.wideoapp.WideoAppSecurity.Entity.User;
import com.wideoapp.WideoAppSecurity.Entity.Video;
import com.wideoapp.WideoAppSecurity.Web.Model.ReviewToAdd;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ReviewServiceImpl implements ReviewService {

    private ReviewDAO reviewDAO;
    private UserDao userDao;
    private VideoDao videoDao;

    @Autowired
    public ReviewServiceImpl(ReviewDAO reviewDAO, UserDao userDao, VideoDao videoDao) {
        this.reviewDAO = reviewDAO;
        this.userDao = userDao;
        this.videoDao = videoDao;
    }

    @Override
    public void addReview(String email, ReviewToAdd reviewToAdd) {

        Video video = findVideoById(reviewToAdd.getVideoId());
        User user = findUserByEmail(email);

        Review review = new Review(reviewToAdd.getComment(), user.getId(), video.getId());

        reviewDAO.save(review);
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
