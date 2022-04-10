package game.board.entities.playerentities.soldiers;

import game.gamemanaging.Player;
import game.utils.Position;

/**
 * @author D'Andréa William
 */
public class FlightSoldier extends Soldier {

    private boolean ignoreObstacles = true;


    /**
     * Flight soldier entity extended from Soldier
     * @param position
     * @param owner
     * @param price
     * @param healthPoint
     * @param numberOfMoveAtEachRound
     * @param killRewards
     */
    public FlightSoldier(Position position, Player owner, int price, int healthPoint, int numberOfMoveAtEachRound, int killRewards) {

        super(position, owner, price, healthPoint, numberOfMoveAtEachRound, killRewards);
    }


    public boolean getIgnoreObstacle() { return ignoreObstacles; }
}
