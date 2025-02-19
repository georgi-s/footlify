package GUI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class ClubPanel extends JPanel {
    private JComboBox<String> clubComboBox;
    private DefaultTableModel availableModel;
    private DefaultTableModel selectedModel;

    public ClubPanel() {
        setLayout(new BorderLayout());

        clubComboBox = new JComboBox<>(new String[]{"Club A", "Club B", "Club C"});
        add(clubComboBox, BorderLayout.NORTH);

        availableModel = new DefaultTableModel(new String[]{"Player Name"}, 0);
        selectedModel = new DefaultTableModel(new String[]{"Player Name"}, 0);

        JTable availableTable = new JTable(availableModel);
        JTable selectedTable = new JTable(selectedModel);

        JScrollPane availableScrollPane = new JScrollPane(availableTable);
        JScrollPane selectedScrollPane = new JScrollPane(selectedTable);

        JButton moveToSelectedButton = new JButton("Move Selected →");
        JButton moveToAvailableButton = new JButton("← Move Selected");

        moveToSelectedButton.addActionListener(e -> movePlayers(availableTable, availableModel, selectedModel));
        moveToAvailableButton.addActionListener(e -> movePlayers(selectedTable, selectedModel, availableModel));

        JPanel buttonPanel = new JPanel(new GridLayout(2, 1));
        buttonPanel.add(moveToSelectedButton);
        buttonPanel.add(moveToAvailableButton);

        JPanel centerPanel = new JPanel(new GridLayout(1, 3));
        centerPanel.add(availableScrollPane);
        centerPanel.add(buttonPanel);
        centerPanel.add(selectedScrollPane);

        add(centerPanel, BorderLayout.CENTER);

        clubComboBox.addActionListener(e -> loadPlayers((String) clubComboBox.getSelectedItem()));
        loadPlayers((String) clubComboBox.getSelectedItem());
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
            String player = (String) sourceModel.getValueAt(selectedRows[i], 0);
            targetModel.addRow(new Object[]{player});
            sourceModel.removeRow(selectedRows[i]);
        }
    }

    private void loadPlayers(String club) {
        availableModel.setRowCount(0);
        selectedModel.setRowCount(0);
        // Example data, replace with actual data loading logic
        if ("Club A".equals(club)) {
            availableModel.addRow(new Object[]{"Player A1"});
            availableModel.addRow(new Object[]{"Player A2"});
            availableModel.addRow(new Object[]{"Player A3"});
        } else if ("Club B".equals(club)) {
            availableModel.addRow(new Object[]{"Player B1"});
            availableModel.addRow(new Object[]{"Player B2"});
            availableModel.addRow(new Object[]{"Player B3"});
        } else if ("Club C".equals(club)) {
            availableModel.addRow(new Object[]{"Player C1"});
            availableModel.addRow(new Object[]{"Player C2"});
            availableModel.addRow(new Object[]{"Player C3"});
        }
    }
}
