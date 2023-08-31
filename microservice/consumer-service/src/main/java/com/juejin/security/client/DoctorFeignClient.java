package com.juejin.security.client;

import com.juejin.security.config.FeignConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

//@FeignClient(name = "provider-service", path = "/doctors")
@FeignClient(name = "provider-service", path = "/doctors", configuration = FeignConfiguration.class)
public interface DoctorFeignClient {

    @RequestMapping("/mock")
    String mock();
}