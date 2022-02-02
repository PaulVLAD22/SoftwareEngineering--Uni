package com.university.ilock.dtos;


import lombok.Data;

@Data
public class NfcDto {
    private int TypeLength;
    private int PayloadLength;
    private int IdLength;
    private String RecordType;
    private String Id;
    private NfcPayload Payload;
}

