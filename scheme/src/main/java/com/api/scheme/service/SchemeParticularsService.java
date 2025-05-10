package com.api.scheme.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.api.scheme.entity.SchemeParticulars;
import com.api.scheme.repository.SchemeParticularsRepository;

@Service
public class SchemeParticularsService {

    @Autowired
    private SchemeParticularsRepository schemeParticularsRepository;

    public SchemeParticulars createSchemeParticular(SchemeParticulars schemeParticulars) {
        return schemeParticularsRepository.save(schemeParticulars);
    }

    public Optional<SchemeParticulars> getSchemeParticularById(Long id) {
        return schemeParticularsRepository.findById(id);
    }

    public List<SchemeParticulars> getAllSchemeParticulars() {
        return schemeParticularsRepository.findAll();
    }

    public List<SchemeParticulars> getParticularsBySchemeId(Long schemeId) {
        return schemeParticularsRepository.findParticularsBySchemeId(schemeId);
    }

    public SchemeParticulars updateSchemeParticular(Long id, SchemeParticulars newSchemeParticular) {
        Optional<SchemeParticulars> existingSchemeParticular = schemeParticularsRepository.findById(id);

        if (existingSchemeParticular.isPresent()) {
            SchemeParticulars updatedParticular = existingSchemeParticular.get();

            updatedParticular.setParticulars(newSchemeParticular.getParticulars());
            updatedParticular.setGroupName(newSchemeParticular.getGroupName());
            updatedParticular.setGroupId(newSchemeParticular.getGroupId());
            updatedParticular.setProductId(newSchemeParticular.getProductId());
            updatedParticular.setMinQty(newSchemeParticular.getMinQty());
            updatedParticular.setUnit(newSchemeParticular.getUnit());
            updatedParticular.setFreeQty(newSchemeParticular.getFreeQty());
            updatedParticular.setUnitFree(newSchemeParticular.getUnitFree());
            updatedParticular.setProductIdFree(newSchemeParticular.getProductIdFree());
            updatedParticular.setSchemeId(newSchemeParticular.getSchemeId());

            return schemeParticularsRepository.save(updatedParticular); // Save the updated entity
        } else {
            throw new RuntimeException("SchemeParticular not found with id: " + id);
        }
    }

    public void deleteSchemeParticular(Long id) {
        if (schemeParticularsRepository.existsById(id)) {
            schemeParticularsRepository.deleteById(id);
        } else {
            throw new RuntimeException("SchemeParticular not found with id: " + id);
        }
    }

    public void deleteAllSchemeParticulars() {
        schemeParticularsRepository.deleteAll();
    }

}
