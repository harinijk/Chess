//Written by Harini Kuchibhotla, kuchi027
public class Knight {
    //instance variables
    private int row;
    private int col;
    private boolean isBlack;

    //constructor
    public Knight(int row, int col, boolean isBlack) {
        this.row = row;
        this.col = col;
        this.isBlack = isBlack;
    }

    //checks if the move is legal by checking if the knight moves in either one of the valid "L" shapes
    public boolean isMoveLegal(Board board, int endRow, int endCol) {


        if(board.verifySourceAndDestination(this.row, this.col, endRow, endCol, this.isBlack)){

            // case 1 checks if it moves two squares out horizontally  and then 1 additional square
            //out perpendicular to that direction
            if((endRow == this.row+2 || endRow == this.row-2) && (endCol == this.col-1 || endCol == this.col+1)){
                return true;
        }
            // case 2 checks if it moves two squares out vertically and then 1 additional square
            //out perpendicular to that direction
            else if((endRow == this.row+1 || endRow == this.row-1) && (endCol == this.col-2 || endCol == this.col+2)){
                return true;
            }
            return false;
        }
        return false;
    }
}