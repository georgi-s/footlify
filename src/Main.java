import Model.DataModel;
import View.MainFrame;
import com.formdev.flatlaf.FlatDarkLaf;
import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        try {
            System.setProperty("flatlaf.useWindowDecorations", "True");
            System.setProperty("flatlaf.menuBarEmbedded", "True");
        FlatDarkLaf.setup();

        SwingUtilities.invokeLater(() -> {
            DataModel dataModel = new DataModel();
            MainFrame mainFrame = new MainFrame(dataModel);
            mainFrame.setVisible(true);
        });
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(null, "An error occurred while initializing the application. Please try again later.", "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }
}


