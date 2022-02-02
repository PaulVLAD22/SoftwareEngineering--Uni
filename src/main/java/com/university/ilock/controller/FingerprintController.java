package com.university.ilock.controller;

import com.university.ilock.dtos.FaceUnlockDto;
import com.university.ilock.dtos.FingerprintUnlockDto;
import com.university.ilock.service.FaceService;
import com.university.ilock.service.FingerprintService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Tag(name = "Fingerprint Controller", description = "Set of endpoints for Creating, Retrieving, Updating and Deleting Entity.")
@RequestMapping("/api/fingerprint")
@RestController
@RequiredArgsConstructor
@Slf4j
public class FingerprintController {
    private final FingerprintService fingerprintService;

//    @PostMapping("/add")
//    public boolean AddFingerprint(@RequestBody FaceUnlockDto faceUnlockDto){
//        return faceService.AddFace(faceUnlockDto);
//    }

    @PostMapping("/validate")
    public boolean Validate(@RequestBody FingerprintUnlockDto fingerprintUnlockDto) throws IOException {
        return fingerprintService.Validate(fingerprintUnlockDto);
    }

    @PostMapping("/register")
    public boolean Register(@RequestBody FingerprintUnlockDto fingerprintUnlockDto) throws IOException {
        return fingerprintService.Register(fingerprintUnlockDto);
    }

}