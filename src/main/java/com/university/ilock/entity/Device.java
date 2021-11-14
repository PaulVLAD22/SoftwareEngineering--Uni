package com.university.ilock.entity;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;

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
