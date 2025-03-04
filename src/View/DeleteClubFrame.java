package View;

import Model.DataModel;
import Model.Mannschaft;
import Model.Turnier;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DeleteClubFrame extends JFrame {
    private JComboBox<Mannschaft> clubComboBox;
    public DeleteClubFrame(DataModel dataModel) {
        setTitle("Turnier löschen");
        //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);


        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;

        JLabel waehlenLabel = new JLabel("Wähle ein zu löschende Mannschaft aus");
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panel.add(waehlenLabel, gbc);

        clubComboBox = new JComboBox<>(dataModel.getMannschaftList().toArray(new Mannschaft[0]));

        clubComboBox.setPreferredSize(new Dimension(200, 20));
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        panel.add(clubComboBox, gbc);

        JButton loeschenButton = new JButton("Löschen");
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        panel.add(loeschenButton, gbc);
        // Add the panel to the frame
        add(panel, BorderLayout.CENTER);

        // Add action listener to the button
        loeschenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dataModel.removeMannschaft((Mannschaft) clubComboBox.getSelectedItem());
                JOptionPane.showMessageDialog(DeleteClubFrame.this, "Mannschaft gelöscht", "Info", JOptionPane.INFORMATION_MESSAGE);

                dispose();
            }
        });

    }

}
