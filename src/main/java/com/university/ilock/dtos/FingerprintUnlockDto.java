package com.university.ilock.dtos;

import lombok.Data;

@Data
public class FingerprintUnlockDto {
    private long id;
    private String fingerprint;
    private String pin;
}