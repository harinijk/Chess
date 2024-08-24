//Written by Harini Kuchibhotla, kuchi027
public class Bishop {

    //instance variables
    private int row;
    private int col;
    private boolean isBlack;

    //constructor
    public Bishop(int row, int col, boolean isBlack) {
        this.row = row;
        this.col = col;
        this.isBlack = isBlack;
    }

    // checks if the move is legal by checking if the bishop moves in a valid diagonal direction
    public boolean isMoveLegal(Board board, int endRow, int endCol) {
        if (board.verifySourceAndDestination(this.row, this.col, endRow, endCol, this.isBlack) &&
                (board.verifyDiagonal(this.row,this.col,endRow,endCol)) ){
            return true;
        }
        return false;
    }
}