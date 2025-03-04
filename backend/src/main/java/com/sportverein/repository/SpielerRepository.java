package com.sportverein.repository;

import com.sportverein.entity.Spieler;
import com.sportverein.entity.Torwart;
import com.sportverein.entity.Verteidiger;
import com.sportverein.entity.Mittelfeldspieler;
import com.sportverein.entity.Stuermer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface SpielerRepository extends JpaRepository<Spieler, Long> {
    List<Spieler> findByMannschaftId(Long mannschaftId);
    
    @Query("SELECT s FROM Spieler s WHERE TYPE(s) = Torwart")
    List<Torwart> findAllTorwart();
    
    @Query("SELECT s FROM Spieler s WHERE TYPE(s) = Verteidiger")
    List<Verteidiger> findAllVerteidiger();
    
    @Query("SELECT s FROM Spieler s WHERE TYPE(s) = Mittelfeldspieler")
    List<Mittelfeldspieler> findAllMittelfeldspieler();
    
    @Query("SELECT s FROM Spieler s WHERE TYPE(s) = Stuermer")
    List<Stuermer> findAllStuermer();
}
