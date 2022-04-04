package game.utils;

/**
 * @author D'Andréa William
 */
public class BoardDimension {

    private static int length;
    private static int width;

    /**
     * Game board size
     * @param length
     * board length
     * @param width
     * board width
     */
    public BoardDimension(int length, int width) {
        this.length = length;
        this.width = width;
    }

    public static int getLength() {
        return length;
    }

    public static int getWidth() {
        return width;
    }


}
