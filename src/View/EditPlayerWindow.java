package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import Model.*;

public class EditPlayerWindow extends JFrame {
    private JList<Spieler> playerList;
    private DefaultListModel<Spieler> listModel;
    private DataModel dataModel;

    public EditPlayerWindow(DataModel dataModel) {
        this.dataModel = dataModel;
        setTitle("Edit Player");
        setSize(400, 400);
        setLayout(new BorderLayout());

        listModel = new DefaultListModel<>();
        for (Spieler player : dataModel.getSpielerList()) {
            listModel.addElement(player);
        }

        playerList = new JList<>(listModel);
        add(new JScrollPane(playerList), BorderLayout.CENTER);

        JButton editButton = new JButton("Edit");
        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editPlayer();
            }
        });
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