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
import java.util.ArrayList;
import java.util.UUID;

public class ClubPanel extends JPanel implements DataModelListener {
    private final JComboBox<Mannschaft> clubComboBox;
    private final DefaultTableModel availableModel;
    private final DefaultTableModel selectedModel;
    private final DataModel dataModel;
    private final JTextField TrainerField;
    private final JComboBox<Formation> cboFormation;
    private final JComboBox<Liga> cboLiga;
    private final JLabel scoreField;

    public ClubPanel(DataModel dataModel) {
        this.dataModel = dataModel;
        dataModel.addDataModelListener(this);

        //generate Testdata

        setLayout(new BorderLayout());

        clubComboBox = new JComboBox<>(dataModel.getMannschaftList().toArray(new Mannschaft[0]));

        add(clubComboBox, BorderLayout.NORTH);

        String[] columnNames = {"ID", "Vorname", "Nachname", "Position", "Bewertung"};
        availableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // All cells are not editable
            }
        };
        selectedModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // All cells are not editable
            }
        };

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
            if (!aufstellungPruefen(dataModel.getSelectedClub().getClubId())) {
                JOptionPane.showMessageDialog(this, "Aufstellung entspricht nicht der Formation", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Aufstellung ist korrekt");
            }
        });
        JPanel buttonPanel = new JPanel(new GridLayout(5, 3, 5, 5));

        buttonPanel.add(moveToSelectedButton);
        buttonPanel.add(moveToAvailableButton);
        buttonPanel.add(checkFormation);
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10)); // 10px margin all sides

        JPanel LHeaderPanel = new JPanel();
        LHeaderPanel.add(new JLabel("Verfügbare Spieler"));
        JPanel RHeaderPanel = new JPanel();
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
        cboFormation = new JComboBox<>(Formation.values());
        cboLiga = new JComboBox<>(Liga.values());
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
        cboFormation.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                dataModel.getSelectedClub().setFormation((Formation) e.getItem());
            }
        });

        comboBoxPanel.add(cboLiga);
        cboLiga.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                dataModel.getSelectedClub().setLiga((Liga) e.getItem());
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

        clubComboBox.addActionListener(e -> {
            Mannschaft selectedClub = (Mannschaft) clubComboBox.getSelectedItem();
            assert selectedClub != null;
            loadPlayers(selectedClub.getClubId());
            loadData(selectedClub.getClubId());
        });

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
        for (UUID playerId : club.getAuswechselspieler()) {
            Model.Spieler player = dataModel.getSpielerById(playerId);
            availableModel.addRow(new Object[]{player.getPlayerId(), player.getVorname(), player.getNachname(), player.getClass().getSimpleName(), player.spielerBewertung()});
        }
        for (UUID playerId : club.getFeldspieler()) {
            Model.Spieler player = dataModel.getSpielerById(playerId);
            selectedModel.addRow(new Object[]{player.getPlayerId(), player.getVorname(), player.getNachname(), player.getClass().getSimpleName(), player.spielerBewertung()});
        }


        scoreField.setText("Mannschaftsbewertung: " + mannschaftsbewertungAusgeben(clubId));
    }

    private void loadData(UUID clubId) {
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

    private boolean aufstellungPruefen(ArrayList<UUID> feldspieler, Formation formation) {
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

    public Double mannschaftsbewertungAusgeben(UUID clubId) {
        return mannschaftsbewertungAusgeben(dataModel.getClubById(clubId).getFeldspieler());
    }

    public Double mannschaftsbewertungAusgeben(ArrayList<UUID> feldspieler) {
        double bewertung = 0;
        for (UUID playerId : feldspieler) {
            bewertung += dataModel.getSpielerById(playerId).spielerBewertung();
        }

        return Math.round((bewertung / 11) * 100.0) / 100.0;
    }

    @Override
    public void onDataModelUpdated() {
        if (dataModel.getSelectedClub() == null || dataModel.getSelectedClub().getClubId() == null) {
            return;
        }

        loadPlayers(dataModel.getSelectedClub().getClubId());
        loadData(dataModel.getSelectedClub().getClubId());
    }
}
