package GUI;

import sportverein.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;

public class ClubPanel extends JPanel {
    private ArrayList<Mannschaft> clubs;
    private ArrayList<Spieler> players;
    private JComboBox<Mannschaft> clubComboBox;
    private DefaultTableModel availableModel;
    private DefaultTableModel selectedModel;
    private JTextField TrainerField;
    private JComboBox<Formation> cboFormation;
    private JComboBox<Liga> cboLiga;
    private JLabel scoreField;

    public ClubPanel() {
        //generate Testdata
        players = new ArrayList<>();

        // Bayern München
        players.add(new Stuermer(15, 0.65, 0.30, 1, "Müller", "Thomas", new Date(1989, 9, 13), 20, false, new Date(2000, 7, 1), 0, 2));
        players.add(new Stuermer(20, 0.70, 0.40, 2, "Lewandowski", "Robert", new Date(1988, 8, 21), 18, false, new Date(2014, 7, 1), 1, 1));
        players.add(new Stuermer(10, 0.55, 0.25, 3, "Gnabry", "Serge", new Date(1995, 7, 14), 19, false, new Date(2017, 7, 1), 0, 3));
        players.add(new Stuermer(8, 0.60, 0.20, 4, "Sané", "Leroy", new Date(1996, 1, 11), 15, false, new Date(2020, 7, 1), 0, 2));

        // Borussia Dortmund (BVB)
        players.add(new Stuermer(12, 0.62, 0.32, 5, "Reus", "Marco", new Date(1989, 5, 31), 22, false, new Date(2012, 7, 1), 1, 4));
        players.add(new Stuermer(18, 0.75, 0.45, 6, "Haaland", "Erling", new Date(2000, 7, 21), 16, false, new Date(2020, 1, 1), 0, 1));
        players.add(new Stuermer(9, 0.58, 0.27, 7, "Malen", "Donyell", new Date(1999, 1, 19), 18, false, new Date(2021, 7, 1), 0, 2));
        players.add(new Stuermer(6, 0.50, 0.18, 8, "Moukoko", "Youssoufa", new Date(2004, 11, 20), 14, false, new Date(2020, 7, 1), 0, 0));

        // FC Schalke 04
        players.add(new Stuermer(10, 0.55, 0.28, 9, "Terodde", "Simon", new Date(1988, 3, 2), 17, false, new Date(2021, 7, 1), 1, 3));
        players.add(new Stuermer(7, 0.50, 0.22, 10, "Bülter", "Marius", new Date(1993, 3, 29), 16, false, new Date(2021, 7, 1), 0, 1));
        players.add(new Stuermer(5, 0.48, 0.20, 11, "Polter", "Sebastian", new Date(1991, 4, 1), 14, false, new Date(2022, 7, 1), 0, 2));
        players.add(new Stuermer(3, 0.45, 0.15, 12, "Pieringer", "Marvin", new Date(1999, 10, 4), 10, false, new Date(2021, 7, 1), 0, 1));

        // SC Preußen Münster
        players.add(new Stuermer(8, 0.52, 0.26, 13, "Schauerte", "Julian", new Date(1988, 4, 2), 19, false, new Date(2019, 7, 1), 0, 1));
        players.add(new Stuermer(6, 0.49, 0.22, 14, "Kobylanski", "Martin", new Date(1994, 3, 8), 18, false, new Date(2022, 7, 1), 0, 2));
        players.add(new Stuermer(5, 0.47, 0.20, 15, "Wegkamp", "Rene", new Date(1993, 4, 14), 17, false, new Date(2018, 7, 1), 0, 1));


        // Bayern München
        players.add(new Mittelfeldspieler(10, 6, 0.88, 16, "Kimmich", "Joshua", new Date(1995, 2, 8), 22, false, new Date(2015, 7, 1), 0, 3));
        players.add(new Mittelfeldspieler(12, 5, 0.85, 17, "Goretzka", "Leon", new Date(1995, 2, 6), 20, false, new Date(2018, 7, 1), 0, 2));
        players.add(new Mittelfeldspieler(8, 4, 0.82, 18, "Musiala", "Jamal", new Date(2003, 2, 26), 19, false, new Date(2020, 7, 1), 0, 1));
        players.add(new Mittelfeldspieler(7, 3, 0.80, 19, "Gravenberch", "Ryan", new Date(2002, 5, 16), 17, false, new Date(2022, 7, 1), 0, 1));

        // Borussia Dortmund (BVB)
        players.add(new Mittelfeldspieler(11, 5, 0.87, 20, "Bellingham", "Jude", new Date(2003, 6, 29), 21, false, new Date(2020, 7, 1), 1, 2));
        players.add(new Mittelfeldspieler(9, 4, 0.84, 21, "Brandt", "Julian", new Date(1996, 5, 2), 20, false, new Date(2019, 7, 1), 0, 3));
        players.add(new Mittelfeldspieler(7, 3, 0.79, 22, "Dahoud", "Mahmoud", new Date(1996, 1, 1), 18, false, new Date(2017, 7, 1), 0, 2));
        players.add(new Mittelfeldspieler(6, 2, 0.77, 23, "Reyna", "Giovanni", new Date(2002, 11, 13), 16, false, new Date(2019, 7, 1), 0, 1));

        // FC Schalke 04
        players.add(new Mittelfeldspieler(8, 3, 0.78, 24, "Krauß", "Tom", new Date(2001, 6, 22), 19, false, new Date(2022, 7, 1), 0, 1));
        players.add(new Mittelfeldspieler(10, 4, 0.80, 25, "Drexler", "Dominick", new Date(1990, 5, 26), 20, false, new Date(2021, 7, 1), 0, 2));
        players.add(new Mittelfeldspieler(5, 2, 0.75, 26, "Latza", "Danny", new Date(1989, 12, 7), 17, false, new Date(2021, 7, 1), 0, 1));
        players.add(new Mittelfeldspieler(6, 2, 0.74, 27, "Flick", "Florian", new Date(2000, 5, 1), 16, false, new Date(2020, 7, 1), 0, 1));
        players.add(new Mittelfeldspieler(7, 3, 0.76, 28, "Mollet", "Florent", new Date(1991, 11, 19), 18, false, new Date(2022, 7, 1), 0, 1));

        // SC Preußen Münster
        players.add(new Mittelfeldspieler(6, 3, 0.73, 29, "Schmidt", "Marc", new Date(1994, 3, 15), 18, false, new Date(2020, 7, 1), 0, 1));
        players.add(new Mittelfeldspieler(8, 4, 0.75, 30, "Hoffmann", "Jan", new Date(1997, 6, 25), 19, false, new Date(2019, 7, 1), 0, 1));
        players.add(new Mittelfeldspieler(5, 2, 0.70, 31, "Meier", "Stefan", new Date(1996, 9, 10), 17, false, new Date(2021, 7, 1), 0, 1));
        players.add(new Mittelfeldspieler(7, 3, 0.74, 32, "Becker", "Tobias", new Date(1993, 12, 8), 18, false, new Date(2018, 7, 1), 0, 1));


        // Bayern München
        players.add(new Verteidiger(30, 60, 0.89, 33, "de Ligt", "Matthijs", new Date(1999, 8, 12), 22, false, new Date(2022, 7, 1), 1, 3));
        players.add(new Verteidiger(28, 58, 0.86, 34, "Pavard", "Benjamin", new Date(1996, 3, 28), 21, false, new Date(2019, 7, 1), 0, 2));
        players.add(new Verteidiger(25, 55, 0.84, 35, "Davies", "Alphonso", new Date(2000, 11, 2), 20, false, new Date(2019, 7, 1), 0, 1));
        players.add(new Verteidiger(32, 65, 0.88, 36, "Upamecano", "Dayot", new Date(1998, 10, 27), 22, false, new Date(2021, 7, 1), 1, 4));
        players.add(new Verteidiger(27, 57, 0.85, 37, "Hernández", "Lucas", new Date(1996, 2, 14), 19, false, new Date(2019, 7, 1), 0, 2));

        // Borussia Dortmund (BVB)
        players.add(new Verteidiger(29, 59, 0.87, 38, "Hummels", "Mats", new Date(1988, 12, 16), 22, false, new Date(2019, 7, 1), 0, 3));
        players.add(new Verteidiger(26, 54, 0.83, 39, "Schlotterbeck", "Nico", new Date(1999, 12, 1), 21, false, new Date(2022, 7, 1), 1, 2));
        players.add(new Verteidiger(24, 50, 0.80, 40, "Süle", "Niklas", new Date(1995, 9, 3), 20, false, new Date(2022, 7, 1), 0, 1));
        players.add(new Verteidiger(31, 62, 0.86, 41, "Ryerson", "Julian", new Date(1997, 11, 17), 22, false, new Date(2023, 1, 1), 0, 2));
        players.add(new Verteidiger(28, 56, 0.84, 42, "Meunier", "Thomas", new Date(1991, 9, 12), 18, false, new Date(2020, 7, 1), 0, 1));

        // FC Schalke 04
        players.add(new Verteidiger(27, 55, 0.82, 43, "Yoshida", "Maya", new Date(1988, 8, 24), 19, false, new Date(2022, 7, 1), 0, 2));
        players.add(new Verteidiger(25, 53, 0.80, 44, "Matriciani", "Henning", new Date(1999, 3, 14), 18, false, new Date(2021, 7, 1), 0, 1));
        players.add(new Verteidiger(30, 60, 0.83, 45, "Kaminski", "Marcin", new Date(1992, 1, 15), 20, false, new Date(2021, 7, 1), 1, 3));
        players.add(new Verteidiger(22, 48, 0.78, 46, "Brunner", "Cedric", new Date(1994, 2, 18), 17, false, new Date(2022, 7, 1), 0, 1));

        // SC Preußen Münster
        players.add(new Verteidiger(21, 45, 0.76, 47, "Schulz", "Nico", new Date(1993, 4, 1), 18, false, new Date(2022, 7, 1), 0, 1));
        players.add(new Verteidiger(23, 50, 0.78, 48, "Petersen", "Lars", new Date(1995, 6, 9), 19, false, new Date(2021, 7, 1), 0, 2));
        players.add(new Verteidiger(28, 55, 0.81, 49, "Lindner", "Sebastian", new Date(1996, 3, 7), 20, false, new Date(2020, 7, 1), 1, 3));
        players.add(new Verteidiger(26, 52, 0.79, 50, "Kramer", "Tim", new Date(1997, 7, 19), 19, false, new Date(2019, 7, 1), 0, 1));
        players.add(new Verteidiger(29, 58, 0.82, 51, "Schmidt", "Andreas", new Date(1998, 9, 25), 20, false, new Date(2020, 7, 1), 0, 2));
        players.add(new Verteidiger(24, 49, 0.77, 52, "Weber", "Felix", new Date(1994, 10, 30), 18, false, new Date(2018, 7, 1), 0, 1));

        // Bayern München
        players.add(new Torwart(10, 15, 0.89, 53, "Neuer", "Manuel", new Date(1986, 3, 27), 22, false, new Date(2011, 7, 1), 0, 1));
        players.add(new Torwart(5, 10, 0.83, 54, "Ulreich", "Sven", new Date(1988, 8, 3), 14, false, new Date(2015, 7, 1), 0, 0));

        // Borussia Dortmund (BVB)
        players.add(new Torwart(9, 18, 0.87, 55, "Kobel", "Gregor", new Date(1997, 12, 6), 21, false, new Date(2021, 7, 1), 0, 1));
        players.add(new Torwart(4, 12, 0.81, 56, "Meyer", "Alexander", new Date(1991, 4, 13), 12, false, new Date(2022, 7, 1), 0, 0));

        // FC Schalke 04
        players.add(new Torwart(8, 20, 0.85, 57, "Fährmann", "Ralf", new Date(1988, 9, 27), 19, false, new Date(2011, 7, 1), 0, 1));
        players.add(new Torwart(3, 14, 0.79, 58, "Schwolow", "Alexander", new Date(1992, 6, 2), 15, false, new Date(2022, 7, 1), 0, 0));

        // SC Preußen Münster
        players.add(new Torwart(7, 22, 0.82, 59, "Schulze", "Jan", new Date(1995, 5, 14), 20, false, new Date(2020, 7, 1), 0, 1));
        players.add(new Torwart(2, 16, 0.75, 60, "Becker", "Lukas", new Date(1998, 10, 30), 13, false, new Date(2019, 7, 1), 0, 0));


        clubs = new ArrayList<>();
        clubs.add(new Mannschaft(1, "FC Bayern München", "Hansi Flick",new ArrayList<>(Arrays.asList(2, 3, 4, 17, 18, 19, 34, 35, 36, 37, 54)) , new ArrayList<>(Arrays.asList(1, 16, 33, 53)), Formation.f433, Liga.Bundesliga1));
        clubs.add(new Mannschaft(2, "Borussia Dortmund", "Lucien Favre", new ArrayList<>(Arrays.asList(6, 7, 8, 21, 22, 23, 39, 40, 41, 42, 56)), new ArrayList<>(Arrays.asList(5, 20, 38, 55)), Formation.f433, Liga.Bundesliga1));
        clubs.add(new Mannschaft(3, "FC Schalke 04", "David Wagner", new ArrayList<>(Arrays.asList(10, 11, 12, 25, 26, 27, 28, 44, 45, 46, 58)),new ArrayList<>(Arrays.asList(9, 24, 43, 57)), Formation.f343, Liga.Bundesliga1));
        clubs.add(new Mannschaft(4,"SC Preußen Münster", "Sascha Hildmann",new ArrayList<>(Arrays.asList(14, 15, 30, 31, 32, 48, 49, 50, 51, 52, 60)) ,new ArrayList<>(Arrays.asList(13, 29, 47, 59)), Formation.f532, Liga.Bundesliga2));

        setLayout(new BorderLayout());

        clubComboBox = new JComboBox<sportverein.Mannschaft>(clubs.toArray(new sportverein.Mannschaft[0]));

        add(clubComboBox, BorderLayout.NORTH);

        String[] columnNames = {"ID","Vorname", "Nachname", "Position", "Bewertung"};
        availableModel = new DefaultTableModel(columnNames, 0);
        selectedModel = new DefaultTableModel(columnNames, 0);

        JTable availableTable = new JTable(availableModel);
        JTable selectedTable = new JTable(selectedModel);

        TableRowSorter<TableModel> sorter = new TableRowSorter<>(availableModel);
        availableTable.setRowSorter(sorter);
        sorter.setSortable(0, true);
        sorter.setSortable(1, true);
        sorter.setSortable(2, true);
        sorter.setSortable(3, true);
        sorter.setSortable(4, true);


        TableRowSorter<TableModel> sorterSelect = new TableRowSorter<>(selectedModel);
        selectedTable.setRowSorter(sorterSelect);
        sorterSelect.setSortable(0, true);
        sorterSelect.setSortable(1, true);
        sorterSelect.setSortable(2, true);
        sorterSelect.setSortable(3, true);
        sorterSelect.setSortable(4, true);


        JScrollPane availableScrollPane = new JScrollPane(availableTable);
        JScrollPane selectedScrollPane = new JScrollPane(selectedTable);

        JButton moveToSelectedButton = new JButton("Move Selected →");
        JButton moveToAvailableButton = new JButton("← Move Selected");
        JButton checkFormation = new JButton("Aufstellung Prüfen");

        moveToSelectedButton.addActionListener(e -> movePlayers(availableTable, availableModel, selectedModel));
        moveToAvailableButton.addActionListener(e -> movePlayers(selectedTable, selectedModel, availableModel));
        checkFormation.addActionListener(e -> {
            if(!aufstellungPruefen(getSelectedClub().getClubId())){
                JOptionPane.showMessageDialog(this, "Aufstellung entspricht nicht der Formation", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Aufstellung ist korrekt");
            }
        });
        JPanel buttonPanel = new JPanel(new GridLayout(5, 3,5,5));

        buttonPanel.add(moveToSelectedButton);
        buttonPanel.add(moveToAvailableButton);
        buttonPanel.add(checkFormation);
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10)); // 10px margin all sides

