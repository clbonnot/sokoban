/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sokobanAdmin;

import java.util.Scanner;
import sokobanPlayer.Board;

/**
 *
 * @author clemc
 */
public class Administrator {

    public static void main(String[] args) {
        menuChoice();
        System.out.println("coucou");
    }

    public static void editMenu() {
        System.out.println(" ");
        System.out.println("Menu : ");
        System.out.println("1. Create new database");
        System.out.println("2. List board");
        System.out.println("3. Show board");
        System.out.println("4. Add board from file");
        System.out.println("5. Remove board from database [DANGEROUS]");
        System.out.println("6. Quit.");
    }

    public static void menuChoice() {
        editMenu();
        System.out.println("Que voulez-vous faire ?");

        Scanner sc = new Scanner(System.in);

        if (sc.hasNextLine()) {
            try {
                int choice = Integer.parseInt(sc.nextLine());
                switch (choice) {
                    case 1:
                        System.out.println("1. Create new database TODO");
                        break;
                    case 2:
                        System.out.println("2. List board TODO");
                        break;
                    case 3:
                        System.out.println("3. Show board");
                        break;
                    case 4:
                        System.out.println("4. Add board from file");
                        break;
                    case 5:
                        System.out.println("5. Remove board from database [DANGEROUS]");
                        break;
                    case 6:
                        System.out.println("STOP");
                        System.exit(1);
                        break;
                    default:
                        System.out.println("Veuillez rentrer un chiffre correcte");
                        menuChoice();
                }

            } catch (Exception e) {
                System.out.println("Veuillez rentrer un chiffre correcte");
                menuChoice();
            }
        }
    }

}
