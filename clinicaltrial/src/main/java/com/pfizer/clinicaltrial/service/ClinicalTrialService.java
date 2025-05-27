package com.pfizer.clinicaltrial.service;

import com.pfizer.clinicaltrial.model.ClinicalTrial;
import com.pfizer.clinicaltrial.repository.ClinicalTrialRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClinicalTrialService {

    private final ClinicalTrialRepository repository;
    private final KafkaProducerService kafkaProducerService; // ✅ Injected via constructor

    public ClinicalTrial create(ClinicalTrial trial) {
        ClinicalTrial saved = repository.save(trial);
        kafkaProducerService.sendMessage("New clinical trial created: " + saved.getTitle()); // ✅ Kafka message
        return saved;
    }

    public List<ClinicalTrial> getAll() {
        return repository.findAll();
    }

    public Optional<ClinicalTrial> getById(Long id) {
        return repository.findById(id);
    }

    public ClinicalTrial update(Long id, ClinicalTrial updated) {
        updated.setId(id);
        return repository.save(updated);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}