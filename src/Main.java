import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Player playerX = new Player(Color.X);
        Player playerO = new Player(Color.O);
        Game game = new Game();
        boolean gameIsPlayIng = true;

        printGreeting();

        while (gameIsPlayIng){

            playARound(game,playerX, sc);
            gameIsPlayIng = !game.checkIfMoveWins();
            if(gameIsPlayIng) {
                playARound(game, playerO, sc);
                gameIsPlayIng = !game.checkIfMoveWins();
            }

            if(!gameIsPlayIng){
                gameIsPlayIng = wannaGoAgain(sc);
                game.setUpNewGame();
            }
            if(!game.hasEmptyPieces()){
                System.out.println("It's a draw!");
                gameIsPlayIng = wannaGoAgain(sc);
                game.setUpNewGame();
            }
        }
        sc.close();
        printByeBye();
    }

    static void playARound(Game game, Player player, Scanner sc){
        boolean moveSuccessful = false;

        while (!moveSuccessful){
            game.printPieces();
            moveSuccessful = game.putPieceInSlotAtCol(player.getMove(sc),player.getColor());
        }
    }

    static void printGreeting(){
        System.out.println("*******************************");
        System.out.println("*** Welcome to Connect 4 v0 ***");
        System.out.println("********** by daniel halasz ***");
        System.out.println("********** Player1: X *********");
        System.out.println("********** Player2: O *********");
        System.out.println("*******************************");
        System.out.println();
    }

    static void printByeBye(){
        System.out.println("thank you for playing, see you soon");
    }

    static boolean wannaGoAgain(Scanner sc){
        char again = '#';

        while (!(again == 'y' || again == 'n')){
            try{
                sc.nextLine();
                System.out.println("wanna go again? y/n:");
                again = sc.nextLine().charAt(0);
            } catch (Exception e){
                System.err.println("only y or n please.");
            }
        }
        return again == 'y';
    }
}

