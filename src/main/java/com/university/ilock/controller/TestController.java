package com.university.ilock.controller;


import com.university.ilock.config.*;
import com.university.ilock.service.*;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.*;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Test Controller", description = "Set of endpoints for Creating, Retrieving, Updating and Deleting Entity.")
@RestController
@RequestMapping("/api/test")
@RequiredArgsConstructor
@Slf4j
public class TestController {
}
