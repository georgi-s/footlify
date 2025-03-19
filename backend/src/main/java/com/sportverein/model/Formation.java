package com.sportverein.model;

public enum Formation {
    f442(4, 4, 2),
    f532(5, 3, 2),
    f433(4, 3, 3),
    f343(3, 4, 3);

    private final int verteidigerAnzahl;
    private final int mittelfeldspielerAnzahl;
    private final int stuermerAnzahl;

    Formation(int verteidiger, int mittelfeldspieler, int stuermer) {
        this.verteidigerAnzahl = verteidiger;
        this.mittelfeldspielerAnzahl = mittelfeldspieler;
        this.stuermerAnzahl = stuermer;
    }

    public int getVerteidigerAnzahl() {
        return verteidigerAnzahl;
    }

    public int getMittelfeldspielerAnzahl() {
        return mittelfeldspielerAnzahl;
    }

    public int getStuermerAnzahl() {
        return stuermerAnzahl;
    }
}
