package com.example.clinicalapi.repository;

import com.example.clinicalapi.entity.ClinicalData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClinicalDataRepo extends JpaRepository<ClinicalData, Integer> {
}
