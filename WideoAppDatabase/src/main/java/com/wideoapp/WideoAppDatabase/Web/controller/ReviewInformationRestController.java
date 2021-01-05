package com.wideoapp.WideoAppDatabase.Web.controller;

import com.wideoapp.WideoAppDatabase.Service.ReviewService;
import com.wideoapp.WideoAppDatabase.Web.Model.ReviewDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
//@CrossOrigin( origins = "http://localhost:4200")
public class ReviewInformationRestController {

    private ReviewService reviewService;

    @Autowired
    public ReviewInformationRestController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    //@GetMapping(path = "/get-reviews/{videoId}")
    @GetMapping(path = "/reviews/{videoId}")
    public List<ReviewDto> getReviews(@PathVariable("videoId") int videoId) {
        return reviewService.getAllReviewByVideoId(videoId);
    }
}
