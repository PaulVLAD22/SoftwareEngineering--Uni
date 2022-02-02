package com.university.ilock.controller;

import com.university.ilock.dtos.FaceUnlockDto;
import com.university.ilock.service.FaceService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Tag(name = "Face Controller", description = "Set of endpoints for Creating, Retrieving, Updating and Deleting Entity.")
@RequestMapping("/api/face")
@RestController
@RequiredArgsConstructor
@Slf4j
public class FaceController {
    private final FaceService faceService;

    @GetMapping("/enable")
    public void EnableFaceUnlocking(@RequestParam long deviceId){
        faceService.EnableFaceUnlocking(deviceId);
    }

    @PostMapping("/add")
    public boolean AddFace(@RequestBody FaceUnlockDto faceUnlockDto){
        return faceService.AddFace(faceUnlockDto);
    }

    @PostMapping("/validate")
    public boolean Validate(@RequestBody FaceUnlockDto faceUnlockDto) throws IOException {
        return faceService.Validate(faceUnlockDto);
    }

}
