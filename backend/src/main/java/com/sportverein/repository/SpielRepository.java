package com.sportverein.repository;

import com.sportverein.entity.MannschaftEntity;
import com.sportverein.entity.SpielEntity;
import com.sportverein.model.SpielStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

/**
 * Repository für den Zugriff auf Spiel-Entitäten in der Datenbank
 */
@Repository
public interface SpielRepository extends JpaRepository<SpielEntity, Long> {
    List<SpielEntity> findByHeimMannschaftOrGastMannschaft(MannschaftEntity heimMannschaft, MannschaftEntity gastMannschaft);
    List<SpielEntity> findByStatus(SpielStatus status);
    List<SpielEntity> findByDatumBetween(LocalDate von, LocalDate bis);
    List<SpielEntity> findByHeimMannschaft(MannschaftEntity heimMannschaft);
    List<SpielEntity> findByGastMannschaft(MannschaftEntity gastMannschaft);
    List<SpielEntity> findByLiga_Name(String ligaName);
}
