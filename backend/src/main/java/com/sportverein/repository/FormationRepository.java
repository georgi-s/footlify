package com.sportverein.repository;

import com.sportverein.entity.FormationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FormationRepository extends JpaRepository<FormationEntity, Long> {
    Optional<FormationEntity> findByBezeichnung(String bezeichnung);
}
