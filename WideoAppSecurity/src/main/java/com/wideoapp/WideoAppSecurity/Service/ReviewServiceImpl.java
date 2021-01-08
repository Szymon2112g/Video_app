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
    public void save(ReviewToAdd reviewToAdd) {
        Video video = videoDao.findById(reviewToAdd.getVideoId());
        User user = userDao.findByEmail(reviewToAdd.getEmail());

        Review review = new Review(reviewToAdd.getComment(), user.getId(), video.getId());

        reviewDAO.save(review);
    }
}
