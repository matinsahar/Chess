import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;

/**
 * @Author :  Matin Saharkhiz
 * @version : 1.0
 */


public class Knight extends Piece {

    public String color;

    public Knight(String color) {
        super();
        this.color = color;
        if (color.equals("white")) {
            try {
                Image img = ImageIO.read(getClass().getResource("pieces/wkn.png"));
                this.setIcon(new ImageIcon(img));
            } catch (Exception ex) {
                System.out.println(ex);
            }
        } else {
            try {
                Image img = ImageIO.read(getClass().getResource("pieces/bkn.png"));
                this.setIcon(new ImageIcon(img));
            } catch (Exception ex) {
                System.out.println(ex);
            }
        }

    }



    public boolean[][] getPossibleMoves(Piece [][] board , int currentRow , int currentCol) {
        for (int i = 0; i < 8; i++)
            for (int j = 0; j < 8; j++) {
                if(Math.abs(i - currentRow) == 2 && Math.abs(j - currentCol) == 1){
                    PossibleMoves[i][j] = true;
                }

                if(Math.abs(i - currentRow) == 1 && Math.abs(j - currentCol) == 2){
                    PossibleMoves[i][j] = true;
                }

            }
        return PossibleMoves;
    }

    @Override
    /**
     * checks if the move is valid or not
     * @param board
     * @param currentRow
     * @param currentCol
     * @param newRow
     * @param newCol
     * @return
     */
    public boolean validateMove(Piece[][] board, int currentRow, int currentCol, int newRow, int newCol) {
        for (int i = 0; i < 8; i++)
            for (int j = 0; j < 8; j++) {
                if(Math.abs(i - currentRow) == 2 && Math.abs(j - currentCol) == 1){
                   PossibleMoves[i][j] = true;
                }

                if(Math.abs(i - currentRow) == 1 && Math.abs(j - currentCol) == 2){
                    PossibleMoves[i][j] = true;
                }

            }
            return PossibleMoves[newRow][newCol];
    }

    /**
     *
     * @return color
     */
    public String getColor(){
        return this.color;
    }

    /**
     * returns N for Knight as charachter
     * @return char
     */
    public String toString(){
        return color.charAt(0) + "N";
    }

}