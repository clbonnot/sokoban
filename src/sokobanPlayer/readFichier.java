/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sokobanPlayer;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import static sokobanPlayer.Board.board;

/**
 *
 * @author clemc
 */
public class readFichier {

    public readFichier() {
    }

    /**
     * Méthode qui crée le plateau de jeu depuis un fichier.txt
     * @throws IOException
     */
    public static void saveBoard() throws IOException {
        BufferedReader in = new BufferedReader(new FileReader("C:\\Users\\clemc\\OneDrive\\Documents\\NetBeansProjects\\sokoban\\plateau.txt"));
        String line;

        //  ligne
        int y = 0;
        char car = 0;
        while ((line = in.readLine()) != null) {
            //  On parcourt les colonnes
            for (int x = 0; x < line.length(); x++) {
                car = line.charAt(x);
                switch (car) {
                    case '.':
                        board[x][y].setNature(Nature.VOID);
                        break;
                    case '#':
                        board[x][y].setNature(Nature.WALL);
                        break;
                    case 'X':
                        board[x][y].setNature(Nature.TARGET);
                        break;
                    case 'C':
                        board[x][y].setNature(Nature.BOX);
                        break;
                    case 'P':
                        board[x][y].setNature(Nature.PERSON);
                        break;
                    default:
                        break;
                }
            }
            y++;
        }
        in.close();
    }
}
