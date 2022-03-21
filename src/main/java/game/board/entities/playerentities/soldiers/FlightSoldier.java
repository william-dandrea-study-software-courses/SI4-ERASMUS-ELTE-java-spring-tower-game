package game.board.entities.playerentities.soldiers;

import game.utils.Position;

/**
 * @author D'Andréa William
 */
public class FlightSoldier extends Soldier {

    private boolean ignoreObstacles = true;

    public FlightSoldier(Position position, double price, int healthPoint) {
        super(position, price, healthPoint);
    }

    public boolean getIgnoreObstacle() { return ignoreObstacles; }
}
