import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;

/**
 * @Author :  Matin Saharkhiz
 * @version : 1.0
 */

public class Bishop extends Piece{

    public String color;
    public boolean[][] PossibleMoves  ;


    public boolean[][] getPossibleMoves() {
        return PossibleMoves;
    }

    public Bishop(String color){
        super();
        this.color = color;
        if (color.equals("white")) {
            try {
                Image img = ImageIO.read(getClass().getResource("pieces/wbi.png"));
                this.setIcon(new ImageIcon(img));
            } catch (Exception ex) {
                System.out.println(ex);
            }
        } else {
            try {
                Image img = ImageIO.read(getClass().getResource("pieces/bbi.png"));
                this.setIcon(new ImageIcon(img));
            } catch (Exception ex) {
                System.out.println(ex);
            }
        }
    }

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
                boolean condition = true;
                if(currentRow == i || currentCol == j){
                    //Did not move diagonally
                    condition = false;
                    continue;
                }

                if(Math.abs(i - currentRow) != Math.abs(j - currentCol)){
                    condition = false;
                    continue;
                }

                int rowOffset, colOffset;

                if(currentRow < i){
                    rowOffset = 1;
                }else{
                    rowOffset = -1;
                }

                if(currentCol < j){
                    colOffset = 1;
                }else{
                    colOffset = -1;
                }

                int y = currentCol + colOffset;
                for(int x = currentRow + rowOffset; x != newRow; x += rowOffset){

                    if(board[x][y] != null){
                        condition = false;
                        break;
                    }

                    y += colOffset;
                }

                PossibleMoves[i][j] = condition;

            }
        return PossibleMoves[newRow][newCol];
    }

    public String getColor(){
        return this.color;
    }
    /**
     * returns B for Bishop as charachter
     * @return char
     */
    public String toString(){
        return color.charAt(0) + "B";

    }

}