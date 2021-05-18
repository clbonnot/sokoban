package sokobanPlayer;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author Clément Bonnot
 */
public class Board {

    // Largeur X du plateau
    public static int xSize;
    // Longueur Y du plateau
    public static int ySize;
    // Plateau de jeu
    public static Case[][] board;
    //Booléen de la victoire 
    public static boolean win = false;

    /**
     * Constructeur de la class Board
     *
     * @param xSize taille X du plateau
     * @param ySize taille Y du plateau
     */
    public Board(int xSize, int ySize) {
    }

    /**
     * Méthode qui affiche le plateau de jeu
     */
    public static void showBoard() {
        System.out.print("  ");
        for (int i = 0; i < xSize; i++) {
            System.out.print(" " + i);
        }
        System.out.println(" ");
        for (int y = 0; y < ySize; y++) {
            System.out.print(" " + y);
            for (int x = 0; x < xSize; x++) {
                System.out.print(" " + board[x][y].getNature().getSymbol());
            }
            System.out.println(" ");
        }
    }

    /**
     * Fonction qui met à jour la position du personnage sur le plateau lorsque
     * cela est possible
     *
     * @param direction la direction
     * @return le plateau de jeu
     */
    static Case[][] movePosition(String direction) {
        Case currentCase = takePlayer();
        int targetX = currentCase.getCaseX();
        int targetY = currentCase.getCaseY();
        switch (direction) {
            case "left":
                if (!outOfBoard(targetX - 1, targetY) && board[targetX - 1][targetY].getNature() != Nature.BOX && board[targetX - 1][targetY].getNature() != Nature.WALL && board[targetX - 1][targetY].getNature() != Nature.TARGET) {
                    board[targetX][targetY].setNature(Nature.VOID);
                    board[targetX - 1][targetY].setNature(Nature.PERSON);
                } else if (board[targetX - 1][targetY].getNature() == Nature.BOX && board[targetX - 2][targetY].getNature() == Nature.VOID || board[targetX - 2][targetY].getNature() == Nature.TARGET) {
                    if (board[targetX - 2][targetY].getNature() == Nature.TARGET) {
                        win = true;
                    }
                    board[targetX][targetY].setNature(Nature.VOID);
                    board[targetX - 1][targetY].setNature(Nature.PERSON);
                    board[targetX - 2][targetY].setNature(Nature.BOX);
                }
                break;
            case "right":
                if (!outOfBoard(targetX + 1, targetY) && board[targetX + 1][targetY].getNature() != Nature.BOX && board[targetX + 1][targetY].getNature() != Nature.WALL && board[targetX + 1][targetY].getNature() != Nature.TARGET) {
                    board[targetX][targetY].setNature(Nature.VOID);
                    board[targetX + 1][targetY].setNature(Nature.PERSON);
                } else if (board[targetX + 1][targetY].getNature() == Nature.BOX && board[targetX + 2][targetY].getNature() == Nature.VOID || board[targetX + 2][targetY].getNature() == Nature.TARGET) {
                    if (board[targetX + 2][targetY].getNature() == Nature.TARGET) {
                        win = true;
                    }
                    board[targetX][targetY].setNature(Nature.VOID);
                    board[targetX + 1][targetY].setNature(Nature.PERSON);
                    board[targetX + 2][targetY].setNature(Nature.BOX);
                }
                break;
            case "up":
                if (!outOfBoard(targetX, targetY - 1) && board[targetX][targetY - 1].getNature() != Nature.BOX && board[targetX][targetY - 1].getNature() != Nature.WALL && board[targetX][targetY - 1].getNature() != Nature.TARGET) {
                    board[targetX][targetY].setNature(Nature.VOID);
                    board[targetX][targetY - 1].setNature(Nature.PERSON);
                } else if (board[targetX][targetY - 1].getNature() == Nature.BOX && board[targetX][targetY - 2].getNature() == Nature.VOID || board[targetX][targetY - 2].getNature() == Nature.TARGET) {
                    if (board[targetX][targetY - 2].getNature() == Nature.TARGET) {
                        win = true;
                    }
                    board[targetX][targetY].setNature(Nature.VOID);
                    board[targetX][targetY - 1].setNature(Nature.PERSON);
                    board[targetX][targetY - 2].setNature(Nature.BOX);
                }
                break;
            case "down":
                if (!outOfBoard(targetX, targetY + 1) && board[targetX][targetY + 1].getNature() != Nature.BOX && board[targetX][targetY + 1].getNature() != Nature.WALL && board[targetX][targetY + 1].getNature() != Nature.TARGET) {
                    board[targetX][targetY].setNature(Nature.VOID);
                    board[targetX][targetY + 1].setNature(Nature.PERSON);
                } else if (board[targetX][targetY + 1].getNature() == Nature.BOX && board[targetX][targetY + 2].getNature() == Nature.VOID || board[targetX][targetY - 2].getNature() == Nature.TARGET) {
                    if (board[targetX][targetY + 2].getNature() == Nature.TARGET) {
                        win = true;
                    }
                    board[targetX][targetY].setNature(Nature.VOID);
                    board[targetX][targetY + 1].setNature(Nature.PERSON);
                    board[targetX][targetY + 2].setNature(Nature.BOX);
                }
                break;
            default:
                break;
        }
        return board;
    }

