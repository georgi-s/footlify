package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.UUID;

import Model.*;

public class EditPlayerWindow extends JFrame {
    private final JList<Spieler> playerList;
    private final DataModel dataModel;

    public EditPlayerWindow(DataModel dataModel) {
        this.dataModel = dataModel;
        setTitle("Edit Player");
        setSize(400, 400);
        setLayout(new BorderLayout());

        DefaultListModel<Spieler> listModel = new DefaultListModel<>();

        Mannschaft selectedClub = dataModel.getSelectedClub();
        if (selectedClub != null) {
            for (UUID playerId : selectedClub.getFeldspieler()) {
                Spieler player = dataModel.getSpielerById(playerId);
                if (player != null) {
                    listModel.addElement(player);
                }
            }
        }

        playerList = new JList<>(listModel);
        add(new JScrollPane(playerList), BorderLayout.CENTER);

        JButton editButton = new JButton("Edit");
        editButton.addActionListener(e -> editPlayer());
        add(editButton, BorderLayout.SOUTH);
    }

    private void editPlayer() {
        Spieler selectedPlayer = playerList.getSelectedValue();
        if (selectedPlayer != null) {
            new EditPlayerFormWindow(dataModel, selectedPlayer).setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "Please select a player to edit.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}