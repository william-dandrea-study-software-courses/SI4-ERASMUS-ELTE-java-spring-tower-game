package game.settings.game;

/**
 * @author D'Andréa William
 */
public class GeneralSettings {

    private final int widthBoard;
    private final int lengthBoard;
    private final int radiusToPlaceBuilding;
    private final int enemyForbiddenRadiusForBuilding;

    /**
     * The general settings of the game
     * @param widthBoard
     * The board width
     * @param lengthBoard
     * The board length
     * @param radiusToPlaceBuilding
     * Number of surrounding squares to place a building
     * @param enemyForbiddenRadiusForBuilding
     * Number of surrounding squares of enemy buildings where the opponent cannot place buildings
     */
    public GeneralSettings(int widthBoard, int lengthBoard, int radiusToPlaceBuilding, int enemyForbiddenRadiusForBuilding) {
        this.widthBoard = widthBoard;
        this.lengthBoard = lengthBoard;
        this.radiusToPlaceBuilding = radiusToPlaceBuilding;
        this.enemyForbiddenRadiusForBuilding = enemyForbiddenRadiusForBuilding;
    }

    public int getLengthBoard() {
        return lengthBoard;
    }

    public int getWidthBoard() {
        return widthBoard;
    }

    public int getRadiusToPlaceBuilding() {
        return radiusToPlaceBuilding;
    }

    public int getEnemyForbiddenRadiusForBuilding() {
        return enemyForbiddenRadiusForBuilding;
    }
}
