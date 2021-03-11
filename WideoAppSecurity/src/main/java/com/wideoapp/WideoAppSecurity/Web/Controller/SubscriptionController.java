package com.wideoapp.WideoAppSecurity.Web.Controller;

import com.wideoapp.WideoAppSecurity.Jwt.JwtTokenUtil;
import com.wideoapp.WideoAppSecurity.Service.JwtTokenService;
import com.wideoapp.WideoAppSecurity.Service.SubscribeService;
import com.wideoapp.WideoAppSecurity.Web.Controller.Exception.NoFoundObjectException;
import com.wideoapp.WideoAppSecurity.Web.Model.SubscribedUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
//@CrossOrigin(origins="http://localhost:8765")
public class SubscriptionController {

    private SubscribeService subscribeService;
    private JwtTokenService jwtTokenService;

    @Autowired
    public SubscriptionController(SubscribeService subscribeService, JwtTokenService jwtTokenService) {
        this.subscribeService = subscribeService;
        this.jwtTokenService = jwtTokenService;
    }

    //@GetMapping(path = "/get-subscription/{email}")
    @GetMapping(path = "/subscription/get/")
    public ResponseEntity<?> getUserSubscriptions(@RequestHeader Map<String, String> header)
            throws NoFoundObjectException {

        String email = jwtTokenService.findEmailFromTokenOfHeader(header);

        List<SubscribedUser> subscribes = subscribeService.getUserSubscriptions(email);

        if (subscribes == null || subscribes.isEmpty()) {
            throw new NoFoundObjectException("no found subscriptions");
        }

        return ResponseEntity.ok(subscribes);
    }

    //@PostMapping(path = "/add-subscription")
    @PostMapping(path = "/subscription/add/user/{userId}")
    public ResponseEntity<?> addSubscription(@RequestHeader Map<String, String> header,
                                             @PathVariable int userId) throws NoFoundObjectException {

        if (userId < 0) {
            return ResponseEntity.badRequest().build();
        }

        String email = jwtTokenService.findEmailFromTokenOfHeader(header);

        boolean isSuccess = subscribeService.addSubscription(email, userId);

        if (!isSuccess) {
            throw new NoFoundObjectException("subscription can't be added");
        }

        return ResponseEntity.ok().build();
    }

    //@PostMapping(path = "/subtract-subscription")
    @PostMapping(path = "/subscription/subtract/user/{userId}")
    public ResponseEntity<?> subtractSubscription(@RequestHeader Map<String, String> header,
                                                  @PathVariable int userId) throws NoFoundObjectException {

        if (userId < 0) {
            return ResponseEntity.badRequest().build();
        }

        String email = jwtTokenService.findEmailFromTokenOfHeader(header);

        boolean isSuccess = subscribeService.subtractSubscription(email, userId);

        if (!isSuccess) {
            throw new NoFoundObjectException("subscription can't be subtract");
        }

        return ResponseEntity.ok().build();
    }
}
