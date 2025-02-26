package GUI;

import javax.swing.*;
import java.awt.*;

public class EditTurnierFrame extends JFrame {
    public EditTurnierFrame(){
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
        JComboBox<String> turnierBox = new JComboBox<>();
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
        JComboBox<String> mannschaft1Box = new JComboBox<>();
        mannschaft1Box.setPreferredSize(new Dimension(100, 20));
        panel.add(mannschaft1Box, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        panel.add(new JLabel("Mannschaft 2"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 5;
        JComboBox<String> mannschaft2Box = new JComboBox<>();
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

        // Make the frame visible
        setVisible(false);
    }

}
