package com.university.ilock.controller;


import com.university.ilock.Repository.DeviceRepository;
import com.university.ilock.config.*;
import com.university.ilock.model.Device;
import com.university.ilock.service.*;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.*;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Pin Controller", description = "Set of endpoints for Creating, Retrieving, Updating and Deleting Entity.")
@RestController
@RequestMapping("/api/pin")
@RequiredArgsConstructor
@Slf4j
public class PINController {
    private final DeviceRepository deviceRepository;
    private final PINService pinService;

    @PutMapping("/update")
    public void updatePin(@RequestParam long deviceId,
                          @RequestParam String pin) throws MqttException {
        pinService.updatePINForDevice(deviceId,pin);
    }

    @GetMapping("/validate")
    public boolean validatePin(@RequestParam long deviceId,
                               @RequestParam String pin) throws MqttException {
        boolean valid = pinService.validatePin(deviceId,pin);
        if (valid){
            Device device = deviceRepository.getById(deviceId);
            device.setIsLocked(false);
            deviceRepository.save(device);
        }
        return valid;

    }

}
