//Written by Harini Kuchibhotla, kuchi027
public class Rook {

    //instance variables
    private int row;
    private int col;
    private boolean isBlack;

    //constructor
    public Rook(int row, int col, boolean isBlack) {
        this.row = row;
        this.col = col;
        this.isBlack = isBlack;

    }

    // checks if the move is legal by checking if the rook moves in a valid horizontal or vertical direction
    public boolean isMoveLegal(Board board, int endRow, int endCol) {
        if (board.verifySourceAndDestination(this.row, this.col, endRow, endCol, this.isBlack) &&
                (board.verifyVertical(this.row, this.col, endRow, endCol) ||
                        board.verifyHorizontal(this.row, this.col, endRow, endCol))) {
            return true;
        }
        return false;

    }
}
