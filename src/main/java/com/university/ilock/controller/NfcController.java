package com.university.ilock.controller;

import com.university.ilock.dtos.NfcDto;
import com.university.ilock.service.NfcService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "NFC Controller", description = "Set of endpoints for Unlocking device with NFC")
@RequestMapping("/api/nfc")
@RestController
@RequiredArgsConstructor
@Slf4j
public class NfcController {

    private final NfcService nfcService;

    @PostMapping(value = "/validate")
    public boolean ValidateNfc(@RequestBody NfcDto nfcDto){
        return nfcService.ValidateNfc(nfcDto);
    }
}

// https://totp.app