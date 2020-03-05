package com.wideoapp.WideoAppDatabase.Controller;

import com.wideoapp.WideoAppDatabase.Controller.Model.ReviewInformation;
import com.wideoapp.WideoAppDatabase.Controller.Model.Service.ReviewInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin( origins = "http://localhost:4200")
public class ReviewInformationRestController {

    private ReviewInformationService reviewInformationService;

    @Autowired
    public ReviewInformationRestController(ReviewInformationService reviewInformationService) {
        this.reviewInformationService = reviewInformationService;
    }

    @GetMapping(path = "/get-reviews/{videoId}")
    public List<ReviewInformation> getReviews(@PathVariable("videoId") int videoId) {
        return reviewInformationService.getAllReviewByVideoId(videoId);
    }
}
