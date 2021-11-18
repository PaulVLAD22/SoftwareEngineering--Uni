package com.university.ilock.config;

import com.university.ilock.mqtt.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

@RestController
public class MqttController {
    @Autowired
    MqttGateway mqtGateway;
    @PostMapping("/sendMessage")
    public ResponseEntity<?> publish(@RequestBody String mqttMessage){

        try {
            // asa luam ce am primit
            JsonObject convertObject = new Gson().fromJson(mqttMessage, JsonObject.class);
            System.out.println(convertObject.get("message"));

            // asa trimitem raspunsul
            mqtGateway.senToMqtt(convertObject.get("message").toString(), convertObject.get("topic").toString());
            return ResponseEntity.ok("Success");
        }catch(Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.ok("fail");
        }
    }

}