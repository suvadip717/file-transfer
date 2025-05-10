package com.api.scheme.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.api.scheme.entity.Scheme;
import com.api.scheme.repository.SchemeRepository;

@Service
public class SchemeService {

    @Autowired
    private SchemeRepository schemeRepository;

    public Scheme createScheme(Scheme scheme) {
        if (scheme.getSchemeParticulars() != null) {
        scheme.getSchemeParticulars().forEach(particular -> particular.setScheme(scheme));
    }
        return schemeRepository.save(scheme);
    }

    public List<Scheme> getAllSchemes() {
        return schemeRepository.findAll();
    }

    public Optional<Scheme> getSchemeById(Long schemeId) {
        return schemeRepository.findById(schemeId);
    }

    public Scheme updateScheme(Long schemeId, Scheme updatedScheme) {
        Optional<Scheme> existingScheme = schemeRepository.findById(schemeId);

        if (existingScheme.isPresent()) {
            Scheme scheme = existingScheme.get();
            scheme.setSchemeName(updatedScheme.getSchemeName());
            scheme.setSchemeType(updatedScheme.getSchemeType());
            scheme.setApplicableOn(updatedScheme.getApplicableOn());
            scheme.setStartDate(updatedScheme.getStartDate());
            scheme.setEndDate(updatedScheme.getEndDate());

            return schemeRepository.save(scheme);
        } else {
            throw new RuntimeException("Scheme not found with ID: " + schemeId);
        }
    }

    public void deleteScheme(Long schemeId) {
        Optional<Scheme> scheme = schemeRepository.findById(schemeId);
        if (scheme.isPresent()) {
            schemeRepository.delete(scheme.get());
        } else {
            throw new RuntimeException("Scheme not found with ID: " + schemeId);
        }
    }
}
