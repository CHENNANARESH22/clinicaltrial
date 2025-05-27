package com.pfizer.clinicaltrial.controller;

import com.pfizer.clinicaltrial.model.ClinicalTrial;
import com.pfizer.clinicaltrial.service.ClinicalTrialService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clinicaltrials")
@RequiredArgsConstructor
public class ClinicalTrialController {

    private final ClinicalTrialService service;

    @PostMapping
    public ResponseEntity<ClinicalTrial> create(@RequestBody ClinicalTrial trial) {
        return ResponseEntity.ok(service.create(trial));
    }

    @GetMapping
    public ResponseEntity<List<ClinicalTrial>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClinicalTrial> getById(@PathVariable Long id) {
        return service.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClinicalTrial> update(@PathVariable Long id, @RequestBody ClinicalTrial updated) {
        return ResponseEntity.ok(service.update(id, updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}