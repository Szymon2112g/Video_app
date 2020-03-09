package com.wideoapp.WideoAppSecurity.Proxy;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;

import org.springframework.web.bind.annotation.GetMapping;


@FeignClient(name="netflix-zuul-api-gateway-server2",url = "localhost:8766")
@RibbonClient(name = "WideoAppFilesStore")
public interface WideoAppFS {

    @GetMapping(path = "/wideoappfilesstore/get-address-url")
    public String getAddressUrl();
}
