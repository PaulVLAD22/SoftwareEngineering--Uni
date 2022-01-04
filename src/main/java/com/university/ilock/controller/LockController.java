package com.university.ilock.controller;

import com.university.ilock.Repository.DeviceRepository;
import com.university.ilock.model.Device;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Pin Controller", description = "Set of endpoints for Creating, Retrieving, Updating and Deleting Entity.")
@RestController
@RequestMapping("/api/lock")
@RequiredArgsConstructor
@Slf4j
public class LockController {
    @Autowired
    private DeviceRepository deviceRepository;
    @GetMapping("/")
    public void lock(@RequestParam long deviceId) throws MqttException {
        Device device = deviceRepository.getById(deviceId);
        device.setIsLocked(true);
        deviceRepository.save(device);
    }
}
