package com.university.ilock.model;

import lombok.*;
import javax.persistence.*;

@Data
@Entity
@Table(name = "device")
public class Device {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String pin;
    private Byte [] NfcSignal;
    //fingerprint
    //faceId
    // alte metode de control (ore de acces)
}
