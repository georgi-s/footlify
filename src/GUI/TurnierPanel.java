package GUI;

import javax.swing.*;

public class TurnierPanel extends JPanel {
    public TurnierPanel() {
        // Initialize Turnier related components
    }

    public JMenu getMenu() {
        JMenu turnierMenu = new JMenu("Turnier");
        JMenuItem createTurnier = new JMenuItem("Turnier erstellen");
        JMenuItem editTurnier = new JMenuItem("Turnier editieren");
        JMenuItem deleteTurnier = new JMenuItem("Turnier löschen");

        turnierMenu.add(createTurnier);
        turnierMenu.add(editTurnier);
        turnierMenu.add(deleteTurnier);

        createTurnier.addActionListener(e -> JOptionPane.showMessageDialog(this, "Insert Dialog to create Turnier"));
        editTurnier.addActionListener(e -> JOptionPane.showMessageDialog(this, "Insert Dialog to edit Turnier"));
        deleteTurnier.addActionListener(e -> JOptionPane.showMessageDialog(this, "Insert Dialog to delete Turnier"));

        return turnierMenu;
    }
}
