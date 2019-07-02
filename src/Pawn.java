import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;

/**
 * @Author :  Matin Saharkhiz
 * @version : 1.0
 */
public class Pawn extends Piece {

    public String color;
    private boolean hasMoved;



    public Pawn(String color) {
        super();
        this.color = color;
        this.hasMoved = false;
        if (color.equals("white")) {
            try {
                Image img = ImageIO.read(getClass().getResource("pieces/wpa.png"));
                this.setIcon(new ImageIcon(img));
            } catch (Exception ex) {
                System.out.println(ex);
            }
        } else {
            try {
                Image img = ImageIO.read(getClass().getResource("pieces/bpa.png"));
                this.setIcon(new ImageIcon(img));
            } catch (Exception ex) {
                System.out.println(ex);
            }
        }
    }

    /**
     * checks if the move is valid or not
     * @param board array of board positions
     * @param currentRow
     * @param currentCol
     * @param newRow next move's row
     * @param newCol next move's colomn
     * @return true if valid false if not
     */
    public boolean validateMove(Piece[][] board, int currentRow, int currentCol, int newRow, int newCol) {
        for (int i = 0; i < 8; i++)
            for (int j = 0; j < 8; j++) {
                if (color.equals("white")) {
                    if (i > newRow) {
                        continue;
                    }
                } else {
                    if (i > currentRow) {
                        continue;
                    }
                }

                if (currentCol == j) {
                    //Not taking a piece
                    if (color.equals("white")) {
                        if (board[currentRow + 1][currentCol] != null) {
                            continue;
                        }
                    } else {
                        if (board[currentRow - 1][currentCol] != null) {
                            continue;
                        }
                    }

                    if (Math.abs(i - currentRow) > 2) {
                        break;
                    } else if (Math.abs(i - currentRow) == 2) {
                        //Advancing two spaces at beginning
                        if (hasMoved) {
                            continue;
                        }

                        if (color.equals("white")) {
                            if (board[currentRow + 2][currentCol] != null) {
                                continue;
                            }
                        } else {
                            if (board[currentRow - 2][currentCol] != null) {
                                continue;
                            }
                        }

                    }
                } else {
                    //Taking a piece
                    if (Math.abs(j - currentCol) != 1 || Math.abs(i - currentRow) != 1) {
                        continue;
                    }

                    if (board[i][j] == null) {
                        continue;
                    }
                }


                if (!hasMoved) {
                    hasMoved = true;
                }

                PossibleMoves [i][j] = true;
            }
        return PossibleMoves[newRow][newCol];
    }

    public boolean [][] getPossibleMoves(Piece[][] board, int currentRow, int currentCol) {
        for (int i = 0; i < 8; i++)
            for (int j = 0; j < 8; j++) {
                if (color.equals("white")) {
                    if (i > currentRow) {
                        continue;
                    }
                }
                else {
                    if (i < currentRow) {
                        continue;
                    }
                }


                if (currentCol == j) {
                    //Not taking a piece
                    if (color.equals("white")) {
                        if (board[currentRow + 1][currentCol] != null) {
                            continue;
                        }
                    } else {
                        if (board[currentRow - 1][currentCol] != null) {
                            continue;
                        }
                    }

                    if (Math.abs(i - currentRow) > 2) {
                        break;
                    } else if (Math.abs(i - currentRow) == 2) {
                        //Advancing two spaces at beginning
                        if (hasMoved) {
                            continue;
                        }

                        if (color.equals("white")) {
                            if (board[currentRow + 2][currentCol] != null) {
                                continue;
                            }
                        } else {
                            if (board[currentRow - 2][currentCol] != null) {
                                continue;
                            }
                        }

                    }
                } else {
                    //Taking a piece
                    if (Math.abs(j - currentCol) != 1 || Math.abs(i - currentRow) != 1) {
                        continue;
                    }

                    if (board[i][j] == null) {
                        continue;
                    }
                }


                if (!hasMoved) {
                    hasMoved = true;
                }

                PossibleMoves [i][j] = true;
            }
        return PossibleMoves;

    }
    /**
     *
     * @return color
     */
    public String getColor(){
        return this.color;
    }

    /**
     * returns P for pawn as charachter
     * @return char
     */
    public String toString(){
        return color.charAt(0) + "p";

    }

}