package com.university.ilock.model;

import lombok.*;

import javax.persistence.*;
import java.sql.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "audit")
public class Audit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String unlockingMethod;
    private Timestamp timestamp;
}
