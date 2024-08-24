//Written by Harini Kuchibhotla, kuchi027
public class King {
    //instance variables
    private int row;
    private int col;
    private boolean isBlack;

    public King(int row, int col, boolean isBlack) {
        this.row = row;
        this.col = col;
        this.isBlack = isBlack;
    }

    // checks if the move is legal by checking if the king moves in a valid direction one position away from where it stands and if the
    // king is “captured” and they lose the game
    public boolean isMoveLegal(Board board, int endRow, int endCol) {

        if(board.verifySourceAndDestination(this.row, this.col, endRow, endCol, this.isBlack)){
            //checks if it is a legal adjacent row and adjacent columns move
            if((endRow == this.row+1 || endRow == this.row-1) &&
                    (endCol == this.col+1 || endCol == this.col-1 || endCol == this.col) ){
                return true;
            }
            //checks if it is a legal same row and adjacent column move
            else if(endRow == this.row && (endCol == this.col+1 || endCol == this.col-1)){
                return true;
            }
            // checks if either color king is captured to end the game
            else if((board.getPiece(endRow,endCol).toString()).equals("\u265a") ||
                    (board.getPiece(endRow,endCol).toString()).equals("\u2654")){
                board.isGameOver();
                return true;
            }
            return false;

        }
        return false;
    }


}
