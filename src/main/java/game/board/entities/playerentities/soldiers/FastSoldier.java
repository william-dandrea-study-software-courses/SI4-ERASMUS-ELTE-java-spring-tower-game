package game.board.entities.playerentities.soldiers;

import game.gamemanaging.Player;
import game.utils.Position;

/**
 * @author D'Andréa William
 */
public class FastSoldier extends Soldier {

    private int numberOfTileHeCanJump;

    /**
     * Fast soldier entity extended from Soldier
     * @param position
     * @param owner
     * @param price
     * @param healthPoint
     * @param numberOfMoveAtEachRound
     * @param killRewards
     * @param numberOfTileHeCanJump
     * The number of tile (step) the fast soldier can move per time
     */
    public FastSoldier(Position position, Player owner, int price, int healthPoint, int numberOfMoveAtEachRound, int killRewards, int numberOfTileHeCanJump) {
        super(position, owner, price, healthPoint, numberOfMoveAtEachRound, killRewards);
        this.numberOfTileHeCanJump = numberOfTileHeCanJump;
        this.setNumberOfMoveAtEachRound(numberOfTileHeCanJump);
    }


    public int getNumberOfTileHeCanJump() {
        return numberOfTileHeCanJump;
    }

    public void setNumberOfTileHeCanJump(int numberOfTileHeCanJump) {
        this.numberOfTileHeCanJump = numberOfTileHeCanJump;
    }
}
