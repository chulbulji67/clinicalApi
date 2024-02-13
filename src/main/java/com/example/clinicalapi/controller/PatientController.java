package com.example.clinicalapi.controller;

import com.example.clinicalapi.entity.ClinicalData;
import com.example.clinicalapi.entity.Patient;
import com.example.clinicalapi.repository.PatientRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/patients")
public class PatientController {

    @Autowired
    PatientRepo patientRepo;

    //Savig patient Data
    @PostMapping()
    public Patient savePatient(@RequestBody Patient patient){
        return patientRepo.save(patient);
    }

    //Getting all the patient information
    @GetMapping()
    public List<Patient> getAllPatient(){
        return patientRepo.findAll();
    }

    //GetPatientByid
    @GetMapping("/{id}")
    public Patient getPatientDetailsById(@PathVariable int id){
        return patientRepo.findById(id).orElse(null);
    }

    @GetMapping("/analyze/{id}")
    public Patient analyze(@PathVariable int id){
        Map<String, String> filters= new HashMap<>();
        Patient patient = patientRepo.findById(id).get();
        List<ClinicalData> clinicalData = patient.getClinicalDataList();
        List<ClinicalData> duplicateClinicalData = new ArrayList<>(clinicalData);
        for (ClinicalData eachEntry : duplicateClinicalData){
            if(filters.containsKey(eachEntry.getComponentName())){
                clinicalData.remove(eachEntry);
            }else {
                filters.put(eachEntry.getComponentName(),null );
            }
            if(eachEntry.getComponentName().equals("bp")){
            String[] heightAndWeight = eachEntry.getComponentValue().split("/");
            if(heightAndWeight != null && heightAndWeight.length >1){
                float heightInMetres = Float.parseFloat(heightAndWeight[0])*0.4536F;
                float bmi = Float.parseFloat(heightAndWeight[1])/(heightInMetres*heightInMetres);
                ClinicalData bmiData = new ClinicalData();
                bmiData.setComponentName("bmi");
                bmiData.setComponentValue(Float.toString(bmi));
                clinicalData.add(bmiData);
            }
        }}

     return patient;
    }
}
