package game.settings.game;

/**
 * @author D'Andréa William
 */
public class ObstacleSettings {

    private final int numberOfObstacles;
    private final int radiusOfObstacles;

    /**
     * The obstacle settings
     * @param numberOfObstacles
     * the number of the obstacles which will appears on the board
     * @param radiusOfObstacles
     * The radius of the obstacles
     */
    public ObstacleSettings(int numberOfObstacles, int radiusOfObstacles) {
        this.numberOfObstacles = numberOfObstacles;
        this.radiusOfObstacles = radiusOfObstacles;
    }

    public int getNumberOfObstacles() {
        return numberOfObstacles;
    }

    public int getRadiusOfObstacles() {
        return radiusOfObstacles;
    }
}
