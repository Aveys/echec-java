package launcher;

import javax.swing.*;

/**
 * Created by lbl on 04/11/2015.
 */
public class MasterLauncher {

    public static void main(String[] args) {

        JFrame frame = new JFrame();

        Icon blueIcon = new ImageIcon("yourFile.gif");
        Object stringArray[] = { "On lan", "Local" };
        int result = JOptionPane.showOptionDialog(frame, "Quel mode voulez-vous jouer ?", "Option de jeu",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, blueIcon, stringArray, stringArray[0]);

        switch (result) {
            case -1:
                System.out.println("Fermeture du jeu, aucun choix de mode n'a été sélectionné");
                break;
            case 0:
                System.out.println("On lan");

                LauncherGUINetwork launcherNet = new LauncherGUINetwork();
                break;
            case 1:
                System.out.println("Local");
                LauncherGUI launcherLocal = new LauncherGUI();
        }
    }
}
