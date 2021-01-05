package com.wideoapp.WideoAppSecurity.Web.Controller;

import com.wideoapp.WideoAppSecurity.Service.SubscribeService;
import com.wideoapp.WideoAppSecurity.Web.Model.GetSubscriptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins="http://localhost:4200")
public class SubscriptionController {

    private SubscribeService subscribeService;

    @Autowired
    public SubscriptionController(SubscribeService subscribeService) {
        this.subscribeService = subscribeService;
    }


    //@GetMapping(path = "/get-subscription/{email}")
    @GetMapping(path = "/subscription/get/{email}")
    public List<GetSubscriptions> getSubscriptions(@PathVariable("email") String email) {
        List<GetSubscriptions> subscribes = subscribeService.getUserSubscriptions(email);
        return subscribes;
    }

    //@PostMapping(path = "/add-subscription")
    @PostMapping(path = "/subscription/add")
    public ResponseEntity<?> addSubscription(@RequestBody Map<String, Object> body) {

        int userVideoId = Integer.parseInt(body.get("userId").toString());
        String email = body.get("email").toString();
        subscribeService.addSubscription(email, userVideoId);
        return ResponseEntity.ok().build();
    }

    //@PostMapping(path = "/subtract-subscription")
    @PostMapping(path = "/subscription/subtract")
    public ResponseEntity<?> subtractSubscription(@RequestBody Map<String, Object> body) {

        int userVideoId = Integer.parseInt(body.get("userId").toString());
        String email = body.get("email").toString();

        subscribeService.subtractSubscription(email, userVideoId);
        return ResponseEntity.ok().build();
    }

}
