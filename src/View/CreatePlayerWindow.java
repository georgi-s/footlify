package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import Model.*;

public class CreatePlayerWindow extends JFrame {
    private JComboBox<String> playerTypeComboBox;
    private JTextField nachnameField;
    private JTextField vornameField;
    private JTextField geburtsdatumField;
    private JSpinner gespielteSpieleSpinner;
    private JCheckBox gesperrtCheckBox;
    private JTextField vereinsbeitrittField;
    private JSpinner roteKartenSpinner;
    private JSpinner gelbeKartenSpinner;
    private JPanel additionalFieldsPanel;
    private DataModel dataModel;
    private SimpleDateFormat dateFormat;

    public CreatePlayerWindow(DataModel dataModel) {
        this.dataModel = dataModel;
        this.dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        setTitle("Create Player");
        setSize(400, 600);
        setLayout(new GridLayout(0, 2));

        String[] playerTypes = {"Position auswählen", "Verteidiger", "Stuermer", "Mittelfeldspieler", "Torwart"};
        playerTypeComboBox = new JComboBox<>(playerTypes);
        playerTypeComboBox.addActionListener(e -> updateAdditionalFields());
        add(new JLabel("Player Type:"));
        add(playerTypeComboBox);

        nachnameField = new JTextField("Nachname");
        vornameField = new JTextField("Vorname");
        geburtsdatumField = new JTextField("dd.MM.yyyy");
        gespielteSpieleSpinner = new JSpinner(new SpinnerNumberModel(0, 0, Integer.MAX_VALUE, 1));
        gesperrtCheckBox = new JCheckBox();
        vereinsbeitrittField = new JTextField("dd.MM.yyyy");
        roteKartenSpinner = new JSpinner(new SpinnerNumberModel(0, 0, Integer.MAX_VALUE, 1));
        gelbeKartenSpinner = new JSpinner(new SpinnerNumberModel(0, 0, Integer.MAX_VALUE, 1));

        add(new JLabel("Nachname:"));
        add(nachnameField);
        add(new JLabel("Vorname:"));
        add(vornameField);
        add(new JLabel("Geburtsdatum:"));
        add(geburtsdatumField);
        add(new JLabel("Gespielte Spiele:"));
        add(gespielteSpieleSpinner);
        add(new JLabel("Gesperrt:"));
        add(gesperrtCheckBox);
        add(new JLabel("Vereinsbeitritt:"));
        add(vereinsbeitrittField);
        add(new JLabel("Rote Karten:"));
        add(roteKartenSpinner);
        add(new JLabel("Gelbe Karten:"));
        add(gelbeKartenSpinner);

        additionalFieldsPanel = new JPanel(new GridLayout(0, 2));
        add(additionalFieldsPanel);

        JButton createButton = new JButton("Create");
        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createPlayer();
            }
        });
        add(createButton);
    }

    private void updateAdditionalFields() {
        additionalFieldsPanel.removeAll();
        String playerType = (String) playerTypeComboBox.getSelectedItem();
        if ("Verteidiger".equals(playerType)) {
            addAdditionalField("Geblockte Angriffe:", new JSpinner(new SpinnerNumberModel(0, 0, Integer.MAX_VALUE, 1)));
            addAdditionalField("Gewonnene Zweikämpfe:", new JSpinner(new SpinnerNumberModel(0, 0, Integer.MAX_VALUE, 1)));
            addAdditionalField("Passquote:", new JTextField("0.65"));
        } else if ("Stuermer".equals(playerType)) {
            addAdditionalField("Geschossene Tore:", new JSpinner(new SpinnerNumberModel(0, 0, Integer.MAX_VALUE, 1)));
            addAdditionalField("Schussgenauigkeit:", new JTextField("0.67"));
            addAdditionalField("Chancenverwertung:", new JTextField("0.4"));
        } else if ("Mittelfeldspieler".equals(playerType)) {
            addAdditionalField("Anzahl Vorlagen:", new JSpinner(new SpinnerNumberModel(0, 0, Integer.MAX_VALUE, 1)));
            addAdditionalField("Tore:", new JSpinner(new SpinnerNumberModel(0, 0, Integer.MAX_VALUE, 1)));
            addAdditionalField("Passquote (double):", new JTextField("0.65"));
        } else if ("Torwart".equals(playerType)) {
            addAdditionalField("Spiele ohne Gegentor:", new JSpinner(new SpinnerNumberModel(0, 0, Integer.MAX_VALUE, 1)));
            addAdditionalField("Gegentore:", new JSpinner(new SpinnerNumberModel(0, 0, Integer.MAX_VALUE, 1)));
            addAdditionalField("Haltequote (double):", new JTextField("0.8"));
        }
        additionalFieldsPanel.revalidate();
        additionalFieldsPanel.repaint();
    }

    private void addAdditionalField(String label, JComponent component) {
        additionalFieldsPanel.add(new JLabel(label));
        additionalFieldsPanel.add(component);
    }

    private void createPlayer() {
        try {
            String nachname = nachnameField.getText();
            String vorname = vornameField.getText();
            Date geburtsdatum = dateFormat.parse(geburtsdatumField.getText());
            int gespielteSpiele = (int) gespielteSpieleSpinner.getValue();
            boolean gesperrt = gesperrtCheckBox.isSelected();
            Date vereinsbeitritt = dateFormat.parse(vereinsbeitrittField.getText());
            int roteKarten = (int) roteKartenSpinner.getValue();
            int gelbeKarten = (int) gelbeKartenSpinner.getValue();

            String playerType = (String) playerTypeComboBox.getSelectedItem();
            Spieler player = null;

            if ("Verteidiger".equals(playerType)) {
                int geblockteAngriffe = (int) ((JSpinner) additionalFieldsPanel.getComponent(1)).getValue();
                int gewonneneZweikaempfe = (int) ((JSpinner) additionalFieldsPanel.getComponent(3)).getValue();
                double passquote = Double.parseDouble(((JTextField) additionalFieldsPanel.getComponent(5)).getText());
                player = new Verteidiger(geblockteAngriffe, gewonneneZweikaempfe, passquote, nachname, vorname, geburtsdatum, gespielteSpiele, gesperrt, vereinsbeitritt, roteKarten, gelbeKarten);
            } else if ("Stuermer".equals(playerType)) {
                int geschosseneTore = (int) ((JSpinner) additionalFieldsPanel.getComponent(1)).getValue();
                double schussgenauigkeit = Double.parseDouble(((JTextField) additionalFieldsPanel.getComponent(3)).getText());
                double chancenverwertung = Double.parseDouble(((JTextField) additionalFieldsPanel.getComponent(5)).getText());
                player = new Stuermer(geschosseneTore, schussgenauigkeit, chancenverwertung, nachname, vorname, geburtsdatum, gespielteSpiele, gesperrt, vereinsbeitritt, roteKarten, gelbeKarten);
            } else if ("Mittelfeldspieler".equals(playerType)) {
                int anzahlVorlagen = (int) ((JSpinner) additionalFieldsPanel.getComponent(1)).getValue();
                int tore = (int) ((JSpinner) additionalFieldsPanel.getComponent(3)).getValue();
                double passquote = Double.parseDouble(((JTextField) additionalFieldsPanel.getComponent(5)).getText());
                player = new Mittelfeldspieler(anzahlVorlagen, tore, passquote, nachname, vorname, geburtsdatum, gespielteSpiele, gesperrt, vereinsbeitritt, roteKarten, gelbeKarten);
            } else if ("Torwart".equals(playerType)) {
                int spieleOhneGegentor = (int) ((JSpinner) additionalFieldsPanel.getComponent(1)).getValue();
                int gegentore = (int) ((JSpinner) additionalFieldsPanel.getComponent(3)).getValue();
                double haltequote = Double.parseDouble(((JTextField) additionalFieldsPanel.getComponent(5)).getText());
                player = new Torwart(spieleOhneGegentor, gegentore, haltequote, nachname, vorname, geburtsdatum, gespielteSpiele, gesperrt, vereinsbeitritt, roteKarten, gelbeKarten);
            }

            if (player != null) {
                dataModel.addSpieler(player);
                JOptionPane.showMessageDialog(this, "Player created successfully!");
                dispose();
            }
        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(this, "Please enter dates in the format yyyy-MM-dd.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Please fill out all fields correctly.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}