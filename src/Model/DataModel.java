package Model;

import View.DataModelListener;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class DataModel {
    private List<Spieler> spielerList;
    private List<Mannschaft> mannschaftList;
    private List<Turnier> turnierList;
    private Mannschaft selectedClub;
    private List<DataModelListener> listeners;

    public DataModel() {
        spielerList = new ArrayList<>();
        mannschaftList = new ArrayList<>();
        turnierList = new ArrayList<>();
        listeners = new ArrayList<>();
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
        selectedClub.addFeldspieler(spieler.getPlayerId());
        notifyListeners();
    }

    public void removeSpieler(Spieler spieler) {
        spielerList.remove(spieler);
        notifyListeners();
    }

    public void addMannschaft(Mannschaft mannschaft) {
        mannschaftList.add(mannschaft);
        notifyListeners();
    }

    public void removeMannschaft(Mannschaft mannschaft) {
        mannschaftList.remove(mannschaft);
        notifyListeners();
    }

    public void addTurnier(Turnier turnier) {
        turnierList.add(turnier);
        notifyListeners();
    }

    public void removeTurnier(Turnier turnier) {
        turnierList.remove(turnier);
        notifyListeners();
    }

    public Mannschaft getSelectedClub() {
        return selectedClub;
    }

    public void setSpielerList(List<Spieler> spielerList) {
        this.spielerList = spielerList;
        notifyListeners();
    }

    public void setSelectedClub(Mannschaft selectedClub) {
        this.selectedClub = selectedClub;
        notifyListeners();
    }

    public void setMannschaftList(List<Mannschaft> mannschaftList) {
        this.mannschaftList = mannschaftList;
        notifyListeners();
    }

    public Spieler getSpielerById(UUID playerId) {
        for (Spieler spieler : spielerList) {
            if (spieler.getPlayerId() == playerId) {
                return spieler;
            }
        }
        return null;
    }

    public Mannschaft getClubById(UUID clubId) {
        for (Mannschaft mannschaft : mannschaftList) {
            if (mannschaft.getClubId() == clubId) {
                return mannschaft;
            }
        }
        return null;
    }

    public void addDataModelListener(DataModelListener listener) {
        listeners.add(listener);
    }

    public void removeDataModelListener(DataModelListener listener) {
        listeners.remove(listener);
    }

    public void notifyListeners() {
        for (DataModelListener listener : listeners) {
            listener.onDataModelUpdated();
        }
    }
}