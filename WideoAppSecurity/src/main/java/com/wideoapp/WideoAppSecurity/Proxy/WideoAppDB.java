package com.wideoapp.WideoAppSecurity.Proxy;

import org.springframework.cloud.openfeign.FeignClient;


@FeignClient(name="WideoAppDatabase")
public interface WideoAppDB {

}
