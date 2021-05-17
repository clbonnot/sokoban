/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sokobanPlayer;

import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author clemc
 */
public class gameMain {

    /**
     * Méthode d'affichage du menu principal
     */

    /**
     * Méthode gérant le lancement et la partie
     *
     * @throws IOException
     */
    static void startGame() throws IOException {
        System.out.println("Lancement de la partie...");
        Board.initBoard();
        readFichier.saveBoard();
        Board.showBoard();
        while (!Board.win) {
            choiceDirection();
            Board.showBoard();
        }
        System.out.println("Gagné");
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
                for (int i = 0; i < value.length(); i++) {
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
    
    
    
    // FONCTIONS INUTILES DE LA PARTIE 1 : 
    
    /**
     * Choix de la taille du plateau par le joueur : Ne fonctionne plus
     * (Height/Width n'existent plus depuis que l'on a fait le fichier)
     */
    static void choiceXYBoard() {
        System.out.println("Quelle longueur de plateau souhaitez-vous ?");
        Scanner sc = new Scanner(System.in);
        int WIDTH = 0;
        if (sc.hasNextLine()) {
            try {
                int count = Integer.parseInt(sc.nextLine());
                WIDTH = count;
                if (count > 11) {
                    System.out.println("Vous ne pouvez pas choisir plus de 10 cases.");
                    choiceXYBoard();
                }
            } catch (Exception e) {
                System.out.println("Veuillez rentrer un chiffre correcte");
                choiceXYBoard();
            }
        }
        System.out.println("Quelle largeur de plateau souhaitez-vous ?");
        Scanner sc2 = new Scanner(System.in);
        if (sc2.hasNextLine()) {
            try {
                int count2 = Integer.parseInt(sc2.nextLine());
                int HEIGHT = count2;
                Board.initBoard();
                if (count2 > 11) {
                    System.out.println("Vous ne pouvez pas choisir plus de 10 cases.");
                    choiceXYBoard();
                }
            } catch (Exception e) {
                System.out.println("Veuillez rentrer un chiffre correcte");
                choiceXYBoard();
            }
        }
    }

}
