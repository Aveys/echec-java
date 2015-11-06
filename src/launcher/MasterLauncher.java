package launcher;

import javax.swing.*;

/**
 * Main du jeu, permet de lancer soit le mode local, soit le mode on lan
 */
public class MasterLauncher {

    public static void main(String[] args) {

        JFrame frame = new JFrame();

        Object stringArray[] = { "On lan", "Local" };
        int result = JOptionPane.showOptionDialog(frame, "Quel mode voulez-vous jouer ?", "Option de jeu",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, stringArray, stringArray[0]);

        switch (result) {
            case -1:
                System.out.println("Fermeture du jeu, aucun choix de mode n'a été sélectionné");
                break;
            case 0:
                System.out.println("On lan");

                new LauncherGUINetwork();
                break;
            case 1:
                System.out.println("Local");
                new LauncherGUI();
        }
    }
}
