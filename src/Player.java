import java.util.Scanner;

public class Player {

    private final Color color;
    private final Scanner sc = new Scanner(System.in);

    public Player(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    public char getCaption(){
        return color.getCaption();
    }

    public int getMove(){
        int move = -1;

        while (move < 1 || move > 7){
            try {
                System.out.print("Player " + getCaption() + " choose a column from 1 to 7: ");
                move = sc.nextInt();
            } catch (Exception e){
                sc.next();
                System.err.println("Must choose a number between 1 and 7");
            }
        }
        return move;
    }
}
