package com.wideoapp.WideoAppSecurity.Web.Controller;

import com.wideoapp.WideoAppSecurity.Service.DislikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@CrossOrigin(origins="http://localhost:4200")
public class DislikeController {

    private DislikeService dislikeService;

    @Autowired
    public DislikeController(DislikeService dislikeService) {
        this.dislikeService = dislikeService;
    }

    //@PostMapping(path = "/add-dislike-to-video", consumes = "application/json")
    @PostMapping(path = "/dislike/add/video", consumes = "application/json")
    public ResponseEntity<?> addDislikeToVideo(@RequestBody Map<String, Object> body) {
        int id = Integer.parseInt(body.get("id").toString());
        String email = body.get("email").toString();
        dislikeService.addDislike(id, email);

        return ResponseEntity.ok().build();
    }

    //@GetMapping(path = "/is-dislike-to-video")
    @GetMapping(path = "/dislike/video")
    public boolean isDislikeToVideoRest(@RequestParam("id") String idString, @RequestParam("email") String email) {
        int id = Integer.parseInt(idString);
        boolean isDislike = dislikeService.isDislikeToVideo(id, email);

        return isDislike;
    }

    //@PostMapping(path = "/subtract-dislike-to-video", consumes = "application/json")
    @PostMapping(path = "/dislike/subtract/video", consumes = "application/json")
    public ResponseEntity<?> subtractDislikeToVideo(@RequestBody Map<String, Object> body) {
        int id = Integer.parseInt(body.get("id").toString());
        String email = body.get("email").toString();

        dislikeService.subtractDislike(id, email);

        return ResponseEntity.ok().build();
    }
}
