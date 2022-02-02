package com.university.ilock.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.university.ilock.model.EmailModel;
import com.university.ilock.model.MailUserModel;
import com.university.ilock.utils.Constants;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Map;

@Service
@Slf4j
@RequiredArgsConstructor
public class EmailService {

//    HttpClient httpClient = HttpClient.newHttpClient();
    private final RestTemplate restTemplate;

    @Autowired
    public EmailService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public void SendEmailToDevice(String email) throws JsonProcessingException {
        EmailModel emailModel = new EmailModel();
        MailUserModel sender = new MailUserModel();
        sender.setEmail("security@ilock.com");
        sender.setName("ILock");
        emailModel.setSender(sender);
        MailUserModel recipient = new MailUserModel();
        recipient.setEmail(email);
        recipient.setName("iLock Device");
        emailModel.setRecipients(new ArrayList<MailUserModel>(Arrays.asList(recipient)));
        emailModel.setSubject("iLock Security alert");
        emailModel.setContent(Constants.CallThePoliceMail);

        SendEmail(emailModel);
    }

    public void SendEmail(EmailModel email) throws JsonProcessingException {
        String url = "https://api.sendinblue.com/v3/smtp/email";

        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = ow.writeValueAsString(email);

        // create headers
        HttpHeaders headers = new HttpHeaders();
        // set `content-type` header
        headers.setContentType(MediaType.APPLICATION_JSON);
        // set `accept` header
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.set("api-key", "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");

        // create a map for post parameters
//        Map<String, Object> map = new HashMap<>();
//        map.put("userId", 1);
//        map.put("title", "Introduction to Spring Boot");
//        map.put("body", "Spring Boot makes it easy to create stand-alone, production-grade Spring based Applications.");

        String content = json;
        // build the request
        HttpEntity<String> entity = new HttpEntity<>(content, headers);

        // send POST request
        ResponseEntity<String> response = this.restTemplate.postForEntity(url, entity, String.class);

        System.out.println(response);
    }

}
