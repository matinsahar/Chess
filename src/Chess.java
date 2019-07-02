import java.io.IOException;
import java.util.Scanner;

public class Chess {


    String color;
    public Board gameBoard;
        public Chess (){
           gameBoard = new Board();
           color = "white";
        }


        public void gameplay(String move ) {
            while(true){

            System.out.println(gameBoard);

            if(move.contains("resign")){
                System.out.println(color + " resigns");
                System.out.println(colorToggle(color) + " wins the game!");
                return;
            }

            try {

                gameBoard.performMove(move, color, true);
            } catch (IOException e) {
                // Ask for user input again
                System.out.println("Invalid input!");
                continue;
            }
            Piece[][] oldBoard = gameBoard.board.clone();

            if(!gameBoard.canAnyPieceMakeAnyMove(colorToggle(color))){
                if(gameBoard.isInCheck(colorToggle(color))){
                    System.out.println("Checkmate. " + color + " wins");
                    System.out.println("Game over!");
                }else{
                    System.out.println("Stalemate!");
                }
                return;
            }

            gameBoard.board = oldBoard;

            if(gameBoard.isInCheck(colorToggle(color))){
                System.out.println(colorToggle(color) + " is in check.");
            }
            //Now I have to check to see if either player is in check or checkmate
            //I also have to see if there is a draw

            color = colorToggle(color);

        }

        }


        public static String colorToggle(String color){
            if(color.equals("white")){
                return "black";
            }

            return "white";
        }

    }

