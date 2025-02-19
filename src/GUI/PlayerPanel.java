package GUI;

import javax.swing.*;

public class PlayerPanel extends JPanel {
    public PlayerPanel() {
        // Initialize Player related components
    }

    public JMenu getMenu() {
        JMenu playerMenu = new JMenu("Player");
        JMenuItem createPlayer = new JMenuItem("Player erstellen");
        JMenuItem editPlayer = new JMenuItem("Player editieren");
        JMenuItem deletePlayer = new JMenuItem("Player löschen");

        playerMenu.add(createPlayer);
        playerMenu.add(editPlayer);
        playerMenu.add(deletePlayer);

        createPlayer.addActionListener(e -> JOptionPane.showMessageDialog(this, "Insert Dialog to create Player"));
        editPlayer.addActionListener(e -> JOptionPane.showMessageDialog(this, "Insert Dialog to edit Player"));
        deletePlayer.addActionListener(e -> JOptionPane.showMessageDialog(this, "Insert Dialog to delete Player"));

        return playerMenu;
    }
}
