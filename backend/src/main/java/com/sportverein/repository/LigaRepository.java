package com.sportverein.repository;

import com.sportverein.entity.LigaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository für den Zugriff auf Liga-Entitäten in der Datenbank
 */
@Repository
public interface LigaRepository extends JpaRepository<LigaEntity, Long> {
    Optional<LigaEntity> findByName(String name);
}
