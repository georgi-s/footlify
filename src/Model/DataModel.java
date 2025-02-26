package Model;

import View.DataModelListener;

import java.sql.Date;
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

        createTestData();
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
    public Turnier getTurnierById(UUID turnierId) {
        for (Turnier turnier : turnierList) {
            if (turnier.getTurnierId() == turnierId) {
                return turnier;
            }
        }
        return null;
    }
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

    private void createTestData() {
        ArrayList<Spieler>players = new ArrayList<>();

        ArrayList<Spieler> playersBayern = new ArrayList<>();
        ArrayList<Spieler> auswechselSpielerBayern = new ArrayList<>();

        ArrayList<Spieler> playersDortmund = new ArrayList<>();
        ArrayList<Spieler> auswechselSpielerDortmund = new ArrayList<>();

        ArrayList<Spieler> playersSchalke = new ArrayList<>();
        ArrayList<Spieler> auswechselSpielerSchalke = new ArrayList<>();

        ArrayList<Spieler> playersMuenster = new ArrayList<>();
        ArrayList<Spieler> auswechselSpielerMuenster = new ArrayList<>();

        // Bayern München
        playersBayern.add(new Stuermer(15, 0.65, 0.30, "Müller", "Thomas", new Date(1989, 9, 13), 20, false, new Date(2000, 7, 1), 0, 2));
        playersBayern.add(new Stuermer(20, 0.70, 0.40, "Lewandowski", "Robert", new Date(1988, 8, 21), 18, false, new Date(2014, 7, 1), 1, 1));
        playersBayern.add(new Stuermer(10, 0.55, 0.25, "Gnabry", "Serge", new Date(1995, 7, 14), 19, false, new Date(2017, 7, 1), 0, 3));
        auswechselSpielerBayern.add(new Stuermer(8, 0.60, 0.20, "Sané", "Leroy", new Date(1996, 1, 11), 15, false, new Date(2020, 7, 1), 0, 2));

        // Borussia Dortmund (BVB)
        playersDortmund.add(new Stuermer(12, 0.62, 0.32, "Reus", "Marco", new Date(1989, 5, 31), 22, false, new Date(2012, 7, 1), 1, 4));
        playersDortmund.add(new Stuermer(18, 0.75, 0.45, "Haaland", "Erling", new Date(2000, 7, 21), 16, false, new Date(2020, 1, 1), 0, 1));
        playersDortmund.add(new Stuermer(9, 0.58, 0.27, "Malen", "Donyell", new Date(1999, 1, 19), 18, false, new Date(2021, 7, 1), 0, 2));
        auswechselSpielerDortmund.add(new Stuermer(6, 0.50, 0.18, "Moukoko", "Youssoufa", new Date(2004, 11, 20), 14, false, new Date(2020, 7, 1), 0, 0));

        // FC Schalke 04
        playersSchalke.add(new Stuermer(10, 0.55, 0.28, "Terodde", "Simon", new Date(1988, 3, 2), 17, false, new Date(2021, 7, 1), 1, 3));
        playersSchalke.add(new Stuermer(7, 0.50, 0.22, "Bülter", "Marius", new Date(1993, 3, 29), 16, false, new Date(2021, 7, 1), 0, 1));
        playersSchalke.add(new Stuermer(5, 0.48, 0.20, "Polter", "Sebastian", new Date(1991, 4, 1), 14, false, new Date(2022, 7, 1), 0, 2));
        auswechselSpielerSchalke.add(new Stuermer(3, 0.45, 0.15, "Pieringer", "Marvin", new Date(1999, 10, 4), 10, false, new Date(2021, 7, 1), 0, 1));

        // SC Preußen Münster
        playersMuenster.add(new Stuermer(8, 0.52, 0.26, "Schauerte", "Julian", new Date(1988, 4, 2), 19, false, new Date(2019, 7, 1), 0, 1));
        playersMuenster.add(new Stuermer(6, 0.49, 0.22, "Kobylanski", "Martin", new Date(1994, 3, 8), 18, false, new Date(2022, 7, 1), 0, 2));
        auswechselSpielerMuenster.add(new Stuermer(5, 0.47, 0.20, "Wegkamp", "Rene", new Date(1993, 4, 14), 17, false, new Date(2018, 7, 1), 0, 1));


        // Bayern München
        playersBayern.add(new Mittelfeldspieler(10, 6, 0.88, "Kimmich", "Joshua", new Date(1995, 2, 8), 22, false, new Date(2015, 7, 1), 0, 3));
        playersBayern.add(new Mittelfeldspieler(12, 5, 0., "Goretzka", "Leon", new Date(1995, 2, 6), 20, false, new Date(2018, 7, 1), 0, 2));
        playersBayern.add(new Mittelfeldspieler(8, 4, 0.82, "Musiala", "Jamal", new Date(2003, 2, 26), 19, false, new Date(2020, 7, 1), 0, 1));
        auswechselSpielerBayern.add(new Mittelfeldspieler(7, 3, 0.80, "Gravenberch", "Ryan", new Date(2002, 5, 16), 17, false, new Date(2022, 7, 1), 0, 1));

        // Borussia Dortmund (BVB)
        playersDortmund.add(new Mittelfeldspieler(11, 5, 0.87, "Bellingham", "Jude", new Date(2003, 6, 29), 21, false, new Date(2020, 7, 1), 1, 2));
        playersDortmund.add(new Mittelfeldspieler(9, 4, 0.84, "Brandt", "Julian", new Date(1996, 5, 2), 20, false, new Date(2019, 7, 1), 0, 3));
        playersDortmund.add(new Mittelfeldspieler(7, 3, 0.79, "Dahoud", "Mahmoud", new Date(1996, 1, 1), 18, false, new Date(2017, 7, 1), 0, 2));
        auswechselSpielerDortmund.add(new Mittelfeldspieler(6, 2, 0.77, "Reyna", "Giovanni", new Date(2002, 11, 13), 16, false, new Date(2019, 7, 1), 0, 1));

        // FC Schalke 04
        playersSchalke.add(new Mittelfeldspieler(8, 3, 0.78, "Krauß", "Tom", new Date(2001, 6, 22), 19, false, new Date(2022, 7, 1), 0, 1));
        playersSchalke.add(new Mittelfeldspieler(10, 4, 0.80, "Drexler", "Dominick", new Date(1990, 5, 26), 20, false, new Date(2021, 7, 1), 0, 2));
        playersSchalke.add(new Mittelfeldspieler(5, 2, 0.75, "Latza", "Danny", new Date(1989, 12, 7), 17, false, new Date(2021, 7, 1), 0, 1));
        playersSchalke.add(new Mittelfeldspieler(6, 2, 0.74, "Flick", "Florian", new Date(2000, 5, 1), 16, false, new Date(2020, 7, 1), 0, 1));
        auswechselSpielerSchalke.add(new Mittelfeldspieler(7, 3, 0.76, "Mollet", "Florent", new Date(1991, 11, 19), 18, false, new Date(2022, 7, 1), 0, 1));

        // SC Preußen Münster
        playersMuenster.add(new Mittelfeldspieler(6, 3, 0.73, "Schmidt", "Marc", new Date(1994, 3, 15), 18, false, new Date(2020, 7, 1), 0, 1));
        playersMuenster.add(new Mittelfeldspieler(8, 4, 0.75, "Hoffmann", "Jan", new Date(1997, 6, 25), 19, false, new Date(2019, 7, 1), 0, 1));
        playersMuenster.add(new Mittelfeldspieler(5, 2, 0.70, "Meier", "Stefan", new Date(1996, 9, 10), 17, false, new Date(2021, 7, 1), 0, 1));
        auswechselSpielerMuenster.add(new Mittelfeldspieler(7, 3, 0.74, "Becker", "Tobias", new Date(1993, 12, 8), 18, false, new Date(2018, 7, 1), 0, 1));


        // Bayern München
        playersBayern.add(new Verteidiger(30, 60, 0.89, "de Ligt", "Matthijs", new Date(1999, 8, 12), 22, false, new Date(2022, 7, 1), 1, 3));
        playersBayern.add(new Verteidiger(28, 58, 0.86, "Pavard", "Benjamin", new Date(1996, 3, 28), 21, false, new Date(2019, 7, 1), 0, 2));
        playersBayern.add(new Verteidiger(25, 55, 0.84, "Davies", "Alphonso", new Date(2000, 11, 2), 20, false, new Date(2019, 7, 1), 0, 1));
        playersBayern.add(new Verteidiger(32, 65, 0.88, "Upamecano", "Dayot", new Date(1998, 10, 27), 22, false, new Date(2021, 7, 1), 1, 4));
        auswechselSpielerBayern.add(new Verteidiger(27, 57, 0.85, "Hernández", "Lucas", new Date(1996, 2, 14), 19, false, new Date(2019, 7, 1), 0, 2));

        // Borussia Dortmund (BVB)
        playersDortmund.add(new Verteidiger(29, 59, 0.87, "Hummels", "Mats", new Date(1988, 12, 16), 22, false, new Date(2019, 7, 1), 0, 3));
        playersDortmund.add(new Verteidiger(26, 54, 0.83, "Schlotterbeck", "Nico", new Date(1999, 12, 1), 21, false, new Date(2022, 7, 1), 1, 2));
        playersDortmund.add(new Verteidiger(24, 50, 0.80, "Süle", "Niklas", new Date(1995, 9, 3), 20, false, new Date(2022, 7, 1), 0, 1));
        playersDortmund.add(new Verteidiger(31, 62, 0.86, "Ryerson", "Julian", new Date(1997, 11, 17), 22, false, new Date(2023, 1, 1), 0, 2));
        auswechselSpielerDortmund.add(new Verteidiger(28, 56, 0.84, "Meunier", "Thomas", new Date(1991, 9, 12), 18, false, new Date(2020, 7, 1), 0, 1));

        // FC Schalke 04
        playersSchalke.add(new Verteidiger(27, 55, 0.82, "Yoshida", "Maya", new Date(1988, 8, 24), 19, false, new Date(2022, 7, 1), 0, 2));
        playersSchalke.add(new Verteidiger(25, 53, 0.80, "Matriciani", "Henning", new Date(1999, 3, 14), 18, false, new Date(2021, 7, 1), 0, 1));
        playersSchalke.add(new Verteidiger(30, 60, 0.83, "Kaminski", "Marcin", new Date(1992, 1, 15), 20, false, new Date(2021, 7, 1), 1, 3));
        auswechselSpielerSchalke.add(new Verteidiger(22, 48, 0.78, "Brunner", "Cedric", new Date(1994, 2, 18), 17, false, new Date(2022, 7, 1), 0, 1));

        // SC Preußen Münster
        playersMuenster.add(new Verteidiger(21, 45, 0.76, "Schulz", "Nico", new Date(1993, 4, 1), 18, false, new Date(2022, 7, 1), 0, 1));
        playersMuenster.add(new Verteidiger(23, 50, 0.78, "Petersen", "Lars", new Date(1995, 6, 9), 19, false, new Date(2021, 7, 1), 0, 2));
        playersMuenster.add(new Verteidiger(28, 55, 0.81, "Lindner", "Sebastian", new Date(1996, 3, 7), 20, false, new Date(2020, 7, 1), 1, 3));
        playersMuenster.add(new Verteidiger(26, 52, 0.79, "Kramer", "Tim", new Date(1997, 7, 19), 19, false, new Date(2019, 7, 1), 0, 1));
        playersMuenster.add(new Verteidiger(29, 58, 0.82, "Schmidt", "Andreas", new Date(1998, 9, 25), 20, false, new Date(2020, 7, 1), 0, 2));
        auswechselSpielerMuenster.add(new Verteidiger(24, 49, 0.77, "Weber", "Felix", new Date(1994, 10, 30), 18, false, new Date(2018, 7, 1), 0, 1));

        // Bayern München
        playersBayern.add(new Torwart(10, 15, 0.89, "Neuer", "Manuel", new Date(1986, 3, 27), 22, false, new Date(2011, 7, 1), 0, 1));
        auswechselSpielerBayern.add(new Torwart(5, 10, 0.83, "Ulreich", "Sven", new Date(1988, 8, 3), 14, false, new Date(2015, 7, 1), 0, 0));

        // Borussia Dortmund (BVB)
        playersDortmund.add(new Torwart(9, 18, 0.87, "Kobel", "Gregor", new Date(1997, 12, 6), 21, false, new Date(2021, 7, 1), 0, 1));
        auswechselSpielerDortmund.add(new Torwart(4, 12, 0.81, "Meyer", "Alexander", new Date(1991, 4, 13), 12, false, new Date(2022, 7, 1), 0, 0));

        // FC Schalke 04
        playersSchalke.add(new Torwart(8, 20, 0.85, "Fährmann", "Ralf", new Date(1988, 9, 27), 19, false, new Date(2011, 7, 1), 0, 1));
        auswechselSpielerSchalke.add(new Torwart(3, 14, 0.79, "Schwolow", "Alexander", new Date(1992, 6, 2), 15, false, new Date(2022, 7, 1), 0, 0));

        // SC Preußen Münster
        playersMuenster.add(new Torwart(7, 22, 0.82, "Schulze", "Jan", new Date(1995, 5, 14), 20, false, new Date(2020, 7, 1), 0, 1));
        auswechselSpielerMuenster.add(new Torwart(2, 16, 0.75, "Becker", "Lukas", new Date(1998, 10, 30), 13, false, new Date(2019, 7, 1), 0, 0));

        players.addAll(playersBayern);
        players.addAll(playersDortmund);
        players.addAll(playersSchalke);
        players.addAll(playersMuenster);
        players.addAll(auswechselSpielerBayern);
        players.addAll(auswechselSpielerDortmund);
        players.addAll(auswechselSpielerSchalke);
        players.addAll(auswechselSpielerMuenster);
        this.setSpielerList(players);

        ArrayList<Mannschaft> clubs = new ArrayList<>();
        // Create Mannschaft objects for each team
        Mannschaft bayern = new Mannschaft("FC Bayern München", "Hansi Flick", new ArrayList<>(), new ArrayList<>(), Formation.f433, Liga.Bundesliga1);
        Mannschaft dortmund = new Mannschaft("Borussia Dortmund", "Lucien Favre", new ArrayList<>(), new ArrayList<>(), Formation.f433, Liga.Bundesliga1);
        Mannschaft schalke = new Mannschaft("FC Schalke 04", "David Wagner", new ArrayList<>(), new ArrayList<>(), Formation.f343, Liga.Bundesliga1);
        Mannschaft muenster = new Mannschaft("SC Preußen Münster", "Sascha Hildmann", new ArrayList<>(), new ArrayList<>(), Formation.f532, Liga.Bundesliga2);

// Add players to their respective Mannschaft objects
        for (Spieler player : playersBayern) {
            bayern.addFeldspieler(player.getPlayerId());
        }
        for (Spieler player : playersDortmund) {
            dortmund.addFeldspieler(player.getPlayerId());
        }
        for (Spieler player : playersSchalke) {
            schalke.addFeldspieler(player.getPlayerId());
        }
        for (Spieler player : playersMuenster) {
            muenster.addFeldspieler(player.getPlayerId());
        }

        for (Spieler player : auswechselSpielerBayern) {
            bayern.addAuswechselspieler(player.getPlayerId());
        }
        for (Spieler player : auswechselSpielerDortmund) {
            dortmund.addAuswechselspieler(player.getPlayerId());
        }
        for (Spieler player : auswechselSpielerSchalke) {
            schalke.addAuswechselspieler(player.getPlayerId());
        }
        for (Spieler player : auswechselSpielerMuenster) {
            muenster.addAuswechselspieler(player.getPlayerId());
        }

        clubs.add(bayern);
        clubs.add(dortmund);
        clubs.add(schalke);
        clubs.add(muenster);

        this.setMannschaftList(clubs);
    }
}