        JPanel LHeaderPanel = new JPanel ();
        LHeaderPanel.add(new JLabel("Verfügbare Spieler"));
        JPanel RHeaderPanel = new JPanel ();
        RHeaderPanel.add(new JLabel("Ausgewählte Spieler"));
        JPanel Empty = new JPanel();

        JPanel centerPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        centerPanel.add(LHeaderPanel, gbc);

        gbc.gridx = 1;
        gbc.weightx = 0.0;
        centerPanel.add(Empty, gbc);

        gbc.gridx = 2;
        gbc.weightx = 0.1;
        centerPanel.add(RHeaderPanel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        centerPanel.add(availableScrollPane, gbc);

        gbc.gridx = 1;
        gbc.weightx = 0.0;
        centerPanel.add(buttonPanel, gbc);

        gbc.gridx = 2;
        gbc.weightx = 1.0;
        centerPanel.add(selectedScrollPane, gbc);

        // Create combo boxes
        TrainerField = new JTextField();
        cboFormation = new JComboBox<Formation>(Formation.values());
        cboLiga = new JComboBox<Liga>(Liga.values());
        scoreField = new JLabel();
        // Add combo boxes to a panel
        JPanel comboBoxPanel = new JPanel(new GridLayout(1, 4, 5, 5));


        comboBoxPanel.add(TrainerField);
        TrainerField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                getSelectedClub().setTrainer(TrainerField.getText());
            }
        });

        comboBoxPanel.add(cboFormation);
        cboFormation.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    getSelectedClub().setFormation((Formation) e.getItem());
                }
            }
        });

        comboBoxPanel.add(cboLiga);
        cboLiga.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    getSelectedClub().setLiga((Liga) e.getItem());
                }
            }
        });

        comboBoxPanel.add(scoreField);

        comboBoxPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        // Add the comboBoxPanel to the centerPanel
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 3;
        gbc.weightx = 1.0;
        gbc.weighty = 0.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        centerPanel.add(comboBoxPanel, gbc);

        centerPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        add(centerPanel, BorderLayout.CENTER);

        clubComboBox.addActionListener(e ->{
            Mannschaft selectedClub = (Mannschaft) clubComboBox.getSelectedItem();
            assert selectedClub != null;
            loadPlayers(selectedClub.getClubId());
            loadData(selectedClub.getClubId());
        } );

        Mannschaft selectedClub = (Mannschaft) clubComboBox.getSelectedItem();
        assert selectedClub != null;
        loadData(selectedClub.getClubId());
        loadPlayers(selectedClub.getClubId());
    }

    public JMenu getMenu() {
        JMenu clubMenu = new JMenu("Club");
        JMenuItem createClub = new JMenuItem("Club erstellen");
        JMenuItem editClub = new JMenuItem("Club editieren");
        JMenuItem deleteClub = new JMenuItem("Club löschen");

        clubMenu.add(createClub);
        clubMenu.add(editClub);
        clubMenu.add(deleteClub);

        createClub.addActionListener(e -> JOptionPane.showMessageDialog(this, "Insert Dialog to create Club"));
        editClub.addActionListener(e -> JOptionPane.showMessageDialog(this, "Insert Dialog to edit Club"));
        deleteClub.addActionListener(e -> JOptionPane.showMessageDialog(this, "Insert Dialog to delete Club"));

        return clubMenu;
    }

    private void movePlayers(JTable sourceTable, DefaultTableModel sourceModel, DefaultTableModel targetModel) {
        int[] selectedRows = sourceTable.getSelectedRows();
        for (int i = selectedRows.length - 1; i >= 0; i--) {
            int playerId = (int) sourceModel.getValueAt(selectedRows[i], 0);
            sportverein.Mannschaft club = getSelectedClub();
            club.transferPlayer(playerId);
            sportverein.Spieler player = getPlayerById(playerId);
            targetModel.addRow(new Object[]{player.getPlayerId(), player.getVorname(), player.getNachname(), player.getClass().getSimpleName(), player.spielerBewertung()});
            sourceModel.removeRow(selectedRows[i]);
            scoreField.setText("Mannschaftsbewertung: " + mannschaftsbewertungAusgeben(club.getClubId()));
        }

    }

    private void loadPlayers(int clubId) {
        availableModel.setRowCount(0);
        selectedModel.setRowCount(0);
        //get club by clubId
        sportverein.Mannschaft club = getClubById(clubId);
        for(int playerId : club.getAuswechselspieler()) {
            sportverein.Spieler player = getPlayerById(playerId);
            availableModel.addRow(new Object[]{player.getPlayerId(), player.getVorname(), player.getNachname(), player.getClass().getSimpleName(), player.spielerBewertung()});
        }
        for(int playerId : club.getFeldspieler()) {
            sportverein.Spieler player = getPlayerById(playerId);
            selectedModel.addRow(new Object[]{player.getPlayerId(), player.getVorname(), player.getNachname(), player.getClass().getSimpleName(), player.spielerBewertung()});
        }


        scoreField.setText("Mannschaftsbewertung: " + mannschaftsbewertungAusgeben(clubId));
    }

    private void loadData(int clubId){
        Mannschaft club = getClubById(clubId);
        TrainerField.setText(club.getTrainer());
        cboFormation.setSelectedItem(club.getFormation());
        cboLiga.setSelectedItem(club.getLiga());
        scoreField.setText("Mannschaftsbewertung: " + mannschaftsbewertungAusgeben(clubId));
    }

    public sportverein.Mannschaft getSelectedClub() {
        Mannschaft selectedClub = (Mannschaft) clubComboBox.getSelectedItem();
        assert selectedClub != null;
        int clubId = selectedClub.getClubId();
        for(sportverein.Mannschaft club : clubs) {
            if (club.getClubId() == clubId) {
                return club;
            }
        }
        return null;
    }

    public sportverein.Mannschaft getClubById(int clubId){
        for(sportverein.Mannschaft club : clubs){
            if(club.getClubId() == clubId){
                return club;
            }
        }
        return null;
    }

    public sportverein.Spieler getPlayerById(int playerId){
            for(sportverein.Spieler player : players){
            if(player.getPlayerId() == playerId){
                return player;
            }
        }
        return null; }

    private boolean aufstellungPruefen(int clubId) {
        sportverein.Mannschaft club = getClubById(clubId);
        return aufstellungPruefen(club.getFeldspieler(), club.getFormation());
    }
    private boolean aufstellungPruefen(ArrayList<Integer> feldspieler, Formation formation)
    {
        if (feldspieler.size() != 11) {
            return false;
        }

        int verteidigerCount = 0;
        int mittelfeldspielerCount = 0;
        int stuermerCount = 0;

        for (int spielerId : feldspieler) {
            sportverein.Spieler spieler = getPlayerById(spielerId);
            if (spieler instanceof Verteidiger) {
                verteidigerCount++;
            } else if (spieler instanceof Mittelfeldspieler) {
                mittelfeldspielerCount++;
            } else if (spieler instanceof Stuermer) {
                stuermerCount++;
            }
        }

        return verteidigerCount == formation.getVerteidigerAnzahl() &&
                mittelfeldspielerCount == formation.getMittelfeldspielerAnzahl() &&
                stuermerCount == formation.getStuermerAnzahl();
    }
    public Double mannschaftsbewertungAusgeben(int clubId)
    {
        return mannschaftsbewertungAusgeben(getClubById(clubId).getFeldspieler());
    }
    public Double mannschaftsbewertungAusgeben(ArrayList<Integer> feldspieler)
    {
        double bewertung = 0;
        for (int playerId : feldspieler) {
            bewertung += getPlayerById(playerId).spielerBewertung();
        }

        return Math.round((bewertung / 11) * 100.0) / 100.0;
    }

}
