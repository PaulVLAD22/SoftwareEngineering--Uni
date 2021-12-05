package com.university.ilock.dtos;

import lombok.Data;

@Data
public class FaceUnlockDto {
    private long id;
    private String url;
    private String pin;
}
