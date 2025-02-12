package sportverein;

import java.util.Date;

public class Stuermer extends Spieler {
    private int geschosseneTore;
    private double schussgenauigkeit;
    private double chancenverwertung;

    public Stuermer(int geschosseneTore, double schussgenauigkeit, double chancenverwertung,
                    String nachname, String vorname, Date geburtsdatum, int gespielteSpiele,
                    boolean gesperrt, Date vereinsbeitritt, int roteKarten, int gelbeKarten) {
        super(nachname, vorname, geburtsdatum, gespielteSpiele, gesperrt, vereinsbeitritt, roteKarten, gelbeKarten);
        this.geschosseneTore = geschosseneTore;
        this.schussgenauigkeit = schussgenauigkeit;
        this.chancenverwertung = chancenverwertung;
    }
    
    public int getGeschosseneTore() {
        return geschosseneTore;
    }
    
    public double getSchussgenauigkeit() {
        return schussgenauigkeit;
    }
    
    public double getChancenverwertung() {
        return chancenverwertung;
    }

    public int getGespielteSpiele() {
        // Hier rufen wir den (angenommen vorhandenen) Getter der Superklasse auf.
        return super.getGespielteSpiele();
    }

    @Override
    // Spielerquote ausgeben
    public double spielerBewertung() {
        int spiele = getGespielteSpiele();
        double normToreProSpiel = 0.0;
        if (spiele > 0) {
            normToreProSpiel = ((double)getGeschosseneTore() / spiele) / 5.0;
        }

        double gewichtungTore = 5.0;
        double gewichtungSchussgenauigkeit = 3.0;
        double gewichtungChancenverwertung = 4.0;
        
        // Schritt 3: Berechnung der Bewertung (gewichtete Summe)
        double bewertung = normToreProSpiel * gewichtungTore
                         + getSchussgenauigkeit() * gewichtungSchussgenauigkeit
                         + getChancenverwertung() * gewichtungChancenverwertung;

        double gesamtGewichtung = gewichtungTore + gewichtungSchussgenauigkeit + gewichtungChancenverwertung;
        bewertung = (bewertung / gesamtGewichtung) * 100.0;

        return bewertung;
    }

    @Override
    public String spielerstatistikAusgeben() {
        return "Stürmerstatistik: Tore = " + getGeschosseneTore() +
               ", Schussgenauigkeit = " + getSchussgenauigkeit() +
               ", Chancenverwertung = " + getChancenverwertung();
    }
}
