/**
 * @Author :  Matin Saharkhiz
 * @version : 1.0
 */

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.IOException;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;


public class Graphic {



    Chess game = new Chess();
    private MouseEvent a;
    JButton [][] buttons = new JButton[9][9];
    Piece [][] board = new Piece[9][9] ;
    public Graphic (){
        for(int x = 0; x < 9; x++){
            for(int y = 0; y<9; y++){
                board[x][y] = (Piece) buttons[x][y];
            }
        }
        JFrame frame = new JFrame("BorderLayoutDemo");
        JPanel panel1 = new JPanel();
        JPanel panel2 = new JPanel();
        JPanel panel3 = new JPanel();
        JPanel panel4 = new JPanel();

        panel1.setBackground(new Color(0,255,0));
        panel2.setBackground(new Color(255,0,0));
        panel4.setBackground(new Color(22,105,155));
        panel3.setBackground(new Color(122,22,52));

        panel2.setPreferredSize(new Dimension(600,800));
        panel4.setPreferredSize(new Dimension(300,800));

        Container pane = frame.getContentPane();

        BorderLayout border = new BorderLayout();

        frame.setLayout(border);
        frame.add(panel1 , BorderLayout.NORTH);
        frame.add(panel2 , BorderLayout.CENTER);
        frame.add(panel4 , BorderLayout.WEST);

        panel4.setLayout(new BorderLayout());
        JPanel chapBala = new JPanel();
        chapBala.setPreferredSize(new Dimension(300 ,200));
        chapBala.setBackground(new Color(185,55,66));
        //eliminated pieces chap vasat
        JPanel chapVasat = new JPanel();
        chapVasat.setLayout(new BorderLayout());

        JPanel WhiteHit = new JPanel();
        chapVasat.add(WhiteHit , BorderLayout.NORTH);

        JPanel BlackHit = new JPanel();
        chapVasat.add(BlackHit , BorderLayout.SOUTH);

        chapVasat.setPreferredSize(new Dimension(300 ,400));
        BlackHit.setPreferredSize(new Dimension(300 ,150));
        WhiteHit.setPreferredSize(new Dimension(300 ,150));

        chapVasat.setBackground(new Color(100,22,122));
        //Chap paeen
        JPanel chapPaeen = new JPanel();

        chapPaeen.setPreferredSize(new Dimension(300 ,200));
        chapPaeen.setBackground(new Color(200,22,122));

        panel4.add(chapBala , BorderLayout.NORTH);
        panel4.add(chapVasat , BorderLayout.CENTER);
        panel4.add(chapPaeen , BorderLayout.SOUTH);

        //Board
        panel2.setLayout(new GridLayout(0,9));

        for(int x=1; x<9; x++){
            buttons[1][x] = new Pawn("black");

        }

        // white pawns
        for(int x=1; x<9; x++){
            buttons[6][x] = new Pawn("white");
        }

        //Rooks
        buttons[0][1] = new Rook("black");
        buttons[0][8] = new Rook("black");
        buttons[7][1] = new Rook("white");
        buttons[7][8] = new Rook("white");

        //Knights
        buttons[0][2] = new Knight("black");
        buttons[0][7] = new Knight("black");
        buttons[7][2] = new Knight("white");
        buttons[7][7] = new Knight("white");

        //Bishops
        buttons[0][3] = new Bishop("black");
        buttons[0][6] = new Bishop("black");
        buttons[7][3] = new Bishop("white");
        buttons[7][6] = new Bishop("white");

        //Queens
        buttons[0][5] = new Queen("black");
        buttons[7][5] = new Queen("white");

        //Kings
        buttons[0][4] = new King("black");
        buttons[7][4] = new King("white");

        for(Integer row =0; row < 9; row++)
            for(Integer col=0; col<9;col++){
                if ( !(buttons[row][col] instanceof Piece) )
                    buttons[row][col] = new JButton();


            }
        for(Integer row =0; row < 9; row++)
            for(Integer col=0; col<9;col++)
            {
                Listen lis = new Listen();
                if( buttons[row][col] != (null) )
                    buttons[row][col].addMouseListener(lis);
                if(col==0 && row==8)
                {    buttons[row][col].setEnabled(false);
                    buttons[row][col].setContentAreaFilled(false);
                }
                else if(col==0){
                    Integer c= 8-row;
                    buttons[row][col].setEnabled(false);
                    buttons[row][col].setText(""+(c).toString());
                }

                else if(row == 8){
                    buttons[row][col].setText(""+(char)('A'+col - 1));
                    buttons[row][col].setEnabled(false);
                }

                else if((col+row)%2==1)
                    buttons[row][col].setBackground(Color.LIGHT_GRAY);
                else
                    buttons[row][col].setBackground(Color.DARK_GRAY);

                panel2.add(buttons[row][col]);
            }


        frame.pack();
        frame.setVisible(true);
    }

