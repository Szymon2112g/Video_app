package com.wideoapp.WideoAppDatabase.Service;

import com.wideoapp.WideoAppDatabase.DAO.VideoDAO;
import com.wideoapp.WideoAppDatabase.Web.Mapper.ReviewMapper;
import com.wideoapp.WideoAppDatabase.Web.Model.ReviewDto;
import com.wideoapp.WideoAppDatabase.Entity.Review;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {

    private VideoDAO videoDAO;
    private ReviewMapper reviewMapper = Mappers.getMapper(ReviewMapper.class);

    @Autowired
    public ReviewServiceImpl(VideoDAO videoDAO) {
        this.videoDAO = videoDAO;
    }

    @Override
    @Transactional
    public List<ReviewDto> getAllReviewByVideoId(int id) {

        List<Review> reviews = videoDAO.findById(id).getReviews();
        List<ReviewDto> reviewsDTO = new ArrayList<>(reviews.size());

        for(Review review: reviews) {

            ReviewDto reviewDto = reviewMapper.ReviewToReviewDto(review);
            reviewsDTO.add(reviewDto);
        }

        return reviewsDTO;
    }
}
