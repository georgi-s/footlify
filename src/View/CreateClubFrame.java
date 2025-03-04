package View;
import Model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.UUID;

public class CreateClubFrame extends JFrame {
    private DataModel dataModel;
    public CreateClubFrame(DataModel dM) {
        this.dataModel = dM;

        setTitle("Mannschaft erstellen");
        setSize(600, 400);

        // Use a GridBagLayout for the main panel
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.WEST;

        JLabel nameLabel = new JLabel("Name");
        JTextField nameField = new JTextField();

        JLabel trainerLabel = new JLabel("Name");
        JTextField trainerField = new JTextField();

        JLabel formationLabel = new JLabel("Formation");
        JComboBox<Formation> formationBox = new JComboBox<>(Formation.values());

        JLabel ligaLabel = new JLabel("Liga");
        JComboBox<Liga> LigaBox = new JComboBox<>(Liga.values());

        // Add components to the panel with GridBagConstraints
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(nameLabel, gbc);
        gbc.gridx = 1;
        panel.add(nameField, gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(trainerLabel, gbc);
        gbc.gridx = 1;
        panel.add(trainerField, gbc);
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(formationLabel, gbc);
        gbc.gridx = 1;
        panel.add(formationBox, gbc);
        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(ligaLabel, gbc);
        gbc.gridx = 1;
        panel.add(LigaBox, gbc);

        // Add the panel to the frame
        add(panel, BorderLayout.CENTER);

        // Create and add the button to the frame
        JButton createButton = new JButton("Create");
        add(createButton, BorderLayout.SOUTH);

        // Add action listener to the button
        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dataModel.addMannschaft(new Mannschaft(nameField.getText(), trainerField.getText(), new ArrayList<UUID>(), new ArrayList<UUID>(), (Formation) formationBox.getSelectedItem(), (Liga) LigaBox.getSelectedItem()));
                JOptionPane.showMessageDialog(CreateClubFrame.this, "Club erstellt", "Info", JOptionPane.INFORMATION_MESSAGE);


                dispose();
            }
        });

    }

}
