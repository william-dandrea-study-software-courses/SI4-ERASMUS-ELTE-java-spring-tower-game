package game.board.entities.playerentities.soldiers;

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
    public FlightSoldier(Position position, double price, int healthPoint) {
        super(position, price, healthPoint);
    }

    public boolean getIgnoreObstacle() { return ignoreObstacles; }
}
