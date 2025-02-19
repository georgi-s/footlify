
import sportverein.Mannschaft;
import sportverein.Spieler;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {
    private ArrayList<sportverein.Spieler> spieler;
    private ArrayList<sportverein.Mannschaft> clubs;
    private sportverein.Turnier turnier;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Main::createAndShowGUI);
    }

    private static final Map<String, String[]> clubPlayers = new HashMap<>();

    static {
        clubPlayers.put("Club A", new String[]{"Player A1", "Player A2", "Player A3"});
        clubPlayers.put("Club B", new String[]{"Player B1", "Player B2", "Player B3"});
        clubPlayers.put("Club C", new String[]{"Player C1", "Player C2", "Player C3"});
    }


    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Club Selector");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLayout(new BorderLayout());

        JMenuBar menuBar = new JMenuBar();

        JMenu ClubMenu = new JMenu("Club");
        JMenuItem createClub = new JMenuItem("Club erstellen");
        JMenuItem editClub = new JMenuItem("Club editieren");
        JMenuItem deleteClub = new JMenuItem("Club löschen");
        ClubMenu.add(createClub);
        ClubMenu.add(editClub);
        ClubMenu.add(deleteClub);
        menuBar.add(ClubMenu);

        createClub.addActionListener(e -> {
            JOptionPane.showMessageDialog(frame, "Insert Dialog to create Club");
        });
        editClub.addActionListener(e -> {
            JOptionPane.showMessageDialog(frame, "Insert Dialog to edit Club");
        });
        deleteClub.addActionListener(e -> {
            JOptionPane.showMessageDialog(frame, "Insert Dialog to delete Club");
        });


        JMenu TurnierMenu = new JMenu("Turnier");
        JMenuItem createTurnier = new JMenuItem("Turnier erstellen");
        JMenuItem editTurnier= new JMenuItem("Turnier editieren");
        JMenuItem deleteTurnier = new JMenuItem("Turnier löschen");
        TurnierMenu.add(createTurnier);
        TurnierMenu.add(editTurnier);
        TurnierMenu.add(deleteTurnier);
        menuBar.add(TurnierMenu);

        createTurnier.addActionListener(e -> {
            JOptionPane.showMessageDialog(frame, "Insert Dialog to create Turnier");
        });
        editTurnier.addActionListener(e -> {
            JOptionPane.showMessageDialog(frame, "Insert Dialog to edit Turnier");
        });
        deleteTurnier.addActionListener(e -> {
            JOptionPane.showMessageDialog(frame, "Insert Dialog to delete Turnier");
        });

        JMenu PlayerMenu = new JMenu("Player");
        JMenuItem createPlayer = new JMenuItem("Player erstellen");
        JMenuItem editPlayer = new JMenuItem("Player editieren");
        JMenuItem deletePlayer = new JMenuItem("Player löschen");
        PlayerMenu.add(createPlayer);
        PlayerMenu.add(editPlayer);
        PlayerMenu.add(deletePlayer);
        menuBar.add(PlayerMenu);

        createPlayer.addActionListener(e -> {
            JOptionPane.showMessageDialog(frame, "Insert Dialog to create Player");
        });
        editPlayer.addActionListener(e -> {
            JOptionPane.showMessageDialog(frame, "Insert Dialog to edit Player");
        });
        deletePlayer.addActionListener(e -> {
            JOptionPane.showMessageDialog(frame, "Insert Dialog to delete Player");
        });


        frame.setJMenuBar(menuBar);


        // ComboBox for club selection
        String[] clubs = {"Club A", "Club B", "Club C"};
        JComboBox<String> clubComboBox = new JComboBox<>(clubs);
        frame.add(clubComboBox, BorderLayout.NORTH);

        // Table models
        String[] columnNames = {"Player Name"};
        DefaultTableModel availableModel = new DefaultTableModel(columnNames, 0);
        DefaultTableModel selectedModel = new DefaultTableModel(columnNames, 0);

        // Tables
        JTable availableTable = new JTable(availableModel);
        JTable selectedTable = new JTable(selectedModel);

        // Scroll panes for tables
        JScrollPane availableScrollPane = new JScrollPane(availableTable);
        JScrollPane selectedScrollPane = new JScrollPane(selectedTable);

        // Buttons to move players
        JButton moveToSelectedButton = new JButton("Move Selected →");
        JButton moveToAvailableButton = new JButton("← Move Selected");

        moveToSelectedButton.addActionListener(e -> movePlayers(availableTable, availableModel, selectedModel));
        moveToAvailableButton.addActionListener(e -> movePlayers(selectedTable, selectedModel, availableModel));

        // Layout for tables and buttons
        JPanel centerPanel = new JPanel(new GridLayout(1, 3));
        JPanel buttonPanel = new JPanel(new GridLayout(2, 1));
        buttonPanel.add(moveToSelectedButton);
        buttonPanel.add(moveToAvailableButton);

        centerPanel.add(availableScrollPane);
        centerPanel.add(buttonPanel);
        centerPanel.add(selectedScrollPane);

        frame.add(centerPanel, BorderLayout.CENTER);

        clubComboBox.addActionListener(e -> loadPlayers((String) clubComboBox.getSelectedItem(), availableModel, selectedModel));
        loadPlayers((String) clubComboBox.getSelectedItem(), availableModel, selectedModel);

        frame.setVisible(true);
    }

    private static void movePlayers(JTable sourceTable, DefaultTableModel sourceModel, DefaultTableModel targetModel) {
        int[] selectedRows = sourceTable.getSelectedRows();
        for (int i = selectedRows.length - 1; i >= 0; i--) {
            String player = (String) sourceModel.getValueAt(selectedRows[i], 0);
            targetModel.addRow(new Object[]{player});
            sourceModel.removeRow(selectedRows[i]);
        }
    }

    private static void loadPlayers(String club, DefaultTableModel availableModel, DefaultTableModel selectedModel) {
        availableModel.setRowCount(0);
        selectedModel.setRowCount(0);
        if (clubPlayers.containsKey(club)) {
            for (String player : clubPlayers.get(club)) {
                availableModel.addRow(new Object[]{player});
            }
        }
    }


    private sportverein.Mannschaft mannschaftErstellen()
    {
        sportverein.Mannschaft test = null;
        return test;
    }

    private void mannschaftBearbeiten(sportverein.Mannschaft team)
    {

    }
    private void deleteClub(int clubId){
        for(int i = 0; i < clubs.size(); i++){
            if(clubs.get(i).ClubId == clubId){
                clubs.remove(i);
                break;
            }
        }
    }
    private Spieler createPlayer(){
        Spieler test = null;
        return test;
    }
    private void deletePlayer(int spielerId){
        for(int i = 0; i < spieler.size(); i++){
            if(spieler.get(i).getPlayerId() == spielerId){
                spieler.remove(i);
                break;
            }
        }
    }
    private void deleteTurnier(){
        turnier = null;
    }
}