package com.sportverein.repository;

import com.sportverein.entity.Turnier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface TurnierRepository extends JpaRepository<Turnier, Long> {
    List<Turnier> findByOrt(String ort);
    List<Turnier> findByDatum(String datum);
    List<Turnier> findByTeilnehmerId(Long mannschaftId);
}
