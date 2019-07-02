import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;

public class King extends Piece{

    public String color;
    public boolean hasMoved;

    public boolean[][] getPossibleMoves() {
        return PossibleMoves;
    }

    public boolean castled;
    public boolean[][] PossibleMoves  ;
    public King(String color){
        super();
        this.color = color;
        this.hasMoved = false;
        this.castled = false;
        if (color.equals("white")) {
            try {
                Image img = ImageIO.read(getClass().getResource("pieces/wki.png"));
                this.setIcon(new ImageIcon(img));
            } catch (Exception ex) {
                System.out.println(ex);
            }
        } else {
            try {
                Image img = ImageIO.read(getClass().getResource("pieces/bki.png"));
                this.setIcon(new ImageIcon(img));
            } catch (Exception ex) {
                System.out.println(ex);
            }
        }
    }


    public boolean validateMove(Piece[][] board, int currentRow, int currentCol, int newRow, int newCol) {
        for (int i = 0; i < 8; i++)
            for (int j = 0; j < 8; j++) {
                if(Math.abs(i - currentRow) > 1 || Math.abs(j - currentCol) > 1){

                    if(hasMoved){
                        continue;
                    }

                    //Do castling logic here
                    if(j - currentCol == 2 && currentRow == i){
                        //Castle kingside
                        if(board[i][currentCol + 1] != null || board[i][currentCol + 2] != null){
                            castled = false;
                            continue;
                        }

                    }else if(currentCol - j == 3 && currentRow == i){
                        if(board[i][currentCol - 1] != null || board[i][currentCol - 2] != null || board[i][currentCol - 3] != null){
                            castled = false;
                            continue;
                        }

                    }else{
                        castled = false;
                        continue;
                    }

                    castled = true;

                }
                PossibleMoves[i][j] = true;
            }
        return PossibleMoves[newCol][newRow];
    }

    public String getColor(){
        return this.color;
    }

    public String toString(){
        return color.charAt(0) + "K";
    }

}