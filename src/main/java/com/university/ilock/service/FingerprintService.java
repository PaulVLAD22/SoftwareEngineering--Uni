package com.university.ilock.service;


import com.machinezoo.sourceafis.FingerprintImage;
import com.machinezoo.sourceafis.FingerprintImageOptions;
import com.machinezoo.sourceafis.FingerprintMatcher;
import com.machinezoo.sourceafis.FingerprintTemplate;
import com.university.ilock.Repository.DeviceRepository;
import com.university.ilock.dtos.FingerprintUnlockDto;
import com.university.ilock.model.Device;
import com.university.ilock.model.Fingerprint;
import com.university.ilock.utils.Constants;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor

public class FingerprintService {

    private final DeviceRepository deviceRepository;
    private final int threshold = 40;

    public boolean Register(FingerprintUnlockDto fingerprintDto){
        Device device = deviceRepository.getById(fingerprintDto.getId());
        if(!fingerprintDto.getPin().equals(deviceRepository.getById(fingerprintDto.getId()).getPin())){
            return false;
        }
        Fingerprint newFingerprint = Fingerprint.builder().content(fingerprintDto.getFingerprint()).build();
        device.getFingerprints().add(newFingerprint);
        deviceRepository.save(device);
        return true;
    }

    public boolean Validate(FingerprintUnlockDto fingerprintDto) throws IOException {

        byte[] decodedString = Base64.getDecoder().decode(new String(fingerprintDto.getFingerprint().getBytes()).getBytes("UTF-8"));

        FingerprintTemplate probe = new FingerprintTemplate(
                new FingerprintImage(
                        decodedString,
                        new FingerprintImageOptions()
                                .dpi(500)));
        Device device = deviceRepository.getById(fingerprintDto.getId());


        boolean res = device.getFingerprints().stream().anyMatch((fingerprint -> {
            try {
                byte[] decodedContent = Base64.getDecoder().decode(new String(fingerprint.getContent().getBytes()).getBytes("UTF-8"));
                FingerprintTemplate candidate = new FingerprintTemplate(
                        new FingerprintImage(
                                decodedContent,
                                new FingerprintImageOptions()
                                        .dpi(500)));
                double score = new FingerprintMatcher(probe).match(candidate);
                return score >= threshold;
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            return false;
        }));

        return res;
    }

}