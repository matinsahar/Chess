import java.io.IOException;
import java.util.Scanner;

public class Chess {



        /**
         * parameters
         */
        public static void main(String[] args) {


            Board gameBoard = new Board();

            String color = "white";


            while(true){

                System.out.println(gameBoard);

                System.out.println(color + " make a move: ");
                Scanner sc = new Scanner(System.in);
                String move = sc.nextLine();

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
                        System.out.println("no move possible");
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

