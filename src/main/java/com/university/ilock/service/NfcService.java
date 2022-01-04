package com.university.ilock.service;

import com.university.ilock.Repository.DeviceRepository;
import com.university.ilock.dtos.NfcDto;
import com.university.ilock.model.Device;
import dev.samstevens.totp.code.CodeGenerator;
import dev.samstevens.totp.code.CodeVerifier;
import dev.samstevens.totp.code.DefaultCodeGenerator;
import dev.samstevens.totp.code.DefaultCodeVerifier;
import dev.samstevens.totp.time.SystemTimeProvider;
import dev.samstevens.totp.time.TimeProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class NfcService {

    private final DeviceRepository deviceRepository;
    private final TimeProvider timeProvider = new SystemTimeProvider();
    private final CodeGenerator codeGenerator = new DefaultCodeGenerator();
    private final CodeVerifier verifier = new DefaultCodeVerifier(codeGenerator, timeProvider);

    public boolean ValidateNfc(NfcDto nfcDto){
        Device device = deviceRepository.getById(nfcDto.getPayload().getId());
        String secret = device.getTotpSecret();
        boolean response = verifier.isValidCode(secret, nfcDto.getPayload().getCode());
        return response;
    }
}