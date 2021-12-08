package com.university.ilock.controller;


import com.university.ilock.config.*;
import com.university.ilock.service.*;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.*;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Pin Controller", description = "Set of endpoints for Creating, Retrieving, Updating and Deleting Entity.")
@RestController
@RequestMapping("/api/pin")
@RequiredArgsConstructor
@Slf4j
public class PINController {

    private final PINService pinService;

    @PutMapping("/update")
    public void updatePin(@RequestParam long deviceId,
                          @RequestParam String pin) throws MqttException {
        pinService.updatePINForDevice(deviceId,pin);

        String response = "Raspunsul meu";
        String topic = "mytopic";

        MqttMessage mqttMessage = new MqttMessage(response.getBytes());
        mqttMessage.setQos(0);
        mqttMessage.setRetained(true);

        Mqtt.getInstance().publish(topic, mqttMessage);
    }

    @GetMapping("/validate")
    public void validatePin(@RequestParam long deviceId,
                               @RequestParam String pin) throws MqttException {
        boolean valid = pinService.validatePin(deviceId,pin);

        String response =  String.valueOf(valid);
        String topic = "mytopic";

        MqttMessage mqttMessage = new MqttMessage(response.getBytes());
        mqttMessage.setQos(0);
        mqttMessage.setRetained(true);

        Mqtt.getInstance().publish(topic, mqttMessage);

    }

}
