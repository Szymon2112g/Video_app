package com.wideoapp.WideoAppSecurity.Web.Controller;

import com.wideoapp.WideoAppSecurity.Service.HistoryService;
import com.wideoapp.WideoAppSecurity.Service.JwtTokenService;
import com.wideoapp.WideoAppSecurity.Web.Controller.Exception.NoFoundObjectException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
//@CrossOrigin(origins="http://localhost:8765")
public class HistoryController {

    private HistoryService historyService;
    private JwtTokenService jwtTokenService;

    @Autowired
    public HistoryController(HistoryService historyService, JwtTokenService jwtTokenService) {
        this.historyService = historyService;
        this.jwtTokenService = jwtTokenService;
    }

    //@PostMapping(path = "/add-display-with-user")
    @PostMapping(path = "/history/video/{videoId}")
    public ResponseEntity<?> addDisplayWithUser(@RequestHeader Map<String, String> header,
                                                @PathVariable int videoId) throws NoFoundObjectException {

        String email = jwtTokenService.findEmailFromTokenOfHeader(header);

        boolean isSuccess = historyService.addHistory(email, videoId);

        if (!isSuccess) {
            throw new NoFoundObjectException("history can't be added");
        }

        return ResponseEntity.ok().build();
    }
}
