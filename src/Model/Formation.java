// pfad: STDM_GruppeC_SCPM06/src/sportverein/Formation.java 
package Model;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */

/**
 *
 * @author mwiederspahn
 */
public enum Formation {
    f442(4, 4, 2),
    f532(5, 3, 2),
    f433(4, 3, 3),
    f343(3, 4, 3);

    private final Integer verteidigerAnzahl;
    private final Integer mittelfeldspielerAnzahl;
    private final Integer stuermerAnzahl;

    Formation(int verteidiger, int mittelfeldspielerAnzahl, int stuermerAnzahl) {
        this.verteidigerAnzahl = verteidiger;
        this.mittelfeldspielerAnzahl = mittelfeldspielerAnzahl;
        this.stuermerAnzahl = stuermerAnzahl;
    }

    public Integer getVerteidigerAnzahl() {
        return verteidigerAnzahl;
    }

    public Integer getMittelfeldspielerAnzahl() {
        return mittelfeldspielerAnzahl;
    }

    public Integer getStuermerAnzahl() {
        return stuermerAnzahl;
    }
}


