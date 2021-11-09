package com.university.ilock.controller;

import com.university.ilock.exception.NoSuchDevice;
import com.university.ilock.service.DeviceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
public class PINController {

    private final DeviceService pinService;

    @PutMapping("/pin/update")
    public void updatePin(@RequestParam long deviceId,
                          @RequestParam String pin) {
       pinService.updatePINForDevice(deviceId,pin);
    }

    //localhost:8080/pin/validate?deviceId=1&pin=1234 exemplu request
    
    @PostMapping("/pin/validate")
    public boolean validatePin(@RequestParam long deviceId,
                          @RequestParam String pin){
        return pinService.validatePin(deviceId,pin);
    }


}
