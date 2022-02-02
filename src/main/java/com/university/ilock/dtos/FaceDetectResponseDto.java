package com.university.ilock.dtos;

import lombok.Data;

@Data
public class FaceDetectResponseDto {
    public String persistedFaceId;
    public double confidence;
}
