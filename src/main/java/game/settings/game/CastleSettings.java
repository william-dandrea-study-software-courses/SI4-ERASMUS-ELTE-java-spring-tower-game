package game.settings.game;

/**
 * @author D'Andréa William
 */
public class CastleSettings {

    private final int initialHealthPoints;
    private final int healthPointsRemovedWhenSoldierReachCastle;

    /**
     * The castle settings
     * @param initialHealthPoints
     * Castle initial health points
     * @param healthPointsRemovedWhenSoldierReachCastle
     * Number of health points removed when a unit reaches the enemy castle
     */
    public CastleSettings(int initialHealthPoints, int healthPointsRemovedWhenSoldierReachCastle) {
        this.initialHealthPoints = initialHealthPoints;
        this.healthPointsRemovedWhenSoldierReachCastle = healthPointsRemovedWhenSoldierReachCastle;
    }

    public int getInitialHealthPoints() {
        return initialHealthPoints;
    }

    public int getHealthPointsRemovedWhenSoldierReachCastle() {
        return healthPointsRemovedWhenSoldierReachCastle;
    }
}
