package com.wideoapp.WideoAppSecurity.Proxy;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;

@FeignClient(name="netflix-zuul-api-gateway-server2",url = "localhost:8766")
@RibbonClient(name = "WideoAppFilesStore")
public interface WideoAppFS {

    //@PostMapping(path = "/videoappfilesstore/addvideofile", consumes = {"multipart/form-data"})
    //    public ResponseEntity<?> handleFileUpload(@RequestBody MultipartFile file);


    @GetMapping("/wideoappfilesstore/usertest")
    public String gettest();


    @PostMapping(path = "/wideoappfilesstore/addvideofile", consumes = {"multipart/form-data"})
    public ResponseEntity<String> handleFileUpload(@RequestBody MultipartFile file);



}
