package com.wideoapp.WideoAppSecurity.Proxy;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name="netflix-zuul-api-gateway-server",url = "localhost:8765")
@RibbonClient(name = "WideoAppDatabase")
public interface WideoAppDB {

    @GetMapping("/wideoappdatabase/usertest")
    public String gettest();

}
