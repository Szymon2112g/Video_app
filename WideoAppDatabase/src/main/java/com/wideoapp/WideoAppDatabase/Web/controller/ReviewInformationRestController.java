package com.wideoapp.WideoAppDatabase.Web.controller;

import com.wideoapp.WideoAppDatabase.Service.ReviewService;
import com.wideoapp.WideoAppDatabase.Web.Model.ReviewDto;
import com.wideoapp.WideoAppDatabase.Web.controller.Exception.InvalidInputException;
import com.wideoapp.WideoAppDatabase.Web.controller.Exception.NoFoundObjectException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<?> getReviews(@PathVariable("videoId") int videoId)
            throws InvalidInputException, NoFoundObjectException {

        if (videoId <= 0) {
            throw new InvalidInputException("videoId is no correct");
        }

        List<ReviewDto> reviewDtoList = reviewService.getAllReviewByVideoId(videoId);

        if (reviewDtoList == null || reviewDtoList.isEmpty()) {
            throw new NoFoundObjectException("No found reviews");
        }

        return ResponseEntity.ok(reviewDtoList);
    }
}
