/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sokobanAdmin;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import sokobanPlayer.Board;
import static sokobanPlayer.Board.board;

/**
 *
 * @author clemc
 */
public class gestionBD {

    static void menuChoice(Connection c) throws SQLException {
        boolean encore = true;
        while (encore) {
            System.out.println(" ");
            System.out.println("Menu : ");
            System.out.println("1. Création base de données");
            System.out.println("2. Lister les plateaux de jeu");
            System.out.println("3. Afficher les plateaux de jeu");
            System.out.println("4. Ajouter un plateau de jeu depuis un fichier");
            System.out.println("5. Retirer un plateau de jeu de la base de donnée [DANGEROUS]");
            System.out.println("6. Quit.");
            System.out.println("Que voulez-vous faire ?");

            Scanner sc = new Scanner(System.in);

            if (sc.hasNextLine()) {
                try {
                    int choice = Integer.parseInt(sc.nextLine());
                    switch (choice) {
                        case 1:
                            createBase(c);
                            break;
                        case 2:
                            listBoardBD(c);
                            break;
                        case 3:
                            System.out.println("3. Show board TODO");
                            break;
                        case 4:
                            addBoardFromFile(c);
                            break;
                        case 5:
                            System.out.println("5. Remove board from database [DANGEROUS] TODO");
                            break;
                        case 6:
                            System.out.println("STOP");
                            System.exit(1);
                            break;
                        default:
                            System.out.println("Veuillez rentrer un chiffre correcte");
                    }
                } catch (Exception e) {
                    System.out.println("Veuillez rentrer un chiffre correcte");

                }
            }
        }
    }

    private static void createBase(Connection c) {
        {
            try {
                Statement s = c.createStatement();
                s.execute("drop table if exists BOARD ");
                s.execute("create table BOARD "
                        + "(id_board text , board_name text , nb_rows integer, nb_cols integer)");
                s.execute("drop table if exists ROWS ");
                s.execute("create table ROWS "
                        + "(id_board text , rows_num integer , description text)");
            } catch (SQLException ex) {
                System.err.println("* Exception " + ex.getMessage());
            }
        }
    }

    private static void listBoardBD(Connection c) throws SQLException {
        System.out.println("Liste des plateaux :");
        Statement statement = c.createStatement();
        ResultSet resultats = statement.executeQuery("select * from board");
        while (resultats.next()) {
            String id = resultats.getString("id_board");
            String titre = resultats.getString("board_name");
            System.out.println("ID : "+id+ " |  Nom : "+titre+"");
        }
    }

    private static void addBoardFromFile(Connection c) throws IOException, SQLException {
        System.out.println("Vous allez ajouter un nouveau plateau de jeu :");
        System.out.println("Quel identifiant pour votre plateau ?");
        Scanner sc = new Scanner(System.in);
        String idAdd = null;
        String nameAdd = null;

        if (sc.hasNextLine()) {
            idAdd = sc.nextLine();
        }
        System.out.println("Quel nom pour votre plateau de jeu ?");
        Scanner sc2 = new Scanner(System.in);
        if (sc2.hasNextLine()) {
            nameAdd = sc2.nextLine();
        }
        Statement s = c.createStatement();
        s.executeUpdate("insert into BOARD "
                + "values ('" + idAdd + "','" + nameAdd + "', 10 , 6)");
        System.out.println("Plateau crée");
        //System.out.println(nameAdd);
        /*BufferedReader in = new BufferedReader(new FileReader("C:\\Users\\clemc\\OneDrive\\Documents\\NetBeansProjects\\sokoban\\plateau.txt"));
        String line;
        while ((line = in.readLine()) != null) {


        }

        in.close();  */
    }

}
