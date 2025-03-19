package com.sportverein.repository;

import com.sportverein.entity.MannschaftEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MannschaftRepository extends JpaRepository<MannschaftEntity, Long> {
    // Eigene Abfrage-Methoden können hier ergänzt werden
}
