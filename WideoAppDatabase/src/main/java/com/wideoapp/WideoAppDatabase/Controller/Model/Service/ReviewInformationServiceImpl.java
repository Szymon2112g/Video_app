package com.wideoapp.WideoAppDatabase.Controller.Model.Service;

import com.wideoapp.WideoAppDatabase.Controller.Model.ReviewInformation;
import com.wideoapp.WideoAppDatabase.Entity.Review;
import com.wideoapp.WideoAppDatabase.Service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReviewInformationServiceImpl implements ReviewInformationService {

    private VideoService videoService;

    @Autowired
    public ReviewInformationServiceImpl(VideoService videoService) {
        this.videoService = videoService;
    }

    @Override
    public List<ReviewInformation> getAllReviewByVideoId(int id) {

        List<ReviewInformation> reviewInformations = new ArrayList<>();

        for(Review review: videoService.findById(id).getReviews()) {
            ReviewInformation tmp =
                    new ReviewInformation(review.getComment(), review.getUser().getFirstName(),
                                            review.getUser().getLastName());

            reviewInformations.add(tmp);
        }

        return reviewInformations;
    }
}
