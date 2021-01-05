package com.wideoapp.WideoAppSecurity.Web.Controller;

import com.wideoapp.WideoAppSecurity.Service.LikesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@CrossOrigin(origins="http://localhost:4200")
public class LikeController {

    private LikesService likesService;

    @Autowired
    public LikeController(LikesService likesService) {
        this.likesService = likesService;
    }

    //@PostMapping(path = "/add-like-to-video", consumes = "application/json")
    @PostMapping(path = "/like/add/video", consumes = "application/json")
    public ResponseEntity<?> addLikeToVideo(@RequestBody Map<String, Object> body) {
        int id = Integer.parseInt(body.get("id").toString());
        String email = body.get("email").toString();

        likesService.addLike(id, email);

        return ResponseEntity.ok().build();
    }

    //@GetMapping(path = "/is-like-to-video")
    @GetMapping(path = "/like/video")
    public boolean isLikeToVideoRest(@RequestParam("id") String idString, @RequestParam("email") String email) {
        int id = Integer.parseInt(idString);
        boolean isLike = likesService.isLikeToVideo(id, email);
        return isLike;
    }

    //@PostMapping(path = "/subtract-like-to-video", consumes = "application/json")
    @PostMapping(path = "/like/subtract/video", consumes = "application/json")
    public ResponseEntity<?> subtractLikeToVideo(@RequestBody Map<String, Object> body) {
        int id = Integer.parseInt(body.get("id").toString());
        String email = body.get("email").toString();

        likesService.subtractLike(id, email);

        return ResponseEntity.ok().build();
    }
}
