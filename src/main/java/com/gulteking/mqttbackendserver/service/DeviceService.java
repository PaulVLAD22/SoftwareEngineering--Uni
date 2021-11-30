package com.gulteking.mqttbackendserver.service;

import com.gulteking.mqttbackendserver.Repository.*;
import com.gulteking.mqttbackendserver.model.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class DeviceService {
    private final DeviceRepository deviceRepository;

    public void updatePINForDevice(long deviceId, String pin){
        Device device = deviceRepository.getById(deviceId);
        device.setPin(pin);
        deviceRepository.save(device);
    }
    public boolean validatePin(long deviceId, String pin){
        return pin.equals(deviceRepository.getById(deviceId).getPin());
    }

    public boolean validateDeviceId(long deviceId){
        Optional<Device> optionalDevice = deviceRepository.findById(deviceId);
        return optionalDevice.isPresent();
    }
}
