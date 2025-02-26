package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DeleteTurnierFrame extends JFrame {
    public DeleteTurnierFrame() {
        setTitle("Turnier löschen");
        //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;

        JLabel waehlenLabel = new JLabel("Wähle ein zu löschendes Turnier aus");
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panel.add(waehlenLabel, gbc);  

        JComboBox<String> turnierBox = new JComboBox<>();
        turnierBox.setPreferredSize(new Dimension(200, 20));
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        panel.add(turnierBox, gbc);

        JButton loeschenButton = new JButton("Löschen");
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        panel.add(loeschenButton, gbc);

        // Add action listener to the button
        loeschenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


                JOptionPane.showMessageDialog(DeleteTurnierFrame.this, "Turnier gelöscht", "Info", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        // Add the panel to the frame
        add(panel, BorderLayout.CENTER);

        // Make the frame visible
        //setVisible(true);
    }

}
