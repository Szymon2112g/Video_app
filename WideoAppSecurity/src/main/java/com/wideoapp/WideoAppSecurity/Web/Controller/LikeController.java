package com.wideoapp.WideoAppSecurity.Web.Controller;

import com.wideoapp.WideoAppSecurity.Service.JwtTokenService;
import com.wideoapp.WideoAppSecurity.Service.LikesService;
import com.wideoapp.WideoAppSecurity.Web.Controller.Exception.NoFoundObjectException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
//@CrossOrigin(origins="http://localhost:4200")
public class LikeController {

    private LikesService likesService;
    private JwtTokenService jwtTokenService;

    @Autowired
    public LikeController(LikesService likesService, JwtTokenService jwtTokenService) {
        this.likesService = likesService;
        this.jwtTokenService = jwtTokenService;
    }

    //@PostMapping(path = "/add-like-to-video", consumes = "application/json")
    @PostMapping(path = "/like/add/video/{videoId}", consumes = "application/json")
    public ResponseEntity<?> addLikeToVideo(@RequestHeader Map<String, String> header,
                                            @PathVariable int videoId) throws NoFoundObjectException {

        String email = jwtTokenService.findEmailFromTokenOfHeader(header);

        boolean isSuccess = likesService.addLike(videoId, email);

        if (!isSuccess) {
            throw new NoFoundObjectException("like can't be added");
        }

        return ResponseEntity.ok().build();
    }

    //@GetMapping(path = "/is-like-to-video")
    @GetMapping(path = "/like/video/{videoId}")
    public ResponseEntity<?> isLikeToVideoRest(@RequestHeader Map<String, String> header,
                                               @PathVariable int videoId) {

        String email = jwtTokenService.findEmailFromTokenOfHeader(header);

        boolean isLike = likesService.isLikeToVideo(videoId, email);

        return ResponseEntity.ok(isLike);
    }

    //@PostMapping(path = "/subtract-like-to-video", consumes = "application/json")
    @PostMapping(path = "/like/subtract/video/{videoId}", consumes = "application/json")
    public ResponseEntity<?> subtractLikeToVideo(@RequestHeader Map<String, String> header,
                                                 @PathVariable int videoId) throws NoFoundObjectException {

        String email = jwtTokenService.findEmailFromTokenOfHeader(header);

        boolean isSuccess = likesService.subtractLike(videoId, email);

        if (!isSuccess) {
            throw new NoFoundObjectException("like can't be subtract");
        }

        return ResponseEntity.ok().build();
    }
}
