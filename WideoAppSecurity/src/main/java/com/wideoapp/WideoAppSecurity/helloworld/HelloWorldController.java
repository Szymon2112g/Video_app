package com.wideoapp.WideoAppSecurity.helloworld;

import com.wideoapp.WideoAppSecurity.Proxy.WideoAppDB;
import com.wideoapp.WideoAppSecurity.Proxy.WideoAppFS;
import feign.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

//Controller
@CrossOrigin(origins="http://localhost:4200")
@RestController
public class HelloWorldController {

	@Autowired
	private WideoAppDB wideoAppDB;

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private WideoAppFS wideoAppFS;

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
			wideoAppFS.handleFileUpload(file);

		return ResponseEntity.ok().build();
	}



	@GetMapping(path = "/hello-world/path-variable/{name}")
	public HelloWorldBean helloWorldPathVariable(@PathVariable String name) {
		return new HelloWorldBean(String.format("Hello World, %s", name));
	}

}
