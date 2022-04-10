package game.settings.soldiers;

/**
 * @author D'Andréa William
 */
public class FastSoldierSettings extends SoldierSettings {

    private final int numberOfTileHeCanJump;

    /**
     * The fast soldier settings
     * @param price
     * @param initialHealthPoints
     * @param numberOfMovesAtEachRound
     * @param numberOfTileHeCanJump
     * The number of tile the fast soldier can jump (instead of moving one tile by one tile)
     */
    public FastSoldierSettings(int price, int initialHealthPoints, int numberOfMovesAtEachRound, int killRewards, int numberOfTileHeCanJump) {
        super(price, initialHealthPoints, numberOfMovesAtEachRound, killRewards);
        this.numberOfTileHeCanJump = numberOfTileHeCanJump;
    }

    public int getNumberOfTileHeCanJump() {
        return numberOfTileHeCanJump;
    }


}
