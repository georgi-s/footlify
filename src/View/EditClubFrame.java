package View;

import Model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.UUID;

public class EditClubFrame extends JFrame {
    private DataModel dataModel;
    private JComboBox<Mannschaft> clubBox;
    private Mannschaft selectedClub;
    public EditClubFrame(DataModel dM) {
        this.dataModel = dM;
        setTitle("Turnier editieren");
        setSize(600, 400);

        // Create a panel to hold all the components
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        JLabel nameLabel = new JLabel("Name");
        JTextField nameField = new JTextField();

        JLabel trainerLabel = new JLabel("Name");
        JTextField trainerField = new JTextField();

        JLabel formationLabel = new JLabel("Formation");
        JComboBox<Formation> formationBox = new JComboBox<>(Formation.values());

        JLabel ligaLabel = new JLabel("Liga");
        JComboBox<Liga> LigaBox = new JComboBox<>(Liga.values());

        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Add components to the panel with GridBagConstraints
        JLabel waehlenLabel = new JLabel("Wähle ein zu löschende Mannschaft aus");
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(waehlenLabel, gbc);


        clubBox= new JComboBox<>(dataModel.getMannschaftList().toArray(new Mannschaft[0]));
        clubBox.setPreferredSize(new Dimension(200, 20));

        gbc.gridx = 1;
        panel.add(clubBox, gbc);


        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(nameLabel, gbc);
        gbc.gridx = 1;
        panel.add(nameField, gbc);
        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(trainerLabel, gbc);
        gbc.gridx = 1;
        panel.add(trainerField, gbc);
        gbc.gridx = 0;
        gbc.gridy = 4;
        panel.add(formationLabel, gbc);
        gbc.gridx = 1;
        panel.add(formationBox, gbc);
        gbc.gridx = 0;
        gbc.gridy = 5;
        panel.add(ligaLabel, gbc);
        gbc.gridx = 1;
        panel.add(LigaBox, gbc);

        // Add the panel to the frame
        add(panel, BorderLayout.CENTER);

        // Create and add the button to the frame
        JButton speichernButton = new JButton("Speichern");
        add(speichernButton, BorderLayout.SOUTH);

        // Add action listener to the button
        speichernButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Mannschaft Mannschaft =  (Mannschaft) clubBox.getSelectedItem();
                assert Mannschaft != null;


                dataModel.getClubById(selectedClub.getClubId()).editClub(nameField.getText(), trainerField.getText(), (Formation) formationBox.getSelectedItem(), (Liga) LigaBox.getSelectedItem());
                JOptionPane.showMessageDialog(EditClubFrame.this, "Club editieren", "Info", JOptionPane.INFORMATION_MESSAGE);

                dispose();
            }
        });

        clubBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!dataModel.getMannschaftList().isEmpty()) {
                    selectedClub = (Mannschaft) clubBox.getSelectedItem();
                    assert selectedClub != null;
                    nameField.setText(selectedClub.getName());
                    trainerField.setText(selectedClub.getTrainer());
                    formationBox.setSelectedItem(selectedClub.getFormation());
                    LigaBox.setSelectedItem(selectedClub.getLiga());
                }
                else{
                    JOptionPane.showMessageDialog(EditClubFrame.this, "Keine Mannschaft vorhanden", "Info", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        if(!dataModel.getMannschaftList().isEmpty()) {
            selectedClub = (Mannschaft) clubBox.getSelectedItem();
            assert selectedClub != null;
            nameField.setText(selectedClub.getName());
            trainerField.setText(selectedClub.getTrainer());
            formationBox.setSelectedItem(selectedClub.getFormation());
            LigaBox.setSelectedItem(selectedClub.getLiga());
        }
        else{
            JOptionPane.showMessageDialog(EditClubFrame.this, "Keine Mannschaft vorhanden", "Info", JOptionPane.INFORMATION_MESSAGE);
        }
    }

}
