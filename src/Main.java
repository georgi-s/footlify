import Model.DataModel;
import View.MainFrame;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            DataModel dataModel = new DataModel();
            MainFrame mainFrame = new MainFrame(dataModel);
            mainFrame.setVisible(true);
        });
    }
}