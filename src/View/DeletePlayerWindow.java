package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Model.*;

public class DeletePlayerWindow extends JFrame {
    private final JList<Spieler> playerList;
    private final DefaultListModel<Spieler> listModel;
    private final DataModel dataModel;

    public DeletePlayerWindow(DataModel dataModel) {
        this.dataModel = dataModel;
        setTitle("Delete Player");
        setSize(400, 400);
        setLayout(new BorderLayout());

        listModel = new DefaultListModel<>();
        for (Spieler player : dataModel.getSpielerList()) {
            listModel.addElement(player);
        }

        playerList = new JList<>(listModel);
        add(new JScrollPane(playerList), BorderLayout.CENTER);

        JButton deleteButton = new JButton("Delete");
        deleteButton.addActionListener(e -> deletePlayer());
        add(deleteButton, BorderLayout.SOUTH);
    }

    private void deletePlayer() {
        Spieler selectedPlayer = playerList.getSelectedValue();
        if (selectedPlayer != null) {
            dataModel.removeSpieler(selectedPlayer);
            listModel.removeElement(selectedPlayer);
            JOptionPane.showMessageDialog(this, "Player deleted successfully!");
        } else {
            JOptionPane.showMessageDialog(this, "Please select a player to delete.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}