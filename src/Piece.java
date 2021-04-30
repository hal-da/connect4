public class Piece {

    private final Color color;

    public Piece(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    public char getCaption(){
        return color.getCaption();
    }

    @Override
    public String toString() {
        return color + "";
    }
}
