package com.wideoapp.WideoAppSecurity.Web.Controller;

import com.wideoapp.WideoAppSecurity.Service.ReviewService;
import com.wideoapp.WideoAppSecurity.Web.Model.ReviewToAdd;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@CrossOrigin(origins="http://localhost:4200")
public class ReviewController {

    private ReviewService reviewService;

    @Autowired
    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    //@PostMapping(path = "/add-review/")
    @PostMapping(path = "/review")
    public ResponseEntity<?> addReview(@RequestBody ReviewToAdd reviewToAdd) {
        if (reviewToAdd == null) {
            ResponseEntity.badRequest().build();
        }

        reviewService.save(reviewToAdd);
        return ResponseEntity.ok().build();
    }
}
