package com.sportverein.repository;

import com.sportverein.entity.Liga;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LigaRepository extends JpaRepository<Liga, Long> {
    Liga findByName(String name);
}
