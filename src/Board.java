import java.io.IOException;
import java.util.*;

public class Board {
    public Piece[][] board = new Piece[8][8];
    public int whiteKingPos;
    public int blackKingPos;

    public Board(){
        for(int x = 0; x<board.length; x++){
            for(int y = 0; y<board[0].length; y++){
                board[x][y] = null;
            }
    }

        // White pawns
        for(int x=0; x<8; x++){
            board[1][x] = new Pawn("white");
        }

        // Black pawns
        for(int x=0; x<8; x++){
            board[6][x] = new Pawn("black");
        }

        //Rooks
        board[0][0] = new Rook("white");
        board[0][7] = new Rook("white");
        board[7][7] = new Rook("black");
        board[7][0] = new Rook("black");

        //Knights
        board[0][1] = new Knight("white");
        board[0][6] = new Knight("white");
        board[7][6] = new Knight("black");
        board[7][1] = new Knight("black");

        //Bishops
        board[0][2] = new Bishop("white");
        board[0][5] = new Bishop("white");
        board[7][2] = new Bishop("black");
        board[7][5] = new Bishop("black");

        //Queens
        board[0][3] = new Queen("white");
        board[7][3] = new Queen("black");

        //Kings
        board[0][4] = new King("white");
        board[7][4] = new King("black");

    }

    /**
     * Checks to see if the king of the given color is in check. This will be called twice after every move
     * @param color
     * @return boolean value corresponding to the king being in check
     */
    public boolean isInCheck(String color){
        return false;
    }

    /**
     * Performs the move, and modifies the actual board
     *
     * @throws IOException
     */
    public void performMove(String move, String color) throws IOException{
        int[] moveArray = parseInput(move);
        System.out.println(board[moveArray[0]][moveArray[1]]);

        if(board[moveArray[0]][moveArray[1]] == null){
            throw new IOException();
        }

        if(!board[moveArray[0]][moveArray[1]].getColor().equals(color)){
            throw new IOException();
        }

        if(board[moveArray[2]][moveArray[3]] != null){
            if(board[moveArray[2]][moveArray[3]].getColor().equals(color)){
                throw new IOException();
            }
        }

        if(board[moveArray[0]][moveArray[1]].validateMove(board, moveArray[0], moveArray[1], moveArray[2], moveArray[3])){
            //This means the move was valid

            //Switch the two spots on the board because the move was valid
            board[moveArray[2]][moveArray[3]] = board[moveArray[0]][moveArray[1]];
            board[moveArray[0]][moveArray[1]] = null;

            if(board[moveArray[2]][moveArray[3]].getClass().isInstance(new King("white"))){
                ((King) board[moveArray[2]][moveArray[3]]).hasMoved = true;
                //This Piece is a King
                if(((King) board[moveArray[2]][moveArray[3]]).castled){
                    if(moveArray[3] - moveArray[1] == 2){
                        board[moveArray[2]][moveArray[3] - 1] = board[moveArray[2]][moveArray[3] + 1];
                        board[moveArray[2]][moveArray[3] + 1] = null;
                    }else{
                        board[moveArray[2]][moveArray[3] + 1] = board[moveArray[2]][moveArray[3] - 1];
                        board[moveArray[2]][moveArray[3] - 1] = null;
                    }
                    ((King) board[moveArray[2]][moveArray[3]]).castled = false;
                }
            }

            if(isInCheck(color)){
                //Because the current player put him/herself in check
                //I need to find out what is going to happen to the board in this case
                throw new IOException();
            }

        }else{
            throw new IOException();
        }

    }

    /**
     * Parses the user's string input for a move
     * @param move
     * @return An array of size 4 with the initial x, y positions and the final x, y positions in that order
     */
    public static int[] parseInput(String move){
        int[] returnArray = new int[4];

        String[] split = move.split(" ");
        returnArray[1] = charToInt(Character.toLowerCase(split[0].charAt(0)));
        returnArray[0] = Integer.parseInt(move.charAt(1) + "") - 1;

        returnArray[3] = charToInt(Character.toLowerCase(split[1].charAt(0)));
        returnArray[2] = Integer.parseInt(split[1].charAt(1) + "") - 1;
        return returnArray;

    }

    /**
     * Returns an integer corresponding to the user input
     */
    public static int charToInt(char ch){
        switch(ch){
            case 'a': return 0;
            case 'b': return 1;
            case 'c': return 2;
            case 'd': return 3;
            case 'e': return 4;
            case 'f': return 5;
            case 'g': return 6;
            case 'h': return 7;
            default: return 8;
        }
    }

    /**
     * Checks to see if the player is in checkmate
     * @return true if checkmated, false otherwise
     */
    public boolean checkMate(String color){
        return false;
    }

    /**
     * Checks to see if there is a stalemate
     * @return true if stalemated, false otherwise
     */
    public boolean staleMate(String color){
        return false;
    }

    public String toString(){
        String string = "";
        int fileCount = 0;
        for(Piece[] pieces: board){
            int rankCount = 0;
            for(Piece piece: pieces){
                if(piece==null){
                    if(fileCount%2 == 0){
                        if(rankCount%2 == 0){
                            string += "##";
                        }else{
                            string += "  ";
                        }
                    }else{
                        if(rankCount%2 == 0){
                            string += "  ";
                        }else{
                            string += "##";
                        }
                    }
                }else{
                    string += piece;
                }
                string += " ";
                rankCount++;
            }
            fileCount++;
            string += "\n";
        }

        String reverseString = "";

        reverseString += "  a  b  c  d  e  f  g  h \n";
        String[] stringSplit = string.split("\n");
        for(int x = stringSplit.length-1; x >= 0; x--){
            reverseString += x+1 + " " + stringSplit[x] + "\n";
        }

        return reverseString;
    }
}
