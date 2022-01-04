package com.university.ilock.controller;

import com.university.ilock.Repository.DeviceRepository;
import com.university.ilock.config.Mqtt;
import com.university.ilock.model.Device;
import com.university.ilock.service.OtcService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.web.bind.annotation.*;

@Tag(name = "One Time Use Code Controller", description = "Set of endpoints for Creating, Retrieving, Updating and Deleting Entity.")
@RestController
@RequestMapping("/api/otc")
@RequiredArgsConstructor
@Slf4j
public class OtcController {
    private final OtcService otcService;
    private final DeviceRepository deviceRepository;

    @GetMapping("/validate")
    public boolean validatePin(@RequestParam long deviceId,
                            @RequestParam int otc) {
        if (otc==otcService.getOTC(deviceId)){
            Device device = deviceRepository.getById(deviceId);
            device.setIsLocked(false);
            deviceRepository.save(device);
            return true;
        }
        return false;
    }
    @GetMapping("/generate")
    public int generateOtc(@RequestParam long deviceId){
        int otc = otcService.generateOTC(deviceId);
        return otc;
    }



}
