package View;

import Model.DataModel;

import javax.swing.*;

public class TurnierMenu extends JPanel {
    private DataModel dataModel;

    public TurnierMenu(DataModel dataModel) {
        this.dataModel = dataModel;
    }

    public JMenu getMenu() {
        JMenu turnierMenu = new JMenu("Turnier");
        JMenuItem createTurnier = new JMenuItem("Turnier erstellen");
        JMenuItem editTurnier = new JMenuItem("Turnier editieren");
        JMenuItem deleteTurnier = new JMenuItem("Turnier löschen");
        JMenuItem playTurnier = new JMenuItem("Turnier spielen");

        turnierMenu.add(createTurnier);
        turnierMenu.add(editTurnier);
        turnierMenu.add(deleteTurnier);
        turnierMenu.addSeparator();
        turnierMenu.add(playTurnier);

        createTurnier.addActionListener(e -> new CreateTurnierFrame(dataModel).setVisible(true));
        editTurnier.addActionListener(e ->  new EditTurnierFrame(dataModel).setVisible(true));
        deleteTurnier.addActionListener(e -> new DeleteTurnierFrame(dataModel).setVisible(true));
        playTurnier.addActionListener(e -> new PlayTurnierFrame(dataModel).setVisible(true));

        return turnierMenu;
    }


}
