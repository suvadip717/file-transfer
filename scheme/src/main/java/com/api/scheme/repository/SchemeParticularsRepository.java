package com.api.scheme.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.api.scheme.entity.SchemeParticulars;

@Repository
public interface SchemeParticularsRepository extends JpaRepository<SchemeParticulars, Long> {
    List<SchemeParticulars> findParticularsBySchemeId(Long schemeId);

}
