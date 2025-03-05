package com.sportverein.config;

import com.sportverein.entity.*;
import com.sportverein.service.DataService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.time.LocalDate;

@Configuration
@Profile("dev")
public class TestDataSeeder {

    @Bean
    CommandLineRunner initDatabase(DataService dataService) {
        return args -> {
            // Bayern München
            var bayernMuenchen = new Mannschaft();
            bayernMuenchen.setName("FC Bayern München");
            bayernMuenchen.setTrainer("Thomas Tuchel");
            bayernMuenchen.setFormation(Formation.f433);
            bayernMuenchen.setLiga(Liga.Bundesliga1);
            bayernMuenchen = dataService.saveMannschaft(bayernMuenchen);

            // Stürmer
            var mueller = new Stuermer();
            mueller.setGeschosseneTore(15);
            mueller.setSchussgenauigkeit(0.65);
            mueller.setChancenverwertung(0.30);
            mueller.setNachname("Müller");
            mueller.setVorname("Thomas");
            mueller.setGeburtsdatum(LocalDate.of(1989, 10, 13));
            mueller.setGespielteSpiele(20);
            mueller.setGesperrt(false);
            mueller.setVereinsbeitritt(LocalDate.of(2000, 8, 1));
            mueller.setRoteKarten(0);
            mueller.setGelbeKarten(2);
            mueller.setMannschaft(bayernMuenchen);
            dataService.saveSpieler(mueller);

            var lewandowski = new Stuermer();
            lewandowski.setGeschosseneTore(20);
            lewandowski.setSchussgenauigkeit(0.70);
            lewandowski.setChancenverwertung(0.40);
            lewandowski.setNachname("Lewandowski");
            lewandowski.setVorname("Robert");
            lewandowski.setGeburtsdatum(LocalDate.of(1988, 9, 21));
            lewandowski.setGespielteSpiele(18);
            lewandowski.setGesperrt(false);
            lewandowski.setVereinsbeitritt(LocalDate.of(2014, 8, 1));
            lewandowski.setRoteKarten(1);
            lewandowski.setGelbeKarten(1);
            lewandowski.setMannschaft(bayernMuenchen);
            dataService.saveSpieler(lewandowski);

            // Mittelfeld
            var kimmich = new Mittelfeldspieler();
            kimmich.setAnzahlVorlagen(10);
            kimmich.setTore(6);
            kimmich.setPassquote(0.88);
            kimmich.setNachname("Kimmich");
            kimmich.setVorname("Joshua");
            kimmich.setGeburtsdatum(LocalDate.of(1995, 3, 8));
            kimmich.setGespielteSpiele(22);
            kimmich.setGesperrt(false);
            kimmich.setVereinsbeitritt(LocalDate.of(2015, 8, 1));
            kimmich.setRoteKarten(0);
            kimmich.setGelbeKarten(3);
            kimmich.setMannschaft(bayernMuenchen);
            dataService.saveSpieler(kimmich);

            // Verteidiger
            var deLigt = new Verteidiger();
            deLigt.setGeblockteAngriffe(30);
            deLigt.setGewonneneZweikaempfe(60);
            deLigt.setPassqoute(0.89);
            deLigt.setNachname("de Ligt");
            deLigt.setVorname("Matthijs");
            deLigt.setGeburtsdatum(LocalDate.of(1999, 9, 12));
            deLigt.setGespielteSpiele(22);
            deLigt.setGesperrt(false);
            deLigt.setVereinsbeitritt(LocalDate.of(2022, 8, 1));
            deLigt.setRoteKarten(1);
            deLigt.setGelbeKarten(3);
            deLigt.setMannschaft(bayernMuenchen);
            dataService.saveSpieler(deLigt);

            // Borussia Dortmund
            var bvb = new Mannschaft();
            bvb.setName("Borussia Dortmund");
            bvb.setTrainer("Edin Terzić");
            bvb.setFormation(Formation.f433);
            bvb.setLiga(Liga.Bundesliga1);
            bvb = dataService.saveMannschaft(bvb);

            // Beispiel-Turnier
            var turnier = new Turnier();
            turnier.setOrt("München");
            turnier.setDatum("2025-08-01");
            turnier.setInsgPreisgeld(100000);
            turnier.getTeilnehmer().add(bayernMuenchen);
            turnier.getTeilnehmer().add(bvb);
            dataService.saveTurnier(turnier);
        };
    }
}
