public class Game {

    static final int NUMBER_ROWS = 6;
    static final int NUMBER_COLS = 7;
    static final String FONT_COLOR_RED = "\u001B[31m";
    static final String FONT_COLOR_RESET = "\u001B[0m";

    private final Piece[][] board = new Piece[NUMBER_ROWS][NUMBER_COLS];

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

            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[i].length-1; j++) {
                    if(board[i][j].getCaption() != Color.Empty.getCaption() && board[i][j].getCaption() == board[i][j+1].getCaption()) sameInARow++;
                    else sameInARow = 0;
                    if (sameInARow == 3){
                        int[][] fourConnected = {
                                {i, j-2},
                                {i, j-1},
                                {i, j},
                                {i, j+1},
                        };
                        printWonGame(fourConnected);
                        System.out.println(board[i][j].getCaption() + " has won by 4 in a row: " + i);
                        return true;
                }
            } sameInARow = 0;
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
                    int[][] fourConnected = {
                            {row - 2, col},
                            {row - 1, col},
                            {row, col},
                            {row + 1, col},
                    };

                    printWonGame(fourConnected);
                    System.out.println(board[row][col].getCaption() + " has won by 4 in a column in column " + (col+1));
                    return true;
                }
            } sameInCol = 0;
        }
        return false;
    }

    public boolean fourDiagonal(){
        int equalPieces = 0;

        // diagonal increasing

        for (int i = 3; i <= 5; i++) {
            for (int j = 0; j <= 3; j++) {
                for (int offset = 0; offset < 3; offset++) {
                    if((board[i-offset][j+offset].getCaption() != Color.Empty.getCaption()) && ( board[i-offset][j+offset].getCaption() == board[i-offset -1][j+offset +1].getCaption())) equalPieces++;
                    else equalPieces = 0;

                    if (equalPieces == 3){
                        int[][] fourConnected = {
                                {i, j},
                                {i-1, j+1},
                                {i-2, j+2},
                                {i-3, j+3},
                        };
                        printWonGame(fourConnected);
                        System.out.println(board[j][i].getCaption() + " has won by 4 in diagonal increasing in col " + (j+1) + " to col " + (j + 4));
                        return true;
                    }
                }equalPieces = 0;
            } equalPieces = 0;
        }

        // diagonal decreasing

        equalPieces = 0;
        for (int i = 0; i <= 2; i++) {
            for (int j = 0; j <= 3; j++) {
                for (int offset = 0; offset < 3; offset++) {
                    if(board[i+offset][j+offset].getCaption() != Color.Empty.getCaption() && (board[i+offset][j+offset].getCaption() == board[i+offset+1][j+offset+1].getCaption())) equalPieces++;
                    else equalPieces = 0;

                    if (equalPieces == 3){
                        int[][] fourConnected = {
                                {i, j},
                                {i+1, j+1},
                                {i+2, j+2},
                                {i+3, j+3},
                        };
                        printWonGame(fourConnected);
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

    public void printWonGame(int[][] fourConnected){

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if(
                        i== fourConnected[0][0] && j == fourConnected[0][1] ||
                        i== fourConnected[1][0] && j == fourConnected[1][1] ||
                        i== fourConnected[2][0] && j == fourConnected[2][1] ||
                        i== fourConnected[3][0] && j == fourConnected[3][1]
                ) {
                    System.out.print("| " + FONT_COLOR_RED + board[i][j].getCaption() + " " + FONT_COLOR_RESET);
                } else System.out.printf("| %s ", board[i][j].getCaption() );
            }
            System.out.println("|");
        }
        System.out.println("¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯");
    }
}
