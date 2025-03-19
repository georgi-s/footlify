package com.sportverein.repository;

import com.sportverein.entity.SpielerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpielerRepository extends JpaRepository<SpielerEntity, Long> {
    // Hier können weitere benutzerdefinierte Query-Methoden ergänzt werden.
}
