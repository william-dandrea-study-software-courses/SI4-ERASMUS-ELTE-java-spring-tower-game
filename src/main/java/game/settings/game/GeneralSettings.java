package game.settings.game;

/**
 * @author D'Andréa William
 */
public class GeneralSettings {

    private final int widthBoard;
    private final int lengthBoard;
    // Number of surrounding squares to place a building
    private final int radiusToPlaceBuilding;
    // Number of surrounding squares of enemy buildings where the opponent cannot place buildings
    private final int ennemyForbiddenRadiusForBuilding;

    public GeneralSettings(int widthBoard, int lengthBoard, int radiusToPlaceBuilding, int ennemyForbiddenRadiusForBuilding) {
        this.widthBoard = widthBoard;
        this.lengthBoard = lengthBoard;
        this.radiusToPlaceBuilding = radiusToPlaceBuilding;
        this.ennemyForbiddenRadiusForBuilding = ennemyForbiddenRadiusForBuilding;
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

    public int getEnnemyForbiddenRadiusForBuilding() {
        return ennemyForbiddenRadiusForBuilding;
    }
}
