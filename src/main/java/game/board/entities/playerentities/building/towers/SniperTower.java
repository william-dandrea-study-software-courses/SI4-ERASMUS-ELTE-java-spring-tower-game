package game.board.entities.playerentities.building.towers;

import game.utils.Position;

/**
 * @author D'Andréa William
 */
public class SniperTower extends Tower{


    public SniperTower(Position position, float price, int shootingRange, int simultaneousStrike, float percentageRewardIfDestroyed) {
        super(position, price, shootingRange, simultaneousStrike, percentageRewardIfDestroyed);
    }
}
