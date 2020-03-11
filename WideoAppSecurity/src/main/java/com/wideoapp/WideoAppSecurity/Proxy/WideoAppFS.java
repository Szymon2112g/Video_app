package com.wideoapp.WideoAppSecurity.Proxy;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;


@FeignClient(name="WideoAppFilesStore")
@RibbonClient(name = "WideoAppFilesStore")
public interface WideoAppFS {

    @PostMapping(path = "/send-file", consumes = {"multipart/form-data"})
    public String handleFileUpload(@RequestBody MultipartFile file);
}
