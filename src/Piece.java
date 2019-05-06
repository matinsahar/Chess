

public abstract class Piece {
    public String color;
    public boolean hasMoved;



     // Gets the color

    public abstract String getColor();

  // move piece
    public abstract boolean validateMove(Piece[][] board, int currentRow, int currentCol, int newRow, int newCol);
}

