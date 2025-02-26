package View;
import javax.swing.*;
import java.awt.*;

public class CreateTurnierFrame extends JFrame {
    public CreateTurnierFrame(){
        setTitle("Turnier erstellen");
        //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
        JComboBox<String> mannschaft1Box = new JComboBox<>();
        mannschaft1Box.setPreferredSize(new Dimension(100, 20));
        JLabel mannschaft2 = new JLabel("Mannschaft 2");
        JComboBox<String> mannschaft2Box = new JComboBox<>();
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

        // Make the frame visible
        //setVisible(true);
    }

}
