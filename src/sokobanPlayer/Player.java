package sokobanPlayer;

import java.io.IOException;
import java.util.Scanner;
import sokobanAdmin.Administrator;

/**
 *
 * @author Clément Bonnot
 */
public class Player {

    public static void main(String[] args) throws IOException {

        System.out.println("1) Play");
        System.out.println("2) Edit");
        System.out.println("3) Exit");
        System.out.println("Que voulez-vous faire ?");

        Scanner sc = new Scanner(System.in);
        try {
            if (sc.hasNextLine()) {

                int choice = Integer.parseInt(sc.nextLine());
                switch (choice) {
                    case 1:
                        gameMain.gameManag();
                        break;
                    case 2:
                        Administrator.main(args);
                        break;
                    case 3:
                        System.exit(2);
                        break;
                    default:
                        System.out.println("Chiffre valide demandé");
                        Player.main(args);
                }
            }
        } catch (Exception e) {
            System.out.println("Chiffre demandé");
            Player.main(args);

        }
    }
}
