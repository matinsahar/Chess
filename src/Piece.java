/**
 * @Author :  Matin Saharkhiz
 * @version : 1.0
 */

import javax.swing.*;

public abstract class Piece extends JButton{
    public String color;
    public boolean hasMoved;
    public boolean[][] PossibleMoves = new boolean[8][8];
    public Piece(){
        for (int i = 0; i < 8; i++)
            for (int j = 0; j < 8; j++) {
                PossibleMoves[i][j] = false;
            }
    }



     // Gets the color

    public boolean[][] getPossibleMoves(Piece [][] board , int currentrow , int currentcol) {
        return PossibleMoves;
    }

    public abstract String getColor();

  // move piece
    public abstract boolean validateMove(Piece[][] board, int currentRow, int currentCol, int newRow, int newCol);
}

