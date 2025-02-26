package View;

import javax.swing.*;
import Model.DataModel;

public class PlayerMenu extends JPanel {
    private final DataModel dataModel;

    public PlayerMenu(DataModel dataModel) {
        this.dataModel = dataModel;
    }

    public JMenu getMenu() {
        JMenu playerMenu = new JMenu("Player");
        JMenuItem createPlayer = new JMenuItem("Player erstellen");
        JMenuItem editPlayer = new JMenuItem("Player editieren");
        JMenuItem deletePlayer = new JMenuItem("Player löschen");

        playerMenu.add(createPlayer);
        playerMenu.add(editPlayer);
        playerMenu.add(deletePlayer);

        createPlayer.addActionListener(e -> new CreatePlayerWindow(dataModel).setVisible(true));
        editPlayer.addActionListener(e -> new EditPlayerWindow(dataModel).setVisible(true));
        deletePlayer.addActionListener(e -> new DeletePlayerWindow(dataModel).setVisible(true));

        return playerMenu;
    }
}