
import sportverein.Mannschaft;
import sportverein.Spieler;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

public class Main {
    private ArrayList<sportverein.Spieler> spieler;
    private ArrayList<sportverein.Mannschaft> clubs;
    private sportverein.Turnier turnier;
    private ArrayList<String> clubNames;

    public static void main(String[] args) {

                
        SwingUtilities.invokeLater(() -> {



            JFrame frame = new JFrame("Basic GUI");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(400, 300);

            JMenuBar menuBar = new JMenuBar();
            JMenu clubMenu = new JMenu("Mannschaft");
            JMenu playerMenu = new JMenu("Spieler");
            JMenu turnamentMenu = new JMenu("Turnier");

            JMenuItem createClub = new JMenuItem("Erstellen");
            JMenuItem editCLub = new JMenuItem("Bearbeiten");
            JMenuItem deleteClub = new JMenuItem("Löschen");

            clubMenu.add(createClub);
            clubMenu.add(editCLub);
            clubMenu.addSeparator();
            clubMenu.add(deleteClub);

            menuBar.add(clubMenu);
            menuBar.add(playerMenu);
            menuBar.add(turnamentMenu);

            frame.setJMenuBar(menuBar);

            JPanel panel = new JPanel();
            panel.setLayout(new FlowLayout());

            frame.add(panel);
            frame.setVisible(true);
        });
    }

    private sportverein.Mannschaft mannschaftErstellen()
    {
        sportverein.Mannschaft test = null;
        return test;
    }

    private void mannschaftBearbeiten(sportverein.Mannschaft team)
    {

    }
    private void deleteClub(int clubId){
        for(int i = 0; i < clubs.size(); i++){
            if(clubs.get(i).ClubId == clubId){
                clubs.remove(i);
                break;
            }
        }
    }
    private Spieler createPlayer(){
        Spieler test = null;
        return test;
    }
    private void deletePlayer(int spielerId){
        for(int i = 0; i < spieler.size(); i++){
            if(spieler.get(i).getPlayerId() == spielerId){
                spieler.remove(i);
                break;
            }
        }
    }
    private void deleteTurnier(){
        turnier = null;
    }
}