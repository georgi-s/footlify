package Model;

import java.util.ArrayList;
import java.util.List;

public class DataModel {
    private List<Spieler> spielerList;
    private List<Mannschaft> mannschaftList;
    private List<Turnier> turnierList;

    public DataModel() {
        spielerList = new ArrayList<>();
        mannschaftList = new ArrayList<>();
        turnierList = new ArrayList<>();
    }

    public List<Spieler> getSpielerList() {
        return spielerList;
    }

    public List<Mannschaft> getMannschaftList() {
        return mannschaftList;
    }

    public List<Turnier> getTurnierList() {
        return turnierList;
    }

    public void addSpieler(Spieler spieler) {
        spielerList.add(spieler);
    }

    public void removeSpieler(Spieler spieler) {
        spielerList.remove(spieler);
    }

    public void addMannschaft(Mannschaft mannschaft) {
        mannschaftList.add(mannschaft);
    }

    public void removeMannschaft(Mannschaft mannschaft) {
        mannschaftList.remove(mannschaft);
    }

    public void addTurnier(Turnier turnier) {
        turnierList.add(turnier);
    }

    public void removeTurnier(Turnier turnier) {
        turnierList.remove(turnier);
    }
}
