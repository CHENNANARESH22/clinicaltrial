package com.pfizer.clinicaltrial.repository;

import com.pfizer.clinicaltrial.model.ClinicalTrial;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClinicalTrialRepository extends JpaRepository<ClinicalTrial, Long> {
}
