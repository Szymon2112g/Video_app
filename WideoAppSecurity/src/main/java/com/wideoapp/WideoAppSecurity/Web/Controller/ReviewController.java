package com.wideoapp.WideoAppSecurity.Web.Controller;

import com.wideoapp.WideoAppSecurity.Service.JwtTokenService;
import com.wideoapp.WideoAppSecurity.Service.ReviewService;
import com.wideoapp.WideoAppSecurity.Web.Controller.Exception.InvalidInputException;
import com.wideoapp.WideoAppSecurity.Web.Controller.Exception.NoFoundObjectException;
import com.wideoapp.WideoAppSecurity.Web.Model.ReviewToAdd;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
//@CrossOrigin(origins="http://localhost:8765")
public class ReviewController {

    private ReviewService reviewService;
    private JwtTokenService jwtTokenService;

    @Autowired
    public ReviewController(ReviewService reviewService, JwtTokenService jwtTokenService) {
        this.reviewService = reviewService;
        this.jwtTokenService = jwtTokenService;
    }

    //@PostMapping(path = "/add-review/")
    @PostMapping(path = "/review/add/")
    public ResponseEntity<?> addReview(@RequestHeader Map<String, String> header,
                                       @RequestBody ReviewToAdd reviewToAdd)
            throws InvalidInputException, NoFoundObjectException {

        if (reviewToAdd == null ) {
            throw new InvalidInputException("body of review is null");
        }

        if (reviewToAdd.getComment() == null || reviewToAdd.getComment().equals("")) {
            throw new InvalidInputException("comment is empty");
        }

        if (reviewToAdd.getVideoId() < 0) {
            throw new InvalidInputException("video id is below 1");
        }

        String email = jwtTokenService.findEmailFromTokenOfHeader(header);

        boolean isSuccess = reviewService.addReview(email, reviewToAdd);

        if (!isSuccess) {
            throw new NoFoundObjectException("review can't be added");
        }

        return ResponseEntity.ok().build();
    }
}
