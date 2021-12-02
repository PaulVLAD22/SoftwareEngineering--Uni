package com.university.ilock;

import com.university.ilock.service.*;
import lombok.extern.slf4j.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.*;

@SpringBootApplication
@Slf4j
public class ILock {
    @Autowired
    public PINService pinService;
    @Autowired
    public OtcService otcService;

    public static void main(String[] args) {
        SpringApplication.run(ILock.class, args);
    }

    @Bean
    public void testPIN () {
        // Inputul
        long deviceId = 1;
        String newPIN = "141414";
        // Rularea functiilor
        pinService.updatePINForDevice(deviceId,newPIN);
        log.info(String.valueOf(pinService.validatePin(1,newPIN)));
    }
    @Bean
    public void testOneTimeCode(){
        int otc = otcService.generateOTC(1);
        log.info(String.valueOf(otc==otcService.getOTC(1)));
    }

}
