package View;
import Model.DataModel;
import Model.Mannschaft;
import Model.Turnier;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;
import java.util.UUID;

public class CreateTurnierFrame extends JFrame {
    private JComboBox<Mannschaft> mannschaft1Box;
    private JComboBox<Mannschaft> mannschaft2Box;
    private DataModel dataModel;
    public CreateTurnierFrame(DataModel dM) {
        this.dataModel = dM;

        setTitle("Turnier erstellen");
        setSize(600, 400);

        // Use a GridBagLayout for the main panel
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.WEST;

        JLabel ortLabel = new JLabel("Ort");
        JTextField ortField = new JTextField();
        ortField.setPreferredSize(new Dimension(100, 20));
        JLabel datumLabel = new JLabel("Datum");
        JTextField datumField = new JTextField();
        datumField.setPreferredSize(new Dimension(100, 20));
        JLabel preisgeldLabel = new JLabel("Preisgeld");
        JTextField preisgeldField = new JTextField();
        preisgeldField.setPreferredSize(new Dimension(100, 20));

            JLabel mannschaft1 = new JLabel("Mannschaft 1");
        mannschaft1Box = new JComboBox<>(dataModel.getMannschaftList().toArray(new Mannschaft[0]));
        mannschaft1Box.setPreferredSize(new Dimension(100, 20));
        JLabel mannschaft2 = new JLabel("Mannschaft 2");
        mannschaft2Box = new JComboBox<>(dataModel.getMannschaftList().toArray(new Mannschaft[0]));
        mannschaft2Box.setPreferredSize(new Dimension(100, 20));

        // Add components to the panel with GridBagConstraints
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(ortLabel, gbc);
        gbc.gridx = 1;
        panel.add(ortField, gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(datumLabel, gbc);
        gbc.gridx = 1;
        panel.add(datumField, gbc);
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(preisgeldLabel, gbc);
        gbc.gridx = 1;
        panel.add(preisgeldField, gbc);
        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(mannschaft1, gbc);
        gbc.gridx = 1;
        panel.add(mannschaft1Box, gbc);
        gbc.gridx = 0;
        gbc.gridy = 4;
        panel.add(mannschaft2, gbc);
        gbc.gridx = 1;
        panel.add(mannschaft2Box, gbc);

        // Add the panel to the frame
        add(panel, BorderLayout.CENTER);

        // Create and add the button to the frame
        JButton createButton = new JButton("Create");
        add(createButton, BorderLayout.SOUTH);

        // Add action listener to the button
        createButton.addActionListener(new ActionListener() {
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



                dataModel.addTurnier(new Turnier(ortField.getText(), datumField.getText(), Integer.parseInt(preisgeldField.getText()), mannschaften));
                JOptionPane.showMessageDialog(CreateTurnierFrame.this, "Turnier erstellt", "Info", JOptionPane.INFORMATION_MESSAGE);

                dispose();
            }
        });

        // Make the frame visible
        //setVisible(true);
    }

}
