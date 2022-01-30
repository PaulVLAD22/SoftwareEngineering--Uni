package com.university.ilock.dtos;

import lombok.Data;

@Data
public class DeviceValidateDto {
    private long deviceId;
    private String pin;
}
