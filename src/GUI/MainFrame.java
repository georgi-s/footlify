package GUI;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    private ClubPanel clubPanel;
    private TurnierPanel turnierPanel;
    private PlayerPanel playerPanel;

    public MainFrame() {
        setTitle("Club Selector");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 400);
        setLayout(new BorderLayout());

        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        clubPanel = new ClubPanel();
        turnierPanel = new TurnierPanel();
        playerPanel = new PlayerPanel();

        menuBar.add(clubPanel.getMenu());
        menuBar.add(turnierPanel.getMenu());
        menuBar.add(playerPanel.getMenu());

        add(clubPanel, BorderLayout.CENTER);
    }
}
