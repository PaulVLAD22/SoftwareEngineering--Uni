package com.university.ilock.controller;


import com.university.ilock.dtos.DevicePostDto;
import com.university.ilock.model.EmailModel;
import com.university.ilock.model.MailUserModel;
import com.university.ilock.service.DeviceService;
import com.university.ilock.service.EmailService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

@Tag(name = "Device Controller", description = "Register")
@RequestMapping("/api/device")
@RestController
@RequiredArgsConstructor
@Slf4j
public class DeviceController {

    private final DeviceService deviceService;

    @PostMapping(value = "/register")
    public long RegisterDevice(@RequestBody DevicePostDto devicePostDto){
        return deviceService.RegisterDevice(devicePostDto);
    }
}