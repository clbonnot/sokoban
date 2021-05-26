/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sokobanPlayer;

import java.util.HashSet;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import static sokobanPlayer.Board.board;
import static sokobanPlayer.Board.xSize;
import static sokobanPlayer.Board.ySize;

/**
 *
 * @author clemc
 */
public class BoardTest {

    public BoardTest() {
    }

    @Test
    public void natureTest() {
        int xSize = 5;
        int ySize = 5;
        board = new Case[xSize][ySize];
        for (int x = 0; x < xSize; x++) {
            for (int y = 0; y < ySize; y++) {
                board[x][y] = new Case(x, y);
            }
        }
        board[1][2].setNature(Nature.VOID);
        assertEquals(board[1][2].getNature(), Nature.VOID);
        board[1][2].setNature(Nature.PERSON);
        assertEquals(board[1][2].getNature(), Nature.PERSON);
        board[3][2].setNature(Nature.PERSON);
        assertFalse(board[3][2].getNature() == Nature.TARGET);
        Board.setBox(1, 1);
        assertTrue(board[1][1].getNature() == Nature.BOX);
        Board.setWall(1, 1);
        assertTrue(board[1][1].getNature() == Nature.WALL);
        Board.addHorizontalWall(2, 1, 3);
        assertTrue(board[1][2].getNature() == Nature.WALL);
        assertTrue(board[2][2].getNature() == Nature.WALL);
        assertTrue(board[2][2].getNature() == Nature.WALL);
        assertFalse(board[4][2].getNature() == Nature.WALL);
        assertFalse(board[0][2].getNature() == Nature.WALL);
    }

}
