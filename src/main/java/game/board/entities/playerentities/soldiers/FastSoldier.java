package game.board.entities.playerentities.soldiers;

import game.utils.Position;

/**
 * @author D'Andréa William
 */
public class FastSoldier extends Soldier {

    private int numberOfTileHeCanJump;

    /**
     * Fast soldier entity extended from Soldier
     * @param position
     * @param price
     * @param healthPoint
     * @param numberOfTileHeCanJump
     * The number of tile (step) the fast soldier can move per time
     */
    public FastSoldier(Position position, double price, int healthPoint, int numberOfTileHeCanJump) {
        super(position, price, healthPoint);
        this.numberOfTileHeCanJump = numberOfTileHeCanJump;
    }

    public int getNumberOfTileHeCanJump() {
        return numberOfTileHeCanJump;
    }

    public void setNumberOfTileHeCanJump(int numberOfTileHeCanJump) {
        this.numberOfTileHeCanJump = numberOfTileHeCanJump;
    }
}
