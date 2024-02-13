package com.example.clinicalapi.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int patientId;

    private String patientFirstName;

    private String patientLastName;

    private String patientAge;

//    @JsonBackReference
    @OneToMany(mappedBy = "patient")
    private List< ClinicalData> clinicalDataList;
}
