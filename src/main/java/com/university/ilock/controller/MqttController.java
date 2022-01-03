package com.university.ilock.controller;

import com.university.ilock.Repository.*;
import com.university.ilock.config.Mqtt;
import com.university.ilock.exceptions.ExceptionMessages;
import com.university.ilock.exceptions.MqttException;
import com.university.ilock.model.*;
import com.university.ilock.service.*;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.beans.factory.annotation.*;
import org.springframework.context.annotation.*;
import org.springframework.scheduling.annotation.*;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;


@RestController
@Tag(name = "MQTT Controller", description = "Set of endpoints for Creating, Retrieving, Updating and Deleting Entity.")
@RequestMapping(value = "/api/mqtt")
public class MqttController {
    @Autowired
    private AuditRepository auditRepository;
    @Autowired
    private DeviceRepository deviceRepository;

    @Scheduled(fixedRate = 5000)
    public void publishStatus() throws org.eclipse.paho.client.mqttv3.MqttException {
        String status = String.valueOf(deviceRepository.getById(deviceId).getIsLocked());
//        List<Audit> auditList = auditRepository.getLastFive();
//
//        String status = auditList.stream().map(Audit::toString).reduce("",(audit1,audit2)-> audit1+"\n"+audit2);

        String topic = "mytopic";
        MqttMessage mqttMessage = new MqttMessage(status.getBytes());
        mqttMessage.setQos(0);
        mqttMessage.setRetained(true);

        Mqtt.getInstance().publish(topic, mqttMessage);
    }

    @PostMapping("/publish")
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

    @GetMapping("/subscribe")
    public List<MqttSubscribeModel> subscribeChannel(@RequestParam(value = "topic") String topic,
                                                     @RequestParam(value = "wait_millis") Integer waitMillis)
            throws InterruptedException, org.eclipse.paho.client.mqttv3.MqttException {
        List<MqttSubscribeModel> messages = new ArrayList<>();
        CountDownLatch countDownLatch = new CountDownLatch(10);
        Mqtt.getInstance().subscribeWithResponse(topic, (s, mqttMessage) -> {
            MqttSubscribeModel mqttSubscribeModel = new MqttSubscribeModel();
            mqttSubscribeModel.setId(mqttMessage.getId());
            mqttSubscribeModel.setMessage(new String(mqttMessage.getPayload()));
            mqttSubscribeModel.setQos(mqttMessage.getQos());
            messages.add(mqttSubscribeModel);
            countDownLatch.countDown();
        });

        countDownLatch.await(waitMillis, TimeUnit.MILLISECONDS);

        return messages;
    }


}
