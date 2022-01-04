package com.university.ilock.model;

import lombok.*;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "device")
public class Device {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String pin;
    private boolean IsFaceUnlockEnabled;
    private String email;
    private String totpSecret;
    private int numberOfWrongInputs;
    private LocalTime lastWrongInputDate;
    private boolean isBlocked = false;

    @OneToMany(cascade = {CascadeType.ALL})
    private List<Fingerprint> fingerprints;

//    private Byte [] NfcSignal;
    //fingerprint
    //faceId
    // alte metode de control (ore de acces)
}
