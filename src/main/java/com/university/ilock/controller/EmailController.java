package com.university.ilock.controller;

import com.university.ilock.model.EmailModel;
import com.university.ilock.model.MailUserModel;
import com.university.ilock.service.EmailService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

@Tag(name = "Email Controller", description = "Set of endpoints for Creating, Retrieving, Updating and Deleting Entity.")
@RequestMapping("/api/email")
@RestController
@RequiredArgsConstructor
@Slf4j
public class EmailController {

    private final EmailService emailService;

    @PostMapping(value = "/test")
    public void SendEmail (@RequestParam String email) throws IOException {
        emailService.SendEmailToDevice(email);
    }
}
