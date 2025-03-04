package View;

import Model.DataModel;
import Model.Mannschaft;
import Model.Turnier;

import javax.swing.*;
import javax.xml.crypto.Data;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class EditTurnierFrame extends JFrame {
    private DataModel dataModel;
    private JComboBox<Turnier> turnierBox;
    private JComboBox<Mannschaft> mannschaft1Box;
    private JComboBox<Mannschaft> mannschaft2Box;
    private Turnier selectedTurnier;
    public EditTurnierFrame(DataModel dM) {
        this.dataModel = dM;
        setTitle("Turnier editieren");
        setSize(600, 400);

        // Create a panel to hold all the components
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Add components to the panel
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(new JLabel("Waehle ein zu editierendes Turnier aus"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        turnierBox= new JComboBox<>(dataModel.getTurnierList().toArray(new Turnier[0]));
        turnierBox.setPreferredSize(new Dimension(200, 20));
        panel.add(turnierBox, gbc);


        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(new JLabel("Ort"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        JTextField ortField = new JTextField();
        ortField.setPreferredSize(new Dimension(100, 20));
        panel.add(ortField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(new JLabel("Datum"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        JTextField datumField = new JTextField();
        datumField.setPreferredSize(new Dimension(100, 20));
        panel.add(datumField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(new JLabel("Preisgeld"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        JTextField preisgeldField = new JTextField();
        preisgeldField.setPreferredSize(new Dimension(100, 20));
        panel.add(preisgeldField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        panel.add(new JLabel("Mannschaft 1"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 4;
        mannschaft1Box = new JComboBox<>(dataModel.getMannschaftList().toArray(new Mannschaft[0]));
        mannschaft1Box.setPreferredSize(new Dimension(100, 20));
        panel.add(mannschaft1Box, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        panel.add(new JLabel("Mannschaft 2"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 5;
        mannschaft2Box = new JComboBox<>(dataModel.getMannschaftList().toArray(new Mannschaft[0]));
        mannschaft2Box.setPreferredSize(new Dimension(100, 20));
        panel.add(mannschaft2Box, gbc);

        // Add the "Speichern" button
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        JButton speichernButton = new JButton("Speichern");
        panel.add(speichernButton, gbc);

        // Add the panel to the frame
        add(panel);

        // Add action listener to the button
        speichernButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<UUID> mannschaften = new ArrayList<>();

                Mannschaft Mannschaft =  (Mannschaft) mannschaft1Box.getSelectedItem();
                assert Mannschaft != null;
                mannschaften.add(Mannschaft.ClubId);
                Mannschaft = null;
                Mannschaft =  (Mannschaft) mannschaft2Box.getSelectedItem();
                assert Mannschaft != null;
                mannschaften.add(Mannschaft.ClubId);



                dataModel.getTurnierById(selectedTurnier.getTurnierId()).editTurnier(ortField.getText(),datumField.getText(), Integer.parseInt(preisgeldField.getText()), mannschaften);
                JOptionPane.showMessageDialog(EditTurnierFrame.this, "Turnier editieren", "Info", JOptionPane.INFORMATION_MESSAGE);

                dispose();
            }
        });

        turnierBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!dataModel.getTurnierList().isEmpty()) {
                    selectedTurnier = (Turnier) turnierBox.getSelectedItem();
                    assert selectedTurnier != null;
                    mannschaft1Box.setSelectedItem(dataModel.getClubById(selectedTurnier.getTeilnehmer().get(0)));
                    mannschaft2Box.setSelectedItem(dataModel.getClubById(selectedTurnier.getTeilnehmer().get(1)));
                    ortField.setText(selectedTurnier.getOrt());
                    datumField.setText(selectedTurnier.getDate());
                    preisgeldField.setText(selectedTurnier.getInsgPreisgeld() + "");
                }
                else{
                    JOptionPane.showMessageDialog(EditTurnierFrame.this, "Keine Turniere vorhanden", "Info", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        if(!dataModel.getTurnierList().isEmpty()) {

            selectedTurnier = (Turnier) turnierBox.getSelectedItem();
            assert selectedTurnier != null;
            mannschaft1Box.setSelectedItem(dataModel.getClubById(selectedTurnier.getTeilnehmer().get(0)));
            mannschaft2Box.setSelectedItem(dataModel.getClubById(selectedTurnier.getTeilnehmer().get(1)));
            ortField.setText(selectedTurnier.getOrt());
            datumField.setText(selectedTurnier.getDate());
            preisgeldField.setText(selectedTurnier.getInsgPreisgeld() + "");
        }
        else
            JOptionPane.showMessageDialog(EditTurnierFrame.this, "Keine Turniere vorhanden", "Info", JOptionPane.INFORMATION_MESSAGE);
        // Make the frame visible
        setVisible(false);
    }

}
