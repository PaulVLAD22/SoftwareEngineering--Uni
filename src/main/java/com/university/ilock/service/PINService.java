package com.university.ilock.service;


import com.university.ilock.Repository.*;
import com.university.ilock.model.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class PINService {
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