    private String coordinatesToMoveString(int row, int col, int newRow, int newCol){

        String returnString = "";

        switch(col){
            case 1: returnString += 'a'; break;
            case 2: returnString += 'b'; break;
            case 3: returnString += 'c'; break;
            case 4: returnString += 'd'; break;
            case 5: returnString += 'e'; break;
            case 6: returnString += 'f'; break;
            case 7: returnString += 'g'; break;
            case 8: returnString += 'h'; break;
            default: returnString += 'a'; break;
        }

        int addInt = 8-row;

        returnString += addInt + "";

        returnString += " ";

        switch(newCol){
            case 1: returnString += 'a'; break;
            case 2: returnString += 'b'; break;
            case 3: returnString += 'c'; break;
            case 4: returnString += 'd'; break;
            case 5: returnString += 'e'; break;
            case 6: returnString += 'f'; break;
            case 7: returnString += 'g'; break;
            case 8: returnString += 'h'; break;
            default: returnString += 'a'; break;
        }

        addInt = 8-newRow;

        returnString += addInt + "";
        //System.out.println(row + " " + col + " " + newRow + " " + newCol + " " + returnString);
        return returnString;
    }



int moving = 0;
    int currenrow , currenntcol ;
    String color = "white";
    class Listen implements MouseListener , MouseMotionListener {
        private int nextrow , nextcol;
        String movestring;
        @Override
        public void mouseClicked(MouseEvent e) {
            for (Integer row = 0; row < 9; row++)
                for (Integer col = 1; col < 9; col++) {
                    buttons[row][col].setEnabled(true);
                }
            if(moving%2 == 0){
                JButton butt = (JButton) e.getSource();
                for (Integer row = 0; row < 9; row++)
                    for (Integer col = 1; col < 9; col++) {
                        if (butt.equals(buttons[row][col]) && (buttons[row][col] instanceof Piece)) {
                            Piece r = (Piece) butt;
                            color = r.getColor();
                            currenrow = row;
                            currenntcol = col;
                            for (int i = 0; i < 8; i++)
                                for (int j = 0; j < 8; j++){
                                    if ( r.getPossibleMoves(board , row   , col -1 )[i][j])
                                        buttons[i][j+1].setEnabled(false);
                                }
                        }

                    }
                moving ++;
                System.out.println(moving);
            }
            else{
                for (Integer row = 0; row < 9; row++)
                    for (Integer col = 0; col < 9; col++) {
                        JButton butt = (JButton) e.getSource();

                        if (butt.equals(buttons[row][col]) ) {
                            nextrow = row ;
                            nextcol = col ;
                            System.out.println(nextrow +", " + nextcol + "   " + currenrow + currenntcol);
                            movestring = coordinatesToMoveString(currenrow , currenntcol , nextrow , nextcol) ;
                            System.out.println(movestring);
                            Piece p = (Piece)buttons[currenrow][currenntcol];
                            Piece[][] oldBoard = game.gameBoard.board.clone();
                            if (!game.gameplay(movestring).equals("Invalid input!")){
                                buttons[nextrow][nextcol] = buttons [currenrow][currenntcol] ;
                                buttons [currenrow][currenntcol] = null;
                            }

                        }

                        // for (int i = 0; i < 8; i++)
                        // buttons[i][col].setBackground(Color.blue);
                    }
                System.out.println(game);
                moving ++;

            }

        }
        @Override
        public void mousePressed(MouseEvent e) {


            }


        @Override
        public void mouseReleased(MouseEvent c) {


        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }

        @Override
        public void mouseDragged(MouseEvent e) {
        }

        @Override
        public void mouseMoved(MouseEvent e) {

        }
    }

}
