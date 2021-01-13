package com.wideoapp.WideoAppSecurity.Web.Controller;

import com.wideoapp.WideoAppSecurity.Service.SubscribeService;
import com.wideoapp.WideoAppSecurity.Web.Model.SubscribedUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
//@CrossOrigin(origins="http://localhost:4200")
public class SubscriptionController {

    private SubscribeService subscribeService;

    @Autowired
    public SubscriptionController(SubscribeService subscribeService) {
        this.subscribeService = subscribeService;
    }


    //@GetMapping(path = "/get-subscription/{email}")
    @GetMapping(path = "/subscription/{email}")
    public ResponseEntity<?> getSubscriptions(@PathVariable("email") String email) {
        if (email == null) {
            return ResponseEntity.badRequest().build();
        }

        if (!checkIfStringIsEmail(email)) {
            return ResponseEntity.badRequest().build();
        }


        List<SubscribedUser> subscribes = subscribeService.getUserSubscriptions(email);
        return ResponseEntity.ok(subscribes);
    }

    //@PostMapping(path = "/add-subscription")
    @PostMapping(path = "/subscription/add")
    public ResponseEntity<?> addSubscription(@RequestBody Map<String, Object> body) {
        if (body.get("userId") == null || body.get("email") == null) {
            return ResponseEntity.badRequest().build();
        }

        if (!checkIfStringIsNumber(body.get(("userId")).toString())) {
            return ResponseEntity.badRequest().build();
        }

        if (checkIfStringIsEmail(body.get("email").toString())) {
            return ResponseEntity.badRequest().build();
        }

        int userVideoId = Integer.parseInt(body.get("userId").toString());
        String email = body.get("email").toString();

        subscribeService.addSubscription(email, userVideoId);
        return ResponseEntity.ok().build();
    }

    //@PostMapping(path = "/subtract-subscription")
    @PostMapping(path = "/subscription/subtract")
    public ResponseEntity<?> subtractSubscription(@RequestBody Map<String, Object> body) {
        if (body.get("userId") == null || body.get("email") == null) {
            return ResponseEntity.badRequest().build();
        }

        if (!checkIfStringIsNumber(body.get(("userId")).toString())) {
            return ResponseEntity.badRequest().build();
        }

        if (checkIfStringIsEmail(body.get("email").toString())) {
            return ResponseEntity.badRequest().build();
        }

        int userVideoId = Integer.parseInt(body.get("userId").toString());
        String email = body.get("email").toString();

        subscribeService.subtractSubscription(email, userVideoId);
        return ResponseEntity.ok().build();
    }

    private boolean checkIfStringIsNumber(String number) {
        return number.matches("\\d+");
    }

    @Deprecated
    private boolean checkIfStringIsEmail(String email) {
        return true;
    }

}
