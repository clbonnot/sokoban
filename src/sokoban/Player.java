package sokoban;

import java.util.Scanner;

/**
 *
 * @author clbonnot
 */
public class Player {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //  INITIALISATION DU PLATEAU DE JEU
        System.out.println("Lancement...");
        Board.initBoard(10, 6);
        Board.addHorizontalWall(0, 1, 7);
        Board.addVerticalWall(0,0,6);
        Board.addVerticalWall(9,1,6);
        Board.addBox(4, 4);
        Board.addTarget(2, 4);
        Board.setPosition(5,4);
        Board.showBoard();
        
        while(!Board.win) {
            choiceDirection();
            Board.showBoard();
        }
        System.out.println("Gagné");
    }

    /**
     * Choix de la taille du plateau par le joueur
     */
    private static void initGame() {
        System.out.println("Quelle longueur de plateau souhaitez-vous ?");
        Scanner sc = new Scanner(System.in);
        int WIDTH = 0;
        if (sc.hasNextLine()) {
            try {
                int count = Integer.parseInt(sc.nextLine());
                WIDTH = count;
                if (count > 11) {
                    System.out.println("Vous ne pouvez pas choisir plus de 10 cases.");
                    initGame();
                }
            } catch (Exception e) {
                System.out.println("Veuillez rentrer un chiffre correcte");
                initGame();
            }
        }
        System.out.println("Quelle largeur de plateau souhaitez-vous ?");
        Scanner sc2 = new Scanner(System.in);
        if (sc2.hasNextLine()) {
            try {
                int count2 = Integer.parseInt(sc2.nextLine());
                int HEIGHT = count2;
                Board.initBoard(WIDTH, HEIGHT);
                if (count2 > 11) {
                    System.out.println("Vous ne pouvez pas choisir plus de 10 cases.");
                    initGame();
                }
            } catch (Exception e) {
                System.out.println("Veuillez rentrer un chiffre correcte");
                initGame();
            }
        }
    }

    /**
     * Choix des directions
     */
    private static void choiceDirection() {
        System.out.println("Tapez une direction :");
        Scanner sc2 = new Scanner(System.in);

        if (sc2.hasNextLine()) {
            try {
                String value = sc2.nextLine();
                for (int i = 0; i < 100; i++) {
                    char letter = value.charAt(i);

                    switch (letter) {
                        case 'L':
                            Board.movePosition("left");
                            break;
                        case 'R':
                            Board.movePosition("right");
                            break;
                        case 'U':
                            Board.movePosition("up");
                            break;
                        case 'D':
                            Board.movePosition("down");
                            break;
                        default:
                            System.out.println("Le caractère " + letter + " n'a pas été traité, veuillez entrer une autre commande (Exemple : L, R, D, U):");
                    }
                }
            } catch (Exception e) {
                //System.out.println("Veuillez rentrer un chiffre correcte");
            }
        }
    }
}
