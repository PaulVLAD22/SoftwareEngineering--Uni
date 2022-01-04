package com.university.ilock.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.university.ilock.Repository.DeviceRepository;
import com.university.ilock.dtos.DevicePostDto;
import com.university.ilock.model.Device;
import com.university.ilock.utils.Constants;
import dev.samstevens.totp.secret.DefaultSecretGenerator;
import dev.samstevens.totp.secret.SecretGenerator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.jni.Time;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.TemporalAmount;

@Service
@Slf4j
@RequiredArgsConstructor
public class DeviceService {
    private final DeviceRepository deviceRepository;
    private final EmailService emailService;
    private final SecretGenerator secretGenerator = new DefaultSecretGenerator();

    public void RegisterDevice(DevicePostDto deviceDto){
        Device device = new Device();
        device.setPin(deviceDto.getPin());
        device.setEmail(deviceDto.getEmail());
        device.setTotpSecret(secretGenerator.generate());
        deviceRepository.save(device);
    }

    public void RegisterWrongInput(long id){
        Device device = deviceRepository.getById(id);
        // add reset logic
        if(device.getLastWrongInputDate() != null && device.getLastWrongInputDate().isAfter(LocalTime.now().plusHours(1))){
            device.setNumberOfWrongInputs(0);
        }
        device.setLastWrongInputDate(LocalTime.now());
        device.setNumberOfWrongInputs(device.getNumberOfWrongInputs() + 1);
        if(device.getNumberOfWrongInputs() >= Constants.wrongInputCallThePolice){
            // block device
            device.setBlocked(true);
            // send email
            try {
                emailService.SendEmailToDevice(device.getEmail());
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
        deviceRepository.save(device);
    }

    public boolean CheckInputAvailability(long id){
        Device device = deviceRepository.getById(id);
        if(device.isBlocked()){
            return false;
        }
        if(device.getNumberOfWrongInputs() > 0
                && device.getNumberOfWrongInputs() % 3 == 0
                && !(LocalTime.now().isAfter(device.getLastWrongInputDate().plusSeconds(Constants.wrongInputTimeout)))) {
            return false;
        }
        return true;
    }
}
