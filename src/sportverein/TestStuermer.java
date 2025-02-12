package sportverein;

import java.util.Date;

public class TestStuermer {
    public static void main(String[] args) {
        // Beispiel-Daten (Hinweis: Der Konstruktor von Date, der hier verwendet wird, ist veraltet,
        // aber für einfache Tests ausreichend. Alternativ kann auch java.time.LocalDate genutzt werden.)
        // Beachte: Bei Date wird das Jahr als "Jahr - 1900" angegeben!
        Date geburtsdatum = new Date(80, 0, 1);      // entspricht 1. Januar 1980
        Date vereinsbeitritt = new Date(105, 0, 1);    // entspricht 1. Januar 2005

        // Erzeuge ein Stuermer-Objekt:
        // Parameter: geschosseneTore, schussgenauigkeit, chancenverwertung, 
        //            nachname, vorname, geburtsdatum, gespielteSpiele, gesperrt, 
        //            vereinsbeitritt, roteKarten, gelbeKarten
        Stuermer stuermer = new Stuermer(10, 0.7, 0.5, "Mueller", "Thomas",
                                        geburtsdatum, 20, false, vereinsbeitritt, 1, 2);

        // Ausgabe der Spielerstatistik
        System.out.println("Spielerstatistik:");
        System.out.println(stuermer.spielerstatistikAusgeben());

        // Berechnung und Ausgabe der Spielerbewertung
        System.out.println("Spielerbewertung:");
        System.out.println(stuermer.spielerBewertung());
    }
}
