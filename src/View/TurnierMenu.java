package View;

import Model.DataModel;

import javax.swing.*;

public class TurnierMenu extends JPanel {
    public TurnierMenu(DataModel dataModel) {
        // Initialize Turnier related components
    }


    public JMenu getMenu() {
        JMenu turnierMenu = new JMenu("Turnier");
        JMenuItem createTurnier = new JMenuItem("Turnier erstellen");
        JMenuItem editTurnier = new JMenuItem("Turnier editieren");
        JMenuItem deleteTurnier = new JMenuItem("Turnier löschen");

        GUI.CreateTurnierFrame createFrame = new GUI.CreateTurnierFrame();
        GUI.DeleteTurnierFrame deleteFrame = new GUI.DeleteTurnierFrame();
        GUI.EditTurnierFrame editFrame = new GUI.EditTurnierFrame();

        turnierMenu.add(createTurnier);
        turnierMenu.add(editTurnier);
        turnierMenu.add(deleteTurnier);

        createTurnier.addActionListener(e -> createFrame.setVisible(true));
        editTurnier.addActionListener(e -> editFrame.setVisible(true));
        deleteTurnier.addActionListener(e -> deleteFrame.setVisible(true));

        return turnierMenu;
    }

}
