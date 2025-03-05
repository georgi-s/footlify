package com.sportverein.repository;

import com.sportverein.entity.Mannschaft;
import com.sportverein.entity.Liga;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface MannschaftRepository extends JpaRepository<Mannschaft, Long> {
    List<Mannschaft> findByLiga(Liga liga);
    Mannschaft findByName(String name);
}
