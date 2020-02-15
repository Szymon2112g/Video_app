package com.wideoapp.WideoAppSecurity.helloworld;

import com.wideoapp.WideoAppSecurity.Dao.UserDao;
import com.wideoapp.WideoAppSecurity.Entity.*;
import com.wideoapp.WideoAppSecurity.Proxy.WideoAppDB;
import com.wideoapp.WideoAppSecurity.Proxy.WideoAppFS;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

//Controller
@CrossOrigin(origins="http://localhost:4200")
@RestController
public class HelloWorldController {

	@Autowired
	private WideoAppDB wideoAppDB;

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private WideoAppFS wideoAppFS;

	@Autowired
	private UserDao userDao;

	@GetMapping(path = "/hello-world")
	public String helloWorld() {
		return "Hello World";
	}

	@GetMapping(path = "/hello-world-bean")
	public HelloWorldBean helloWorldBean() {
		//throw new RuntimeException("Some Error has Happened! Contact Support at ***-***");
		return new HelloWorldBean("wideoAppDB.gettest()");
	}


	@PostMapping(path = "/sendvideofile", consumes = {"multipart/form-data","application/json"})
	public ResponseEntity<?> SendVideoFile(@RequestBody MultipartFile file) {
		//ResponseEntity<?> cos = wideoAppFS.handleFileUpload(file);
		if(file != null) {
			logger.warn("file rozne od null");
		} else {
			logger.error("file jest null");
		}
			wideoAppDB.gettest();
			wideoAppFS.gettest();
			ResponseEntity<String> responseEntity = wideoAppFS.handleFileUpload(file);

		logger.error("path " + responseEntity.toString());

		return ResponseEntity.ok(new Testowa(responseEntity.getBody()));
	}

	@PostMapping(path = "/sendvideotodb")
	public ResponseEntity<?> sendVideoToDB(@RequestBody VideoToSend videoToSend)
	{
		logger.warn("wynik to " + videoToSend);

		User user = userDao.findByEmail(videoToSend.getEmail());

		VideoDetail videoDetail = new VideoDetail(0,0,0);


		Video video = new Video(videoToSend.getUrl(),videoToSend.getTitle(),
				videoToSend.getDescription(),null,videoDetail,null,videoToSend.getPhotoUrl());

		user.getVideoList().add(video);

		userDao.save(user);

		return ResponseEntity.ok().build();
	}

}
