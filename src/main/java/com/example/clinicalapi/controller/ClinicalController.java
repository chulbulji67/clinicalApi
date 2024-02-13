package com.example.clinicalapi.controller;

import com.example.clinicalapi.dto.ClinicDto;
import com.example.clinicalapi.entity.ClinicalData;
import com.example.clinicalapi.entity.Patient;
import com.example.clinicalapi.repository.ClinicalDataRepo;
import com.example.clinicalapi.repository.PatientRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clinicaldata")
public class ClinicalController {

    @Autowired
    ClinicalDataRepo clinicalDataRepo;
    @Autowired
    PatientRepo patientRepo;

    //Save the clinical data for an specific patient

    @PostMapping()
    public ClinicalData saveClinicalData(@RequestBody ClinicDto clinicDto) throws Exception {
        Patient patient = patientRepo.findById(clinicDto.getPatientId()).orElse(null);
        if(patient == null) throw  new Exception("Patient Did not found");
        ClinicalData clinicalData = new ClinicalData();
        clinicalData.setComponentName(clinicDto.getComponentName());
        clinicalData.setComponentValue(clinicDto.getComponentValue());
        clinicalData.setMeasuredDateTime(clinicDto.getMeasuredDateTime());
        clinicalData.setPatient(patient);
        return clinicalDataRepo.save(clinicalData);
    }


    //GetAllClinicalData by patient id
    @GetMapping("/{id}")
    public List<ClinicalData> findAllClinicalDataBasedOnPatientId(@PathVariable int id){
        return patientRepo.findById(id).get().getClinicalDataList();
    }
}
