/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sokobanAdmin;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import java.sql.PreparedStatement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author clemc
 */
public class Administrator {

    static Scanner entree = new Scanner(System.in);

    public static void main(String[] args) {

        String chemin = "C:\\Users\\clemc\\OneDrive\\Documents\\NetBeansProjects\\sokoban\\data\\bdBoard.sqlite3";
        String URL = "jdbc:sqlite:" + chemin;
        chargerPiloteSQLite();
        try ( Connection connexion = DriverManager.getConnection(URL)) {
            preparerRequetes(connexion);
            gestionBD.menuChoice(connexion);
        } catch (SQLException ex) {
            System.err.println("* Base " + URL + " introuvable.");
        }
    }

    private static void chargerPiloteSQLite() {
        String sqlite_driver = "org.sqlite.JDBC";
        try {
            Class.forName(sqlite_driver);
        } catch (ClassNotFoundException ex) {
            System.err.println("* Driver " + sqlite_driver + " introuvable.");
            System.exit(1);
        }
    }

    private static void preparerRequetes(Connection c) {
        // Preparer une requete
        // "select * from livres where id = ?"
    }

    
}
