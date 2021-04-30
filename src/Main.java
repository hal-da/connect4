import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Player playerX = new Player("X", Color.X);
        Player playerO = new Player("O", Color.O);
        Game game = new Game();

        boolean gameIsPlayIng = true;

        printGreeting();

        while (gameIsPlayIng){

            playARound(game,playerX);
            gameIsPlayIng = !game.checkIfMoveWins();
            if(gameIsPlayIng) {
                playARound(game, playerO);
                gameIsPlayIng = !game.checkIfMoveWins();
            }

            if(!gameIsPlayIng){
                game.printPieces();
                game.checkIfMoveWins();
                gameIsPlayIng = wannaGoAgain();
                game.setUpNewGame();
            }
            if(!game.hasEmptyPieces()){
                System.out.println("It's a draw!");
                gameIsPlayIng = wannaGoAgain();
                game.setUpNewGame();
            }
        }
    }

    static void playARound(Game game, Player player){
        boolean moveSuccessful = false;
        while (!moveSuccessful){
            game.printPieces();
            moveSuccessful = game.putPieceInSlotAtCol(player.getMove(),player.getColor());
        }
    }

    static void printGreeting(){
        System.out.println("*******************************");
        System.out.println("*** Welcome to Connect 4 v0 ***");
        System.out.println("********** by daniel halasz ***");
        System.out.println("********** Player1: X *********");
        System.out.println("********** Player2: O *********");
        System.out.println("*******************************");
    }

    static boolean wannaGoAgain(){
        Scanner sc = new Scanner(System.in);
        char again = '#';

        while (!(again == 'y' || again == 'n')){
            try{
                System.out.println("wanna go again? y/n:");
                again = sc.nextLine().charAt(0);
            } catch (Exception e){
                //do nothing;
            }
        }
        return again == 'y';
    }
}

