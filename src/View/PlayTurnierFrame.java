package View;

import Model.DataModel;
import Model.Mannschaft;
import Model.Turnier;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.UUID;

public class PlayTurnierFrame extends JFrame {
    private DataModel dataModel;
    private JComboBox<Turnier> turnierBox;
    private JComboBox<Mannschaft> mannschaft1Box;
    private JComboBox<Mannschaft> mannschaft2Box;
    private Turnier selectedTurnier;
    public PlayTurnierFrame(DataModel dM) {
        this.dataModel = dM;
        setTitle("Turnier spielen");
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
        datumField.setEditable(false);
        panel.add(datumField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(new JLabel("Preisgeld"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        JTextField preisgeldField = new JTextField();
        preisgeldField.setPreferredSize(new Dimension(100, 20));
        preisgeldField.setEditable(false);
        panel.add(preisgeldField, gbc);


        gbc.gridx = 0;
        gbc.gridy = 4;
        panel.add(new JLabel("Mannschaft 1"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 4;
        mannschaft1Box = new JComboBox<>(dataModel.getMannschaftList().toArray(new Mannschaft[0]));
        mannschaft1Box.setPreferredSize(new Dimension(100, 20));
        mannschaft1Box.setEditable(false);
        panel.add(mannschaft1Box, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        panel.add(new JLabel("Mannschaft 2"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 5;
        mannschaft2Box = new JComboBox<>(dataModel.getMannschaftList().toArray(new Mannschaft[0]));
        mannschaft2Box.setPreferredSize(new Dimension(100, 20));
        mannschaft2Box.setEditable(false);
        panel.add(mannschaft2Box, gbc);

        // Add the "Speichern" button
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        JButton spielenButton = new JButton("Spielen!");
        panel.add(spielenButton, gbc);

        // Add the panel to the frame
        add(panel);

        // Add action listener to the button
        spielenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Mannschaft Man1 =  (Mannschaft) mannschaft1Box.getSelectedItem();
                assert Man1 != null;
                Mannschaft Man2 =  (Mannschaft) mannschaft2Box.getSelectedItem();
                assert Man2 != null;


                //calc wich team wins
                double man1Val = mannschaftsbewertungAusgeben(Man1.ClubId);
                double man2Val = mannschaftsbewertungAusgeben(Man2.ClubId);

                man1Val = Math.round((man1Val/(man1Val+man2Val)) * 100.0) / 100.0;
                man2Val = Math.round((man2Val/(man1Val+man2Val)) * 100.0) / 100.0;

                double rand = Math.random();
                while(rand == man1Val)
                    rand = Math.random();


                if(rand < man1Val ){
                    JOptionPane.showMessageDialog(PlayTurnierFrame.this, Man1.getName()+" hat gewonnen und bekommt "+selectedTurnier.getInsgPreisgeld()+" Euro .", "Info", JOptionPane.INFORMATION_MESSAGE);
                }
                else{
                    JOptionPane.showMessageDialog(PlayTurnierFrame.this, Man2.getName()+" hat gewonnen und bekommt "+selectedTurnier.getInsgPreisgeld()+" Euro .", "Info", JOptionPane.INFORMATION_MESSAGE);
                }

                //Ask if tunier delete and if delete it
                int dialogResult = JOptionPane.showConfirmDialog(PlayTurnierFrame.this, "Moechtest du das Turnier loeschen?", "Info", JOptionPane.YES_NO_OPTION);
                if(dialogResult == JOptionPane.YES_OPTION){
                    dataModel.deleteTurnier(selectedTurnier.getTurnierId());
                    JOptionPane.showMessageDialog(PlayTurnierFrame.this, "Turnier geloescht", "Info", JOptionPane.INFORMATION_MESSAGE);
                }
                dispose();

            }
        });

        turnierBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectedTurnier = (Turnier) turnierBox.getSelectedItem();
                assert selectedTurnier != null;
                mannschaft1Box.setSelectedItem(dataModel.getClubById(selectedTurnier.getTeilnehmer().get(0)));
                mannschaft2Box.setSelectedItem(dataModel.getClubById(selectedTurnier.getTeilnehmer().get(1)));
                ortField.setText(selectedTurnier.getOrt());
                datumField.setText(selectedTurnier.getDate());
                preisgeldField.setText(selectedTurnier.getInsgPreisgeld() + "");
            }
        });

        selectedTurnier = (Turnier) turnierBox.getSelectedItem();
        assert selectedTurnier != null;
        mannschaft1Box.setSelectedItem(dataModel.getClubById(selectedTurnier.getTeilnehmer().get(0)));
        mannschaft2Box.setSelectedItem(dataModel.getClubById(selectedTurnier.getTeilnehmer().get(1)));
        ortField.setText(selectedTurnier.getOrt());
        datumField.setText(selectedTurnier.getDate());
        preisgeldField.setText(selectedTurnier.getInsgPreisgeld()+"");

        // Make the frame visible
        setVisible(false);
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

}
