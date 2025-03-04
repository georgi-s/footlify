package View;

import javax.swing.*;
import java.awt.*;
import Model.DataModel;

public class MainFrame extends JFrame {
    private ClubPanel clubPanel;
    private ClubMenu clubMenu;
    private TurnierMenu turnierMenu;
    private PlayerMenu playerMenu;
    private DataModel dataModel;

    public MainFrame(DataModel dataModel) {
        setTitle("Club Selector");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 400);
        setLayout(new BorderLayout());

        this.dataModel = dataModel;

        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        clubMenu = new ClubMenu(this.dataModel);
        turnierMenu = new TurnierMenu(this.dataModel);
        playerMenu = new PlayerMenu(this.dataModel);

        clubPanel = new ClubPanel(this.dataModel);

        menuBar.add(clubMenu.getMenu());
        menuBar.add(turnierMenu.getMenu());
        menuBar.add(playerMenu.getMenu());

        add(clubPanel, BorderLayout.CENTER);
    }
}
