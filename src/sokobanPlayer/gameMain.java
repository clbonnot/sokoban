package sokobanPlayer;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import static sokobanAdmin.Administrator.loadPilotSQLite;
import static sokobanPlayer.Board.board;
import static sokobanPlayer.Board.showBoard;
import static sokobanPlayer.Board.xSize;
import static sokobanPlayer.Board.ySize;

/**
 *
 * @author Clément Bonnot
 */
public class gameMain {

    /**
     * Méthode gérant le lancement et la partie
     *
     * @throws IOException
     */
    static void gameManag() throws IOException {
        //  Initialisation base de donnée
        System.out.println("Lancement de la partie...");
        String chemin = "C:\\Users\\clemc\\OneDrive\\Documents\\NetBeansProjects\\sokoban\\data\\bdBoard.sqlite3";
        String URL = "jdbc:sqlite:" + chemin;
        loadPilotSQLite();

        System.out.println("Avec quel plateau souhaitez-vous jouer ?");
        Scanner sc2 = new Scanner(System.in);
        String numPlateau = null;
        if (sc2.hasNextLine()) {
            numPlateau = sc2.nextLine();
        }

        try ( Connection connexion = DriverManager.getConnection(URL)) {
            initBoardBD(connexion, numPlateau);
            setNatureBD(connexion, numPlateau);
            //saveFile();
        } catch (SQLException ex) {
            System.err.println("* Base " + URL + " introuvable.");
        }

        showBoard();

        //  Vérification de la victoire
        while (!Board.win) {
            choiceDirection();
            Board.showBoard();
        }
        System.out.println("Gagné");
    }

    /**
     * Fonction qui initialise le plateau de jeu vide
     *
     * @param c la connexion à la base de donnée
     * @param choice le choix du plateau
     * @return le plateau de jeu
     * @throws SQLException
     */
    static Case[][] initBoardBD(Connection c, String choice) throws SQLException {
        {
            int nbLines = 0;
            int nbColumns = 0;
            Statement statement = c.createStatement();
            ResultSet resultats = statement.executeQuery("select * from BOARD WHERE id_board ='" + choice + "' ");
            while (resultats.next()) {
                nbLines = resultats.getInt("nb_lines");
                nbColumns = resultats.getInt("nb_columns");
            }
            xSize = nbColumns;
            ySize = nbLines;
            board = new Case[xSize][ySize];
            for (int x = 0; x < xSize; x++) {
                for (int y = 0; y < ySize; y++) {
                    board[x][y] = new Case(x, y);
                }
            }
            return board;
        }
    }

    /**
     * Méthode qui enregistre les natures des cases depuis la base de donnée
     *
     * @param c la connexion à la base de donnée
     * @param choice le choix du plateau
     * @throws IOException
     * @throws SQLException
     */
    public static void setNatureBD(Connection c, String choice) throws IOException, SQLException {
        Statement statement = c.createStatement();
        ResultSet resultats = statement.executeQuery("select * from LINES WHERE id_board ='" + choice + "' ");
        int y = 0;

        while (resultats.next()) {
            String line = resultats.getString("description");
            Board.setNature(line, y);
            y++;
        }

    }

    /**
     * Choix des directions
     */
    static void choiceDirection() {
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
     * Méthode qui permet d'enregistrer un plateau depuis un fichier
     *
     * @throws IOException
     */
    public static void setNatureFile() throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("C:\\Users\\clemc\\OneDrive\\Documents\\NetBeansProjects\\sokoban\\plateau.txt"));
        String line;
        int y = 0;
        while ((line = in.readLine()) != null) {
            Board.setNature(line, y);
            y++;
        }
        in.close();
    }

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
                Board.initBoardFile();
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
