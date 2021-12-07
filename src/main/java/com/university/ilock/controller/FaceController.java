package com.university.ilock.controller;

import com.university.ilock.dtos.FaceUnlockDto;
import com.university.ilock.service.FaceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@Slf4j
public class FaceController {
    private final FaceService faceService;

    @GetMapping("/face/enable")
    public void EnableFaceUnlocking(@RequestParam long deviceId){
        faceService.EnableFaceUnlocking(deviceId);
    }

    @PostMapping("/face/add")
    public boolean AddFace(@RequestBody FaceUnlockDto faceUnlockDto){
        return faceService.AddFace(faceUnlockDto);
    }

    @PostMapping("/face/validate")
    public boolean Validate(@RequestBody FaceUnlockDto faceUnlockDto) throws IOException {
        return faceService.Validate(faceUnlockDto);
    }

}
