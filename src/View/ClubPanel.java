package View;

import Model.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import Model.DataModel;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.UUID;

public class ClubPanel extends JPanel {
    private ArrayList<Mannschaft> clubs;
    private ArrayList<Spieler> players;
    private JComboBox<Mannschaft> clubComboBox;
    private DefaultTableModel availableModel;
    private DefaultTableModel selectedModel;
    private DataModel dataModel;
    private JTextField TrainerField;
    private JComboBox<Formation> cboFormation;
    private JComboBox<Liga> cboLiga;
    private JLabel scoreField;

    public ClubPanel(DataModel dataModel) {
        this.dataModel = dataModel;

        //generate Testdata
        players = new ArrayList<>();

        ArrayList<Spieler> playersBayern = new ArrayList<>();
        ArrayList<Spieler> auswechselSpielerBayern = new ArrayList<>();

        ArrayList<Spieler> playersDortmund = new ArrayList<>();
        ArrayList<Spieler> auswechselSpielerDortmund = new ArrayList<>();

        ArrayList<Spieler> playersSchalke = new ArrayList<>();
        ArrayList<Spieler> auswechselSpielerSchalke = new ArrayList<>();

        ArrayList<Spieler> playersMuenster = new ArrayList<>();
        ArrayList<Spieler> auswechselSpielerMuenster = new ArrayList<>();

        // Bayern München
        playersBayern.add(new Stuermer(15, 0.65, 0.30, UUID.randomUUID(), "Müller", "Thomas", new Date(1989, 9, 13), 20, false, new Date(2000, 7, 1), 0, 2));
        playersBayern.add(new Stuermer(20, 0.70, 0.40, UUID.randomUUID(), "Lewandowski", "Robert", new Date(1988, 8, 21), 18, false, new Date(2014, 7, 1), 1, 1));
        playersBayern.add(new Stuermer(10, 0.55, 0.25, UUID.randomUUID(), "Gnabry", "Serge", new Date(1995, 7, 14), 19, false, new Date(2017, 7, 1), 0, 3));
        auswechselSpielerBayern.add(new Stuermer(8, 0.60, 0.20, UUID.randomUUID(), "Sané", "Leroy", new Date(1996, 1, 11), 15, false, new Date(2020, 7, 1), 0, 2));

        // Borussia Dortmund (BVB)
        playersDortmund.add(new Stuermer(12, 0.62, 0.32, UUID.randomUUID(), "Reus", "Marco", new Date(1989, 5, 31), 22, false, new Date(2012, 7, 1), 1, 4));
        playersDortmund.add(new Stuermer(18, 0.75, 0.45, UUID.randomUUID(), "Haaland", "Erling", new Date(2000, 7, 21), 16, false, new Date(2020, 1, 1), 0, 1));
        playersDortmund.add(new Stuermer(9, 0.58, 0.27, UUID.randomUUID(), "Malen", "Donyell", new Date(1999, 1, 19), 18, false, new Date(2021, 7, 1), 0, 2));
        auswechselSpielerDortmund.add(new Stuermer(6, 0.50, 0.18, UUID.randomUUID(), "Moukoko", "Youssoufa", new Date(2004, 11, 20), 14, false, new Date(2020, 7, 1), 0, 0));

        // FC Schalke 04
        playersSchalke.add(new Stuermer(10, 0.55, 0.28, UUID.randomUUID(), "Terodde", "Simon", new Date(1988, 3, 2), 17, false, new Date(2021, 7, 1), 1, 3));
        playersSchalke.add(new Stuermer(7, 0.50, 0.22, UUID.randomUUID(), "Bülter", "Marius", new Date(1993, 3, 29), 16, false, new Date(2021, 7, 1), 0, 1));
        playersSchalke.add(new Stuermer(5, 0.48, 0.20, UUID.randomUUID(), "Polter", "Sebastian", new Date(1991, 4, 1), 14, false, new Date(2022, 7, 1), 0, 2));
        auswechselSpielerSchalke.add(new Stuermer(3, 0.45, 0.15, UUID.randomUUID(), "Pieringer", "Marvin", new Date(1999, 10, 4), 10, false, new Date(2021, 7, 1), 0, 1));

        // SC Preußen Münster
        playersMuenster.add(new Stuermer(8, 0.52, 0.26, UUID.randomUUID(), "Schauerte", "Julian", new Date(1988, 4, 2), 19, false, new Date(2019, 7, 1), 0, 1));
        playersMuenster.add(new Stuermer(6, 0.49, 0.22, UUID.randomUUID(), "Kobylanski", "Martin", new Date(1994, 3, 8), 18, false, new Date(2022, 7, 1), 0, 2));
        auswechselSpielerMuenster.add(new Stuermer(5, 0.47, 0.20, UUID.randomUUID(), "Wegkamp", "Rene", new Date(1993, 4, 14), 17, false, new Date(2018, 7, 1), 0, 1));


        // Bayern München
        playersBayern.add(new Mittelfeldspieler(10, 6, 0.88, UUID.randomUUID(), "Kimmich", "Joshua", new Date(1995, 2, 8), 22, false, new Date(2015, 7, 1), 0, 3));
        playersBayern.add(new Mittelfeldspieler(12, 5, 0.85, UUID.randomUUID(), "Goretzka", "Leon", new Date(1995, 2, 6), 20, false, new Date(2018, 7, 1), 0, 2));
        playersBayern.add(new Mittelfeldspieler(8, 4, 0.82, UUID.randomUUID(), "Musiala", "Jamal", new Date(2003, 2, 26), 19, false, new Date(2020, 7, 1), 0, 1));
        auswechselSpielerBayern.add(new Mittelfeldspieler(7, 3, 0.80, UUID.randomUUID(), "Gravenberch", "Ryan", new Date(2002, 5, 16), 17, false, new Date(2022, 7, 1), 0, 1));

        // Borussia Dortmund (BVB)
        playersDortmund.add(new Mittelfeldspieler(11, 5, 0.87, UUID.randomUUID(), "Bellingham", "Jude", new Date(2003, 6, 29), 21, false, new Date(2020, 7, 1), 1, 2));
        playersDortmund.add(new Mittelfeldspieler(9, 4, 0.84, UUID.randomUUID(), "Brandt", "Julian", new Date(1996, 5, 2), 20, false, new Date(2019, 7, 1), 0, 3));
        playersDortmund.add(new Mittelfeldspieler(7, 3, 0.79, UUID.randomUUID(), "Dahoud", "Mahmoud", new Date(1996, 1, 1), 18, false, new Date(2017, 7, 1), 0, 2));
        auswechselSpielerDortmund.add(new Mittelfeldspieler(6, 2, 0.77, UUID.randomUUID(), "Reyna", "Giovanni", new Date(2002, 11, 13), 16, false, new Date(2019, 7, 1), 0, 1));

        // FC Schalke 04
        playersSchalke.add(new Mittelfeldspieler(8, 3, 0.78, UUID.randomUUID(), "Krauß", "Tom", new Date(2001, 6, 22), 19, false, new Date(2022, 7, 1), 0, 1));
        playersSchalke.add(new Mittelfeldspieler(10, 4, 0.80, UUID.randomUUID(), "Drexler", "Dominick", new Date(1990, 5, 26), 20, false, new Date(2021, 7, 1), 0, 2));
        playersSchalke.add(new Mittelfeldspieler(5, 2, 0.75, UUID.randomUUID(), "Latza", "Danny", new Date(1989, 12, 7), 17, false, new Date(2021, 7, 1), 0, 1));
        playersSchalke.add(new Mittelfeldspieler(6, 2, 0.74, UUID.randomUUID(), "Flick", "Florian", new Date(2000, 5, 1), 16, false, new Date(2020, 7, 1), 0, 1));
        auswechselSpielerSchalke.add(new Mittelfeldspieler(7, 3, 0.76, UUID.randomUUID(), "Mollet", "Florent", new Date(1991, 11, 19), 18, false, new Date(2022, 7, 1), 0, 1));

        // SC Preußen Münster
        playersMuenster.add(new Mittelfeldspieler(6, 3, 0.73, UUID.randomUUID(), "Schmidt", "Marc", new Date(1994, 3, 15), 18, false, new Date(2020, 7, 1), 0, 1));
        playersMuenster.add(new Mittelfeldspieler(8, 4, 0.75, UUID.randomUUID(), "Hoffmann", "Jan", new Date(1997, 6, 25), 19, false, new Date(2019, 7, 1), 0, 1));
        playersMuenster.add(new Mittelfeldspieler(5, 2, 0.70, UUID.randomUUID(), "Meier", "Stefan", new Date(1996, 9, 10), 17, false, new Date(2021, 7, 1), 0, 1));
        auswechselSpielerMuenster.add(new Mittelfeldspieler(7, 3, 0.74, UUID.randomUUID(), "Becker", "Tobias", new Date(1993, 12, 8), 18, false, new Date(2018, 7, 1), 0, 1));


        // Bayern München
        playersBayern.add(new Verteidiger(30, 60, 0.89, UUID.randomUUID(), "de Ligt", "Matthijs", new Date(1999, 8, 12), 22, false, new Date(2022, 7, 1), 1, 3));
        playersBayern.add(new Verteidiger(28, 58, 0.86, UUID.randomUUID(), "Pavard", "Benjamin", new Date(1996, 3, 28), 21, false, new Date(2019, 7, 1), 0, 2));
        playersBayern.add(new Verteidiger(25, 55, 0.84, UUID.randomUUID(), "Davies", "Alphonso", new Date(2000, 11, 2), 20, false, new Date(2019, 7, 1), 0, 1));
        playersBayern.add(new Verteidiger(32, 65, 0.88, UUID.randomUUID(), "Upamecano", "Dayot", new Date(1998, 10, 27), 22, false, new Date(2021, 7, 1), 1, 4));
        auswechselSpielerBayern.add(new Verteidiger(27, 57, 0.85, UUID.randomUUID(), "Hernández", "Lucas", new Date(1996, 2, 14), 19, false, new Date(2019, 7, 1), 0, 2));

        // Borussia Dortmund (BVB)
        playersDortmund.add(new Verteidiger(29, 59, 0.87, UUID.randomUUID(), "Hummels", "Mats", new Date(1988, 12, 16), 22, false, new Date(2019, 7, 1), 0, 3));
        playersDortmund.add(new Verteidiger(26, 54, 0.83, UUID.randomUUID(), "Schlotterbeck", "Nico", new Date(1999, 12, 1), 21, false, new Date(2022, 7, 1), 1, 2));
        playersDortmund.add(new Verteidiger(24, 50, 0.80, UUID.randomUUID(), "Süle", "Niklas", new Date(1995, 9, 3), 20, false, new Date(2022, 7, 1), 0, 1));
        playersDortmund.add(new Verteidiger(31, 62, 0.86, UUID.randomUUID(), "Ryerson", "Julian", new Date(1997, 11, 17), 22, false, new Date(2023, 1, 1), 0, 2));
        auswechselSpielerDortmund.add(new Verteidiger(28, 56, 0.84, UUID.randomUUID(), "Meunier", "Thomas", new Date(1991, 9, 12), 18, false, new Date(2020, 7, 1), 0, 1));

        // FC Schalke 04
        playersSchalke.add(new Verteidiger(27, 55, 0.82, UUID.randomUUID(), "Yoshida", "Maya", new Date(1988, 8, 24), 19, false, new Date(2022, 7, 1), 0, 2));
        playersSchalke.add(new Verteidiger(25, 53, 0.80, UUID.randomUUID(), "Matriciani", "Henning", new Date(1999, 3, 14), 18, false, new Date(2021, 7, 1), 0, 1));
        playersSchalke.add(new Verteidiger(30, 60, 0.83, UUID.randomUUID(), "Kaminski", "Marcin", new Date(1992, 1, 15), 20, false, new Date(2021, 7, 1), 1, 3));
        auswechselSpielerSchalke.add(new Verteidiger(22, 48, 0.78, UUID.randomUUID(), "Brunner", "Cedric", new Date(1994, 2, 18), 17, false, new Date(2022, 7, 1), 0, 1));

        // SC Preußen Münster
        playersMuenster.add(new Verteidiger(21, 45, 0.76, UUID.randomUUID(), "Schulz", "Nico", new Date(1993, 4, 1), 18, false, new Date(2022, 7, 1), 0, 1));
        playersMuenster.add(new Verteidiger(23, 50, 0.78, UUID.randomUUID(), "Petersen", "Lars", new Date(1995, 6, 9), 19, false, new Date(2021, 7, 1), 0, 2));
        playersMuenster.add(new Verteidiger(28, 55, 0.81, UUID.randomUUID(), "Lindner", "Sebastian", new Date(1996, 3, 7), 20, false, new Date(2020, 7, 1), 1, 3));
        playersMuenster.add(new Verteidiger(26, 52, 0.79, UUID.randomUUID(), "Kramer", "Tim", new Date(1997, 7, 19), 19, false, new Date(2019, 7, 1), 0, 1));
        playersMuenster.add(new Verteidiger(29, 58, 0.82, UUID.randomUUID(), "Schmidt", "Andreas", new Date(1998, 9, 25), 20, false, new Date(2020, 7, 1), 0, 2));
        auswechselSpielerMuenster.add(new Verteidiger(24, 49, 0.77, UUID.randomUUID(), "Weber", "Felix", new Date(1994, 10, 30), 18, false, new Date(2018, 7, 1), 0, 1));

        // Bayern München
        playersBayern.add(new Torwart(10, 15, 0.89, UUID.randomUUID(), "Neuer", "Manuel", new Date(1986, 3, 27), 22, false, new Date(2011, 7, 1), 0, 1));
        auswechselSpielerBayern.add(new Torwart(5, 10, 0.83, UUID.randomUUID(), "Ulreich", "Sven", new Date(1988, 8, 3), 14, false, new Date(2015, 7, 1), 0, 0));

        // Borussia Dortmund (BVB)
        playersDortmund.add(new Torwart(9, 18, 0.87, UUID.randomUUID(), "Kobel", "Gregor", new Date(1997, 12, 6), 21, false, new Date(2021, 7, 1), 0, 1));
        auswechselSpielerDortmund.add(new Torwart(4, 12, 0.81, UUID.randomUUID(), "Meyer", "Alexander", new Date(1991, 4, 13), 12, false, new Date(2022, 7, 1), 0, 0));

        // FC Schalke 04
        playersSchalke.add(new Torwart(8, 20, 0.85, UUID.randomUUID(), "Fährmann", "Ralf", new Date(1988, 9, 27), 19, false, new Date(2011, 7, 1), 0, 1));
        auswechselSpielerSchalke.add(new Torwart(3, 14, 0.79, UUID.randomUUID(), "Schwolow", "Alexander", new Date(1992, 6, 2), 15, false, new Date(2022, 7, 1), 0, 0));

        // SC Preußen Münster
        playersMuenster.add(new Torwart(7, 22, 0.82, UUID.randomUUID(), "Schulze", "Jan", new Date(1995, 5, 14), 20, false, new Date(2020, 7, 1), 0, 1));
        auswechselSpielerMuenster.add(new Torwart(2, 16, 0.75, UUID.randomUUID(), "Becker", "Lukas", new Date(1998, 10, 30), 13, false, new Date(2019, 7, 1), 0, 0));

        players.addAll(playersBayern);
        players.addAll(playersDortmund);
        players.addAll(playersSchalke);
        players.addAll(playersMuenster);
        players.addAll(auswechselSpielerBayern);
        players.addAll(auswechselSpielerDortmund);
        players.addAll(auswechselSpielerSchalke);
        players.addAll(auswechselSpielerMuenster);
        dataModel.setSpielerList(players);

        clubs = new ArrayList<>();
        // Create Mannschaft objects for each team
        Mannschaft bayern = new Mannschaft(UUID.randomUUID(), "FC Bayern München", "Hansi Flick", new ArrayList<>(), new ArrayList<>(), Formation.f433, Liga.Bundesliga1);
        Mannschaft dortmund = new Mannschaft(UUID.randomUUID(), "Borussia Dortmund", "Lucien Favre", new ArrayList<>(), new ArrayList<>(), Formation.f433, Liga.Bundesliga1);
        Mannschaft schalke = new Mannschaft(UUID.randomUUID(), "FC Schalke 04", "David Wagner", new ArrayList<>(), new ArrayList<>(), Formation.f343, Liga.Bundesliga1);
        Mannschaft muenster = new Mannschaft(UUID.randomUUID(), "SC Preußen Münster", "Sascha Hildmann", new ArrayList<>(), new ArrayList<>(), Formation.f532, Liga.Bundesliga2);

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

        dataModel.setMannschaftList(clubs);

        setLayout(new BorderLayout());

        clubComboBox = new JComboBox<Model.Mannschaft>(clubs.toArray(new Model.Mannschaft[0]));

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
            if(!aufstellungPruefen(dataModel.getSelectedClub().getClubId())){
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
                dataModel.getSelectedClub().setTrainer(TrainerField.getText());
            }
        });

        comboBoxPanel.add(cboFormation);
        cboFormation.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    dataModel.getSelectedClub().setFormation((Formation) e.getItem());
                }
            }
        });

        comboBoxPanel.add(cboLiga);
        cboLiga.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    dataModel.getSelectedClub().setLiga((Liga) e.getItem());
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
        dataModel.setSelectedClub(selectedClub);
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
            UUID playerId = (UUID) sourceModel.getValueAt(selectedRows[i], 0);
            Model.Mannschaft club = dataModel.getSelectedClub();
            club.transferPlayer(playerId);
            Model.Spieler player = dataModel.getSpielerById(playerId);
            targetModel.addRow(new Object[]{player.getPlayerId(), player.getVorname(), player.getNachname(), player.getClass().getSimpleName(), player.spielerBewertung()});
            sourceModel.removeRow(selectedRows[i]);
            scoreField.setText("Mannschaftsbewertung: " + mannschaftsbewertungAusgeben(club.getClubId()));
        }
    }

    private void loadPlayers(UUID clubId) {
        availableModel.setRowCount(0);
        selectedModel.setRowCount(0);
        //get club by clubId
        Model.Mannschaft club = dataModel.getClubById(clubId);
        for(UUID playerId : club.getAuswechselspieler()) {
            Model.Spieler player = dataModel.getSpielerById(playerId);
            availableModel.addRow(new Object[]{player.getPlayerId(), player.getVorname(), player.getNachname(), player.getClass().getSimpleName(), player.spielerBewertung()});
        }
        for(UUID playerId : club.getFeldspieler()) {
            Model.Spieler player = dataModel.getSpielerById(playerId);
            selectedModel.addRow(new Object[]{player.getPlayerId(), player.getVorname(), player.getNachname(), player.getClass().getSimpleName(), player.spielerBewertung()});
        }


        scoreField.setText("Mannschaftsbewertung: " + mannschaftsbewertungAusgeben(clubId));
    }

    private void loadData(UUID clubId){
        Mannschaft club = dataModel.getClubById(clubId);
        TrainerField.setText(club.getTrainer());
        cboFormation.setSelectedItem(club.getFormation());
        cboLiga.setSelectedItem(club.getLiga());
        scoreField.setText("Mannschaftsbewertung: " + mannschaftsbewertungAusgeben(clubId));
    }

    private boolean aufstellungPruefen(UUID clubId) {
        Model.Mannschaft club = dataModel.getClubById(clubId);
        return aufstellungPruefen(club.getFeldspieler(), club.getFormation());
    }
    private boolean aufstellungPruefen(ArrayList<UUID> feldspieler, Formation formation)
    {
        if (feldspieler.size() != 11) {
            return false;
        }

        int verteidigerCount = 0;
        int mittelfeldspielerCount = 0;
        int stuermerCount = 0;

        for (UUID spielerId : feldspieler) {
            Model.Spieler spieler = dataModel.getSpielerById(spielerId);
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
    public Double mannschaftsbewertungAusgeben(UUID clubId)
    {
        return mannschaftsbewertungAusgeben(dataModel.getClubById(clubId).getFeldspieler());
    }
    public Double mannschaftsbewertungAusgeben(ArrayList<UUID> feldspieler)
    {
        double bewertung = 0;
        for (UUID playerId : feldspieler) {
            bewertung += dataModel.getSpielerById(playerId).spielerBewertung();
        }

        return Math.round((bewertung / 11) * 100.0) / 100.0;
    }
}
