package game.utils;

/**
 * @author D'Andréa William
 */
public class BoardDimension {

    private final int length;
    private final int width;

    public BoardDimension(int length, int width) {
        this.length = length;
        this.width = width;
    }

    public int getLength() {
        return length;
    }

    public int getWidth() {
        return width;
    }
}
