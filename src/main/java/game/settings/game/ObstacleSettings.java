package game.settings.game;

/**
 * @author D'Andréa William
 */
public class ObstacleSettings {

    private final int numberOfObstacles;

    /**
     * The obstacle settings
     * @param numberOfObstacles
     * the number of the obstacles which will appears on the board
     */
    public ObstacleSettings(int numberOfObstacles, int radiusOfObstacles) {
        this.numberOfObstacles = numberOfObstacles;
    }

    public int getNumberOfObstacles() {
        return numberOfObstacles;
    }
}
