package com.gulteking.mqttbackendserver.controller;

import com.gulteking.mqttbackendserver.config.*;
import com.gulteking.mqttbackendserver.service.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
public class PINController {

    private final DeviceService pinService;

    @PutMapping("/pin/update")
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

    @GetMapping("/pin/validate")
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
