package com.wideoapp.WideoAppSecurity.Web.Controller;

import com.wideoapp.WideoAppSecurity.Service.DislikeService;
import com.wideoapp.WideoAppSecurity.Service.JwtTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
//@CrossOrigin(origins="http://localhost:4200")
public class DislikeController {

    private DislikeService dislikeService;
    private JwtTokenService jwtTokenService;

    @Autowired
    public DislikeController(DislikeService dislikeService, JwtTokenService jwtTokenService) {
        this.dislikeService = dislikeService;
        this.jwtTokenService = jwtTokenService;
    }

    //@PostMapping(path = "/add-dislike-to-video", consumes = "application/json")
    @PostMapping(path = "/dislike/add/video/{videoId}", consumes = "application/json")
    public ResponseEntity<?> addDislikeToVideo(@RequestHeader Map<String, String> header,
                                               @PathVariable int videoId) {

        String email = jwtTokenService.findEmailFromTokenOfHeader(header);

        dislikeService.addDislike(videoId, email);

        return ResponseEntity.ok().build();
    }


    //@GetMapping(path = "/is-dislike-to-video")
    @GetMapping(path = "/dislike/video/{videoId}")
    public ResponseEntity<?> isDislikeVideo(@RequestHeader Map<String, String> header,
                                                  @PathVariable("videoId") int videoId) {

        String email = jwtTokenService.findEmailFromTokenOfHeader(header);

        boolean isDislike = dislikeService.isDislikeToVideo(videoId, email);

        return ResponseEntity.ok(isDislike);
    }

    //@PostMapping(path = "/subtract-dislike-to-video", consumes = "application/json")
    @PostMapping(path = "/dislike/subtract/video/{videoId}", consumes = "application/json")
    public ResponseEntity<?> subtractDislikeFromVideo(@RequestHeader Map<String, String> header,
                                                    @PathVariable int videoId) {

        String email = jwtTokenService.findEmailFromTokenOfHeader(header);

        dislikeService.subtractDislike(videoId, email);

        return ResponseEntity.ok().build();
    }
}
