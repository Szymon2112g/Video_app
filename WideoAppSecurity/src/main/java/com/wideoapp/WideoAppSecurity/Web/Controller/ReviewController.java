package com.wideoapp.WideoAppSecurity.Web.Controller;

import com.wideoapp.WideoAppSecurity.Service.JwtTokenService;
import com.wideoapp.WideoAppSecurity.Service.ReviewService;
import com.wideoapp.WideoAppSecurity.Web.Model.ReviewToAdd;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
//@CrossOrigin(origins="http://localhost:4200")
public class ReviewController {

    private ReviewService reviewService;
    private JwtTokenService jwtTokenService;

    @Autowired
    public ReviewController(ReviewService reviewService, JwtTokenService jwtTokenService) {
        this.reviewService = reviewService;
        this.jwtTokenService = jwtTokenService;
    }

    //@PostMapping(path = "/add-review/")
    @PostMapping(path = "/review/add")
    public ResponseEntity<?> addReview(@RequestHeader Map<String, String> header,
                                       @RequestBody ReviewToAdd reviewToAdd) {

        if (reviewToAdd == null ) {
            return ResponseEntity.badRequest().build();
        }

        if (reviewToAdd.getComment() == null || reviewToAdd.getComment().equals("")) {
            return ResponseEntity.badRequest().build();
        }

        if (reviewToAdd.getVideoId() < 0) {
            return ResponseEntity.badRequest().build();
        }

        String email = jwtTokenService.findEmailFromTokenOfHeader(header);

        reviewService.addReview(email, reviewToAdd);

        return ResponseEntity.ok().build();
    }
}
