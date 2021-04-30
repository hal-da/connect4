public enum Color {
    X('X'),O('O'),Empty(' ');

    final private char caption;

    Color(char caption) {
        this.caption = caption;
    }

    public char getCaption() {
        return caption;
    }

    @Override
    public String toString() {
        return caption + "";
    }
}
