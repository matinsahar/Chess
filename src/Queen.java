import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;

/**
 * @Author :  Matin Saharkhiz
 * @version : 1.0
 */

public class Queen extends Piece{

    public String color;

    public boolean[][] getPossibleMoves() {
        return PossibleMoves;
    }

    public boolean[][] PossibleMoves  ;

    public Queen(String color){
        super();
        this.color = color;
        if (color.equals("white")) {
            try {
                Image img = ImageIO.read(getClass().getResource("pieces/wqu.png"));
                this.setIcon(new ImageIcon(img));
            } catch (Exception ex) {
                System.out.println(ex);
            }
        } else {
            try {
                Image img = ImageIO.read(getClass().getResource("pieces/bqu.png"));
                this.setIcon(new ImageIcon(img));
            } catch (Exception ex) {
                System.out.println(ex);
            }
        }
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
        //A Queen's move is the same as a Rook's or a Bishop's
        Rook r=new Rook(color);
        Bishop b = new Bishop(color);
        boolean ans = r.validateMove(board, currentRow, currentCol, newRow, newCol) || b.validateMove(board, currentRow, currentCol, newRow, newCol);
        for (int i = 0; i < 8; i++)
            for (int j = 0; j < 8; j++) {
                PossibleMoves [i][j] = r.PossibleMoves[i][j] || b.PossibleMoves[i][j];
            }
        return ans;
    }

    public String getColor(){
        return this.color;
    }

    public String toString(){
        return color.charAt(0) + "Q";

    }

}