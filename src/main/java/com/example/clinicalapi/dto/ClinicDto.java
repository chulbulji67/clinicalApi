package com.example.clinicalapi.dto;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
public class ClinicDto {
    private String componentName;

    private String componentValue;

    private Timestamp measuredDateTime;

    private int patientId;
}
