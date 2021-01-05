package com.wideoapp.WideoAppSecurity.Web.Controller;

import com.wideoapp.WideoAppSecurity.Service.HistoryService;
import com.wideoapp.WideoAppSecurity.Web.Model.AddDisplayWithUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins="http://localhost:4200")
public class HistoryController {

    private HistoryService historyService;

    @Autowired
    public HistoryController(HistoryService historyService) {
        this.historyService = historyService;
    }

    //@PostMapping(path = "/add-display-with-user")
    @PostMapping(path = "/history/add/video")
    public ResponseEntity<?> addDisplayWithUser(@RequestBody AddDisplayWithUser addDisplayWithUser) {
        historyService.addHistory(addDisplayWithUser.getEmail(), addDisplayWithUser.getVideoId());
        return ResponseEntity.ok().build();
    }
}
