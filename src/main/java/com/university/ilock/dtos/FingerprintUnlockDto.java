package com.university.ilock.dtos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FingerprintUnlockDto {
    private long id;
    private String fingerprint;
    private String pin;
}