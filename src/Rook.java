import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;

/**
 * @Author :  Matin Saharkhiz
 * @version : 1.0
 */

public class Rook extends Piece{

    public String color;


    public boolean[][] getPossibleMoves() {
        return PossibleMoves;
    }

    public Rook(String color) {
        super();

        this.color = color;
        if(color.equals("white")){
            try {
                Image img = ImageIO.read(getClass().getResource("pieces/wro.png"));
                this.setIcon(new ImageIcon(img));
            } catch (Exception ex) {
                System.out.println(ex);
            }
        }
        else {
            try {
                Image img = ImageIO.read(getClass().getResource("pieces/bro.png"));
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
                if(currentRow != i && currentCol != j){
                    //Did not move along one rank/file
                    condition = false;
                    continue;
                }

                //First I will assumed the Rook is moving along the rows.
                int offset;

                if(currentRow != newRow){
                    if(currentRow < newRow){
                        offset = 1;
                    }else{
                        offset = -1;
                    }

                    for(int x = currentRow + offset; x != newRow; x += offset){
                        //Go from currentRow to newRow, and check every space
                        if(board[x][currentCol] != null){
                            condition = false;
                            break;
                        }
                    }
                }

                //Now do the same for columns
                if(currentCol != newCol){
                    if(currentCol < newCol){
                        offset = 1;
                    }else{
                        offset = -1;
                    }

                    for(int x = currentCol + offset; x != newCol; x += offset){
                        //Go from currentCol to newCol, and check every space
                        if(board[currentRow][x] != null){
                            condition = false;
                            break;
                        }
                    }
                }

                    PossibleMoves[i][j] = condition;


            }
        return PossibleMoves[newRow][newCol];

    }

    public String getColor(){
        return this.color;
    }

    public String toString(){
        return color.charAt(0) + "R";

    }

}