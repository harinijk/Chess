// Written by Harini Kuchibhotla, kuchi027
import java.util.Scanner;
public class Game {

    // main method for players to interact with the game
    public static void main(String[]args){

        Board chessBoard= new Board();
        int count=0; // variable to keep track of the turns
        int startRow;
        int startCol;
        int endRow;
        int endCol;

        Scanner s= new Scanner(System.in);
        Fen.load("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR", chessBoard);

        while(!chessBoard.isGameOver()){

          System.out.println(chessBoard.toString()); //Prints the visual representation of the board

            // White's turn if count is even
            if(count%2 == 0) {
                System.out.println("It is currently white's turn to play. What is your move? (format: [start row][start col] [end row] [endcol])");
                startRow = s.nextInt();
                startCol = s.nextInt();
                endRow = s.nextInt();
                endCol = s.nextInt();

                if(chessBoard.getPiece(startRow,startCol) != null &&
                        !(chessBoard.getPiece(startRow,startCol).getIsBlack())
                        && chessBoard.movePiece(startRow, startCol, endRow, endCol) ){
                    count++;
                }
                else{
                    System.out.println("Invalid Move. Choose again");
                    System.out.println();
                }
            }

            // Black's turn if count is odd
            else {
                System.out.println("It is currently black's turn to play. What is your move? (format: [start row][start col] [end row] [endcol])");
                startRow = s.nextInt();
                startCol = s.nextInt();
                endRow = s.nextInt();
                endCol = s.nextInt();

                if(chessBoard.getPiece(startRow,startCol) != null &&
                        (chessBoard.getPiece(startRow,startCol).getIsBlack()) &&
                        chessBoard.movePiece(startRow, startCol, endRow, endCol) ){
                    count++;
                }
                else{
                    System.out.println("Invalid Move. Choose again");
                    System.out.println();
                }
            }
        }

        //check who wins based on count
         if(count%2 == 0) {
             System.out.print("Black has won the game!");
         }
         else {
             System.out.print("White has won the game!");
         }
    }
}
