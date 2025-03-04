package View;

import Model.DataModel;

import javax.swing.*;

public class ClubMenu extends JPanel {
    private DataModel dataModel;

    public ClubMenu(DataModel dataModel) {
        this.dataModel = dataModel;
    }

    public JMenu getMenu() {
        JMenu clubMenu = new JMenu("Club");
        JMenuItem createClub = new JMenuItem("Club erstellen");
        JMenuItem editClub = new JMenuItem("Club editieren");
        JMenuItem deleteClub = new JMenuItem("Club löschen");

        clubMenu.add(createClub);
        clubMenu.add(editClub);
        clubMenu.add(deleteClub);

        createClub.addActionListener(e -> new CreateClubFrame(dataModel).setVisible(true));
        editClub.addActionListener(e ->  new EditClubFrame(dataModel).setVisible(true));
        deleteClub.addActionListener(e -> new DeleteClubFrame(dataModel).setVisible(true));

        return clubMenu;
    }

}
