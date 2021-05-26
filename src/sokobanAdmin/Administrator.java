package sokobanAdmin;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Clément Bonnot
 */
public class Administrator {

    public static void main(String[] args) throws IOException {
        // Initialisation de la base de donnée
        String chemin = "C:\\Users\\clemc\\OneDrive\\Documents\\NetBeansProjects\\sokoban\\data\\bdBoard.sqlite3";
        String URL = "jdbc:sqlite:" + chemin;
        loadPilotSQLite();
        try ( Connection connexion = DriverManager.getConnection(URL)) {
            managementBD.menuChoice(connexion);
        } catch (SQLException ex) {
            System.err.println("* Base " + URL + " introuvable.");
        }
    }

    /**
     * Méthode de lancement du pilote SQLite
     */
    public static void loadPilotSQLite() {
        String sqlite_driver = "org.sqlite.JDBC";
        try {
            Class.forName(sqlite_driver);
        } catch (ClassNotFoundException ex) {
            System.err.println("* Driver " + sqlite_driver + " introuvable.");
            System.exit(1);
        }
    }
}
