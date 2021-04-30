import java.util.Arrays;

public class Game {

    static final int NUMBER_ROWS = 6;
    static final int NUMBER_COLS = 7;

    private Piece[][] board = new Piece[NUMBER_ROWS][NUMBER_COLS];

    public Game() {
        setUpNewGame();
    }

    public void setUpNewGame(){
        for (int cols = 0; cols < NUMBER_COLS; cols++) {
            for (int rows = 0; rows < NUMBER_ROWS; rows++) {
                board[rows][cols] = new Piece(Color.Empty);
            }
        }
    }

    public void printPieces(){
        for (int i = 0; i < NUMBER_COLS; i++) {
            System.out.print("  " + (i+1) + " ");
        }
        System.out.println();

        for (Piece[] rows: board){
            for (Piece piece: rows){
                System.out.print("| " + piece.getCaption() + " ");
            }
            System.out.println("|");
        }
        System.out.println("¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯");
    }



    public boolean checkIfMoveWins(){

        return fourInAColumn() || fourInARow() || fourDiagonal();

    }

    public boolean putPieceInSlotAtCol(int col, Color color){

        col--;

        try {
            for (int i = 0; i < board.length; i++) {
                if(board[i][col].getColor() != Color.Empty) {
                    board[i-1][col] = new Piece(color);
                    return true;
                }
                if(i == board.length-1) {
                    board[i][col] = new Piece(color);
                    return true;
                }
            }
        } catch (Exception e){
            System.err.println("Column seems to be full, choose another one");
            return false;
        }
        return false;
    }

    public boolean fourInARow(){
        int sameInARow = 0;

        for(Piece[] rows: board){
            for (int i = 0; i < NUMBER_COLS-1; i++) {
                if(rows[i].getCaption() != Color.Empty.getCaption() && rows[i].getCaption() == rows[i+1].getCaption()) sameInARow++;
                else sameInARow = 0;
                if (sameInARow == 3){
                    System.out.println(rows[i].getCaption() + " has won by 4 in a row: " + Arrays.toString(rows));
                    return true;
                }
            }
        }
        return false;
    }

    // eigentlich sinnlos

    public boolean fourInAColumn(int col){
        col--;
        int sameInCol = 0;

        for (int row = 0; row < NUMBER_ROWS-1; row++) {
            if(board[row][col].getCaption() != Color.Empty.getCaption() && board[row][col].getCaption() == board[row+1][col].getCaption()) sameInCol++;
            else sameInCol = 0;

            if(sameInCol == 3){
                System.out.println(board[row][col].getCaption() + " has won by 4 in a column in column " + (col+1));
                return true;
            }
        }

        return false;
    }

    public boolean fourInAColumn(){
        int sameInCol = 0;

        for (int col  = 0; col < NUMBER_COLS; col++) {
            for (int row = 0; row < NUMBER_ROWS-1; row++) {

                if(board[row][col].getCaption() != Color.Empty.getCaption() &&
                    board[row][col].getCaption() == board[row+1][col].getCaption()) sameInCol++;
                else sameInCol = 0;

                if(sameInCol == 3){
                    System.out.println(board[row][col].getCaption() + " has won by 4 in a column in column " + (col+1));
                    return true;
                }
            }

        }

        return false;
    }

    public boolean fourDiagonal(){

        int equalPieces = 0;
        for (int i = 3; i <= 5; i++) {
            for (int j = 0; j <= 3; j++) {
                for (int offset = 0; offset < 3; offset++) {
                    if((board[i-offset][j+offset].getCaption() != Color.Empty.getCaption()) && ( board[i-offset][j+offset].getCaption() == board[i-offset -1][j+offset +1].getCaption())) equalPieces++;
                    else equalPieces = 0;

                    if (equalPieces == 3){
                        System.out.println(board[j][i].getCaption() + " has won by 4 in diagonal increasing in col " + (j+1) + " to col " + (j + 4));
                        return true;
                    }
                }equalPieces = 0;
            } equalPieces = 0;
        }

        //----------------------------------

        equalPieces = 0;
        for (int i = 0; i <= 2; i++) {
            for (int j = 0; j <= 3; j++) {
                for (int offset = 0; offset < 3; offset++) {
                    if(board[i+offset][j+offset].getCaption() != Color.Empty.getCaption() && (board[i+offset][j+offset].getCaption() == board[i+offset+1][j+offset+1].getCaption())) equalPieces++;
                    else equalPieces = 0;

                    if (equalPieces == 3){
                        System.out.println(board[i][j].getCaption() + " has won by 4 in diagonal decreasing in col " + (j+1) + " to col " + (j + 4));
                        return true;
                    }
                }equalPieces = 0;
            }equalPieces = 0;
        }
        return false;
    }

    public boolean hasEmptyPieces(){
        for(Piece[] row : board){
            for (Piece piece: row){
                if(piece.getCaption() == Color.Empty.getCaption()) return true;
            }
        }
        return false;
    }
}
