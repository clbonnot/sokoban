package sokobanPlayer;
import java.util.Scanner;
import static sokobanAdmin.Administrator.menuChoice;

/**
 *
 * @author clbonnot
 */
public class Player {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        gameMain.showMainMenu();
        System.out.println("Que voulez-vous faire ?");
        Scanner sc = new Scanner(System.in);
        if (sc.hasNextLine()) {
            try {
                int choice = Integer.parseInt(sc.nextLine());
                switch (choice) {
                    case 1:
                        gameMain.startGame();
                        break;
                    case 2:
                        menuChoice();
                        break;
                    case 3:
                        System.exit(2);

                        break;
                    default:
                        System.out.println("Veuillez rentrer un chiffre valide");
                        Player.main(args);
                }

            } catch (Exception e) {
                System.out.println("Veuillez rentrer un chiffre correcte");
                Player.main(args);
            }
        }
    }
}
