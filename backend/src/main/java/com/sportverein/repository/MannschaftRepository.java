package com.sportverein.repository;

import com.sportverein.model.Mannschaft;
import com.sportverein.model.Formation;
import com.sportverein.model.Liga;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface MannschaftRepository extends JpaRepository<Mannschaft, Long> {
    List<Mannschaft> findByLiga(Liga liga);
    Mannschaft findByName(String name);
}
