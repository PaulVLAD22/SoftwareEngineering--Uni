package com.university.ilock.controller;

import com.university.ilock.model.EmailModel;
import com.university.ilock.model.MailUserModel;
import com.university.ilock.service.EmailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

@RestController
@RequiredArgsConstructor
@Slf4j
public class EmailController {

    private final EmailService emailService;

    @PostMapping(value = "/email/test")
    public void SendEmail () throws IOException {

        EmailModel email= new EmailModel();
        email.setSender(new MailUserModel());
        email.getSender().setName("ILock");
        email.getSender().setEmail("security@ilock.com");
        MailUserModel recipient = new MailUserModel();
        recipient.setEmail("acbc2815@gmail.com");
        recipient.setName("Grecu Bogdan");
        email.setRecipients(new ArrayList<MailUserModel>(Arrays.asList(recipient)));
        email.setSubject("Subject");
        email.setContent("Content");

        emailService.SendEmail(email);

    }
}