    /**
     * Fonction qui récupère la case sur laquelle se situe le joueur
     *
     * @return la case sur laquelle est le joueur
     */
    private static Case takePlayer() {
        for (int x = 0; x < xSize; x++) {
            for (int y = 0; y < ySize; y++) {
                if (board[x][y].getNature() == Nature.PERSON) {
                    Case c = new Case(x, y);
                    return c;
                }
            }
        }
        return null;
    }

    /**
     * Méthode qui ajoute des natures sur les cases.
     * @param line la ligne concernée 
     * @param y la colonne concernée
     * @throws IOException 
     */
    static void setNature(String line, int y) throws IOException {
        for (int x = 0; x < line.length(); x++) {
            char car = 0;
            car = line.charAt(x);
            switch (car) {
                case '.':
                    Board.setVoid(x, y);
                    break;
                case '#':
                    Board.setWall(x, y);
                    break;
                case 'X':
                    Board.setTarget(x, y);
                    break;
                case 'C':
                    Board.setBox(x, y);
                    break;
                case 'P':
                    Board.setPerson(x, y);
                    break;
                default:
                    break;
            }
        }
    }

    /**
     * Booléen qui vérifie qu'une Case est bien dans le plateau
     *
     * @param xTarget coordonnée X de la case
     * @param yTarget coordonnée Y de la case
     * @return vrai si la Case est hors du plateau, faux sinon
     */
    private static boolean outOfBoard(int xTarget, int yTarget) {
        return xTarget < 0 || xTarget > xSize - 1 || yTarget < 0 || yTarget > ySize - 1;
    }

    /**
     * Fonction qui ajoute une caisse sur le plateau de jeu
     *
     * @param line la ligne concernée
     * @param column la colonne concernée
     * @return le plateau de jeu
     */
    private static Case[][] setBox(int line, int column) {
        board[line][column].setNature(Nature.BOX);
        return board;
    }

    /**
     * Fonction qui rajoute une cible sur le plateau de jeu
     *
     * @param line la ligne concernée
     * @param column la colonne concernée
     * @return le plateau de jeu
     */
    private static Case[][] setTarget(int line, int column) {
        board[line][column].setNature(Nature.TARGET);
        return board;
    }

    /**
     * Fonction qui met une position de départ pour le joueur
     *
     * @param line la ligne concernée
     * @param column la colonne concernée
     * @return la plateau de jeu
     */
    private static Case[][] setPerson(int line, int column) {
        board[line][column].setNature(Nature.PERSON);
        return board;
    }

    /**
     * Fonction qui pose un mur
     *
     * @param line la ligne concernée
     * @param column la colonne concernée
     * @return le plateau de jeu
     */
    private static Case[][] setWall(int line, int column) {
        board[line][column].setNature(Nature.WALL);
        return board;
    }

    /**
     * Fonction qui met une case vide
     *
     * @param line la ligne
     * @param column la colonne
     * @return le plateau de jeu
     */
    private static Case[][] setVoid(int line, int column) {
        board[line][column].setNature(Nature.VOID);
        return board;
    }

    // FONCTIONS INUTILES DE LA PARTIE 1 : 
    /**
     * Fonction qui rajoute un mur horizontal
     *
     * @param line la ligne concernée
     * @param c1 première coordonnée Y
     * @param c2 deuxième coordonnée Y
     * @return le plateau de jeu
     */
    private static Case[][] addHorizontalWall(int line, int c1, int c2) {

        for (int y = c1; y < c2; y++) {
            board[y][line].setNature(Nature.WALL);
        }
        return board;
    }

    /**
     * Fonction qui rajoute un mur vertical
     *
     * @param column la colonne concernée
     * @param l1 la première coordonnée X
     * @param l2 la deuxième coordonnée X
     * @return le plateau de jeu
     */
    private static Case[][] addVerticalWall(int column, int l1, int l2) {
        for (int y = l1; y < l2; y++) {
            board[column][y].setNature(Nature.WALL);
        }
        return board;
    }

    /**
     * Fonction d'initialisation du plateau de jeu
     *
     * @return le plateau de jeu
     */
    static Case[][] initBoardFile() throws IOException {
        {
            BufferedReader in = new BufferedReader(new FileReader("C:\\Users\\clemc\\OneDrive\\Documents\\NetBeansProjects\\sokoban\\plateau.txt"));
            String line;
            int nbLine = 0;
            int nbColumn = 0;
            while ((line = in.readLine()) != null) {
                nbLine++;
                nbColumn = line.length();
            }
            xSize = nbColumn;
            ySize = nbLine;
            in.close();
        }
        board = new Case[xSize][ySize];
        for (int x = 0; x < xSize; x++) {
            for (int y = 0; y < ySize; y++) {
                board[x][y] = new Case(x, y);
            }
        }
        return board;
    }
}
