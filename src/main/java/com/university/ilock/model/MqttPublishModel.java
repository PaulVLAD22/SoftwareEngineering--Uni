package com.university.ilock.model;

import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
@Data
public class MqttPublishModel {

    @NotNull
    @Size(min = 1,max = 255)
    private String topic;

    @NotNull
    @Size(min = 1,max = 255)
    private String message;

    @NotNull
    private Boolean retained;

    @NotNull
    private Integer qos;

    @NotNull
    private String method;

}
