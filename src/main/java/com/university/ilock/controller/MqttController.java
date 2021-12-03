package com.university.ilock.controller;

import com.university.ilock.config.Mqtt;
import com.university.ilock.exceptions.ExceptionMessages;
import com.university.ilock.exceptions.MqttException;
import com.university.ilock.model.MqttPublishModel;
import com.university.ilock.model.MqttSubscribeModel;
import com.university.ilock.service.*;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.beans.factory.annotation.*;
import org.springframework.context.annotation.*;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping(value = "/api/mqtt")
public class MqttController {
    @Autowired
    private PINService deviceService;


    @PostMapping("publish")
    public void publishMessage(@RequestBody @Valid MqttPublishModel messagePublishModel,
                               BindingResult bindingResult) throws org.eclipse.paho.client.mqttv3.MqttException {
        if (bindingResult.hasErrors()) {
            throw new MqttException(ExceptionMessages.SOME_PARAMETERS_INVALID);
        }

//        if (messagePublishModel.getMethod().equals("validatePin")){
//            deviceService.validatePin()
//        }
        String response = "Raspunsul meu";

        MqttMessage mqttMessage = new MqttMessage(response.getBytes());
        mqttMessage.setQos(messagePublishModel.getQos());
        mqttMessage.setRetained(messagePublishModel.getRetained());

        Mqtt.getInstance().publish(messagePublishModel.getTopic(), mqttMessage);
    }

//    @GetMapping("subscribe")
//    public List<MqttSubscribeModel> subscribeChannel(@RequestParam(value = "topic") String topic,
//                                                     @RequestParam(value = "wait_millis") Integer waitMillis)
//            throws InterruptedException, org.eclipse.paho.client.mqttv3.MqttException {
    @Bean
    public void listenToTopic() throws org.eclipse.paho.client.mqttv3.MqttException {
        String outputTopic ="responseTopic";
        String inputTopic = "mytopic";
        List<MqttSubscribeModel> messages = new ArrayList<>();


        Mqtt.getInstance().subscribeWithResponse(inputTopic, (s, mqttMessage) -> {
            String message = new String(mqttMessage.getPayload());

            System.out.println(message);

            MqttMessage mqttMessage2 = new MqttMessage(message.getBytes());
            mqttMessage.setQos(0);
            mqttMessage.setRetained(true);

            Mqtt.getInstance().publish(outputTopic, mqttMessage2);
        });
    }


}
