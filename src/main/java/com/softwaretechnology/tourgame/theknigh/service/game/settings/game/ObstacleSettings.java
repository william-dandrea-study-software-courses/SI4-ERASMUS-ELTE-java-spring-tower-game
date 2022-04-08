package com.softwaretechnology.tourgame.theknigh.service.game.settings.game;

/**
 * @author D'Andréa William
 */
public class ObstacleSettings {

    private final int numberOfObstacles;
    private final int radiusOfObstacles;

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
