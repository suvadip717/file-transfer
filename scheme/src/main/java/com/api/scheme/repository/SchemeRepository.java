package com.api.scheme.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.api.scheme.entity.Scheme;

@Repository
public interface SchemeRepository extends JpaRepository<Scheme, Long> {

}
