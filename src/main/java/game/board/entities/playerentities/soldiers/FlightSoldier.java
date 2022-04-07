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
     * @param price
     * @param healthPoint
     */
    public FlightSoldier(Position position, Player owner, double price, int healthPoint, int numberOfMoveAtEachRound) {

        super(position, owner, price, healthPoint, numberOfMoveAtEachRound);
    }


    public boolean getIgnoreObstacle() { return ignoreObstacles; }
}
