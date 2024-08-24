//Written by Harini Kuchibhotla, kuchi027
public class Board {

    // Instance variable
    private Piece[][] board;

    // Construct an object of type Board using given arguments.

    public Board(){
        board = new Piece[8][8];
    }

    // Accessor Methods

    // Returns the Piece object stored at a given row and column
    public Piece getPiece(int row, int col) {
        if(row >= 0 && row < 8 && col >= 0 && col < 8) {
            return board[row][col];
        }
        else{
            return null;
        }
    }


    // Update a single cell of the board to the new piece.
    public void setPiece(int row, int col, Piece piece) {
        board[row][col]=piece;
    }

    // Game functionality methods


    // Moves a Piece object from one cell in the board to another, provided that
    // this movement is legal. A constraint of a legal move is:
    // - there exists a Piece at (startRow, startCol) and the destination square is seizable.
    // Returns a boolean to signify success or failure.
    // This method calls all necessary helper functions to determine if a move is legal,
    // and to execute the move if it is.
    // Your Game class should not directly call any other method of this class.
    // Hint: this method should call isMoveLegal() on the starting piece. 
    public boolean movePiece(int startRow, int startCol, int endRow, int endCol) {
            Piece tempPiece= getPiece(startRow,startCol);

            // checks if piece exists and is a legal move
            if(tempPiece != null && tempPiece.isMoveLegal(this,endRow,endCol)){
                board[endRow][endCol]=tempPiece;
                board[startRow][startCol]=null;
                tempPiece.setPosition(endRow,endCol);

                //checks for pawn promotion
                if((tempPiece.getCharacter()=='\u2659' && endRow==0) ||(tempPiece.getCharacter()=='\u265f') && endRow==7){
                    tempPiece.promotePawn(endRow,tempPiece.getIsBlack());
                }
                return true;
            }
            return false;
    }


    // Determines whether the game has been ended, i.e., if one player's King
    // has been captured.
    public boolean isGameOver() {

        int count=0; // variable to check the number of kings on the board

        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[i].length; j++){
                if(board[i][j] != null){
                    // checks if the character of the piece is the kings character
                    if((board[i][j].toString()).equals("\u265a") || (board[i][j].toString()).equals("\u2654")){
                    count++;
                    }
                }
            }
        }
        // checks if there are less than 2 kings which is the case of winning the game
        if(count < 2){
            return true;
        }
        return false;
    }

    // Constructs a String that represents the Board object's 2D array.
    // Returns the fully constructed String.
    public String toString() {
        StringBuilder out = new StringBuilder();
        out.append(" ");
        for(int i = 0; i < 8; i++){
            out.append(" ");
            out.append(i);
        }
        out.append('\n');
        for(int i = 0; i < board.length; i++) {
            out.append(i);
            out.append("|");
            for(int j = 0; j < board[0].length; j++) {
                out.append(board[i][j] == null ? "\u2001|" : board[i][j] + "|");
            }
            out.append("\n");
        }
        return out.toString();
    }


    // Sets every cell of the array to null. For debugging and grading purposes.
    public void clear() {
        for(int i =0; i < board.length; i++){
            for(int j =0; j <board[i].length; j++){
                board[i][j] = null;
            }
        }
    }

    // Movement helper functions


    // Ensure that the player's chosen move is even remotely legal.
    // Returns a boolean to signify whether:
    // - 'start' and 'end' fall within the array's bounds.
    // - 'start' contains a Piece object, i.e., not null.
    // - Player's color and color of 'start' Piece match.
    // - 'end' contains either no Piece or a Piece of the opposite color.
    // - where 'start' = (startRow, startCol) and 'end' = (endRow, endCol)
    public boolean verifySourceAndDestination(int startRow, int startCol, int endRow, int endCol, boolean isBlack) {

        //checks that start and end positions are in board bounds
        if(startRow >= 0 && startRow < 8 && startCol >= 0 && startCol < 8 &&
                endRow >= 0 && endRow < 8 && endCol >= 0 && endCol < 8){

            //checks if there is a piece at start position and if it is the same color as given color
            if(board[startRow][startCol] != null && board[startRow][startCol].getIsBlack() == isBlack ){

                //checks if end position is empty or has a piece of opposite color
                if(board[endRow][endCol] == null || board[endRow][endCol].getIsBlack() != isBlack ){
                    return true;
                }
            }
        }
        return false;
    }


    // Check whether the 'start' position and 'end' position are adjacent to each other
    public boolean verifyAdjacent(int startRow, int startCol, int endRow, int endCol) {

        //checks for the adjacent vertical and horizontal positions
        if((startRow == endRow && (startCol == endCol || startCol == endCol+1 || startCol == endCol-1)) ||
                (startCol == endCol && (startRow == endRow+1 || startRow == endRow-1)) ){
            return true;
        }

        //checks for the adjacent diagonal positions
        else if((Math.abs(startRow-endRow) == 1) && (Math.abs(startCol-endCol) == 1)){
            return true;
        }
        return false;
    }


    // Checks whether a given 'start' and 'end' position are a valid horizontal move.
    // Returns a boolean to signify whether:
    // - The entire move takes place on one row.
    // - All spaces directly between 'start' and 'end' are empty, i.e., null.
    public boolean verifyHorizontal(int startRow, int startCol, int endRow, int endCol) {

        //checks if the move is in one row
        if(startRow == endRow){
            for(int i = Math.min(startCol,endCol)+1; i < Math.max(startCol,endCol); i++){
                //checks if the spaces between start and end are null
                if(board[startRow][i] != null){
                    return false;
                }
            }
            return true;
        }
        return false;
    }


    // Checks whether a given 'start' and 'end' position are a valid vertical move.
    // Returns a boolean to signify whether:
    // - The entire move takes place on one column.
    // - All spaces directly between 'start' and 'end' are empty, i.e., null.
    public boolean verifyVertical(int startRow, int startCol, int endRow, int endCol) {

        // checks if move is in one column
        if(startCol == endCol){
            for(int i = Math.min(startRow,endRow)+1; i < Math.max(startRow,endRow); i++){
                //checks if spaces between start and end are empty
                if(board[i][startCol] != null){
                    return false;
                }
            }
            return true;
        }
        return false;
    }


    // Checks whether a given 'start' and 'end' position are a valid diagonal move.
    // Returns a boolean to signify whether:
    // - The path from 'start' to 'end' is diagonal... change in row and col.
    // - All spaces directly between 'start' and 'end' are empty, i.e., null.
    public boolean verifyDiagonal(int startRow, int startCol, int endRow, int endCol) {

        //checks if start to end is a valid diagonal
        if((startRow + startCol) == (endRow + endCol)) {
            int j = Math.max(startCol, endCol);
            for (int i = Math.min(startRow, endRow)+1; i < Math.max(startRow, endRow); i++) {
                //checks if spaces between start and end are empty
                if (board[i][--j] != null) {
                    return false;
                }
            }
            return true;
        }
        //checks if start to end is a valid diagonal
        else if((startRow - startCol) == (endRow - endCol)){
            int j= Math.min(startCol, endCol);
            for (int i = Math.min(startRow, endRow)+1; i < Math.max(startRow, endRow);i++){
                //checks if spaces between start and end are empty
                if(board[i][++j] != null){
                    return false;
                }
            }
            return true;
        }
    return false;
        }
}
